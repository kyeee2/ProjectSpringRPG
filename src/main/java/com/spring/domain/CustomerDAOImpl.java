package com.spring.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private CustomerDAO mapper;
	
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
	public int addAuth(String id, String auth) {
		return mapper.addAuth(id, auth);
	}

	@Override
	public int deleteUser(CustomerDTO user) {
		return mapper.deleteUser(user);
	}

	@Override
	public int deleteAuth(String id, String auth) {
		return mapper.deleteAuth(id, auth);
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
	

}
