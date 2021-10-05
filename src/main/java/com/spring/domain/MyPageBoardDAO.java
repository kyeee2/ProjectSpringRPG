package com.spring.domain;

import java.util.List;

public interface MyPageBoardDAO {
	
	// 마이페이지
	// 특정 회원이 쓴 게시글 총 개수
	public abstract int countMyPost(int uid);
	
	// 특정 회원이 쓴 전체 게시글 목록
	public abstract List<BoardDTO> listMyPage(int uid, int from, int pageRows);
}
