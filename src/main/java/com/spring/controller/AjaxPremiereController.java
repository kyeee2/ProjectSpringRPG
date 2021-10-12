package com.spring.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.io.File;
//import javax.swing.filechooser.FileSystemView;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@DeleteMapping("")	// /premiere
	public AjaxBoardResult deleteOk(HttpServletRequest request,
			HttpServletResponse response, 
			int [] uid) {
		
		int count = 0;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			List<String> filenames = null;
			
			if(uid != null) {
				filenames = ajaxpremiereService.getFileName(uid);
				count = ajaxpremiereService.deleteByUid(uid);
				status = "OK";
			}
			
			// 이미지를 업로드할 디렉토리(배포 디렉토리로 설정)
			ServletContext context = request.getServletContext();
			String saveDirectory = context.getRealPath("/file/premiere"); // 무조건 여기 폴더에 저장
			
			for(int i = 0; i < filenames.size(); i++) {
				System.out.println(filenames.get(i));
				// 원래 파일 삭제하기
				File oldFile = new File(saveDirectory + File.separator + filenames.get(i));
				System.out.println(oldFile.getPath());
				oldFile.delete();
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
