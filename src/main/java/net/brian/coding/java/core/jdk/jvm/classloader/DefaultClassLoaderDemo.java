package net.brian.coding.java.core.jdk.jvm.classloader;
/**
 * 
 * ClassLoader����Ҫ���ã�
 * a.��Class���ص�JVM�����ô�
 * b.���ÿ������˭���أ���һ�ָ����ȵĵȼ����ػ���
 * c.��Class�ֽ������½�����JVMͳһҪ��Ķ����ʽ
 * 
 * ClassLoader������ķ�ʽ��
 * a.��ʽ���أ�ĳ��class��ClassLoader�������ؾ�����ʽ�ģ���ʽ���صķ�ʽͨ���У�
 * Class.forName("abc.class");ClassLoader.loaderClass("abc.class");ClassLoader.findSystemClass("abc.class");
 * ��Щ��������һ����ͬ����Ǵ���Ĳ�������һ���ַ�����һ���ַ���ƴд������߸�����û�������ͻ��׳�ClassNotFoundException
 * 
 * �����ʽ���س������쳣����ClassNotFoundException
 * 
 * @see net.brian.coding.java.core.jdk.jvm.classloader.ClassUtil.printClassPath(ClassLoader)
 * 
 * b.��ʽ���أ���class�����ص�ʱ�����������������಻���ڴ���JVM���Զ�����Щ����أ��������ʽ���ء���ʽ����ʽͨ���ǻ��ʹ�õ�
 * 
 * �����ʽ���س������쳣����NoClassDefFoundError��������JVM�������ڼ���ʽ����ĳ�����ʱ����ʽ�����˸����а������������������
 * ������NoClassDefFoundError��Ҫ�ڴ�������������ʱ��ŷ����ģ��������޷����ô��󣬱���ķ������Ǳ�֤���е������඼��classpath��
 * 
 */
public class DefaultClassLoaderDemo {
	/**
	 * ����㲻�����¶��������Ĺ���Ҳû�и��ӵĴ����߼�
	 * ֻ�������е�ʱ������Լ�ָ����һ������ѣ��Ϳ������������ַ���
	 * @throws ClassNotFoundException
	 */
	public void loadClassByDefault() throws ClassNotFoundException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		// ����loadClass
		classLoader.loadClass("abc.class");
		// ����ClassLoader�ṩ��getResource����getResourceAsStream
		classLoader.getResource("abc.class");
		classLoader.getResourceAsStream("abc.class");
	}
}
