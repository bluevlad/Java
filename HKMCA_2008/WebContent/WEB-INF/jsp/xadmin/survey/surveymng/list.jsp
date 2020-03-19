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
	
	function doEdit(surveyid){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "edit";
			frm.surveyid.value=surveyid;
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
    
    function doSearch(frm) {
		if(frm) {
   			frm.cmd.value = "list";
			frm.miv_page.value = 1;
			return true;
		}     
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="get" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="list" />
<mf:input type="hidden" name="surveyid" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
        <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="search">
                <tr>
                    <th><mf:label name="surveyid" for="s_surveyid" /></th>
                    <td><mf:input type="text" name="s_surveyid" cssStyle="width:95%" value="${LISTOP.ht.s_surveyid}" /></td>
                    <th><mf:label name="surveytitle" for="s_surveytitle" /></th>
                    <td><mf:input type="text" name="s_surveytitle" cssStyle="width:95%" value="${LISTOP.ht.s_surveytitle}" /></td>
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
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th>#</th>
                    <th nowrap><mf:header name="surveyid" /></th>
                    <th nowrap><mf:header name="surveytitle" /></th>
                    <th nowrap><mf:header name="survey_sdat" /></th>
                    <th nowrap><mf:header name="survey_edat" /></th>
                    <th nowrap><mf:header name="upt_dt" sort="true" /></th>
                    <th nowrap><mf:header name="active_yn" sort="true" /></th>
                    <th>#</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td align="center"><a href="javaScript:doEdit('<c:out value="${item.surveyid}"/>')" /><mh:out value="${item.surveyid}" td="true" /></a></td>
                        <td align="center"><a href="javaScript:doEdit('<c:out value="${item.surveyid}"/>')" /><mh:out value="${item.surveytitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.survey_sdat}" td="true" /></td>
                        <td align="center"><mh:out value="${item.survey_edat}" td="true" /></td>
                        <td align="center"><mh:out value="${item.upt_dt}" format="fulldate" td="true" /></td>
                        <td align="center"><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" td="true" /></td>
                        <td align="center"><mf:button bundle="button" key="survey.result" onclick="javaScript:doView('${item.surveyid}')" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
		    <tr>
		        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
		    </tr>
		</table>
        </div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>