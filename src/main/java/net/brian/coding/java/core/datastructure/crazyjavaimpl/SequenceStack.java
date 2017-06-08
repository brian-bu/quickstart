package net.brian.coding.java.core.datastructure.crazyjavaimpl;
   
import java.util.Arrays;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SequenceStack<T>
{
	private int DEFAULT_SIZE = 10;
	//保存数组的长度。
	private int capacity;
	//定义当底层数组容量不够时，程序每次增加的数组长度
	private int capacityIncrement = 0;
	//定义一个数组用于保存顺序栈的元素
	private Object[] elementData;
	//保存顺序栈中元素的当前个数
	private int size = 0;
	//以默认数组长度创建空顺序栈
	public SequenceStack()
	{
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
	}
	//以一个初始化元素来创建顺序栈
	public SequenceStack(T element)
	{
		this();
		elementData[0] = element;
		size++;
	}
	/**
	 * 以指定长度的数组来创建顺序栈
	 * @param element 指定顺序栈中第一个元素
	 * @param initSize 指定顺序栈底层数组的长度
	 */
	public SequenceStack(T element , int initSize)
	{
		this.capacity = initSize;
		elementData = new Object[capacity];
		elementData[0] = element;
		size++;
	}
	/**
	 * 以指定长度的数组来创建顺序栈
	 * @param element 指定顺序栈中第一个元素
	 * @param initSize 指定顺序栈底层数组的长度
	 * @param capacityIncrement 指定当顺序栈底层数组的长度不够时，底层数组每次增加的长度
	 */
	public SequenceStack(T element , int initSize
		, int capacityIncrement)
	{
		this.capacity = initSize;
		this.capacityIncrement = capacityIncrement;
		elementData = new Object[capacity];
		elementData[0] = element;
		size++;
	}
	//获取顺序栈的大小
	public int length()
	{
		return size;
	}
	//入栈
	public void push(T element)
	{
		ensureCapacity(size + 1);
		elementData[size++] = element;
	}
	//很麻烦，而且性能很差
	private void ensureCapacity(int minCapacity)
	{
		//如果数组的原有长度小于目前所需的长度
		if (minCapacity > capacity)
		{
			if (capacityIncrement > 0)
			{
				while (capacity < minCapacity)
				{
					//不断地将capacity长度加capacityIncrement，
					//直到capacity大于minCapacity为止
					capacity += capacityIncrement;
				}
			}
			else
			{
				//不断地将capacity * 2，直到capacity大于minCapacity为止
				while (capacity < minCapacity)
				{
					capacity <<= 1;
				}
			}
			elementData = Arrays.copyOf(elementData , capacity);
		}
	}
	//出栈
    public T pop()
	{
		T oldValue = (T)elementData[size - 1];
		//释放栈顶元素
		elementData[--size] = null; 
		return oldValue;
	}
	//返回栈顶元素，但不删除栈顶元素
    public T peek()
	{
		return (T)elementData[size - 1];
	}
	//判断顺序栈是否为空栈
	public boolean empty()
	{
		return size == 0;
	}
	//清空顺序栈
	public void clear()
	{
		//将底层数组所有元素赋为null
		Arrays.fill(elementData , null);
		size = 0;
	}
	public String toString()
	{
		if (size == 0)
		{
			return "[]";
		}
		else
		{
			StringBuilder sb = new StringBuilder("[");
			for (int i = size - 1  ; i > -1 ; i-- )
			{
				sb.append(elementData[i].toString() + ", ");
			}
			int len = sb.length();
			return sb.delete(len - 2 , len).append("]").toString();
		}
	}
}
