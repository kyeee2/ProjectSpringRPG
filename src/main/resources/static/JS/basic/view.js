var boardType = "";	// boardType 세팅
var uid = "";	//게시글 고유번호

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
	
	//댓글 읽어오기
	commentList(boardType, uid);
	
	//댓글등록 버튼 클릭 시
	$('[name=commentInsertBtn]').click(function(event) {	//댓글등록 버튼 클릭 시
		if(chkCosubmit()==true) {
			
		var insertData = $('[name=commentFrm]').serialize(); //commentFrm 내용 가져오기
		commentinsert(insertData);//TODO
		} else {
			event.preventDefault();
		}
		
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


	//댓글 등록
	function commentinsert(insertData) {
		$.ajax({
			url : "/comment/writeOk",
			type : "POST",
			data : insertData,
			cache : false,
			success : function(data){
				if(data.count == 1) {
					alert("댓글 작성 완료");
					//댓글 읽어오기
					commentList(boardType, uid);
					$('[name=content]').val('');
				}
			}
		})
	}//end commentinsert
		
	//댓글 목록 
	function commentList(boardType, uid){
		$.ajax({
			url : "comment/view/" + boardType + "/" + uid, // url : /comment/view/{boardType}/{uid}
			type : 'GET',
			datatype : 'json',
			//data : {'uid':uid},
			cahce : false,
			success : function(data, status) {
				if(status=="success") {
					writeComment(data.data);
				}
			}
			
		});
	}//end commentList()
	
	function writeComment(jsonObj) {
		
		var comment ="";
		
		for(i=0; i<jsonObj.length; i++) {
		
		comment += "<form name='frm" + i +"'>\n";
		comment += "닉네임 : " + jsonObj[i].nickName + "<br>\n"; 
		comment += "댓글내용 : <textarea>" + jsonObj[i].content + "</textarea><br>\n"; 
		comment += "작성시간 : " + jsonObj[i].datetime + "<br>\n"; 
		comment += "<div class='exam'>\n";
		comment += "<button type='button' class='CoUpdateBtn' name='CoUpdateBtn' onclick='clickUpdate(event)'>댓글수정</button>";
		comment += "<button type='button' name='CoDeleteBtn' onclick='clickDelete(event)'>댓글삭제</button>";
		comment += "</div><br><br><br>\n";
		comment += "</form>\n";
		}
		
		$("#comment").html(comment);	// 정보 업데이트
		
	}//end wrtieComment
	
	//->clickUpdate(event) 클릭 시 content가 textarea로 바뀌고, 댓글삭제버튼 없어지고 수정완료버튼 생기고 댓글수정버튼 생김
	//수정완료버튼 누를 시, 입력한 content로 댓글이 바뀌고 writeComment(jsonObj)형식으로 다시 바뀜
	//->어떻게 적용해야할까?
	
	function clickUpdate(event){
		var $form = $(event.target).parent().parent();	// form 가져오기
		
		alert($form.html());
	}
	
	function chkCosubmit() {
		frm = document.forms['commentFrm'];
		
		var content =frm['content'].value.trim();
		
		if(content=="") {
			alert("댓글내용은 반드시 한글자 이상 작성해야 합니다");
			frm['content'].focus();
			return false;
			
		}
		return true;
	}
	
	//댓글 특정 조건(작성한 customer의 nickName과 같을 경우)
	function matchNickName() {
		
	}//end matchNickName
	
	
	//댓글 수정 -댓글 내용 출력을 input폼으로 변경
	function updateC() {
		var updateC ='';	
		// updateC += "닉네임 : " +jsonObj[0].nickName + "<br>\n";
		updateC += '<div>';
		updateC +=	'<input type="text" id = "content" name="content" placeholder="내용을 입력하세요"/>';
		updateC +=	'<span> <button type="button" name="commentUpdateBtn">댓글수정</button> </span>';
		updateC +=	'</div>'
		// updateC += "작성시간 : " +jsonObj[0].dateTime + "<br>\n";
		
		$("#comment").html(updateC); 
	}//end updateComment
	
	
	//댓글 수정 -댓글 수정 버튼을 누를 경우
	function CommentupdateBtn() {
		var updateComment = $("#CommentFrm").serialize();
		$.ajax({
			url : "comment/updateOK/{boardType}/{buid}",
			type : "PUT",
			datatype : "json",
			data : updateComment,
			cache : false,
			success : function(data,status) {
			if(data.status == "OK"){
					alert("UPDATE 성공" + data.status + " : " + data.message);
				} else {
					alert("UPDATE 실패" + data.status + " : " + data.message);
				}
			}
			
		})
			
		
	} // end CommentupdateBtn
	
	//댓글 삭제
	function deleteComment() {
		
	}//end deleteComment

