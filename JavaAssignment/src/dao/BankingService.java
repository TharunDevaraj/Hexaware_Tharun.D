package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Account;
import exception.AccountNotFoundException;
import exception.CustomerNotFoundException;

public class BankingService {
	
	Scanner sc;
	ImpBankServiceDAO bankDAO;
	
	public BankingService()
	{
		sc=new Scanner(System.in);
		
		bankDAO=new ImpBankServiceDAO();
	}
	
	public void createAccount()
	{
		System.out.print("Enter Account Number: ");
		long accId=sc.nextLong();
		System.out.print("Enter Customer Number: ");
		long custId=sc.nextLong();
		System.out.print("Enter Account Type: ");
		sc.nextLine();
	    String accType=sc.nextLine();
	    
	    Account account=new Account(accId,custId,accType,1000);
	    
	    try
	    {
	    	if(bankDAO.createAccount(account)) {
				System.out.println("\nAccount created successfully\n");
			} else {
				System.out.println("\nAccount creation failed\n");
			}
	    }
	    catch(CustomerNotFoundException e)
	    {
	    	System.out.println("\nException: "+e.getMessage());
	    }
	}
	
	public void listAccounts()
	{
		List<Account>accounts=new ArrayList<>();
		try 
		{
			accounts=bankDAO.listAccounts();
			if(accounts.isEmpty())
			{
				System.out.println("\nNo accounts");
			}
			else {
				System.out.println("\nList of Accounts:");
				for(Account acc:accounts)
				{
					acc.printAccountDetails();
					System.out.println();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}
	
	public void deposit()
	{
		System.out.print("Enter account no to deposit: ");
		long accId=sc.nextLong();
		System.out.print("Enter amount:");
		float amount=sc.nextFloat();
		
		try
		{
			float balance=bankDAO.deposit(accId, amount);
			if(balance!=-1)
			{
				System.out.println("\nDeposited successfully, Account balance: "+balance);
			}
			else {
				System.out.println("\nDeposit failed");
			}
		}
		catch(AccountNotFoundException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}
	
	public void withdraw()
	{
		System.out.print("Enter account no to withdraw: ");
		long accId=sc.nextLong();
		System.out.print("Enter amount:");
		float amount=sc.nextFloat();
		
		try
		{
			float balance=bankDAO.withdraw(accId, amount);
			if(balance!=-1)
			{
				System.out.println("\nWithdrawal successful, Account balance: "+balance);
			}
			else {
				System.out.println("\nWithdrawal failed");
			}
		}
		catch(AccountNotFoundException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}
	
	public void transfer()
	{
		System.out.print("Enter sender account no: ");
		long fromAccId=sc.nextLong();
		System.out.print("Enter receiver account no: ");
		long toAccId=sc.nextLong();
		System.out.print("Enter amount:");
		float amount=sc.nextFloat();
		
		try
		{
			if(bankDAO.transfer(fromAccId,toAccId, amount))
			{
				System.out.println("\nAmount Transfer successful");
			}
			else 
			{
				System.out.println("\nAmount Transfer failed");
			}
		}
		catch(AccountNotFoundException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}

	public void calculateInterest()
	{
		System.out.print("Enter account no to calculate interest: ");
		long accId=sc.nextLong();
		
		try {
			if(!bankDAO.calculateInterest(accId))
			{
				System.out.println("Interest can be calculated only for savings account");
			}
		}
		catch(AccountNotFoundException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}

}
