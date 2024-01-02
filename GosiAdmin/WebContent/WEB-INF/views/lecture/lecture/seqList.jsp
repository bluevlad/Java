<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<script type="text/javascript">
	<c:if test="${!empty returnData}">
		//alert("순서가 변경되었습니다.");
		$(opener.location).attr("href", "javascript:fn_reload();");
		self.close();
	</c:if>
</script>
</head>
<body>
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">

    <h2>● 강의관리 > <strong>강의순서변경</strong></h2>
        
    <table class="table01">
		<tr>
			<th>과정선택</th>
			<td colspan="3">
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
			</td>
		</tr>
        <tr>
			<th width="15%">검색</th>
			<td colspan="3">
				<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="">--전체검색--</option>
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>과목</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>강의명</option>
					<option value="3" <c:if test="${params.SEARCHTYPE == '3' }">selected="selected"</c:if>>강사명</option>
					<option value="4" <c:if test="${params.SEARCHTYPE == '4' }">selected="selected"</c:if>>강의코드</option>
				</select> 
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
				<input type="button" onclick="fn_Search()" value="검색" />
          </td>
        </tr>
    </table>
     
    <ul class="boardBtns">
   	  	<li><a href="javascript:fn_SeqUpdate();">순서변경</a></li>
    </ul>
    
	<table class="table02">
		<tr>
	        <th width="110">No</th>
	        <th width="80">직종</th>
	        <th width="80">과목</th>
	        <th width="75">강의코드</th>
	        <th width="55">강사명</th>
	        <th>강의명</th>
	        <th width="75">학습형태</th>
	        <th width="80">등록일</th>
	        <th width="60">개설여부</th>
		</tr>      
		
		<c:forEach items="${list}" var="item" varStatus="loop">
			
			<tr <c:if test="${item.SUBJECT_ISUSE eq 'Y' }">class="vitality"</c:if>>
		        <td class="tdCenter">
					<input type="checkbox" name="DEL_ARR" value="${item.LECCODE}" />
                   	<input type="text" size="2" name="Num" value="${loop.index+1}" readonly="readonly" />
                       <input type="hidden" name="SEQ" value="${item.SEQ}"/>
                       <input type="hidden" name="LECCODE" value="${item.LECCODE}"/>
					<span style='cursor:pointer' id="selectup" ></span>
					<span style='cursor:pointer' id="selectdown" ></span>
					<span style='cursor:pointer' onclick='fncSort(this,"U","${loop.index+1}")' class='arrow2' id="chup${loop.index+1}">▲</span>
					<span style='cursor:pointer' onclick='fncSort(this,"D","${loop.index+1}")' class='arrow2'id="chdown${loop.index+1}"> ▼</span>
		        </td>
		        <td>${item.CATEGORY_NM}</td>
		        <td>${item.SUBJECT_NM}</td>
		        <td>${item.LECCODE}<br>${item.BRIDGE_LECCODE}</td>
		        <td>${item.SUBJECT_TEACHER_NM}</td>
		        <td>${item.SUBJECT_TITLE}</td>
		        <td>${item.LEARNING_NM}</td>
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
		        <td class="txt03"><c:if test="${item.SUBJECT_ISUSE eq 'Y' }">활성</c:if><c:if test="${item.SUBJECT_ISUSE ne 'Y' }">비활성</c:if></td>
			</tr>
		</c:forEach>	
		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center> 
				<td colspan="9">데이터가 존재하지 않습니다.(직종,학습형태 검색필수)</td>
			</tr>
		</c:if>	 		
    </table>   
    
    <ul class="boardBtns">
   	  	<li><a href="javascript:fn_SeqUpdate();">순서변경</a></li>
    </ul>
    
</form>
</div>
<script type="text/javascript">
// 순서변경 스크립트
function fncSort(obj,dir,arrow)
{
	var tbdup = document.getElementById('selectup').parentNode.parentNode.parentNode.parentNode;
    var tbddown = document.getElementById('selectdown').parentNode.parentNode.parentNode.parentNode;
    var str=0;
    var a = 0; 
    var arrow = arrow;
    for(i=0; i<document.frm.DEL_ARR.length; i++){
           if(document.frm.DEL_ARR[i].checked){
              flag=true;
              str = i+1;
          }  
    }
    
	if($("input[name='DEL_ARR']:checked").length < 1){
		alert('순서변경할 강사를 선택해 한개 주세요');
		return;
	}
	
    if($("input[name='DEL_ARR']:checked").length > 1){
    	alert("한개 이상 선택 할수 없습니다");
    	return;
    }else{
        if(dir=='U'){	
        	for(i=0; i<(document.frm.DEL_ARR.length-1); i++){
        		if( i == arrow ){
        			$("#chup"+i).attr("class","tit_arrow");
        			$("#chdown"+i).attr("class","arrow2"); 
        		}else {
        			$("#chup"+i).attr("class","arrow2");
        			$("#chdown"+i).attr("class","arrow2"); 
        		}
        	}
        	
            if((str-1)>0){//맨윗순서까지 이동을 안했을경우에만 실행을 해라 
            	
                var  a = document.frm.Num[str-1].value;
                var  b = document.frm.Num[str-2].value;
                var c = document.frm.SEQ[(str-1)].value; // 현재 select한 값의 seq값
                var d = document.frm.SEQ[(str-2)].value; // 바꾸려는 위치의 seq값
                var e = document.frm.LECCODE[(str-1)].value; // 현재 select한 값의 LECCODE값
                var f = document.frm.LECCODE[(str-2)].value; // 바꾸려는 위치의 LECCODE값
                
                if(str > 1 ){
                    document.frm.Num[str-2].value = a;
                    document.frm.Num[str-1].value = b;
                    document.frm.SEQ[(str-1)].value = d; // seq값을 서로 맞바꾼다 
                    document.frm.SEQ[(str-2)].value = c; // seq값을 서로 맞바꾼다 
                    document.frm.LECCODE[(str-1)].value = e; // LECCODE값을 서로 맞바꾼다 
                    document.frm.LECCODE[(str-2)].value = f; // LECCODE값을 서로 맞바꾼다                    
                    tbdup.moveRow(str-1,str); //srt-1 번째 있는td값을  srt번째로  옮겨라 
                }  
            }else{
                return;
            }
        }else if(dir=='D'){
            
            for(i=0; i<(document.frm.DEL_ARR.length-1); i++){
                if( i == arrow ){
        			$("#chup"+i).attr("class","arrow2");
        			$("#chdown"+i).attr("class","tit_arrow");
                }else {
        			$("#chup"+i).attr("class","arrow2");
        			$("#chdown"+i).attr("class","arrow2");                    	
                }
            }
            
            if(str < (document.frm.DEL_ARR.length-1) ){ // 맨아랫순서까지 이동을 했을경우가 아니라면 실행해라 
                var  a = document.frm.Num[str-1].value;
                var  b = document.frm.Num[str].value;
                var  c = document.frm.SEQ[str-1].value; //select박스의 select 한 값의 seq값 
                var  d = document.frm.SEQ[str].value; // 바꾸려는 위치의 seq값 
                var e = document.frm.LECCODE[(str-1)].value; //  현재 select한 값의 LECCODE값
                var f = document.frm.LECCODE[(str)].value; // 바꾸려는 위치의 LECCODE값                
                if(str<(document.frm.DEL_ARR.length-1)){
                    document.frm.Num[str-1].value = b;
                    document.frm.Num[str].value = a;
                    document.frm.SEQ[str-1].value = d; //seq값을 서로 맞바꾼다 
                    document.frm.SEQ[str].value= c ;//seq값을 서로 맞바꾼다
                    document.frm.LECCODE[str-1].value = e; // LECCODE값을 서로 맞바꾼다 
                    document.frm.LECCODE[str].value= f ;// LECCODE값을 서로 맞바꾼다                    
                    tbddown.moveRow(str,str+1); // str번째 있는 td의 값을 srt-1번째로 옮겨라 
                    }
            }else{
                return;
            }
        }  
    }
}

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

//삭제
function fn_SeqUpdate(){
	if(confirm("순서를 변경하시겠습니까?")){
		$("#frm").attr("action", "<c:url value='/lecture/seqUpdate.pop' />");
		$("#frm").submit();	
	}	
}

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/lecture/seqList.pop' />");
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
</body>
</html>