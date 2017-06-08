package net.brian.coding.java.core.jdk.jvm.deepjvm;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {
	public static void main(String[] args) {
		@SuppressWarnings("unchecked")
		SoftReference<PersonBean>[] people = new SoftReference[100];
		for(int i = 0; i < people.length; i++) {
			people[i] = new SoftReference<PersonBean>(new PersonBean("name:: " + "name" + i, "age:: " + i));
		}
		System.out.println(people[2].get());
		System.out.println(people[4].get());
		System.gc();
		System.runFinalization();
		System.out.println(people[2].get());
		System.out.println(people[4].get());
	}
}