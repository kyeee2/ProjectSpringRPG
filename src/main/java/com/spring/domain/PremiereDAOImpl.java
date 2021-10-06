package com.spring.domain;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PremiereDAOImpl implements PremiereDAO {

	private PremiereDAO mapper;
	
	@Autowired
	public PremiereDAOImpl (SqlSession sqlSession) {
		mapper = sqlSession.getMapper(PremiereDAO.class);
	}
	
	@Override
	public int count() {
		return mapper.count();
	}
	
	@Override
	public List<PremiereDTO> select(int from, int pageRows){
		return mapper.select(from, pageRows);
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
	public int deleteByUid(int [] uids) {
		return mapper.deleteByUid(uids);
	}

}

























