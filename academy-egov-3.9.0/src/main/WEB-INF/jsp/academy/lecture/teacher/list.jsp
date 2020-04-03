<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head></head>

<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="USER_ID" name="USER_ID" value="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">

    <h2>● 강의 관리 > <strong>강사관리</strong></h2>
    
    <!-- 검색 -->    
    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td>            
                <label for="SEARCHTYPE"></label>
                <select name="SEARCHTYPE" id="SEARCHTYPE">
                    <option value="">--전체검색--</option>
                    <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>ID</option>
                    <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>이름</option>
                </select>
                <label for="SEARCHTEXT"></label>
                <input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
            </td>
            <th width="15%">화면출력건수</th>
            <td width="30%">               
                <input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/>
                <input type="button" onclick="fn_Search()" value="검색" />
            </td>
        </tr>
    </table>
    <!-- //검색 -->
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Reg();">등록</a></li>
        <li><a href="javascript:fn_Delete();">삭제</a></li>
        <c:if test="${!empty params.SEARCHCATEGORY}">
            <li><a href="javascript:fn_SeqUpdate();">순서변경</a></li>  
        </c:if>
        <li><a href="javascript:fn_excel_down();">엑셀다운로드</a></li>
    </ul>
    <!--//버튼--> 
    
    <span style="display:inline-block; text-align:left; float:left; padding-bottom:5px;">
        <select name="SEARCHCATEGORY" id="SEARCHCATEGORY" onchange="fn_Search();">
            <option value="">전체</option>
            <c:forEach items="${kindlist}" var="item" varStatus="loop">
                <option value="${item.CODE}" <c:if test="${params.SEARCHCATEGORY == item.CODE}">selected="selected"</c:if>>${item.NAME}</option>                   
            </c:forEach>
				<option value="main" <c:if test="${params.SEARCHCATEGORY == 'main'}">selected="selected"</c:if>>메인배너</option>
				<option value="intro" <c:if test="${params.SEARCHCATEGORY == 'intro'}">selected="selected"</c:if>>교수진소개</option>
				<option value="intro_off" <c:if test="${params.SEARCHCATEGORY == 'intro_off'}">selected="selected"</c:if>>교수진소개(OFF)</option>
        </select>
        
      <c:if test="${!empty params.SEARCHCATEGORY}">
	      <c:if test="${params.SEARCHCATEGORY eq 'intro_off' || params.SEARCHCATEGORY eq 'intro' || params.SEARCHCATEGORY eq 'main'}">
	      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      	<input type="radio" id="ONOFFDIV" name="ONOFFDIV" class="i_check" value="" <c:if test="${params.ONOFFDIV == ''}">checked="checked"</c:if> onclick="fn_Search();" ><label for="a3">All</label>
	      </c:if>
	      <c:if test="${params.SEARCHCATEGORY ne 'intro_off' && params.SEARCHCATEGORY ne 'intro' && params.SEARCHCATEGORY ne 'main'}">
			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <input type="radio" id="ONOFFDIV" name="ONOFFDIV" class="i_check" value="" <c:if test="${params.ONOFFDIV == ''}">checked="checked"</c:if> onclick="fn_Search();" ><label for="a3">All</label>
	        <input type="radio" id="ONOFFDIV" name="ONOFFDIV" class="i_check" value="O" <c:if test="${params.ONOFFDIV == 'O'}">checked="checked"</c:if> onclick="fn_Search();" ><label for="a2">ON</label>&nbsp;&nbsp;
	        <input type="radio" id="ONOFFDIV" name="ONOFFDIV" class="i_check" value="F" <c:if test="${params.ONOFFDIV == 'F'}">checked="checked"</c:if> onclick="fn_Search();" ><label for="a3">OFF</label>      
	      </c:if>
      </c:if>
    </span>
        
    <p class="pInto01">
        <span>총${totalCount}건 
        <c:if test="${empty params.SEARCHCATEGORY}">
            (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)
        </c:if>
        </span>
    </p>
          
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
        <th>ID</th>
        <th width="90">이름</th>
        <th>과목</th>
        <th>등록일</th>
        <th>분류</th>
        <th>사이트구분</th>
        <th>상태(ON)</th>
        <th>상태(OFF)</th>
        </tr>
        <tbody>
        <c:forEach items="${list}" var="item" varStatus="loop">
            <tr>
                <td>
                    <input type="checkbox" name="DEL_ARR" value="${item.USER_ID}" />
                    <c:if test="${!empty params.SEARCHCATEGORY}">
                    <c:choose>
                      <c:when test="${params.ONOFFDIV == 'F'}">
                        <input type="text" size="2" name="Num" value="${loop.index+1}" readonly="readonly" />
                        <input type="hidden" name="SEQ" value="${item.OFF_SEQ}"/>
                      </c:when>
                      <c:otherwise>
                        <input type="text" size="2" name="Num" value="${loop.index+1}" readonly="readonly" />
                        <input type="hidden" name="SEQ" value="${item.SEQ}"/>
                      </c:otherwise>
                    </c:choose>
                        <input type="hidden" name="PROFESSOR_USER_ID" value="${item.USER_ID}"/>
	                    <input type="hidden" name="SUBJECT_CD" id="SUBJECT_CD" value="${item.SUBJECT_CD}"/>
                        <span style='cursor:pointer' id="selectup" ></span>
                        <span style='cursor:pointer' id="selectdown" ></span>
                        <span style='cursor:pointer' onclick='fncSort(this,"U","${loop.index+1}")' class='arrow2' id="chup${loop.index+1}">▲</span>
                        <span style='cursor:pointer' onclick='fncSort(this,"D","${loop.index+1}")' class='arrow2'id="chdown${loop.index+1}"> ▼</span>
                    </c:if>
                    <c:if test="${empty params.SEARCHCATEGORY}">
                        ${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}        
                    </c:if>     
                </td>
                <td>${item.USER_ID}</td>
                <td><a href="javascript:fn_Modify('${item.USER_ID}');">${item.USER_NM}</a></td>
                <td>${item.SUBJECT_NM}</td>
                <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
                <td>${item.CATEGORY_NM}</td>
                <td>
	            	<c:choose>
		            	<c:when test="${item.USER_POSITION == 'ALL'}">통합</c:when>
		            	<c:when test="${item.USER_POSITION == 'PUB'}">공무원</c:when>
		            	<c:when test="${item.USER_POSITION == 'COP'}">경찰</c:when>
		            	<c:when test="${item.USER_POSITION == 'GWJ'}">광주캠퍼스</c:when>
		            	<c:otherwise>미지정</c:otherwise>
	            	</c:choose>
                </td>
                <td>${item.ON_OPENYNNM}</td>
                <td>${item.OFF_OPENYNNM}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center> 
                <td colspan="8">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>  
        </tbody>
    </table>      
    <!-- //테이블--> 
   
   <c:if test="${empty params.SEARCHCATEGORY}">
        <!-- paginate-->
        <c:if test="${!empty list}">
            <c:out value="${pagingHtml}" escapeXml="false" />
        </c:if>
        <!--//paginate-->
    </c:if>
</form> 
</div>
<!--//content --> 

<script type="text/javascript">
// 페이징
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#frm").attr("action", "<c:url value='/teacher/list.do' />");
    $("#frm").submit();
}

// All Checkbox Controller
function fn_CheckAll(id, name) {
    if($("#"+id).attr("checked") == "checked") {
        $("input[name="+name+"]").attr("checked", "checked");
    } else {
        $("input[name="+name+"]").removeAttr("checked");
    }
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

// 삭제
function fn_Delete(){
    if($("input[name='DEL_ARR']:checked").length > 0){
        if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
            $("#frm").attr("action", "<c:url value='/teacher/listDelete.do' />");
            $("#frm").submit();
        }
    }else{
        alert("선택된 항목이 없습니다");
        return;
    }
}

// 검색
function fn_Search() {
    $("#currentPage").val(1);
    $("#frm").attr("action", "<c:url value='/teacher/list.do' />");
    $("#frm").submit();
}

// 등록폼
function fn_Reg(){
    $("#frm").attr("action", "<c:url value='/teacher/write.do'/>");
    $("#frm").submit(); 
}

// 수정폼
function fn_Modify(val){
    $("#USER_ID").val(val);
    $("#frm").attr("action", "<c:url value='/teacher/modify.do' />");
    $("#frm").submit();
}

// 순서변경
function fn_SeqUpdate(){
    if($("input[name='ONOFFDIV']:checked").length < 1){
        alert("ON/OFF 구분자를 선택해 주세요.");
        $("#ONOFFDIV").focus();
        return;
    } else if(confirm("순서를 변경하시겠습니까?")){
        $("#frm").attr("action", "<c:url value='/teacher/seqUpdate.do' />");
        $("#frm").submit(); 
    }
}

// 엔터키 검색
function fn_Enter(){
    $("#SEARCHTEXT").keyup(function(e)  {
        if(e.keyCode == 13) 
            fn_Search();
    });
}

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
        alert('순서변경할 강사를 선택해 주세요');
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
                
                var a = document.frm.Num[str-1].value;
                var b = document.frm.Num[str-2].value;
                var c = document.frm.SEQ[(str-1)].value; // 현재 select한 값의 seq값
                var d = document.frm.SEQ[(str-2)].value; // 바꾸려는 위치의 seq값
                var e = document.frm.PROFESSOR_USER_ID[(str-1)].value; // 현재 select한 값의 PROFESSOR_USER_ID값
                var f = document.frm.PROFESSOR_USER_ID[(str-2)].value; // 바꾸려는 위치의 PROFESSOR_USER_ID값
                
                if(str > 1 ){
                    document.frm.Num[str-2].value = a;
                    document.frm.Num[str-1].value = b;
                    document.frm.SEQ[(str-1)].value = d; // seq값을 서로 맞바꾼다 
                    document.frm.SEQ[(str-2)].value = c; // seq값을 서로 맞바꾼다 
                    document.frm.PROFESSOR_USER_ID[(str-1)].value = e; // PROFESSOR_USER_ID값을 서로 맞바꾼다 
                    document.frm.PROFESSOR_USER_ID[(str-2)].value = f; // PROFESSOR_USER_ID값을 서로 맞바꾼다                    
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
                var a = document.frm.Num[str-1].value;
                var b = document.frm.Num[str].value;
                var c = document.frm.SEQ[str-1].value; //select박스의 select 한 값의 seq값 
                var d = document.frm.SEQ[str].value; // 바꾸려는 위치의 seq값 
                var e = document.frm.PROFESSOR_USER_ID[(str-1)].value; //  현재 select한 값의 PROFESSOR_USER_ID값
                var f = document.frm.PROFESSOR_USER_ID[(str)].value; // 바꾸려는 위치의 PROFESSOR_USER_ID값                
                if(str<(document.frm.DEL_ARR.length-1)){
                    document.frm.Num[str-1].value = b;
                    document.frm.Num[str].value = a;
                    document.frm.SEQ[str-1].value = d; //seq값을 서로 맞바꾼다 
                    document.frm.SEQ[str].value= c ;//seq값을 서로 맞바꾼다
                    document.frm.PROFESSOR_USER_ID[str-1].value = e; // PROFESSOR_USER_ID값을 서로 맞바꾼다 
                    document.frm.PROFESSOR_USER_ID[str].value= f ;// PROFESSOR_USER_ID값을 서로 맞바꾼다                    
                    tbddown.moveRow(str,str+1); // str번째 있는 td의 값을 srt-1번째로 옮겨라 
                }
            }else{
                return;
            }
        }  
    }
}

// 엑셀 다운로드
function fn_excel_down() {
    $("#frm").attr("action", "<c:url value='/teacher/excel.do' />");
    $("#frm").submit();
}
</script>
