package com.spring.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 댓글 공통
public class CommentDTO {
	@JsonProperty("uid")
	private int uid;				// 댓글 고유번호
	@JsonProperty("content")
	private String content;			// 댓글 내용
	@JsonProperty("nickName")
	private String nickName;		// 댓글 작성자
	@JsonProperty("dateTime")
	private LocalDateTime datetime; // 댓글 작성일
	
	private int cusuid; 			// 작성자 고유번호
	private String boardType;		// 게시판별 댓글(자유,리뷰)
	
	@JsonProperty("buid")
	private int buid; 				// 게시물 고유번호
	
	public String getDatetime() {	// 이름 소문자로 통일하기 위해서
		if(this.datetime == null) {
			return "";
		}
		return this.datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
}
