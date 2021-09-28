<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.net.*" %> <%-- URLEncoder 외 --%>
    
<%-- JSTL을 배웠다면 활용해보자 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>     
    
<% request.setCharacterEncoding("utf-8"); %>    

<c:set var="originalFileNames" value="${paramValues.originalFileName }"/>
<c:set var="fileSystemNames" value="${paramValues.fileSystemName }"/>
<c:set var="cnt" value="${fn:length(paramValues.originalFileName)}"/>
    
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>${cnt } 개의 파일 확인</h3>
<ul>
<c:forEach var="i" begin="0" end="${cnt - 1 }" varStatus="status">
	<li>파일: ${i + 1 }</li>
	<ul>
		<li>원본이름: ${originalFileNames[status.index] }</li>
		<li>파일시스템: ${fileSystemNames[status.index] }</li>
	</ul>
</c:forEach>
</ul>

<h3>이미지 보기 </h3>
<c:forEach var="i" begin="0" end="${cnt -1 }">
<div style="width:300px">
	<img style="width:100%; height:auto;"
		src="upload/${fileSystemNames[i] }"/>
</div>
</c:forEach>
<hr>

</body>
</html>