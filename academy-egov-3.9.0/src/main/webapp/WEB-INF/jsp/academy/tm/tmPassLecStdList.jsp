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
	
	$('#searchFrm').attr('action','<c:url value="/tm/tmPassLecStdList.do"/>').submit();
}

//엑셀
function excel_onclick1() { //ck 추가
	<c:if test="${empty list}">
		alert('엑셀파일로 저장할 데이타가 없습니다.');
		return;
	</c:if>
	$('#searchFrm').attr('action','<c:url value="/tm/tmPassLecStdListExcel.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#SEARCHTEXT').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name=USER_ARR]").each(function(index){
			if ($(this).is(':enabled')) {
				$(this).attr("checked", "checked");
			}
		});
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}

}

function fn_tmAdd(leccode){
	
	if($("input[name='USER_ARR']:checked").length < 1){
		alert('배정할 수강생을 선택해주세요');
		return;
	}
	var i = 0;
	var user_arr = "";
	$("input[name='USER_ARR']:checked").each(function(idx,el){
		if(idx==0){
			user_arr += $(this).val();
		}else{
			user_arr += "/"+$(this).val();
		}
	});
	
	if(user_arr != ""){
		window.open('<c:url value="/tm/tmSelUserid.pop"/>?LECCODE='+leccode+'&USER_ARR='+user_arr , '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1040,height=670');
	}
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
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="MGNTNO" name="MGNTNO" value="${params.MGNTNO}" />

<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
	
	<table class="table01">
    	<tr>
        	<th colspan="3">프리패스상품정보</th>
		</tr>
    	<tr>
          	<td>${view[0].LECCODE}</td>
          	<td>${view[0].CATEGORY_NM}</td>
          	<td>${view[0].SUBJECT_TITLE}</td>
		</tr>
	</table>
	<br>
	<table class="table01">
    	<tr>
        	<th width="10%">수강종료일</th>
          	<td  style="width:60%;">
          		<input type="text" id="SDATE" name="SDATE" maxlength="10" value="${params.SDATE}" style="width:90px;"/>
           		~
           		<input type="text" id="EDATE" name="EDATE" maxlength="10" value="${params.EDATE}" style="width:90px;"/>
				&nbsp;&nbsp;
				<input type="text" class="i_text"  title="검색" id="SEARCHTEXT" name="SEARCHTEXT"  type="text" style="width:150px;"  value="${params.SEARCHTEXT}" onkeypress="fn_checkEnter()">
        		<span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="goList(1)">검색</button></span>
			</td>
        	<td><span class="btn_pack medium" style="vertical-align:middle;align:right;"><button type="button" onclick="excel_onclick1();">Excel</button></span></td>
		</tr>
	</table>
    <!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:fn_tmAdd('${params.MGNTNO}');">TM회원배정</a></li>
    </ul>
    <!--//버튼--> 
      
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">
				<input type="checkbox" id="allCheck" name="allCheck" VALUE="" onclick="fn_CheckAll('allCheck', 'USER_ID_ARR')"/>&nbsp;No
          	</th>
			<th>아이디</th>
          	<th>이름</th>
			<th>휴대폰</th>
			<th>전화</th>
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
			<td>
				<input type="checkbox" name="USER_ARR"  <c:if test="${list.ADMUSERNAME ne null}">disabled</c:if> value="${list.USER_ID},${list.USER_NM}"/>
				${totalCount - (( currentPage - 1) * pageRow) - status.index}
			</td>
			<td><a href="#" onclick="javascript:user_view('${list.USER_ID}');">${list.USER_ID}</a></td>
			<td><a href="#" onclick="javascript:Detailview('${list.USER_ID}','${list.ORDERNO}');">${list.USER_NM}</a></td>
			<td>${list.PHONE_NO}</td>
			<td>${list.TEL_NO}</td>
			<td>${list.SUBJECT_TITLE}</td>
			<td>${list.SUBJECT_PERIOD}개월</td>
			<td>${list.ADMUSERNAME}</td>
			<td>${list.END_DATE}</td>
			<td>${list.ALLOCDATE}</td>
			<td>${list.LASTREGDATE}</td>
		</tr>
		</c:forEach>
		</c:if>

		<c:if test="${empty list}">
	    <tr bgColor=#ffffff align=center> 
			<td colspan="10">표시할 데이터가 없습니다.</td>
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