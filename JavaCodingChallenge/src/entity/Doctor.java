package entity;

public class Doctor {

	private int doctorId;
	private String fname;
	private String lname;
	private String specialization;
	private String contactNum;
	
	public Doctor()
	{
		
	}

	public Doctor(int doctorId, String fname, String lname, String specialization, String contactNum) {
		super();
		this.doctorId = doctorId;
		this.fname = fname;
		this.lname = lname;
		this.specialization = specialization;
		this.contactNum = contactNum;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", fname=" + fname + ", lname=" + lname + ", specialization="
				+ specialization + ", contactNum=" + contactNum + "]";
	}
	
}
