package com.example.rapidserve;

public class Complaint {

	int id;
	String comments, complaintText, complaintType, complaintTime, lastUpdated,
			status, satisfiedText, satisfied;
	public Complaint() {
		
	}
	public Complaint(String complaintText, String status) {
		this.complaintText = complaintText;
		this.status = status;
	}

}
