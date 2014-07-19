package com.serve.rapid.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JoinColumn(name = "agent_id")
    @ManyToOne
    private FieldAgent agent;
	
	private String complaintText;
	private Date complaintTime;
	private Date lastUpdated;
	private String status;
	private String address;
	private String contactNumber;
	private String contactEmail;
	private String contactName;
	private String longitude;
	private String latitude;
	private String satisfiedText;
	private String satisfied;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public FieldAgent getAgent() {
		return agent;
	}
	public void setAgent(FieldAgent agent) {
		this.agent = agent;
	}
	public String getComplaintText() {
		return complaintText;
	}
	public void setComplaintText(String complaintText) {
		this.complaintText = complaintText;
	}
	public Date getComplaintTime() {
		return complaintTime;
	}
	public void setComplaintTime(Date complaintTime) {
		this.complaintTime = complaintTime;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	public String getSatisfiedText() {
		return satisfiedText;
	}
	public void setSatisfiedText(String satisfiedText) {
		this.satisfiedText = satisfiedText;
	}
	public String getSatisfied() {
		return satisfied;
	}
	public void setSatisfied(String satisfied) {
		this.satisfied = satisfied;
	}
	
	
	
	
	
}
