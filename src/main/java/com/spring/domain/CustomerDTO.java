package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor	// 생성자
// 회원정보
public class CustomerDTO {
	private int uid;		// 회원 고유 번호
	private String id;		// 회원 아이디
	private String pw;		// 회원 비밀번호
	private int phoneNum;	// 회원 전화번호
	private String nickName;// 회원 닉네임
	private String name;	// 회원 이름
	private int birthDay;	// 회원 생년월일
	private String profile;	// 회원 프로필 사진
}