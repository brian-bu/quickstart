package net.brian.coding.java.core.oop;

public class BaseAndDerived extends BaseClass {

	private String name = "dervied";

	public BaseAndDerived() {
		tellName();
		printName();
	}
	@Override
	public void tellName() {
		System.out.println("Dervied tell name: " + name);
	}
	@Override
	public void printName() {
		System.out.println("Dervied print name: " + name);
	}
	
	public void notOverriding() {
		System.out.println("BaseAndDerived -- notOverriding()");
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		BaseClass b = new BaseAndDerived();
		new Child("Mike");
	}
}

class BaseClass {

	@SuppressWarnings("unused")
	private String name = "base";

	public BaseClass() {
		tellName();
		printName();
	}

	public void tellName() {
		System.out.println("Never get executed!");
		// System.out.println("Base tell name: " + name);
	}

	public void printName() {
		System.out.println("Never get executed!");
		// System.out.println("Base print name: " + name);
	}
}

class People {
	String name;

	public People() {
		System.out.print(1);
	}

	public People(String name) {
		System.out.print(2);
		this.name = name;
	}
}

class Child extends People {
	People father;

	public Child(String name) {
		System.out.print(3);
		this.name = name;
		father = new People(name + ":F");
	}

	public Child() {
		System.out.print(4);
	}

}