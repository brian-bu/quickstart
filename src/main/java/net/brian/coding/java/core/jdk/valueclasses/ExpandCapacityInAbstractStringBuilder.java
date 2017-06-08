package net.brian.coding.java.core.jdk.valueclasses;

import net.brian.coding.java.utils.StringUtil;

/**
 * 这里对StringBuilder和StringBuffer的扩容API进行讲解
 * 由于无论怎样扩容都要用到java.lang.AbstractStringBuilder.expandCapacity(int)
 * 所以这里以这个API的名字命名类名
 * 注意：StringBuilder和StringBuffer的共同父类是java.lang.AbstractStringBuilder
 * 
 * >>>>> 源码分析：
 * 
 * 其中调用append方法则会调用ensureCapacityInternal，其源码如下： 
 * if (minimumCapacity - value.length > 0) expandCapacity(minimumCapacity);
 * 手动调用ensureCapacity发现这个方法的源码内部是： 
 * if (minimumCapacity > value.length) {expandCapacity(minimumCapacity); } 
 * 这就是无论调用append自动扩容还是手动调用ensureCapacity扩容
 * 只要满足minimumCapacity > value.length，都会调用到expandCapacity这个方法
 * 
 * 且看expandCapacity这个方法的关键源码： 
 * void expandCapacity(int minimumCapacity) { 
 *	 int newCapacity = value.length * 2 + 2; 
 *	 if (newCapacity - minimumCapacity < 0)
 *	 newCapacity = minimumCapacity; 
 * 	value = Arrays.copyOf(value, newCapacity);
 * 从源码可以看出只要进入了expandCapacity方法不抛异常就一定会调用Arrays.copyOf
 * 
 * >>>>> 基于以上源码的阅读可以得出结论如下：
 * 
 * >>>>> 对于手动扩容：
 *  第一次如果手动调用ensureCapacity并且传进来的minimumCapacity足够大
 * 会导致value.length很大，也就是创建一个很大的数组，比如100000 接下来即使调用append
 * 那么此时minimumCapacity = count + len 也就是新旧字符串拼在一起的总长度
 *  这个时候minimumCapacity - value.length是远小于0的
 * 所以不会调用expandCapacity，也就不会调用到Arrays.copyOf
 * 
 * >>>>> 对于利用append自动扩容： 
 * 如果是默认的构造器默认的capacity则： 
 * AbstractStringBuilder(int capacity) { value = new char[capacity]; }
 * 调用这个构造器的是buffer的默认构造器，入参是16，即super(16); 
 * 也就是说默认会创建一个长度16的数组接下来调用append方法则会调用ensureCapacityInternal
 * 并以原数组容量和新添加的字符串的长度为入参扩容，即ensureCapacityInternal(count + len);
 * 如果最坏的情况也就是每次都有minimumCapacity - value.length > 0 
 * 也即count + len - value.length > 0 这样就会每次都需要扩容并调用Arrays.copyOf，严重影响性能
 * 这里影响count + len - value.length > 0的基本就是len这个变量 
 * 也就是每次新增的字符串长度len都越来越大且大于value.length
 * 
 */
public class ExpandCapacityInAbstractStringBuilder {
	// 由于StringBuffer和StringBuilder都是同样的扩容机制，这里以Builder作为示例分析
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

	// 调用append方法的时候自动扩容
	public long testAutoExpanding() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			autoExpandingBuilder.append(StringUtil.createRandomString(autoExpandingLength));
			// Point 1：确保count + len - value.length > 0进而每次循环都触发扩容
			int newLength = autoExpandingBuilder.length() + autoExpandingLength + 1;
			int valueLength = autoExpandingBuilder.toString().length();
			// 确保在长度增长到超过int的最大容量时及时停下来
			// 因为当达到Integer.MAX_VALUE之后再增加就变成了负数
			if(newLength < 0 || valueLength < 0){
				System.out.println("The append method executed for " + (i + 1) + " times.");
				// Point 2：确保手动扩容和自动扩容都执行了相同的次数
				setTimes(i);
				break;
			}
			if (newLength > valueLength)
				setLength(newLength);
		}
		long end = System.currentTimeMillis();
		return (end - start);
	}

	// 手动调用ensureCapacity进行扩容
	public long testManualExpanding() {
		long start = System.currentTimeMillis();
		// 为了体现手动扩容的效果，直接设置到int最大容量，确保在程序停下来的时候不会触发自动扩容
		manualExpandingBuilder.ensureCapacity(Integer.MAX_VALUE);
		// 和自动扩容使用同样的代码，唯一的不同就是字符串的长度不需要保证符合触发自动扩容的条件
		// 即：count + len - value.length > 0
		// 如果手动扩容的代码也要符合这个条件那么程序刚一执行就会马上达到最大值退出
		// 这里手动扩容直接把builder的容量设置到了最大， 自动扩容是一点一点把容量扩到最大的
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