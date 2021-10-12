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
	
	// 응모하기 버튼 누르면
	$("#btn_apply").click(function() {
		doApply();
	});
});

function viewData(uid){
	// 읽어오기
	$.ajax({
		url : "/premiere/view/" + uid,
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
	
	result += "제목 : " + jsonObj.title + "<br>\n"
	result += "첨부파일 : " + jsonObj.photo + "<br>\n"
	result += "내용 : " + jsonObj.content + "<br>\n"
	
	$("#result").html(result); // 정보 업데이트 
	
	$("input[name=prUid]").val(jsonObj.pruid);
	
}// end writeData()

function chkDelete(){
	if(!confirm("이 글 삭제?")) return false;
	
	var data = "uid=" + uid;
	
	// DELETE 방식
	$.ajax({
		url : "/premiere",
		type : "DELETE",
		data : data,
		cache : false,
		success : function(data, status){
			if(status == "success"){
				if(data.status == "OK"){
					alert("DELETE 성공 : " + data.count + "개");
					location.href = "/premiere/list";
				} else {
					alert("DELETE 실패" + data.message);
				}
			}
		}
		
	});
}

function doApply() {
	
	var data = $("#apply").serialize();
	alert(data);
	
	$.ajax({
		url : "/premiere/apply",
		type : "POST",
		data : data,
		cache : false,
		success : function(data, status) {
			$("input[value=prUid]").val();
			if(status == "success") {
				if(data.status == "OK") {
					alert("응모완료");
					// 응모 완료하면 폼 초기화
					$("input[name=cusId]").val("");
					$("input[name=email]").val("");
				} else {
					alert(data.message);
				}
			}
		}
	});
}




















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
		url : "/premiere/view/" + uid,
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
	
	result += "<input type='hidden' name='uid' value=" + jsonObj.uid + ">";
	result += "제목 : " + jsonObj.title + "<br>\n"
	if(jsonObj.photo == "") {
		// 저장된 사진이 없다면 디폴트 이미지 보여주기
		result += "첨부파일 : <img src='/file/premiere/no_img.png' alt='빈이미지'/><br>\n"
	} else {
		result += "첨부파일 : <img src='/file/premiere/" + jsonObj.photo + "'/><br>\n"
	}
	result += "내용 : " + jsonObj.content + "<br>\n"
	
	$("#result").html(result); // 정보 업데이트 
	
}// end writeData()

function chkDelete(){
	if(!confirm("이 글 삭제?")) return false;
	
	$("#frmDelete").submit();
}





















