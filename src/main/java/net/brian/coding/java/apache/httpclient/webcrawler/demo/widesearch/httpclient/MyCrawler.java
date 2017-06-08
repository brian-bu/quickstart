package net.brian.coding.java.apache.httpclient.webcrawler.demo.widesearch.httpclient;

import java.io.IOException;
import java.util.Set;

public class MyCrawler {
    /**
     * ʹ�����ӳ�ʼ��URL����
     * @return
     * @param seeds ����URL
     */
     
    private void initCrawlerWithSeeds(String[] seeds) {
        for(int i=0; i<seeds.length; i++)
            LinkQueue.addUnvisitedUrl(seeds[i]);
    }
     
    /**
     * ץȥ����
     * @return
     * @param seeds
     * @throws IOException 
     */
    public void crawling(String[] seeds) throws IOException {
        //���������,��ȡ��http://www.lietu.com��ͷ������
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if(url.startsWith("http://www.lietu.com"))
                    return true;
                else
                    return false;
            }
        };
         
        //��ʼ��URL����
        initCrawlerWithSeeds(seeds);
        //ѭ������: ��ץȥ�����Ӳ�����ץȥ����ҳ������1000
        while(!LinkQueue.unVisitedUrlIsEmpty() && LinkQueue.getVisitedUrlNum() <= 1000) {
            //��ͷURL������
            String visitUrl = (String)LinkQueue.unVisitedUrlDeQueue();
            if(visitUrl == null)
                continue;
            DownloadFile downLoader = new DownloadFile();
            //������ҳ
            downLoader.downloadFile(visitUrl);
            System.out.println("���ص���ҳΪ: " + visitUrl);
            //��URL�����ѷ��ʵ�URL��
            LinkQueue.addVisitedUrl(visitUrl);
            //��ȡ������ҳ�е�URL
            Set<String> links = HtmlParserTool.extractLinks(visitUrl, filter);
            //�µ�δ���ʵ�URL���
            for(String link:links) {
                LinkQueue.addUnvisitedUrl(link);
            }
        }
    }
     
    //main��ڷ���
    public static void main(String args[]) throws IOException {
        MyCrawler crawler = new MyCrawler();
        crawler.crawling(new String[]{"http://www.lietu.com"});
        System.out.println("������\n");
    }
}