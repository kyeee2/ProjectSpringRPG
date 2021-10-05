package com.spring.service;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.CustomerDAO;
import com.spring.domain.CustomerDTO;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


@Service
public class LoginService {
	
	CustomerDAO dao;
	
    
	@Autowired
	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}
	
	public LoginService() {
		System.out.println("LoginService() 생성");
		
	}
	
	@Transactional
	public int addMember(CustomerDTO user) {
		int cnt = dao.addUser(user);
		dao.addAuth(user.getId(), "ROLE_MEMBER");
		return cnt;
	}
	
	// 회원삭제
	@Transactional
	public int deleteMember(CustomerDTO user) {
		dao.deleteAuths(user.getId());  // 권한(들) 먼저 삭제
		int cnt = dao.deleteUser(user);
		return cnt;
	}
	
	// 특정 id(username) 의 정보 가져오기
	public CustomerDTO findById(String id) {
		return dao.findById(id);
	}
	
	// 특정 id 의 권한(들) 정보 가져오기
	public List<String> selectAuthoritiesById(String id){
		return dao.selectAuthoritiesById(id);
	}
	public int idChk(String id) throws Exception {
		List<String> checkid = dao.idChk(id);
		
		if(checkid == null || checkid.size() == 0)
			return 0;
		else
			return 1;
	}
	public int nickChk(String nickname) throws Exception {
		List<String> checknick = dao.nickChk(nickname);
		
		if(checknick == null || checknick.size() == 0)
			return 0;
		else
			return 1;
	}
	 public void certifiedPhoneNumber(String phonenum, String cerNum) {
		 	
		    
	        String api_key = "NCSTYIGPT4F2IML6";
	        String api_secret = "FS11C9ZNKR917RQ7I9MOC5LVXZRRVGGY";
	        Message coolsms = new Message(api_key, api_secret);
	        
	        // 4 params(to, from, type, text) are mandatory. must be filled
	        HashMap<String, String> params = new HashMap<String, String>();
	        params.put("to", phonenum);    // 수신전화번호
	        params.put("from", "01034239810");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	        params.put("type", "SMS");
	        params.put("text", "무비마니아 휴대폰인증 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
	        params.put("app_version", "test app 1.2"); // application name and version

	        try {
	            JSONObject obj = (JSONObject) coolsms.send(params);
	            System.out.println(obj.toString());
	        } catch (CoolsmsException e) {
	            System.out.println(e.getMessage());
	            System.out.println(e.getCode());
	        }

	    }
}


