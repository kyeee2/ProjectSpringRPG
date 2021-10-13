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
<link href="${ pageContext.request.contextPath }/CSS/basic/findIDPW.css" rel="stylesheet" type="text/css">

<!-- 만든 JS -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/findIDPW.js"></script>
<!-- 헤더 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<!-- JQuery 적용 -->
</head>
<body>
<jsp:include page="/WEB-INF/views/global/header.jsp"/>
<h3>ID 찾기</h3>
<form method="post" class="form-signin" action="findIDOk" name="findform">
<div>이름: <input type="text" id="name1" name="name"></div>
<div>전화번호 :<input type="text" id="phonenum1" name="phonenum"></div>

<div><input type="submit" onclick="return findID();" value="확인"></div>

</form>
<h3>PW 찾기</h3>
<form method="post" class="form-signin" action="changePw" name="findform2">
<div>이름:<input type="text" id="name2" name="name"></div>

<div>아이디:<input type="text" id="id2" name="id"></div>

<div>전화번호:<input type="text" id="phonenum2" name="phonenum"></div>

<div><input class="btn btn-lg btn-secondary btn-block text-uppercase" type="submit" name="findPW" onclick="return findPW();" value="check"></div>
<br>
<br>



</form>









	<script type="text/javascript">
		function closethewindow(){
			self.close();
		}
	</script>

</body>
</html>
