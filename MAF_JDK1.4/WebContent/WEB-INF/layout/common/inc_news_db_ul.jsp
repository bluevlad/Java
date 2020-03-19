<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:catch var="error">
    <msql:query var="entries" >
SELECT /*+ INDEX_DESC( d IX_MBBS_DATA_03) */
       d.bid, d.c_index, d.c_subject, d.c_date, (SELECT m.subject
                                                   FROM mbbs_meta m
                                                  WHERE m.bid = d.bid) board_nm
  FROM mbbs_data d
 WHERE EXISTS (SELECT *
                 FROM mbbs_meta m0
                WHERE is_use = 'T'
                  AND m0.bid = d.bid
                  AND m0.bid = ?)
   AND d.c_status = 'T'
   AND d.c_level = 1
   AND ROWNUM <= 5
        <msql:param value="${param.bid}"/>
    </msql:query>
</c:catch>
<c:if test="${!empty error}">Error : <c:out value="${error} "/></c:if>
<table  cellspacing="0" cellpadding="0" border="0" width="362">
<c:forEach var="data" items="${entries.rows}">
    <c:url var="url" value="/board/view.do">
        <c:param name="bid" value="${param.bid}" />
        <c:param name="c_index" value="${data.c_index}" />
        <c:param name="LISTOP" value="${MBOARD.listOpStr}" />

    </c:url>
    <tr>
        <td class="main_notice_data"><c:if test="${! empty data.c_category}">
					<a href="javascript:ShowOnly('category','<c:out value="${data.c_category}"/>')"><mh:capsule value="${data.c_category}" left="[" right="]"/></a>
				</c:if>
					<a href='<c:out value="${url}"/>'><mh:out value="${data.c_subject}" bytes="${60-data.c_level}"/></a></td>
    </tr>
    <tr>
        <td colspan="2"><img src='<c:url value="/images/main/board_sep.gif"/>' border="0"></td>
    </tr>
</c:forEach>
</table>
