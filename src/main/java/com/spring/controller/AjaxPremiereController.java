package com.spring.controller;


import java.util.List;

//import java.io.File;
//import javax.swing.filechooser.FileSystemView;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.AjaxBoardResult;
import com.spring.domain.AjaxPremiereList;
import com.spring.domain.PremiereDTO;
import com.spring.service.AjaxPremiereService;

@RestController
@RequestMapping("/premiere")
public class AjaxPremiereController {
	
	AjaxPremiereService ajaxpremiereService;
	
	@Autowired
	public void setAjaxPremiereService(AjaxPremiereService ajaxPremiereService) {
		this.ajaxpremiereService = ajaxPremiereService;
	}
	
	@GetMapping("/list/{page}/{pageRows}")	// /premiere/list/page/pageRows
	public AjaxPremiereList list(
			@PathVariable int page,
			@PathVariable int pageRows) {
		List<PremiereDTO> list = null;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		int writePages = 10;
		int totalPage = 0;
		int totalCnt = 0;
		
		try {
			totalCnt = ajaxpremiereService.count();
			
			totalPage = (int)Math.ceil(totalCnt/(double)pageRows);
			
			int from = (page - 1) * pageRows;
			
			list = ajaxpremiereService.list(from, pageRows);
			
			if(list == null) {
				message.append("[리스트 할 데이터가 없습니다.");
			}else {
				status = "OK";
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			message.append("트랜잭션 에러 : " + e.getMessage());
		}
		
		AjaxPremiereList result = new AjaxPremiereList();
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
		result.setTotalPage(totalPage);
		
		return result;
	}
	
	@GetMapping("/view/{uid}")	// /premiere/view/uid
	public AjaxPremiereList view(@PathVariable int uid) {
		
		List<PremiereDTO> list = null;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			list = ajaxpremiereService.selectByUid(uid);
			
			if(list == null || list.size() == 0) {
				message.append("해당 데이터가 없습니다");
			}else {
				status = "OK";
			}
		}catch(Exception e) {
			e.printStackTrace();
			message.append("트랜잭션 에러 : " + e.getMessage());
		}
		
		AjaxPremiereList result = new AjaxPremiereList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
		
		return result;
	}
	
	@PostMapping("")	// /premiere
	public AjaxBoardResult writeOk(PremiereDTO dto) 
	//		, @RequestParam("files") MultipartFile file) throws Exception 
	{
		int count = 0;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			count = ajaxpremiereService.write(dto);
			if(count == 0) {
				message.append("트랜잭션 실패");
			}else {
				status = "OK";
			}
		}catch (Exception e) {
			e.printStackTrace();
			message.append("error : " + e.getMessage());
		}
		
		AjaxBoardResult result = new AjaxBoardResult();
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(count);
//		
//		String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//	    String basePath = rootPath + "/";
//	    String filePath = basePath + "/" + file.getOriginalFilename();
//	    File dest = new File(filePath);
//	    file.transferTo(dest); // 파일 업로드 작업 수행
		
		return result;
	}
	
	@PutMapping("")	// /premiere
	public AjaxBoardResult updateOk(PremiereDTO dto) {
		int count = 0;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			count = ajaxpremiereService.update(dto);
			if(count == 0) {
				message.append("트랜잭션 실패");
			}else {
				status = "OK";
			}
		}catch (Exception e) {
			e.printStackTrace();
			message.append("error : " + e.getMessage());
		}
		AjaxBoardResult result = new AjaxBoardResult();
		result.setCount(count);
		result.setMessage(message.toString());
		result.setStatus(status);
		
		return result;
	}
	
	@DeleteMapping("")	// /premiere
	public AjaxBoardResult deleteOk(int [] uid) {
		int count = 0;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			if(uid != null) {
				count = ajaxpremiereService.deleteByUid(uid);
				status = "OK";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			message.append("트랜잭션 에러" + e.getMessage());
		}
		
		AjaxBoardResult result = new AjaxBoardResult();
		result.setCount(count);
		result.setMessage(message.toString());
		result.setStatus(status);
		
		return result;
		
	}
	
	
}