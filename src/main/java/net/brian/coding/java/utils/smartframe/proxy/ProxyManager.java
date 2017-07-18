package net.brian.coding.java.utils.smartframe.proxy;

import java.lang.reflect.Method;
import java.util.List;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理管理器：提供创建代理对象的方法
 *
 */
public class ProxyManager {
/**
 * 创建代理对象的方法
 * @param targetClass 输入一个代理对
 * @param proxyList 输入一组Proxy接口的实现
 * @return 输出一个代理对象
 * @see net.brian.coding.java.utils.smartframe.proxy.AspectProxy.doProxy(ProxyChain)
 */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
            }
        });
    }
}