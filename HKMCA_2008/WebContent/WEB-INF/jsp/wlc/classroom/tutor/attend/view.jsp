<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
                <thead>
                <tr>
                    <th><mfmt:message key="att_dat" bundle="table.wlc_att_log"/></th>
                    <th><mfmt:message key="acs_s_tm" bundle="table.wlc_att_log"/></th>
                    <th><mfmt:message key="acs_e_tm" bundle="table.wlc_att_log"/></th>
                </tr>
                </thead>
                <c:forEach var="list" items="${list}">
                <tr>
                    <td class="center"><mh:out value="${list.att_dat}" /></td>
                    <td class="center"><mh:out value="${list.acs_s_tm}" format="fulldatetime" /></td>
                    <td class="center"><mh:out value="${list.acs_e_tm}" format="fulldatetime" /></td>
                </tr>
                </c:forEach>
            </table>