package net.brian.coding.java.web.ssm.spring.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * ������java�ķ�ʽ��һ��bean����spring����������ʱ����ӵ�spring����
 * �����������ۿ���ǰbean��ע��Ч����Ӧ�ó������
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
