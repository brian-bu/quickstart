package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;
/**
 * 
 * ��̬��˳��������
 * ������������ķ�����transferMoney����
 * ��˳��ȡ���ڴ��ݸ�transferMoney�Ĳ���˳�򣬶���Щ����˳����ȡ�����ⲿ����
 * ��������߳�ͬʱ����transferMoney������һ���̴߳�X��Yת�ˣ���һ����Y��Xת�ˣ��ͻᷢ������
 *
 */
public class DynamicDeadLocking {
	public void transferMoney(Account fromAccount, Account toAccount, DollarAmount amount) throws InsufficientFundsException{
		synchronized (fromAccount) {
			synchronized (toAccount) {
				if (fromAccount.getBalance().compareTo(amount) < 0)
					throw new InsufficientFundsException();
				else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}
			}
		}
	}
}

class Balance implements Comparable<DollarAmount> {

	@Override
	public int compareTo(DollarAmount o) {
		return 0;
	}
}

class Account {

	private Balance bal;

	public Balance getBalance() {
		return bal;
	}

	public void credit(DollarAmount amount) {
		
	}

	public void debit(DollarAmount amount) {
		
	}

}

class DollarAmount {

}

class InsufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}