package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.PremiereDAO;
import com.spring.domain.PremiereDTO;

@Service
public class PremiereService {
	
	PremiereDAO dao;
	
	@Autowired
	public void setDao(PremiereDAO dao) {
		this.dao = dao;
	}
	
	public PremiereService() {
		System.out.println("PremiereService() 생성");
	}
	
	public List<PremiereDTO> list(){
		return dao.select();
	}
	
	public int write(PremiereDTO dto) {
		return dao.insert(dto);
	}
	
	public List<PremiereDTO> selectByUid(int uid){
		return dao.selectByUid(uid);
	}

	public int update(PremiereDTO dto) {
		return dao.update(dto);
	}
	
	public int deleteByUid(int uid) {
		return dao.deleteByUid(uid);
	}
}

























