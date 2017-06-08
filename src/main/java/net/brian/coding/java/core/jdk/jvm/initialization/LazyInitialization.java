package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item71: Use lazy initialization judiciously
 * 
 * 如果与只在类的实例部分被访问且初始化这个域的开销很高，可能就值得使用延迟初始化
 * 要确定这一点唯一的办法就是测量类在用和不用延迟初始化的性能差别
 * 大多数情况下正常的初始化是优先于延迟初始化的，但是如果为了达到性能优化等目的必须延迟初始化一个域
 * 则要注意：多个线程共享一个延迟初始化的域，采用某种形式的同步是很重要的
 * a.对于静态域的延迟初始化，最佳实践是lazy initialization holder class，即：
 * b.对于实例域，可使用双重检查模式，即：
 * c.对于实例域，延迟初始化时如果该域可以接受重复初始化，则可以省去第二次检查变成单重检查
 * 以上三种初始化的示例代码本类所示，均选自Effective Java书籍配套源码
 * d.对于实例域，如果不在意是否每个线程都重新计算域的值，并且域的类型为除了long和double外的基本类型，就可以从单重检查中删除volatile
 * 这种变体称为racy single-check idiom，这种方法不常见，目前仅见过的应用是计算hashCode并缓存
 * 比如：hashCode方法在第一次被调用的时候计算出散列码并缓存起来以备将来再次调用时使用
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent.hashCode()
 * @see java.lang.String.hashCode()
 */
public class LazyInitialization {
		// 正常初始化，对应于延迟初始化
		@SuppressWarnings("unused")
		private final FieldType field1 = computeFieldValue();

		// 实例域的延迟初始化
		private FieldType field2;

		synchronized FieldType getField2() {
			if (field2 == null)
				field2 = computeFieldValue();
			return field2;
		}

		// 静态域延迟初始化最佳实践：Lazy initialization holder class模式
		// 这种方式不需要同步，也不需增加什么访问成本，就是一个普通的私有静态内部类
		private static class FieldHolder {
			static final FieldType field = computeFieldValue();
		}

		static FieldType getField3() {
			return FieldHolder.field;
		}

		// 实例域延迟初始化之双重检查模式：Double-check idiom
		private volatile FieldType field4;

		FieldType getField4() {
			FieldType result = field4;
			if (result == null) { // First check (no locking)
				synchronized (this) {
					result = field4;
					if (result == null) // Second check (with locking)
						field4 = result = computeFieldValue();
				}
			}
			return result;
		}

		// 实例域延迟初始化之单重检查模式：Single-check idiom，会引发重复初始化，如果你不介意的话 
		private volatile FieldType field5;

		@SuppressWarnings("unused")
		private FieldType getField5() {
			FieldType result = field5;
			if (result == null)
				field5 = result = computeFieldValue();
			return result;
		}

		private static FieldType computeFieldValue() {
			return new FieldType();
		}
	}

	class FieldType {
	}