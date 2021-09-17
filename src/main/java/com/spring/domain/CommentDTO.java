package com.spring.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 댓글 공통
public class CommentDTO {
	private int uid;				// 댓글 고유번호
	private String content;			// 댓글 내용
	private String nickName;		// 댓글 작성자
	private LocalDateTime dateTime; // 댓글 작성일
}
