package net.brian.coding.java.core.jdk.jvm.classloader;
/**
 * 自定义ClassLoader能完成的主要工作：
 * a.在自定义路径下查找自定义的class类文件（由于我们自定义的类不一定放在classpath下，所以我们需要自己实现ClassLoader）
 * 比如tomcat由于用户可以定义除了webapp以外的工作路径，因此在沿用AppClassLoader的同时还实现了自定义的StandardClassLoader
 * 这个StandardClassLoader使用的是委托加载，如果不是webapp的工作路径，首先通过委托调用父类加载器AppClassLoader
 * 因此这种情况下tomcat容器本身仍然是通过AppClassLoader加载
 * 如果tomcat的classpath没有设置，AppClassLoader将加载不到tomcat容器的类，这时就真的要通过StandardClassLoader进行加载了
 * 其实无论StandardClassLoader还是AppClassLoader，加载规则都是一样的，唯一不同的就是加载路径不同
 * 
 * b.对于要加载的类进行特殊处理，比如为保证安全性进行加密解密
 * c.实现类的热部署：检查已加载的class文件是否被修改，如果被修改可以重新加载这个类，具体方式是：创建不同的ClassLoader的实例对象
 * 通过这个不同的实例对象来加载同名的类。不同的ClassLoader实例也会在没有引用之后被回收，只是类的字节码会一直留在永久代中直到Full GC才会被回收
 * 
 * 自定义ClassLoader的主要途径：
 * a.自定义的ClassLoader可以选择直接继承抽象类ClassLoader，也可以继承URLClassLoader
 * 因为大部分工作已经被URLClassLoader实现好了，我们只需在适当的地方做一些修改就好了
 * b.不管继承抽象类ClassLoader，还是它的子类URLClassLoader，这个自定义的ClassLoader的父加载类都是AppClassLoader
 * 不论调用哪个父类构造器，创建的对象必须最终调用getSystemClassLoader()作为父加载器，而这个方法获取到的正是AppClassLoader
 * c.对于AppClassLoader，所有在System.getProperty("java.class.path")目录下的类都可以被这个类加载器加载
 * java.class.path就是我们经常用到的classpath
 *
 */
public class CustomizedClassLoaderDemo {

}
