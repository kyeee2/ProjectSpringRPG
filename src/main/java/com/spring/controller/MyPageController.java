package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.config.PrincipalDetails;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 마이페이지 컨트롤러
	@RequestMapping("/myInfo")
	public String myInfo(Authentication authentication, Model model) {

		// 로그인 정보에서 고유번호 가져오기
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        int uid = userDetails.getUid();
       
        model.addAttribute("list", loginService.selectByUid(uid));
		
		
		return "/myPage/info/view";
	}
	@RequestMapping("/myInfo/update")
	public String update(Authentication authentication, Model model) throws Exception{
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        int uid = userDetails.getUid();
		
		model.addAttribute("list", loginService.selectByUid(uid));
		return "/myPage/info/update";
	}
	@GetMapping("/myInfo/deleteOk")
	public String deleteOk(Authentication authentication, Model model) {
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
		CustomerDTO user= userDetails.getUser();
		String id = user.getId();
		System.out.println("탈퇴아이디:" + id);
		int enable= user.getEnable();
		System.out.println("user넣기전enable" +enable);
		user.setEnable(0);
		enable = user.getEnable();
		System.out.println("user넣은후 enable" +enable);
		model.addAttribute("result", loginService.deleteMember(id));
		//userDetails.isEnabled();
		return "/myPage/info/deleteOk";
	}
	@GetMapping("/myInfo/pwUpdate")
	public String pwUpdate( Authentication authentication, Model model) {
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
		int uid = userDetails.getUid();
		model.addAttribute("list", loginService.selectByUid(uid));
		return "/myPage/info/pwUpdate";
	}
	@PostMapping("/myInfo/updateOk")
	public String updateOk(String phonenum, String nickname, Authentication authentication, Model model) throws Exception{
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        CustomerDTO user = userDetails.getUser();
		System.out.println(user);
		int uid = user.getUid();
		model.addAttribute("result", loginService.updateUser(phonenum, nickname, uid));
		return "/myPage/info/updateOk";
	}
	@PostMapping("/myInfo/pwUpdateOk")
	public String pwUpdateOk(String pw, Authentication authentication, Model model) throws Exception{
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        CustomerDTO user= userDetails.getUser();
        int uid = user.getUid();
        
        String rawPassword = pw;
        System.out.println(rawPassword);
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPw(encPassword);
		pw= user.getPw();
        model.addAttribute("result", loginService.updatePw(pw, uid));
		return "/myPage/info/pwUpdateOk";
	}
	// 마이페이지 게시글/ 댓글 조회 페이지
	@RequestMapping("/myPost")
	public String myPost() {	// 특정 회원 uid
		return "/myPage/post/list";
	}
}
