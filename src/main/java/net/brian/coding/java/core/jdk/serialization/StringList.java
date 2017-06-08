package net.brian.coding.java.core.jdk.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item75: Consider using a custom serialized form
 * 
 * 如果一个对象的物理表示法等同于它的逻辑内容，可能就适合于使用默认的序列化形式
 * 即使你确定了默认序列化形式是合适的，也必须提供一个readObject方法保证约束关系和安全性
 * 比如对于Name这个类：它的实例域lastName、firstName、middleName分别代表了名字的三个部分
 * 因此这三个域精确的反映了Name的逻辑内容，因此可以考虑默认实例化
 * 不过还是必须提供一个readObject方法，确保lastName和firstName是非null的
 * 当一个对象的物理表示法与它的逻辑数据内容有实质性的区别时，默认序列化会有诸多缺点，所以考虑自定义序列化
 * 
 * 本类就是一个自定义序列化的例子，虽然这里所有的域都是瞬时(transient)的，但writeObject的首要任务仍是调用defaultWriteObject方法
 * readObject的首要任务仍是调用defaultReadObject方法
 * 如果一个实例将在未来的版本中被序列化，在前一个版本中被反序列化，那么后增加的域将被忽略掉
 * 如果是旧版本的readObject方法没有调用defaultReadObject，反序列化过程将失败，引发StreamCorruptedException异常
 *
 * 关于transient关键字：
 * a.无论是否使用默认的序列化形式，defaultWriteObject方法被调用的时候，每个未被标记为transient的实例域都会被序列化
 * transient就是为了解决物理状态和逻辑状态不一致的类的实例序列化的
 * 因此在决定将一个域做成非transient之前请确信它的值将是该对象逻辑状态的一部分
 * b.默认序列化时如果多个域被标记为transient，则一个实例被反序列化的时候，这些域将被初始化为它们的默认值
 * 如果这些值不被任何transient域接受，则必须提供一个readObject方法，首先调用defaultReadObject，然后把这些transient域恢复为可接受的值
 * c.无论是否使用默认的序列化形式，如果在读取整个对象状态的任何其他方法上强制任何同步，则也必须在对象序列化上强制这种同步
 *
 */
public final class StringList implements Serializable {
	private transient int size = 0;
	private transient Entry head = null;

	// No longer Serializable!
	private static class Entry {
		String data;
		Entry next;
		@SuppressWarnings("unused")
		Entry previous;
	}

	// Appends the specified string to the list
	public final void add(String s) {
		// Implementation omitted
	}

	/**
	 * Serialize this {@code StringList} instance.
	 * 
	 * @serialData The size of the list (the number of strings it contains) is
	 *             emitted ({@code int}), followed by all of its elements (each
	 *             a {@code String}), in the proper sequence.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(size);

		// Write out all elements in the proper order.
		for (Entry e = head; e != null; e = e.next)
			s.writeObject(e.data);
	}

	private void readObject(ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();
		int numElements = s.readInt();

		// Read in all elements and insert them in list
		for (int i = 0; i < numElements; i++)
			add((String) s.readObject());
	}
	// 利用serialver[-classpath类路径][-show][类名称]这个命令可以根据类的成员结构生成一个UID
	private static final long serialVersionUID = 93248094385L;
	// Remainder omitted
}