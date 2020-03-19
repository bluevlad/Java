<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doWrite(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "add";
            frm.submit();
        }
    }
    
    function doView(group_cd){
        var frm = getObject("myform");
        
        if(frm) {
            frm.cmd.value = "edit";
            frm.group_cd.value = group_cd; 
            frm.submit();
        }
    }
    function doSearch(frm) {
        if(frm) {
            frm.cmd.value = "list";
            frm.miv_page.value = 1;
            return true;
        }     
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="list" />
<mf:input type="hidden" name="group_cd" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="search">
                <col width="15%"/>
                <col width="35%"/>
                <col width="15%"/>
                <col width="35%"/>
                <tr>
                    <th><mf:header name="group_cd"/></th>
                    <td><mf:input name="s_group_cd" type="text" cssStyle="width:100%" value="${LISTOP.ht.s_group_cd}" /></td>
                    <th><mf:header name="group_name"/></th>
                    <td><mf:input name="s_group_name" type="text" cssStyle="width:100%" value="${LISTOP.ht.s_group_name}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="0" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" /></td>
                </tr>
            </table>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="group_cd" sort="true"/></th>
                    <th><mf:header name="group_name" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
                    <td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                    <td class='center'><a href='javascript:doView("<c:out value="${item.group_cd}"/>");'><mh:out value="${item.group_cd}" td="true"/></a></td>
                    <td class='center'><a href='javascript:doView("<c:out value="${item.group_cd}"/>");'><mh:out value="${item.group_name}" td="true" /></a></td>
                </tr>
                </c:forEach>
            </tbody>
            </table>
            </div>
        </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
    <tr>
        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/></td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" />