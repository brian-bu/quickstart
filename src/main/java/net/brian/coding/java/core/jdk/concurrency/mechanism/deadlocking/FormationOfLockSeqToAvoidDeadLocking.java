package net.brian.coding.java.core.jdk.concurrency.mechanism.deadlocking;

/**
 * 
 * ��Ȼ����˳�����׵�����������ô���ƶ�����˳���ʱ�򣬿��Կ���ʹ��System.identityHashCode����������˳��
 * �����ǸĽ����transferMoney��������Ч��ֹtransferMoney������������ԭ�����
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
