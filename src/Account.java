import java.util.ArrayList;
import java.util.List;

import org.junit.rules.ExpectedException;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	private final int accountType;
	public List<Transaction> transactions;

	public ExpectedException thrown = ExpectedException.none();

	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	// 存款,需对存款数<=0的情况进行异常处理,异常详情参考测试用例
	public void deposit(double amount) {
		if (amount <= 0) {
			thrown.expect(IllegalArgumentException.class);
			thrown.expectMessage("amount must be greater than zero");
		}
		Transaction transaction = new Transaction(amount);
		transactions.add(transaction);
	}

	// 取款,需对取款数和账户余额<=0的情况分别进行异常处理,异常详情参考测试用例
	public void withdraw(double amount) {
		if (amount <= 0) {
			thrown.expect(IllegalArgumentException.class);
			thrown.expectMessage("amount must be greater than zero");
		}
		if (sumTransactions() <= Math.abs(amount)) {
			thrown.expect(IllegalArgumentException.class);
			thrown.expectMessage("sumTransactions must be greater than zero");
		}

		Transaction transaction = new Transaction(-amount);
		transactions.add(transaction);
	}

	// 根据不同的账号类型，确定不同利率进行利息计算
	public double interestEarned() {
		double amount = sumTransactions();
		if (getAccountType() == 0) {
			amount = amount * 0.001;
		} else if (getAccountType() == 1) {
			if (sumTransactions() <= 1000) {
				amount = amount * 0.001;
			} else {
				amount = (amount - 1000) * 0.002 + 1000 * 0.001;
			}
		} else if (getAccountType() == 2) {
			if (sumTransactions() <= 1000) {
				amount = amount * 0.02;
			} else if (sumTransactions() <= 2000) {
				amount = (amount - 1000) * 0.05 + 1000 * 0.02;
			} else if (sumTransactions() > 2000) {
				amount = (amount - 2000) * 0.1 + 1000 * 0.05 + 1000 * 0.02;
			}
		}
		return amount;
	}

	// 返回账号余额
	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction transaction : transactions) {
			amount += transaction.amount;
		}
		return amount;
	}

	public int getAccountType() {
		return accountType;
	}

}
