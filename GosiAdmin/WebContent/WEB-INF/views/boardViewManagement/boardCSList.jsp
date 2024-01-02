<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
//숫자 입력 폼
function chk(obj){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {          
			$('#pageRow').val("");      
			$('#pageRow').focus();         
			return;       
			}       
		else {          
			if (val < 1) {             
				$('#pageRow').val("");          
				$('#pageRow').focus();            
				return;          
				}       
			}    
		}
}

//엔터키 검색
function fn_checkEnter(){
	$('#searchText').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
	
	$('#pageRow').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}
//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		goList(1);
		return;
	}
	if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#pageRow").val("${params.pageRow}");
    }
}

function fn_IsNumber(input) {
    var chars = "0123456789";
    for (var inx = 0; inx < input.value.length; inx++) {
        if (chars.indexOf(input.value.charAt(inx)) == -1)
            return false;
     }
     return true;
}

function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#form").attr("action", "<c:url value='/boardViewManagement/boardCSMngList.do' />");
	$("#form").submit();
}

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("sdate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

$(function() {
	setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
	initDatePicker1("edate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

//엑셀
function excel_onclick() { //ck 추가 
	$('#form').attr('action','<c:url value="/boardViewManagement/boardCSMngListexcel.do"/>').submit();
}

//비동기 분류 가져오기
function getSubCode(selectId, target, url) {
	var ccode_top = "";
	ccode_top = $("#"+selectId).val();
	
	if(ccode_top == "" || ccode_top == undefined) {
		return false;
	}
	
	$("#ccode").html('').append("<option value=\"\">-- 분류 --</option>");
		
	var _url = url + '?ccode_top=' + ccode_top;
	
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){					
					$("#ccode").append('<option value="' + mouigosa.CODE_VAL + '" >' + mouigosa.CODE_NM + '</option>');
				});	
			}
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
}

//CS상담내용 등록버튼 클릭
function chkTmInsForm2(){ //cs상담내용 등록
	 if ($('#ccode').val() == "") {
		alert('분류를 선택해 주세요.');
		$('#ccode').focus();
		return;
	}

	 if ($('#cscontent').val() == "") {
		alert('상담내용을 입력해 주세요.');
		$('#cscontent').focus();
		return;
	}
	 
	 $('#STS').val("Y");
	 
	 $('#form').attr('action','<c:url value="/boardViewManagement/boardCSInsert.do"/>').submit();
}
</script>
</head>

<div id="content">
	
	<h2>● 게시판관리 > <strong>CS전화응대통계</strong></h2>
	<!--테이블-->
		<form id="form" name="form" method="post">
		<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
		
		<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value=""/>
		<input type="hidden" id="BOARDVIEW_MNG_SEQ" name="BOARDVIEW_MNG_SEQ" value="">
		<input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="">
		<input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="">
		<input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="">
	
	    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
		<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
		<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
		<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

		<input type="hidden" name="STS" id="STS" value="N" >
		<input type="hidden" name="STS_TM" id="STS_TM" value="N" >

		<table class="table01">
		<tr>
            <th width="10%">검색조건</th>
		    <td width="*">
           		&nbsp;
          		<input type="text" id="sdate" name="sdate" maxlength="10" class="i_text" value="${params.sdate}" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           		~
           		<input type="text" id="edate" name="edate" maxlength="10" class="i_text" value="${params.edate}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
           		&nbsp;
				<label for="CS_DIV"></label>
				<select style="width:100px;"   id="CS_DIV" name="CS_DIV">
					<option value="">-- 구분 --</option>
					<option value="CSCOUNSEL"  <c:if test="${params.CS_DIV == 'CSCOUNSEL'}">selected</c:if>>고객상담</option>
					<option value="CSREFUND"  <c:if test="${params.CS_DIV == 'CSREFUND'}">selected</c:if>>환불</option>
					<option value="BOARD"  <c:if test="${params.CS_DIV == 'BOARD'}">selected</c:if>>상담게시판</option>
				</select>
            </td>
            <th width="10%">화면출력건수</th>
		    <td width="20%">
               	<input size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${pageRow}" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
		</tr>
     	</table>
    <!--버튼-->    
    <ul class="boardBtns">
        <li><a href="javascript:excel_onclick();">Excel</a></li>
    </ul>
    <!--//버튼-->
     	
     <!--//테이블-->
		<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
		<table class="table02">
			<tr>
				<th width="8%">No</th>
				<th width="15%">접수시간</th>
				<th width="10%">상담자</th>
				<th width="10%">고객명</th>
				<th width="10%">구분</th>
				<th width="10%">분류</th>
				<th width="10%">조치</th>
				<th>질의내용</th>
			</tr>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="data" varStatus="status">
		        <tr>
					<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		            <td>${data.REG_DT}</td>
					<td>${data.ADMIN_NM}</td>
					<td>${data.USER_NM}</td>
					<td>${data.B_TYPE}</td>
					<td>${data.BOARD_TYPE}</td>
					<td>${data.B_ACT}</td>
					<td class="tdLeft">${data.SUBJECT}</td>
		        </tr>
				</c:forEach>
			
			</c:if>
			<c:if test="${empty list}">
	            <tr bgColor=#ffffff align=center> 
					<td colspan="10">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	
       </table>

    
    <table style="width:100%;">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ CS상담내용 등록</p></td>
      </tr>
    </table>
    
    <!--테이블--> 
      <table class="table05">
          <tr>
            <td style="text-align:left;">            				
				<c:forEach items="${csccode_list}" var="csccode_list" varStatus="status">				
					<input type="radio" name="ccode_top"  id="ccode_top${status.index}" value="${csccode_list.CODE_VAL}" onclick="getSubCode('ccode_top${status.index}', 'ccode', '<c:url value="/productOrder/ccode_top.do"/>')">
                    ${csccode_list.CODE_NM}
				</c:forEach>
				
				<select id="ccode" name="ccode" >
					<option value="">-- 분류 --</option>
				</select>
				
				<select name="actionflag" id="actionflag" >
                    <option value="Y">조치</option>
                    <option value="N">미조치</option>
                </select>
                &nbsp;&nbsp;고객명&nbsp;:&nbsp;
				<input type="text"  id="username" name="username" style="width:120px;" title=""/>
			</td>
  		  </tr>
		  <tr>
			<td style="text-align:left;">
				<input id="cscontent" name="cscontent" type="text" style="width:80%;" title=""/>
				<input type="button" onclick="chkTmInsForm2();"  value="등록" />
		    </td>
          </tr>
      </table>
	</form>	            
		<!--//버튼-->
	<!--paging  -->
	<c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
    <!-- //paging  -->
</div>
<!--//content --> 

</html>