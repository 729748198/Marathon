

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    //返回固定格式的客户列表,格式参考测试用例
    public String customerSummary() {
        String summary = "Customer Summary\n";
        //格式控制  第一次不加回车，第二次开始在开头加一个回车，这样能避免在末尾多加空格
        boolean flag=true;
        for (Customer customer : customers) {

          	String conuntString=customer.getNumberOfAccounts()>1?"accounts":"account";

        	if (flag) {
    			summary+=" - "+customer.getName()+" ("+customer.getNumberOfAccounts()+ " "+conuntString+")";
    			flag=false;
    			continue;
			}
			summary+="\n - "+customer.getName()+" ("+customer.getNumberOfAccounts()+ " "+conuntString+")";
		}
        
        //Customer Summary\n - John (1 account
        
        return summary;
    }
    
    //返回支付所有客户的利息总和
    public double totalInterestPaid() {
        double total = 0;
        for (Customer customer : customers) {
			total+= customer.totalInterestEarned();
		}
        return total;
    }

}
