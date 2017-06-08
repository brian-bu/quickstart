package net.brian.coding.designpatterns.composite;

public class Client {  
	  
    public static void main(String[] args) {  
  
        Cabinet cabinet = new Cabinet("Cabinet");  
  
        Chassis chassis = new Chassis("Chassis");  
  
        cabinet.add(chassis);  
  
        chassis.add(new Disk("Disk"));  
  
        System.out.println("NetPrice of the disk:: " + cabinet.netPrice());  
  
        System.out.println("discountPrice of the disk:: " + cabinet.discountPrice());  
    }  
} 