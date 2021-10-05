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
<form action="FileCheck" method="post" name="fileCheck">
<%
	// MultipartRequest  객체 준비
	// 파일 저장 경로
	String saveDirectory = "";
	saveDirectory = "C:\\upload";
	
	// 서버에 탑재된 서블릿의 물리적인 경로   
	ServletContext context = this.getServletContext();
	saveDirectory = context.getRealPath("upload");
	
	
	System.out.println("업로드 경로: " + saveDirectory);
	
	int maxPostSize = 5 * 1024 * 1024; // 최대용량, 5M byte : 1Kbyte = 1024 byte, 1Mbyte = 1024Kbyte
	String encoding = "utf-8";    // 인코딩
	FileRenamePolicy policy = new DefaultFileRenamePolicy();  // 업로딩 파일 이름 중복에 대한 rename 방식
	
	MultipartRequest multi = null;
	
	try{
		// MultipartRequest 생성 단계에서 이미 파일은 저장됨.
		multi = new MultipartRequest(
				request,    // JSP 내부객체 request
				saveDirectory,  // 업로드 파일 저장경로
				maxPostSize,    // 최대 파일 크기 (post 크기)
				encoding,
				policy
				);
		
		Enumeration names = null;
		
		// 1. Parameter name (들) 추출
		names = multi.getParameterNames();  // 일반 form 요소 name 들 추출
		while(names.hasMoreElements()){
			String name = (String)names.nextElement();  // name
			String value = multi.getParameter(name);  // value
			out.println(name + " : " + value + "<br>");  // 출력
		}
		out.println("<hr>");		
		
		// 2. File (들) 추출
		names = multi.getFileNames();  // type="file" 요소 name 들 추출
		while(names.hasMoreElements()){
			// <input type="file"> 의 name(들) 가져오기
			String name = (String)names.nextElement();
			out.println("input name: " + name + "<br>");
			
			// 위 name에는 form 요소의 name이 담겨 있었다.
			// 그 name 을 가지고 원래 파일(업로드한 파일) 정보를 가져올수 있다.
			String originalFileName = multi.getOriginalFileName(name);
			out.println("원본파일 이름: " + originalFileName + "<br>");
			out.println("<input type='hidden' value='" + originalFileName + "' name='originalFileName'/>");
			
			// 만약 업로드할 폴더에 동일 이름의 파일이 있으면 현재 올리는 파일 이름은 바뀐다 
			// (FileRenamePolicy 중복정책)
			// 그리고 나서 시스템 에 실제 업로딩 된 이름을 알려준다
			String fileSystemName = multi.getFilesystemName(name);
			out.println("파일시스템 이름: " + fileSystemName + "<br>");
			out.println("<input type='hidden' value='" + fileSystemName + "' name='fileSystemName'/>");
			
			// 업로딩된 파일의 타입 : MIME 타입 (ex: image/png ...)
			String fileType = multi.getContentType(name);
			out.println("파일타입: " + fileType + "<br>");
			
			// 문자열 '파일이름'이 name 에 들어온 상태
			// 문자열 파일 이름을 통해 실제 파일 정보를 File객체로 가져온다 
			File file = multi.getFile(name);
			if(file != null){
				long fileSize = file.length();  // File 메소드 사용. 
				out.println("파일크기: " + fileSize + " bytes<br>");
				
				// 이미지 파일 다루기
				// java.awt.image.BufferedImage, javax.imageio.ImageIO 임포트   
				BufferedImage bi = ImageIO.read(file);
				if(bi != null){  // 이미지 파일인지 판정
					int width = bi.getWidth();
					int height = bi.getHeight();
					out.println("이미지 파일 WxH: " + width + " x " + height + "<br>");
				} else {
					out.println("이미지 파일이 아닙니다 <br>");
				}
			}
			
			
			out.println("<hr>");
		}
		
	} catch(Exception e){
		e.printStackTrace();
		out.println("파일처리 예외 발생<br>");
	}
	
%>
<input type="submit" value="업로드 파일 확인"/><br>
</form>



</body>
</html>