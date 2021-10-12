var page = 1;	// 현재 페이지  
var pageRows = 2;	// 페이지당 글의 개수 

$(document).ready(function(){
	// 페이지 최초 로딩 -> 1페이지 로딩
	loadPage(page); 
});
// 페이지 목록 읽어오기 
function loadPage(page) {
	
	$.ajax({
		url : "/premiere/list/" + page  + "/" + pageRows,
		type : "GET",
		cache : false,
		success : function(data, status) {
			if(status == "success") {
				if(data.status == "OK") {
					updateList(data);
				} else {
					alert(data.message);
				}
			}
		}
	});
} // end loadList(page, pageRows)

// 목록 업데이트
// 성공하면 true, 실패하면 false 리턴 
function updateList(jsonObj) {
	var result = ""; // 최종 결과 
	
	if(jsonObj.status == "OK"){
		var count = jsonObj.count;
		
		window.page = jsonObj.page;
		window.pageRows = jsonObj.pagerows;
		
		var items = jsonObj.data; // 배열
		for(var i = 0; i < count; i++){
			result += "<tr>\n";
			
			//result += "<td><input type='checkbox' name='uid' value='" + items[i].uid + "'></td>\n";
			//result += "<td>" + items[i].uid + "</td>\n";
			result += "<td><a href='view?uid=" + items[i].uid + "'>" + items[i].title + "</td>\n";
			//result += "<td>" + items[i].photo + "</td>\n";
			//result += "<td>" + items[i].content + "</td>\n";

			result += "</tr>\n";
		}
		$("#list tbody").html(result); // 업데이트
		
		// 페이지 정보 업데이트
		//$("#pageinfo").text(jsonObj.page + "/" + jsonObj.totalpage + "페이지, " + jsonObj.totalcnt + "개의 ");
		
		// pageRows
		//var txt = "<select id='rows' onchange='changePageRows()'>\n";
		//txt += "<option " + ((window.pageRows == 1)?"selected":"") + " value='1'>1개씩</option>\n";
		//txt += "<option " + ((window.pageRows == 2)?"selected":"") + " value='2'>2개씩</option>\n";
		//txt += "<option " + ((window.pageRows == 5)?"selected":"") + " value='5'>5개씩</option>\n";
		//txt += "<option " + ((window.pageRows == 10)?"selected":"") + " value='10'>10개씩</option>\n";
		//txt += "</select>\n";
		//$("#pageRows").html(txt);
		
		// [페이징] 정보 업데이트
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$(".pagination").html(pagination);
		
	} else {
		alert("내용이 없습니다");
		return false;
	}
	
	return true;
} // end updateList()

// [페이징] 생성
// 한 [페이징]에 표시될 페이지수 --> writePages
// 총 페이지수 --> totalPage
// 현재 페이지 --> curPage
function buildPagination(writePages, totalPage, curPage, pageRows) {
	var str = "";   // 최종적으로 페이징에 나타날 HTML 문자열 <li> 태그로 구성
	
	// 페이징에 보여질 숫자들 (시작숫자 start_page ~ 끝숫자 end_page)
    var start_page = ( (parseInt( (curPage - 1 ) / writePages ) ) * writePages ) + 1;
    var end_page = start_page + writePages - 1;

    if (end_page >= totalPage){
    	end_page = totalPage;
    }
    
   //■ << 표시 여부
	if(curPage > 1){
		str += "<li><a onclick='loadPage(" + 1 + ")' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
	}
	
  	//■  < 표시 여부
    if (start_page > 1) 
    	str += "<li><a onclick='loadPage(" + (start_page - 1) + ")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";
    
    //■  페이징 안의 '숫자' 표시	
	if (totalPage > 1) {
	    for (var k = start_page; k <= end_page; k++) {
	        if (curPage != k)
	            str += "<li><a onclick='loadPage(" + k + ")'>" + k + "</a></li>\n";
	        else
	            str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
	    }
	}
	
	//■ > 표시
    if (totalPage > end_page){
    	str += "<li><a onclick='loadPage(" + (end_page + 1) + ")' class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
    }

	//■ >> 표시
    if (curPage < totalPage) {
        str += "<li><a onclick='loadPage(" + totalPage + ")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
    }

    return str;

}	// end buildPagination()

// <select> 에서 pageRows 값 변경될때마다
function changePageRows() {
	window.pageRows = $("#rows").val();	// select 의 id : rows
	loadPage(window.page);
}	// end changePageRows()

// 특정 uid들로 삭제
function deleteData() {
	
	var uids = [];	// check 된 uid 들을 담을 배열
	$("#list tbody input[name=uid]").each(function() {
		if($(this).is(":checked")) {	// jQuery에서 check 여부 확인 함수
			uids.push(parseInt($(this).val()));	// uids 배열에 check 된 uid값 추가
		}
	});
	
	if(uids.length == 0) {
		alert("삭제할 글을 체크해주세요");
	} else {
		if(!confirm(uids.length + "개의 글을 삭제하시겠습니까?")) return false;
		
		var data = $("#frmList").serialize();
		
		// DELETE 방식
		$.ajax({
			url : "/premiere",	
			type : "DELETE",
			data : data,
			cache : false,
			success : function(data, status) {
				if(status == "success")	{	//	200
					if(data.status == "OK") {
						alert("DELETE 성공 : " + data.count + "개");
						loadPage(window.page);	// 현재 페이지 목록 리로딩
					} else {
						alert("DELETE 실패 " + data.message);
						return false;
					}
				}
			}
		});	// end ajax
	}	// if-else
} // end deleteData()


















var page = 1;	// 현재 페이지  
var pageRows = 10;	// 페이지당 글의 개수 

$(document).ready(function(){
	// 페이지 최초 로딩 -> 1페이지 로딩
	loadPage(page); 
});
// 페이지 목록 읽어오기 
function loadPage(page) {
	
	$.ajax({
		url : "/premiere/list/" + page  + "/" + pageRows,
		type : "GET",
		cache : false,
		success : function(data, status) {
			if(status == "success") {
				if(data.status == "OK") {
					updateList(data);
				} else {
					alert(data.message);
				}
			}
		}
	});
} // end loadList(page, pageRows)

// 목록 업데이트
// 성공하면 true, 실패하면 false 리턴 
function updateList(jsonObj) {
	var result = ""; // 최종 결과 
	
	if(jsonObj.status == "OK"){
		var count = jsonObj.count;
		
		window.page = jsonObj.page;
		window.pageRows = jsonObj.pagerows;
		
		var items = jsonObj.data; // 배열
		for(var i = 0; i < count; i++){
			//result += "<tr>\n";
			result += "<div class='card' style='width:100px'>\n";
			result += "<input type='checkbox' name='uid' value='" + items[i].uid + "'>\n";
			result += "<img class='card-img-top' src='/file/premiere/" + ((items[i].photo == "")?('no_img.png'):(items[i].photo)) + "' alt='빈이미지' style='width:100px'>\n";
			result += "<div class='card-body'>\n";
			result += "<h4 class='card-title'>" + items[i].title + "</h4>\n";
			result += "<p class='card-text'>" + items[i].content + "</p>\n";
			result += "<a href='view?uid=" + items[i].uid + "' class='btn btn-primary'>추첨하기</a>\n";
			result += "</div>";
			result += "</div>";
			//result += "<td><input type='checkbox' name='uid' value='" + items[i].uid + "'></td>\n";
			//result += "<td>" + items[i].uid + "</td>\n";
			//result += "<td><a href='view?uid=" + items[i].uid + "'>" + items[i].title + "</td>\n";
			//result += "<td>" + items[i].photo + "</td>\n";
			//result += "<td>" + items[i].content + "</td>\n";

			//result += "</tr>\n";
		}
		//$("#list tbody").html(result); // 업데이트
		$("#card-list").html(result); // 업데이트
		
		// 페이지 정보 업데이트
		$("#pageinfo").text(jsonObj.page + "/" + jsonObj.totalpage + "페이지, " + jsonObj.totalcnt + "개의 ");
		
		// pageRows
		var txt = "<select id='rows' onchange='changePageRows()'>\n";
		txt += "<option " + ((window.pageRows == 1)?"selected":"") + " value='1'>1개씩</option>\n";
		txt += "<option " + ((window.pageRows == 2)?"selected":"") + " value='2'>2개씩</option>\n";
		txt += "<option " + ((window.pageRows == 5)?"selected":"") + " value='5'>5개씩</option>\n";
		txt += "<option " + ((window.pageRows == 10)?"selected":"") + " value='10'>10개씩</option>\n";
		txt += "</select>\n";
		$("#pageRows").html(txt);
		
		// [페이징] 정보 업데이트
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$("#pagination").html(pagination);
		
	} else {
		alert("내용이 없습니다");
		return false;
	}
	
	return true;
} // end updateList()

// [페이징] 생성
// 한 [페이징]에 표시될 페이지수 --> writePages
// 총 페이지수 --> totalPage
// 현재 페이지 --> curPage
function buildPagination(writePages, totalPage, curPage, pageRows) {
	var str = "";   // 최종적으로 페이징에 나타날 HTML 문자열 <li> 태그로 구성
	
	// 페이징에 보여질 숫자들 (시작숫자 start_page ~ 끝숫자 end_page)
    var start_page = ( (parseInt( (curPage - 1 ) / writePages ) ) * writePages ) + 1;
    var end_page = start_page + writePages - 1;

    if (end_page >= totalPage){
    	end_page = totalPage;
    }
    
   //■ << 표시 여부
	if(curPage > 1){
		str += "<li><a onclick='loadPage(" + 1 + ")' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
	}
	
  	//■  < 표시 여부
    if (start_page > 1) 
    	str += "<li><a onclick='loadPage(" + (start_page - 1) + ")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";
    
    //■  페이징 안의 '숫자' 표시	
	if (totalPage > 1) {
	    for (var k = start_page; k <= end_page; k++) {
	        if (curPage != k)
	            str += "<li><a onclick='loadPage(" + k + ")'>" + k + "</a></li>\n";
	        else
	            str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
	    }
	}
	
	//■ > 표시
    if (totalPage > end_page){
    	str += "<li><a onclick='loadPage(" + (end_page + 1) + ")' class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
    }

	//■ >> 표시
    if (curPage < totalPage) {
        str += "<li><a onclick='loadPage(" + totalPage + ")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
    }

    return str;

}	// end buildPagination()

// <select> 에서 pageRows 값 변경될때마다
function changePageRows() {
	window.pageRows = $("#rows").val();	// select 의 id : rows
	loadPage(window.page);
}	// end changePageRows()

// 특정 uid들로 삭제
function deleteData() {
	
	var uids = [];	// check 된 uid 들을 담을 배열
	//$("#list tbody input[name=uid]").each(function() {
	$("#card-list input[name=uid]").each(function() {
		if($(this).is(":checked")) {	// jQuery에서 check 여부 확인 함수
			uids.push(parseInt($(this).val()));	// uids 배열에 check 된 uid값 추가
		}
	});
	
	if(uids.length == 0) {
		alert("삭제할 글을 체크해주세요");
	} else {
		if(!confirm(uids.length + "개의 글을 삭제하시겠습니까?")) return false;
		
		var data = $("#frmList").serialize();
		
		// DELETE 방식
		$.ajax({
			url : "/premiere",	
			type : "DELETE",
			data : data,
			cache : false,
			success : function(data, status) {
				if(status == "success")	{	//	200
					if(data.status == "OK") {
						alert("DELETE 성공 : " + data.count + "개");
						loadPage(window.page);	// 현재 페이지 목록 리로딩
					} else {
						alert("DELETE 실패 " + data.message);
						return false;
					}
				}
			}
		});	// end ajax
	}	// if-else
} // end deleteData()


















