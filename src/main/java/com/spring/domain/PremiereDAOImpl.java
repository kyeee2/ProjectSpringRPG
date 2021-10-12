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
	public int updateNoFile(PremiereDTO dto) {
		return mapper.updateNoFile(dto);
	}

	@Override
	public int deleteByUid(int [] uids) {
		return mapper.deleteByUid(uids);
	}

	@Override
	public int findUidById(String id) {
		return mapper.findUidById(id);
	}

	@Override
	public int apply(int prUid, int cusUid, String email) {
		return mapper.apply(prUid, cusUid, email);
	}

	@Override
	public int chkId(int prUid, String id) {
		return mapper.chkId(prUid, id);
	}

	@Override
	public List<PremiereWinDTO> selectWin(int prUid, int count) {
		return mapper.selectWin(prUid, count);
	}

	@Override
	public int findUidByTitle(String title) {
		return mapper.findUidByTitle(title);
	}
	
	@Override
	public List<PremiereDTO> getTitle() {
		return mapper.getTitle();
	}

	@Override
	public int updateBool(int prUid, String email) {
		return mapper.updateBool(prUid, email);
	}
	
	
	public List<String> getFileName(int[] uids) {
		return mapper.getFileName(uids);
	}

	@Override
	public List<PremiereDTO> getThreeRecently() {
		return mapper.getThreeRecently();
	}

}

























