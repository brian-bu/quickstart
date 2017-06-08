package net.brian.coding.java.common;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item19: Use interfaces only to define types
 * 
 * 接口只应该用来定义类型，而不是导出常量
 * 
 * 本例是一个常量接口，是对接口的不良使用：
 * a.如果将来发行版本更改了这个接口，就要使得更多的实现类受到影响
 * b.非final类实现了该接口，它所有的命名空间也会被接口的常量所污染
 * 
 * 对于常量的正确使用可以参考如下方案：
 * a.如果常量与某个现有的类或者接口紧密相关，就应该把这些常量添加到这个类或接口中
 * b.考虑用枚举代替static final int这类的常量
 * c.使用私有化构造器的常量类
 * 
 */
public interface ConfigConstant {

    String CONFIG_FILE = "smart.properties";

    String JDBC_DRIVER = "smart.framework.jdbc.driver";
    String JDBC_URL = "smart.framework.jdbc.url";
    String JDBC_USERNAME = "smart.framework.jdbc.username";
    String JDBC_PASSWORD = "smart.framework.jdbc.password";

    String APP_BASE_PACKAGE = "smart.framework.app.base_package";
    String APP_JSP_PATH = "smart.framework.app.jsp_path";
    String APP_ASSET_PATH = "smart.framework.app.asset_path";
    String APP_UPLOAD_LIMIT = "smart.framework.app.upload_limit";
}
