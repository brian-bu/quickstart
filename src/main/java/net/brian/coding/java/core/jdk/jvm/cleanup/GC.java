package net.brian.coding.java.core.jdk.jvm.cleanup;

import java.util.concurrent.TimeUnit;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item07: Avoid finalizers
 * 
 * 关于finalize方法的使用，实际上这么做只是为了示范一下jvm的回收机制
 * 不要被System.gc和System.runFinalization迷惑，它们只是增加了finalize方法被执行的机会
 * 但并不能保证终结方法一定会在期望的时机被执行，也就是说这个是不受程序员控制的
 * 此外对于System.runFinalizersOnExit和Runtime.runFinalizersOnExit虽然能保证finalize一定被执行
 * 但是它们都有致命缺陷，已被废弃了
 * 
 * 在实际开发中并不推荐覆盖及使用finalize方法，finalize方法主要有如下缺点：
 * a.JVM延迟执行finalize方法：
 * 因此用finalize方法关闭已打开的文件是个严重的错误，因为大量文件会保留在打开状态
 * 另一个程序再尝试打开这个文件，将不能再获取打开文件的权限，可能导致程序运行失败
 * b.终结方法线程优先级较低：
 * 因此对于GUI这样的程序，图形对象的终结速度达不到进入队列的速度
 * c.使用finalize方法有严重的性能损失
 * 
 * 因此不要依赖finalize方法进行程序的清理工作，而是要编写显式的终止方法
 * 典型的显式终止方法：每个IO流几乎都有close方法
 * 显式的终止方法通常和finally一起使用确保及时终止
 * 
 * Effective Java上介绍了几种使用finalize方法的情况，详情见原书：
 * a.安全网
 * b.终止非关键的本地资源
 * c.终结方法守卫者
 *
 */
public class GC {

	public static GC SAVE_HOOK = null;

	public void test() {
		System.out.println("Yes , I am still alive");
	}
	
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new GC();
		
		SAVE_HOOK = null;
		// 调用它的作用只是建议垃圾收集器启动，清理无用的对象释放内存空间，但是GC的启动时机并不是一定的
		System.gc();
		// 调用这个方法倒是一定会触发GC，但是这个方法现在已经不用了
		// System.runFinalizersOnExit(false);
		TimeUnit.MICROSECONDS.sleep(500);
		
//		if (null != SAVE_HOOK) { // 此时对象应该处于(reachable, finalized)状态
			SAVE_HOOK.test();
//		} else {
//			System.out.println("No , I am dead");
//		}
//		
//		SAVE_HOOK = null;
//		System.gc();
//		TimeUnit.MICROSECONDS.sleep(500);
//		
//		if (null != SAVE_HOOK) {
//			SAVE_HOOK.test();
//		} else {
//			System.out.println("No , I am dead");
//		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("execute method finalize()");
		SAVE_HOOK = this;
	}
}