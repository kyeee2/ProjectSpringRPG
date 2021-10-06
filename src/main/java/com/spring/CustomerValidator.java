package com.spring;

import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;

import com.spring.domain.CustomerDTO;

public class CustomerValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("supports(" + clazz.getName() + ")");
		return CustomerDTO.class.isAssignableFrom(clazz);
		
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("validate()");
		CustomerDTO user = (CustomerDTO)target;
			
//			Integer uid = user.getUid();
//			if(uid == null) { // binding 실패하면 null 로 남아 있을것이다
//				System.out.println("uid 오류");
//				errors.rejectValue("uid", "invalidUid");
//			}
			String name = user.getName();
			if(name == null || name.trim().isEmpty()) {
				System.out.println("name 오류 : 반드시 한글자라도 입력해야 합니다");
				errors.rejectValue("name", "name 오류 : 반드시 한글자라도 입력해야 합니다");
			}
			Integer birthday = user.getBirthday();
			if(birthday == null ) {
				System.out.println("생년월일 오류 : 반드시 양식에맞춰 입력해야 합니다");
				errors.rejectValue("birthday", "생년월일 오류 : 반드시 양식에맞춰 입력해야 합니다");
			}else {
				Integer birthLength = (int)(Math.log10(birthday)+1);
				if(birthLength !=6) {
					errors.rejectValue("birthday", "생년월일 오류 : 반드시 6자를 입력해야 합니다");
				}
			}
			String phonenum = user.getPhonenum();
			if(phonenum == null || phonenum.trim().isEmpty()) {
				System.out.println("휴대폰번호 오류 : 전화번호를 반드시 입력해야 합니다");
				errors.rejectValue("phonenum", "휴대폰번호 오류 : 전화번호를 반드시 입력해야 합니다");
			}else {
				if(phonenum.trim().length() != 11) {
					errors.rejectValue("phonenum", "휴대폰번호 오류 : 반드시 11자여야합니다");
				}
				
			}
			
			String nickname = user.getNickname();
			if(nickname == null || nickname.trim().isEmpty() || nickname.trim().length() < 2) {
				System.out.println("닉네임 오류 : 반드시 두 글자이상 입력해야 합니다");
				errors.rejectValue("nickname", "닉네임 오류 : 반드시 두 글자이상 입력해야 합니다");
			}
			String id = user.getId();
			if(id == null || id.trim().isEmpty()) {
				System.out.println("id 오류 : 반드시 한 글자라도 입력해야 합니다");
				errors.rejectValue("id", "id 오류 : 반드시 한 글자라도 입력해야 합니다");
			}
			String pw = user.getPw();
			if(pw == null || pw.trim().isEmpty()) {
				System.out.println("비밀번호 오류 : 반드시 한 글자라도 입력해야 합니다");
				errors.rejectValue("pw", "비밀번호 오류 : 반드시 한 글자라도 입력해야 합니다");
			}
			    final int MIN = 8;
			    final int MAX = 20;

			    // 영어, 숫자, 특수문자 포함한 MIN to MAX 글자 정규식
			    final String REGEX = 
			      "^((?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W]).{" + MIN + "," + MAX + "})$";
			  
			    // 공백 문자 정규식
			    final String BLANKPT = "(\\s)";
			    
			    // 정규식 검사객체
			    Matcher matcher;


			    // ASCII 문자 비교를 위한 UpperCase
			    String tmpPw = pw.toUpperCase();
			    // 문자열 길이
			    int strLen = tmpPw.length();

			    // 글자 길이 체크
			    if (strLen > 20 || strLen < 8) {
			    	errors.rejectValue("pw", "WrongLegthPw");
			    }

			    // 공백 체크
			    matcher = Pattern.compile(BLANKPT).matcher(tmpPw);
			    if (matcher.find()) {
			    	errors.rejectValue("pw", "spacepw");
			    }

			    // 비밀번호 정규식 체크
			    matcher = Pattern.compile(REGEX).matcher(tmpPw);
			    if (!matcher.find()) {
			    	errors.rejectValue("pw", "비밀번호 양식에 맞춰주세요");
			    }

			  

			    
	}
}
