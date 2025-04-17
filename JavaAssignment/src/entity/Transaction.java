package entity;

import java.sql.Date;

public class Transaction {

	private long transId;
	private long accId;
	private String transType;
	private float amount;
	private Date transDate;
	
	public Transaction()
	{
		
	}

	public Transaction(long transId, long accId, String transType, float amount, Date transDate) {
		super();
		this.transId = transId;
		this.accId = accId;
		this.transType = transType;
		this.amount = amount;
		this.transDate = transDate;
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	
	public void printTransactionDetails() {
		System.out.println("Transaction No: " + transId);
        System.out.println("Account No: " + accId);
        System.out.println("Transaction Type: " + transType);
        System.out.println("Amount: " + amount);
        System.out.println("Transaction Date: " + transDate);
    }
}
