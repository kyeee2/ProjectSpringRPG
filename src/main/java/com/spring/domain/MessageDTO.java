package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 쪽지
public class MessageDTO {
	private int uid;			// 쪽지 고유번호
	private String sendNick;	// 쪽지 보낸 사람 닉네임
	private String RecNick;		// 쪽지 받는 사람 닉네임
	private String title;		// 쪽지 제목
	private String content;		// 쪽지 내용
}
