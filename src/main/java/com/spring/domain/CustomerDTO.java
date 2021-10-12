package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor	// 생성자
// 회원정보
public class CustomerDTO {
	private Integer uid;		// 회원 고유 번호
	private String id;		// 회원 아이디
	private String pw;		// 회원 비밀번호
	private String phonenum;	// 회원 전화번호
	private String nickname;// 회원 닉네임
	private String name;	// 회원 이름
	private Integer birthday;	// 회원 생년월일
	private byte[] profile;
	private int enable;
	 // 회원 프로필 사진
	
	 
}

