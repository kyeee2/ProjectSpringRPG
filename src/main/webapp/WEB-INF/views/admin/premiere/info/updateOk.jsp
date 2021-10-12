<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- CORE --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 위에서 필요한 트랜잭션이 마무리 되면 페이지 만들어주기 --%>
<c:choose>
	<c:when test="${result == 0 }">
		<script>
			alert("수정 실패!!!!");
			history.back();  
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("수정 성공");
			location.href = "view?uid=${param.uid}";  <%-- 수정 성공하면 view 로 이동하여 제대로 수정되었는지 확인하기--%>
		</script>
	</c:otherwise>
</c:choose>


























































