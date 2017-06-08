package net.brian.coding.java.core.oop.classesinterfaces.annotation;
/**
 * Effective Java 2th by Joshua Bloch
 * 
 * item35: Prefer annotations to naming patterns
 * 
 * item37: Use marker interfaces to define types
 * 
 * naming patterns即命名模式：通过命名进行约束，比如早期的junit要求方法命名以test开头
 * 命名模式的缺点：
 * a.文字拼写错误会导致失败，且没有任何提示
 * b.无法确保他们只用于相应的程序元素上
 * c.没有提供将参数值和程序元素关联起来的好方法
 * 
 * 注解很好的解决了所有命名模式的问题，标注注解没有参数，只是标注被注解的元素
 * 因此如果该注解不是被用在方法声明或者存在编写错误则根本无法通过编译
 * 关于注解的示范代码已经根据Effective Java形成一个mini框架，见：
 * net/brian/coding/java/core/oop/classesinterfaces/annotation/minijunit/package-info.java
 * 
 * 注解永远不会改变被注解代码的语义，但使得程序可以被工具进行特殊处理，具体处理方法就是：
 * 测试工具在命令行上使用完全匹配的类名，并通过调用Method.invoke反射式地运行类中所有标注了Test的方法
 * 
 * ===============划重点了=================
 * 
 * 对于标记接口，也就是空接口，它可以描述整个对象的某个约束条件或者表明实例能够利用其他某个类的方法进行处理。
 * 常见标记接口有：
 * a.Cloneable：实现这个接口的类实例可以调用Object.clone或者覆盖这个方法进行实例的克隆
 * b.Serializable：实现了这个接口的类的实例可以被写到ObjectOutputStream中
 * 
 * 标记接口并没有因为注解而过时，它相比于注解的两个优点：
 * a.标记接口定义的类型是由被标记类的实例实现的，标记注解则没有定义这样的类型
 * 这个类型允许你编译时捕捉在使用标记注解的情况下要到运行时才能捕捉到的错误
 * b.更加精确的被锁定
 * 
 * 标记注解相比于标记接口的优势：
 * a.简单的标记注解类型可以演变成更加丰富的注解类型，这种演变对于标记接口而言是不可能的
 * b.标记注解在那些支持注解作为编程元素之一的框架中同样具有一致性
 * 
 * 注意：
 * 如果想要定义一个任何新方法都不会与之关联的类型，标记接口就是最好的选择，如果想要标记程序元素而非类和接口
 * 考虑到未来可能要给标记添加更多的信息，或者标记要适合于已经广泛使用了注解类型的框架，那么标记注解就是最好的选择
 * 
 * 本条讲的是如果想要定义类型，一定要使用接口。与之相对的是item19：如果不想定义类型就不要使用接口
 * @see net.brian.coding.java.common.ConfigConstant
 * 
 */
public class MarkerAnnotationAndMarkerInterface {

}
