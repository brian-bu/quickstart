package net.brian.coding.java.core.jdk.jvm.oom;

import java.util.ArrayList;
import java.util.List;

public class StringInternOOM {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while(true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
