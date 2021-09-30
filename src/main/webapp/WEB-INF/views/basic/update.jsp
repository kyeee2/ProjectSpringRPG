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
<title>글수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/update.js"></script>
</head>
<body>
<h2>글수정</h2>
<form id="frm" name="frm" method="post">
	<input type="hidden" name="boardType" value="">
	<input type="hidden" name="uid" value="">
	작성자 : <span name=nickName></span><br> <%-- 작성자 이름은 변경 불가 --%>
	<c:choose>
		<c:when test="${ param.boardType == 'movieboard' }">
			주제: 
			<input type="text" name="subject" value=""/><br>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	제목 :
	<input type="text" name="title" value=""/><br>
	내용 : <br>
	<textarea name="content"></textarea>
	<br><br>
	<button id="btn-submit" type="button">수정완료</button>	<%-- type을 submit 에서 button으로 바꿈 --%>
</form>
<button onclick="history.back()">이전으로</button>
<button onclick="location.href='${ param.boardType }'">목록보기</button>
<br>
</body>
</html>
