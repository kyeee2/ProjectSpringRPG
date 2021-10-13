package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 시사회 응모자 정보
public class PreApplyDTO {
	private int prUid;			// 시사회 uid
	private String cusId;		// 응모자 아이디
	private String cusEmail;	// 응모자 이메일
}
