package net.brian.coding.java.core.oop.classesinterfaces;
/**
 * 
 * �������������ڲ�ʵ�ֲ�������ϸ�ڣ�private��protected������main��������final����
 * �ӿ��Ǿ��Գ�������һ��ϸ�ڵģ�Ĭ��public��final������������ʵ���������ʵ��ȫ������
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item17: Design and document for inheritance or else prohibit it
 * 
 * item18: Prefer interfaces to abstract classes
 * 
 * mixin(�������)��������͵�ʵ�֣�����java�����̳����Գ����಻�ʺ�mixin��ʵ�֣���Ҫ�ýӿ�
 * 
 * �����������(��ϻ���AbstractAction����)��
 * a.�������������ĳЩ������ʵ�֣��ӿ���������˳���������γ�ģ��ģʽ
 * ����һ���ַ�����ʵ�֣�Ȼ��������������ʵ�����еĳ��󷽷�
 * b.Ϊ��ʵ���ɳ����ඨ������ͣ�������Ϊ�������һ�����������޷��̳б����
 * ���Ȱ취�ǹǼ�ʵ�֣���������mixin�ӿڣ�Ȼ��ͨ��һϵ�еĳ�����ʵ��һ���ַ�����
 * ����ģ��ģʽ����һ���ַ������ӳ�����ʵ�֣������γ�һ���Ǽ�
 * һ���ӿ���Ҫ����·�����������µĽӿڵ�ʱ�򣬲���Ҫ�Ķ����е�ʵ���࣬����Ķ����ֹǼ�ʵ���༴��
 * 
 * ����item17������ĹǼ�ʵ�������Ϊ�˼̳ж���Ƶ�
 * Ϊ������̳У����������ܵ��ÿɱ����ǵķ�����ͬ����Ϊclone������readObject�����ǳ����ƹ�����
 * ��������clone����readObject���������Ե��ÿɸ��ǵķ���
 * 
 * ��������Ѿ������޷���չ�ɹǼ�ʵ���࣬��ô��ͨ��java��˽���ڲ���ģ����ؼ̳�
 * �����ڲ������ϸ���ܼ���
 * {@link https://app.yinxiang.com/shard/s62/nl/12840192/0def3bc6-dae7-435a-9e78-4d3eb9e1a4bc ӡ��ʼ�}
 *
 */
public class SkeletalDemo extends SkeletalInterface {
	public static void main(String[] args) {
		SkeletalInterface demo = new SkeletalDemo();
		demo.method();
		demo.additionalMethod();
	}

	@Override
	public void method() {
		System.out.println("SkeletalDemo - method()...");
	}

	@Override
	public void anotherMethod() {
		System.out.println("SkeletalDemo - anotherMethod()...");
	}
	
	@Override
	public void testDefaultForJDK8() {
		System.out.println("Implementation of this method declared in Skeletal interface is optional.");
	}
}
