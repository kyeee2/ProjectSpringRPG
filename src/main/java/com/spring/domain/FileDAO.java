package com.spring.domain;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface FileDAO {
	
	int insert(int uid, List<String> originalFileNames, List<String> fileSystemNames);
	
	int deleteFileByUid(int uid,HttpServletRequest request);
	
	List<FileDTO> selectFileByUid(int uid);
	}

