<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- CORE --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Functions --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
   
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
}

#btn_update {
	background-color: #4CAF50;

}

.menu {
	width:15%;
	height:120px;
	background-color: #EAEAEA;
	float:left;
	margin:auto;
	text-align: center;
	border-radius: 20px;
	margin-left: 20px;
}

#result {
	background-color : #D5D5D5;
	width : 40%;
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
	color: black;
}

#btn_group {
	margin: bottom;
	width: 40%;
	margin: auto;
}
</style>
</head>
<body>

	<h2>시사회 관리</h2>
	<div class="menu">
		<p><a href="list">시사회 정보</a></p>
		<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
		<p><a href="../admin/premiere/win">시사회 추첨하기</a>
	</div>

	<h2 class="con"></h2>
	<br>
	<div id="result"></div>
	<br>
	<form id="apply" name="apply">
		<input type="text" name="prUid" value="${param.uid }"/>
		아이디: <input type="text" name="id"><br>
		이메일: <input type="text" name="email"><br> 
		<button type="button" id="btn_apply">응모하기</button>
	</form>
	<br>
	<div id="btn_group">
		<button id="btn_update" onclick="location.href='/admin/premiere/update?uid=${ param.uid }'">수정하기</button>
		<button id="btn_list" onclick="location.href='list'">목록보기</button>
		<button id="doDelete">삭제하기</button>
	</div>
</body>