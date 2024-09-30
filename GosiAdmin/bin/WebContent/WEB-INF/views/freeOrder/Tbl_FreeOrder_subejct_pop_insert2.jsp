<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
var package_no = "${searchMap.package_no}";
//alert("###package_no:"+package_no);
var msg = "${msg}";
var message = "${message}";

var keyword = "${searchMap.keyword}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	/* alert(	"keyword:"+keyword +"\n"+
			"currentPage:"+currentPage +"\n"+
			"pageRow:"+pageRow +"\n"+
			"TOP_MENU_ID:"+TOP_MENU_ID +"\n"+
			"MENUTYPE:"+MENUTYPE +"\n"+
			"L_MENU_NM:"+L_MENU_NM); */

	/* alert("msg:"+msg +"\n"+
			"message:"+message); */

	if(msg != "") {
		alert(msg);
		self.close();
	}

	if(message != "") {
		if(message == "등록완료") {
			alert("수강신청 등록되었습니다.");
			self.close();
		}else{
			alert("수강신청 변경되었습니다.");

			 window.opener.myform.keyword.value=keyword;
			 window.opener.myform.cmd.value="Y";
			 window.opener.myform.currentPage.value=currentPage;
			 window.opener.myform.pageRow.value=pageRow;

			 window.opener.myform.TOP_MENU_ID.value=TOP_MENU_ID;
			 window.opener.myform.MENUTYPE.value=MENUTYPE;
			 window.opener.myform.L_MENU_NM.value=L_MENU_NM;

			 window.opener.goList(currentPage);

			self.close();
		}
	}

	/* alert("S_EXAMYEAR:"+S_EXAMYEAR +"\n"+
	"S_EXAMROUND:"+S_EXAMROUND +"\n"+
	"S_searchFlag:"+S_searchFlag +"\n"+
	"S_searchKeyWord:"+S_searchKeyWord +"\n"+
	"S_currentPage:"+S_currentPage +"\n"+
	"S_pageRow:"+S_pageRow); */
}

//강의선택
function plus_subject_pop() {
	//alert("userid:"+$("#userid").val());

	window.open('<c:url value="/freeOrder/pop_subject_add2.pop"/>?userid=' + $("#userid").val() + '&keyword=' + escape(encodeURIComponent(keyword))
			 + '&p_currentPage=' + $("#currentPage").val() + '&p_pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=no,width=1000,height=550,top=0,left=0,location=no,scrollbars=yes');
}

//부모페이지 이벤트
function save(){
	//alert("package_no:"+$("#package_no").val());

	if ($('#package_no').val() == "" || $('#package_no').val() == null) {
		//alert("1");
    	$('#popFrm').attr('action','<c:url value="/freeOrder/pop_add2.pop"/>').submit();
	}else{
		//alert("2");
		$('#popFrm').attr('action','<c:url value="/freeOrder/pop_change.pop"/>').submit();
	}
}

//확인
function save_submit(){
	//alert("userid:"+$('#userid').val());

	if ($('#package_no').val() == "" || $('#package_no').val() == null) {
		if ($('#userid').val() == "") {
			alert('수강자ID를 입력해 주세요.');
			$('#userid').focus();
			return;
		}

		if ($('#leccode').val() == "") {
			alert('강의번호를 입력해 주세요.');
			$('#leccode').focus();
			return;
		}

		if ($('#subject_period').val() >= 30 && $('#memo').val() == "") {
			alert('30일 이상 강의는 무료수강 이유를 입력해 주세요.');
			$('#memo').focus();
			return;
		}
		
		if ($('#start_date').val() == "") {
			alert('수강 시작일을 입력해 주세요.');
			$('#start_date').focus();
			return;
		}
		
		if ($('#subject_period').val() == "") {
			alert('수강 기간을 입력해 주세요.');
			$('#subject_period').focus();
			return;
		}

		$('#popFrm').attr('action','<c:url value="/freeOrder/pop_subject_add_insert2.do"/>').submit();

	}else{
		if ($('#leccode_2').val() != $('#leccode').val()) {

			if ($('#userid').val() == "") {
				alert('수강자ID를 입력해 주세요.');
				$('#userid').focus();
				return;
			}

			if ($('#leccode').val() == "") {
				alert('강의번호를 입력해 주세요.');
				$('#leccode').focus();
				return;
			}

			$('#popFrm').attr('action','<c:url value="/freeOrder/pop_subject_add_update.do"/>').submit();
		}else{
			alert("동일한 강의로 변경이 불가능합니다.");
			return;
		}

	}
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDateFicker1("start_date");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function onOnlyNumber(obj) {
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
	 } else {
	  event.returnValue = false;
	 }
}

function addLecture(title, leccode){
    var divLength = $("#addLecture div").length;
    var lastItemNo;
    var nNum;

    if(divLength > 0){
        lastItemNo = $("#addLecture div:last-child").attr('id').replace("row",""); // $("#fileControl").replace("ATTACH_FILE", "");
        nNum = parseInt(lastItemNo) + 1;
    } else {
        nNum = 1;
    }

    var newitem = '<div id="row' + nNum + '"><input type="text" name="AddLecture" id="AddLecture_' + nNum + '" value="' + title + '" size="70">';
    newitem += ' <input type="button" name="delLecButton" value="삭제" onclick="javascript:delRow(\'' + nNum + '\',\'' + leccode + '\');"></div>';
    $("#addLecture").append(newitem);

}

function delRow(id, leccode, param){

    if(param == 'text') $("#rowText" + id).remove();
    else $("#row" + id).remove();
	
	var temp = $("#leccode").val();
	
	temp = temp.replace(leccode+"/",'');
	
	$("#leccode").val(temp);
	
}
</script>
</head>

<body>
<!--content -->
<div>
<form name="popFrm" id="popFrm" method="post">
<input type="hidden" id="keyword" name="keyword" value="${searchMap.keyword}" />
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />
<input type="hidden" id="cmdcnt" name="cmdcnt" value="${searchMap.cmdcnt}" />
<input type="hidden" id="DETAIL_SITE_ID" name="DETAIL_SITE_ID" value="" />

<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />

<input type="hidden" id="package_no" name="package_no" value="${searchMap.package_no}" />
<input type="hidden" id="orderno" name="orderno" value="${searchMap.orderno}" />

	<c:if test="${searchMap.package_no eq ''}">
		<h2>● 수강관리 &gt; <strong>다중수강신청</strong></h2>
	</c:if>

	<c:if test="${searchMap.package_no ne null and searchMap.package_no ne ''}">
		<h2>● 수강관리 &gt; <strong>수강변경</strong></h2>
	</c:if>
<table style="width:100%;">
<tr>
<td style="width:2%;">
</td>
<td>

	<c:if test="${searchMap.package_no ne null and searchMap.package_no ne ''}">
    <table style="width:100%; border:0;">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 기존 수강정보</p></td>
      </tr>
    </table>

	<!--//테이블-->
	 <table class="table05">

   		<tr>
    		<th width="15%">직종 / 분류</th>
   			<td class="tdLeft">
				&nbsp;&nbsp;${searchMap2.cat_nm} &nbsp;&nbsp;/ &nbsp;&nbsp;${searchMap2.menu_nm}
   			</td>
   		</tr>
   		<tr>
    		<th>강사</th>
   			<td class="tdLeft">
				&nbsp;&nbsp;${searchMap2.user_nm}
   			</td>
   		</tr>

   		<tr>
    		<th>강의명</th>
   			<td class="tdLeft">
				&nbsp;강의번호 :  <input type="text" id="leccode_2" name="leccode_2"  title="레이블 텍스트"  style="width:20% ;background:#FFECEC;" value="${searchMap2.leccode}" readonly="readonly"/>
				<input type="text" id="textfield5_2" name="textfield5_2"  title="레이블 텍스트" style="width:50% ;background:#FFECEC;" value="${searchMap2.subject_title}" readonly="readonly"/>
				&nbsp;&nbsp;${searchMap2.subject_period} 일
   			</td>
   		</tr>

   		<tr>
    		<th>수강료</th>
   			<td class="tdLeft">
				&nbsp;수강료(원가)  :  <fmt:formatNumber value="${searchMap2.subject_price}" groupingUsed="true" />원 ,
				&nbsp;할인율  :  <input type="text" id="subject_discount_2" name="subject_discount_2"  title="레이블 텍스트"  style="width:5% ;background:#FFECEC;" value="${searchMap2.subject_discount}"  readonly="readonly"/>% ,
				&nbsp;할인가  :  <input type="text" id="subject_price_temp_2" name="subject_price_temp_2"  title="레이블 텍스트"  style="width:10% ;background:#FFECEC;" value="${searchMap2.subject_movie}"  readonly="readonly"/>원
   			</td>
   		</tr>
	</table>
	<!--//테이블-->


	<br><br>
    <table style="width:100%; border:0;">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 변경할 수강정보</p></td>
      </tr>
    </table>

    </c:if>
	 <!--//테이블-->
	 <table class="table05">
    	<tr>
    		<th width="15%">수강자ID</th>
   			<td class="tdLeft">
   				<input type="text" id="userid" name="userid"  title="레이블 텍스트"  style="width:50% ;background:#FFECEC;" value="${searchMap.userid}"
   				<c:if test="${searchMap.cmdcnt eq 'one'}"> readonly="readonly" </c:if> />
   				<c:if test="${searchMap.cmdcnt eq 'many'}"> (※ 여러 수강생을 등록 시 구분자는 / 로 해주세요. 예)kim88/kim89 )</c:if>

   			</td>
   		</tr>

   		<tr>
    		<th>직종</th>
   			<td class="tdLeft">
				<input type="text" id="cat_nm" name="cat_nm"  title="레이블 텍스트"  style="width:30% ; value="" readonly="readonly"/>
   			</td>
   		</tr>

   		<tr>
    		<th>분류</th>
   			<td class="tdLeft">
				<input type="text" id="menu_nm" name="menu_nm"  title="레이블 텍스트"  style="width:30% ; value="" readonly="readonly"/>
   			</td>
   		</tr>

   		<tr>
    		<th>강사</th>
   			<td class="tdLeft">
   				<input type="text" id="teacher_nm" name="teacher_nm"  title="레이블 텍스트"  style="width:30% ; value="" readonly="readonly"/>
   			</td>
   		</tr>

   		<tr>
    		<th rowspan="3">강의명</th>
   			<td class="tdLeft">
				강의번호 : <input type="text" id="leccode" name="leccode"  title="레이블 텍스트"  style="width:80% ;background:#FFECEC;" value="${searchMap.leccode}" readonly="readonly"/>
   			</td>
   		</tr>

   		<tr>
   			<td class="tdLeft">
				<input type="text" id="textfield5" name="textfield5"  title="레이블 텍스트" style="width:70% ;background:#FFECEC;" value="${searchMap.subject_title}" readonly="readonly"/>
				<input type="button" onclick='javascript:plus_subject_pop();' value="강의선택" />
   			</td>
   		</tr>

   		<tr>
   			<td class="tdLeft">
   				<input type="text" id="period" name="period"  title="레이블 텍스트"  style="width:5% ; value="" readonly="readonly"/>일
   			</td>
   		</tr>
		
		<tr>
		
		</tr>
			<th>추가된강의명</th>
			<td td class="tdLeft">
				<div class="item" id="addLecture"></div>
			</td>
   		<tr>
    		<th rowspan="3">금액</th>
   			<td class="tdLeft">
				실 수강료  : <input type="text" id="price" name="price"  title="레이블 텍스트"  style="width:10% ; value=""/> 원
   			</td>
   		</tr>

   		<tr>
   			<td class="tdLeft">
				할인율  :  <input type="text" id="subject_discount" name="subject_discount"  title="레이블 텍스트"  style="width:10% ; value=""/>%
   			</td>
   		</tr>

   		<tr>
   			<td class="tdLeft">
				할인가  :  <input type="text" id="subject_price_temp" name="subject_price_temp"  title="레이블 텍스트"  style="width:10% ; value=""/>원
   			</td>
   		</tr>

   		<tr>
    		<th rowspan="5">변경옵션</th>
   			<td class="tdLeft">
					<select style="width:100px;"   id="wmv_check" name="wmv_check">
						<option value="VOD" >VOD</option>
						<option value="PMP" >PMP</option>
						<option value="VOD+PMP" >VOD+PMP</option>
						<option value="MP4" >MP4</option>
						<!--option value="VOD+MP4" <c:if test="${searchMap.subject_option == 'VOD+MP4'}">selected</c:if>>VOD+MP4</option-->
					</select>
   			</td>
   		</tr>

   		<tr>
   			<td class="tdLeft">
				실 수강료  :  <input type="text" id="subject_price" name="subject_price"  title="레이블 텍스트"  style="width:10% ;background:#FFECEC;" value="0"/>원
   			</td>
   		</tr>

   		<tr>
   			<td class="tdLeft">
				수강 시작일  :  <input type="text" id="start_date" name="start_date" maxlength="10" class="i_text" value="${searchMap.to_date }" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
   			</td>
   		</tr>

   		<tr>
   			<td class="tdLeft">
				기간  :  <input type="text" id="subject_period" name="subject_period"  title="레이블 텍스트"  style="width:10% ;background:#FFECEC;" value="${searchMap.subject_period}"/>일
   			</td>
   		</tr>

   		<tr>
   			<td class="tdLeft">
				상태  :
				  <select id="statuscode" name="statuscode" style="width:100px;">
					<c:forEach items="${order_list}"  var="order_list">
						<option value="${order_list.CODE_VAL}" <c:if test="${'DLV105' == order_list.CODE_VAL}">selected</c:if>>${order_list.CODE_NM }</option>
					</c:forEach>
				  </select>
   			</td>
   		</tr>
   		<tr>
    		<th>무료수강사유</th>
   			<td class="tdLeft"><input type="text" id="memo" name="memo"  title="메모"  style="width:100% ;background:#FFECEC;" value="${searchMap.memo}"/>	</td>
   		</tr>

	</table>
	<!--//테이블-->

	<!--버튼-->
    <ul class="boardBtns">
   	  	<li><a href="javascript:save_submit()">확인</a></li>
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->
</td>
<td style="width:2%;">
</td>
</tr>
</table>
</form>
</div>
<!--//content -->
</body>
