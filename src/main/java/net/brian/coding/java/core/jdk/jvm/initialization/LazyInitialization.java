package net.brian.coding.java.core.jdk.jvm.initialization;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item71: Use lazy initialization judiciously
 * 
 * �����ֻ�����ʵ�����ֱ������ҳ�ʼ�������Ŀ����ܸߣ����ܾ�ֵ��ʹ���ӳٳ�ʼ��
 * Ҫȷ����һ��Ψһ�İ취���ǲ��������úͲ����ӳٳ�ʼ�������ܲ��
 * ���������������ĳ�ʼ�����������ӳٳ�ʼ���ģ��������Ϊ�˴ﵽ�����Ż���Ŀ�ı����ӳٳ�ʼ��һ����
 * ��Ҫע�⣺����̹߳���һ���ӳٳ�ʼ�����򣬲���ĳ����ʽ��ͬ���Ǻ���Ҫ��
 * a.���ھ�̬����ӳٳ�ʼ�������ʵ����lazy initialization holder class������
 * b.����ʵ���򣬿�ʹ��˫�ؼ��ģʽ������
 * c.����ʵ�����ӳٳ�ʼ��ʱ���������Խ����ظ���ʼ���������ʡȥ�ڶ��μ���ɵ��ؼ��
 * �������ֳ�ʼ����ʾ�����뱾����ʾ����ѡ��Effective Java�鼮����Դ��
 * d.����ʵ��������������Ƿ�ÿ���̶߳����¼������ֵ�������������Ϊ����long��double��Ļ������ͣ��Ϳ��Դӵ��ؼ����ɾ��volatile
 * ���ֱ����Ϊracy single-check idiom�����ַ�����������Ŀǰ��������Ӧ���Ǽ���hashCode������
 * ���磺hashCode�����ڵ�һ�α����õ�ʱ������ɢ���벢���������Ա������ٴε���ʱʹ��
 * @see net.brian.coding.java.core.jdk.valueclasses.objectoverriding.correction.RightValuedClassStudent.hashCode()
 * @see java.lang.String.hashCode()
 */
public class LazyInitialization {
		// ������ʼ������Ӧ���ӳٳ�ʼ��
		@SuppressWarnings("unused")
		private final FieldType field1 = computeFieldValue();

		// ʵ������ӳٳ�ʼ��
		private FieldType field2;

		synchronized FieldType getField2() {
			if (field2 == null)
				field2 = computeFieldValue();
			return field2;
		}

		// ��̬���ӳٳ�ʼ�����ʵ����Lazy initialization holder classģʽ
		// ���ַ�ʽ����Ҫͬ����Ҳ��������ʲô���ʳɱ�������һ����ͨ��˽�о�̬�ڲ���
		private static class FieldHolder {
			static final FieldType field = computeFieldValue();
		}

		static FieldType getField3() {
			return FieldHolder.field;
		}

		// ʵ�����ӳٳ�ʼ��֮˫�ؼ��ģʽ��Double-check idiom
		private volatile FieldType field4;

		FieldType getField4() {
			FieldType result = field4;
			if (result == null) { // First check (no locking)
				synchronized (this) {
					result = field4;
					if (result == null) // Second check (with locking)
						field4 = result = computeFieldValue();
				}
			}
			return result;
		}

		// ʵ�����ӳٳ�ʼ��֮���ؼ��ģʽ��Single-check idiom���������ظ���ʼ��������㲻����Ļ� 
		private volatile FieldType field5;

		@SuppressWarnings("unused")
		private FieldType getField5() {
			FieldType result = field5;
			if (result == null)
				field5 = result = computeFieldValue();
			return result;
		}

		private static FieldType computeFieldValue() {
			return new FieldType();
		}
	}

	class FieldType {
	}