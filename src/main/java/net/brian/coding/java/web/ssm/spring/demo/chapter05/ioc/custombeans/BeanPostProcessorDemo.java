package net.brian.coding.java.web.ssm.spring.demo.chapter05.ioc.custombeans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/**
 * 
 * ʵ����BeanPostProcessor����ͳ��˺��ô����������ں��ô�������ϸ���ۼ�ӡ��ʼǣ�
 * {@linkplain https://app.yinxiang.com/shard/s62/nl/12840192/65b16be9-66e2-4a8c-86ff-da82379b265d ӡ��ʼ�}
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
	 * ͨ��BeanPostProcessor.postProcessAfterInitialization��ӡ��ʼ���������е�bean�ɷ��֣�
	 * ��ͬһ��Bean��@Scope(BeanDefinition.SCOPE_PROTOTYPE)ע���ǵ�ʱ���bean���ᱻ��ӡ
	 * ��ע��@Scope(BeanDefinition.SCOPE_SINGLETON)��ǵ�ʱ��Ϳ��Դ�ӡ�����bean
	 * ��Ҳ˵����spring�����ǲ�����ά��prototype��bean��
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println("BeanPostProcessorDemo.postProcessAfterInitialization(): Bean '" + beanName + "' created : " + bean.toString());
		System.out.println("Bean '" + beanName + "' created : " + bean.toString());
		return bean;
	}
}