package com.serve.rapid.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class FieldAgent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String contactNumber;
	
	private String type;
	
	
	
	@OneToMany(mappedBy="agent", cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<FieldAgentLocation> locations;

	@OneToMany(mappedBy="agent", cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Complaint> complaints;

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Collection<FieldAgentLocation> getLocations() {
		return locations;
	}



	public void setLocations(Collection<FieldAgentLocation> locations) {
		this.locations = locations;
	}
	
	
}
