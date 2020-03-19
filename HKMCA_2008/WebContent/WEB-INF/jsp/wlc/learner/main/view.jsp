<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
    function doRequest(can) {
        var frm = getObject("myform");
<c:choose>
  <c:when test="${''==usn || null == usn}">
        alert('<mfmt:message bundle="common.scripts" key="alert.login"/>');
		return;
  </c:when>
  <c:otherwise>
		if(can == 'cannot'){
            alert('<mfmt:message bundle="common.scripts" key="req.dup"/>');
			return;
		} else if(confirm('<mfmt:message bundle="common.scripts" key="confirm.apply"/>')){
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
<c:set var="flag" value="false"/>
<c:set var="cando" value="can"/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd"/>
<c:set var="flag" value="${(item.aschsdt <= date) && (date <= item.aschedt)}"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <c:set value="2" var="cols"/>
    <c:set value="1" var="cnt"/>
    <tr>
    <c:forEach items="${sch}" var="scheduleitem" varStatus="status">
        <th><mfmt:message bundle="table.bas_lec" key="sch_type_${scheduleitem.sch_type}"/></th> 
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
<c:set var="can_admit" value="true"/>
<c:if test="${(''!=usn && null!=usn)}">
<c:set var="reqcnt" value="0"/>
<c:catch var="error">
    <msql:query var="entries">
        SELECT count(usn) AS cnt
        FROM wlc_lec_req 
        WHERE USN=? 
            AND LEC_CD=? 
            AND REQ_STAT!=?
        <msql:param value="${usn}"/>
        <msql:param value="${item.lec_cd}"/>
        <msql:param value="CA"/>
    </msql:query>
    <c:forEach var="row" items="${entries.rows}">
      <c:set var="reqcnt" value="${row.cnt}"/>
    </c:forEach>    
</c:catch>
<c:if test="${!empty error}">Error : <c:out value="${error} : ${usn}, ${item.lec_cd}"/></c:if>    
	<c:if test="${reqcnt > 0}">
	    <c:set var="can_admit" value="false"/>
	</c:if>
</c:if>
<c:choose>
  <c:when test="${can_admit}">
    <c:set var="cando" value="can"/> 
  </c:when>
  <c:otherwise>
    <c:set var="cando" value="cannot"/>
  </c:otherwise>
</c:choose>  
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn"> 
    <tr>
        <td align="right">
            <c:if test="${flag}">
	        <mf:button bundle="button" key="lec.request" onclick="javascript:doRequest('${cando}')" icon="icon_save"/>
            </c:if>
	        <mf:button bundle="button" key="list" onclick="javascript:goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</div>