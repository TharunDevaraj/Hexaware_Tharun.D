package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;
import exception.AppointmentNotFoundException;
import exception.DoctorNumberNotFoundException;
import exception.PatientNumberNotFoundException;
import util.DBConnection;

public class ImpHospitalService {
	
	private Connection con;
	
	private PreparedStatement stat;
	
	private ResultSet set;
	
	public ImpHospitalService()
	{
		con=DBConnection.getConnection();
	}
	
	public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException
	{
		Appointment appointment=null;
		try 
		{
			stat=con.prepareStatement("select * from appointment where appointmentid=?");
			stat.setInt(1, appointmentId);
			set=stat.executeQuery();
			if(set.next())
			{
				appointment=getAppointment(set);
			}
			else 
			{
				throw new AppointmentNotFoundException("Appointment with appointment id:"+appointmentId+" not found");
			}
			
		} 
		catch (SQLException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return appointment;
	}
	
	public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException
	{
		List<Appointment> appointments=new ArrayList<>();
		try 
		{
			stat=con.prepareStatement("select * from appointment where patientid=?");
			stat.setInt(1, patientId);
			set=stat.executeQuery();
			while(set.next())
			{
				appointments.add(getAppointment(set));
			}
			if(appointments.isEmpty()) 
			{
				throw new PatientNumberNotFoundException("No appointments for patient id:"+patientId);
			}
			
		} 
		catch (SQLException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return appointments;
	}
	
	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorNumberNotFoundException
	{
		List<Appointment> appointments=new ArrayList<>();
		try 
		{
			stat=con.prepareStatement("select * from appointment where doctorid=?");
			stat.setInt(1, doctorId);
			set=stat.executeQuery();
			while(set.next())
			{
				appointments.add(getAppointment(set));
			}
			if(appointments.isEmpty()) 
			{
				throw new DoctorNumberNotFoundException("No appointments for doctor id:"+doctorId);
			}
			
		} 
		catch (SQLException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return appointments;
	}
	
	public boolean scheduleAppointment(Appointment appointment)
	{
		try 
		{
			stat=con.prepareStatement("insert into appointment values(?,?,?,?,?)");
			stat.setInt(1, appointment.getAppointmentId());
			stat.setInt(2, appointment.getPatientId());
			stat.setInt(3, appointment.getDoctorId());
			stat.setString(4, appointment.getAppointmentDate());
			stat.setString(5, appointment.getDescription());
			if(stat.executeUpdate()>0)
			{
				return true;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return false;
	}
	
	public boolean updateAppointment(Appointment appointment) throws AppointmentNotFoundException
	{
		try 
		{
			stat=con.prepareStatement("update appointment set appointmentdate=?,description=? where appointmentid=?");
			stat.setString(1, appointment.getAppointmentDate());
			stat.setString(2, appointment.getDescription());
			stat.setInt(3, appointment.getAppointmentId());
			if(stat.executeUpdate()>0)
			{
				return true;
			}
			throw new AppointmentNotFoundException("Appointment with appointment id:"+appointment.getAppointmentId()+" not found");
			
		} 
		catch (SQLException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return false;
	}
	
	public boolean cancelAppointment(int appointmentId) throws AppointmentNotFoundException
	{
		try 
		{
			stat=con.prepareStatement("delete from appointment where appointmentid=?");
			stat.setInt(1, appointmentId);
			if(stat.executeUpdate()>0)
			{
				return true;
			}
			throw new AppointmentNotFoundException("Appointment with appointment id:"+appointmentId+" not found");
			
		} 
		catch (SQLException e) 
		{
			System.out.println("\nException: "+e.getMessage());
		}
		return false;
	} 
	
	private Appointment getAppointment(ResultSet set) throws SQLException
	{
		Appointment appointment=new Appointment();
		appointment.setAppointmentId(set.getInt(1));
		appointment.setPatientId(set.getInt(2));
		appointment.setDoctorId(set.getInt(3));
		appointment.setAppointmentDate(set.getString(4));
		appointment.setDescription(set.getString(5));
		return appointment;
	}

}
