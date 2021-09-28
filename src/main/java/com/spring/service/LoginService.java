package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.CustomerDAO;
import com.spring.domain.CustomerDTO;

@Service
public class LoginService {
	
	CustomerDAO dao;
	
	@Autowired
	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}
	
	public LoginService() {
		System.out.println("LoginService() 생성");
		
	}
	
	@Transactional
	public int addMember(CustomerDTO user) {
		int cnt = dao.addUser(user);
		dao.addAuth(user.getId(), "ROLE_MEMBER");
		return cnt;
	}
	
	// 회원삭제
	@Transactional
	public int deleteMember(CustomerDTO user) {
		dao.deleteAuths(user.getId());  // 권한(들) 먼저 삭제
		int cnt = dao.deleteUser(user);
		return cnt;
	}
	
	// 특정 id(username) 의 정보 가져오기
	public CustomerDTO findById(String id) {
		return dao.findById(id);
	}
	
	// 특정 id 의 권한(들) 정보 가져오기
	public List<String> selectAuthoritiesById(String id){
		return dao.selectAuthoritiesById(id);
	}
}


