package model;

public class Victim {

	private int victimId;
	private String fname;
	private String lname;
	private String dateOfBirth;
	private String gender;
	private String contact;
	
	public Victim()
	{
		
	}
	
	public Victim(int victimId, String fname, String lname, String dateOfBirth, String gender, String contact) {
		super();
		this.victimId = victimId;
		this.fname = fname;
		this.lname = lname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contact = contact;
	}

	public int getVictimId() {
		return victimId;
	}

	public void setVictimId(int victimId) {
		this.victimId = victimId;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Victim [victimId=" + victimId + ", fname=" + fname + ", lname=" + lname + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", contact=" + contact + "]";
	}
	
	
}
