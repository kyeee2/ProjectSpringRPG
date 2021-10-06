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
<title>내가 쓴 게시글, 댓글</title>
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
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/myPage/post/list.js"></script>
</head>
<body>
		<span id="post">게시글</span><span id="comment">댓글</span>
		<div id="pageinfo"></div>
		<div id="pageRows"></div>
		<form id="frm" name="frm">
			<table id="list">
				<thead>
				<%-- JS로 테이블 생성 --%>
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
		<button onclick="location.href = 'write?boardType=freeboard'">신규등록</button>

</body>
</html>