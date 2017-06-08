package net.brian.coding.java.core.jdk.newfeatures;

public class JavaCallback {
	public static void main(String[] args) {
		int a = 26549;
		int b = 16487;
		Student s = new Student("Brian");
		s.callHelp(a, b);
	}
}

class SuperCalculator {
	public void add(int a, int b, Student brian) {
		int result = a + b;
		brian.fillBlank(a, b, result);
	}
}

class Student {
	private String name = null;

	public Student(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void callHelp(int a, int b) {
		new SuperCalculator().add(a, b, this);
	}

	public void fillBlank(int a, int b, int result) {
		System.out.println(name + "JavaCallback - Student - Asking Sure for help:: " + a + " + " + b + " = " + result);
	}
}