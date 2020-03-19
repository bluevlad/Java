<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view"> 
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
	<mf:input type="hidden" name="queid" value="${queid}"/>
	<mf:input type="hidden" name="lang" value="${item.lang}"/>
    <mf:input type="hidden" name="from" value="${from}"/>
    <mf:input type="hidden" name="setid" value="${setid}"/>
    <mf:input type="hidden" name="queowner" value="${item.queowner}"/>
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
    <tr>
        <th><mfmt:message bundle="survey" key="quetype" /></th>
        <td><mf:select name="quetype" codeGroup="ETEST.QTYPE" curValue="${item.quetype}" onchange="type_reload('myform')"/></td>
        <th><mfmt:message bundle="survey" key="quecnt" /></th>
        <td>
            <select name="quecount" onchange="changeQueuCount();">
	        <c:forEach var="i" begin="0" end="10">
	        	<mf:option value="${i}" curValue="${item.quecount}"><mh:out value="${i}"/></mf:option>
	        </c:forEach>
            </select> 
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="common" key="isuse" /></th>
        <td colspan="3"><mf:select name="active_yn" codeGroup="ACTIVE_YN" curValue="${item.active_yn}"/></td>
    </tr>
</table>
<br>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view"> 
	<col width="20%"/>
	<col width="80%"/>
    <tr>
        <th><mfmt:message bundle="survey" key="quetitle" /></th>
        <td><mf:textarea name="quetitle" cssStyle="width:98%" rows="3" value="${item.quetitle}"/></td>
    </tr>
    <c:forEach var="v" begin="1" end="10" varStatus="status">
        <c:set var="view" value= "none"/>
        <c:set var="Sview" value= ""/>
        <c:set var="Mview" value= ""/>
        <c:choose>
            <c:when test="${item.quetype == 's'}">
                <c:set var="Mview" value= "none"/>    
            </c:when>
            <c:when test="${item.quetype == 'm'}">
                <c:set var="Sview" value= "none"/>    
            </c:when>
            <c:otherwise>
                <c:set var="Sview" value= ""/>
                <c:set var="Mview" value= ""/>
            </c:otherwise>
        </c:choose>
        <c:if test="${empty Sview || empty Mview}">
            <c:set var="view" value= ""/>
        </c:if>
        <c:choose>
            <c:when test="${status.count == 1}">
                <c:set var="viw" value= "${item.queviw1}"/>
            </c:when>
            <c:when test="${status.count == 2}">
                <c:set var="viw" value= "${item.queviw2}"/>
            </c:when>
            <c:when test="${status.count == 3}">
                <c:set var="viw" value= "${item.queviw3}"/>
            </c:when>
            <c:when test="${status.count == 4}">
                <c:set var="viw" value= "${item.queviw4}"/>
            </c:when>
            <c:when test="${status.count == 5}">
                <c:set var="viw" value= "${item.queviw5}"/>
            </c:when>
            <c:when test="${status.count == 6}">
                <c:set var="viw" value= "${item.queviw6}"/>
            </c:when>
            <c:when test="${status.count == 7}">
                <c:set var="viw" value= "${item.queviw7}"/>
            </c:when>
            <c:when test="${status.count == 8}">
                <c:set var="viw" value= "${item.queviw8}"/>
            </c:when>
            <c:when test="${status.count == 9}">
                <c:set var="viw" value= "${item.queviw9}"/>
            </c:when>
            <c:when test="${status.count == 10}">
                <c:set var="viw" value= "${item.queviw10}"/>
            </c:when>
            <c:otherwise>
                <c:set var="viw" value= ""/>
            </c:otherwise>
        </c:choose>
    <!-- single select -->
    <tr style='<c:out value="display:${Sview}"/>' id='<c:out value="SviewTEXT${status.count}"/>'>
    	<mh:indexOf var="t" value="${item.queansw}" needle="${status.count}"/>
        <th><mfmt:message bundle="survey" key="queviw"/><c:out value="${status.count}"/></th>
        <td><mf:textarea name="Squeviw${status.count}" cssStyle="width:98%" rows="3" value="${viw}"/></td>
    </tr>
    <!-- multi select -->
    <tr style='<c:out value="display:${Mview}"/>' id='<c:out value="MviewTEXT${status.count}"/>' >
    	<mh:indexOf var="t" value="${item.queansw}" needle="${status.count}"/>
        <th><mfmt:message bundle="survey" key="queviw"/><c:out value="${status.count}"/></th>
        <td><mf:textarea name="Mqueviw${status.count}" cssStyle="width:98%" rows="3" value="${viw}"/></td>
    </tr>
    </c:forEach>    
</table>