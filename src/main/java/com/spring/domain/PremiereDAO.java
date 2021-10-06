package com.spring.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface PremiereDAO {
	
	// 총 개수 
	public abstract int count();
	
	// 전체 SELECT
	public abstract List<PremiereDTO> select(int from, int pageRows);
	
	// 새글작성 <-- DTO
	public abstract int insert(PremiereDTO dto);
	
	// 특정 uid 글 내용 읽기,
	public abstract List<PremiereDTO> selectByUid(int uid);
	
	// 특정 uid 글 수정 ( 제목, 내용 )
	public abstract int update(PremiereDTO dto);
	
	// 특정 uid 글 삭제하기
	public abstract int deleteByUid(int [] uids);
}
