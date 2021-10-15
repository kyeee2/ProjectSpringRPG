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
<!-- google 폰트 사용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Jua&family=Nanum+Gothic&display=swap" rel="stylesheet">

<!-- JQuery 사용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- 만든 css 사용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/footer.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/myPage/info/pwUpdate.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/myPage/info/side_menu.css" rel="stylesheet" type="text/css">
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<!-- 만든 JS -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/myPage/info/pwUpdate.js"></script>

</head>
<body>
	<%-- 푸터 삽입 --%>
<jsp:include page="/WEB-INF/views/global/footer.jsp"/>
<section>
<jsp:include page="/WEB-INF/views/global/header.jsp"/>
<jsp:include page="/WEB-INF/views/myPage/info/side_menu.jsp"/>
<br><br>
<h3>마이페이지</h3>
<br><br>
<form action="pwUpdateOk" method="POST">

<div class="total-content">
<div class="account-content">*새 비밀번호<input type="password" id="pw" name="pw">
<span>*8~15자리의 영문, 숫자, 특수문자의 입력만 가능합니다.*</span></div><hr><br>
<div id="newpw_check"></div>
<div class="account-content">*새 비밀번호 확인<input type="password" id="pwchk" name="newPwCheck">
<span id="alert-success" style="display: none; color:green;">비밀번호가 일치합니다.</span>
    <span id="alert-danger" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span>
</div><hr>
<br><br>
<div><input type="submit" value="변경 완료"></div>
</div>
</form>
</section>
</body>
</html>

