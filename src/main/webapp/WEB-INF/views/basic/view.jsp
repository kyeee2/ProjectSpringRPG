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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/freeboard/view.css" rel="stylesheet" type="text/css">

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
		<h2 id="headtitle"></h2>
		<br>
		<div id="result"></div>
		<br>
		<div class="btn_group">
			<button id="btn_list" onclick="location.href='${ param.boardType }'">목록</button>
			<button id="btn_update" onclick="location.href='/user/update?boardType=${ param.boardType }&uid=${ param.uid }'">수정</button>
			<button id="doDelete">삭제</button>
			<button id="btn_write" onclick="location.href='/user/write?boardType=${ param.boardType }'">글쓰기</button>
			<div class="clear"></div>
		</div>
		<br><br>
		<h3>댓글작성</h3>
		<form id="commentFrm" name="commentFrm" method="get">
		<input type="hidden" name="buid" value="${param.uid }"/>
		<input type="hidden" name="boardType" value="${param.boardType }"/>
		<input type="text" id = "contents" name="content" placeholder="내용을 입력하세요"/>
		<span class="exam">
			<button type="button" name="commentInsertBtn">댓글등록</button>
		</span>
		</form>
		<br><br><br>
		<div id="comment">
		</div>
		<div class="clear"></div>
	</section>
	
</body>
</html>