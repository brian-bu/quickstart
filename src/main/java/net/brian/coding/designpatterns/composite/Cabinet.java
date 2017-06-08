package net.brian.coding.designpatterns.composite;

public class Cabinet extends CompositeEquipment {

	public static double cabinetNetPrice = 10.0;
	public static double cabinetDiscountPrice = 5.0;

	public Cabinet(String name) {
		super(name);
	}

	public double netPrice() {
		return cabinetNetPrice + super.netPrice();
	}

	public double discountPrice() {
		return cabinetDiscountPrice + super.discountPrice();
	}
}
