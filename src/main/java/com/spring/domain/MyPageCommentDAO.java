package com.spring.domain;

import java.util.List;

public interface MyPageCommentDAO {
	//특정 회원이 쓴 댓글 총 개수 
	public abstract int countMyComment(int uid);
	
	//특정 회원이 쓴 전체 댓글 목록
	public abstract List<CommentDTO> listMyCo(int uid,int from, int pageRows);
	
}


