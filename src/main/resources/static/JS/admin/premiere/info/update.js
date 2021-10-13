var uid = "";

$(document).ready(function(){
	
	const url = new URL(window.location.href);
	
	const urlParams = url.searchParams;
	
	uid = urlParams.get('uid');
	
	loadData(uid);
	
	/*
	$("#btn-submit").click(function(event) {
		if(chkSubmit()){
			updateData();
		}else{
			event.preventDefault();
		}
	});
	*/
	
	$(".btn").click(function(event) {
		changeButton(event);
	})
	
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
	$("#img").attr("src", "/file/premiere/" + data.photo);	// 이미지 경로 + 파일명
	$("#img").attr("data-img", data.photo);	// 이미지 파일 이름만 저장
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

function changeButton(event){
	var img = $("#img").attr("data-img");
	var $target = $(event.target);
	
	// 사진수정 버튼을 누르면 현재 사진이 삭제될 수 잇도록 type이 hidden인 input을 생성
	//		사진을 새로 올릴 수 있도록 type이 file인 input 생성
	// 수정취소 버튼을 누르면 file-delete에 있는 요소 다 삭제
	// 		file-change에 있는 요소 다 삭제
	//alert($target.get(0) == $("#btn-update").get(0));	// 이래야 두 요소가 같다고 나온다!
	if($target.get(0) == $("#btn-update").get(0)) {
		$("#btn-update").hide();
		$("#file-delete").append("<input type='hidden' name='photo' value=" + img + ">");	
		
		$("#btn-cancel").show();
		$("#file-change").show();
	} else if($target.get(0) == $("#btn-cancel").get(0)) {
		$("#btn-update").show();
		$("#file-delete").empty();	
		
		$("#btn-cancel").hide();
		$("#file-change").empty();
		$("#file-change").append("<input type='file' id='file-new' name='file' value=''>");
		$("#file-change").hide();
	}
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






















