package net.brian.coding.java.core.oop;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item20: Prefer class hierarchies to tagged classes
 * 
 * FigureByTaggedClass是一个典型的标签类，它有许多缺点：
 * 充斥着枚举、标签域、条件语句，由多个实现乱七八糟的挤在一个类中，破坏可读性，使得逻辑混乱
 * 域不能做成final的，进而产生更多的样板代码
 * 构造器必须不借助编译器来设置标签域，并初始化正确的数据域，如果初始化错误的域就会在运行时失败
 * switch-case语句可能遗漏分支导致程序出错
 * 
 * 总结来说就是：标签类过于冗长，容易出错，效率低下
 * 由于标签类本质是类层次的简单仿效，所以利用oop的特征可以定义一个类层次，进而屏蔽标签类的全部缺点
 * 
 */
class FigureByTaggedClass {
	enum Shape {
		RECTANGLE, CIRCLE
	};

	// Tag field - the shape of this figure
	final Shape shape;

	// These fields are used only if shape is RECTANGLE
	double length;
	double width;

	// This field is used only if shape is CIRCLE
	double radius;

	// Constructor for circle
	FigureByTaggedClass(double radius) {
		shape = Shape.CIRCLE;
		this.radius = radius;
	}

	// Constructor for rectangle
	FigureByTaggedClass(double length, double width) {
		shape = Shape.RECTANGLE;
		this.length = length;
		this.width = width;
	}

	double area() {
		switch (shape) {
		case RECTANGLE:
			return length * width;
		case CIRCLE:
			return Math.PI * (radius * radius);
		default:
			throw new AssertionError();
		}
	}
}

abstract class FigureByHierarchy {
	abstract double area();
}

class Circle extends FigureByHierarchy {
	final double radius;

	Circle(double radius) {
		this.radius = radius;
	}

	double area() {
		return Math.PI * (radius * radius);
	}
}

class Rectangle extends FigureByHierarchy {
	final double length;
	final double width;

	Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	double area() {
		return length * width;
	}
}

public class HierarchyBetterThanTaggedClass {
	//TODO: 对AccountSelectionPopup做类似的改进
}