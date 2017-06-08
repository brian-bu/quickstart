package net.brian.coding.java.apache.httpclient.webcrawler.demo.widesearch.commonshttpclient;

import java.util.HashSet;
import java.util.Set;

public class SetQueue {
	/** 
	  * �ѷ��ʵ�url���ϣ���Visited�� 
	  */  
	private static Set visitedUrl=new HashSet();
	//�����ʵ�URL����
	private static Queue unVisitedUrl=new Queue();
	
	/** 
	  * ��ӵ����ʹ��� URL ������ 
	  */  
	 public static void addVisitedUrl(String url) {  
	  visitedUrl.add(url);  
	 }  
	 /** 
	  * �Ƴ����ʹ��� URL 
	  */  
	 public static void removeVisitedUrl(String url) {  
	  visitedUrl.remove(url);  
	 }  
	 /** 
	  * ����Ѿ����ʵ� URL ��Ŀ 
	  */  
	 public static int getVisitedUrlNum() {  
	  return visitedUrl.size();  
	 }  
	 /** 
	  * ���UnVisited���� 
	  */  
	 public static Queue getUnVisitedUrl() {  
	  return unVisitedUrl;  
	 }  
	 /** 
	  * δ���ʵ�unVisitedUrl������ 
	  */  
	 public static Object unVisitedUrlDeQueue() {  
	  return unVisitedUrl.outQueue();  
	 }  
	 /** 
	  * ��֤���url��unVisitedUrl��ʱ��ÿ�� URLֻ������һ�� 
	  */  
	 public static void addUnvisitedUrl(String url) {  
	  if (url != null && !url.trim().equals("") && !visitedUrl.contains(url)  
	    && !unVisitedUrl.contians(url))  
	   unVisitedUrl.inQueue(url);  
	 }  
	 /** 
	  * �ж�δ���ʵ� URL�������Ƿ�Ϊ�� 
	  */  
	 public static boolean unVisitedUrlsEmpty() {  
	  return unVisitedUrl.empty();  
	 }  
}
