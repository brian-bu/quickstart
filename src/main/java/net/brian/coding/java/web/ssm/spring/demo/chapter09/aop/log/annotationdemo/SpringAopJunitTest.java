package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.annotationdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//XXX: �����Ѿ���ʼ��maven��project�ˣ�������û���ͨ��junit���в��ԣ���ð����������maven��test��ȥ
public class SpringAopJunitTest {
	private ApplicationContext ctx;
	@Test
	public void inteceptorTest() {
		ctx = new ClassPathXmlApplicationContext("classpath:/net/brian/coding/java/web/ssm/spring/demo/chapter05/aopdemo.xml");
		PersonService bean = (PersonService) ctx.getBean("personServiceBean");
		bean.save(null);
	}

}
