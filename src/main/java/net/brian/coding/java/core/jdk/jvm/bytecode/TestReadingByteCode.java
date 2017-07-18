package net.brian.coding.java.core.jdk.jvm.bytecode;

import java.io.InputStream;
/**
 * 
 * 测试方法入口，可以使用javap进行字节码反编译，来对比我们的读取与反编译结果差别，用于查错
 *
 */
public class TestReadingByteCode {
	public static void main(String[] args) throws Exception {
		InputStream in = Class.class.getResourceAsStream("Y:\\projects\\quickstart\\target\\classes\\net\\brian\\coding\\java\\core\\jdk\\jvm\\bytecode\\DemoClassForByteCode.class");
		ClassParser parser = new ClassParser(in);
		parser.parse();
	}
}
