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
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/view.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	
	<%-- 메인 컨텐츠 --%>
	<section>
		<h2>글읽기</h2>
		<br>
		<button id="btn_good">좋아요</button>
		<div id="result"></div>
		<hr>
		<br>
		<div id="btn_group">
			<button onclick="location.href='/user/update?boardType=${ param.boardType }&uid=${ param.uid }'">수정하기</button>
			<button onclick="location.href='${ param.boardType }'">목록보기</button>
			<button id="doDelete">삭제하기</button>
			<button onclick="location.href='/user/write?boardType=${ param.boardType }'">신규등록</button>
		</div>
		<br><br>
		<h5>댓글목록</h5>
		<br>
		<form id="commentFrm" name="commentFrm" method="get">
		<input type="hidden" name="buid" value="${param.uid }"/>
		<input type="hidden" name="boardType" value="${param.boardType }"/>
		<input type="text" id = "content" name="content" placeholder="내용을 입력하세요"/>
		<span>
			<button type="button" name="commentInsertBtn">댓글등록</button>
		</span>
		</form>
		<br>
		<div id="comment">
		</div>
	</section>
	
</body>
</html>