package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.config.PrincipalDetails;
import com.spring.domain.AjaxBoardList;
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
		
		return cresult;
		
	} // end view(boardType, uid)
	
	//마이페이지용 목록 출력
	@GetMapping("/list/myPage/{boardType}/{page}/{pageRows}")	// URI : /view/boardType/uid
	public AjaxCommentList list(
			@PathVariable int page,
			@PathVariable int pageRows,
			Authentication authentication) {
		List<CommentDTO> list = null;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		// 페이징 관련 세팅 값들
				//page : 현재 페이지
				//pageRows : 한 '페이지'에 몇개의 글을 리스트 할것인가?
				int writePages = 10;    // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
				int totalPage = 0; // 총 몇 '페이지' 분량인가? 
				int totalCnt = 0;  // 글은 총 몇개인가?
		try {
			

			// 로그인 정보에서 아이디 가져오기
			PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
	        
	        // 아이디로 특정 회원의 고유번호 찾기
	        int uid = userDetails.getUid();
	        
			//댓글 전체 개수 구하기
			totalCnt = commentservice.countMyComment(uid);
			
			// 총 몇 페이지 분량?
						totalPage = (int)Math.ceil(totalCnt / (double)pageRows);
						
						// from : 몇 번째 row 부터?
						int from = (page - 1) * pageRows;	// MySQL 의 LIMIT 는 0-base
						list = commentservice.listMyCo(uid, from, pageRows);
						System.out.println(list.toString());
						if(list == null) {
							message.append("[리스트할 데이터가 없습니다.]");
						} else {
							status = "OK";
						}
					} catch(Exception e) {
						e.printStackTrace();
						message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
					}

					AjaxCommentList result = new AjaxCommentList();
					
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
					result.setTotalCnt(totalCnt);
					
					return result;
	} // end commentlist
	

	@PostMapping("/writeOk")
	public AjaxCommentResult writeOk(@Valid CommentDTO dto 
			 , Authentication authentication
			, BindingResult bresult
			, Model model) {	
		
		// 유효성 검사 결과 먼저 확인
				if(bresult.hasErrors()) {
					// 에러가 존재한다면
					
				}
		
		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			// 로그인 정보에서 아이디 가져오기
			if(authentication != null) {
				
            PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
              String id = userDetails.getUsername();   // 아이디 뽑아내기
              // 아이디로 특정 회원의 고유번호 찾아서 dto에 세팅해주기
              int uid = commentservice.findCusUidById(id);
              dto.setCusuid(uid);
			}
            dto.toString();

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
	
	
	@PostMapping("/deleteOk")
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
