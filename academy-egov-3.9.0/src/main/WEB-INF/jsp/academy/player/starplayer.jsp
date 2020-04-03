<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
      	String bs = request.getHeader("User-Agent");
      	String browser = "";

	    if(bs.indexOf("chrome") != -1 || bs.indexOf("Chrome") != -1) {
	        browser = "Chrome";
	    }
	    else if(bs.indexOf("opera") != -1 || bs.indexOf("Opera") != -1) {
	        browser = "Opera";
	    }
	    else if(bs.indexOf("firefox") != -1 || bs.indexOf("Firefox") != -1) {
	        browser = "Firefox";
	    }
	    else if(bs.indexOf("safari") != -1 || bs.indexOf("Safari") != -1) {
	        browser = "Safari";
	    }
	    else if(bs.indexOf("skipstone") != -1) {
	        browser = "Skipstone";
	    }
	    //msie는 Expolrer 11d이전 버전, trident는 Explorer 11버전
	    else if(bs.indexOf("msie") != -1 || bs.indexOf("trident") != -1 || bs.indexOf("Msie") != -1 || bs.indexOf("Trident") != -1) {
	        browser = "Internet Explorer";
	    }
	    else if(bs.indexOf("netscape") != -1) {
	        browser = "Netscape";
	    }
	    else {
	        browser = "Unknown";
	    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>WILLBES</title>
<c:if test="${empty USER_ID}">
	<script>
		alert("잘못된 접근입니다.");
		self.close();
	</script>
</c:if>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" /><!-- 20140128 수정-->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9, requiresActiveX=true"/>
<link href="<c:url value='/resources/css/player/player.css' />" rel="stylesheet" type="text/css"  />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.8.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/player/star/starplayer_config.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/player/star/starplayer.js'/>"></script>
<script language="JavaScript" type="text/JavaScript"> 
window.onload = function(){
	//alert("${subject_monitormode}");
	frameControl();//컨트롤 박스에 포커스 주기
}

//컨트롤 박스에 포커스 주기 
function Controlfocus(){ 
 	document.fmLeft.focus();
}

var setyn = "";
function frameControl(){
	var frameCheck = parent.document.frmMain.frmCheck.value ;
	var ScreenW = window.screen.availWidth; // 현재컴퓨터의 해상도싸이즈
	var xo=parent.document.body.offsetWidth; //현재 팝업창 싸이즈
	
	if(setyn == ""){
		//parent.window.resizeBy(-280,0);
		setyn = "Y";
	}
	if(frameCheck == "open"){
			if (550 < xo-272)
			{
				parent.window.resizeBy(-280,0);
			}else{
			<c:if test="${params.Quality eq 'Wide' }">
			//alert("open1");
				parent.window.resizeBy(1570-xo,0);
			</c:if>
			<c:if test="${params.Quality eq 'high_Q' }">
			//alert("open2");
				parent.window.resizeBy(1970-xo,0);
			</c:if>
			<c:if test="${params.Quality eq 'low_Q' }">
			//alert("open3");
				parent.window.resizeBy(938-xo,0);
			</c:if>
			}
			//	document.all.viewBtn.src = "/resources/images/player/btn2.jpg";
				parent.document.frmMain.frmCheck.value = "close";
	}else{	
			var fsize = parent.document.frmMain.frmSize.value;
			parent.window.resizeBy(0,0);
			xo=parent.document.body.offsetWidth; 
			
			if (xo < 1800)
			{
				parent.window.moveBy(0,0);
			<c:if test="${params.Quality eq 'Wide' }">
			//alert("open11-1");
				parent.window.resizeBy(1574-xo,0);
				
			</c:if>
			<c:if test="${params.Quality eq 'high_Q' }">
			//alert("open22");
				parent.window.resizeBy(1254-xo,0);
			</c:if>
			<c:if test="${params.Quality eq 'low_Q' }">
			//alert("open33");
				parent.window.resizeBy(938-xo,0);
			</c:if>
			
			}
			//document.all.viewBtn.src = "/resources/images/player/btn2.jpg";
			parent.document.frmMain.frmCheck.value = "open";
	}	
	Controlfocus(); // 컨트롤 박스에 포커스 주기 
}

//목록닫기
function Close(){
	parent.document.frmMain.frmCheck.value = "open";
	frameControl();	
}

//단축키 안내 
function toggle_A(item_ma) {
	Controlfocus(); // 컨트롤 박스에 포커스 주기 
	if(document.getElementById(item_ma).style.display =="none"){
		document.getElementById(item_ma).style.display = "";
	}else{ 
		document.getElementById(item_ma).style.display = "none";
	}
}

function NotFile_close() {
	Controlfocus();
	layer_file_popup.style.display = "none";
}

function NotFile(){
	Controlfocus();
	layer_file_popup.style.display = "";
	history.back();
}

$(window).bind('beforeunload',  function (){
	
  	if($("#CoopLecture").val() != ""){	

		/* var opener_url	= "";

		//var player_time	= player.getPlayTime();
		//var player_position = player.getCurrentPosition();
		
		//재생창을 닫을때 부모창을 리로드한다. (이를 통해 강좌의 잔여기간 및 진도율을 갱신한다)
		var url = "/movieLectureInfo/playerInsert.html?ORDERNO=${params.ORDERNO}&PACKAGE_NO=${params.PACKAGE_NO}&LECCODE=${params.LECCODE}&LECTURE_NO=${params.LECCODE}&MOVIE_NO=${params.MOVIE_NO}&CURR_TIME=" 
			+ Math.floor(0) 
			+ "&LAST_POSITION_TIME=" 
			+ Math.floor(0) 
			+ "&opener_url=" 
			+ opener_url;
		
		// ajax post
		$.post(url);
	
		// 부모창 리로드
		//opener.location.reload();
		parent.opener.fn_PlayerReload();		// DB처리보다 페이지 리로드가 빠르다 딜레이를 주자 */
	}  
	

});


function fn_BookMark(){
	url = document.fmLeft.fn_BookMarkLink();	 
	$("#fmRight1").attr("src", url);
}

function fn_RightList(){
	url = document.fmLeft.fn_RightListLink();
	//$("#fmRight").attr("src", url);

}
//-->
</script>
<script>
	$(function(){
		//tab
		$('.pTabCont').hide();
		$('#pTabC1').show()
		$('.pTab>li>a').click(function(){
			var hrefs=$(this).attr('href');
	
		   	$('.pTab>li').removeClass('active')
            $(this).parents().addClass('active');
            $('.pTabCont').hide();
            $(hrefs).show();
           	$('.pTab>li>a>img').each(function(){			
				var imgSrc=$(this).attr('src');
				imgSrc=imgSrc.replace('_on.png','.png');
				$(this).attr('src',imgSrc);
		   	});
		  	var imgSrcAct=$(this).children().attr('src');
		  	imgSrcAct=imgSrcAct.replace('.png','_on.png');
		  	$(this).children().attr('src',imgSrcAct);		  
			return false;    
		});

        //table hover
		$('.listBody table tr').hover(function(){
			$(this).addClass('hv')
		},function(){
		    $(this).removeClass('hv')		
		});

		
		<c:if test="${params.Quality != 'Wide'}">
		//열고닫음
		$('.openClose').click(function(){
			if($(this).hasClass('close')){//열기
				
				$(this).removeClass('close');
              	$('.playerListAr').show();
              	$('.playerWrap').css('width','960px');
              	frameControl();
			} else{//닫기
				$(this).addClass('close');
				$('.playerListAr').hide();
				$('.playerWrap').css('width','680px');
				Close();
			}
		});
		</c:if>
		
		<c:if test="${params.Quality == 'Wide'}">
		//열고닫음
		$('.openClose').click(function(){
			if($(this).hasClass('close')){
				$(this).removeClass('close');
              	$('.playerListAr').show();
              	if($('.playerWrap').hasClass('widePlayer')){
              		$('.playerWrap').css('width','1775px');
              	} else if($('.playerWrap').hasClass('widePlayer2')){
              		$('.playerWrap').css('width','1560px');
              	} else if($('.playerWrap').hasClass('highPlayer')){
              		$('.playerWrap').css('width','1240px');
              	} else if($('.playerWrap').hasClass('lowPlayer')){
              		$('.playerWrap').css('width','925px');
              	} else{
              		$('.playerWrap').css('width','960px');
              	}
              	frameControl();
			} else{
				$(this).addClass('close');
				$('.playerListAr').hide();
				if($('.playerWrap').hasClass('widePlayer')){
              		$('.playerWrap').css('width','892px');
				} else if($('.playerWrap').hasClass('widePlayer2')){
              		$('.playerWrap').css('width','1280px');
				} else if($('.playerWrap').hasClass('highPlayer')){
              		$('.playerWrap').css('width','960px');
				} else if($('.playerWrap').hasClass('lowPlayer')){
              		$('.playerWrap').css('width','643px');
              	} else{
              		$('.playerWrap').css('width','680px');
              	}		
				Close();
				
			}
		});
		</c:if>
		
		//단축기
		$('.ctrkbtn').click(function(){
			if($(this).hasClass('show')){
				$(this).removeClass('show');
				$('.ctrlKey').hide();
	  		} else {
	  			$(this).addClass('show');
	  			$('.ctrlKey').show();
	  		}
		});

		
	});
	
	function NotFile_close() {
		Controlfocus();
		layer_file_popup.style.display = "none";
	}
	
	function fn_Goboard(state){
		if(state == 'Y'){
			var openNewWindow = window.open("about:blank");
			openNewWindow.location.href="/teacher/board/board_list.html?topMenuType=O&topMenuGnb=OM_002&topMenu=001&menuID=OM_002_006_002&topMenuName=9급공무원&BOARDTYPE=T3&INCTYPE=list&BOARD_MNG_SEQ=BOARD_012&searchUserId=${SUBJECT_TEACHER}&searchUserNm=${SUBJECT_TEACHER_NM}";
		} else {
			alert("준비중입니다.");
		}
	}
</script>

</head>
<body>
<!-- <body> -->
 <form name="frmMain">
  <input type="hidden" name="PF_IDX" value="">
  <input type="hidden" name="frmCheck" value="close">
  <input type="hidden" name="frmSize" value="550,265,0">
  <input type="hidden" name="LEC_TYPE" value="">
  
</form>

<c:if test="${params.Quality eq 'Wide' }">
		<div class="playerWrap widePlayer2">
		<c:set var="width1" value="1280" />
		<c:set var="width2" value="680" />
		<c:set var="r_width2" value="270" />
		<c:set var="r_height2" value="575" />
	</c:if>
	<c:if test="${params.Quality eq 'high_Q' }">
	<!-- <script>alert("영상33");</script> -->
		<div class="playerWrap highPlayer">
		<c:set var="width1" value="960" />
		<c:set var="width2" value="670" />
		<c:set var="r_width2" value="270" />
		<c:set var="r_height2" value="392" />
	</c:if>
	<c:if test="${params.Quality eq 'low_Q' }">
	<!-- <script>alert("영상44");</script> -->
		<div class="playerWrap lowPlayer">
		<c:set var="width1" value="642" />
		<c:set var="width2" value="358" />
		<c:set var="r_width2" value="270" />
		<c:set var="r_height2" value="370" />
	</c:if>

<div class="openClose">오픈</div>
<c:set value="<%=browser%>" var="browser"/>
	<div class="playerAr">
		<h1>${TITLE} <span id="subTitle" name="subTitle" class="st" ><c:out value="${fn:substring(playerinfo.MOVIE_NAME,0,20)}"/><c:if test="${fn:length(playerinfo.MOVIE_NAME) > 20 }">...</c:if></span></h1>
		<div class="movAr">
			
			<iframe name="fmLeft" id="fmLeft" scrolling="no" width='${width1 }' height='${width2 }' src="<c:url value='/player/view_left.pop2?LECCODE=${params.LECCODE}&BRIDGE_LECCODE=${params.BRIDGE_LECCODE}&MOVIE_NO=${params.MOVIE_NO}&GUBUN=${params.GUBUN}&Quality=${params.Quality}&BROWSER=${browser}'/>" frameborder=0 marginwidth="0" marginheight="0" border="0" noresize="no" style="margin: 0" allowfullscreen></iframe>
		</div>
	</div>

<div class="playerListAr">
		<div class="head">
			<a href="#" class="ctrkbtn"><img src="<c:url value='/resources/img/btn_ctr4.png'/>" alt="단축기"></a>
		</div>		
		<div class="content">
			<div class="pConts">
			<ul class="pTab pTabFree">
				    <div id="resumelist" class="ctrlKey" style="display: none;">
						<img src="<c:url value='/resources/img/ctrlkey.png'/>" alt="단축키안내">
					</div>
				</ul>
				<div class="pTabCont" id="pTabC1">	
					
					<iframe name="fmRight" id="fmRight"  width='${r_width2 }' height='${r_height2 }' src="<c:url value='/player/view_right.pop2?LECCODE=${params.LECCODE}&BRIDGE_LECCODE=${params.BRIDGE_LECCODE}&MOVIE_NO=${params.MOVIE_NO}&GUBUN=${params.GUBUN}&PAGE=1&position=1&samplecount=1&Quality=${params.Quality}&BROWSER=${browser}'/>" frameborder=0 scrolling="no" marginwidth="0" marginheight="0" border="0" noresize="0" ></iframe>
				</div>
				<!-- //pTabC1 -->
				<div class="pTabCont" id="pTabC2">	
					<iframe name="fmRight1" id="fmRight1" width='${r_width2 }' height='${r_height2 }' src="" frameborder=0 scrolling="no" marginwidth="0" marginheight="0" border="0" noresize="0" ></iframe>
				</div> 
				<!-- //pTabC2 -->
			</div>
 			<!-- //pConts -->
		</div>
		<!-- //content -->
		<div class="foot"><img src="<c:url value='/resources/img/foot_logo.gif'/>" alt="가방빠른 합격의 전략 윌비스"></div>
	</div>
	</div>
<input type=hidden name="oCheckWin">
</body> 
</html>
