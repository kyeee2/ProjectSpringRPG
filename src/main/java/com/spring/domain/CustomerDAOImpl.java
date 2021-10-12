package com.spring.domain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private CustomerDAO mapper;
	
	SqlSession sqlSession;
	@Autowired
	public CustomerDAOImpl(SqlSession sqlSession) {
		System.out.println("CustomerDAOImpl() 생성");
		
		mapper = sqlSession.getMapper(CustomerDAO.class);
	}
	

	@Override
	public int addUser(CustomerDTO user) {
		
		return mapper.addUser(user);
	}

	@Override
	public int addAuth(String auth, String id) {
		return mapper.addAuth(auth, id);
	}

	@Override
	public int deleteUser(int enable, String id) {
		return mapper.deleteUser(enable, id);
	}

	@Override
	public int deleteAuth(String auth, String id) {
		return mapper.deleteAuth(auth, id);
	}

	@Override
	public int deleteAuths(String id) {
		return mapper.deleteAuths(id);
	}

	@Override
	public CustomerDTO findById(String id) {
		return mapper.findById(id);
	}

	@Override
	public List<String> selectAuthoritiesById(String id) {
		return mapper.selectAuthoritiesById(id);
	}


	@Override
	public List<String> idChk(String id) throws Exception {
		return mapper.idChk(id);
	}


	@Override
	public List<String> nickChk(String nickname) throws Exception {
		return mapper.nickChk(nickname);
	}


	@Override
	public int updateUser(String phonenum, String nickname, int uid)  {
		
		return mapper.updateUser(phonenum, nickname, uid);
	}


	@Override
	public List<CustomerDTO> selectByUid(int uid) {
		System.out.println("dao에 걸렸니?");
		return mapper.selectByUid(uid);
	}


	@Override
	public int updatePw(String pw, int uid) {
		return mapper.updatePw(pw, uid);
	}


	@Override
	public String findID(String name, String phonenum) throws Exception {
		return mapper.findID(name, phonenum);
	}


	@Override
	public List<CustomerDTO> findPW(String pw, String name, String phonenum, String id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.findPW(pw, name, phonenum, id);
	}


	@Override
	public List<CustomerDTO> selectByUidAll(int from, int pageRows) {
		// TODO Auto-generated method stub
		return mapper.selectByUidAll(from, pageRows);
	}

	@Override
	public int count() {
		return mapper.count();
	}


	@Override
	public String findNameByPhonenum(String phonenum) {
	
		return mapper.findNameByPhonenum(phonenum);
	}


	@Override
	public int changePw(String pw, String id, String name, String phonenum) {
		
		return mapper.changePw(pw, id, name, phonenum);
	}
	


	
	
	


//	@Override
//	public int insert(int uid, List<String> originalFileNames, List<String> fileSystemNames) {
//		// TODO Auto-generated method stub
//		return mapper.insert(uid, originalFileNames, fileSystemNames);
//	}
//
//	@Override
//	public int deleteFileByUid(int uid, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		return mapper.deleteFileByUid(uid, request);
//	}
//
//	@Override
//	public List<FileDTO> selectFileByUid(int uid) {
//		// TODO Auto-generated method stub
//		return mapper.selectFileByUid(uid);
//	}

}
