var boardType = "";	// boardType 세팅
var uid = "";

$(document).ready(function() {
	
	const url = new URL(window.location.href);	// 현재 URL 받기
	
	// URLSearchParams 객체
	const urlParams = url.searchParams;
	
	// URLSearchParams.get('parameter') : 파라미터명이 'parameter'인 것 중 첫번째 value 값 리턴
	boardType = urlParams.get('boardType'); // 파라미터명이 boardType인 value 값 받기 
	uid = urlParams.get('uid'); // 파라미터명이 uid인 value 값 받기 	
	
	// 수정할 정보 얻어오기
	loadData(boardType, uid);
	
	// 수정완료 클릭하면
	$("#frm").submit(function(event) {
		if(chkSubmit()){
			updateData();
		} else {
			event.preventDefault();	// 유효성검사 실패했을 때 다시 request하는걸 막아주기
		}
	});
	
});

function loadData(boardType, uid) {
	
	$.ajax({
		url : "/read/" + boardType + "/" + uid,
		type : "GET",
		cache : false,
		success : function(data, status) {
			if(status == "success") {
				if(data.status == "OK"){
					writeValue(data.data[0]);	// 어차피 하나의 데이터값만 있다.
				} else {
					alert("정보를 불러오는데 실패하였습니다.");
				}
			}
		}
	});
}

// 원래 작성된 내용 넣어주기
function writeValue(data) {
	
	$("input[name=boardType]").val(boardType);
	$("input[name=uid]").val(uid);
	$("span[name=nickName]").text(data.nickname);
	$("input[name=title]").val(data.title);
	$("textarea[name=content]").val(data.content);
}

// 유효성 검사
function chkSubmit(){
	frm = document.forms['frm'];
	
	var title = frm['title'].value.trim();
	var content = frm['content'].value.trim();

	if(title == ""){
		alert("제목은 반드시 작성해야 합니다");
		frm['title'].focus();
		return false;
	}
	if(content == ""){
		alert("내용은 반드시 작성해야 합니다");
		frm['content'].focus();
		return false;
	}
	
	return true;
} // end chkSubmit()

// 글 수정
function updateData() {
	
	var formData = $("#frm").serialize();	// form 안의 name 값들을 모두 가져옴
	
	$.ajax({
		url : "/",
		type : "PUT",
		cache : false,
		data : formData,
		success : function(data, status) {
			if(status == "success") {
				if(data.status == "OK"){
					alert("UPDATE 성공" + data.status + " : " + data.message);
					location.href = "/view?boardType=" + boardType + "&uid=" + uid;
				} else {
					alert("UPDATE 실패" + data.status + " : " + data.message);
				}
			}
		}
	});
	
	return false;
}
