package com.spring.domain;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface CustomerDAO {
	
	// 사용자 추가
		int addUser(CustomerDTO user);
		
		// 사용자 권한 추가
		int addAuth(String id, String auth);
		
		// 사용자 삭제
		int deleteUser(CustomerDTO user);
		
		// 특정 사용자 권한 삭제
		int deleteAuth(String id, String auth);
		
		// 특정 사용자 권한(들) 전부 삭제
		int deleteAuths(String id);
		
		// 특정 id (username) 의 사용자 찾기
		CustomerDTO findById(String id);
		
		// 특정 id (username) 의 권한(들) 뽑기
		List<String> selectAuthoritiesById(String id);
		
}
