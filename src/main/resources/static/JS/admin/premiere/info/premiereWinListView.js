var uid = "";

$(document).ready(function(){
	const url = new URL(window.location.href); // 현재 URL 
	
	// URLSearchParams 객체 
	const urlParams = url.searchParams;
	
	//URLSearchParams.get('parameter') : 파라미터명이 'parameter' 인 것 중 첫번째
	uid = urlParams.get('uid'); // 파라미터명이 uid 인 value 값 받기
	
	// 정보 읽어오기 
	viewData(uid);
	
	// uid 로 삭제하기 
	$("#doDelete").click(function(){
		chkDelete();
	});
	
});

function viewData(uid){
	// 읽어오기
	$.ajax({
		url : "/premiere/premiereWin/view/" + uid,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				if(data.status == "OK" && data.data.length != 0){
					// 글 1개 읽어오기 성공하면, 내부 데이터에 세팅하고 팝업 대화상자 보여줌 
					writeData(data.data[0]);
				} else if(data.data.length = 0){
					alert("실패 : 이미 삭제 되었거나 없는 정보");
					history.back();
				} else {
					alert("실패 : " + data.message);
				}
			}
		}
		
	});
} // end viewData()

function writeData(jsonObj){
	
	result = "";
	
	result += "<div id='dateTime'>작성 날짜 : " + jsonObj.datetime + "</div><br><br>\n"
	result += "<h2>영화 제목 : " + jsonObj.title + "</h2><br>\n"
	result += "내용 : " + jsonObj.content + "<br>\n"
	
	$("#result").html(result); // 정보 업데이트 
	
	$("input[name=prUid]").val(jsonObj.uid);
	
}// end writeData()


