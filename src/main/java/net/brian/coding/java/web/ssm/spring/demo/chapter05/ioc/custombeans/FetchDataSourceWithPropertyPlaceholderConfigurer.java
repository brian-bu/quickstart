package net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 在net.brian.coding.java.web.ssm.spring.demo中，作为demo使用的一共有两个xml配置文件
 * 分别对应了两个new ClassPathXmlApplicationContext来加载
 * 本例是利用new ClassPathXmlApplicationContext加载demo.xml，对于另一个xml即aopdemo.xml的加载见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.NormalAopLogDemo
 * 
 * 要点一、关于ClassPathXmlApplicationContext构造器参数中classpath的解释见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.NormalAopLogDemo.testSpringAOP()
 * 要点二、本类的最初作用如其名：FetchDataSourceWithPropertyPlaceholderConfigurer
 * 也就是通过org.springframework.beans.factory.config.PropertyPlaceholderConfigurer加载jdbc对应的properties文件
 * 定义一个dataSource的bean作为获取properties文件中参数的渠道，然后通过这些参数连接mysql数据库进行数据查询：
 * a.jdbc数据库连接的具体配置见/net/brian/coding/java/web/ssm/spring/demo/chapter05/ioc/custombeans/jdbc.properties
 * b.spring相关的容器bean配置见/net/brian/coding/java/web/ssm/spring/demo/demo.xml
 * c.log的配置见：@see 
 * 此外还有另一种方式获取properties文件中的参数属性，是利用了@PropertySource注解，详见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.environmentabstraction.PropertySourseBySpringDemo
 * 
 * 要点二、控制台log输出分析：
 * Bean 'propertySourseBySpringDemo' created : net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.environmentabstraction.PropertySourseBySpringDemo$$EnhancerBySpringCGLIB$$aa4ec6d3@4278a03f
 * Bean 'dataBindingControllerDemo' created : net.brian.coding.java.web.ssm.spring.demo.chapter07.mvc.databinding.DataBindingControllerDemo@147ed70f
 * Constructing ConfigurationClasspathScanningAndManagedComponentsDemo...
 * Bean 'configurationClasspathScanningAndManagedComponentsDemo' created : net.brian.coding.java.web.ssm.spring.demo.ConfigurationClasspathScanningAndManagedComponentsDemo$$EnhancerBySpringCGLIB$$e3e6dea3@61dd025
 * Bean 'org.springframework.context.event.internalEventListenerProcessor' created : org.springframework.context.event.EventListenerMethodProcessor@6973bf95
 * Bean 'org.springframework.context.event.internalEventListenerFactory' created : org.springframework.context.event.DefaultEventListenerFactory@229d10bd
 * Bean 'dataSource' created : org.apache.commons.dbcp.BasicDataSource@2b6faea6
 * createStaticFactory() on ioc container startup...
 * Bean 'staticFactory' created : net.brian.coding.java.web.ssm.spring.demo.ClientService@794cb805
 * Bean 'instanceFactory' created : net.brian.coding.java.web.ssm.spring.demo.DefaultServiceLocator@4b5a5ed1
 * Bean 'clientService' created : net.brian.coding.java.web.ssm.spring.demo.ClientServiceImpl@3cc2931c
 * PropertySourseBySpringDemo -- testBean.getName() -- name:: helloworld
 * Bean 'testBean' created : net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.environmentabstraction.TestBean@1c93084c
 * Output the value of cno:: computer
 * -->分析要点一：
 * 对于bean xxx created的log输出，这种输出是自动的，完全是因为实现了BeanPostProcessor中的方法，使得spring容器在初始化前后执行一些额外操作，具体细节见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.BeanPostProcessorDemo
 * 这里不单输出自己在demo.xml定义的bean，还会输出spring容器初始化的时候默认加载的一些功能bean
 * 注意：如果注释掉aopdemo.xml中的这个bean，以上的log就都不会打出来了：
 * <bean class="net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.BeanPostProcessorDemo" />
 * -->分析要点二：
 * Constructing ConfigurationClasspathScanningAndManagedComponentsDemo...
 * @see net.brian.coding.java.web.ssm.spring.demo.ConfigurationClasspathScanningAndManagedComponentsDemo
 * -->分析要点三：
 * Bean 'clientService' created
 * 在demo.xml文件中配置了两个bean：clientService和accountService。但是根据打印的log来看，只有clientService，没有accountService。
 * 这是因为accountService配置了lazy-init，因此只有被调用的时候才会被注入，而容器启动的时候只有clientService被注入到了容器
 * -->分析要点四：
 * 因为注解而被当作bean注入到容器的有以下注解类型：@Service/@Controller/@Configuration/@Bean等
 * 因为配置了@Configuration或者@Controller才被容器注入：propertySourseBySpringDemo和dataBindingControllerDemo
 * 注意到spring容器在注入这两个bean的时候自动将bean的类名开头字母小写，然后作为bean的名字
 * 对于@Bean注解而被注入容器的bean见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.environmentabstraction.PropertySourseBySpringDemo.testBean()
 * 此外，对于net.brian.coding.java.web.ssm.spring.demo.ComponentClasspathScanningAndManagedComponentsDemo这个类
 * 虽然有@Service的注解，但是因为这个bean的作用域被设置成了prototype，即：@Scope(BeanDefinition.SCOPE_PROTOTYPE)
 * 所以这个类并没有被打印出来，spring容器是不负责管理作用域是prototype的bean的
 * -->分析要点五：
 * createStaticFactory() on ioc container startup...
 * @see net.brian.coding.java.web.ssm.spring.demo.DefaultServiceLocator
 *
 */
public class FetchDataSourceWithPropertyPlaceholderConfigurer {
	private static ApplicationContext context;
	/**
	 * 利用context.getBeanDefinitionNames()可以在spring容器启动之后打印容器中所有的bean的name
	 * @param context
	 */
	public static void outputBeanDefinitionNamesOnIocStartup(ApplicationContext context) {
		String[] beanDefNames = context.getBeanDefinitionNames();
		for (String beanDefName : beanDefNames) {
			System.out.println("SpringDemo -- Bean Definition Names: " + beanDefName);
		}
	}
	public static void main(String[] args) throws SQLException {
		context = new ClassPathXmlApplicationContext("classpath:/net/brian/coding/java/web/ssm/spring/demo/demo.xml");
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String SQLString = "";
		// 定义查询语句 InputStream in = null; //定义输入流
		@SuppressWarnings("unused")
		int len = 10 * 1024 * 1024; // 定义字符数组长度
		try {
			DataSource dataSource = (DataSource) context.getBean("dataSource");
			SQLString = "select cname from demo_sqlart_course where cno = 10";
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQLString);
		} catch (Exception e) {
			System.out.println("Failed to fetch data from db:: " + e.toString());
		}
		if (rs != null) {
			while (rs.next()) {
				String cno = rs.getString(1);
				System.out.println("Output the value of cno:: " + cno);
			}
		}
	}
}
