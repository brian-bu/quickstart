package net.brian.coding.java.core.jdk.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class FileDemo {
	// This is the directory's path in current file system.
	private static String FILEPATH_DIRLISTER = ".";

	public static void main(String[] args) {
		// When we create a File object, it will point to the handler of a file
		// or directory of current operation system.
		// This will take a few steps:
		// First: A static handler of current FileSystem.
		// Second: NullPointerException if the pathname is null.
		// Then: Try to convert the given pathname string to normal form.
		// Then: Assign the pathname to static path in File.class.
		// Then: The object filepath is legal to invoke the methods defined in File.class.
		File filepath = new File(FILEPATH_DIRLISTER);
		String[] list;
		if (args.length == 0) {
			// return filesystem.list(this);// The this here is filepath.
			list = filepath.list();
		} else {
			// return v.toArray(new String[v.size()]);
			list = filepath.list(new DirFilter(args[0]));
		}
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list) {
			System.out.println("net.brian.jdk.io.demo.FileDemo.main(String[]) -- dirItem:: " + dirItem);
		}
	}
}

class DirFilter implements FilenameFilter {
	private Pattern pattern;

	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}

	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}
}
