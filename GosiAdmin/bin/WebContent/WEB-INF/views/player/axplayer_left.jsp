<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="com.willbes.platform.axis.*" %>
<%@ page import="com.willbes.platform.axis.security.cryptography.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/player/style.css" />" rel="stylesheet" type="text/css"  />
<c:if test="${empty playerinfo}">
	<SCRIPT type="text/javascript" LANGUAGE="JavaScript">
		alert("잘못된 접근입니다.");
		parent.location.href="/";
		parent.self.close();
	</script>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>sample</title>
<script type="text/javascript" src="<c:url value="/resources/ax/ax.js" />"></script>
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
 function movie_unload(){
	    var player_time	= player_.GetSpeedPlayedTime();
		var last_position_time	= player_.getCurrentPosition(); // 미디어의 현재 재생 위치 반환 return:미디어의 현재 재생 시간(초)(다음플레이어 재생시 이어보기 하기위한 시간)
		var full_time = player_.getDuration();
}
 window.onunload = function(){
	 movie_unload();
}
</script>

<script language="JavaScript">
	axinit("<c:url value='/resources/ax'/>", "AxPlayer");
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div style="line-height:0;font-size:0; display:inline " >
		 <comment id="__NSID__">
				<OBJECT ID="AxPlayer" CLASSID="CLSID:62255DEA-3E2F-48b0-AA3D-589F86D6FFAA" codebase="${activeUrl}#version=${activeVersion}" width="${activeWidth}" height="${activeHeight}" style="line-height:0;font-size:0; display:inline " >
					<param name="AutoPlay" value="true">
					<param name="volume" value=50>
					<param name="showControlPane" value="false">
					<param name="swfURL" value="http://www.axissoft.co.kr/Intro.swf">
					<param name="swfPlayTime" value=0>
					<param name="checkMonitor" value="${dual_diff_density_restrict}">
					<param name="info3" value="${infoValue}">
					<param name="canshowratecontrol" value="true">
					<param name="forwardtime" value=10>
					<param name="topmost" value="false">
					<param name="watermarkshowtime" value=5>
				 	<param name="watermarkintervals" value=600>
				 	<param name="watermarkcolor" value="25,0,20,50">
				 	<param name="watermarkfontsize" value="20">
				 	<param name="watermarklight" value="true">
				</OBJECT>
			</comment>
			<!-- 동영상 화면에 undefined 문구 뜨는거 이 스크립트 때문에 생기는 현상  그래서 try catch 로 묶음 -->
			<script>
				try {
					if(__ws__(__NSID__));
				}catch (exception){

				}
			</script>
		</div>
		<div id="control_box"></div>
		<script>
			cb = new controlbox();
			cb.setwidth("${activeWidth}");
			cb.build();
			cb.writeTo("control_box");
		</script>
		<!-- AxPlayer 보안 끝 -->


<script language="JavaScript">
	var isIE = (navigator.appName.indexOf("Microsoft") != -1);
 	var player_ = isIE ? document.all.AxPlayer : document.AxPlayer;
 </script>
</body>
</html>
