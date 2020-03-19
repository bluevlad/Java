<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language='javascript' src='<c:url value="/js/lib.validate.js"/>' ></script>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="view">
        <thead>
        <tr>
          <th width="55"><mfmt:message bundle="table.wlb_lec_chp" key="itm_sequence"/></th>
          <th ><mfmt:message bundle="table.wlb_lec_chp" key="itm_title"/></th>
          <th ><mfmt:message bundle="table.wla_rat_mst" key="jindo_page"/></th>
          <th ><mfmt:message bundle="table.wla_rat_mst" key="jindo_time"/></th>
        </tr>
        </thead>
        <tbody>
    <c:forEach var="sitem" items="${navigator.list}" varStatus="status">
      <c:set var="avg_page" value=""/>
      <c:choose>
        <c:when test="${sitem.t_page > 0}">
          <fmt:formatNumber var="avg_page" value="${(sitem.j_page*100.0)/(sitem.t_page)}" pattern="##.#"/>
        </c:when>
        <c:otherwise>
          <c:set var="avg_page" value="0"/>
        </c:otherwise>
      </c:choose>
      <c:set var="avg_time" value=""/>
      <c:choose>
        <c:when test="${sitem.t_time > 0}">
          <fmt:formatNumber var="avg_time" value="${(sitem.j_time*100.0)/(sitem.t_time)}" pattern="###.#"/>
        </c:when>
        <c:otherwise>
          <c:set var="avg_time" value="100"/>
        </c:otherwise>
      </c:choose>
        <tr >
           <td align="center"><mh:out value="${sitem.itm_sequence}" td="true" /></td>
           <td align="left"><mh:out value="${sitem.itm_title}" td="true" /></td>
            <c:choose>
              <c:when test="${avg_page >= 100.0}">
            <td align="left">
                <img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph10.gif" width="100" height="12"/>100.0% (<mh:out value="${sitem.t_page}" td="true" />P/<mh:out value="${sitem.t_page}" td="true" />P)
            </td>
              </c:when>
              <c:otherwise>
            <td align="left">
                <c:if test="${avg_page > 0.0}">
                <img src='<c:url value="/maf_images/graph/graph10.gif"/>' width="<mh:out value="${avg_page}" />" height="12"/>
                </c:if><mh:out value="${avg_page}" td="true" />% (<mh:out value="${sitem.j_page}" td="true" />P/<mh:out value="${sitem.t_page}" td="true" />P)    
            </td>
               </c:otherwise>
             </c:choose>  
            <td align="left">
                <c:if test="${avg_time > 0.0}" >
                <img src='<c:url value="/maf_images/graph/graph10.gif"/>' width="<mh:out value="${avg_time}"  />" height="12"/>
                </c:if><mh:out value="${avg_time}" td="true" />% (<fmt:formatNumber var="s_avg_time" value="${(sitem.s_time/60)}" pattern="###"/><mh:out value="${s_avg_time}" td="true" /><mfmt:message bundle="common" key="table.time.min"/>)
            </td>
         </tr>
    </c:forEach>
        </tbody>
        </table></td>
    </tr>
</table>
<br/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <tr>
        <th width=35% align="center"><mfmt:message bundle="common" key="table.total.list"/><mfmt:message bundle="common" key="title.wlb_chp_prg.total_time"/></th>
        <td width=15% align="right" bgcolor="#FFFFFF"><mh:out value="${totaltime}" td="true" /> <mfmt:message bundle="common" key="table.time.min"/></td>
        <th width=35% align=center><mfmt:message bundle="common" key="title.wlb_chp_prg.limit_study_time"/></th>
        <td width=15% align=right bgcolor="#FFFFFF"><mh:out value="${limit_study_time}" td="true" /> <mfmt:message bundle="common" key="table.time.min"/></td>
    </tr>
</table>
