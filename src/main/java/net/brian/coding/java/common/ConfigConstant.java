package net.brian.coding.java.common;
/**
 * 
 * Effective Java 2th by Joshua Bloch
 * 
 * item19: Use interfaces only to define types
 * 
 * �ӿ�ֻӦ�������������ͣ������ǵ�������
 * 
 * ������һ�������ӿڣ��ǶԽӿڵĲ���ʹ�ã�
 * a.����������а汾����������ӿڣ���Ҫʹ�ø����ʵ�����ܵ�Ӱ��
 * b.��final��ʵ���˸ýӿڣ������е������ռ�Ҳ�ᱻ�ӿڵĳ�������Ⱦ
 * 
 * ���ڳ�������ȷʹ�ÿ��Բο����·�����
 * a.���������ĳ�����е�����߽ӿڽ�����أ���Ӧ�ð���Щ������ӵ�������ӿ���
 * b.������ö�ٴ���static final int����ĳ���
 * c.ʹ��˽�л��������ĳ�����
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
