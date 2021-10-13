<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- CORE --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Functions --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시사회 추첨</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/premiereWin.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<style>
table {
	border-collapse: collapse;
	padding: 5px 10px;
	text-align : center;
	margin: auto;
}

.menu {
	width: 15%;
	height: 120px;
	background-color: #EAEAEA;
	float: left;
	margin: auto;
	text-align: center;
	border-radius: 20px;
	margin-left: 20px;
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

#btn_win {
	float: left;
	background-color: #FAED7D;
	color: red;
}

#submit {
	float: right;
	color: black;
}

#win {
	float: center;
	margin: auto;
	width: 500px;
}

p > a {
	text-decoration : none;
}

p:hover {
	background-color: #BDBDBD;
}

.head {
	text-align : center;
	position: relative;
	width: 80%;
	margin: auto;
}

</style>
</head>
<body>

<h2>시사회 관리</h2>
	<div class="menu">
		<p><a href="/premiere/list">시사회 정보</a>
		<p><a href="/premiereWin">시사회 당첨자 발표</a>
		<p><a href="../premiere/win">시사회 추첨하기</a>
	</div>

<h2 class="head">추첨</h2>
<br>
<br>
<form id="win" name="win" action="./winOk" method="post">
	시사회 : 
	<c:if test="${!empty titleList && fn:length(titleList) > 0}">
	<select name="prUid" id="selectBox" style="width:200px">
				<option selected>시사회 제목</option>
				<c:forEach items="${ titleList }" var="title" varStatus="index">
					<option value="${ title.uid }">${ title.title }</option>
				</c:forEach>
	</select>
	</c:if>
	당첨자 수 : <input type="number" name="count"/>
	<button type="button" id="btn_win" onclick="selectWin()">추첨하기</button>

		<table id="list">
			<thead>
				<th>닉네임</th>
				<th>이메일</th>
			</thead>
			<tbody>
			</tbody>
		</table>
	<button type="submit" id="submit"> 완료 </button>
</form>
	
</body>
</html>











