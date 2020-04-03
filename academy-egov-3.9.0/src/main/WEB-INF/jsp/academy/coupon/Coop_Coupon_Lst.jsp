<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<html>
<head></head>
<body>
<!--content -->
  <div id="content">
	<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
    
    <!--테이블-->
    <form id="searchFrm" name="searchFrm" method="post">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
	<input type="hidden" id="page" name="page" value="${params.page}" />

	<input type="hidden" id="COOP_CD" name="COOP_CD" value="" />
	<input type="hidden" id="LECCODE" name="LECCODE" value="" />

        <table class="table01">
        <tr>
          <th width="10%">검색</th>
          <td>
			<label for="SEARCHTYPE"></label>
			<select style="width:150px;" id="S_COOP_CD" name="S_COOP_CD">
				<option value=""  >-제휴사선택-</option>
				<c:forEach items="${coop_list}" var="item" varStatus="loop">
				<option value="${item.COOP_CD}" <c:if test="${params.S_COOP_CD == item.COOP_CD}">selected</c:if>>${item.COOP_NM}</option>
				</c:forEach>
			</select>
			<select style="width:80px;" id="SEARCHTYPE" name="SEARCHTYPE">
				<option value=""  >-전체검색-</option>
				<option value="NM"  <c:if test="${params.SEARCHTYPE == 'NM'}">selected</c:if>>수강권명</option>
				<option value="TITLE"  <c:if test="${params.SEARCHTYPE == 'TITLE'}">selected</c:if>>제공강좌명</option>
				<option value="COUPON_CD"  <c:if test="${params.SEARCHTYPE == 'COUPON_CD'}">selected</c:if>>쿠폰코드</option>
			</select>
			<label for="SEARCHTEXT"></label>
			<input class="i_text"  title="검색" type="text" id="SEARCHTEXT" name="SEARCHTEXT"  type="text" size="40"  value="${params.SEARCHTEXT}" onkeypress="fn_checkEnter()">
          </td>
		  <th width="15%">화면출력건수</th>
		  <td width="20%">
               	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
                <input name="textfield3" type="button" id="textfield3" value="검색" onclick="goList(1)"  >
          </td>          
        </tr>
    </table>           
       
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
								
	<table class="table02" style="width:100%;">
		<tr>
			<th style="width:5%;">No.</th>
          	<th style="width:8%;">제휴사명</th>
          	<th style="width:*;">수강권명</th>
          	<th style="width:35%;">제공강좌</th>
          	<th style="width:15%;">유효기간</th>
          	<th style="width:5%;">발행수</th>
          	<th style="width:5%;">인증수</th>
          	<th style="width:5%;">삭제</th>
        </tr>
	    <tbody>
        <c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
	    <tr<c:if test="${list.EXPD eq 'Y'}">class="vitality"</c:if>>
	    	<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
			<td>${list.COOP_NM}</td>
			<td>${list.COUPON_NM}</td>
			<td><a href="javascript:lec_view('${list.LECCODE}', '${list.CATEGORY_CD}' , '${list.LEARNING_CD}', '${list.LEC_TYPE_CHOICE}' );">${list.SUBJECT_TITLE}</a></td>
			<td>${list.ST_DT} ~ ${list.ED_DT}</td>
			<td><a href="javascript:coupon_list('${list.COOP_CD}', '${list.LECCODE}')">${list.COUPON_CNT}</a></td>
			<td><a href="javascript:user_list('${list.COOP_CD}', '${list.LECCODE}')">${list.COUPON_USE}</a></td>
			<td><a href="javascript:fn_Del('${list.COOP_CD}', '${list.LECCODE}','${list.COUPON_USE}');">[삭제]</a></td>
		</tr>
		</c:forEach>
		</c:if>

		<c:if test="${empty list}">
        <tr bgColor=#ffffff align=center> 
        	<td colspan="11">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
       </tbody>
    </table>
    <!--//테이블-->
	
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->

    <ul class="boardBtns">
   	  	<li><a href="javascript:fn_Reg();">등록</a></li>
    </ul>
</form>
</div> 
<script type="text/javascript">
//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
		$('#searchFrm').attr('action','<c:url value="/Coupon/coop_coupon_list.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#searchkey').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//등록폼
function fn_Reg(){
	$("#searchFrm").attr("action", "<c:url value='/Coupon/CoopCouponWrite.do'/>");
	$("#searchFrm").submit();
}

//삭제
function fn_Del(coop_cd, leccode, cnt){
	if (cnt > 0) {
		alert("이미 인증된 쿠폰 내역이 있습니다.");
		return;
	} else {
		if(confirm("발행한 쿠폰도 모두 삭제됩니다. 수강권을 삭제하시겠습니까?")){
			$("#COOP_CD").val(coop_cd);
			$("#LECCODE").val(leccode);
			$("#searchFrm").attr("action", "<c:url value='/Coupon/CoopCouponDelete.do'/>");
			$("#searchFrm").submit();
		}
	}
}

function lec_view(leccode, category_cd, learning_cd, lec_type){
	if (lec_type == "D") {
	   	window.open('<c:url value="http://www.willbesgosi.net/lecture/movieLectureDetail.html"/>?topMenu='+category_cd+'&topMenuType=O&searchSubjectCode='+learning_cd+'&searchLeccode=' + leccode, '_blank', 'location=no,resizable=no,width=1080,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
	}else {
	   	window.open('<c:url value="http://www.willbesgosi.net/lecture/movieLectureSDetail.html"/>?topMenu='+category_cd+'&topMenuType=O&searchSubjectCode='+learning_cd+'&searchLeccode=' + leccode, '_blank', 'location=no,resizable=no,width=1080,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
	}
}

function coupon_list(coop_cd, leccode){
   	window.open('<c:url value="/Coupon/coop_coupon_all.pop"/>?COOP_CD=' + coop_cd + '&LECCODE=' + leccode, '_blank', 'location=no,resizable=yes,width=800,height=500,top=0,left=0,scrollbars=yes,location=no');
}

function user_list(coop_cd, leccode){
   	window.open('<c:url value="/Coupon/coop_coupon_ord.pop"/>?COOP_CD=' + coop_cd + '&LECCODE=' + leccode, '_blank', 'location=no,resizable=yes,width=800,height=500,top=0,left=0,scrollbars=yes,location=no');
}
</script>
</html>