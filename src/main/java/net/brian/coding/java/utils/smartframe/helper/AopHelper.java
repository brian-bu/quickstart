package net.brian.coding.java.utils.smartframe.helper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.brian.coding.java.utils.smartframe.annotation.Aspect;
import net.brian.coding.java.utils.smartframe.annotation.Service;
import net.brian.coding.java.utils.smartframe.proxy.AspectProxy;
import net.brian.coding.java.utils.smartframe.proxy.Proxy;
import net.brian.coding.java.utils.smartframe.proxy.ProxyManager;
import net.brian.coding.java.utils.smartframe.proxy.TransactionProxy;

/**
 * 方法拦截助手类
 * 加载AOP框架，由HelperLoader在启动时载入：
 * @see net.brian.coding.java.utils.smartframe.HelperLoader
 * 这个类获取所有的目标类以及被拦截的切面类实例，并通过ProxyManager.createProxy创建代理对象（也就是各个bean）最后将其放入Bean Map
 * 
 * 这个类就是仿照spring进行的AOP框架的核心实现，至于核心实现所依赖的机制（代理）的具体实现，见：
 * @see net.brian.coding.java.utils.smartframe.proxy.AspectProxy
 * @see net.brian.coding.java.utils.smartframe.proxy.ProxyManager
 *
 */
public final class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
/**
 * 这个静态初始化块负责初始化整个AOP框架，具体流程见详细注释
 */
    static {
        try {
        	// 获取代理类及其目标类集合的映射关系
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            // 获取目标类与代理对象列表的映射关系
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            // 遍历这个目标类与代理对象列表的映射关系从而获取目标类与代理对象列表
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                // 利用动态代理创建代理对象，也就是各个bean
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                // 将创建的代理对象放入Bean Map中
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            LOGGER.error("aop failure", e);
        }
    }
/**
 * 获取代理类和目标类集合之间的映射关系，一个代理类对应一个或多个目标类，这里的代理类指的是切面类
 * @return
 * @throws Exception
 */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }
    /**
     * 添加普通代理
     * @param proxyMap
     * @throws Exception
     */
    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass : proxyClassSet) {
            if (proxyClass.isAnnotationPresent(Aspect.class)) {
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
    }
    /**
     * 用于添加事务代理，事务代理和普通代理都是由AopHelper这个代理拦截类控制的
     * 只不过这里放进map的是TransactionProxy - Service映射
     */
    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class, serviceClassSet);
    }
/**
 * 获取Aspect注解中设置的注解类
 * @param aspect
 * @return
 * @throws Exception
 */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect.class)) {
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass : targetClassSet) {
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<Proxy>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }
        return targetMap;
    }
}
