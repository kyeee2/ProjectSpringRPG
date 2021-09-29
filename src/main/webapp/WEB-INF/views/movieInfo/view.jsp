<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/movieInfo/view.js"></script>
</head>
<body>
<h2 id="moviename"></h2>
<div id="poster"></div>
<div id="info">
	<div id="star"></div>
	<span id="genre"></span><span> | </span>
	<span id="nation"></span><span> | </span>
	<span id="running-time"></span><span> | </span>
	<span id="open-date"></span><br>
	시청연령 : <div id="viewing-page"></div>
	<div id="summary"></div>
</div>
<div id="directorActor"></div>
</body>
</html>