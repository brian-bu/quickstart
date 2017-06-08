package net.brian.coding.java.web.ssm.spring.demo;
/**
 * 
 * spring������������Ĺ�����ʽ��
 * ��̬������ʵ������
 *
 */
public class DefaultServiceLocator {
	private static ClientService clientServiceForStaticFactory = new ClientService();
	private static ClientService clientServiceForInstanceFactory = new ClientServiceImpl();
	private static AccountService accountServiceForInstanceFactory = new AccountServiceImpl();
	// ˽�й��������޷�ͨ��������ʵ������ǿ�ƹ涨���bean��ʵ��������ʽΪspring����
	private DefaultServiceLocator() {
	}

	public static ClientService createStaticFactory() {
		// ClientServiceû�б�����Ϊlazy-init�������������ʱ��佫���ӡ������̨
		System.out.println("createStaticFactory() on ioc container startup...");
		return clientServiceForStaticFactory;
	}

	public AccountService createAccountInstanceFactory() {
		// AccountService������Ϊlazy-init�������������ʱ��䲻���ӡ������̨
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