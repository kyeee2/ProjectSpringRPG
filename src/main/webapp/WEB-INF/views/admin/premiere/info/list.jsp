<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
<%-- sec tag 사용하기 위해서 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/premiere.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- 부트스트랩 사용 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
table {
width: 40%;
margin: auto;
}
table, th, td {
	border: 1px solid black;
}
#btn_write {
	background-color: #4CAF50;
  border: none;
  color: white;
  padding: 5px 5px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  float: right;
  margin-right: 40px;
  border-radius: 5px;
  
}

button:hover {
	box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
}

.menu {
	width:15%;
	height:200%;
	background-color: white;
	float:left;
	margin:auto;
	text-align: center;
	margin-left: 20px;
}

.nav {
	position: sticky;
	top: 100;
	background-color: #EAEAEA;
	margin-right: 20px;
	margin-top: 100px;
	justify-content: center;
	border-radius: 20px;
}

div.page {
	width:85%;
	height:35px;
	float: right;
}
div.paginations {
	width: 100%;
	display: block;
	border-radius: 5px;
}
.paginations a{
	color: black;
	float: none;
	padding: 8px 16px;
	text-decoration: none;
	border-radius: 5px;
}
.paginations a.active {
  background-color: #4CAF50;
  color: white;
}
.paginations a:hover:not(.active) {
  background-color: #ddd;
}
ul.pagination {
	line-height: 100px;
	width: 100%;
	justify-content : center;
}

p:hover {
	background-color: #BDBDBD;
}

p > a {
	text-decoration : none;
}

div.card-list {	
	list-style-type: none;
	display: flex; 
	flex-wrap:wrap;
	flex-direction: row;
	justify-content: flex-start;
	margin: 0;
	padding: 0;
}

.card {
	float: left;
	width: 350px;
}

.card-img-top {
	width: 300px;
	height: 350px;
	margin: auto;
}

.head1 {
	font-size: 25px;
	width: 80%;
	margin: auto;
	height: 100px;
	text-align: center;
 	line-height: 100px;
 	border-top: solid black;
 	border-bottom: solid black;
}

.btn-primary {
	width:100%;
}

.card-title {
	text-align: center;
}

</style>
</head>
<body>
	
	<div class="menu">
		<div class="nav">
			<div class="m01">
				<p><a href="list">시사회 정보</a></p>
			
				<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
				<sec:authorize access="hasRole('ROLE_ADMIN')">	<!-- 로그인된 사용자의 권한이 ADMIN인 경우에만 보이도록 -->
				<p><a href="../admin/premiere/win">시사회 추첨하기</a></p>
				</sec:authorize>
			</div>
		</div>
	</div>
	
	
	<%-- header 헤더 --%>
	<div class="d01">
		<div class="left" id="pageinfo"></div>
		<div class="right" id="pageRows"></div>
	</div>
	
	
	<!-- 목록 -->
	<form id="frmList" name="frmList">
	<h2 class="head1">시사회 목록</h2>
		<div id="card-list"></div>
	</form>
	<div class="clear"></div>
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">	<!-- 로그인된 사용자의 권한이 ADMIN인 경우에만 보이도록 -->
	<%-- bottom button --%>
		<div class="button">
		<button id="btn_write" onclick="location.href = '/admin/premiere/write'">글 쓰기</button>
		</div>
	</sec:authorize>
	
	
	
	<%-- pagination --%>
	<div class="paginations">
		<ul class="pagination"></ul>
	</div>
	
</body>
</html>



