<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="miraenet.AppConfig" %>
<script language="javascript"   src="${CPATH}/js/lib.validate.js"></script>
<%
    // 웹 브라우저가 캐쉬하지 못하도록 헤더를 세팅하는 스크립틀릿
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    if(request.getProtocol().equals("HTTP/1.1")) {
        response.setHeader("Cache-Control", "no-cache");
    }
%>
<%        
    //String utype = CookieUtil.getValue(request, Header.TYPE);   // 권한
    //int user_role=Cd2String.getUserLevel(utype);
%>
<c:set var="user_role" value="45"/>
<c:set var="page_url" value="${citem.launch_data}"/>
<c:set var="cntserver" value=""/>
<c:choose>
  <c:when test="${cnt_type == 'J'}">
    <c:set var="cntserver" value="<%=AppConfig.CONTENTS_SERVER2%>"/>
  </c:when>
  <c:otherwise>
    <c:set var="cntserver" value="<%=AppConfig.CONTENTS_SERVER%>"/>
  </c:otherwise>
</c:choose>
<c:set var="contents_url" value="${cntserver}${page_url}"/>
<html>
<head>
<title><mfmt:message bundle="common" key="classroom.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script language="javascript">
<!--
//document.domain="sunwlc.co.kr"
<mfmt:message var="m1" bundle="common.scripts" key="script.classroom.right.button.not.use"/>
<mfmt:message var="m2" bundle="common.scripts" key="script.classroom.button.not.use"/>

function ClickHandler() {    
    var e = window.event;
    if (e.button == 2 || e.button == 3 || e.keyCode == 93) {
        window.alert('<mh:out value="${m1}" escapeJS="true"/>');
    } else if (e.ctrlKey || e.shiftKey || e.altKey) {
        window.alert('<mh:out value="${m2}" escapeJS="true"/>');
    }   
}
//document.onmousedown = ClickHandler;
//document.onkeydown = ClickHandler;
-->
</script>
<c:if test="${empty page_url}">
<script language="JavaScript">
<!--
var msg1 = "<mfmt:message bundle="common" key="classroom.title"/>\n";
msg1 +=    "<mfmt:message bundle="common" key="classroom.line.bar"/>\n";
msg1 +=    "<mfmt:message bundle="common" key="script.classroom.not.found.contents"/>\n";
msg1 +=    "<mfmt:message bundle="common" key="classroom.line.bar"/>";
alert(msg1);
self.close();
-->
</script>
<%   //return; %>
</c:if>
<c:if test="${user_role >= 0 && user_role < 49}">
<script language="javascript">
<!--
var isFirst;
var isAttendance=0; // 출석포함 저장카운터 (1인경우만 저장수행)
var save_interval="<%=AppConfig.LECTURE_SAVE_INTERVAL_TIME%>";

function startclock() {
    window.setTimeout("startclock()", (eval(save_interval) *( 1000*60) ) );  //분마다 Update한다
    if(isFirst == false) {
        isFirst = true;
    } else {
        ++isAttendance;
        saveTime('M');
    }
}
// 자동저장(S:최초, M:중간, E:종료)
function saveTime(flag) {
    if(flag == "S") {
        isFirst = false;
    }
    var srvXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

    // 종료
    if(flag=='E') {
        srvXmlHttp.Open("GET","${control_action}?cmd=save_time&insflag=E&isAttendance="+isAttendance+"&leccode=${leccode}&lecnumb=${reqnumb}&itm_id=${citem.itm_id}&userid=${userid}&total_time="+save_interval, false);
        srvXmlHttp.send();
        srvXmlHttp = null;  
        //opener.top.mainFrame.location.reload();
        opener.top.location.reload();
    } else {
        srvXmlHttp.Open("GET","${control_action}?cmd=save_time&insflag="+flag+"&isAttendance="+isAttendance+"&leccode=${leccode}&lecnumb=${reqnumb}&itm_id=${citem.itm_id}&userid=${userid}&total_time="+save_interval, false);
        srvXmlHttp.send();
        srvXmlHttp = null;
        //opener.top.mainFrame.location.reload();
        opener.top.location.reload();
    }
}
function pageinit() {
    saveTime('S');
    startclock();
}
function destroy() {
    saveTime('E');
}
window.onload = pageinit;
window.onbeforeunload = destroy;
-->
</script>
</c:if>
</head>
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<!--
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 bgcolor=#000000 oncontextmenu="return false" ondragstart="return false" onselectstart="return false">
-->
<table width=100% height=100% border=0 cellspacing=0 cellpadding=0>
    <tr height=100%>
        <td width=100% height=100%>
            <iframe id="Viewer_Frame" width=100% height=100% frameborder=0 scrolling=no src="${contents_url}" marginwidth=0 marginheight=0></iframe>
        </td>
    </tr>
</table>
</body>
</html>
