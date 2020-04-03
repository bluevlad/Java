<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
</head>
<div id="content">
    <h2>● 강의 관리 > <strong>직급/직종관리</strong></h2>
    <ul class="lecWritheTab">
        <li><a href="javascript:fn_go_list('C');" <c:if test="${TYPE_CHOICE eq 'C'}">class="active"</c:if>>직종관리</a></li>
        <li><a href="javascript:fn_go_list('S');" <c:if test="${TYPE_CHOICE eq 'S'}">class="active"</c:if>>직종-직렬관리</a></li>
    </ul>       
     <table class="table01">
         <tr >
             <td   width="380px"  height="1000px"  style="vertical-align:top;">
               <iframe src="<c:url value='/category/tree.frm'/>?TYPE_CHOICE=${TYPE_CHOICE}&TOP_MENU_ID=${TOP_MENU_ID}&MENU_ID=${MENU_ID}&MENUTYPE=${MENUTYPE}" name='frmTree'  id='frmTree' width="100%" height="100%" marginwidth="0" marginheight="0" scrolling="auto" frameborder="0"></iframe>
             </td>
             <td  width="800px" height="1000px"  style="vertical-align:top;">
              <iframe src="<c:url value='/category/detail.frm'/>?TYPE_CHOICE=${TYPE_CHOICE}&CODE=${VIEWCODE}&TOP_MENU_ID=${TOP_MENU_ID}&MENU_ID=${MENU_ID}&MENUTYPE=${MENUTYPE}" name="frmMenuEdit" id="frmMenuEdit" width="100%" height="100%"  marginwidth="0" marginheight="0"  frameborder="0"></iframe>
             </td>
         </tr>
     </table>
</div>
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${TYPE_CHOICE}">
</form> 
<script type="text/javascript">
function fn_go_list(val){
    $("#TYPE_CHOICE").val(val);
    if(val=="C"){
        $("#frm").attr("action", "<c:url value='/kind/list.do' />");
    }else{
        $("#frm").attr("action", "<c:url value='/category/main.do' />");
    }
    $("#frm").submit(); 
}
</script>