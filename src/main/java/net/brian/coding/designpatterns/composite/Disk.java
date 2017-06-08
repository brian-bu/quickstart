package net.brian.coding.designpatterns.composite;

public class Disk extends Equipment {

	public static double diskNetPrice = 2.0;

	public static double diskDiscountPrice = 1.0;

	public Disk(String name) {
		super(name);
	}

	@Override
	public double netPrice() {
		return diskNetPrice;
	}

	@Override
	public double discountPrice() {
		return diskDiscountPrice;
	}

}