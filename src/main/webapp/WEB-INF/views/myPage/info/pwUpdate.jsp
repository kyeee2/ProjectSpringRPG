<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
<div>*기존 비밀번호<input type="text" name="pw"></div>
<div>*새 비밀번호<input type="text" name="newPW"></div>
<div>*새 비밀번호 확인<span><input type="text" name="newPWCheck"></span></div>
<div><input type="submit" value="변경 완료"></div>

</form>
</body>
</html>