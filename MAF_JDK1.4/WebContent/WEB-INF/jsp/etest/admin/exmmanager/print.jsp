<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<script>
    function goList() {

            var frm = getObject("myform");
            if(frm) {
                frm.cmd.value = "view";
                frm.submit();
            }
        }
 
     
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
    <mf:input type="hidden" name="cmd" value=""/>
    <mf:input type="hidden" name="usn" value=""/>
    <mf:input type="hidden" name="lecnumb" value=""/>
    <mf:input type="hidden" name="exmid" value="${item.exmid}"/>
    <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
</mf:form>
    <div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />
        <tr>
            <th nowrap><mf:header name="exmowner" /></th>
            <td><mh:out value="${item.exmowner}" /></td>
            <th nowrap><mf:header name="exmtitle" /></th>
            <td><mh:out value="${item.exmtitle}" /></td>
        </tr>
        <tr>            
            <th nowrap><mf:header name="exm_sdat_t1" /></th>
            <td><mh:out value="${item.exm_sdat_t1}" /></td>
            <th nowrap><mf:header name="exm_edat_t1" /></th>
            <td><mh:out value="${item.exm_edat_t1}" /></td>
        <tr>
            <th nowrap><mf:header name="setid" /></th>
            <td><mh:out value="${item.settitle}"/>(<mh:out value="${item.setid}" />)</td>
            <th nowrap><mf:header name="active_yn" /></th>
            <td><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" /></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="questions" /></th>
            <td ><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}" td="true"/></td>
            <th nowrap><mf:header name="update_dt" /></th>
            <td><mh:out value="${item.update_usn }"/>, <mh:out value="${item.update_dt}" format="fulldatetime"/></td>
        </tr>
    </table>
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td colspan="2" align="center">
                <mf:button bundle="button" key="goList" onclick="goList();" />
                <table border="0" cellpadding="2" cellspacing="0" class="list" width="100%">
                    <thead>
                        <tr>
                            <th>Language</th>
                            <th>PDF</th>
                            <th>HTML</th>
                        </tr>
                    </thead>
                <c:forEach var="i" items="${setlangs }">
                    <c:url var="url" value="${controlaction}">
                        <c:param name="exmid" value="${item.exmid}"/>
                        <c:param name="lang" value="${i.lang}"/>
                    </c:url>
                    <tr>
                        <th><mh:out value="${i.allnames}"/></th>
                        <td><a href='<c:url value="${url }"><c:param name="cmd" value="printPDF"/></c:url>' > PDF </a></td>
                        <td><a href='<c:url value="${url }"><c:param name="cmd" value="printHTML"/></c:url>' target="_blank">HTML </a></td>
                    </tr>
                </c:forEach>
                        
                    </tbody>
                </table>
                </td>
        </tr>
    </table>
 
    </div>


