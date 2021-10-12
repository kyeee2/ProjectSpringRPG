<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Functions --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글수정</title>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/update.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<!-- CKEditor 적용 -->
<script src="${ pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>						
</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	
	<%-- 메인 컨텐츠 --%>
	<section>
		<h2>글수정</h2>
		<form id="frm" name="frm" method="post">
			<input type="hidden" name="boardType" value="">
			<input type="hidden" name="uid" value="">
			<table>
			<tr>
				<th><label for="nickName">작성자</label></th>
				<td>
					<span class="nickName"></span> <%-- 작성자 이름은 변경 불가 --%>
					<input type="hidden" name="nickName" value=""/>
				</td>
			</tr>
			<c:choose>
				<c:when test="${ param.boardType == 'movieboard' }">
					<tr>
						<th><label for="subject">주제</label></th> 
						<td>
							<input type="text" name="subject" value=""/>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<tr>
				<th><label for="title">제목</label></th>
				<td>
					<input type="text" name="title" value=""/><span id="title-message" class="warning" style="color:red;"></span>
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
			<button id="btn-submit" type="button">수정완료</button>	<%-- type을 submit 에서 button으로 바꿈 --%>
		</form>
		<button onclick="history.back()">이전으로</button>
		<button onclick="location.href='../${ param.boardType }'">목록보기</button>
		<br>
	</section>
</body>
</html>
