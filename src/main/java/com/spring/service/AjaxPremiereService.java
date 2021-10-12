package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.CustomerDTO;
import com.spring.domain.PremiereDAO;
import com.spring.domain.PremiereDTO;
import com.spring.domain.PremiereWinDTO;

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

	// 응모정보 넣기 
	@Transactional
	public int apply(int prUid, String id, String email) {
		int cusUid = dao.findUidById(id);
		return dao.apply(prUid, cusUid, email);
	}

	// 응모한 아이디인지 확인하기 
	public int chkId(int prUid, String id) {
		return dao.chkId(prUid, id);
	}
	
	// 응모된 계정 추첨 -> 당첨자 닉네임 리턴 
	public List<PremiereWinDTO> selectWin(int prUid, int count){
		System.out.println("서비스 넘어옴");
		return dao.selectWin(prUid , count);
	}

	// 시사회 제목, uid -> select box
	public List<PremiereDTO> getTitle() {
		return dao.getTitle();
	}
	
	// 당첨자 저장 완료
	public int updateBool(int prUid, String email){
		return dao.updateBool(prUid, email);
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

























