<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

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

function changeLang() {   
    var frm = getObject('myform');
    frm.cmd.value="editLang";
    frm.submit();
}
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform"  enctype="multipart/form-data">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="queid" value="${item.queid}" />
<mf:input type="hidden" name="quetype"  value="${item.quetype}" />
<mf:input type="hidden" name="quecount"  value="${item.quecount}" />
<mf:input type="hidden" name="queviw1" value=""/>
<mf:input type="hidden" name="queviw2" value=""/>
<mf:input type="hidden" name="queviw3" value=""/>
<mf:input type="hidden" name="queviw4" value=""/>
<mf:input type="hidden" name="queviw5" value=""/>
<mf:input type="hidden" name="queviw6" value=""/>
<mf:input type="hidden" name="queviw7" value=""/>
<mf:input type="hidden" name="queviw8" value=""/>
<mf:input type="hidden" name="queviw9" value=""/>
<mf:input type="hidden" name="queviw10" value=""/>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view"> 
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
<c:choose>
    <c:when test="${item.quetype == 's' || item.quetype == 'm'}">
        <mf:input type="hidden" name="tmp" value="${item.quecount}"/>
    </c:when>
    <c:otherwise>
        <input type="hidden" name="tmp" value="4">
    </c:otherwise>
</c:choose>
    <tr>
        <th height="28"><mf:label name="quetype"/></th>
        <td><mh:out value="${item.quetype}" codeGroup="ETEST.QTYPE"/></td>
        <th><mf:label name="quecount"/></th>
        <td><mfmt:message bundle="table.exm_bnk" key="quecount"><mh:out value="${item.quecount}"/></mfmt:message></td>
    </tr>
    <tr>
        <th height="28"><mf:label name="quelevel"/></th>
        <td><mh:out value="${item.quelevel}" codeGroup="ETEST.QLEVEL"/></td>
        <th><mf:label name="active_yn"/></th>
        <td><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
    </tr>
    <tr>
        <th height="28"><mf:label name="quescore"/></th>
        <td><mh:out value="${item.quescore}"/><mfmt:message bundle="common" key="etest.score"/></td>
        <th>Language</th>
        <td><mf:select name="lang" items="${langList}" keyValue="code" keyText="allnames" curValue="${curLang}"/>&nbsp;<mf:button onclick="changeLang()" bundle="button" key="search" /></td>
    </tr>
</table>
<br>
<table border="0" cellpadding="2" cellspacing="1" class="view" width="100%"> 
    <col width="15%"/>
    <col width="85%"/>
    <tr>
        <th><mf:label name="quetitle"/></th>
        <td>
            <div class="simplebox"><mh:out value="${item.quetitle}"/></div>
            <textarea name="quetitle" style="width:100%" rows='3' required><c:out value="${langItem.quetitle}"/></textarea>
        </td>
    </tr>
    <tr>
        <th><mf:label name="quetext"/></th>
        <td>
            <div class="simplebox"><mh:out value="${item.quetext}"/></div>
            <textarea name="quetext" style="width:100%" rows='3' hname="<mf:header name="quetext"/>"><c:out value="${langItem.quetext}"/></textarea>
        </td>
    </tr>
    <c:if test="${!empty item.queimag}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queimag"/></th>
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
        <th><mfmt:message bundle="table.exm_bnk" key="queviw1"/></th>
        <td><div class="simplebox"><mh:out value="${item.queviw1}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw1" value="${langItem.queviw1}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw2}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw2"/></th>
        <td><div class="simplebox"><mh:out value="${item.queviw2}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw2" value="${langItem.queviw2}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw3}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw3"/></th>
        <td><div class="simplebox"><mh:out value="${item.queviw3}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw3" value="${langItem.queviw3}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw4}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw4"/></th>
        <td><div class="simplebox"><mh:out value="${item.queviw4}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw4" value="${langItem.queviw4}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw5}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw5"/></th>
        <td><div class="simplebox"><mh:out value="${item.queviw5}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw5" value="${langItem.queviw5}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw6}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw6"/></th>
        <td><div class="simplebox"><mh:out value="${item.queviw6}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw6" value="${langItem.queviw6}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw7}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw7"/></th>
        <td><div class="simplebox"><mh:out value="${item.queviw7}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw7" value="${langItem.queviw7}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw8}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw8"/></th>
        <td colspan="2"><div class="simplebox"><mh:out value="${item.queviw8}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw8" value="${langItem.queviw8}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw9}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw9"/></th>
        <td><div class="simplebox" ><mh:out value="${item.queviw9}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw9" value="${langItem.queviw9}"/></td>
    </tr>
    </c:if>
    <c:if test="${!empty item.queviw10}">
    <tr>
        <th><mfmt:message bundle="table.exm_bnk" key="queviw10"/></th>
        <td><div class="simplebox"><mh:out value="${item.queviw10}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="queviw10" value="${langItem.queviw10}"/></td>
    </tr>
    </c:if>
                
    <c:if test="${!(item.quetype == 's' || item.quetype == 'm')}">
        <th><mf:label name="queansw"/></th>
        <td>
            <div class="simplebox"><mh:out value="${item.queansw}"/></div>
            <mf:textarea cssStyle="width:100%" rows="3" name="queansw" value="${langItem.queansw}"/>
        </td>
    </tr>
    </c:if>

    <c:if test="${item.quetype == 's' || item.quetype == 'm'}">
    <th><mf:label name="queansw"/></th>
        <td><div class="simplebox"><mh:out value="${item.queansw}"/></div>
        <mf:input type="hidden" name="queansw" value="${item.queansw}"/>
        </td>
    </tr>
    </c:if>

    <tr>
        <th><mf:label name="quedesc"/></th>
        <td><div class="simplebox"><mh:out value="${item.quedesc}"/></div>
        <mf:textarea cssStyle="width:100%" rows="3" name="quedesc"  value="${langItem.quedesc}"/>
        </td>
    </tr>    
</table>
<br>
<table border="0" cellpadding="2" cellspacing="1" width="100%" class="viewBtn">     
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="doWrite()" icon="icon_save"/>
            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>