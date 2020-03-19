<%@ page contentType="charset=UTF-8"%>
<%@ page import="maf.aam.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mf" uri="http://maf.jstl.com/jsp/tld/maf-form.tld"%>
<%@ taglib prefix="mh" uri="http://maf.jstl.com/jsp/tld/maf-html.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.jstl.com/jsp/tld/maf-fmt.tld" %>
<%
    Object aam = request.getAttribute(AAMManager.AUTH_INFO_KEY);
    request.setAttribute("AAM", aam);
%>

<style>
    #tailinfo {
        display: normal; /* none normal */
        position: relative; 
        clear: both;
    }
</style>
<div id="tailinfo">
MAF_INFO :
<table border="1" cellpadding="2" cellspacing="0">
<tr>
    <th>pgid / mid</th>
    <th>control_actio / Java className</th>
    <th>cmd / method</th>
    <th>JSP file</th>
</tr>
<tr>
    <td><mh:out value="${MAF_INFO.pgid}" td="true"/> <br>
        <mh:out value="${MAF_INFO.mid}" td="true"/></td>
    <td><mh:out value="${control_action}" td="true"/> <br> 
            <mh:out value="${MAF_INFO.className}" td="true"/></td>
    <td><mh:out value="${param.cmd}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.file}" td="true"/></td>
    
</tr>
</table>


Session :
<table border="1" cellpadding="2" cellspacing="0">
<tr>
    <th>userid</th>
    <th>nm</th>
    <th>usn</th>
    <th>role</th>
</tr>
<tr>
    <td><mh:out value="${sessionScope.msession.userid}" td="true"/></td>
    <td><mh:out value="${sessionScope.msession.nm}" td="true"/></td>
    <td><mh:out value="${sessionScope.msession.usn}" td="true"/></td>
    <td><c:forEach  var="item" items="${sessionScope.msession.userRole.roleList}">
            <c:out value="${item}"/><br>
        </c:forEach></td>
</tr>
</table>
AAM:
<table border="1" cellpadding="2" cellspacing="0">
<tr>
    <th>site</th>
    <th>pgid</th>
    <th>role</th>
    <th>C</th>
    <th>R</th>
    <th>U</th>
    <th>D</th>
</tr>
<tr>
    <td><mh:out value="${AAM.site}" td="true"/></td>
    <td><mh:out value="${AAM.pgid}" td="true"/></td>
    <td><mh:out value="${AAM.role}" td="true"/></td>
    <td><mh:out value="${AAM.auth_c}" td="true"/></td>
    <td><mh:out value="${AAM.auth_r}" td="true"/></td>
    <td><mh:out value="${AAM.auth_u}" td="true"/></td>
    <td><mh:out value="${AAM.auth_d}" td="true"/></td>
</tr>
</table>


Template : 
<table border="1" cellpadding="2" cellspacing="0">
<tr>
    <th>name</th>
    <th>templateInfo</th>
    <th>title</th>
    <th>titleKey</th>
    <th>mUri</th>
    <th>siteInfo</th>
    <th>viewName</th>
</tr>
<tr>
    <td><mh:out value="${MAF_INFO.templateInfo.name}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.templateInfo}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.title}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.titleKey}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.muri}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.siteInfo}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.viewName}" td="true"/></td>
</tr>
</table>

<mfmt:setBundle var="m" basename="resources.common" />
<mfmt:getLocale var="t"/>
Locale :
<table border="1" cellpadding="2" cellspacing="0">
<tr>
    <th>locale</th>
    <th>locale2</th>
    <th>CharacterEncoding</th>
    <th>LANG</th>
</tr>
<tr>
    <td><mh:out value="${pageContext.request.locale}" td="true"/></td>
    <td><mh:out value="${t}" td="true"/></td>
    <td><%=request.getCharacterEncoding()%></td>
    <td><mfmt:message bundle="${m}" key="lang"/></td>
</tr>
</table>


<c:out value="LISTOP = [${LISTOP.ht}]<br>
MBOARD.BTN_AUTH = [${MBOARD.BTN_AUTH}]"/><br>

navigator = [<c:out value="${navigator}"/>]<br>
<table border="1" cellpadding="2" cellspacing="0">
    <thead>
        <tr>
            <th>Param Key</th>
            <th>value</th>
        </tr>
    </thead>
<c:forEach var="aKey" items="${paramValues}">
        <tr>
            <td><c:out value="${aKey.key}" /></td>
            <td><c:forEach var="val" items="${aKey.value}">
                    [<c:out value="${val}" />]<br />
            </c:forEach></td>
        </tr>
    </c:forEach>
</table>    

</div>