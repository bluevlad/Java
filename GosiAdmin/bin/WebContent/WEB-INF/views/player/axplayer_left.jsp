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
		alert("�߸��� �����Դϴ�.");
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
		alert("�߸��� �����Դϴ�.");
	    parent.location.href="/";
	}

	 function click(){
		if ((event.button==2) || (event.button==3) || (event.keyCode == 93)){
			if(window.navigator.browserLanguage != "ko")alert('Can not use right-click of mouse.');
			else alert('���콺 ������ ��ư�� ����Ҽ� �����ϴ�.');
		}
		else{
			if((event.ctrlKey) || (event.keyCode == 67)){
				if(window.navigator.browserLanguage != "ko")alert('Can not copy without permission.');
				else alert('������ ���ܺ����Ͻ� �� �����ϴ�.');
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
		var last_position_time	= player_.getCurrentPosition(); // �̵���� ���� ��� ��ġ ��ȯ return:�̵���� ���� ��� �ð�(��)(�����÷��̾� ����� �̾�� �ϱ����� �ð�)
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
			<!-- ������ ȭ�鿡 undefined ���� �ߴ°� �� ��ũ��Ʈ ������ ����� ����  �׷��� try catch �� ���� -->
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
		<!-- AxPlayer ���� �� -->


<script language="JavaScript">
	var isIE = (navigator.appName.indexOf("Microsoft") != -1);
 	var player_ = isIE ? document.all.AxPlayer : document.AxPlayer;
 </script>
</body>
</html>
