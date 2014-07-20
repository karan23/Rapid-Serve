package com.serve.rapid.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serve.rapid.Constants;
import com.serve.rapid.domain.Comment;
import com.serve.rapid.domain.Complaint;
import com.serve.rapid.domain.Customer;
import com.serve.rapid.domain.FieldAgent;
import com.serve.rapid.domain.FieldAgentLocation;
import com.serve.rapid.repository.CommentRepository;
import com.serve.rapid.repository.ComplaintRepository;
import com.serve.rapid.repository.CustomerRepository;
import com.serve.rapid.repository.FieldAgentLocationRepository;
import com.serve.rapid.repository.FieldAgentRepository;

@Controller
@RequestMapping("/crud")
public class CrudController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FieldAgentRepository fieldAgentRepository;

	@Autowired
	private FieldAgentLocationRepository fieldAgentLocationRepository;

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private CommentRepository commentRepository;

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Iterable<Customer> addCustomer(@RequestBody Customer customer,
			ModelMap model) {
		 customerRepository.save(customer);
		 return customerRepository.findAll();
	}

	@RequestMapping(value = "/addFieldAgent", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Iterable<FieldAgent> addFieldAgent(
			@RequestBody FieldAgent fieldAgent, ModelMap model) {
		fieldAgent.setType(Constants.UT_FIELDAGENT);
		fieldAgent.setFAID("FA" + gen());
		fieldAgentRepository.save(fieldAgent);
		return fieldAgentRepository.findAll();
	}

	@RequestMapping(value = "/getAllComplaints", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<Complaint> getAllComplaints() {
		return complaintRepository.findAll();
	}
	
	@RequestMapping(value = "/getAllFieldAgent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<FieldAgent> getAllFieldAgent() {
		return fieldAgentRepository.findAll();
	}

	@RequestMapping(value = "/addFieldAgentLocation", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody FieldAgentLocation addFieldAgentLocation(
			@RequestBody FieldAgentLocation fieldAgentLocation, ModelMap model) {
		return fieldAgentLocationRepository.save(fieldAgentLocation);
	}

	@RequestMapping(value = "/addComplaint", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Iterable<Complaint> addComplaint(
			@RequestBody Complaint complaint, ModelMap model) {
		Date creationTime = new Date();
		complaint.setComplaintTime(creationTime);
		complaint.setStatus(Constants.COMP_CREATED);
		complaint.setSatisfiedText(Integer.toString(gen()));
		Complaint compSaved = complaintRepository.save(complaint);
		Customer customer = compSaved.getCustomer();
		double custLong = Double.parseDouble(customer.getLongitude());
		double custLat = Double.parseDouble(customer.getLatitude());
		Map<FieldAgent, Double> distanceMap = new HashMap<FieldAgent, Double>();
		for(FieldAgent agent : fieldAgentRepository.findAll()){
			
			List<FieldAgentLocation> locs = fieldAgentLocationRepository.findByAgentOrderBySeenDesc(agent);
			if(locs.size()>0){
				double distance = getDistance(custLat, custLong, Double.parseDouble(locs.get(0).getLatitude()), Double.parseDouble(locs.get(0).getLongitude()));
				distanceMap.put(agent, distance);
				
			}
			
			System.out.println(distanceMap);
		}
		FieldAgent assigned = null;
		// find minimum first
		double min = Integer.MAX_VALUE;
		for(Entry<FieldAgent, Double> distance : distanceMap.entrySet()) {
		    min = Math.min(min, distance.getValue());
		}
		for(Entry<FieldAgent, Double> distance : distanceMap.entrySet()) {
		    if(distance.getValue() == min) {
		    	assigned = distance.getKey();
		    }
		}
		if(assigned!=null){
			System.out.println(assigned);
			compSaved.setAgent(assigned);
			complaintRepository.save(compSaved);
		}
		return complaintRepository.findAll();
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Comment addComment(@RequestBody Comment comment,
			ModelMap model) {
		return commentRepository.save(comment);
	}
	
	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<Customer> getAllCustomers() {
	return customerRepository.findAll();
	}
	
	@RequestMapping(value = "/getNearByFA/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<FieldAgentLocation> getNearByFA(@PathVariable String id) {
		System.out.println(id);
		List<FieldAgentLocation> locs = null;
		Complaint complaint = complaintRepository.findOne(Long.parseLong(id));
		if(complaint.getAgent()!=null){
		locs = (List<FieldAgentLocation>) complaint.getAgent().getLocations();
		Collections.sort(locs, new Comparator<FieldAgentLocation>() {
		    public int compare(FieldAgentLocation m1, FieldAgentLocation m2) {
		        return m2.getSeen().compareTo(m1.getSeen());
		    }
		});
		}
	
		return locs;
	}
	
	
	
	public static double rad(double x) {
		  return x * Math.PI / 180;
		}

		public static double getDistance(double p1lat, double p1long, double p2lat, double p2long) {
			double R = 6378137; // Earth’s mean radius in meter
			double dLat = rad(p2lat - p1lat);
			double dLong = rad(p2long - p1long);
			double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
		    Math.cos(rad(p1lat)) * Math.cos(rad(p2lat)) *
		    Math.sin(dLong / 2) * Math.sin(dLong / 2);
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		  double d = R * c;
		  return d; // returns the distance in meter
		};
		
		
	public int gen() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}
}
