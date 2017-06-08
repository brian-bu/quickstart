package net.brian.coding.designpatterns.composite;

public class Chassis extends CompositeEquipment {

	public static double chassisNetPrice = 2.0;
	public static double chassisDiscountPrice = 1.0;

	public Chassis(String name) {
		super(name);
	}

	public double netPrice() {
		return chassisNetPrice + super.netPrice();
	}

	public double discountPrice() {
		return chassisDiscountPrice + super.discountPrice();
	}
}