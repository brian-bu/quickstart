// Enum type with data and behavior - Pages 149-150
package net.brian.coding.java.core.oop.classesinterfaces.enumeration;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item30: Use enums instead of int constants
 * 
 * ö�ٵı�����intֵ��֮������ö�ٴ���static final int����Ϊ��
 * a.���������Щstatic final int����Ҫ���±������
 * b.int�ı�����Stringö�٣��������ʹ�ó����������ַ����Ƚϲ�����������
 * c.Stringö�ٵ��ַ����а���ƴд�������ڱ����ڼ䲻�ᱻ��鵽
 * 
 * �����Բ�����ö�����͵ĺô���
 * a.ö����������final���ͻ��˼����ܴ���ö�����͵�ʵ��Ҳ���ܶ���������չ
 * b.ö��������ʵ���ܿصģ����ǵ����ķ��ͻ��������������ǵ�Ԫ�ص�ö��
 * c.ö���ṩ�˱���ʱ�����Ͱ�ȫ
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
	// Ϊ�˽����ݺ�ö�ٳ�������������������ʵ���򲢱�дһ���������ݲ������ݱ��������еĹ�����
	MERCURY(3.302e+23, 2.439e6), VENUS(4.869e+24, 6.052e6), EARTH(5.975e+24,
			6.378e6), MARS(6.419e+23, 3.393e6), JUPITER(1.899e+27, 7.149e7), SATURN(
			5.685e+26, 6.027e7), URANUS(8.683e+25, 2.556e7), NEPTUNE(1.024e+26,
			2.477e7);
	// Ϊ�˽����ݺ���������������Կ�������һЩ��ͷ�����ö��������
	// ��ý�ö�ٵ���Ҳ˽�л����ṩ���еķ��ʷ�������һ�����ͨ�������ʵ����Ľ�����һ����
	private final double mass; // In kilograms
	private final double radius; // In meters
	private final double surfaceGravity; // In m / s^2

	// Universal gravitational constant in m^3 / kg s^2
	private static final double G = 6.67300E-11;

	// Constructor
	// Ϊ�˽����ݺ�ö�ٳ�������������������ʵ���򲢱�дһ���������ݲ������ݱ��������еĹ�����
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
