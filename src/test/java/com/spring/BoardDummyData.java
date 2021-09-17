package com.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class BoardDummyData {
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

	public static final String SQL_FREEBOARD_INSERT = 
			"INSERT INTO freeboard (fb_title, fb_content, fb_boardtype, cus_uid) "
			+ "VALUES (?, ?, ?, ?)";

	public static final String SQL_MOVIEBOARD_INSERT = 
			"INSERT INTO movieboard (mb_title, mb_content, mb_subject, mb_boardtype, cus_uid) "
			+ "VALUES (?, ?, ?, ?, ?)";
	
	public static final String SQL_NOTICEBOARD_INSERT = 
			"INSERT INTO noticeboard (nb_title, nb_content, nb_boardtype, cus_uid) "
			+ "VALUES (?, ?, ?, ?)";
	
	public static final int [] UIDS = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
	
	@Test
	void genData() {
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_FREEBOARD_INSERT);
			
			for(int i = 0; i < 10; i++) {				
				pstmt.setString(1, String.format("title%02d", i));
				pstmt.setString(2, String.format("content%02d", i));
				pstmt.setString(3, "freeboard");
				pstmt.setInt(4, UIDS[i]);
				cnt += pstmt.executeUpdate();
			}
			
			System.out.println(cnt + "개 의 회원 데이터가 INSERT 되었습니다");
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(SQL_MOVIEBOARD_INSERT);
			
			for(int i = 0; i < 10; i++) {				
				pstmt.setString(1, String.format("title%02d", i));
				pstmt.setString(2, String.format("content%02d", i));
				pstmt.setString(3, String.format("subject%02d", i));
				pstmt.setString(4, "movieboard");
				pstmt.setInt(5, UIDS[i]);
				cnt += pstmt.executeUpdate();
			}
			
			System.out.println(cnt + "개 의 회원 데이터가 INSERT 되었습니다");
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(SQL_NOTICEBOARD_INSERT);
			
			for(int i = 0; i < 10; i++) {				
				pstmt.setString(1, String.format("title%02d", i));
				pstmt.setString(2, String.format("content%02d", i));
				pstmt.setString(3, "noticeboard");
				pstmt.setInt(4, UIDS[i]);
				cnt += pstmt.executeUpdate();
			}
			
			System.out.println(cnt + "개 의 회원 데이터가 INSERT 되었습니다");
			
			pstmt.close();

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
