package com.spring.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MyPageCommentDAOImpl implements MyPageCommentDAO {

	MyPageCommentDAO mapper;
	
	public MyPageCommentDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(MyPageCommentDAO.class);
	}
	
	@Override
	public List<CommentDTO> listMyCo(int cusuid,int from, int pageRows) {
		return mapper.listMyCo(cusuid, from, pageRows);
	}

	@Override
	public int countMyComment(int uid) {
		return mapper.countMyComment(uid);
	}

}
