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
</head>
<body>
<h2>시사회 수정</h2>
<form action="./updateOk" id="frmUpdate" name="frmUpdate" method="post" enctype="multipart/form-data">
<input type="hidden" name="uid" value="">
<div id="file-delete"><%-- 현재 이미지를 삭제한다면 여기 담아서 보내기 --%></div>
첨부파일 : <img id="img" src="" data-img="" /><br>
<button type="button" id="btn-update" class="btn">사진수정</button>
<button type="button" id="btn-cancel" class="btn" style="display: none;" onclick="">수정취소</button>
<div id="file-change" style="display: none;"><input type="file" id="file-new" name="file" value=""></div>
<br>
제목 :
<input type="text" name="title" value=""/><br>
내용 : <br>
<textarea name="content"></textarea>
<br><br>
<button id="btn-submit" type="submit">수정완료</button>
</form>
<button onclick="history.back()">이전으로</button>
<button onclick="location.href='list'">목록보기</button>
<br>
</body>
</html>