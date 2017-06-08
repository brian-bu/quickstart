package net.brian.coding.java.core.jdk.newfeatures;

/**
 * 
 * �ص�������һ�ֳ��������ģʽ�����ѹ������ڵ�ĳ�����̷�������Լ���Ľӿڱ�¶���ⲿʹ����
 * Ϊ�ⲿʹ�����ṩ���ݣ���Ҫ���ⲿʹ�����ṩ���ݡ�
 * 
 * �ӵ��÷�ʽ�ϣ����԰����Ƿ�Ϊ���ࣺͬ�����á��ص����첽���á�
 * ͬ�����ã�һ������ʽ���ã����÷�Ҫ�ȴ��Է�ִ����ϲ��ܷ��أ�����һ�ֵ�����ã�
 * �ص���һ��˫�����ģʽ��Ҳ����˵�������÷��ڽӿڱ�����ʱҲ����öԷ��Ľӿ�
 * �첽���ã�һ��������Ϣ���¼��Ļ��ƣ��������ĵ��÷���պ��෴
 * �ӿڵķ������յ�ĳ��ѶϢ����ĳ���¼�ʱ��������֪ͨ�ͻ����������ÿͻ����Ľӿڣ���
 * �ص����첽���õĹ�ϵ�ǳ����ܣ�ʹ�ûص���ʵ���첽��Ϣ��ע�ᣬͨ���첽������ʵ����Ϣ��֪ͨ��
 * 
 * ����һ����ͨ�Ļص�����ģ��ģʽ�����ӣ� 
 * �ص����ַ�ʽ������ģ��ģʽ�̳г�����ķ�ʽ����˸��Ӽ�����
 * ����ҪΪ��ʵ�ֳ��󷽷������Ǽ̳г����࣬����ֻ��Ҫͨ���ص�������һ����������
 * 
 * ע�⣺A������Ϣ��B��B�����AҪ�������󣬽�������ظ�A��A�ٶ�B���صĽ��������һ���Ĵ���
 *
 */
public class CallbackBetterThanTemplate implements CallBack, Runnable {

	/**
	 * Զ�̽�����Ϣ���࣬ģ��point-to-point
	 */
	private Remote remote;
	private String message;

	public CallbackBetterThanTemplate(Remote remote, String message) {
		super();
		this.remote = remote;
		this.message = message;
	}

	public void sendMessage() {
		System.out.println(Thread.currentThread().getName());
		Thread thread = new Thread(this);
		thread.start();
		System.out.println("Message has been sent by Local~!");
	}
	
	@Override
	public void execute(String msg) {
		System.out.println(msg);
		System.out.println(Thread.currentThread().getName());
		Thread.interrupted();
	}

	public static void main(String[] args) {
		CallbackBetterThanTemplate local = new CallbackBetterThanTemplate(new Remote(), "Hello");
		local.sendMessage();
	}

	@Override
	public void run() {
		// ������ﲻ����this��������������CallBack�ӿڵ�ʵ����Ļ����ǾͲ��ܳ�֮Ϊ���ص�����
		// ��OO��������Ǿ����ڡ�ί�ɡ���Ҳ����˵�����ص�����������Ϣ�ķ�������������Ϣ���
		remote.executeMessage(message, this);
	}
}

interface CallBack {
	/**
	 * �ص������������漸�����ԣ�
	 * ���ڹ�������һ�����֣�
	 * ���밴�չ�����ָ���ĵ���Լ�������������壩��
	 * ���ĵ���ʱ���ɹ������������ص�������ʵ���߲���ֱ�ӵ��ûص�������ʵ�ֹ������Ĺ��ܣ�
	 * 
	 * @param msg
	 */
	void execute(String msg);
}

class Remote {
	public void executeMessage(String msg, CallBack callBack) {
		/** ģ��Զ�������ڴ����������飬������Ҫ�������ʱ�� **/
		for (int i = 0; i < 1000000000; i++) {

		}
		System.out.println("I hava executed the message by Local");
		/** ִ�лص� **/
		callBack.execute(msg);
	}
}