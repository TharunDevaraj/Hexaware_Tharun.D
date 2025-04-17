package model;

public class Suspect {

	private int suspectId;
	private String fname;
	private String lname;
	private String dateOfBirth;
	private String gender;
	private String contact;
	
	public Suspect()
	{
		
	}
	
	public Suspect(int suspectId, String fname, String lname, String dateOfBirth, String gender, String contact) {
		super();
		this.suspectId = suspectId;
		this.fname = fname;
		this.lname = lname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contact = contact;
	}

	public int getSuspectId() {
		return suspectId;
	}

	public void setSuspectId(int suspectId) {
		this.suspectId = suspectId;
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
		return "Suspect [suspectId=" + suspectId + ", fname=" + fname + ", lname=" + lname + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", contact=" + contact + "]";
	}
	
	
}
