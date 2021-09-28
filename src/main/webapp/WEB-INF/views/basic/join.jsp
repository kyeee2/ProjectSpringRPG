
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%-- validation 실패시 --%>
<c:if test="${not empty ERR }">
	<script>
		alert("등록 실패 " + "${ERR}");
	</script>	
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
 <br><br>
        <h2>회원가입</h2>
        <br><br><br>
        
        
      
        <form action="joinOk" method="post" name="userInfo">
            <table>
                <tr>
                    <td id="title">아이디</td>
                    <td>
                        <input type="text" name="id"  maxlength="45">
                        <input type="button" value="중복확인" >    
                    </td>
                </tr>
                        
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="pw" maxlength="45"  >
                    </td>
                </tr>
                
                <tr>
                    <td id="title">비밀번호 확인</td>
                    <td>
                        <input type="password" name="pwcheck" maxlength="50">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">이름</td>
                    <td>
                        <input type="text" name="name" maxlength="50" >
                    </td>
                </tr>
                    <tr>
                    <td id="title">닉네임</td>
                    <td>
                        <input type="text" name="nickname" maxlength="50">
                        <input type="button" value="중복확인">
                    </td>
                </tr> 
              
                    
                <tr>
                    <td id="title">생년월일</td>
                    <td>
                        <input type="text" name="birthday"  maxlength="6" placeholder="yymmdd" size="8" >
                        
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">프로필사진</td>
                   <td>
	
	<a href="FileUpload">업로드</a>
           
                </tr>
                    
                <tr>
                    <td id="title">핸드폰 번호</td>
                    <td>
                        <input type="text" name="phonenum" placeholder="-제외"/>
                     	<!-- <form action= "danal.jsp"> -->
                        <button type="submit" >인증</button>
                      
                    </td>
                </tr>
                <tr>
                    <td id="title">인증번호입력</td>
                    <td>
                        <input type="text" name="certification" />
                        <input type="button" value="인증">
                    </td>
                </tr>
                
            </table>
            <br>
            <input type="submit" value="회원가입"/> 
        </form>
 <input type="button" value="취소">
</body>
</html>