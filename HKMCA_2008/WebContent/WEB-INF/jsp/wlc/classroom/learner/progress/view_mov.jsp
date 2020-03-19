<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
function resize() {
    var w = <mh:out value="${v_width}"/>+20;
    var h = <mh:out value="${v_height}"/>+150;

    with(document.body)
    {
       window.resizeTo(w, h);
    }

}

function sizechange(v_width,v_height)
{
    var frm = getObject("myform");
    frm.v_width.value = v_width;
    frm.v_height.value = v_height;
    frm.submit();
}

addEvent(window, 'load',function() {resize()} )

//document.domain="hkmca.co.kr"
// 경로 및 파라미터를 생성.xxx
var LMSServer = '<mh:out value="${lmsserver}"/>';   
var LMSExecName = '<mh:out value="${lmsservlet}"/>';
var lec_cd = '<mh:out value="${lec_cd}"/>';
var usn = '<mh:out value="${usn}"/>';
var item_id = '<mh:out value="${itm_id}"/>';

<mfmt:message var="m1" bundle="common.scripts" key="classroom.right.button.not.use"/>
<mfmt:message var="m2" bundle="common.scripts" key="classroom.button.not.use"/>
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
                            + "&itm_id=<mh:out value="${citem.itm_id}"/>"
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
        opener.top.mainFrame.location.reload();
        opener.top.location.reload();
        opener.location.reload();
    } else {
        var sendParameter   = "?cmd=save_time"
                            + "&insflag="   +flag
                            + "&isAttendance="+isAttendance
                            + "&lec_cd="   +lec_cd
                            + "&usn="    +usn
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
        opener.top.mainFrame.location.reload();
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
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
                        + "&lec_cd="   +lec_cd
                        + "&usn="    +usn
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

//-->
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value="study"/>
<mf:input type="hidden" name="lec_cd" value="${citem.lec_cd}" />
<mf:input type="hidden" name="htmcode" value="${citem.htmcode}"/>
<mf:input type="hidden" name="itm_id" value="${citem.itm_id}"/>
<mf:input type="hidden" name="v_width" value="${v_width}"/>
<mf:input type="hidden" name="v_height" value="${v_height}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td>
			<!-- BODY BOX 시작 -->
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" bgcolor="#FFFFFF" style="padding:5 5 5 5">
                        <script>
                        write_player('<mh:out value="${citem.htmurl}"/>','<mh:out value="${v_width}"/>','<mh:out value="${v_height}"/>');
                        </script>
					</td>
				</tr>
			</table>
			<!-- BODY BOX 끝 -->
		</td>
    </tr>
    <tr>
    <!-- 화면 크기조정 시작 -->
    	<td align="center"><br>
            <a href="javascript:sizechange(320,240)"><img src="/jmf_images/mplayer/btn_s320.gif" align=absmiddle alt="320*240"></a>
            <a href="javascript:sizechange(640,480)"><img src="/jmf_images/mplayer/btn_s640.gif" align=absmiddle alt="640*480"></a>
            <a href="javascript:sizechange(800,600)"><img src="/jmf_images/mplayer/btn_s800.gif" align=absmiddle alt="800*600"></a>
 		</td>
	<!-- 화면 크기조정 끝 -->
	</tr>
</table>
</mf:form>