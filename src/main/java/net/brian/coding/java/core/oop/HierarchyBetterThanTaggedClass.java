package net.brian.coding.java.core.oop;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item20: Prefer class hierarchies to tagged classes
 * 
 * FigureByTaggedClass��һ�����͵ı�ǩ�࣬�������ȱ�㣺
 * �����ö�١���ǩ��������䣬�ɶ��ʵ�����߰���ļ���һ�����У��ƻ��ɶ��ԣ�ʹ���߼�����
 * ��������final�ģ���������������������
 * ���������벻���������������ñ�ǩ�򣬲���ʼ����ȷ�������������ʼ���������ͻ�������ʱʧ��
 * switch-case��������©��֧���³������
 * 
 * �ܽ���˵���ǣ���ǩ������߳������׳���Ч�ʵ���
 * ���ڱ�ǩ�౾�������εļ򵥷�Ч����������oop���������Զ���һ�����Σ��������α�ǩ���ȫ��ȱ��
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
	//TODO: ��AccountSelectionPopup�����ƵĸĽ�
}