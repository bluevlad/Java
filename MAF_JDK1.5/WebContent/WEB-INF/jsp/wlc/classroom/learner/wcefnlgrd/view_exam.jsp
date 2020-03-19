<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript"   src="${CPATH}/js/lib.validate.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
    <input type="hidden" name="${mrt:mvcCmd()}" value="view">
    <input type="hidden" name="leccode" value="${mhtml:nvl(leccode,'')}">
    <input type="hidden" name="userid" value="${mhtml:nvl(userid,'')}">
    <input type="hidden" name="reqnumb" value="${mhtml:nvl(reqnumb,'')}">
    <input type="hidden" name="suserid" value="${mhtml:nvl(userid,'')}">
    <input type="hidden" name="sreqnumb" value="${mhtml:nvl(reqnumb,'')}">
    <input type="hidden" name="exmcode" value="">
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
        <thead>
        <tr>
          <th class="list BL" width="20">No</th>
          <th class="list"><mfmt:message bundle="table.Wlcexmmst" key="wlc_exm_mst.exmtitle"/></th>
          <th class="list"><mfmt:message bundle="table.Wlcexmmst" key="wlc_exm_mst.exm_date"/></th>
          <th class="list"><mfmt:message bundle="table.Wlcexmmst" key="wlc_exm_mst.exmrate"/></th>
          <th class="list"><mfmt:message bundle="table.Wlcexmrst" key="wlc_exm_rst.rstatscore2"/></th>
          <th class="list"><mfmt:message bundle="table.Wlcexmrst" key="wlc_exm_rst.rstmtscore"/></th>
          <th class="list"><mfmt:message bundle="table.Wlcexmrst" key="wlc_exm_rst.rstoffscore1"/></th>
          <th class="list"><mfmt:message bundle="common" key="table.score"/></th>
          <th class="list BR"><mfmt:message bundle="table.Wlcexmrst" key="wlc_exm_rst.rst_sdate"/></th>
        </tr>
        </thead>
        <tbody>
    <jsp:useBean id="now" class="java.util.Date"/>
    <fmt:formatDate var="date" value="${now}" pattern="yyyyMMddHHmm"/>
    <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <c:if var="sw1" test="${(item.exm_sdat_t1 <= date) && (date <= item.exm_edat_t1)}"/>
        <c:set var="sw2" value="false"/>
        <c:if test="${(!empty item.exm_sdat_t2) && (!empty item.exm_edat_t2)}">
          <c:if var="sw2" test="${(item.exm_sdat_t2 <= date) && (date <= item.exm_edat_t2)}"/>
        </c:if>
        <c:set var="sum" value= "${sum + item.exmrate}"/>
        <c:set var="count" value= "${status.count}"/>
        <tr >
           <td class="list" align="center">${mhtml:null2nbsp(item.exmcode)}</td>
           <td class="list" align="center">${mhtml:null2nbsp(item.exmtitle)}</td>
           <td class="list" align=center>
           <c:if test="${sw1}"><blink></c:if>    
               <fmt:parseDate value="${item.exm_sdat_t1}" pattern="yyyyMMddHHmm" var="sdate"/><fmt:formatDate value="${sdate}" pattern="yyyy.MM.dd HH:mm"/>
                ~ <fmt:parseDate value="${item.exm_edat_t1}" pattern="yyyyMMddHHmm" var="edate"/><fmt:formatDate value="${edate}" pattern="yyyy.MM.dd HH:mm"/>
           <c:if test="${sw1}"></blink></c:if>
           <c:choose>
             <c:when test="${(null != item.exm_sdat_t2) && (null != item.exm_edat_t2)}">  
               <c:if test="${sw2}"><blink></c:if>    
               <br><fmt:parseDate value="${item.exm_sdat_t2}" pattern="yyyyMMddHHmm" var="sdate"/><fmt:formatDate value="${sdate}" pattern="yyyy.MM.dd HH:mm"/>
                ~ <fmt:parseDate value="${item.exm_edat_t2}" pattern="yyyyMMddHHmm" var="edate"/><fmt:formatDate value="${edate}" pattern="yyyy.MM.dd HH:mm"/>
               <c:if test="${sw2}"></blink></c:if>
             </c:when>  
           </c:choose>    
           </td>
           <td class="list" align=center>${mhtml:nvl(item.exmrate, '0')} %</td>
           <td class="list" align=center>${mhtml:nvl(item.rstatscore, '0')} </td>
           <td class="list" align=center>${mhtml:nvl(item.rstmtscore, '0')} </td>
           <td class="list" align=center>${mhtml:nvl(item.rstoffscore, '0')} </td>
           <td class="list" align=center>
           <c:choose>
             <c:when test="${(!empty item.rstflag) && (item.rstflag == 'Y')}">
               ${mhtml:nvl(item.usrscore, '')}
             </c:when>
             <c:otherwise>
               <mfmt:message bundle="common" key="table.notscoreing"/>
             </c:otherwise>
           </c:choose>  
           </td>
           <td class="list" align=center>
           <c:choose>
             <c:when test="${(!empty item.rst_sdt)}">
               <fmt:formatDate value="${item.rst_sdt}" pattern="yyyy.MM.dd"/>
             </c:when>
             <c:otherwise>
               <mfmt:message bundle="common" key="table.exm.view.no"/>
             </c:otherwise>
           </c:choose>  
           </td>
         </tr>         
    </c:forEach>
        </tbody>
        </table></td>
    </tr>
</form>    
</table>
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