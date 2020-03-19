<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>
<script language="javascript">
<!--
function goBack()   {
    var frm = getObject("myform");
    frm.submit();    
}
//-->
</script>
<table border="0" cellpadding="2" cellspacing="0" width="100%"> 
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this);return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
<input type="hidden" name="cmd" value="view">
<mf:input type="hidden" name="lec_cd" value="${lec_cd}" />
<mf:input type="hidden" name="usn" value="${usn}" />
<mf:input type="hidden" name="reqnumb" value="${reqnumb}" />
<mf:input type="hidden" name="suserid" value="${userid}" />
<mf:input type="hidden" name="sreqnumb" value="${reqnumb}" />
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
            <thead>
            <tr>
              <th><mfmt:message bundle="table.wlb_off_req" key="seq_no"/></th>
              <th><mfmt:message bundle="table.wlb_off_req" key="itm_title"/></th>
              <th><mfmt:message bundle="table.wlb_off_req" key="reg_dt"/></th>
              <th><mfmt:message bundle="table.wlb_off_req" key="appr_dt"/></th>
              <th><mfmt:message bundle="table.wlb_off_req" key="off_score"/></th>
             </tr>
            </thead>
            <tbody>
         <c:forEach var="item" items="${navigator.list}" varStatus="status">
            <tr >
               <td align="center"><mh:out value="${item.itm_sequence}" td="true" /></td>
               <td align=left><mh:out value="${item.itm_title}" td="true" /></td>
               <td align=center><fmt:formatDate value="${item.reg_dt}" pattern="yyyy-MM-dd"/>&nbsp;</td>
               <td align=center><fmt:formatDate value="${item.appr_dt}" pattern="yyyy.MM.dd"/>&nbsp;</td>
               <td align=center><mh:out value="${item.off_score}" td="true" /></td>
             </tr>
        </c:forEach>
            </tbody>
            </table>
        </td>
    </tr>
</mf:form>    
</table>
<br/>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">   
  <tr>
    <td colspan="2" align="center">
    <mf:button bundle = "button"  key="return" onclick="goBack()"/></td>
  </tr>
</table>