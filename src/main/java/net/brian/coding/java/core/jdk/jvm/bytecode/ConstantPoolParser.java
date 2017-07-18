package net.brian.coding.java.core.jdk.jvm.bytecode;

import java.io.IOException;
import java.io.InputStream;
/**
 * 
 * �������صĽ�������Ϊ�����ر��϶࣬��������Ҳ�ϴ���˵���������������
 *
 */
public class ConstantPoolParser {
	public static final int Utf8_info = 1;
	public static final int Integer_info = 3;
	public static final int Float_info = 4;
	public static final int Long_info = 5;
	public static final int Double_info = 6;
	public static final int Class_info = 7;
	public static final int String_info = 8;
	public static final int Fieldref_info = 9;
	public static final int Methodref_info = 10;
	public static final int InterfaceMethodref_info = 11;
	public static final int NameAndType_info = 12;
	public static final int MethodHandle_info = 15;
	public static final int MethodType_info = 16;
	public static final int InvokeDynamic_info = 18;
	private InputStream in;

	public ConstantPoolParser(InputStream in) {
		this.in = in;
	}

	public void constPool() throws IOException {
		line();
		int length = StreamUtils.read2(in);
		System.out.println("����" + length + "������");
		boolean doubleBytes = false;
		for (int i = 1; i < length; i++) {
			if (doubleBytes) {
				doubleBytes = false;
				continue;
			}
			line();
			System.out.println("����������" + i);
			int flag = StreamUtils.read1(in);
			// System.out.println("��־:"+flag);
			switch (flag) {
			case Utf8_info:
				utf8Info();
				continue;
			case Integer_info:
				integerInfo();
				continue;
			case Float_info:
				floatInfo();
				continue;
			case Long_info:
				doubleBytes = true;
				longInfo();
				continue;
			case Double_info:
				doubleBytes = true;
				doubleInfo();
				continue;
			case Class_info:
				classInfo();
				continue;
			case String_info:
				stringInfo();
				continue;
			case Fieldref_info:
				fieldrefInfo();
				continue;
			case Methodref_info:
				methodrefInfo();
				continue;
			case InterfaceMethodref_info:
				interfaceMethodrefInfo();
				continue;
			case NameAndType_info:
				nameAndTypeInfo();
				continue;
			case MethodHandle_info:
				methodHandleInfo();
				continue;
			case MethodType_info:
				methodTypeInfo();
				continue;
			case InvokeDynamic_info:
				invokeDynamicInfo();
				continue;
			default:
				System.err.println(flag);
				throw new RuntimeException("unknown");
			}
		}
	}

	private void line() {
		System.out.println("----------------------");
	}

	private void utf8Info() throws IOException {
		int length = StreamUtils.read2(in);
		byte[] buf = StreamUtils.read(in, length);
		String s = new String(buf, 0, buf.length);
		System.out.println("utf8Info����");
		System.out.println("ֵ��" + s);
	}

	private void integerInfo() throws IOException {
		System.out.println("integerInfo����");
		int value = StreamUtils.read4(in);
		System.out.println("ֵ��" + value);
	}

	private void floatInfo() throws IOException {
		System.out.println("floatInfo����");
		int value = StreamUtils.read4(in);
		float f = Float.intBitsToFloat(value);
		System.out.println("ֵ��" + f);
	}

	private void longInfo() throws IOException {
		System.out.println("longInfo����");
		long value = StreamUtils.read8(in);
		System.out.println("ֵ��" + value);
	}

	private void doubleInfo() throws IOException {
		System.out.println("doubleInfo����");
		long value = StreamUtils.read8(in);
		double d = Double.longBitsToDouble(value);
		System.out.println("ֵ��" + d);
	}

	private void classInfo() throws IOException {
		System.out.println("classInfo��:");
		int index = StreamUtils.read2(in);
		System.out.println("index:" + index);
	}

	private void stringInfo() throws IOException {
		System.out.println("stringInfo��:");
		int index = StreamUtils.read2(in);
		System.out.println("index:" + index);
	}

	private void fieldrefInfo() throws IOException {
		int classIndex = StreamUtils.read2(in);
		int nameAndTypeIndex = StreamUtils.read2(in);
		System.out.println("fieldrefInfo��:");
		System.out.println("classIndex:" + classIndex);
		System.out.println("nameAndTypeIndex:" + nameAndTypeIndex);
	}

	private void methodrefInfo() throws IOException {
		int classIndex = StreamUtils.read2(in);
		int nameAndTypeIndex = StreamUtils.read2(in);
		System.out.println("methodrefInfo��:");
		System.out.println("classIndex:" + classIndex);
		System.out.println("nameAndTypeIndex:" + nameAndTypeIndex);
	}

	private void interfaceMethodrefInfo() throws IOException {
		int classIndex = StreamUtils.read2(in);
		int nameAndTypeIndex = StreamUtils.read2(in);
		System.out.println("interfaceMethodrefInfo��:");
		System.out.println("classIndex:" + classIndex);
		System.out.println("nameAndTypeIndex:" + nameAndTypeIndex);
	}

	private void nameAndTypeInfo() throws IOException {
		int nameIndex = StreamUtils.read2(in);
		int typeIndex = StreamUtils.read2(in);
		System.out.println("nameAndTypeInfo��:");
		System.out.println("nameIndex:" + nameIndex);
		System.out.println("typeIndex:" + typeIndex);
	}

	private void methodHandleInfo() throws IOException {
		int referenceKind = StreamUtils.read1(in);
		int referenceIndex = StreamUtils.read2(in);
		System.out.println("methodHandleInfo����");
		System.out.println("referenceKind:" + referenceKind);
		System.out.println("referenceIndex:" + referenceIndex);
	}

	private void methodTypeInfo() throws IOException {
		System.out.println("methodTypeInfo����");
		int descriptorIndex = StreamUtils.read2(in);
		System.out.println("descriptorIndex:" + descriptorIndex);
	}

	private void invokeDynamicInfo() throws IOException {
		int bootstrapMethodAttrIndex = StreamUtils.read2(in);
		int nameAndTypeIndex = StreamUtils.read2(in);
		System.out.println("bootstrapMethodAttrIndex:" + bootstrapMethodAttrIndex);
		System.out.println("nameAndTypeIndex:" + nameAndTypeIndex);
	}
}