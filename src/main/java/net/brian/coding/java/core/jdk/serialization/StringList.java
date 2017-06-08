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
 * ���һ������������ʾ����ͬ�������߼����ݣ����ܾ��ʺ���ʹ��Ĭ�ϵ����л���ʽ
 * ��ʹ��ȷ����Ĭ�����л���ʽ�Ǻ��ʵģ�Ҳ�����ṩһ��readObject������֤Լ����ϵ�Ͱ�ȫ��
 * �������Name����ࣺ����ʵ����lastName��firstName��middleName�ֱ���������ֵ���������
 * �����������ȷ�ķ�ӳ��Name���߼����ݣ���˿��Կ���Ĭ��ʵ����
 * �������Ǳ����ṩһ��readObject������ȷ��lastName��firstName�Ƿ�null��
 * ��һ������������ʾ���������߼�����������ʵ���Ե�����ʱ��Ĭ�����л��������ȱ�㣬���Կ����Զ������л�
 * 
 * �������һ���Զ������л������ӣ���Ȼ�������е�����˲ʱ(transient)�ģ���writeObject����Ҫ�������ǵ���defaultWriteObject����
 * readObject����Ҫ�������ǵ���defaultReadObject����
 * ���һ��ʵ������δ���İ汾�б����л�����ǰһ���汾�б������л�����ô�����ӵ��򽫱����Ե�
 * ����Ǿɰ汾��readObject����û�е���defaultReadObject�������л����̽�ʧ�ܣ�����StreamCorruptedException�쳣
 *
 * ����transient�ؼ��֣�
 * a.�����Ƿ�ʹ��Ĭ�ϵ����л���ʽ��defaultWriteObject���������õ�ʱ��ÿ��δ�����Ϊtransient��ʵ���򶼻ᱻ���л�
 * transient����Ϊ�˽������״̬���߼�״̬��һ�µ����ʵ�����л���
 * ����ھ�����һ�������ɷ�transient֮ǰ��ȷ������ֵ���Ǹö����߼�״̬��һ����
 * b.Ĭ�����л�ʱ�������򱻱��Ϊtransient����һ��ʵ���������л���ʱ����Щ�򽫱���ʼ��Ϊ���ǵ�Ĭ��ֵ
 * �����Щֵ�����κ�transient����ܣ�������ṩһ��readObject���������ȵ���defaultReadObject��Ȼ�����Щtransient��ָ�Ϊ�ɽ��ܵ�ֵ
 * c.�����Ƿ�ʹ��Ĭ�ϵ����л���ʽ������ڶ�ȡ��������״̬���κ�����������ǿ���κ�ͬ������Ҳ�����ڶ������л���ǿ������ͬ��
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
	// ����serialver[-classpath��·��][-show][������]���������Ը�����ĳ�Ա�ṹ����һ��UID
	private static final long serialVersionUID = 93248094385L;
	// Remainder omitted
}