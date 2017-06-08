package net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * ��net.brian.coding.java.web.ssm.spring.demo�У���Ϊdemoʹ�õ�һ��������xml�����ļ�
 * �ֱ��Ӧ������new ClassPathXmlApplicationContext������
 * ����������new ClassPathXmlApplicationContext����demo.xml��������һ��xml��aopdemo.xml�ļ��ؼ���
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.NormalAopLogDemo
 * 
 * Ҫ��һ������ClassPathXmlApplicationContext������������classpath�Ľ��ͼ���
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.NormalAopLogDemo.testSpringAOP()
 * Ҫ�������������������������FetchDataSourceWithPropertyPlaceholderConfigurer
 * Ҳ����ͨ��org.springframework.beans.factory.config.PropertyPlaceholderConfigurer����jdbc��Ӧ��properties�ļ�
 * ����һ��dataSource��bean��Ϊ��ȡproperties�ļ��в�����������Ȼ��ͨ����Щ��������mysql���ݿ�������ݲ�ѯ��
 * a.jdbc���ݿ����ӵľ������ü�/net/brian/coding/java/web/ssm/spring/demo/chapter05/ioc/custombeans/jdbc.properties
 * b.spring��ص�����bean���ü�/net/brian/coding/java/web/ssm/spring/demo/demo.xml
 * c.log�����ü���@see 
 * ���⻹����һ�ַ�ʽ��ȡproperties�ļ��еĲ������ԣ���������@PropertySourceע�⣬�����
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.environmentabstraction.PropertySourseBySpringDemo
 * 
 * Ҫ���������̨log���������
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
 * -->����Ҫ��һ��
 * ����bean xxx created��log���������������Զ��ģ���ȫ����Ϊʵ����BeanPostProcessor�еķ�����ʹ��spring�����ڳ�ʼ��ǰ��ִ��һЩ�������������ϸ�ڼ���
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.BeanPostProcessorDemo
 * ���ﲻ������Լ���demo.xml�����bean���������spring������ʼ����ʱ��Ĭ�ϼ��ص�һЩ����bean
 * ע�⣺���ע�͵�aopdemo.xml�е����bean�����ϵ�log�Ͷ����������ˣ�
 * <bean class="net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.BeanPostProcessorDemo" />
 * -->����Ҫ�����
 * Constructing ConfigurationClasspathScanningAndManagedComponentsDemo...
 * @see net.brian.coding.java.web.ssm.spring.demo.ConfigurationClasspathScanningAndManagedComponentsDemo
 * -->����Ҫ������
 * Bean 'clientService' created
 * ��demo.xml�ļ�������������bean��clientService��accountService�����Ǹ��ݴ�ӡ��log������ֻ��clientService��û��accountService��
 * ������ΪaccountService������lazy-init�����ֻ�б����õ�ʱ��Żᱻע�룬������������ʱ��ֻ��clientService��ע�뵽������
 * -->����Ҫ���ģ�
 * ��Ϊע���������beanע�뵽������������ע�����ͣ�@Service/@Controller/@Configuration/@Bean��
 * ��Ϊ������@Configuration����@Controller�ű�����ע�룺propertySourseBySpringDemo��dataBindingControllerDemo
 * ע�⵽spring������ע��������bean��ʱ���Զ���bean��������ͷ��ĸСд��Ȼ����Ϊbean������
 * ����@Beanע�����ע��������bean����
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.environmentabstraction.PropertySourseBySpringDemo.testBean()
 * ���⣬����net.brian.coding.java.web.ssm.spring.demo.ComponentClasspathScanningAndManagedComponentsDemo�����
 * ��Ȼ��@Service��ע�⣬������Ϊ���bean�����������ó���prototype������@Scope(BeanDefinition.SCOPE_PROTOTYPE)
 * ��������ಢû�б���ӡ������spring�����ǲ����������������prototype��bean��
 * -->����Ҫ���壺
 * createStaticFactory() on ioc container startup...
 * @see net.brian.coding.java.web.ssm.spring.demo.DefaultServiceLocator
 *
 */
public class FetchDataSourceWithPropertyPlaceholderConfigurer {
	private static ApplicationContext context;
	/**
	 * ����context.getBeanDefinitionNames()������spring��������֮���ӡ���������е�bean��name
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
		// �����ѯ��� InputStream in = null; //����������
		@SuppressWarnings("unused")
		int len = 10 * 1024 * 1024; // �����ַ����鳤��
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
