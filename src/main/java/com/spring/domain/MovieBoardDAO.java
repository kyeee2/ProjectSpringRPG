package com.spring.domain;

import java.util.List;

public interface MovieBoardDAO {
	// 게시판 CRUD

	// 게시글 총 개수
	public abstract int count();

	// 게시글 목록
	public abstract List<BoardDTO> selectAll(int from, int pageRows);
	
	// 게시글을 조회하기 위해서는
	// 먼저 게시글의 조회수를 1 증가시키고
	// 그 다음 게시글을 불러와야한다!
	
	// 게시글 조회수 증가
	public abstract int incViewCnt(int uid);							// 조회하고자 하는 게시글의 uid
	
	// 특정 uid 게시글 조회
	public abstract List<BoardDTO> selectByUid(int uid);			// 조회하고자 하는 게시글의 uid
	
	// 닉네임으로 회원 uid 찾기
	public abstract int findCusUid(String nickName);
	
	// 게시글 작성
	public abstract int insert(BoardDTO dto);	// 로그인 된 회원의 uid

	// 게시글은 제목과 내용만 수정 가능
	// 게시글 수정
	public abstract int update(BoardDTO dto);	// 수정하는 게시글의 uid
	
	// 게시글을 삭제하기 위해서는
	// 먼저 게시글의 좋아요와 댓글을 삭제해야한다
	// 그 후 게시글을 삭제!
	
	// 특정 uid 게시글 좋아요 삭제
	public abstract int deleteGood(int [] uids);							// 삭제하는 게시글의 uid
	
	// 특정 uid 게시글 댓글 삭제
	public abstract int deleteComment(int [] uids);							// 삭제하는 게시글의 uid
	
	// 특정 uid 게시글 삭제
	public abstract int delete(int [] uids);								// 삭제하는 게시글의 uid

}
