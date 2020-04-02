<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
</head>
<body>
<!--content -->
<div id="content">
<c:import url="/WEB-INF/views/survey/tab.jsp" />

    <p>&nbsp; </p>
	<form name="frm" id="frm" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

	<input type="hidden" id="S_MENU" name="S_MENU" value="${params.S_MENU}" />
	<input type="hidden" id="SETID" name="SETID" value="${view.SETID}"/>
	<input type="hidden" id="QUEID" name="QUEID" value=""/>
	
	<table class="table01">
		<col width="20%"/>
		<col width="80%"/>
	    <tr>
	        <th>제목</th>
	        <td><input type="text" id="SETTITLE" name="SETTITLE" value="${view.SETTITLE}" style="width:95%"/></td>
	    </tr>
	    <tr>
	        <th>설명</th>
	        <td><textarea name="SETDESC" id="SETDESC" style="width:95%" rows="3">${view.SETDESC}</textarea></td>
	    </tr>
	    <tr>
	        <th>사용여부</th>
	        <td>
	        	<select name="ISUSE" id="ISUSE">
	        		<option value="Y" <c:if test="${view.ISUSE == 'Y'}">selected</c:if>>사용</option>
	        		<option value="N" <c:if test="${view.ISUSE == 'N'}">selected</c:if>>비사용</option>
	        	</select>
	        </td>
	    </tr>
	</table>    
   <br> 
	<table class="table01">
		<thead>
		<col width="50" />
		<col width="*" />
		<col width="100" />
		<col width="100" />
		<col width="50" />
        <tr>
            <th>순서</th>
            <th>질문명</th>
            <th>문항수</th>
            <th>문항타입</th>
            <th>삭제</th>
		</tr>
        </thead>
        <tbody>
		<c:forEach var="item" items="${item}" varStatus="status">
        <input type="hidden" name="v_queids" value="${item.QUEID}" />
        <tr>
            <td align="center"><input type="text" name="qseq_${item.QUEID}" style="width:30px" maxlength="2" value="${item.QSEQ}" /></td>
            <td>${item.QUETITLE}</td>
            <td>${item.QUECOUNT}</td>
            <td>${item.QUENM}</td>
            <td><a href="javascript:fn_DelItem(' ${item.QUEID}');">삭제</a></td>
		</tr>
        </c:forEach>
        </tbody>
        </table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_AddItem('${view.SETID}');">문항추가</a></li>
   		<li><a href="javascript:fn_Submit();">저장</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->

	</form>
</div>
<!--//content --> 
<script type="text/javascript">
// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/survey/set/list.do' />");
	$("#frm").submit();
}

//등록
function fn_Submit(){
	if($.trim($("#SETTITLE").val()) == ""){
		alert("제목을 입력하세요");
		$("#SETTITLE").focus();
     return;
	}

	if(confirm("설문 문항을 저장하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/survey/set/update.do' />");
		$("#frm").submit();
	}
}

//문항 리스트
function fn_AddItem(no) {
	window.open('<c:url value="/survey/set/itemList.pop"/>?SETID='+no, '_survey', 'location=no,resizable=no,width=800,height=600,top=0,left=0,location=no,scrollbars=yes');
}

//문항 삭제
function fn_DelItem(queid){
	if(confirm("해당 문항을 삭제하시겠습니까?")){
		$("#QUEID").val(queid);
		$("#frm").attr("action","<c:url value='/survey/set/itemDelete.do' />");
		$("#frm").submit();
	}
}
</script>
</body>
</html>