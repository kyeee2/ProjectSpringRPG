<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
<%-- sec tag 사용하기 위해서 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시사회 당첨자 발표 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/premiereWinList.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>

<style>
table {
width: 40%;
margin: auto;
}
table, th, td {
	border: 1px solid black;
	text-align: center;
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

.head {
	text-align : center;
}

p > a {
	text-decoration : none;
}

p:hover {
	background-color: #BDBDBD;
}

.head1 {
	margin-left: 73px;
}

#th01 {
	width: 20px;
}

div.page {
	width:85%;
	height:35px;
	float: right;
}
div.paginations {
	width: 100%;
	display: block;
	border-radius: 5px;
}
.paginations a{
	color: black;
	float: none;
	padding: 8px 16px;
	text-decoration: none;
	border-radius: 5px;
}
.paginations a.active {
  background-color: #4CAF50;
  color: white;
}
.paginations a:hover:not(.active) {
  background-color: #ddd;
}
ul.pagination {
	line-height: 100px;
	width: 100%;
	justify-content : center;
}



</style>
</head>
<body>

	<h2 class="head1">시사회 관리</h2>
	<div class="menu">
		<p><a href="/premiere/list">시사회 정보</a></p>
		<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
		<sec:authorize access="hasRole('ROLE_ADMIN')">	<!-- 로그인된 사용자의 권한이 ADMIN인 경우에만 보이도록 -->
		<p><a href="../admin/premiere/win">시사회 추첨하기</a></p>
		</sec:authorize>
	</div>
	
<h2 class="head">시사회 당첨자 발표 목록</h2>
<%-- 목록 --%>
	<form id="winList" name="winList">
		<table id="list">
			<thead>
				<th>NO</th>
				<th>제목</th>
				<th>날짜</th>
			</thead>
			<tbody>
			</tbody>
		</table>
	</form>
	
	<%-- 
	<div class="paginations">
		<ul class="pagination"></ul>
	</div>
	--%>



</body>
</html>