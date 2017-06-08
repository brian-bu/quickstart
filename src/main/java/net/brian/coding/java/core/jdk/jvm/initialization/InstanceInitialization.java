package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * java��Ϊ�������ͱ������������ͱ����������������ͱ��������������ͨ��java����
 * 
 * ����java���������е����ñ���������Ҫ������ν�ĳ�ʼ������Ҫ���г�ʼ���������Ǹ����ñ��������õĶ���
 * �����������Ҫ���г�ʼ�������������������Ҫ��ʼ����������������ñ�������Ҫ��ʼ��������������Ҫ��ʼ��
 * 
 * ������Ϊ��
 * a.�������
 * �������⣬�����ڣ���static���εı�����ͬһ��JVM��ÿ����ֻ��һ��Class������������������ļ���ֻ�ܳ�ʼ��һ��
 * b.ʵ��������
 * �������⣬�����ڣ�����static���εı������������������ɳ�ʼ������
 * �������ֺ��������ͬ�������ʱ���ʼ���ʹ�����ʼ��
 * ���⣬����ÿ�ε��ù������������ʵ����ʱ�򶼻ᴴ���µ�ʵ��������ʵ���������Ա�������ڹ������л�ö����һ�γ�ʼ���Ļ���
 * @see net.brian.coding.jdk.jvm.initialization.Cat
 * ʵ�������������ʵ�����������౾�������ÿ�δ���һ�����ʵ���ͻ�Ϊʵ����������һ���ڴ�ռ䲢��ʵ��������ʼ��һ��
 * һ�仰������������ʼ���׶���ɳ�ʼ����ʵ�������ڶ����ʼ���׶���ɳ�ʼ��
 * c.�ֲ�������
 * �ֲ�������Ϊ���ࣺ�βΣ������ڵľֲ�������������ڵľֲ�����
 * �������ʵ���������кü��λ�����Գ�ʼ�������Ƕ��ھֲ����������ڶ����ʹ�ö��ڷ����ڲ�
 * ���Գ����βο��Ա��������÷����Σ����������������ʱ���ʼ��
 * ���оֲ��������Ǵ����ջ�ڴ棬�����ǻ������ͱ���������������
 * �����ڸ��Եķ���ջ�����������ͱ��������õĶ����ܴ洢�ڶ��ڴ���
 * 
 * û�о�����ʼ�����������ͱ�����ֻҪ�������������Ժͷ�����������ȫ����ʹ������ֻ����Ĭ��Ϊnull
 * ֻ�е��������ͱ�����ʼ����ϲſ��Է����������Ժͷ�����������׳�NPE
 *
 */
public class InstanceInitialization {
	// �������ע���������p.eyeNum��p2.eyeNum�ı��뾯�棺
	// The static field Cat.eyeNum should be accessed in a static way
	@SuppressWarnings("static-access")
	public static void main(String[] args) 
	{
		//��������ڸ��౾����ֻҪ�����ʼ����ɣ����򼴿�ʹ���������
		Cat.eyeNum = 2;
		//ͨ��Person�����eyeNum�����
		System.out.println(Cat.eyeNum);
		//������һ��Person����
		Cat p = new Cat();
		p.name = "NameA";
		p.age = 300;
		//ͨ��p����Person���eyeNum�����
		System.out.println(p.eyeNum);
		p.toString();
		//�����ڶ���Person����
		Cat p2 = new Cat();
		p2.name = "NameB";
		p2.age = 500;
		p2.toString();
		//ͨ��p2�޸�Person���eyeNum�����
		p2.eyeNum = 3;
		//�ֱ�ͨ��p��p2��Person����Person���eyeNum�����
		//ʵ��Ҳ�ǿ��Է���������ģ����Ǳ�������������棬����������������
		System.out.println(p.eyeNum);
		System.out.println(p2.eyeNum);
		System.out.println(Cat.eyeNum);
	}
}