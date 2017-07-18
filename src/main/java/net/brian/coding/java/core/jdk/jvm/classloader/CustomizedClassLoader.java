package net.brian.coding.java.core.jdk.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立其在java虚拟机中的唯一性
 * 本例将演示：由自定义的加载类只能获取同包下的class，而系统的class不能被加载
 * 而且由Class.forName()获取的类与自定义加载类得到的类不是同一个类
 * 
 * 注意：最好不要重写loadClass方法，因为这样容易破坏双亲委托模式
 * 不能把 com/paddx/test/classloading/Test.class 放在类路径下
 * 否则，由于双亲委托机制的存在，会直接导致该类由AppClassLoader 加载，而不会通过我们自定义类加载器来加载。
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
