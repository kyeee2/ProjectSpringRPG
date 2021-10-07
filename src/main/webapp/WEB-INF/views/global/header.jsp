<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- header -->
  <header class="header">
    <div class="header-container">
      <!-- 대한항공 로고 -->
      <a href="#"><div class="header-logo"></div></a>

      <ul class="header-nav">
        <li><a href="../freeboard">자유게시판</a></li>
        <li><a href="../movieboard">영화 리뷰</a></li>
        <li><a href="../movieInfo">영화 정보</a></li>
        <li><a href="./map">영화관 위치</a></li>
        <li><a href="../noticeboard">공지사항</a></li>
      	<li><a href="../premiere/list">시사회</a>
      </ul>

      <ul class="header-icons">
      	<sec:authorize access="isAuthenticated()">
        	<li><a href="/logout" class="header-login">로그아웃</a></li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
        	<li><a href="/login" class="header-login">로그인</a></li>
        </sec:authorize>
        <li><a href="#"><i class="fas fa-bell"></i></a></li>
        <li><a href="#"><i class="fas fa-search"></i></a></li>
        <li><button class="btn-hamburger"><span class="fas fa-bars"></span></button></li>
      </ul>
    </div>
  </header>