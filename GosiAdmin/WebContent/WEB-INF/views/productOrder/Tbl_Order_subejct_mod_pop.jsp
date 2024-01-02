<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
var USER_ID = "${searchMap.USER_ID}";

var orderstatuscode = "${searchMap.orderstatuscode}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	if(USER_ID != ""){
		getSubCODE('CLASSSERIESCODE', '<c:url value="/productOrder/subCode.do"/>', USER_ID);
	}
}

//엔터키 검색
function fn_checkEnter(){
	 $('#search_keyword').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//검색
function goList(page) {	
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/pop_subject_mod.pop"/>').submit();
}

//수강신청하기 클릭
function put_leccode(LECCODE, SUBJECT_NM, SUBJECT_REAL_PRICE) {	
	window.opener.searchFrm.MGNTNO_<c:out value="${searchMap.Num}"/>.value=LECCODE;
	window.opener.searchFrm.REALPRICE_<c:out value="${searchMap.Num}"/>.value=SUBJECT_REAL_PRICE;
	window.opener.searchFrm.ORG_PRICE_<c:out value="${searchMap.Num}"/>.value=SUBJECT_REAL_PRICE;
	window.opener.document.getElementById("SUBJECT_NM_<c:out value='${searchMap.Num}'/>").innerHTML = SUBJECT_NM;
	window.opener.document.getElementById("SUBJECT_REAL_PRICE_<c:out value='${searchMap.Num}'/>").innerHTML = SUBJECT_REAL_PRICE;
	window.self.close();
}
</script>
</head>
  <!--content -->
  <div>
  
    <form id="searchFrm" name="searchFrm" method="post">

	<input type="hidden" id="USER_ID" name="USER_ID" value="${searchMap.USER_ID}">	
	
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
	
	<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
	<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
	<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
	<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
	<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
	<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />
	
   <!--테이블--> 
      <table class="table01">
          <tr>
            <th width="15%">검색</th>
            <td>            
            	<select id="s_cat_cd" name="s_cat_cd">
					<option value="">--전체검색--</option>
				<c:forEach items="${category_list}"  var="category_list">
					<option value="${category_list.CODE }" <c:if test="${searchMap.s_cat_cd == category_list.CODE}">selected</c:if>>${category_list.NAME }</option>
				</c:forEach>
				</select>
					
				<select id="s_menu_id" name="s_menu_id">
					<option value="">--전체검색--</option>
				<c:forEach items="${lec_list}"  var="lec_list">
					<option value="${lec_list.CODE }" <c:if test="${searchMap.s_menu_id == lec_list.CODE}">selected</c:if>>${lec_list.NAME }</option>
				</c:forEach>
				</select>
				
				<select id="s_sjt_cd" name="s_sjt_cd">
					<option value="">--전체검색--</option>
				<c:forEach items="${subject_list}"  var="subject_list">
					<option value="${subject_list.SUBJECT_CD }" <c:if test="${searchMap.s_sjt_cd == subject_list.SUBJECT_CD}">selected</c:if>>${subject_list.SUBJECT_NM }</option>
				</c:forEach>
				</select>
				
				<select id="search_type" name="search_type">
					<option value="1" <c:if test="${searchMap.search_type == '1'}">selected</c:if>>강사명</option>
					<option value="2" <c:if test="${searchMap.search_type == '2'}">selected</c:if>>강의코드</option>
					<option value="3" <c:if test="${searchMap.search_type == '3'}">selected</c:if>>강의명</option>
			   	</select>
				
				<input  title="검색" size="15" type="text" id="search_keyword" name="search_keyword"  value="${searchMap.search_keyword}" onkeypress="fn_checkEnter()">
				
				<input type="button"   onclick="goList(1)"  value="검색" />
		    </td>
          </tr>
      </table>
    
    <!--//테이블-->	
       
	<p style="text-align:left;">&nbsp;<span>총${totalCount}건</span></p>
    
    <table  style="width:100%;">
	    <tr>
			<td>
			    <!--테이블-->
			     <table class="table02" style="width:100%;">
			        <tr>
						<th>직종</th>
						<th>학습형태</th>
						<th>과목</th>
						<th>강의명</th>
						<th>강의상태</th>
						<th>구분</th>
						<th>회차</th>
						<th>수강료</th>
						<th>선택</th>
			        </tr>
			         <c:if test="${not empty list}">
			          <c:forEach items="${list}" var="list" varStatus="status">
			             <tr>
							<td>${list.CATEGORY_NM}</td>
							<td>${list.LEARNING_NM}</td>
							<td>${list.SUBJECT_NM}</td>
							<td style="text-align:left;">${list.SUBJECT_TITLE}</td>
				            <td>${list.LEC_STAT}</td>
				            <td>${list.SUBJECT_TYPE_NM}</td>
				            <td>${list.LEC_SCHEDULE}</td>
				            <td><fmt:formatNumber value="${list.SUBJECT_REAL_PRICE}" type="currency" /></td>
				            <td>
				            	<input type="button" onclick="javascript:put_leccode('${list.LECCODE}', '${list.SUBJECT_TITLE}', '${list.SUBJECT_REAL_PRICE}')" value="선택" />
				            </td>
				        </tr>
			      </c:forEach>
					</c:if>
					<c:if test="${empty list}">
					         <tr bgColor=#ffffff align=center> 
						<td colspan="9">데이터가 존재하지 않습니다.</td>
					</tr>
					</c:if>	 
			     </table>
			     <!--//테이블--> 
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
	     
		     </td>
	     </tr>
     </table>
</form>
</div>
</html>