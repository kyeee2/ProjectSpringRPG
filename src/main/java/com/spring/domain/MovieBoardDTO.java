package com.spring.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
// 영화 리뷰
public class MovieBoardDTO extends BoardDTO {

	private String nickName;		// 게시판 작성자 닉네임
	private String subject; 		// 게시판 글 카테고리
	private int goodCnt;			// 게시판 좋아요 수
	private int commentCnt;			// 게시판 댓글 수
	private int reportCnt;			// 게시판 신고 수
	
	// 기본 생성자
	public MovieBoardDTO() {
		super();
	}
	
	// 생성자
	public MovieBoardDTO(int uid, String title, String content, int viewcnt, String boardType, String nickName,
			LocalDateTime dateTime, String subject, int goodCnt, int commnetCnt, int reportCnt) {
		super(uid, title, content, viewcnt, boardType, dateTime);
		this.nickName = nickName;
		this.subject = subject;
		this.goodCnt = goodCnt;
		this.commentCnt = commnetCnt;
		this.reportCnt = reportCnt;
	}
}
