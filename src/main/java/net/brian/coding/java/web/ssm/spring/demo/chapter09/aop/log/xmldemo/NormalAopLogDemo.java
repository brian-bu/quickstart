package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * ��net.brian.coding.java.web.ssm.spring.demo·���У���Ϊdemoʹ�õ�һ��������xml�����ļ�
 * �ֱ��Ӧ������new ClassPathXmlApplicationContext������
 * ����������new ClassPathXmlApplicationContext����aopdemo.xml��������һ��xml��demo.xml�ļ��ؼ���
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.FetchDataSourceWithPropertyPlaceholderConfigurer
 * 
 * Ҫ��һ��classpath֮maven project��java�ļ��ı���·��
 * Ҫ�����ͨ������aopdemo.xml������õ�spring������Ȼ��������л�ȡָ�����Ƶ�bean��loginService
 * Ҫ������ʾ��aopӦ����log����ĳ���
 * 
 * �ͱ�������ص��ļ���
 * a.log�����Pointcut����@see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.LogServiceImpl
 * b.����log��aop���������ã���aopdemo.xml�������룺<aop:aspect id="dd" ref="logService">
 * 
 * ע�⣺ͨ��getBean����ʽ����Ϊ��main�����в���ʹ�ã����ַ�����û����ȫ����spring��api�����ɺ�api����Ϲ�ϵ
 *
 */
public class NormalAopLogDemo {
	private static ApplicationContext ctx; 
	public static void testSpringAOP() {
		// ����maven project��classpath��Ӧ�ð���/src/main/java������netǰ��һ��Ҫ��/
		// ��Ϊ������main����ִ�г���Ҳ���Ƿ�web���������ڷ�web����NormalAopLogDemo.java��ִ��·���ǣ�
		// /quickstart/target/quickstart-0.0.1-SNAPSHOT/WEB-INF/classes
		// ���·������û��/src/main/java��
		ctx = new ClassPathXmlApplicationContext("classpath:/net/brian/coding/java/web/ssm/spring/demo/chapter05/aopdemo.xml");
		ILoginService loginService = (ILoginService) ctx.getBean("loginService");
		loginService.login("zhangsan", "12344");
	}

	public static void main(String[] args) {
		testSpringAOP();
	}
}
