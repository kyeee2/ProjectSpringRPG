package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.BoardDTO;
import com.spring.service.AjaxBoardService;

@Controller
public class BoardController {
	
	@Autowired
	AjaxBoardService service;
	
	@RequestMapping("/freeboard")
	public String freeboardList(Model model) {
		return "/freeboard/list";
	}
	
	@RequestMapping("/movieboard")
	public String movieboardList(Model model) {
		return "/movieboard/list";
	}
	
	@RequestMapping("/noticeboard")
	public String noticeboardList(Model model) {
		return "/noticeboard/list";
	}
 
	@RequestMapping("/write")
	public String boardWrite() {
		return "/basic/write";
	}
	
	@RequestMapping("/view")
	public String boardView(String boardType, int uid, Model model) {
		return "/basic/view";
	}
	
	@RequestMapping("/update")
	public String boardUpdate(String boardType, int uid, Model model) {
		return "/basic/update";
	}
	
}
