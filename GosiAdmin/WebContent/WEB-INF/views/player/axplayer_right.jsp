<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<link href="<c:url value="/resources/css/player/style.css" />" rel="stylesheet" type="text/css"  />
<style>
.paginate_complex{padding:20px 0;line-height:normal;text-align:center; float:left; width:100%;}
.paginate_complex a,
.paginate_complex strong{display:inline-block;position:relative;z-index:2;margin:0 -3px;padding:1px 8px;border-left:1px solid #d6d6d6;border-right:1px solid #d6d6d6;background-color:#fff;font:bold 12px/16px Gulim, Tahoma, Sans-serif;color:#323232;text-decoration:none;vertical-align:top}
.paginate_complex a:hover,
.paginate_complex a:active,

.paginate_complex strong{color:#ff8600}
.paginate_complex .direction{border:0;font-weight:normal;color:#767676;text-decoration:none !important;z-index:1}
.paginate_complex .direction:hover,
.paginate_complex .direction:active,
.paginate_complex .direction:focus{color:#323232;background-color:#fff}
.paginate_complex .prev{border-left:0}
.paginate_complex .next{border-right:0}
.paginate_complex .direction span{display:inline-block;position:relative;top:4px;width:0;height:0;font-size:0;line-height:0;vertical-align:top}
.paginate_complex .prev span{*left:-4px;margin-right:1px;border:3px solid;border-top:solid #fff;border-bottom:solid #fff;border-left:0}
.paginate_complex .next span{margin-left:1px;border:3px solid;border-top:solid #fff;border-bottom:solid #fff;border-right:0}

.paginate_complex a {background:#000000; color:#FFFFFF;}
</style>
<SCRIPT LANGUAGE="JavaScript" >
<!--
 function go(movie_no,i){
	 var i = i ;
	 var bgcolor = document.myform.bgcolor.value;
	 if(bgcolor !=''){
		 document.getElementById(bgcolor).style.background = "";
	 }
	 document.getElementById(i).style.background = "#4682B4";
	 document.myform.bgcolor.value = i;
	 document.myform.movie_no.value = movie_no;
	 document.pageform.movie_no.value = movie_no;
	 document.myform.submit();
	 document.pageform.submit();
 }
 
 function Default_bgcolor(i){
	 var i = i ;
	 var bgcolor = document.myform.bgcolor.value;
	 if(bgcolor !=''){
		 document.getElementById(bgcolor).style.background = "";
	 }
	 document.getElementById(i).style.background = "#4682B4";
	 document.myform.bgcolor.value = i;
 }
 //-->
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
    //풍선 도움말 소스
    var nav = (document.layers);
    var iex = (document.all);
    function popHelp(msg,bak,width,titlemsg) {
         if(msg == "") return;
         var skn = document.all.topdecks;
         var content ="<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=1 BGCOLOR=#505050 style='width:200px;'><TR><TD>"+"<TABLE cellpadding=0 cellspacing=0 BGCOLOR="+bak+" style='width:200px;'><TR><TD style='width:200px;' style='padding:5;'><FONT COLOR=#000000><nobr>"+msg+"</nobr></FONT></TD></TR></TABLE></TD></TR></TABLE>";
         var content = "<div style='border: 1px solid #00006F; background-color:#E0FFFF; width:230px; padding:5px;'> <strong>"+msg+"</strong></div>"
         skn.innerHTML = content;
         skn.style.display = "";
    }
    function get_mouse(e) {
            var skn = document.all.topdecks;
            var x = (nav) ? e.pageX : event.x+document.body.scrollLeft;
            var y = (nav) ? e.pageY : event.y+document.body.scrollTop;
            skn.style.left = x - 50;
            skn.style.top  = y + 10;
    }
    function kill() {
                    var skn = document.all.topdecks;
                    skn.style.display = "none";
    }
    document.onmousemove = get_mouse;       
//-->
</SCRIPT>
</head>
<body bgcolor="0000000">
<form name=myform method="post" action="" target="fmLeft" style="margin:0" >
<input type="hidden" name="orderno" value="">
<input type="hidden" name="leccode" value="${params.LECCODE}">
<input type="hidden" name="samplecount" value="">
<input type="hidden" name="movie_no" >
<input type="hidden" name="bgcolor" id="bgcolor">	
	
<table style="width:100%; height:28" border="0" cellpadding="0" cellspacing="0" bordercolordark="#282828" bordercolorlight="#282828" align="center" bgcolor="323232">
	<tr valign="middle">
		<td width="85" align="center">
			<font color="#c8c8c8">회차</font>
		</td>
		<td width="150" align="center">
			<font color="#c8c8c8">강의목록</font>
		</td>
		<td width="50" align="center">
			<font color="#c8c8c8">자료</font>
		</td>
	</tr>
</table>
<table border="0" cellspacing="0" cellpadding="0"  bgcolor="000000" align="center" style="width:100%;height:305;">
<div id="topdecks" name="topdecks" style="position : absolute;display:none;"></div>

	<c:forEach items="${list}" var="item" varStatus="loop">
		<c:if test="${item.MOVIE_NO eq params.MOVIE_NO}">
			<tr style='cursor:pointer;background:#4682B4;' id="${loop.index}" class="ltr">
		</c:if>
		<c:if test="${item.MOVIE_NO ne params.MOVIE_NO}">
			<tr style='cursor:pointer;' onClick="fn_DoMovie('${item.MOVIE_NO}', '${loop.index}')" id="${loop.index}" class="ltr">
		</c:if>
			<td align="center" valign="bottom" width="85" height="26">${item.MOVIE_ORDER1}회 ${item.MOVIE_ORDER2}강</td>
			<td width="150" valign="bottom" align="left">
				<a href="javascript:fn_DoMovie('${item.MOVIE_NO}', '${loop.index}')">${item.MOVIE_NAME}</a>	
			</td>
			<td width="50" valign="bottom" align="center">
				<c:if test="${!empty item.MOVIE_DATA_FILENAME and item.MOVIE_DATA_FILE_YN eq 'Y'}">
					<img src="/resources/images/player/file.jpg" align="absmiddle"  style="cursor:hand;" onclick="fn_FileDownload('${item.MOVIE_DATA_FILENAME}');">
				</c:if>
			</td>			         			
		</tr>		
	</c:forEach>

 </table>
 
 	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
 
</form>
<form name=myform method="post" action="" target="fmLeft" style="margin:0" >
	<input type="hidden" name="orderno" value="">
	<input type="hidden" name="leccode" value="">
	<input type="hidden" name="samplecount" value="">
	<input type="hidden" name="movie_no" >
	<input type="hidden" name="bgcolor" id="bgcolor">	
</form>
<form id="pageform" name="pageform"  method="post" action="" target="fmRight" style="margin:0">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.currentPage}">
<input type="hidden" name="LECCODE" value="${params.LECCODE}">
<input type="hidden" name="BRIDGE_LECCODE" value="${params.BRIDGE_LECCODE}">
<input type="hidden" name="MOVIE_NO" value="${params.MOVIE_NO}">
</form>

<script language="JavaScript">
//페이징
function goList(page) {
	url = "/player/view_right.pop2?pageRow=${pageRow}&LECCODE=${params.LECCODE}&BRIDGE_LECCODE=${params.BRIDGE_LECCODE}&MOVIE_NO=${params.MOVIE_NO}&currentPage="+page;
	$(parent.document).find("#fmRight").attr("src", url);	
	
}

function fn_DoMovie(mno, i){
	$(".ltr").css("background","");
	document.getElementById(i).style.background = "#4682B4";	
	url = "/player/view_left.pop2?LECCODE=${params.LECCODE}&BRIDGE_LECCODE=${params.BRIDGE_LECCODE}&MOVIE_NO=" + mno;
	$(parent.document).find("#fmLeft").attr("src", url); 
}

//파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
}
</script>
</body>
