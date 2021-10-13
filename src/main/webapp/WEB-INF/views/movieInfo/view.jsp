<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/movieinfo/view.css" rel="stylesheet" type="text/css">

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/movieInfo/view.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>

</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>
		
	<%-- 메인 컨텐츠 --%>
	<section>
	<div class="flex-container">
		<div id="poster"></div>
		<div id="info">
		<h2 id="moviename"></h2>
			<div id="star"></div>
			<span id="genre"></span><span> | </span>
			<span id="nation"></span><span> | </span>
			<span id="running-time"></span><span> | </span>
			<span id="open-date"></span><br>
			<div id="viewing-page"></div>
			<div id="summary"></div>
	</div>
		</div>
		<h2 class="h2con">출연진</h2>
		<div id="directorActor"></div>
		<iframe id="video" width="560" height="315" src="" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"></iframe>
	</section>
</body>
</html>