package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.domain.CustomerDTO;
import com.spring.service.LoginService;

public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private LoginService loginService;
	
	
	
	// UserDetails 를 리턴한다 --> 누구한테 리턴하나?
	// 시큐리티 sesssion (<= Authentication(<= 리턴된 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername(" + id + ")");
		
		CustomerDTO user = loginService.findById(id);
		
		// 해당 id 의 user 가 있다면
		if(user != null) {
			PrincipalDetails userDetails = new PrincipalDetails(user);
			userDetails.setloginService(loginService);
			return userDetails;
		}else {
		
		// 찾지 못했으면!
		
		return null;
		}
	}

}


