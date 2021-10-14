<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자유게시판</title>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/freeboard/list.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/freeboard/freeboard.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	
	<%-- 메인 컨텐츠 --%>
	<section>
		<div class="head-wrap">
		<h2 id="head">자유게시판</h2>
		</div>
		<div class="page-wrap">
		<a id="pageinfo"> </a>
		<a id="pageRows"></a>
		</div>
		<div class="clear"></div>
		<table id="list">
			<thead>
				<th class="num">NO</th>
				<th class="num">추천수</th>
				<th class="title">제목</th>
				<th class="num1">작성자</th>
				<th class="num1">작성일</th>
				<th class="num1">조회수</th>
			</thead>
			<tbody id="vogue_list">
			<%-- 인기글 목록 JS로 테이블 생성 --%>
			</tbody>
			<tbody id="board_list">
			<%-- 게시판 전체 목록 JS로 테이블 생성 --%>
			</tbody>
			<tbody id="search_list">
			<%-- 검색 목록 JS로 테이블 생성 --%>
			</tbody>
		</table>
		<%-- [페이징] --%>
		<div class="center">
		<%-- 자유게시판 안에서 검색 기능 - 제목과 내용으로 검색 가능 --%>
		<sec:authorize access="isAuthenticated()">	<!-- 로그인되어있는 경우에만 버튼 보이도록 -->
			<button onclick="location.href = '/user/write?boardType=freeboard'" id="new-regi">신규등록</button>
		</sec:authorize>
			<ul class="pagination" id="pagination"></ul>
			<input type="text" id="input-search" name="search" placeholder="제목이나 내용으로 검색">
			<button id="btn-search">검색</button>
		
		
		</div>
		
		
		<br>
	</section>

</body>
</html>