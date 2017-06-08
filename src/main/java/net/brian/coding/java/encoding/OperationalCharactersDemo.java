package net.brian.coding.java.encoding;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

public class OperationalCharactersDemo {
	public static void main(String[] args) {
		int n = 11100;
		int fourthBitFromRight = (n & (1 << 3)) >> 3;
		System.out.println("fourthBitFromRight:: " + fourthBitFromRight);
		String str = "你好世界";
		outputCharOfStringByDifferentEncoding(str, "iso8859-1");
	}
	
	private static void outputCharOfStringByDifferentEncoding(String str, String encoding) {
		String encodingStr = null;
		try {
			encodingStr = new String(str.getBytes(), encoding);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			Field field = Class.forName("java.lang.String").getDeclaredField("value");
			field.setAccessible(true);
			char[] ch = (char[])field.get(encodingStr);
			System.out.println("Output the size of the char array:: " + ch.length);
			for(char c : ch)
				System.out.println("Output the value of the char value in the String:: " + c);
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
