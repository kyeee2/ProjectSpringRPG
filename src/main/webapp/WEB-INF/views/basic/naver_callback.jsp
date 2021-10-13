<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<sec:authorize access="isAuthenticated()">
<script>
$(document).ready(function(){
	
	location.href="/loginOk";
	
});
</script>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">	
<script>
$(document).ready(function(){
	
	location.href="/login";
	
});
</script>
</sec:authorize>