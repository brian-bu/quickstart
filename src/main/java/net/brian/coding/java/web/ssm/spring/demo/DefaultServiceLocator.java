package net.brian.coding.java.web.ssm.spring.demo;
/**
 * 
 * spring容器创建对象的工厂方式：
 * 静态工厂、实例工厂
 *
 */
public class DefaultServiceLocator {
	private static ClientService clientServiceForStaticFactory = new ClientService();
	private static ClientService clientServiceForInstanceFactory = new ClientServiceImpl();
	private static AccountService accountServiceForInstanceFactory = new AccountServiceImpl();
	// 私有构造器，无法通过构造器实例化，强制规定这个bean的实例创建方式为spring或反射
	private DefaultServiceLocator() {
	}

	public static ClientService createStaticFactory() {
		// ClientService没有被配置为lazy-init，因此容器启动时这句将会打印到控制台
		System.out.println("createStaticFactory() on ioc container startup...");
		return clientServiceForStaticFactory;
	}

	public AccountService createAccountInstanceFactory() {
		// AccountService被配置为lazy-init，因此容器启动时这句不会打印到控制台
		System.out.println("createAccountInstanceFactory() on ioc container startup...");
		return accountServiceForInstanceFactory;
	}

	public ClientService createClientInstanceFactory() {
		return clientServiceForInstanceFactory;
	}
}

class ClientService {}
class ClientServiceImpl extends ClientService {}
class AccountService {}
class AccountServiceImpl extends AccountService {}