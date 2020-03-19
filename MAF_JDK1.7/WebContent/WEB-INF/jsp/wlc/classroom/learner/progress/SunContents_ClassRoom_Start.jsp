<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="miraenet.AppConfig" %>
<%
    //String utype = CookieUtil.getValue(request, Header.TYPE);   // 권한
    //int user_role=Cd2String.getUserLevel(utype);
%>
<c:set var="user_role" value="45"/>
<c:set var="page_url" value="${citem.launch_data}"/>
<c:set var="cntserver" value="<%=AppConfig.CONTENTS_SERVER2%>"/>
<c:set var="contents_url" value="${cntserver}${page_url}"/>
<c:set var="cnt_width" value="1024"/>
<c:choose>
  <c:when test="${!empty citem.cnt_width && cnt_width > 1}">
    <c:set var="cnt_width" value="${citem.cnt_width}"/>
  </c:when>
  <c:otherwise>
    <c:set var="cnt_width" value="1024"/>
  </c:otherwise>
</c:choose>
<c:set var="cnt_height" value="768"/>
<c:choose>
  <c:when test="${!empty citem.cnt_height && citem.cnt_height > 1}">
    <c:set var="cnt_height" value="${citem.cnt_height}"/>
  </c:when>
  <c:otherwise>
    <c:set var="cnt_height" value="768"/>
  </c:otherwise>
</c:choose>
<html>
<head>
<title><mfmt:message bundle="common" key="classroom.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script language="javascript">
<!-- 
window.ondragstart=false;
window.oncontextmenu=false;
window.onselectstart=false;

function formact() {
    window.resizeTo(${cnt_width},${cnt_height});
}
function ClickHandler() {    
    var e = window.event;
    if (e.button == 2 || e.button == 3 || e.keyCode == 93) {
        window.alert("<mfmt:message bundle="common.scripts" key="script.classroom.right.button.not.use"/>");
    //} else if (e.ctrlKey || e.shiftKey || e.altKey) {
    } else if (e.ctrlKey ) {
        window.alert("<mfmt:message bundle="common.scripts" key="script.classroom.button.not.use"/>");
    }   
}
document.onmousedown = ClickHandler;
document.onkeydown = ClickHandler;
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
<%  //return; %>
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
        srvXmlHttp.Open("GET","${control_action}?cmd=sun_save_time&insflag=E&isAttendance="+isAttendance+"&leccode=${leccode}&lecnumb=${reqnumb}&itm_id=${citem.itm_id}&userid=${userid}&total_time="+save_interval, false);
        srvXmlHttp.send();
        srvXmlHttp = null;  
        opener.top.location.reload();
        self.close();
    } else {
        srvXmlHttp.Open("GET","${control_action}?cmd=sun_save_time&&insflag="+flag+"&isAttendance="+isAttendance+"&leccode=${leccode}&lecnumb=${reqnumb}&itm_id=${citem.itm_id}&userid=${userid}&total_time="+save_interval, false);
        srvXmlHttp.send();
        srvXmlHttp = null;  
        opener.top.location.reload();
    }
}
function pageinit() {
    formact();
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

<frameset rows="25,*" frameborder="no" border="0" framespacing="0" cols="*">
    <frame name="topFrame" scrolling="no" noresize src="${CPATH}/jsp/wlc/learner/learning/progress/lec_chp/top_frame.jsp" frameborder="no" noresize>
    <frame name="Viewer_Frame" src="${contents_url}" frameborder="no" scrolling="no" noresize frameborder="no" oncontextmenu="return false" ondragstart="return false" onselectstart="return false">
</frameset>

<noframes>
    <body bgcolor="#FFFFFF" text="#000000">
        <font color=red size=2> ▣ This page is frameset... ▣ </font>
    </body>
</noframes>

</body>
</html>
