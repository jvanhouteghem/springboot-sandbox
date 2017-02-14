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
	
	/*@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/user")
	public String user(){
		foo(); // no need to secure this method with secure annotation
		return "user"; // Fail "views/view2/view2", "view2", "views/view2/view2.html"
	}*/
	
	//http://localhost:8080/views/view2/view2.html
	
	private void foo(){
		// do something
	}
	
}
