package com.spring.domain;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface MbCommentDAO {
	//댓글 목록,댓글 입력,댓글 수정,댓글 삭제

		
			//댓글 보기
			public abstract List<CommentDTO> view(int buid);
			
			// 댓글 작성
			public abstract int insert(String boardType, int buid);
			
			// 특정(회원의)uid 댓글 조회
			public abstract List<CommentDTO> selectByUid(int buid);
			
			// 댓글 수정
			public abstract int update(String boardType, int buid);
			
			// 댓글 삭제
			public abstract int deleteByUid(int buid);
}
