package com.spring.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.spring.BoardValidator;
import com.spring.domain.BoardDTO;
import com.spring.domain.FreeBoardDAO;
import com.spring.domain.MovieBoardDAO;
import com.spring.domain.NoticeBoardDAO;

@Service
public class AjaxBoardService {

	FreeBoardDAO fbDAO;
	MovieBoardDAO mbDAO;
	NoticeBoardDAO nbDAO;
	
	@Autowired
	public void setFbDao(FreeBoardDAO fbDAO) {
		this.fbDAO = fbDAO;
	}

	@Autowired
	public void setMbDAO(MovieBoardDAO mbDAO) {
		this.mbDAO = mbDAO;
	}
	
	@Autowired
	public void setNbDAO(NoticeBoardDAO nbDAO) {
		this.nbDAO = nbDAO;
	}

	public int count(String boardType) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.count();
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.count();
		} else if (boardType != null && boardType.equals("noticeboard")) {
			return nbDAO.count();
		} else {
			return 0;
		}
	}

	public List<BoardDTO> list(String boardType, int from, int pageRows) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.selectAll(from, pageRows);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.selectAll(from, pageRows);
		} else if (boardType != null && boardType.equals("noticeboard")) {
			return nbDAO.selectAll(from, pageRows);
		} else {
			return null;
		}
	}

	@Transactional
	public List<BoardDTO> view(String boardType, int uid) {
		if(boardType != null && boardType.equals("freeboard")) {
			fbDAO.incViewCnt(uid);
			return fbDAO.selectByUid(uid);
		} else if (boardType != null && boardType.equals("movieboard")) {
			mbDAO.incViewCnt(uid);
			return mbDAO.selectByUid(uid);
		} else if (boardType != null && boardType.equals("noticeboard")) {
			nbDAO.incViewCnt(uid);
			return nbDAO.selectByUid(uid);
		} else {
			return null;
		}
	}

	public List<BoardDTO> read(String boardType, int uid) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.selectByUid(uid);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.selectByUid(uid);
		} else if (boardType != null && boardType.equals("noticeboard")) {
			return nbDAO.selectByUid(uid);
		} else {
			return null;
		}
	}

	@Transactional
	public int write(BoardDTO dto) {
		
		// 작성된 닉네임으로 회원 고유번호, boardType 가져오기
		String boardType = dto.getBoardType();
		int cusUid = fbDAO.findCusUid(dto.getNickName());
		dto.setCusUid(cusUid);
		
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.insert(dto);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.insert(dto);
		} else if (boardType != null && boardType.equals("noticeboard")) {
			return nbDAO.insert(dto);
		} else {
			return 0;
		}
	}

	public int update(BoardDTO dto) {

		// 유효성 검사가 끝나면 update 실행하기
		
		// dto에서 boardType 가져오기
		String boardType = dto.getBoardType();
		
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.update(dto);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.update(dto);
		} else if (boardType != null && boardType.equals("noticeboard")) {
			return nbDAO.update(dto);
		} else {
			return 0;
		}
	}

	@Transactional
	public int delete(String boardType, int [] uids) {
		if(boardType != null && boardType.equals("freeboard")) {
			fbDAO.deleteGood(uids);
			fbDAO.deleteComment(uids);
			return fbDAO.delete(uids);
		} else if (boardType != null && boardType.equals("movieboard")) {
			mbDAO.deleteGood(uids);
			mbDAO.deleteComment(uids);
			return mbDAO.delete(uids);
		} else if (boardType != null && boardType.equals("noticeboard")) {
			return nbDAO.delete(uids);
		} else {
			return 0;
		}
	}
	
}
