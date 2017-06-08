package net.brian.coding.java.core.jdk.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item23: Don’t use raw types in new code
 * 
 * item24: Eliminate unchecked warnings
 * 
 * 泛型中也有形参和实参：List<E>和List<String>，E是所有泛型的形参
 * 任意指定的类型，比如String，就是E的实参
 * 
 * 对于“不要在新代码中使用原生态类型”有两个例外：
 * a.类文字中必须使用原生态：List.class, String[].class和int.class都合法
 * 但是List<String>.class和List<?>.class不合法
 * b.泛型可在运行时被擦除，参数化类型和instanceof一起使用是非法的
 * 但是无限制通配符类型却是合法的，因此利用泛型来使用instanceof的首选方法见：
 * @see net.brian.coding.java.core.jdk.generic.RawTypeDisadvantages.testGenericWithInstanceof(Object)
 * 
 * 对于有限制通配符类型：
 * @see net.brian.coding.java.core.jdk.generic.BoundedWildcardType
 *
 */
public class RawTypeDisadvantages {
	/** 
	 * 泛型有子类型化(sub typing)的规则
	 * List<String>是原生态List的一个子类型，而不是List<Object>的子类型
	 * 事实上所有List<E>都是原生态List的子类型(E是泛型化的形参，可以为任意类型)
	 * 如果使用像List这样的原生态类型就会失掉类型安全性，使用List<Object>则不会
	 */
	public void testSubTyping() {
		List<String> strings = new ArrayList<String>();
		unsafeAdd(strings, new Integer(42));
		String s = strings.get(0);
		System.out.println(s);
		
	}

	/**
	 * 这里的list参数就能看出List和List<Object>的不同：
	 * 对于testSubTyping方法的调用：
	 * 如果参数list声明为List<Object>则编译时就检查出错误
	 * 因为List<String>不是List<Object>的子类型，传参的时候就会报错
	 * 如果参数list声明为List，因为List<String>是List的子类型则编译时不报错，直到运行时才发现错误
	 * 
	 * 此外根据item24，这里需要注解来消除list.add(obj)的非受检警告，即unchecked，这个警告只能放在方法外面
	 * 只有在无法消除警告且可以证明引起警告的代码是类型安全的时候才可以用unchecked，显然这里是错误的示范
	 * 而对于rawtypes的声明，应该尽量减小@SuppressWarnings的作用范围，比如应该声明在list参数前就尽量不要声明在方法外面
	 * 
	 * @param list
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	private void unsafeAdd(@SuppressWarnings("rawtypes") List list, Object obj) {
		// 每次使用unchecked注解都应该加一条注释说明为什么这样是安全的
		// 这里就不加注释了，因为很显然当前这个例子是不安全的
		list.add(obj);
	}
	
	/**
	 * 
	 * 在不确定或者不在乎实际的类型参数的时候也不能用原生类型，这时可以考虑用无限制的通配符类型，即问号?
	 * 例如Set<E>的无限制通配符类型就是Set<?>，这表明这个Set可以持有任何集合
	 * 
	 * 此外，这里的unused是因为这个方法的定义并不是为了用，而是为了提供一种代码示例
	 * 所以我非常确定它的非受检警告是多余的，所以就加了unused的注解
	 * @param list
	 * @param obj
	 * 
	 * 若无法接受?带来的如下注释中的限制，可以考虑用泛型方法和有限制的通配符类型
	 * 泛型方法：
	 * 
	 * 有限制的通配符类型：
	 * 
	 */
	@SuppressWarnings("unused")
	private void safeAdd(List<?> list, Object obj) {
		// 这句会报编译错误：? is not applicable for the arguments (Object)
		// list.add(obj);
		// 你不能将除了null以外的任何元素加到这个list里去
		list.add(null);
	}
	
	public void testGenericWithInstanceof(Object o) {
		if(o instanceof Set) {
			@SuppressWarnings("unused")
			Set<?> m = (Set<?>)o;
		}
	}
}
