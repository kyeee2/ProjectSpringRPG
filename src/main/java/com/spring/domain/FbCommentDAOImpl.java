package com.spring.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FbCommentDAOImpl implements FbCommentDAO {

private FbCommentDAO mapper;
	
	@Autowired
	public FbCommentDAOImpl(SqlSession sqlSession) {
		System.out.println("FbCommentDAOImpl() 생성");
		mapper = sqlSession.getMapper(FbCommentDAO.class);
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
	public int deleteByUid(String boardType, int uid[]) {
		
		return mapper.deleteByUid(boardType, uid);
	}

	@Override
	public List<CommentDTO> view(int buid) {
		return mapper.view(buid);
	}



	@Override
	public List<CommentDTO> selectByUid(int buid) {
		return mapper.selectByUid(buid);
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
