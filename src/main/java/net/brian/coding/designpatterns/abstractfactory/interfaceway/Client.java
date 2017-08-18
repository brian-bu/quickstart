package net.brian.coding.designpatterns.abstractfactory.interfaceway;

public class Client {
	public static void main(String[] args) {
		IFactory factory1 = new ConcreteFactory1();
		IProductA productA1 = factory1.createProductA();
		productA1.selfIntroduceA();
		IProductB productB1 = factory1.createProductB();
		productB1.selfIntroduceB();
		IFactory factory2 = new ConcreteFactory2();
		IProductA productA2 = factory2.createProductA();
		productA2.selfIntroduceA();
		IProductB productB2 = factory2.createProductB();
		productB2.selfIntroduceB();
	}
}
