<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<c:set var="ActionClassName" value="${packageName}.${className}Action" />
<c:set var="DaoClassName" value="${packageName}.${className}DB" />
<mh:set var="TableName" value="${tabsname}" case="upper" />
<mh:length var="ColumnCnt" value="${columns}" />
<mh:replace var="jspPath" value="${packageName}/${className}" before="." after="/" />
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE maf-resource PUBLIC
         "-//Miranet//DTD maf-command config XML V1.0//EN"
         "mvc-configuration.dtd">
<configuration>
	<template-list>
		<template-info name="inner" uri="/WEB-INF/jsp/<c:out value="${TableName}"/>_style1.jsp" />
	</template-list>

	<global-view>
	</global-view>
	
	<action uri="<mh:out value="${className}.do" case="lower"/>" type="<c:out value="${ActionClassName}"/>" 
        jsppath="/WEB-INF/jsp/<mh:out value="${jspPath}" case="lower"/>/"
		desc="<c:out value="${ActionClassName}"/>" title="<c:out value="${ActionClassName}"/>" default="list">
		<action-view>
			<view-info name="list" uri="list.jsp" useTemplate="inner" />
			<view-info name="view" uri="view.jsp" useTemplate="inner" />
			<view-info name="edit" uri="edit.jsp" useTemplate="inner" />
			<view-info name="add" uri="add.jsp" useTemplate="inner" />
		</action-view>
		<cmds>
			<cmd name="list" method="cmdList" formname="frm1" authtype="R" />
			<cmd name="view" method="cmdView" formname="frm1" authtype="R" />
			<cmd name="edit" method="cmdEdit" formname="frm1" authtype="U" />
			<cmd name="update" method="cmdUpdate" formname="frm1" from="edit" authtype="U" />
			<cmd name="insert" method="cmdInsert" formname="frm1" from="update" authtype="C" />
			<cmd name="add" method="cmdAdd" formname="frm1" from="update" authtype="C" />
			<cmd name="delete" method="cmdDelete" formname="frm1" from="delete" authtype="D" />
		</cmds>
		<forms>
			<form name="frm1" bundle="table.<mh:out value="${TableName}" case="lower"/>">
				<c:forEach var="item" items="${columns}" varStatus="status">
					<input name="<mh:out value="${item.name}" case="lower"/>" message="<mh:out value="${item.name}" case="lower"/>" type="text" <c:if test="${item.nullable != 'Y'}">required="true"</c:if>/>
				</c:forEach>
				
			</form>		
		</forms>
	</action>
	

</configuration>
