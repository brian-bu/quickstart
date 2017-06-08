package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway;
// ����Ϊ��ʾ��������������й��ڵȼ۹�ϵ�����ԣ�������Ҫһ����ȷ����equals������Student
// ����û�����õ�ǰpackage���е�Student�࣬�������Student1�Ĺ��ڸ���equals�ķ������Ǵ��
// ���Էŵ�wrongway��package��
import net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent;

/**
 * 
 * �����ͨ����equals�ĸ��Ǻ�����age��Ϣ��������¶��������������й��ڵȼ۹�ϵ��һ���������⣺
 * �����޷�����չ��ʵ���������ͬʱ���������µ�ֵ���ͬʱ�ֱ���equals��Լ��
 *
 * @see net.brian.coding.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent1
 */
public class WrongValuedClassStudent1 extends RightValuedClassStudent{
	// Ϊ�˸�������������Ǹ��꼶���ǵ��꼶��ѧ�������Ǽ���һ��������Ϣ
	// Point 1����ʱ�����ȫ������equals������ֱ����Student��̳й�����
	// ���ڱȽ�ͬ��ͬ�Ա������䲻һ������������Ĺ�������ֻ�Ƚ������ֺ��Ա�����ͱ����Ե���
	private int age;

	// Point 2��������Ǹ���equals��������������������Ǿ�Υ���˸���ԭ��ĶԳ���
	// Ҳ����a.equals(b)����true����ôb.equals(a)ҲӦ�÷���true
	// @Override
	// public boolean equals(Object obj) {
	// // ���ڷǿ��ԡ�this�ȵ��ж��Լ���name��sex�ıȽ϶���super.equals�ж�����ˣ������ظ�����
	// if(!(obj instanceof RightValuedClassStudent1)) return false;
	// return super.equals(obj) && ((RightValuedClassStudent1)obj).age == age;
	// }
	// Point 3��������Ǹ���equals��������������������Ǿ�Υ���˸���ԭ��Ĵ�����
	// �����Ծ���a�������b����b�����ֵ���c������a�������c����
	// ������̸�������Ե�ʱ����Ҫע����ǣ��������ӵ���Ϣ��Ӱ�쵽equals�ıȽϽ��
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RightValuedClassStudent)) return false;
		// ���obj����Student��ʵ���ֲ���Student1��ʵ�����Ǿ�ֱ�ӵ���Student�ﶨ���equals
		if(!(obj instanceof WrongValuedClassStudent1)) return obj.equals(this);
		// �����һ��Student1��ʵ�����͵���Student��equals�Ƚ�name��sexͬʱ�Ƚ�������߼�״̬age
		return super.equals(obj) && ((WrongValuedClassStudent1)obj).age == age;
	}
	// Point 4���������ϵĶԳ��Ժʹ����ԣ���equals�����ĸ��ǻ����������������ԣ�
	// һ���ԣ��������������ȣ��ͱ���ʼ����ȣ���Ҫ��equals��������һЩ���ɿ�����Դ������URL��io
	// �ǿ��ԣ����е�equals�������ڸ��ǵ�ʱ��Ӧ�����ͷ�ж�һ�£�����������Ĳ�����null��ֱ�ӷ���false
	// �Է��ԣ���������һ�����󲻵������Լ���
	
	
	public WrongValuedClassStudent1(String name, char sex, int age) {
		super(name, sex);
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}