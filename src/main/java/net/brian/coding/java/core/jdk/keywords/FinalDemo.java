package net.brian.coding.java.core.jdk.keywords;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item13: Minimize the accessibility of classes and members
 * 
 * ���ɱ����ֻ��һ��״̬����������ʱ��״̬�����ȷ�����й�������������������Լ����ϵ
 * �Ϳ���ȷ�����Լ����ϵ�������������ڲ��ٱ仯��ʹ��������ʱ���������Ĺ���ά����ЩԼ����ϵ
 * ��˲��ɱ�Ķ������̰߳�ȫ�ģ���������ص㣺
 * a.��������������ʵ��
 * @see net.brian.coding.java.core.oop.MinimizeMutabilityDemo.ZERO
 * b.���沢��������ʵ���������ڴ�ռ�ú��������ճɱ�
 * @see net.brian.coding.java.core.oop.MinimizeMutabilityDemo.valueOf(double, double)
 * 
 * ���Դ���һ�������ɱ����Ĳ��ɱ����ģ���ֻ��Ҫ����һ�㣬��Ҫ����ɱ��������þͿ�����
 * �����Ҫ�仯ʱ���ͷ���ԭ����ı����Կ�������������Ӿ��Ƕ����а���һ�����ڶ�������ã�����
 * @see net.brian.coding.java.core.jdk.serialization.BogusPeriod
 * 
 * final���α������������ͱ���ֵ�󲻿����¸�ֵ���������ͳ�ʼ���󲻿ɸı�����
 * final���η������������ܱ����ǻ���д
 * final�����ࣺ���಻�ɱ��̳�
 * 
 * �������������ʵ���������Ǿֲ�������ֻҪ����ñ���ʱʹ��final����
 * ���ڶ����final������ʱ��ָ���˳�ʼֵ�Ҹó�ʼֵ�ڱ����ʱ�����ȷ������
 * ��ô���final���������Ͼ��Ѿ������Ǳ���������ֱ����
 * 
 * ��final������ʱ����Щ��Ҫô�����������͵�ֵ��Ҫôָ�򲻿ɱ�Ķ�������ַ���������
 * ���final������ɱ��������ã���ô��Ȼ���ñ������޸ģ������������õĶ�����Ա��޸�
 * ע�⣺���ȷ�����������ǿɱ�ģ���������й��еľ�̬final������򷵻���Щ��ķ��ʷ������⼸�����Ǵ��
 * 
 */
public class FinalDemo {
	public static class FinalClass {
		// final���ε�ʵ������������ʽָ����ʼֵ����ֻ���ڶ��塢����顢���������������ѡ��һ�ֶ����ܶ�θ�ֵ
		// ������б������The blank final field name may not have been initialized
		final String name;
		// 12345��һ���ڱ����ʱ�����ȷ����ֵ��ֱ��������final����֮��ͱ���˺����
		// ���⻹�б����ʱ��Ϳ���ͨ��ֱ�������������ı���final int a = 5 + 2;Ҳ�Ǻ����
		// �������͵ı���ͨ������ȡ��һ��ͳһ�ĳ������в��ô�д��ĸ����
		final int id = 12345;
		final SubFinalClass myClass = new SubFinalClass();
		// ���ȷ�����������ǿɱ�ģ��������й��еľ�̬final������򷵻�����������ķ��ʷ������������Ǵ����
		final String[] myClassArr = {"Brian", "Sure"};

		public void changeFinalArray() {
			// �����������ÿ϶��ǲ��е�
			// myClassArr = new String[12];
			// �����������ǰ�����ڶ���Ԫ����Sure�ĳ���Yanan
			// ����ȴ�ǿ��Եģ�Ҳ����˵�ͻ��˿���������������ڵ�Ԫ�ض�Ӧ������
			myClassArr[1] = "Yanan";
		}
		// ���������ָĽ������������������֣���Ҫ�Ȱ������Ϊ˽�е�
		private static final String[] PRIVATE_VALUES = {"Brian", "Sure"};
		// ���final������myClassArr�ĸĽ����Է�ֹ��final������ĸ��ģ���final��������������֣�
		// ʹ����������˽�еģ�������һ�����еĲ��ɱ��б�
		// ������Ҫ��unmodifiableList�������ã�������Դ���֪�������þ��Ƿ���һ������List�Ĳ��ɱ���ͼ��unmodifiable view��
		// ������ɱ���ͼ��read-only�ģ����������Դ��ע��
		public static final List<String> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
		
		// ��Է���changeFinalArray�ĸĽ����Է�ֹ��final������ĸ��ģ���final������Ĳ����������֣�
		// ʹ�����Ϊ˽�У�������һ�����з���������˽�������һ������
		public static final String[] values() {
			return PRIVATE_VALUES.clone();
		}

		public void outputReferenceClass() {
			//���1����֤�����ñ�����final����֮����Ȼ������ָ���������󣬵�����ָ��Ķ���������ǿɱ��
			System.out.println(++myClass.i);
		}
		
		public FinalClass() {
			this.name = "Brian";
		}
		
		public final void testFinalMethod() {
			// ����������ܱ����า�ǣ������ڵ�ǰ���п��Ա���д
		}
		
		public void testFinalMethod(int age) {
			// �������û��final�ؼ������Σ����Ա����า��
		}
		
		public void testFinalParam(final int age) {
			// �����������ô������Ĳ������ɱ䣬���ڷ����������־ֲ����������ε�ʱ��͸�ֵ��
			// �����final���ε�Ŀ�ľ���ȷ���ڵ��ø÷�������֮�����ֵ�Ͳ��ٱ��ı�
			// age = 10;
			System.out.println("Output final int age in param list:: " + age);
		}
	}
	public static class SubFinalClass extends FinalClass {
		public int i = 0;
		@Override
		public void testFinalMethod(int age) {
			// ����testFinalMethod()�ᱨ�������ֻ�ܸ���testFinalMethod(int age)
		}
	}
	// �Ա���������������Ϊ������ֱ����������
	public static void main(String[] args) {
		// ֱ����
		String str1 = "Brian&Sure";
		String str12 = "Brian" + "&Sure";
		System.out.println("str1 == str12:: " + (str1 == str12));//true
		// ��ֱ����
		String str21 = "Brian";
		String str22 = "&Sure";
		String str2 = str21 + str22;
		System.out.println("str1 == str2:: " + (str1 == str2));//false
		// ����final���Σ�ԭ���ķ�ֱ��������Ҳ�����ֱ����
		final String str31 = "Brian";
		final String str32 = "&Sure";
		String str3 = str31 + str32;
		System.out.println("str1 == str3:: " + (str1 == str3));//true
	}
}
