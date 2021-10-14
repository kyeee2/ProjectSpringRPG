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
<!-- CKEditor 적용 -->
<script src="${ pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
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
	width: 100%;
}

button:hover{
	box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
}

#btn_list {
	background-color: #B0B0B0;
	height: 40px;
	width: 700px;
	margin-left: 364px;
	margin-bottom: 200px;
}

#btn-submit{
	background-color: #4CAF50;
	height: 40px;
	width: 700px;
	margin-left: 220px;
}

textarea {
	width:700px;
	height: 500px;
	resize: none;
}

#head {
	width:80%;
	margin: auto;
	height: 100px;
	text-align: center;
	line-height: 100px;
}

#frmWrite {
	width: 80%;
	margin: auto;
	justify-content: center;
	text-align: center;
}

input {
	width: 700px;
	height: 30px;
}

#file{
	float: right;
	width: 100%;
}

#photo {
	margin: auto;
	height: 20px;
	float: right;
}




</style>
<body>
<h2 id="head">시사회 작성</h2>

<form action="./writeOk" id="frmWrite" name="frmWrite" method="post" enctype="multipart/form-data">
	<input type="text" name="title" placeholder="제목을 입력하세요"/><br><br>
	<textarea name="content" id="ckeditor" placeholder="내용을 입력하세요"></textarea>
	<!-- 내용 작성할 때 CKEditor 사용 -->
		<script>
			CKEDITOR.replace("ckeditor", {
				allowedContent: true, // HTML 태그 자동 삭제 방지 설정
				width: '640px',
				height : '400px',
				filebrowserUploadUrl: '${pageContext.request.contextPath}/file/ckUpload',
				toolbar : [
			        ['Font', 'FontSize'],
			        ['BGColor', 'TextColor' ],
			        ['Bold', 'Italic', 'Strike', 'Superscript', 'Subscript', 'Underline', 'RemoveFormat'],   
			        ['BidiLtr', 'BidiRtl'],
			        '/',
			        ['Image', 'SpecialChar', 'Smiley'],
			        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
			        ['Blockquote', 'NumberedList', 'BulletedList'],
			        ['Link', 'Unlink'],
			        ['Source'],
			        ['Undo', 'Redo']
				]
			});
			</script>
			<br><br>
	<div id="photo">
		<input type="file" name="file" id="file" accept="*" multiple /><br>
	</div>
	
	<br><br>
	<button id="btn-submit" type="submit">작성완료</button>
</form>
<br>
<br>
<br>
<button id="btn_list" type="submit" onclick="location.href = 'list'">목록으로</button>
</body>
</html>
