<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mt_upg.css' />" >

<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/images/btn_calendar.gif'/>");
	initDateFicker2("SDATE", "EDATE");	
});
	
//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	if($("#SDATE").val()!='' || $("#EDATE").val()!=''){
		if($("#SDATE").val()!='' && $("#EDATE").val()!=''){
			if(parseInt($("#EDATE").val().replace(/-/g,'')) < parseInt($("#SDATE").val().replace(/-/g,''))){
				alert('검색일자 종료일은 시작일보다 큰 날짜를 선택해야 합니다.');
				return;
			}			
		}		
	}
	
	$('#searchFrm').attr('action','<c:url value="/tm/tmPassList.do"/>').submit();
}

//엑셀
function excel_onclick1() { //ck 추가
	<c:if test="${empty list}">
		alert('엑셀파일로 저장할 데이타가 없습니다.');
		return;
	</c:if>
	$('#searchFrm').attr('action','<c:url value="/tm/tmPassOrderListExcel.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#SEARCHTEXT').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}
</script>
</head>

<!--content -->
<div id="content">
	   
    <!--테이블-->    
<form id="searchFrm" name="searchFrm" method="post">    
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
	
	<table class="table01">
    	<tr>
        	<th width="10%">수강종료일</th>
          	<td  style="width:60%;">
          		<input type="text" id="SDATE" name="SDATE" maxlength="10" value="${params.SDATE}" style="width:90px;"/>
           		~
           		<input type="text" id="EDATE" name="EDATE" maxlength="10" value="${params.EDATE}" style="width:90px;"/>
				 &nbsp;
		    	<select name="PTYPE" id="PTYPE">
				<option value="" >--강의기간구분--</option>
				<option value="6" <c:if test="${ params.PTYPE eq '6'}">selected="selected"</c:if>>6개월</option>
		        <option value="12" <c:if test="${ params.PTYPE eq '12'}">selected="selected"</c:if>>12개월</option>
		        </select>  
				&nbsp;&nbsp;
				<input type="text" class="i_text"  title="검색" id="SEARCHTEXT" name="SEARCHTEXT"  type="text" style="width:150px;"  value="${params.SEARCHTEXT}" onkeypress="fn_checkEnter()">
        		<span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="goList(1)">검색</button></span>
			</td>
        	<td><span class="btn_pack medium" style="vertical-align:middle;align:right;"><button type="button" onclick="excel_onclick1();">Excel</button></span></td>
		</tr>
	</table>
      
	<p class="pInto01">&nbsp;<span>- 전체 결제건 : ${totalCount} 건
	&nbsp;&nbsp;/&nbsp;- 전체 수강금액 : <fmt:formatNumber value="${totalLecPrice}" groupingUsed="true"/> 원 
	&nbsp;&nbsp;/&nbsp;- 전체 결제금액 : <fmt:formatNumber value="${totalPrice}" groupingUsed="true"/> 원 / Page:(<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span>
	</p>
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">No</th>
			<th>주문번호</th>
          	<th>주문자(아이디)</th>
			<th>결제일</th>
			<th>강의명</th>
			<th>강의금액</th>
			<th>결제금액</th>
			<th>상담원(아이디)</th>
			<th>배정일(최종통화일)</th>
        </tr>
		<c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
			<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
			<td>${list.ORDERNO}</td>
			<td>${list.USER_ID}(${list.USER_NM})</td>
			<td>${list.ISCONFIRM}</td>
			<td>${list.SUBJECT_TITLE}</td>
			<td>${list.SUBJECT_MOVIE}</td>
			<td>${list.PRICE}</td>
			<td>${list.ADMUSERID}(${list.ADMUSERNAME})</td>
			<td>${list.LASTREGDATE}</td>
		</tr>
		</c:forEach>
		</c:if>

		<c:if test="${empty list}">
	    <tr bgColor=#ffffff align=center> 
			<td colspan="9">표시할 데이터가 없습니다.</td>
		</tr>
		</c:if>	 
	</table>
    <!--//테이블-->
	
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
</form>

</div> 
</html>