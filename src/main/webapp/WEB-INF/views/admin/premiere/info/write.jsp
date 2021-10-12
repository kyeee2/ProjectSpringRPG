<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시사회 작성</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/write.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
</head>
<script>

</script>
<body>
<h2>시사회 작성</h2>
<form action="./writeOk" id="frmWrite" name="frmWrite" method="post" enctype="multipart/form-data">
	제목:
	<input type="text" name="title" placeholder="제목을 입력하세요"/><br>
	내용:<br>
	<textarea name="content" placeholder="내용을 입력하세요"></textarea>
	<div>
		첨부파일(이미지 파일만 가능):
		<input type="file" name="file" id="file" accept="*" multiple /><br>
	</div>
	<br><br>
	<button  id="btn-submit">작성완료</button>
</form>
<br>
<button type="button" onclick="location.href = 'list'">목록으로</button>
</body>
</html>