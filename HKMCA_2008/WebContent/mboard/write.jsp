<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
<jsp:include page="/WEB-INF/layout/common/commonHead.jsp" flush="true"/> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0"  >
<div id="div_r1">
    <jsp:include  page="/WEB-INF/layout/common/menu_top.jsp" flush="true"/> 
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td valign="top" width="180"><div id="div_leftMenu"><jsp:include page="/WEB-INF/layout/common/menu_left.jsp" flush="true"/></div></td>
        <td valign="top">
            <div id="div_contents_container">
                <div id="div_title_container">
                    <div class="title"><mh:out value="${MAF_INFO.title}"/></div>
                    <div class="help"><img src='<c:url value="/maf_images/button_icon/search.gif"/>' border="0" onclick="popHelp('<mh:out value="${MAF_INFO.pgid}"/>');"/></div>
                    <c:import url="/WEB-INF/layout/common/st02_sub_navi.jsp">
                        <c:param name="mid" value="${content.mid}"/>
                    </c:import>
                </div>
                <div id="div_contents">
                    <c:catch var="error">
                    <c:if test="${!empty(MBOARD.board.file_header)}">
                        <BASE HREF="<mh:CPATH/>/user/board/">
                        <c:import url="/user/board/${MBOARD.board.file_header}" />
                    </c:if>
                        <mh:import url="${MBOARD.PATH}/inc/c_write.jsp" />
                    <c:if test="${!empty(MBOARD.board.file_footer)}">
                        <BASE HREF="${CPATH}/user/board/">
                        <mh:import url="/user/board/${MBOARD.board.file_footer}" />
                    </c:if>                    
                    </c:catch>
                    <c:if test="${!empty error}">
                        <hr/>Error:<mh:out value="${error}" nl2br="true"/><hr/>
                    </c:if>
                </div>
            </div>
        </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/common/commonTail.jsp" flush="true"/> 
</body>
</html>

<script language="javascript">
function popHelp(pgid) {
    var theURL = '/xadmin.common/MenuDesc.do?cmd=view&pgid='+pgid;
    var winName = '_help';
    popupWindow(theURL, winName, "Y");
}
</script>