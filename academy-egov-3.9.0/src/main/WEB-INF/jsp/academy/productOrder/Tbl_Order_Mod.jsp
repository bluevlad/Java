<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<html>
<head>
<meta name="decorator" content="index">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

//var sts = "${sts}";
//alert("sts:"+sts);

/* var REG_DT = "${order_list.REG_DT}";
var USER_NM = "${order_list.USER_NM}";
var BIRTH_DT = "${order_list.BIRTH_DT}";
var TEL_NO = "${order_list.TEL_NO}";
var PHONE_NO = "${order_list.PHONE_NO}"; */

//alert("MOCKCODE:"+MOCKCODE);

var orderstatuscode = "${searchMap.orderstatuscode}";
var search_date_type = "${searchMap.search_date_type}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var payall = "${searchMap.payall}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

var message = "${message}";

window.onload = function () {

	/* alert("orderstatuscode:"+orderstatuscode +"\n"+
			"search_date_type:"+search_date_type +"\n"+
			"searchkey:"+searchkey +"\n"+
			"searchtype:"+searchtype +"\n"+
			"paycode:"+paycode +"\n"+
			"payall:"+payall +"\n"+
			"sdate:"+sdate +"\n"+
			"edate:"+edate +"\n"+
			"currentPage:"+currentPage +"\n"+
			"pageRow:"+pageRow +"\n"+
			"TOP_MENU_ID:"+TOP_MENU_ID +"\n"+
			"MENUTYPE:"+MENUTYPE +"\n"+
			"L_MENU_NM:"+L_MENU_NM); */

	/* alert("REG_DT:"+REG_DT +"\n"+
	"USER_NM:"+USER_NM +"\n"+
	"BIRTH_DT:"+BIRTH_DT +"\n"+
	"TEL_NO:"+TEL_NO +"\n"+
	"PHONE_NO:"+PHONE_NO ); */

	if(message != "") {
		alert(message);
	}
}

//숫자 입력 폼
function chk(obj, target){

	var val = obj.value;
	if (val) {
		if (val.match(/^\d+$/gi) == null) {
			$('#realprice'+target).val("");
			$('#realprice'+target).focus();
			return;
		}
		else {
			if (val < 1) {
				/* if(target == "OFFCLOSEPERSONNUMBER") {
					$('#OFFCLOSEPERSONNUMBER').val("");
					$('#OFFCLOSEPERSONNUMBER').focus();
					return;
				}  */
			}
		}
	}
}

//수정하기 버튼
function submit_onclick()
{
	if(confirm("변경내역을 수정하시겠습니까?")) {
		//$("#zipcd").val($("#zipcd1").val()+$("#zipcd2").val());
		$("#zipcd").val($("#ZIP_CODE").val());
		$('#myform').attr('action','<c:url value="/productOrder/insert.do"/>').submit();
	}
}

//숫자만 입력
function fn_OnlyNumber(obj) {
	for (var i = 0; i < obj.value.length ; i++){
		chr = obj.value.substr(i,1);
	  	chr = escape(chr);
	  	key_eg = chr.charAt(1);
		if (key_eg == "u"){
	   		key_num = chr.substr(i,(chr.length-1));
		   	if((key_num < "AC00") || (key_num > "D7A3")) {
		    	event.returnValue = false;
		   	}
	  	}
	}
	if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
	}else{
		event.returnValue = false;
	}
}

//상품구분 셀렉트 박스 onchange
function wmv_pmp_modify()
{
	//alert("wmv_check:"+$("#wmv_check").val());

	if(confirm("상품을 변경 하시겠습니까?")) {
		$('#myform2').attr('action','<c:url value="/productOrder/wmv_pmp_update.do"/>').submit();
	}
}

//상품명 팝업
function view(leccode){

	return;

	 var w = '780';  //가로
	 var h = '460'; //세로
	 var scroll = 'no'; //옵션
	 var name = "sample_AxPlayer";
	 var LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
     var TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
     var settings = 'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable=no';

	 window.open('<c:url value="/productOrder/view.pop"/>?leccode=' + leccode, '_blank', settings);
}

// 금액수정 버튼 - 20130118 각각의 판매가 마다 금액수정을 만들기 위해 추가됨
function submit_money(i){

	var realprice = "realprice" + i;
	var mgntno = "mgntno" + i;
	var iscancle = "iscancle" + i;

	document.myform3.realprice.value =  document.getElementById(realprice).value;
	document.myform3.mgntno.value =  document.getElementById(mgntno).value;
	document.myform3.iscancle.value = document.getElementById(iscancle).value;

	if(document.myform3.realprice.value == null || document.myform3.realprice.value == ""){
		alert("금액을 입력해 주세요");
		return;
    }

	if(confirm("판매가를 수정하시겠습니까?")) {
		$('#myform3').attr('action','<c:url value="/productOrder/submit_money.do"/>').submit();
	}
}

// 상태코드 삭제 버튼 - 20130122 환불 내역 삭제하기 위해 추가됨
function refund_money_delete(i){

	if(confirm("상품을 삭제 하시겠습니까?")) {
		var mgntno = "mgntno" + i;
		var iscancle = "iscancle" + i;

		document.myform3.mgntno.value =  document.getElementById(mgntno).value;
		document.myform3.iscancle.value = document.getElementById(iscancle).value;

		$('#myform3').attr('action','<c:url value="/productOrder/refund_money_delete.do"/>').submit();
	}
}

//목록
function goList() {
	$('#myform2').attr('action','<c:url value="/productOrder/list.do"/>').submit();
}

//우편번호찾기 팝업
function ZipSearch() {
	var args = ZipSearch.arguments;
    var winOpts="width=800,height=400,scrollbars=no,resizable=no";
    var url = "";
    url = "<c:url value='/memberManagement/getZipCode.pop' />";
    window.open(url, "", winOpts);
}

//주소검색 입력
function setZipcode(_zipcode , _address2) {
	var zipcode1 = 	_zipcode.substring (0,3);
	var zipcode2 = 	_zipcode.substring (4,7);
	var address1 = 	_zipcode.substring (7);
	var address2 = 	_address2;

	$("#zipcd1").val(zipcode1);
	$("#zipcd2").val(zipcode2);
	$("#juso").val(address1+" "+address2);

}

function update_study_per(orderno, mgntno){

    var per = "study_per_" + mgntno

    document.myform3.mgntno.value =  mgntno;
    $("#study_percent").val(document.getElementById(per).value);

    if(document.getElementById(per).value == null || document.getElementById(per).value == ""){
		alert("진도율을 입력해 주세요");
		return;
    }

	if(confirm("진도율을 수정하시겠습니까?")) {
		$('#myform3').attr('action','<c:url value="/productOrder/update_study_per.do"/>').submit();
	}
}

function fn_find_zip() {

	//      $('#SEARCHCODE').val("old");
	//      $('#search_address').show();
	//      $("#ZIP_NM").focus();
		// 우편번호 찾기 iframe을 넣을 element
	 var element = document.getElementById('search_address');
	// 현재 scroll 위치를 저장해놓는다.
	 var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
	 new daum.Postcode({
	     oncomplete: function(data) {
	         // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	         // 우편번호와 주소 및 영문주소 정보를 해당 필드에 넣는다.
	         //document.getElementById('ZIP_CODE1').value = data.postcode1;
	         //document.getElementById('ZIP_CODE2').value = data.postcode2;
	         document.getElementById('juso').value = data.address;
	         //$("#juso").text(data.address);
	         //document.getElementById('ADDRESS2').focus();
	         // iframe을 넣은 element를 안보이게 한다.
	         element.style.display = 'none';
	         // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
	         document.body.scrollTop = currentScroll;
	         var zip  = data.postcode1+"-"+data.postcode2;
	         //var zip  = data.zonecode;
	         $('#ZIP_CODE').val(zip);
	     },
	     // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분.
	     // iframe을 넣은 element의 높이값을 조정한다.
	     onresize : function(size) {
	         element.style.height = size.height + "px";
	     },
	     width : '100%',
	     height : '100%'
	 }).embed(element);
	 // iframe을 넣은 element를 보이게 한다.
	 element.style.display = 'block';
	
	 }

//사용자 로그인
function fn_login()
{  
	window.open('', 'user', 'scrollbars=yes,toolbar=yes,resizable=yes');
	$('#myform').attr('target' ,'user');
	$('#myform').attr('action','<c:url value="http://www.willbescop.net/login/loginAdminProc"/>').submit();
}
</script>

</head>

<!--content -->
  <div id="content">
	<h2>● 상품주문관리 > <strong>전체상품주문관리</strong></h2>

    <!--테이블-->
    <form id="myform2" name="myform2" method="post">
    <input type="hidden" id="orderno" name="orderno" value="${searchMap.orderno}" />

	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />

	<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
	<input type="hidden" id="search_date_type" name="search_date_type" value="${searchMap.search_date_type}" />
	<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
	<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
	<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
	<input type="hidden" id="payall" name="payall" value="${searchMap.payall}" />
	<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
	<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

	<table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 주문내역&#13;</p></td>
      </tr>
    </table>

	<!--테이블-->

    <table class="table02">
        <tr>
          <th>상품코드</th>
          <th>상품명</th>
          <th>상품구분</th>
          <th>정가<br />(장바구니할인)</th>
          <th>기본<br />할인율</th>
          <th>장바구니<br />할인율</th>
          <th>진도율</th>
          <th>판매가</th>
          <th>수량</th>
          <th>합계금액</th>
          <th>상태코드</th>
        </tr>
        <tbody>

        <c:set var="tot_sum" value="0"/>

        <c:if test="${not empty lec_list}">
         <c:forEach items="${lec_list}" var="lec_list" varStatus="status">

         <input type="hidden" name="iscancle" id="iscancle${status.index}" value="${lec_list.ISCANCEL}"> <!-- 20130118 판매가 하나하나 마다 id값 다르게 하기위해 뒤에 i값을 주었음 iscancle은 새로 추가하였음 -->
         <input type="hidden" name="mgntno" id="mgntno${status.index}" value="${lec_list.MGNTNO}"> <!-- 20130118 상품코드 하나하나 마다 id값 다르게 하기위해 뒤에 i값을 주었음 -->

            <tr>
              <td>${lec_list.MGNTNO}</td>
              <td>
              	<c:if test="${lec_list.GIFT_YN eq 'Y'}">
					(사은품)
				</c:if>
              	<a href="javascript:view('${lec_list.MGNTNO}')" style="text-decoration:none;">${lec_list.NAME}</a>&nbsp;&nbsp;&nbsp;
               	${lec_list.SDATE}
              </td>

              <td>
              	<c:if test="${(fn:substring(lec_list.MGNTNO, 0, 1) ne  'L')}"> <!-- 교재가 아닌것만 링크 걸리게 수정 ck  -->
	              	<c:set var="VOD">VOD#${lec_list.WMV_PMP}#${lec_list.ORDERNO}#${lec_list.MGNTNO}</c:set>
	              	<c:set var="PMP">PMP#${lec_list.WMV_PMP}#${lec_list.ORDERNO}#${lec_list.MGNTNO}</c:set>
	              	<c:set var="VOD_PMP">VOD+PMP#${lec_list.WMV_PMP}#${lec_list.ORDERNO}#${lec_list.MGNTNO}</c:set>
	              	<c:set var="MP4">MP4#${lec_list.WMV_PMP}#${lec_list.ORDERNO}#${lec_list.MGNTNO}</c:set>
	              	<c:set var="VOD_MP4">VOD+MP4#${lec_list.WMV_PMP}#${lec_list.ORDERNO}#${lec_list.MGNTNO}</c:set>

			    	<select name="wmv_check" id="wmv_check" onchange="wmv_pmp_modify()" style="width:120;">
			        	<OPTION>선택</OPTION>
			        	<OPTION value="${VOD}" <c:if test="${lec_list.WMV_PMP == 'VOD'}">selected</c:if>>VOD</OPTION>
			            <OPTION value="${PMP}" <c:if test="${lec_list.WMV_PMP == 'PMP'}">selected</c:if>>PMP</OPTION>
			            <OPTION value="${VOD_PMP}" <c:if test="${lec_list.WMV_PMP == 'VOD+PMP'}">selected</c:if>>VOD+PMP</OPTION>
			            <!-- 20130128 -->
			            <OPTION value="${MP4}" <c:if test="${lec_list.WMV_PMP == 'MP4'}">selected</c:if>>MP4</OPTION>
			            <%-- <OPTION value="${VOD_MP4}" <c:if test="${lec_list.WMV_PMP == 'VOD+MP4'}">selected</c:if>>VOD+MP4</OPTION> --%>
			       	</select>
			   </c:if>
			  </td>

			  <fmt:parseNumber var="discount_pay" value="${lec_list.DISCOUNTPER/100}" integerOnly="true" />
			  <fmt:parseNumber var="discount_pay" value="${lec_list.PRICE*discount_pay}" integerOnly="true" />

              <td><fmt:formatNumber value="${lec_list.PRICE}" groupingUsed="true" /><c:if test="${lec_list.DISCOUNTPER > 0 }">(<fmt:formatNumber value="${discount_pay}" groupingUsed="true" />)</c:if></td>
              <td>${lec_list.RATE}%</td>
              <td><c:if test="${lec_list.DISCOUNTPER ne null }" >${lec_list.DISCOUNTPER}%</c:if><c:if test="${lec_list.DISCOUNTPER eq null }" >0%</c:if></td>
              <td>
              	<c:if test="${(fn:substring(lec_list.MGNTNO, 0, 1) ne  'L')}"> <!-- 교재가 아닌것만 링크 걸리게 수정 ck  -->
                    <input type="text" id="study_per_${lec_list.MGNTNO }" name="study_per_${lec_list.MGNTNO }" value="${lec_list.STUDY_PER}" style="width: 8%">%
	              	<input type="button" onclick="javascript:update_study_per('${lec_list.ORDERNO }','${lec_list.MGNTNO }');" value="진도율수정" />
              	</c:if>
              </td>
              <td>
<%--                	<c:if test="${status.index eq 0 }"> --%>
				
              	
              	<c:if test="${lec_list.GIFT_YN eq 'Y'}">
						-
				</c:if>
              	<c:if test="${lec_list.GIFT_YN ne 'Y'}">
					<c:if test="${(fn:substring(lec_list.MGNTNO, 0, 1) ne  'L')}"> <!-- 교재가 아닌것만 링크 걸리게 수정 ck  -->
		              	<input type="text" name="realprice" id="realprice${status.index}" value="${lec_list.REALPRICE}" size="7" onkeyup="chk(this,'${status.index}')" onblur="chk(this,'${status.index}')" onKeyDown="fn_OnlyNumber(this);">
		              	<input type="button" onclick='javascript:submit_money(${status.index});' value="금액수정" />
              		</c:if>
				</c:if>
              </td><!-- 20130118 판매가 하나하나 마다 id값 다르게 하기위해 뒤에 i값을 주었음 -->

              <td>${lec_list.CNT}개</td>
              <td><fmt:formatNumber value="${lec_list.TOT_PRICE}" groupingUsed="true" /></td>

              <td>
				<c:choose>
					<c:when test="${lec_list.STATUSCODE ne null }">
						${lec_list.STATUSCODENAME}
					</c:when>

					<c:otherwise>
						<input type="button" onclick='javascript:refund_money_delete(${status.index});' value="삭제" /><!-- 20130122 환불금액 삭제하기 위해 추가됨-->
					</c:otherwise>
				</c:choose>
			  </td>
            </tr>

            <c:set var="tot_sum" value="${tot_sum + lec_list.TOT_PRICE}"/>

     	</c:forEach>
		</c:if>

		<c:if test="${empty lec_list}">
		          <tr bgColor=#ffffff align=center>
				<td colspan="10">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>

		<tr>
          <td colspan="3">&nbsp;</td>
          <td colspan="2"><p>상품합계</p></td>
          <td colspan="6" align="right"><fmt:formatNumber value="${tot_sum}" groupingUsed="true" /></td>
        </tr>

        </tbody>
    </table>
    </form>

    <!--//테이블-->

    <form id="myform" name="myform" method="post">

    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />

    <input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
	<input type="hidden" id="search_date_type" name="search_date_type" value="${searchMap.search_date_type}" />
	<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
	<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
	<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
	<input type="hidden" id="payall" name="payall" value="${searchMap.payall}" />
	<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
	<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

	<input type="hidden" id="a_userid" name="a_userid" value="${order_list.USER_ID}" >
	<input type="hidden" id="a_userPwd" name="a_userPwd" value="a" >

    <c:choose>
		<c:when test="${deliverscount > 0 }">
			<input type="hidden" id="zipcd" name="zipcd" value="${del_list.ZIPCD}" />
		</c:when>

		<c:otherwise>
			<input type="hidden" id="zipcd" name="zipcd" value="" />
		</c:otherwise>
	</c:choose>

	<br><br>
    <table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 주문자정보&#13;</p></td>
      </tr>
    </table>

    <!--//주문자정보 테이블-->
   	<table class="table01">
   	<tr>
   		<th width="10%">주문번호</th>
		<td width="25%">
			<input type="text" id="orderno" name="orderno"  title="레이블 텍스트"  size="20"  maxlength="20" style="width:70%; background:#FFECEC;" value="${searchMap.orderno}" />
		</td>

		<th width="10%">주문날짜</th>
		<td width="20%">
			${order_list.REG_DT}
		</td>

		<th width="10%">주문자명</th>
		<td width="25%">
			${order_list.USER_NM}
			<input type="button" onclick="fn_login();"  value="로그인" />
		</td>
	</tr>
	<tr>
   		<th >생년월일</th>
		<td >
			${order_list.BIRTH_DT}
		</td>

		<th >전화</th>
		<td >
			${order_list.TEL_NO}
		</td>

		<th >휴대폰</th>
		<td >
			${order_list.PHONE_NO}
		</td>
	</tr>
	<tr>
   		<th >주소</th>
		<td colspan="3">
			[${order_list.ZIP_CODE}] ${order_list.ADDRESS1}
		</td>

		<th >이메일</th>
		<td >
			${order_list.EMAIL}
		</td>
	</tr>
	</table>
	<!--//주문자정보 테이블-->

    <c:if test="${deliverscount > 0 }">

    <br><br>

    <table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 배송지 정보&#13;</p></td>
      </tr>
    </table>

    <!--//배송지 테이블-->
   	<table class="table01">
   	<tr>
   		<th width="10%">송장번호</th>
		<td width="55%">
			<input type="text" id="sendno" name="sendno"  title="레이블 텍스트"  size="20"  maxlength="20" style="width:50%; background:#FFECEC;" value="${del_list.SENDNO}" />
		</td>

		<th width="10%">수령인</th>
		<td width="25%">
			<input type="text" id="d_name" name="d_name"  title="레이블 텍스트"  size="20"  maxlength="20" style="width:50%; background:#FFECEC;" value="${del_list.USERNAME}" />
		</td>
	</tr>
	<tr>
   		<th >전화</th>
		<td >
			<input type="text" id="d_telno" name="d_telno"  title="레이블 텍스트"  size="20"  maxlength="20" style="width:50%; background:#FFECEC;" value="${del_list.TELNO}" onKeyDown="fn_OnlyNumber(this);"/>
		</td>

		<th >휴대폰</th>
		<td >
			<input type="text" id="d_cellno" name="d_cellno"  title="레이블 텍스트"  size="20"  maxlength="20" style="width:50%; background:#FFECEC;" value="${del_list.CELLNO}" onKeyDown="fn_OnlyNumber(this);"/>
		</td>
	</tr>

	<c:set var="ju_so" value="${del_list.ADDR}"/>
	<c:set var="first_zipcd" value="${fn:substring(del_list.ZIPCD_SET1, 0, 2)}"/>

	<tr>
   		<th >주소</th>
		<td>
			<%--  <input type="text" name="zipcd1" id="zipcd1" value="${del_list.ZIPCD_SET1}" size="5" maxlength="3" readonly="readonly">
			 -
			 <input type="text" name="zipcd2" id="zipcd2" value="${del_list.ZIPCD_SET2}" size="5" maxlength="3" readonly="readonly"> --%>
			 <input type="text" name="ZIP_CODE" id="ZIP_CODE" value="${del_list.ZIPCD}" size="5" maxlength="3" readonly="readonly">
			 <!-- <input name="input" type="button" onClick="ZipSearch()" value="우편번호찾기"> -->
			 <input name="input" type="button" onClick="fn_find_zip()" value="우편번호찾기">
			 <div class="layer search" id="search_address">
												
			</div>
	         <br>
	         <input type="text" name="juso" id="juso" style="width:90%;" value="${del_list.ADDR}">
		</td>

		<th >배송방법</th>
		<td style="vertical-align:middle;">
			${del_list.DLEORDER_NM }
		</td>
	</tr>

	<tr>
   		<th >메모</th>
		<td>
			<textarea rows="4" cols="60" name="d_memo" id="d_memo"><c:out value='${del_list.MEMO}'/></textarea>
		</td>
   		<th >배송일</th>
		<td style="vertical-align:middle;">
			${del_list.SENDDATE }
		</td>
	</tr>
	</table>

	</c:if>
	<!--//배송지 테이블-->



    <br><br>
    <table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 결제 정보</p></td>
      </tr>
    </table>

    <!--//결제 정보 테이블-->
   	<table class="table01">
   	<tr>
   		<th width="10%">총구매금액</th>
		<td width="20%"><fmt:formatNumber value="${list.PRICE}" groupingUsed="true" /></td>
		<th width="10%">배송비</th>
		<td width="25%">
			<fmt:formatNumber value="${list.ADDPRICE}" groupingUsed="true" />
			<c:if test="${first_zipcd eq '69' and first_zipcd ne null and first_zipcd ne ''}">
				<c:set var="chuga_add" value="2500"/>(추가 배송료 : 2,500원)
			</c:if>
			<c:if test="${list.USE_DEL_COUPON_CNT>0}">
				(쿠폰사용)
			</c:if>
		</td>
		<th width="10%">사용한 포인트</th>
		<td width="25%">${list.POINT}</td>
	</tr>
	<tr>
		<th>지불방법</th>
		<td>${list.PAYCODENAME}</td>
		<th>입금계좌</th>
		<td>
			<c:if test="${list.PAYCODE ne 'PAY110'}">
					${list.VCDBANK}&nbsp;(${list.VACCT})&nbsp; 
					<input type="text" id="payname" name="payname"  title="입금자명"  style="width:80px; background:#FFECEC;" value="${list.PAYNAME}" />&nbsp;
			</c:if>
		</td>
		<th>입금상태</th>
		<td>
			<c:if test="${list.PAYCODE == 'PAY120' and list.PRICE>0}">
				${list.STATUS_NM}
		       	<c:choose>
		       		<c:when test="${list.STATUSCODE == 'DLV100'}">
		       			(<span style="color:#0D71BB;">입금기한:<strong>${list.STATUS_DT}</strong></span>)
		       		</c:when>
		       		<c:when test="${list.STATUSCODE == 'DLV105'}">
					(<span style="color:#0D71BB;">결제일:<strong>${list.STATUS_DT}</strong></span>)
				</c:when>
		       	</c:choose>
	      	</c:if>
	   	</td>
	</tr>
	<tr>
   		<th width="10%">PG사결제금액</th>
		<td width="10%"><fmt:formatNumber value="${list.PG_PRICE}" groupingUsed="true" /></td>
		<th>TID</th>
		<td colspan="5">${list.TID}</td>
	</tr>
	<c:if test="${not empty lec_list[0].MEMO}">
	<tr>
   		<th>비고</th>
		<td colspan="5">${lec_list[0].MEMO}</td>
	</tr>
	</c:if>
	</table>
	<!--//결제 정보 테이블-->
	</form>

	<!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:submit_onclick();">수정하기</a></li>
   	  <li><a href="javascript:goList();">목록</a></li>
    </ul>
    <!--//버튼-->

	<!-- 20130118 금액수정용 form을 따로 만들었음 -->
	<form id="myform3" name="myform3" method="post">
		<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
		<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
		<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />

		<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
		<input type="hidden" id="search_date_type" name="search_date_type" value="${searchMap.search_date_type}" />
		<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
		<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
		<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
		<input type="hidden" id="payall" name="payall" value="${searchMap.payall}" />
		<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
		<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
		<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
		<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

		<input type="hidden" name="realprice" id="realprice" value="">
		<input type="hidden" name="mgntno" id="mgntno" value="">
		<input type="hidden" name="orderno" id="orderno" value="${searchMap.orderno}">
		<input type="hidden"   name="iscancle" id="iscancle" value="">
		<input type="hidden"   name="study_percent" id="study_percent" value="">
	</form>

</div>