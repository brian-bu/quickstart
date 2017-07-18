package net.brian.coding.java.utils.smartframe.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.brian.coding.java.utils.apache.DatabaseHelperByDbUtils;
import net.brian.coding.java.utils.smartframe.annotation.Transaction;

/**
 * �������������
 * @see net.brian.coding.java.utils.smartframe.helper.AopHelper.addTransactionProxy(Map<Class<?>, Set<Class<?>>>)
 */
public class TransactionProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    // ��������̱߳�����һ����־����֤ͬһ���߳�����������߼�ֻ��ִ��һ��
    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };
/**
 * ���������Ƶ�����߼�
 */
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        // ��ȡĿ�귽���ж��Ƿ���Transactionע��
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
            	// �Ƴ������̱߳����ı�־
                FLAG_HOLDER.remove();
            }
        } else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}