package com.spring.domain;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PremiereDAOImpl implements PremiereDAO {
	
	private PremiereDAO mapper;
	
	@Autowired
	public PremiereDAOImpl(SqlSession sqlSession) {
		System.out.println("PremiereDAOImpl() 생성");
		mapper = sqlSession.getMapper(PremiereDAO.class);
	}

	@Override
	public List<PremiereDTO> select() {
		return mapper.select();
	}

	@Override
	public int insert(PremiereDTO dto) {
		return mapper.insert(dto);
	}

	@Override
	public List<PremiereDTO> selectByUid(int uid) {
		return mapper.selectByUid(uid);
	}

	@Override
	public int update(PremiereDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int deleteByUid(int uid) {
		return mapper.deleteByUid(uid);
	}

}
