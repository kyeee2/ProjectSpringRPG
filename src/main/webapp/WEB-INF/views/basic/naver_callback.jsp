<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="org.json.simple.*"%>
<%@ page import="org.json.simple.parser.*"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
  name : ${ name }<br>
  nickname : ${ nickname }<br>
  profile : <img src="${ profile }" style="width:500px; hieght:auto;" />
  birthday : ${ birthday }<br>
  mobile : ${ phonenum }<br>

</body>
</html>