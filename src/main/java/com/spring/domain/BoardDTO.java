package com.spring.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
// 게시판 공통
public class BoardDTO {
	private int uid;				// 게시판 고유번호
	private String title;			// 게시판 제목
	private String content;			// 게시판 내용
	private int viewcnt;			// 게시판 조회수
	private String boardType;		// 게시판 종류 (자유게시판, 영화 리뷰, 공지사항, 시사회 당첨자)
	private LocalDateTime dateTime;	// 게시판 작성일
}
