package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;
/**
 * 
 * 动态锁顺序死锁：
 * 这里产生死锁的方法是transferMoney方法
 * 锁顺序取决于传递给transferMoney的参数顺序，而这些参数顺序又取决于外部输入
 * 如果两个线程同时调用transferMoney，其中一个线程从X向Y转账，另一个从Y向X转账，就会发生死锁
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