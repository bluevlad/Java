<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="BRIDGE_LECCODE" name="BRIDGE_LECCODE" value="">
<input type="hidden" id="LECCODE" name="LECCODE" value="">

    <h2>● 강의관리 > <strong>강의개설</strong></h2>
    
    <ul class="lecWritheTab">
    	<li><a href="javascript:fn_goLecType('D');" <c:if test="${params.LEC_TYPE_CHOICE eq 'D'}">class="active"</c:if>>단과</a></li>
        <li><a href="javascript:fn_goLecType('J');" <c:if test="${params.LEC_TYPE_CHOICE eq 'J'}">class="active"</c:if>>종합반</a></li>
        <li><a href="javascript:fn_goLecType('P');" <c:if test="${params.LEC_TYPE_CHOICE eq 'P'}">class="active"</c:if>>패키지</a></li>
        <li><a href="javascript:fn_goLecType('Y');" <c:if test="${params.LEC_TYPE_CHOICE eq 'Y'}">class="active"</c:if>>연회원패키지</a></li>
    </ul>       
    
    <table class="table01">
		<tr>
			<th>과정선택</th>
			<td colspan="3">
	          	<label for="SEARCHKIND"></label>
	            <select name="SEARCHKIND" id="SEARCHKIND">
					<option value="">--직종검색--</option>
					<c:forEach items="${kindlist}" var="item" varStatus="loop">
						<option value="${item.CODE}" <c:if test="${params.SEARCHKIND == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
					</c:forEach>
	            </select>
	            <label for="SEARCHFORM"></label>
	           	<select name="SEARCHFORM" id="SEARCHFORM">
					<option value="">--학습형태검색--</option>
					<c:forEach items="${formlist}" var="item" varStatus="loop">
						<option value="${item.CODE}" <c:if test="${params.SEARCHFORM == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
					</c:forEach>
	          </select>
	            <label for="SEARCHFORM"></label>
	           	<select name="SEARCHISUSE" id="SEARCHISUSE">
					<option value="">--개설여부검색--</option>
					<option value="Y" <c:if test="${params.SEARCHISUSE == 'Y'}">selected="selected"</c:if>>활성</option>					
					<option value="N" <c:if test="${params.SEARCHISUSE == 'N'}">selected="selected"</c:if>>비활성</option>					
	          </select>
			</td>
		</tr>
        <tr>
			<th width="15%">검색</th>
			<td>
				<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="">--전체검색--</option>
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>과목</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>강의명</option>
					<option value="3" <c:if test="${params.SEARCHTYPE == '3' }">selected="selected"</c:if>>강사명</option>
					<option value="4" <c:if test="${params.SEARCHTYPE == '4' }">selected="selected"</c:if>>강의코드</option>
				</select> 
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
          </td>
          <th width="15%">화면출력건수</th>
          <td width="30%"><input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/> <input type="button" onclick="fn_Search()" value="검색" /></td>
        </tr>
    </table>
	
    <ul class="lecArrary">
   	  <li><a href="javascript:fn_SeqPop();">순서변경</a></li>
      <li><span>활성된 강의</span> </li>
      <li><span class="span01">비활성된 강의</span> </li>
      <li><input id="SEARCHYEAR" name="SEARCHYEAR" type="checkbox" value="Y" <c:if test="${params.SEARCHYEAR == 'Y' }">checked="checked"</c:if>>2011년도 이후강의만 보기</li>
    </ul>
    
    <ul class="boardBtns">
    	<li><a href="javascript:fn_Reg();">등록</a></li>
    	<!-- 
    	<li><a href="#">삭제</a></li> 
        <li><a href="#">개설</a></li>
        <li><a href="#">폐강</a></li>
        -->
    </ul>
    
<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>    
    
	<table class="table02">
      <!--  
      <tr>
        <th width="110"><input name="input" type="checkbox" value="">No </th>
        <th width="80">직종<br><span class="spanMR01"><a href="#">▲</a></span> <span><a href="#">▼</a></span></th>
        <th width="80">과목<br><span class="spanMR01"><a href="#">▲</a></span> <span><a href="#">▼</a></span></th>
        <th width="75">강의코드</th>
        <th width="55">강사명<br><span class="spanMR01"><a href="#">▲</a></span> <span><a href="#">▼</a></span></th>
        <th>강의명<br><span class="spanMR01"><a href="#">▲</a></span> <span><a href="#">▼</a></span></th>
        <th width="75">학습형태<br><span class="spanMR01"><a href="#">▲</a></span> <span><a href="#">▼</a></span></th>
        <th width="70">VOD / PMP <br>
        / V+P</th>
        <th width="60">시작일<br><span class="spanMR01"><a href="#">▲</a></span> <span><a href="#">▼</a></span></th>
        <th width="70">PMP / MP4<br>
        / 동영상</th>
        <th width="80">등록일<br><span class="spanMR01"><a href="#">▲</a></span> <span><a href="#">▼</a></span></th>
        <th width="60">개설여부<br><span class="spanMR01"><a href="#">▲</a></span> <span><a href="#">▼</a></span></th>
      </tr>
      -->
		<tr>
	        <th width="110"><!-- <input name="input" type="checkbox" value=""> -->No </th>
	        <th width="80">직종</th>
	        <th width="80">과목</th>
	        <th width="75">강의코드</th>
	        <th width="55">강사명</th>
	        <th>강의명</th>
	        <th width="75">학습형태</th>
	        <th width="100">VOD / PMP / V+P</th>
	        <th width="110">동영상 / MP4 / PMP</th>
	        <th width="80">등록일</th>
	        <th width="60">개설여부</th>	        
	        <!--  
	        <th width="70">VOD / PMP <br>
	        / V+P</th>
	        <th width="60">시작일</th>
	        <th width="70">PMP / MP4<br>
	        / 동영상</th>
	        <th width="80">등록일</th>
	        <th width="60">개설여부</th>
	        -->
		</tr>      
		
		<c:forEach items="${list}" var="item" varStatus="loop">
			
			<tr id="TR_${item.BRIDGE_LECCODE}" <c:if test="${item.SUBJECT_ISUSE eq 'Y'}">class="vitality"</c:if>>
		        <td class="tdCenter">${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
		        <td><a href="javascript:fn_DataView('${item.BRIDGE_LECCODE}','${item.LECCODE}');">${item.CATEGORY_NM}</a></td>
		        <td>${item.SUBJECT_NM}</td>
		        <td>${item.LECCODE}<br>${item.BRIDGE_LECCODE}</td>
		        <td>${item.SUBJECT_TEACHER_NM}</td>
		        <!-- <td class="tdLeft"><a href="#">${item.SUBJECT_TITLE}</a></td> -->
		        <td style="text-align:left;"><a href="javascript:fn_Modify('${item.BRIDGE_LECCODE}','${item.LECCODE}');">${item.SUBJECT_TITLE}</a></td>
		        <td>${item.LEARNING_NM}</td>
		        <td><a href="javascript:fn_PayListPop('Y','VOD','${item.LECCODE}','${item.BRIDGE_LECCODE}');">${item.VODY}</a>/<a href="javascript:fn_PayListPop('N','VOD','${item.LECCODE}','${item.BRIDGE_LECCODE}');">${item.VODN}</a> | <a href="javascript:fn_PayListPop('Y','PMP','${item.LECCODE}','${item.BRIDGE_LECCODE}');">${item.PMPY}</a>/<a href="javascript:fn_PayListPop('N','PMP','${item.LECCODE}','${item.BRIDGE_LECCODE}');">${item.PMPN}</a> | <a href="javascript:fn_PayListPop('Y','VOD+PMP','${item.LECCODE}','${item.BRIDGE_LECCODE}');">${item.VODPMPY}</a>/<a href="javascript:fn_PayListPop('N','VOD+PMP','${item.LECCODE}','${item.BRIDGE_LECCODE}');">${item.VODPMPN}</a></td>
		        <td>
		        	<c:set var="OPTIONSTR" value="" />
					<c:if test="${!empty item.SUBJECT_OPTION}">
						<c:set var="SOA" value="${fn:split(item.SUBJECT_OPTION,',')}"/>
						<c:forEach items="${SOA}" var="item2" varStatus="loop2">
							<c:if test="${item2 eq 'AA'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}MP4" />
							</c:if>
							<c:if test="${item2 eq '2'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}PMP" />							
							</c:if>						
							<c:if test="${item2 eq '1'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}동영상" />							
							</c:if>
							<c:if test="${item2 eq '4'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}동영상" />							
							</c:if>
						</c:forEach>        			
					</c:if>
		        	<a href="javascript:fn_MovieDataViewPop('${item.BRIDGE_LECCODE}','${item.LECCODE}','PMP');">${OPTIONSTR}</a>
		        </td>			        
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
		        <%-- <td class="txt03"><c:if test="${item.SUBJECT_ISUSE eq 'Y' }">활성</c:if><c:if test="${item.SUBJECT_ISUSE ne 'Y' }">비활성</c:if></td> --%>
		        <td class="txt03"> <select onchange="javascript:Lec_ON_OFF('${item.BRIDGE_LECCODE}','${item.SUBJECT_TITLE}');" id="ON_OFF_${item.BRIDGE_LECCODE}"> <option value="ON" id="ON_${item.BRIDGE_LECCODE}"<c:if test="${item.SUBJECT_ISUES eq 'Y' }">selected</c:if>>활성</option><option value="OFF" id="OFF_${item.BRIDGE_LECCODE}"<c:if test="${item.SUBJECT_ISUSE ne 'Y'}">selected</c:if>>비활성</option></select></td>
		        <!--  
		        <td class="tdRight">
		            V : <a href="#">0</a> / <a href="#">0</a><br>
		            P : <a href="#">0</a> / <a href="#">0</a><br>
		            V+P : <a href="#">0</a> / <a href="#">0</a>
		        </td>
		        <td>2013.10</td>
		        <td><a href="#">PMP</a> / <a href="#">MP4</a> / <a href="#">동영상</a></td>
		        <td>2013-10-12</td>
		        <td class="txt03">활성</td>
		        -->
			</tr>
		</c:forEach>	
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="10">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>	 		
    </table>   

	<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
    
    <ul class="boardBtns">
   	  	<li><a href="javascript:fn_Reg();">등록</a></li>
        <!-- 
        <li><a href="#">삭제</a></li> 
        <li><a href="#">개설</a></li>
        <li><a href="#">폐강</a></li>
        -->
    </ul>
    
</form>
</div>
<script type="text/javascript">

// 페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/lecture/list.do' />");
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

//삭제
function fn_Delete(){
	if($("input[name='DEL_ARR']:checked").length > 0){
		if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
			$("#frm").attr("action", "<c:url value='/lecture/listDelete.do' />");
			$("#frm").submit();
		}
	}else{
		alert("선택된 항목이 없습니다");
		return;
	}
}

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/lecture/list.do' />");
	$("#frm").submit();
}

// 등록폼
function fn_Reg(){
	$("#frm").attr("action", "<c:url value='/lecture/write.do'/>");
	$("#frm").submit();
}

// 수정폼
function fn_Modify(val1, val2){
	$("#BRIDGE_LECCODE").val(val1);
	$("#LECCODE").val(val2);
	$("#frm").attr("action", "<c:url value='/lecture/modify.do' />");
	$("#frm").submit();
}

// 직종클릭시 팝업
function fn_DataView(val1, val2){
	window.open('<c:url value="/lecture/dataViewList.pop"/>?LECCODE=' + val2 + '&BRIDGE_LECCODE=' + val1, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1100,height=670');
}

// 엔터키 검색
function fn_Enter(){
	$("#SEARCHTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			fn_Search();
	});
}

// RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		fn_Search();
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

function fn_goLecType(val){
	$("#LEC_TYPE_CHOICE").val(val);
	if(val=="D"){
		$("#frm").attr("action", "<c:url value='/lecture/list.do' />");
	} else if(val=="Y"){
		$("#frm").attr("action", "<c:url value='/lecture/yearlist.do' />");
	}else{
		$("#frm").attr("action", "<c:url value='/lecture/jongList.do' />");
	}
	$("#frm").submit();	
}

// 순서변경팝업
function fn_SeqPop(type){
	window.open('<c:url value="/lecture/seqList.pop"/>?LEC_TYPE_CHOICE=${params.LEC_TYPE_CHOICE}', '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1040,height=670');
}

// 리로드
function fn_reload(){
	$("#frm").attr("action", "<c:url value='/lecture/list.do' />");
	$("#frm").submit();
}

//VOD,PMP,동영상 유/무 팝업
function fn_PayListPop(gubn, type, leccode, bridge_leccode){
	window.open('<c:url value="/lecture/payList.pop"/>?SEARCHPAYYN=' + gubn + '&SEARCHOPENPAGE=L&SEARCHPAYTYPE=' + type + '&LECCODE=' + leccode + '&BRIDGE_LECCODE=' + bridge_leccode , '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1040,height=670');
}

// VOD,PMP,동영상 클릭시 팝업
function fn_MovieDataViewPop(val1, val2, val3){
	window.open('<c:url value="/lecture/dataMovieViewList.pop"/>?LECCODE=' + val2 + '&BRIDGE_LECCODE=' + val1, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1734,height=1032');
}

function Lec_ON_OFF(Rcode,LecNM){
	var id = "#ON_OFF_"+ Rcode;
	//var dataString = $("#frm").serialize(); 
	var flag = $(id).val();
	var flag2 = "list";
	
	if(confirm("개설 여부를 변경 하시겠습니까?")){
 		$.ajax({
			type: "GET",
			url: "<c:url value='/lecture/Modify_Lecture_On_Off.do' />"+"?Rcode="+Rcode + "&flag=" + flag + "&flag2=" + flag2,
			dataType: "text",
			success: function(result){
				var state = ""
				if(result == 'ON'){
					state = '활성';
					$("#TR_"+Rcode).toggleClass('vitality');
				}else{
					state = '비활성'
					$("#TR_"+Rcode).removeClass();
				}
				alert("'" + LecNM + "'" + " 강의가 " + state + " 상태로 변경 되었습니다.");
				return;
			},error: function(){
				alert("전송 실패");
				return;
			}
		}); 
	}else{
		if(flag == 'ON'){
			document.getElementById('OFF_'+Rcode).selected = "selected";
		}else{
			document.getElementById('ON_'+Rcode).selected = "selected";
		} 
		return;
	}
}

</script>
</body>
</html>