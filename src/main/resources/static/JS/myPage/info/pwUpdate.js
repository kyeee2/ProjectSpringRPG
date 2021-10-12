var pwJ=/^((?=.*\d)(?=.*[a-zA-Z])(?=.*[\W]).{8,15})$/;

$(document).ready(function(){
	
$("#pw").keyup(function() {
		if (pwJ.test($(this).val())) {
				console.log(pwJ.test($(this).val()));
				$("#newpw_check").text('');
		}
		 else{
			
			$('#newpw_check').text('비밀번호 양식에 맞춰주세요');
			$('#newpw_check').css('color', 'red');
		}
	});
	

$('#pwchk').keyup(function () {
        var pw1 = $("input[name=pw]").val();
        var pw2 = $("input[name=newPwCheck]").val();
  
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

});