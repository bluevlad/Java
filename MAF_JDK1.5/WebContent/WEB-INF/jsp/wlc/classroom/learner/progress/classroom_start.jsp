<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    if(request.getProtocol().equals("HTTP/1.1")) {
        response.setHeader("Cache-Control", "no-cache");
    }
%>
<c:set var="user_role" value="45"/>
<c:set var="page_url" value="${citem.launch_data}"/>
<c:set var="cntserver" value="${cntserver}"/>
<c:set var="cntserver" value="http://kca.kia.co.kr"/>
<c:set var="contents_url" value="${cntserver}${page_url}"/>
<c:set var="contents_url" value="${page_url}"/>
<html>
<head>
<title><mfmt:message bundle="common" key="classroom.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src='<c:url value="/js/sub.common.js"/>'></script>
<script language="javascript">
<!--
//document.domain="hkmca.co.kr"
// 경로 및 파라미터를 생성.xxx
var LMSServer   = 'kca.kia.co.kr'; //'<mh:out value="${lmsserver}"/>';   
var LMSExecName = '<mh:out value="${lmsservlet}"/>';
var lectureCD   = '<mh:out value="${leccode}"/>';
var reqnumb     = '<mh:out value="${reqnumb}"/>';
var userid      = '<mh:out value="${studentid}"/>';
var item_id     = '<mh:out value="${itm_id}"/>';

<mfmt:message var="m1" bundle="common.scripts" key="script.classroom.right.button.not.use"/>
<mfmt:message var="m2" bundle="common.scripts" key="script.classroom.button.not.use"/>
function ClickHandler() {    
    var e = window.event;
    if (e.button == 2 || e.button == 3 || e.keyCode == 93) {
        window.alert("<mh:out value="${m1}" escapeJS="true"/>");
    } else if (e.ctrlKey || e.shiftKey || e.altKey) {
        window.alert("<mh:out value="${m2}" escapeJS="true"/>");
    }   
}

document.onmousedown = ClickHandler;
document.onkeydown = ClickHandler;
//-->
</script>
<c:if test="${empty page_url}">
<script language="JavaScript">
<!--

var msg1 = "<mfmt:message bundle="common" key="classroom.title"/>\n";
msg1 +=    "<mfmt:message bundle="common" key="classroom.line.bar"/>\n";
msg1 +=    "<mfmt:message bundle="common" key="script.classroom.not.found.contents"/>\n";
msg1 +=    "<mfmt:message bundle="common" key="classroom.line.bar"/>";
alert(msg1);
//self.close();
//-->
</script>
<%
//return;
%>
</c:if>
<script language="javascript">
<!--
var isFirst;
var isAttendance=0; // 출석포함 저장카운터 (1인경우만 저장수행)
var save_interval="<mh:out value="${save_interval}"/>";

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
    
    var srvXmlHttp = newXMLHttpRequest();
    //var srvXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    // 종료
    if(flag=='E') {
        var sendParameter   = "?cmd=save_time"
                            + "&insflag=E" 
                            + "&isAttendance="+isAttendance
                            + "&leccode="   +lectureCD
                            + "&lecnumb="   +reqnumb
                            + "&userid="    +userid
                            + "&itm_id=<mh:out value="${citem.itm_id}"/>"
                            + "&total_time="+save_interval;
        
        var endUrl     = LMSExecName+sendParameter;
        var e;
        try {
            srvXmlHttp.open("GET", endUrl, false);
            srvXmlHttp.send(null);
            srvXmlHttp = null;  
        }
        catch( e )
        {
        }
        //opener.top.mainFrame.location.reload();
        //opener.top.location.reload();
        //opener.location.reload();
    } else {
        var sendParameter   = "?cmd=save_time"
                            + "&insflag="   +flag
                            + "&isAttendance="+isAttendance
                            + "&leccode="   +lectureCD
                            + "&lecnumb="   +reqnumb
                            + "&userid="    +userid
                            + "&itm_id=<mh:out value="${citem.itm_id}"/>"
                            + "&total_time="+save_interval;
        
        var firstUrl     = LMSExecName+sendParameter;
        var e;
        try {
            srvXmlHttp.open("GET", firstUrl, false);
            srvXmlHttp.send(null);
            srvXmlHttp = null;  
        }
        catch( e )
        {
        }
        //opener.top.mainFrame.location.reload();
        //opener.top.location.reload();
    }
}

function pageinit() {
    saveTime('S');
    startclock();
}
function destroy() {
    saveTime('E');
}
addEvent(window,'load',function() {pageinit()});
this.window.onbeforeunload = destroy;

/**
* 현재학습페이지 LMS Server로 진도정보 Send
*
* @param page_seq_no 페이지순서번호
* @param page_flag 학습처리상태 [N : 미처리, Y:학습처리완료]
* @param page_nm 현재페이지 파일명[전체경로명 포함하면 더욱좋음]
*/
function sendProgressToLMS(page_seq_no, page_flag, page_nm)
{

    var sendParameter   = "?cmd=save_page"
                        + "&leccode="   +lectureCD
                        + "&lecnumb="   +reqnumb
                        + "&userid="    +userid
                        + "&itm_id="    +item_id
                        + "&page_no="   +page_seq_no
                        + "&page_flag=" +page_flag
                        + "&page_nm="   +page_nm;
    
    var sendUrl     = LMSExecName+sendParameter;    
    var e;

        var srvXmlHttp = newXMLHttpRequest();

        srvXmlHttp.open("GET", sendUrl, false);
        srvXmlHttp.send(null);      
        srvXmlHttp = null;  

}

/**
* 현재학습페이지 LMS Server로 진도정보 Send
* @param act last:단원 마지막
*/
function sendLastProgressToLMS(act)
{

    var sendParameter   = "?cmd=danwon"
                        + "&insflag=E"
                        + "&isAttendance=0"
                        + "&leccode="   +lectureCD
                        + "&lecnumb="   +reqnumb
                        + "&userid="    +userid
                        + "&itm_id="    +item_id
                        + "&act_move="  +act;
                        
    var lastUrl     = LMSExecName+sendParameter;
    var e;
    try {
        var srvXmlHttp = newXMLHttpRequest();
        //var srvXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        srvXmlHttp.open("GET", lastUrl, false);
        srvXmlHttp.send();
        srvXmlHttp = null; 
    }
    catch( e )
    {
    }
}

/**
* LMS Server로 단원이동요청 Send
*
* @param act prev:이전단원, next: 다음단원
*/
function sendMoveToLMS(act)
{

    // 현재단원 최종학습정보 저장
    var sendParameter   = "?cmd=danwon"
                        + "&insflag=E"
                        + "&isAttendance=0"
                        + "&leccode="   +lectureCD
                        + "&lecnumb="   +reqnumb
                        + "&userid="    +userid
                        + "&itm_id="    +item_id
                        + "&act_move="  +act;
                        
    var finishUrl     = LMSExecName+sendParameter;
    var e;
    try {
        var srvXmlHttp = newXMLHttpRequest();

        srvXmlHttp.open("GET", finishUrl, false);
        srvXmlHttp.send();
        srvXmlHttp = null; 
    }
    catch( e )
    {
    }
    
    var sendParameter   = "?cmd=danwon"
                        + "&leccode="   +lectureCD
                        + "&lecnumb="   +reqnumb
                        + "&userid="    +userid
                        + "&itm_id="    +item_id
                        + "&act_move="  +act;
                        + "&isok=Y";
    
    var sendUrl     = LMSExecName+sendParameter;
    parent.parent.parent.location.href = sendUrl;
}

/**
* LMS Server로 단원평가결과 점수정보 Send
*
* @param exam_score 단원평가결과 점수
*/
function sendScoreToLMS(exam_score)
{

    var sendParameter   = "?cmd=save_score"
                        + "&leccode="   +lectureCD
                        + "&lecnumb="   +reqnumb
                        + "&userid="    +userid
                        + "&itm_id="    +item_id
                        + "&test_score="+exam_score;
    
    var sendUrl = LMSExecName+sendParameter;
    var e;
    try {
        var srvXmlHttp = newXMLHttpRequest();
        //var srvXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        srvXmlHttp.open("GET", sendUrl, false);
        srvXmlHttp.send();
        srvXmlHttp = null; 
    }
    catch( e )
    {
    }
    
}

/*
* 크로스 브라우저 XMLHttpRequest 구현하기
* Returns a new XMLHttpRequest object, or false if this browser
* doesn't support it
* from : dbguide.net
*/
function newXMLHttpRequest() {
    /* Create a new XMLHttpRequest object to talk to the Web server */
    var xmlHttp = false;
    /*@cc_on @*/
    /*@if (@_jscript_version >= 5)
    try {
      xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        xmlHttp = false;
      }
    }
    @end @*/
    
    if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
      xmlHttp = new XMLHttpRequest();
    }
	
	return xmlHttp;
}
-->
</script>
</head>
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<!--
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 bgcolor=#000000 oncontextmenu="return false" ondragstart="return false" onselectstart="return false">
-->
<table width=100% height=100% border=0 cellspacing=0 cellpadding=0>
    <tr height=100%>
        <td width=100% height=100%>
            <iframe id="Viewer_Frame" width=100% height=100% frameborder=0 scrolling=no src="<mh:out value="${contents_url}"/>" marginwidth=0 marginheight=0></iframe>
        </td>
    </tr>
</table>
</body>
</html>
