package net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.environmentabstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
/**
 * 
 * ����@PropertySource��classpath�Ľ��ͼ���
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.NormalAopLogDemo.testSpringAOP()
 * ���ڻ�ȡproperties�ļ��еĲ������Գ�����ʹ��@PropertySourceע���⻹��ͨ��xml�ļ�������PropertyPlaceholderConfigurer������ʵ�ּ���
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.FetchDataSourceWithPropertyPlaceholderConfigurer
 *
 */
@Configuration
@PropertySource("classpath:/net/brian/coding/java/web/ssm/spring/demo/chapter05/ioc/environmentabstraction/demo.properties")
public class PropertySourseBySpringDemo {
	//FIXME: Spring�����Autowired�޷�����������ʱ��ע�룿��������Ķ��ܹ�����ע���ˣ��ǵø���FetchDataSourceWithPropertyPlaceholderConfigurer���ע��
	@Autowired
	Environment env;

	@Bean
	public TestBean testBean() {
		TestBean testBean = new TestBean();
		testBean.setName(env.getProperty("testbean.name"));
		System.out.println("PropertySourseBySpringDemo -- testBean.getName() -- name:: " + testBean.getName());
		return testBean;
	}
}
class TestBean {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}