package net.brian.coding.java.core.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NullObjectHandler {
	private final List<String> stringList = somehowReturnList();
	private static final String[] EMPTY_STRING_ARRAY = new String[0];

	public List<String> getStringList() {
		if (stringList.isEmpty()) {
			System.err.println("NullObjectHandler -- getStringList():: stringList is not null but empty!");
			return Collections.emptyList();
		} else {
			System.out.println("NullObjectHandler -- getStringList():: stringList is not null nor empty!");
			return new ArrayList<String>(stringList);
		}
	}

	private List<String> somehowReturnList() {
		return Arrays.asList(EMPTY_STRING_ARRAY);
		// Instead of return null;
	}
}
