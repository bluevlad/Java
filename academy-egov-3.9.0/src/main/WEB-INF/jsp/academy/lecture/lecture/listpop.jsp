<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<table class="table02">
	<tr>
        <th width="110"><!-- <input name="input" type="checkbox" value=""> -->No </th>
        <th width="80">직종</th>
        <th width="80">과목</th>
        <th width="75">강의코드</th>
        <th width="55">강사명</th>
        <th>강의명</th>
        <th width="75">학습형태</th>
        <th width="80">등록일</th>
     </tr>

	<c:forEach items="${list}" var="item" varStatus="loop">

		<tr>
		<%-- <tr id="TR_${item.BRIDGE_LECCODE}" <c:if test="${item.SUBJECT_ISUSE eq 'Y'}">class="vitality"</c:if>> --%>
		        <td class="tdCenter">${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}<input name="LECCODE_INFO_ARR" type="radio" value="${item.LECCODE}#${item.SUBJECT_TITLE}"/></td>
		        <td><a href="javascript:fn_DataView('${item.BRIDGE_LECCODE}','${item.LECCODE}');">${item.CATEGORY_NM}</a></td>
		        <td>${item.SUBJECT_NM}</td>
		        <td>${item.LECCODE}</td>
		        <td>${item.SUBJECT_TEACHER_NM}</td>
		        <!-- <td class="tdLeft"><a href="#">${item.SUBJECT_TITLE}</a></td> -->
		        <td style="text-align:left;"><%-- <a href="javascript:fn_Modify('${item.BRIDGE_LECCODE}','${item.LECCODE}');"> --%>${item.SUBJECT_TITLE}<!-- </a> --></td>
		        <td>${item.LEARNING_NM}</td>
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
			</tr>
	
	</c:forEach>
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="8">검색된 강의가 존재하지 않습니다.</td>
			</tr>
		</c:if>	 	
	</table>
<!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->

<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
	
<script type="text/javascript">

// 페이징

function goList(page){
	var gift_leccode_arr =  "";
	var gift_name_arr =  "";
	
	var gift_leccode = "";
	var gift_name = "";
	
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	var param =  "&SEARCHKIND=" + $('#SEARCHKIND').val()+"&SEARCHFORM=" + $('#SEARCHFORM').val()+"&SEARCHTYPE=" + $('#SEARCHTYPE').val()+"&currentPage=" + $('#currentPage').val()+"&SEARCHTEXT=" + encodeURI($('#SEARCHGIFT').val());

	$.ajax({
		    type : "POST"
		    ,url : '<c:url value="/lecture/listpop.pop"/>'
		    ,data :  param
		    ,success : function (data){
		     	$('#div_lecture_search').html(data);
		     	
		     	
		    	$("input[name='LECCODE_INFO_ARR']").click(function(){
			     	if($("#GIFT_LECCODE").val() != ""){
		    			gift_leccode = $("#GIFT_LECCODE").val();
		    			//alert("gift_leccode="+gift_leccode);
		    		}
		    		if($("#GIFT_NAME").val() != ""){
		    			gift_name = $("#GIFT_NAME").val();
		    		}
		    		if(gift_name != ""){
			    		gift_leccode_arr = ""+gift_leccode+", ";
			    		gift_name_arr = ""+gift_name+", ";
		    		}
		    		//alert("list");
		    		
		    		$("#GIFT_LECCODE").val(gift_leccode_arr + $("input[name='LECCODE_INFO_ARR']:checked").val().split("#")[0]);
		    		$("#GIFT_NAME").val(gift_name_arr + $("input[name='LECCODE_INFO_ARR']:checked").val().split("#")[1]);
		    		//$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
		     	});
		    	
		    }
		});	
}

</script>