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
<title>글조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/view.js"></script>
</head>
<body>
	<h2>글읽기</h2>
	<br>
	<div id="result"></div>
	<hr>
	<br>
	<div id="btn_group">
		<button onclick="location.href='update?boardType=${ param.boardType }&uid=${ param.uid }'">수정하기</button>
		<button onclick="location.href='${ param.boardType }'">목록보기</button>
		<button id="doDelete">삭제하기</button>
		<button onclick="location.href='write?boardType=${ param.boardType }'">신규등록</button>
	</div>
</body>
</html>