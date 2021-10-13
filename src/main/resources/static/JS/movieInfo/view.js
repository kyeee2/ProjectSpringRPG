var code = ""; // 영화 코드값

$(document).ready(function() {
	
	// 파라미터에 있는 code 값 가져오기
	const url = new URL(window.location.href);	// 현재 URL 받기
	
	// URLSearchParams 객체
	const urlParams = url.searchParams;
	
	// URLSearchParams.get('parameter') : 파라미터명이 'parameter'인 것 중 첫번째 value 값 리턴
	code = urlParams.get('code'); // 파라미터명이 code인 value 값 받기
	
	// code 값을 가진 영화 상세 정보 출력
	movieData(code);
	
	
});	// end document.ready()

// ajax로 상세 정보 가져오기
function movieData(code) {
	
	$.ajax({
		url : "/movieInfo/naver/crawling/" + code,
		type : "GET",
		cache : false,
		success : function(data, status) {
			if(status == "success") {
				writeData(data);
			} else {
				alert("해당 영화 정보를 가져올 수 없습니다.");
			}
		}
	});
	
} // end movieData(code)

function writeData(jsonObj) {
	
	// 타이틀에 영화 제목 나타나게 하기
	$(document).find("title").text(jsonObj.moviename);
	
	// body에 영화 정보들 넣어주기
	$("#moviename").text("영화제목:" + jsonObj.moviename);
	$("#poster").html("<img src='" + jsonObj.poster + "'/>"	);
	$("#info #star").text("평점:" + jsonObj.star);
	$("#info #genre").text("개요:" + jsonObj.genre);
	$("#info #nation").text("국가:" + jsonObj.nation);
	$("#info #running-time").text("러닝타임:" + jsonObj.runningtime);
	$("#info #open-date").text("개봉일:" + jsonObj.opendate);
	if(jsonObj.viewingPage != undefined){
		$("#info #viewing-page").text("시청연령 : " + jsonObj.viewingPage);
	}
	$("#summary").html(jsonObj.summary);
	
	var directorActor = "";
	var lists = jsonObj.directorAndActor;
	for(list of lists) {
		directorActor += "<div style='display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;'>\n";
		directorActor += "<img src='" + list.image + "'/>\n";
		directorActor += "<div>" + list.name + "</div>\n";
		directorActor += "<span>" + list.staff + "</span> | <span>" + list.actname + "</span>\n";
		directorActor += "</div>\n";
	}
	$("#directorActor").html(directorActor);
	
	getVideoId(jsonObj.moviename);
	
} // end writeData(jsonObj)

var videoId = "";
function getVideoId(moviename) {
	// 유투브 API 사용
	$.ajax({
		url: "https://www.googleapis.com/youtube/v3/search?part=id, snippet&type=video&key=AIzaSyANvPgRrnXnBWKp1yV8Cinh5mAfdVPRETQ&maxResult=1&order=viewCount&q=" + moviename + " 예고편",
		type: "GET",
		cache: false,
		success : function(data, status) {
            if(status == "success") {
				videoId = data.items[0].id.videoId;
				$("#video").attr("src", "https://www.youtube.com/embed/" + videoId);
			}
		}	
    });

 }	// end getVideoId()
