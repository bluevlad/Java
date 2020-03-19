<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(exmid){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "view";
			frm.exmid.value=exmid;
			frm.submit();
		}
	}
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="exmid" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
	            <thead>
	                <tr>
	                    <th><mf:header name="exmid" sort="true" /></th>
	                    <th><mf:header name="exmtitle" sort="true" /></th>
	                    <th><mf:header name="exm_sdat" sort="true" /></th>
	                    <th><mf:header name="exm_edat" sort="true" /></th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="item" items="${item}">
	                    <tr>
	                        <td align="center"><a href="javaScript:doView(<c:out value='${item.exmid}'/>)" /><mh:out value="${item.exmid}" td="true" /></a></td>
	                        <td align="center"><a href="javaScript:doView(<c:out value='${item.exmid}'/>)" /><mh:out value="${item.exmtitle}" td="true" /></a></td>
	                        <td align="center"><mh:out value="${item.exm_sdat}" td="true" /></td>
	                        <td align="center"><mh:out value="${item.exm_edat}" td="true" /></td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	        </div>
        </td>
    </tr>
</table>
</mf:form>