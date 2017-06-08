package net.brian.coding.java.core.jdk.valueclasses.objectoverriding;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item11: Override clone judiciously
 * 
 * ������Ҫ��ʾ��Object.clone����ʵ�ֵ�ǳ��¡
 * ����Ҳ��ʾ����Object��clone�����Ļ����϶��������������⴦��Ӷ�ʵ�����¡
 * ���⻹ʾ����java.util.HashMap.putAll(Map<? extends K, ? extends V>)��ǳ��¡
 * 
 * ��������һЩ�������
 * ���ǳ����Ҳ�����ǳ������Ҳ��shallow clone��deep clone
 * 
 * ǳ���ƣ�ֻ���ƶ�������ã�����������Ȼָ��ͬһ���������ڴ���ռ��ͬһ���ڴ�
 * �����ƶ�������б�����������ԭ���Ķ�����ͬ��ֵ�������еĶ����������������Ȼָ��ԭ���Ķ���
 * ����֮��ǳ���ƽ������������ǵĶ��󣬶��������������õĶ���
 * 
 * ��ƣ������ƶ�������б�����������ԭ���Ķ�����ͬ��ֵ����ȥ��Щ������������ı���
 * ��Щ������������ı�����ָ�򱻸��ƹ����¶��󣬶�������ԭ�е���Щ�����õĶ���
 * ����֮����ư�Ҫ���ƵĶ��������õĶ��󶼸�����һ��
 * 
 * java.lang.Object��clone()����������һ���������ظ�������
 * ��ΪObject���clone()����ͨ�������¶�����������������ģ�Ȼ������򿽱���field-by-filed��
 * �����ڸ�ֵ���������ֲ�������ԭʼ���ͣ�primitives���Ͳ��ɱ����ͣ�immutable����˵��û�����
 * �����������һЩ�ɱ�����ݽṹ�磺ArrayList������Ͳ�������
 * �������ԭʼ����͸�������ָ����ͬ�Ķѣ�Ϊȷ��ÿһ���ɱ���򱻶����Ŀ�¡����Ҫ��ȿ�¡
 * ������ʾ��Ƕ��������������⴦��
 * 
 * ����ע�⣺
 * a.����clone()�����Ķ������������������ʵ��Cloneable�ӿ���ָʾ Object.clone()�������ԺϷ��ضԸ���ʵ�����а��ֶθ���
 * �����ڵ���Clone������ʱ����׳�CloneNotSupportedException
 * b.Cloneable�ӿ�ʵ�����Ǹ���ʶ�ӿڣ�û���κνӿڷ��������ڱ�ǽӿڵ�˵������
 * @see net.brian.coding.java.core.oop.classesinterfaces.annotation.MarkerAnnotationAndMarkerInterface
 * c.����String�����Ĳ��ɱ����Ӧ���ṩclone�������߱����Թ�����
 * ��Ϊ������Щ����Ľ��ʼ�յ���ԭʼ���󣬵�String���ڲ�û����ʶ����һ���Ծɰ���clone��������Ҫʹ��
 * d.��final�����ʵ�������ܱ�֤clone����һ�����ظ���Ķ��󣬿��ܷ���ר�ų��ڶ����Ŀ�Ķ���ƵĲ������ε�����ʵ��
 * ��˶��ڲ������Ϳ��Ա��������η����໯�Ĳ�������Ҫʹ��clone�������б����Կ���
 * 
 * ������ȿ��������Բο������Ա�дreadObject���ַ���
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.DeepCopyDemo
 * 
 */
public class ShallowCopyDemo implements Cloneable {
	
	private Date hireDay;

	/**
	 * HashMap��putAll���Ի���������������������ã�������������ֻ�´��������
	 */
	@Test
	public void deepCopyByHashMap() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(100);
		list.add(200);

		HashMap<String, Object> map = new HashMap<String, Object>();
		// �Ż�����������
		map.put("basic", 100);
		// �Ŷ���
		map.put("list", list);

		HashMap<String, Object> mapNew = new HashMap<String, Object>();
		mapNew.putAll(map);
		System.out.println("Show results after putAll:: " + mapNew);
		// ���Ļ������͵���ֵû��Ӱ�쵽mapNew�Ķ���˵���Ի������ͷ��������
		map.put("basic", 200);
		System.out.println("Show results after changing primitive type for putAll:: " + mapNew);
		// �����������͵���ֵӰ�쵽mapNew�Ķ���˵�����������ͷ�����ǳ����
		list.add(300);
		System.out.println("Show results after changing reference type for putAll:: " + mapNew);

		mapNew = DeepCopyDemo.deepCopy(map);
		list.add(400);
		// ��β���ͬʱ�����ڻ������ͺ��������͵����
		System.out.println("Show results after changing reference type for deepCopy:: " + mapNew);
	}

	/**
	 * ��ȿ�¡�ı�ְ������Object��clone�����Ļ����϶��������������⴦��
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		ShallowCopyDemo cloned = (ShallowCopyDemo)super.clone();  
	    cloned.hireDay = (Date)hireDay.clone();
	    return cloned;
	}
	
	//TODO: �����֤�����ڲ������Ϳ��Ա��������η����໯�Ĳ�������Ҫʹ��clone�������б����Կ���
}
