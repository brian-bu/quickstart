package net.brian.coding.designpatterns.observer;

import java.util.HashSet;

public class NormalTest {
	public static void main(String[] args) {
		ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

		set.addObserver(new SetObserver<Integer>() {
			public void added(ObservableSet<Integer> s, Integer e) {
				System.out.println(e);
			}
		});

		for (int i = 0; i < 100; i++)
			set.add(i);
	}
}
