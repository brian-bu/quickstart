package net.brian.coding.designpatterns.abstractfactory.interfaceway;

public class ProductB2 implements IProductB {
	public ProductB2() {
		System.out.println("ProductB2 is already created......");
	}

	@Override
	public void selfIntroduceB() {
		System.out.println("ProductB2 created by ConcreteFactory2...");
	}
}
