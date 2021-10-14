package com.spring.controller;
import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.config.PrincipalDetails;
import com.spring.domain.AjaxBoardResult;
import com.spring.domain.AjaxPremiereList;
import com.spring.domain.AjaxPremiereWinList;
import com.spring.domain.AjaxPremiereWinnerList;
import com.spring.domain.PremiereDTO;
import com.spring.domain.PremiereWinDTO;
import com.spring.domain.PremiereWinListDTO;
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
	
	// 응모하기
	@PostMapping("/apply")	// URI: /premiere/apply
	public AjaxBoardResult apply(int prUid, String id, String email,	// 응모할 때 아이디와 이메일을 적는다.
			Authentication authentication	// 로그인한 회원 정보
			) {	
		int count = 0;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			// 로그인 정보에서 아이디 가져오기
            PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
            String loginId = userDetails.getUsername();   // 아이디 뽑아내기

            if(id != null && id.equals(loginId)) {
            	
            	// 같은 아이디로 응모 했는지 체크
            	if(ajaxpremiereService.chkId(prUid, id) == 0) {	// 원래는 0 아니면 1
            		// 응모 안했다면 응모 진행
	            	count = ajaxpremiereService.apply(prUid, id, email);
	            	
	            	if(count == 1) {
	            		status = "OK";
	            	} else {
	            		message.append("INSERT 실패");
	            	}
            	} else {
            		// 응모 했으면 이미 응모됐다고 알림
            		message.append("이미 응모 완료했습니다.");            	}
            } else {
            	// 아이디가 일치하지 않음
            	message.append("아이디가 일치하지 않습니다.");
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
	
	// 시사회 당첨자 추첨 
	@GetMapping("/win/{prUid}/{count}")
	public AjaxPremiereWinList selectWin(@PathVariable int prUid, @PathVariable int count) {
		
		List<PremiereWinDTO> list = null;
		
		// message
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			
			// 추첨한 결과 얻어오기 
			list = ajaxpremiereService.selectWin(prUid, count);
			System.out.println(list);
			if(list == null || list.size() == 0) {
				message.append("해당 데이터가 없습니다");
			}else {
				status = "OK";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		AjaxPremiereWinList result = new AjaxPremiereWinList();
		
		if(list != null && list.size() != 0) {
			result.setList(list);
			result.setCount(list.size());
		}
		result.setCount(count);
		result.setMessage(message.toString());
		result.setStatus(status);
		
		return result;
		
	}
	
	// 시사회 당첨자 리스트 
	@GetMapping("/premiereWin/{page}/{pageRows}")	// /premiere/list/page/pageRows
	public AjaxPremiereWinnerList winList(
			@PathVariable int page,
			@PathVariable int pageRows) {
		List<PremiereWinListDTO> list = null;
		
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
			
			list = ajaxpremiereService.winList(from, pageRows);
			
			if(list == null) {
				message.append("[리스트 할 데이터가 없습니다.");
			}else {
				status = "OK";
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			message.append("트랜잭션 에러 : " + e.getMessage());
		}
		
		AjaxPremiereWinnerList result = new AjaxPremiereWinnerList();
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
	
	@GetMapping("/premiereWin/view/{uid}")	// /premiere/view/uid
	public AjaxPremiereWinnerList winView(@PathVariable int uid) {
		
		List<PremiereWinListDTO> list = null;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			list = ajaxpremiereService.selectWinView(uid);
			
			if(list == null || list.size() == 0) {
				message.append("해당 데이터가 없습니다");
			}else {
				status = "OK";
			}
		}catch(Exception e) {
			e.printStackTrace();
			message.append("트랜잭션 에러 : " + e.getMessage());
		}
		
		AjaxPremiereWinnerList result = new AjaxPremiereWinnerList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
		
		return result;
	}
	
}

	
	
	

