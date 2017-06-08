package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.annotationdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//XXX: 现在已经开始用maven的project了，所以最好还是通过junit进行测试，最好把这个类整理到maven的test中去
public class SpringAopJunitTest {
	private ApplicationContext ctx;
	@Test
	public void inteceptorTest() {
		ctx = new ClassPathXmlApplicationContext("classpath:/net/brian/coding/java/web/ssm/spring/demo/chapter05/aopdemo.xml");
		PersonService bean = (PersonService) ctx.getBean("personServiceBean");
		bean.save(null);
	}

}
