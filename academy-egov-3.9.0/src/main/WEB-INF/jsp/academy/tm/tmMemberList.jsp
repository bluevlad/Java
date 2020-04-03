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
	
	$('#searchFrm').attr('action','<c:url value="/tm/tmMemberList.do"/>').submit();
}

//엑셀
function excel_onclick1() { //ck 추가
	<c:if test="${empty list}">
		alert('엑셀파일로 저장할 데이타가 없습니다.');
		return;
	</c:if>
	$('#searchFrm').attr('action','<c:url value="/tm/tmMemberList_excel.do"/>').submit();
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
function member_view(userid){
    
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
    	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=820,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
    }
}

</script>

<title>TM관리 - 회원관리</title>
</head>

<!--content -->
  <div id="content">
	<h2>● TM 관리 &gt; <strong>TM회원관리</strong></h2>
	   
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post">    
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
		
	<input type="hidden" id="ORDERNO" name="ORDERNO" />
	<input type="hidden" id="USER_ID" name="USER_ID" />
	<input type="hidden" id="STATUSCODE" name="STATUSCODE" />
	
	<table class="table01">
        <tr>
          <th width="10%">배정일</th>
          <td  style="width:60%;">
          <input type="text" id="SDATE" name="SDATE" maxlength="10" class="i_text" value="${params.SDATE }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           ~
           <input type="text" id="EDATE" name="EDATE" maxlength="10" class="i_text" value="${params.EDATE }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           
           &nbsp;
		
		<input type="text" class="i_text"  title="검색" id="SEARCHTEXT" name="SEARCHTEXT"  type="text" style="width:80px;"  value="${params.SEARCHTEXT}" onkeypress="fn_checkEnter()">
        <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
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
			<th>전화번호</th>
			<th>핸드폰</th>
			<th>분류</th>
			<th>관리자</th>
			<th>배정일</th>
			<th>최종통화일</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
             <tr>
				<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
				<td><a href="#" onclick="javascript:member_view('${list.USERID}');">${list.USERID}</a></td>
				<td><a href="#" onclick="javascript:member_view('${list.USERID}');">${list.USERNAME}</a></td>
				<td>${list.TEL}</td>
				<td>${list.PHONE}</td>				
				<td style="cursor:pointer" title="${list.TOOLTIP}">${list.USERTYPE_NM}</td>				
				<td>${list.ADMUSERNAME}</td>
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