var boardType = "";	// boardType 세팅
var uid = "";

$(document).ready(function() {
	
	const url = new URL(window.location.href);	// 현재 URL 받기
	
	// URLSearchParams 객체
	const urlParams = url.searchParams;
	
	// URLSearchParams.get('parameter') : 파라미터명이 'parameter'인 것 중 첫번째 value 값 리턴
	boardType = urlParams.get('boardType'); // 파라미터명이 boardType인 value 값 받기 
	uid = urlParams.get('uid'); // 파라미터명이 uid인 value 값 받기 	
	
	// URLSearchParams.getAll('parameter')	: 파라미터명이 'parameter'인 모든 value값을 배열로 리턴
	//document.write(urlParams.getAll('q') + '<br>'); // ['coffee']
	
	// 정보 읽어오기 (조회수 증가)
	viewData(boardType, uid);
	
	// uid로 삭제하기
	$("#doDelete").click(function() {
		chkDelete();
	});
});

function viewData(boardType, uid) {

	// 읽어오기
	$.ajax({
		url : "/board/view/" + boardType + "/" + uid,	// url : /ajax/{boardType}/{uid}
		type : "GET",
		cache : false,
		success : function(data, status) {
			if(status == "success") {
				if(data.status == "OK" && data.data.length != 0) {
					
					// 글 1개 읽어오기 성공하면, 내부데이터에 세팅하고 팝업 대화상자 보여주기
					writeData(data.data[0]);	//	어차피 하나가 담겨있음
					
				} else if(data.data.length == 0) {
					alert("VIEW 실패 : 이미 삭제됐거나 없는 정보입니다.");
					history.back();
				} else {
					alert("VIEW 실패 : "+ data.message);
				}
			}
		}
	});
	
}	// end addViewEvent()

function writeData(jsonObj) {
	
	result = "";	// 결과값 초기화

	result += "작성자 : " + jsonObj.nickname + "<br>\n";
	result += "등록일 : " + jsonObj.datetime + "<br>\n";
	result += "조회수 : " + jsonObj.viewcnt + "<br>\n";
	if(jsonObj.boardtype != "noticeboard") {
		// 좋아요수와 댓글수는 공지사항에서는 보이지 않는다
		result += "좋아요수 : " + jsonObj.goodcnt + "<br>\n";
		result += "댓글수 : " + jsonObj.commentcnt + "<br>\n";
	}
	if(jsonObj.boardtype == "movieboard"){	
		// 주제는 영화 리뷰에서만 보임
		result += "주제 : " + jsonObj.subject + "<br>\n";
	}
	result += "제목 : " + jsonObj.title + "<br>\n";
	result += "내용 : " + jsonObj.content + "<br>\n";
	
	$("#result").html(result);	// 정보 업데이트
	
}	// end writeResult()

function chkDelete() {
	if(!confirm("이 글을 삭제하시겠습니까?")) return false;
	
	var data = "boardType=" + boardType + "&uid=" + uid;
	
	// DELETE 방식
	$.ajax({
		url : "/board",
		type : "DELETE",
		data : data,
		cache : false,
		success : function(data, status) {
			if(status == "success")	{	//	200
				if(data.status == "OK") {
					alert("DELETE 성공 : " + data.count + "개");
					location.href = "/" + boardType;
				} else {
					alert("DELETE 실패 " + data.message);
				}
			}
		}
	});
}
