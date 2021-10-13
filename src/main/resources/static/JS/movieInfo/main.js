$(document).ready(function() {
	
	$("#search").click(function() {
		var movieName = $("#searchMovie").val();	// 검색창에 적은 값 가져오기
		if(movieName == ""){
			alert("영화 이름은 필수입니다.");
		} else {
			searchNaver(movieName);
		}
	});
	
	$("#findMovie").click(function(){
		$("#searchForm").slideToggle();
	});
	$('.post-wrapper').slick({
  slidesToShow: 5,
  slidesToScroll: 3,
  autoplay: false,
  autoplaySpeed: 4000,
	nextArrow:$('.next'),
  	prevArrow:$('.prev'),
});
	$('.post-wrapper1').slick({
  slidesToShow: 3,
  slidesToScroll: 1,
  autoplay: true,
  autoplaySpeed: 4000,
});
});

function searchNaver(movieName) {
	
	$.ajax({
		url : "/movieInfo/naver/api/" + movieName,
		type : "GET",
		cache : false,
		success : function(data, status) {
			if(status == "success") {
				writeList(data);
			} else {
				alert("오류");
			}
		}
	});
	
} // end searchNaver(movieName)

function writeList(jsonObj) {
	
	result = "";
	
	var data = jsonObj.items;

	for(i = 0; i < data.length; i++) {
		var link = data[i].link;	// data의 link 값
		var code =link.substring(link.indexOf("=") + 1 );	// link에 있는 code 값 가져오기
		
		result += "<a href='/movieInfo/view?code=" + code + "' style='display: table-cell; width: 100px; hieght: 300px; padding: 15px; vertical-align: super;'>\n";
		
		var image = (data[i].image == "")?'https://ssl.pstatic.net/static/movie/2012/06/dft_img203x290.png':data[i].image;	// 이미지가 없으면 "이미지 준비중" 띄우기
		
		result += "<img src='" + image + "' />\n";
		result += "<h5>" + data[i].title.replace("<b>", "").replace("</b>", "") + "</h5>\n";
		result += "</a>\n";
	}
	
	$("#searchResult").html(result);
	
} // end writeList(jsonObj)


