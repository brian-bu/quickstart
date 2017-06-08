package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.annotationdemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
/**
 * 
 * Ҫ��һ������ע�ⷽʽӦ��ע�⣬�ٷ��ĵ���ȷָ����ע���ִ��˳��Ҫ����xml��ʽ��������߲����˳�ͻ�����ߵ����û����ǰ�ߵ����ã�
 * Annotation injection is performed before XML injection
 * thus the latter configuration will override the former for properties wired through both approaches
 * 
 * Ҫ���������ʹ�õ���aspectj��ע�⣿
 *
 */
public class NormalAopLogDemo {
	//FIXME��Spring�������õ���spring aop����aspectj��
    @Pointcut("execution(* net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.annotationdemo.PersonServiceBean.*(..))")  
    private void anyMethod(){}//����һ�������  
    @Before("anyMethod() && args(name)")  
    public void doAccessCheck(String name){  
        System.out.println(name);  
        System.out.println("ǰ��֪ͨ");  
    }  
      
    @AfterReturning("anyMethod()")  
    public void doAfter(){  
        System.out.println("����֪ͨ");  
    }  
      
    @After("anyMethod()")  
    public void after(){  
        System.out.println("����֪ͨ");  
    }  
      
    @AfterThrowing("anyMethod()")  
    public void doAfterThrow(){  
        System.out.println("����֪ͨ");  
    }  
      
    @Around("anyMethod()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
        System.out.println("���뻷��֪ͨ");  
        Object object = pjp.proceed();//ִ�и÷���  
        System.out.println("�˳�����");  
        return object;  
    }  
}  

