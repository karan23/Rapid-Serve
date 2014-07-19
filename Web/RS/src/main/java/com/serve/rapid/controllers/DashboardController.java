package com.serve.rapid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@RequestMapping("/layout")
	public String getRootPage(ModelMap modelMap) {
		return "dashboard/dashboard";
	}
}
