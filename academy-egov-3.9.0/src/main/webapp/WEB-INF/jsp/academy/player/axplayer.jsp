<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<c:if test="${empty USER_ID}">
	<script>
		alert("잘못된 접근입니다.");
		self.close();
	</script>
</c:if>
<link href="<c:url value="/resources/css/player/style.css" />" rel="stylesheet" type="text/css"  />
<script language="JavaScript" type="text/JavaScript"> 
window.onload = function(){
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
		parent.window.resizeBy(-270,0);
		setyn = "Y";
	}
	if(frameCheck == "open"){
			parent.document.all("fmRight").width = 0; 
			if (550 < xo-260)
			{
				parent.window.resizeBy(-270,0);
			}else{
				parent.window.resizeBy(960-xo,0);
			}
				document.all.viewBtn.src = "/resources/images/player/btn2.jpg";
				parent.document.frmMain.frmCheck.value = "close";
	}else{	
			var fsize = parent.document.frmMain.frmSize.value;
			parent.document.all("fmRight").width =260;			
			parent.window.resizeBy(0,0);
			xo=parent.document.body.offsetWidth; 
			if (xo < 1010)
			{
				parent.window.moveBy(0,0);
				parent.window.resizeBy(960-xo,0);
			}
			document.all.viewBtn.src = "/resources/images/player/btn2.jpg";
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

function close_unload() {
	document.fmLeft.movie_unload();	
}
//-->
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
<table border="0" bgcolor="#000000" style="line-height:0;font-size:0; ">
    <tr>
        <td width="590" height="38" bgcolor="#1a1a1a">
            <img src="/resources/images/player/dot.jpg" align="absmiddle">
            <font color="#ffffff">
            		<strong><b>${TITLE}</b></strong>
            </font>
        </td>
        <td width="150" bgcolor="#1a1a1a">
          	   <a href="#">
          	   		<img src="/resources/images/player/btn1.jpg" align="absmiddle"  style="cursor:hand;" onclick="toggle_A('resumelist');">
          	   </a>
          	   <div id="resumelist" style="position: absolute; left: expression(540); top: expression(40); display:none; zIndex:9999;"> 
	 				<iframe name="level" id="level" src="/resources/images/player/dan.jpg" height="266" width="65"  border="0" frameBorder="0" frameSpacing="0" marginHeight="0" marginWidth="0" scrolling="no"></iframe>
				</div>
          	   <a href="#">
          	   		<img src="/resources/images/player/btn2.jpg" align="absmiddle" style="cursor:hand;" onClick="frameControl();" name="viewBtn">
        	   </a> 
        </td>
        <td bgcolor="#1a1a1a" width="126">
            <p>
            	<img src="/resources/images/player/title.jpg" align="absmiddle">
            </p>
        </td>
        <td bgcolor="#1a1a1a" align="right">
               <img src="/resources/images/player/btn3.jpg" align="absmiddle" style="cursor:hand;" onclick="javascript:Close();" >
        </td>
    </tr>
    <tr bgcolor="#000000">
         <td rowspan="2" align="center" colspan="2" width="675" valign="top" bgcolor="#000000">
			<!-- 플레이어 들어오는 곳 -->
			<iframe name="fmLeft" id="fmLeft" scrolling="no" width='680' height='455' src="/player/view_left.pop2?LECCODE=${params.LECCODE}&BRIDGE_LECCODE=${params.BRIDGE_LECCODE}&MOVIE_NO=${params.MOVIE_NO}" frameborder=0 marginwidth="0" marginheight="0" border="0" noresize="no" style="margin: 0"></iframe>
		</td>
         <td colspan="2" align="center"  style="padding:5px;" valign="top" align="center"  bgcolor="#000000">
            <table>
	            <tr height="350" valign="top">
	            	<td valign="top">
						<!-- 강의목록 나오는 곳 -->
		            	<iframe name="fmRight" id="fmRight"  scrolling="no" width='290' height='330' src="/player/view_right.pop2?LECCODE=${params.LECCODE}&BRIDGE_LECCODE=${params.BRIDGE_LECCODE}&MOVIE_NO=${params.MOVIE_NO}&PAGE=1&position=1&samplecount=1" frameborder=0 scrolling="no" marginwidth="0" marginheight="0" border="0" noresize="0" ></iframe>	
		            </td>
		         </tr>
		         <tr>
		         	<td valign="bottom">
	         			 <a href="/servlets/C_WMainServlet?cmd=mainlink1" target="_blank"> <!-- 20130128 링크 자주하는질문 으로 변경 -->
	   						<img src="/resources/images/player/help.jpg" >
	   					</a>
		         	</td>
		         </tr>
	         </table>
        </td>
    </tr>
</table>
<div id="layer_file_popup" style="position:absolute;left:expression(720); top: expression(150); display:none"> 	
	<table border="1"  cellspacing="0" cellpadding="0" style="background-color:#F8F8FF";>
			<tr height="20">
			 	<td align="right">
			 		<a href="#" onClick="NotFile_close();" title="닫기">
			 			<b>X&nbsp;&nbsp;&nbsp;</b>
			 		</a>
			 	</td>
			</tr>
			<tr height="50">
			 	<td>
		 		 	<font size="2" color="red">
		 		 		<b>
		 					해당 파일이 존재하지 않습니다.
		 				</b>
		 			</font>
			 	</td>
			</tr>
	</table>
</div>
<input type=hidden name="oCheckWin">
</body> 
</html>
