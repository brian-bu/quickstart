package net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/**
 * 
 * 实现了BeanPostProcessor的类就成了后置处理器，关于后置处理器详细讨论见印象笔记：
 * {@linkplain https://app.yinxiang.com/shard/s62/nl/12840192/65b16be9-66e2-4a8c-86ff-da82379b265d 印象笔记}
 *
 */
public class BeanPostProcessorDemo implements BeanPostProcessor {
	// simply return the instantiated bean as-is
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("BeanPostProcessorDemo.postProcessBeforeInitialization(): Bean '" + beanName + "' created : " + bean.toString());
		return bean; // we could potentially return any object reference here...
	}
	/**
	 * 通过BeanPostProcessor.postProcessAfterInitialization打印初始化后容器中的bean可发现：
	 * 对同一个Bean用@Scope(BeanDefinition.SCOPE_PROTOTYPE)注解标记的时候该bean不会被打印
	 * 用注解@Scope(BeanDefinition.SCOPE_SINGLETON)标记的时候就可以打印出这个bean
	 * 这也说明了spring容器是不负责维护prototype的bean的
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("BeanPostProcessorDemo.postProcessAfterInitialization(): Bean '" + beanName + "' created : " + bean.toString());
		System.out.println("Bean '" + beanName + "' created : " + bean.toString());
		return bean;
	}
}