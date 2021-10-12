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
<style>
button {
	border: none;
	color: white;
	padding: 5px 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	float: left;
	margin-left: 40px;
	border-radius: 5px;
}

button:hover{
	box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
}

#btn_list {
	background-color: #B0B0B0;
}

#btn-submit{
	background-color: #4CAF50;
}

textarea {
	width:700px;
	height: 500px;
	resize: none;
}

</style>
<body>
<h2>시사회 작성</h2>

<form id="frm" name="frm" method="post" enctype="multipart/form-data">
	제목:
	<input type="text" name="title" placeholder="제목을 입력하세요"/><br>
	내용:<br>
	<textarea name="content" placeholder="내용을 입력하세요"></textarea>
	<div>
		첨부파일(이미지 파일만 가능):
		<input type="file" name="photo" accept="image/png, image/jpeg"/><br>
	</div>
	
	<br><br>
	<button id="btn-submit" type="button">작성완료</button>
</form>
<br>
<button id="btn_list" type="button" onclick="location.href = 'list'">목록으로</button>
</body>
</html>
















