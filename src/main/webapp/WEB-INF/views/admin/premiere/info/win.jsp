<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/premiere.js"></script>
</head>
<body>
<h2>시사회 당첨 추첨</h2>

시사회 : 
<select id="premiereList" style="width:100px">
	<option value=""></option>
	
</select><br>
당첨자 수 : <input type="number">
<button id="premiereButton">추첨</button>

</body>
</html>