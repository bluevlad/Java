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

    function doView(sjt_cd, htmcode){
        var frm = getObject("myform");

        if(frm) {
            frm.cmd.value = "edit";
            frm.sjt_cd.value=sjt_cd;
            frm.htmcode.value=htmcode;
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

    function doDelete() {
        var frm = getObject("myform");
        if((frm)) {
            frm.cmd.value = "delete";
            frm.submit();
        }
    }

    function doExcelUp() {
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "add_excel";
            frm.submit();
        }
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type='hidden' name='sjt_cd' value=''/>
<mf:input type='hidden' name='htmcode' value=''/>
<mf:input type="hidden" name="destination" value="${destination}"/> 
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
            <col width="15%">
            <col width="35%">
            <col width="15%">
            <col width="35%">
            <tr>
                <th><mf:label name="sjt_nm"/></th>
                <td colspan="3">
	                <jsp:include page="/WEB-INF/jsp/common/sel_sjt.jsp" flush="true">
	                    <jsp:param name="os_crs" value='<%=request.getParameter("os_crs")%>'/>
	                    <jsp:param name="os_sjt" value='<%=request.getParameter("os_sjt")%>'/>
	                    <jsp:param name="destination" value='<%=request.getAttribute("destination")%>'/>
	                </jsp:include>
                </td>
             </tr>
            <tr>
                <th><mf:label name="daename"/></th>
                <td colspan="3"><mf:input type="text" name="daename" value="${LISTOP.ht.daename}" cssStyle="width:250px"/></td>
             </tr>
            <tr>
                <th><mf:label name="cenname"/></th>
                <td><mf:input type="text" name="cenname" value="${LISTOP.ht.cenname}" cssStyle="width:200px"/></td>
                <th><mf:label name="soname"/></th>
                <td><mf:input type="text" name="soname" value="${LISTOP.ht.soname}" cssStyle="width:200px"/></td>
             </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" icon="icon_search"/></td>
                </tr>
            </table>
            </div>
        </td>
    </tr>
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="5%"/>
                <col width="5%"/>
                <col width="20%"/>
                <col width="*"/>
                <col width="25%"/>
                <col width="10%"/>
		        <thead>
		        <tr>
		            <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','htmcode_chk')"/></th>
		            <th>#</th>
                    <th><mf:header name="sjt_nm" sort="true"/></th>
		            <th><mf:header name="daename" sort="true"/></th>
		            <th><mf:header name="cenname" sort="true"/></th>
		            <th><mf:header name="cnt_type"/></th>
		        </tr>
		        </thead>
		        <tbody>
		        <c:forEach var="item" items="${navigator.list}" varStatus="status">
		        <tr >
		            <td align="center"><mf:input type="checkbox" name="htmcode_chk" value="${item.sjt_cd}#${item.htmcode}"/></td>
		            <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
                    <td align="center"><a href="javaScript:doView('<mh:out value="${item.sjt_cd}"/>','<mh:out value="${item.htmcode}"/>')"/><mh:out value="${item.sjt_nm}" td="true"/></a></td>
		            <td align="center"><a href="javaScript:doView('<mh:out value="${item.sjt_cd}"/>','<mh:out value="${item.htmcode}"/>')"/><mh:out value="${item.daename}" td="true" /></a></td>
		            <td align="center"><a href="javaScript:doView('<mh:out value="${item.sjt_cd}"/>','<mh:out value="${item.htmcode}"/>')"/><mh:out value="${item.cenname}" td="true" /></a></td>
		            <td align="center"><mh:out value="${item.cnt_type}" codeGroup="LEC.CNT_TYPE"/></td>
		        </tr>
		        </c:forEach>
		        </tbody>
	        </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
			    <tr>
			        <td align="right">
			            <mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
			            <mf:button onclick="doDelete()" bundle="button" key="delete" icon="icon_delete"/>
			            <mf:button onclick="doExcelUp()" bundle="button" key="excel.up" icon="icon_search"/>
			        </td>
			    </tr>
			</table>
	        </div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true">
    <jsp:param name="os_crs" value='<%=request.getAttribute("os_crs")%>'/>
    <jsp:param name="os_sjt" value='<%=request.getAttribute("os_sjt")%>'/>
    <jsp:param name="destination" value='<%=request.getAttribute("destination")%>'/>
</jsp:include>