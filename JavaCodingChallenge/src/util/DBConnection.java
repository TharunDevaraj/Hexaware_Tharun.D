package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static Connection con;
	
	public static Connection getConnection()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital_management_system","root","root");
			System.out.println("Database connected");
		} 
		catch (Exception e) 
		{
			System.out.println("Exception: "+e.getMessage());
		}
		return con;
	}
}
