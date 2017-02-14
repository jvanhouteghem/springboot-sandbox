package com.jvanhouteghem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

	@RequestMapping("/helloworld")
	public String helloworld(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth : " + auth.getAuthorities());
		return "hello";
	}
	
	@RequestMapping("/user")
	public User user(){
		// source : http://stackoverflow.com/a/31160173
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // [ROLE_USER]
		
		//Normalize role
		String role = auth.getAuthorities().toString()
				.replace("[", "")
				.replace("]", "")
				.replace("ROLE_", "");
		role = role.toLowerCase();
		
		User u = new User();
		u.setRole(role);
		
		return u;
	}
	
}
