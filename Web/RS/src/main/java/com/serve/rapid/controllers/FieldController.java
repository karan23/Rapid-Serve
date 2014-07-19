package com.serve.rapid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serve.rapid.domain.FieldAgent;


@Controller
@RequestMapping("/fa")
public class FieldController {

	@RequestMapping("/manage")
	public String getRootPage(ModelMap modelMap) {
		return "fa/manage";
	}
	
	
	@RequestMapping(value = "/addFieldAgent", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addDetailsDiagnostics(@RequestBody FieldAgent agent,
			ModelMap model) {
    	System.out.println(agent);
    	
    	
    	 return "";
	}
}
