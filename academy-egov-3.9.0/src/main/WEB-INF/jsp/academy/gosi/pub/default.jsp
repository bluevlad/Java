<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
</head>
<div id="content">
<h2>● 수강생지원관리 > <strong>주요공고/지표관리</strong></h2>
 <table class="table01">
     <tr >
         <td   width="380px"  height="1000px"  style="vertical-align:top;">
           <iframe src="<c:url value="/pub/pub_tree.frm"/>?TOP_MENU_ID=${params.TOP_MENU_ID}&MENU_ID=${params.MENU_ID}&MENUTYPE=${params.MENUTYPE}&L_MENU_NM=${params.L_MENU_NM}" name='frmTree'  id='frmTree' width="100%" height="100%" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0"></iframe>
         </td>
         <td  width="800px" height="1000px"  style="vertical-align:top;">
        	<iframe src="<c:url value="/pub/pub_detail.frm"/>?TOP_MENU_ID=${params.TOP_MENU_ID}&MENU_ID=${params.MENU_ID}&MENUTYPE=${params.MENUTYPE}&L_MENU_NM=${params.L_MENU_NM}" name="frmEdit" id="frmEdit" width="100%" height="100%" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0"></iframe>
         </td>
     </tr>
 </table>
</div>
