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
<link href="${ pageContext.request.contextPath }/CSS/myPage/info/update.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/myPage/info/update.js"></script>
</head>
<body>
	<section>
	
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	<jsp:include page="/WEB-INF/views/myPage/info/side_menu.jsp"/>
<h3>마이페이지</h3>

<form class="total-content" action="./updateOk" method="POST" enctype="multipart/form-data">
<div class="account-content">아이디 <span class="content">${list[0].id }</span></div>
<hr>
<div class="account-content">*핸드폰 번호 <span class="content"><input type="text" name="phonenum" value="${list[0].phonenum }"/></span></div>
<hr>
<div class="account-content">이름 <span class="content">${list[0].name }</span></div>
<hr>
<div class="account-content">닉네임 <span class="content"><input type="text" name="nickname" value="${list[0].nickname }"/></span></div><hr>
<div class="account-content">생년월일 <span class="content">${list[0].birthday }</span></div><hr>
<div  class="account-content" id="file-delete"><%-- 현재 이미지를 삭제한다면 여기 담아서 보내기 --%></div>
<div  class="account-content">프로필사진</div><span class="content"><img src="/file/customer/${ list[0].profile }"><button type="button" id="btn-update" class="btn">사진수정</button></span>

<br><br><br><br>

<button type="button" id="btn-cancel" class="btn" style="display: none;" onclick="">수정취소</button>
<div class="account-content" id="file-change" style="display: none;"><input type="file" id="file-new" name="file" value=""></div>

<br><br><br><br><hr><br><br>
<div><input type="submit" value="수정 완료"></div>
</form>
</section>	
</body>
</html>

</c:otherwise>
</c:choose>