package net.brian.coding.designpatterns.composite;

import java.util.Iterator;

public abstract class Equipment {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Equipment(String name) {
		this.name = name;
	}

	public abstract double netPrice();

	public abstract double discountPrice();

	public boolean add(Equipment equipment) {
		return false;
	}

	public boolean remove(Equipment equipment) {
		return false;
	}

	public Iterator<Equipment> iter() {
		return null;
	}

}