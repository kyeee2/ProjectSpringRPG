<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Functions --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- sec tag 사용하기 위해서 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px 10px;
	text-align : center;
}
</style>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/noticeboard/list.css" rel="stylesheet" type="text/css">

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/noticeboard/noticeboard.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	
	<%-- 메인 컨텐츠 --%>
	<section>
		<h2>공지사항</h2>
		<div class="page-wrap">
		<div id="pageinfo"></div>
		<div id="pageRows"></div>
		</div>
		<form id="frm" name="frm">
			<table id="list">
				<thead>
					<th></th>
					<th>NO</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</thead>
				<tbody id="board_list">
				<%-- 게시판 전체 목록 JS로 테이블 생성 --%>
				</tbody>
				<tbody id="search_list">
				<%-- 검색 목록 JS로 테이블 생성 --%>
				</tbody>
			</table>
		</form>
		<br>
		
		<%-- [페이징] --%>
		<div class="center">
			<ul class="pagination" id="pagination"></ul>
		</div>
		
		<%-- 자유게시판 안에서 검색 기능 - 제목과 내용으로 검색 가능 --%>
		<div id="search">
			<input type="text" id="input-search" name="search" placeholder="검색하세요">
			<button id="btn-search">검색</button>
		</div>
			
		<br>
		<sec:authorize access="hasRole('ROLE_ADMIN')">	<!-- 로그인된 사용자의 권한이 ADMIN인 경우에만 보이도록 -->
			<button onclick="deleteData()">삭제하기</button>
			<button onclick="location.href = '/user/write?boardType=freeboard'">신규등록</button>
		</sec:authorize>
	</section>

</body>
</html>