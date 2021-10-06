var uid = "";

$(document).ready(function(){
	
	const url = new URL(window.location.href);
	
	const urlParams = url.searchParams;
	
	uid = urlParams.get('uid');
	
	loadData(uid);
	
	$("#btn-submit").click(function(event) {
		if(chkSubmit()){
			updateData();
		}else{
			event.preventDefault();
		}
	});
});

function loadData(uid){
	
	$.ajax({
		url : "/premiere/view/" + uid,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				if(data.status == "OK"){
					writeValue(data.data[0]);
				} else {
					alert("실패");
				}
			}
		}
		
	});
}

// 원래 내용 
function writeValue(data){
	
	$("input[name=uid]").val(data.uid);
	$("input[name=title]").val(data.title);
	$("input[name=photo]").val(data.photo);
	$("textarea[name=content]").val(data.content);
}

// 유효성 
function chkSubmit(){
	frm = document.forms['frm'];
	
	var title = frm.title.value.trim();
	
	if(title == ""){
		alert("제목은 반드시 입력해야 합니다");
		frm.title.focus();
		return false;
	}
	
	return "/premiere/list";
}


// 수
function updateData(){
	
	var formData = $("#frm").serialize();
	
	$.ajax({
		url : "/premiere",
		type : "PUT",
		cache : false,
		data : formData,
		success : function(data, status){
			if(status == "success"){
				if(data.status == "OK"){
					alert("UPDATE 성공" + data.status + " : " + data.message);
					location.href = "/premiere/view?uid=" + uid;
				} else {
					alert("UPDATE 실패" + data.status + " : " + data.message);
				}
			}
		}
	})
}






















