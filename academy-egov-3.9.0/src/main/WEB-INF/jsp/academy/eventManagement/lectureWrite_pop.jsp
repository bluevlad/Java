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
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="BRIDGE_LECCODE" name="BRIDGE_LECCODE" value="">
<input type="hidden" id="LECCODE" name="LECCODE" value="">
<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="${params.EVENT_NO}">

    <h2>● 이벤트 강의 등록</h2>
    
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
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>과목</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>강의명</option>
					<option value="3" <c:if test="${params.SEARCHTYPE == '3' }">selected="selected"</c:if>>강사명</option>
					<option value="4" <c:if test="${params.SEARCHTYPE == '4' }">selected="selected"</c:if>>강의코드</option>
				</select> 
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
          </td>
          <th width="15%">화면출력건수</th>
          <td width="30%"><input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/> <input type="button" onclick="fn_Search()" value="검색" /></td>
        </tr>
    </table>
    
    <ul class="boardBtns">
    	<li><a href="javascript:fn_Reg();">이벤트강의등록</a></li>
    </ul>
    
<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>    
    
	<table class="table02">
		<tr>
	        <th width="110">
	       		<input type="checkbox" name="allCheck" id="allCheck" value="" onclick="fn_CheckAll('allCheck', 'LEC_ARR')" /> No
	        </th>
	        <th width="80">직종</th>
	        <th width="80">과목</th>
	        <th width="75">강의코드</th>
	        <th width="55">강사명</th>
	        <th>강의명</th>
	        <th width="75">학습형태</th>
	        <th width="110">동영상 / MP4 / PMP</th>
	        <th width="80">등록일</th>
		</tr>      
		
		<c:forEach items="${list}" var="item" varStatus="loop">
			<tr id="TR_${item.BRIDGE_LECCODE}" <c:if test="${item.SUBJECT_ISUSE eq 'Y'}">class="vitality"</c:if>>
		        <td class="tdCenter">
					<input type="checkbox" name="LEC_ARR" value="${item.LECCODE}" />
		        	${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}
		        </td>
		        <td>${item.CATEGORY_NM}</td>
		        <td>${item.SUBJECT_NM}</td>
		        <td>${item.LECCODE}<br>${item.BRIDGE_LECCODE}</td>
		        <td>${item.SUBJECT_TEACHER_NM}</td>
		        <td style="text-align:left;">${item.SUBJECT_TITLE}</td>
		        <td>${item.LEARNING_NM}</td>
		        <td>
		        	<c:set var="OPTIONSTR" value="" />
					<c:if test="${!empty item.SUBJECT_OPTION}">
						<c:set var="SOA" value="${fn:split(item.SUBJECT_OPTION,',')}"/>
						<c:forEach items="${SOA}" var="item2" varStatus="loop2">
							<c:if test="${item2 eq 'AA'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}MP4" />
							</c:if>
							<c:if test="${item2 eq '2'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}PMP" />							
							</c:if>						
							<c:if test="${item2 eq '1'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}동영상" />							
							</c:if>
							<c:if test="${item2 eq '4'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}동영상" />							
							</c:if>
						</c:forEach>        			
					</c:if>
		        	${OPTIONSTR}
		        </td>			        
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>	
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="10">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>	 		
    </table>   

	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
    
    <ul class="boardBtns">
   	  	<li><a href="javascript:fn_Reg();">이벤트강의등록</a></li>
    </ul>
    
</form>

<script type="text/javascript">
$(window).bind('beforeunload',  function (){
	window.opener.location.href = window.opener.location.href;
});

// 페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/LecEventMng/lectureWrite.pop' />");
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

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/LecEventMng/lectureWrite.pop' />");
	$("#frm").submit();
}

//등록
function fn_Reg(){
	if(confirm("이벤트 강의를 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/LecEventMng/eventLectureInsertProcess.do' />");
		$("#frm").submit();
	}
//	self.close();
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
</script>
</body>
</html>