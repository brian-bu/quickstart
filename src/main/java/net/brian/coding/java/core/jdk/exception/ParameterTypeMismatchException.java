package net.brian.coding.java.core.jdk.exception;
/**
 * 
 * ��Java���õ��쳣��������ȷ��˵���쳣�����ʱ����Ҫ�����Լ����쳣
 * ��Ҫע����ǣ�Ψһ���õľ��������������Ϣ�����Բ�Ҫ���쳣�������ϻ��Ѿ���
 *
 */
public class ParameterTypeMismatchException extends Throwable

{
	private static final long serialVersionUID = 8404502905866505151L;

	public ParameterTypeMismatchException(String string) {
	}

	public static void main(String[] args) {
		try {
			// ���ô�throws�����ķ�����������ʽ������쳣
			// ���򣬱�����main�������ٴ������׳�
			throwChecked(-3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// �����׳�Runtime�쳣�ķ����ȿ�����ʽ������쳣��
		// Ҳ�ɲ�������쳣
		throwRuntime(3);
	}

	public static void throwChecked(int a) throws Exception {
		if (a > 0) {
			// �����׳�Exception�쳣
			// �ô�����봦��try������ڴ�throws�����ķ�����
			throw new Exception("a��ֵ����0��������Ҫ��");
		}
	}

	public static void throwRuntime(int a) {
		if (a > 0) {
			// �����׳�RuntimeException�쳣���ȿ�����ʽ������쳣
			// Ҳ����ȫ��������쳣���Ѹ��쳣�����÷��������ߴ���
			throw new RuntimeException("a��ֵ����0��������Ҫ��");
		}
	}
}