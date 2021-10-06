package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.spring.domain.PremiereDTO;
import com.spring.service.AjaxPremiereService;


@Controller
public class PremiereController{
	
	@Autowired
	AjaxPremiereService premiereService;
	
	@RequestMapping(value = "/premiere/list", method=RequestMethod.GET)
	public String premiereList(Model model) {
		return "/admin/premiere/info/list";
	}
	
	
	@RequestMapping("premiere/write")
	public String premiereWrite() {
		return "/admin/premiere/info/write";
	}
	
	@RequestMapping("premiere/writeOk")
	public String premiereWriteOk(PremiereDTO dto, Model model) {
		return "/admin/premiere/info/writeOk";
	}
	
	@RequestMapping("premiere/view")
	public String premiereView(int uid, Model model) {
		return "/admin/premiere/info/view";
	}
	
	@RequestMapping("premiere/update")
	public String premiereUpdate(int uid, Model model) {
		return "/admin/premiere/info/update";
	}
	
	@RequestMapping("premiere/updateOk")
	public String premiereUpdateOk(PremiereDTO dto, Model model) {
		return "/admin/premiere/info/updateOk";
	}
	
	@RequestMapping("premiere/deleteOk")
	public String premiereDeleteOk(int [] uid, Model model) {
		return "/admin/premiere/info/deleteOk";
	}
	
	// 시사회 당첨 추첨
	@RequestMapping("/premiere/win")
	public String premiereWin() {
		return "/admin/premiere/info/win";
	}
	
	// 시사회 당첨자 발표 목록 페이지
	@RequestMapping("/premiereWin")
	public String premiereWinList() {
		return "/admin/premiere/win/list";
	}
	
	// 시사회 당첨자 발표 조회
	@RequestMapping("/premiereWin/view")
	public String premiereWinView() {
		return "/admin/premiere/win/view";
	}
	
	// 시사회 당첨자 발표 삭제
	@RequestMapping("/premiereWin/deleteOk")
	public String premiereWinDeleteOk() {
		return "/admin/premiere/win/deleteOk";
	}
	
	
}
















