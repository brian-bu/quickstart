package net.brian.coding.java.core.oop;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FindGuitarTester3 {
	public static void main(String[] args) {
		Inventory2 inventory = new Inventory2();
		GuitarSpec guitarSpec = new GuitarSpec(Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
		inventory.addGuitar("", 0, guitarSpec);
		GuitarSpec whatDoYouLike = new GuitarSpec(Builder.FENDER, "Stratocaster", Type.ELECTRIC, Wood.ALDER,
				Wood.ALDER);
		List<Guitar2> guitar = inventory.search(whatDoYouLike);
		if (null != guitar && guitar.size() > 0) {
			System.out.println("FindGuitar2Tester -- main:: We got the guitar you like, we got " + guitar.size());
		} else {
			System.out.println("FindGuitar2Tester -- main:: We got nothing.");
		}
	}
}

class Inventory2 {
	private List<Guitar2> guitars;

	public Inventory2() {
		guitars = new LinkedList<Guitar2>();
	}

	public void addGuitar(String serialNumber, double price, GuitarSpec guitarSpec) {
		Guitar2 guitar = new Guitar2(serialNumber, price, guitarSpec);
		guitars.add(guitar);
	}

	public Guitar2 getGuitar(String serialNumber) {
		for (Iterator<Guitar2> i = guitars.iterator(); i.hasNext();) {
			Guitar2 guitar = (Guitar2) i.next();
			if (guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;
	}

	public List<Guitar2> getGuitars(String serialNumber, GuitarSpec spec) {
		for (Iterator<Guitar2> i = guitars.iterator(); i.hasNext();) {
			Guitar2 guitar = i.next();
			if ((null == guitar.getSerialNumber()) || (!guitar.getSerialNumber().equals(serialNumber))) {
				continue;
			}
			if ((null != spec.getModel()) && (!spec.getModel().equals(guitar.getSpec().getModel()))) {
				continue;
			}
			if (!(guitar.getSpec().getBackWood() != spec.getBackWood())) {
				continue;
			}
			if (!(guitar.getSpec().getBuilder() != spec.getBuilder())) {
				continue;
			}
			if (!(guitar.getSpec().getTopWood() != spec.getTopWood())) {
				continue;
			}
			if (!(guitar.getSpec().getType() != spec.getType())) {
				continue;
			}
			guitars.add(guitar);
		}
		return guitars;
	}

	public List<Guitar2> search(GuitarSpec searchGuitar) {
		List<Guitar2> matchingGuitars = this.getGuitars("", searchGuitar);
		return matchingGuitars;
	}
}

class Guitar2 {
	private String serialNumber;
	private double price;
	private GuitarSpec spec;

	public Guitar2(String serialNumber, double price, GuitarSpec guitarSpec) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.spec = guitarSpec;
	}

	public GuitarSpec getSpec() {
		return spec;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public double getPrice() {
		return price;
	}

}

class GuitarSpec {
	private Builder builder;
	private String model;
	private Type type;
	private Wood backWood, topWood;

	public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood) {
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.backWood = backWood;
		this.topWood = topWood;
	}

	public Builder getBuilder() {
		return builder;
	}

	public String getModel() {
		return model;
	}

	public Type getType() {
		return type;
	}

	public Wood getBackWood() {
		return backWood;
	}

	public Wood getTopWood() {
		return topWood;
	}
}

enum Type3 {
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

enum Builder3 {
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

enum Wood3 {
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