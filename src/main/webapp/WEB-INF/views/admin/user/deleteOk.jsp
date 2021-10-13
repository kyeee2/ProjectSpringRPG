<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<c:choose>
	<c:when test="${result == 0 }">
		<script>
			
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			
			location.href = "/admin/user"; 
		</script>
	</c:otherwise>
</c:choose>