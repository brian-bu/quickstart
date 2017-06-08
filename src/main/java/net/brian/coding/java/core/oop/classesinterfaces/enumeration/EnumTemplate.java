// Enum type with data and behavior - Pages 149-150
package net.brian.coding.java.core.oop.classesinterfaces.enumeration;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item30: Use enums instead of int constants
 * 
 * 枚举的本质是int值，之所以用枚举代替static final int是因为：
 * a.如果更改这些static final int就需要重新编译程序
 * b.int的变体是String枚举，但是这会使得程序依赖于字符串比较操作，耗性能
 * c.String枚举的字符串中包含拼写错误则在编译期间不会被检查到
 * 
 * 与此相对产生了枚举类型的好处：
 * a.枚举是真正的final，客户端即不能创建枚举类型的实例也不能对它进行扩展
 * b.枚举类型是实例受控的，它是单例的泛型化。单例本质上是单元素的枚举
 * c.枚举提供了编译时的类型安全
 *
 */
public class EnumTemplate {
	public static void main(String[] args) {
		double earthWeight = Double.parseDouble("88.1232");
		double mass = earthWeight / Planet.EARTH.surfaceGravity();
		for(Planet p : Planet.values())
			System.out.println("Weight on planet " + p + " is:: " + p.surfaceWeight(mass));
	}
}

enum Planet {
	// 为了将数据和枚举常量关联起来，得声明实例域并编写一个带有数据并将数据保存在域中的构造器
	MERCURY(3.302e+23, 2.439e6), VENUS(4.869e+24, 6.052e6), EARTH(5.975e+24,
			6.378e6), MARS(6.419e+23, 3.393e6), JUPITER(1.899e+27, 7.149e7), SATURN(
			5.685e+26, 6.027e7), URANUS(8.683e+25, 2.556e7), NEPTUNE(1.024e+26,
			2.477e7);
	// 为了将数据和域关联起来，可以考虑引入一些域和方法到枚举类型中
	// 最好将枚举的域也私有化并提供公有的访问方法，这一点和普通的类对于实例域的建议是一样的
	private final double mass; // In kilograms
	private final double radius; // In meters
	private final double surfaceGravity; // In m / s^2

	// Universal gravitational constant in m^3 / kg s^2
	private static final double G = 6.67300E-11;

	// Constructor
	// 为了将数据和枚举常量关联起来，得声明实例域并编写一个带有数据并将数据保存在域中的构造器
	Planet(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		surfaceGravity = G * mass / (radius * radius);
	}

	public double mass() {
		return mass;
	}

	public double radius() {
		return radius;
	}

	public double surfaceGravity() {
		return surfaceGravity;
	}

	public double surfaceWeight(double mass) {
		return mass * surfaceGravity; // F = ma
	}
}
