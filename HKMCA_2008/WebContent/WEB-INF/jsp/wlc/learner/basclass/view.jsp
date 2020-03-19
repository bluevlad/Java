<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
    function doRequest(can) {
        var frm = getObject("myform");
<c:choose>
  <c:when test="${''==usn || null == usn}">
        maf.alert('common.scripts','script.alert.login'); 
		return;
  </c:when>
  <c:otherwise>
		if(can == 'cannot'){
            maf.alert('common.message','message.req.dup'); 
			return;
		}
		else if(confirm('<mfmt:message bundle="common.scripts" key="script.confirm.apply"/>')){
          frm.cmd.value= "insert";
          frm.submit();
		}
  </c:otherwise>
</c:choose>
    }  
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
</script>
<div class="viewContainer">
<mf:form action="${control_action}"  method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="lec_cd" value="${item.lec_cd}"/>
<mf:input type="hidden" name="lec_type" value="${item.lec_type}"/>
</mf:form>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mf:label name="lec_nm"/></th> 
        <td colspan="3"><mh:out value="${item.lec_nm}" td="true"/>&nbsp;&nbsp;[<mh:out value="${item.lec_cd}" td="true"/>]</td>
    </tr>
    <tr>
        <th><mf:label name="lec_year"/></th> 
        <td><mh:out value="${item.lec_year}" td="true"/></td>
        <th><mf:label name="lec_num"/></th> 
        <td><mh:out value="${item.lec_num}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_stat"/></th> 
        <td><mh:out value="${item.lec_stat}" codeGroup="LECSTAT" td="true"/></td>
        <th><mf:label name="lec_type"/></th> 
        <td><mh:out value="${item.lec_type}" codeGroup="LEC_TYPE" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_price"/></th> 
        <td><mh:out value="${item.lec_price}" td="true"/></td>
        <th><mf:label name="finished_score"/></th> 
        <td><mh:out value="${item.finished_score}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="learn_day_cnt"/></th> 
        <td><mh:out value="${item.learn_day_cnt}" td="true"/></td>
        <th><mf:label name="relearn_day_cnt"/></th> 
        <td><mh:out value="${item.relearn_day_cnt}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_capacity"/></th> 
        <td><mh:out value="${item.lec_capacity}" td="true"/></td>
        <th><mf:label name="lec_min_capacity"/></th> 
        <td><mh:out value="${item.lec_min_capacity}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="learn_time"/></th> 
        <td><mh:out value="${item.learn_time}" td="true"/></td>
        <th><mf:label name="limit_study_time"/></th> 
        <td><mh:out value="${item.limit_study_time}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_datm"/></th> 
        <td><mh:out value="${item.lec_datm}" td="true"/></td>
        <th><mf:label name="lec_wetm"/></th> 
        <td><mh:out value="${item.lec_wetm}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="test_type"/></th> 
        <td><mh:out value="${item.test_type}" td="true"/></td>
        <th><mf:label name="adminid"/></th> 
        <td><mh:out value="${item.adminid}" td="true"/></td>
    </tr>
</table>
<br>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <c:set value="2" var="cols"/>
    <c:set value="1" var="cnt"/>
    <tr>
    <c:forEach items="${sch}" var="scheduleitem" varStatus="status">
        <th nowrap><mfmt:message bundle="table.bas_lec" key="sch_type_${scheduleitem.sch_type}"/></th> 
        <td align="center">
            <mh:out value="${scheduleitem.sch_sdt}" td="true"/> - <mh:out value="${scheduleitem.sch_edt}" td="true"/> 
        </td>
    <c:if test="${cnt%cols == 0}">
    </tr>
    <tr>
    </c:if>
    <c:set value="${cnt+1}" var="cnt"/>
    </c:forEach>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" width="100%"> 
    <tr>
        <td align="right">
	        <mf:button bundle="button" key="button.lec.request" onclick="javascript:doRequest('${cando}')"/>
	        <mf:button bundle="button" key="goList" onclick="javascript:goList()"/>
        </td>
    </tr>
</table>
</div>
