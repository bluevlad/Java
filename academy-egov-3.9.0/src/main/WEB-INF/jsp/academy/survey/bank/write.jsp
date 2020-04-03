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
	<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
	<input type="hidden" id="SEARCHFORM" name="SEARCHFORM" value="${params.SEARCHFORM}"/>
	<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}"/>
	<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
	<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
	<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
	<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
	
    <input type="hidden" name="tmp"  id="tmp" value="4" />
	<input type="hidden" name="QUEID" id="QUEID"  value=""/>
    <input type="hidden" name="QUEOWNER" id="QUEOWNER" value=""/>
	<input type="hidden" name="QUEVIW1" id="QUEVIW1" value=""/>
	<input type="hidden" name="QUEVIW2" id="QUEVIW2" value=""/>
	<input type="hidden" name="QUEVIW3" id="QUEVIW3" value=""/>
	<input type="hidden" name="QUEVIW4" id="QUEVIW4" value=""/>
	<input type="hidden" name="QUEVIW5" id="QUEVIW5" value=""/>
	<input type="hidden" name="QUEVIW6" id="QUEVIW6" value=""/>
	<input type="hidden" name="QUEVIW7" id="QUEVIW7" value=""/>
	<input type="hidden" name="QUEVIW8" id="QUEVIW8" value=""/>
	<input type="hidden" name="QUEVIW9" id="QUEVIW9" value=""/>
	<input type="hidden" name="QUEVIW10" id="QUEVIW10" value=""/>
    
	<table class="table01">
		<col width="20%"/>
		<col width="30%"/>
		<col width="20%"/>
		<col width="30%"/>
	    <tr>
	        <th>유형</th>
	        <td>
	        	<select name="QUETYPE" id="QUETYPE" onchange="type_reload()">
	        		<option value="S">선택형</option>
	        		<option value="M">선다형</option>
	        		<option value="T">복수답변형</option>
	        		<option value="D">서술형</option>
	        	</select>
	        </td>
	        <th>문항수</th>
	        <td>
	            <select name="QUECOUNT" id="QUECOUNT" onchange="changeQueuCount();">
		        <c:forEach var="i" begin="0" end="10">
		        	<option value="${i}"><c:out value="${i}"/></option>
		        </c:forEach>
	            </select> 
	        </td>
	    </tr>
	    <tr>
	        <th>사용여부</th>
	        <td colspan="3">
	        	<select name="ISUSE" id="ISUSE">
	        		<option value="Y">사용</option>
	        		<option value="N">비사용</option>
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
	        <td colspan="3"><input type="text" id="QUETITLE" name="QUETITLE" value="" style="width:95%"/></td>
	    </tr>
	    <tr>
	        <th>문제설명</th>
	        <td colspan="3"><textarea name="QUEDESC" id="QUEDESC" style="width:95%" rows="3"></textarea></td>
	    </tr>
	    <c:forEach var="v" begin="1" end="10" varStatus="status">
	    <!-- single select -->
	    <tr style='<c:out value="display:none"/>' id='<c:out value="SviewTEXT${status.count}"/>'>
	        <th>보기<c:out value="${status.count}"/></th>
	        <td><textarea name="Squeviw${status.count}" id="Squeviw${status.count}" style="width:98%" rows="3"></textarea></td>
	        <th>힌트<c:out value="${status.count}"/></th>
	        <td><textarea name="HINT${status.count}" id="HINT${status.count}" style="width:98%" rows="3"></textarea></td>
	    </tr>
	    <!-- multi select -->
	    <tr style='<c:out value="display:none"/>' id='<c:out value="MviewTEXT${status.count}"/>' >
	        <th>보기<c:out value="${status.count}"/></th>
	        <td><textarea name="Mqueviw${status.count}" id="Mqueviw${status.count}" style="width:98%" rows="3"></textarea></td>
	        <th>힌트<c:out value="${status.count}"/></th>
	        <td><textarea name="HINT${status.count}" id="HINT${status.count}" style="width:98%" rows="3"></textarea></td>
	    </tr>
	    </c:forEach>    
	</table>    
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">등록</a></li>
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
    }else if($("#QUETYPE").val() == "T"){    //단답형
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
    	var sv = document.getElementById("SviewTEXT"+i);
        var sm = document.getElementById("MviewTEXT"+i);
        if(i<=cnt && (type=='S' || type=='M' || type=='T')){
 			if(sv && sm ) {
	            if(type == "S"){  //single 선택형
	            	sv.style.display = "";
	            	sm.style.display = "none";
	            }else if(type == "M"){    //multi 선택형
	            	sv.style.display="none";
	            	sm.style.display="";
	            }else if(type == "T"){    //복수 답변형
	            	sv.style.display="none";
	            	sm.style.display="";
	            }
	        }
        }else{
            sv.style.display = "none";
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

	if(confirm("설문 문항을 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/survey/bank/insert.do' />");
		$("#frm").submit();
	}
}
</script>
</body>
</html>