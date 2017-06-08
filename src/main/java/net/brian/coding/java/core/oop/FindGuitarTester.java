package net.brian.coding.java.core.oop;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FindGuitarTester {
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		inventory.addGuitar("", 0, "fender", "Stratocaster", "electric", "Alder", "Alder");
		Guitar whatDoYouLike = new Guitar("", 0, "fender", "Stratocaster", "electric", "Alder", "Alder");
		Guitar guitar = inventory.search(whatDoYouLike);
		if (null != guitar) {
			System.out.println("FindGuitarTester -- main:: We got the guitar you like.");
		} else {
			System.out.println("FindGuitarTester -- main:: We got nothing.");
		}
	}
}

class Inventory {
	private List<Guitar> guitars;

	public Inventory() {
		guitars = new LinkedList<Guitar>();
	}

	public void addGuitar(String serialNumber, double price, String builder, String model, String type, String backWood,
			String topWood) {
		Guitar guitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
		guitars.add(guitar);
	}

	public Guitar getGuitar(String serialNumber) {
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = i.next();
			if (guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;
	}

	public Guitar search(Guitar searchGuitar) {
		for (Iterator<Guitar> i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = i.next();
			if (!guitar.getModel().equals(searchGuitar.getModel())) {
				continue;
			}
			if (!guitar.getBuilder().equals(searchGuitar.getBuilder())) {
				continue;
			}
			if (!guitar.getBackWood().equals(searchGuitar.getBackWood())) {
				continue;
			}
			if (!guitar.getTopWood().equals(searchGuitar.getTopWood())) {
				continue;
			}
			if (!guitar.getType().equals(searchGuitar.getType())) {
				continue;
			}
			return guitar;
		}
		return null;
	}
}

class Guitar {
	private String serialNumber, builder, model, type, backWood, topWood;
	private double price;

	public Guitar(String serialNumber, double price, String builder, String model, String type, String backWood,
			String topWood) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.backWood = backWood;
		this.topWood = topWood;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getBuilder() {
		return builder;
	}

	public String getModel() {
		return model;
	}

	public String getType() {
		return type;
	}

	public String getBackWood() {
		return backWood;
	}

	public String getTopWood() {
		return topWood;
	}

	public double getPrice() {
		return price;
	}
}