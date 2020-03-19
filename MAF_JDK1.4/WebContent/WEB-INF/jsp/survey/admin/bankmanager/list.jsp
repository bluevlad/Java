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
	
	function doView(code){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "edit";
			frm.queid.value = code; 
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

<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
        <h1><mfmt:message bundle="common" key="searchtitle" /></h1>
        <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
        <input type="image" id="dummy" width="0" height="0" border="0" class="hidden" />
        <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
        <mf:input type="hidden" name="miv_page" value="1" />
        <mf:input type="hidden" name="cmd" value="list" />
        <mf:input type="hidden" name="queid" value="" />
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="15%"/>
				<col width="85%"/>
                <tr>
                    <td><mf:label name="quetitle" /></td>
                    <td><mf:input name="s_quetitle" type="text" size="80" value="${LISTOP.ht.s_quetitle}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" /></td>
                </tr>
            </table>
        </mf:form>
        </div>
        </td>
    </tr>
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list " enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th><mf:header name="queid" /></th>
                    <th><mf:header name="quetitle" /></th>
                    <th><mf:header name="quetype" /></th>
                    <th><mf:header name="active_yn" /></th>
                    <th><mf:header name="update_dt" sort="true"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
                    <td class='center'><a href='javascript:doView("<c:out value="${item.queid}"/>");'/><mh:out value="${item.queid}" td="true"/></a></td>
                    <td align="left" ><a href='javascript:doView("<c:out value="${item.queid}"/>");' title='<mh:out
                        value="${item.quetitle}" escapeJS="true"/>'><mh:out
                        value="${item.quetitle}" td="true" bytes="50"/></a></td>
                    <td class='center'><mh:out value="${item.quetype}" td="true" codeGroup="ETEST.QTYPE" /></td>
                    <td align="center"><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" td="true" /></td>
                    <td align="center"><fmt:formatDate value="${item.update_dt}" pattern="yyyy.MM.dd" /></td>
                </tr>
                </c:forEach>
            </tbody>
	        </table>
        	</div>
        </td>
    </tr>
    <tr>
        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/></td>
    </tr>
    <tr>
        <td align="center"><jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" /></td>
    </tr>
</table>