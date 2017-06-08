package net.brian.coding.java.core.jdk.valueclasses;

import static java.lang.System.out;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
/**
 * 
 * ����String���ɱ�ķ������棺
 * ����String��Դ�룬����һ����������value��offset��count��
 * 
 * ��������������private�ģ�����û���ṩ�����������޸���Щֵ
 * ������String����ⲿ�޷��޸�String
 * ���һ����ʼ���Ͳ����޸ģ�������String����ⲿ���ܷ�����������Ա��
 * 
 * ���⣬value��offset��count��������������final��
 * Ҳ����˵��String���ڲ���һ��������ֵ��ʼ���ˣ� Ҳ���ܱ��ı䡣
 * ���Կ�����ΪString�����ǲ��ɱ���ˡ�
 *
 */
public class StringDemo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String str;

	private static synchronized String changeString() {
		str = "Hello world!";
		return str;
	}

	public void syncString() {
		String changedStr = null;
		changedStr = changeString();
		System.out.println(changedStr);
	}

	private String testNullObjCasting() {
		return (String) null;
	}

	public void testInstanceofString() {
		String str = null;
		System.out.println(str == testNullObjCasting());
		System.out.println(str == null);
		System.out.println(testNullObjCasting() instanceof String);
		System.out.println(testNullObjCasting() instanceof Object);
	}

	// Constants Pool related.
	public void testOperator() {
		String a = "abc";
		String b = "abc";
		String c = new String("abc");
		String d = "ab" + "c";
		System.out.println("StringDemo -- testOperator() -- ( a==b ):: " + (a == b));
		System.out.println("StringDemo -- testOperator() -- ( a==c ):: " + (a == c));
		System.out.println("StringDemo -- testOperator() -- ( a==d ):: " + (a == d));
		System.out.println("StringDemo -- testOperator() -- ( c==d ):: " + (c == d));
	}
	
	private String variableParam(String... args) {
		String nullStr = null;
		String emptyStr = "";
		for(int i = 0; i < args.length; i++) {
			nullStr = nullStr + args[i];
			emptyStr = emptyStr + args[i];
		}
		//XXX: AOP��ӡlog�ع���To be implemented by net.brian.utils.LogAspect start.
		out.println("StringDemo -- variableParam -- emptyStr:: " + emptyStr);
		out.println("StringDemo -- variableParam -- nullStr:: " + nullStr);
		out.println("StringDemo -- variableParam -- args.length:: " + args.length);
		// To be implemented by net.brian.utils.LogAspect end.
		return emptyStr;
	}

	
	@Test
	public void finalClass() {
		String s = "ABCabc";
		System.out.println("s = " + s);
		// ������ִ�й�֮���ִ�����һ���µĶ���123456��
		// ����s����ָ��������ĵĶ���ԭ���Ķ���ABCabc�������ڴ��д��ڣ���û�иı䡣
		s = "123456";
		// ��������������s��ֵȷʵ�ı��ˡ���ô��ô��˵String�����ǲ��ɱ���أ�
		// ��ʵ�������һ�������� sֻ��һ��String��������ã���ָ����һ������Ķ��󣬲����Ƕ�����
		System.out.println("s = " + s);

	}
	
	public void finalClassForSet() {
		// ����ַ������Ա��ı䣬��ô�����÷����п���Υ��Set�����ԭ����ΪSetҪ�����е�Ԫ�ز������ظ���
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
	}
	
	/**
	 * ���Ӣ����jdk�ж���String(String original)�����������ע�ͣ�
	 * 
	 * Initializes a newly created {@code String} object so that it represents
     * the same sequence of characters as the argument; in other words, the
     * newly created string is a copy of the argument string. Unless an
     * explicit copy of {@code original} is needed, use of this constructor is
     * unnecessary since Strings are immutable.
	 */
	public void testStringConstructors() {
		// ���Ƽ�������
		str = new String("Hello world");
	}
}
