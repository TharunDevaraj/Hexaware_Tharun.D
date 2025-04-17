package dao;

import java.util.List;
import java.util.Scanner;

import exceptions.IncidentNumberNotFoundException;
import exceptions.InvalidDateException;
import exceptions.OfficerIdNotFoundException;
import exceptions.SuspectIdNotFoundException;
import exceptions.VictimIdNotFoundException;
import model.Incident;
import model.Officer;
import model.Report;
import model.Suspect;
import model.Victim;

public class CrimeAnalysisService {

	Scanner sc;
	
	ImpCrimeAnalysisService impcrime;
	
	public CrimeAnalysisService()
	{
		sc=new Scanner(System.in);
		
		impcrime=new ImpCrimeAnalysisService();
	}
	
	public void createIncident() 
	{
		System.out.print("Enter Incident Id: ");
		int incidentId=sc.nextInt();
		System.out.print("Enter Incident Type: ");
		sc.nextLine();
		String incidentType=sc.nextLine();
		System.out.print("Enter Incident Date(yyyy-mm-dd): ");
		String incidentDate=sc.nextLine();
		System.out.print("Enter Location: ");
		String location=sc.nextLine();
		System.out.print("Enter Description: ");
		String description=sc.nextLine();
		System.out.print("Enter Status: ");
		String status=sc.nextLine();
		System.out.print("Enter Victim Id: ");
		int victimId=sc.nextInt();
		System.out.print("Enter Suspect Id: ");
		int suspectId=sc.nextInt();
		System.out.print("Enter Officer Id: ");
		int officerId=sc.nextInt();
		
		Incident incident=new Incident(incidentId,incidentType,incidentDate,location,description,status,victimId,suspectId,officerId);
		boolean flag=false;
		try 
		{
			flag=impcrime.createIncident(incident);
			
		}
		catch (InvalidDateException e) 
		{
		    System.out.println("\nException: "+e.getMessage());
		}
		catch (VictimIdNotFoundException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		catch (SuspectIdNotFoundException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		catch (OfficerIdNotFoundException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("\nException: "+e.getMessage());
		}

		if(!flag)
		{
			System.out.println("\nIncident addition failed\n");
		}
		else
		{
			System.out.println("\nIncident added successfully\n");
		}
	
	}
	
	public void updateIncident()
	{
		System.out.print("Enter incident id to update: ");
		int incidentId=sc.nextInt();
		System.out.print("Enter status of incident: ");
		sc.nextLine();
		String status=sc.nextLine();
		try 
		{
			if(impcrime.updateIncident(status, incidentId))
			{
				System.out.println("\nStatus of Incident Id:"+incidentId+" updated successfully\n");
			}
			else 
			{
				System.out.println("\nStatus updation failed. Enter valid status\n");
			}
		} 
		catch (IncidentNumberNotFoundException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
		
	}
		
	public void getIncidentInDateRange()
	{
		System.out.print("Enter start date(yyyy-mm-dd): ");
		String startDate=sc.nextLine();
		System.out.print("Enter end date(yyyy-mm-dd): ");
		String endDate=sc.nextLine();
		try
		{
	        List<Incident>result=impcrime.getIncidentInDateRange(startDate,endDate);
	        if(result.isEmpty())
	        {
	        	System.out.println("\nNo incident between "+startDate+" and "+endDate);
	        }
	        else
	        {
	        	System.out.println("\nIncident between "+startDate+" and "+endDate+":\n");
	        	for(Incident incident:result)
	        	{
	        		System.out.println(incident.toString());
	        	}
	        }
		}
	    catch (InvalidDateException e) 
		{
		    System.out.println("\nException: " + e.getMessage());
		}
	}
	
	public void searchIncidents()
	{
		System.out.print("Enter incident type to search: ");
		String incidentType=sc.nextLine();
		try 
		{
			List<Incident>result=impcrime.searchIncident(incidentType);
			if(!result.isEmpty())
			{
				System.out.println("\nIncidents of incident type - "+incidentType+":\n");
			    for(Incident incident : result)
			    {
			    	System.out.println(incident.toString());
			    }
			}
			else 
			{
				System.out.println("\nNo incident of incident type:"+incidentType);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("\nException: " + e.getMessage());
		}
		
	}
	
	public void generateIncidentReport()
	{
		System.out.print("Enter incident id to generate report: ");
		int incidentId=sc.nextInt();
		System.out.print("Enter report id: ");
		int reportid=sc.nextInt();
		try 
		{
			Report report=impcrime.generateIncidentReport(incidentId,reportid);
			if(report!=null)
			{
				System.out.println("\nReport Generated with Report Id:"+reportid);
				System.out.println(report.toString());
			}
			else 
			{
				System.out.println("\nReport Generation failed");
			}
		} 
		catch (IncidentNumberNotFoundException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
	}
	
	public void addVictim()
	{
		System.out.print("\nEnter victim id: ");
		int victimId=sc.nextInt();
		System.out.print("Enter victim first name: ");
		sc.nextLine();
		String fname=sc.nextLine();
		System.out.print("Enter victim last name: ");
		String lname=sc.nextLine();
		System.out.print("Enter victim date of birth: ");
		String dateOfBirth=sc.nextLine();
		System.out.print("Enter victim gender: ");
		String gender=sc.nextLine();
		System.out.print("Enter victim contact info: ");
		String contact=sc.nextLine();
			
		Victim vic=new Victim(victimId,fname,lname,dateOfBirth,gender,contact);
			
		impcrime.addVictim(vic);
			
	}
	
	public void addSuspect()
	{
		System.out.print("\nEnter suspect id: ");
		int suspectId=sc.nextInt();
		System.out.print("Enter suspect first name: ");
		String fname=sc.nextLine();
		System.out.print("Enter suspect last name: ");
		String lname=sc.nextLine();
		System.out.print("Enter suspect date of birth: ");
		String dateOfBirth=sc.nextLine();
		System.out.print("Enter suspect gender: ");
		String gender=sc.nextLine();
		System.out.print("Enter suspect contact info: ");
		String contact=sc.nextLine();
		
		Suspect sus=new Suspect(suspectId,fname,lname,dateOfBirth,gender,contact);
		
		impcrime.addSuspect(sus);
	}
	
	public void addOfficer()
	{
		System.out.print("\nEnter officer id: ");
		int officerId=sc.nextInt();
		System.out.print("Enter officer first name: ");
		String fname=sc.nextLine();
		System.out.print("Enter officer last name: ");
		String lname=sc.nextLine();
		System.out.print("Enter officer bandge num: ");
		String badgeNum=sc.nextLine();
		System.out.print("Enter officer rank: ");
		String rank=sc.nextLine();
		System.out.print("Enter officer contact info: ");
		String contact=sc.nextLine();
		System.out.print("Enter agency id: ");
		int agencyId=sc.nextInt();
		
		if(impcrime.isAgencyAvailable(agencyId))
		{
			Officer off=new Officer(officerId,fname,lname,badgeNum,rank,contact,agencyId);
		
			impcrime.addOfficer(off);
		}
		else
		{
			System.out.println("No agency with agency id: "+agencyId+". Enter valid agency id");
			return;
		}
	}
}
