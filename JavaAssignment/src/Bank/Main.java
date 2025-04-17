package Bank;

import java.util.Scanner;

import dao.BankingService;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		BankingService serv=new BankingService();
		
		int choice;
		
		do 
		{
	            System.out.println("\nBanking System");
	            System.out.println("1. Create Account");
	            System.out.println("2. Deposit");
	            System.out.println("3. Withdraw");
	            System.out.println("4. Transfer");
	            System.out.println("5. List Accounts");
	            System.out.println("6. Calculate Interest");
	            System.out.println("7. Exit");
	            System.out.print("\nChoose an option: ");
	            choice = sc.nextInt();
	            
	            switch(choice)
	            {
	            case 1:
	            	serv.createAccount();
	            	break;
	            case 2:
	            	serv.deposit();
	            	break;
	            case 3:
	            	serv.withdraw();
	            	break;
	            case 4:
	            	serv.transfer();
	            	break;
	            case 5:
	            	serv.listAccounts();
	            	break;
	            case 6:
	            	serv.calculateInterest();
	            	break;
	            case 7:
	            	System.out.println("\nBanking System Exit");
	            	break;
	            default:
	            	System.out.println("Invalid choice, enter a valid choice");
	            }
		}
		while(choice!=7);

	}

}
