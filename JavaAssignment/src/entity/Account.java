package entity;

public class Account {

	private long accId;
	private long custId;
    private String accType;
    private float balance;
    
    public Account()
    {
    	
    }

	public Account(long accId, long custId, String accType, float balance) {
		super();
		this.accId = accId;
		this.custId = custId;
		this.accType = accType;
		this.balance = balance;
	}

	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
    
	public void printAccountDetails() {
        System.out.println("Account No: " + accId);
        System.out.println("Customer No: "+ custId);
        System.out.println("Account Type: " + accType);
        System.out.println("Balance: " + balance);
    }
}
