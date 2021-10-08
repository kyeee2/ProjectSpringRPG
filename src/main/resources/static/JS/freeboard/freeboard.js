var page = 1;	// 현재 페이지
var pageRows = 5;	// 페이징당 글의 개수

var search = false;	// 검색을 사용했는지
var searchPage = 1;	// 검색한 경우 검색의 현재 페이지

$(document).ready(function() {
	
	
	// 페이지 최초 로딩되면 1페이지 내용을 로딩
	loadPage(page);
	
	// 검색한 경우
	$("#btn-search").click(function() {
		window.search = true;
		searchData(searchPage);
	});
	
});

// page번째 페이지 목록 읽어오기
function loadPage(page) {
	
	if(page == 1) {	// 첫 페이지에는 인기글 목록도 추가
		$.ajax({
			url: "/board/vogueList/freeboard",	// url : /board/vogueList/freeboard
			type: "GET",
			cache: false,
			success: function(data, status) {
				if(status == "success") {
					// response가 application/json 이면 이미 parse된 결과가 data에 담겨있다.
					addvogueList(data)
				}
			}
		});
	} else {
		$("#list #vogue_list").html("");	// 첫페이지가 아니면 인기글 목록은 없음
	}
	
	$.ajax({
		url: "/board/list/freeboard/" + page + "/" + pageRows,	// url : /ajax/freeboard/{page}/{pageRows}
		type: "GET",
		cache: false,
		success: function(data, status) {
			if(status == "success") {
				// response가 application/json 이면 이미 parse된 결과가 data에 담겨있다.
				updateList(data)
			}
		}
	});
}	// end loadPage()

// 목록 업데이트

// 인기글 목록
function addvogueList(jsonObj) {
	var vogue = "";
	
	if(jsonObj.status == "OK") {
		var count = jsonObj.count;
		
		var items = jsonObj.data;	// 배열
		for(var i = 0; i < count; i++) {
			vogue += "<tr>\n";
			vogue += "<td class='num'>" + items[i].uid + "</td>\n";
			vogue += "<td class='num'>" + items[i].goodcnt + "</td>\n";
			vogue += "<td><a href='view?boardType=freeboard&uid=" + items[i].uid + "'> (인기글)" + items[i].title + "(" + items[i].commentcnt + ")"+ "</td>\n";
			vogue += "<td class='num1'>" + items[i].nickname + "</td>\n";
			vogue += "<td class='num1'>" + items[i].datetime + "</td>\n";
			vogue += "<td class='num1'><span data-viewcnt='" + items[i].uid +"'>" + items[i].viewcnt + "</span></td>\n";

			vogue += "</tr>\n";
			
			$("#list #vogue_list").html(vogue);	// 테이블 업데이트
		}
	} else {
		return false;
	}
}

// 성공하면 true, 실패하면 false 리턴
function updateList(jsonObj) {
	var result = "";	// 최종 결과
	
	if(jsonObj.status == "OK") {
		var count = jsonObj.count;
		
		window.page = jsonObj.page;
		window.pageRows = jsonObj.pagerows;
		
		var items = jsonObj.data;	// 배열
		for(var i = 0; i < count; i++) {
			result += "<tr>\n";
			result += "<td class='num'>" + items[i].uid + "</td>\n";
			result += "<td class='num'>" + items[i].goodcnt + "</td>\n";
			result += "<td class='text'><a href='view?boardType=freeboard&uid=" + items[i].uid + "'>" + items[i].title + "(" + items[i].commentcnt + ")" + "</td>\n";
			result += "<td class='num1'>" + items[i].nickname + "</td>\n";
			result += "<td class='num1'>" + items[i].datetime + "</td>\n";
			result += "<td class='num1'><span data-viewcnt='" + items[i].uid +"'>" + items[i].viewcnt + "</span></td>\n";

			result += "</tr>\n";
		}
		
		$("#list #board_list").html(result);	// 테이블 업데이트
		
		// 페이지 정보 업데이트
		$("#pageinfo").text(jsonObj.page + "/" + jsonObj.totalpage + "페이지, " + jsonObj.totalcnt + "개의 글");
		
		// pageRows
		var txt = "<select id='rows' onchange='changePageRows()'>\n";
		txt += "<option " + ((window.pageRows == 1)?"selected":"")  + " value='1'>1개씩</option>\n";
		txt += "<option " + ((window.pageRows == 2)?"selected":"")  + " value='2'>2개씩</option>\n";
		txt += "<option " + ((window.pageRows == 5)?"selected":"")  + " value='5'>5개씩</option>\n";
		txt += "<option " + ((window.pageRows == 10)?"selected":"")  + " value='10'>10개씩</option>\n";
		txt += "</select>\n";
		$("#pageRows").html(txt);	// pageRows 업데이트
		
		// [페이징] 정보 업데이트
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$("#pagination").html(pagination);
		
	} else {
		alert("내용이 없습니다.");
		return false;
	}
	
	return true;
}	// end updateList()

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
		str += "<li><a onclick='" + ((window.search)?"searchData(":"loadPage(") + 1 + ")' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
	}
	
  	//■  < 표시 여부
    if (start_page > 1) 
    	str += "<li><a onclick='" + ((window.search)?"searchData(":"loadPage(") + (start_page - 1) + ")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";
    
    //■  페이징 안의 '숫자' 표시	
	if (totalPage > 1) {
	    for (var k = start_page; k <= end_page; k++) {
	        if (curPage != k)
	            str += "<li><a onclick='" + ((window.search)?"searchData(":"loadPage(") + k + ")'>" + k + "</a></li>\n";
	        else
	            str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
	    }
	}
	
	//■ > 표시
    if (totalPage > end_page){
    	str += "<li><a onclick='" + ((window.search)?"searchData(":"loadPage(") + (end_page + 1) + ")' class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
    }

	//■ >> 표시
    if (curPage < totalPage) {
        str += "<li><a onclick='" + ((window.search)?"searchData(":"loadPage(") + totalPage + ")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
    }

    return str;

}	// end buildPagination()

// <select> 에서 pageRows 값 변경될때마다
function changePageRows() {
	window.pageRows = $("#rows").val();	// select 의 id : rows
	if(window.search) {	// 검색한 상태면 
		searchData(window.searchPage);
	} else {	// 검색이 아니라면
		loadPage(window.page);
	}
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
		
		var data = $("#frm").serialize();
		
		// DELETE 방식
		$.ajax({
			url : "/board/delete",	// localhost:8090
			type : "POST",
			data : "boardType=freeboard&" + data,
			cache : false,
			success : function(data, status) {
				if(status == "success")	{	//	200
					if(data.status == "OK") {
						alert("DELETE 성공 : " + data.count + "개");
						loadPage(window.page);	// 현재 페이지 목록 리로딩

					} else {
						alert("DELETE 실패 " + data.message);
					}
				}
			}
		});	// end ajax
	}	// if-else
} // end deleteData()

// 게시판 안에서 검색했을 때 해당 목록 가져오기
function searchData(searchPage) {
	var text = $("#input-search").val();
	
	$.ajax({
			url : "/board/searchList/freeboard/" + searchPage + "/" + pageRows,
			type : "POST",
			data : "text=" + text,
			cache : false,
			success : function(data, status) {
				if(status == "success")	{	//	200
					if(data.status == "OK") {
						alert("검색 성공");
						writeSearchList(data);
					} else {
						alert("검색 실패");
					}
				}
			}
		});	// end ajax
}	// end searchData(searchPage)

function writeSearchList(jsonObj){
	var result = "";	// 최종 결과
	
	if(jsonObj.status == "OK" ) {
		var count = jsonObj.count;
		
		if(count == 0) {	// 데이터가 없다면
			alert("찾으시는 항목이 없습니다.");
			$("#input-search").val("");
			loadPage(page);
		} else {
			window.page = jsonObj.page;
			window.pageRows = jsonObj.pagerows;
			
			var items = jsonObj.data;	// 배열
			for(var i = 0; i < count; i++) {
				result += "<tr>\n";
				result += "<td class='num'>" + items[i].uid + "</td>\n";
				result += "<td class='num'>" + items[i].goodcnt + "</td>\n";
				result += "<td class='text'><a href='view?boardType=freeboard&uid=" + items[i].uid + "'>" + items[i].title + "(" + items[i].commentcnt + ")" + "</td>\n";
				result += "<td class='num1'>" + items[i].nickname + "</td>\n";
				result += "<td class='num1'>" + items[i].datetime + "</td>\n";
				result += "<td class='num1'><span data-viewcnt='" + items[i].uid +"'>" + items[i].viewcnt + "</span></td>\n";
				result += "</tr>\n";
			}
			
			
			$("#list #vogue_list").html("");	// 테이블 업데이트
			$("#list #board_list").html("");	// 테이블 업데이트
			$("#list #search_list").html(result);	// 테이블 업데이트
			
				
			// 페이지 정보 업데이트
			$("#pageinfo").text(jsonObj.page + "/" + jsonObj.totalpage + "페이지, " + jsonObj.totalcnt + "개의 글");
			
			// [페이징] 정보 업데이트
			var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
			$("#pagination").html(pagination);
		}
	} else {
		alert("데이터를 불러오는데 실패했습니다.");
	}
	
}	// end writeSearchList(jsonObj)