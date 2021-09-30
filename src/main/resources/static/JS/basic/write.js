var boardType = "";	// boardType 세팅

$(document).ready(function() {
	
	$("#btn-submit").click(function(event) {	// event로 두번 submit 되지 않게 막기
		if(chkSubmit()){
			writeData();
		} else {
			event.preventDefault();	// 유효성검사 실패했을 때 다시 request하는걸 막아주기
		}
	});
	
	// 라디오 버튼을 클릭할 때
	$("input[name=boardType]").click(function(event) {
		var radio = event.target;
		if($(radio).val() == "movieboard") {
			// 클릭한 라디오 버튼의 값이 movieboard라면
			// 주제 작성 칸 생성
			$("#subject").html("주제: <input type='text' name='subject' value=''/><br>");
		} else {
			// movieboard가 아니라면
			// 주제 작성 칸 삭제
			$("#subject").html("");
		}
	});
	
});

function chkSubmit(){
	frm = document.forms['frm'];
	
	var nickName = frm['nickName'].value.trim();
	var title = frm['title'].value.trim();
	var content = frm['content'].value.trim();

	if(nickName == ""){
		alert("작성자 란은 반드시 입력해야 합니다");
		frm['nickName'].focus();
		return false;
	}
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


function writeData() {
	
	var formData = $("#frm").serialize();	// form 안의 name 값들을 모두 가져옴
	boardType = $('input[name=boardType]:checked').val();
	
	$.ajax({
		url : "/board",
		type : "POST",
		cache : false,
		data : formData,
		success : function(data, status) {
			if(status == "success") {
				if(data.status == "OK"){
					alert("INSERT 성공" + data.status + " : " + data.message);
					location.href = "/" + boardType;
				} else {
					alert("INSERT 실패" + data.status + " : " + data.message);
				}
			}
		}
	});
}	// end writeData()
