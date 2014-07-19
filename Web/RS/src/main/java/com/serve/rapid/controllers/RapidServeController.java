package com.serve.rapid.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serve.rapid.domain.Complaint;
import com.serve.rapid.domain.Customer;
import com.serve.rapid.domain.FieldAgent;
import com.serve.rapid.repository.CommentRepository;
import com.serve.rapid.repository.ComplaintRepository;
import com.serve.rapid.repository.CustomerRepository;
import com.serve.rapid.repository.FieldAgentLocationRepository;
import com.serve.rapid.repository.FieldAgentRepository;

@Controller
@RequestMapping("/endpoint")
public class RapidServeController {

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

	@RequestMapping(value = "/findUserById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Customer findUserById(@RequestParam Map<String, String> params,
			ModelMap model) {
		long id = Long.parseLong(params.get("id"));
		String contactNumber = params.get("contactNumber");
		List<Customer> result = customerRepository.findByIdAndContactNumber(id, contactNumber);
		Customer customer = null;
		if(result.size() > 0)
			customer = result.get(0);
		return customer;
	}

	@RequestMapping(value = "/getAllComplaintByUserId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Complaint> getAllComplaintByUserId(@PathVariable String id,
			ModelMap model) {
		long custId = Long.parseLong(id);
		Customer cust = customerRepository.findOne(custId);
		return complaintRepository.findByCustomer(cust);
	}

	@RequestMapping(value = "/findAgentById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FieldAgent findAgentById(@RequestParam Map<String, String> params,
			ModelMap model) {
		long id = Long.parseLong(params.get("id"));
		String contactNumber = params.get("contactNumber");
		List<FieldAgent> result = fieldAgentRepository.findByIdAndContactNumber(id, contactNumber);
		FieldAgent customer = null;
		if(result.size() > 0)
			customer = result.get(0);
		return customer;
	}

	@RequestMapping(value = "/getAllComplaintByAgentId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Complaint> getAllComplaintByAgentId(@PathVariable String id,
			ModelMap model) {
		long custId = Long.parseLong(id);
		FieldAgent cust = fieldAgentRepository.findOne(custId);
		List<Complaint> complaints = complaintRepository.findByAgent(cust);
		
		return complaints;
	}
	
}
