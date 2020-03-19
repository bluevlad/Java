<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doSelect(setid) {
        var frm = getObject('<c:out value="${param.frm_id}"/>',opener.document);
        frm.<c:out value="${elm_id}"/>.value=setid;
        self.close();
    }
    
    function goList() {
            <c:url var="urlList" value="${control_action}">
                <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
                <c:param name="cmd" value="list"/>
                <c:param name="frm_id" value="${frm_id}"/>
                <c:param name="elm_id" value="${elm_id}"/>
            </c:url>
            document.location = '<mh:out value="${urlList}"/>';
        }
//-->
</SCRIPT>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value="edit"/>
<mf:input type='hidden' name='setid' value="${item.setid}"/>
<mf:input type="hidden" name="frm_id" value="${frm_id}"/>
<mf:input type="hidden" name="elm_id" value="${elm_id}"/>
<div class="viewContainer">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="no_border">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:header name="settitle" /></th>
        <td colspan="3">[<mh:out value="${item.setid}" />] <mh:out value="${item.settitle}" /></td>
    </tr>
    <tr>
        <th><mf:header name="setdesc" /></th>
        <td colspan="3"><mh:out value="${item.setdesc}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:header name="exmtime" /></th>
        <td><mh:out value="${item.exmtime}" td="true"/></td>
        <th><mf:header name="passing_score" /></th>
        <td><mh:out value="${item.passing_score}" td="true"/></td>
    </tr>
	<tr>
        <th><mf:header name="questions" /></th>
        <td colspan="3">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
                <col width="20%"/>
                <col width="16%"/>
                <col width="16%"/>
                <col width="16%"/>
                <col width="16%"/>
                <col width="16%"/>
                <thead>
                <tr>
                    <th>Total</th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt1"/></th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt2"/></th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt3"/></th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt4"/></th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt5"/></th>
                 </tr>
                 </thead>
                 <tbody>
                 <tr>
                    <th align="center"><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}"/></th>
                    <td align="center"><mh:out value="${item.exmcnt1}"/></td>
                    <td align="center"><mh:out value="${item.exmcnt2}"/></td>
                    <td align="center"><mh:out value="${item.exmcnt3}"/></td>
                    <td align="center"><mh:out value="${item.exmcnt4}"/></td>
                    <td align="center"><mh:out value="${item.exmcnt5}"/></td>
                 </tr>
                 </tbody>
            </table>
        </td>
    </tr>
</table>
</div>
</mf:form>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right">
        	<mf:button bundle="button" key="select" onclick="doSelect('${item.setid}')" /> 
        	<mf:button bundle="button" key="list" onclick="goList();" />
        </td>
    </tr>
</table>
<br>
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" >
    <col width="60"/>
    <col width="60"/>
    <col width="60"/>
    <col width="60"/>
    <col width="*"/>
    <thead>
    <tr>
        <th>#</th>
        <th><mf:header name="quelevel" /></th>
        <th><mf:header name="quescore" /></th>
        <th><mf:header name="lang" /></th>
        <th><mf:header name="quetitle" /></th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="si" items="${setitems}" varStatus="status">
        <tr>
            <td align="center"><mh:out value="${status.count}" /></td>
            <td align="center"><mh:out value="${si.quelevel}" codeGroup="ETEST.QLEVEL"/></td>
            <td align="center"><mh:out value="${si.quescore}" /></td>
            <td align="center"><mh:out value="${si.quelang}" /></td>
            <td><mh:out value="${si.quetitle}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>