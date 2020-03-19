<%@ page contentType = "text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<a href="<c:url value="${controlaction}">
		<c:param name="type" value="table"/>
		<c:param name="owner" value="${owner}"/></c:url>">[ Table ]</a> | 
<a href="<c:url value="${controlaction}">
		<c:param name="type" value="view"/>
		<c:param name="owner" value="${owner}"/></c:url>">[ View ] </a> | 
<a href="<c:url value="${controlaction}">
		<c:param name="type" value="procs"/>
		<c:param name="owner" value="${owner}"/></c:url>">[ Procs ] </a> | 
<a href="javascript:location.reload()">[ RELOAD ]</a> 
<hr/>
<div id="divList" style="display: block;">
<form action="tabsinfo.do" method="post" name="msgform" id="msgform" target="mainFrame">
<input type="hidden" name="owner" value='<c:out value="${owner}"/>'>
<input type="hidden" name="cmd" value="">
<div id="divColumnInfo" style="width: 340px; height: 580px; overflow: auto; border: 1px solid Gray;">
<table border="0" cellpadding="2" cellspacing="0" class="table" width="100%">
	<c:choose>
		<c:when test="${param.type=='procs'}">
			<tr>
			    <th class="th">Type</th>
			    <th class="th">Name</th>
			</tr>
			<c:forEach items="${tables}" var="item">
				<c:url var="url" value="procinfo.do">
					<c:param name="owner" value="${owner}"/>
					<c:param name="type" value="${item.object_type}"/>
					<c:param name="name" value="${item.object_name}"/>
				</c:url>
				<c:if test="${item.object_type=='PACKAGE'}">
					<c:url var="url2" value="procinfo.do">
						<c:param name="owner" value="${owner}"/>
						<c:param name="type" value="PACKAGE BODY"/>
						<c:param name="name" value="${item.object_name}"/>
					</c:url>
				</c:if>
				<tr class="tr">
					<td class="td"><mh:out value="${item.object_type}"/></td>
				    <td class="td"><a href="<mh:out value='${url}'/>" target="mainFrame" ><mh:out value="${item.object_name}"/></a>
					<c:if test="${item.object_type=='PACKAGE'}">
						<a href="<mh:out value='${url2}'/>" target="mainFrame" >[BODY]</a>
					</c:if></td>
				  </tr>
		  	</c:forEach>		
		</c:when>
		<c:otherwise>
			<tr>
                <th class="th"></th>
			    <th class="th">Name</th>
			</tr>
			<c:forEach items="${tables}" var="item">
			<c:url var="url" value="tabsinfo.do">
				<c:param name="owner" value="${owner}"/>
				<c:param name="cmd" value="sql"/>
				<c:param name="tabsname" value="${item.name}"/>
			</c:url>

			<tr class="tr">
				  <td class="td"></td>
			    <td class="td"><a href="<c:out value="${url}"/>" target="mainFrame" ><c:out value="${item.name}" />
					<c:if test="${item.comments!=null && item.comments != ''}">
						(<c:out value="${item.comments}"/>))
					</c:if></a></td>
			  </tr>
		  	</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
</div>
</form>
</div>
<div id="divSql" style="Z-INDEX: 1; VISIBILITY: hidden; OVERFLOW: auto; POSITION: absolute; background-color: #ffffff; top:10px, left:0px;">
<form action="tabsinfo.do" method="post" name="frmSql" id="frmSql" target="mainFrame">
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<input type="hidden" name="owner" value='<c:out value="${owner}"/>'>
	<input type="hidden" name="cmd" value="SQL">
	<tr>
		<td><textarea cols="40" rows="8" name="sql"></textarea>	</td>
	</tr>
	<tr>
		<td ><a href="javascript:getJava('SQL')">Java Bean</a> | <a href="javascript:getJava('STMT')">Java Statement</a></td>
	</tr>
</table>	
</form>
</div>
<script language="JavaScript">
<!--
function getJava( cmd )  {
	var frm = getObject("frmSql");
	frm.cmd.value = cmd;
	frm.submit();
	
}
//-->
</script>