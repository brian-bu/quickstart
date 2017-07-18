package net.brian.coding.java.core.jdk.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ��������һ���࣬����Ҫ�ɼ��������������������౾��һͬȷ������java������е�Ψһ��
 * ��������ʾ�����Զ���ļ�����ֻ�ܻ�ȡͬ���µ�class����ϵͳ��class���ܱ�����
 * ������Class.forName()��ȡ�������Զ��������õ����಻��ͬһ����
 * 
 * ע�⣺��ò�Ҫ��дloadClass��������Ϊ���������ƻ�˫��ί��ģʽ
 * ���ܰ� com/paddx/test/classloading/Test.class ������·����
 * ��������˫��ί�л��ƵĴ��ڣ���ֱ�ӵ��¸�����AppClassLoader ���أ�������ͨ�������Զ���������������ء�
 *
 */
public class CustomizedClassLoader extends ClassLoader {
 
    private String root;
 
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }
 
    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try {
            @SuppressWarnings("resource")
			InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public String getRoot() {
        return root;
    }
 
    public void setRoot(String root) {
        this.root = root;
    }
 
    public static void main(String[] args)  {
 
    	CustomizedClassLoader classLoader = new CustomizedClassLoader();
        classLoader.setRoot("Y:\\projects\\quickstart\\src\\main\\resources");
 
        Class<?> testClass = null;
        try {
            testClass = classLoader.loadClass("net.brian.coding.java.core.jdk.jvm.deepjvm.PersonBean");
            Object obj1 = testClass.newInstance();
			Object obj2 = Class.forName("net.brian.coding.java.core.jdk.jvm.deepjvm.PersonBean").newInstance();
			System.out.println(obj1 instanceof net.brian.coding.java.core.jdk.jvm.classloader.PersonBean);
			System.out.println(obj2 instanceof net.brian.coding.java.core.jdk.jvm.classloader.PersonBean);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
