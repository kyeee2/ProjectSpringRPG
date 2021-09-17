package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 시사회 응모자 정보
public class PreApplyDTO {
	private String preTitle;	// 시사회 제목
	private String cusId;		// 응모자 아이디
	private int cusPhoneNum;	// 응모자 전화번호
	private String cusEmail;	// 응모자 이메일
}
