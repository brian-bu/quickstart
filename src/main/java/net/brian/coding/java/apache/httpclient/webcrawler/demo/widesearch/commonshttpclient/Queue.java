package net.brian.coding.java.apache.httpclient.webcrawler.demo.widesearch.commonshttpclient;

import java.util.LinkedList;

/** 
 * �Զ�������� ����TODO�� 
 */  
public class Queue {
		//ʹ������ʵ�ֶ���
	private   static final LinkedList<Object> queue;
	static{
		queue=new LinkedList<Object>();
	}
	/** 
	  * ��t���뵽������ 
	  */  
	 public void inQueue(Object t) {  
	  queue.addLast(t);  
	 }  
	 /** 
	  * �Ƴ������еĵ�һ����䷵�� 
	  */  
	 public Object outQueue() {  
	  return queue.removeFirst();  
	 }  
	 /** 
	  * ���ض����Ƿ�Ϊ�� 
	  */  
	 public boolean isQueueEmpty() {  
	  return queue.isEmpty();  
	 }  
	 /** 
	  * �жϲ����ض����Ƿ����t 
	  */  
	 public boolean contians(Object t) {  
	  return queue.contains(t);  
	 }  
	 /** 
	  * �жϲ����ض����Ƿ�Ϊ�� 
	  */  
	 public boolean empty() {  
	  return queue.isEmpty();  
	 }  
	
}
