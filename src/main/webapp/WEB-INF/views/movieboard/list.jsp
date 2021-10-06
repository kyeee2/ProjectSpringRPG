<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Functions --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>영화리뷰</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px 10px;
	text-align : center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/movieboard/movieboard.js"></script>
</head>
<body>
		<h2>영화리뷰</h2>
		<div id="pageinfo"></div>
		<div id="pageRows"></div>
		<form id="frm" name="frm">
			<table id="list">
				<thead>
					<th></th>
					<th>NO</th>
					<th>추천수</th>
					<th>주제</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</thead>
				<tbody>
				<%-- JS로 테이블 생성 --%>
				</tbody>
				
			</table>
		</form>
		<br>
		
		<%-- [페이징] --%>
		<div class="center">
			<ul class="pagination" id="pagination"></ul>
		</div>
		
		<br>
		<button onclick="deleteData()">삭제하기</button>
		<button onclick="location.href = '/user/write?boardType=movieboard'">신규등록</button>

</body>
</html>