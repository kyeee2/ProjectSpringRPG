<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<c:choose>
	<c:when test="${empty id }">
		<script>
			alert("잘못된 정보 입니다.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("회원님의 아이디는 ${ id }입니다");
			location.href = "/findIDPW"; 
		</script>
	</c:otherwise>
</c:choose>