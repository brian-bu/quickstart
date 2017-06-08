package net.brian.coding.java.utils.apache;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import net.brian.coding.java.utils.entity.DemoBean;

public final class BeanUtilsDemo {
	
	private BeanUtilsDemo() {}
	
	@Test
	public void execute() throws Exception {
		this.testSetProperty();
	}

	private void testSetProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DemoBean demo = new DemoBean();
		BeanUtils.setProperty(demo, "name", "Brian");
		String name = BeanUtils.getSimpleProperty(demo, "name");
		System.out.println("BeanUtils.getSimpleProperty --> name: " + name);
	}
}
