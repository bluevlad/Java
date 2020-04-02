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
	$('#searchFrm').attr('action','<c:url value="/tm/tmPassListExcel.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#SEARCHTEXT').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//주문자 클릭시
function user_view(userid){
	if(userid=="" || userid ==null){
	     alert("비회원입니다.");
	     return;
	}else{
	 	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
	}
}

function Detailview(userid, orderno){
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
    	window.open('<c:url value="/lecture/FreePassDetailList.pop"/>?USER_ID=' + userid+ '&ORDERNO=' + orderno, '_blank', 'location=no,resizable=no,width=1050,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
    }
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
	
    <ul class="tabA">
        <li><a href="<c:url value="tmPassList.do"/>?MENU_ID=${MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${MENU_NM}" class="active">한눈에보기</a></li>
        <li><a href="<c:url value="tmPassLecList.do"/>?MENU_ID=${MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&L_MENU_NM=${L_MENU_NM}&MENU_NM=${MENU_NM}">상품별보기</a></li>
    </ul>

	<div>※ TM 회원 배정에서 등록된 관리 인원만 노출 됩니다.</div>

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
      
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">No</th>
			<th>아이디</th>
          	<th>이름</th>
			<th>핸드폰</th>
			<th>전화번호</th>
			<th>강의명</th>
			<th>분류</th>
			<th>관리자</th>
			<th>수강종료일</th>
			<th>배정일</th>
			<th>최종통화일</th>
        </tr>
		<c:if test="${not empty list}">
        <c:forEach items="${list}" var="list" varStatus="status">
        <tr>
			<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
			<td><a href="#" onclick="javascript:user_view('${list.USER_ID}');">${list.USER_ID}</a></td>
			<td><a href="#" onclick="javascript:Detailview('${list.USER_ID}','${list.ORDERNO}');">${list.USER_NM}</a></td>
			<td>${list.PHONE_NO}</td>
			<td>${list.TEL_NO}</td>
			<td>${list.SUBJECT_TITLE}</td>
			<td>${list.PASSTYPE}개월</td>
			<td>${list.ADMUSERNAME}</td>
			<td>${list.END_DATE}</td>
			<td>${list.ALLOCDATE}</td>
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