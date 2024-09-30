<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("searchStartDate");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDatePicker1("searchEndDate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function fn_search() {
	var date1 = parseInt($("#searchStartDate").val());
	var date2 = parseInt($("#searchEndDate").val());
	if(date1 > date2) {
		alert("검색일을 잘못 입력하였습니다.\n검색 시작일은 종료일보다 늦게 설정할 수 없습니다.");
		return;
	}
	$("#currentPage").val(1);
	$("#pageRow").val(10);	
	
	$("#form").attr("action", "<c:url value='/offmng/teacher/offTeacherAdjustList.do' />");
	$("#form").submit();
}

function fn_searchall() {
	var date1 = parseInt($("#searchStartDate").val());
	var date2 = parseInt($("#searchEndDate").val());
	if(date1 > date2) {
		alert("검색일을 잘못 입력하였습니다.\n검색 시작일은 종료일보다 늦게 설정할 수 없습니다.");
		return;
	}
	$("#currentPage").val(1);
	$("#pageRow").val(10000);	
	
	$("#form").attr("action", "<c:url value='/offmng/teacher/offTeacherAdjustList.do' />");
	$("#form").submit();
}

function fn_detail(id, leccode) {
	$("#searchTeacher").val(id);
	$("#searchLeccode").val(leccode);
	$("#form").attr("action", "<c:url value='/offmng/teacher/offTeacherAdjustDetail.do' />");
	$("#form").submit();
}

function fn_excel() {
	$("#form").attr("action", "<c:url value='/offmng/teacher/offTeacherAdjustExcel.do' />");
	$("#form").submit();
}

function goList(page) {	
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$("#form").attr("action","<c:url value='/offmng/teacher/offTeacherAdjustList.do' />");
	$("#form").submit();
}
</script>
</head>

<div id="content">
<form id="form" name="form" method="post">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="searchTeacher" name="searchTeacher" value=""/>
<input type="hidden" id="searchLeccode" name="searchLeccode" value=""/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<h2>● 경영관리 &gt; <strong>강사료 정산관리</strong></h2>

    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="13%">
				<label for="SEARCHTYPE"></label>
				<select style="width:100px;"   id="searchType" name="searchType">
					<option value="SETTLE_DT"  <c:if test="${params.searchType == 'SETTLE_DT'}">selected</c:if>>정산일자</option>
					<option value="SUBJECT_CLOSE_DATE"  <c:if test="${params.searchType == 'SUBJECT_CLOSE_DATE'}">selected</c:if>>강의종료일자</option>
					<option value="SUBJECT_OPEN_DATE"  <c:if test="${params.searchType == 'SUBJECT_OPEN_DATE'}">selected</c:if>>강의시작일자</option>
				</select>
            </th>
            <td width="40%">
            	<input type="text" id="searchStartDate" name="searchStartDate" value="${searchStartDate}" maxlength="8" readonly="readonly"/> 
             	~
             	<input type="text" id="searchEndDate" name="searchEndDate" value="${searchEndDate}" maxlength="8" readonly="readonly"/>
            </td>
            <th width="10%">강사명</th>
            <td>
            	<input type="text" id="searchTeacherName" name="searchTeacherName" value="${searchTeacherName}" /> 
				<input type="button" onclick="fn_search()" value="검색" />
				<input type="button" onclick="fn_searchall()" value="NoPage검색" />
            </td>
		</tr>
	</table>
    <!-- //검색 -->
     
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>

	<c:set var="sumDCnt" value="0" />
	<c:set var="sumJCnt" value="0" />
	<c:set var="sumPREAMOUNT" value="0" />
	<c:set var="sumAMOUNT" value="0" />
	<c:set var="sumDEDUCTAMOUNT" value="0" />
	<c:set var="sumTEACHERAMOUNT" value="0" />
	<c:set var="sumTEACHERPAY" value="0" />
	<c:set var="sumWITHHOLDTAX" value="0" />
	<c:set var="sumDEDUCTAMOUNT_ETC" value="0" />
	<c:set var="sumADJUSTAMOUNT" value="0" />
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="5%" rowspan="2">정산일자</th>
	        <th width="5%" rowspan="2">강사명</th>
	        <th width="5%" rowspan="2">과목</th>
	        <th width="15%" rowspan="2">강의명</th>
	        <th width="5%" rowspan="2">강의시작</th>
	        <th width="5%" rowspan="2">강의종료</th>
	        <th width="5%" rowspan="2">수강인원(명)</th>
	        <th width="50%" colspan="9">정산금액(원)</th>
   		</tr>
   		<tr>
	        <th width="7%">공제전 수강총액</th>
	        <th width="7%">수강총액</th>
	        <th width="4%">공제액</th>
	        <th width="7%">강사료산정 대상금액</th>
	        <th width="5%">비율</th>
	        <th width="5%">강사료</th>
	        <th width="5%">원천세</th>
	        <th width="5%">기타공제액</th>
	        <th width="5%">정산금액</th>
		</tr>
        <tbody>
        	<c:if test="${fn:length(list) == 0 }">
        	<td colspan="18">데이터가 존재하지 않습니다.</td>
        	</c:if>
        	<c:if test="${fn:length(list) > 0 }">
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<c:set var="sumCnt" value="${sumCnt + item.OFFERERCNT}" />	        
				<c:set var="sumPREAMOUNT" value="${sumPREAMOUNT + item.PREAMOUNT}" />	        
				<c:set var="sumAMOUNT" value="${sumAMOUNT + item.AMOUNT}" />	        
				<c:set var="sumDEDUCTAMOUNT" value="${sumDEDUCTAMOUNT + item.DEDUCTAMOUNT}" />	        
				<c:set var="sumTEACHERAMOUNT" value="${sumTEACHERAMOUNT + item.TEACHERAMOUNT}" />	        
				<c:set var="sumTEACHERPAY" value="${sumTEACHERPAY + item.TEACHERPAY}" />	        
				<c:set var="sumWITHHOLDTAX" value="${sumWITHHOLDTAX + item.WITHHOLDTAX}" />	        
				<c:set var="sumDEDUCTAMOUNT_ETC" value="${sumDEDUCTAMOUNT_ETC + item.DEDUCTAMOUNT_ETC}" />	        
				<c:set var="sumADJUSTAMOUNT" value="${sumADJUSTAMOUNT + item.ADJUSTAMOUNT}" />	        
			<tr>
				<td>${item.SETTLE_DT}</td>
				<td>${item.TEACHER_NM}</td>
				<td>${item.SUBJECT_NM }</td>
				<td><a href="javascript:fn_detail('${item.SUBJECT_TEACHER}','${item.BRIDGE_LECCODE}');">${item.SUBJECT_TITLE}</a></td>
				<td><fmt:formatDate value="${item.MIN_DATE}" pattern="yyyyMMdd"/></td>
				<td><fmt:formatDate value="${item.MAX_DATE}" pattern="yyyyMMdd"/></td>
				<td>${item.OFFERERCNT}</td>
				<td><fmt:formatNumber value="${item.PREAMOUNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.AMOUNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.DEDUCTAMOUNT}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.TEACHERAMOUNT}" groupingUsed="true"/></td>
				<td>${item.CALCUCRITERIA_DVALUE}(%), ${item.CALCUCRITERIA_JVALUE}(%)</td>
				<td><fmt:formatNumber value="${item.TEACHERPAY}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.WITHHOLDTAX}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.DEDUCTAMOUNT_ETC}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${item.ADJUSTAMOUNT}" groupingUsed="true"/></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="6"><font color="red">인원합계</font></td>
				<td><fmt:formatNumber value="${sumCnt }" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${sumPREAMOUNT }" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${sumAMOUNT }" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${sumDEDUCTAMOUNT }" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${sumTEACHERAMOUNT }" groupingUsed="true"/></td>
				<td> </td>
				<td><fmt:formatNumber value="${sumTEACHERPAY }" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${sumWITHHOLDTAX }" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${sumDEDUCTAMOUNT_ETC }" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${sumADJUSTAMOUNT }" groupingUsed="true"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_excel();">엑셀</a></li>
      <li><a href="javascript:goList(1);">목록</a></li>
    </ul>	     
   <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
  <!--//paginate-->
	     
    <!-- //테이블--> 
</form>
</div>    

</html>