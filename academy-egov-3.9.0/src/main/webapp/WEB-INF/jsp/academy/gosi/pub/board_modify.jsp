<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/libs/cheditor/cheditor.js" /></script>
<script type="text/javascript">
var editor = null;
$(document).ready(function(){
	editor = new cheditor();              // 에디터 개체를 생성합니다.
	editor.config.editorHeight = '250px';     // 에디터 세로폭입니다.
	editor.config.editorWidth = '90%';        // 에디터 가로폭입니다.
	editor.config.editorPath = '<c:url value="/resources/libs/cheditor" />';
	editor.inputForm = 'PUB_CONTENTS';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	editor.run(); 

	$('#MAIN_PID').on('change', function(){
	   	SelectMain();
	});
});

function SelectMain(){
	if ($("select[name=MAIN_PID]").val() == ""){
	     $('#SUB_PID').find('option').remove();
	     $('#SUB_PID').find('option').end().append("<option value=''>-하위분류를 선택하세요-</option>").val("");
	     return;
	} else {
		var params = '?MAIN_PID=' + $("select[name=MAIN_PID]").val();
		$.ajax({
		type: "GET",
		url : '<c:url value="/pub/getPubCate.json"/>'+params,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		dataType: "json",
		async : false,
		success: function(RES) {
			if(RES.length > 0){
				$('#SUB_PID').find('option').remove();
				$('#SUB_PID').find('option').end().append("<option value=''>-하위분류를 선택하세요-</option>").val("");
				var sel_idx = 0;
				$.each(RES,function(idx, data){
				var list = "";
				if(data.P_IDX == '${params.SUB_PID}') {
					sel_idx = (idx+1);
				}
				list +="<option value='" + data.IDX +"' >" + data.NM + "</option>";
				$('#SUB_PID')
	            	.find('option')
	            	.end()
	            	.append(list)
	            	.val(data.IDX);
				});
				$('#SUB_PID').prop('selectedIndex',sel_idx);
			}else {
				$('#SUB_PID').find('option').remove();
				$('#SUB_PID').find('option').end().append("<option value=''>-하위분류를 선택하세요-</option>").val("");
				return;
			}
		},error: function(){
	 		alert("검색 실패");
	 		return;
		}});
	}
}

//등록
function fn_Update(){
	
	var contentStr = editor.outputBodyHTML();
	$("#PUB_CONTENTS").val(contentStr);

	if($("#PUB_YEAR").val() == ""){
		alert("년도를 입력해주세요.");
		return;
	}else if($("#PUB_CAT").val() == ""){
		alert("등록구분을 선택해주세요.")
		return;
	}else if($("#MAIN_PID").val() == ""){
		alert("상위분류를 선택해주세요.")
		return;
	}else if($("#SUB_PID").val() == ""){
		alert("하위분류를 선택해주세요.")
		return;
	}else if($("#PUB_TITLE").val() == ""){
		alert("제목을 입력해주세요.")
		return;
	}else if($("#PUB_CONTENTS").val() == ""){
		alert("내용을 입력해주세요.")
		return;
	}
	
	if(confirm("주요시험공고/지표를 수정하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/pub/pub_board_update.do' />");
		$("#frm").submit();
	}
}

function fn_Delete(){
	if(confirm("삭제 하시겠습니까?")){
		var dataString = $("#frm").serialize(); // frm은 form 이름
			$.ajax({
				type: "POST", 
			    url : '<c:url value="/pub/pub_board_delete.do"/>?',
			    data: dataString,
			    dataType: "text",  
			    async : false,
			    success: function(RES) {
			    if($.trim(RES)=="Y"){           
					alert("주요시험공고/지표가 삭제되었습니다.");
			        $("#frm").attr("action","<c:url value='/pub/pub_board_list.do' />");
			        $("#frm").submit();
			        return;
				}
			},error: function(){
				alert("삭제 실패");
			    return;
			}});
	}
}

function addAttchFile(){
    var divLength = $("#fileControl div").length;
    var lastItemNo;
    var nNum;

    if(divLength > 0){
        lastItemNo = $("#fileControl div:last-child").attr('id').replace("row",""); // $("#fileControl").replace("ATTACH_FILE", "");
        nNum = parseInt(lastItemNo) + 1;
    } else {
        nNum = 1;
    }
    var newitem = '<div id="row' + nNum + '"><input type="file" name="ATTACH_FILE" id="ATTACH_FILE_' + nNum + '" size="50">';
    newitem += ' <input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow(\'' + nNum + '\');"></div>';
    $("#fileControl").append(newitem);
}

function delRow(id, param){
    if(param == 'text') $("#rowText" + id).remove();
    else $("#row" + id).remove();
}

function deleteAttachFile(idx){
	var checkparam = "PUB_NO=${pubDetail.PUB_NO}&FILE_NO="+idx;
	$.ajax({
		type: "GET",
	  	url : '<c:url value="/pub/AttachFile_delete.do"/>?' + checkparam,
	  	dataType: "text",  
	  	async : false,
	  	success: function(RES) {
		if($.trim(RES)=="Y"){
			alert("첨부파일을 삭제했습니다.");
	        $("#frm").attr("action","<c:url value='/pub/pub_board_modify.do' />");
	        $("#frm").submit();
			return;
		}else{
			alert("삭제 실패");
			return;
		}
	},error: function(){
		alert("삭제 실패");
		return;
	}});
}

function fn_List(){
	$("#frm").attr("action", "<c:url value='/pub/pub_board_list.do' />");
	$("#frm").submit();
}
</script>
</head>

<body>
<!--content -->
<div id="content">
<form name="frm" id="frm" class="form form-horizontal" enctype="multipart/form-data" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}"/>
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${params.MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}"/>
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}"/>

<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="PUB_FILE" name="PUB_FILE" value="${pubDetail.PUB_FILE }" />
<input type="hidden" id="PUB_NEW_FILE" name="PUB_NEW_FILE" value="${pubDetail.PUB_NEW_FILE }" />
<input type="hidden" id="PUB_NO" name="PUB_NO" value="${pubDetail.PUB_NO}" />
        	
	<h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
        
    	<table class="table01">
		      <tr>
		        <th>년도</th>
		        <td class="tdLeft">
					<input name="PUB_YEAR" id="PUB_YEAR" type="text" value="${pubDetail.PUB_YEAR }" class="inputbox" style="width:62px;" />
		        </td>
		      </tr>
		      <tr>
		        <th>등록구분</th>
		        <td class="tdLeft">
					<label for="PUB_CAT"></label>
					<select name="PUB_CAT" id="PUB_CAT">
						<option value="">-구분-</option>
						<c:forEach items="${pub_gubun}" var="item" varStatus="loop">
							<option value="${item.CODE_VAL}" <c:if test="${pubDetail.PUB_CAT == item.CODE_VAL }">selected="selected"</c:if>>${item.CODE_NM}</option>					
						</c:forEach>
					</select>
		        </td>
		      </tr>
		      <tr>
		        <th>대상분류</th>
		        <td class="tdLeft">
					<label for="MAIN_PID"></label>
					<select name="MAIN_PID" id="MAIN_PID">
						<option value="">-상위분류를 선택하세요-</option>
						<c:forEach items="${main_pub}" var="item" varStatus="loop">
						<option value="${item.IDX}" <c:if test="${pubDetail.MAIN_PID == item.IDX }">selected="selected"</c:if>>${item.NM}</option>
						</c:forEach>
					</select>
					&nbsp;
					<label for="SUB_PID"></label>
					<select name="SUB_PID" id="SUB_PID">
						<option value="">-하위분류를 선택하세요-</option>
						<c:forEach items="${sub_pub}" var="item" varStatus="loop">
						<c:if test="${item.P_IDX eq pubDetail.MAIN_PID}" >
						<option value="${item.IDX}" <c:if test="${pubDetail.SUB_PID == item.IDX }">selected="selected"</c:if>>${item.NM}</option>
						</c:if>
						</c:forEach>
					</select>
		        </td>
		      </tr>
		      <tr>
		        <th>제목</th>
		        <td class="tdLeft">
					<input type="text" id="PUB_TITLE" name="PUB_TITLE" style="width: 33%" value="${pubDetail.PUB_TITLE }">
		        </td>
		      </tr>
	      </table>
	      <br/>
	<table class="table01">
		<tr>
			<th width="10%">내용</th>
		    <td class="tdLeft">
				<div class="item"><textarea id="PUB_CONTENTS" name="PUB_CONTENTS" class="i_text" title="레이블 텍스트" ><c:out value="${pubDetail.PUB_CONTENTS}"/></textarea></div>		        
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
	            <td style="text-align:left;">
	            <c:forEach items="${pub_files}" var="attachFile">
					${attachFile.FILE_NAME }<a href="javascript:deleteAttachFile('${attachFile.FILE_NO}')">  [ 삭제 ]</a><br/>
	            </c:forEach> <br/>
				<div class="item" id="fileControl">
                <input type="button" id="addButton" onclick="javascript:addAttchFile();" value="파일추가">
                <div id="row1"><input type="file" name="ATTACH_FILE" id="ATTACH_FILE_1" size="50">
                <input type="button" name="delAttchButton" value="삭제" onclick="javascript:delRow('1');"></div>
				</div>
			</td>
		</tr>
	</table>
	<br/>
	<br/>
		<!--//테이블--> 
	    <!--버튼-->
	    <ul class="boardBtns">
	   	  <li><a href="javascript:fn_Update();">수정</a></li>
	   	  <li><a href="javascript:fn_Delete();">삭제</a></li>
	      <li><a href="javascript:fn_List();">목록</a></li>
	    </ul>
	    <!--//버튼--> 
</form>
</div>
<!--//content --> 
</body>
</html>