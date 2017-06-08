package net.brian.coding.java.core.jdk.reflection;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.brian.coding.java.utils.entity.DemoBean;

/**
 * 
 * 大部分情况下，如果不设计框架，就很少会用到反射，但是这里也做一个初步了解
 * 这里仅针对反射常用的API写了一个应用的Demo
 * 
 * 我对反射的理解：所谓反射，就是根据一个已经实例化了的对象来还原类的完整信息
 * 
 */
public class ReflectionDemo {
	@Test
	public void loadClassElements() throws Exception {
		this.introspector2InvokeGettersAndSetters();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadConstructors() throws Exception {
		Class clazz = Class.forName("net.brian.java.web.demo.Demo");
		Constructor constructorWithoutParam = clazz.getConstructor(null);
		DemoBean constructorWithoutParamDemo = (DemoBean) constructorWithoutParam.newInstance(null);

		List<String> demoList = new ArrayList<String>();
		demoList.add("Hello List");
		Constructor constructorWithListParam = clazz.getDeclaredConstructor(List.class);
		constructorWithListParam.setAccessible(true);
		// The constructor Demo(List<String>) is not visible
		// Demo demo = new Demo(demoList);
		// But this time we can use reflection to visit the private constructor.
		DemoBean constructorWithListParamDemo = (DemoBean) constructorWithListParam.newInstance(demoList);

		String demoStr = "Hello String!";
		Constructor constructorWithStringParam = clazz.getConstructor(String.class);
		DemoBean constructorWithStringParamDemo = (DemoBean) constructorWithStringParam.newInstance(demoStr);
	}
	
	public void loadMain1() throws Exception {
		Class<?> clazz = Class.forName("net.brian.java.web.demo.Demo");
		Method[] methodMain = clazz.getMethods();
		String methodName = methodMain[0].getName();
		if(null != methodName && "main".equalsIgnoreCase(methodName)) {
			Object[] invokingMethod_ParamList = {new String[]{}};
			// Output Hello Demo!
			methodMain[0].invoke(DemoBean.class.newInstance(), invokingMethod_ParamList);
			// Output Hello Demo!
			methodMain[0].invoke(DemoBean.class, invokingMethod_ParamList);
			// Output Hello Demo! main() is a static method, we don't need reference to Demo class to invoke it.
			methodMain[0].invoke(null, invokingMethod_ParamList);
		} else {
			System.out.println("methodMain[0] is not the method named main!");
		}
	}
	public void loadMain2() throws Exception {

		Class<?> clazz = Class.forName("net.brian.java.web.demo.Demo");
		Method methodMain = clazz.getMethod("main", String[].class);
		// In jdk1.4 main(String str1, String str2) should be there if you do like below:
		// methodMain.invoke(null, new String[]{"name", "pass"});
		// Consider the compatibility with 1.4, have to pass String[] for main(String[] args)
		methodMain.invoke(null, new Object[]{new String[]{}});
	}
	public void introspectorExceptClassItself() throws IntrospectionException {
		BeanInfo beanInfoExceptClassItsel = Introspector.getBeanInfo(DemoBean.class, Object.class);
		BeanDescriptor beanDescriptor = beanInfoExceptClassItsel.getBeanDescriptor();
		MethodDescriptor[] methodDescriptors = beanInfoExceptClassItsel.getMethodDescriptors();
		PropertyDescriptor[] propertyDescriptors = beanInfoExceptClassItsel.getPropertyDescriptors();
		for(MethodDescriptor methodDescriptor : methodDescriptors) {
			System.out.println("MethodDescriptor[] methodDescriptors --> " + methodDescriptor.getName());
		}
		for(PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println("PropertyDescriptor[] propertyDescriptors --> " + propertyDescriptor.getName());
		}
		System.out.println("BeanDescriptor beanDescriptor -- >" + beanDescriptor.getName());
	}
	
	public void introspectorIncludingClassItself() throws IntrospectionException {
		BeanInfo beanInfoIncludingClassItself = Introspector.getBeanInfo(DemoBean.class);
		BeanDescriptor beanDescriptor = beanInfoIncludingClassItself.getBeanDescriptor();
		MethodDescriptor[] methodDescriptors = beanInfoIncludingClassItself.getMethodDescriptors();
		PropertyDescriptor[] propertyDescriptors = beanInfoIncludingClassItself.getPropertyDescriptors();
		for(MethodDescriptor methodDescriptor : methodDescriptors) {
			System.out.println("MethodDescriptor[] methodDescriptors --> " + methodDescriptor.getName());
		}
		for(PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println("PropertyDescriptor[] propertyDescriptors --> " + propertyDescriptor.getName());
		}
		System.out.println("BeanDescriptor beanDescriptor -- >" + beanDescriptor.getName());
	}
	
	public void introspector2InvokeGettersAndSetters() throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DemoBean demo = new DemoBean();
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", DemoBean.class);
		// The same as demo.setName("Brian");
		propertyDescriptor.getWriteMethod().invoke(demo, "Brian");
		// The same as demo.getName();
		System.out.println(propertyDescriptor.getReadMethod().invoke(demo, null));
	}
}
