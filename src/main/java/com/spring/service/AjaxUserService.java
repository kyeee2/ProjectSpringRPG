package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.CustomerDAO;
import com.spring.domain.CustomerDTO;
@Service
public class AjaxUserService {
	CustomerDAO dao;
	
	@Autowired
	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}
	
	public AjaxUserService() {
		System.out.println("ajaxuserservice() 생성");
	}
	
	public List<CustomerDTO> list(int from, int pageRows) {
		System.out.println("유저서비스에 걸렸니?");
		return dao.selectByUidAll(from, pageRows);
	}
	public int count() {
		System.out.println("유저카운트에 걸렸니?");
			return dao.count();
	}
}
