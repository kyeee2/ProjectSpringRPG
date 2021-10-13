<%@page import="org.springframework.web.bind.annotation.ResponseBody"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.security.SecureRandom"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS 적용 -->

<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/basic/login.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>

<jsp:include page="/WEB-INF/views/global/header.jsp"/>
</head>
<body>
	
<%-- 헤더 삽입 --%>
	
 	
	<sec:authorize access="isAuthenticated()">
		<h2>로그인 상태입니다 </h2>
		<form action="logout">
			<input type="submit" value="로그아웃"><br>
			<a href=" http://nid.naver.com/nidlogin.logout"><input type="submit" value="로그아웃"></a><br>
		<a href="http://developers.kakao.com/logout"><input type="submit" value="로그아웃"></a>
		</form>
	</sec:authorize>
	<div class="totallogin">
	<sec:authorize access="!isAuthenticated()">	
		<div><h2>로그인 </h2>
		</div>
		<br>
		<form id="loginfrm" action="loginOk" method="POST">
			<div class="login-1">
			<input type="text" class="idandpw" id="id" name="id" placeholder="아이디"></div><br>
			<div class="login-2"><input type="password" class="idandpw" id="pw" name="pw" placeholder="비밀번호">
			</div><br>
			<input type="submit" id="loginbtn" value="로그인">
			<br>
			
		</form>
		
	</sec:authorize>
	
	<div class="apilogin-1">
	<form id="naver_login" action="${ apiURL }" method="POST"><input type="image" height="38px" width="160px" src="../img/naver.png"/></form>
		<br></div>
	<hr>

		<div class="login-3"><form action="joinAgree"><input type="submit" value="회원가입"></form><br></div>
		<div class="login-4"><a href="findIDPW">ID/PW 찾기</a>
	</div></div>


</body>
</html>