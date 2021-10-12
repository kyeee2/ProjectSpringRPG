function findID(){
	if($("#name1").val() == "") {
		alert("이름을 입력해주세요");
		$('#name1').focus(); 
		return false;
	}
	if($("#phonenum1").val() == "") {
		alert("핸드폰 번호를 입력해주세요");
		$('#phonenum1').focus(); 
		return false;
	}
/*
	$.ajax({
      url:'/findIDOk',
      type:'POST',
    data: {"name" : $("#name1").val(), "phonenum" : $("#phonenum1").val(), "id" : $("#id").val()},
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType : "json",
 success:function(data){
        	if(data == 1){
				alert("회원님의 아이디는" + "${id}" +"입니다");
				$("#IdList").css('display', 'inline-block');
				$("#IdList").append("<h1>"+"회원님의 정보로 등록된 이메일은 : "+"${id}"+" 입니다.</h1>")
			}else if(data == 0){
				alert("이름 또는 전화번호가 틀립니다.")
				$("#IdList").css('display', 'none');
				return false;
			}
		}
	
    });
*/
	return true;
}
function findPW(){
	if($("#name2").val() == "") {
		alert("이름을 입력해주세요");
		$('#name2').focus(); 
		return false;
	}
	if($("#phonenum2").val() == "") {
		alert("핸드폰 번호를 입력해주세요");
		$('#phonenum2').focus(); 
		return false;
	}
	if($("#id").val() == "") {
		alert("아이디를 입력해주세요");
		$('#id').focus(); 
		return false;
	}
	$.ajax({
      url:'/basic/findPWOk',
      type:'POST',
    data: {"pw" : $("pw").val(), "name" : $("#name1").val(), "phonenum" : $("#phonenum1").val(), "id" : $("#id").val()},
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType : "json",
 success:function(data){
        	if(data == 1){
				alert("회원님의 아이디는" + '${list[0].id}' +"입니다");
				$("#IdList").css('display', 'inline-block');
				$("#IdList").append("<h1>"+"회원님의 정보로 등록된 이메일은 : "+'${list[0].id}'+" 입니다.</h1>")
			}else if(data == 0){
				alert("이름 또는 전화번호가 틀립니다.")
				$("#IdList").css('display', 'none');
				return false;
			}
		}
	
    });
	return true;
}
