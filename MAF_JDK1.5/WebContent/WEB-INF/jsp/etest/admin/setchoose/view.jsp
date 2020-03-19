<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script>

    function doSelect(setid) {
        var frm = getObject('<c:out value="${param.frm_id}"/>',opener.document);
        frm.<c:out value="${param.elm_id}"/>.value=setid;
        self.close();
    }
    
    function goList() {
            <c:url var="urlList" value="${control_action}">
                <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
                <c:param name="cmd" value="list"/>
                <c:param name="frm_id" value="${param.frm_id}"/>
                <c:param name="elm_id" value="${param.elm_id}"/>
            </c:url>
            document.location = '<mh:out value="${urlList}"/>';
        }
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" name="cmd" value="edit">
    <mf:input type='hidden' name='setid' value="${item.setid}"/>
    <mf:input type="hidden" name="frm_id" value="${param.frm_id}"/>
    <mf:input type="hidden" name="elm_id" value="${param.elm_id}"/>

<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="90%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th nowrap><mf:header name="setid" /></th>
        <td><mh:out value="${item.setid}" /></td>
        <th nowrap><mf:header name="setowner" /></th>
        <td><mh:out value="${item.setowner}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="settitle" /></th>
        <td colspan="3"><mh:out value="${item.settitle}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="setdesc" /></th>
        <td colspan="3"><mh:out value="${item.setdesc}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="exmtime" /></th>
        <td><mh:out value="${item.exmtime}" /></td>
        <th nowrap><mf:header name="passing_score" /></th>
        <td><mh:out value="${item.passing_score}" /></td>
        
    </tr>
	<tr>
        <th nowrap><mf:header name="questions" /></th>
        <td colspan="3"> <table  border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
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
                    <th  align="center"><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}"/></th>
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
    <tr>
        <th nowrap><mf:header name="update_usn" /></th>
        <td><mh:out value="${item.update_usn}" /> , <mh:out value="${item.update_dt}" /></td>
        <th nowrap><mf:header name="reg_usn" /></th>
        <td><mh:out value="${item.reg_usn}" />, <mh:out value="${item.reg_dt}" /></td>
    </tr>

    <tr>


            <th nowrap><mf:label name="cat_name" /></th>
            <td colspan="3"><mh:out   value="${item.cat_name}" /></td>
        </tr>
</mf:form>
</table>


<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="90%">
    <tr>
        <td colspan="2" align="center">
        	<mf:button bundle="button" key="button.select" onclick="doSelect('${item.setid}')" /> 
        	<mf:button bundle="button" key="goList" onclick="goList();" /></td>
    </tr>
</table>

    <div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" >
            <col width="60"/>
            <col width="100"/>
            <col width="60"/>
            <col width="60"/>
            <col width="60"/>
            <col width="*"/>
            <thead>
                <tr>
                <th>#</th>
                <th><mf:header name="cat_name" /></th>
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
                    <td align="center"><mh:out value="${si.cat_name}" /></td>
                    <td align="center"><mh:out value="${si.quelevel}" codeGroup="ETEST.QLEVEL"/></td>
                    <td align="center"><mh:out value="${si.quescore}" /></td>
                    <td align="center"><mh:out value="${si.quelang}" /></td>
                    <td><mh:out value="${si.quetitle}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
