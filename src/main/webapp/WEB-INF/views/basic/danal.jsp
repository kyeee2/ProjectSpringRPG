<!DOCTYPE html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.7.js" ></script>
<!-- 이 부분을 추가해야 iOS WKWebView에서 X버튼과 알뜬폰 화면이 정상적으로 표시 됩니다. -->
<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1,viewport-fit=cover"> 
</head>
<body>
<!-- 아임포트 자바스크립트는 jQuery 기반으로 개발되었습니다 -->

<script type="text/javascript">
// 모바일 에이전트 구분
var isMobile = {
	Android: function () {
		return navigator.userAgent.match(/Android/i) == null ? false : true;
	},
	IOS: function () {
		return navigator.userAgent.match(/iPhone|iPad|iPod/i) == null ? false : true;
	}
};

var IMP = window.IMP; // 생략가능
IMP.init('B010042932'); // 'imp00000000' 대신 부여받은 "가맹점 식별코드"를 사용

/* 중략 */
IMP.certification({
	
}, function(rsp) {
	console.log(JSON.stringify(rsp));
	if ( rsp.success ) {
		// 인증성공
		$.ajax({
			url: "https://myapp.apiserver.com/smsauth", // 현재 서비스의 웹서버로 인증된 사용자 정보 요청
			method: "POST",
			headers: { "Content-Type": "application/json" },
			data: JSON.stringify( { impUid: rsp.imp_uid } )
		}).success(function(rsp) {
			// 이후 Business Logic 처리
			takeResponseAndHandle(rsp)
		}).error( function( request, status, error ) {
			if(isMobile.IOS()){
				// 브릿지 연동 : authFail는 브릿지 네임 (프로젝트에 맞게 설정 필요)
				webkit.messageHandlers.reqFail.postMessage( request.responseText );
			} else if(isMobile.Android()){
				android.reqFail( request.responseText );
			}
		});
	} else {
		// 인증취소 또는 인증실패
		if(isMobile.IOS()){
			// 브릿지 연동 : authFail는 브릿지 네임 (프로젝트에 맞게 설정 필요)
			webkit.messageHandlers.authFail.postMessage(JSON.stringify(rsp));
		} else if(isMobile.Android()){
			android.authFail(JSON.stringify(rsp));
		}
	}
});

function takeResponseAndHandle(rsp) {
	// 인증성공
	if(isMobile.IOS()){
		// 브릿지 연동 (프로젝트에 맞게 설정 필요)
		webkit.messageHandlers.authSuccess.postMessage(JSON.stringify(rsp));
	} else if(isMobile.Android()){
		android.authSuccess(JSON.stringify(rsp));
	}
}

</script>
</body>
</html>