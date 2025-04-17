package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {
	
	private static Connection con;
	
	public static Connection getCon()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crimemanagement","root","root");
			System.out.println("Database Connected");
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+e.getMessage());
		}
		return con;
	}

}
