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
 * 对于一些银行汇率等的货币计算，最好用BigDecimal、int、long，而不是用浮点型，也即float或double
 * 对于使用int、long还是BigDecimal，可参考如下意见：
 * 如果要通过法定要求的舍入行为进行业务计算，使用BigDecimal是十分方便的；
 * 如果性能很关键你自己又不介意自行处理十进制小数点，而且所涉及的数值又不大，就可以用int或者long
 * 数值范围没超过9位十进制数字就可以用int，不超过18位用long，超过18位用BigDecimal
 * 
 * 这里包含常见的数学处理API，不单包含Math类，还有BigDecimal等的示例代码
 * 此外还包括Random的生成方法：new Random()、new Random(47)和Math.random()
 * 
 * Math中的random方法作用是生成一个[0,1.0)区间的随机小数
 * 1.计算机的伪随机数是由随机种子根据一定的计算方法计算出来的数值
 * 所以，只要计算方法一定，随机种子一定，那么产生的随机数就是固定的。
 * 2.只要用户或第三方不设置随机种子，那么在默认情况下随机种子来自系统时钟
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
	 * 对于一些银行汇率等的货币计算，最好用BigDecimal而不是用float或double
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
