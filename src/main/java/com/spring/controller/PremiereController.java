package com.spring.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.PremiereDTO;
import com.spring.service.AjaxPremiereService;


@Controller
public class PremiereController{
	
	@Autowired
	AjaxPremiereService premiereService;
	
	//@RequestMapping(value = "/premiere/list", method=RequestMethod.GET)
	@RequestMapping({"premiere/list", "/admin/premiere/list"})
	public String premiereList(Model model) {
		return "/admin/premiere/info/list";
	}
	
	
	@RequestMapping("/admin/premiere/write")
	public String premiereWrite() {
		return "/admin/premiere/info/write";
	}
	
	@RequestMapping("/admin/premiere/writeOk")
	public String premiereWriteOk(PremiereDTO dto, Model model) {
		return "/admin/premiere/info/writeOk";
	}
	
	@RequestMapping({"premiere/view", "/admin/premiere/view"})
	public String premiereView(int uid, Model model) {
		return "/admin/premiere/info/view";
	}
	
	@RequestMapping("/admin/premiere/update")
	public String premiereUpdate(int uid, Model model) {
		return "/admin/premiere/info/update";
	}
	
	@RequestMapping("/admin/premiere/updateOk")
	public String premiereUpdateOk(PremiereDTO dto, Model model) {
		return "/admin/premiere/info/updateOk";
	}
	
	@RequestMapping("/admin/premiere/deleteOk")
	public String premiereDeleteOk(int [] uid, Model model) {
		return "/admin/premiere/info/deleteOk";
	}
	
	// 시사회 당첨 추첨
	@RequestMapping("/admin/premiere/win")
	public String premiereWin(Model model) {
		model.addAttribute("titleList", premiereService.getTitle());
		return "/admin/premiere/info/win";
	}
	
	// 시사회 당첨 추첨 완료 클릭하면 
	@PostMapping("/admin/premiere/winOk")
	public String premiereWinOk(int prUid, int count, String [] email, Model model) {
		System.out.println("prUid : " + prUid);
		System.out.println("count: " + count);
		System.out.println("email : ");
		String email_arr = "";
		for(int i = 0; i < email.length; i++) {
			if(i != 0) {
				email_arr += ", ";
			}
			email_arr += "'";
			email_arr += email[i];
			email_arr += "'";
		}
		System.out.println(email_arr);
		model.addAttribute("updateBool", premiereService.updateBool(prUid, email_arr));
		return "/admin/premiere/info/winOk";
	}
	
	// 시사회 당첨자 발표 목록 페이지
	@RequestMapping({"/premiereWin", "/admin/premiereWin"})
	public String premiereWinList(Model model) {
		return "/admin/premiere/win/list";
	}
	
	// 시사회 당첨자 발표 조회
	@RequestMapping({"/premiereWin/view", "/admin/premiereWin/view"})
	public String premiereWinView() {
		return "/admin/premiere/win/view";
	}
	
	// 시사회 당첨자 발표 삭제
	@RequestMapping("/admin/premiereWin/deleteOk")
	public String premiereWinDeleteOk() {
		return "/admin/premiere/win/deleteOk";
	}
	
	
}
















