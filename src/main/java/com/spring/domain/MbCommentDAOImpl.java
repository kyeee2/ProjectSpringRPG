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
	public int insert(CommentDTO dto) {
		return mapper.insert(dto);
	}


	@Override
	public int update(CommentDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int deleteByUid(int uid[]) {
		return mapper.deleteByUid(uid);
	}
	

	@Override
	public List<CommentDTO> view(int buid) {
		return mapper.view(buid);
	}


	@Override
	public List<CommentDTO> selectByUid(int uid) {
		return mapper.selectByUid(uid);
	}


	@Override
	public int findCusUid(String nickName) {
		return mapper.findCusUid(nickName);
	}


	@Override
	public int findByUid(String nickName) {
		return mapper.findByUid(nickName);
	}

}
