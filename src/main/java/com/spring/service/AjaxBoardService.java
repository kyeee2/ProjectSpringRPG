package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.BoardDTO;
import com.spring.domain.FreeBoardDAO;
import com.spring.domain.MovieBoardDAO;
import com.spring.domain.MyPageBoardDAO;
import com.spring.domain.NoticeBoardDAO;

@Service
public class AjaxBoardService {

	// 게시판 각각의 목록, 조회, 수정, 삭제용
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

	// 특정 게시판의 전체 글 개수
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

	// 특정 게시판의 검색 글 개수
	public int countSearch(String boardType, String text) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.countSearch(text);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.countSearch(text);
		} else if (boardType != null && boardType.equals("noticeboard")) {
			return nbDAO.countSearch(text);
		} else {
		}
		return 0;
	}

	// 특정 게시판의 전체 글 목록
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

	// 특정 게시판(자유게시판, 영화 리뷰)의 인기글 5개 가져오기(조회순)
	public List<BoardDTO> vogueList(String boardType) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.selectVogue();
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.selectVogue();
		} else {
			return null;
		}
	}

	// 게시판 내 검색 목록 가져오기
	public List<BoardDTO> searchList(String boardType, String text, int from, int pageRows) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.selectSearch(text, from, pageRows);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.selectSearch(text, from, pageRows);
		} else if (boardType != null && boardType.equals("noticeboard")) {
			return nbDAO.selectSearch(text, from, pageRows);
		} else {
			return null;
		}
	}

	// 특정 게시판의 특정 게시글 조회 (조회수 증가 o)
	@Transactional
	public List<BoardDTO> view(String boardType, int uid) {	// uid : 게시글uid
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

	// 특정 게시판의 특정 게시글 조회 (조회수 증가 x)
	public List<BoardDTO> read(String boardType, int uid) {	// uid : 게시글uid
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

	// 아이디로 특정 회원의 고유번호 가져오기
	public int findCusUidById(String id) {
		return fbDAO.findCusUidById(id);	// Customer DAO로 바꾸기
	}
	
	// 아이디로 특정 회원의 고유번호 가져오기
	public int findCusUidByNickName(String nickName) {
		return fbDAO.findCusUidByNickName(nickName);	// Customer DAO로 바꾸기
	}

	// 특정 게시판에 게시글 작성
	@Transactional
	public int write(BoardDTO dto) {
		
		// 작성된 닉네임으로 회원 고유번호, boardType 가져오기
		String boardType = dto.getBoardType();
		
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

	// 특정 게시판에 게시글 수정
	public int update(BoardDTO dto) {

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

	// 특정 게시판에 게시글 삭제
	@Transactional
	public int delete(String boardType, int [] uids) {	// uids : 게시글 uid들
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

	// 좋아요 눌렀는지 확인 눌렀으면 1, 아니면 0
	public int isGood(String boardType, int boardUid, int cusUid) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.chkCusUid(boardUid, cusUid);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.chkCusUid(boardUid, cusUid);
		} else {
			return 0;
		}
	}
	
	// 좋아요 증가
	public int incGoodCnt(String boardType, int boardUid, int cusUid) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.incGoodCnt(boardUid, cusUid);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.incGoodCnt(boardUid, cusUid);
		} else {
			return 0;
		}
	}
	
	// 좋아요 감소
	public int decGoodCnt(String boardType, int boardUid, int cusUid) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.decGoodCnt(boardUid, cusUid);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.decGoodCnt(boardUid, cusUid);
		} else {
			return 0;
		}
	}

	// 특정 게시글에 좋아요 수
	public int getGoodCnt(String boardType, int boardUid) {
		if(boardType != null && boardType.equals("freeboard")) {
			return fbDAO.getGoodCnt(boardUid);
		} else if (boardType != null && boardType.equals("movieboard")) {
			return mbDAO.getGoodCnt(boardUid);
		} else {
			return 0;
		}
	}

	// 마이페이지용
	MyPageBoardDAO myPageDAO;
	
	@Autowired
	public void setMyPageDAO(MyPageBoardDAO myPageDAO) {
		this.myPageDAO = myPageDAO;
	}

	// 마이페이지에서 특정 회원이 쓴 글 총 개수
	public int countMyPost(int uid) {
		return myPageDAO.countMyPost(uid);
	}
	
	// 마이페이지에서 특정 회원이 쓴 글 전체 목록 (자유게시판, 영화 리뷰 한번에)
	public List<BoardDTO> listMyPost(int uid, int from, int pageRows) {
		return myPageDAO.listMyPage(uid, from, pageRows);
	}

}
