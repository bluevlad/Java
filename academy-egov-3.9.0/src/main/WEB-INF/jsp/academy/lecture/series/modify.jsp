<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head></head>

<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SRS_CD" name="SRS_CD" value="${list[0].SRS_CD}"/>
<input type="hidden" id="ORDR" name="ORDR" value="${list[0].ORDR}"/>
<input type="hidden" id="TYPE_CHOICE" name="TYPE_CHOICE" value="${params.TYPE_CHOICE}">

    <h2>● 강의 관리 > <strong>직렬관리</strong></h2>

    <ul class="lecWritheTab">
        <li><a href="javascript:fn_go_list('S');" <c:if test="${params.TYPE_CHOICE eq 'S'}">class="active"</c:if>>직렬-직급등록</a></li>
        <li><a href="javascript:fn_go_list('C');" <c:if test="${params.TYPE_CHOICE eq 'C'}">class="active"</c:if>>직급-직렬연결</a></li>
    </ul>       

    <table class="table01">
        <tr>
            <th>코드</th>
            <td>${list[0].SRS_CD}</td>
        </tr>
        <tr>
            <th>직렬명</th>
            <td>
                <input type="text" id="SRS_NM" name="SRS_NM" value="${list[0].SRS_NM}" size="20"  maxlength="25" title="직렬명" style="width:28%;background:#FFECEC;"/>
            </td>
        </tr>
        <tr>
            <th>관련 직종</th>
            <td>
                <c:set var="CAT_CDs" value="${fn:split(list[0].CAT_CD, ',')}" />
                <c:forEach items="${cat_list}" var="item" varStatus="status" >
                  <c:set var="vChecked">
                    <c:forEach var="data" items="${CAT_CDs}" varStatus="status2">
                      <c:choose>
                        <c:when test="${item.CODE == data}">checked="checked"</c:when>
                        <c:otherwise></c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </c:set>
                <input type="checkbox" id="CAT_CD" name="CAT_CD" value="${item.CODE}" <c:out value='${vChecked}'/> ><label for="a1">${item.NAME }</label><c:if test="${(status.count mod 6) eq 0}"><br/></c:if>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th>상태</th>
            <td>
                <input type="radio" id="ISUSE" name="ISUSE" value="Y" title="활성" <c:if test="${list[0].ISUSE eq 'Y' }">checked="checked"</c:if> /><label for="ISUSE">활성</label>
                <input type="radio" id="ISUSE" name="ISUSE" value="N" title="비활성" <c:if test="${list[0].ISUSE eq 'N' }">checked="checked"</c:if> /><label for="ISUSE2">비활성</label>
            </td>
        </tr>
    </table>
    
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_submit();">수정</a></li>
        <li><a href="javascript:fn_list();">목록</a></li>
        <!-- <li><a href="javascript:fn_delete();">삭제</a></li> -->
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 

<script type="text/javascript">
// 목록으로
function fn_list(){
    $("#frm").attr("action","<c:url value='/series/list.do' />");
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

// 수정
function fn_submit(){
    if($.trim($("#SRS_NM").val())==""){
        alert("직렬명을 입력해주세요");
        $("#SRS_NM").focus();
        return;
    }
    if(confirm("수정하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/series/update.do' />");
        $("#frm").submit();
    }
}

// 삭제
function fn_delete(){
    if(confirm("정말 삭제하시겠습니까?")){
        $("#frm").attr("action","<c:url value='/series/delete.do' />");
        $("#frm").submit();
    }
}
</script>
