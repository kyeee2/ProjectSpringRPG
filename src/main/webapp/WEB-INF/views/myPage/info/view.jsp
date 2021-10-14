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
<link href="${ pageContext.request.contextPath }/CSS/myPage/info/side_menu.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/myPage/info/view.css" rel="stylesheet" type="text/css">
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>



<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>


</head>
<body>
<jsp:include page="/WEB-INF/views/global/header.jsp"/>
<section>
<jsp:include page="/WEB-INF/views/myPage/info/side_menu.jsp"/>
<br><br>
<h3>마이페이지</h3>
<br><br>
<div class="total-content">
<div class="account-content">
아이디 <span class="content">${list[0].id }</span>
</div><hr>
<br>
<div class="account-content">
핸드폰 번호 <span class="content">${list[0].phonenum }</span>
</div><hr>
<br>
<div class="account-content">
이름 <span class="content">${list[0].name }</span>
</div><hr>
<br>
<div class="account-content">
닉네임 <span class="content">${list[0].nickname }</span>
</div><hr>
<br>
<div class="account-content">
생년월일 <span class="content">${list[0].birthday }</span>
</div><hr>
<br>
<div class="account-content">
프로필 사진 <span class="content"><img src="/file/customer/${ list[0].profile }" /></span>
</div>
<br><br><br>
<hr>
<div class="button">
<button onclick="location.href='myInfo/pwUpdate'">비밀번호 변경</button>
<button onclick="location.href='myInfo/update'">회원 정보 수정</button>
<button onclick="deleteUser()">회원탈퇴</button>
</div></div>
</section>
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