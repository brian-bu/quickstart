package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo;

import org.aspectj.lang.JoinPoint;

public class LogServiceImpl implements ILogService {

    @Override
    public void log() {
        System.out.println("*************Log*******************");
    }
    
    //�в��޷���ֵ�ķ���
    public void logArg(JoinPoint point) {
        //�˷������ص���һ�����飬�����а���request�Լ�ActionCofig�������
        Object[] args = point.getArgs();
        System.out.println("Ŀ������б�");
        if (args != null) {
            for (Object obj : args) {
                System.out.println(obj + ",");
            }
            System.out.println();
        }
    }

    //�вβ��з���ֵ�ķ���
    public void logArgAndReturn(JoinPoint point, Object returnObj) {
        //�˷������ص���һ�����飬�����а���request�Լ�ActionCofig�������
        Object[] args = point.getArgs();
        System.out.println("Ŀ������б�");
        if (args != null) {
            for (Object obj : args) {
                System.out.println(obj + ",");
            }
            System.out.println();
            System.out.println("ִ�н���ǣ�" + returnObj);
        }
    }
}