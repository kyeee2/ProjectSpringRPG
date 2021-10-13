<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
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
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px 10px;
	text-align : center;
	margin: auto;
	width: 40%;
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

</style>
</head>
<body>

	<h2>시사회 관리</h2>
	<div class="menu">
		<p><a href="/premiere/list">시사회 정보</a></p>
		<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
		<p><a href="../admin/premiere/win">시사회 추첨하기</a>
	</div>
	
<h2 class="head">시사회 당첨자 발표 목록</h2>
<%-- 목록 --%>
	<form id="winList" name="winList">
		<table id="list">
			<thead>
				<th>NO</th>
				<th>제목</th>
				<th>조회수</th>
			</thead>
			<tbody>
			</tbody>
		</table>
	</form>


</body>
</html>