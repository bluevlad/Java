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
<c:choose>
    <c:when test="${empty param.sdate  || empty param.edate}" >
        <msql:query var="entries">
            SELECT to_char(TRUNC (SYSDATE, 'MM'),'YYYY') lecyear
            FROM DUAL
        </msql:query>
        <c:forEach var="row" items="${entries.rows}">
            <c:set var="lecyear" value="${row.lecyear }"/>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:set var="sdate" value="${param.lecyear }"/>
    </c:otherwise>    
</c:choose>


<c:catch var="error">
    <msql:query var="entries">
SELECT   hkmca_get.get_org_name (r.orgn_cd) org_name_disp, r.*
    FROM (SELECT   cl.orgn_cd, cl.otype, COUNT (DISTINCT lr.userid) cnt,
                   SUM (DECODE (req_stat, 'LE', 1, 0)) cnt_le,
                   SUM (DECODE (is_grad, 'G', 1, 0)) cnt_grad, GROUPING (cl.orgn_cd) g_orgn_cd,
                   ROW_NUMBER () OVER (PARTITION BY cl.orgn_cd ORDER BY cl.orgn_cd) row_num,
                   ROW_NUMBER () OVER (PARTITION BY cl.orgn_cd, cl.otype ORDER BY cl.otype)
                                                                                      row_num_otype
              FROM bas_class cl, wcc_lec_req lr
             WHERE cl.leccode = lr.leccode
               AND cl.lec_year = ?
          GROUP BY ROLLUP (cl.orgn_cd, cl.otype)) r,
         bas_org org
   WHERE r.orgn_cd = org.org_cd
ORDER BY DECODE (org.org_type,
                 'H', '0' || org_name_disp,
                 'TC', '1' || org_name_disp,
                 'DI', '2' || org_name_disp,
                 'DE', '2' || org_name_disp,
                 '9' || org_name_disp
                )
        <msql:param value="${lecyear }"/>
    </msql:query>
</c:catch>
<c:if test="${!empty error}">Error : <c:out value="${error} "/></c:if>
<div class="searchContainer">
<h1><mfmt:message bundle="common" key="searchtitle"/></h1>
    <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch();return false; ">
    <mf:input type="hidden" name="cmd" value="list"/>
    <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
        
        <tr>
            <td>
                <mf:input type="text" name="sdate" size="15" value="${sdate}" cssClass="dropdown" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/> ~
                <mf:input type="text" name="edate" size="15" value="${edate}" cssClass="dropdown" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/>
            </td>
         </tr>
    </table>
    <table border="0" cellspacing="0" cellpadding="2" class="searchBtn" width="100%">
        <tr>
            <td align="right">
                <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
            </td>
        </tr>
     </table>
     </mf:form>
</div>   
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableMouseOver="false">
    <thead>
        <tr>
            <th > OrgName </th>
            <th > Online / Offline </th>
            <th > 수강자 </th>
            <th > 수료자 / 완료자 </th>
        </tr>
       
     </thead>
     <tbody>
<c:forEach var="row" items="${entries.rows}">
    
    <tr>
        <c:if test="${row.row_num ==1 }">
        <th rowspan='<c:out value="${row.row_cnt}"/>'><c:choose>
            <c:when test="${row.g_orgn_cd == 1 }">
                Total
            </c:when>
            <c:otherwise>
                <mh:out value="${row.org_name_disp}" td="true"/></c:otherwise>
            </c:choose> </th>
         </c:if>
        <c:choose>
            <c:when test="${row.g_orgn_cd == 1 && row.g_orgn_cd == 1 }">
                <td>&nbsp;</td>
            </c:when>
            <c:when test="${row.g_n == 1 }">
                <th>Total by Org</th>
            </c:when>
            <c:otherwise>
                <td align="center"><mh:out value="${row.otype}"  /></td>
            </c:otherwise>
            </c:choose> 
            
        <td align="right"><mh:out value="${row.cnt_di}" td="true" format="currency"/> /
                            <mh:out value="${row.distcnt_di}" td="true" format="currency"/> </td>
        <td align="right"><mh:out value="${row.cnt_de}" td="true" format="currency"/> /
                        <mh:out value="${row.distcnt_de}" td="true" format="currency"/>  </td>
        <td align="right"><mh:out value="${row.cnt-(row.cnt_di + row.cnt_de)}" td="true" format="currency"/> /
                        <mh:out value="${row.distcnt-(row.distcnt_di + row.distcnt_de)}" td="true" format="currency"/> </td>
    </tr>
</c:forEach>
    </tbody>
    </table>
