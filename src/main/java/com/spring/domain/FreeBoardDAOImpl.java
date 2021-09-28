package com.spring.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO {

	FreeBoardDAO mapper;
	
	@Autowired
	public FreeBoardDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(FreeBoardDAO.class);
	}

	@Override
	public int count() {
		return mapper.count();
	}

	@Override
	public List<BoardDTO> selectAll(int from, int pageRows) {
		return mapper.selectAll(from, pageRows);
	}

	@Override
	public int incViewCnt(int uid) {
		return mapper.incViewCnt(uid);
	}

	@Override
	public List<BoardDTO> selectByUid(int uid) {
		return mapper.selectByUid(uid);
	}
	
	@Override
	public int findCusUid(String nickName) {
		return mapper.findCusUid(nickName);
	}

	@Override
	public int insert(BoardDTO dto) {
		return mapper.insert(dto);
	}

	@Override
	public int update(BoardDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int deleteGood(int [] uids) {
		return mapper.deleteGood(uids);
	}

	@Override
	public int deleteComment(int [] uids) {
		return mapper.deleteComment(uids);
	}

	@Override
	public int delete(int [] uids) {
		return mapper.delete(uids);
	}

}
