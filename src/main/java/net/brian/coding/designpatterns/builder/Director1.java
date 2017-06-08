package net.brian.coding.designpatterns.builder;

public class Director1 {
	private Builder1 builder;

	public Director1(Builder1 builder) {
		this.builder = builder;
	}

	public void construct() {
		System.out.println("Director ask the Builder to build...");
		builder.buildPartA();
		builder.buildPartB();
		builder.buildPartC();
	}

}
