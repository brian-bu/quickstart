package net.brian.coding.java.web.ssm.spring.demo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
//This bean will not maintained by spring container since it is prototype scoped.
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
public class ComponentClasspathScanningAndManagedComponentsDemo {
	public ComponentClasspathScanningAndManagedComponentsDemo() {
		System.out.println("Constructing ComponentClasspathScanningAndManagedComponentsDemo...");
	}
}
