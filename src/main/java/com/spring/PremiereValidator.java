package com.spring;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.domain.PremiereDTO;

public class PremiereValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return PremiereDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("[PremiereValidator::validate() 호출]");
		PremiereDTO dto = (PremiereDTO) target;
		
		String title = dto.getTitle();
		if(title == null || title.trim().isEmpty()) {
			errors.rejectValue("title", "empty title");
		}
		String content = dto.getContent();
		if(content == null || content.trim().isEmpty()) {
			errors.rejectValue("content", "empty content");
		}
	}

}
