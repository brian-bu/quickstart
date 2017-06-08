package net.brian.coding.java.core.jdk.generic;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item27: Favor generic methods
 * 
 * 所有类型形参声明放在修饰符和方法返回类型之间
 * 方法中定义的类型形参只能在该方法里使用，而接口或类中定义的类型形参可以在整个接口、类中使用
 * 
 * 泛型方法与类型通配符的区别:   
 * 泛型方法允许类型形参被用来表示方法的一个或多个参数之间的类型依赖关系，或者方法返回值与参数之间的类型依赖关系
 * 如果没有这样的类型依赖关系，就不应该使用泛型方法
 * 类型通配符既可以在方法签名中定义形参的类型，也可以用于定义变量类型，但泛型方法中的类型形参必须在对应方法中显式声明
 *
 */
public class GenericMethods {
//TODO: 泛型：针对上述理论进行代码实现
}
