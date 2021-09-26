package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.AjaxBoardList;
import com.spring.domain.AjaxBoardResult;
import com.spring.domain.BoardDTO;
import com.spring.service.AjaxBoardService;

@RestController
public class AjaxBoardController {
	
	AjaxBoardService ajaxBoardService;

	@Autowired
	public void setAjaxBoardService(AjaxBoardService ajaxBoardService) {
		this.ajaxBoardService = ajaxBoardService;
	}
	
	@GetMapping("/list/{boardType}/{page}/{pageRows}")	// URI : /list/boardType/page/pageRows
	public AjaxBoardList list(
			@PathVariable String boardType, 
			@PathVariable int page, 
			@PathVariable int pageRows) {
		
		List<BoardDTO> list = null;
		
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
			// 글 전체 개수 구하기
			totalCnt = ajaxBoardService.count(boardType);
			
			// 총 몇 페이지 분량?
			totalPage = (int)Math.ceil(totalCnt / (double)pageRows);
			
			// from : 몇 번째 row 부터?
			int from = (page - 1) * pageRows;	// MySQL 의 LIMIT 는 0-base
			
			list = ajaxBoardService.list(boardType, from, pageRows);
			
			if(list == null) {
				message.append("[리스트할 데이터가 없습니다.]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}

		AjaxBoardList result = new AjaxBoardList();
		
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
		
	} // end list(boardType, page, pageRows)
	
	@GetMapping("/view/{boardType}/{uid}")	// URI : /view/boardType/uid
	public AjaxBoardList view(@PathVariable String boardType, @PathVariable int uid) {
		
		List<BoardDTO> list = null;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			list = ajaxBoardService.view(boardType, uid);
			
			if(list == null) {
				message.append("[리스트할 데이터가 없습니다.]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}
		
		AjaxBoardList result = new AjaxBoardList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());	
			result.setList(list);
		}
		
		return result;
		
	} // end view(boardType, uid)
	
	@GetMapping("/read/{boardType}/{uid}")	// URI : /read/boardType/uid
	public AjaxBoardList read(@PathVariable String boardType, @PathVariable int uid) {
		
		List<BoardDTO> list = null;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			list = ajaxBoardService.read(boardType, uid);
			
			if(list == null) {
				message.append("[리스트할 데이터가 없습니다.]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}
		
		AjaxBoardList result = new AjaxBoardList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
		
		return result;
		
	} // end view(boardType, uid)
	
	@PostMapping("")
	public AjaxBoardResult writeOk(BoardDTO dto) {	// dto에는 boardType, nickName, title, content 가 저장되어있다.
		
		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			count = ajaxBoardService.write(dto);
			
			if(count == 0) {
				message.append("[트랜잭션 실패 : 0 INSERT]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}
		
		AjaxBoardResult result = new AjaxBoardResult();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(count);
		
		return result;
		
	} // end writeOk()
	
	@PutMapping("")
	public AjaxBoardResult updateOk(BoardDTO dto) {

		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			count = ajaxBoardService.update(dto);
			
			if(count == 0) {
				message.append("[트랜잭션 실패 : 0 UPDATE]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}
		
		AjaxBoardResult result = new AjaxBoardResult();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(count);
		
		return result;
		
	} // end updateOk()
	
	@DeleteMapping("")
	public AjaxBoardResult deleteOk(String boardType, int [] uid) {
		
		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {

			count = ajaxBoardService.delete(boardType, uid);
			
			if(count == 0) {
				message.append("[트랜잭션 실패 : 0 DELETE]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
		}

		AjaxBoardResult result = new AjaxBoardResult();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(count);
		
		return result;
		
	} // end deleteOk(boardType, uid[])
	
}
