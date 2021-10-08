package com.spring.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class UserLoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		System.out.println("failHandler() 생성");
		if (exception instanceof AuthenticationServiceException) {
			request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
			System.out.println("존재하지 않는 사용자입니다.");
		} else if(exception instanceof BadCredentialsException) {
			request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
			System.out.println("아이디 또는 비밀번호가 틀립니다..");
			
		} else if(exception instanceof LockedException) {
			request.setAttribute("loginFailMsg", "잠긴 계정입니다..");
			
			System.out.println("잠긴 계정입니다..");
		} else if(exception instanceof DisabledException) {
			request.setAttribute("loginFailMsg", "비활성화된 계정입니다..");
			System.out.println("비활성화된 계정입니다.");
			
		} else if(exception instanceof AccountExpiredException) {
			request.setAttribute("loginFailMsg", "만료된 계정입니다..");
			
			System.out.println("만료된 계정입니다.");
		} else if(exception instanceof CredentialsExpiredException) {
			request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
			System.out.println("비밀번호가 만료되었습니다.");
		}
		
		// 로그인 페이지로 다시 포워딩
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
////		dispatcher.forward(request, response);
//		redirectAtt.addAttribute("message");
		response.sendRedirect("/login");
		
	}
	
}