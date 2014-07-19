package com.serve.rapid.controllers;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
		public @ResponseBody Customer addCustomer(@RequestBody Customer customer,
				ModelMap model) {
		return customerRepository.save(customer);
	}

	@RequestMapping(value = "/addFieldAgent", method = RequestMethod.POST, produces = "application/json")
		public @ResponseBody Iterable<FieldAgent> addFieldAgent(@RequestBody FieldAgent fieldAgent,
				ModelMap model) {
		fieldAgent.setType(Constants.UT_FIELDAGENT);
		fieldAgent.setFAID("FA"+gen());
		fieldAgentRepository.save(fieldAgent);
		return fieldAgentRepository.findAll();
	}

	
	@RequestMapping(value = "/getAllFieldAgent", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<FieldAgent> getAllFieldAgent() {
	return fieldAgentRepository.findAll();
}
	
	@RequestMapping(value = "/addFieldAgentLocation", method = RequestMethod.POST, produces = "application/json")
		public @ResponseBody FieldAgentLocation addFieldAgentLocation(@RequestBody FieldAgentLocation fieldAgentLocation,
				ModelMap model) {
		return fieldAgentLocationRepository.save(fieldAgentLocation);
	}

	@RequestMapping(value = "/addComplaint", method = RequestMethod.POST, produces = "application/json")
		public @ResponseBody Complaint addComplaint(@RequestBody Complaint complaint,
				ModelMap model) {
		return complaintRepository.save(complaint);
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.POST, produces = "application/json")
		public @ResponseBody Comment addComment(@RequestBody Comment comment,
				ModelMap model) {
		return commentRepository.save(comment);
	}
	public int gen() {
	    Random r = new Random( System.currentTimeMillis() );
	    return 10000 + r.nextInt(20000);
	}
}
