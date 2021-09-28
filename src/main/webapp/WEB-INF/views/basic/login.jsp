<%@page import="org.springframework.web.bind.annotation.ResponseBody"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
 	<%
		// 현재 로그인 상태인지, 즉 로그인 세션 (name이 'userid'인 세션값)이 있는지 확인
		if(session.getAttribute("userid") != null){					
	%>
		<h2>로그인 상태입니다 </h2>
		<form action="logout">
			<input type="submit" value="로그아웃"><br>
		</form>
		<a href="http://developers.kakao.com/logout">카카오 로그아웃</a>
	<%
		} else {
		// 로그인 상태가 아니라면 ... 
	%>
		<h2>로그인 </h2>
		<form action="loginOk" method="POST">
			<input type="text" name="username" placeholder="아이디"><br>
			<input type="password" name="password" placeholder="비밀번호"><br>
			<input type="submit" value="로그인"><br>
		</form>
		<a href="join">회원가입</a>
		<a href="find">ID/PW 찾기</a>
		 <%
	   String clientId = "MdDnprhEU_V4J7WsPkRu";//애플리케이션 클라이언트 아이디값";
	   String redirectURI = URLEncoder.encode("http://localhost:8090/basic/callback", "UTF-8");
	   SecureRandom random = new SecureRandom();
	   String state = new BigInteger(130, random).toString();
	   
	   String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	   apiURL += "&client_id=" + clientId;
	   apiURL += "&redirect_uri=" + redirectURI;
	   apiURL += "&state=" + state;
	   
	   session.setAttribute("state", state);
	%>
	<a href="<%=apiURL%>"><img height="38px" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>	
	
		<%
		String redirectURI2 = URLEncoder.encode("http://localhost:8090/basic/kakao", "UTF-8");
		String reqUrl = 
					"https://kauth.kakao.com/oauth/authorize"
					+ "?client_id=d11a12ee85c98662914e0bac1931a617"
					+ "&redirect_uri=" + redirectURI2
					+ "&response_type=code";
			
			
		 %>
	<a href="<%=reqUrl %>">
		<img height="38px" src="../img/kakao_login_medium_narrow.png" />
 </a>
<%}%>
</body>
</html>