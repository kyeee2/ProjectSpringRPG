package com.spring.domain;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface FbCommentDAO {
	//댓글 목록,댓글 입력,댓글 수정,댓글 삭제
		
			//댓글 보기
			public abstract List<CommentDTO> view(int buid);
			
			// 댓글 작성
			public abstract int insert(CommentDTO dto);
			
			// 특정(회원의)uid 댓글 조회
			public abstract List<CommentDTO> selectByUid(int buid);
			
			// 댓글 수정
			public abstract int update(CommentDTO dto);
			
			// 닉네임으로 회원 uid 찾기 (쿼리문 사용 용도)
			public abstract int findCusUid(String nickName);
			
			// 댓글 삭제
			public abstract int deleteByUid(String boardType, int[] uid);
		
			// 특정 회원의 uid 가져오기
			public abstract int findByUid(String nickName);
			
}
