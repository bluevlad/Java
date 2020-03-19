<%@ page contentType="charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld" %>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>

<style>
    #tailinfo {
        /* display:normal !important; */
        /* display:none */;   
        position: relative;
        clear: both;
    }
</style>
<div id="tailinfo">
MAF_INFO :
<table border="1" cellpadding="2" cellspacing="0">
<tr>
    <th>pgid</th>
    <th>mid</th>
    <th>cmd</th>
    <th>JSP file</th>
    <th>control_action</th>
    <th>Java className</th>
</tr>
<tr>
    <td><mh:out value="${MAF_INFO.pgid}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.mid}" td="true"/></td>
    <td><mh:out value="${param.cmd}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.file}" td="true"/></td>
    <td><mh:out value="${control_action}" td="true"/></td>
    <td><mh:out value="${MAF_INFO.className}" td="true"/></td>
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
MAF_AAM:
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
    <td><mh:out value="${MAF_AAM.site}" td="true"/></td>
    <td><mh:out value="${MAF_AAM.pgid}" td="true"/></td>
    <td><mh:out value="${MAF_AAM.role}" td="true"/></td>
    <td><mh:out value="${MAF_AAM.auth_c}" td="true"/></td>
    <td><mh:out value="${MAF_AAM.auth_r}" td="true"/></td>
    <td><mh:out value="${MAF_AAM.auth_u}" td="true"/></td>
    <td><mh:out value="${MAF_AAM.auth_d}" td="true"/></td>
</tr>
</table>
Lecture :
[<c:out value="${Lecture}"/>]
<fmt:setBundle var="m" basename="resources.common" />
<mfmt:getLocale var="t"/>
Locale :
<table border="1" cellpadding="2" cellspacing="0">
<tr>
    <th>pageContext.request.locale</th>
    <th>mfmt:getLocale</th>
    <th>CharacterEncoding</th>
    <th>LANG</th>
    <th>request.getLocale()</th>

</tr>
<tr>
    <td><mh:out value="${pageContext.request.locale}" td="true"/></td>
    <td><mh:out value="${t}" td="true"/></td>
    <td><%=request.getCharacterEncoding()%></td>
    <td><fmt:message bundle="${m}" key="lang"/></td>
    <td><%=request.getLocale()%></td>
    
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
