<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
	<c:when test="${empty list || fn:length(list) == 0 }">
		<script>
			alert("해당 정보가 삭제되거나 없습니다");
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<jsp:include page="/WEB-INF/views/global/header.jsp"/>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>

</head>
<body>
<h3>마이페이지</h3>
<ul class="list">
<li>회원정보</li>
<li>내가 쓴 글,댓글</li>
<li>쪽지</li>
</ul>
아이디 ${list[0].id }
<br>
핸드폰 번호 ${list[0].phonenum }
<br>
이름 ${list[0].name }
<br>
닉네임 ${list[0].nickname }
<br>
생년월일 ${list[0].birthday }
<br>
프로필 사진 <img src="/file/customer/${ list[0].profile }" />
<br>

<div></div>
<div></div>
<div></div>
<div></div>
<div></div>
<div></div>
<div></div>
<button onclick="location.href='myInfo/pwUpdate'">비밀번호 변경</button>
<button onclick="location.href='myInfo/update'">회원 정보 수정</button>
<button onclick="deleteUser()">회원탈퇴</button>
<script>
    function deleteUser() {
        if (confirm("정말 회원정보를 삭제하시겠습니까?")) {
            location.href='myInfo/deleteOk';
        }
    }
</script>
</body>
</html>

</c:otherwise>
</c:choose>