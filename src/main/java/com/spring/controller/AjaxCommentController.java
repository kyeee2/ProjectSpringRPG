package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
			System.out.println(list.size());
			
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
	
	
	@PostMapping("/writeOK/{boardType}/{uid}")
	public AjaxCommentResult writeOk(@PathVariable String boardType, @PathVariable(value="uid") int buid) {	
		
		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			count = commentservice.insert(boardType, buid);
			
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
	
	@PutMapping("/updateOK/{boardType}/{uid}")
	public AjaxCommentResult updateOk(String boardType, int uid) {

		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			count = commentservice.update(boardType, uid);
			
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
	
	@DeleteMapping("/deleteOK/{boardType}/{uid}")
	public AjaxCommentResult deleteOk(String boardType, int buid) {
		
		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {

			count = commentservice.delete(boardType, buid);
			
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
		
	} // end deleteOk(CommentDTO, uid)
	
}
