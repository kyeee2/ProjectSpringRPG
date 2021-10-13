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
<title>영화정보</title>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/movieinfo/main.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/movieinfo/slick.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath }/CSS/movieinfo/slick-theme.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<!-- JS 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/movieInfo/main.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/movieInfo/slick.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>

</head>
<body>
<script>
  		$(function(){
			$('#slider-div').slick({
				slide: 'div',		//슬라이드 되어야 할 태그 ex) div, li 
				infinite : true, 	//무한 반복 옵션	 
				slidesToShow : 4,		// 한 화면에 보여질 컨텐츠 개수
				slidesToScroll : 1,		//스크롤 한번에 움직일 컨텐츠 개수
				speed : 100,	 // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
				arrows : true, 		// 옆으로 이동하는 화살표 표시 여부
				dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
				autoplay : true,			// 자동 스크롤 사용 여부
				autoplaySpeed : 10000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
				pauseOnHover : true,		// 슬라이드 이동	시 마우스 호버하면 슬라이더 멈추게 설정
				vertical : false,		// 세로 방향 슬라이드 옵션
				prevArrow : "<button type='button' class='slick-prev'>Previous</button>",		// 이전 화살표 모양 설정
				nextArrow : "<button type='button' class='slick-next'>Next</button>",		// 다음 화살표 모양 설정
				dotsClass : "slick-dots", 	//아래 나오는 페이지네이션(점) css class 지정
				draggable : true, 	//드래그 가능 여부 
				
				responsive: [ // 반응형 웹 구현 옵션
					{  
						breakpoint: 960, //화면 사이즈 1280px
						settings: {
							//위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
							slidesToShow:3 
						} 
					},
					{ 
						breakpoint: 768, //화면 사이즈 768px
						settings: {	
							//위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
							slidesToShow:2 
						} 
					}
				]
			});
  		})
	</script>
	<%-- 헤더 삽입 --%>
	<jsp:include page="/WEB-INF/views/global/header.jsp"/>	
	
	<%-- 메인 컨텐츠 --%>
	<section>
		<!-- 박스오피스 1-5위 -->
			<div class="post-slider">
			<h3 class="mainheader">박스오피스 순위</h3>
					<div class="post-wrapper1">
			<c:set var="cnt" value="${ fn:length(titleShowing) }" />
			<c:if test="${ cnt > 0 }">
				<c:forEach var="i" begin="0" end = "${ cnt - 1 }" varStatus="status">
					<%--<a href="https://movie.naver.com/movie/bi/mi/basic.naver?code=${ linkShowing[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> --%>
					<div class="post">
					<a href="/movieInfo/view?code=${ codeShowing[status.index] }" class="movielink"> <%-- 영화 code값만 넘기기 --%>
						<img src="${ posterShowing[status.index] }" alt=""/>
						<h5>${ titleShowing[status.index] }</h5>
					</a>
					</div>
				</c:forEach>
			</c:if>
					</div>
					</div>
		<!-- 개봉 예정작 3일치 -->
			<div class="post-slider">
			<h3 class="mainheader">개봉예정작</h3>
			<i class="fas fa-chevron-left prev"></i>  <!--왼쪽 방향 버튼-->
       	 <i class="fas fa-chevron-right next"></i>   <!--오른쪽 방향 버튼-->
			<div class="post-wrapper">
			<c:set var="cnt" value="${ fn:length(titleUpcomming) }" />
			<c:if test="${ cnt > 0 }">
				<c:forEach var="i" begin="0" end = "${ cnt - 1 }" varStatus="status">
					<%--<a href="https://movie.naver.com/movie/bi/mi/basic.naver?code=${ linkUpcomming[status.index] }" id="movielink" style="display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;"> --%>
					<div class="post">
					<a href="/movieInfo/view?code=${ codeUpcomming[status.index] }" class="movielink"> <%-- 영화 code값만 넘기기 --%>	
						<img src="${ posterUpcomming[status.index] }" />
						<h5>${ titleUpcomming[status.index] }</h5>
					</a>
					</div>
				</c:forEach>
			</c:if>
			</div>
			</div>
		<!-- 영화 검색 -->
		<div id="searchContainer">
		<span id="findMovie">찾는 영화가 없으신가요? 그렇다면 click!</span>
		<form id="searchForm" name="searchForm" method="get" style='display:none'>
			<input type="search" placeholder="검색하세요" id="searchMovie" name="movieName" value="" >
			<button type="button" id="search">검색</button>
		</form>
		<div id="searchResult"></div>
		</div>
	
	</section>
</body>
</html>