package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Account;
import exception.AccountNotFoundException;
import exception.CustomerNotFoundException;
import util.DataConnect;

public class ImpBankServiceDAO {

	private static Connection con;
	private PreparedStatement stat;
	private ResultSet set;
	
	public ImpBankServiceDAO()
	{
		con=DataConnect.getCon();
	}
	
	private boolean isCustomerAvailable(long custId)
	{
		try
		{
			stat=con.prepareStatement("select * from customers where cust_id=?");
			stat.setLong(1, custId);
			set=stat.executeQuery();
			if(set.next())
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Exception: "+e.getMessage());
		}
		return false;
	}
	
	private boolean isAccountAvailable(long accId)
	{
		try
		{
			stat=con.prepareStatement("select * from accounts where acc_id=?");
			stat.setLong(1, accId);
			set=stat.executeQuery();
			if(set.next())
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Exception: "+e.getMessage());
		}
		return false;
	}
	
	public boolean createAccount(Account acc) throws CustomerNotFoundException
	{
		try 
		{
			if(!isCustomerAvailable(acc.getCustId()))
			{
				throw new CustomerNotFoundException("Customer Id:"+acc.getCustId()+" not found");
			}
			stat=con.prepareStatement("insert into accounts values(?,?,?,?)");
			stat.setLong(1, acc.getAccId());
			stat.setLong(2, acc.getCustId());
			stat.setString(3, acc.getAccType());
			stat.setFloat(4, acc.getBalance());
			stat.executeUpdate();
			return true;
		} 
		catch (SQLException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return false;
	}
	
	public List<Account> listAccounts() {
        List<Account> list = new ArrayList<>();
        try {
            stat = con.prepareStatement("SELECT * FROM accounts");
            set = stat.executeQuery();
            while (set.next()) {
                list.add(new Account(set.getLong(1), set.getLong(2), set.getString(3), set.getFloat(4)));
            }
        } catch (SQLException e) {
            System.out.println("\nException: " + e.getMessage());
        }
        return list;
    }
	
	public float deposit(long accNo, float amount) throws AccountNotFoundException {
        try 
        {
        	if(!isAccountAvailable(accNo))
        	{
        		throw new AccountNotFoundException("Account Id:"+accNo+" not found");
        	}
        	
            float currentBalance = getAccountBalance(accNo);
            float newBalance = currentBalance + amount;

            stat = con.prepareStatement("UPDATE accounts SET balance=? WHERE acc_id=?");
            stat.setFloat(1, newBalance);
            stat.setLong(2, accNo);
            stat.executeUpdate();

            addTransaction(accNo, "Deposit", amount);
            return newBalance;

        } 
        catch (SQLException e) 
        {
            System.out.println("Exception: " + e.getMessage());
        }
        return -1;
    }
	
	public float withdraw(long accNo, float amount) throws AccountNotFoundException {
        try {
        	if(!isAccountAvailable(accNo))
        	{
        		throw new AccountNotFoundException("Account Id:"+accNo+" not found");
        	}
        	
            float currentBalance = getAccountBalance(accNo);

            if (currentBalance - amount < 500) {
                System.out.println("\nInsufficient funds!");
                return -1;
            }

            float newBalance = currentBalance - amount;

            stat = con.prepareStatement("UPDATE accounts SET balance=? WHERE acc_id=?");
            stat.setFloat(1, newBalance);
            stat.setLong(2, accNo);
            stat.executeUpdate();

            addTransaction(accNo, "Withdraw", amount);
            return newBalance;
        } 
        catch (SQLException e) 
        {
            System.out.println("Exception: " + e.getMessage());
        }
        return -1;
    }
	
	public boolean transfer(long fromAcc, long toAcc, float amount) throws AccountNotFoundException {
        try 
        {
        	if(!isAccountAvailable(fromAcc))
        	{
        		throw new AccountNotFoundException("Account Id:"+fromAcc+" not found");
        	}
        	if(!isAccountAvailable(toAcc))
        	{
        		throw new AccountNotFoundException("Account Id:"+toAcc+" not found");
        	}
        	
        	
            float fromBal = getAccountBalance(fromAcc);
            float toBal = getAccountBalance(toAcc);

            if(fromBal-amount<500)
            {
            	System.out.println("\nMinimum balance of 500 should be maintained");
                return false;
            }

            con.setAutoCommit(false);
            stat = con.prepareStatement("UPDATE accounts SET balance=? WHERE acc_id=?");
            stat.setFloat(1, fromBal - amount);
            stat.setLong(2, fromAcc);
            stat.executeUpdate();
            stat = con.prepareStatement("UPDATE accounts SET balance=? WHERE acc_id=?");
            stat.setFloat(1, toBal + amount);
            stat.setLong(2, toAcc);
            stat.executeUpdate();
            addTransaction(fromAcc, "Transfer to " + toAcc, amount);
            con.commit();
            con.setAutoCommit(true);
            return true;
        } 
        catch (SQLException e) 
        {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback Error: " + ex.getMessage());
            }
            System.out.println("Transfer Exception: " + e.getMessage());
        }
        return false;
    }
	
	public boolean calculateInterest(long accId) throws AccountNotFoundException {
        try 
        {
        	if(!isAccountAvailable(accId))
        	{
        		throw new AccountNotFoundException("Account Id:"+accId+" not found");
        	}
        	
            stat = con.prepareStatement("SELECT * FROM accounts WHERE acc_type='Savings' and acc_id=?");
            stat.setLong(1, accId);
            set = stat.executeQuery();
            if (set.next()) {
                float bal = set.getFloat("balance");
                float interest = (bal * 0.04f); 
                System.out.println("\nCalculated interest for account:" + accId+" : "+interest);
                return true;
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Exception: " + e.getMessage());
        }
        return false;
    }

	
	private float getAccountBalance(long accNo) throws SQLException {
        stat = con.prepareStatement("SELECT balance FROM accounts WHERE acc_id=?");
        stat.setLong(1, accNo);
        set = stat.executeQuery();
        if (set.next()) {
            return set.getFloat(1);
        }
        return 0;
    }
	
	private void addTransaction(long accNo, String transType, float amount) {
	    try 
	    {
	        stat = con.prepareStatement("SELECT MAX(trans_id) FROM transactions");
	        ResultSet rs = stat.executeQuery();
	        int transId = 1; 

	        if (rs.next()) {
	            transId = rs.getInt(1) + 1;
	        }
	        
	        Date currentDate = new Date(System.currentTimeMillis());

	        stat = con.prepareStatement("INSERT INTO transactions VALUES (?, ?, ?, ?, ?)");
	        stat.setInt(1, transId);
	        stat.setLong(2, accNo);
	        stat.setString(3, transType);
	        stat.setFloat(4, amount);
	        stat.setDate(5, currentDate); 
	        stat.executeUpdate();

	    } 
	    catch (SQLException e) 
	    {
	        System.out.println("Exception: " + e.getMessage());
	    }
	}

}
