<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- sec tag 사용하기 위해서 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글작성</title>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/write.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<!-- CKEditor 적용 -->
<script src="${ pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>						
</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	
	<%-- 메인 컨텐츠 --%>
	<section>
		<h2>글작성</h2>
		<form id="frm" name="frm" method="post">
			<table>
				<tr>
					<th><label for="boardType">게시판 종류</label></th>
					<td>
						<input type="radio" name="boardType" value="freeboard" ${ (param.boardType=='freeboard')?'checked':'' } />자유게시판	<%-- 처음에 어떤 게시판에서 넘어왔는지에 따라 라디오 버튼에 표시해주기 --%>
						<input type="radio" name="boardType" value="movieboard" ${ param.boardType=='movieboard'?'checked':'' }/>영화리뷰
						<sec:authorize access="hasRole('ROLE_ADMIN')">	<%-- 관리자만 공지사항 글 작성 가능 --%>
							<input type="radio" name="boardType" value="noticeboard" ${ param.boardType=='noticeboard'?'checked':'' }/>공지사항
						</sec:authorize>
					</td>
				</tr>
				<!-- 
				<tr>
					<th><label for="nickName">작성자</label></th>
					<td>
						<input type="text" name="nickName"/>
					</td>
				</tr>
				 -->
				<tr id="subject">	<%-- 주제는 영화 리뷰에서만 나타남 JS에서 처리 --%>
				</tr>
				<tr>
					<th><label for="title">제목</label></th>
					<td>
						<input type="text" name="title"/><span id="title-message" class="warning" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<th><label for="content">내용</label></th>
					<td>
						<textarea name="content" id="ckeditor"></textarea>
						<!-- <div><%= request.getRealPath("/") %></div>	webapp까지 나옴! -->
						<!-- 내용 작성할 때 CKEditor 사용 -->
						<script>
							CKEDITOR.replace("ckeditor", {
								allowedContent: true, // HTML 태그 자동 삭제 방지 설정
								width: '640px',
								height : '400px',
								filebrowserUploadUrl: '${pageContext.request.contextPath}/file/ckUpload'
							});
						</script>
						<span id="content-message" class="warning" style="color:red;"></span>
					</td>
				</tr>
			</table>
			<br><br>
			<button id="btn-submit" type="button">작성완료</button>	<%-- type을 submit 에서 button으로 바꿈 --%>
		</form>
		<br>
		<button type="button" onclick="location.href = '../${ param.boardType }'">목록으로</button>
	</section>
</body>
</html>