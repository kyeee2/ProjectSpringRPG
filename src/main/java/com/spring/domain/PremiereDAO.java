package com.spring.domain;

import java.util.List;

public interface PremiereDAO {
	
	// 전체
	public abstract List<PremiereDTO> select();
	
	// 작성
	public abstract int insert(PremiereDTO dto);
	
	// 조회
	public abstract List<PremiereDTO> selectByUid(int uid);
	
	// 수정
	public abstract int update(PremiereDTO dto);
	
	// 삭제
	public abstract int deleteByUid(int uid);
}
