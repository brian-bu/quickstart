package net.brian.coding.java.apache.httpclient.webcrawler.demo.widesearch.commonshttpclient;

import java.util.Set;

public class MyCrawler {
	/** 
	  * ʹ�����ӳ�ʼ��URL���� 
	  */  
	 private void initCrawlerWithSeeds(String[] seeds) {  
	  for (int i = 0; i < seeds.length; i++)  
	   SetQueue.addUnvisitedUrl(seeds[i]);  
	 }  
	 // �������������ȡ�� <a target=_blank href="http://www.xxxx.com/" style="color: rgb(0, 102, 153); text-decoration: none;">http://www.xxxx.com</a>��ͷ������  
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
	  // ��ʼ�� URL ����  
	  initCrawlerWithSeeds(seeds);  
	  // ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������ 1000  
	  while (!SetQueue.unVisitedUrlsEmpty()  
	    && SetQueue.getVisitedUrlNum() <= 1000) {  
	   // ��ͷ URL ������  
	   String visitUrl = (String) SetQueue.unVisitedUrlDeQueue();  
	   if (visitUrl == null)  
	    continue;  
	   DownLoad downLoader = new DownLoad();  
	   // ������ҳ  
	   downLoader.downloadFile(visitUrl);  
	   // �� URL �����ѷ��ʵ� URL ��  
	   SetQueue.addVisitedUrl(visitUrl);  
	   // ��ȡ��������ҳ�е� URL  
	   Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);  
	   // �µ�δ���ʵ� URL ���  
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
