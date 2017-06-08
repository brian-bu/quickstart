package net.brian.coding.java.core.jdk.valueclasses.objectoverriding.wrongway;
// 这里为了示范面向对象语言中关于等价关系的特性，所以需要一个正确覆盖equals方法的Student
// 所以没有引用当前package就有的Student类，但是这个Student1的关于覆盖equals的方法仍是错的
// 所以放到wrongway的package下
import net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent;

/**
 * 
 * 这个类通过对equals的覆盖和增加age信息，进而暴露了面向对象语言中关于等价关系的一个基本问题：
 * 我们无法在扩展可实例化的类的同时，既增加新的值组件同时又保留equals的约定
 *
 * @see net.brian.coding.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent1
 */
public class WrongValuedClassStudent1 extends RightValuedClassStudent{
	// 为了根据年龄段区分是高年级还是低年级的学生，我们加入一个年龄信息
	// Point 1：此时如果完全不覆盖equals方法而直接用Student类继承过来的
	// 则在比较同名同性别但是年龄不一样的两个对象的过程中则只比较了名字和性别，年龄就被忽略掉了
	private int age;

	// Point 2：如果考虑覆盖equals，如果你像下面这样覆盖就违反了覆盖原则的对称性
	// 也就是a.equals(b)返回true，那么b.equals(a)也应该返回true
	// @Override
	// public boolean equals(Object obj) {
	// // 对于非空性、this等的判断以及对name、sex的比较都在super.equals中定义好了，不需重复定义
	// if(!(obj instanceof RightValuedClassStudent1)) return false;
	// return super.equals(obj) && ((RightValuedClassStudent1)obj).age == age;
	// }
	// Point 3：如果考虑覆盖equals，如果你像下面这样覆盖就违反了覆盖原则的传递性
	// 传递性就是a对象等于b对象，b对象又等于c对象，则a对象等于c对象
	// 当我们谈到传递性的时候，主要注意的是：子类增加的信息会影响到equals的比较结果
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RightValuedClassStudent)) return false;
		// 如果obj既是Student的实例又不是Student1的实例，那就直接调用Student里定义的equals
		if(!(obj instanceof WrongValuedClassStudent1)) return obj.equals(this);
		// 如果是一个Student1的实例，就调用Student的equals比较name和sex同时比较子类的逻辑状态age
		return super.equals(obj) && ((WrongValuedClassStudent1)obj).age == age;
	}
	// Point 4：除了以上的对称性和传递性，对equals方法的覆盖还必须满足如下特性：
	// 一致性：如果两个对象相等，就必须始终相等，不要让equals方法依赖一些不可靠的资源，比如URL、io
	// 非空性：所有的equals方法，在覆盖的时候都应该在最开头判断一下：如果传进来的参数是null，直接返回false
	// 自反性：很难想象一个对象不等于它自己。
	
	
	public WrongValuedClassStudent1(String name, char sex, int age) {
		super(name, sex);
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}