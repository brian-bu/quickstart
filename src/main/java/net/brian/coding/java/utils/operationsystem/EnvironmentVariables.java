package net.brian.coding.java.utils.operationsystem;
//: holding/EnvironmentVariables.java
import java.util.Map;

import org.junit.Test;

public final class EnvironmentVariables {
	
	private EnvironmentVariables() {}
	
	@Test
	public void outputAll() {
		for (@SuppressWarnings("rawtypes")
		Map.Entry entry : System.getenv().entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	@Test
	public void testSystemProperties() {
		System.out.println("Java runtime version:\n" + System.getProperty("java.version"));

		System.out.println("Java runtime env supplier:\n" + System.getProperty("java.vendor"));

		System.out.println("Java supplier URL:\n" + System.getProperty("java.vendor.url"));

		System.out.println("Java home path:\n" + System.getProperty("java.home"));

		System.out.println("Java VM api version:\n" + System.getProperty("java.vm.specification.version"));

		System.out.println("Java classpath:\n" + System.getProperty("java.class.path"));

		System.out.println("Java library:\n" + System.getProperty("java.library.path"));

		System.out.println("Java compiler name:\n" + System.getProperty("java.compiler"));

		System.out.println("Extension directorie(s):\n" + System.getProperty("java.ext.dirs"));

		System.out.println("Name of OS:\n" + System.getProperty("os.name"));

		System.out.println("The architecture of OS:\n" + System.getProperty("os.arch"));

		System.out.println("The version of OS:\n" + System.getProperty("os.version"));

		System.out.println("File system seperator:\n" + System.getProperty("file.separator"));

		System.out.println("File system pathSeperator:\n" + System.getProperty("path.separator"));

		System.out.println("File system line separator:\n" + System.getProperty("line.separator"));

		System.out.println("User name in OS:\n" + System.getProperty("user.name"));

		System.out.println("User home in OS:\n" + System.getProperty("user.home"));

		System.out.println("User working directory in OS:\n" + System.getProperty("user.dir"));

	}
}