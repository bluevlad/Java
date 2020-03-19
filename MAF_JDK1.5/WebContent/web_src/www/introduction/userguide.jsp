<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<c:catch var="error">
<msql:query var="entries">
SELECT   c.section, c.crs_cd, c.crs_nm, c.cert_lvl, s.subject_nm, s.sjt_cd,
         ROW_NUMBER () OVER (PARTITION BY c.crs_cd ORDER BY s.subject_nm) crs_rn,
         ROW_NUMBER () OVER (PARTITION BY c.section,  c.cert_lvl ORDER BY c.section,
          c.crs_cd, s.subject_nm) lvl_rn,
		  COUNT (*) OVER (PARTITION BY c.section,  c.cert_lvl) lvl_cnt,
         ROW_NUMBER () OVER (PARTITION BY c.section ORDER BY c.section, c.crs_cd) section_rn,
         COUNT (*) OVER (PARTITION BY c.crs_cd) cnt
    FROM bas_crs_cd c, bas_subject_cd s
   WHERE c.isuse = 'Y'
     AND c.FIELD = 'C'
     AND c.crs_cd = s.crs_cd(+)
	 and nvl(s.isuse,'Y') = 'Y'
ORDER BY c.section, c.cert_lvl, c.crs_cd, s.subject_nm
    </msql:query>
</c:catch>

<c:forEach var="row" items="${entries.rows}">
    <c:if test="${row.section_rn ==1}"> 
    <c:set var="section" value="${row.section}"/>
    <br><h3>#<mh:out value="${row.section}" codeGroup="SECTION"/></h3>
    <table cellpadding="2" cellspacing="0" class="list" width="100%" enableMouseOver="false">
        <col width="100"/>
        <thead>
        <tr>
            <th> Level </th>
        <c:forEach var="r1" items="${entries.rows}">
            <c:if test="${section == r1.section && r1.lvl_rn == 1}">
            <th ><mh:out value="${r1.cert_lvl}" codeGroup="CERT_LVL"/></th>
            </c:if>
        </c:forEach>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th> Course / Subject </th>
            <c:forEach var="r1" items="${entries.rows}">
                <c:if test="${section == r1.section && r1.lvl_rn == 1}">
                <c:set var="lvl" value="${r1.cert_lvl}"/>
            <td valign="top">
                <c:forEach var="r2" items="${entries.rows}">
                    <c:if test="${section == r2.section && lvl == r2.cert_lvl && r2.crs_rn == 1}">
                        <br><strong><mh:out value="${r2.crs_nm}"/></strong><br>
                    </c:if>
                    <c:if test="${section == r2.section && lvl == r2.cert_lvl }">
                    &nbsp;&nbsp;&nbsp;<a href="javascript:showSubjectInfo('<mh:out value="${r2.sjt_cd}" />')">
                    <mh:out value="${r2.subject_nm}"/></a><br>
                    </c:if>
                </c:forEach>
            </td>
                </c:if>
            </c:forEach>
        </tr>
        </tbody>
    </table>
    </c:if>
</c:forEach>

