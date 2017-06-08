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
 * 要点一：采用注解方式应该注意，官方文档明确指出：注解的执行顺序要先于xml方式，如果二者产生了冲突，后者的配置会代替前者的配置：
 * Annotation injection is performed before XML injection
 * thus the latter configuration will override the former for properties wired through both approaches
 * 
 * 要点二：这里使用的是aspectj的注解？
 *
 */
public class NormalAopLogDemo {
	//FIXME：Spring：这里用的是spring aop还是aspectj？
    @Pointcut("execution(* net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.annotationdemo.PersonServiceBean.*(..))")  
    private void anyMethod(){}//定义一个切入点  
    @Before("anyMethod() && args(name)")  
    public void doAccessCheck(String name){  
        System.out.println(name);  
        System.out.println("前置通知");  
    }  
      
    @AfterReturning("anyMethod()")  
    public void doAfter(){  
        System.out.println("后置通知");  
    }  
      
    @After("anyMethod()")  
    public void after(){  
        System.out.println("最终通知");  
    }  
      
    @AfterThrowing("anyMethod()")  
    public void doAfterThrow(){  
        System.out.println("例外通知");  
    }  
      
    @Around("anyMethod()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
        System.out.println("进入环绕通知");  
        Object object = pjp.proceed();//执行该方法  
        System.out.println("退出方法");  
        return object;  
    }  
}  

