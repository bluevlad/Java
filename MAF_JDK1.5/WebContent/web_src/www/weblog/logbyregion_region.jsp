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
            SELECT to_char(TRUNC (SYSDATE, 'MM'),'YYYY-MM-DD') sdate, 
                   to_char(TRUNC (ADD_MONTHS (SYSDATE, 1), 'MM') - 1,'YYYY-MM-DD') edate
            FROM DUAL
        </msql:query>
        <c:forEach var="row" items="${entries.rows}">
            <c:set var="sdate" value="${row.sdate }"/>
            <c:set var="edate" value="${row.edate }"/>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:set var="sdate" value="${param.sdate }"/>
        <c:set var="edate" value="${param.edate }"/>
    </c:otherwise>    
</c:choose>


<c:catch var="error">
    <msql:query var="entries">
SELECT   bo.region, NVL (bo.nation, 'N/A') nation, SUM (l.cnt) cnt, GROUPING (region) g_r,
         GROUPING (nation) g_n, COUNT (*) OVER (PARTITION BY bo.region) row_cnt,
         ROW_NUMBER () OVER (PARTITION BY bo.region ORDER BY nation) row_num, SUM (l.distcnt) distcnt,
		 SUM (decode(org_type,'DI',l.distcnt,0)) distcnt_di,
		 SUM (decode(org_type,'DE',l.distcnt,0)) distcnt_de,
		 SUM (decode(org_type,'DI',l.cnt,0)) cnt_di,
		 SUM (decode(org_type,'DE',l.cnt,0)) cnt_de
    FROM (SELECT   usn, COUNT (*) cnt, COUNT (distinct usn) distcnt
              FROM maf_log_user
             WHERE logindt BETWEEN TO_DATE (?, 'YYYY-MM-DD') AND TO_DATE (?, 'YYYY-MM-DD')
          GROUP BY usn) l,
         maf_user mu,
         bas_org bo
   WHERE l.usn = mu.usn
     AND mu.org_cd = bo.org_cd
GROUP BY ROLLUP (bo.region, bo.nation)
        <msql:param value="${sdate }"/>
        <msql:param value="${edate }"/>
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
            <th rowspan="2"> Region </th>
            <th rowspan="2"> Nation </th>
            <th colspan=3"> Login / Users </th>
        </tr>
        <tr>
            <th>Distributer</th>
            <th>Dealer</th>
            <th>ETC</th>
        </tr>
     </thead>
     <tbody>
<c:forEach var="row" items="${entries.rows}">
    
    <tr>
        <c:if test="${row.row_num ==1 }">
        <th rowspan='<c:out value="${row.row_cnt}"/>'><c:choose>
            <c:when test="${row.g_r == 1 }">
                Total
            </c:when>
            <c:otherwise>
                <mh:out value="${row.region}" codeGroup="REGION" td="true"/></c:otherwise>
            </c:choose> </th>
         </c:if>
        <c:choose>
            <c:when test="${row.g_r == 1 && row.g_n == 1 }">
                <td>&nbsp;</td>
            </c:when>
            <c:when test="${row.g_n == 1 }">
                <th>Total by Region</th>
            </c:when>
            <c:otherwise>
                <td align="center"><a href='<c:url value="logbyregion.jsp">
                    <c:param name="sdate" value="${sdate }"/>
                    <c:param name="edate" value="${edate }"/>
                    <c:param name="nation" value="${row.nation}"/>
                </c:url>'><mh:out value="${row.nation}" codeGroup="NATION_CODE" default="${row.nation}" /></a></td>
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
