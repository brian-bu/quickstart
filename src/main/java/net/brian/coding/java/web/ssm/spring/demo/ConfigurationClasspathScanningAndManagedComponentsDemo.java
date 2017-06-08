package net.brian.coding.java.web.ssm.spring.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * 测试用java的方式将一个bean类在spring容器启动的时候添加到spring容器
 * 启动容器并观看当前bean的注入效果的应用程序见：
 * @see net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans.FetchDataSourceWithPropertyPlaceholderConfigurer
 *
 */
@Configuration
@ComponentScan(basePackages="net.brian.coding.java.web.ssm.spring.demo")
public class ConfigurationClasspathScanningAndManagedComponentsDemo {
	public ConfigurationClasspathScanningAndManagedComponentsDemo() {
		System.out.println("Constructing ConfigurationClasspathScanningAndManagedComponentsDemo...");
	}
}
