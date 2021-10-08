package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.BoardValidator;
import com.spring.config.PrincipalDetails;
import com.spring.domain.AjaxBoardList;
import com.spring.domain.AjaxBoardResult;
import com.spring.domain.BoardDTO;
import com.spring.service.AjaxBoardService;

@RestController
@RequestMapping("/board")
public class AjaxBoardController {
	
	AjaxBoardService ajaxBoardService;

	@Autowired
	public void setAjaxBoardService(AjaxBoardService ajaxBoardService) {
		this.ajaxBoardService = ajaxBoardService;
	}
	
	// 자유게시판, 영화리뷰, 공지사항에 각각 목록을 출력
	@GetMapping("/list/{boardType}/{page}/{pageRows}")	// URI : /board/list/boardType/page/pageRows
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
			
			// page 와 pageRows의 유효성 검사
//			if(page < 0 || page > totalCnt / pageRows) {	// page 유효성 검사
//				
//			}
			
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
	
	// 특정 게시판(자유게시판, 영화 리뷰)의 인기글 5개 가져오기(조회순)
	@GetMapping("/vogueList/{boardType}")	// URI : /board/vogueList/boardType
	public AjaxBoardList vogueList(@PathVariable String boardType) {
		List<BoardDTO> list = null;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			list = ajaxBoardService.vogueList(boardType);
			
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
	}	// end vogueList(boardType)
	
	// 특정 게시판 검색 목록
	@PostMapping("/searchList/{boardType}/{page}/{pageRows}")	// URI : /board/searchList/boardType/page/pageRows
	public AjaxBoardList searchList(@PathVariable String boardType,
			@PathVariable int page, 
			@PathVariable int pageRows,
			String text) {	// 검색내용은 바디로 전송

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
			totalCnt = ajaxBoardService.countSearch(boardType, text);
			
			// page 와 pageRows의 유효성 검사
//			if(page < 0 || page > totalCnt / pageRows) {	// page 유효성 검사
//				
//			}
			
			// 총 몇 페이지 분량?
			totalPage = (int)Math.ceil(totalCnt / (double)pageRows);
			
			// from : 몇 번째 row 부터?
			int from = (page - 1) * pageRows;	// MySQL 의 LIMIT 는 0-base
			
			list = ajaxBoardService.searchList(boardType, text, from, pageRows);
			
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
	}	// end vogueList(boardType)
	
	// 특정 게시글 조회 - viewcnt 증가 o
	@GetMapping("/view/{boardType}/{uid}")	// URI : /board/view/boardType/uid
	public AjaxBoardList view(@PathVariable String boardType, 
			@PathVariable int uid) {
		
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
	
	// 특정 게시글 읽기 (수정화면에 필요) - viewcnt 증가 x
	@GetMapping("/read/{boardType}/{uid}")	// URI : /board/read/boardType/uid
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
	
	// 게시글 작성
	@PostMapping("/write")	// URI :  /board
	public AjaxBoardResult writeOk(@Valid BoardDTO dto	// title, content 검증
			, Authentication authentication	// 로그인한 회원 정보
			, BindingResult bindResult	// validation 결과 담겨있음
			) {
		
		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		// 유효성 검사 결과 먼저 확인
		if(bindResult.hasErrors()) {
			// 에러가 존재한다면
			if(bindResult.getFieldError("title") != null) {
				message.append("제목은 필수입니다.");
			} else if(bindResult.getFieldError("content") != null) {
				message.append("내용은 필수입니다.");
			}
			status = "HOLD";	// 유효성 검사에서 걸리면 hold해서 표시해주기
		} else {
			try {
				// 유효성 검사가 통과하면 insert 실행하기
				
				// 로그인 정보에서 아이디 가져오기
				PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
		        String id = userDetails.getUsername();	// 아이디 뽑아내기
		        
		        // 아이디로 특정 회원의 고유번호 찾아서 dto에 세팅해주기
//		        int uid = ajaxBoardService.findCusUidById(id);
		        int uid = userDetails.getUid();
		        dto.setCusUid(uid);
		        
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
		}
		
		AjaxBoardResult result = new AjaxBoardResult();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(count);
		
		return result;
		
	} // end writeOk()
	
	// 게시글 수정
	@PostMapping("/update")	// URI : /board
	public AjaxBoardResult updateOk(@Valid BoardDTO dto	// title, content 검증
			, Authentication authentication
			, BindingResult bindResult	// validation 결과 담겨있음
			) {

		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		// 유효성 검사 결과 먼저 확인
		if(bindResult.hasErrors()) {
			// 에러가 존재한다면
			if(bindResult.getFieldError("title") != null) {
				message.append("제목은 필수입니다.");
			} else if(bindResult.getFieldError("content") != null) {
				message.append("내용은 필수입니다.");
			}
			status = "HOLD";	// 유효성 검사에서 걸리면 hold해서 폼에 표시해주기
		} else {
			try {
				// 유효성 검사가 통과하면 update 실행하기
				System.out.println("수정사항 dto=" + dto.toString());
				
				// 로그인 정보에서 아이디 가져오기
				PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
		        String id = userDetails.getUsername();
		        
		        // 아이디로 특정 회원의 고유번호 찾기
		        int findUidById = ajaxBoardService.findCusUidById(id);
		        int findUidByNickName = ajaxBoardService.findCusUidByNickName(dto.getNickName());
		        
		        if(findUidById == findUidByNickName) {
		        	count = ajaxBoardService.update(dto);
		        }
				
				if(count == 0) {
					message.append("[트랜잭션 실패 : 0 UPDATE]");
				} else {
					status = "OK";
				}
			} catch(Exception e) {
				e.printStackTrace();
				message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
			}
		}
		
		AjaxBoardResult result = new AjaxBoardResult();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(count);
		
		return result;
		
	} // end updateOk()
	
	// 게시글 삭제
	@PostMapping("/delete")	// URI :  /board/delete
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
	
	// 좋아요
	@GetMapping("/good/{boardType}/{boardUid}")	
	public AjaxBoardResult goodOk(@PathVariable String boardType,
				@PathVariable int boardUid,
				Authentication authentication) {

		int count = 0;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {

			// 로그인 정보에서 아이디 가져오기
			PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
	        String id = userDetails.getUsername();
	        
	        // 아이디로 특정 회원의 고유번호 찾기
	        int cusUid = ajaxBoardService.findCusUidById(id);
			
	        if(ajaxBoardService.isGood(boardType, boardUid, cusUid) == 0) {
	        	// 좋아요를 안눌렀다면
	        	count = ajaxBoardService.incGoodCnt(boardType, boardUid, cusUid);

				if(count == 0) {
					message.append("[트랜잭션 실패 : 0 DELETE]");
				} else {
					status = "OK";
					message.append(ajaxBoardService.getGoodCnt(boardType, boardUid));	// message에 현재 좋아요 수 담기
				}
	        } else {
	        	// 좋아요를 눌렀다면
	        	count = ajaxBoardService.decGoodCnt(boardType, boardUid, cusUid);
	        	
				if(count == 0) {
					message.append("[트랜잭션 실패 : 0 DELETE]");
				} else {
					status = "OK";
					message.append(ajaxBoardService.getGoodCnt(boardType, boardUid));	// message에 현재 좋아요 수 담기
				}
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
		
	}
	
	// 마이페이지용
	
//	// 마이페이지에서 특정 회원이 쓴 글 전체 목록 (자유게시판, 영화 리뷰 한번에)
	@GetMapping("/list/myPage/{page}/{pageRows}")	// URI :  /board/list/myPage/page/pageRows
	public AjaxBoardList myPageList(
			@PathVariable int page, 
			@PathVariable int pageRows,
			Authentication authentication) {
		
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

			// 로그인 정보에서 아이디 가져오기
			PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
	        String id = userDetails.getUsername();
	        
	        // 아이디로 특정 회원의 고유번호 찾기
	        int cusUid = userDetails.getUid();
			
			
			// 게시글 총 개수 구하기
			totalCnt = ajaxBoardService.countMyPost(cusUid);
			
			// page 와 pageRows의 유효성 검사
//			if(page < 0 || page > totalCnt / pageRows) {	// page 유효성 검사
//				
//			}
			
			// 총 몇 페이지 분량?
			totalPage = (int)Math.ceil(totalCnt / (double)pageRows);
			
			// from : 몇 번째 row 부터?
			int from = (page - 1) * pageRows;	// MySQL 의 LIMIT 는 0-base
			
			list = ajaxBoardService.listMyPost(cusUid, from, pageRows);
			
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
		
	} // end myPageList(page, pageRows)
	

	// 이 컨트롤러 클래스가 handler에서 폼 데이터를 바인딩할 때 검증하는 Validator 객체 지정
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new BoardValidator());
	}
	
}
