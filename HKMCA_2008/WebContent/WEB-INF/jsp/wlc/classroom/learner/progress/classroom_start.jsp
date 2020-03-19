<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="page_url" value="${citem.launch_data}"/>
<c:set var="cntserver" value="${cntserver}"/>
<c:set var="contents_url" value="${cntserver}${page_url}"/>

<mfmt:message var="m1" bundle="common.scripts" key="classroom.right.button.not.use"/>
<mfmt:message var="m2" bundle="common.scripts" key="classroom.button.not.use"/>
<script language="javascript">
<!--
//document.domain="hkmca.co.kr"
// 경로 및 파라미터를 생성.xxx
var LMSServer = '<mh:out value="${lmsserver}"/>';   
var LMSExecName = '<mh:out value="${lmsservlet}"/>';
var lec_cd = '<mh:out value="${lec_cd}"/>';
var usn = '<mh:out value="${usn}"/>';
var htmcode = '<mh:out value="${htmcode}"/>';

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
                            + "&lec_cd="   +lec_cd
                            + "&usn="    +usn
                            + "&htmcode=<mh:out value="${citem.htmcode}"/>"
                            + "&total_time="+save_interval;
        
        var endUrl = LMSExecName+sendParameter;
        var e;
        try {
            srvXmlHttp.open("GET", endUrl, false);
            srvXmlHttp.send(null);
            srvXmlHttp = null;  
        }
        catch( e )
        {
        }
//        opener.top.mainFrame.location.reload();
        opener.top.location.reload();
        opener.location.reload();
    } else {
        var sendParameter   = "?cmd=save_time"
                            + "&insflag="   +flag
                            + "&isAttendance="+isAttendance
                            + "&lec_cd="   +lec_cd
                            + "&usn="    +usn
                            + "&htmcode=<mh:out value="${citem.htmcode}"/>"
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
//        opener.top.mainFrame.location.reload();
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

addEvent(window, 'load', function(){pageinit()} )
window.onbeforeunload = function(){
    if(confirm('<mfmt:message bundle="common.message.lecture" key="classroom.lecture.end"/>')) destroy();
};

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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
                        + "&htmcode="    +htmcode
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
                        + "&htmcode="    +htmcode
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
                        + "&htmcode="    +htmcode
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
                        + "&htmcode="    +htmcode
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
                        + "&htmcode="    +htmcode
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
//-->
</script>

<table width=100% border=0 cellspacing=0 cellpadding=0>
    <tr>
        <td>
            <iframe id="Viewer_Frame" width=100% height=<mh:out value="${v_height+30}"/> frameborder=0 scrolling=no src="<mh:out value="${contents_url}"/>" marginwidth=0 marginheight=0></iframe>
        </td>
    </tr>
</table>