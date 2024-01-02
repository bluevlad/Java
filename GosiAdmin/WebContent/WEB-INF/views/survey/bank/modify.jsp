<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
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
	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
	<input type="hidden" id="SEARCHFORM" name="SEARCHFORM" value="${params.SEARCHFORM}"/>
	<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}"/>
	<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
	<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
	
	<c:choose>
	    <c:when test="${view.QUETYPE == 'S' || view.QUETYPE == 'M'}">
	        <input type="hidden" name="tmp" id="tmp" value="${view.QUECOUNT}"/>
	    </c:when>
	    <c:otherwise>
	        <input type="hidden" name="tmp" id="tmp" value="4">
	    </c:otherwise>
	</c:choose>
	<input type="hidden" name="QUEID" id="QUEID"  value="${view.QUEID}"/>
    <input type="hidden" name="QUEOWNER" id="QUEOWNER" value="${view.QUEOWNER}"/>
    
	<table class="table01">
		<col width="20%"/>
		<col width="30%"/>
		<col width="20%"/>
		<col width="30%"/>
	    <tr>
	        <th>유형</th>
	        <td>
	        	<select name="QUETYPE" id="QUETYPE" onchange="type_reload()">
	        		<option value="S" <c:if test="${view.QUETYPE == 'S'}">selected</c:if>>선택형</option>
	        		<option value="M" <c:if test="${view.QUETYPE == 'M'}">selected</c:if>>선다형</option>
	        		<option value="T" <c:if test="${view.QUETYPE == 'T'}">selected</c:if>>복수단답형</option>
	        		<option value="D" <c:if test="${view.QUETYPE == 'D'}">selected</c:if>>답변형</option>
	        	</select>
	        </td>
	        <th>문항수</th>
	        <td>
	            <select name="QUECOUNT" id="QUECOUNT" onchange="changeQueuCount();">
		        <c:forEach var="i" begin="0" end="10">
		        	<option value="${i}"<c:if test="${view.QUECOUNT == i}">selected</c:if>><c:out value="${i}"/></option>
		        </c:forEach>
	            </select> 
	        </td>
	    </tr>
	    <tr>
	        <th>사용여부</th>
	        <td colspan="3">
	        	<select name="ISUSE" id="ISUSE">
	        		<option value="Y" <c:if test="${view.ISUSE == 'Y'}">selected</c:if>>사용</option>
	        		<option value="N" <c:if test="${view.ISUSE == 'N'}">selected</c:if>>비사용</option>
	        	</select>
	        </td>
	    </tr>
    </table>
	<br>
	<table class="table01">
		<col width="15%"/>
		<col width="35%"/>
		<col width="15%"/>
		<col width="35%"/>
	    <tr>
	        <th>제목</th>
	        <td colspan="3"><input type="text" id="QUETITLE" name="QUETITLE" value="${view.QUETITLE}" style="width:95%"/></td>
	    </tr>
	    <tr>
	        <th>문제설명</th>
	        <td colspan="3"><textarea name="QUEDESC" id="QUEDESC" style="width:95%" rows="3">${view.QUEDESC}</textarea></td>
	    </tr>
	    <c:forEach var="v" begin="1" end="10" varStatus="status">
        <c:set var="Sview" value= "none"/>
       	<c:if test="${view.QUECOUNT >= status.count}"><c:set var="Sview" value= ""/></c:if>
        <c:choose>
            <c:when test="${status.count == 1}">
                <c:set var="viw" value = "${view.QUEVIW1}"/>
                <c:set var="hint" value = "${view.HINT1}"/>
            </c:when>
            <c:when test="${status.count == 2}">
                <c:set var="viw" value= "${view.QUEVIW2}"/>
                <c:set var="hint" value = "${view.HINT2}"/>
            </c:when>
            <c:when test="${status.count == 3}">
                <c:set var="viw" value= "${view.QUEVIW3}"/>
                <c:set var="hint" value = "${view.HINT3}"/>
            </c:when>
            <c:when test="${status.count == 4}">
                <c:set var="viw" value= "${view.QUEVIW4}"/>
                <c:set var="hint" value = "${view.HINT4}"/>
            </c:when>
            <c:when test="${status.count == 5}">
                <c:set var="viw" value= "${view.QUEVIW5}"/>
                <c:set var="hint" value = "${view.HINT5}"/>
            </c:when>
            <c:when test="${status.count == 6}">
                <c:set var="viw" value= "${view.QUEVIW6}"/>
                <c:set var="hint" value = "${view.HINT6}"/>
            </c:when>
            <c:when test="${status.count == 7}">
                <c:set var="viw" value= "${view.QUEVIW7}"/>
                <c:set var="hint" value = "${view.HINT7}"/>
            </c:when>
            <c:when test="${status.count == 8}">
                <c:set var="viw" value= "${view.QUEVIW8}"/>
                <c:set var="hint" value = "${view.HINT8}"/>
            </c:when>
            <c:when test="${status.count == 9}">
                <c:set var="viw" value= "${view.QUEVIW9}"/>
                <c:set var="hint" value = "${view.HINT9}"/>
            </c:when>
            <c:when test="${status.count == 10}">
                <c:set var="viw" value= "${view.QUEVIW10}"/>
                <c:set var="hint" value = "${view.HINT10}"/>
            </c:when>
            <c:otherwise>
                <c:set var="viw" value= ""/>
            </c:otherwise>
        </c:choose>
	    <!-- single select -->
	    <tr style='<c:out value="display:${Sview}"/>' id='<c:out value="viewTEXT${status.count}"/>'>
	        <th>보기<c:out value="${status.count}"/></th>
	        <td><textarea name="QUEVIW${status.count}" id="QUEVIW${status.count}" style="width:98%" rows="3">${viw}</textarea></td>
	        <th>힌트<c:out value="${status.count}"/></th>
	        <td><textarea name="HINT${status.count}" id="HINT${status.count}" style="width:98%" rows="3">${hint}</textarea></td>
	    </tr>
	    </c:forEach>    
	</table>    
    
    <!--버튼-->
	<ul class="boardBtns">
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
	$("#frm").attr("action","<c:url value='/survey/bank/list.do' />");
	$("#frm").submit();
}

function type_reload(formName){
    if($("#QUETYPE").val() == "S"){  //single선택형
    	$("#QUECOUNT").selectedIndex == $("#tmp").val();
        view_reload("S");
    	$("#QUECOUNT").disabled == false;
    }else if($("#QUETYPE").val() == "M"){  //multi선택형
    	$("#QUECOUNT").selectedIndex == $("#tmp").val();
        view_reload("M");
    	$("#QUECOUNT").disabled == false;
    }else if($("#QUETYPE").val() == "T"){    //복수답변형
    	$("#QUECOUNT").selectedIndex == $("#tmp").val();
        view_reload("T");
    	$("#QUECOUNT").disabled == false;
    }else if($("#QUETYPE").val() == "D"){    //서술형
    	$("#QUECOUNT").selectedIndex == 0;
        view_reload("D");
    	$("#QUECOUNT").disabled == false;
    }
}

function view_reload(type){
    var cnt = parseInt($("#tmp").val());
    for(i=1; i<=10; i++){
        var sm = document.getElementById("viewTEXT"+i);
        if(i<=cnt && (type=='S' || type=='M' || type=='T')){
 			if(sm) {
            	sm.style.display="";
	        }
        }else{
            sm.style.display = "none";
        }
    }
}

function changeQueuCount() {
	$("#tmp").val($("#QUECOUNT").val())
	view_reload($("#QUETYPE").val());
}

//등록
function fn_Submit(){
	if($.trim($("#QUETITLE").val()) == ""){
		alert("제목을 입력하세요");
		$("#QUETITLE").focus();
     return;
	}

	if(confirm("설문 문항을 저장하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/survey/bank/update.do' />");
		$("#frm").submit();
	}
}
</script>
</body>
</html>