package net.brian.coding.java.core.jdk.valueclasses;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item48: Avoid float and double if exact answers are required
 * 
 * ����һЩ���л��ʵȵĻ��Ҽ��㣬�����BigDecimal��int��long���������ø����ͣ�Ҳ��float��double
 * ����ʹ��int��long����BigDecimal���ɲο����������
 * ���Ҫͨ������Ҫ���������Ϊ����ҵ����㣬ʹ��BigDecimal��ʮ�ַ���ģ�
 * ������ܹܺؼ����Լ��ֲ��������д���ʮ����С���㣬�������漰����ֵ�ֲ��󣬾Ϳ�����int����long
 * ��ֵ��Χû����9λʮ�������־Ϳ�����int��������18λ��long������18λ��BigDecimal
 * 
 * ���������������ѧ����API����������Math�࣬����BigDecimal�ȵ�ʾ������
 * ���⻹����Random�����ɷ�����new Random()��new Random(47)��Math.random()
 * 
 * Math�е�random��������������һ��[0,1.0)��������С��
 * 1.�������α���������������Ӹ���һ���ļ��㷽�������������ֵ
 * ���ԣ�ֻҪ���㷽��һ�����������һ������ô��������������ǹ̶��ġ�
 * 2.ֻҪ�û��������������������ӣ���ô��Ĭ������������������ϵͳʱ��
 *
 */
public class MathematicsDemo {
	@Test
	public void testSeed() {
		// Same seed will provide the same random digit.
		final int seed1 = 30;
		final int seed2 = 50;
		Random r1 = new Random(seed1);
		Random r2 = new Random(seed1);
		r1.setSeed(seed2);
		Random r3 = new Random(seed1);
		Random r4 = new Random(seed2);
		for (int i = 0; i < 2; i++) {
			System.out.println("RandomDemo -- testSeed() -- Output the value of r1:: " + r1.nextInt());
			System.out.println("RandomDemo -- testSeed() -- Output the value of r2:: " + r2.nextInt());
			System.out.println("RandomDemo -- testSeed() -- Output the value of r3:: " + r3.nextInt());
			System.out.println("RandomDemo -- testSeed() -- Output the value of r4:: " + r4.nextInt());
			// It's interesting that no matter how many times the code executed,
			// the output are the same unless you change the value of the seeds.
		}
	}

	public void testRandomNextInt() {
		Random rand = new Random(47);
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for (int i = 0; i < 10000; i++) {
			// Produce a number between 0 and 20:
			int r = rand.nextInt(20);
			Integer freq = m.get(r);
			m.put(r, freq == null ? 1 : freq + 1);
		}
		System.out.println(m);
	}

	@Test
	public void testDouble() {
		// This is to create a random digit in [2, 3.5).
		double randomDouble = Math.random() * 1.5 + 2;
		System.out.println("RandomDemo -- testSeed() -- randomDouble:: " + randomDouble);
	}

	public void testMin() {
		Math.min(23, 27);
	}
	public void testRandom() {
		Math.random();
	}
	
	/**
	 * ����һЩ���л��ʵȵĻ��Ҽ��㣬�����BigDecimal��������float��double
	 */
	@Test
	public void bigDecimalInsteadOfFloat() {
		System.out.println("Output the result of (1.03 - .42):: " + (1.03 - .42));// 0.6100000000000001
	}
	
	@Test
	public void bigDecimalForSortedeCollections() {
		BigDecimal decimal1 = new BigDecimal("1.0");
		BigDecimal decimal2 = new BigDecimal("1.00");
		Set<BigDecimal> hashSet = new HashSet<BigDecimal>();
		Set<BigDecimal> treeSet = new TreeSet<BigDecimal>();
		hashSet.add(decimal1);
		hashSet.add(decimal2);
		treeSet.add(decimal1);
		treeSet.add(decimal2);
		System.out.println("Output the size of HashSet:: " + hashSet.size());//2
		System.out.println("Output the size of TreeSet:: " + treeSet.size());//1
	}
}
