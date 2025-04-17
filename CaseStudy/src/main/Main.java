package main;

import java.util.Scanner;

import dao.CrimeAnalysisService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CrimeAnalysisService serv=new CrimeAnalysisService();
		Scanner sc=new Scanner(System.in);
		int choice;
		
		do
		{
			System.out.println("\nCrime Analysis Reporting System\n1. Create Incident\n2. Update status of incident\n3. Get incidents within a date range\n4. Search incidents based on incident type\n5. Generate Report\n6. Exit\n");
			System.out.print("Enter your choice: ");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				serv.createIncident();
				break;
			case 2:
				serv.updateIncident();
				break;
			case 3:
				serv.getIncidentInDateRange();
				break;
			case 4:
				serv.searchIncidents();
				break;
			case 5:
				serv.generateIncidentReport();
				break;
			case 6:
				System.out.println("Exit!");
				break;
			default:
				System.out.println("Invalid choice, enter valid number\n");
			}
			
		}
		while(choice!=6);
		
		sc.close();
	}

}
