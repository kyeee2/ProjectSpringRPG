package com.spring.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MyPageBoardDAOImpl implements MyPageBoardDAO {

	MyPageBoardDAO mapper;
	
	public MyPageBoardDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(MyPageBoardDAO.class);
	}
	
	@Override
	public int countMyPost(int uid) {
		return mapper.countMyPost(uid);
	}

	@Override
	public List<BoardDTO> listMyPage(int uid, int from, int pageRows) {
		return mapper.listMyPage(uid, from, pageRows);
	}



}
