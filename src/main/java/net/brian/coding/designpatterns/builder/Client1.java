package net.brian.coding.designpatterns.builder;

public class Client1 {
	public static void main(String[] args) {
		Builder1 aConcreteBuilder = new ConcreteBuilder1();
		Director1 aDirector = new Director1(aConcreteBuilder);
		aDirector.construct();
	}
}
