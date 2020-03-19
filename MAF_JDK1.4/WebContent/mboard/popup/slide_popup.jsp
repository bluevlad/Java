<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<fmt:setBundle var="m" basename="resources.board" />
<html>
<head>
    <META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=UTF-8">
	<title><fmt:message bundle="${m}" key="title.board.imageview" /></title>
	<link href='<c:url value="/include/style.css"/>' rel="stylesheet" type="text/css"></link>
    <script type="text/javascript" src='<c:url value="/js/sub.common.js"/>'></script>
	<style>
		.div_thumb {
			position: absolute;
			visibility: visible;
			overflow: auto;
			z-index: 5;
			top: 67;
			left: 645;
			width: 125;
			height: 468;
			background-color: #ffffff;
		};
		.div_image {position: absolute; visibility: visible;  overflow: auto; z-index: 5; top: 41; left: 18; width: 601; height: 534;};
		.div_close {position: absolute; visibility: visible; overflow: hidden; z-index: 12; top: 2; left: 720; width: 53; height: 39;};
</style>
<script language='javascript'>
<!--
	function KMM_preloadImages() { //v3.0
	  var d=document; 
	  if(d.images){ if(!d.MM_p) d.KMM_p=new Array();
	  var i,j=d.KMM_p.length,a=KMM_preloadImages.arguments; 
	  for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ 
			d.KMM_p[j]=new Image; 
			d.KMM_p[j++].src=a[i];
		}
	  }
	}
	
	function KMM_swapImage() { //v3.0
	  var i,j=0,x,a=KMM_swapImage.arguments; 
	  document.KMM_sr=new Array; 
	  for(i=0;i<(a.length-2);i+=3) {
	    if ((x=KMM_findObj(a[i]))!=null){
			document.KMM_sr[j++]=x; 
			if(!x.oSrc) x.oSrc=x.src; 
			x.src=a[i+2];
		}
	  }
	}
	
	function KMM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; 
	  if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; 
		n=n.substring(0,p);
	  }
	  if(!(x=d[n])&&d.all) x=d.all[n]; 
	  for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=KMM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}
	function set_img_size(iMain) {
		if (iMain.width > maxWidth) {
			iMain.width = maxWidth;
		}
	}
	var maxWidth = 601;
	var maxHeight = 534;


	function showThumb(){
		var tmbImages = new Array(
		<c:forEach items="${attItems}" var="data" varStatus="status">
			<c:url var="imgUrl" value="/pds/board/${MBOARD.bid}/thumb/${data.real_filename}"/>
			<c:if test="${status.count>1}">,</c:if>'<c:out value="${imgUrl}"/>'
		</c:forEach>
		);
		var bigImages = new Array(	<c:forEach items="${attItems}" var="data" varStatus="status">
			<c:url var="imgUrl" value="/pds/board/${MBOARD.bid}/${data.real_filename}"/>
			<c:if test="${status.count>1}">,</c:if>'<c:out value="${imgUrl}"/>'
		</c:forEach>);
		var sHtml = "";
		for(i=0; i<tmbImages.length; i++) {
			sHtml += '<table cellspacing=0 border=0 border=0 align=center><tr><td>';
			sHtml += '<a href="#"><img src="'+tmbImages[i]+'" border=0 ';
			sHtml += 'onclick="KMM_swapImage(\'viewImage\',\'\',\''+bigImages[i]+'\',1)"></a>';
			sHtml += '</td></tr></table>';
		}	
		var divThm = document.getElementById("thumbDiv");
		if(divThm) {
			divThm.innerHTML = sHtml;
		}
	}
	KMM_preloadImages(<c:forEach items="${attItems}" var="data" varStatus="status">
			<c:url var="imgUrl" value="/pds/board/${MBOARD.bid}/${data.real_filename}"/>
			<c:if test="${seq == data.seq}"><c:set var="curimg" value="${imgUrl}"/></c:if>
			<c:if test="${status.count>1}">,</c:if>'<c:out value="${imgUrl}"/>'
		</c:forEach>);
	function init(){
		showThumb();
		window.resizeTo(800, 650);
	}
//-->	
</script>
</head>
<body background='<c:out value="${MBOARD.CPATH}"/>/popup/images/photo_bg.gif' leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" style="overflow: hidden; background-repeat: no-repeat;" onLoad="init()">
<div id="thumbDiv"  class="div_thumb"></div>
<div id="ImageDiv" class="div_image"><table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center" valign="middle"><img src='<c:out value="${curimg}"/>' alt="" name="viewImage" id="viewImage" border="0" style="cursor:pointer;" onload="set_img_size(this)"></td>
	</tr>
</table></div>          
<div id="closeDiv" class="div_close"><img src='<c:out value="${MBOARD.CPATH}"/>/popup/images/photo_close.gif' alt="" width="53" height="39" border="0" style="cursor: pointer;" onClick="self.close()"></div>
</body>
</html>
<script language='javascript'>
<!--






//-->
</script>