package com.serve.rapid.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serve.rapid.domain.Complaint;

@Controller
@RequestMapping("/endpoint")
public class RapidServeController {

	@RequestMapping(value = "/addComplaint", method = RequestMethod.POST, produces = "application/json")
		public @ResponseBody Iterable<Complaint> addChapter(@RequestParam Map<String, String> params,
				ModelMap model) {
			
		return null;
	}
}
