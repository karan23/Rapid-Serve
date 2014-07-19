package com.serve.rapid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@RequestMapping("/manage")
		public String getRootPage(ModelMap modelMap) {
			return "customer/manage";
		}
		
}
