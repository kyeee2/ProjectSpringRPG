$(document).ready(function(){
	
	
	$(function() {
	$('#loginbtn').click(function() {
		var id = $('#id').val();
		var pw = $('#pw').val();
		if(id== ""|| id == null || id == undefined ){
			alert("아이디를 입력해주세요.")
			
			return false;
		}
		if(pw== ""|| pw == null || pw == undefined ){
			alert("비밀번호를 입력해주세요.")
			return false;
		}
		return true;
			
		});
	});
});