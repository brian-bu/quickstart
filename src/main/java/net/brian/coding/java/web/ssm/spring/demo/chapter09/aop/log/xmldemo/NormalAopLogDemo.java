package net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 在net.brian.coding.java.web.ssm.spring.demo路径中，作为demo使用的一共有两个xml配置文件
 * 分别对应了两个new ClassPathXmlApplicationContext来加载
 * 本例是利用new ClassPathXmlApplicationContext加载aopdemo.xml，对于另一个xml即demo.xml的加载见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.FetchDataSourceWithPropertyPlaceholderConfigurer
 * 
 * 要点一、classpath之maven project中java文件的编译路径
 * 要点二、通过加载aopdemo.xml里的配置到spring容器，然后从容器中获取指定名称的bean：loginService
 * 要点三、示例aop应用于log输出的场景
 * 
 * 和本程序相关的文件：
 * a.log输出的Pointcut见：@see net.brian.coding.java.web.ssm.spring.demo.chapter09.aop.log.xmldemo.LogServiceImpl
 * b.对于log的aop的切面配置，见aopdemo.xml的这句代码：<aop:aspect id="dd" ref="logService">
 * 
 * 注意：通过getBean的形式仅作为在main方法中测试使用，这种方法并没有完全屏蔽spring的api，依旧和api有耦合关系
 *
 */
public class NormalAopLogDemo {
	private static ApplicationContext ctx; 
	public static void testSpringAOP() {
		// 对于maven project，classpath不应该包含/src/main/java，但是net前面一定要有/
		// 因为这里是main方法执行程序，也就是非web环境，对于非web环境NormalAopLogDemo.java的执行路径是：
		// /quickstart/target/quickstart-0.0.1-SNAPSHOT/WEB-INF/classes
		// 这个路径下是没有/src/main/java的
		ctx = new ClassPathXmlApplicationContext("classpath:/net/brian/coding/java/web/ssm/spring/demo/chapter05/aopdemo.xml");
		ILoginService loginService = (ILoginService) ctx.getBean("loginService");
		loginService.login("zhangsan", "12344");
	}

	public static void main(String[] args) {
		testSpringAOP();
	}
}
