<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/write.js"></script>
</head>
<script>

</script>
<body>
<h2>글작성</h2>
<form id="frm" name="frm" method="post">
	<input type="radio" name="boardType" value="freeboard" ${ (param.boardType=='freeboard')?'checked':'' } />자유게시판
	<input type="radio" name="boardType" value="movieboard" ${ param.boardType=='movieboard'?'checked':'' }/>영화리뷰
	<input type="radio" name="boardType" value="noticeboard" ${ param.boardType=='noticeboard'?'checked':'' }/>공지사항<br>
	작성자:
	<input type="text" name="nickName"/><br>
	<div id="subject"></div>
	제목:
	<input type="text" name="title"/><br>
	내용:<br>
	<textarea name="content"></textarea>
	<br><br>
	<button id="btn-submit" type="button">작성완료</button>	<%-- type을 submit 에서 button으로 바꿈 --%>
</form>
<br>
<button type="button" onclick="location.href = '${ param.boardType }'">목록으로</button>
</body>
</html>