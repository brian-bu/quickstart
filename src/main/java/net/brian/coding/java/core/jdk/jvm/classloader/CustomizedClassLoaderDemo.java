package net.brian.coding.java.core.jdk.jvm.classloader;
/**
 * �Զ���ClassLoader����ɵ���Ҫ������
 * a.���Զ���·���²����Զ����class���ļ������������Զ�����಻һ������classpath�£�����������Ҫ�Լ�ʵ��ClassLoader��
 * ����tomcat�����û����Զ������webapp����Ĺ���·�������������AppClassLoader��ͬʱ��ʵ�����Զ����StandardClassLoader
 * ���StandardClassLoaderʹ�õ���ί�м��أ��������webapp�Ĺ���·��������ͨ��ί�е��ø��������AppClassLoader
 * ������������tomcat����������Ȼ��ͨ��AppClassLoader����
 * ���tomcat��classpathû�����ã�AppClassLoader�����ز���tomcat�������࣬��ʱ�����Ҫͨ��StandardClassLoader���м�����
 * ��ʵ����StandardClassLoader����AppClassLoader�����ع�����һ���ģ�Ψһ��ͬ�ľ��Ǽ���·����ͬ
 * 
 * b.����Ҫ���ص���������⴦������Ϊ��֤��ȫ�Խ��м��ܽ���
 * c.ʵ������Ȳ��𣺼���Ѽ��ص�class�ļ��Ƿ��޸ģ�������޸Ŀ������¼�������࣬���巽ʽ�ǣ�������ͬ��ClassLoader��ʵ������
 * ͨ�������ͬ��ʵ������������ͬ�����ࡣ��ͬ��ClassLoaderʵ��Ҳ����û������֮�󱻻��գ�ֻ������ֽ����һֱ�������ô���ֱ��Full GC�Żᱻ����
 * 
 * �Զ���ClassLoader����Ҫ;����
 * a.�Զ����ClassLoader����ѡ��ֱ�Ӽ̳г�����ClassLoader��Ҳ���Լ̳�URLClassLoader
 * ��Ϊ�󲿷ֹ����Ѿ���URLClassLoaderʵ�ֺ��ˣ�����ֻ�����ʵ��ĵط���һЩ�޸ľͺ���
 * b.���ܼ̳г�����ClassLoader��������������URLClassLoader������Զ����ClassLoader�ĸ������඼��AppClassLoader
 * ���۵����ĸ����๹�����������Ķ���������յ���getSystemClassLoader()��Ϊ���������������������ȡ��������AppClassLoader
 * c.����AppClassLoader��������System.getProperty("java.class.path")Ŀ¼�µ��඼���Ա���������������
 * java.class.path�������Ǿ����õ���classpath
 *
 */
public class CustomizedClassLoaderDemo {

}
