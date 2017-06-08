package net.brian.coding.java.core.jdk.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class InnerClassFileDemo {
	private static String FILEPATH_DIRLISTER = ".";

	public static void main(final String[] args) {
		File filepath = new File(FILEPATH_DIRLISTER);
		String[] list;
		if (args.length == 0) {
			list = filepath.list();
		} else {
			list = filepath.list(new FilenameFilter() {
				private Pattern pattern;

				@Override
				public boolean accept(File dir, String name) {
					return pattern.matcher(name).matches();
				}
			});
			Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
			for (String dirItem : list) {
				System.out.println("net.brian.jdk.io.demo.FileDemo.main(String[]) -- dirItem:: " + dirItem);
			}
		}
	}
}
