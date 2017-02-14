package com.jvanhouteghem.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getApplications(){
		System.out.println("application");
		return "/application/";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getApplication(@PathVariable String id){
		System.out.println("application");
		return "/application/"+ id;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addApplication(@PathVariable String id){
		//System.out.println("application");
		return "/application/"+ id;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteApplication(@PathVariable String id){
		//System.out.println("application");
		return "/application/"+ id;
	}
	
}
