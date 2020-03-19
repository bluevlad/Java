<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<script language="javascript" src='<c:url value="/js/calendar.js"/>' ></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function doSearch() {
        var frm = getObject("myform");
        if(frm) {
            frm.submit();
        }     
    }
//-->
</SCRIPT>  

<c:set var="sdate" value="${param.sdate }"/>
<c:set var="edate" value="${param.edate }"/>
<c:catch var="error">
    <msql:query var="entries">
        SELECT bo.org_name, bo.org_type, r.cnt, r.org_cd, distcnt
      FROM (SELECT   bo.org_cd, SUM (l.cnt) cnt, SUM (l.distcnt) distcnt
                FROM (SELECT   usn, COUNT (*) cnt,  COUNT (distinct usn) distcnt
                          FROM maf_log_user
                         WHERE logindt BETWEEN TO_DATE (?, 'YYYY-MM-DD')
                                           AND TO_DATE (?, 'YYYY-MM-DD')
                      GROUP BY usn) l,
                     maf_user mu,
                     bas_org bo
               WHERE l.usn = mu.usn
                 AND mu.org_cd = bo.org_cd
                 AND bo.nation = ?
            GROUP BY bo.org_cd) r,
           bas_org bo
     WHERE r.org_cd = bo.org_cd
     order by decode(org_type,'DI','0' || org_name,'1' || org_name)
        <msql:param value="${sdate }"/>
        <msql:param value="${edate }"/>
        <msql:param value="${param.nation }"/>
    </msql:query>
</c:catch>
<c:if test="${!empty error}">Error : <c:out value="${error} "/></c:if>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableMouseOver="false">
    <thead>
        <tr>
            <th> Dist / Dealer </th>
            <th> Type</th>
            <th> Login / Users </th>
        </tr>
     </thead>
     <tbody>
<c:set var="tot" value="0"/>     
<c:set var="tot_dist" value="0"/>    
<c:forEach var="row" items="${entries.rows}">
    <tr>
        <td align="center"><a href='<c:url value="/info/OrgInfo.do">
            <c:param name="cmd" value="view"/>
            <c:param name="org_cd" value="${row.org_cd}"/>
        </c:url>' target="new"><mh:out value="${row.org_name}" td="true"/></a></td>
        <td align="center"><mh:out value="${row.org_type}" td="true"/> </td>
        <td align="right"><mh:out value="${row.cnt}" td="true" format="currency"/> /
                        <mh:out value="${row.distcnt}" td="true" format="currency"/> </td>
        <c:set var="tot" value="${tot+row.cnt}"/> 
        <c:set var="tot_dist" value="${tot_dist+row.distcnt}"/>
    </tr>
</c:forEach>
    <tr>
        <th colspan="2">Total</th>
        <td align="right"><mh:out value="${tot}" td="true" format="currency"/> / <mh:out value="${tot_dist}" td="true" format="currency"/> </td>
    </tr>
    <tr>
        <td colspan="4" align="center"><a href="javascript:history.back();">Back</a></td>
    </tr>
    </tbody>
    </table>