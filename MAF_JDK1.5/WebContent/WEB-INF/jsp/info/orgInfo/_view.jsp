<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<c:url var="tabUrl" value="${control_action}"><c:param name="org_cd" value="${item.org_cd}"/></c:url>
<script>
    function doEdit() {
        var frm = getObject("myform");
        
			frm.org_cd.value = '<c:out value="${item.org_cd}"/>';
		 		
        frm.submit();
    }
    function goList() {
	        <c:url var="urlList" value="${control_action}">
	            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	            <c:param name="cmd" value="list"/>
	        </c:url>
	        document.location = '<mh:out value="${urlList}"/>';
	    }
    function viewPhoto(img) {
        popupWindow('<c:url value="/web_src/viewImg.jsp"/>?img='+img, "photo", 'no');
    }
    function tabGo(url) {
        document.location = url;
    }
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doEdit(); ">
    <input type="hidden" name="cmd" value="edit">
    <input type='hidden' name='org_cd' value="">
</mf:form>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="20%" />
    <col width="30%" />
    <col width="20%" />
    <col width="30%" />
    <tbody>
        <tr>
            <th nowrap><mf:header name="org_cd" /></th>
            <td><mh:out value="${item.org_cd}" td="true"/></td>
            <th nowrap><mf:header name="p_org_cd" /></th>
            <td><mh:out value="${item.p_org_cd}" td="true"/></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="org_type" /></th>
            <td><mh:out value="${item.org_type}" td="true" codeGroup="ORG_TYPE"/></td>
            <th nowrap><mf:header name="region" /></th>
            <td><mh:out value="${item.region}" codeGroup="REGION" td="true"/></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="org_name" /></th>
            <td><mh:out value="${item.org_name}" td="true"/></td>
            <th nowrap><mf:header name="nation" /></th>
            <td><mh:out value="${item.nation}" codeGroup="NATION_CODE" td="true"/></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="dist_no" /></th>
            <td><mh:out value="${item.dist_no}" td="true"/></td>
            <th nowrap><mf:header name="deal_no" /></th>
            <td><mh:out value="${item.deal_no}" td="true"/></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="deal_region" /></th>
            <td colspan="3"><mh:out value="${item.deal_region}" td="true"/></td>
        </tr>
    </tbody>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="center"><mf:button bundle="button" key="goList" onclick="goList();" /></td>
    </tr>
</table>
<br>
<c:url var="tabUrl" value="${control_action}"><c:param name="org_cd" value="${item.org_cd}"/></c:url>

<input type="button" value="Photo" onClick="tabGo('<c:url value="${tabUrl}"><c:param name="cmd" value="photo"/></c:url>')"/>
<input type="button" value="Facility" onClick="tabGo('<c:url value="${tabUrl}"><c:param name="cmd" value="faci"/></c:url>')"/>
<input type="button" value="Organization" onClick="tabGo('<c:url value="${tabUrl}"><c:param name="cmd" value="orgn"/></c:url>')"/>
<input type="button" value="Technical Training" onClick="tabGo('<c:url value="${tabUrl}"><c:param name="cmd" value="tech"/></c:url>')"/>
    <mh:codeList var="codes" codeGroup="SECTION" />
    
    <c:forEach var="i" items="${codes}" varStatus="status">

        <input type="button" value='<c:out value="${i.codeName}"/>' onClick="tabGo('<c:url value="${tabUrl}"><c:param name="cmd" value="user"/><c:param name="section" value="${i.codeNo}"/></c:url>')"/>

    </c:forEach>
<input type="button" value="Etc" onClick="tabGo('<c:url value="${tabUrl}"><c:param name="cmd" value="user"/><c:param name="section" value="etc"/></c:url>')"/>

