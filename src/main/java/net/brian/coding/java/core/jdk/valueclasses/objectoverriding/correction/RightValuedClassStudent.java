package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item08: Obey the general contract when overriding equals
 * 
 * item09: Always override hashCode when you override equals
 * 
 * item10: Always override toString
 * 
 * item12: Consider implementing Comparable
 * 
 * ������Ƕ�Student������һЩ����ȷ�ķ����ľ���
 * ��Ҫ�����ĵط�����ע�ͱ�����
 * 
 * ���⣬����hashCode��equals��clone��toString��setter��getter��Щͨ�õķ���
 * eclipse�����п�ݼ�ʵ�ֵģ����Ҽ���sourse����Ҳ�ǿ����ҵ���
 * 
 * @see net.brian.coding.jdk.valueclasses.objectoverriding.wrongway.WrongValuedClassStudent
 *
 */
public class RightValuedClassStudent implements Comparable<RightValuedClassStudent> {

	private String name;
	private double height;
	private boolean ifGraduated;
	private char sex;// "F" or "M"
	private float gpa;
	private long timeStamp;
	
	// ��Ȼ����equals��ʱ��涨�˶ԱȵĹ�����ô����Ҫ����һ��������
	// �������equals�Ƚ�����ʵ����ʱ��������ʵ������Ĭ�Ϲ��������������
	// ������equals����ʲô�ж������������Ƿ�����أ�
	public RightValuedClassStudent(String name, char sex) {
		this.name = name;
		this.sex = sex;
	}

	@Override
	public boolean equals(Object obj) {
		// ����Ҫȷ��equals�ĸ��Ƿ������ԭ��֮һ���ǿ���
		if(obj == null) return false;
		// �������һ�д�ӡ��Ϣ��Ϊ����֤ArrayList��contains�����е��õ�equals����
		// �Ƿ����е�ֵ�า�Ǻ��equals����������Object��equals
		System.out.println("Entering RightValuedClassStudent.equals...");
		if (this == obj)
			return true;
		if (!(obj instanceof RightValuedClassStudent))
			return false;
		RightValuedClassStudent student = (RightValuedClassStudent) obj;
		// ���ڸ����е�ÿ�����ؼ����򣬼������е����Ƿ���ö����ж�Ӧ������ƥ�䡣
		// ����Ĺؼ�������Ϊ�����ǹ���������Ҫ����
		// ��Щ����������Ƿ���ͬ��ֱ�Ӿ����˹��������Studentʵ���Ƿ��߼�״̬��һ��
		return this.name == student.name && this.sex == student.sex;
		// ������Щ��������Ҳ���ǲ����ڶ����߼�״̬����û��Ҫ�Ƚ�����
		// ��ν�Ķ����߼�״ָ̬������Ҫ�������������״̬��Ҳ���Ǳ����й����������б��и����Ĳ���
		// this.gpa == student.gpa && this.timeStamp == student.timeStamp&& this.height == student.height && this.ifGraduated == student.ifGraduated 
	}
	
	private volatile int hashCode;
	/**
	 * ���hashCode��Ϊ��ݷ�ʽ��ȷ����ȣ���ôֻ��һ��������Ӧ�ù���:��ȵĶ���Ӧ�þ�����ͬ�Ĺ�ϣ��
	 * ��Ҳ��Ϊʲô���������д��equals���������Ǳ��봴��һ����֮ƥ���hashCodeʵ�ֵ�ԭ�򣬷�����ȵĶ����ǿ��ܲ�������ͬ�Ĺ�ϣ��
	 * �Ӷ����¸����޷�������л���ɢ�еļ���һ��������������HashMap��HashTable��HashSet����ļ��ϲ�����ͨ��equals�ж�Ԫ����ȵ�
	 * ����ͨ����ݷ�ʽ����ݷ�ʽ�Ƚϼ�ͨ���ȽϹ�ϣֵ�������Խ�һ��ʵ����һ������ֵ�����档
	 * ��ϣ����ͬ��ʵ����һ����ȣ�����ȵ�ʵ��һ����������ͬ�Ĺ�ϣֵ��
	 * ����ȵ�Ԫ������ͬ�Ĺ�ϣ��,����������ͬһ��Ͱ�ϲ���������һ�����������ǵ�hashCode����ʼ�շ�����ͬ��intֵ
	 * ��ô���е�Ԫ�ض�����һ��Ͱ�Ͼ��γ���һ������
	 * ������Щ���������Դ����Ż������磺
	 * HashMap��һ���Ż�������ʵ������contains����ʱ�����Խ���ÿ�����������ɢ���뻺�����������ɢ���벻ƥ�䣬Ҳ���ؼ������ĵ�ͬ��
	 * 
	 * ����hashCode��Ҫ�Ƕ�����������ת����int���͵Ĺ��򣬶�������������ų����ⲻ�ü���
	 * ��ò�Ҫʹ�ÿɱ��ֶμ����ϣ��
	 * ���⣬���һ�����ǲ��ɱ�ģ����Ҽ���ɢ����Ŀ���Ҳ�Ƚϴ�Ļ���Ӧ�ÿ��ǰ�ɢ���뻺���ڶ����ڲ�
	 * private volatile int hashCode;  // (See Item 71)
	 * 
	 * ����8���������ͳ���boolean�����ȫ����ֱ��ת����
	 * byte��char��short����int���ͣ������(int)f
	 * float���ͣ������Float.floatToIntBits(f) 
	 * long���ͣ������(int)(f ^ (f >>> 32))
	 * doubleͨ��Double.doubleToLongBits(height)��ת��longȻ����תint 
	 * ������boolean�����(f ? 1 : 0)
	 * 
	 * ���ڶ������õ�ת���� ���øö����hashCode������f.hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		// ��ʼ��
		int result = hashCode;
		/**
		 *  �����൱�ڽ�hashCode�������������ڶ����ٷ��ʵ�ʱ��result��Ϊ0�˾Ϳ������ϴμ���ó���hashCode��
		 *  ������ӳٳ�ʼ����@see net.brian.coding.java.core.jdk.jvm.initialization.LazyInitialization
		 */
		if (result == 0) {
			// String����(��������)
			result = this.name.hashCode() + result;

			// byte��short��char����
			result = result + (int) sex;
			
			// ��ֵ��Ĺؼ���μ�hashCode���㣬�����򲻲μӣ������������㶼ע�͵���

			// float����
			// result = Float.floatToIntBits(gpa) + result;

			// boolean����
			// result = result + (ifGraduated ? 1 : 0);
			
			// int����
			// result = this.id + result;

			// long���ͺ�double����һ��ʾ��
			// long heightBits = Double.doubleToLongBits(height);
			// int heightTemp = (int) (heightBits ^ (heightBits >>> 32));
			// result = heightTemp + result;

			hashCode = result;
		}
		return hashCode;
	}
	
	/**
	 * ʼ��Ҫ����toString����
	 */
	@Override
	public String toString() {
		String output = "The name is:: " + this.name + "\nThe sex is:: " + this.sex;
		return output;
	}

	/**
	 * ���е�compareTo����������Է��ԡ��Գ��Ժʹ����ԣ���һ���equals��ʵ�ֹ�����һ����
	 * �����������е�compareTo��������е�ͬ�Բ��ԣ�����((x.compareTo(y) == 0) == (x.equals(y))
	 * ��ͬ�Բ��Խ��Ϊtrue����ô����equals��ϵһ�£�����һ��
	 * ��һ�µ����Ծɿ�����������������ĳЩ���򼯺ϰ����˸���Ԫ�ؾ��޷�������ؽӿڣ�Collection��Map��Set����ͨ��Լ��
	 * ����BigDecimal��û�е�ͬ�Բ��ԣ���˶�����������BigDecimal("1.00")��BigDecimal("1.0")
	 * ����ŵ�HashSet������ʵ��ͨ��equals�Ƚ��ǲ���ȵģ����Ի��������Ԫ��
	 * ����ŵ�TreeSet�У���ֻ�ܷŽ�һ��Ԫ�أ���Ϊ����ͨ��compareTo�ıȽ�����ȵ�
	 * @see net.brian.coding.java.core.jdk.valueclasses.MathematicsDemo.bigDecimalForSortedeCollections()
	 * @see java.math.BigDecimal.compareTo(BigDecimal)
	 */
	@Override
	public int compareTo(RightValuedClassStudent o) {
		// ���������������ؼ���name��sex����˰���ʲô����˳�����Ƚ���Щ���Ƿǳ��ؼ���
		// �������PhoneNumber������ֵ�൱Ȼ���ȱȽ�area code������+86���й�
		// Ȼ���ǱȽ����ű���010�Ǳ��������ȽϷֻ��ŵȺ���
		// ���ڱ��������ȱȽ����ֺ�Ƚ��Ա�Ůʿ���ȣ�Ů������ǰ�棬��F>M
		if (name.compareTo(o.name) == -1)
			return -1;
		if (name.compareTo(o.name) == 1)
			return 1;
		if (sex < o.sex)
			return -1;
		if (sex > o.sex)
			return 1;
		return 0; // All fields are equal
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isIfGraduated() {
		return ifGraduated;
	}

	public void setIfGraduated(boolean ifGraduated) {
		this.ifGraduated = ifGraduated;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
