package net.brian.coding.java.core.jdk.jvm.oom;

import java.nio.ByteBuffer;
/**
 * nio包下就有一些可以直接通过API申请直接内存的方法，这里直接拿来用。
 * 
 * 本例示范的是：直接申请系统内存和从java堆申请内存哪个更耗时间以及直接内存的访问速度和堆内存哪个更快：
 * 
 * a.直接申请系统内存和从java堆申请内存哪个更耗时间，输出结果：
 * bufferAllocate time:: 82
 * directAllocate time:: 193
 * bufferAllocate time:: 156
 * directAllocate time:: 178
 * 
 * b.直接内存的访问速度和堆内存哪个更快，输出结果：
 * 
 * 
 * 根据程序的输出可以看出：还是直接内存更耗费时间，但是直接内存的访问速度却比堆内存快
 * 因此得出结论：直接内存适合申请次数较少，访问较频繁的场合。如果内存空间本身需要频繁申请，则并不适合使用直接内存
 *
 */
public class AllocateDirectBuffer {
	public void directAccess() {
		long startTime = System.currentTimeMillis();
		ByteBuffer b = ByteBuffer.allocateDirect(100000);
		for(int i = 0; i < 100; i++) {
			for(int j = 0; i < 9; j++) {
				b.putInt(j);
			}
			b.flip();
			for(int j = 0; j < 9; j++) {
				b.getInt();
			}
			b.clear();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("directAccess time:: " + (endTime - startTime));
	}
	public void bufferAccess() {
		long startTime = System.currentTimeMillis();
		ByteBuffer b = ByteBuffer.allocate(100000);
		for(int i = 0; i < 100; i++) {
			for(int j = 0; i < 9; j++) {
				b.putInt(j);
			}
			b.flip();
			for(int j = 0; j < 9; j++) {
				b.getInt();
			}
			b.clear();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("bufferAccess time:: " + (endTime - startTime));
	}
	public void directAllocate() {
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 200000; i++) {
			@SuppressWarnings("unused")
			ByteBuffer b = ByteBuffer.allocateDirect(1000);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("directAllocate time:: " + (endTime - startTime));
	}
	public void bufferAllocate() {
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 200000; i++) {
			@SuppressWarnings("unused")
			ByteBuffer b = ByteBuffer.allocate(1000);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("bufferAllocate time:: " + (endTime - startTime));
	}
	public static void main(String[] args) {
		//TODO: Exception in thread "main" java.nio.BufferOverflowException
		// 解决了这个问题之后记得填写类注释里的：b.直接内存的访问速度和堆内存哪个更快，输出结果：
		AllocateDirectBuffer demo = new AllocateDirectBuffer();
		// 这次用于虚拟机热身
		demo.bufferAccess();
//		demo.directAccess();
		// 这次才是真实的模拟
		demo.bufferAccess();
//		demo.directAccess();
	}
}
