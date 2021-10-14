var page = 1;
var pageRows = 50;

$(document).ready(function(){
	// 페이지 최초 로딩 -> 1페이지 로딩 
	loadPage(page);
});

function loadPage(page){
	
	$.ajax({
		url : "/premiere/premiereWin/" + page + "/" + pageRows,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				updateList(data);
			} else {
				alert(data.message);
			}
		} 
		
	});
	
}// end loadPage(page, pageRows)

function updateList(jsonObj){
	var result = ""; // 최종 결과 
	
	if(jsonObj.status == "OK"){
		var count = jsonObj.count;
		
		window.page = jsonObj.page;
		window.pageRows = jsonObj.pagerows;
		
		var items = jsonObj.data; // 배열
		for(var i = 0; i < count; i++){
			result += "<tr>\n";
			result += "<td>" + items[i].uid + "</td>\n";
			result += "<td><a href='/premiereWin/view?uid=" + items[i].uid + "'>" + items[i].title + "</td>\n";
			result += "<td>" + items[i].datetime + "</td>\n";
			result += "</tr>\n";
		}
		$("#list tbody").html(result); // 업데이트
		
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$(".pagination").html(pagination);
		
	} else {
		alert("내용이 없습니다");
		return false;
	}
	
	return true;
} // end updateList()

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











