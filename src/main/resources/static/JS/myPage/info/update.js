$(document).ready(function(){
	
	$(".btn").click(function(event) {
		changeButton(event);
	})
	
});

function changeButton(event){
	var img = $("#img").attr("data-img");
	var $target = $(event.target);
	
	// 사진수정 버튼을 누르면 현재 사진이 삭제될 수 잇도록 type이 hidden인 input을 생성
	//		사진을 새로 올릴 수 있도록 type이 file인 input 생성
	// 수정취소 버튼을 누르면 file-delete에 있는 요소 다 삭제
	// 		file-change에 있는 요소 다 삭제
	//alert($target.get(0) == $("#btn-update").get(0));	// 이래야 두 요소가 같다고 나온다!
	if($target.get(0) == $("#btn-update").get(0)) {
		$("#btn-update").hide();
		$("#file-delete").append("<input type='hidden' name='photo' value=" + img + ">");	
		
		$("#btn-cancel").show();
		$("#file-change").show();
	} else if($target.get(0) == $("#btn-cancel").get(0)) {
		$("#btn-update").show();
		$("#file-delete").empty();	
		
		$("#btn-cancel").hide();
		$("#file-change").empty();
		$("#file-change").append("<input type='file' id='file-new' name='file' value=''>");
		$("#file-change").hide();
	}
}
