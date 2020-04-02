<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %><head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/libs/cheditor/cheditor.js" /></script>
<script type="text/javascript">
var editor = null;
$(document).ready(function(){
	editor = new cheditor();              // 에디터 개체를 생성합니다.
	editor.config.editorHeight = '250px';     // 에디터 세로폭입니다.
	editor.config.editorWidth = '100%';        // 에디터 가로폭입니다.
	editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
	editor.inputForm = 'CONTENTS_TEXT';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	editor.run(); 
	
	setDateFickerImageUrl("<c:url value='/resources/images/btn_calendar.gif'/>");
	initDatePicker1("START_DATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDatePicker1("END_DATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	
	contntEvent();
});

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

//등록
function fn_Submit(){
	if($.trim($("#TITLE").val())==""){
		alert("이벤트명을 입력해주세요");
		$("#TITLE").focus();
		return;
	}
	var contentStr = editor.outputBodyHTML();
	//alert("contentStr : "+ contentStr);
	//alert($("#CONTENTS_TEXT").val());
	
	if(confirm("이벤트를 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/eventManagement/eventUpdateProcess.do' />");
		$("#frm").submit();
	}
}

function fn_List(){
	$("#frm").attr("action", "<c:url value='/eventManagement/eventMgtList.do' />");
	$("#frm").submit();
}

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

function fn_TextIn(){
	if($.trim($("#OPTION_NAME_TMP").val())==""){
		alert("선택명을 입력해주세요");
		$("#OPTION_NAME_TMP").focus();
		return;
	} else if($("select[name='PEOPLE_GUBUN_TMP']").val() == '2' 
				&& $.trim($("#PEOPLE_CNT_TMP").val())==""){
		alert("인원을 입력해주세요");
		$("#PEOPLE_CNT_TMP").focus();
		return;
	}
	
	var divLength = $("#listControl div").length;
	var lastItemNo;
	var nNum;
	//alert("divLength > " + divLength);
	if(divLength > 0){
		lastItemNo = $("#listControl div:last-child").attr('id').replace("rowText",""); // $("#fileControl").replace("ATTACH_FILE", "");
	    nNum = parseInt(lastItemNo) + 1;
	} else {
		nNum = 1;
	}
	// alert(lastItemNo + " : " + nNum);
	//alert($("#PEOPLE_GUBUN_TMP option:selected").val());
	var cntTmp;
	if($("select[name='PEOPLE_GUBUN_TMP']").val() == '1') cntTmp = ""; 
	else cntTmp = $("#PEOPLE_CNT_TMP").val();
	
	var htmlIn = '<div id="rowText' + nNum + '">' + $("#OPTION_NAME_TMP").val() + ' - ';
	if($("select[name='PEOPLE_GUBUN_TMP']").val() == '1') htmlIn += '무제한';
	else htmlIn += '<strong>' + $("#PEOPLE_CNT_TMP").val() + '명</strong>';// + ' 인원수(100)명'
	htmlIn += '<input type="hidden" name="OPTION_NAME" value="' + $("#OPTION_NAME_TMP").val() + '">';
	htmlIn += '<input type="hidden" name="PEOPLE_GUBUN" value="' + $("#PEOPLE_GUBUN_TMP option:selected").val() + '">';
	htmlIn += '<input type="hidden" name="PEOPLE_CNT" value="' + cntTmp + '">';
	htmlIn += '<input type="button" value="삭제" onclick="javascript:delRow(\'' + nNum + '\',\'text\');"></div>';
	
	//alert(htmlIn);
	$("#listControl").append(htmlIn);
}

function fn_Disbl(){
	if($("select[name='PEOPLE_GUBUN_TMP']").val() == '1') $("#PEOPLE_CNT_TMP").attr("disabled", true);
	else $("#PEOPLE_CNT_TMP").attr("disabled", false);
}

function fn_Display(){
	if($("#OPTION1").attr("checked") == "checked") $("#option1Table").show();
	else $("#option1Table").hide();
	
	if($("#OPTION2").attr("checked") == "checked") $("#option2Table").show();
	else $("#option2Table").hide();
	<c:if test="${MENUTYPE eq 'FM_ROOT'}">
	if($("#OPTION3").attr("checked") == "checked") $("#option3Table").show();
	else $("#option3Table").hide();
	</c:if>
}

function contntEvent(){
	//alert('thumChngEvent');
	var gubun = $('input:radio[name="CONTNT_GUBUN"]:checked').val();
	
	if(gubun == 'IMG'){
		$("#CONTENTS_IMG").attr("disabled", false);
		$("#CONTENTS_TEXT").attr("disabled", true);
		$('input[name="ATTACH_FILE"]').attr("disabled", true);
		$("#addButton").attr("disabled", true);
		$('input[name="delAttchButton"]').attr("disabled", true);
	}else{
		$("#CONTENTS_IMG").attr("disabled", true);
		$("#CONTENTS_TEXT").attr("disabled", false);
		$('input[name="ATTACH_FILE"]').attr("disabled", false);
		$("#addButton").attr("disabled", false);
		$('input[name="delAttchButton"]').attr("disabled", false);
	}
}

function addAttchFile(){
	var divLength = $("#fileControl div").length;
	var lastItemNo;
	var nNum;
	//alert("divLength > " + divLength);
	if(divLength > 0){
		lastItemNo = $("#fileControl div:last-child").attr('id').replace("row",""); // $("#fileControl").replace("ATTACH_FILE", "");
	    nNum = parseInt(lastItemNo) + 1;
	} else {
		nNum = 1;
	}
	//alert(lastItemNo + " : " + nNum);	
    var newitem = '<div id="row' + nNum + '"><input type="file" name="ATTACH_FILE" id="ATTACH_FILE_' + nNum + '" size="50">';
    newitem += ' <input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow(\'' + nNum + '\');"></div>';
    $("#fileControl").append(newitem);
	/*
    if(nNum > 10){
        alert("최대 10개까지 가능합니다.");
        return;
    }*/
}

function delRow(id, param){
	if(param == 'text') $("#rowText" + id).remove();
	else $("#row" + id).remove();
}

function goFileDownload(filePath) {
	document.location.href = "<c:url value='/download.do' />?path="+filePath;
}

function delFileRow(param, fNo){
	var id = 'existRow'+param;
	//alert(id);
	$("#"+id).remove();
	var newitem = '<input type="hidden" name="FILE_DEL_ALL" value="'+ fNo +'"/>';
	//alert(newitem);
    $("#existFileControl").append(newitem);
    alert('수정버튼을 눌러야 적용이 됩니다.');
}

function deleteImage(param){
	if(param == 'contentsImg'){
		//alert($("#bannerImg").html());
		$("#contentsImg").html('');
		$("#CONTENTS_IMG_DEL").val('Y');
	}else if(param == 'listThumImg'){
		$("#listThumImg").html('');
		$("#LIST_THUMBNAIL_DEL").val('Y');		
	}else if(param == 'issuThumImg'){
		$("#issuThumImg").html('');
		$("#ISSUE_THUMBNAIL_DEL").val('Y');		
	}
	alert('수정버튼을 눌러야 적용이 됩니다.');
}

//textarea에 입력된 문자의 바이트 수를 체크
function checkByte(frm) {
   
       var totalByte = 0;
       var message = frm.MESSAGEBOX.value;

       for(var i =0; i < message.length; i++) {
               var currentByte = message.charCodeAt(i);
               if(currentByte > 128) totalByte += 2;
		else totalByte++;
       }
       frm.MESSAGEBYTE.value = totalByte;

       /* if(totalByte > limitByte) {
   		alert( limitByte+"바이트까지 전송가능합니다.");
		frm.messagebox.value = message.substring(0,limitByte);
   	} */
}
</script>
</head>


<!--content -->


<div id="content">
<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>

<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="${ params.EVENT_NO }"/>
<c:choose>
	<c:when test="${ MENUTYPE eq 'FM_ROOT' }">
    	<input type="hidden" name="ONOFF_DIV" id="ONOFF_DIV" value="F"/>       			
	</c:when>
    <c:otherwise>
    	<input type="hidden" name="ONOFF_DIV" id="ONOFF_DIV" value="O"/>	
    </c:otherwise>
</c:choose>
        	
<table class="table01">
	<col width="20%">
	<col width="30%">
	<col width="20%">
	<col width="30%">
	<tr>
		<th>직렬</th>
			<td colspan="3" class="tdLeft">${ view.CATEGORY_NAME }</td>
		</tr>
	<tr>
		<th>공지구분</th>
		<td colspan="3" class="tdLeft">
			<c:if test="${ ! empty view.NOTICE_GUBUN && view.NOTICE_GUBUN ne ''}">
			<c:set var="gubun1" value="${ fn:substring(view.NOTICE_GUBUN,0,1) }" />
			<c:set var="gubun2" value="${ fn:substring(view.NOTICE_GUBUN,2,3) }" />
			</c:if>
			<c:if test="${ gubun1 eq '1' || gubun2 eq '1' }"></c:if>
			<input type="checkbox" name="NOTICE_GUBUN" id="NOTICE_GUBUN1" value="1"
			<c:if test="${ gubun1 eq '1' || gubun2 eq '1' }">checked="checked"</c:if>/>TOP
			<input type="checkbox" name="NOTICE_GUBUN" id="NOTICE_GUBUN2" value="2"
			<c:if test="${ gubun1 eq '2' || gubun2 eq '2' }">checked="checked"</c:if>/>이슈
		</td>
	</tr>
	<tr>
		<th>적용여부</th>
		<td class="tdLeft">
			<input type="radio" id="OPEN_YN_N" name="OPEN_YN" value="N"
			<c:if test="${ view.OPEN_YN eq 'N' }">checked="checked"</c:if>/>미적용
			<input type="radio" id="OPEN_YN_Y" name="OPEN_YN" value="Y"
			<c:if test="${ view.OPEN_YN eq 'Y' }">checked="checked"</c:if>/>적용
		</td>
		<th>이벤트 참여회원</th>
		<td class="tdLeft">
			<input type="radio" id="MEMBER_GUBUN_1" name="MEMBER_GUBUN" value="1" 
			<c:if test="${ view.MEMBER_GUBUN eq '1' }">checked="checked"</c:if>/>회원+비회원
			<input type="radio" id="MEMBER_GUBUN_2" name="MEMBER_GUBUN" value="2"
			<c:if test="${ view.MEMBER_GUBUN eq '2' }">checked="checked"</c:if>/>회원만 가능
		</td>
	</tr>
	<tr>
		<th>시작일:${view.START_TIME }:${view.END_TIME }</th>
		<td colspan="3" class="tdLeft">
			<input type="text" id="START_DATE" name="START_DATE" style="width:60px;" readonly="readonly" value="${ view.START_DATE }">
			<select id="START_TIME" name="START_TIME">
			<c:forEach begin="1" end="24" step="1" var="item">
				<c:if test="${ item < 10}">
				<c:set var="itemCmpr" value="0${ item }"/>
				<option value="${ itemCmpr }"
				<c:if test="${ view.START_TIME eq itemCmpr }">selected="selected"</c:if>>0${item}</option></c:if>
				<c:if test="${ item >= 10}"><option value="${ item }"
				<c:if test="${ view.START_TIME eq item }">selected="selected"</c:if>>${item}</c:if>
			</c:forEach>
			</select>
			시
			&nbsp;&nbsp;~&nbsp;
			<input type="text" id="END_DATE" name="END_DATE" style="width:60px;" readonly="readonly" value="${ view.END_DATE }">
			<c:if test="${ view.END_TIME eq item }">selected="selected"</c:if>
			<select id="END_TIME" name="END_TIME">
			<c:forEach begin="1" end="24" step="1" var="item">
				<c:if test="${ item < 10}">
				<c:set var="itemCmpr" value="0${ item }"/>
				<option value="0${ item }" <c:if test="${ view.END_TIME eq itemCmpr }">selected="selected"</c:if>>0${item}</option></c:if>
				<c:if test="${ item >= 10}"><option value="${ item }"
				<c:if test="${ view.END_TIME eq item }">selected="selected"</c:if>>${item}</option></c:if>
			</c:forEach>
			</select>
			시
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td colspan="3" class="tdLeft"><input type="text" id="TITLE" name="TITLE" value="${ view.TITLE }" style="width:96%;"></td>
	 </tr>
</table>

<br/>
● 내용(둘 중에 하나만 선택 가능)
<table class="table01">
	<tr>
		<th width="20%">
			<input type="radio" id="CONTNT_GUBUN" name="CONTNT_GUBUN" value="IMG" 
			checked="checked" onclick="javascript:contntEvent();"/><br/>이미지
		</th>
		<td class="tdLeft">
			<input type="file" name="CONTENTS_IMG" id="CONTENTS_IMG" size="50">
			<input type="hidden" name="CONTENTS_IMG_BEFORE" id="CONTENTS_IMG_BEFORE" value="${view.CONTENTS_IMG}">
			<input type="hidden" name="CONTENTS_IMG_DEL" id="CONTENTS_IMG_DEL" value="">
			<ul class="bannerFile" id="contentsImg">
			<c:if test="${!empty view.CONTENTS_IMG}" >
			<li>${fn:substring(view.CONTENTS_IMG, fn:indexOf(view.CONTENTS_IMG, '/')+1, fn:length(view.CONTENTS_IMG))}
			<a href="javascript:deleteImage('contentsImg');"> X </a></li>
			<li><img src="<c:url value="/imgFileView.do?path=${view.CONTENTS_IMG}"/>" width="550"></li>
			</c:if>
			</ul>
		</td>	        	
	</tr>
	<tr>
		<th width="10%">
			<input type="radio" id="CONTNT_GUBUN" name="CONTNT_GUBUN" value="EDTR"
			<c:if test="${ ! empty view.CONTENTS_TEXT }">checked="checked" </c:if> onclick="javascript:contntEvent();"/><br/>에디터</th>
			<td class="tdLeft">
			<div><textarea id="CONTENTS_TEXT" name="CONTENTS_TEXT" title="레이블 텍스트" style="width:96%;">
			${ view.CONTENTS_TEXT }
			</textarea></div>
		</td>
	</tr>
	<tr>
		<th width="10%">첨부파일</th>
		<td class="tdLeft">
			<div class="item" id="existFileControl">
			<c:forEach var="item" items="${fileList }" varStatus="status">
				<c:if test="${item.FILE_PATH ne null}">
				<div id="existRow${ status.count }"><a href="javascript:goFileDownload('<c:out value="${item.FILE_PATH}" />')">${item.FILE_NAME}</a>
				<input type="button" name="delAttchButton" value="삭제" onclick="javascript:delFileRow('${status.count}','${ item.FILE_NO}');"><br/></div>
				</c:if>
			</c:forEach>
			</div>
			<div class="item" id="fileControl">
			<input type="button" id="addButton" onclick="javascript:addAttchFile();" value="파일추가">
			<div id="row1"><input type="file" name="ATTACH_FILE" id="ATTACH_FILE_1" style="width:500px;">
			<input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow('1');"></div>						
			</div>
		</td>
	</tr>		      
</table>

<br/>
● 리스트 썸네일 이미지 등록
<table class="table01">
	<tr>
		<th width="20%">리스트 썸네일</th>
      	<td class="tdLeft">
      		<input type="file" name="LIST_THUMBNAIL" id="LIST_THUMBNAIL" size="50">
          	<input type="hidden" name="LIST_THUMBNAIL_BEFORE" id="LIST_THUMBNAIL_BEFORE" value="${view.LIST_THUMBNAIL}">
			<input type="hidden" name="LIST_THUMBNAIL_DEL" id="LIST_THUMBNAIL_DEL" value="">
			<ul class="bannerFile" id="listThumImg">
			<c:if test="${!empty view.LIST_THUMBNAIL}" >
			<li>${fn:substring(view.LIST_THUMBNAIL, fn:indexOf(view.LIST_THUMBNAIL, '/')+1, fn:length(view.LIST_THUMBNAIL))}
			<a href="javascript:deleteImage('listThumImg');"> X </a></li>
			<li><img src="<c:url value="/imgFileView.do?path=${view.LIST_THUMBNAIL}"/>"   style="width:500px;"></li>
			</c:if> 
          	</ul>		        	
		</td>
	</tr>
    <tr>
		<th>이슈 썸네일</th>
      	<td class="tdLeft">
			<input type="file" name="ISSUE_THUMBNAIL" id="ISSUE_THUMBNAIL" size="50">
          	<input type="hidden" name="ISSUE_THUMBNAIL_BEFORE" id="ISSUE_THUMBNAIL_BEFORE" value="${view.ISSUE_THUMBNAIL}">
			<input type="hidden" name="ISSUE_THUMBNAIL_DEL" id="ISSUE_THUMBNAIL_DEL" value="">
			<ul class="bannerFile" id="issuThumImg">
			<c:if test="${!empty view.ISSUE_THUMBNAIL}" >
			<li>${fn:substring(view.ISSUE_THUMBNAIL, fn:indexOf(view.ISSUE_THUMBNAIL, '/')+1, fn:length(view.ISSUE_THUMBNAIL))}
			<a href="javascript:deleteImage('issuThumImg');"> X </a></li>
			<li><img src="<c:url value="/imgFileView.do?path=${view.ISSUE_THUMBNAIL}"/>"  style="width:500px;"></li>
			</c:if>
          	</ul>		        	
		</td>
	</tr>
</table>
		  
<br/>
● 옵션 1 <input type="checkbox" id="OPTION1" name="OPTION1" value="Y" onclick="javascript:fn_Display();" 
<c:if test="${ view.OPTION1_YN eq 'Y' }">checked="checked"</c:if>/> (선택시 노출)
<table class="table01" id="option1Table" 
	<c:if test="${ view.OPTION1_YN ne 'Y' }">style="display: none;"</c:if>>
	<tr>
		<th width="10%">선택리스트</th>
		<td class="tdLeft tdValign">
  			* 1개라도 옵션리스트 설정시 정보입력(이름, 연락, 이메일) 자동 생성<br/>
  			<input type="text" id="OPTION_NAME_TMP" name="OPTION_NAME_TMP">
  			<select id="PEOPLE_GUBUN_TMP" name="PEOPLE_GUBUN_TMP" onclick="javascript:fn_Disbl();">
				<option value="1">무제한</option>
				<option value="2">인원설정</option>
			</select>
			<input type="text" id="PEOPLE_CNT_TMP" name="PEOPLE_CNT_TMP" onKeyDown="fn_OnlyNumber(this);">
			<input type="button" onclick="javascript:fn_TextIn();" value="확인">
			<div id="listControl">
				<c:forEach var="item" items="${option1List }" varStatus="status">
				<div id="rowText${ status.count }"><input type="text" name="OPTION_NAME" value="${item.OPTION_NAME }" size="50"> -
				<c:if test="${ item.PEOPLE_GUBUN eq '1' }">무제한</c:if>
				<c:if test="${ item.PEOPLE_GUBUN ne '1' }">${ item.PEOPLE_CNT }명</c:if>
				<input type="hidden" name="PEOPLE_GUBUN" value="${item.PEOPLE_GUBUN }"/>
				<input type="hidden" name="PEOPLE_CNT" value="${ item.PEOPLE_CNT }"/>
				<input type="button" value="삭제" onclick="javascript:delRow('${status.count }','text');"/></div>			        		
				</c:forEach>
			</div>       	
		</td>
	</tr>
   	<tr>
		<th>선택 방식 설정</th>
     	<td class="tdLeft tdValign">
			<input type="checkbox" id="MULTI_SELECT_YN" name="MULTI_SELECT_YN" value="Y"
     		<c:if test="${ option1List[0].MULTI_SELECT_YN eq 'Y' }">checked="checked"</c:if>/>다중선택
		</td>
	</tr>
</table>

<br/>
● 옵션 2 <input type="checkbox" id="OPTION2" name="OPTION2" value="Y" onclick="javascript:fn_Display();"]
<c:if test="${ view.OPTION2_YN eq 'Y' }">checked="checked"</c:if>/> (선택시 노출)
<table class="table01" id="option2Table" 
	<c:if test="${ view.OPTION2_YN ne 'Y' }">style="display: none;"</c:if>>
	<tr>
		<th width="10%">덧글기능 선택</th>
  		<td class="tdLeft tdValign">선택시 이벤트 내용 하단에 댓글 기능 노출</td>
	</tr>
	<tr>
  		<th>덧글 비노출 설정</th>
  		<td class="tdLeft tdValign">
  			<input type="checkbox" id="OPTION2_HIDDEN" name="OPTION2_HIDDEN" value="N"
  			<c:if test="${ view.OPTION2_HIDDEN eq 'N' }">checked="checked"</c:if>/>비노출
        </td>
	</tr>
</table>
<c:if test="${MENUTYPE eq 'FM_ROOT'}">
<br/>
● 옵션 3 [자동문자발송설정] <input type="checkbox" id="OPTION3" name="OPTION3" value="Y" onclick="javascript:fn_Display();" <c:if test="${ view.OPTION3_YN eq 'Y' }">checked="checked"</c:if>/> (선택시 노출)
		<table class="table01" id="option3Table" <c:if test="${ view.OPTION3_YN ne 'Y' }">style="display: none;"</c:if>>
		      <tr>
		        <th width="10%">발송정보</th>
		        <td width="20%">
				<textarea name="MESSAGEBOX" cols="40" rows="10" onKeyUp="checkByte(this.form);">${view.SMS_MESSAGE_BOX}</textarea>
				</td>
				<td width="80%">
					<br>
					# 사용자 이벤트 페이지에서 해당 이벤트 신청 시 위에 입력한 발송문구가 자동 발송됩니다.	
					<br><br>
					&nbsp;<input type="text" name="MESSAGEBYTE" value="0" size="1" readonly> <span style="color: red;">byte</span> <span style="color: blue;">(80byte 이상 입력 시 MMS 문자로 전환됩니다.)</span>
					<br><br><br><br>
					[발신번호] <input type="text" name="SMSNUM" value="${view.SMS_NUM}" >				
				</td>
		      </tr>
		</table>	
		</c:if>	
<!--버튼-->
<ul class="boardBtns">
	<li><a href="javascript:fn_Submit();">확인</a></li>
	<li><a href="javascript:fn_List();">목록</a></li>
</ul>
<!--//버튼--> 
</form>
</div>
<!--//content --> 