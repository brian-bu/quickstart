package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo;

import org.aspectj.lang.JoinPoint;

public interface ILogService {
    //�޲ε���־����
    public void log();
    //�вε���־����
    public void logArg(JoinPoint point);
    //�в��з���ֵ�ķ���
    public void logArgAndReturn(JoinPoint point,Object returnObj);
}