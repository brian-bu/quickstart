package net.brian.coding.java.core.jdk;

/**
 * Javaһֱ��ֵ���ݡ����ҵ��ǣ����Ǿ�����ָ��������ã�����������Ǳ����Ρ���Ϊ��Щ����Ҳ��ͨ��ֵ���ݵ�
 * 
 * ����java�ķ�����
 * ֵ���ݻ������ô��ݶ���ڵ��÷�����ʱ�򴫲ε��������ʱJava��ֻ�а�ֵ���ݣ�û�а����ô���
 * ֵ���ݵľ����ǣ����ݵ��Ǵ洢��Ԫ�е����ݣ����ǵ�ַ�������ã����������ⲿ�����Ķ����ڴ��ݸ�����ʱ���ö�������ݿ����ڱ����õķ����иı�
 * ���ö��������(�������õĸ���)����Զ����ı�ġ����� StringBuffer buffer = new StringBuffer("original");
 * �������buffer�ڵ��÷���ǰ��ʼ��ָ��new StringBuffer("original")
 * �ı�һ�������״̬����Ӱ�쵽���к͸ö���ı����йصĴ��룬���籾����buffer�Ķ����״̬�ĳ�buffer = new StringBuffer("current");
 * ��ʱ���а������buffer��Ϊ�����ķ����Լ�ָ��buffer�����ö����ܵ�Ӱ��
 * 
 * ����java�Ķ���
 * ʹ���������͵ĸ�ֵ��佫�ᴴ��һ�������õĸ�������ֵ��䲻�ᴴ���µĶ��󣬶�ֻ�Ǵ�����һ��ָ��ĳ���Ѵ��ڵĶ�������ã���������
 * ԭʼ������������ֵ���ݣ����磺���x��y��ԭʼ�������͵ı�������x=y�Ὣy��ֵ���Ƶ�x�У�������������ͣ����Ƶ������ö���ʵ�ʵ�ֵ
 * 
 * ���ǻ������������ô���תΪֵ���ݵķ�����Ҳ�����Ӷ��ߵ�Ŧ����final�ؼ��֡�����Ϊfinal�������źͻ�������һ������������Ϊ��һ����ֵ�Ͳ��ɱ���
 * ����String�������������͵�Ŀ���Ƿ�װ�����ֵ���Ա㽫���ԭʼ��������һ�����ڸ�ֵ��䡢��Ϊ�����Ĳ�������ֵ
 * һ�㶼��ϣ��String��ֵ�ı䣬����Ҳ��������ϣ��ʹ�ÿɱ��ַ�������������ʱ��javaΪ�����ṩ��StringBuffer��StringBuilder
 * Java���������ǿɱ�ģ���Ϊ����ȷʵ��Ҫ�ı�������Ԫ�ص�ֵ����Ҳ����Щ�����Ҫ����ʹ�ò��ɱ�����飬���ʱ��javaΪ�����ṩVector��ArrayList��
 *
 */
public class PassByValueOrByReference {

	public static void main(String[] args) {
		// ���øú���
		int num = 5;
		System.out.println(num);
		changePrimitive(num);
		System.out.println(num);

		StringBuffer buffer = new StringBuffer("original");
		// �������Զ����ԭ����new StringBuffer("original")��ֵ
		// ����ȥ��bufferָ����һ���µĶ���ԭ����û�仯��
		changePointer(buffer);
		System.out.println(buffer);
		// �������Զ����ԭ����new StringBuffer("original")��ֵ
		// ����ȥ��buffer����ָ��ԭ����ͨ��append�ı�����ֵ
		changeOriginal(buffer);
		System.out.println(buffer);
	}

	/**
	 * ����һ�����ڴ��п���������洢�ռ䣺
	 * һ��淽�����õ�ʱ�򴫽�����ʵ��num��һ��淽��ѹ��ջ��ʱ���������β�int x 
	 * ����������ô��x������Ӱ�쵽ԭ��num��ֵ
	 * ��������ڷ����ⲿ����num�������۵��÷���֮ǰ����֮�󶼲���ı�num��ֵ
	 * 
	 * �ڷ��������ϼ�final��������ȷ��num���ᱻ�ı䣬����java��ֵ���ݵĻ��Ʊ�֤��num�ڵ��÷���ǰ����������
	 * �ڷ����β��ϼ�final�ؼ��֣�ֻ�Ǳ�֤�����βδӵ��÷�����ʼ��ֵ֮��Ͳ������¸�ֵ
	 * 
	 * ����������һ�仰��final���εĻ������͵ı���һ����ʼ���Ͳ����ٸ�ֵ�ˣ��������Ͳ����Ըı����õ��ǿ��Ըı������
	 * ���ǻ������ͱ������һ��ֵ������ı����ֵ�����൱�ڸı������ã�����IntegerΪ1��Ϊ2��ʱ��ָ����ǲ�ͬ�Ķ���
	 * 
	 * �����������β����ջ��ǻ����ŷ���ִ�н�����������������������ظ��������÷����߶Է�������󷵻�ֵ����Ӱ����ô��û��Ҫ����Ϊfinal
	 * ���������ʾ����
	 * @see net.brian.coding.java.core.jdk.keywords.FinalClass.testFinalParam(int)
	 */
	public static void changePrimitive(int x) {
		x = x * 2;
	}

	/**
	 * ���ݹ�ȥ�����ý��в���ʹ��ԭ���Ķ������˱仯����ʱ�����൱��û�ı����õ�ָ���ǰ���¸ı��˶���
	 * �൱��changeOriginal(buffer) --> StringBuffer bufferStr = buffer --> bufferStr.append(" pointer");
	 * 
	 * ����������һ�仰��final���ε����ñ���һ����ʼ���Ͳ��ܸı����ö����ˣ����Ƕ�������Ըı� 
	 */
	public static void changeOriginal(StringBuffer bufferStr) {
		bufferStr.append(" pointer");
	}

	/**
	 * ���ݹ�ȥ������ָ�����µĶ�����ôԭ���Ķ����Ǵ��ڵ��Ҳ��䣬���÷���ǰ��ԭ���Ķ���û���κα仯
	 * �൱��changeOriginal(buffer) --> StringBuffer bufferStr = buffer --> bufferStr = new StringBuffer("new");
	 * Ҳ����˵������ѹ��ջ��ʱ����bufferStr��buffer��������ͬʱָ���� new StringBuffer("original")�������
	 * ��������е���String a = "hello"; a = "world";��Ȼa��ֵ�ı��ˣ����ǳ�������"hello"��"world"���������ǲ����
	 * aֻ�����Ǹı��˶�������ã���û�иı��ڴ��ж����ֵ
	 * ��˶��ڱ�������
	 * �ڵ��÷���֮���ٴ�ʹ�øö����û���ø÷���ʱһ�������﷽�����൱�ڸı���ԭ�ж�������ã���û�иı�ԭ�еĶ���
	 */
	public static void changePointer(StringBuffer bufferStr) {
		bufferStr = new StringBuffer("new");
	}

}