package net.brian.coding.java.core.jdk.jvm.oom;

import java.nio.ByteBuffer;
/**
 * nio���¾���һЩ����ֱ��ͨ��API����ֱ���ڴ�ķ���������ֱ�������á�
 * 
 * ����ʾ�����ǣ�ֱ������ϵͳ�ڴ�ʹ�java�������ڴ��ĸ�����ʱ���Լ�ֱ���ڴ�ķ����ٶȺͶ��ڴ��ĸ����죺
 * 
 * a.ֱ������ϵͳ�ڴ�ʹ�java�������ڴ��ĸ�����ʱ�䣬��������
 * bufferAllocate time:: 82
 * directAllocate time:: 193
 * bufferAllocate time:: 156
 * directAllocate time:: 178
 * 
 * b.ֱ���ڴ�ķ����ٶȺͶ��ڴ��ĸ����죬��������
 * 
 * 
 * ���ݳ����������Կ���������ֱ���ڴ���ķ�ʱ�䣬����ֱ���ڴ�ķ����ٶ�ȴ�ȶ��ڴ��
 * ��˵ó����ۣ�ֱ���ڴ��ʺ�����������٣����ʽ�Ƶ���ĳ��ϡ�����ڴ�ռ䱾����ҪƵ�����룬�򲢲��ʺ�ʹ��ֱ���ڴ�
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
		// ������������֮��ǵ���д��ע����ģ�b.ֱ���ڴ�ķ����ٶȺͶ��ڴ��ĸ����죬��������
		AllocateDirectBuffer demo = new AllocateDirectBuffer();
		// ����������������
		demo.bufferAccess();
//		demo.directAccess();
		// ��β�����ʵ��ģ��
		demo.bufferAccess();
//		demo.directAccess();
	}
}
