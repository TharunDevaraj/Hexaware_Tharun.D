package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import util.DataConnect;

public class ImpCrimeAnalysisService {
	
	private static Connection con;
	
	private static PreparedStatement stat;
	
	private static ResultSet set;
		
	public ImpCrimeAnalysisService()
	{
		con=DataConnect.getCon();
	}
	
	private boolean isVictimAvailable(int victimid)
	{
		try
		{
			stat=con.prepareStatement("select * from victim where victimid=?");
			stat.setInt(1, victimid);
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
	
	public boolean isSuspectAvailable(int suspectid) 
	{
		try
		{
			stat=con.prepareStatement("select * from suspect where suspectid=?");
			stat.setInt(1, suspectid);
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
	
	public boolean isOfficerAvailable(int officerid)
	{
		try
		{
			stat=con.prepareStatement("select * from officer where officerid=?");
			stat.setInt(1, officerid);
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
	
	public boolean isAgencyAvailable(int agencyid) 
	{
	    try 
	    {
	        stat = con.prepareStatement("select * from agency where agencyid=?");
	        stat.setInt(1, agencyid);
	        set = stat.executeQuery();
	        return set.next();
	    } 
	    catch (Exception e) 
	    {
	    	System.out.println("Exception: "+e.getMessage());
	    }
	    return false;
	}

	
	public void addVictim(Victim vic)
	{
		try
		{
			stat=con.prepareStatement("insert into victim values(?,?,?,?,?,?)");
			stat.setInt(1, vic.getVictimId());
			stat.setString(2, vic.getFname());
			stat.setString(3, vic.getLname());
			stat.setString(4, vic.getDateOfBirth());
			stat.setString(5, vic.getGender());
			stat.setString(6, vic.getContact());
			stat.execute();
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+e.getMessage());
		}
	}
	
	public void addSuspect(Suspect sus)
	{
		try
		{
			stat=con.prepareStatement("insert into suspect values(?,?,?,?,?,?)");
			stat.setInt(1, sus.getSuspectId());
			stat.setString(2, sus.getFname());
			stat.setString(3, sus.getLname());
			stat.setString(4, sus.getDateOfBirth());
			stat.setString(5, sus.getGender());
			stat.setString(6, sus.getContact());
			stat.execute();
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+e.getMessage());
		}
	}
	
	public void addOfficer(Officer off)
	{
		try
		{
			stat=con.prepareStatement("insert into officer values(?,?,?,?,?,?,?)");
			stat.setInt(1, off.getOfficerId());
			stat.setString(2, off.getFname());
			stat.setString(3, off.getLname());
			stat.setString(4, off.getBadgeNum());
			stat.setString(5, off.getRank());
			stat.setString(6, off.getContact());
			stat.setInt(7, off.getAgencyId());
			stat.execute();
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+e.getMessage());
		}
	}
	
	public boolean createIncident(Incident i) throws VictimIdNotFoundException,SuspectIdNotFoundException,OfficerIdNotFoundException,InvalidDateException
	{
		try
		{
			if(!validateDate(i.getIncidentDate()))
			{
				throw new InvalidDateException("Invalid date format: " + i.getIncidentDate() + ". Use yyyy-mm-dd and a valid calendar date");
			}
			if(!isVictimAvailable(i.getVictimId()))
			{
				throw new VictimIdNotFoundException("Victim with victim id:"+i.getVictimId()+" not found");
			}
			if(!isSuspectAvailable(i.getSuspectId()))
			{
				throw new SuspectIdNotFoundException("Suspect with suspect id:"+i.getSuspectId()+" not found");
			}
			if(!isOfficerAvailable(i.getOfficerId()))
			{
				throw new OfficerIdNotFoundException("Officer with officer id:"+i.getOfficerId()+" not found");
			}
			stat=con.prepareStatement("insert into incident values(?,?,?,?,?,?,?,?,?)");
			stat.setInt(1, i.getIncidentId());
			stat.setString(2, i.getIncidentType());
			stat.setString(3, i.getIncidentDate());
			stat.setString(4, i.getLocation());
			stat.setString(5, i.getDescription());
			stat.setString(6, i.getStatus());
			stat.setInt(7, i.getVictimId());
			stat.setInt(8, i.getSuspectId());
			stat.setInt(9, i.getOfficerId());
			return stat.executeUpdate()>0;
		}
		catch(SQLException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return false;
	}
	
	public boolean updateIncident(String status,int incidentId) throws IncidentNumberNotFoundException
	{
		try
		{
			if(!(status.equalsIgnoreCase("open")||status.equalsIgnoreCase("closed")||status.equalsIgnoreCase("under investigation")))
			{
				return false;
			}
			stat=con.prepareStatement("update incident set status=? where incidentid=?");
			stat.setString(1, status);
			stat.setInt(2, incidentId);
			int c=stat.executeUpdate();
			if(c==0)
			{
				throw new IncidentNumberNotFoundException("Incident with incident id:"+incidentId+" not found");
			}
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return false;
	}
	
	public List<Incident> getIncidentInDateRange(String sDate,String eDate) throws InvalidDateException
	{
		List<Incident>incidents=new ArrayList<>();
		try
		{
			if(!validateDate(sDate))
			{
				throw new InvalidDateException("Invalid date format: " + sDate + ". Please use yyyy-MM-dd and a valid calendar date");
			}
			if(!validateDate(eDate))
			{
				throw new InvalidDateException("Invalid date format: " + eDate + ". Please use yyyy-MM-dd and a valid calendar date");
			}
			stat=con.prepareStatement("select * from incident where incidentdate between ? and ?");
			stat.setString(1, sDate);
			stat.setString(2, eDate);
			set=stat.executeQuery();
			while(set.next())
			{
				incidents.add(getIncident(set));
			}
		}
		catch(SQLException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return incidents;
	}
	
	public List<Incident> searchIncident(String incidentType)
	{
		List<Incident>incidents=new ArrayList<>();
		try
		{
			stat=con.prepareStatement("select * from incident where incidenttype=?");
			stat.setString(1, incidentType);
			set=stat.executeQuery();
			while(set.next())
			{
				incidents.add(getIncident(set));
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return incidents;
	}
	
	public Report generateIncidentReport(int incidentId,int reportId) throws IncidentNumberNotFoundException
	{
		Report report=new Report();
		Incident incident=null;
		try
		{
			stat=con.prepareStatement("select * from incident where incidentid=?");
			stat.setInt(1, incidentId);
			set=stat.executeQuery();
			if(!set.next())
			{
				throw new IncidentNumberNotFoundException("Incident with incident id:"+incidentId+" not found for generating report");
			}
			else
			{
				incident=getIncident(set);
				report.setReportId(reportId);
				report.setIncidentId(incidentId);
				report.setReportingOfficerId(incident.getOfficerId());
				report.setReportDate(incident.getIncidentDate());
				report.setReportDetails(incident.getDescription());
				report.setStatus(incident.getStatus());
				try
				{
					stat=con.prepareStatement("insert into report values (?,?,?,?,?,?)");
					stat.setInt(1, report.getReportId());
					stat.setInt(2, incidentId);
					stat.setInt(3, report.getReportingOfficerId());
					stat.setString(4, report.getReportDate());
					stat.setString(5, report.getReportDetails());
					stat.setString(6, report.getStatus());
					stat.execute();
				}
				catch(Exception e)
				{
					System.out.println("\nException: "+e.getMessage());
					return null;
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return report;
		
	}
	
	private Incident getIncident(ResultSet set) throws SQLException
	{
		Incident incident=new Incident();
		incident.setIncidentId(set.getInt("incidentid"));
        incident.setIncidentType(set.getString("incidenttype"));
        incident.setIncidentDate(set.getString("incidentdate"));
        incident.setLocation(set.getString("location"));
        incident.setDescription(set.getString("description"));
        incident.setStatus(set.getString("status"));
        incident.setVictimId(set.getInt("victimid"));
        incident.setSuspectId(set.getInt("suspectid"));
        incident.setOfficerId(set.getInt("officerid"));
        return incident;
	}
	
	public Incident getIncidentById(int incidentId)
	{
		 Incident incident = null;
		    try 
		    {
		        stat = con.prepareStatement("select * from incident where incidentid=?");
		        stat.setInt(1, incidentId);
		        set = stat.executeQuery();
		        if (set.next()) 
		        {
		            incident = getIncident(set);
		        }
		    } 
		    catch (SQLException e) 
		    {
		        System.out.println("\nException: " + e.getMessage());
		    }
		    return incident;
	}
	
	public void showAgency()
	{
		try
		{
			stat=con.prepareStatement("select * from agency");
			set=stat.executeQuery();
			while(set.next())
			{
				System.out.println(set.getString(2)+" "+set.getString(3));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private boolean validateDate(String date) {
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     sdf.setLenient(false); 
	     try 
	     {
	         sdf.parse(date); 
	     } 
	     catch (ParseException e) 
	     {
	         return false;
	     }
	     return true;
	}

}
