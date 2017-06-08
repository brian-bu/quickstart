package net.brian.coding.algorithm.demo;

public class RecurrenceOutput {
	public static String exR1(int input) {
		if(input<=0) return "";
		return exR1(input-3) + input + exR1(input-2) + input;
	}
	
	
	public static void main(String[] args) {
		System.out.println(exR1(6));
	}
}
