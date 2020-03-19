<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script>
    function doPrint(certi_no) {
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value="printCerti";
            frm.certi_no.value=certi_no;
            frm.submit();
        }
    }
    
</script>
<mf:form action="${controlaction}" name="myform" id="myform">
    <mf:input type="hidden" name="cmd" value=""/>
    <mf:input type="hidden" name="certi_no" value=""/>
</mf:form>

<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="20%" />
    <col width="30%" />
    <col width="20%" />
    <col width="30%" />
    <tbody>
        <tr>
            <th nowrap><mf:header name="userid" /></th>
            <td><mh:out value="${user.userid}" /></td>
            <th nowrap><mf:header name="nm" /></th>
            <td><mh:out value="${user.nm}" /></td>
        </tr>
         <tr>
            <th nowrap><mf:header name="org_name" /></th>
            <td><mh:out value="${user.org_name}" /></td>
            <th nowrap><mf:header name="section" /></th>
            <td><mh:out value="${user.mst_section}" codeGroup="SECTION" /></td>
        </tr>
        <tr>
            <th nowrap>Certification</th>
            <td colspan="3"><table border="0" cellpadding="2" cellspacing="0" class="list" width="100%">
            <thead>
                <tr>
                    <th>Certi. Name</th>
                    <th>Certi. No</th>
                    <th>Issue Date</th>
                    <th>Print</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${ceries}" varStatus="status">
             <tr>
                <td align="center"><mh:out value="${item.certi_name}" td="true"/></td>
                <td align="center"><mh:out value="${item.certi_no}" td="true"/></td>
                <td align="center"><mh:out value="${item.certi_date}" format="fulldate"  td="true"/></td>
                <td align="center"><mf:button bundle="button" key="print" onclick="doPrint('${item.certi_no}')" icon="icon_print"/></td>
             </tr>
             </c:forEach>
             </tbody>
            </table></td>
        </tr>
    </tbody>
</table>
<br>
<br>

<table border="0" cellpadding="2" cellspacing="0" class="list" width="100%">
    
    <col width="220" />
    <col width="*" />
    <col width="10" />
    <col width="100" />
    <thead>
        <tr>
            <th>crs name</th>
            <th>subject name</th>
            <th>stat</th>
            <th>finish date</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${crslist}" varStatus="status">
         <tr>
            <td align="center"><c:if test="${item.rn == 1}"><mh:out value="${item.crs_nm}" /></c:if>&nbsp;</td>
            <td align="center"><a href="javascript:showSubjectInfo('<mh:out value="${item.sjt_cd}" />')"><mh:out value="${item.subject_nm}" /></a></td>
            <td align="center"><mh:out value="${item.is_grad}" td="true"/></td>
            <td align="center"><mh:out value="${item.req_edat}" format="fulldate" td="true" /></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>