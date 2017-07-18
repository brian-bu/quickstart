package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;

/**
 * 
 * 既然锁的顺序容易导致死锁，那么在制定锁的顺序的时候，可以考虑使用System.identityHashCode来定义锁的顺序
 * 本例是改进版的transferMoney，可以有效防止transferMoney产生的死锁。原版见：
 * 
 * @see net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking.DynamicDeadLocking.transferMoney(Account,
 *      Account, DollarAmount)
 */
public class FormationOfLockSeqToAvoidDeadLocking {
	private static final Object tieLock = new Object();

	public void transferMoney(final Account fromAcct, final Account toAcct, final DollarAmount amount)
			throws InsufficientFundsException {
		class Helper {
			public void transfer() throws InsufficientFundsException {
				if (fromAcct.getBalance().compareTo(amount) < 0)
					throw new InsufficientFundsException();
				else {
					fromAcct.debit(amount);
					toAcct.credit(amount);
				}
			}
		}
		
		int fromHash = System.identityHashCode(fromAcct);
		int toHash = System.identityHashCode(toAcct);
		
		if (fromHash < toHash) {
			synchronized (fromAcct) {
				synchronized (toAcct) {
					new Helper().transfer();
				}
			}
		} else if (fromHash > toHash) {
			synchronized (toAcct) {
				synchronized (fromAcct) {
					new Helper().transfer();
				}
			}
		} else {
			synchronized (tieLock) {
				synchronized (fromAcct) {
					synchronized (toAcct) {
						new Helper().transfer();
					}
				}
			}
		}
	}
}
