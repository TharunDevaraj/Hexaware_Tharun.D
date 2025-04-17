package entity;

public class Patient {

	private int patientId;
	private String fname;
	private String lname;
	private String dateOfBirth;
	private String gender;
	private String contactNum;
	private String address;
	
	public Patient()
	{
		
	}

	public Patient(int patientId, String fname, String lname, String dateOfBirth, String gender, String contactNum,
			String address) {
		super();
		this.patientId = patientId;
		this.fname = fname;
		this.lname = lname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactNum = contactNum;
		this.address = address;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
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

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", fname=" + fname + ", lname=" + lname + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", contactNum=" + contactNum + ", address=" + address + "]";
	}
	
}
