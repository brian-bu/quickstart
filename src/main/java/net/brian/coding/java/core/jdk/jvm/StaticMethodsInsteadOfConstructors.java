package net.brian.coding.java.core.jdk.jvm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item01: Consider static factory methods instead of constructors
 * 
 * ��̬������ȹ����������ƣ�
 * a.�����ƣ���Boolean.valueOf��������������Ҫ���ౣ��һ�£�����޷�һĿ��Ȼ�ؿ���ÿ���������ľ�������
 * b.����ÿ�ζ�����һ���¶������еĹ����������Է���ͬһ��ʵ��������������������Ҫ�ܴ���۵���������ַ�ʽ��������������
 * ��Ȼ�Ƿ���ͬһ��ʵ�����´αȽ���������ʱ������==����equals�������Ƚ϶�������Ҳ�����������
 * c.����ԭ���ض���������ͣ��������ڷ����������Ǿ����˸���������
 * һ��ĳЩ�����ͱ���֤���������ǿ�����ʱ�滻�����Ƕ�����Կͻ��˲����κ�Ӱ��
 * ��Ϊ�ͻ��˵��õ��ǹ����������������ڲ�ʵ�ֶԿͻ��˶����ǲ��ɼ���
 * 
 * ����ʵ�־�����
 * a.������ÿ���汾��jdk������и�����Ϊ�˷���Ķ����룬java.util.Collections�������Ӧ�������ַ���
 * @see java.util.Collections
 * b.JDBC����һ�������ṩ�߿�ܣ������ɶ�������ṩ��ʵ��һ������ϵͳΪ�����ṩ�ߵĿͻ����ṩ���ʵ��
 * �������ǴӶ��ʵ���н������������JDBC API����һ�����͵ķ����ṩ�߿�ܡ�
 * ����JDBC����ϸ����ʾ����JdbcConnectionByMysql����
 * 
 * @see net.brian.coding.db.jdbc.JdbcConnectionByMysql
 * {@linkplain http://www.tuicool.com/articles/vmMni2}
 *
 */
public class StaticMethodsInsteadOfConstructors extends SuperClient {
	// ˽�л�����������ͨ�������������������ù��������ṩ��Ŀͻ��ˣ��ڹ��������ڲ�����˽�й�������������
	private StaticMethodsInsteadOfConstructors() {
	}
	// ��ϴ���̳еķ�ʽʵ�־�̬������ʵ����javaҲ������ϴ���̳�
	private static StaticMethodsInsteadOfConstructors client = new StaticMethodsInsteadOfConstructors();

	public static StaticMethodsInsteadOfConstructors giveMethodMeaningfulName() {
		return client;
	}

	public static SuperClient returnMultiType() {
		return new StaticMethodsInsteadOfConstructors();
	}

	public static <K, V> HashMap<K, V> newHashMapInstance() {
		return new HashMap<K, V>();
	}

	public static void main(String[] args) {
		// a.������ֻ�ܺ���ͬ���������������Լ�������
		StaticMethodsInsteadOfConstructors client1 = giveMethodMeaningfulName();
		StaticMethodsInsteadOfConstructors client2 = giveMethodMeaningfulName();
		// b.ͨ��==�Ƚ϶��󣬱�equalsӵ�и��õ�����
		System.out.println("If static method factory always return the same reference:: " + (client1 == client2));
		// c.�����߳��ĸ�ֵ��Map<String, List<String>> m = new HashMap<String, List<String>>();
		@SuppressWarnings("unused")
		Map<String, List<String>> m = StaticMethodsInsteadOfConstructors.newHashMapInstance();
	}
}

class SuperClient {
}