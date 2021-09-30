package com.spring.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxCommentResult {
	int count;	// 데이터 개수
	String status; // 처리 결과
	String message;	// 결과 메세지
}
