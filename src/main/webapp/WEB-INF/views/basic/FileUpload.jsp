<%-- cos 라이브러리 --%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.FileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>    

<%-- parameter 값들,  file name 값들 추출 --%>
<%@ page import="java.util.Enumeration" %>   

<%--File 객체 추출 --%>
<%@ page import="java.io.File" %>

<%-- 이미지 파일 다루기 --%>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>    

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<hr>
<form th:action method="post" enctype="multipart/form-data">
 <ul> 

 <li>첨부파일<input type="file" name="attachedFile"></li> 
 <!-- <li>다수의 파일들<input type="file" multiple="multiple" name="multiFiles"></li> --> 
 </ul> 
 <input type="submit"/>
  </form>


</body>
</html>