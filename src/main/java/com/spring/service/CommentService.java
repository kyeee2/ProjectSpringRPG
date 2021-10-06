package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.CommentDTO;
import com.spring.domain.CustomerDAO;
import com.spring.domain.CustomerDTO;
import com.spring.domain.FbCommentDAO;
import com.spring.domain.MbCommentDAO;

@Service
public class CommentService {
	
	FbCommentDAO fbComDAO;
	MbCommentDAO mbComDAO;
	
	
	@Autowired
	public void setfbDao(FbCommentDAO fbDAO) {
		this.fbComDAO = fbDAO;
	}

	@Autowired
	public void setmbDAO(MbCommentDAO mbDAO) {
		this.mbComDAO = mbDAO;
	}
	
	
	
	//리스트,보기 작성 수정 삭제 조건은 게시글 uid 비교.

	//댓글 보기
	@Transactional
	public List<CommentDTO> view(String boardType, int buid) {
		
		if(boardType != null && boardType.equals("freeboard")) {
			return fbComDAO.view(buid);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbComDAO.view(buid);
		}  else {
			return null;
		}
	}
	
	//댓글작성
	@Transactional
	public int insert(CommentDTO dto) {
		String boardType =dto.getBoardType();
		
		if(boardType != null && boardType.equals("freeboard")) {
			return fbComDAO.insert(dto);
		} 
	
		else if (boardType != null && boardType.equals("movieboard")) {
			return mbComDAO.insert(dto);
		} else {
			return 0;
		}
	}
	
	//댓글수정

	public int update(CommentDTO dto) { 
		String boardType =dto.getBoardType();
		if(boardType != null && boardType.equals("freeboard")) {
			return fbComDAO.update(dto);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbComDAO.update(dto);
		} else {
			return 0;
		}
	}
	
	//댓글삭제

	public int delete(String boardType, int uid []) { 
		if(boardType != null && boardType.equals("freeboard")) {
			return fbComDAO.deleteByUid(uid);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbComDAO.deleteByUid(uid);
		} else { 
			return 0;
		}
	}
	
	// 회원의 uid 찾기
	public int findCusUidById(String id) {
		int uid = fbComDAO.findByUid(id);
		
		return uid;
	}	
}
