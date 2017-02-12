package com.jvanhouteghem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/helloworld")
	public String helloworld(){
		return "hello";
	}
	
}
