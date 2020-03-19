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

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<input type="image" id="dummy" width="0" height="0" border="0" class="hidden" />
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="list" />
<mf:input type="hidden" name="queid" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
        <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="search">
				<col width="15%"/>
				<col width="85%"/>
                <tr>
                    <th><mfmt:message bundle="survey" key="quetitle" /></th>
                    <td><mf:input name="s_quetitle" type="text" cssStyle="width:98%" value="${LISTOP.ht.s_quetitle}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="0" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" icon="icon_search" /></td>
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
                    <th><mf:header name="queid" sort="true"/></th>
                    <th><mf:header name="quetitle" sort="true" /></th>
                    <th><mf:header name="quetype" sort="true" /></th>
                    <th><mf:header name="active_yn" sort="true" /></th>
                    <th><mf:header name="upt_dt" sort="true"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
                    <td class='center'><a href='javascript:doView("<c:out value="${item.queid}"/>");'/><mh:out value="${item.queid}" td="true"/></a></td>
                    <td align="left">
                        <a href='javascript:doView("<c:out value="${item.queid}"/>");' title='<mh:out value="${item.quetitle}" escapeJS="true"/>'>
                        <mh:out value="${item.quetitle}" td="true" bytes="50"/></a>
                    </td>
                    <td class='center'><mh:out value="${item.quetype}" td="true" codeGroup="ETEST.QTYPE" /></td>
                    <td align="center"><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" td="true" /></td>
                    <td align="center"><mh:out value="${item.upt_dt}" format="fulldate" /></td>
                </tr>
                </c:forEach>
            </tbody>
	        </table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewBtn">
			    <tr>
			        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/></td>
			    </tr>
			</table>
        	</div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" />