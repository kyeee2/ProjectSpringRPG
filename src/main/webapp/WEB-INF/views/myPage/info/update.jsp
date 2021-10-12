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
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/myPage/info/update.js"></script>
</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
<h3>마이페이지</h3>
<ul class="list">
<li>회원정보</li>
<li>내가 쓴 글,댓글</li>
<li>쪽지</li>
</ul>
<form action="./updateOk" method="POST" enctype="multipart/form-data">

<div>아이디 ${list[0].id }</div>
<div>*핸드폰 번호 <input type="text" name="phonenum" value="${list[0].phonenum }"/></div>
<div>이름 ${list[0].name }</div>
<div>닉네임 <input type="text" name="nickname" value="${list[0].nickname }"/></div>
<div>생년월일 ${list[0].birthday }</div>
<div id="file-delete"><%-- 현재 이미지를 삭제한다면 여기 담아서 보내기 --%></div>
<div>프로필사진<img src="/file/customer/${ list[0].profile }"></div>
<button type="button" id="btn-update" class="btn">사진수정</button>
<button type="button" id="btn-cancel" class="btn" style="display: none;" onclick="">수정취소</button>
<div id="file-change" style="display: none;"><input type="file" id="file-new" name="file" value=""></div>
<br>
<div><input type="submit" value="수정 완료"></div>
</form>
</body>
</html>

</c:otherwise>
</c:choose>