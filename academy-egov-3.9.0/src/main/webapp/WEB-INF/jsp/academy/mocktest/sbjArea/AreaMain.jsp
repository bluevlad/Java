<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!--content -->
<div id="content">
    <h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>

<table class="table01">
	<tr>
    	<td width="300px"  height="600px"  style="vertical-align:top;">
        	<iframe src="<c:url value="/mouigosa/AreaList.frm"/>?TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}" name='frmList'  id='frmList' width="100%" height="100%" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0"></iframe>
        </td>
        <td width="600px" height="600px"  style="vertical-align:top;">
        	<iframe src="<c:url value="/mouigosa/AreaView.frm"/>?MENU_ID=${VWMENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}" name="frmEdit" id="frmEdit" width="100%" height="100%" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0"></iframe>
         </td>
	</tr>
</table>
</div>
