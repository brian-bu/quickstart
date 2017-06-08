package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;
/**
 * 
 * ���������������ԭ�����ڵ�����join
 *
 */
public class DeadLockingByStaticThreadInitWithJoin
{
	static
	{
		//���������ڲ������������߳�
		Thread t = new Thread()
		{
			//�������߳̽�website��������Ϊwww.leegang.org
			public void run()
			{
				System.out.println("����run����");
				System.out.println(website);
				website = "www.leegang.org";
				System.out.println("�˳�run����");
			}
		};
		t.start();
		try
		{
			//����t�߳�
			t.join();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	//����һ����̬field���������ʼֵΪwww.crazyit.org
	static String website = "www.crazyit.org";
	public static void main(String[] args) 
	{
		System.out.println(DeadLockingByStaticThreadInitWithJoin.website);
	}
}
