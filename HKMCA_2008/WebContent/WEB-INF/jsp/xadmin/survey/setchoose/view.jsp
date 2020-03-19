<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doSelect(setid) {
	    var frm = getObject('<c:out value="${param.frm_id}"/>',opener.document);
	    frm.<c:out value="${elm_id}"/>.value=setid;
	    self.close();
    }
    
    function goList() {
	    <c:url var="urlList" value="${control_action}">
	        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	        <c:param name="cmd" value="list"/>
	        <c:param name="frm_id" value="${frm_id}"/>
	        <c:param name="elm_id" value="${elm_id}"/>
	    </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
//-->
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value="edit"/>
<mf:input type='hidden' name='setid' value="${item.setid}"/>
<mf:input type="hidden" name="frm_id" value="${frm_id}"/>
<mf:input type="hidden" name="elm_id" value="${elm_id}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="no_border">
    <tr>
        <td>
			<div class="viewContainer">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
			    <col width="15%" />
			    <col width="35%" />
			    <col width="15%" />
			    <col width="35%" />
			    <tr>
			        <th><mf:header name="setid" /></th>
			        <td><mh:out value="${item.setid}" /></td>
			        <th><mf:header name="setowner" /></th>
			        <td><mh:out value="${item.setowner}" /></td>
			    </tr>
			    <tr>
			        <th><mf:header name="settitle" /></th>
			        <td colspan="3"><mh:out value="${item.settitle}" /></td>
			    </tr>
			    <tr>
			        <th><mf:header name="setdesc" /></th>
			        <td colspan="3"><mh:out value="${item.setdesc}" /></td>
			    </tr>
			</table>
			</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewBtn">
			    <tr>
			        <td align="right">
			            <mf:button bundle="button" key="select" onclick="doSelect('${item.setid}')" /> 
			            <mf:button bundle="button" key="list" onclick="goList();" /></td>
			    </tr>
			</table>
			<br>
			<div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list">
			    <thead>
			        <tr>
			        <th>#</th>
			        <th><mf:header name="quetitle" /></th>
			        </tr>
			    </thead>
			    <tbody>
			    <c:forEach var="si" items="${setitems}" varStatus="status">
			        <tr>
			            <td align="center"><mh:out value="${status.count}" /></td>
			            <td><mh:out value="${si.quetitle}" /></td>
			        </tr>
			    </c:forEach>
			    </tbody>
			</table>
			</div>
        </td>
    </tr>
</table>
</mf:form>