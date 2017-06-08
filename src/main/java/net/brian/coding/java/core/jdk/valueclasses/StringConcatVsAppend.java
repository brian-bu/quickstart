package net.brian.coding.java.core.jdk.valueclasses;
/**
 * 
 * �������³������������ÿ�ζ���concatֵ������buffer����С����builder
 * 
 * ����builder��buffer��������synchronized�ؼ��ִ�����������ģ���������ֻ����concat��buffer��append����
 * 
 * ����concat��Ӱ�����ܵĺ���Դ�����£�
 *  int len = value.length;
        char buf[] = Arrays.copyOf(value, len + otherLen);
        str.getChars(buf, len);
        return new String(buf, true);
 * �������ܵķ������£�
 * �϶������һ��Arrays.copyOf
 * ������һ��getChars�ĵ��ã���������ײ��Ƕ�System.arraycopy�ĵ��ã���ε�����ʮ�ֺ����ܵ�
 * return��ʱ��Ҫ����һ���µ�String����
 * 
 * ����buffer��Ӱ�����ܵĺ���Դ�����£�
 *  int len = str.length();
		ensureCapacityInternal(count + len);
 		str.getChars(0, len, value, count);
 * ��δ������buffer��builder���õĸ��෽��java.lang.AbstractStringBuilder.append(String)
 * ����len��append����ַ����ĳ��ȣ����ensureCapacityInternal�������ԭ���ַ����ĳ��ȼ�������ӵ��ַ����ĳ���
 * �������ܵ���Ҫ�������£�
 * ensureCapacityInternal�ײ����һ��Arrays.copyOf�ĵ���
 * ͬconcat������Ҳ����һ��getChars�ĵ���
 * 
 * �ɴ˿ɼ�concat��buffer�ײ������Ӱ���������Ҫ���ڣ�
 * buffer����Ҫnewһ��String���󣬶�concatÿ�ζ�newһ�����ⲻ���˷ѿռ䣬����Ҳ��һ���̶�Ӱ������
 * ��������������ַ����ļ����ӾͿ�����concat���Ͼ�û��ҪΪ���ֶ�����һ��buffer����������ʡ�˺ܶ����
 */
public class StringConcatVsAppend {
	public static void main(String[] args) {
		long[] timeStringconcat = new long[10];
		long[] timeStringBuffer = new long[10];
		;
		long[] timeStringBuilder = new long[10];
		;
		for (int x = 0; x < 10; x++) {
			System.out.println("-----------��" + (x + 1) + "��------------------");
			long startTime1 = System.currentTimeMillis();
			String str1 = "�ַ���ƴ�� ";
			for (int i = 0; i < 100000; i++) {
				str1.concat("Hello world!");
				str1.concat("Hello world!");
				str1.concat("Hello world!");
			}
			long endTime1 = System.currentTimeMillis();
			timeStringconcat[x] = (endTime1 - startTime1);
			System.out.println("ʹ��String.concat()����10W��ƴ�Ӻ�ʱ��" + timeStringconcat[x] + "����");

			long startTime2 = System.currentTimeMillis();
			StringBuffer sbr1 = new StringBuffer("�ַ���ƴ��");
			/**
			 * @see: net.brian.coding.jdk.valueclasses.ExpandCapacityInAbstractStringBuilder
			 */
			sbr1.ensureCapacity(100000);
			for (int i = 0; i < 100000; i++) {
				sbr1.append("Hello world!");
				sbr1.append("Hello world!");
				sbr1.append("Hello world!");
			}
			long endTime2 = System.currentTimeMillis();
			timeStringBuffer[x] = (endTime2 - startTime2);
			System.out.println("ʹ��StringBuffer.append()����10W��ƴ�Ӻ�ʱ��" + timeStringBuffer[x] + "����");

			long startTime3 = System.currentTimeMillis();
			// ��forѭ���ⲿ����StringBuilder����forѭ���ڲ�����append
			StringBuilder sbd1 = new StringBuilder("�ַ���ƴ��");
			/**
			 * 
			 * ���ڵ�ǰ�ĳ���ʾ���� ÿ����ӵĶ��ǳ�������Һ�С���ַ���
			 * ��������append�Զ����ݻ����ֶ�����Ӱ�춼����
			 * ����Ƿ����sbr1.ensureCapacity(100000);����Ӱ����
			 * �����Ƿ����ensureCapacity���ֵ����ܲ�����дһ����˵��
			 * 
			 * @see: net.brian.coding.jdk.valueclasses.ExpandCapacityInAbstractStringBuilder
			 */
			sbd1.ensureCapacity(100000);
			for (int i = 0; i < 100000; i++) {
				sbd1.append("Hello world!");
				sbd1.append("Hello world!");
				sbd1.append("Hello world!");
			}
			long endTime3 = System.currentTimeMillis();
			timeStringBuilder[x] = (endTime3 - startTime3);
			System.out.println("ʹ��StringBuilder.append()����10W��ƴ�Ӻ�ʱ��" + timeStringBuilder[x] + "����");
			System.out.println("----------------------------------");
		}
		System.out.println("ƽ��ֵ��");
		long result = 0l;
		for (long avg : timeStringconcat) {
			result += avg;
		}
		System.out.println(result / 10.0);
		result = 0l;
		for (long avg : timeStringBuffer) {
			result += avg;
		}
		System.out.println(result / 10.0);
		result = 0l;
		for (long avg : timeStringBuilder) {
			result += avg;
		}
		System.out.println(result / 10.0);
	}
}