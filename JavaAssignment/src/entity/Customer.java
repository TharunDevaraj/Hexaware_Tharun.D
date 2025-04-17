package entity;

import java.sql.Date;

public class Customer {
	
	private long custId;
	private String fname;
	private String lname;
	private Date dateOfBirth;
	private String email;
	private String phone;
	private String address;
	
	public Customer()
	{
		
	}

	public Customer(long custId, String fname, String lname, Date dateOfBirth, String email, String phone,
			String address) {
		super();
		this.custId = custId;
		this.fname = fname;
		this.lname = lname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void printCustomerDetails() {
        System.out.println("Customer ID: " + custId);
        System.out.println("Name: " + fname + " " + lname);
        System.out.println("DOB: " + dateOfBirth);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }
}
