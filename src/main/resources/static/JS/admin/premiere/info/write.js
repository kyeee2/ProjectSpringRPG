$(document).ready(function(){
	
	/*
	$("#btn-submit").click(function(event){
		if(chkSubmit()){
			writeData();
		} else {
			event.preventDefault();
		}
	});
	*/
});

function chkSubmit(){
	frm = document.forms['frm'];
	
	var title = frm.title.value.trim();
	
	if(title == ""){
		alert("제목은 반드시 작성해야 합니다");
		frm.title.focus();
		return false;
	}
	
	return true;	
} // end chkSubmit()

function writeData(){
	
	alert($("#photo").val());
	var f = document.getElementById('frm');
	var formData = new FormData(f);
	alert(formData);
	alert(formData.title);
	
	$.ajax({
		url : "/premiere",
		type : "POST",
		enctype: 'multipart/form-data',	// 필수
		data: formData, // 필수 
		processData: false, // 필수 
		contentType: false, // 필수

		cache : false,
		success : function(data, status){
			if(status == "success"){
				if(data.status == "OK"){
					alert("INSERT 성공" + data.status + " : " + data.message);
					location.href = "/premiere/list";					
				}else {
					alert("INSERT 실패" + data.status + " : " + data.message);
				}
			}
		}
		
		
	})
}
