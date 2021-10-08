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
	public int deleteUser(CustomerDTO user) {
		return mapper.deleteUser(user);
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
	public int updateUser(CustomerDTO user)  {
		
		return mapper.updateUser(user);
	}


	@Override
	public List<CustomerDTO> selectByUid(int uid) {
		
		return mapper.selectByUid(uid);
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
