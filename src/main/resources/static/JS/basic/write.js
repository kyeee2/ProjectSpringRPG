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
			var subject = "<th><label for='subject'>주제</label></th>\n";
			subject += "<td><input type='text' name='subject' value=''/></td>\n";
			$("#subject").html(subject);
		} else {
			// movieboard가 아니라면
			// 주제 작성 칸 삭제
			$("#subject").html("");
		}
	});
	
});

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
		CKEDITOR.instances.ckeditor.focus();
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

// 글 작성
function writeData() {
	
	var formData = $("#frm").serialize();	// form 안의 name 값들을 모두 가져옴
	boardType = $('input[name=boardType]:checked').val();	// 라디오버튼으로 체크한 boardType을 가져오기
	
	$.ajax({
		url : "/board/write",
		type : "POST",
		cache : false,
		data : formData,
		success : function(data, status) {
			if(status == "success") {
				if(data.status == "OK"){
					alert("INSERT 성공" + data.status + " : " + data.message);
					location.href = "/" + boardType;
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
					alert("INSERT 실패" + data.status + " : " + data.message);
				}
			}
		}
	});
}	// end writeData()
