<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/colorbox/colorbox.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/libs/colorbox/jquery.colorbox.js" />"></script>
<meta name="decorator" content="index">
<script type="text/javascript">
$(document).ready(function(){
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("SDATE"); 
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer');
    initDatePicker1("EDATE");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
    
    $(".show_pop").colorbox({rel:'show_pop',closeButton:true,width:'50%',height:'50%'});

	
});
//엑셀 다운로드
function fn_excel_down() {
    $("#frm").attr("action", "<c:url value='/gosi/gosi_event_excel.do' />");
    $("#frm").submit();
}
// function show_pop(path){
// 	var url = '<c:url value="/gosi/event1.pop"/>?FILE_PATH='+path;
//     window.open(url,'event1_pop', 'scrollbars=no,toolbar=no,resizable=no'); 
// }
</script>
</head>
<body>
<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="GOSI_CD" name="GOSI_CD" value="${params.GOSI_CD}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
	<h2>● 경찰 고시 > <strong> 수험표 인증</strong></h2>
	
    <!-- 검색 -->    
	<table class="table01">
    	<tr>          
            <th width="15%">검색</th>
            <td COLSPAN="3">            
				<select name="searchEventNo" id="searchEventNo">
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
                    <option value="USER_NM"  <c:if test="${params.SEARCHTYPE == 'USER_NM'}">selected</c:if>>이름</option>
                     <option value="RST_NO"  <c:if test="${params.SEARCHTYPE == 'RST_NO'}">selected</c:if>>응시번호</option>
				</select>
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
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
              
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
			 <c:if test="${!empty params.SEARCHCATEGORY}">
            <th width="120">
                No
            </th>
        </c:if>
        <c:if test="${empty params.SEARCHCATEGORY}">
            <th width="85">
                <input type="checkbox" name="allCheck" id="allCheck" value="" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" /> No
            </th>           
        </c:if>
	        <th>아이디</th>
	        <th>이름</th>
	        <th>응시직렬</th>
	        <th>응시번호</th>
	        <th>파일</th>
	        <th>등록일</th>	        
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
			    	 <td class="tdCenter">
					<input type="checkbox" name="DEL_ARR" value="${loop.index+1}" />
		        		<c:if test="${empty params.SEARCHCATEGORY}">
                        ${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}        
                    </c:if>  
		        	</td>
	                <td>${item.USER_ID}</td>
	                <td>${item.USER_NM}</td>
	                <td>${item.ARM_NM}</td>
	                <td>${item.ARM_NO}</td>                
	                <td>
	                	<c:if test="${not empty item.FILE_PATH}">
	                		<c:set var="fileNm" value="${fn:toLowerCase(item.FILE_PATH)}" />
							<c:forTokens var="token" items="${fileNm}" delims="." varStatus="status">
								<c:if test="${status.last }">
									<c:choose>
										<c:when test="${token eq 'jpg' || token eq 'gif' || token eq 'png' || token eq 'bmp' }">
											<a class="show_pop" href="/imgFileView.do?path=/${item.FILE_PATH}">파일</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:fn_FileDownload('${item.FILE_PATH}');">파일다운로드(pdf)</a>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forTokens>
	                	</c:if>
	                </td>
	                <td>${item.REG_DT}</td>	                           
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
        <li><a href="javascript:fn_excel_down();">엑셀다운로드</a></li>
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
	$("#frm").attr("action", "<c:url value='/gosi/Gosi_event_list.do' />");
	$("#frm").submit();
}

//페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/gosi/Gosi_event_list.do' />");
	$("#frm").submit();
}

//파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
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

</script>
</body>
</html>