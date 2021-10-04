
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
        

           
        <form action="joinOk" method="post" name="userInfo" enctype="Multipart/form-data">
            <table>
                <tr>
                    <td id="title">아이디</td>
                    <td>
                        <input type="text" name="id"  maxlength="45">
                       <button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>   
                    </td>
                </tr>
                        
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="pw" class="pw" maxlength="45"  >
                        <span>8~15자리의 영문, 숫자, 특수문자의 입력이 가능합니다.</span>
                    </td>
                </tr>
                
                <tr>
                    <td id="title">비밀번호 확인</td>
                    <td>
                        <input type="password" name="pwcheck" class="pw" maxlength="45">
                        <span id="alert-success" style="display: none;">비밀번호가 일치합니다.</span>
    <span id="alert-danger" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span>
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">이름</td>
                    <td>
                        <input type="text" name="name" maxlength="45" >
                    </td>
                </tr>
                    <tr>
                    <td id="title">닉네임</td>
                    <td>
                        <input type="text" name="nickname" maxlength="45">
                       <button class="nickChk" type="button" id="nickChk" onclick="fn_nickChk();" value="N">중복확인</button>
                    </td>
                </tr> 
              
                    
                <tr>
                    <td id="title">생년월일</td>
                    <td>
                        <input type="text" name="birthday"  maxlength="6" placeholder="yymmdd" size="8" >
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
                        <input type="text" name="phonenum" placeholder="-제외" maxlength="12"/>
                     	
                        <button type="button" id="sendPhoneNumber" onclick= >인증</button>
                      
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
            <input type="submit"  value="회원가입"/> <span id="progress"></span>
        </form>
 <input type="button" value="취소">
<script>
    $('.pw').focusout(function () {
        var pw1 = $("input=[name=pw]").val();
        var pw2 = $("input=[name=pwcheck]").val();
  
        if ( pw1 != '' && pw2 == '' ) {
            null;
        } else if (pw1 != "" || pw2 != "") {
            if (pw1 == pw2) {
                $("#alert-success").css('display', 'inline-block');
                $("#alert-danger").css('display', 'none');
            } else {
                alert("비밀번호가 일치하지 않습니다. 비밀번호를 재확인해주세요.");
                $("#alert-success").css('display', 'none');
                $("#alert-danger").css('display', 'inline-block');
            }
        }
    });
</script>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
$("input[name=profile]").on('change', function() {
var file = $("input[name=profile]")[0].files[0];
var filename = file.name;
var reader = new FileReader();
reader.onload = function(e) {
// 변환이 끝나면 reader.result로 옵니다.
var base64data = reader.result;
console.log(base64data);
// 여기서 구조가 중요합니다.
// 구조는 「data: 파일 타입; base64, 데이터」입니다.
var data = base64data.split(',')[1];
//data가 이제 데이터 입니다.
//사실 ajax로 넘길때는 큰 사이즈 설정해서 데이터를 넘기면 빠르게 되는데
//예제이다보니 프로그래스바 구조를 나타내기 위해 문자 1개 단위로 보내겠습니다.
var sendsize = 1024;
var filelength = data.length;
var pos = 0;
var upload = function() {
$.ajax({
type : 'POST',
dataType : 'json',
data : {
filename : filename,
filelength : filelength,
filepos : pos,
data : data.substring(pos, pos + sendsize)
},
url : '/basic/upload',
success : function(data) {
// 전체가 전송될 때까지
if (pos < filelength) {
// 재귀
setTimeout(upload, 1);
}
pos = pos + sendsize;
if (pos > filelength) {
pos = filelength;
}
$('#progress').text(pos + ' / ' + filelength);
},
error : function(jqXHR, textStatus, errorThrown) {
},
complete : function(jqXHR, textStatus) {
}
});
};
setTimeout(upload, 1);
}
// base64로 넘깁니다.
reader.readAsDataURL(file);
});

function fn_idChk(){
	$.ajax({
		url : "/basic/idChk",
		type : "post",
		dataType : "json",
		data : {"id" : $("input[name=id]").val()},
		success : function(data){
			if(data == 1){
				alert("중복된 아이디입니다.");
			}else if(data == 0){
				$("#idChk").attr("value", "Y");
				alert("사용가능한 아이디입니다.");
			}
		}
	})
}
function fn_nickChk(){
	$.ajax({
		url : "/basic/nickChk",
		type : "post",
		dataType : "json",
		data : {"nickname" : $("input[name=nickname]").val()},
		success : function(data){
			if(data == 1){
				alert("중복된 닉네임입니다.");
			}else if(data == 0){
				$("#nickChk").attr("value", "Y");
				alert("사용가능한 닉네임입니다.");
			}
		}
	})
}

$('#sendPhoneNumber').click(function(){
    let phonenumber = $("input[name=phonenum]").val();
    Swal.fire('인증번호 발송 완료!')


    $.ajax({
        type: "POST",
        url: "/basic/certified",
        data: {
            "phonenumber" : phonenumber
        },
        success: function(res){
            $('#checkBtn').click(function(){
                if($.trim(res) ==$('#CertifiedNumber').val()){
                    Swal.fire(
                        '인증성공!',
                        '휴대폰 인증이 정상적으로 완료되었습니다.',
                        'success'
                    )

                   
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: '인증오류',
                        text: '인증번호가 올바르지 않습니다!',
                        footer: '<a href="redirect:/basic/join">다음에 인증하기</a>'
                    })
                }
            })


        }
    })
});
</script>



</body>
</html>