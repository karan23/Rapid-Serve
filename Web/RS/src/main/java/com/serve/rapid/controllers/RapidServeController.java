package com.serve.rapid.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serve.rapid.Constants;
import com.serve.rapid.data.custom.ComplaintPojo;
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
	public @ResponseBody Customer findUserById(
			@RequestParam Map<String, String> params, ModelMap model) {
		long id = Long.parseLong(params.get("id"));
		String contactNumber = params.get("contactNumber");
		List<Customer> result = customerRepository.findByIdAndContactNumber(id,
				contactNumber);
		Customer customer = null;
		if (result.size() > 0)
			customer = result.get(0);
		return customer;
	}

	@RequestMapping(value = "/getAllComplaintByUserId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ComplaintPojo> getAllComplaintByUserId(
			@PathVariable String id, ModelMap model) {
		long custId = Long.parseLong(id);
		Customer cust = customerRepository.findOne(custId);
		List<Complaint> complaints = null;
		complaints = complaintRepository.findByCustomer(cust);
		
		List<ComplaintPojo> result = getComplaintPojoFromComplaintObject(complaints);
		return result;
	}

	@RequestMapping(value = "/findAgentById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FieldAgent findAgentById(
			@RequestParam Map<String, String> params, ModelMap model) {
		long id = Long.parseLong(params.get("id"));
		String contactNumber = params.get("contactNumber");
		List<FieldAgent> result = fieldAgentRepository
				.findByIdAndContactNumber(id, contactNumber);
		FieldAgent customer = null;
		if (result.size() > 0)
			customer = result.get(0);
		return customer;
	}

	@RequestMapping(value = "/getAllComplaintByAgentId/{id}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ComplaintPojo> getAllComplaintByAgentId(
			@PathVariable String id, @PathVariable String status, ModelMap model) {
		long custId = Long.parseLong(id);
		FieldAgent cust = fieldAgentRepository.findOne(custId);
		List<Complaint> complaints = null;
		if (!status.equals("")) {
			if (status.equals("opened")) {
				status = Constants.COMP_INPROGRESS;
			} else if (status.equals("closed")) {
				status = Constants.COMP_COMPLETE;
			} else {
				return null;
			}
			complaints = complaintRepository.findByAgentAndStatus(cust, status);
		} else {
			complaints = complaintRepository.findByAgent(cust);
		}

		List<ComplaintPojo> result = getComplaintPojoFromComplaintObject(complaints);
		return result;
	}

	@RequestMapping(value = "/addComplaint", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Iterable<Complaint> addComplaint(
			@RequestParam Map<String, String> params, ModelMap model) {
		String complaintText = params.get("complaintText");
		String complaintType = params.get("complaintType");
		Customer customer = customerRepository.findOne(Long.parseLong(params
				.get("customerId")));
		Date complaintTime = new Date();

		Complaint complaint = new Complaint();
		complaint.setComplaintText(complaintText);
		complaint.setComplaintType(complaintType);
		complaint.setComplaintTime(complaintTime);
		complaint.setCustomer(customer);
		complaint.setStatus(Constants.COMP_CREATED);
		Random r = new Random(System.currentTimeMillis());
		String satisfiedText = Integer.toString(10000 + r.nextInt(20000));
		complaint.setSatisfiedText(satisfiedText);

		complaintRepository.save(complaint);
		return complaintRepository.findAll();
	}

	@RequestMapping(value = "/addFieldAgentLocation", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void addFieldAgentLocation(
			@RequestParam Map<String, String> params, ModelMap model) {
		String latitude = params.get("latitude");
		String longitude = params.get("longitude");
		String agentId = params.get("agentId");
		FieldAgentLocation location = new FieldAgentLocation();
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		FieldAgent agent = fieldAgentRepository
				.findOne(Long.parseLong(agentId));
		location.setAgent(agent);
		location.setSeen(new Date());
		fieldAgentLocationRepository.save(location);
	}

	@RequestMapping(value = "/complaintAssignToAgent", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, String> complaintAssignToAgent(@RequestParam Map<String, String> params, ModelMap model) {
		String complaintId = params.get("complaintId");
		String agentId = params.get("agentId");
		Map<String, String> result = new HashMap<String, String>();
		FieldAgent agent = fieldAgentRepository.findOne(Long.parseLong(agentId));
		if(agent.equals(null)) {
			result.put("result", "Sorry Agent Not found!");
		} else {
			Complaint complaint = complaintRepository.findOne(Long.parseLong(complaintId));
			complaint.setAgent(agent);
			complaint.setStatus(Constants.COMP_INPROGRESS);
			complaintRepository.save(complaint);
			result.put("result", "Assigned successfully!");
		}

		return result;
	}

	@RequestMapping(value = "/complaintResolvedByAgent", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, String> complaintResolvedByAgent(@RequestParam Map<String, String> params, ModelMap model) {
		String complaintId = params.get("complaintId");
		String agentId = params.get("agentId");
		String satisfiedText = params.get("satisfiedText");
		Map<String, String> result = new HashMap<String, String>();
		FieldAgent agent = fieldAgentRepository.findOne(Long.parseLong(agentId));
		if(!agent.equals(null)) {
			Complaint complaint = complaintRepository.findOne(Long.parseLong(complaintId));
			if(!complaint.getStatus().equals(Constants.COMP_INPROGRESS)) {
				//TODO
			}
			complaint.setAgent(agent);
			complaint.setStatus(Constants.COMP_COMPLETE);
			if(satisfiedText.equals(complaint.getSatisfiedText())) {
				complaint.setSatisfied(Constants.ST_YES);
			} else {
				complaint.setSatisfied(Constants.ST_YES);
			}
			complaint.setLastUpdated(new Date());
			complaintRepository.save(complaint);
			result.put("result", "Resolved complaint successfully!");
		}

		return result;
	}

	@RequestMapping(value = "/findAgentStatistics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<FieldAgentLocation> findAgentStatistics(
			@RequestParam Map<String, String> params, ModelMap model) {
		String agentId = params.get("agentId");
		return fieldAgentLocationRepository.findAllById(Long.parseLong(agentId));
	}
	
	public List<ComplaintPojo> getComplaintPojoFromComplaintObject(List<Complaint> complaints) {
		List<ComplaintPojo> result = new ArrayList<ComplaintPojo>();
		for (Complaint complaint : complaints) {
			ComplaintPojo cPojo = new ComplaintPojo();
			Customer customer = customerRepository.findOne(complaint
					.getCustomer().getId());
			/*if(!complaint.getAgent().equals(null)) {
				FieldAgent fieldAgent = fieldAgentRepository.findOne(complaint
						.getAgent().getId());
				fieldAgent.setComplaints(null);
				cPojo.setFieldAgent(fieldAgent);
			}*/
			cPojo.setComplaint(complaint);
			customer.setComplaints(null);
			//cPojo.setCustomer(customer);
			result.add(cPojo);
		}
		return result;
	}

}
