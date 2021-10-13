var page = 1;	// 현재 페이지
var pageRows = 5;	// 페이징당 글의 개수

$(document).ready(function() {
	
	loadPage(page);

});

function loadPage(page) {
	$.ajax({
			url: "/admin/user/" + page + "/" + pageRows,	
			type: "GET",
			cache: false,
			success: function(data, status) {
				if(status == "success") {
					// response가 application/json 이면 이미 parse된 결과가 data에 담겨있다.
					userList(data)
				}
			}
		});
		
}
function userList(jsonObj) {
	var result ="";
	
	if(jsonObj.status =="OK") {
		var count = jsonObj.count;
		
		window.page= jsonObj.page;
		window.pageRows= jsonObj.pagerows;
		var users = jsonObj.data;
		
		
		
		for(var i = 0; i < count; i++) {
			var userid =  users[i].id;
			result += "<tr id='list-height'>\n";
			result += "<td class='num'>" + users[i].uid + "</td>\n";
			result += "<td class='num'>" + users[i].id + "</td>\n";
			result += "<td class='num1'>" + users[i].nickname + "</td>\n";
			result += "<td class='num1'><button class='delete' data-uid='" + userid + "'>비활성화</button></td>\n";
			result += "</tr>\n";
			/*document.getElementById("delete").onclick = function() {deleteUser(users[i].id)};*/
	}
	$("#list #user_list").html(result);
	$("button.delete").click(function(){
		deleteUser($(this).attr('data-uid'));
	});
	
	// [페이징] 정보 업데이트
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$("#pagination").html(pagination);
	
} else {
		alert("내용이 없습니다.");
		return false;
	}
	
	return true;
}
 function deleteUser(id) {
        if (!confirm("정말 회원정보를 삭제하시겠습니까?")) { 
	
        } else {
           $.ajax({
				url: "/admin/user",	
				type: "DELETE",
				cache: false,
				data: {"id" : id},
				success: function(data, status) {
					if(status == "success") {
						// response가 application/json 이면 이미 parse된 결과가 data에 담겨있다.
						loadPage(page);
					}
				}
		
			});
        }
    }

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

}

