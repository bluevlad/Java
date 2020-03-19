<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript"   src='<c:url value="/js/lib.validate.js"/>'></script>

<script language="javascript"   >

function goList()   {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = '<c:out value="${urlList}"/>';
}                


function doWrite(){
    var frm = getObject("myform");
    if(frm)   {
        frm.cmd.value='updateLang';
        frm.submit();
    } else {
        return;
    }
}




//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform"  enctype="multipart/form-data">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>


<SCRIPT LANGUAGE="JavaScript">

    function changeLang() {   
        var frm = getObject('myform');
        frm.cmd.value="editLang";
        
        frm.submit();
    }
</script>
<table border="0"   cellpadding="2" cellspacing="1" class="view" width="100%"> 
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

	<mf:input type="hidden" name="queid" value="${item.queid}" />
    <mf:input type="hidden" name="cat_id" value="${item.cat_id}"/>
    <mf:input type="hidden" name="from" value="${from}"/>
    <mf:input type="hidden" name="setid" value="${setid}"/>
    

    <mf:input type="hidden" name="quetype"  value="${item.quetype}" />
    <mf:input type="hidden" name="quecount"  value="${item.quecount}" />
    <tr>
        <th height="28"><mf:label name="quetype"/></th>
        <td ><mh:out value="${item.quetype}" codeGroup="ETEST.QTYPE"/></td>
        <th height="28" ><mf:label name="quecount"/></th>
        <td >
            <mfmt:message bundle="table.exm_bank" key="label.quecount" > <mfmt:param value="${item.quecount}"/></mfmt:message>
        </td>
    </tr>
    <tr>
        <th height="28"><mf:label name="quelevel"/></th>
        <td ><mh:out value="${item.quelevel}" codeGroup="ETEST.QLEVEL"/></td>
        <th height="28"><mf:label name="active_yn"/></th>
        <td ><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
    </tr>
    <tr>
        <th height="28" ><mf:label name="quescore"/></th>
        <td ><mh:out value="${item.quescore}" /> <mfmt:message bundle="table.exm_bank" key="label.score"/></td>
        <th height="28"><mf:label name="cat_name"/></th>
        <td ><mh:out value="${item.cat_name}" /> </td>
        
    </tr>
    <tr>
        <th>Language</th>
        <td colspan="3"><mf:select name="lang" items="${langList}" keyValue="code" keyText="allnames" curValue="${curLang}"/> <mf:button onclick="changeLang()" bundle="button" key="search" /></td>
        
    </tr>
</table>

<br><br>
<table border="0"   cellpadding="2" cellspacing="1" class="view" width="100%"> 
	<col width="15%"/>
	<col width="85%"/>

    <tr>
        
        <th ><mf:label name="quetitle"/></th>
        <td ><div class="simplebox" ><mh:out value="${item.quetitle}"/></div>
        <textarea name="quetitle" style="width:100%" rows='3' required><c:out value="${langItem.quetitle}"/></textarea>
        </td>
    </tr>
    <tr>
        <th><mf:label name="quetext"/></th>
        <td><div class="simplebox" ><mh:out value="${item.quetext}"/></div>
        <textarea name="quetext" style="width:100%" rows='3' hname="<mf:header name="quetext"/>"><c:out value="${langItem.quetext}"/></textarea>
        </td>
    </tr>
    <c:if test="${!empty item.queimag}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queimag"/></th>
        <td>
            
                <input type="file" name="queimag" style="width:100%" value='' >
                <c:if test="${!empty langItem.queimag}">
                <c:url var="iurl" value="/wdownload">
                    <c:param name="type" value="exam_file"/>
                    <c:param name="ext" value="${langItem.sjt_cd}"/>
                    <c:param name="filename" value="${langItem.queimag}"/>
                </c:url>
                <br><a href='<c:out value="${iurl}"/>' target='_blank'><mh:out value="${langItem.queimag}"/></a>&nbsp;&nbsp;<input type="checkbox" name="queimag_flag" style="border:none" value="Y" checked >:<mfmt:message bundle="common.message" key="message.use_file"/><br>
                </c:if>
                <c:if test="${!empty item.queimag}">
                	<c:url var="iurl" value="/wdownload">
                		<c:param name="type" value="exam_file"/>
                		<c:param name="ext" value="${item.sjt_cd}"/>
                		<c:param name="filename" value="${item.queimag}"/>
                	</c:url>
                    <br><a href='<c:out value="${iurl}"/>' target='_blank'><mh:out value="${item.queimag}"/></a><br>
                </c:if>
            
        </td>
    </tr>
    </c:if>
    
    <c:if test="${!empty item.queviw1}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 1</th>
        <td ><div class="simplebox" ><mh:out value="${item.queviw1}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw1" value="${langItem.queviw1}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw2}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 2</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw2}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw2" value="${langItem.queviw2}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw3}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 3</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw3}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw3}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw4}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 4</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw4}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw4}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw5}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 5</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw5}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw5}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw6}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 6</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw6}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw6}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw7}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 7</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw7}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw7}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw8}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 8</th>
        <td colspan="2"><div class="simplebox" ><mh:out  value="${item.queviw8}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw8}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw9}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 9</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw9}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw9}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw10}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 10</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw10}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw10}"/></td>
    </tr>
    </c:if>
    

    
	<c:if test="${!empty item.queviw11}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 11</th>
        <td ><div class="simplebox" ><mh:out value="${item.queviw11}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw11" value="${langItem.queviw11}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw12}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 12</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw12}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw12" value="${langItem.queviw12}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw13}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 13</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw13}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw13" value="${langItem.queviw13}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw14}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 14</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw14}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw14" value="${langItem.queviw14}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw15}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 15</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw15}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw15" value="${langItem.queviw15}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw16}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 16</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw16}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw16" value="${langItem.queviw16}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw17}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 17</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw17}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw17" value="${langItem.queviw17}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw18}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 18</th>
        <td colspan="2"><div class="simplebox" ><mh:out  value="${item.queviw18}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw18" value="${langItem.queviw18}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw19}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 19</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw19}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw19" value="${langItem.queviw19}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw20}">
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queviw"/> 20</th>
        <td ><div class="simplebox" ><mh:out  value="${item.queviw20}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw20" value="${langItem.queviw20}"/></td>
    </tr>
    </c:if>


                
    <c:if test="${!(item.quetype == 's' || item.quetype == 'm')}">
    <th ><mf:label  name="queansw"/></th>
        <td  ><div class="simplebox" ><mh:out  value="${item.queansw}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queansw" value="${langItem.queansw}"/><br>
            <mfmt:message bundle="table.exm_bank" key="wlc_exm_bnk.queansw.seusul.tip"/>
        </td>
    </tr>
    </c:if>
    
    <c:if test="${item.quetype == 's' || item.quetype == 'm'}">
    <th ><mf:label  name="queansw"/></th>
        <td ><div class="simplebox" ><mh:out  value="${item.queansw}"/></div>
        <mf:input type="hidden" name="queansw" value="${item.queansw}"/>
        </td>
    </tr>
    </c:if>    
    <tr>
        <th ><mf:label  name="quedesc"/></th>
        <td ><div class="simplebox" ><mh:out  value="${item.quedesc}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="quedesc"  value="${langItem.quedesc}"/>
        </td>
    </tr>    
  
</table>

<table border="0"   cellpadding="2" cellspacing="1" class="view" width="100%">     
    <tr>
        <td colspan="3" align="center">
            <mf:button bundle="button" key="button.save" onclick="doWrite()"/>
	        <mf:button bundle="button" key="button.list" onclick="goList()"/>&nbsp;&nbsp;
            <mf:button bundle="button" key="button.delete" onclick="doDelete()"/>
        </td>
    </tr>
</table>
</mf:form>

