package net.brian.coding.java.apache.httpclient.webcrawler.demo.widesearch.httpclient;

public interface LinkFilter {
    public boolean accept(String url);
}