package com.spring.domain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface CustomerDAO {
	
	// 사용자 추가
		int addUser(CustomerDTO user);
		
		// 사용자 권한 추가
		int addAuth(String auth, String id);
		
		// 사용자 삭제
		int deleteUser(String id);
		
		int updatePw(String pw, int uid);
		
		int changePw(String pw, String id, String name, String phonenum);
		// 특정 사용자 권한 삭제
		int deleteAuth(String id, String auth);
		
		// 특정 사용자 권한(들) 전부 삭제
		int deleteAuths(String id);
		
		//유저정보 수정
		int updateUser(String phonenum, String nickname, int uid);
		
		//유저 정보 가져오기(uid)
		List<CustomerDTO> selectByUid(int uid);
		
		// 특정 id (username) 의 사용자 찾기
		CustomerDTO findById(String id);
		
		// 특정 id (username) 의 권한(들) 뽑기
		List<String> selectAuthoritiesById(String id);
		public int count();
		
		public List<String> idChk(String id) throws Exception;
		
		public List<CustomerDTO> selectByUidAll(int from, int pageRows);
		public List<String> nickChk(String nickname) throws Exception;
		
		public String findNameByPhonenum(String phonenum); 
		
		public String findID(String name, String phonenum) throws Exception;
//		int insert(int uid, List<String> originalFileNames, List<String> fileSystemNames);
		public List<CustomerDTO> findPW(String pw,String name, String phonenum, String id) throws Exception;
//		
//		int deleteFileByUid(int uid,HttpServletRequest request);
//		
//		List<FileDTO> selectFileByUid(int uid);

		// 마이페이지 수정
		// 프로필 사진도 수정한 경우
		int update(CustomerDTO dto);

		// 프로필사진은 수정하지 않은 경우
		int updateNoFile(CustomerDTO dto);
		
		
		
		
		
}
