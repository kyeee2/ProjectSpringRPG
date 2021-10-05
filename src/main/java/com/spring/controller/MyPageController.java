package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MyPageController {
	
	// 마이페이지 컨트롤러
	
	// 마이페이지 게시글/ 댓글 조회 페이지
	@RequestMapping("/myPost")
	public String myPost(int uid) {	// 특정 회원 uid
		return "/myPage/post/list";
	}
}
