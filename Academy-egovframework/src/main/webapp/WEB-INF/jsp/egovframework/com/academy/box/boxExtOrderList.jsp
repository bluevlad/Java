<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>

<head>
<script type="text/javascript">
var search_date_type = "${params.search_date_type}";

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	if($("#SDATE").val()!='' || $("#EDATE").val()!=''){
		if($("#SDATE").val()!='' && $("#EDATE").val()!=''){
			if(parseInt($("#EDATE").val().replace(/-/g,'')) < parseInt($("#SDATE").val().replace(/-/g,''))){
				alert('검색일자 종료일은 시작일보다 큰 날짜를 선택하세요.');
				return;
			}			
		}		
	}
	$('#searchFrm').attr('action','<c:url value="/box/boxExtOrderList.do"/>').submit();
}

//엑셀
function excel_onclick1() { //ck 추가 	
	$('#searchFrm').attr('action','<c:url value="/box/boxExtOrderList_excel.do"/>').submit();
}

//엔터키 검색
function fn_checkEnter(){
	$('#SEARCHKEY').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("SDATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("EDATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

//SMS보내기
function smssend_pop() {

	var tmp = "";
	var userphone = "";
	$("input[name=smsname]").each(function(index){
		if ($(this).is(':checked')) {
			if(tmp == null || tmp == ""){
				tmp = $(this).val();
				userphone = $("#userphone"+index).val();
			}else{
				tmp = tmp + "/" + $(this).val();
				userphone = userphone + "/" + $("#userphone"+index).val();
			}
		}
	});

	if(tmp == null || tmp == "" || tmp == undefined){
		alert("받을사람을 선택해 주세요.");
		return;
	}
	alert("tmp:"+tmp +"\n"+
			"userphone:"+userphone);
	/* alert("tmp:"+tmp +"\n"+
			"userphone:"+userphone); */
	window.open('<c:url value="/productOrder/add.pop"/>?userphone=' + userphone + '&smsname=' + escape(encodeURIComponent(tmp)) + '&orderstatuscode=' + $("#orderstatuscode").val() + '&search_date_type=' + escape(encodeURIComponent(search_date_type))
			 + '&searchkey=' + escape(encodeURIComponent($("#searchkey").val())) + '&searchtype=' + $("#searchtype").val() + '&paycode=' + $("#paycode").val()
			 + '&payall=' + $("#payall").val() + '&sdate=' + $("#sdate").val() + '&edate=' + $("#edate").val()
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=no,width=1005,height=400,top=0,left=0,scrollbars=no,location=no,scrollbars=no');
}

</script>

</head>

<body>
<!--content -->
  <div id="content">
	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
    
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post">    
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />
		
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<input type="hidden" id="ORDERNO" name="ORDERNO" />
	<input type="hidden" id="STATUSCODE" name="STATUSCODE" />
	<input type="hidden" id="USER_ID" name="USER_ID" />
	<input type="hidden" id="CALLPOSITION" name="CALLPOSITION" value="ORDERLIST">
	
	<table class="table01">
        <tr>
          <th width="15%">검색</th>
          <td>
          
          <input type="text" id="SDATE" name="SDATE" maxlength="10" class="i_text" value="${params.SDATE }" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           ~
           <input type="text" id="EDATE" name="EDATE" maxlength="10" class="i_text" value="${params.EDATE }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           
           &nbsp;
        <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
        </td>
        </tr>
    </table>
      
    <!--버튼-->    
    <ul class="boardBtns">
   	    <li><a href="javascript:smssend_pop();">SMS보내기</a></li>
        <li><a href="javascript:excel_onclick1();">Excel</a></li>
    </ul>
    <!--//버튼-->
	</form>
	       
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        
     <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">No</th>
          	<th>주문번호</th>
			<th>성명(핸드폰)</th>
			<th>이메일</th>
			<th>품목(사물함 번호)</th>
			<th>대여시작일</th>
			<th>대여종료일</th>
			<th></th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
				<input type="hidden" name="userphone${status.index}" id="userphone${status.index}" value="${fn:replace(list.PHONE_NO,'-', '')}">
             <tr>
				<td>
					<input type="checkbox" name="smsname" id="smsname" value="${list.USER_NM}">
					${totalCount - (( currentPage - 1) * pageRow) - status.index}
				</td>
				<td>${list.ORDERNO}</td>
				<td>${list.USER_NM}(${list.PHONE_NO})</td>
				<td>${list.EMAIL}</td>
				<td>${list.BOX_NM} ${list.BOX_NUM}번 사물함</td>				
				<td>${list.RENT_START}</td>
				<td><font style='color:red;'>${list.RENT_END}</font></td>
	        </tr>
      </c:forEach>
		</c:if>
		<c:if test="${empty list}">
		         <tr bgColor=#ffffff align=center> 
			<td colspan="9">표시할 데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
     </table>
     <!--//테이블-->
	
    <!-- paginate-->
    <c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
</div>
</body> 
</html>