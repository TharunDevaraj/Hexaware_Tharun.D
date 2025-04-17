package model;

public class Officer {

	private int officerId;
	private String fname;
	private String lname;
	private String badgeNum;
	private String rank;
	private String contact;
	private int agencyId;
	
	public Officer()
	{
		
	}

	public Officer(int officerId, String fname, String lname, String badgeNum, String rank, String contact, int agencyId) {
		super();
		this.officerId = officerId;
		this.fname = fname;
		this.lname = lname;
		this.badgeNum = badgeNum;
		this.rank = rank;
		this.contact = contact;
		this.agencyId = agencyId;
	}

	public int getOfficerId() {
		return officerId;
	}

	public void setOfficerId(int officerId) {
		this.officerId = officerId;
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

	public String getBadgeNum() {
		return badgeNum;
	}

	public void setBadgeNum(String badgeNum) {
		this.badgeNum = badgeNum;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	@Override
	public String toString() {
		return "Officer [officerId=" + officerId + ", fname=" + fname + ", lname=" + lname + ", badgeNum=" + badgeNum
				+ ", rank=" + rank + ", contact=" + contact + ", agencyId=" + agencyId + "]";
	}
	
	
}
