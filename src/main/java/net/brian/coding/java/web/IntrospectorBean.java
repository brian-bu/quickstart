package net.brian.coding.java.web;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class IntrospectorBean {

	@Parameters
	public static Collection<Student> testingDataForListPropertyNames() {
		Collection<Student> bean = new ArrayList<Student>();
		bean.add(new Student());
		return bean;
	}

	@Test
	public void listPropertyNames(Class<?> c) throws IntrospectionException {
		PropertyDescriptor[] pd;
		pd = Introspector.getBeanInfo(c).getPropertyDescriptors();
		for (int i = 0; i < pd.length; i++) {
			System.out.println(pd[i].getName() + " (" + pd[i].getPropertyType().getName() + ")");
		}
	}
}
class Student {
	private String name;
	private String id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}