<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>시사회 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/update.js"></script>
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
textarea{
	width: 700px;
	height: 500px;
	resize: none;
}

button:hover {
	box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
}

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

#btn-submit {
	background-color: #4CAF50;
	margin: auto;
}

#btn_list {
	background-color: #B0B0B0;
}

#btn_back {
	background-color : #D5D5D5;
}

#btn-update {
	background-color: #4CAF50;
}

#frm {
	margin: auto;
}
#head {
	margin: auto;
	line-height: 130px;
	text-align: center;
}
#img {
	float: center;
}

#frmUpdate {
	width: 30%;
	margin:auto;
	justify-content: center;
	text-align: center;
	margin-left:400px;
}

#ckeditor {
	width: 100%;
	float: right;
}

#title{
	width: 640px;
}

.title{
	width: 640px;
}

#btn-cancel{
	background-color: #FF6C6C;
}

#img {
	width: 640px;
}



</style>
<body>
<h2 id="head">시사회 수정</h2>
<form action="./updateOk" id="frmUpdate" name="frmUpdate" method="post" enctype="multipart/form-data">
<input type="hidden" name="uid" value="">
<div id="file-delete"><%-- 현재 이미지를 삭제한다면 여기 담아서 보내기 --%></div>
<img id="img" src="" data-img="" />
<button type="button" id="btn-update" class="btn">사진수정</button>
<button type="button" id="btn-cancel" class="btn" style="display: none;" onclick="">수정취소</button>
<div id="file-change" style="display: none;"><input type="file" id="file-new" name="file" value=""></div>

<input type="text" name="title" value="" id="title"/><br><br>

<textarea name="content" id="ckeditor"></textarea>
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
<button id="btn-submit" type="submit">수정완료</button>
</form>
<button id="btn_list" onclick="location.href='list'">목록보기</button>
<button id="btn_back" onclick="history.back()">이전으로</button>
<br>
</body>
</html>


