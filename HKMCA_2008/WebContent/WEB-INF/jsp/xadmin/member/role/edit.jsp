<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>   	

<script language="javascript" >
<!--
	function frmSubmit() {
		var frm = getObject("myform");	
		if (validate(frm)) {
            <c:choose>
            <c:when test="${param.cmd == 'edit'}">
                frm.cmd.value = "update";
            </c:when>
            <c:when test="${param.cmd == 'add'}">
                frm.cmd.value = "insert";
            </c:when>
            <c:otherwise>
                frm.cmd.value = "insert";
            </c:otherwise>
            </c:choose>
			frm.submit();
		} else {
			return;
		}
	}
	function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<c:out value="${urlList}"/>';
    }
	function doDelete() {
		var frm = getObject("myform");	
		frm.cmd.value = "delete";
		frm.submit();
	}
    function doSearch() {
	    var frm = getObject("myform");
			if(frm) {
    		    frm.cmd.value = "edit";
                frm.miv_page.value = 1;
    		    frm.submit();
			}     
    }
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="20%"/>
    <col width="30%"/>
    <col width="20%"/>
    <col width="30%"/>
    <tr>
        <th><mf:header name="role_id" /></th> 
        <td><mf:input type="text" name="role_id" cssStyle="width:120px" value="${item.role_id}"/></td>
        <th><mf:header name="role_name" /></th> 
        <td><mf:input type="text" name="role_name" cssStyle="width:98%" value="${item.role_name}"/></td>
    </tr>
    <tr>
        <th><mf:header name="is_super" /></th> 
        <td><mf:select name="is_super" curValue="${item.is_super}" codeGroup="ACTIVE_YN"/></td>
        <th><mf:header name="bbs_level" /></th> 
        <td><mf:select name="bbs_level" curValue="${item.bbs_level}" codeGroup="BBS.BRD_LEVEL"/></td>
    </tr>
    <tr>
        <th><mf:header name="site" /></th> 
        <td colspan="3"><mf:select name="site" items="${sites}" keyValue="site" keyText="site_title" curValue="${item.site}" /></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="common" key="comments" /></th> 
        <td colspan="3"><mf:input type="text" name="comments" cssStyle="width:98%" value="${item.comments}"/></td>
    </tr>
</table>

<br>
<c:if test="${!empty(list)}">
<table border="0" cellspacing="0" cellpadding="2" class="list" width="100%" enableAlternateRows="true" rowAlternateClass="alternateRow">
    <thead>
    <tr>
        <th><mfmt:message bundle="common" key="no" /></th>
        <th><mf:header name="userid" sort="true" /></th>
        <th><mf:header name="usernm" sort="true" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td class=" center"><mf:input type="checkbox" name="vusn" value="${list.usn}" checked="true"/></td>
        <td class=" center"><mh:out value="${list.userid}" td="true"/></td>
        <td class=" center"><mh:out value="${list.nm}" td="true"/></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>

<br><br>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <c:if test="${param.cmd == 'edit'}">
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="view">
			    <col width="15%"/>
			    <col width="30%"/>
			    <col width="15%"/>
			    <col width="30%"/>
                <col width="10%"/>
                <tr>
                    <th><mf:header name="usernm"/></th>
                    <td><mf:input type="text" name="nm" cssStyle="width:95%" value="${LISTOP.ht.nm}"/> </td>
                    <th><mf:header name="userid"/></th>
                    <td><mf:input type="text" name="userid" cssStyle="width:95%" value="${LISTOP.ht.userid}"/></td>
                    <td><mf:button onclick="doSearch()" bundle="button" key="search" /></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table border="0" cellspacing="0" cellpadding="2" class="list" width="100%" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="10%"/>
                <col width="30%"/>
                <col width="*"/>
                <thead>
                <tr>
                    <th><mfmt:message bundle="common" key="no" /></th>       
                    <th><mf:header name="userid"/></th>     
                    <th><mf:header name="usernm"/></th>     
                </tr>
                </thead>
                <tbody>
                <c:forEach var="nlist" items="${navigator.list}" varStatus="status">
                <tr>    
                    <td align="center"><mf:input type="checkbox" name="vusn" value="${nlist.usn}"/></td>
                    <td align="center"><mh:out value="${nlist.userid}" td="true"/></td>
                    <td align="center"><mh:out value="${nlist.nm}" td="true"/></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </td>
    </tr>
    </c:if>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="frmSubmit()" icon="icon_save" />
            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
            <c:if test="${param.cmd == 'edit'}">
            <mf:button bundle="button" key="delete" onclick="doDelete()" icon="icon_delete"/>
            </c:if>
        </td>
    </tr>
</table>
</mf:form>      
<jsp:include page="navigator.jsp"/>