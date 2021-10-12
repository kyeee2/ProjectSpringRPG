var boardType = "";	// boardType 세팅
var uid = "";	//게시글 고유번호
var nickName = "";
var head = ""; // 게시판 head용

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
	
	// 좋아요 누르기
	$("#btn_good").click(function() {
		doGood();
	});
	
	// uid로 삭제하기
	$("#doDelete").click(function() {
		chkDelete();
	});
	
	//boardtype이 freeboard일 때
	if(boardType=="freeboard") {
		 head="자유게시판";
		
		$("#headtitle").html(head);
	} else {
		 head="리뷰게시판"
		$("#headtitle").html(head);
	}
	
	//댓글 읽어오기
	commentList(boardType, uid);
	
	//댓글등록 버튼 클릭 시
	$('[name=commentInsertBtn]').click(function(event) {	//댓글등록 버튼 클릭 시
		if(chkCosubmit() == true) {
			var insertData = $('[name=commentFrm]').serialize(); //commentInsertForm 내용 가져오기
			commentinsert(insertData);//TODO
		} else {
			event.preventDefault();
		}
		
	});
	
});

	function chkCosubmit() {
		frm = document.forms['commentFrm'];
		
		var content = frm['content'].value.trim();
		
		if(content="") {
			alert("댓글내용은 반드시 한 글자 이상 작성해야 합니다")
			frm['content'].focus();
			return false;
		}
		return true;
	}

// 게시글 내용 읽어오기
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

// 게시글 내용 작성하기
function writeData(jsonObj) {
	
	result = "";	// 결과값 초기화

	result += "<div id='head'><div class='item'>작성자:" + jsonObj.nickname + "</div>\n";
	result += "<div class='item'>날짜:" + jsonObj.datetime + "</div>\n";
	result += "<div class='item'>조회수:" + jsonObj.viewcnt + "</div>\n";
	if(jsonObj.boardtype == "movieboard"){	
		// 주제는 영화 리뷰에서만 보임
		result += "<div class='item'>주제 : " + jsonObj.subject + "</div>\n";
	}
	if(jsonObj.boardtype != "noticeboard") {
		// 좋아요수와 댓글수는 공지사항에서는 보이지 않는다
		
		result += "<div class='item' id='goodCnt'>추천수:" + jsonObj.goodcnt + "</div>";
		result += "<div class='item'>댓글수: " + jsonObj.commentcnt +"</div></div>";
	} 
	result += "<br><div id='title'<div class='item'>제목 : " + jsonObj.title +"</div></div>\n";
	result += "<br><div id='content'><div class='item'>내용 : " + jsonObj.content +"<button id='btn_good' onclick='doGood()'><i class='far fa-grin-alt'></i></button></div></div>\n";
	$("#result").html(result);	// 정보 업데이트
	
	nickName = jsonObj.nickname;
	
}	// end writeResult()

// 게시글 삭제 확인
function chkDelete() {
	if(!confirm("이 글을 삭제하시겠습니까?")) return false;
	
	var data = "boardType=" + boardType + "&buid=" + uid + "&nickName=" + nickName;
	
	// DELETE 방식
	$.ajax({
		url : "/board/deleteOne",
		type : "POST",
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

// 게시글 좋아요
function doGood() {
	
	$.ajax({
		url : "/board/good/" + boardType + "/" + uid,
		type : "GET",
		cache : false,
		success : function(data, status) {
			if(status == "success") {
				if(data.count == 1) {
					var goodCnt = data.message;	// 성공했으면 메세지에 좋아요 수 담겨져있음
					$("#goodCnt").text("추천수: " + goodCnt);
				}
			} else {
				alert(data.message);
			}
		}
	});
	
}	// end doGood()

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

						for(var i=0; i<data.data.length; i++) {
							writeComment(data.data);	//
						
					}

				}
			}
			
		});
	}//end commentList()
	
	// 댓글 목록 작성
	function writeComment(jsonObj) {
		
		var comment ="";
		

		for(i=0; i<jsonObj.length; i++) {
			
		comment += "<form name='frm'>\n"
		comment += "<input type='hidden' name='uid' value='" + jsonObj[i].uid + "'>";
		comment += "댓글내용 : <span>" + jsonObj[i].content + "</span><br>\n"; 
		comment += "<div class='CoContain'><div class='item'>닉네임 : " + jsonObj[i].nickName + "</div>"; 
		comment += "<div class='item'>작성시간 : " + jsonObj[i].dateTime + "</div></div><br>\n"; 
		comment += "<div class= 'exam'>\n";
		comment += "<button type='button' name='btn_update' onclick='clickUpdate(event)'>댓글수정</button>";
		comment += "<button type='button' name='btn_delete' onclick='clickDelete(event)'>댓글삭제</button>";
		comment += "<button type='button' name='btn_updateOk' style='display : none;' onclick='clickUpdateOk(event)'>수정완료</button>";
		comment += "</div>\n"
		comment += "</form>\n"
		comment += "<br><br><br><br>"

		
		$("#comment").html(comment);	// 정보 업데이트

		
		
	}//end wrtieComment
	}
	function clickUpdate(event) {
		var $form =$(event.target).parent().parent();
		console.log($form.html());
		var $span = $form.children('span');

		
		var text = $span.text();
		
		$span.replaceWith("<input name='content' class='comContent' />");
		$('input[class="comContent"]').val(text);
		
		// 버튼 토글
		$form.children('div').children(1).toggle();
		$form.children('div').children(2).toggle();
		$form.children('div').children(3).toggle();
	};
	
	// 댓글 수정 완료
	function clickUpdateOk(event) {
		var $form =$(event.target).parent().parent();
		
		var comUid = $form.children('input[type=hidden]').val();
		var content = $form.children('input[name=content]').val();
		
		var data = "content=" + content + "&uid=" + comUid + "&buid" + uid + "&boardType=" + boardType;
		
		$.ajax({
			url : "/comment/updateOk",
			type : "POST",
			data : data,
			cache : false,
			success : function(data, status) {
				if(status == "success"){
					if(data.count == 1) {
						alert("댓글 수정 완료");
						commentList(boardType, uid);
					} else {
						alert("댓글 수정 실패");
					}
				}
			}
		});
	}
	
	
	//댓글 삭제 ->가져올 정보는?boardType, 댓글 uid
	function clickDelete(event) {
		var $form =$(event.target).parent().parent();
		var comUid = $form.children('input[type=hidden]').val();
		var data = "boardType=" + boardType + "&uid=" + comUid + "&buid" +uid;
		
		$.ajax({ 
			url : "/comment/deleteOk",
			type : "POST",
			data : data,
			cache : false,
			success : function(data, status) {
				if(status == "success") {
					if(data.count == 1) {		
					alert("댓글 삭제 완료")
					commentList(boardType, uid);
				} else {
					alert("댓글 삭제 실패");
					
					}
				}
			}
			
		});
	}  //end deleteComment
