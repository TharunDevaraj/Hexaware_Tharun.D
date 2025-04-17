package main;

import java.util.Scanner;

import dao.HospitalService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		HospitalService service=new HospitalService();
		int choice;
		
		do 
		{
			System.out.println("\nHospital Management System\n1. Get appointment by id\n2. Get appointments for patient\n3. Get appointments for doctor\n4. Schedule appointment\n5. Update appointment details\n6. Cancel appointment\n7. Exit\n");
			System.out.print("Enter your choice: ");
			choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
				service.getAppointmentById();
				break;
			case 2:
				service.getAppointmentsforPatient();
				break;
			case 3:
				service.getAppointmentsforDoctor();
				break;
			case 4:
				service.scheduleAppointment();
				break;
			case 5:
				service.updateAppointment();
				break;
			case 6:
				service.cancelAppointment();
				break;
			case 7:
				System.out.println("\nExit");
				break;
			default:
				System.out.println("\nInvalid choice, enter valid choice\n");
			}
				
		} 
		while (choice!=7);
	}

}
