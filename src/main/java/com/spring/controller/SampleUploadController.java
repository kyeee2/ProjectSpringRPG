package com.spring.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/basic")
public class SampleUploadController {
	
	@Resource(name="loginService")
	LoginService loginService;
	
	

	@GetMapping("/FileUpload")
	public String newFile() {
		return "/basic/FileUpload";
		}
	@PostMapping("/FileUpload")
	public String saveFile(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
		log.info("request = {}", request);
		
		log.info("multipartFile = {}", file);
		
		return "/basic/FileUpload";
		}
	}

	



