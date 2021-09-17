package com.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class CustomerDummyData {

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

	public static final String SQL_CUSTOMER_INSERT = 
			"INSERT INTO customer " 
			+  "(cus_id, cus_pw, cus_phonenum, cus_nickname ,cus_name, cus_birthday) "
			+ "VALUES (?, ?, ? ,? ,?, ?)";

	public static final String SQL_AUTH_INSERT = 
			"INSERT INTO authority (cus_id, cus_auth) "
			+ "VALUES (?, ?)";
	

	public static final String [] USERNAMES = {"user01", "user02", "admin03", "admin04", "master05"};
	public static final String [] PASSWORDS = {"pw01", "pw02", "pw03", "pw04", "pw05"};
	public static final String [] PHONENUMS = {"01012345678", "01012345678", "01012345678", "01012345678", "01012345678"};
	public static final String [] NICKNAMES = {"홍길동1", "홍길동2", "홍길동3", "홍길동4", "홍길동5"};
	public static final String [] NAMES = {"홍길동1", "홍길동2", "홍길동3", "홍길동4", "홍길동5"};
	public static final int [] BD = {000000, 111111, 222222, 333333, 444444, 555555};
	
	@Test
	void genData() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERID, USERPW);
			
			// 테스트용 dummy 데이터 만들기
			pstmt = conn.prepareStatement(SQL_CUSTOMER_INSERT);
			
			for(int i = 0; i < USERNAMES.length; i++) {				
				pstmt.setString(1, USERNAMES[i]);
				pstmt.setString(2, encoder.encode(PASSWORDS[i]));
				pstmt.setString(3, PHONENUMS[i]);
				pstmt.setString(4, NICKNAMES[i]);
				pstmt.setString(5, NAMES[i]);
				pstmt.setInt(6, BD[i]);
				cnt += pstmt.executeUpdate();
			}
			
			System.out.println(cnt + "개 의 회원 데이터가 INSERT 되었습니다");
			
			pstmt.close();
			
			// 권한
			pstmt = conn.prepareStatement(SQL_AUTH_INSERT);
			pstmt.setString(1, USERNAMES[2]);
			pstmt.setString(2, "ROLE_ADMIN");
			pstmt.executeUpdate();

			pstmt.setString(1, USERNAMES[3]);
			pstmt.setString(2, "ROLE_ADMIN");
			pstmt.executeUpdate();

			pstmt.setString(1, USERNAMES[4]);
			pstmt.setString(2, "ROLE_MASTER");
			pstmt.executeUpdate();

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
