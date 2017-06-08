package net.brian.coding.java.core.oop;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item41: Use overloading judiciously
 * 
 * �������ط���(overloaded method)��ѡ���Ǿ�̬�����ڱ����ǵķ���(overriden method)��ѡ���Ƕ�̬�ġ�
 * ѡ�񱻸��ǵķ�������ȷ�汾��������ʱ���еģ�ѡ��������Ǳ����÷������ڶ��������ʱ����
 * 
 * �����������صĲ��ԣ�
 * a.��Զ��Ҫ��������������ͬ������Ŀ�����ط������������ʹ�ÿɱ���������صĲ����Ǹ�����Ҫ������
 * b.ʼ�ո�������ͬ�����ƶ��������أ������Ϳ��Ա��⡰�����κ�һ��ʵ�ʵĲ������ĸ����ط��������õġ����ɻ���
 * ���ڹ����������أ����Բ���item02�������Ե�����̬������������ÿ��������ͬ�����֣����������ع�����
 * c.����Ӧ�ñ������������Σ�ͬһ�����ֻ�辭������ת���Ϳ��Ա����ݸ���ͬ�����ط���
 * ������ܱ�֤��һ�㣬������Ҫ��֤������ͬ���Ĳ���ʱ�������ط�������Ϊ���뱣��һ��
 *
 */
public class OverloadingDemo {
	// �������������������������������������
	// ���������ʱ���Ͳ���Ӱ�조�ĸ����ذ汾����ִ�С�
	// ѡ�������ڱ���ʱ���еģ���ȫ���ڲ����ı���ʱ����
	private static class Wine {
		String name() {
			return "wine";
		}
	}

	private static class SparklingWine extends Wine {
		@Override
		String name() {
			return "sparkling wine";
		}
	}

	private static class Champagne extends SparklingWine {
		@Override
		String name() {
			return "champagne";
		}
	}

	public static void main(String[] args) {
		Wine[] wines = { new Wine(), new SparklingWine(), new Champagne() };
		for (Wine wine : wines)
			System.out.println(wine.name());
	}
	// ������������ͨ�����������滻CollectionClassifier�����ص�����classify����
	// ��������������Ŀ����������Խ��һ���÷�������ͨ��instanceofԤ���ж�Collection��������
	public static String classify(Collection<?> c) {
		return c instanceof Set ? "Set" : c instanceof List ? "List" : "Unknown Collection";
	}
}

// ����һ��������������ͬ�����б��ͬ�����������ǲ���֮����ڼ̳й�ϵ��Won't work!
// ѡ������ĸ�������������ʱ���еģ����е�ʱ���ѡ��Collection������Ӧ�ķ���ִ�У���Ϊ������collections����Collection��
class CollectionClassifier {
	public static String classify(Set<?> s) {
		return "Set";
	}

	public static String classify(List<?> lst) {
		return "List";
	}

	public static String classify(Collection<?> c) {
		return "Unknown Collection";
	}

	public static void main(String[] args) {
		Collection<?>[] collections = { new HashSet<String>(), new ArrayList<BigInteger>(),
				new HashMap<String, String>().values() };

		for (Collection<?> c : collections)
			System.out.println(classify(c));
	}
}