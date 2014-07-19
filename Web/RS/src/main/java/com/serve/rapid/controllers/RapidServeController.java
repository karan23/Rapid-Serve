package com.serve.rapid.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serve.rapid.domain.Customer;
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

	@RequestMapping(value = "/findByContactEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Customer findByContactEmail(@RequestParam Map<String, String> params,
			ModelMap model) {
		String contactName = params.get("contactName");
		String contactNumber = params.get("contactNumber");
		List<Customer> result = customerRepository.findByContactNumberAndContactNumber(contactName, contactNumber);
		Customer customer = null;
		if(result.size() > 0)
			customer = result.get(0);
		return customer;
	}
	
}
