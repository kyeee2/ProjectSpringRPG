package com.spring.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.domain.CustomerDTO;
import com.spring.service.LoginService;

import lombok.Getter;

public class PrincipalDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private LoginService loginService;
	public void setloginService(LoginService loginService) {
		this.loginService = loginService;
	}

	private CustomerDTO user;
	
	public PrincipalDetails(CustomerDTO user) {
		System.out.println("PrincipalDetails(user) 생성: " + user);
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("getAuthorities() 호출");
		
		Collection<GrantedAuthority> collect = new ArrayList<>();
		
		List<String> list = loginService.selectAuthoritiesById(user.getId());
		
		for(String auth : list) {
			collect.add(new GrantedAuthority() {
			
				private static final long serialVersionUID = 1L;

				@Override
				public String getAuthority() {
					return auth;
				}
			});
		}		
		
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPw();
	}
	
	@Override
	public String getUsername() {	// 아이디 가져오기
		return user.getId();
	}
	
	public void setPassword(String pw) {
		user.setPw(pw);
	}
	
	public CustomerDTO getUser(){
		
		return user;
	}
	
	public int getUid() {
		return user.getUid();
	}
	
	public String getNickName() {
		return user.getNickname();
	}

	// 계정이 만료된건 아닌지?
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠긴건 아닌지?
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정 credential 이 만료된건 아닌지?
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화 되었는지?
	@Override
	public boolean isEnabled() {
		if(user.getEnable() ==1 ) {
			return true;
		}
		return false;
		// ex)
		// 사이트에서 1년동안 회원이 로그인을 안하면 휴면계정으로 하기로 했다면?
		// 현재시간 - 로그인시간 => 1년을 초과하면 false  
	}

}
