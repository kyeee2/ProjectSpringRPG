package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.AjaxCommentList;
import com.spring.domain.AjaxCommentResult;
import com.spring.domain.CommentDTO;
import com.spring.service.CommentService;

// mapping 시작 /comment로 통일

@RestController
@RequestMapping("/comment")
public class AjaxCommentController {
	CommentService commentservice;
	
	@Autowired
	public void setACommentService(CommentService commentservice) {
		this.commentservice = commentservice;
	}
	
	
	@GetMapping("/view/{boardType}/{buid}")	// URI : /view/boardType/uid
	public AjaxCommentList view(@PathVariable String boardType, @PathVariable(value = "buid") int buid) {
		
		List<CommentDTO> list = null;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		

		
		try {
			list = commentservice.view(boardType, buid);
			
			if(list == null) {
				message.append("[리스트할 데이터가 없습니다.]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}
		
		AjaxCommentList cresult = new AjaxCommentList();
		
		cresult.setStatus(status);
		cresult.setMessage(message.toString());
		
		if(list != null) {
			cresult.setCount(list.size());	
			cresult.setList(list);
		}
		System.out.println(cresult.getCount());
		
		return cresult;
		
	} // end view(boardType, uid)
	

	@PostMapping("/writeOk")
	public AjaxCommentResult writeOk(@Valid CommentDTO dto 
			, BindingResult bresult
			, Model model) {	
		
		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {

			count = commentservice.insert(dto);
			
			if(count == 0) {
				message.append("[트랜잭션 실패 : 0 INSERT]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}
		
		AjaxCommentResult cresult = new AjaxCommentResult();
		
		cresult.setStatus(status);
		cresult.setMessage(message.toString());
		cresult.setCount(count);
		
		return cresult;
		
	} // end writeOk()

	@PostMapping("/updateOk")
	public AjaxCommentResult updateOk(@Valid CommentDTO dto, BindingResult bresult,
			 Model model) {

		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			count = commentservice.update(dto);
			
			if(count == 0) {
				message.append("[트랜잭션 실패 : 0 UPDATE]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}
		
		AjaxCommentResult cresult = new AjaxCommentResult();
		
		cresult.setStatus(status);
		cresult.setMessage(message.toString());
		cresult.setCount(count);
		
		return cresult;
		
	} // end updateOk()
	
	
	@DeleteMapping("/deleteOk")
	public AjaxCommentResult deleteOk(String boardType, int [] uid) {
		
		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {

			count = commentservice.delete(boardType, uid);
			
			if(count == 0) {
				message.append("[트랜잭션 실패 : 0 DELETE]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}

		AjaxCommentResult cresult = new AjaxCommentResult();
		
		cresult.setStatus(status);
		cresult.setMessage(message.toString());
		cresult.setCount(count);
		
		return cresult;
		
	} // end deleteOk(CommentDTO, buid, uid)
	
}
