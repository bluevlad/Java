<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>

<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/images/btn_calendar.gif'/>");
	initDatePicker1("SDATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDatePicker1("EDATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	if ($("#sUSERTYPE").val() == "D") {
		$("#endDate").show();
	} else {
		$("#endDate").hide();
	}
	<c:if test="${params.TMMANAGERYN eq 'N'}" > 
		alert('TM 회원을 배정할 권한이 없습니다.');
	</c:if>
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
	// 필수과목 강좌코드
	var allocNos = "";
	var iSum = 0;
	$(".clsCount").each(function(){
		if (allocNos != "") allocNos += ","; 
		allocNos += $.trim($(this).val());
		iSum += Number($.trim($(this).val()));
	});
	$("#TMALLOCNOS").val(allocNos);
	if (iSum != $("#nSCOUNT").val()) {
		alert("배정한 회원수가 검색된 회원수와 다릅니다.")
//		alert("배정한 회원수가 검색된 회원수와 다릅니다. \n 배정한 회원수를 확인하세요.")
//		return;
	}
	
//	alert($("#TMADMINS").val());
//	alert($("#TMALLOCNOS").val());
//	alert($("#CMD").val());
	$("#CMD").val("alloc");
	if(confirm("회원을 배정하시겠습니까? \n \n 배정한 후에는 재배정할 수 없습니다.")){
		$("#frm").attr("action","<c:url value='/tm/tmAlloc.do' />");
		$("#frm").submit();
	}
}

function fn_SearchTM() {
// 검색 버튼 클릭
	$("#CMD").val("view");
	$("#frm").attr("action", "<c:url value='/tm/tmAlloc.do' />");
	$("#frm").submit();
}

function fn_changeUserType(target){
	var boardType = $(target).val();
	
	if(boardType == 'D'){
		$("#endDate").show();
	} else {
		$("#endDate").hide();
	}
}

</script>
</head>


<!--content -->
<div id="content">
	<form name="frm" id="frm" class="form form-horizontal" method="post" action="">
		<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
		<input type="hidden" id="SEARCHBANNERNO" name="SEARCHBANNERNO" value="${params.SEARCHBANNERNO}"/>
		<input type="hidden" id="SEARCHISUSE" name="SEARCHISUSE" value="${params.SEARCHISUSE}"/>
		
		<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
		<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
		<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
		<input type="hidden" id="CMD" name="CMD" value="view"/>
	
    	<h2>● TM 관리 &gt; <strong>TM 회원배정</strong></h2>
        
    	<table class="table01">
		      <tr>
		        <th width="15%">구분</th>
		        <td class="tdLeft">
		        <select name="sUSERTYPE" id="sUSERTYPE" onchange="fn_changeUserType(this);">
		          <option value="A" <c:if test="${ params.USERTYPE eq 'A'}">selected="selected"</c:if>>신규</option>
		          <option value="B" <c:if test="${ params.USERTYPE eq 'B'}">selected="selected"</c:if>>재수강</option>
		          <option value="C" <c:if test="${ params.USERTYPE eq 'C'}">selected="selected"</c:if>>장바구니</option>
		          <option value="D" <c:if test="${ params.USERTYPE eq 'D'}">selected="selected"</c:if>>회수(부재)</option>
		        </select>  <span><input type="text" id="SDATE" name="SDATE" value="${ params.SDATE }" size="10"></span>
		        	<span id="endDate"><input type="text" id="EDATE" name="EDATE" value="${ params.EDATE }" size="10"></span>
		        	<c:if test="${params.TMMANAGERYN eq 'Y'}" ><input type="button" onclick="fn_SearchTM();" value="검색" /></c:if>
		          </td>
		      </tr>
		      <tr>
		        <th>검색된 회원수</th>
		        <td class="tdLeft tdValign"><span class="tdLeft">
		          <input type="text" name="nSCOUNT" id="nSCOUNT" size="5" maxlength="5" value="${alloccnt }" readonly /> 건
				</span></td>
		      </tr>
		      <tr>
		        <th>회원 배정</th>
		        <td class="tdLeft tdValign">
		        	<c:set var="adminCnt" value="${fn:length(tmadminlist) }" />
		        	<fmt:parseNumber var="perCnt" value="${alloccnt div adminCnt }" integerOnly="true"> </fmt:parseNumber>
		        	<c:set var="firstCnt" value="${alloccnt - (perCnt * (adminCnt-1)) }" />
		        	<c:set var="tmAdminIds" value="" />
		        	<c:set var="tmAdminNames" value="" />
		        	<c:forEach items="${tmadminlist}" var="item" varStatus="loop">
		        		<c:if test="${loop.index == '0'}" >
		        			<c:set var="tmAdminIds" value="${item.ADMINID}" />
		        			<c:set var="tmAdminNames" value="${item.ADMINNAME}" />
		        			<p><span class="tdLeft">${item.ADMINNAME } : <input type="text" id="ALLOCCNT_${item.ADMINID}" name="ALLOCCNT_${item.ADMINID}" value="${firstCnt}" class="clsCount" size="3" maxlength="3" onKeyDown="fn_OnlyNumber(this);"/> 건</span> </p>
		        		</c:if>
		        		<c:if test="${loop.index > '0'}" >
		        			<c:set var="tmAdminIds" value="${tmAdminIds},${item.ADMINID}" />
		        			<c:set var="tmAdminNames" value="${tmAdminNames},${item.ADMINNAME}" />
		        			<p><span class="tdLeft">${item.ADMINNAME } : <input type="text" id="ALLOCCNT_${item.ADMINID}" name="ALLOCCNT_${item.ADMINID}" value="${perCnt}" class="clsCount" size="3" maxlength="3" onKeyDown="fn_OnlyNumber(this);"/> 건</span> </p>
		        		</c:if>
		        	</c:forEach>
		        	<input type="hidden" id="TMADMINS" name="TMADMINS" value="${tmAdminIds}" />
		        	<input type="hidden" id="TMADMINNAMES" name="TMADMINNAMES" value="${tmAdminNames}" />
		        	<input type="hidden" id="TMALLOCNOS" name="TMALLOCNOS" value="0" />
		        </td>
		      </tr>
		      <tr>
		        <th>TM 사이트명</th>
		        <td class="tdLeft tdValign"><span class="tdLeft">
		          <input type="text" name="USERSOURCE" id="USERSOURCE" size="20" maxlength="20" value="노량진공무원"/> 
				</span></td>
		      </tr>
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><c:if test="${params.TMMANAGERYN eq 'Y'}" ><a href="javascript:fn_tmAlloc();">배정하기</a></c:if></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
