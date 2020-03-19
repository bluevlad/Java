<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script>
function doSurvey(surveyid){
    document.location = CPATH+"/survey/survey.do?cmd=view&surveyid="+surveyid;
}

</SCRIPT>
<table width="95%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"
    rowAlternateClass="alternateRow">
    <thead>
        <tr>
            <th nowrap><mfmt:message key="surveyid" bundle="table.survey_mst" /></th>
            <th nowrap><mfmt:message key="surveytitle" bundle="table.survey_mst" /></th>
            <th nowrap><mfmt:message key="survey_sdat_t1" bundle="table.survey_mst" /></th>
            <th nowrap><mfmt:message key="survey_edat_t1" bundle="table.survey_mst" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${list}" varStatus="status">
            <tr>
                <td align="center"><a href="javaScript:doSurvey('<c:out value="${item.surveyid}"/>')" /><mh:out
                    value="${item.surveyid}" td="true" /></a></td>
                <td align="center"><a href="javaScript:doSurvey('<c:out value="${item.surveyid}"/>')" /><mh:out
                    value="${item.surveytitle}" td="true" /></a></td>
                <td align="center"><mh:out value="${item.survey_sdat_t1}" format="fulldate" td="true" /></td>
                <td align="center"><mh:out value="${item.survey_edat_t1}" format="fulldate" td="true" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
