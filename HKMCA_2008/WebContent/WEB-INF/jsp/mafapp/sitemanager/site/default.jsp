<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
<!--
    function frmSave(site) {
        var frm = getObject("myform");  
			frm.cmd.value = "update";
            frm.site.value = site;
			frm.submit();
    }
    function frmDelete(site) {
        var frm = getObject("myform");
        if(confirm('<mfmt:message bundle="common.scripts" key="alert.info2"/>')){
            frm.cmd.value = "delete";
            frm.site.value = site;
            frm.submit();
        }
    }
    function frmAdd() {
        var frm = getObject("myform");  
        frm.cmd.value = "insert";
        frm.submit();
    }
//-->       
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<table border="0" cellpadding="2" cellspacing="1" class="list"width="100%">
    <col width="15%"/>
    <col width="*"/>
    <col width="25%"/>
    <col width="25%"/>
    <col width="20%"/>
    <thead>
    <tr>
        <th><mfmt:message bundle="common" key="site" /></th>
        <th><mfmt:message bundle="common" key="site_title" /></th>
        <th><mfmt:message bundle="common" key="layout"/></th>    
        <th><mfmt:message bundle="common" key="url"/></th>    
        <th><mfmt:message bundle="button" key="delete" /></th>      
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td align="center"><mh:out value="${list.site}"/></td>
        <td align="center"><mf:input name="title_${list.site}" cssStyle="width:90%" value="${list.site_title}"/></td>
        <td align="center"><mf:input name="layout_${list.site}" cssStyle="width:90%" value="${list.layout}"/></td>
        <td align="center"><mf:input name="url_${list.site}" cssStyle="width:90%" value="${list.url}"/></td>
        <td align="center">
            <mf:button bundle="button" key="save" onclick="frmSave('${list.site}')" icon="icon_save"/>&nbsp;
            <mf:button bundle="button" key="delete" onclick="frmDelete('${list.site}')" icon="icon_delete"/>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="list">
    <col width="15%"/>
    <col width="*"/>
    <col width="25%"/>
    <col width="25%"/>
    <col width="10%"/>
    <thead>
    <tr>
        <th><mfmt:message bundle="common" key="site" /></th>
        <th><mfmt:message bundle="common" key="site_title" /></th>
        <th><mfmt:message bundle="common" key="layout"/></th>    
        <th><mfmt:message bundle="common" key="url"/></th>    
        <th>#</th>  
    </tr>
    </thead>
    <tbody>
    <tr>
        <td align="center"><mf:input name="site" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:input name="site_title" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:input name="layout" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:input name="url" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:button bundle="button" key="add" onclick="frmAdd()" icon="icon_save"/></td>
    </tr>
    </tbody>
</table>
</mf:form>