<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
//등록
function addCoop() {
    $('#Frm').attr('action','<c:url value="/CoopManagement/CoopInsertProcess.do"/>').submit();
}

function fn_Update() {
    $('#Frm').attr('action','<c:url value="/CoopManagement/CoopUpdateProcess.do"/>').submit();
}

function ipView(coop_cd){
   	window.open('<c:url value="/CoopManagement/CoopIpList.pop"/>?COOP_CD=' + coop_cd, '_blank', 'location=no,resizable=yes,scrollbars=yes,width=400,height=630,top=0,left=0');
}
</script>
</head>

<!--content -->
<div id="content">
	<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>

    <!--테이블-->
    <form id="Frm" name="Frm" method="post">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

 	<ul class="boardBtns">
    	<li><a href="javascript:fn_Update();">저장</a></li>
    </ul>
    <table class="table02">
		<tr>
			<th width="100">제휴사ID</th>
			<th width="200">제휴사명</th>
			<th width="80">할인율</th>
			<th width="*">제휴사설명</th>
			<th width="100">등록아이피</th>
			<th width="80">등록일</th>
        </tr>
		<tbody>
		<c:forEach items="${list}" var="list" varStatus="status">
	    <input type="hidden" id="V_COOP_CD" name="V_COOP_CD" value="${list.COOP_CD}"/></td>
		<tr>
			<td>${list.COOP_CD}</td>
			<td><input type="text" id="COOP_NM_${list.COOP_CD}" name="COOP_NM_${list.COOP_CD}" value="${list.COOP_NM}" style="width:180px;"></td>
			<td><input type="text" id="DISCOUNT_PER_${list.COOP_CD}" name="DISCOUNT_PER_${list.COOP_CD}" value="${list.DISCOUNT_PER}" style="width:40px;">%</td>
		    <td><input type="text" id="COOP_DESC_${list.COOP_CD}" name="COOP_DESC_${list.COOP_CD}" value="${list.COOP_DESC}" style="width:80%;"></td>
		    <td><a href="javascript:ipView('${list.COOP_CD}');">${list.IP_CNT}</a></td>
		    <td><fmt:formatDate value="${list.REG_DT}" pattern="yyyy-MM-dd"/></td>
		</tr>
        </c:forEach>
		<tr>
			<th><input type="text" id="COOP_CD" name="COOP_CD" value="" style="width:90px;"></th>
			<th><input type="text" id="COOP_NM" name="COOP_NM" value="" style="width:180px;"></th>
			<th><input type="text" id="DISCOUNT_PER" name="DISCOUNT_PER" value="" style="width:40px;">%</th>
		    <th><input type="text" id="COOP_DESC" name="COOP_DESC" value="" style="width:80%;"></th>
		    <th>#</th>
		    <th><input type="button" name="input" onClick="addCoop()" value="신규등록"></th>
		</tr>
		</tbody>
    </table>
    </form>
</div>
<!--//content -->