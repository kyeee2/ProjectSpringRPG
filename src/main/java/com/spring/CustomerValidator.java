//package com.spring;
//
//import org.springframework.validation.Validator;
//import org.springframework.validation.Errors;
//
//import com.spring.domain.CustomerDTO;
//
//public class CustomerValidator implements Validator{
//	
//	@Override
//	public boolean supports(Class<?> clazz) {
//		System.out.println("supports(" + clazz.getName() + ")");
//		return CustomerDTO.class.isAssignableFrom(clazz);
//		
//	@Override
//	public void validate(Object target, Errors errors) {
//		System.out.println("validate()");
//		CustomerDTO user = (CustomerDTO)target;
//			
//			Integer uid = user.getUid();
//			if(uid == null) { // binding 실패하면 null 로 남아 있을것이다
//				System.out.println("uid 오류");
//				errors.rejectValue("uid", "invalidUid");
//			}
//			String name = user.getName();
//			if(name == null || name.trim().isEmpty()) {
//				System.out.println("name 오류 : 반드시 한글자라도 입력해야 합니다");
//				errors.rejectValue("name", "emptyName");
//			}
//			
//		}
//
//	}
//
//	@Override
//	public void validate(Object target, Errors errors) {
//		// TODO Auto-generated method stub
//		
//	}
//}
