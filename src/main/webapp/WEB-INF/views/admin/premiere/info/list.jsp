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

<!-- 부트스트랩 사용 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
table {
width: 80%;
margin: auto;
}
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
#btn_write {
	float: right;
	background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
}

#btn_write:hover {
	box-shadow: 0 80px 0 0 rgba(0,0,0,0.25) inset, 0 -80px 0 0 rgba(0,0,0,0.25) inset;
}

#btn_delete:hover {
	box-shadow: 0 80px 0 0 rgba(0,0,0,0.25) inset, 0 -80px 0 0 rgba(0,0,0,0.25) inset;
}

#btn_delete {
	float: left;
	background-color: #f44336;
  border: none;
  color: white;
  padding: 10px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
}

#pagination {
	display: inline-block;
	color: black;
  	float: left;
  	padding: 8px 16px;
  	text-decoration: none;
}

.menu_btn {
	width:15%;
	height:100px;
	background-color:red;
	float:left;
	margin:auto;
	text-align: center;
}

.button_group {
	width:85%;
	height:150px;
	background-color:yellow;
	float:right;
}

</style>
</head>
<body>
	
	<h2>시사회 관리</h2>
	<div class="menu_btn">
		<p><a href="list">시사회 정보</a></p>
		<p><a href="/premiereWin">시사회 당첨자 발표</a></p>
	</div>
	
	
	<%-- header 헤더 --%>
	<div class="d01">
		<div class="left" id="pageinfo"></div>
		<div class="right" id="pageRows"></div>
	</div>
	<div class="clear"></div>
	
	
	<%-- 목록 --%>
	<form id="frmList" name="frmList">
		<%--
		<table id="list">
			<thead>
				<th></th>
				<th>uid</th>
				<th>제목</th>
				<th>첨부파일</th>
				<th>내용</th>
			</thead>
			<tbody>
			</tbody>
		</table>
		--%>
		<div id="card-list"></div>
	</form>
	
	<%-- 페이징 --%>
	<div>
		<div id="pagination"></div>
	</div>
	
	<%-- bottom button --%>
	<div class="button_group">
	<button id="btn_delete" onclick="deleteData()">삭제하기</button>
	<button id="btn_write" onclick="location.href = 'write'">글 쓰기</button>
	</div>
</body>
</html>










