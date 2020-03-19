<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
    function doRequest(can) {
        var frm = getObject("myform");
<c:choose>
  <c:when test="${''==userid || null == userid}">
		//alert('<mfmt:message bundle="common.scripts" key="script.alert.login"/>');
        maf.alert('common.scripts','script.alert.login'); 
		return;
  </c:when>
  <c:otherwise>
		if(can == 'cannot'){
			//alert('<mfmt:message bundle="common.message" key="message.req.dup"/>');
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
    <mf:input type="hidden" name="leccode" value="${item.leccode}"/>
    <mf:input type="hidden" name="lec_type" value="${item.lec_type}"/>
</mf:form>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mf:label name="leccode"/></th> 
        <td colspan="3"><mh:out value="${item.leccode}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_year"/></th> 
        <td><mh:out value="${item.lec_year}" td="true"/></td>
        <th><mf:label name="lecnumb"/></th> 
        <td><mh:out value="${item.lecnumb}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lecname"/></th> 
        <td colspan="3"><mh:out value="${item.lecname}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lecstat"/></th> 
        <td><mh:out value="${item.lecstat}" codeGroup="LECSTAT" td="true"/></td>
        <th><mf:label name="section"/></th> 
        <td><mh:out value="${item.section}" codeGroup="SECTION" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="ltype"/></th> 
        <td><mh:out value="${item.ltype}" codeGroup="LTYPE" td="true"/></td>
        <th><mf:label name="cert_lvl"/></th> 
        <td><mh:out value="${item.cert_lvl}" codeGroup="CERT_LVL" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="otype"/></th> 
        <td><mh:out value="${item.otype}" codeGroup="OTYPE" td="true"/></td>
        <th><mf:label name="lec_type"/></th> 
        <td><mh:out value="${item.lec_type}" codeGroup="LEC_TYPE" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="field"/></th> 
        <td><mh:out value="${item.field}" codeGroup="FIELD" td="true"/></td>
        <th><mf:label name="lec_price"/></th> 
        <td><mh:out value="${item.lec_price}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_sdate"/></th> 
        <td><mh:out value="${item.lec_sdate}" format="fulldate" td="true"/> </td>
        <th><mf:label name="lec_edate"/></th> 
        <td><mh:out value="${item.lec_edate}" format="fulldate" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="learn_day_cnt"/></th> 
        <td><mh:out value="${item.learn_day_cnt}" td="true"/></td>
        <th><mf:label name="learn_time"/></th> 
        <td><mh:out value="${item.learn_time}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="relearn_day_cnt"/></th> 
        <td><mh:out value="${item.relearn_day_cnt}" td="true"/></td>
        <th><mf:label name="lec_capacity"/></th> 
        <td><mh:out value="${item.lec_capacity}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_min_capacity"/></th> 
        <td><mh:out value="${item.lec_min_capacity}" td="true"/></td>
        <th><mf:label name="finished_score"/></th> 
        <td><mh:out value="${item.finished_score}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="limit_study_time"/></th> 
        <td colspan="3"><mh:out value="${item.limit_study_time}" td="true"/></td>
    </tr>



    <tr>
        <th><mf:label name="lec_reg_edate"/></th> 
        <td><mh:out value="${item.lec_reg_edate}" format="fulldate" td="true"/></td>
        <th><mf:label name="adminid"/></th> 
        <td><mh:out value="${item.adminid}" td="true"/></td>
    </tr>

    <tr>
        <th><mf:label name="lec_datm"/></th> 
        <td><mh:out value="${item.lec_datm}" td="true"/></td>
        <th><mf:label name="lec_wetm"/></th> 
        <td><mh:out value="${item.lec_wetm}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="test_type"/></th> 
        <td colspan="3"><mh:out value="${item.test_type}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="precondition"/></th> 
        <td colspan="3"><mh:out value="${item.precondition}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="orgn_cd"/></th> 
        <td><mh:out value="${item.orgn_cd}"/></td>
        <th><mf:label name="exm_condition"/></th> 
        <td><mh:out value="${item.exm_condition}"/></td>
    </tr>
    <tr>
        <th><mf:label name="dlr_cd"/></th> 
        <td colspan="3"><mh:out value="${item.dlr_cd}" td="true"/></td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" width="100%"> 
    <tr>
        <td align="right">
<c:set var="flag" value="false"/>
<c:set var="cando" value="can"/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="date" value="${now}" pattern="yyyyMMdd"/>
<c:choose>
  <c:when test="${('G' == item.lec_type)}">
    <c:set var="flag" value="${(item.aschsdt <= date) && (date <= item.aschedt)}"/>
  </c:when>
  <c:otherwise>
    <c:set var="flag" value="${(item.aschsdt <= date) && (date <= item.aschedt)}"/>
  </c:otherwise>
</c:choose>  
<c:set var="can_admit" value="true"/>
<c:if test="${(''!=userid && null!=userid) && ('R'==item.lec_type)}">
<c:set var="reqcnt" value="0"/>
<c:catch var="error">
    
    <msql:query var="entries">
        SELECT count(userid) AS cnt
        FROM wcc_lec_req 
        WHERE USERID=? 
            AND LECCODE=? 
            AND REQ_STAT!=?
        <msql:param value="${userid}"/>
        <msql:param value="${item.leccode}"/>
        <msql:param value="CA"/>
    </msql:query>
    <c:forEach var="row" items="${entries.rows}">
      <c:set var="reqcnt" value="${row.cnt}"/>
    </c:forEach>    
</c:catch>
<c:if test="${!empty error}">Error : <c:out value="${error} : ${userid}, ${item.leccode}"/></c:if>    
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
<c:if test="${flag}">
        <mf:button bundle="button" key="button.lec.request" onclick="javascript:doRequest('${cando}')"/>
</c:if>
        <mf:button bundle="button" key="goList" onclick="javascript:goList()"/></td>
    </tr>
</table>
</div>
