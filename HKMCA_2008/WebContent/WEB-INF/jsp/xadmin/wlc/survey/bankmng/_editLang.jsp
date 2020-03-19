<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@ page import="modules.etest.support.Etest" %>

<SCRIPT LANGUAGE="JavaScript">
    function changeLang() {   
        var frm = getObject('myform');
        frm.cmd.value="editLang";
        frm.submit();
    }
</script>
<table border="0" cellpadding="2" cellspacing="1" class="view" width="100%"> 
	<col width="20%"/>
	<col width="30%"/>
	<col width="20%"/>
	<col width="30%"/>
<c:choose>
    <c:when test="${item.quetype == 's' || item.quetype == 'm'}">
        <mf:input type="hidden" name="tmp" value="${item.quecount}"/>
    </c:when>
    <c:otherwise>
        <input type="hidden" name="tmp" value="4">
    </c:otherwise>
</c:choose>

	<mf:input type="hidden" name="queid" value="${item.queid}" required="true"/>
    <mf:input type="hidden" name="cat_id" value="${item.cat_id}" required="true"/>
    <mf:input type="hidden" name="from" value="${from}"/>
    <mf:input type="hidden" name="setid" value="${setid}"/>
    
	<input type="hidden" name="queviw1" value="">
	<input type="hidden" name="queviw2" value="">
	<input type="hidden" name="queviw3" value="">
	<input type="hidden" name="queviw4" value="">
	<input type="hidden" name="queviw5" value="">
	<input type="hidden" name="queviw6" value="">
	<input type="hidden" name="queviw7" value="">
	<input type="hidden" name="queviw8" value="">
	<input type="hidden" name="queviw9" value="">
	<input type="hidden" name="queviw10" value="">
    <mf:input type="hidden" name="quetype"  value="${item.quetype}" />
    <mf:input type="hidden" name="quecount"  value="${item.quecount}" />
    <tr>
        <th height="28"><mf:label name="quetype"/></th>
        <td ><mh:out value="${item.quetype}" codeGroup="ETEST.QTYPE"/></td>
        <th height="28" ><mf:label name="quecount"/></th>
        <td >
            <mfmt:message bundle="table.exm_bank" key="label.quecount"><mfmt:param value="${item.quecount}"/></mfmt:message>
        </td>
    </tr>
    <tr>
        <th height="28"><mf:label name="quelevel"/></th>
        <td><mh:out value="${item.quelevel}" codeGroup="ETEST.QLEVEL"/></td>
        <th height="28"><mf:label name="active_yn"/></th>
        <td><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
    </tr>
    <tr>
        <th height="28"><mf:label name="quescore"/></th>
        <td><mh:out value="${item.quescore}" /><mfmt:message bundle="table.exm_bank" key="label.score"/></td>
        <th height="28"><mf:label name="cat_name"/></th>
        <td><mh:out value="${item.cat_name}" /> </td>
        
    </tr>
    <tr>
        <th></th>
        <td colspan="3"><mf:select name="lang" items="${langList}" keyValue="code" keyText="allnames" curValue="${curLang}"/><mf:button onclick="changeLang()" bundle="button" key="search" /></td>
    </tr>
</table>

<br><br>
<table border="0"   cellpadding="2" cellspacing="1" class="view" width="100%"> 
	<col width="15%"/>
	<col width="15%"/>
	<col width="70%"/>
    <tr>
        <th rowspan=3><mfmt:message bundle="table.exm_bank" key="title"/></th>
        <th><mf:label name="quetitle"/></th>
        <td><textarea name="quetitle" style="width:100%" rows='3' required><c:out value="${item.quetitle}"/></textarea></td>
    </tr>
    <tr>
        <th><mf:label name="quetext"/></th>
        <td><textarea name="quetext" style="width:100%" rows='3' hname="<mf:header name="quetext"/>"><c:out value="${item.quetext}"/></textarea></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queimag"/></th>
        <td>
        <input type="file" name="queimag" style="width:100%" value='' >
            <c:if test="${!empty item.queimag}">
            	<c:url var="iurl" value="/wdownload">
            		<c:param name="type" value="exam_file"/>
            		<c:param name="ext" value="${item.sjt_cd}"/>
            		<c:param name="filename" value="${item.queimag}"/>
            	</c:url>
                <br><a href='<c:out value="${iurl}"/>' target='_blank'><mh:out value="${item.queimag}"/></a>&nbsp;&nbsp;<input type="checkbox" name="queimag_flag" style="border:none" value="Y" checked >:<mfmt:message bundle="common.message" key="message.use_file"/><br>
            </c:if>
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 1</th>
        <td colspan="2"><mh:out value="${item.queviw1}"/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 2</th>
        <td colspan="2"><mh:out  value="${item.queviw2}"/></td>
    </tr>
        <th><mf:label name="queansw"/></th>
        <td colspan="2">
            <mf:textarea cssStyle="width:100%" rows="3" name="queansw_word" value="${item.queansw}"/><br>
            <mfmt:message bundle="table.exm_bank" key="wlc_exm_bnk.queansw.seusul.tip"/>
        </td>
    </tr>
    <tr>
        <th><mf:label name="quedesc"/></th>
        <td colspan="2"><mf:textarea cssStyle="width:100%" rows="3" name="quedesc"  value="${item.quedesc}"/></td>
    </tr>    
    <tr>
        <th colspan="3" >&nbsp;</th>
    </tr>    
</table>