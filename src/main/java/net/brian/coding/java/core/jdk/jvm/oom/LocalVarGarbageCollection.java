package net.brian.coding.java.core.jdk.jvm.oom;
/**
 * 本例意在展示局部变量对垃圾回收的影响，局部变量表是栈帧的重要组成部分之一，用于保存函数参数以及局部变量
 * 函数调用结束方法退出时，随栈帧销毁而销毁。局部变量表中的变量也是重要的垃圾回收根节点
 * 只要被局部变量表中直接或间接引用的对象都是不会被回收的。因此，理解局部变量表对理解垃圾回收也有一定帮助
 * 
 * 对于触发OOM最好的方法就是创建尽可能大的对象，必要的话可以利用for循环批量创建
 * 本例中每个方法的局部变量都创建一个6MB的byte数组，并使用局部变量引用
 * 
 * 本例使用虚拟机参数：-XX:+PrintGC -Xmx20m -Xms10m
 * 打印出的GC日志：
 * [GC (System.gc())  7062K->6800K(9728K), 0.0013850 secs]
 * [Full GC (System.gc())  6800K->610K(9728K), 0.0089410 secs]
 * 
 * 这里对日志进行分析：
 * a.可以看出这里总共分给Java堆9728K内存，大约10M，这是参数-Xms10m产生的结果
 * b.第一次垃圾回收仅回收了1M的空间，很显然没有回收大对象
 * c.接着触发了Full GC，可以看出回收之前6800k，回收之后610k，差了大概6M左右，这次回收真正回收了大对象
 * d.像6M这么大的对象，一般是直接放到老年代的，调用System.gc()第一次触发的是年轻代的GC，直到触发了Full GC的时候才回收老年代
 * 添加-XX:+PrintGCDetails运行相同的程序打印出详细的GC日志可以看出：
 * [GC (System.gc()) [PSYoungGen: 918K->488K(2560K)] 7062K->6800K(9728K), 0.0025846 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 * [Full GC (System.gc()) [PSYoungGen: 488K->0K(2560K)] [ParOldGen: 6312K->610K(7168K)] 6800K->610K(9728K), [Metaspace: 2569K->2569K(1056768K)], 0.0077014 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
 * Heap
 *  PSYoungGen      total 2560K, used 20K [0x00000000ff980000, 0x00000000ffc80000, 0x0000000100000000)
 *   eden space 2048K, 1% used [0x00000000ff980000,0x00000000ff985360,0x00000000ffb80000)
 *   from space 512K, 0% used [0x00000000ffb80000,0x00000000ffb80000,0x00000000ffc00000)
 *   to   space 512K, 0% used [0x00000000ffc00000,0x00000000ffc00000,0x00000000ffc80000)
 *  ParOldGen       total 7168K, used 610K [0x00000000fec00000, 0x00000000ff300000, 0x00000000ff980000)
 *   object space 7168K, 8% used [0x00000000fec00000,0x00000000fec98930,0x00000000ff300000)
 *  Metaspace       used 2575K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 285K, capacity 386K, committed 512K, reserved 1048576K
 * 大部分的空间都没有利用，只有老年代ParOldGen有一行：object space 7168K, 8% used，对象空间占用7M左右，很明显，这个大对象回收前就在这里
 */
public class LocalVarGarbageCollection {
	private static final int BIG_SIZE = 6 * 1024 * 1024;
	// 申请空间后立即回收，因为这里是强引用因此无法回收，属于内存泄漏的典型
	public void localVarGc1() {
		@SuppressWarnings("unused")
		byte[] a = new byte[BIG_SIZE];
		System.gc();
	}
	// 将对象声明为实例域又怕线程不安全，声明在方法里又怕内存泄漏，这可怎么办呢？
	// 这里给出一种思路：用完就清理，清理手段之一就是置空，失去了强引用，大对象a就可以被回收了
	public void localVarGc2() {
		@SuppressWarnings("unused")
		byte[] a = new byte[BIG_SIZE];
		a = null;
		System.gc();
	}
	// 这里也存在内存泄漏，即便局部代码块使得局部变量a失效，但是局部变量表依旧保存着a
	public void localVarGc3() {
		{
			@SuppressWarnings("unused")
			byte[] a = new byte[BIG_SIZE];
		}
		System.gc();
	}
	@SuppressWarnings("unused")
	public void localVarGc4() {
		{
			byte[] a = new byte[BIG_SIZE];
		}
		int c = 10;
		System.gc();
	}
	// 虽然在localVarGc1中的垃圾回收没有什么效果，但是这里已经是localVarGc5
	// 这时localVarGc1方法已经退出栈，相应的栈帧也就被清除，栈帧里的局部变量表同样被清除
	// 这个时候localVarGc1创建的对象a已经失去存在的意义等待垃圾回收，这时在localVarGc5再调用gc就真的可以回收了
	// 从这里也看出来：只要单次在方法内部的对象创建和使用不存在内存泄漏就可以，方法调用结束就没必要考虑方法内部内存泄漏了
	public void localVarGc5() {
		localVarGc1();
		System.gc();
	}

	public static void main(String[] args) {
		LocalVarGarbageCollection gc = new LocalVarGarbageCollection();
		gc.localVarGc4();
	}
}
