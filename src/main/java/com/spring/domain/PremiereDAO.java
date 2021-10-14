package com.spring.domain;

import java.util.List;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface PremiereDAO {
	
	// 총 개수 
	public abstract int count();
	
	// 전체 SELECT
	public abstract List<PremiereDTO> select(int from, int pageRows);
	
	// 새글작성 <-- DTO
	public abstract int insert(PremiereDTO dto);
	
	// 특정 uid 글 내용 읽기
	public abstract List<PremiereDTO> selectByUid(int uid);
	
	// 특정 uid 글 수정 ( 제목, 내용 )
	public abstract int update(PremiereDTO dto);

	// 파일은 수정하지 않고 수정
	public abstract int updateNoFile(PremiereDTO dto);
	
	// 특정 uid 글 삭제하기
	public abstract int deleteByUid(int [] uids);

	// 특정 회원 ID로 회원 UID 찾기 
	public abstract int findUidById(String id);
	
	// 시사회 제목으로 해당 글 UID 찾기
	public abstract int findUidByTitle(String title);

	// 응모 정보 DB에 넣기 
	public abstract int apply(int prUid, int cusUid, String email);

	// 응모한 아이디인지 체크 
	public abstract int chkId(int prUid, String id);
	
	// 응모된 계정중에 당첨자 닉네임 추첨
	public abstract List<PremiereWinDTO> selectWin(int prUid, int count);
	
	// 시사회 제목, uid 갖고오기 ( select box )
	public abstract List<PremiereDTO> getTitle();
	
	// 추첨 당첨자 boolean 값 1로 변경
	public abstract int updateBool(int prUid, String email);
	// 특정 uid 의 파일명들 가져오기
	public abstract List<String> getFileName(int[] uids);
	
	// 메인페이지용
	// 시사회 최근 글 3개
	public abstract List<PremiereDTO> getThreeRecently();
	
	// 당첨자 premiereWinBoard 에 insert
	public abstract int applyList(String title, String content);

	// applyList의 해당 시사회 타이틀 (pr_title) 불러오기
	public abstract String applyTitle(int prUid);

	// 당첨자 리스트 불러오기
	public abstract List<PremiereWinListDTO> winList(int from, int pageRows);

	// 당첨자 특정 글 조회
	public abstract List<PremiereWinListDTO> selectWinView(int uid); 
}



















