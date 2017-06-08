package net.brian.coding.java.core.jdk.valueclasses;

import net.brian.coding.java.utils.StringUtil;

/**
 * �����StringBuilder��StringBuffer������API���н���
 * ���������������ݶ�Ҫ�õ�java.lang.AbstractStringBuilder.expandCapacity(int)
 * �������������API��������������
 * ע�⣺StringBuilder��StringBuffer�Ĺ�ͬ������java.lang.AbstractStringBuilder
 * 
 * >>>>> Դ�������
 * 
 * ���е���append����������ensureCapacityInternal����Դ�����£� 
 * if (minimumCapacity - value.length > 0) expandCapacity(minimumCapacity);
 * �ֶ�����ensureCapacity�������������Դ���ڲ��ǣ� 
 * if (minimumCapacity > value.length) {expandCapacity(minimumCapacity); } 
 * ��������۵���append�Զ����ݻ����ֶ�����ensureCapacity����
 * ֻҪ����minimumCapacity > value.length��������õ�expandCapacity�������
 * 
 * �ҿ�expandCapacity��������Ĺؼ�Դ�룺 
 * void expandCapacity(int minimumCapacity) { 
 *	 int newCapacity = value.length * 2 + 2; 
 *	 if (newCapacity - minimumCapacity < 0)
 *	 newCapacity = minimumCapacity; 
 * 	value = Arrays.copyOf(value, newCapacity);
 * ��Դ����Կ���ֻҪ������expandCapacity���������쳣��һ�������Arrays.copyOf
 * 
 * >>>>> ��������Դ����Ķ����Եó��������£�
 * 
 * >>>>> �����ֶ����ݣ�
 *  ��һ������ֶ�����ensureCapacity���Ҵ�������minimumCapacity�㹻��
 * �ᵼ��value.length�ܴ�Ҳ���Ǵ���һ���ܴ�����飬����100000 ��������ʹ����append
 * ��ô��ʱminimumCapacity = count + len Ҳ�����¾��ַ���ƴ��һ����ܳ���
 *  ���ʱ��minimumCapacity - value.length��ԶС��0��
 * ���Բ������expandCapacity��Ҳ�Ͳ�����õ�Arrays.copyOf
 * 
 * >>>>> ��������append�Զ����ݣ� 
 * �����Ĭ�ϵĹ�����Ĭ�ϵ�capacity�� 
 * AbstractStringBuilder(int capacity) { value = new char[capacity]; }
 * �����������������buffer��Ĭ�Ϲ������������16����super(16); 
 * Ҳ����˵Ĭ�ϻᴴ��һ������16���������������append����������ensureCapacityInternal
 * ����ԭ��������������ӵ��ַ����ĳ���Ϊ������ݣ���ensureCapacityInternal(count + len);
 * ���������Ҳ����ÿ�ζ���minimumCapacity - value.length > 0 
 * Ҳ��count + len - value.length > 0 �����ͻ�ÿ�ζ���Ҫ���ݲ�����Arrays.copyOf������Ӱ������
 * ����Ӱ��count + len - value.length > 0�Ļ�������len������� 
 * Ҳ����ÿ���������ַ�������len��Խ��Խ���Ҵ���value.length
 * 
 */
public class ExpandCapacityInAbstractStringBuilder {
	// ����StringBuffer��StringBuilder����ͬ�������ݻ��ƣ�������Builder��Ϊʾ������
	private StringBuilder autoExpandingBuilder = new StringBuilder(1);
	private StringBuilder manualExpandingBuilder = new StringBuilder(1);
	private int autoExpandingLength;
	private int times;
	
	public void setLength(int length) {
		this.autoExpandingLength = length;
	}
	
	public void setTimes(int times) {
		this.times = times;
	}

	// ����append������ʱ���Զ�����
	public long testAutoExpanding() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			autoExpandingBuilder.append(StringUtil.createRandomString(autoExpandingLength));
			// Point 1��ȷ��count + len - value.length > 0����ÿ��ѭ������������
			int newLength = autoExpandingBuilder.length() + autoExpandingLength + 1;
			int valueLength = autoExpandingBuilder.toString().length();
			// ȷ���ڳ�������������int���������ʱ��ʱͣ����
			// ��Ϊ���ﵽInteger.MAX_VALUE֮�������Ӿͱ���˸���
			if(newLength < 0 || valueLength < 0){
				System.out.println("The append method executed for " + (i + 1) + " times.");
				// Point 2��ȷ���ֶ����ݺ��Զ����ݶ�ִ������ͬ�Ĵ���
				setTimes(i);
				break;
			}
			if (newLength > valueLength)
				setLength(newLength);
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

	// �ֶ�����ensureCapacity��������
	public long testManualExpanding() {
		long start = System.currentTimeMillis();
		// Ϊ�������ֶ����ݵ�Ч����ֱ�����õ�int���������ȷ���ڳ���ͣ������ʱ�򲻻ᴥ���Զ�����
		manualExpandingBuilder.ensureCapacity(Integer.MAX_VALUE);
		// ���Զ�����ʹ��ͬ���Ĵ��룬Ψһ�Ĳ�ͬ�����ַ����ĳ��Ȳ���Ҫ��֤���ϴ����Զ����ݵ�����
		// ����count + len - value.length > 0
		// ����ֶ����ݵĴ���ҲҪ�������������ô�����һִ�оͻ����ϴﵽ���ֵ�˳�
		// �����ֶ�����ֱ�Ӱ�builder���������õ������ �Զ�������һ��һ���������������
		for (int i = 0; i < times; i++) {
			manualExpandingBuilder.append(StringUtil.createRandomString(10));
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}
	
	public static void main(String[] args) {
		ExpandCapacityInAbstractStringBuilder demo = new ExpandCapacityInAbstractStringBuilder();
		System.out.println("Total time:: " + demo.testAutoExpanding());
		System.out.println("Total time:: " + demo.testManualExpanding());
	}
}