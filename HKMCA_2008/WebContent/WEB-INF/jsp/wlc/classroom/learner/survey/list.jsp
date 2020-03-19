<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(surveyid){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "view";
			frm.surveyid.value=surveyid;
			frm.submit();
		}
	}
//-->
</SCRIPT>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="surveyid" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"  rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th><mf:header name="surveyid" /></th>
                    <th><mf:header name="surveytitle" /></th>
                    <th><mf:header name="survey_sdat" /></th>
                    <th><mf:header name="survey_edat" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list}" varStatus="status">
                    <tr>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.surveyid}"/>')" /><mh:out value="${item.surveyid}" td="true" /></a></td>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.surveyid}"/>')" /><mh:out value="${item.surveytitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.survey_sdat}" td="true" /></td>
                        <td align="center"><mh:out value="${item.survey_edat}" td="true" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        </td>
    </tr>
</table>
</mf:form>