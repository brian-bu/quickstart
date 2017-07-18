package net.brian.coding.java.utils.smartframe.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.brian.coding.java.utils.apache.DatabaseHelperByDbUtils;
import net.brian.coding.java.utils.smartframe.annotation.Transaction;

/**
 * 事务代理切面类
 * @see net.brian.coding.java.utils.smartframe.helper.AopHelper.addTransactionProxy(Map<Class<?>, Set<Class<?>>>)
 */
public class TransactionProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    // 这个本地线程变量是一个标志，保证同一个线程中事务相关逻辑只会执行一次
    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };
/**
 * 完成事务控制的相关逻辑
 */
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        // 获取目标方法判断是否有Transaction注解
        Method method = proxyChain.getTargetMethod();
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(true);
            try {
                DatabaseHelperByDbUtils.beginTransaction();
                LOGGER.debug("begin transaction");
                result = proxyChain.doProxyChain();
                DatabaseHelperByDbUtils.commitTransaction();
                LOGGER.debug("commit transaction");
            } catch (Exception e) {
            	DatabaseHelperByDbUtils.rollbackTransaction();
                LOGGER.debug("rollback transaction");
                throw e;
            } finally {
            	// 移除本地线程变量的标志
                FLAG_HOLDER.remove();
            }
        } else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}