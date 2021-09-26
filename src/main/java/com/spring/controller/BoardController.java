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
	
	@RequestMapping("/writeOk")
	public String boardWriteOk(BoardDTO dto, Model model) {
		return "/basic/writeOk";
	}
	
	@RequestMapping("/view")
	public String boardView(String boardType, int uid, Model model) {
		return "/basic/view";
	}
	
	@RequestMapping("/update")
	public String boardUpdate(String boardType, int uid, Model model) {
		return "/basic/update";
	}
	
	@RequestMapping("/updateOk")
	public String boardUpdateOk(BoardDTO dto, Model model) {
		return "/basic/updateOk";
	}
	
	@RequestMapping("/deleteOk")
	public String boardDeleteOk(String boardType, int [] uid, Model model) {
		return "/basic/deleteOk";
	}
}
