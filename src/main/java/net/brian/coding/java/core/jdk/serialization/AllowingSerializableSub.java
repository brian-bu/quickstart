package net.brian.coding.java.core.jdk.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item74: Implement Serializable judiciously
 * 
 * “有一种办法可以给不可序列化但可扩展的类增加无参构造器，同时避免以上不足”
 * 关于序列化的详细讲解详见：
 * {@linkplain https://app.yinxiang.com/shard/s62/nl/12840192/b7320ea8-2135-44e3-bc38-be1daff42044 印象笔记}
 *
 */
public class AllowingSerializableSub extends AbstractFoo implements Serializable {
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();

		// Manually deserialize and initialize superclass state
		int x = s.readInt();
		int y = s.readInt();
		initialize(x, y);
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();

		// Manually serialize superclass state
		s.writeInt(getX());
		s.writeInt(getY());
	}

	// Constructor does not use the fancy mechanism
	public AllowingSerializableSub(int x, int y) {
		super(x, y);
	}

	private static final long serialVersionUID = 1856835860954L;
}

abstract class AbstractFoo {
	private int x, y; // Our state

	// This enum and field are used to track initialization
	private enum State {
		NEW, INITIALIZING, INITIALIZED
	};

	private final AtomicReference<State> init = new AtomicReference<State>(State.NEW);

	public AbstractFoo(int x, int y) {
		initialize(x, y);
	}

	// This constructor and the following method allow
	// subclass's readObject method to initialize our state.
	protected AbstractFoo() {
	}

	protected final void initialize(int x, int y) {
		if (!init.compareAndSet(State.NEW, State.INITIALIZING))
			throw new IllegalStateException("Already initialized");
		this.x = x;
		this.y = y;
		// Do anything else the original constructor did
		init.set(State.INITIALIZED);
	}

	// These methods provide access to internal state so it can
	// be manually serialized by subclass's writeObject method.
	protected final int getX() {
		checkInit();
		return x;
	}

	protected final int getY() {
		checkInit();
		return y;
	}

	// 该方法会被所有的public and protected instance methods调用
	// 这样可以确保如果有编写不好的子类没有初始化实例，该方法调用就可以快速干净的失败
	private void checkInit() {
		if (init.get() != State.INITIALIZED)
			throw new IllegalStateException("Uninitialized");
	}
	// Remainder omitted
}