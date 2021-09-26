package com.spring.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
// 게시판 공통
public class BoardDTO {
	private int uid;				// 게시판 고유번호
	private int cusUid;				// 작성자 고유번호
	private String title;			// 게시판 제목
	private String content;			// 게시판 내용
	@JsonProperty("viewcnt")
	private int viewCnt;			// 게시판 조회수
	@JsonProperty("boardtype")
	private String boardType;		// 게시판 종류 (자유게시판, 영화 리뷰, 공지사항, 시사회 당첨자)
	private LocalDateTime date;		// 게시판 작성일

	// 자유게시판, 영화 리뷰에 추가적으로 필요한 데이타
	@JsonProperty("nickname")
	private String nickName;		// 게시판 작성자 닉네임
	private String subject; 		// 게시판 글 카테고리
	@JsonProperty("goodcnt")
	private int goodCnt;			// 게시판 좋아요 수
	@JsonProperty("commentcnt")
	private int commentCnt;			// 게시판 댓글 수
	@JsonProperty("reportcnt")
	private int reportCnt;			// 게시판 신고 수
	
	public String getDatetime() {	// 이름 소문자로 통일하기 위해서
		if(this.date == null) {
			return "";
		}
		return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
