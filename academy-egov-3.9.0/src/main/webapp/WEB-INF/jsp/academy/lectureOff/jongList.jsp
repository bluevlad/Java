<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="LECCODE" name="LECCODE" value="">
<input type="hidden" id="SEQ" name="SEQ" value="">
<input type="hidden" id="SUBJECT_ISUSE" name="SUBJECT_ISUSE" value="">

    <h2>● 강의관리 > <strong>강의개설</strong></h2>
    
    <ul class="lecWritheTab">
    	<li><a href="javascript:fn_goLecType('D');" <c:if test="${params.LEC_TYPE_CHOICE eq 'D'}">class="active"</c:if>>단과</a></li>
        <li><a href="javascript:fn_goLecType('J');" <c:if test="${params.LEC_TYPE_CHOICE eq 'J'}">class="active"</c:if>>종합반</a></li>
        <li><a href="javascript:fn_goLecType('N');" <c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">class="active"</c:if>>선택형 종합반</a></li>
        <li><a href="javascript:fn_goLecType('P');" <c:if test="${params.LEC_TYPE_CHOICE eq 'P'}">class="active"</c:if>>패키지</a></li>
    </ul>       
    
    <table class="table01">
		<tr>
			<th>과정선택</th>
			<td colspan="3">
	          	<label for="SEARCHKIND"></label>
	            <select name="SEARCHKIND" id="SEARCHKIND">
					<option value="">--직종검색--</option>
					<c:forEach items="${kindlist}" var="item" varStatus="loop">
						<option value="${item.CODE}" <c:if test="${params.SEARCHKIND == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
					</c:forEach>
	            </select>
	            <label for="SEARCHFORM"></label>
	           	<select name="SEARCHFORM" id="SEARCHFORM">
					<option value="">--학습형태검색--</option>
					<c:forEach items="${formlist}" var="item" varStatus="loop">
						<option value="${item.CODE}" <c:if test="${params.SEARCHFORM == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
					</c:forEach>
	          </select>
			</td>
		</tr>
        <tr>
			<th width="15%">검색</th>
			<td>
				<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="">--전체검색--</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>강의명</option>
					<option value="4" <c:if test="${params.SEARCHTYPE == '4' }">selected="selected"</c:if>>강의코드</option>
				</select> 
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
          </td>
          <th width="15%">화면출력건수</th>
          <td width="30%"><input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/> <input type="button" onclick="fn_Search()" value="검색" /></td>
        </tr>
    </table>
	
    <ul class="lecArrary">
      <li><span>활성된 강의</span> </li>
      <li><span class="span01">비활성된 강의</span> </li>
    </ul>
    
    <ul class="boardBtns">
        <li><a href="javascript:fn_OpenClose('Y');">개설</a></li>
        <li><a href="javascript:fn_OpenClose('N');">폐강</a></li>    
    	<li><a href="javascript:fn_Reg();">등록</a></li>
    </ul>
    
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    
	<table class="table02">
		<tr>
	        <th width="110">
	        	<input type="checkbox" id="allCheck" name="allCheck" VALUE="" onclick="fn_CheckAll('allCheck', 'JONGSEQ_ARR')"/>&nbsp;
	        	No</th>
	        <th width="80">직종</th>
	        <th width="75">학습형태</th>
	        <th>강의명</th>
	        <th width="80">등록일</th>
	        <th width="60">개설여부</th>
		</tr>
		<c:forEach items="${list}" var="item" varStatus="loop">
			<tr class="vitality">
		        <td class="tdCenter">
		        	<input type="checkbox" name="JONGSEQ_ARR" value="${item.JONGSEQ}"/>
		        	${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
		        <td>${item.CATEGORY_NM}</td>
		        <td>${item.LEARNING_NM}</td>
		        <td style="text-align:left;"><a href="javascript:fn_Modify('${item.LECCODE}','${item.JONGSEQ}');">${item.SUBJECT_TITLE}</a></td>
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
		        <c:set var="USESTATUS" value="" />
		        <c:set var="USESTATUSSTYLE" value="" />
				<c:if test="${item.SUBJECT_ISUSE eq 'Y' }">
					<c:set var="USESTATUS" value="개설" />
					<c:set var="USESTATUSSTYLE" value="style='color:green;'" />
				</c:if>
				<c:if test="${item.SUBJECT_ISUSE ne 'Y' }">
					<c:set var="USESTATUS" value="폐강" />
					<c:set var="USESTATUSSTYLE" value="style='color:#999999;'" />				
				</c:if>		        
		        <td class="txt03" ${USESTATUSSTYLE}>${USESTATUS}</td>
			</tr>
		</c:forEach>	
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="6">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>	 		
    </table>   

	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
    
    <ul class="boardBtns">
        <li><a href="#">개설</a></li>
        <li><a href="#">폐강</a></li>    
   	  	<li><a href="javascript:fn_Reg();">등록</a></li>
    </ul>
    
</form>
</div>
<script type="text/javascript">
// 페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/lectureOff/jongList.do' />");
	$("#frm").submit();
}

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

//삭제
function fn_Delete(){
	if($("input[name='DEL_ARR']:checked").length > 0){
		if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
			$("#frm").attr("action", "<c:url value='/lectureOff/listDelete.do' />");
			$("#frm").submit();
		}
	}else{
		alert("선택된 항목이 없습니다");
		return;
	}
}

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/lectureOff/jongList.do' />");
	$("#frm").submit();
}

// 등록폼
function fn_Reg(){
	$("#frm").attr("action", "<c:url value='/lectureOff/jongWrite.do'/>");
	$("#frm").submit();
}

//개설/폐강 다중 처리
function fn_OpenClose(param){
	if($("input[name='JONGSEQ_ARR']:checked").length < 1){
		alert('강의를 선택해주세요');
		return;
	}
	//체크 안된건 value 값 삭제
	$("#SUBJECT_ISUSE").val(param);
	$("#frm").attr("action", "<c:url value='/lectureOff/multiUpdateUsg.do'/>");
	$("#frm").submit();
}

// 수정폼
function fn_Modify(val, val2){
	$("#LECCODE").val(val);
	$("#SEQ").val(val2);
	$("#frm").attr("action", "<c:url value='/lectureOff/jongModify.do' />");
	$("#frm").submit();
}

// 엔터키 검색
function fn_Enter(){
	$("#SEARCHTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			fn_Search();
	});
}

// RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		fn_Search();
		return;
	}
	if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#pageRow").val("${params.pageRow}");
    }
}

function fn_IsNumber(input) {
    var chars = "0123456789";
    for (var inx = 0; inx < input.value.length; inx++) {
        if (chars.indexOf(input.value.charAt(inx)) == -1)
            return false;
     }
     return true;
}

function fn_goLecType(val){
	$("#LEC_TYPE_CHOICE").val(val);
	if(val=="D"){
		$("#frm").attr("action", "<c:url value='/lectureOff/list.do' />");
	}else{
		$("#frm").attr("action", "<c:url value='/lectureOff/jongList.do' />");
	}
	$("#frm").submit();	
}

// 리로드
function fn_reload(){
	$("#frm").attr("action", "<c:url value='/lectureOff/jongList.do' />");
	$("#frm").submit();
}

//VOD,PMP,동영상 유무 팝업
function fn_PayListPop(gubn, type){
	window.open('<c:url value="/lectureOff/jongPayList.pop"/>?SEARCHPAYYN=' + gubn + '&SEARCHPAYTYPE=' + type, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
}
</script>
</body>
</html>