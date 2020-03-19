<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<%@page language="java"%>
<%@page import="maf.base.BaseHttpSession"%>
<%@page import="maf.web.session.MySession"%>
<%@page import="maf.web.session.SessionPool"%>

<c:set var="m" value="board" />
<link href="<c:out value="${MBOARD.PATH}"/>/css/board_m00.css" rel="stylesheet" type="text/css"></link>
<script language="JavaScript" src='<c:out value="${MBOARD.PATH}"/>/js/view.js' type="text/javascript"></script>
<script language="JavaScript">
function mboard_popup(bid, c_index, seq) {
    var urlname = CPATH+"/board/viewimage.do?bid=" + bid + "&c_index=" + c_index + "&seq="+seq <c:if test="${!empty(course)}">+ "&extleccode=${course.extleccode}"</c:if>;
    browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=yes,resizable=yes,location=false, menubar=no,status=no,scrollbars=no,width=10,height=10");
    browsing_window.focus();
}
</script>
<script type="text/javascript" src="/js/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
addEvent(window,'load',function() {
    var sBasePath = CPATH+'/js/fckeditor/';
    var oFCKeditor = new FCKeditor( 'c_content' );
    oFCKeditor.BasePath = sBasePath;
    oFCKeditor.ToolbarSet = 'mBoard';
    oFCKeditor.Height = 400;
    oFCKeditor.DisplayErrors = false;
    oFCKeditor.ReplaceTextarea();
});
</script>
<script language="JavaScript1.2" src='<c:out value="${MBOARD.PATH}"/>/js/uploadimage.js'></script>
<form action="act.do" method="post" name="writeform" id="writeform" enctype="multipart/form-data" onSubmit="return validate(this)">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="view">
    <col width="20%">
    <col width="30%">
    <col width="20%">
    <col width="30%">
    <c:choose>  
        <c:when test="${!empty(msession) && msession != null}">
    <tr>
        <th><mfmt:message bundle="${m}" key="label.writer" /></th>
        <td><mh:out value="${msession.nm}"/>(<mh:out value="${msession.userid}"/>)</td>
        <th><mfmt:message bundle="${m}" key="label.password" /></th>
        <td>&nbsp;</td>
    </tr>
        </c:when>
        <c:otherwise>
    <tr>
        <th><mfmt:message bundle="${m}" key="label.writer" /></th>
        <td><mf:input type="text" name="wname" value="${item.wname}" cssStyle="width:90%" hname="Name" maxlength="20"/></td>
        <th><mfmt:message bundle="${m}" key="label.password" /></th>
        <td><mf:input type="text" name="c_passwd" value="${item.c_passwd}" cssStyle="width:90%" hname="Password" maxlength="20"/></td>
    </tr>
        </c:otherwise>
    </c:choose>
    <tr>
        <th><mfmt:message bundle="${m}" key="label.title"/></th>
        <td colspan="3"><mf:input type="text" cssStyle="width:90%" maxlength="100" name="c_subject" value="${item.c_subject}" required="true" hname="Subject" /></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="${m}" key="title.board.boardutype"/></th>
        <td colspan="3">
            <table border="0" cellspacing="0" cellpadding="0" class="noborder">
                <tr>
                    <td class="noborder">
                        <mh:indexOf var="idx" value="${MBOARD.BTN_AUTH}" needle="N"/>
                        <mh:iif var="chk_is_notice" test="${idx >= 0 }" trueValue="" falseValue="disabled"/>
                        <input type="checkbox" name="is_notice" value="T" <mh:iif test="${item.is_notice=='T'}" trueValue="checked"/> 
                        <c:out value="${chk_is_notice}"/> > : <mfmt:message key="title.board.first" bundle="board"/> &nbsp;&nbsp;                   
                    </td>
                    <c:if test="${!empty(MBOARD.board.category)}">
                    <td width="80" align="right" ><mfmt:message bundle="${m}" key="title.board.burru" />&nbsp;</td>
                    <td class="noborder">
                        <select name="category" >
                            <option value=""></option>
                            <c:forTokens var="cat" items="${MBOARD.board.category}" delims="," >
                                <mf:option value='${cat}' curValue="${item.c_category}" text="${cat}"/>
                            </c:forTokens>
                        </select>
                    </td>
                    </c:if>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <th colspan="4">
            <textarea cols="68" rows="38" name="c_content" id="c_content" class="bbs_text_area" style="WIDTH: 100%;" hname="Contents">
            <mh:out value="${item.c_content}"/>
            </textarea>
            <c:if test="${MBOARD.board.fl_html == 'T'}" >
                <!------- START :: Wizwig -------> 
                <script language="javascript1.2">
                //editor_generate('c_content');
                </script>
                <!------- END :: Wizwig-------> 
            </c:if>
        </th>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="bbs_table">
    <c:if test="${MBOARD.board.number_attach > 0 }" >
    <tr>
        <td colspan="2">
        <c:import url="${MBOARD.PATH}/inc/write.addImage.inc.jsp"/>
        </td>
    </tr>
    </c:if>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <c:if test="${!empty(attItems)}" >
    <tr>
        <td><br><mfmt:message bundle="${m}" key="title.board.prefilecheck"/></td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="1">
                <tr>
                <c:forEach items="${attItems}" var="data">
                    <mh:indexOf var="idx" value="${data.content_type}" needle="image"/>
                    <c:if test="${idx >-1}">
                    <c:url var="imgUrl" value="/mbbsfile/${MBOARD.bid}/thumb/${data.real_filename}"/>
                    <td width="100">
                        <table border="0" cellspacing="0" cellpadding="2">
                            <tr>
                                <td align="center"><mf:input type="checkbox"  name="deleteAttachSeq" value="${data.seq}" 
                                    cssStyle="width:14px; height:14px;border:none;"/><mh:out value="${data.ori_filename}" td="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="center"><img src='<c:out value="${imgUrl}"/>' border="0" width="80"></td>
                            </tr>
                        </table>
                    </td>
                    </c:if>
                </c:forEach>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="1">
                <c:forEach items="${attItems}" var="data">
                <mh:indexOf var="idx" value="${data.content_type}" needle="image"/>
                <c:if test="${idx<0}">
                <tr>
                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="2">
                            <tr>
                                <td class="td"><mf:input type="checkbox" name="deleteAttachSeq" value="${data.seq}"
                                                 cssStyle="width:14px; height:14px;border:none;"/></td>
                                <td class="td"><mh:out value="${data.ori_filename}" td="true"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                </c:if>
                </c:forEach>
            </table>
        </td>
    </tr>
    </c:if>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td align="right">
            <mf:button onclick="doSubmit()" bundle="button" key="save" icon="icon_save" />
            <mf:button onclick="go_list()" bundle="button" key="list" icon="icon_list" />
        </td>
    </tr>
</table>
<mf:input type="hidden" name="LISTOP" value="${MBOARD.listOpStr}"/>
<mf:input type="hidden" name="cmd" value="${cmd}"/>
<mf:input type="hidden" name="bid" value="${MBOARD.bid}"/>
<c:if test="${!empty(course)}">
<mf:input type="hidden" name="extleccode" value="${course.extleccode}"/>
</c:if> 
</form>
<mh:import url="${MBOARD.PATH}/inc/frmBBS.jsp" />
<!-- <a href="javascript:Preview()">Preview</a> -->
<SCRIPT LANGUAGE="JavaScript">
<!--
var onlyoneClick = false;

function Preview(num){
    var printWin = open(CPATH + "/mboard/preview.html", "printWin", "width=660, height=600, resizable=yes, scrollbars=yes");
}

function viewPreviewPost(printWin){
    var printObj = document.all["printPost" ]; //printPost
    var printHTML = "";

    if(typeof(printObj) == "object" && typeof(printWin.document.all.printArea) == "object"){
        printWin.document.all.printArea.innerHTML = document.writeform.c_content.value;
    }

}

function doSubmit() {
    var vfrm = document.getElementById("writeform");
    if(validate(vfrm)){
        if (!onlyoneClick) {
            onlyoneClick = true;
            vfrm.submit();
        } else {
            alert('<mfmt:message bundle="${m}" key="title.board.click" />');        
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