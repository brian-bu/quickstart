package net.brian.coding.java.utils.smartframe.proxy;

/**
 * ����ӿ�
 *
 */
public interface Proxy {

    /**
     * ִ����ʽ����
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}