<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%--  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/colorbox/colorbox.css"/>" /> --%>
<%-- <script type="text/javascript" src="<c:url value="/resources/libs/colorbox/jquery.colorbox.js" />"></script> --%>
<meta name="decorator" content="index">
<script type="text/javascript">
$(document).ready(function(){
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("SDATE"); 
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer');
    initDatePicker1("EDATE");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
    
//     $(".show_pop").colorbox({rel:'show_pop',closeButton:true,width:'50%',height:'50%'});

	
});
//엑셀 다운로드
function fn_excel() {
    $("#frm").attr("action", "<c:url value='/LecEventMng/LecEventExcel.do' />");
    $("#frm").submit();
}
</script>
</head>
<body>
<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" />
	<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />

	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
	<input type="hidden" id="LECCODE_ARR" name="LECCODE_ARR" value="${params.LECCODE_ARR}"/>
	<input type="hidden" id="ARM_EVENT" name="ARM_EVENT" value="${params.ARM_EVENT}"/>
	<input type="hidden" id="MESSAGE" name="MESSAGE" value=""/>
	<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="${params.EVENT_NO}"/>
	<input type="hidden" id="EVENT_USER_ID" name="EVENT_USER_ID" value=""/>
	<input type="hidden" id="USER_NAME" name="USER_NAME" value=""/>
	<input type="hidden" id="PHONE_NO" name="PHONE_NO" value=""/>
	<input type="hidden" id="SEND_NO" name="SEND_NO" value="1544-6219"/>
	<input type="hidden" id="IS_CHK" name="IS_CHK" value=""/>
	<input type="hidden" id="is_mms" name="is_mms" value=""/>
	
	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
	
    <!-- 검색 -->    
	<table class="table01">
    	<tr>          
            <th width="15%">검색</th>
            <td COLSPAN="3">
				<select name="eCode" id="eCode" onchange="goList(1)">
			 	<option value="" >-이벤트 구분-</option>
            	<c:forEach items="${eventList}" var="item" varStatus="loop">
                    <option value="${item.CODE_VAL}"  <c:if test="${params.EVENT_NO == item.CODE_VAL}"> selected</c:if>>${item.CODE_NM}</option>
				</c:forEach>
				</select>
				&nbsp;&nbsp;        
				<label for="SEARCHTYPE"></label>
				<select name="SEARCHTYPE" id="SEARCHTYPE">
				 	<option value=""  <c:if test="${params.SEARCHTYPE == ''}">selected</c:if>>-전체-</option>
                    <option value="USER_ID"  <c:if test="${params.SEARCHTYPE == 'USER_ID'}">selected</c:if>>회원ID</option>
                    <option value="USER_NAME"  <c:if test="${params.SEARCHTYPE == 'USER_NAME'}">selected</c:if>>이름</option>
                    <option value="PHONE_NO"  <c:if test="${params.SEARCHTYPE == 'PHONE_NO'}">selected</c:if>>전화번호</option>
                    <option value="IS_CHK"  <c:if test="${params.SEARCHTYPE == 'IS_CHK'}">selected</c:if>>승인여부</option>
				</select>
				<label for="SEARCHKEYWORD"></label>
				<input type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD" value="${params.SEARCHKEYWORD}" size="40" title="검색어" onkeypress="fn_Enter()">
			</td>
		    
		</tr>
		<tr>
			<th width="15%">등록일</th>
            <td>
            	<input type="text" id="SDATE" name="SDATE" value="${params.SDATE}" maxlength="8" readonly="readonly" style="width:100px;"/>
                ~
                <input type="text" id="EDATE" name="EDATE" value="${params.EDATE}" maxlength="8" readonly="readonly" style="width:100px;"/>
            </td>
            <th width="15%">화면출력건수</th>
		    <td width="30%">               
	            <input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="검색어" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/>
		        <input type="button" onclick="fn_Search()" value="검색" />
			</td>
		</tr>
	</table>
    <!-- //검색 --> 
    <ul class="boardBtns">
        <li><a href="javascript:fn_excel();">엑셀다운로드</a></li>
    </ul>
              
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
			 <c:if test="${!empty params.SEARCHCATEGORY}">
            <th width="120">
                No
            </th>
        </c:if>
            <th width="85">
                No
            </th>           
	        <th>아이디</th>
	        <th>이름</th>
	        <th>전화번호</th>
	        <c:choose>
            	<c:when test="${params.EVENT_NO eq 53}">
            		<th>군무 기관명</th>
            		<th>군별</th>
            		<th>계급</th>
            		<th>군번</th>
            		<th>가입경로</th>
            	</c:when>
            	<c:when test="${params.EVENT_NO eq 70}">
            		<th>소속</th>
            		<th>직위/직급</th>
            	</c:when>
            	<c:when test="${params.EVENT_NO eq 108}">
            		<th>구분</th>
            		<th>소속</th>
            		<th>직위/직급</th>
            	</c:when>
            	<c:otherwise>
            		<th>입력정보</th>
            	</c:otherwise>
            </c:choose>
	        <th>파일</th>
	        <th>등록일</th>	
	        <th>승인여부</th>	 
        	<th>승인확인</th>	 
        	<th>승인일(승인취소일)/승인자(승인취소자)</th>	
       		<c:if test="${params.EVENT_NO ne 108}">
         		<th>구매여부</th>	 
       			<th>구매일자</th>	 
           	</c:if> 
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
			    	 <td class="tdCenter">
                        ${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}        
		        	</td>
	                <td>${item.USER_ID}</td>
	                <td><a style="text-decoration: underline;" href="javascript:user_view('${item.USER_ID}')">${item.USER_NM}</a></td>
	                <td>${item.PHONE_NO}</td>
	                <c:choose>
	                	<c:when test="${params.EVENT_NO eq 53}">
		            		<td>${item.ARM_NM}</td>
		            		<td>${item.ARM_DIV}</td>
		            		<td>${item.ARM_RANK}</td>
		            		<td>${item.ARM_NO}</td>
		            		<td>${item.EVENT_TXT}</td>
		            	</c:when>
		            	<c:when test="${params.EVENT_NO eq 70}">
		            		<td>${item.ARM_NM}</td>
		            		<td>${item.ARM_RANK}</td>
		            	</c:when>
		            	<c:when test="${params.EVENT_NO eq 108}">
		            		<td>${item.EVENT_TXT}</td>           
		            		<td>${item.ARM_NM}</td>
		            		<td>${item.ARM_RANK}</td>
		            	</c:when>
	                	<c:otherwise>
	                		<td>${item.EVENT_TXT}</td>           
	                	</c:otherwise>
	                </c:choose>
	                <td>
	                	<c:if test="${not empty item.FILE_PATH}">
	                		<c:set var="fileNm" value="${fn:toLowerCase(item.FILE_PATH)}" />
							<c:forTokens var="token" items="${fileNm}" delims="." varStatus="status">
								<c:if test="${status.last }">
									<c:choose>
										<c:when test="${token eq 'jpg' || token eq 'gif' || token eq 'png' || token eq 'bmp' }">
											<a class="show_pop" href="javascript:showPopup('${item.FILE_PATH}','${item.USER_ID}','${item.USER_NAME}','${item.PHONE_NO}','${item.IS_CHK}')">파일</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:fn_FileDownload('${item.FILE_PATH}');">파일다운로드(pdf)</a>
										</c:otherwise>
									</c:choose>
									${filename}
								</c:if>
							</c:forTokens>
	                	</c:if>
                	</td>
	                <td>${item.REG_DD}</td>	    
	                <td>${item.IS_CHK}</td>	    
	                <td>
	                 <c:choose>
	                  <c:when test="${empty item.IS_CHK || item.IS_CHK==''}">
	                  	<a style="text-decoration: underline;" href="javascript:setChk('${item.USER_ID}','${item.USER_NAME}','${item.PHONE_NO}','Y')">승인확인</a>
	                  	/ <a style="text-decoration: underline;" href="javascript:setChk('${item.USER_ID}','${item.USER_NAME}','${item.PHONE_NO}','N')">승인취소</a>
	                  </c:when>
	                  <c:when test="${item.IS_CHK=='Y'}">
	                  	<a style="text-decoration: underline;" href="javascript:setChk('${item.USER_ID}','${item.USER_NAME}','${item.PHONE_NO}','N')">승인취소</a>
	                  </c:when>
	                    <c:when test="${item.IS_CHK=='N'}">
	                  	<a style="text-decoration: underline;" href="javascript:setChk('${item.USER_ID}','${item.USER_NAME}','${item.PHONE_NO}','Y')">승인확인</a>
	                  </c:when>
	                 </c:choose>
	                </td>	    
	                <td>
	                	<c:if test="${not empty item.IS_CHK and item.IS_CHK!=''}">
	                		<c:if test="${item.IS_CHK=='Y'}">${item.UPD_DD}/${item.UPD_NM}</c:if>
	                		<c:if test="${item.IS_CHK=='N'}">(${item.UPD_DD}/${item.UPD_NM})</c:if>
	                	</c:if>
	                </td>	   
	                <c:if test="${params.EVENT_NO ne 108}">
		                <td>${item.IS_BUY}</td>	   
		                <td>${item.BUY_DD}</td>	 
	                </c:if>                         
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="12">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	 
        </tbody>
	</table>      
    <!-- //테이블--> 
    
	<!-- paginate-->
		<c:if test="${!empty list}">
			<c:out value="${pagingHtml}" escapeXml="false" />
		</c:if>
	<!--//paginate-->
	<!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_excel();">엑셀다운로드</a></li>
    </ul>
	
</form>	
</div>
<!--//content --> 

<script type="text/javascript">
$(document).ready(function(){
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDatePicker1("searchStartDate");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDatePicker1("searchEndDate");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});
//검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/LecEventMng/reboundEventList.do' />");
	$("#frm").submit();
}

//페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/LecEventMng/reboundEventList.do' />");
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
function setChk(id,name,phoneno,is_chk){
	var message = ""; 
	if(is_chk=="Y"){
		message = "인증 완료하시겠습니까?"; 
	}else if(is_chk=="N"){
		message = "인증 취소하시겠습니까?"; 
	}
	
	if(confirm(message)){

		$("#EVENT_USER_ID").val(id);
		$("#USER_NAME").val(name);
		$("#PHONE_NO").val(phoneno);
		$("#IS_CHK").val(is_chk);

		var message = "";
		message += "[윌비스 신광은경찰]\n";
		if ($("#EVENT_NO").val() == 31) {
			if (is_chk == "Y") {
	    		message += "리바운드 회원 인증이 완료 되었습니다.\n";
	    		message+="http://bit.ly/2fA1H3n";
			}else{
	    		message += "리바운드 회원 인증 보류안내 \n";
	    		message+="자세한 사항은 고객센터로 문의 바랍니다";
			}
		}else if($("#EVENT_NO").val() == 70) {
			if (is_chk == "Y") {
				message += "경찰승진 회원 인증이 완료되었습니다.\n\n";
				message += "http://goo.gl/xaA1TW";
			}else{
	    		message += "경찰승진 회원 인증 보류안내 \n";
	    		message+="자세한 사항은 고객센터로 문의 바랍니다";
			}
		}else if($("#EVENT_NO").val() == 88) {
			if (is_chk == "Y") {
				$("#is_mms").val("Y");
				message += "환승 특별 이벤트의 타사 수강 인증이 완료 되었습니다.\n";
				message += "프리패스 신청시 쿠폰 할인 받으세요!\n";
				message += "(내강의실 쿠폰 확인, 유효기간 7일)\n\n";
				message += "☞프리패스 구매바로가기 goo.gl/1BFDgk\n\n";
				message += "윌비스 신광은 경찰팀은 여러분의 빠른 합격을 위해 최선을 다하겠습니다.";
			}else{
	    		message += "환승 특별 이벤트 인증 보류안내 \n";
	    		message+="자세한 사항은 고객센터로 문의 바랍니다";
			}
		}else if($("#EVENT_NO").val() == 107) {
			if (is_chk == "Y") {
				$("#is_mms").val("Y");
				message += "환승 특별 이벤트의 타사 수강 인증이 완료 되었습니다.\n";
				message += "프리패스 신청시 쿠폰 할인 받으세요!\n";
				message += "(내강의실 쿠폰 확인, 유효기간 7일)\n\n";
				message += "윌비스 신광은 경찰팀은 여러분의 빠른 합격을 위해 최선을 다하겠습니다.";
			}else{
				$("#is_mms").val("Y");
	    		message += "환승 특별 이벤트의 타사 수강 인증이 보류되었습니다. \n";
	    		message+="정확한 인증을 위해 자세한 사항은 고객센터로 문의 부탁드립니다.\n\n";
	    		message += "윌비스 신광은 경찰팀은 여러분의 빠른 합격을 위해 최선을 다하겠습니다.";
			}
		}else if($("#EVENT_NO").val() == 108) {
			if (is_chk == "Y") {
				$("#is_mms").val("Y");
				message += "MOU 인증이 완료되었습니다.\n";
				message += "강의 구매 시 쿠폰 할인받으세요!\n";
				message += "(내강의실 쿠폰 확인)\n\n";
				message += "윌비스 신광은 경찰팀은 여러분의 빠른 합격을 위해 최선을 다하겠습니다.";
			}else{
				$("#is_mms").val("Y");
	    		message += "MOU 인증이 보류되었습니다.\n";
	    		message+="정확한 인증을 위해 자세한 사항은 고객센터로 문의 부탁드립니다.\n\n";
	    		message += "윌비스 신광은 경찰팀은 여러분의 빠른 합격을 위해 최선을 다하겠습니다.";
			}
		}

		$("#MESSAGE").val(message);
		
		$.ajax(	{
			type: "POST", 
		    url : '<c:url value="/LecEventMng/update_rebound_chk.json"/>',
			data: $("#frm").serialize(),
		    dataType: "text",  
		    async : false,
		    success: function(RES) {
		    	if($.trim(RES)=="Y"){
		        	alert("변경하였습니다.");
		        	location.reload();
		      	}else{
		    		alert("실패하였습니다.");
		    	  	return;
		      	}
			},error: function(){
		    	alert("실패하였습니다.");
		      	return;
		    }
		});
	}
}

function showPopup(filepath,id,name,phoneno,is_chk){
	var checkparam = "EVENT_NO="+$("#EVENT_NO").val()+"&EVENT_USER_ID="+id+"&FILE_PATH="+filepath;
		checkparam+="&USER_NAME="+encodeURIComponent(name) +"&PHONE_NO="+phoneno+"&SEND_NO="+$("#SEND_NO").val()+"&IS_CHK="+is_chk+"&ARM_EVENT="+$("#ARM_EVENT").val();
	  var url = '<c:url value="/LecEventMng/rebound.pop?"/>'+checkparam ;
	  window.open(url,'event', 'top=100,scrollbars=yes,toolbar=no,resizable=yes,width=750,height=750');
}

//파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
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
</script>
</body>
</html>