<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.*"%>
<%@ page import="com.spring.domain.CustomerDTO" %>
<%@ page import="com.spring.service.LoginService" %>


<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
  <form action="/loginOk" method="post">
  	<input name="id" value="${ id }">
  	<input name="pw" value="${ pw }">
  </form>
</body>
</html>
<script>
	$(document).ready(function() {
		
	});
</script>