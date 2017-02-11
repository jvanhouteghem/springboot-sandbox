package com.jvanhouteghem.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvanhouteghem.foo.NotificationService;

@RestController
public class PageController {
	
	// Avoid this (not testable)
	//@Autowired
	//private NotificationService notificationService;

	// Better but avoid this too : 
	/*@Autowired
	public PageController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}*/
	
	// Use this (setter based injection)
	private NotificationService notificationService;
	@Autowired
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RequestMapping("/page")
	public String home(){
		return notificationService.toString();
	}
	
	
}
