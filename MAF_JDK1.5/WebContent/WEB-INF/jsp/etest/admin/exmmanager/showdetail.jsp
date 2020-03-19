<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script>

    
    function updateScore(qseq, sc, obj) {
        if(obj.checked) {
            eobj = getObject("usrscore_"+qseq);
            eobj.value=sc;
        } else {
            eobj = getObject("usrscore_"+qseq);
            eobj.value='0';
        };
        markCheck(qseq);
    }
    
    function markCheck(qseq) {
        var elmName = "updated";
        var frm = getObject("myform");
        if(frm ) {
            for (var i=0; i<frm.elements.length;i++) {
                var elm = frm.elements[i];
                if (elm.type == "checkbox" && 
                        elm.name == elmName && 
                        elm.value == qseq) {
                    frm.elements[i].checked = true;
                }
            }
        } 
    }
    
    function goList(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "view";
            frm.submit();
        }
    }
    // 
    function setRetry() {
        var url = '<c:url value="${control_action}"/>';
        var params={<c:out value="'cmd':'setRetry','exmid':'${param.exmid}','usn':'${uitem.usn}','lecnumb':'${uitem.lecnumb}'" escapeXml="false"/>};
        new Ajax.Request(url, {
                        method: 'get', 
                        parameters: params,
                        
                        onSuccess: function(transport) {
                            goList();
                        }
                      }
                ); 
     }
  
     function setContinue() {
        var url = '<c:url value="${control_action}"/>';
        var params={<c:out value="'cmd':'setContinue','exmid':'${param.exmid}','usn':'${uitem.usn}','lecnumb':'${uitem.lecnumb}'" escapeXml="false"/>};
        new Ajax.Request(url, {
                        method: 'get', 
                        parameters: params,
                        
                        onSuccess: function(transport) {
                            goList();
                        }
                      }
                ); 
     }
     function markScore() {
        var frm = getObject("myform");
        
        if(frm) {
            frm.cmd.value="markScore";
            frm.submit();
        }
     }
</script>


<div class="viewContainer">
<table border="0"   cellpadding="2" cellspacing="1" class="view" width="100%"> 
    <col width="20%"/>
    <col width="30%"/>
    <col width="20%"/>
    <col width="30%"/>

    <tr>
        <th><mf:header name="org_cd" /></th>
        <td ><mh:out value="${uitem.org_name} ( ${uitem.org_cd})" /></td>
        <th ><mf:header name="nm" /></th>
        <td ><mh:out value="${uitem.nm} ( ${uitem.userid})" />
        </td>
    </tr>
    <tr>
        <th><mf:header name="rst_status" /></th>
        <td ><mh:out value="${uitem.rst_status}" codeGroup="ETEST.RST_STATUS"/></td>
        <th  ><mf:header name="exm_sdat_t1" />~<mf:header name="exm_edat_t1" /></th>
        <td ><mh:out value="${uitem.rst_sdt}" format="fulldatetime"/> ~ <mh:out value="${uitem.rst_edt}" format="fulldatetime"/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="etest.common" key="result.rstscore100" /></th>
        <td><mh:out value="${uitem.rstscore100}" td="true"/></td>
        <th><mfmt:message bundle="table.exm_rst" key="passing_yn" /></th>
        <td><mh:out value="${uitem.passing_yn}" codeGroup="ETEST.PASSING_YN" td="true"/></td>
     </tr>
</table>

<mf:form action="${control_action}" method="post" name="myform" id="myform" >
    <mf:input type="hidden" name="cmd" value=""/>
    <mf:input type="hidden" name="usn" value="${param.usn}"/>
    <mf:input type="hidden" name="lecnumb" value="${uitem.lecnumb}"/>
    <mf:input type="hidden" name="exmid" value="${param.exmid}"/>
    <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td colspan="2" align="center">
            <mf:button bundle="button" key="button.exm.retry" onclick="setRetry();" />
            <mf:button bundle="button" key="button.exm.continue" onclick="setContinue();" />
            <mf:button bundle="button" key="goList" onclick="goList()" />
        </td>
    </tr>
</table>
<table border="0"   cellpadding="2" cellspacing="1" class="list" width="100%"> 
    <thead>
        <tr>
            <th>#</th>
            <th><mf:header name="quelevel" /></th>
            <th nowrap><mf:header name="quetitle" /></th>
            <th nowrap><mf:header name="queansw" /></th>
            <th nowrap><mf:header name="usransw" /></th>
            <th ><mf:header name="correct_yn" /></th>
            <th nowrap><mf:header name="quescore" /></th>
            <th nowrap><mf:header name="usrscore" /></th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="i" items="${items}">
        
        <tr>
            <td align="center"><mh:out value="${i.qseq}" td="true"/></td>
            <td align="center"><img src='<c:url value="/images/etest/degree/${i.quelevel}.gif"/>' border="0" title='<mh:out value="${i.quelevel}" codeGroup="ETEST.QLEVEL"/>'/></td>
            <td><mh:out value="${i.quetitle}" td="true"/></td>
            <td align="center"><mh:out value="${i.queansw}" td="true"/></td>
            <td align="center"><mh:out value="${i.usransw}" td="true"/></td>
            <td align="center">
                <mf:input type="checkbox" name="correct_${i.qseq}" value="Y" curValue="${i.correct_yn}" onclick="updateScore('${i.qseq}', ${i.quescore}, this)"/></td>
            <td align="center"><mh:out value="${i.quescore}" td="true"/></td>
            <td align="center"><mf:select name="usrscore_${i.qseq}" onchange="markCheck('${i.qseq}')">
            <c:forEach var="j" begin="0" end="${i.quescore}" >
                <mf:option value="${j}" curValue="${i.usrscore}" text="${j}"/>
            </c:forEach>
            <mf:input type="checkbox" name="updated" value="${i.qseq}" cssStyle="display:none"/>
            </mf:select></td>
        </tr>
        
    </c:forEach>
    </tbody>
</table>
</mf:form>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td colspan="2" align="center">
            
            <mf:button bundle="button" key="save" onclick="markScore()" />
            <mf:button bundle="button" key="goList" onclick="goList()" />
        </td>
    </tr>
</table>
<table border="1" cellspacing="0" cellpadding="2" >

<c:forEach var="j" begin="1" end="5">
    <tr>
        <td><img src='<c:url value="/images/etest/degree/${j}.gif"/>' border="0"/></td>
        <td><mh:out value="${j}" codeGroup="ETEST.QLEVEL"/></td>
    </tr>
</c:forEach>


</table>
</div>