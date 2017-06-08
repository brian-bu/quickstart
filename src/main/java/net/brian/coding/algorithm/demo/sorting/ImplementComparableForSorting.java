package net.brian.coding.algorithm.demo.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImplementComparableForSorting {
	public static void main(String[] args) {
		List<User> userList = new ArrayList<User>();
		userList.add(new User("Lucy", 19));
		userList.add(new User("Jack", 19));
		userList.add(new User("Jim", 19));
		userList.add(new User("James", 19));
		userList.add(new User("Herry", 19));
		userList.add(new User("Luccy", 19));
		userList.add(new User("James", 18));
		userList.add(new User("Herry", 20));

		Collections.sort(userList);

		for (User user : userList) {
			System.out.println(user.getName() + "\t\t" + user.getAge());
		}
	}

	private static class User implements Comparable<User> {

		private String name;
		private int age;

		public User(String name, int age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public int compareTo(User another) {
			int compareName = this.name.compareTo(another.getName());
			if (compareName == 0) {
				return (this.age == another.getAge() ? 0 : (this.age > another.getAge() ? 1 : -1));
			}
			return compareName;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}
	}
}