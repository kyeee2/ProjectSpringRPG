package com.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class PremiereDummyData {

	// JDBC 관련 기본 객체 변수들 선언
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;   // executeQuery(), SELECT 결과 
	int cnt = 0;           // executeUpdate(), DML 결과	
	
	// MySQL
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";  // JDBC 드라이버 클래스
	public static final String URL = "jdbc:mysql://localhost:3306/moviemania";  // DB 서버 정보
	public static final String USERID = "springRPG";   // DB 사용자 계정 정보
	public static final String USERPW = "1q2w3e4r";

	public static final String SQL_PREMIERE_INSERT = 
			"INSERT INTO premiere " 
			+  "(pr_title, pr_photo, pr_content) "
			+ "VALUES (?, ?, ?)";

	@Test
	void genData() {
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_PREMIERE_INSERT);
			
			for(int i = 0; i < 10; i++) {				
				pstmt.setString(1, String.format("title%02d", i));
				pstmt.setString(2, String.format("photo%02d", i));
				pstmt.setString(3, String.format("content%02d", i));
				cnt += pstmt.executeUpdate();
			}
			
			System.out.println(cnt + "개 의 회원 데이터가 INSERT 되었습니다");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
