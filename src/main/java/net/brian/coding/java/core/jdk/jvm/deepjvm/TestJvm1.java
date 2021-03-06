package net.brian.coding.java.core.jdk.jvm.deepjvm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestJvm1 {

	@Before
	public void testInternInJdk6() {
		String sb1 = new StringBuffer("Hi ").append("there").toString();
		System.out.println("sb.intern() == sb:: " + (sb1.intern() == sb1));
		String sb2 = new StringBuffer("Hi").append("there").toString();
		System.out.println("sb.intern() == sb:: " + (sb2.intern() == sb2));
	}

	@Test
	public void testPernGenOOM() {
		List<String> list = new ArrayList<String>();
		int i = 0;
		// Would interrupted after about 56 secs cause PermGen error under jdk6.
		while (true) {
			System.out.println("Output the value of i:: " + i);
			list.add(Integer.valueOf(i).toString().intern());
			// Always output 0 cause i would be re-initialized every time.
			// i = i++;
			i++;
		}
	}

}
