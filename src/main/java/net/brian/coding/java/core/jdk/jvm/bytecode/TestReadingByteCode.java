package net.brian.coding.java.core.jdk.jvm.bytecode;

import java.io.InputStream;
/**
 * 
 * ���Է�����ڣ�����ʹ��javap�����ֽ��뷴���룬���Ա����ǵĶ�ȡ�뷴������������ڲ��
 *
 */
public class TestReadingByteCode {
	public static void main(String[] args) throws Exception {
		InputStream in = Class.class.getResourceAsStream("Y:\\projects\\quickstart\\target\\classes\\net\\brian\\coding\\java\\core\\jdk\\jvm\\bytecode\\DemoClassForByteCode.class");
		ClassParser parser = new ClassParser(in);
		parser.parse();
	}
}
