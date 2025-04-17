package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.ImpCrimeAnalysisService;
import exceptions.IncidentNumberNotFoundException;
import exceptions.InvalidDateException;
import exceptions.OfficerIdNotFoundException;
import exceptions.SuspectIdNotFoundException;
import exceptions.VictimIdNotFoundException;
import model.Incident;

public class CrimeSystemUnitTest {
	
	static ImpCrimeAnalysisService service;
	
	@BeforeAll
	public static void beforeAll()
	{
		service=new ImpCrimeAnalysisService();
	}
	
	@Test
	void testCreateIncident() {
	    Incident incident = new Incident(108, "Robbery", "2021-04-07", "Mumbai","bank robbery","Open",201,11,21);
	    boolean created=false;
		try 
		{
			created = service.createIncident(incident);
		} 
		catch (VictimIdNotFoundException | SuspectIdNotFoundException | OfficerIdNotFoundException | InvalidDateException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
	    assertTrue(created);
	}

	@Test
	void testCreateIncidentValues() {
	    Incident incident = new Incident(109, "Assault","2019-09-13","Kolkata", "physical assault","Open",202,12,17 );
	    try 
	    {
			service.createIncident(incident);
		} 
	    catch (VictimIdNotFoundException | SuspectIdNotFoundException | OfficerIdNotFoundException | InvalidDateException e) 
	    {
	    	System.out.println("\nException: "+e.getMessage());
		}
	    
	    Incident testIncident = service.getIncidentById(109);
	    assertEquals("Assault", testIncident.getIncidentType());
	    assertEquals("Open", testIncident.getStatus());
	    assertEquals("2019-09-13", testIncident.getIncidentDate());
	}
	
	@Test
	void testUpdateIncidentStatus() throws IncidentNumberNotFoundException {
	    boolean updated = service.updateIncident("Closed",103);
	    assertTrue(updated);

	    Incident updatedIncident = service.getIncidentById(103);
	    assertEquals("Closed",updatedIncident.getStatus());
	}
	
	@Test
	void testInvalidStatusUpdate() throws IncidentNumberNotFoundException {

		boolean result = service.updateIncident("Closed",104);
	    assertTrue(result);
		
	    result = service.updateIncident("InvalidStatus",104);
	    assertFalse(result);
	}

}
