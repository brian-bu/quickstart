package net.brian.coding.designpatterns.abstractfactory.interfaceway;

public class ProductB1 implements IProductB {
	public ProductB1() {
		System.out.println("ProductB1 is already created......");
	}

	@Override
	public void selfIntroduceB() {
		System.out.println("ProductB1 created by ConcreteFactory1...");
	}
}
