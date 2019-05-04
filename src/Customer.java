

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }
    
    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }
    
    public int getNumberOfAccounts() {
        return accounts.size();
    }
    //计算客户各账户的总利率
    public double totalInterestEarned() {
        double total = 0;
        for (Account account : accounts) {
			total+=account.interestEarned();
		}
        return total;
    }
    
    //获取所有账户详情
    public String getStatement() {
        String statement = "";
        /**
         * "Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  withdrawal $50.00\n" +
                "Total $50.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Maxi Savings Account\n" +
                "  deposit $500.00\n" +
                "Total $500.00\n" +
                "\n" +
                "Total In All Accounts $4,350.00"
         */
        statement+="Statement for "+name+"\n";
        Double totalDouble=0.0;       
        for (Account account : accounts) {
		if(account.getAccountType()==0){
			 statement+="\n"+
					 "Checking Account\n";
			 for (Transaction transaction : account.transactions) {
				if(transaction.amount>0){
					statement+="  deposit "+toDollars(transaction.amount)+"\n";
				}else {
					statement+="  withdrawal "+toDollars(Math.abs(transaction.amount))+"\n";
				}
			}
			statement+="Total "+toDollars(account.sumTransactions())+"\n";
			totalDouble+=account.sumTransactions();
		}else if(account.getAccountType()==1){
			statement+="\n"+
					 "Savings Account\n";
			 for (Transaction transaction : account.transactions) {
					if(transaction.amount>0){
						statement+="  deposit "+toDollars(transaction.amount)+"\n";
					}else {
						statement+="  withdrawal "+toDollars(Math.abs(transaction.amount))+"\n";
					}
				}
				statement+="Total "+toDollars(account.sumTransactions())+"\n";
				totalDouble+=account.sumTransactions();
		}
		else if(account.getAccountType()==2){
			statement+="\n"+
					 "Maxi Savings Account\n";
			 for (Transaction transaction : account.transactions) {
					if(transaction.amount>0){
						statement+="  deposit "+toDollars(transaction.amount)+"\n";
					}else {
						statement+="  withdrawal "+toDollars(Math.abs(transaction.amount))+"\n";
					}
				}
				statement+="Total "+toDollars(account.sumTransactions())+"\n";
				totalDouble+=account.sumTransactions();
		}
	}
       statement+="\nTotal In All Accounts "+toDollars(totalDouble);

        return statement;
    }
        
    //格式化金额输出，无需修改,可直接引用
    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
