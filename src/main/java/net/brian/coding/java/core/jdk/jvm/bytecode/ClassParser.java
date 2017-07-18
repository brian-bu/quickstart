package net.brian.coding.java.core.jdk.jvm.bytecode;

import java.io.IOException;
import java.io.InputStream;
/**
 * 
 * �������Class�ֽ�������ṹ�Ľ�����
 *
 */
public class ClassParser {
	private InputStream in;

	public ClassParser(InputStream in) {
		this.in = in;
	}

	public void parse() throws IOException { // ħ��
		magicNumber();
		// ���ΰ汾��
		version();
		// ������
		constantPool();
		// ���ӿ����η�
		accessFlag();
		// �̳й�ϵ����ǰ�ࡢ���ࡢ���ӿڣ�
		inheritence();
		// �ֶμ���
		fieldList();
		// ��������
		methodList();
		// ���Լ���
		attributeList();
	}

	private void attributeList() throws IOException {
		line();
		int attrLength = StreamUtils.read2(in);
		System.out.println("����" + attrLength + "������");
		for (int i = 0; i < attrLength; i++) {
			line();
			attribute();
		}
	}

	private void attribute() throws IOException {
		int nameIndex = StreamUtils.read2(in);
		int length = StreamUtils.read4(in);
		byte[] info = StreamUtils.read(in, length);
		System.out.println("nameIndex:" + nameIndex);
		System.out.println("length:" + length);
		System.out.println("info:" + info);
	}

	private void methodList() throws IOException {
		int length = StreamUtils.read2(in);
		System.out.println("����" + length + "������");
		for (int i = 0; i < length; i++)
			method();
	}

	private void method() throws IOException {
		System.out.println("---------------------");
		int accessFlag = StreamUtils.read2(in);
		int nameIndex = StreamUtils.read2(in);
		int descriptorIndex = StreamUtils.read2(in);
		System.out.println("accessFlag:" + accessFlag);
		System.out.println("nameIndex:" + nameIndex);
		System.out.println("descriptorIndex:" + descriptorIndex);
		attributeList();
	}

	private void fieldList() throws IOException {
		line();
		int length = StreamUtils.read2(in);
		System.out.println("����" + length + "���ֶ�");
		for (int i = 0; i < length; i++) {
			System.out.println("-----------------------------");
			int accessFlag = StreamUtils.read2(in);
			int nameIndex = StreamUtils.read2(in);
			int descriptorIndex = StreamUtils.read2(in);
			System.out.println("accessFlag:" + accessFlag);
			System.out.println("nameIndex:" + nameIndex);
			System.out.println("descriptorIndex:" + descriptorIndex);
			attributeList();
		}
	}

	private void inheritence() throws IOException {
		line();
		int thisClassRef = StreamUtils.read2(in);
		int superClassRef = StreamUtils.read2(in);
		System.out.println("thisClassRef:" + thisClassRef);
		System.out.println("superClassRef:" + superClassRef);
		int interfaceLen = StreamUtils.read2(in);
		System.out.println("�ӿ�������" + interfaceLen);
		for (int i = 0; i < interfaceLen; i++) {
			int interfaceRef = StreamUtils.read2(in);
			System.out.println("interfaceRef:" + interfaceRef);
		}
	}

	private void accessFlag() throws IOException {
		line();
		int accessFlag = StreamUtils.read2(in);
		System.out.println("accessFlag:0x" + Integer.toHexString(accessFlag) + "(" + accessFlag + ")");
	}

	private void constantPool() throws IOException {
		new ConstantPoolParser(in).constPool();
	}

	private void version() throws IOException {
		line();
		int minorVersion = StreamUtils.read2(in);
		int majorVersion = StreamUtils.read2(in);
		System.out.println("�汾��" + majorVersion + "." + minorVersion);
	}

	private void magicNumber() throws IOException {
		line();
		int magic = StreamUtils.read4(in);
		System.out.println("ħ����" + Integer.toHexString(magic).toUpperCase());
	}

	private void line() {
		System.out.println("----------------------");
	}
}