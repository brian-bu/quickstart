package net.brian.coding.designpatterns.abstractfactory;

public class ProductA1 implements IProductA {
	public ProductA1() {
		System.out.println("ProductA1 is already created......");
	}

	@Override
	public void selfIntroduceA() {
		System.out.println("ProductA1 created by ConcreteFactory1...");
	}
}
