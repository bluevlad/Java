<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
</head>
<div id="content">
 <table class="table01">
     <tr >
         <td   width="380px"  height="1000px"  style="vertical-align:top;">
           <iframe src="<c:url value="/adminManagementMenu/menuTree.frm"/>?TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}" name='frmTree'  id='frmTree' width="100%" height="100%" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0"></iframe>
         </td>
         <td  width="800px" height="1000px"  style="vertical-align:top;">
          <%--   <iframe src="" name="frmMenuEdit" id="frmMenuEdit" width="100%" height="100%"  marginwidth="0" marginheight="0"  frameborder="0"></iframe> --%>
          <iframe src="<c:url value="/adminManagementMenu/menuDetail.frm"/>?MENU_ID=${VWMENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}" name="frmMenuEdit" id="frmMenuEdit" width="100%" height="100%" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0"></iframe>
         </td>
     </tr>
 </table>
</div>

