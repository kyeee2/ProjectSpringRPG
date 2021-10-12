package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.AjaxUserService;

@Controller
@RequestMapping("/admin")
public class UserController {
	
	public UserController() {
		System.out.println("userController() 생성");
	}
	
	@Autowired
	AjaxUserService ajaxUserService;
	

	
	@RequestMapping("/user")
	public String userlist() {
		return "/admin/user/list";
	}
			
}
	
