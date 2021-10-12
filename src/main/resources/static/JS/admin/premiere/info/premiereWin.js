function selectWin(){
	
	var prUid = $("select").val();
	var count = $("input[name=count]").val();
	
	$.ajax({
		url : "/premiere/win/ " + prUid + "/" + count,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				if(data.status == "OK"){
					updateList(data);
					alert("리스트 성공");
				}else {
					alert(data.message);
				}
			}
		}
	});
}

function updateList(jsonObj) {
	var result = ""; // 최종 결과 
	if(jsonObj.status == "OK"){
		var count = jsonObj.count;
		var items = jsonObj.data; // 배열
		for(var i = 0; i < count; i++){
			result += "<tr>\n";
			result += "<td>" + items[i].nickname + "</td>\n";
			result += "<td>" + items[i].email + "</td>\n";
			result += "<input type='hidden' name='email' value='" + items[i].email + "'></td>\n";
			result += "</tr>\n";
		}
		$("#list tbody").html(result); // 업데이트
		
	} else {
		alert("내용이 없습니다");
		return false;
	}
	return true;
} 