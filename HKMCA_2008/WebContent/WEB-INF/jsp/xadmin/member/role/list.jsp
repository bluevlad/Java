<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doWrite(){
        var frm = getObject("myform");
            if(frm) {
            frm.cmd.value = "add";
            frm.submit();
            }
    }
    function doView(id){
        var frm = getObject("myform");
            if(frm) {
                frm.role_id.value = id;
                frm.cmd.value = "edit";
                frm.submit();
            }
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="role_id" value=""/> 
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list"  enableAlternateRows="true" rowAlternateClass="alternateRow">
			    <col width="5%"/>
			    <col width="15%"/>
			    <col width="*"/>
			    <col width="15%"/>
                <col width="15%"/>
                <col width="15%"/>
				<thead>
				<tr>
					<th><mfmt:message bundle="common" key="no" /></th>
					<th><mf:header name="role_id" sort="true" /></th>
					<th><mf:header name="role_name" sort="true" /></th>
					<th><mf:header name="is_super" sort="true" /></th>
                    <th><mf:header name="bbs_level" sort="true" /></th>
					<th><mf:header name="site" sort="true" /></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
					<td class="center"><a href="javaScript:doView('<c:out value="${item.role_id}"/>')" ><mh:out value="${item.role_id}" td="true"/></a></td>
					<td class="center"><a href="javaScript:doView('<c:out value="${item.role_id}"/>')" ><mh:out value="${item.role_name}"  td="true"/></a></td>
					<td class="center"><mh:out value="${item.is_super}" codeGroup="ACTIVE_YN" td="true" /></td>
					<td class="center"><mh:out value="${item.bbs_level}" codeGroup="BBS.BRD_LEVEL" td="true" /></td>
                    <td class="center"><mh:out value="${item.site}" td="true" /></td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
			    <tr>
			        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
			    </tr>
			</table>
            </div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>