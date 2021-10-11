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
<title>Insert title here</title>
<title>MOVIEMANIA</title>
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">

<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/baisc/main.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>
</head>
<body>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	
	<%-- 메인 컨텐츠 --%>
	<section>
		<!-- 박스오피스 1-5위 -->
		<div>
			<h3>박스오피트 순위</h3>
			<c:set var="cnt" value="${ fn:length(titleShowing) }" />
			<c:if test="${ cnt > 0 }">
				<c:forEach var="i" begin="0" end = "${ cnt - 1 }" varStatus="status">
					<%--<a href="https://movie.naver.com/movie/bi/mi/basic.naver?code=${ linkShowing[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> --%>
					<a href="/movieInfo/view?code=${ codeShowing[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> <%-- 영화 code값만 넘기기 --%>
						<img src="${ posterShowing[status.index] }" />
						<h5>${ titleShowing[status.index] }</h5>
					</a>
				</c:forEach>
			</c:if>
		</div>
		<!-- 최근 인기 글 10개 -->
		<div id="vogue-list">
			<h3>인기글</h3>
			<c:set var="cnt" value="${ fn:length(vogueList) }" />
			<c:if test="${ cnt > 0 }">
				<table>
					<thead>
						<th>NO</th>
						<th>제목</th>
					</thead>
					<tbody>
						<c:forEach var="i" begin="0" end = "${ cnt - 1 }" varStatus="status">
							<tr>
								<td>${ status.index + 1 }</td>
								<td><a href="/view?boardType=${ vogueList[status.index].boardType }&uid=${ vogueList[status.index].uid }">${ vogueList[status.index].title }</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</section>
</body>
</html>