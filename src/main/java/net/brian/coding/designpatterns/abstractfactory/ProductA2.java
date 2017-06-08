package net.brian.coding.designpatterns.abstractfactory;

public class ProductA2 implements IProductA {
	public ProductA2() {
		System.out.println("ProductA2 is already created......");
	}

	@Override
	public void selfIntroduceA() {
		System.out.println("ProductA2 created by ConcreteFactory2...");
	}
}
