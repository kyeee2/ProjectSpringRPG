<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시사회 응모</title>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
</head>
<body>
<h2> 시사회 응모 </h2>
아이디 : 
<input type="text" name="userId" placeholder="아이디를 입력하세요"/>
이메일 : 
<input type="text" name="userEmail" placeholder="이메일을 입력하세요"/>

<button id="submit" type="button">응모 하</button>
</body>
</html>