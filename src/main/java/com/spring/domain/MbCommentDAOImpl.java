package com.spring.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MbCommentDAOImpl implements MbCommentDAO {

private MbCommentDAO mapper;
	
	@Autowired
	public MbCommentDAOImpl(SqlSession sqlSession) {
		System.out.println("MbCommentDAOImpl() 생성");
		mapper = sqlSession.getMapper(MbCommentDAO.class);
	}
	

	@Override
	public int insert(String boardType, int buid) {
		return mapper.insert(boardType, buid);
	}


	@Override
	public int update(String boardType, int buid) {
		return mapper.update(boardType, buid);
	}

	@Override
	public int deleteByUid(int buid) {
		return mapper.deleteByUid(buid);
	}
	

	@Override
	public List<CommentDTO> view(int buid) {
		return mapper.view(buid);
	}


	@Override
	public List<CommentDTO> selectByUid(int uid) {
		return mapper.selectByUid(uid);
	}

}
