package net.brian.coding.java.core.jdk.jvm.classloader.inpractice;
/**
 * Ϊ�˶������ִ���������ļ�����
 * ��defineClass�������ų�����ֻ���ⲿ��ʽ���õ�ʱ��Ż�ʹ�õ�loadByte����
 * �����������ʱ����Ȼ����ԭ�е�˫��ί�ɹ���ʹ��loadClass�������������
 * û����дloadClass��findClass����������ⲿ��ʽ����loadByte�Ļ�������������������ҷ�Χ�����ĸ������������ȫһ�µģ�
 * �����������ʱ�����ᰴ��˫��ί��ģ�ͽ����������
 * 
 * ���������ʵ�֡�ͬһ���������Ա���μ��ء����������������Ψһ�Ĺ������ǹ�������ClassLoader��protected������defineClass
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }

}

