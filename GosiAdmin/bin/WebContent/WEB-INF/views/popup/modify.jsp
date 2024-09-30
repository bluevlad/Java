<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>
<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker1("START_DATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDateFicker1("END_DATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	
	
	
	$("#START_TIME").val('${view.START_TIME}');
	$("#END_TIME").val('${view.END_TIME}');
});


function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

function paramCheck() {
	
	var codeStr = "";
	$("input[name=CATEGORY_CODE]:checked").each(function() {
		checkboxValue = $(this).val();
		codeStr+= checkboxValue+",";
	});
	
	codeStr = codeStr.slice(0 , -1);
	
	if(codeStr==""){
		alert("직렬구분을 1개 이상 체크해 주세요.");
	}
	else if($("#START_DATE").val() == "") {
		alert("시작일을 등록해 주세요.");
		$("#START_DATE").focus();
	} 
	else if($("#END_DATE").val() == "") {
		alert("종료일을 등록해 주세요.");
		$("#END_DATE").focus();
	} 
	else if($("#END_DATE").val() == "") {
		alert("종료일을 등록해 주세요.");
		$("#END_DATE").focus();
	}
	else if($("#TITLE").val() == "") {
		alert("제목을 등록해 주세요.");
		$("#TITLE").focus();
	} 
	else if($("#POPUP_IMG").val() == "" && $("#POPUP_IMG_DEL").val() == "Y") {
		alert("팝업이미지를 등록해 주세요.");
	}
	else if($("#THUMBNAIL").val() == "" && $("#THUMBNAIL_DEL").val() == "Y") {
		alert("메인 썸네일을 등록해 주세요.");
	}
	else {
		$bb  = $("#codeStr").val(codeStr);
		if(confirm("수정하시겠습니까?")){
			$("#frm").attr("action", "<c:url value='/popup/update.do' />");
			$("#frm").submit();			
		}
	}
}
function fn_List(){
	$("#frm").attr("action", "<c:url value='/popup/list.do' />");
	$("#frm").submit();
}

function fn_delete_img(val , id){
	$("#"+id).html("");
	if(val == "popup"){
		$("#POPUP_IMG_DEL").val("Y");
	}
	if(val == "thumbnail"){
		$("#THUMBNAIL_DEL").val("Y");
	}
}

function fn_popupDelete(){
	if(confirm("삭제하시겠습니까?")){
		$("#frm").attr("action", "<c:url value='/popup/delete.do' />");
		$("#frm").submit();	
	}
}
</script>
</head>


<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="codeStr" name="codeStr"  value=""/>
<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}"/>
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="NO" name="NO" value="${view.NO}">

<input type="hidden" id="POPUP_IMG_DEL" name="POPUP_IMG_DEL" value="">
<input type="hidden" id="POPUP_IMG_DEL_NM" name="POPUP_IMG_DEL_NM" value="${view.POPUP_IMG }">
<input type="hidden" id="THUMBNAIL_DEL" name="THUMBNAIL_DEL" value="">
<input type="hidden" id="THUMBNAIL_DEL_NM" name="THUMBNAIL_DEL_NM" value="${view.THUMBNAIL }">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />

    	<h2>● 사이트관리  > <strong>팝업 관리</strong></h2>
    	<table class="table01">
    	<tr>
    		<th scope="col">직렬구분</th>
   			<td scope="col" colspan="3"  style="text-align:left;">
   				<div class="item" id="codeList">
	   				<input type="checkbox" id="allCheck" name="allCheck" VALUE="" onclick="fn_CheckAll('allCheck', 'CATEGORY_CODE')"/>전체 &nbsp;
	   				<c:forEach items="${categoryList}"  var="data" varStatus="status" >
	   					<c:set var="vChecked">
							  <c:forEach var="vList2" items="${popupCateList}" varStatus="vStatus2">
								   <c:choose>
								    <c:when test="${data.CODE == vList2}">checked="checked"</c:when>
								    <c:otherwise></c:otherwise>
								   </c:choose>
							  </c:forEach>
						 </c:set>
	   					<input name="CATEGORY_CODE"  class="i_check" type="checkbox" value="${data.CODE}" <c:out value='${vChecked}'/>><label for="a1">${data.NAME }</label>
					</c:forEach>
				</div>
   			</td>
   		</tr>
   		   		<tr>
			<th>작성일</th>
			<td width="30%">
				${view.REG_DT}
			</td>
			<th>뷰카운트</th>
			<td>
				<fmt:formatNumber value="${view.HIT}" type="number"/>
			</td>
		</tr>
   		<tr>
			<th>시작일</th>
			<td width="30%">
				<input type="text" id="START_DATE" name="START_DATE" value="${view.START_DATE }" style="width:60;" maxlength="8" readonly="readonly"/>
				<select id="START_TIME" name="START_TIME">
					<option value="00">00시</option>
					<option value="01">01시</option>
					<option value="02">02시</option>
					<option value="03">03시</option>
					<option value="04">04시</option>
					<option value="05">05시</option>
					<option value="06">06시</option>
					<option value="07">07시</option>
					<option value="08">08시</option>
					<option value="09">09시</option>
					<option value="10">10시</option>
					<option value="11">11시</option>
					<option value="12">12시</option>
					<option value="13">13시</option>
					<option value="14">14시</option>
					<option value="15">15시</option>
					<option value="16">16시</option>
					<option value="17">17시</option>
					<option value="18">18시</option>
					<option value="19">19시</option>
					<option value="20">20시</option>
					<option value="21">21시</option>
					<option value="22">22시</option>
					<option value="23">23시</option>
				</select>
			</td>
			<th>종료일</th>
			<td>
				<input type="text" id="END_DATE" name="END_DATE" value="${view.END_DATE }" style="width:60;" maxlength="8" readonly="readonly"/>
				<select id="END_TIME" name="END_TIME">
					<option value="00">00시</option>
					<option value="01">01시</option>
					<option value="02">02시</option>
					<option value="03">03시</option>
					<option value="04">04시</option>
					<option value="05">05시</option>
					<option value="06">06시</option>
					<option value="07">07시</option>
					<option value="08">08시</option>
					<option value="09">09시</option>
					<option value="10">10시</option>
					<option value="11">11시</option>
					<option value="12">12시</option>
					<option value="13">13시</option>
					<option value="14">14시</option>
					<option value="15">15시</option>
					<option value="16">16시</option>
					<option value="17">17시</option>
					<option value="18">18시</option>
					<option value="19">19시</option>
					<option value="20">20시</option>
					<option value="21">21시</option>
					<option value="22">22시</option>
					<option value="23">23시</option>
				</select>
			</td>
		</tr>
   		<tr>
    		<th scope="col">적용 여부</th>
   			<td scope="col" colspan="3" style="text-align:left;">
   				<div class="item">
	   				<input name="OPEN_YN"  class="i_check" value="N" type="radio"  <c:if test="${view.OPEN_YN == 'N' }">checked="checked"</c:if>><label for="a2">미적용</label>&nbsp;&nbsp;
	   				<input name="OPEN_YN"  class="i_check" value="Y" type="radio"  <c:if test="${view.OPEN_YN == 'Y' }">checked="checked"</c:if>><label for="a3">적용</label>
				</div>
   			</td>
   		</tr>	
   		<tr>
   			<th scope="col">제목</th>
   			<td scope="col" colspan="3" style="text-align:left;">
   				<div class="item">
   					<input type="text" id="TITLE" name="TITLE" class="i_text" title="레이블 텍스트" value="${view.TITLE }" style="width:68%;" />&nbsp;&nbsp;
   				</div>
   			</td>
   		</tr>
		<tr>
			<th>팝업<br>(JPG,GIF)</th>
			<td style="text-align:left;" colspan="3">
				<input title="레이블 텍스트" type="file" name="POPUP_IMG" id="POPUP_IMG"  />
				<ul class="bannerFile" id="POPUP_IMG_ID">                
	                <li>${view.POPUP_IMG_NM } <a href="javascript:fn_delete_img('popup','POPUP_IMG_ID')"> X </a></li>
	                <li><img src="<c:url value="/imgFileView.do?path=${view.POPUP_IMG}"/>" width="150px" height="100px"></li>
	            </ul>
			</td>
		</tr>
		<tr>
			<th>메인 썸내일 <br>132px * 154px(JPG,GIF)</th>
			<td style="text-align:left;" colspan="3">
				<input title="레이블 텍스트" type="file" name="THUMBNAIL" id="THUMBNAIL" />
				<ul class="bannerFile" id="THUMBNAIL_ID">                
	                <li>${view.THUMBNAIL_NM } <a href="javascript:fn_delete_img('thumbnail','THUMBNAIL_ID')"> X </a></li>
	                <li><img src="<c:url value="/imgFileView.do?path=${view.THUMBNAIL}"/>" width="150px" height="100px"></li>
	            </ul>
			</td>
		</tr>
		<tr>
   			<th scope="col">링크주소</th>
   			<td scope="col" style="text-align:left;" colspan="3">
   				<div class="item">
   					<input type="text" id="LINK_ADDR" name="LINK_ADDR" class="i_text" title="레이블 텍스트" value="${view.LINK_ADDR }" style="width:68%;" />&nbsp;&nbsp;
   				</div>
   			</td>
   		</tr>
   		<tr>
    		<th scope="col">Target</th>
   			<td scope="col" style="text-align:left;" colspan="3">
   				<div class="item">
	   				<input name="LINK_TARGET"  class="i_check" value="1" type="radio" <c:if test="${view.LINK_TARGET == '1' }">checked="checked"</c:if>><label for="a2">현재창으로 연결</label>&nbsp;&nbsp;
	   				<input name="LINK_TARGET"  class="i_check" value="2" type="radio"  <c:if test="${view.LINK_TARGET == '2' }">checked="checked"</c:if>><label for="a3">새창으로 연결</label>
				</div>
   			</td>
   		</tr>	
		</table>
	<!--//테이블--> 
    
    <!--버튼-->
     <ul class="boardBtns">
	   	  <li><a href="javascript:paramCheck();">수정</a></li>
	   	  <li><a href="javascript:fn_popupDelete();">삭제</a></li>
	      <li><a href="javascript:fn_List();">목록</a></li>
	  </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
