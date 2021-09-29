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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/movieInfo/main.js"></script>
</head>
<body>
<!-- 박스오피스 1-5위 -->
<div>
	<h3>박스오피트 순위</h3>
	<c:set var="cnt" value="${ fn:length(titleShowing) }" />
	<c:forEach var="i" begin="0" end = "${ cnt - 1 }" varStatus="status">
		<%--<a href="https://movie.naver.com/movie/bi/mi/basic.naver?code=${ linkShowing[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> --%>
		<a href="/movieInfo/view?code=${ codeShowing[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> <%-- 영화 code값만 넘기기 --%>
			<img src="${ posterShowing[status.index] }" />
			<h5>${ titleShowing[status.index] }</h5>
		</a>
	</c:forEach>
</div>
<!-- 개봉 예정작 3일치 -->
<div>
	<h3>개봉예정작</h3>
	<c:set var="cnt" value="${ fn:length(titleUpcomming) }" />
	<c:forEach var="i" begin="0" end = "${ cnt - 1 }" varStatus="status">
		<%--<a href="https://movie.naver.com/movie/bi/mi/basic.naver?code=${ linkUpcomming[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> --%>
		<a href="/movieInfo/view?code=${ codeUpcomming[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> <%-- 영화 code값만 넘기기 --%>	
			<img src="${ posterUpcomming[status.index] }" />
			<h5>${ titleUpcomming[status.index] }</h5>
		</a>
	</c:forEach>
</div>

<!-- 영화 검색 -->
<h5>찾는 영화가 없으신가요? 그렇다면 click!</h5>
<form method="get">
	<input type="search" placeholder="검색하세요" id="searchMovie" name="movieName" value="" >
	<button type="button" id="search">검색</button>
</form>
<div id="searchResult"></div>
</body>
</html>