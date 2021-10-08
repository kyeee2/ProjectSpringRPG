
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
 <script type="text/javascript" src="//code.jquery.com/jquery-3.4.1.min.js"></script>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- 만든 JS -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/basic/join.js"></script>
<!-- 헤더 적용 -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/global/header.js"></script>

</head>
<body>
 <br><br>
        <h2>회원가입</h2>
        <br><br><br>
        

           
        <form action="joinOk" method="post" name="userInfo" enctype="Multipart/form-data">
            <table>
                <tr>
                    <td class="title">아이디</td>
                    <td>
                        <input type="text" name="id" id="id" maxlength="45">
                       <button type="button" id="idChk" onclick="return fn_idChk();" value="N">중복확인</button> 
                 		<div class="check_font" id="id_check"></div>
                    
                    </td>
        
                </tr>
                        
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="pw" id="pw" maxlength="45"  >
                        <span>8~15자리의 영문, 숫자, 특수문자의 입력만 가능합니다.</span>
                        <div class="check_font" id="pw_check"></div>
                    </td>
                </tr>
                
                <tr>
                    <td id="title">비밀번호 확인</td>
                    <td>
                        <input type="password" name="pwcheck" id="pwchk" maxlength="45">
                        <span id="alert-success" style="display: none; color:green;">비밀번호가 일치합니다.</span>
    <span id="alert-danger" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span>
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">이름</td>
                    <td>
                        <input type="text" id="name" name="name" maxlength="45" >
                        <div class="check_font" id="name_check"></div>
                    </td>
                </tr>
                    <tr>
                    <td id="title">닉네임</td>
                    <td>
                        <input type="text" id="nickname" name="nickname" maxlength="45">
                       <button class="nickChk" type="button" id="nickChk" onclick="return fn_nickChk();" value="N">중복확인</button>
                       <div class="check_font" id="nickname_check"></div>
                    </td>
                </tr> 
              
                    
                <tr>
                    <td id="title">생년월일</td>
                    <td>
                        <input type="text" id="birthday" name="birthday"  maxlength="6" placeholder="yymmdd" size="8" >
                        <div class="check_font" id="birthday_check"></div>
                    </td>
                </tr>
                  <tr>   
   				<td id="title">프로필 </td>
   						<td>
   				<input type="file" name="file">
				
							</td>
 				</tr>

                    
                <tr>
                    <td id="title">핸드폰 번호</td>
                    <td>
                        <input type="text" id="phonenum" name="phonenum" placeholder="-제외" maxlength="13"/>
                     	
                        <button type="button" id="sendPhoneNumber" value="N" >인증</button>
                       <div class="check_font" id="phonenum_check"></div>
                    </td>
                </tr>
                <tr>
                    <td id="title">인증번호입력</td>
                    <td>
                        <input type="text" id="CertifiedNumber" />
                        <input type="button" id="checkBtn" value="인증 확인">
                    </td>
                </tr>
                
            
            </table>
            <input type="submit" id="joinbtn" value="회원가입"/>
        </form>
 <input type="button" value="취소">

</body>
</html>