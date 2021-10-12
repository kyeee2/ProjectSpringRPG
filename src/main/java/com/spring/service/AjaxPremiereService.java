package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.PremiereDAO;
import com.spring.domain.PremiereDTO;

@Service
public class AjaxPremiereService {
	
	PremiereDAO dao;
	
	@Autowired
	public void setDao(PremiereDAO dao) {
		this.dao = dao;
	}
	
	public int count() {
		return dao.count();
	}
	
	public List<PremiereDTO> list(int from, int pageRows){
		return dao.select(from, pageRows);
	}
	
	public List<PremiereDTO> selectByUid(int uid){
		return dao.selectByUid(uid);
	}
	
	public int write(PremiereDTO dto) {
		return dao.insert(dto);
	}

	public int update(PremiereDTO dto) {
		return dao.update(dto);
	}
	
	public int deleteByUid(int [] uids) {
		return dao.deleteByUid(uids);
	}

	public int updateNoFile(PremiereDTO dto) {
		return dao.updateNoFile(dto);
	}
	
	public List<String> getFileName(int [] uids) {
		return dao.getFileName(uids);
	}

	// 메인페이지용
	// 시사회 최근 글 3개
	public List<PremiereDTO> getThreeRecently() {
		return dao.getThreeRecently();
	}

}

























