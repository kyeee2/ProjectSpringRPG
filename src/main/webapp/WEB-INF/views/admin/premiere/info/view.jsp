<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- CORE --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Functions --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
<%-- sec tag 사용하기 위해서 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
   
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>시사회 조회</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/view.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<style>
button {
  border: none;
  color: white;
  padding: 5px 5px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  float: left;
  margin-left : 40px;
  border-radius: 5px;

}

button:hover {
	box-shadow : rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
}

#doDelete {
  background-color: #f44336;
}

#btn_list {
  background-color: #B0B0B0;
  margin: 0, auto;
}

#btn_update {
	background-color: #4CAF50;

}

.menu {
	width:15%;
	height:120px;
	background-color: #EAEAEA;
	float: left;
	margin: auto;
	text-align: center;
	border-radius: 20px;
	margin-left: 20px;
}

#result {
	height: 60%;
	width : 60%;
	margin: auto;
	text-align: center;
}

#apply {
	width: 40%;
	margin: auto;
	text-align: center;
}

#btn_apply {
	float: right;
	color: white;
	width: 100px;
	background-color: #368AFF;	
}

#btn_group {
	margin: bottom;
	width: 40%;
	margin: auto;
}

.head1 {
	margin: auto;
	line-height: 130px;
	text-align: center;
	font-size: 80px;
}

#content {
	margin: 50 20 50 20;
	font-size: 20px;
	float: left;
	
}

#image {
	margin: 0, auto;
	width: 100%;
	height: 100%;
}

#title {
	font-size: 40px;
	border: 5px solid black;
}



</style>
</head>
<body>

	<h2 class="head1">시사회 내용</h2>
		<div class="menu">
			<p><a href="list">시사회 정보</a></p>
			<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
			<sec:authorize access="hasRole('ROLE_ADMIN')">	<!-- 로그인된 사용자의 권한이 ADMIN인 경우에만 보이도록 -->
			<p><a href="/admin/premiere/win">시사회 추첨하기</a></p>
			</sec:authorize>
		</div>
	
	<br>
	<form action="./deleteOk" id="frmDelete" name="frmDelete" method="post">
	<div id="result"></div>
	</form>
	<br><br><br>
	
	<form id="apply" name="apply">
		<input type="hidden" name="prUid" value="${param.uid }"/>
		닉네임: <input type="text" name="nickname">
		이메일: <input type="text" name="email"> 
		<button type="button" id="btn_apply">응모하기</button>
	</form>
	<br>
	<sec:authorize access="hasRole('ROLE_ADMIN')">	<!-- 로그인된 사용자의 권한이 ADMIN인 경우에만 보이도록 -->
	<div id="btn_group">
		<button id="btn_update" onclick="location.href='/admin/premiere/update?uid=${ param.uid }'">수정하기</button>
		<button id="doDelete">삭제하기</button>
	</div>
	</sec:authorize>
		<button id="btn_list" onclick="location.href='/premiere/list'">목록보기</button>
</body>
</html>























































