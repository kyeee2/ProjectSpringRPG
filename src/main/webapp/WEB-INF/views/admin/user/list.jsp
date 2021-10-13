<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style>
table {width: 100%;}
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

</style>
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/admin/user/list.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script> 

<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/user/list.js"></script>
</head>
<body>
<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	

<section>
		<div class="head-wrap">
		<h2 id="head">회원 정보</h2>
		</div>
		<div class="clear"><br><br></div>
		<table id="list">
			<thead>
				<tr id="list-height">
				<th class="num">회원번호</th>
				<th class="num">아이디</th>
				<th class="num1">닉네임</th>
				<th class="num1">강제 회원 탈퇴</th>
				</tr>
			</thead>
		
			<tbody id="user_list">
			<%-- JS로 테이블 생성 --%>
			</tbody>
			

		</table>
		<br><br>
		<div class="paging-div">
		 <ul class="pagination" id="pagination">
		 </ul>
		  </div>
		<br>
		</section>
		
</body>
</html>