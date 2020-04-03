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
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="ADDAREA" name="ADDAREA" value="${params.ADDAREA}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">

	<h2>● 강의제작 > <strong>단과불러오기</strong></h2>
 
     <!-- 검색 -->    
	<table class="table01">
    	<tr>
            <th width="15%">검색</th>
            <td>            
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
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
				<input type="button" onclick="fn_Search()" value="검색" />
			</td>
		</tr>
	</table>
    <!-- //검색 --> 
          
    <p class="pInto01">&nbsp;</p>
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="85"><input type="checkbox" name="allCheck" id="allCheck" class="i_check" value="" onclick="fn_CheckAll('allCheck', 'ADD_ARR')" />항목</th>
	        <th>직종</th>
	        <th>학습형태</th>
	        <th>강의명</th>
	        <th>강사</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
			    	<td>
			    		<input type="checkbox" name="ADD_ARR" class="i_check" value="${item.LECCODE}" /> ${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}
			    		<input type="hidden" name="LECCODE_ARR" value="${item.LECCODE}"/> 
			    		<input type="hidden" name="CATEGORY_NM_ARR" value="${item.CATEGORY_NM}"/>
			    		<input type="hidden" name="LEARNING_NM_ARR" value="${item.LEARNING_NM}"/>
			    		<input type="hidden" name="SUBJECT_TITLE_ARR" value="${item.SUBJECT_TITLE}"/>
			    		<input type="hidden" name="SUBJECT_TEACHER_NM_ARR" value="${item.SUBJECT_TEACHER_NM}"/>
			        </td>
					<td>${item.CATEGORY_NM}</td>
					<td>${item.LEARNING_NM}</td>
					<td>${item.SUBJECT_TITLE}</td>
					<td>${item.SUBJECT_TEACHER_NM}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="5">데이터가 존재하지 않습니다.</td>
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
		<li><a href="javascript:fn_Add();">추가</a></li>
	    <li><a href="javascript:self.close();">닫기</a></li>
	</ul>
    <!--//버튼--> 	
	
</form>	
</div>
<!--//content --> 

<script type="text/javascript">
// 페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/lectureOff/subjectList.pop' />");
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

// 추가
function fn_Add(){
	if($("input[name='ADD_ARR']:checked").length < 1){
		alert("선택한 항목이 없습니다");
		return;
	}else{
		var appendStr = "";
		var inputLeccode = "MST_LECCODE";
		if("${params.ADDAREA}" == "ADDAREA2"){
			inputLeccode = "MST_LECCODE2";
		}
		var maxSort = 0;
		if("N" == $.trim("${params.LEC_TYPE_CHOICE}")){
			$(opener.document).find("#"+ "${params.ADDAREA}").find("input[name='" + inputLeccode + "_SORT']").each(function(idx,el){
				if(maxSort < $(this).val()){
					maxSort = $(this).val();
				}
			});
		}
		
		$("input[name='ADD_ARR']:checked").each(function(idx,el){
			var checkVal = "Y";
			var curVal = $(this).next().val();		
			$(opener.document).find("#"+ "${params.ADDAREA}").find("input[name='" + inputLeccode + "']").each(function(idx2,el2){
				if($(this).val() == curVal)
					checkVal = "N";
			});
			if(checkVal == "Y"){
				appendStr += "<tr>";				
				appendStr += "<td>"+ $(this).next().next().val() + "</td>";
				appendStr += "<td>"+ $(this).next().next().next().val() + "</td>";
				appendStr += "<td>"+ $(this).next().next().next().next().val() + "</td>";				
				if("N" == $.trim("${params.LEC_TYPE_CHOICE}")){
					maxSort = parseInt(maxSort)+1;
					appendStr += "<td><input type=\"text\" name=\"" + inputLeccode + "_SORT\" size=\"8\" value=\"" + maxSort + "\" style=\"background:#FFECEC;\"/></td>";
				}
				appendStr += "<td>"+ $(this).next().next().next().next().next().val() + "</td>";
				appendStr += "<td><input name=\"BTN_BOOKDEL\" type=\"button\" value=\"삭제\"/>";
				appendStr += "<input type=\"hidden\" name=\"" + inputLeccode + "\" value=\"" + $(this).next().val() + "\" /></td>";
				appendStr += "</tr>";
			}
		});
		$(opener.document).find("#"+ "${params.ADDAREA}").find(".basic_space").remove();
		$(opener.document).find("#"+ "${params.ADDAREA}").append(appendStr);
	}
}

//검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/lectureOff/subjectList.pop' />");
	$("#frm").submit();
}

//엔터키 검색
function fn_Enter(){
	$("#SEARCHTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			fn_Search();
	});
}
</script>
</body>
</html>