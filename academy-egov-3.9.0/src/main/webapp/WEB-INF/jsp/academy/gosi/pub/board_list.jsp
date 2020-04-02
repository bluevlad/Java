<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
    $('#S_MAIN_PID').on('change', function(){
	   	SelectMain();
    });
});

function SelectMain(){
	if ($("select[name=S_MAIN_PID]").val() == ""){
	     $('#S_SUB_PID').find('option').remove();
	     $('#S_SUB_PID').find('option').end().append("<option value=''>-하위분류를 선택하세요-</option>").val("");
	     return;
	} else {
		var params = '?MAIN_PID=' + $("select[name=S_MAIN_PID]").val();
		$.ajax({
		type: "GET",
		url : '<c:url value="/pub/getPubCate.json"/>'+params,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "json",
		async : false,
		success: function(RES) {
	 		if(RES.length  > 0){
		    	$('#S_SUB_PID').find('option').remove();
		     	$('#S_SUB_PID').find('option').end().append("<option value=''>-하위분류를 선택하세요-</option>").val("");
		     	var sel_idx = 0;
		     	$.each(RES,function(idx, data){
		        var list = "";
		        if( data.P_IDX == '${params.S_SUB_PID}') {
		        	sel_idx = (idx+1);
		        }
		        list +="<option value='" + data.P_IDX +"' >" + data.NM + "</option>";
		        $('#S_SUB_PID')
		        	.find('option')
		            .end()
		            .append(list)
		            .val(data.P_IDX);
		     	});
				$('#S_SUB_PID').prop('selectedIndex',sel_idx);
			}else {
		    	$('#S_SUB_PID').find('option').remove();
		    	$('#S_SUB_PID').find('option').end().append("<option value=''>-하위분류를 선택하세요-</option>").val("");
		    	return;
	 		}
		},error: function(){
	 	alert("검색 실패");
	 	return;
		}});
	}
}

//등록폼
function fn_Reg(){
	$("#frm").attr("action", "<c:url value='/pub/pub_board_add.do'/>");
	$("#frm").submit();	
}

//수정폼
function fn_Modify(val){
	$("#PUB_NO").val(val);
	$("#frm").attr("action", "<c:url value='/pub/pub_board_modify.do' />");
	$("#frm").submit();
}

//페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/pub/pub_board_list.do' />");
	$("#frm").submit();
}

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/pub/pub_board_list.do' />");
	$("#frm").submit();
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
</head>
<body>
<!--content -->
<div id="content">
	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
    
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="PUB_NO" name="PUB_NO" value=""/>
    <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">검색</th>
            <td>         
            	<input name="SEARCH_YEAR" type="text" value="${params.SEARCH_YEAR }" class="inputbox" style="width:62px;" />년
            	&nbsp;
            	 
            	<label for="SEARCH_GUBUN"></label>
				<select name="SEARCH_GUBUN" id="SEARCH_GUBUN">
					<option value="">-구분-</option>
					<c:forEach items="${pub_gubun}" var="item" varStatus="loop">
						<option value="${item.CODE_VAL}" <c:if test="${params.SEARCH_GUBUN== item.CODE_VAL }">selected="selected"</c:if>>${item.CODE_NM}</option>					
					</c:forEach>
				</select>
				 &nbsp;
				          	
				<label for="S_MAIN_PID"></label>
				<select name="S_MAIN_PID" id="S_MAIN_PID">
					<option value="">-상위분류를 선택하세요-</option>
					<c:forEach items="${pub_category}" var="item" varStatus="loop">
						<c:if test="${item.P_IDX eq '1' }" >
							<option value="${item.IDX}" <c:if test="${params.S_MAIN_PID== item.IDX }">selected="selected"</c:if>>${item.NM}</option>
						</c:if>										
					</c:forEach>
				</select>
				&nbsp;
				<label for="S_SUB_PID"></label>
				<select name="S_SUB_PID" id="S_SUB_PID">
					<option value="">-하위분류를 선택하세요-</option>					
				</select>
				
				&nbsp;
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="30" title="검색어" onkeypress="fn_Enter()">
				<input type="button" onclick="fn_Search()" value="검색" />
			</td>
		    
		</tr>
	</table>
    <!-- //검색 -->
    
    <!--버튼-->
	<ul class="boardBtns">
		<li><a href="javascript:fn_Reg();">등록</a></li>
	</ul>
    <!--//버튼--> 
        
    <p class="pInto01">&nbsp;<span>총${pub_list_count}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="10%">번호</th>
	        <th width="10%">년도</th>
	        <th width="15%">상위구분</th>
	        <th width="10%">하위구분</th>
	        <th width="*">제목</th>
	        <th width="15%">구분</th>
		</tr>
        <tbody>
	        <c:forEach items="${pub_list}" var="item" varStatus="loop">
				<tr>
			    	<td>${pub_list_count-((params.currentPage-1)*params.pageRow)-loop.index}</td>
					<td>${item.PUB_YEAR }</td>
					<td>${item.MAIN_NM }</td>
					<td>${item.SUB_NM }</td>
					<td style="text-align:left;"><a href="javascript:fn_Modify('${item.PUB_NO}');">${item.PUB_TITLE }</a></td>
					<td>${item.CODE_NM }</td>
				</tr>
			</c:forEach>
			<c:if test="${empty pub_list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="9">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	 
        </tbody>
	</table>      
    <!-- //테이블--> 
   
	<!-- paginate-->
	<c:if test="${!empty pub_list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->	
</form>	
</div>
<!--//content --> 
</body>
</html>