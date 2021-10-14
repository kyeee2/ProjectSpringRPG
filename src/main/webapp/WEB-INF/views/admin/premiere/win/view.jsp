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
<title>시사회 당첨자 발표</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/premiereWinListView.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
</head>
<style>
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

.head {
	text-align: center;
}

button {
	border: none;
	color: white;
	padding: 5px 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	float: left;
	margin-left: 40px;
	border-radius: 5px;
}

button:hover {
	box-shadow : rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
}

#btn_list {
	background-color: #B0B0B0;
	margin-bottom: 100px;
}

#doDelete {
	float: right;
	background-color: #f44336;
}

#result {
	width: 70%;
	margin: auto;
	height: 100%;
}

#buttonList{
	margin: auto;
}

#dateTime{
	font-size: 15px;
}


</style>
<body>
<h2 class="head">시사회 당첨자 발표</h2>
	<div class="menu">
		<p><a href="../premiere/list">시사회 정보</a></p>
		<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
		<sec:authorize access="hasRole('ROLE_ADMIN')">	<!-- 로그인된 사용자의 권한이 ADMIN인 경우에만 보이도록 -->
			<p><a href="../admin/premiere/win">시사회 추첨하기</a></p>
		</sec:authorize>
	</div>
	<br>
	<div id="result"></div>
	
	<div id="buttonList">
		<button id="btn_list" onclick="location.href='/premiereWin'">뒤로가기</button>
	</div>

</body>
</html>