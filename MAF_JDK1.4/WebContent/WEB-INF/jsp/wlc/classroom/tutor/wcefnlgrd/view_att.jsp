<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript"   src="${CPATH}/js/lib.validate.js"></script>
<form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
    <input type="hidden" name="${mrt:mvcCmd()}" value="view">
    <input type="hidden" name="leccode" value="${mhtml:nvl(leccode,'')}">
    <input type="hidden" name="userid" value="${mhtml:nvl(userid,'')}">
    <input type="hidden" name="reqnumb" value="${mhtml:nvl(reqnumb,'')}">
    <input type="hidden" name="suserid" value="${mhtml:nvl(userid,'')}">
    <input type="hidden" name="sreqnumb" value="${mhtml:nvl(reqnumb,'')}">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%"> 
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
            <thead>
            <tr>
              <th class="list BL">No</th>
              <th class="list"><mfmt:message bundle="table.Wcefnlgrd" key="wce_fnl_grd.userid"/></th>
              <th class="list"><mfmt:message bundle="table.Wcefnlgrd" key="wce_fnl_grd.usernm"/></th>
              <th class="list"><mfmt:message bundle="table.Wcefnlgrd" key="wce_fnl_grd.lecnumb"/></th>
          <c:if test="${!empty slist}">
            <c:forEach var="sitem" items="${slist}" varStatus="status">
              <th class="list">${mhtml:null2nbsp(sitem.smcname)}</th>            
            </c:forEach>            
          </c:if>    
              <th class="list" BR><mfmt:message bundle="table.Wcefnlgrd" key="wce_fnl_grd.score7"/></th>
             </tr>
            </thead>
            <tbody>
         <c:forEach var="item" items="${navigator.list}" varStatus="status">
            <tr >
               <td class="list" align="center">${status.count}</td>
               <td class="list" align=center>${mhtml:null2nbsp(item.usrid)}</td>
               <td class="list" align=center>${mhtml:null2nbsp(item.usrname)}</td>
               <td class="list" align=center>${mhtml:null2nbsp(item.reqnumb)}</td>
          <c:if test="${!empty ulist}">
            <c:forEach var="uitem" items="${ulist}" varStatus="status">
              <td class="list" align=center>${mhtml:nvl(uitem.bbsread, '0')}/${mhtml:nvl(uitem.bbswrite, '0')}</td>            
            </c:forEach>            
          </c:if>    
               <td class="list" align=center>${mhtml:nvl(item.attscore, '0')}</td>
             </tr>
        </c:forEach>
            </tbody>
            </table>
        </td>
    </tr>
</table>
</form>    
<table border="0" cellpadding="2" cellspacing="1" width="100%">
    <tr>
        <td colspan="4" align="right">
        <a href="javascript:goBack();"><mfmt:button bundle="button" key="prev"/></a>
        </td>
    </tr>
</table>
<script language="javascript"   >
<!--
function goBack()   {
    var frm = getObject("myform");
    frm.submit();    
}
//-->
</script>