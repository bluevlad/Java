<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<a href="<c:url value="${controlaction}">
		<c:param name="type" value="table"/>
		<c:param name="owner" value="${owner}"/></c:url>">[ Table ]</a> | 
<a href="<c:url value="${controlaction}">
		<c:param name="type" value="view"/>
		<c:param name="owner" value="${owner}"/></c:url>">[ View ] </a> | 
<a href="<c:url value="${controlaction}">
		<c:param name="type" value="procs"/>
		<c:param name="owner" value="${owner}"/></c:url>">[ Procs ] </a> | 
<a href="javascript:showSql()">[ SQL ]</a> 
<a href="javascript:Excel()">[ Excel ]</a> 
<a href="javascript:location.reload()">[ RELOAD ]</a> 
<hr/>
<div id="divList" style="display: block;">
<form action="tabsinfo.do" method="post" name="msgform" id="msgform" target="mainFrame">
<input type="hidden" name="owner" value='<c:out value="${owner}"/>'>
	<input type="hidden" name="cmd" value="">
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
					<td class="td">${item.object_type}</td>
				    <td class="td"><a href="${url}" target="mainFrame" >${item.object_name}</a>
					<c:if test="${item.object_type=='PACKAGE'}">
						<a href="${url2}" target="mainFrame" >[B]</a>
					</c:if></td>
				  </tr>
		  	</c:forEach>		
		</c:when>
		<c:otherwise>
			<tr>
					<th class="th"><input type="checkbox" name="checkboxAll" 
							value="allcode"  class="checkbox" 
							onclick="allChekboxToggle(this,'msgform','table_nm')"></th>
			    <th class="th">Name</th>
			</tr>
			<c:forEach items="${tables}" var="item">
			<c:url var="url" value="tabsinfo.do">
				<c:param name="owner" value="${owner}"/>
				<c:param name="cmd" value="sql"/>
				<c:param name="tabsname" value="${item.name}"/>
			</c:url>

			<tr class="tr">
				  <td class="td"><input type="checkbox" name="table_nm" value="<c:out value="${item.name}" />" class="checkbox"></td>
			    <td class="td"><a href="<c:out value="${url}"/>" target="mainFrame" ><c:out value="${item.name}" />
					<c:if test="${item.comments!=null && item.comments != ''}">
						(<c:out value="${item.comments}"/>))
					</c:if></a></td>
			  </tr>
		  	</c:forEach>
		</c:otherwise>
	</c:choose>

</table>
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
function showSql()  { 
	var divSql = getObject("divSql");
	var divList = getObject("divList");
	if(divSql) {
		if(divSql.style) {
		  	divSql.style.visibility=(divSql.style.visibility == "visible")?"hidden":"visible";
		  	divSql.style.pixelTop = element_top(divList);
		  	divSql.style.pixelLeft = element_left(divList);
		}
	}
}
function Excel()  { 
	var frm = getObject("msgform");
	frm.cmd.value = "excel";
	frm.submit();
}

/************************
*
* formname의 checkname을 모두 toggle, allcheckname = akkcheck
* 사용법 1:
* allChekboxToggle(this, 'formName','usn_check','allCheckbox');
* 사용법 2: allcheck 발생원이 check박스의 경우
* allChekboxToggle(this, 'formName','usn_check');
*/
function allChekboxToggle(srcObject,  formName, chekName, allCheckName) {
	var frm = getObject(formName);
	var obj = null;
	if(allCheckName != null ) {
		var allcheck = getObject(allCheckName);
	
		if (allcheck.checked == false) {
			allcheck.checked = true;
		} else {
			allcheck.checked = false;
		} 
		obj = allcheck;
		//alert("a "+ obj.name);
	} else {
		obj = srcObject;
		//alert("b " + obj.name);
	}	
	
	if(frm ) {
		for (var i=0; i<frm.elements.length;i++) {
			//alert(frm.elements[i].type + "," + frm.elements[i].name);
			if (frm.elements[i].type == "checkbox" && frm.elements[i].name == chekName) {
				frm.elements[i].checked = obj.checked;
			}
		}
	} else {
		alert("FormName(" + formName+") is invalid!!!");
	}
}
//-->
</script>