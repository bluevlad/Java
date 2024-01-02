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
<input type="hidden" id="SRS_CD" name="SRS_CD" value="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${TYPE_CHOICE}">

    <h2>● 강의 관리 > <strong>직렬관리</strong></h2>

    <ul class="lecWritheTab">
        <li><a href="javascript:fn_go_list('S');" <c:if test="${TYPE_CHOICE eq 'S'}">class="active"</c:if>>직종관리</a></li>
        <li><a href="javascript:fn_go_list('C');" <c:if test="${TYPE_CHOICE eq 'C'}">class="active"</c:if>>직종-직렬관리</a></li>
    </ul>       
    
    <!-- 검색 -->    
    <table class="table01">
        <tr>
            <th width="15%">검색</th>
            <td>            
                <label for="SEARCHTYPE"></label>
                <select name="SEARCHTYPE" id="SEARCHTYPE">
                    <option value="">--전체검색--</option>
                    <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>코드</option>
                    <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>직렬명</option>
                </select>
                <label for="SEARCHTEXT"></label>
                <input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_enter()">
            </td>
            <th width="15%">화면출력건수</th>
            <td width="30%">               
                 <input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/>
                 <input type="button" onclick="fn_search()" value="검색" />
            </td>
        </tr>
    </table>
    <!-- //검색 -->
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_add();">등록</a></li>
        <li><a href="javascript:fn_delete();">비활성</a></li>
    </ul>
    <!--//버튼--> 
        
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
          
    <!-- 테이블-->
    <table class="table02">
        <tr>
            <th width="85"><input type="checkbox" name="allCheck" id="allCheck" class="i_check" value="" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />No</th>
            <th>코드</th>
            <th>직렬명</th>
            <th>상태</th>
            <th>등록일</th>
            <th>직종</th>
        </tr>
        <tbody>
            <c:forEach items="${list}" var="item" varStatus="loop">
                <tr>
                    <td>
                        <input type="checkbox" name="DEL_ARR" class="i_check" value="${item.SRS_CD}" /> ${totalCount-((params.currentPage-1)*params.pageRow)-loop.index} 
                    </td>
                    <td>${item.SRS_CD}</td>
                    <td><a href="javascript:fn_modify('${item.SRS_CD}');">${item.SRS_NM}</a></td>
                    <td>${item.ISUSENM}</td>
                    <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
                    <td>${item.CAT_NM}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty list}">
                <tr bgColor=#ffffff align=center> 
                    <td colspan="6">데이터가 존재하지 않습니다.</td>
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
    
</form> 
</div>
<!--//content --> 

<script type="text/javascript">
// 페이징
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#frm").attr("action", "<c:url value='/series/list.do' />");
    $("#frm").submit();
}

function fn_go_list(val){
    $("#TYPE_CHOICE").val(val);
    if(val=="S"){
        $("#frm").attr("action", "<c:url value='/series/list.do' />");
    }else{
        $("#frm").attr("action", "<c:url value='/series/cat/main.do' />");
    }
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

//삭제
function fn_delete(){
    if($("input[name='DEL_ARR']:checked").length > 0){
        if(confirm("선택한 항목들을 비활성으로 변경하시겠습니까?")){
            $("#frm").attr("action", "<c:url value='/series/listDelete.do' />");
            $("#frm").submit();
        }
    }else{
        alert("선택된 항목이 없습니다");
        return;
    }
}

// 검색
function fn_search() {
    $("#currentPage").val(1);
    $("#frm").attr("action", "<c:url value='/series/list.do' />");
    $("#frm").submit();
}

// 등록폼
function fn_add(){
    $("#frm").attr("action", "<c:url value='/series/write.do'/>");
    $("#frm").submit();
}

// 수정폼
function fn_modify(val){
    $("#SRS_CD").val(val);
    $("#frm").attr("action", "<c:url value='/series/modify.do' />");
    $("#frm").submit();
}

// 엔터키 검색
function fn_enter(){
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
