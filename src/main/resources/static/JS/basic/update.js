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
	$("#btn-submit").click(function(event) {	// event로 두번 submit 되지 않게 막기
		if(chkSubmit()){
			updateData();
		} else {
			event.preventDefault();	// 유효성검사 실패했을 때 다시 request하는걸 막아주기
		}
	});
	
});

function loadData(boardType, uid) {
	
	$.ajax({
		url : "/board/read/" + boardType + "/" + uid,
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
	if(boardType == "movieboard") {
		// 영화 리뷰라면 주제에 원래 내용 넣어주기
		$("input[name=subject]").val(data.subject);	
	}
	$("input[name=title]").val(data.title);
	$("textarea[name=content]").val(data.content);
}

// 유효성 검사
function chkSubmit(){
	frm = document.forms['frm'];
	
	var title = frm['title'].value.trim();
	var content = CKEDITOR.instances.ckeditor.getData();	// ckeditor에 적은 데이터 뽑아내기

	if(title == ""){
		$("#title-message").text("  제목은 필수입니다.");
		$("#content-message").text("");
		frm['title'].focus();
		return false;
	}
	if(content == ""){
		$("#title-message").text("");
		$("#content-message").text("  내용은 필수입니다.");
		frm['content'].focus();
		return false;
	} else {
		$("textarea").text(content);	// 비어잇지 않으면 textarea에 갱신하기
	}
	if(title != "" && content != ""){
		$("#title-message").text("");
		$("#content-message").text("");
	}
	
	return true;
} // end chkSubmit()

// 글 수정
function updateData() {
	
	var formData = $("#frm").serialize();	// form 안의 name 값들을 모두 가져옴
	
	$.ajax({
		url : "/board/update",
		type : "POST",
		cache : false,
		data : formData,
		success : function(data, status) {
			if(status == "success") {
				if(data.status == "OK"){
					alert("UPDATE 성공" + data.status + " : " + data.message);
					location.href = "/view?boardType=" + boardType + "&uid=" + uid;
				} else if(data.status == "HOLD"){
					if(data.message.substring(0, 2) == "제목") {
						$("input[name=title]").focus();
						$("#title-message").text("  " + data.message);
						$("#content-message").text("");
					} else {
						$("#title-message").text("");
						$("textarea[name=content]").focus();
						$("#content-message").text("  " + data.message);
					}
				} else {
					alert("UPDATE 실패" + data.status + " : " + data.message);
				}
			}
		}
	});
	
	return false;
}
