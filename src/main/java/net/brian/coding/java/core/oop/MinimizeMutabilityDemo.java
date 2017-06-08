package net.brian.coding.java.core.oop;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item15: Minimize mutability
 * 
 * Ϊ��ʹ�಻�ɱ䣬��Ҫ��ѭ������ԭ��
 * a.��Ҫ�ṩ�κλ��޸Ķ���״̬�ķ���������setter
 * b.��֤�಻�ᱻ��չ��
 * @see net.brian.coding.java.core.oop.AvoidingInheritance
 * c.ʹ���е�����final��
 * d.ʹ���е�����˽�е�
 * e.ȷ�����κοɱ�����Ļ�����ʣ������һ��Ҫ����ָ��ɱ������򣬱���ȷ���ͻ����޷���ò�������Щ����
 * ���ڹ��������ʷ�����readObject��ʹ�ñ����Կ���
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.DeepCopyDemo
 * 
 * ע�⣺���ɱ����Ҳ����ȱ��ģ�����Ψһȱ����Ƕ���ÿһ����ͬ��ֵ����Ҫһ�������Ķ���
 * ִ��һ���ಽ������ÿ�����趼�����һ���¶��󣬱���+���Ӷ���ַ��������ֻ����һ�����������ȫ����
 * �����ͻ�����ܴ���������⣬Ϊ�˿��ṩ���еĿɱ������࣬����StringBuilder֮��String
 * ������+�����ַ������������Զ�����StringBuilder��append���������Ż�
 * 
 * �������һЩ��������ļ��������final�򽫽����������������Լ���¼���Ŀ���
 * ��Ϊ����Ĳ��ɱ��ԣ�������Щ�������ٴα�ִ�п��Բ�����ͬ�Ľ������˲���Ҫ�ظ�ִ�� 
 * ����String��hashCode�ļ�������Ƚ����󻺴�������������ӳٳ�ʼ��
 * 
 * ���⻹Ӧ�ù��ڱ����ڳ�����ע�����������·�ʾ�������ע�͡�
 * 
 */
// Point b����֤�಻�ᱻ��չ
public final class MinimizeMutabilityDemo {

	// Point c and d: ʹ���е�����final�ģ�ʹ���е�����˽�е�
	private final double re;
	private final double im;

	private MinimizeMutabilityDemo(double re, double im) {
		this.re = re;
		this.im = im;
	}

	// ���沢��������ʵ���������ڴ�ռ�ú��������ճɱ�
	public static MinimizeMutabilityDemo valueOf(double re, double im) {
		// Notes: Normal final-like class style: To return a new reference as
		// result instead of change the old one.
		return new MinimizeMutabilityDemo(re, im);
	}

	// ��̬�����ĵڶ����ô������и��Ե�����
	public static MinimizeMutabilityDemo valueOfPolar(double r, double theta) {
		return new MinimizeMutabilityDemo(r * Math.cos(theta), r * Math.sin(theta));
	}

	// ���ɱ����һ�����̰߳�ȫ�ģ���˹������̶�����������Ա�֤�̰߳�ȫ����������������ʵ��
	// �������Ƶ���õ���ֵ�ṩpublic static final�����ĳ�����Ҳ���Ǳ����ڳ���
	// ���ڱ����ڳ�����ʹ����Ҫע������⣺
	// ������̬���ɱ䣨public static final ������Ҳ����������˵�ı����ڳ���������� public ��ѡ��
	 // ʵ������Щ�����ڱ���ʱ�ᱻ�滻������Ϊ������֪����Щ������ֵ������֪����Щ����������ʱ���ܸı�
	// ���ַ�ʽ���ڵ�һ����������ʹ����һ���ڲ��Ļ���������еĹ��б���ʱ�������������ֵ���汻�����˸ı���
	// ������Ŀͻ�����Ȼ��ʹ���ϵ�ֵ���������Ѿ�������һ���µ�jar
	// Ϊ�˱�����������������ڸ������� JAR �ļ�ʱ��ȷ�����±�����ĳ���
	public static final MinimizeMutabilityDemo ZERO = new MinimizeMutabilityDemo(0, 0);
	public static final MinimizeMutabilityDemo ONE = new MinimizeMutabilityDemo(1, 0);
	public static final MinimizeMutabilityDemo I = new MinimizeMutabilityDemo(0, 1);

	// Point a.��Ҫ�ṩ�κλ��޸Ķ���״̬�ķ���������setter
	public double realPart() {
		return re;
	}

	public double imaginaryPart() {
		return im;
	}

	/**
	 * ���������ĸ���ʾ�Ӽ��˳��ķ�����һ�ֵ��͵ĺ���ʽ������
	 * �����������µ�MinimizeMutabilityDemoʵ�����������޸����ʵ��
	 * ��������ɱ���඼ʹ������ģʽ������String��replace��replaceAll�ȷ���
	 * ���ַ��������˲��ɱ���
	 * @see 
	 * ��Ӧ���ǹ���ʽ������ʽ��������Ҳ��void������������ֵ��ֱ�ӶԾֲ�������ȫ�ֱ��������߼�����
	 * ���������ᵼ�·����Ĳ��������״̬�����ı�
	 * @param c
	 * @return
	 */
	public MinimizeMutabilityDemo add(MinimizeMutabilityDemo c) {
		return new MinimizeMutabilityDemo(re + c.re, im + c.im);
	}

	public MinimizeMutabilityDemo subtract(MinimizeMutabilityDemo c) {
		return new MinimizeMutabilityDemo(re - c.re, im - c.im);
	}

	public MinimizeMutabilityDemo multiply(MinimizeMutabilityDemo c) {
		return new MinimizeMutabilityDemo(re * c.re - im * c.im, re * c.im + im * c.re);
	}

	public MinimizeMutabilityDemo divide(MinimizeMutabilityDemo c) {
		double tmp = c.re * c.re + c.im * c.im;
		return new MinimizeMutabilityDemo((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof MinimizeMutabilityDemo))
			return false;
		MinimizeMutabilityDemo c = (MinimizeMutabilityDemo) o;

		// See page 43 to find out why we use compare instead of ==
		return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
	}

	@Override
	public int hashCode() {
		int result = 17 + hashDouble(re);
		result = 31 * result + hashDouble(im);
		return result;
	}

	private int hashDouble(double val) {
		long longBits = Double.doubleToLongBits(re);
		return (int) (longBits ^ (longBits >>> 32));
	}

	@Override
	public String toString() {
		return "(" + re + " + " + im + "i)";
	}

}