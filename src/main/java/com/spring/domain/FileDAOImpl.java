package com.spring.domain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class FileDAOImpl implements FileDAO{
	
	private FileDAO mapper;
	
	@Autowired
	public FileDAOImpl(SqlSession sqlSession) {
		System.out.println("FileDAOImpl() 생성");
		mapper = sqlSession.getMapper(FileDAO.class);
	}

	@Override
	public int insert(int prouid, List<String> originalFileNames, List<String> fileSystemNames) {
		// TODO Auto-generated method stub
		return mapper.insert(prouid, originalFileNames, fileSystemNames);
	}

	@Override
	public int deleteFileByUid(int uid, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return mapper.deleteFileByUid(uid, request);
	}

	@Override
	public List<FileDTO> selectFileByUid(int uid) {
		// TODO Auto-generated method stub
		return mapper.selectFileByUid(uid);
	}
	
	
	
}
