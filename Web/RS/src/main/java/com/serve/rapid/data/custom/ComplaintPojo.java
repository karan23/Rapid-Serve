package com.serve.rapid.data.custom;

import com.serve.rapid.domain.Complaint;

public class ComplaintPojo {
	private Complaint complaint;
	private String longitude;
	private String latitude;
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
