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
	
	function doView(surveyid){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "view";
			frm.surveyid.value=surveyid;
			frm.submit();
		}
	}
    
    function doEdit(surveyid){
        var frm = getObject("myform");
        
        if(frm) {
            frm.cmd.value = "edit";
            frm.surveyid.value=surveyid;
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
            <mf:form action="${control_action}" method="get" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false;">
            <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <mf:input type="hidden" name="miv_page" value="1"/>
            <mf:input type="hidden" name="cmd" value="list"/>
            <mf:input type="hidden" name="surveyid" value=""/>
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <col width="15%">
                <col width="85%">
                <tr>
                    <th><mf:label name="surveytitle" for="s_surveytitle" /></th>
                    <td><mf:input type="text" name="s_surveytitle" size="50" value="${LISTOP.ht.s_surveytitle}" /></td>
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
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th><mf:header name="surveyid" /></th>
                    <th><mf:header name="surveytitle" /></th>
                    <th><mf:header name="survey_sdat" /></th>
                    <th><mf:header name="survey_edat" /></th>
                    <th><mfmt:message bundle="common" key="poll.view.yes"/>&nbsp;/&nbsp;<mfmt:message bundle="common" key="reqnumber"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td align="center"><a href="javaScript:doEdit('<c:out value="${item.surveyid}"/>')" /><mh:out value="${item.surveyid}" td="true" /></a></td>
                        <td align="center"><a href="javaScript:doEdit('<c:out value="${item.surveyid}"/>')" /><mh:out value="${item.surveytitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.survey_sdat}" td="true" /></td>
                        <td align="center"><mh:out value="${item.survey_edat}" td="true" /></td>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.surveyid}"/>')" />[ <b><mh:out value="${item.srv}" /></b>&nbsp;/&nbsp;<mh:out value="${item.cnt}" /> ]</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
		<table width="100%" border="0" cellspacing="0" cellpadding="2">
		    <tr>
		        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
		    </tr>
		</table>
        </div>
        </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>