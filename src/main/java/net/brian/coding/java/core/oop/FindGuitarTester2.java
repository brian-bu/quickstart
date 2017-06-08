package net.brian.coding.java.core.oop;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FindGuitarTester2 {
	public static void main(String[] args) {
		Inventory1 inventory = new Inventory1();
		inventory.addGuitar("", 0, Builder3.FENDER, "Stratocaster", Type3.ELECTRIC, Wood3.ALDER, Wood3.ALDER);
		Guitar1 whatDoYouLike = new Guitar1("", 0, Builder3.FENDER, "Stratocaster", Type3.ELECTRIC, Wood3.ALDER,
				Wood3.ALDER);
		List<Guitar1> guitar = inventory.search(whatDoYouLike);
		if (null != guitar && guitar.size() > 0) {
			System.out.println("FindGuitar1Tester -- main:: We got the guitar you like, we got " + guitar.size());
		} else {
			System.out.println("FindGuitar1Tester -- main:: We got nothing.");
		}
	}
}

class Inventory1 {
	private List<Guitar1> guitars;

	public Inventory1() {
		guitars = new LinkedList<Guitar1>();
	}

	public void addGuitar(String serialNumber, double price, Builder3 fender, String model, Type3 electric, Wood3 alder,
			Wood3 alder2) {
		Guitar1 guitar = new Guitar1(serialNumber, price, fender, model, electric, alder, alder2);
		guitars.add(guitar);
	}

	public Guitar1 getGuitar(String serialNumber) {
		for (Iterator<Guitar1> i = guitars.iterator(); i.hasNext();) {
			Guitar1 guitar = (Guitar1) i.next();
			if (guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;
	}

	public List<Guitar1> search(Guitar1 searchGuitar) {
		List<Guitar1> matchingGuitars = new LinkedList<Guitar1>();
		for (Iterator<Guitar1> i = matchingGuitars.iterator(); i.hasNext();) {
			Guitar1 guitar = i.next();
			if (!guitar.getModel().equals(searchGuitar.getModel())) {
				continue;
			}
			if (searchGuitar.getBackWood() != guitar.getBackWood()) {
				continue;
			}
			if (searchGuitar.getBuilder() != guitar.getBuilder()) {
				continue;
			}
			if (searchGuitar.getTopWood() != guitar.getTopWood()) {
				continue;
			}
			if (searchGuitar.getType() != guitar.getType()) {
				continue;
			}
			matchingGuitars.add(guitar);
		}
		return matchingGuitars;
	}
}

class Guitar1 {
	private String serialNumber;
	private Builder3 builder;
	private String model;
	private Type3 type;
	private Wood3 backWood, topWood;
	private double price;

	public Guitar1(String serialNumber, double price, Builder3 builder, String model, Type3 type, Wood3 backWood,
			Wood3 topWood) {
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

	public Builder3 getBuilder() {
		return builder;
	}

	public String getModel() {
		return model;
	}

	public Type3 getType() {
		return type;
	}

	public Wood3 getBackWood() {
		return backWood;
	}

	public Wood3 getTopWood() {
		return topWood;
	}

	public double getPrice() {
		return price;
	}

}

enum Type {
	ACOUSTIC, ELECTRIC;
	public String toString() {
		switch (this) {
		case ACOUSTIC:
			return "acoustic";
		case ELECTRIC:
			return "electric";
		default:
			return "";
		}
	}
}

enum Builder {
	FENDER, MARTIN;
	public String toString() {
		switch (this) {
		case FENDER:
			return "Fender";
		case MARTIN:
			return "Martin";
		default:
			return "";
		}
	}
}

enum Wood {
	BRAZILIAN, INDIAN, ALDER;
	public String toString() {
		switch (this) {
		case BRAZILIAN:
			return "Brazilian";
		case ALDER:
			return "Alder";
		case INDIAN:
			return "Indian";
		default:
			return "";
		}
	}
}