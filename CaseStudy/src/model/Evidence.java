package model;

public class Evidence {

	private int evidenceId;
	private String description;
	private boolean locationFound;
	private int incidentId;
	
	public Evidence()
	{
		
	}

	public Evidence(int evidenceId, String description, boolean locationFound, int incidentId) {
		super();
		this.evidenceId = evidenceId;
		this.description = description;
		this.locationFound = locationFound;
		this.incidentId = incidentId;
	}

	public int getEvidenceId() {
		return evidenceId;
	}

	public void setEvidenceId(int evidenceId) {
		this.evidenceId = evidenceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isLocationFound() {
		return locationFound;
	}

	public void setLocationFound(boolean locationFound) {
		this.locationFound = locationFound;
	}
	
	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}

	@Override
	public String toString() {
		return "Evidence [evidenceId=" + evidenceId + ", description=" + description + ", locationFound="
				+ locationFound + ", incidentId=" + incidentId + "]";
	}

}
