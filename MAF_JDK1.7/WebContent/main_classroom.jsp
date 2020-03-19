<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function doView(sjtcode, leccode, lecnumb){
        var frm = getObject("myform");
        if(frm) {
            frm.action = CPATH+"/wlc.learner/default.do";
            frm.cmd.value = "default";
            frm.to.value  = CPATH+"/wlc.learner/chapters.do";
            frm.sjtcode.value  = sjtcode;
            frm.leccode.value = leccode;
            frm.lecnumb.value = lecnumb;
            frm.submit();
        }
    }
 
//-->
</SCRIPT>
            <mf:form action="${control_action}" method="get" name="myform" id="myform">
                <input type="image" value="test" width="0" height="0" border="0" class="hidden"/>
                <mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
                <mf:input type="hidden" name="miv_page" value="1"/>
                <mf:input type="hidden" name="cmd" value="list"/>
                <mf:input type="hidden" name="sjtcode" value=""/> 
                <mf:input type="hidden" name="leccode" value=""/> 
                <mf:input type="hidden" name="lecnumb" value=""/> 
                <mf:input type="hidden" name="to" value=""/>
                <mf:input type="hidden" name="surveyid" value=""/>
            </mf:form> 
<table width="95%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"
    rowAlternateClass="alternateRow">
    <thead>
        <tr>

            <th><mfmt:message bundle="table.bas_class" key="lecname" /></th>
            <th><mfmt:message bundle="common" key="table.status" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${list_ing}" varStatus="status">
            <tr>

                <jsp:useBean id="now" class="java.util.Date" />
                <fmt:formatDate var="date" value="${now}" pattern="yyyyMMdd" />
                <c:choose>
                    <c:when test="${item.lec_type=='R' && (item.sch_sdt<=date && date<=item.sch_edt)}">
                        <td><a
                            href='javascript:doView("<c:out value="${item.sjt_cd}"/>","<c:out value="${item.leccode}"/>","<c:out value="${item.lecnumb}"/>");'><mh:out
                            value="${item.lecname}" td="true" /></a></td>
                    </c:when>
                    <c:when test="${item.lec_type=='G' && (item.req_sdat<=date && date<=item.req_edat)}">
                        <td><a
                            href='javascript:doView("<c:out value="${item.sjt_cd}"/>","<c:out value="${item.leccode}"/>","<c:out value="${item.lecnumb}"/>");'><mh:out
                            value="${item.lecname}" td="true" /></a></td>
                    </c:when>
                    <c:otherwise>
                        <td><mh:out value="${item.lecname}" td="true" /></td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${!empty item.req_stat || item.req_stat != ''}">
                        <td align="center"><mh:out value="${item.req_stat}" codeGroup="REQ_ST" td="true" /></td>
                    </c:when>
                    <c:otherwise>
                        <td align="center"><mh:out value="${item.lecstat}" codeGroup="LECSTAT" td="true" /></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </tbody>
</table>
