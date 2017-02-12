package com.jvanhouteghem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

	@Secured("ROLE_ADMIN")
	@RequestMapping("/admin")
	public String admin(){
		return "admin";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("/user")
	public String user(){
		return "user";
	}
	
}
