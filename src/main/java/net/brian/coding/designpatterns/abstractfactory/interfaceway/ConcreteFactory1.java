package net.brian.coding.designpatterns.abstractfactory.interfaceway;

public class ConcreteFactory1 implements IFactory{
	@Override
	public IProductA createProductA() {
		return new ProductA1();
	}
	@Override
	public IProductB createProductB() {
		return new ProductB1();
	}
}
