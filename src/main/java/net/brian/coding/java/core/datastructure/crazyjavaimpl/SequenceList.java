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
public class SequenceList<T>
{
	private int DEFAULT_SIZE = 16;
	//保存数组的长度。
	private int capacity;
	//定义一个数组用于保存顺序线性表的元素
	private Object[] elementData;
	//保存顺序表中元素的当前个数
	private int size = 0;
	//以默认数组长度创建空顺序线性表
	public SequenceList()
	{
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
	}
	//以一个初始化元素来创建顺序线性表
	public SequenceList(T element)
	{
		this();
		elementData[0] = element;
		size++;
	}
	/**
	 * 以指定长度的数组来创建顺序线性表
	 * @param element 指定顺序线性表中第一个元素
	 * @param initSize 指定顺序线性表底层数组的长度
	 */
	public SequenceList(T element , int initSize)
	{
		capacity = 1;
		//把capacity设为大于initSize的最小的2的n次方
		while (capacity < initSize)
		{
			capacity <<= 1;
		}
		elementData = new Object[capacity];
		elementData[0] = element;
		size++;
	}
	//获取顺序线性表的大小
	public int length()
	{
		return size;
	}
	//获取顺序线性表中索引为i处的元素
	public T get(int i)
	{
		if (i < 0 || i > size - 1)
		{
			throw new IndexOutOfBoundsException("线性表索引越界");
		}
		return (T)elementData[i];
	}
	//查找顺序线性表中指定元素的索引
	public int locate(T element)
	{
		for (int i = 0 ; i < size ; i++)
		{
			if (elementData[i].equals(element))
			{
				return i;
			}
		}
		return -1;
	}
	//向顺序线性表的指定位置插入一个元素。
	public void insert(T element , int index)
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("线性表索引越界");
		}
		ensureCapacity(size + 1);
		//将index处以后所有元素向后移动一格。
		System.arraycopy(elementData , index , elementData
			 , index + 1 , size - index);
		elementData[index] = element;
		size++;
	}
	//在线性顺序表的开始处添加一个元素。
	public void add(T element)
	{
		insert(element , size);
	}
	//很麻烦，而且性能很差
	private void ensureCapacity(int minCapacity)
	{
		//如果数组的原有长度小于目前所需的长度
		if (minCapacity > capacity)
		{
			//不断地将capacity * 2，直到capacity大于minCapacity为止
			while (capacity < minCapacity)
			{
				capacity <<= 1;
			}
			elementData = Arrays.copyOf(elementData , capacity);
		}
	}
	//删除顺序线性表中指定索引处的元素
	public T delete(int index)
	{
		if (index < 0 || index > size - 1)
		{
			throw new IndexOutOfBoundsException("线性表索引越界");
		}
		T oldValue = (T)elementData[index];
		int numMoved = size - index - 1;
		if (numMoved > 0)
		{
			System.arraycopy(elementData , index+1
				, elementData, index , 	numMoved);
		}
		//清空最后一个元素
		elementData[--size] = null; 
		return oldValue;
	}
	//删除顺序线性表中最后一个元素
    public T remove()
	{
		return delete(size - 1);
	}
	//判断顺序线性表是否为空表
	public boolean empty()
	{
		return size == 0;
	}
	//清空线性表
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
			for (int i = 0 ; i < size ; i++ )
			{
				sb.append(elementData[i].toString() + ", ");
			}
			int len = sb.length();
			return sb.delete(len - 2 , len).append("]").toString();
		}
	}
}
