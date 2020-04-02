<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.bpopup.min2.js' />"></script>
</head>
<body>
<!--content -->
<script type="text/javascript">
//등록
function fn_tmAdd(){
	if($.trim($("#nSCOUNT").val())=="0"){
		alert("배정할 회원이 없습니다. 다시 검색하세요.");
		return;
	}
	go_popup();

	$.ajax({
		type: "POST",
		url: '<c:url value="/tm/tmPassUserInsert.json"/>',
		data: $("#searchFrm").serialize(),
		cache: false,
		dataType: "json",
		error: function (request, status, error) {
		},
		success: function (response, status, request) {
			alert("배정되었습니다.");
			$("#searchFrm", opener.document).attr("action","<c:url value='/tm/tmPassLecStdList.do' />");
			$("#searchFrm", opener.document).submit();
			window.self.close();
		}
	});
}

function go_popup() {					 
	$('#rd-popup').bPopup();
}

function go_popup_close() {					 
	$('#rd-popup').bPopup().close();
}
</script>
	   
<!--테이블-->
<div id="content_pop" style="padding-left:10px;">
<form id="searchFrm" name="searchFrm" method="post">    
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="LECCODE" name="LECCODE" value="${params.LECCODE}" />

<h2>● TM관리 > <strong>회원개별배정</strong></h2>
	
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_tmAdd();">TM회원배정</a></li>
    </ul>
    <!--//버튼--> 
      
     <!--테이블-->
     <table class="table02">
		<tr>
			<th>아이디</th>
		    <td class="tdLeft tdValign">
		        <c:forEach items="${arr_usr}" var="item" varStatus="loop">
		    	<c:set var="arr_id" value="${arr_id},${item.id}"/> 
		    	<c:set var="arr_nm" value="${arr_nm},${item.nm}"/> 
		        </c:forEach>
        		<input type="text" id="ARR_USR" name="ARR_USR" value="${arr_id}" style="width:95%" readonly/>
		    </td>
		</tr>
		<tr>
			<th>이름</th>
		    <td class="tdLeft tdValign">${arr_nm}</td>
		</tr>
		<tr>
			<th>주문 배정</th>
		    <td class="tdLeft tdValign">
				<c:set var="allocCnt" value="${fn:length(arr_usr) }" />
				<c:set var="adminCnt" value="${fn:length(tmadminlist) }" />
		        <fmt:parseNumber var="perCnt" value="${allocCnt div adminCnt }" integerOnly="true"> </fmt:parseNumber>
		        <c:set var="firstCnt" value="${allocCnt - (perCnt * (adminCnt-1)) }" />
		        <c:forEach items="${tmadminlist}" var="item" varStatus="loop">
		        <input type="hidden" id="v_adminid" name="v_adminid" value="${item.ADMINID}" />
		        <input type="hidden" id="NM_${item.ADMINID}" name="NM_${item.ADMINID}" value="${item.ADMINNAME}" />
					<c:if test="${loop.index == '0'}" >
		        	<p><span class="tdLeft">${item.ADMINNAME } : <input type="text" id="ALLOCCNT_${item.ADMINID}" name="ALLOCCNT_${item.ADMINID}" value="${firstCnt}" readonly class="clsCount" size="3" maxlength="3" onKeyDown="fn_OnlyNumber(this);"/> 건</span> </p>
		        	</c:if>
		        	<c:if test="${loop.index > '0'}" >
		        	<p><span class="tdLeft">${item.ADMINNAME } : <input type="text" id="ALLOCCNT_${item.ADMINID}" name="ALLOCCNT_${item.ADMINID}" value="${perCnt}" readonly class="clsCount" size="3" maxlength="3" onKeyDown="fn_OnlyNumber(this);"/> 건</span> </p>
		        	</c:if>
		        </c:forEach>
			</td>
		</tr>
	</table>
    <!--//테이블-->
</form>
</div> 
<div id="rd-popup" class="rd-Pstyle"  style="visibility:hidden;"><span class="b-close"></span></div>
</html>