package net.brian.coding.designpatterns.builder;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item02: Consider a builder when faced with many constructor parameters
 * 
 * Point 1���ص�������ģʽ���������˴˶�����ÿ�������������б���һ�������Ը��ݲ�������Ҫ����
 * ȱ�㣺�˷������ʺ��ڲ���̫�����������Ҳ���һ����Եÿͻ��˴���ɶ��Լ��
 * Point 2����javabean�ķ�ʽ�������Խ������б����ó�bean�е����ԣ�Ȼ��ͨ��setter���ﴫֵ
 * ȱ�㣺���ַ�ʽ����ֹ�˰������ɲ��ɱ�Ŀ��ܣ������Ҫ����Ա���������Ŭ��ȷ�������̰߳�ȫ���������ַ�ʽ���õ�Ԫ���Ա������
 * 
 * Builderģʽ�Ϳ��Լȱ�֤���ص�������ģʽ��ȫ�ԣ�Ҳ��֤��javabeanģʽ�����Ŀɶ���
 * 
 * item40: Design method signatures carefully
 * 
 * ��������Ĳ����б�����������ݿ���õ�API������Ѳ�ѯ���������Ϊ�������������У���ô�����б�ͺܳ���
 * ������ͬ���͵Ĳ���������ͬ��boolean�����㴫������false�������б�֮��ͻ����ˣ�Ҳ��֪���ĸ�false����ʲô��
 * 
 * ������������б�ķ�����
 * a.��һ�������ֽ�ɶ��������ÿ��������̯���еļ�������
 * b.����������������������ķ��飬��Щ������һ���Ǿ�̬��Ա��
 * ���Ƶ�����ֵĲ������п��Ա������Ǵ�����ĳ�����ص�ʵ�壬����ʹ�����ַ���
 * c.����Builderģʽ��@see net.brian.coding.designpatterns.builder.Client2
 *
 */
public class Client2 {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		// Point 1����֤���ص�������ģʽ��ȫ�ԡ�builder����һ��������һ�������Զ������ǿ��Լ������
		// build�������Լ�����ЩԼ����������������builder������������֮���ڶ������������builder��������
		// Υ�����κ�Լ��������Ӧ���׳�IllegalArgumentException
		// Point 2��ÿһ�δ��ζ��ǿɶ��ģ���������ֱ�Ӹ��ݷ�����calories��������ȥ�Ĳ���100��ָ�Ŀ�·��
		NutritionFactsConcreteBuilder2 builder = new NutritionFactsConcreteBuilder2.Builder2(240, 8).calories(100)
				.sodium(35).carbohydrate(27).build();
	}
}
