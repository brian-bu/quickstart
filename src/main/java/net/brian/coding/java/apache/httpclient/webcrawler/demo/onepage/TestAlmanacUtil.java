package net.brian.coding.java.apache.httpclient.webcrawler.demo.onepage;

public class TestAlmanacUtil {

	public static void main(String args[]) {
		Almanac almanac = AlmanacUtil.getAlmanac();
		System.out.println("����ʱ�䣺" + almanac.getSolar());
		System.out.println("ũ��ʱ�䣺" + almanac.getLunar());
		System.out.println("��ɵ�֧��" + almanac.getChineseAra());
		System.out.println("�ˣ�" + almanac.getShould());
		System.out.println("�ɣ�" + almanac.getAvoid());
	}
}