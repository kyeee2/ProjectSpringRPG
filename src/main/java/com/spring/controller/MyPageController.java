package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.CustomerDTO;
import com.spring.service.LoginService;

@Controller
@RequestMapping("/user")
public class MyPageController {
	
	public MyPageController() {
		System.out.println("MyPageController() 생성");
	}
	@Autowired
	LoginService loginService;
	
	
	// 마이페이지 컨트롤러
	@RequestMapping("/myInfo")
	public String myInfo(CustomerDTO user) {
		if(user.getId() != null)
		loginService.findById(user.getId());
		
		return "/myPage/info/view";
	}
	@RequestMapping("/myInfo/update")
	public String update() {
		
		return "/myPage/info/update";
	}
	@GetMapping("/myInfo/deleteOk")
	public String deleteOk(CustomerDTO user, Model model) {
		model.addAttribute("result",loginService.deleteMember(user));
		return "/myPage/info/deleteOk";
	}
	@PostMapping("/myInfo/updateOk")
	public String updateOk(CustomerDTO user, Model model) {
		
		return "/myPage/info/updateOk";
	}
	@PostMapping("/myInfo/pwUdateOk")
	public String pwUpdateOk(CustomerDTO user, Model model) {
		
		return "/myPage/info/pwUpdateOk";
	}
	// 마이페이지 게시글/ 댓글 조회 페이지
	@RequestMapping("/myPost")
	public String myPost() {	// 특정 회원 uid
		return "/myPage/post/list";
	}
}
