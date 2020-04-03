<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.bpopup.min2.js' />"></script>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/images/btn_calendar.gif'/>");
	initDateFicker2("SDATE", "EDATE");	
});

//숫자만 입력
function fn_OnlyNumber(obj) {
	for (var i = 0; i < obj.value.length ; i++){
		chr = obj.value.substr(i,1);  
	  	chr = escape(chr);
	  	key_eg = chr.charAt(1);
		if (key_eg == "u"){
	   		key_num = chr.substr(i,(chr.length-1));   
		   	if((key_num < "AC00") || (key_num > "D7A3")) { 
		    	event.returnValue = false;
		   	}    
	  	}
	}
	if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
	}else{
		event.returnValue = false;
	}
}

//등록 
function fn_tmAlloc(){
	if($.trim($("#nSCOUNT").val())=="0"){
		alert("배정할 회원이 없습니다. 다시 검색하세요.");
		return;
	}

	var iSum = 0;
	$(".clsCount").each(function(){
		iSum += Number($.trim($(this).val()));
	});
	if (iSum != $("#nSCOUNT").val()) {
		alert("배정한 회원수가 검색된 회원수와 다릅니다.")
	}
	
	if(confirm("회원을 배정하시겠습니까? \n \n 배정한 후에는 재배정할 수 없습니다.")){
		go_popup();
		$("#frm").attr("action","<c:url value='/tm/tmPassInsert.do' />");
		$("#frm").submit();
	}
}

function fn_SearchTM() {
// 검색 버튼 클릭
	$("#CMD").val("view");
	$("#frm").attr("action", "<c:url value='/tm/tmPassCount.do' />");
	$("#frm").submit();
}

function fn_changePassType(target){
	$("#frm").attr("action","<c:url value='/tm/tmPassCount.do' />");
	$("#frm").submit();
}

function go_popup() {	
	$('#rd-popup').bPopup();
}

function go_popup_close() {
	$('#rd-popup').bPopup().close();
}
</script>
</head>

<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
        
	<table class="table01">
		<tr>
			<th width="15%">구분</th>
			<td class="tdLeft">
		    	<select name="SUBJECT_PERIOD" id="SUBJECT_PERIOD">
				<option value="6" <c:if test="${ params.SUBJECT_PERIOD eq '6'}">selected="selected"</c:if>>6개월</option>
		        <option value="12" <c:if test="${ params.SUBJECT_PERIOD eq '12'}">selected="selected"</c:if>>12개월</option>
		        </select>  
		        <span><input type="text" id="SDATE" name="SDATE" value="${params.SDATE }" size="10"></span> ~
		        <span><input type="text" id="EDATE" name="EDATE" value="${params.EDATE }" size="10"></span>
		        <c:if test="${params.TMMANAGERYN eq 'Y'}" ></c:if><input type="button" onclick="fn_SearchTM();" value="검색" />
			</td>
		</tr>
		<tr>
			<th>검색된 주문건수</th>
		    <td class="tdLeft tdValign">
		    	<span class="tdLeft"><input type="text" name="nSCOUNT" id="nSCOUNT" size="5" maxlength="5" value="${allocCnt}" readonly /> 건</span>
		    </td>
		</tr>
		<tr>
			<th>주문 배정</th>
		    <td class="tdLeft tdValign">
				<c:set var="adminCnt" value="${fn:length(tmadminlist) }" />
		        <fmt:parseNumber var="perCnt" value="${allocCnt div adminCnt }" integerOnly="true"> </fmt:parseNumber>
		        <c:set var="firstCnt" value="${allocCnt - (perCnt * (adminCnt-1)) }" />
		        <c:forEach items="${tmadminlist}" var="item" varStatus="loop">
		        <input type="hidden" id="v_adminid" name="v_adminid" value="${item.ADMINID}" />
		        <input type="hidden" id="NM_${item.ADMINID}" name="NM_${item.ADMINID}" value="${item.ADMINNAME}" />
					<c:if test="${loop.index == '0'}" >
		        	<p><span class="tdLeft">${item.ADMINNAME } : <input type="text" id="ALLOCCNT_${item.ADMINID}" name="ALLOCCNT_${item.ADMINID}" value="${firstCnt}" class="clsCount" size="3" maxlength="3" onKeyDown="fn_OnlyNumber(this);"/> 건</span> </p>
		        	</c:if>
		        	<c:if test="${loop.index > '0'}" >
		        	<p><span class="tdLeft">${item.ADMINNAME } : <input type="text" id="ALLOCCNT_${item.ADMINID}" name="ALLOCCNT_${item.ADMINID}" value="${perCnt}" class="clsCount" size="3" maxlength="3" onKeyDown="fn_OnlyNumber(this);"/> 건</span> </p>
		        	</c:if>
		        </c:forEach>
			</td>
		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
		<li><a href="javascript:fn_tmAlloc();">배정하기</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
<div id="rd-popup" class="rd-Pstyle"  style="visibility:hidden;"><span class="b-close"></span></div>
</html>