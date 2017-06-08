package net.brian.coding.designpatterns.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class CompositeEquipment extends Equipment {

	private int i = 0;

	private List<Equipment> equipment = new ArrayList<Equipment>();

	public CompositeEquipment(String name) {
		super(name);
	}

	public boolean add(Equipment equipment) {
		if (equipment instanceof Disk && this instanceof Chassis) {
			System.out.println("CompositeEquipment -- add() -- add a disk into the chassis...");
		} else if (equipment instanceof Chassis && this instanceof Cabinet) {
			System.out.println("CompositeEquipment -- add() -- add a chassis into the cabinet...");
		}
		this.equipment.add(equipment);
		return true;
	}

	public double netPrice() {
		double netPrice = 0.;
		if (this instanceof Cabinet) {
			System.out.println(
					"CompositeEquipment -- netPrice() -- Cabinet.cabinetNetPrice:: " + Cabinet.cabinetNetPrice);
		} else if (this instanceof Chassis) {
			System.out.println(
					"CompositeEquipment -- netPrice() -- Chassis.chassisNetPrice:: " + Chassis.chassisNetPrice);
		}
		Iterator<Equipment> iter = equipment.iterator();
		while (iter.hasNext()) {
			Equipment equipment = (Equipment) iter.next();
			if (equipment instanceof Chassis) {
				System.out.println("CompositeEquipment -- netPrice() -- counting the price of chassis...");
			} else if (equipment instanceof Disk) {
				System.out.println("CompositeEquipment -- netPrice() -- counting the price of disk...");
				System.out.println("CompositeEquipment -- netPrice() -- Disk.diskNetPrice:: " + Disk.diskNetPrice);
			}
			netPrice += equipment.netPrice();
		}
		return netPrice;
	}

	public double discountPrice() {
		double discountPrice = 0.;
		Iterator<Equipment> iter = equipment.iterator();
		while (iter.hasNext()) {
			discountPrice += ((Equipment) iter.next()).discountPrice();
		}
		return discountPrice;
	}

	public Iterator<Equipment> iter() {
		return equipment.iterator();
	}

	public boolean hasNext() {
		return i < equipment.size();
	}

	public Object next() {
		if (hasNext())
			return equipment.get(i++);
		else
			throw new NoSuchElementException();
	}

}