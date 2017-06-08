package net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.environmentabstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
/**
 * 
 * 关于@PropertySource中classpath的解释见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.NormalAopLogDemo.testSpringAOP()
 * 对于获取properties文件中的参数属性除本例使用@PropertySource注解外还可通过xml文件中配置PropertyPlaceholderConfigurer，具体实现见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.FetchDataSourceWithPropertyPlaceholderConfigurer
 *
 */
@Configuration
@PropertySource("classpath:/net/brian/coding/java/web/ssm/spring/demo/chapter05/ioc/environmentabstraction/demo.properties")
public class PropertySourseBySpringDemo {
	//FIXME: Spring：这个Autowired无法在容器启动时被注入？如果经过改动能够重新注入了，记得更改FetchDataSourceWithPropertyPlaceholderConfigurer相关注释
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