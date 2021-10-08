var pwJ=/^((?=.*\d)(?=.*[a-zA-Z])(?=.*[\W]).{8,15})$/;
var phoneJ =  /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/; 
var nameJ = /^[가-힣]{2,6}$/;
var idJ = /^[a-z0-9]{4,12}$/;
var birthJ = /([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/;

$(document).ready(function(){
	
$('#id').keyup(function(){
	if(idJ.test($(this).val())){
		console.log(idJ.test($(this).val()));
		$("#id_check").text('');
	} else {
		$('#id_check').text('영어/숫자를 이용해서 4-12자로 입력해주세요:)');
		$('#id_check').css('color', 'red');
	}
});  
$("#pw").keyup(function() {
		if (pwJ.test($(this).val())) {
				console.log(pwJ.test($(this).val()));
				$("#pw_check").text('');
		}
		 else{
			
			$('#pw_check').text('비밀번호 양식에 맞춰주세요');
			$('#pw_check').css('color', 'red');
		}
	});
    $('#pwchk').keyup(function () {
        var pw1 = $("input[name=pw]").val();
        var pw2 = $("input[name=pwcheck]").val();
  
        if (pw1 != "" || pw2 != "") {
            if (pw1 == pw2) {
                $("#alert-success").css('display', 'inline-block');
                $("#alert-danger").css('display', 'none');
            
            } else { 
                $("#alert-success").css('display', 'none');
                $("#alert-danger").css('display', 'inline-block');
            } 
        }
    });
	$("#pwchk").focusout(function() {
		var pw1 = $("input[name=pw]").val();
        var pw2 = $("input[name=pwcheck]").val();
  
        if ( pw1 != '' && pw2 == '' ) {
            alert("비밀번호를 입력해주세요");
}
	});
    
    $("#name").keyup(function() {
		if (nameJ.test($(this).val())) {
				console.log(nameJ.test($(this).val()));
				$("#name_check").text('');
		} else {
			$('#name_check').text('성함을 입력해주세요');
			$('#name_check').css('color', 'red');
		}
	});
	
	
	// 휴대전화
	$('#phonenum').keyup(function(){
		if(phoneJ.test($(this).val())){
			console.log(phoneJ.test($(this).val()));
			$("#phonenum_check").text('');
		} else {
			$('#phonenum_check').text('휴대폰번호 양식에 맞게 작성해주세요 :)');
			$('#phonenum_check').css('color', 'red');
		}
	});
	$("#birthday").keyup(function() {
		if (birthJ.test($(this).val())) {
			console.log(birthJ.test($(this).val()));
			$("#birthday_check").text('');
	} else {
		$('#birthday_check').text('생년월일을 확인해주세요 예)981017');
		$('#birthday_check').css('color', 'red');
	}
});

$(function() {
	$('#joinbtn').click(function() {
		var valName = $('#name').val();
		var valId = $('#id').val();
		var valNickname = $('#nickname').val();
		var valPhonenum = $('#phonenum').val();
		var valBirthday = $('#birthday').val();
		var idCheck = $('#idChk').attr('value');
		var nickCheck =$('#nickChk').attr('value');
		var phoneCheck=$("#sendPhoneNumber").attr('value');
		console.log(nickCheck);
		if(valName == null || valName == undefined || valName == ""){ alert('성명을 입력해주세요.'); $('#name').focus(); return false;} 
		else if(valId == null || valId == undefined || valId == ""){ alert('아이디를 입력해주세요.'); $('#id').focus();  return false;}
		else if(idCheck == "" || idCheck == "N"){alert('아이디 중복확인을 해주세요'); $('#idChk').focus(); return false;}
		else if(valNickname == null || valNickname == undefined || valNickname == ""){ alert('닉네임을 입력해주세요.'); $('#nickname').focus(); return false;} 
		else if(valPhonenum == null || valPhonenum == undefined || valPhonenum == ""){ alert('전화번호를 입력해주세요.'); $('#phonenum').focus(); return false;} 
		else if(valBirthday == null || valBirthday == undefined || valBirthday == ""){ alert('생년월일을 입력해주세요.'); $('#birthday').focus(); return false;}
		else if(nickCheck == "" || nickCheck == "N"){alert('닉네임 중복확인을 해주세요'); $('#nickChk').focus(); return false;}
		else if(phoneCheck=="" || phoneCheck =="N"){alert('핸드폰 번호 인증을 해주세요'); $('#sendPhoneNumber').focus(); return false;}
		else{
		$('#joinbtn').submit();
		return true;
			}
	});
});


$('#sendPhoneNumber').click(function(){
    let phonenum = $("input[name=phonenum]").val();
    Swal.fire('인증번호 발송 완료!')
	$("#sendPhoneNumber").attr('value', "Y");
	if($("#sendPhoneNumber").attr('value') == "Y"){
	
    $.ajax({
        type: "POST",
        url: "/certified",
        data: {
            "phonenum" : phonenum
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
}
});

	
});


function fn_idChk(){
	if($("#id").val() == "") {
		alert("아이디를 입력해주세요");
		$('#idChk').focus(); 
		return false;
	}
	//if($("#idChk").attr("value") != "Y") {
		//alert("아이디 중복확인을 눌러주세요")
		//$('#idChk').focus();
		//return false;
	//}
	$.ajax({
		url : "/idChk",
		type : "POST",
		dataType : "json",
		data : {"id" : $("input[name=id]").val()},
		success : function(data){
			if(data == 1){
				alert("중복된 아이디입니다.");
				return false;
			}else if(data == 0){
				$("#idChk").attr("value", "Y");
				alert("사용가능한 아이디입니다.");
			}
		}
	});
	
	return true;
}

function fn_nickChk(){
	if($("#nickname").val() == "") {
		alert("닉네임을 입력해주세요");
		$('#nickChk').focus();
		return false;
	}
	
	// 중복 확인 눌렀는지 체크
	//if($("#nickChk").attr("value") != "Y") {
		//alert("닉네임 중복확인을 눌러주세요")
		//$('#nickChk').focus(); 
		//return false;
	//}
	
	$.ajax({
		url : "/nickChk",
		type : "POST",
		dataType : "json",
		data : {"nickname" : $("input[name=nickname]").val()},
		success : function(data){
			if(data == 1){
				alert("중복된 닉네임입니다.");
			return false;
			}else if(data == 0){
				$("#nickChk").attr("value", "Y");
				alert("사용가능한 닉네임입니다.");
				
			}
		}
	});
	return true;
}
