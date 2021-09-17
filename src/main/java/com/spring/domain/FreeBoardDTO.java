package com.spring.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
// 자유게시판
public class FreeBoardDTO extends BoardDTO {

	private String nickName;
	private int goodCnt;	// 게시판 좋아요 수
	private int commentCnt;	// 게시판 댓글 수
	private int reportCnt;	// 게시판 신고 수
	
	// 기본 생성자
	public FreeBoardDTO() {
		super();
	}
	
	// 생성자
	public FreeBoardDTO(int uid, String title, String content, int viewcnt, String boardType, String nickName,
			LocalDateTime dateTime, int goodCnt, int commnetCnt, int reportCnt) {
		super(uid, title, content, viewcnt, boardType, dateTime);
		this.nickName = nickName;
		this.goodCnt = goodCnt;
		this.commentCnt = commnetCnt;
		this.reportCnt = reportCnt;
	}
	
}
