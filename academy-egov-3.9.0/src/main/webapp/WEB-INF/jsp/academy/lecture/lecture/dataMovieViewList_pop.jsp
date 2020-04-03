<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
</head>
<body>
<!--content -->
<div id="content_pop" style="padding-left:10px;">

	<h2>● 강의제작 > <strong>영상정보</strong></h2>

	<c:import url="/lecture/lecview.html" >
	    <c:param name="LECCODE" value="${params.LECCODE}" />
	</c:import>
          
    <p class="pInto01">&nbsp;</p>

	<c:set var="LAST_ORDER1" value="1" />
	<c:set var="LAST_ORDER2" value="1" />
	
	<form id="frm1" name="frm1" method="post" action="">
	<input type="hidden" name="SEARCHTYPE" value="${params.SEARCHTYPE}">
	<input type="hidden" name="BRIDGE_LECCODE" value="${params.BRIDGE_LECCODE}">
	<input type="hidden" name="LECCODE" value="${params.LECCODE}">
	
	<input type="hidden" name="MOVIE_FREE_FLAG" value="" />
	<input type="hidden" name="STOP" value="" />
	<input type="hidden" name="MOVIE_DATA_FILE_YN" value="" />
	<input type="hidden" id="FILE_DEL_NO" name="FILE_DEL_NO" value="" />
	<input type="hidden" id="FILE_DEL_NAME" name="FILE_DEL_NAME" value="" />
	
	    <!-- 테이블-->
	    <table class="table02">
			<tr>
		        <th width="150">회차</th>
		        <th>동영상명</th>
		        <th>경로</th>
		        <th width="150">시간</th>
		        <th width="100">무료</th>
		        <th width="100">활성</th>
		        <th>삭제</th>
			</tr>
	        <tbody>
	        	
	        	<!-- 최상단 메모 -->
				<c:forEach items="${memolist}" var="item1" varStatus="loop1">
					<c:if test="${item1.POSITION eq 'UPUP' }">
						<tr class="vitality">
							<td width="150">메모<br/>${item1.MOVIE_ORDER1}강 ${item1.MOVIE_ORDER2}회</td>
							<td colspan="5" style="text-align:left;">
								<input type="text" name="MST_TEXT" size="100" value="${item1.MST_TEXT}" />
								<input type="hidden" name="POSITION" value="${item1.POSITION}" />
								<input type="hidden" name="MOVIE_ORDERS1" value="${item1.MOVIE_ORDER1}" />
								<input type="hidden" name="MOVIE_ORDERS2" value="${item1.MOVIE_ORDER2}" />
							</td>
							<td><input type="checkbox" name="DELMEMO_ARR" value="${item1.POSITION},${item1.MOVIE_ORDER1},${item1.MOVIE_ORDER2}" /></td>
						</tr>
					</c:if>
				</c:forEach>	        
	        	<!-- // 최상단 메모 -->
	        
		        <c:forEach items="${list}" var="item" varStatus="loop">
			        
			        <!-- 상단 메모 -->
					<c:forEach items="${memolist}" var="item1" varStatus="loop1">
						<c:if test="${item1.POSITION eq 'UP' }">
							<c:if test="${item.MOVIE_ORDER1 eq item1.MOVIE_ORDER1 and item.MOVIE_ORDER2 eq item1.MOVIE_ORDER2 }">
								<tr class="vitality">
									<td width="150">메모<br/>${item1.MOVIE_ORDER1}강 ${item1.MOVIE_ORDER2}회</td>
									<td colspan="5" style="text-align:left;">
										<input type="text" name="MST_TEXT" size="100" value="${item1.MST_TEXT}" />
										<input type="hidden" name="POSITION" value="${item1.POSITION}" />
										<input type="hidden" name="MOVIE_ORDERS1" value="${item1.MOVIE_ORDER1}" />
										<input type="hidden" name="MOVIE_ORDERS2" value="${item1.MOVIE_ORDER2}" />
									</td>
									<td><input type="checkbox" name="DELMEMO_ARR" value="${item1.POSITION},${item1.MOVIE_ORDER1},${item1.MOVIE_ORDER2}" /></td>
								</tr>
							</c:if>
						</c:if>
					</c:forEach>	  		        
		        	<!-- // 상단 메모 -->
		        	
					<tr>
				    	<td style="text-align:left;">
				    		<input type="text" name="MOVIE_ORDER1"  value="${item.MOVIE_ORDER1}" size="2"/>회 <input type="text" name="MOVIE_ORDER2"  value="${item.MOVIE_ORDER2}" size="2"/>강
				    		<br/>[${item.MOVIE_NO}]<input type="hidden" name="MOVIE_NO" value="${item.MOVIE_NO}" />
				    	</td>
						<td style="text-align:left;">
							<div>
								<strong style="color:#D77878">동영상명 :</strong> <input type="text" name="MOVIE_NAME" value="${item.MOVIE_NAME}" size="80"/>
							</div>
							<div style="padding-top:5px;'">
								<strong style="color:#D77878">설명 :</strong> <input type="text" name="MOVIE_DESC" value="${item.MOVIE_DESC}" size="80"/>
							</div>
							<div style="padding-top:5px;'" id="${loop.index}">
								<strong style="color:#D77878">자료 :</strong> <input type="checkbox" name="MOVIE_DATA_FILE_YN_ARR" value="Y" <c:if test="${item.MOVIE_DATA_FILE_YN eq 'Y' }">checked="checked"</c:if> />  <span <c:if test="${item.MOVIE_DATA_FILE_YN ne 'Y' }">style="display:none;"</c:if>><a href="javascript:fn_fileUpload('${loop.index}','${ params.BRIDGE_LECCODE}');">업로드</a></span>
								<span id="FILETEXT"  onClick="fn_FileDownload('${item.MOVIE_DATA_FILENAME}');" style="cursor:pointer;">${item.MOVIE_DATA_FILENAME}</span>
								<span <c:if test="${item.MOVIE_DATA_FILE_YN ne 'Y' }">style="display:none;"</c:if>><a href="javascript:fn_fileDelete('${item.MOVIE_NO}','${item.MOVIE_DATA_FILENAME}');">삭제</a></span>
								<input type="hidden" name="MOVIE_DATA_FILENAME" value="${item.MOVIE_DATA_FILENAME}" />
							</div>							
						</td>				    	
						<td style="text-align:left;">
							<div>
								<strong style="color:#D77878">와이드 :</strong> <input type="text" name="WIDE_URL" value="${item.WIDE_URL}" size="80"/><br/>
								<strong style="color:#D77878">파일명 :</strong> <input type="text" name="MOVIE_FILENAME4" value="${item.MOVIE_FILENAME4}" size="80"/>
								<input type="hidden" name="MOVIE_URL" value="${item.MOVIE_URL}" size="80"/>
								<input type="hidden" name="MOVIE_FILENAME1" value="${item.MOVIE_FILENAME1}" size="80"/>
							</div>
<%-- 							<div>
								<strong style="color:#D77878">500K :</strong> <input type="text" name="MOVIE_URL" value="${item.MOVIE_URL}" size="80"/><br/>
								<strong style="color:#D77878">파일명 :</strong> <input type="text" name="MOVIE_FILENAME1" value="${item.MOVIE_FILENAME1}" size="80"/>
							</div> --%>
							<div style="padding-top:5px;'">
								<strong style="color:#D77878">MP4 :</strong> <input type="text" name="MP4_URL" value="${item.MP4_URL}" size="80"/><br/>
								<strong style="color:#D77878">고화질 :</strong> <input type="text" name="MOVIE_FILENAME3" value="${item.MOVIE_FILENAME3}" size="80"/><br/>
								<strong style="color:#D77878">일반 :</strong> <input type="text" name="MOVIE_FILENAME2" value="${item.MOVIE_FILENAME2}" size="80"/>
								
							</div>
							<div style="padding-top:5px;'">
								<strong style="color:#D77878">PMP :</strong> <input type="text" name="PMP_URL" value="${item.PMP_URL}"  size="80"/><br/>
								<strong style="color:#D77878">파일명 :</strong> <input type="text" name="PMP_FILENAME" value="${item.PMP_FILENAME}" size="80"/>
							</div>
						</td>
						<td>
                            <c:set var="LIST_MOVIE_TIME" value="${item.MOVIE_TIME}" />
                            <c:set var="LIST_MOVIE_TIME_S" value="${LIST_MOVIE_TIME % 60}" />
                            <c:set var="LIST_MOVIE_TIME" value="${(LIST_MOVIE_TIME - LIST_MOVIE_TIME_S) / 60}" />
							<c:set var="LIST_MOVIE_TIME_M" value="${LIST_MOVIE_TIME % 60}" />
							<c:set var="LIST_MOVIE_TIME" value="${(LIST_MOVIE_TIME - LIST_MOVIE_TIME_M) / 60}" />
							<c:set var="LIST_MOVIE_TIME_H" value="${LIST_MOVIE_TIME % 60}" />
							<input type="text" name="MOVIE_TIME_H" value="<fmt:formatNumber value="${LIST_MOVIE_TIME_H}" type="number"/>" size="2" onKeyUp="movie_hms_onkeyup('${loop.index}', '${item.MOVIE_NO}','${fn:length(list) }');" /><strong style="color:#D77878">시</strong> 
							<input type="text" name="MOVIE_TIME_M" value="<fmt:formatNumber value="${LIST_MOVIE_TIME_M}" type="number"/>" size="2" onKeyUp="movie_hms_onkeyup('${loop.index}', '${item.MOVIE_NO}', '${fn:length(list) }');"/><strong style="color:#D77878">분</strong>
							<input type="hidden" name="MOVIE_TIME_S" value="<fmt:formatNumber value="${LIST_MOVIE_TIME_S}" type="number"/>" size="2" onKeyUp="movie_hms_onkeyup('${loop.index}', '${item.MOVIE_NO}', '${fn:length(list) }');"/>
							<input type="hidden" name="MOVIE_TIME" value="${item.MOVIE_TIME}" />
						</td>
						<td>
							<input type="checkbox" name="MOVIE_FREE_FLAG_ARR" value="Y" <c:if test="${item.MOVIE_FREE_FLAG eq 'Y' }">checked="checked"</c:if>/>
						</td>
						<td>
							<input type="checkbox" name="STOP_ARR"  value="N" <c:if test="${item.STOP eq 'N' }">checked="checked"</c:if>/>
						</td>
						<td>
							<input type="checkbox" name="DEL_ARR" value="${item.MOVIE_NO},${params.BRIDGE_LECCODE},${item.MOVIE_ORDER1},${item.MOVIE_ORDER2}" />
						</td>
					</tr>
					
					<!-- 하단 메모 -->
					<c:forEach items="${memolist}" var="item1" varStatus="loop1">
						<c:if test="${item1.POSITION eq 'DOWN' }">
							<c:if test="${item.MOVIE_ORDER1 eq item1.MOVIE_ORDER1 and item.MOVIE_ORDER2 eq item1.MOVIE_ORDER2 }">
								<tr class="vitality">
									<td width="150">메모<br/>${item1.MOVIE_ORDER1}강 ${item1.MOVIE_ORDER2}회</td>
									<td colspan="5" style="text-align:left;">
										<input type="text" name="MST_TEXT" size="100" value="${item1.MST_TEXT}" />
										<input type="hidden" name="POSITION" value="${item1.POSITION}" />
										<input type="hidden" name="MOVIE_ORDERS1" value="${item1.MOVIE_ORDER1}" />
										<input type="hidden" name="MOVIE_ORDERS2" value="${item1.MOVIE_ORDER2}" />
									</td>
									<td><input type="checkbox" name="DELMEMO_ARR" value="${item1.POSITION},${item1.MOVIE_ORDER1},${item1.MOVIE_ORDER2}" /></td>
								</tr>
							</c:if>
						</c:if>
					</c:forEach>						
					<!-- // 하단 메모 -->
					
					<c:if test="${fn:length(list)-1 eq loop.index }">
						<c:set var="LAST_ORDER1" value="${item.MOVIE_ORDER1}" />
						<c:set var="LAST_ORDER2" value="${item.MOVIE_ORDER2}" />					
					</c:if>
					
				</c:forEach>
				<c:if test="${empty list}">
					<tr bgColor=#ffffff align=center> 
						<td colspan="7">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>	 
	        </tbody>
		</table>      
	    <!-- //테이블-->
	    <!--버튼-->
	    <c:if test="${!empty list}">       		
			<ul class="boardBtns">
				<li><input type="radio" name="MOV_ING" value="C" <c:if test="${view[0].MOV_ING eq 'C' }">checked="checked"</c:if> />업데이트 예정
       				<input type="radio" name="MOV_ING" value="I" <c:if test="${view[0].MOV_ING eq 'I' }">checked="checked"</c:if> /><span class="txt03">업데이트중</span>
       				<input type="radio" name="MOV_ING" value="E" <c:if test="${view[0].MOV_ING eq 'E' }">checked="checked"</c:if> /><span class="txt04">업데이트 완료</span></li>
                <li><a href="javascript:fn_Update();">수정</a></li>
			    <li><a href="javascript:fn_Delete();">삭제</a></li>
			</ul>
		</c:if>
	    <!--//버튼--> 	
    </form>

	<p class="pInto01">
		<strong><font color="red">[ 참조 ]</font></strong><br/>
		<strong>※ <font color="red">500K</font> 동영상의  파일명을 입력하여  추가시<font color="red"> MP4</font> 동영상의  파일명은  <font color="red">500K</font> 
		동영상의 파일명과 동일하게 확장자만 <font color="blue">"MP4"</font>로 바뀌어  자동  추가 됩니다</strong><br/>
		<strong>※ 동영상 회차 추가시 회차의  기본값은 <font color="red">비활성</font>으로 저장됩니다.</strong>	
	</p>

	<form id="frm2" name="frm2" method="post" action="">
	<input type="hidden" name="SEARCHTYPE" value="${params.SEARCHTYPE}">
	<input type="hidden" name="BRIDGE_LECCODE" value="${params.BRIDGE_LECCODE}">
	<input type="hidden" name="LECCODE" value="${params.LECCODE}">
	<input type="hidden" name="ADD_MOVIE_ORDER1" value="">
	<input type="hidden" name="ADD_MOVIE_ORDER2" value="">
		
	    <!-- 테이블-->
	    <table class="table02">
			<tr>
		        <th width="150">회차</th>
		        <th>동영상명</th>
		        <th>경로</th>
		        <th width="190">시간</th>
		        <th width="100">무료여부</th>
			</tr>
	        <tbody>
				<tr>
			    	<td style="text-align:left;">
						<c:if test="${empty list}">
			    			<input type="radio" name="ADD_CHOICE" value="1" checked="checked" /> <input type="text" name="" value="1" size="2"/>회 <input type="text" name="" value="1" size="2"/>강
			    			<input type="radio" name="ADD_CHOICE" value="2" /> <input type="text" name="" value="2" size="2"/>회 <input type="text" name="" value="1" size="2"/>강					
						</c:if>	 			    	
			    		<c:if test="${!empty list}">
			    			<input type="radio" name="ADD_CHOICE" value="1" checked="checked" /> <input type="text" name="" value="${LAST_ORDER1}" size="2"/>회 <input type="text" name="" value="${LAST_ORDER2+1}" size="2"/>강
			    			<input type="radio" name="ADD_CHOICE" value="2" /> <input type="text" name="" value="${LAST_ORDER1+1}" size="2"/>회 <input type="text" name="" value="1" size="2"/>강
			    		</c:if>
			    	</td>
					<td style="text-align:left;">
						<div>
							동영상명 : <input type="text" id="ADD_MOVIE_NAME" name="ADD_MOVIE_NAME" value="" size="80"/>
						</div>
						<div style="padding-top:5px;'">
							설명 : <input type="text" id="ADD_MOVIE_DESC" name="ADD_MOVIE_DESC" value="" size="80"/>
						</div>
					</td>
					<td style="text-align:left;">
						<div>
							와이드: <input type="text" id="ADD_WIDE_URL" name="ADD_WIDE_URL" size="80" value="${view[0].SUBJECT_WIDE_DEFAULT_PATH}" /><br/>
							파일명 : <input type="text" id="ADD_MOVIE_FILENAME4" name="ADD_MOVIE_FILENAME4" value="" size="80"/>
						</div>
<%-- 				<div>
							500K : <input type="text" id="ADD_MOVIE_URL" name="ADD_MOVIE_URL" size="80" value="${view[0].SUBJECT_VOD_DEFAULT_PATH}" /><br/>
							파일명 : <input type="text" id="ADD_MOVIE_FILENAME1" name="ADD_MOVIE_FILENAME1" value="" size="80"/>
						</div> --%>
						<div style="padding-top:5px;'">
							MP4 : <input type="text" id="ADD_MP4_URL" name="ADD_MP4_URL" size="80" value="${view[0].SUBJECT_MP4_DEFAULT_PATH}" /><br/>
							고화질 : <input type="text" id="ADD_MOVIE_FILENAME3" name="ADD_MOVIE_FILENAME3" value="" size="80"/><br/>
							일반 : <input type="text" id="ADD_MOVIE_FILENAME2" name="ADD_MOVIE_FILENAME2" value="" size="80"/>
							
						</div>
						<div style="padding-top:5px;'">
							PMP : <input type="text" id="ADD_PMP_URL" name="ADD_PMP_URL" size="80" value="${view[0].SUBJECT_PMP_DEFAULT_PATH}" /><br/>
							파일명 : <input type="text" id="ADD_PMP_FILENAME" name="ADD_PMP_FILENAME" value="" size="80"/>
						</div>
					</td>
					<td style="text-align:left;">
						<div>
						<input type="text" id="ADD_TIME_H" name="ADD_TIME_H" value="0" size="2" onKeyUp="movie_hms_onkeyup2();" />시 
						<input type="text" id="ADD_TIME_M" name="ADD_TIME_M" value="0" size="2" onKeyUp="movie_hms_onkeyup2();" />분 
						<input type="text" id="ADD_TIME_S" name="ADD_TIME_S" value="0" size="2" onKeyUp="movie_hms_onkeyup2();" />초
						</div>
						<div style="paddint-top:10px;text-align:center;">
						= <input type="text" id="ADD_MOVIE_TIME" name="ADD_MOVIE_TIME" value="0" size="2" onKeyUp="movie_time_onkeyup2();" />
						</div>
					</td>
					<td>
						<input type="checkbox" id="ADD_MOVIE_FREE_FLAG" name="ADD_MOVIE_FREE_FLAG" value="Y" />
					</td>
				</tr>	 
	        </tbody>
		</table>      
	    <!-- //테이블-->
	    <!--버튼-->
		<ul class="boardBtns">
			<li>갯수 : <input type="text" id="ADD_LOW" name="ADD_LOW" value="1" size="2" /></li>
		    <li><a href="javascript:fn_AddLow();">추가</a></li>
		</ul>
	    <!--//버튼--> 		
	</form>

	<p class="pInto01">&nbsp;</p>
	
	<form id="frm3" name="frm3" method="post" action="">
	<input type="hidden" name="SEARCHTYPE" value="${params.SEARCHTYPE}">
	<input type="hidden" name="BRIDGE_LECCODE" value="${params.BRIDGE_LECCODE}">
	<input type="hidden" name="LECCODE" value="${params.LECCODE}">
		
	    <!-- 테이블-->
	    <table class="table02">
			<tr>
		        <th style="text-align:left;">메모기능</th>
			</tr>
	        <tbody>
				<tr>
				   	<td style="text-align:left;">
						<input type="radio" name="POSITION" value="UPUP" checked="checked"/> 최상단<br/>
						<input type="radio" name="POSITION" value="UP" /> 상단<br/>
						<input type="radio" name="POSITION" value="DOWN" /> 하단<br/>
						<input type="text" name="MOVIE_ORDER1"  value="" size="2"/>회 <input type="text" name="MOVIE_ORDER2"  value=""  size="2"/>강  
						<input type="text" name="MST_TEXT"  value="" size="60"/>
				   	</td>
				</tr>	 
	        </tbody>
		</table>      
	    <!-- //테이블-->	
	    <!--버튼-->
		<ul class="boardBtns">
			<li><a href="javascript:fn_MemoAdd();">추가</a></li>
		</ul>
	    <!--//버튼-->	
	</form>	
</div>
<!--//content -->
<script type="text/javascript">
$(document).ready(function(){
	$("input[name='MOVIE_DATA_FILE_YN_ARR']").click(function(){
		if(this.checked){
			$(this).next().css("display","block");
		}else{
			$(this).next().css("display","none");
		}
	});
});

// 파일업로드 팝업
function fn_fileUpload(idx, bridgeLeccode){
	window.open('<c:url value="/lecture/dataMovieFile.pop"/>?LOWNUM=' + idx + '&BLECCODE=' + bridgeLeccode, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=520,height=200');
}

// 파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
}

// 파일삭제
function fn_fileDelete(no,fnm){
	if(confirm("파일을 삭제하시겠습니까?")){
		$("#FILE_DEL_NO").val(no);
		$("#FILE_DEL_NAME").val(fnm);
		$("#frm1").attr("action", "<c:url value='/lecture/dataMovieFileDelete.pop'/>");
		$("#frm1").submit();
	}
}

// 로우추가
function fn_AddLow(){
	if($.trim($("#frm2").find("input[name='ADD_CHOICE']:checked").next().val()) == ""){
		alert("회차를 입력해주세요");
		$("#frm2").find("input[name='ADD_CHOICE']:checked").next().focus();
		return;
	}
	if($.trim($("#frm2").find("input[name='ADD_CHOICE']:checked").next().next().val()) == ""){
		alert("회차를 입력해주세요");
		$("#frm2").find("input[name='ADD_CHOICE']:checked").next().next().focus();
		return;
	}
//	if($.trim($("#frm2").find("input[name='ADD_MOVIE_NAME']").val()) == ""){
//		alert("동영상명을 입력해주세요");
//		$("#frm2").find("input[name='ADD_MOVIE_NAME']").focus();
//		return;
//	}
	
	if(confirm("추가하시겠습니까?")){	
		$("#frm2").find("input[name='ADD_MOVIE_ORDER1']").val($("#frm2").find("input[name='ADD_CHOICE']:checked").next().val());  
		$("#frm2").find("input[name='ADD_MOVIE_ORDER2']").val($("#frm2").find("input[name='ADD_CHOICE']:checked").next().next().val());
		
		$("#frm2").attr("action", "<c:url value='/lecture/dataMovieSave.pop'/>");
		$("#frm2").submit();	
	}
}

//로우추가
function fn_MemoAdd(){	
	var position = $("#frm3").find("input[name='POSITION']:checked").val();
	var movie_order1 = $("#frm3").find("input[name='MOVIE_ORDER1']").val();
	var movie_order2 = $("#frm3").find("input[name='MOVIE_ORDER2']").val();
	var tCheckMsg = "Y";
	var CheckMsg = "Y";
	
	if(movie_order1 == ""){
		alert("회차를 입력해주세요");
		$("#frm3").find("input[name='MOVIE_ORDER1']").focus();
		return;
	}
	if(movie_order2 == ""){
		alert("회차를 입력해주세요");
		$("#frm3").find("input[name='MOVIE_ORDER2']").focus();
		return;
	}
	if($("#frm3").find("input[name='MST_TEXT']").val() == ""){
		alert("메모내용을 입력해주세요");
		$("#frm3").find("input[name='MST_TEXT']").focus();
		return;
	}
	
	<c:forEach items="${memolist}" var="item1" varStatus="loop1">
		<c:if test="${item1.POSITION eq 'UPUP' }">
			if(position == "UPUP"){	
				tCheckMsg = "UPUP";
			}
		</c:if>
		<c:if test="${item1.POSITION ne 'UPUP' }">
			if(position == "${item1.POSITION}" && movie_order1 == "${item1.MOVIE_ORDER1}" && movie_order2 == "${item1.MOVIE_ORDER2}"){
				CheckMsg = "UPDOWN";				
			}
		</c:if>	
	</c:forEach>
	
	if(tCheckMsg != "Y" || CheckMsg != "Y"){
		if(position == "UPUP"){
			alert("최상단 메모는 한개만 등록가능합니다");
			return;
		}else{
			if(position == "UP"){
				alert(movie_order1 + "회 " + movie_order2 + "강 상단메모는 이미 등록되었습니다");
				return;				
			}
			if(position == "DOWN"){
				alert(movie_order1 + "회 " + movie_order2 + "강 하단메모는 이미 등록되었습니다");
				return;								
			}			
		}		
	}else{
		if(confirm("메모를 추가하시겠습니까?")){
			$("#frm3").attr("action", "<c:url value='/lecture/dataMovieMemoSave.pop'/>");
			$("#frm3").submit();
		}		
	}
}

function movie_hms_onkeyup(ii, no, len){
	if(len > 1){
        var hh = parseInt(document.frm1.MOVIE_TIME_H[ii].value);
        var mm = parseInt(document.frm1.MOVIE_TIME_M[ii].value);
        var ss = parseInt(document.frm1.MOVIE_TIME_S[ii].value);
        document.frm1.MOVIE_TIME[ii].value = (hh * 3600) + (mm * 60) + ss;
        if (document.frm1.MOVIE_TIME_M[ii].value == "")
        return;
        if (document.frm1.MOVIE_TIME_H[ii].value == "")
        return;
	}else{
		 var hh = parseInt(document.frm1.MOVIE_TIME_H.value);
	     var mm = parseInt(document.frm1.MOVIE_TIME_M.value);
	     var ss = parseInt(document.frm1.MOVIE_TIME_S.value);
	     document.frm1.MOVIE_TIME.value = (hh * 3600) + (mm * 60) + ss;
	     if (document.frm1.MOVIE_TIME_M.value == "")
	     return;
	     if (document.frm1.MOVIE_TIME_H.value == "")
	     return;
	}

    movie_time_onkeyup(ii, no, len);
}

function movie_time_onkeyup(ii, no, len){
	if(len > 1){
		var movie_time = parseInt(document.frm1.MOVIE_TIME[ii].value); // 전체 초
		document.frm1.MOVIE_TIME_S[ii].value = parseInt(movie_time % 60); // 초
		movie_time = parseInt((movie_time - document.frm1.MOVIE_TIME_S[ii].value) / 60); // 전체 분
		document.frm1.MOVIE_TIME_M[ii].value = parseInt(movie_time % 60); // 분
		movie_time = parseInt((movie_time - document.frm1.MOVIE_TIME_M[ii].value) / 60); // 전체 시간
		document.frm1.MOVIE_TIME_H[ii].value = parseInt(movie_time % 60); // 시간  
	}else{
		var movie_time = parseInt(document.frm1.MOVIE_TIME.value); // 전체 초
		document.frm1.MOVIE_TIME_S.value = parseInt(movie_time % 60); // 초
		movie_time = parseInt((movie_time - document.frm1.MOVIE_TIME_S.value) / 60); // 전체 분
		document.frm1.MOVIE_TIME_M.value = parseInt(movie_time % 60); // 분
		movie_time = parseInt((movie_time - document.frm1.MOVIE_TIME_M.value) / 60); // 전체 시간
		document.frm1.MOVIE_TIME_H.value = parseInt(movie_time % 60); // 시간  
	}
	      
}

function movie_time_onkeyup2(){
    var movie_time = parseInt(document.frm2.ADD_MOVIE_TIME.value); // 전체 초
    document.frm2.ADD_TIME_S.value = parseInt(movie_time % 60); // 초
    movie_time = parseInt((movie_time - document.frm2.ADD_TIME_S.value) / 60); // 전체 분
    document.frm2.ADD_TIME_M.value = parseInt(movie_time % 60); // 분
    movie_time = parseInt((movie_time - document.frm2.ADD_TIME_M.value) / 60); // 전체 시간
    document.frm2.ADD_TIME_H.value = parseInt(movie_time % 60); // 시간
}
function movie_hms_onkeyup2(){
    var hh = parseInt(document.frm2.ADD_TIME_H.value);
    var mm = parseInt(document.frm2.ADD_TIME_M.value);
    var ss = parseInt(document.frm2.ADD_TIME_S.value);
    if(document.frm2.ADD_TIME_H.value ==''){
    	hh = 0;
    }
    if(document.frm2.ADD_TIME_M.value ==''){
    	mm = 0;
    }
    if(document.frm2.ADD_TIME_S.value ==''){
    	ss = 0;
    } 
    document.frm2.ADD_MOVIE_TIME.value = (hh * 3600) + (mm * 60) + ss;
    movie_time_onkeyup2();
}

// 삭제
function fn_Delete(){
	if($("#frm1 [name='DEL_ARR']:checked").length < 1 && $("#frm1 [name='DELMEMO_ARR']:checked").length < 1){
		alert("선택한 항목이 없습니다");
		return;
	}
	
	if(confirm("정말 삭제하시겠습니까?")){
		$("#frm1").attr("action", "<c:url value='/lecture/dataMovieDelete.pop'/>");
		$("#frm1").submit();	
	}
}

// 수정
function fn_Update(){
	var valck = "Y";
	$("#frm1 [name='MOVIE_ORDER1']").each(function(){
		if($.trim($(this).val())==""){
			alert("회차를 입력해주세요");
			$(this).focus();
			valck = "N";
		}
	});	
	$("#frm1 [name='MOVIE_ORDER2']").each(function(){
		if($.trim($(this).val())==""){
			alert("회차를 입력해주세요");
			$(this).focus();
			valck = "N";
		}
	});	
	$("#frm1 [name='MOVIE_NAME']").each(function(){
		if($.trim($(this).val())==""){
			alert("동영상명을 입력해주세요");
			$(this).focus();
			valck = "N";
		}
	});		
	$("#frm1 [name='MOVIE_TIME_H']").each(function(){
		if($.trim($(this).val())==""){
			alert("시간을 입력해주세요");
			$(this).focus();
			valck = "N";
		}
	});		
	$("#frm1 [name='MOVIE_TIME_M']").each(function(){
		if($.trim($(this).val())==""){
			alert("시간을 입력해주세요");
			$(this).focus();
			valck = "N";
		}
	});			
	$("#frm1 [name='MST_TEXT']").each(function(){
		if($.trim($(this).val())==""){
			alert("메모내용을 입력해주세요");
			$(this).focus();
			valck = "N";
		}
	});			
	
	if(valck == "Y"){
		var mvv = "";
		var stv = "";
		var fvv = "";
		$("input:checkbox[name='MOVIE_FREE_FLAG_ARR']").each(function(){
			if(mvv != "")
				mvv += ",";
			if(this.checked)
				mvv += "Y";
			else
				mvv += "N";
		});
		$("input:checkbox[name='STOP_ARR']").each(function(){
			if(stv != "")
				stv += ",";
			if(this.checked)
				stv += "N";
			else
				stv += "Y";
		});
		$("input:checkbox[name='MOVIE_DATA_FILE_YN_ARR']").each(function(){
			if(fvv != "")
				fvv += ",";
			if(this.checked)
				fvv += "Y";
			else
				fvv += "N";
		});
		$("input[name='MOVIE_FREE_FLAG']").val(mvv);
		$("input[name='STOP']").val(stv);
		$("input[name='MOVIE_DATA_FILE_YN']").val(fvv);
		if(confirm("수정하시겠습니까?")){
			$("#frm1").attr("action", "<c:url value='/lecture/dataMovieUpdate.pop'/>");
			$("#frm1").submit();
		}
	}
}
</script>
</body>
</html>