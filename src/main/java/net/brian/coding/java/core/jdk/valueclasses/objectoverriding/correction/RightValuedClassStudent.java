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
 * 这个类是对Student这个类的一些不正确的方法的纠正
 * 需要纠正的地方都用注释标明了
 * 
 * 另外，对于hashCode、equals、clone、toString、setter、getter这些通用的方法
 * eclipse都是有快捷键实现的，从右键的sourse里面也是可以找到的
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
	
	// 既然覆盖equals的时候规定了对比的规则，那么至少要重载一个构造器
	// 否则调用equals比较两个实例的时候发现两个实例都用默认构造器构造出来的
	// 那你让equals根据什么判断这两个对象是否相等呢？
	public RightValuedClassStudent(String name, char sex) {
		this.name = name;
		this.sex = sex;
	}

	@Override
	public boolean equals(Object obj) {
		// 这里要确保equals的覆盖符合五大原则之一：非空性
		if(obj == null) return false;
		// 这里添加一行打印信息，为了验证ArrayList的contains方法中调用的equals方法
		// 是泛型中的值类覆盖后的equals方法而不是Object的equals
		System.out.println("Entering RightValuedClassStudent.equals...");
		if (this == obj)
			return true;
		if (!(obj instanceof RightValuedClassStudent))
			return false;
		RightValuedClassStudent student = (RightValuedClassStudent) obj;
		// 对于该类中的每个“关键”域，检查参数中的域是否与该对象中对应的域相匹配。
		// 这里的关键域是因为它们是构造器的主要参数
		// 这些参数传入的是否相同将直接决定了构造出来的Student实例是否“逻辑状态”一致
		return this.name == student.name && this.sex == student.sex;
		// 下面这些是冗余域，也就是不属于对象逻辑状态的域，没必要比较它们
		// 所谓的对象逻辑状态指的是主要区分两个对象的状态，也就是本例中构造器参数列表中给出的参数
		// this.gpa == student.gpa && this.timeStamp == student.timeStamp&& this.height == student.height && this.ifGraduated == student.ifGraduated 
	}
	
	private volatile int hashCode;
	/**
	 * 如果hashCode作为快捷方式来确定相等，那么只有一件事我们应该关心:相等的对象应该具有相同的哈希码
	 * 这也是为什么如果我们重写了equals方法后，我们必须创建一个与之匹配的hashCode实现的原因，否则相等的对象是可能不会有相同的哈希码
	 * 从而导致该类无法结合所有基于散列的集合一起正常运作。如HashMap、HashTable、HashSet这类的集合并不是通过equals判断元素相等的
	 * 而是通过快捷方式，快捷方式比较即通过比较哈希值，它可以将一个实例用一个整数值来代替。
	 * 哈希码相同的实例不一定相等，但相等的实例一定具有有相同的哈希值。
	 * 不相等的元素有相同的哈希码,他们最终在同一个桶上并且捆绑在一起，因此如果覆盖的hashCode方法始终返回相同的int值
	 * 那么所有的元素都绑在一个桶上就形成了一个链表
	 * 此外有些集合甚至对此有优化，比如：
	 * HashMap有一项优化，即当实例进行contains操作时，可以将与每个项相关联的散列码缓存起来，如果散列码不匹配，也不必检验对象的等同性
	 * 
	 * 覆盖hashCode主要是定义数据类型转换成int类型的规则，对于冗余域可以排除在外不用计算
	 * 最好不要使用可变字段计算哈希码
	 * 此外，如果一个类是不可变的，并且计算散列码的开销也比较大的话，应该考虑把散列码缓存在对象内部
	 * private volatile int hashCode;  // (See Item 71)
	 * 
	 * 对于8中数据类型除了boolean其余的全可以直接转换：
	 * byte、char、short或者int类型，则计算(int)f
	 * float类型，则计算Float.floatToIntBits(f) 
	 * long类型，则计算(int)(f ^ (f >>> 32))
	 * double通过Double.doubleToLongBits(height)先转成long然后再转int 
	 * 最后对于boolean则计算(f ? 1 : 0)
	 * 
	 * 对于对象引用的转换： 调用该对象的hashCode方法，f.hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		// 初始化
		int result = hashCode;
		/**
		 *  这里相当于将hashCode缓存了起来，第二次再访问的时候result不为0了就可以用上次计算得出的hashCode了
		 *  这就是延迟初始化：@see net.brian.coding.java.core.jdk.jvm.initialization.LazyInitialization
		 */
		if (result == 0) {
			// String类型(引用类型)
			result = this.name.hashCode() + result;

			// byte、short、char类型
			result = result + (int) sex;
			
			// 仅值类的关键域参加hashCode运算，冗余域不参加，所以以下运算都注释掉了

			// float类型
			// result = Float.floatToIntBits(gpa) + result;

			// boolean类型
			// result = result + (ifGraduated ? 1 : 0);
			
			// int类型
			// result = this.id + result;

			// long类型和double类型一起示范
			// long heightBits = Double.doubleToLongBits(height);
			// int heightTemp = (int) (heightBits ^ (heightBits >>> 32));
			// result = heightTemp + result;

			hashCode = result;
		}
		return hashCode;
	}
	
	/**
	 * 始终要覆盖toString方法
	 */
	@Override
	public String toString() {
		String output = "The name is:: " + this.name + "\nThe sex is:: " + this.sex;
		return output;
	}

	/**
	 * 所有的compareTo都必须遵从自反性、对称性和传递性，这一点和equals的实现规则是一样的
	 * 但并不是所有的compareTo都必须进行等同性测试，即：((x.compareTo(y) == 0) == (x.equals(y))
	 * 等同性测试结果为true，那么称与equals关系一致，否则不一致
	 * 不一致的类仍旧可以正常工作，但是某些有序集合包含了该类元素就无法遵守相关接口（Collection、Map、Set）的通用约定
	 * 比如BigDecimal就没有等同性测试，因此对于两个对象BigDecimal("1.00")和BigDecimal("1.0")
	 * 如果放到HashSet中两个实例通过equals比较是不相等的，所以会添加两个元素
	 * 如果放到TreeSet中，就只能放进一个元素，因为二者通过compareTo的比较是相等的
	 * @see net.brian.coding.java.core.jdk.valueclasses.MathematicsDemo.bigDecimalForSortedeCollections()
	 * @see java.math.BigDecimal.compareTo(BigDecimal)
	 */
	@Override
	public int compareTo(RightValuedClassStudent o) {
		// 本例中类有两个关键域name和sex，因此按照什么样的顺序来比较这些域是非常关键的
		// 比如对于PhoneNumber这样的值类当然是先比较area code，比如+86是中国
		// 然后是比较区号比如010是北京，最后比较分机号等号码
		// 对于本例我们先比较名字后比较性别，女士优先，女生排在前面，即F>M
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
