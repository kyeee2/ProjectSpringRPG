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
<link href="${ pageContext.request.contextPath }/CSS/login.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>

<!-- JQuery 적용 -->
<jsp:include page="/WEB-INF/views/global/header.jsp"/>
</head>
<body>
	<% 
    String clientId = "MdDnprhEU_V4J7WsPkRu";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8090/callback", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);

		%>
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
	<a href="<%=apiURL%>"><img height="38px" width="160px" src="../img/naver.png"/></a>
		<br></div>
	<hr>
	<div id=kakaologin class="apilogin-2" onclick="kakaoLogin()">
		<img height="38px" width="160px" src="../img/kakao_login_medium_narrow.png" /></div>
		<div class="login-3"><form action="joinagree"><input type="submit" value="회원가입"></form><br></div>
		<div class="login-4"><a href="find">ID/PW 찾기</a>
	</div></div>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
  <script>
  //카카오로그인
  function kakaoLogin() {

    $.ajax({
        url: '/getKakaoAuthUrl',
        type: 'get',
        async: false,
        dataType: 'text',
        success: function (res) {
            location.href = res;
        }
    });

  }

  $(document).ready(function() {

      var kakaoInfo = '${kakaoInfo}';

      if(kakaoInfo != ""){
          var data = JSON.parse(kakaoInfo);

          alert("카카오로그인 성공 \n accessToken : " + data['accessToken']);
          alert(
          "user : \n" + "email : "
          + data['email']  
          + "\n nickname : " 
          + data['nickname']);
      }
  });  

  </script>
</body>
</html>