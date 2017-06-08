package net.brian.coding.java.apache.httpclient.webcrawler.demo.widesearch.commonshttpclient;

import java.util.Set;

public class MyCrawler {
	/** 
	  * 使用种子初始化URL队列 
	  */  
	 private void initCrawlerWithSeeds(String[] seeds) {  
	  for (int i = 0; i < seeds.length; i++)  
	   SetQueue.addUnvisitedUrl(seeds[i]);  
	 }  
	 // 定义过滤器，提取以 <a target=_blank href="http://www.xxxx.com/" style="color: rgb(0, 102, 153); text-decoration: none;">http://www.xxxx.com</a>开头的链接  
	 public void crawling(String[] seeds) {  
	  LinkFilter filter = new LinkFilter() {  
	   @Override
	public boolean accept(String url) {  
	    if (url.startsWith("http://www.csdn.net/"))  
	     return true;  
	    else  
	     return false;  
	   }  
	  };  
	  // 初始化 URL 队列  
	  initCrawlerWithSeeds(seeds);  
	  // 循环条件：待抓取的链接不空且抓取的网页不多于 1000  
	  while (!SetQueue.unVisitedUrlsEmpty()  
	    && SetQueue.getVisitedUrlNum() <= 1000) {  
	   // 队头 URL 出队列  
	   String visitUrl = (String) SetQueue.unVisitedUrlDeQueue();  
	   if (visitUrl == null)  
	    continue;  
	   DownLoad downLoader = new DownLoad();  
	   // 下载网页  
	   downLoader.downloadFile(visitUrl);  
	   // 该 URL 放入已访问的 URL 中  
	   SetQueue.addVisitedUrl(visitUrl);  
	   // 提取出下载网页中的 URL  
	   Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);  
	   // 新的未访问的 URL 入队  
	   for (String link : links) {  
	    SetQueue.addUnvisitedUrl(link);  
	   }  
	  }  
	 }  
	
	 public static void main(String[] args) {  
	  MyCrawler crawler = new MyCrawler();  
	  crawler.crawling(new String[] { "http://www.csdn.net/" });  
	 }  
}
