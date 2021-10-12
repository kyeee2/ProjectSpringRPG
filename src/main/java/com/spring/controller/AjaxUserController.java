package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.CustomerValidator;
import com.spring.config.PrincipalDetails;
import com.spring.domain.AjaxUserList;
import com.spring.domain.CustomerDTO;
import com.spring.service.AjaxUserService;
import com.spring.service.LoginService;


@RestController
@RequestMapping("/admin")
public class AjaxUserController {
	
	@Autowired
	AjaxUserService ajaxUserService;
	
	@Autowired
	LoginService loginService;	
	
	@Autowired
	public void setAjaxUserService(AjaxUserService ajaxUserService) {
		this.ajaxUserService = ajaxUserService;
	}
	
	@GetMapping("/user/{page}/{pageRows}")
	public AjaxUserList list(@PathVariable int page, 
			@PathVariable int pageRows) {
		List<CustomerDTO> list = null;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		// 페이징 관련 세팅 값들
		//page : 현재 페이지
		//pageRows : 한 '페이지'에 몇개의 글을 리스트 할것인가?
		int writePages = 10;    // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
		int totalPage = 0; // 총 몇 '페이지' 분량인가? 
		int totalCnt = 0;  // 글은 총 몇개인가?
		
		try {
			
			totalCnt = ajaxUserService.count();
			System.out.println("갯수는 총" +totalCnt + "개");
			// page 와 pageRows의 유효성 검사
			if(page < 0 || page > totalCnt / pageRows) {	// page 유효성 검사
				
			}
			
			// 총 몇 페이지 분량?
			totalPage = (int)Math.ceil(totalCnt / (double)pageRows);
			
			// from : 몇 번째 row 부터?
			int from = (page - 1) * pageRows;	// MySQL 의 LIMIT 는 0-base
			
			list = ajaxUserService.list(from, pageRows);			
			
			if(list == null) {
				message.append("[리스트할 데이터가 없습니다.]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}

		AjaxUserList result = new AjaxUserList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
		
		result.setPage(page);
		result.setTotalPage(totalPage);
		result.setWritePages(writePages);
		result.setPageRows(pageRows);
		result.setTotalCnt(totalCnt);
		
		return result;
		
	}
	
	@PostMapping("/user/deleteOk")
	public String userdeleteOk(Authentication authentication, Model model) {
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
		CustomerDTO user= userDetails.getUser();
		String id = user.getId();
		System.out.println("탈퇴아이디:" + id);
		int enable= user.getEnable();
		System.out.println("user넣기전enable" +enable);
		user.setEnable(0);
		enable = user.getEnable();
		System.out.println("user넣은후 enable" +enable);
		model.addAttribute("result", loginService.deleteMember(enable, id));
		return "/admin/user/deleteOk";
	}
	
	
}
