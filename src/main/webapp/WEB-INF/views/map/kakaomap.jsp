<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%-- Core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Functions --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<head>
<meta charset="UTF-8">
<title>영화관정보</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/map/map.js"></script>
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet">
<link href="${ pageContext.request.contextPath }/CSS/map.css" rel="stylesheet">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f7687bd18520a24ed28fa01e01997dfe&libraries=services"></script>
</head>
<body>
<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
<div><h3>영화관 지도By.KaKaO</h3></div>
<div class="map_wrap">
    <div id="map" style="width:70%;height:760px;position:relative;overflow:hidden;"></div>

    <div id="menu_wrap" class="bg_white">
        <div class="option">
            <div class="my">
                    자신의 위치 입력 : <input type="text" value="" id="keyword" size="15"> 
                    <button type="submit" id="a">검색하기</button> 
            </div>
                    <div class="clear"></div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
</div>
</body>
</html>