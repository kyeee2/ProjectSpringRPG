package com.spring;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.domain.BoardDTO;

// 게시글 작성할 때 유효성 검사
public class BoardValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 검증할 객체의 클래스 타입인지 확인 : BoardDTO = calss => BoardDTO에 대입할 수 있는가?
		return BoardDTO.class.isAssignableFrom(clazz);
	}

	// 주어진 객체(target)에 유효성검사를 하고 
	// 유효성검사에 오류가 있는 경우 주어진 객체에 이 오류들을 errors 에 등록 한다.
	// 아래 validate()는 Spring이 기본적인 binding이 수행한 이후에 호출이 된다.
	// 따라서, errors에는 Spring이 수행한 binding 에러 들이 이미 담겨있다.
	// target 에는 binging이 완료한 커맨드 객체가 전달된다.
	@Override
	public void validate(Object target, Errors errors) {
		
		BoardDTO dto = (BoardDTO)target;
		
		

	}

}
