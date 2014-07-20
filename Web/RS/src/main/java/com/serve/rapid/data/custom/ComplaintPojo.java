package com.serve.rapid.data.custom;

import com.serve.rapid.domain.Complaint;
import com.serve.rapid.domain.Customer;
import com.serve.rapid.domain.FieldAgent;

public class ComplaintPojo {
	private Complaint complaint;
	private Customer customer;
	private FieldAgent fieldAgent;

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

	public FieldAgent getFieldAgent() {
		return fieldAgent;
	}

	public void setFieldAgent(FieldAgent fieldAgent) {
		this.fieldAgent = fieldAgent;
	}
}
