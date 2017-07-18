package net.brian.coding.designpatterns.proxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

public class CglibBean {
	/**
	 * ʵ��Object
	 */
	public Object object = null;

	/**
	 * ����map
	 */
	public BeanMap beanMap = null;

	public CglibBean() {
		super();
	}

	@SuppressWarnings("unchecked")
	public CglibBean(Map propertyMap) {
		this.object = generateBean(propertyMap);
		this.beanMap = BeanMap.create(this.object);
	}

	/**
	 * ��bean���Ը�ֵ
	 * 
	 * @param property
	 *            ������
	 * @param value
	 *            ֵ
	 */
	public void setValue(String property, Object value) {
		beanMap.put(property, value);
	}

	/**
	 * ͨ���������õ�����ֵ
	 * 
	 * @param property
	 *            ������
	 * @return ֵ
	 */
	public Object getValue(String property) {
		return beanMap.get(property);
	}

	/**
	 * �õ���ʵ��bean����
	 * 
	 * @return
	 */
	public Object getObject() {
		return this.object;
	}

	@SuppressWarnings("unchecked")
	private Object generateBean(Map propertyMap) {
		BeanGenerator generator = new BeanGenerator();
		Set keySet = propertyMap.keySet();
		for (Iterator i = keySet.iterator(); i.hasNext();) {
			String key = (String) i.next();
			generator.addProperty(key, (Class) propertyMap.get(key));
		}
		return generator.create();
	}

	@SuppressWarnings("unchecked")
	public static CglibBean createCglibBean(String beanName) {

		// �������Ա����
		HashMap propertyMap = new HashMap();
		try {
			propertyMap.put("id"+beanName, Class.forName("java.lang.Integer"));
			propertyMap.put("name"+beanName, Class.forName("java.lang.String"));
			propertyMap.put("address"+beanName, Class.forName("java.lang.String"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ���ɶ�̬ Bean
		CglibBean bean = new CglibBean(propertyMap);

		// �� Bean ����ֵ
//		bean.setValue("id", new Integer(123));

//		bean.setValue("name", "454");

		// bean.setValue("address", "789");
		//
		// // �� Bean �л�ȡֵ����Ȼ�˻��ֵ�������� Object
		//
		// System.out.println(" >> id = " + bean.getValue("id"));
		//
		// System.out.println(" >> name = " + bean.getValue("name"));
		//
		// System.out.println(" >> address = " + bean.getValue("address"));
		//
		// // ���bean��ʵ��
		// Object object = bean.getObject();
		//
		// // ͨ������鿴���з�����
		// Class clazz = object.getClass();
		// Method[] methods = clazz.getDeclaredMethods();
		// for (int i = 0; i < methods.length; i++) {
		// System.out.println(methods[i].getName());
		// }
		return bean;
	}
}