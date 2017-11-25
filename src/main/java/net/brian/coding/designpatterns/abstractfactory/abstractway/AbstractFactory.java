package net.brian.coding.designpatterns.abstractfactory.abstractway;

import net.brian.coding.designpatterns.abstractfactory.interfaceway.IProductA;
import net.brian.coding.designpatterns.abstractfactory.interfaceway.IProductB;

public abstract class AbstractFactory {
	abstract IProductA createProductA();

	abstract IProductB createProductB();

	abstract IProductB createProductC();
}

class ConcreteFactory extends AbstractFactory{

	@Override
	IProductA createProductA() {
		return null;
	}

	@Override
	IProductB createProductB() {
		return null;
	}

	@Override
	IProductB createProductC() {
		return null;
	}
	
}