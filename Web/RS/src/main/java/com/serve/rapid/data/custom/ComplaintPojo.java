package com.serve.rapid.data.custom;

import com.serve.rapid.domain.Complaint;
import com.serve.rapid.domain.Customer;

public class ComplaintPojo {
	private Complaint complaint;
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	private Customer customer;
}
