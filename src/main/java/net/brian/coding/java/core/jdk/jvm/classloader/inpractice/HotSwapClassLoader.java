package net.brian.coding.java.core.jdk.jvm.classloader.inpractice;
/**
 * 为了多次载入执行类而加入的加载器
 * 把defineClass方法开放出来，只有外部显式调用的时候才会使用到loadByte方法
 * 由虚拟机调用时，仍然按照原有的双亲委派规则使用loadClass方法进行类加载
 * 没有重写loadClass或findClass，如果不算外部显式调用loadByte的话，这个类加载器的类查找范围与它的父类加载器是完全一致的，
 * 被虚拟机调用时，他会按照双亲委派模型交给父类加载
 * 
 * 这个类用于实现“同一个类代码可以被多次加载”这个需求，它所做的唯一的工作就是公开父类ClassLoader的protected方法：defineClass
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }

}

