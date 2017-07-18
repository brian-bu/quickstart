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
 * ��������������
 * ����AOP��ܣ���HelperLoader������ʱ���룺
 * @see net.brian.coding.java.utils.smartframe.HelperLoader
 * ������ȡ���е�Ŀ�����Լ������ص�������ʵ������ͨ��ProxyManager.createProxy�����������Ҳ���Ǹ���bean����������Bean Map
 * 
 * �������Ƿ���spring���е�AOP��ܵĺ���ʵ�֣����ں���ʵ���������Ļ��ƣ������ľ���ʵ�֣�����
 * @see net.brian.coding.java.utils.smartframe.proxy.AspectProxy
 * @see net.brian.coding.java.utils.smartframe.proxy.ProxyManager
 *
 */
public final class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
/**
 * �����̬��ʼ���鸺���ʼ������AOP��ܣ��������̼���ϸע��
 */
    static {
        try {
        	// ��ȡ�����༰��Ŀ���༯�ϵ�ӳ���ϵ
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            // ��ȡĿ�������������б��ӳ���ϵ
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            // �������Ŀ�������������б��ӳ���ϵ�Ӷ���ȡĿ�������������б�
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                // ���ö�̬�������������Ҳ���Ǹ���bean
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                // �������Ĵ���������Bean Map��
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            LOGGER.error("aop failure", e);
        }
    }
/**
 * ��ȡ�������Ŀ���༯��֮���ӳ���ϵ��һ���������Ӧһ������Ŀ���࣬����Ĵ�����ָ����������
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
     * �����ͨ����
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
     * ������������������������ͨ��������AopHelper���������������Ƶ�
     * ֻ��������Ž�map����TransactionProxy - Serviceӳ��
     */
    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class, serviceClassSet);
    }
/**
 * ��ȡAspectע�������õ�ע����
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
