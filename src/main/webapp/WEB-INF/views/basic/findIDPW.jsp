<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
  
	

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/footer.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/basic/findIDPW.css" rel="stylesheet" type="text/css">
<!-- 만든 JS -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/findIDPW.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<!-- JQuery 적용 -->
</head>
<body>
<%-- 푸터 삽입 --%>
<jsp:include page="/WEB-INF/views/global/footer.jsp"/>
<!-- 헤더 적용 -->
<jsp:include page="/WEB-INF/views/global/header.jsp"/>
<div class="totalfind">
<form method="post" class="form-findid" action="findIDOk" name="findform">
<h3 class="findid">ID 찾기</h3>
<div class="name-1">이름: <input type="text" style="font-size:25px;" id="name1" name="name"></div>
<div class="phone-1">전화번호:<input type="text" style="font-size:25px;" id="phonenum1" name="phonenum"></div>

<div><input class="findchk-1" type="submit" onclick="return findID();" value="확인"></div>

</form>
<form method="post" class="form-findpw" action="changePw" name="findform2">
<h3 class="findpw">PW 찾기</h3>
<div class="name-2">이름:<input type="text" style="font-size:25px;" id="name2" name="name"></div>

<div class="id">아이디:<input type="text" style="font-size:25px;" id="id2" name="id"></div>

<div class="phone-2">전화번호:<input type="text" style="font-size:25px;" id="phonenum2" name="phonenum"></div>

<div><input class="findchk-2" type="submit" name="findPW" onclick="return findPW();" value="확인"></div>
<br>
<br>



</form>

</div>







	<script type="text/javascript">
		function closethewindow(){
			self.close();
		}
	</script>

</body>
</html>
