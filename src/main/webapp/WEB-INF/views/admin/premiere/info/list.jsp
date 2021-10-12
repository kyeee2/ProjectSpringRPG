<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
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
	height:120px;
	background-color: #EAEAEA;
	float:left;
	margin:auto;
	text-align: center;
	border-radius: 20px;
	margin-left: 20px;
}

div.page {
	width:85%;
	height:35px;
	float: right;
}
.paginations {
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
	justify-content : center;
}

p:hover {
	background-color: #BDBDBD;
}

p > a {
	text-decoration : none;
}

</style>
</head>
<body>
	
	<h2>시사회 관리</h2>
	<div class="menu">
		<div class="m01">
			<p><a href="list">시사회 정보</a></p>
		</div>
		<div>
			<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
		</div>
		<div>
			<p><a href="../admin/premiere/win">시사회 추첨하기</a>
		</div>
	</div>
	
	
	<%-- header 헤더 --%>
	<div class="d01">
		<div class="left" id="pageinfo"></div>
		<div class="right" id="pageRows"></div>
	</div>
	<div class="clear"></div>
	
	
	<%-- 목록 --%>
	<form id="frmList" name="frmList">
		<table id="list">
			<thead>
				<th> 시사회 제목 </th>
			</thead>
			<tbody>
			</tbody>
		</table>
	</form>
	
	<%-- bottom button --%>
	<div class="button">
	<button id="btn_write" onclick="location.href = '/admin/premiere/write'">글 쓰기</button>
	</div>
	
	<div class="paginations">
		<ul class="pagination"></ul>
	</div>
	
</body>
</html>






































