<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
   
<c:choose>
	<c:when test="${empty pw }">
	<script>
	alert("회원 정보가 일치하지 않습니다.");
	history.back();
	</script>
</c:when>
	<c:otherwise>
	<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- 만든 JS -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/changePw.js"></script>
<!-- 헤더 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<!-- JQuery 적용 -->
<jsp:include page="/WEB-INF/views/global/header.jsp"/>
</head>
<body>

<form action="findPWOk" method="POST">
<input type="hidden" name="id" value="${ id }">

<div>*새 비밀번호<input type="password" id="pw" name="pw">
<span>8~15자리의 영문, 숫자, 특수문자의 입력만 가능합니다.</span></div>
<div class="check_font" id="newpw_check"></div>
<div>*새 비밀번호 확인<input type="password" id="pwchk" name="newPwCheck">
<span id="alert-success" style="display: none; color:green;">비밀번호가 일치합니다.</span>
    <span id="alert-danger" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span>
</div>
<div><input type="submit" value="변경 완료"></div>

</form>
</body>
</html>

</c:otherwise>
</c:choose>