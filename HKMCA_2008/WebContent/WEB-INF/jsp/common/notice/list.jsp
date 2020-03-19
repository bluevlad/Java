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
	
	function doView(id){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "view";
			frm.id.value=id;
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
        <mf:form action="${control_action}" method="post" name="myform" id="myform"
            onSubmit="return frmSubmit(this,'list');return false; ">
            <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden" />
            <input type="hidden" name="miv_page" value="1" />
            <input type="hidden" name="cmd" value="list" />
            <input type='hidden' name='id' value=''>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th><mf:label name="title" for="s_title" /></th>
                    <td><mf:input type="text" name="s_title" value="${LISTOP.ht.s_title}" /></td>
                    <th><mf:label name="category" for="s_category" /></th>
                    <td><mf:select  name="s_category" curValue="${LISTOP.ht.s_category}" codeGroup="NOTICE.CATEGORY">
                        <option value="">-</option>
                        </mf:select></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" /> <mf:button
                        onclick="frmReset('myform');" bundle="button" key="reset" /></td>
                </tr>
            </table>
        </mf:form></div>
        </td>
    </tr>
    <tr>
        <td>
        <div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"
            rowAlternateClass="alternateRow">
           <col width="80"/>
           <col width="120"/>
           <col width="*"/>
           <col width="80"/>
           <col width="150"/>
            <thead>
                <tr>

                    <th><mf:header name="id" /></th>
                    <th><mf:header name="category" /></th>
                    <th><mf:header name="title"/></th>
                    <th><mf:header name="view_cnt"/></th>
                    <th><mf:header name="update_dt" /></th>
                </tr>
            </thead>
            <tbody>
             <c:forEach var="item" items="${notices}" varStatus="status">
                    <tr>

                        <td align="center">Notice</td>
                        <td align="center"><mh:out value="${item.category }" td="true" codeGroup="NOTICE.CATEGORY"/></td>
                        <td><a href="javaScript:doView('<c:out value="${item.id}"/>')" /><mh:out value="${item.title}" td="true" /></a></td>

                        <td align="center"><mh:out value="${item.view_cnt}" td="true" /></td>
                        <td align="center"><mh:out value="${item.update_dt}" td="true" format="fulldate"/>, 
                            <mh:out value="${item.update_id}" td="true" />/<mh:out value="${item.owner}" td="true" /></td>
                    </tr>
                </c:forEach>
                
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>

                        <td align="center"> <mh:out value="${item.id}" td="true" /></td>
                        <td align="center"><mh:out value="${item.category }" td="true" codeGroup="NOTICE.CATEGORY"/></td>
                        <td><a href="javaScript:doView('<c:out value="${item.id}"/>')" /><mh:out value="${item.title}" td="true" /></a></td>

                        <td align="center"><mh:out value="${item.view_cnt}" td="true" /></td>
                        <td align="center"><mh:out value="${item.update_dt}" td="true" format="fulldate"/>, 
                            <mh:out value="${item.update_id}" td="true" />/<mh:out value="${item.owner}" td="true" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        </td>
    </tr>
    <tr>
        <td align="center"><c:if test="${MAF_AAM.auth_c}"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /><br></c:if>
        <jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" /></td>
    </tr>
</table>
