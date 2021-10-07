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
<title>영화정보</title>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/movieInfo/main.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	
	<%-- 메인 컨텐츠 --%>
	<section>
		<!-- 박스오피스 1-5위 -->
		<div>
			<h3>박스오피트 순위</h3>
			<c:set var="cnt" value="${ fn:length(titleShowing) }" />
			<c:if test="${ cnt > 0 }">
				<c:forEach var="i" begin="0" end = "${ cnt - 1 }" varStatus="status">
					<%--<a href="https://movie.naver.com/movie/bi/mi/basic.naver?code=${ linkShowing[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> --%>
					<a href="/movieInfo/view?code=${ codeShowing[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> <%-- 영화 code값만 넘기기 --%>
						<img src="${ posterShowing[status.index] }" />
						<h5>${ titleShowing[status.index] }</h5>
					</a>
				</c:forEach>
			</c:if>
		</div>
		<!-- 개봉 예정작 3일치 -->
		<div>
			<h3>개봉예정작</h3>
			<c:set var="cnt" value="${ fn:length(titleUpcomming) }" />
			<c:if test="${ cnt > 0 }">
				<c:forEach var="i" begin="0" end = "${ cnt - 1 }" varStatus="status">
					<%--<a href="https://movie.naver.com/movie/bi/mi/basic.naver?code=${ linkUpcomming[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> --%>
					<a href="/movieInfo/view?code=${ codeUpcomming[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> <%-- 영화 code값만 넘기기 --%>	
						<img src="${ posterUpcomming[status.index] }" />
						<h5>${ titleUpcomming[status.index] }</h5>
					</a>
				</c:forEach>
			</c:if>
		</div>
		
		<!-- 영화 검색 -->
		<span id="findMovie">찾는 영화가 없으신가요? 그렇다면 click!</span>
		<form id="searchForm" name="searchForm" method="get">
			<input type="search" placeholder="검색하세요" id="searchMovie" name="movieName" value="" >
			<button type="button" id="search">검색</button>
		</form>
		<div id="searchResult"></div>
	</section>
</body>
</html>