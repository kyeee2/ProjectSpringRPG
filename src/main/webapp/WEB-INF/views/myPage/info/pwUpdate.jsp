<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
   

	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<!-- 만든 JS -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/myPage/info/pwUpdate.js"></script>
<!-- JQuery 적용 -->
<jsp:include page="/WEB-INF/views/global/header.jsp"/>

</head>
<body>
<h3>마이페이지</h3>
<ul class="list">
<li>회원정보</li>
<li>내가 쓴 글,댓글</li>
<li>쪽지</li>
</ul>
<form action="pwUpdateOk" method="POST">


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

