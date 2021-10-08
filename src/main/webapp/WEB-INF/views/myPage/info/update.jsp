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
<form action="updateOk" method="POST">
<input type="hidden" name="uid" value="${list[0].uid }"/>
<div>아이디 ${list[0].id }</div>
<div>*핸드폰 번호 <input type="text" name="phonenum" value="${list[0].phonenum }"/></div>
<div>이름 ${list[0].name }</div>
<div>닉네임 <input type="text" name="nickname" value="${list[0].nickname }"/></div>
<div>생년월일 ${list[0].birthday }</div>
<div>프로필사진<img src=#>4x6<b>500KB 이하</b><input type="button" value="업로드"></div>
<div><input type="submit" value="수정 완료"></div>
</form>
</body>
</html>