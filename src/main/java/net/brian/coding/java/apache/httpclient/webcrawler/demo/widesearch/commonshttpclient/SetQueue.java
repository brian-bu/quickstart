package net.brian.coding.java.apache.httpclient.webcrawler.demo.widesearch.commonshttpclient;

import java.util.HashSet;
import java.util.Set;

public class SetQueue {
	/** 
	  * 已访问的url集合，即Visited表 
	  */  
	private static Set visitedUrl=new HashSet();
	//待访问的URL集合
	private static Queue unVisitedUrl=new Queue();
	
	/** 
	  * 添加到访问过的 URL 队列中 
	  */  
	 public static void addVisitedUrl(String url) {  
	  visitedUrl.add(url);  
	 }  
	 /** 
	  * 移除访问过的 URL 
	  */  
	 public static void removeVisitedUrl(String url) {  
	  visitedUrl.remove(url);  
	 }  
	 /** 
	  * 获得已经访问的 URL 数目 
	  */  
	 public static int getVisitedUrlNum() {  
	  return visitedUrl.size();  
	 }  
	 /** 
	  * 获得UnVisited队列 
	  */  
	 public static Queue getUnVisitedUrl() {  
	  return unVisitedUrl;  
	 }  
	 /** 
	  * 未访问的unVisitedUrl出队列 
	  */  
	 public static Object unVisitedUrlDeQueue() {  
	  return unVisitedUrl.outQueue();  
	 }  
	 /** 
	  * 保证添加url到unVisitedUrl的时候每个 URL只被访问一次 
	  */  
	 public static void addUnvisitedUrl(String url) {  
	  if (url != null && !url.trim().equals("") && !visitedUrl.contains(url)  
	    && !unVisitedUrl.contians(url))  
	   unVisitedUrl.inQueue(url);  
	 }  
	 /** 
	  * 判断未访问的 URL队列中是否为空 
	  */  
	 public static boolean unVisitedUrlsEmpty() {  
	  return unVisitedUrl.empty();  
	 }  
}
