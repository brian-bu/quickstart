package net.brian.coding.algorithm.demo.sorting;

import java.util.Arrays;

import org.junit.Test;

public class SortingAPIs {
	@Test
	public void testArraySorting() {
		String[] removement = { "<code>", "</code>", "<b>", "</b>", "<AltParagraph>", "</AltParagraph>", "<Paragraph>",
				"</Paragraph>", "<Content>", "</Content>" };
		Arrays.sort(removement);
		System.out.println(Arrays.toString(removement));
	}
}
