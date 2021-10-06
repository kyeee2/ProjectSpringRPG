<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>시사회 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/update.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
</head>
<body>
<h2>시사회 수정</h2>
<form id="frm" name="frm" method="post">
<input type="hidden" name="uid" value="">
제목 :
<input type="text" name="title" value=""/><br>
첨부파일 : 
<input type="text" name="photo" value=""/><br>
내용 : <br>
<textarea name="content"></textarea>
<br><br>
<button id="btn-submit" type="button">수정완료</button>
</form>
<button onclick="history.back()">이전으로</button>
<button onclick="location.href='list'">목록보기</button>
<br>
</body>
</html>