package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ckeditor.common.Consts;
import com.spring.service.AjaxBoardService;

@Controller
public class BoardController {
	
	@Autowired
	AjaxBoardService service;
	
	@RequestMapping("/freeboard")
	public String freeboardList() {
		return "/freeboard/list";
	}
	
	@RequestMapping("/movieboard")
	public String movieboardList() {
		return "/movieboard/list";
	}
	
	@RequestMapping("/noticeboard")
	public String noticeboardList() {
		return "/noticeboard/list";
	}
 
	@RequestMapping("/user/write")
	public String boardWrite() {
		return "/basic/write";
	}
	
	@RequestMapping("/view")
	public String boardView(String boardType, int uid, Model model) {
		return "/basic/view";
	}
	
	@RequestMapping("/user/update")
	public String boardUpdate(String boardType, int uid, Model model) {
		return "/basic/update";
	}
	
}
