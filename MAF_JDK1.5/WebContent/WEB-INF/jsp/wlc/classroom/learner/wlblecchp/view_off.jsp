<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function goList() {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = "<mh:out value="${urlList}"/>";
}      

function doSearch() {
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "view_off";
        frm.miv_page.value = 1;
        frm.submit();
    }     
}

    
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td><div class="searchContainer">
            <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
            <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
            <input type="hidden" name="miv_page" value="1"/>
            <input type="hidden" name="cmd" value="edit"/>      
            <mf:input type="hidden" name="leccode" value="${leccode}" />
            <mf:input type="hidden" name="reqnumb" value="${reqnumb}" />
            <mf:input type="hidden" name="itm_id" value="${item.itm_id}" />
            <mf:input type="hidden" name="seq_no" value="${uitem.seq_no}" />
                <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th width="20%"><mfmt:message bundle="table.wlb_lec_chp" key="itm_title"/></th>
                    <td width="80%" bgcolor="#ffffff"><mh:out value="${item.itm_title}" td="true" /></td>
                 </tr>
                <tr>
                    <th width="20%"><mfmt:message bundle="table.wlb_lec_chp" key="off_date_info"/></th>
                    <td width="80%" bgcolor="#ffffff"><mh:out value="${item.off_date_info}" td="true" /></td>
                 </tr>
                <tr>
                    <th width="20%"><mfmt:message bundle="table.wlb_lec_chp" key="off_place"/></th>
                    <td width="80%" bgcolor="#ffffff"><mh:out value="${item.off_place}" td="true" /></td>
                 </tr>
               </table>
            </div>   
        </td>
    </tr>
    <tr>
        <td><div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
        <thead>
        <tr>
            
            <th><mf:header name="userid"/></th>
            <th><mfmt:message bundle="table.wlb_off_req" key="usernm"/></th>
            <th><mf:header name="lecnumb"/></th>
            <th><mf:header name="reg_dt"/></th>
            <th><mf:header name="off_score"/></th>
            <th><mf:header name="prf_chk"/></th>                     
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <tr >
                
                <td ><mh:out value="${item.userid}" td="true" /></td>
                <td ><mh:out value="${item.nm}" td="true" /></td>
                <td align="center" ><mh:out value="${item.lecnumb}" td="true" /></td>
                <td align="center" ><mh:out value="${item.reg_dt}" format="fulldate"/></td>
                <td align="center" ><mh:out value="${item.off_score}" td="true" /></td>
                <td align="center" ><mh:out value="${item.prf_chk}" codeGroup="OFF_ATTEND2" td="true"/></td>
        </tr>
        </c:forEach>
        </tbody>
        </table></div>
        </td>
    </mf:form>    
    </tr>
    <tr>
        <td align="center">

            <mf:button bundle="button" key="goList" onclick="goList()" icon="icon_list"/></td>
    </tr>
</table>
