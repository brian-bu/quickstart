package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * 
 * ������Ϊһ����ʼ��˳���ʵս��������Ҫ���Ƿ���ʵ�������ĳ�ʼ����˳������ű�ע
 * �жϾ�̬�ͷǾ�̬������ʼ��˳��ĺ���˼�룺
 * �����Ĵ��볢�Բ���ʵ�������ʷ�static�ı������������ᱨ����Ϊ��Щ������û�б�������������û�и��κ�ʵ��������
 *
 */
public class InitializationInPractice {
	public static void main(String[] args)
	{
		// Point 1: ͨ��Price��INSTANCE����currentPriceʵ������
		// �˴ε���ʱinitPrice��û��ֵ
		System.out.println(Price.INSTANCE.currentPrice);
		// ��ʽ����Priceʵ��
		Price p = new Price(2.8);
		// Point 2: ͨ�����Ǵ�����Priceʵ������currentPriceʵ������
		// �˴ε���ʱinitPrice�϶��Ѿ���ֵ20��
		System.out.println(p.currentPrice);
	}
}
class Price
{
	//���Ա��Priceʵ��
	/*(1)*/final static Price INSTANCE = /*(2)*/new Price(2.8);
	//�ڶ���һ���������
	/*(5)*/static double initPrice = 20;
	//�����Price��currentPriceʵ������
	/*(3)*/double currentPrice;
	public Price(double discount)
	{
		//���ݾ�̬��������ʵ������
		/*(4)*/currentPrice = initPrice - discount;
	}
}
