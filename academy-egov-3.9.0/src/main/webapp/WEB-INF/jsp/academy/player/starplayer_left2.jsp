<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" /><!-- 20140128 수정-->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9, requiresActiveX=true"/><!-- 20140128 수정-->
<title>WILLBES</title>

<link href="<c:url value='/resources/css/player/style.css' />" rel="stylesheet" type="text/css"  />
<link type="text/css" href="<c:url value='/resources/css/player/starplayer.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.8.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui/jquery-ui-1.9.1.custom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/player/star/starplayer_config.js'/>"></script>
<%-- <c:choose>
	<c:when test="${params.USER_ID eq 'test' ||  params.USER_ID eq 'sunmoon1' ||  params.USER_ID eq 'sunmoon2' ||  params.USER_ID eq 'sunmoon3' ||  params.USER_ID eq 'sunmoon4'}">
		<script type="text/javascript" src="/resources/player/star/starplayer_config2.js"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="/resources/player/star/starplayer_config.js"></script>
	</c:otherwise>
</c:choose> --%>
<script type="text/javascript" src="<c:url value='/resources/player/star/starplayer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/player/star/starplayer_ui.js'/>"></script>
<c:if test="${empty playerinfo}">
	<SCRIPT type="text/javascript" LANGUAGE="JavaScript">
		alert("잘못된 접근입니다.");
		parent.location.href="/";
		parent.self.close();
	</script>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>player</title>
<c:if test="${params.Quality eq 'Wide'}">
	<c:set var="filename" value="${playerinfo.WIDE_URL}/${playerinfo.MOVIE_FILENAME4}" />
</c:if>
<c:if test="${params.Quality eq 'low_Q'}">
	<c:set var="filename" value="${playerinfo.MP4_URL}/${playerinfo.MOVIE_FILENAME2}" />
</c:if>
<c:if test="${params.Quality eq 'high_Q'}">
	<c:set var="filename" value="${playerinfo.MP4_URL}/${playerinfo.MOVIE_FILENAME3}" />
</c:if>
<c:set var="filename" value="${fn:replace(filename, ' ', '')}" />
<script language="JavaScript">


<!--
	var player;
	var step_;
	step_ = 10;

	function getStep() {
		return step_;
	}
			
	function setStep(step) {
		step_ = step;
	}

	function onMouseDbclick(x, y) {
		player.setFullscreen(!player.getFullscreen());
	}

	function onKeyDown(keycode) {
		if (window.event) {
			var type = window.event.srcElement.type;
			if (type == "text" || type == "textarea")
				return true;
		}
		switch (keycode) {
			case 13: // ENTER
				player.setFullscreen(true);
				break;
			case 32: // SPACE
				if (player.getPlayState() == PlayState.PLAYING)
					player.pause();
				else 
					player.play();
				break;
			case 38: // UP
				player.setVolume(player.getVolume() + 0.1);
				break;
			case 40: // DOWN
				player.setVolume(player.getVolume() - 0.1);
				break;
			case 37: // LEFT
				player.backward(getStep());
				break;
			case 39: // RIGHT
				player.forward(getStep());
				break;
			case 190: // >
				player.setRate(player.getRate() + 0.2);
				break;
			case 188: // <
				player.setRate((player.getRate() - 0.2) < 0.6 ? 0.6 : (player.getRate() - 0.2));
				break;
			case 77: // M
				player.setMute(!player.getMute());
				break;
			case 82: // R
				player.setRepeat(!player.getRepeat());
				break;
			case 90: // z : 원배속		
				player.setRate(1);
				break;
			case 88: // x : 느리게
				player.setRate((player.getRate() - 0.2) < 0.6 ? 0.6 : (player.getRate() - 0.2));
				break;
			case 67: // c : 빠르게
				player.setRate(player.getRate() + 0.2);
				break;
			default:
				return;
		}
		return false;
	}

	function onPlayStateChange(state) {
		switch (state) {
			case PlayState.PLAYING:
				player.setVisible(true);
				break;
			case PlayState.PAUSED:
				break;
			case PlayState.STOPPED:
				player.setVisible(false);
				break;
			case PlayState.BUFFERING_STARTED:
				break;
		}
	}
			
	function onError(error_code) {
		player.setVisible(true);
	
		switch (error_code) {
			case StarPlayerError.MULTIPLE_CONNECTIONS:
				alert("아이디 동시 접속!");
		};
	}
//-->
</script>
<script language="JavaScript">
<!--
	if(parent.opener == null || parent.opener == undefined){
		alert("잘못된 접근입니다.");
	    parent.location.href="/";
	}

	 function click(){
		if ((event.button==2) || (event.button==3) || (event.keyCode == 93)){
			if(window.navigator.browserLanguage != "ko")alert('Can not use right-click of mouse.');
			else alert('마우스 오른쪽 버튼은 사용할수 없습니다.');
		}
		else{
			if((event.ctrlKey) || (event.keyCode == 67)){
				if(window.navigator.browserLanguage != "ko")alert('Can not copy without permission.');
				else alert('내용을 무단복제하실 수 없습니다.');
			}
		}
	}
	document.onmousedown=click;
	document.onkeydown=click;
//-->
</script>
<script language="JavaScript">
window.onload = function () {
	onLoad();
}
 function movie_unload(){
		var opener_url	= "";;
		var player_time	= player.getPlayTime();
		var player_position = player.getCurrentPosition();
		// 닫기전에 진도율 갱신해보자
// 		var url = "<c:url value='/mylecture/myMovieInsert.html'/>?USER_ID=${params.USER_ID}&PLAYER_KIND=${PLAYER_KIND}&ORDERNO=${params.ORDERNO}&PACKAGE_NO=${params.PACKAGE_NO}&LECCODE=${params.LECCODE}&LECTURE_NO=${params.LECCODE}&MOVIE_NO=${params.MOVIE_NO}&CURR_TIME=" 
// 		//var url = "/movieLectureInfo/playerInsert.html?USER_ID=${params.USER_ID}&PLAYER_KIND=${PLAYER_KIND}&ORDERNO=${params.ORDERNO}&PACKAGE_NO=${params.PACKAGE_NO}&LECCODE=${params.LECCODE}&LECTURE_NO=${params.LECCODE}&MOVIE_NO=${params.MOVIE_NO}&CURR_TIME=" 
// 			+ Math.floor(player_time) 
// 			+ "&LAST_POSITION_TIME=" 
// 			+ Math.floor(player_position) 
// 			+ "&opener_url=" 
// 			+ opener_url;

// 		// ajax post
// 		$.post(url);
}

 
 function getplayer_time(){
	return player.getPlayTime();
 }
 
function getplayer_position(){
	return player.getCurrentPosition();
 }

$(window).bind('beforeunload',  function (){	
// 	if("" == "${params.SAMPLE}"){
// 		movie_unload();
// 		movie_log("OUT"); //플레이어 벗어날때  로그
// 		parent.opener.fn_PlayerReload();		// DB처리보다 페이지 리로드가 빠르다 딜레이를 주자
// 	}
});

var timer_num = 0;
//ANONYMOUS
//userId: "pass_"+"${userInfo.USER_ID}",	
function onLoad() {
 	//alert("playerinfo.MOVIE_FILENAME1=${playerinfo.MOVIE_FILENAME1}");
	//alert("playerinfo.MOVIE_FILENAME2=${playerinfo.MOVIE_FILENAME2}");
	//alert("playerinfo.MOVIE_FILENAME3=${playerinfo.MOVIE_FILENAME3}");
	//alert("playerinfo.MOVIE_FILENAME4=${playerinfo.MOVIE_FILENAME4}");

	//alert("${filename}, ${params.FLAG}");

	var Num = '';
	for(var i=0; i <9; i++){
		Num += Math.floor(Math.random() * 10);
	}	
	
	if("${params.USER_ID}" == "" || "${params.USER_ID}" == null){
		var config = {		
				userId: "cop_"+ Num,			
				id: "starplayer",
				videoContainer: "video-container",
				controllerContainer: "controller-container",
				controllerContainerHtml5: "controller-container2",
				controllerUrl: "/resources/player/axissoft3.bin",
				controller64Url: "/resources/player/axissoft3_x64.bin", //64비트 콘트롤러
				visible:false
			};		
	}else{
		var userid = "";
		if("${params.USER_ID}"=='ANONYMOUS'){
			userid = "${params.USER_ID}";
		}else{
			userid =  "cop_"+"${params.USER_ID}";
		}
		var config = {		
				userId: userid,				
				id: "starplayer",
				videoContainer: "video-container",
				controllerContainer: "controller-container",
				controllerContainerHtml5: "controller-container2",
				controllerUrl: "/resources/player/axissoft3.bin",
				controller64Url: "/resources/player/axissoft3_x64.bin", //64비트 콘트롤러
				visible:false
			};
	}

	var loadTime = 0;
	if(("Y" == "${params.CONN}" && "" == "${params.SAMPLE}") || "" != "${params.BOOKMARK}")
		loadTime = parseInt("${params.MOVIE_LAST_TIME}");	
	
	var media = {
		url: "${filename}",
		startTime: loadTime,
		blockMessenger: false
	};
	player = new StarPlayer(config, media);
	player.onKeyDown = onKeyDown;
	player.onMouseDbclick = onMouseDbclick;
	player.onPlayStateChange = onPlayStateChange;
	player.onError = onError; 
	initScriptUI(player); 
	
	 // 워터마크 
	watermarkText: "${params.USER_ID}" ;
		watermarkTextColor: "#40ffffff";
		watermarkTextSize: "5%";
		watermarkHorzAlign: WatermarkAlign.RANDOM;
		watermarkVertAlign: WatermarkAlign.RANDOM;
		wartermarkShowInterval: 5   ; //5초동안 보임  
		wartermarkInterval: 10 ;      // 10초 간격으로 뜸
		
		init_load();
		

}

function init_load() {

     if( timer_num > 10) {
         return;
     }
     
     var isIE = (navigator.appName.indexOf("Microsoft") != -1);
     var player_ = isIE ? document.all.AxPlayer : document.AxPlayer;
     var study_pos = 342;
		
     if(  player.getPlayState() != PlayState.PLAYING) {
         setTimeout("init_load()", 1000);
         timer_num++;
         return;
     }
     
     
}
</script> 
</head>
	<c:if test="${params.Quality eq 'Wide' }">
		<c:set var="width1" value="1282" />
		<c:set var="width2" value="1282" />
		<c:set var="height1" value="559" />
		<c:set var="height2" value="81" />
	</c:if>
	<c:if test="${params.Quality eq 'high_Q' }">
		<c:set var="width1" value="964" />
		<c:set var="width2" value="964" />
		<c:set var="height1" value="417" />
		<c:set var="height2" value="81" />
	</c:if>
	<c:if test="${params.Quality eq 'low_Q' }">
		<c:set var="width1" value="643" />
		<c:set var="width2" value="643" />
		<c:set var="height1" value="281" />
		<c:set var="height2" value="81" />
	</c:if>



<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onkeydown="javascript:onKeyDown(event.keyCode);">

	<div id="video-container" style="width:${width1 }px;height:${height1 }px;"></div>
	<%-- <div id="controller-container" style="width:${width2}px;height:${height2 }px;"></div> --%>
	<div id="controller-container2" class="starplayer_script_ui" style="margin: 0 0 0 0;width:${width2 }px;height:${height2 }px;background-color:black;">
			<div class="top_area">
				<div class="seekbar_l">
					<div class="currentbar"></div>
					<div class="repeatbar"></div>
					<div class="seekbar_area">
						<a class="btn_common btn_seek"></a>
						<a class="btn_common btn_repeatA" style="left:0%;display:none;"></a>
						<a class="btn_common btn_repeatB" style="left:100%;display:none;"></a>
					</div>
				</div>
				<div class="seekbar_r">
					<a class="btn_common btn_repeat"></a>
					<a class="btn_common btn_fullscreen"></a>
					<a class="btn_common btn_mute"></a>
					<div class="volumebar">
						<div class="current_volumebar"></div>
						<div class="volumebar_area">
							<a class="btn_common btn_volume"></a>
						</div>
					</div>
				</div>
			</div>
			<div class="bottom_area">
				<div class="control_l">
					<div class="basic_controls">
						<a class="btn_common btn_play"></a>
						<a class="btn_common btn_stop"></a>
						<a class="btn_common btn_backward"></a>
						<a class="btn_common btn_forward"></a>
					</div>
					<div class="control_text_status">준비</div>
					<div class="control_text_time"><span id="text_currentTime">00:00:00</span> / <span id="text_duration">00:00:00</span></div>
				</div>		
				<div class="control_r">
					<ul class="speed_controls">
						<li><a class="btn_common btn_speed06"></a></li>
						<li><a class="btn_common btn_speed08"></a></li>
						<li><a class="btn_common btn_speed10 active"></a></li>
						<li><a class="btn_common btn_speed12"></a></li>
						<li><a class="btn_common btn_speed14"></a></li>
						<li><a class="btn_common btn_speed16"></a></li>
						<li><a class="btn_common btn_speed18"></a></li>
						<li><a class="btn_common btn_speed20"></a></li>
					</ul>
				</div>
			</div>
	</div>
	
</body>
</html>