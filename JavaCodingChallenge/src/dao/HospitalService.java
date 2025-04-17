package dao;

import java.util.List;
import java.util.Scanner;

import entity.Appointment;
import exception.AppointmentNotFoundException;
import exception.DoctorNumberNotFoundException;
import exception.PatientNumberNotFoundException;

public class HospitalService {

	Scanner sc;
	
	ImpHospitalService hospServ;
	
	public HospitalService()
	{
		sc=new Scanner(System.in);
		hospServ=new ImpHospitalService();
	}
	
	public void getAppointmentById()
	{
		System.out.print("Enter appointment id: ");
		int appointmentId=sc.nextInt();
		
		try 
		{
		    Appointment appointment=hospServ.getAppointmentById(appointmentId);
		    System.out.println("\nAppointment found:\n" + appointment.toString());
		} 
		catch (AppointmentNotFoundException e) 
		{
		    System.out.println("\nException: "+e.getMessage());
		}
	}
	
	public void getAppointmentsforPatient()
	{
		System.out.print("Enter patient id to get appointments: ");
		int patiendId=sc.nextInt();
		
		try 
		{
		    List<Appointment> appointments = hospServ.getAppointmentsForPatient(patiendId);
		    System.out.println("\nAppointments of patient id:"+patiendId+":\n");
		    for(Appointment app : appointments)
		    {
		    	System.out.println(app.toString());
		    }
		} 
		catch (PatientNumberNotFoundException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}
	
	public void getAppointmentsforDoctor()
	{
		System.out.print("Enter doctor id to get appointments: ");
		int doctorId=sc.nextInt();
		
		try 
		{
		    List<Appointment> appointments = hospServ.getAppointmentsForDoctor(doctorId);
		    System.out.println("\nAppointments of doctor id:"+doctorId+":\n");
		    for(Appointment app : appointments)
		    {
		    	System.out.println(app.toString());
		    }
		} 
		catch (DoctorNumberNotFoundException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}
	
	public void scheduleAppointment()
	{
		System.out.print("Enter appointment id: ");
		int appointmentId=sc.nextInt();
		System.out.print("Enter patient id: ");
		int patientId=sc.nextInt();
		System.out.print("Enter doctor id: ");
		int doctortId=sc.nextInt();
		System.out.print("Enter appointment date: ");
		sc.nextLine();
		String appointmentDate=sc.nextLine();
		System.out.print("Enter description: ");
		String description=sc.nextLine();
		
		Appointment appointment=new Appointment(appointmentId,patientId,doctortId,appointmentDate,description);
		
		try 
		{
			if(hospServ.scheduleAppointment(appointment))
			{
				System.out.println("\nAppointment scheduled successfully");
			}
			else 
			{
				System.out.println("\nAppointment scheduling failed");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}
	
	public void updateAppointment()
	{
		System.out.println("Enter appointment details to update:\n");
		System.out.print("Enter appointment id: ");
		int appointmentId=sc.nextInt();
		System.out.print("Enter patient id: ");
		int patientId=sc.nextInt();
		System.out.print("Enter doctor id: ");
		int doctortId=sc.nextInt();
		System.out.print("Enter appointment date: ");
		sc.nextLine();
		String appointmentDate=sc.nextLine();
		System.out.print("Enter description: ");
		String description=sc.nextLine();
		
		Appointment appointment=new Appointment(appointmentId,patientId,doctortId,appointmentDate,description);
		
		try 
		{
			if(hospServ.updateAppointment(appointment))
			{
				System.out.println("\nAppointment updated successfully");
			}
			else 
			{
				System.out.println("\nAppointment updation failed");
			}
		} 
		catch (AppointmentNotFoundException e) 
		{
			System.out.println("\nException: "+e.getMessage());
			System.out.println("\nAppointment updation failed");
		}
	}
	
	public void cancelAppointment()
	{
		System.out.print("Enter appointment id to cancel appointment: ");
		int appointmentId=sc.nextInt();
		
		try 
		{
			if(hospServ.cancelAppointment(appointmentId))
			{
				System.out.println("\nAppointment cancelled successfully");
			}
			else 
			{
				System.out.println("\nAppointment calcellation failed");
			}
		} 
		catch (AppointmentNotFoundException e) 
		{
			System.out.println("\nException: "+e.getMessage());
			System.out.println("\nAppointment updation failed");
		}
	}
}
