<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
<fmt:setBundle var="m" basename="resources.board" />
<script language="JavaScript1.2" src="${CPATH}/js/lib.validate.js"></script>
<link href="${MBOARD.CPATH}/css/board_01.css" rel="stylesheet" type="text/css"></link>
<!--script type="text/javascript" src="${CPATH}/FCKeditor/fckeditor.js"></script-->
<script type="text/javascript">
/*
window.onload = function()
{
	var sBasePath ='${CPATH}/FCKeditor/' ;

	var oFCKeditor = new FCKeditor( 'c_content' ) ;
	oFCKeditor.BasePath	= sBasePath ;
	oFCKeditor.ReplaceTextarea() ;
}
*/
	_editor_url = "${MBOARD.CPATH}/mHtmlArea/";                     // URL to htmlarea files
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	if (navigator.userAgent.indexOf('Mac')        >= 0) { win_ie_ver = 0; }
	if (navigator.userAgent.indexOf('Windows CE') >= 0) { win_ie_ver = 0; }
	if (navigator.userAgent.indexOf('Opera')      >= 0) { win_ie_ver = 0; }
	if (win_ie_ver >= 5.5) {
		document.write('<scr' + 'ipt src="' +_editor_url+ 'editor.js"');
		document.write(' language="Javascript1.2"></scr' + 'ipt>');  
	} else { 
		document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); 
	}

</script>
<script language="JavaScript1.2" src="${MBOARD.CPATH}/mHtmlArea/extend/uploadimage.js"></script>
<form action="act.do" method="post" name="writeform" id="writeform" enctype="multipart/form-data" onSubmit="return validate(this)">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bbs_table">
	<tr>
		<td class="bbs_view_top_th" width="80"><fmt:message bundle="${m}" key="label.writer" /></td>
<c:choose>	
	<c:when test="${empty(sessionScope.msession)}">
		<td class="bbs_view_top_td" width="496"><input type="text" name="wname" value="${item.wname}" readonly HNAME="이름" MAXBYTE="20"></td>
	</c:when>
	<c:otherwise>
		<td class="bbs_view_top_td" width="496">${sessionScope.msession.nm}</td>
	</c:otherwise>
</c:choose>		
	</tr>
	<tr class="td">
		<td class="bbs_view_th"><fmt:message bundle="${m}" key="label.title" /></td>
		<td ><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="bbs_view_td" height=30>
					<input type="text" size="40" maxbyte="100" name="c_subject" value="${item.c_subject}" required hname="제목" ></td>
				<td class="bbs_view_th" width="80" align="right"><fmt:message bundle="${m}" key="title.board.burru" />&nbsp;</td>
				<td class="bbs_view_th" >
					<select name="v_category" onchange="javascript:doSelcat(this);">
						<option value=""><fmt:message bundle="${m}" key="title.board.burruwire" /></option>
					<c:forTokens var="cat" items="${MBOARD.board.category}" delims="," >
						<option value='${cat}' ${v_srch == cat?'selected':''}>${cat}</option>
					</c:forTokens>
					</select>
				</td>
				<td class="bbs_view_td"><INPUT name="category" id="category" value="${item.c_category}" style="" size="10"  maxbyte="10" hname="카테고리" ></td>
			</tr>
			</table></td>
	</tr>
	<tr >
		<td colspan="2" class="bbs_view_td"><span class="bbs_view_th"><fmt:message bundle="${m}" key="label.contents"/>: max  25,000char</span><br>
			<textarea cols="68" rows="25" name="c_content" id="c_content" maxbyte="25000"
				class="bbs_text_area" style="WIDTH: 95%; HEIGHT: 250px"  hname="내용"
				 >${fn:escapeXml(item.c_content)}</textarea>
			<c:if test="${MBOARD.board.fl_html == 'T'}" >
				<!------- START :: 위지위그 에디트 관련 스크립트 -------> 
				<script language="javascript1.2">
				editor_generate('c_content');
				</script>
				<!------- END :: 위지위그 에디트 관련 스크립트 -------> 
			</c:if>
			
		</td>
	</tr>
	<c:if test="${MBOARD.board.number_attach > 0 }" >
		<c:import url="/mboard/inc/write.addImage.inc.jsp"/>
	</c:if>

	<c:if test="${!empty(attItems)}" >
	<tr class="td" >
		<td class="th" colspan="2">사용중인 파일: <font color="#3B3B3B">기존 등록한 파일 중 삭제할 파일은 체크하세요.</font></td>
	</tr>
	<tr>
		<td colspan="2" class="td">
			<div style="width:630px;height:100px;overflow:auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="1">
			<tr>
			<c:forEach items="${attItems}" var="data">
				<c:if test="${fn:indexOf(data.content_type,'image')>-1}">
				<c:url var="imgUrl" value="/pds/board/${MBOARD.bid}/thumb/${data.real_filename}"/>
	            <td  width="100"><table border="0" cellspacing="0" cellpadding="2">
		            <tr>
						<td align="center" nowrap><input type="checkbox"  name="deleteAttachSeq" value="${data.seq}" style="width:14px; height:14px;border:none;">${data.ori_filename}</td>
					</tr>
					<tr>
						<td align="center"><img src="${imgUrl}" border="0" width="80"></td>
					</tr>
				</table></td>
				</c:if>
			</c:forEach>
			</tr>
		</table></div></td>
	</tr>
	<tr>
		<td colspan=2><table width="100%" border="0" cellspacing="0" cellpadding="1">
			
			<c:forEach items="${attItems}" var="data">
				<c:if test="${fn:indexOf(data.content_type,'image')<0}">
				<tr>
	            	<td><table width="100%" border="0" cellspacing="0" cellpadding="2">
		            <tr>
						<td class="td" nowrap><input type="checkbox"  name="deleteAttachSeq" value="${data.seq}" style="width:14px; height:14px;border:none;"></td>
						<td class="td">${data.ori_filename}</td>
					</tr>
					</table></td>
				</tr>
				</c:if>
			</c:forEach>
		</table></td>
	</td>
	</c:if>
	<tr  >
		<td colspan="2" align="center"><br><img src="${MBOARD.CPATH}/images/button/save.gif" alt="" border="0" align="absmiddle" style="cursor:pointer;" onClick="doSubmit()"></td>
	</tr>
</table>
	<input type="hidden" name="LISTOP" value="${MBOARD.listOpStr}">
	<input type="hidden" name="cmd" value="${cmd}">
	<input type="hidden" name="bid" value="${MBOARD.bid}">
	<c:if test="${!empty(course)}">
		<input type="hidden" name="cor_cd" value="${course.cor_cd}">
	</c:if>	
</form>
<SCRIPT LANGUAGE="JavaScript">
<!--
var onlyoneClick = false;



function doSubmit() {
    var vfrm = document.getElementById("writeform");
    if(validate(vfrm)){
    	if (!onlyoneClick) {
    		onlyoneClick = true;
	    	vfrm.submit();
	    } else {
			alert('<fmt:message bundle="${m}" key="title.board.click" />');		
		}
	}
}

function doSelcat (obj) {
    var txt = document.getElementById("category");
    if(txt && obj) {
    	txt.value = obj.value;
    	if(txt.style) {
		    if (obj.value == "") {
		        txt.style.display = '';
		    }else{
		        txt.style.display = 'none';
		    }
		}
	}
}

doSelcat (document.getElementById("sel_cat"));
//-->
</script>
