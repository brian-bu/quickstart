package net.brian.coding.designpatterns.builder;

public class ConcreteBuilder1 implements Builder1 {

	@Override
	public void buildPartA() {
		System.out.println("Build the part A...");
	}

	@Override
	public void buildPartB() {
		System.out.println("Build the part B...");
	}

	@Override
	public void buildPartC() {
		System.out.println("Build the part C...");
	}

}
