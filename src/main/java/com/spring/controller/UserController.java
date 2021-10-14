package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.config.PrincipalDetails;
import com.spring.domain.CustomerDTO;
import com.spring.service.AjaxUserService;
import com.spring.service.LoginService;

@Controller
@RequestMapping("/admin")
public class UserController {
	
	public UserController() {
		System.out.println("userController() 생성");
	}
	
	@Autowired
	AjaxUserService ajaxUserService;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/user")
	public String userlist() {
		return "/admin/user/list";
	}

}
	
