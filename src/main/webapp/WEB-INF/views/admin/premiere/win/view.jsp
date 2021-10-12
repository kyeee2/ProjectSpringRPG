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
<title>시사회 당첨자 발표 조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/view.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
</head>
<style>
.menu {
	width: 15%;
	height: 120px;
	background-color: #D5D5D5;
	float: left;
	margin: auto;
	text-align: center;
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
}

#doDelete {
	float: right;
	background-color: #f44336;
}

</style>
<body>

<h2>시사회 관리</h2>
	<div class="menu">
		<p><a href="../premiere/list">시사회 정보</a></p>
		<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
		<p><a href="../admin/premiere/win">시사회 추첨하기</a>
	</div>
<h2 class="head">시사회 당첨자 발표 조회</h2>


<button id="btn_list" onclick="location.href='/premiere/list'">목록보기</button>
<button id="doDelete">삭제하기</button>

</body>
</html>