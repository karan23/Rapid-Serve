package com.serve.rapid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/complaint")
public class ComplaintController {

	@RequestMapping("/manage")
	public String getRootPage(ModelMap modelMap) {
		return "complaint/manage";
	}
}
