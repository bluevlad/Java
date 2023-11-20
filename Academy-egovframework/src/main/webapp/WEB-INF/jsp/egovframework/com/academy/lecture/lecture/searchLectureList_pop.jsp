<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="SRCHCODE" name="SRCHCODE" value="${params.SRCHCODE}">
<input type="hidden" id="SRCHTXT" name="SRCHTXT" value="${params.SRCHTXT}">
    <h2>● 강의제작 > <strong>단과불러오기</strong></h2> 
     <!-- 검색 -->    
    <table class="table01">
        <tr>
            <th width="15%">과정선택</th>
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
                <label for="SEARCHTEXT"></label>
                <input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
            </td>
        </tr>
        <tr>
            <th width="15%">과목</th>
            <td width="30%">
            <select name="SEARCHSBJTCD" id="SEARCHSBJTCD">
                <option value="">--전체검색--</option>
            <c:forEach items="${SEARCHSUBJECTs}" var="data" varStatus="status">
                <option value="${data.SUBJECT_CD}" <c:if test="${data.SUBJECT_CD == params.SEARCHSBJTCD}">selected="selected"</c:if>>${data.SUBJECT_NM}</option>
            </c:forEach>
            </select> 
            </td>
            <th width="15%">강사</th>
            <td width="30%">
            <select name="SEARCHPRFID" id="SEARCHPRFID">
                <option value="">--전체검색--</option>
            <c:forEach items="${SEARCHPRFIDs}" var="data" varStatus="status" >
                <option value="${data.USER_ID}" <c:if test="${data.USER_ID == params.SEARCHPRFID}">selected="selected"</c:if>>${data.USER_NM }</option>
            </c:forEach>
            </select> 
            </td>
        </tr>
        <tr>
            <th>화면출력건수</th>
            <td colspan="3">
                <input type="text" title="검색" type="text" id="pageRow" name="pageRow" size="5" maxlength="2" onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow}" onkeypress="fn_RowNumCheck()">
                <input type="button" id="textfield3" name="textfield3" value="검색" onclick="fn_Search();"  >
            </td>
        </tr>
    </table>
    <!-- //검색 --> 
          
    <p class="pInto01">&nbsp;</p>
    <!-- 테이블-->
    <table class="table02">
        <tr>
            <th width="85">No</th>
            <th>직종</th>
            <th>학습형태</th>
            <th>과목</th>
            <th>강사</th>
            <th>강의명</th>
            <th width="85">선택</th>
        </tr>
        <tbody>
    <c:forEach items="${list}" var="item" varStatus="loop">
        <tr>
            <td>${totalCount-((params.currentPage-1)*params.pageRow)-loop.index}</td>
            <td>${item.CATEGORY_NM}</td>
            <td>${item.LEARNING_NM}</td>
            <td>${item.SUBJECT_NM}</td>
            <td>${item.SUBJECT_TEACHER_NM}</td>
            <td>${item.SUBJECT_TITLE}</td>
            <td><input type="button" name="lecbtn" value="선택" onClick="fn_select('${item.LECCODE}','${item.SUBJECT_TITLE}');"></td>
            <input type="hidden" name="LECCODE_ARR" value="${item.LECCODE}"/> 
            <input type="hidden" name="CATEGORY_NM_ARR" value="${item.CATEGORY_NM}"/>
            <input type="hidden" name="LEARNING_NM_ARR" value="${item.LEARNING_NM}"/>
            <input type="hidden" name="SUBJECT_TITLE_ARR" value="${item.SUBJECT_TITLE}"/>
            <input type="hidden" name="SUBJECT_TEACHER_NM_ARR" value="${item.SUBJECT_TEACHER_NM}"/>
        </tr>
    </c:forEach>
    <c:if test="${empty list}">
        <tr bgColor=#ffffff align=center> 
            <td colspan="7">데이터가 존재하지 않습니다.</td>
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
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->
</form> 
</div>
<!--//content --> 
<script type="text/javascript">
$(document).ready(function(){
    $('#frm #SEARCHKIND').on('change', function(){
       fn_select_subject();
    });

    $('#frm #SEARCHSBJTCD').on('change', function(){
       fn_select_teacher();
    });
});
// 페이징
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#frm").attr("action", "<c:url value='/lecture/searchLecList.pop' />");
    $("#frm").submit();
}
//검색
function fn_Search() {
    $("#currentPage").val(1);
    $("#frm").attr("action", "<c:url value='/lecture/searchLecList.pop' />");
    $("#frm").submit();
}
//엔터키 검색
function fn_Enter(){
    $("#SEARCHTEXT").keyup(function(e)  {
        if(e.keyCode == 13) 
            fn_Search();
    });
}
function fn_select(leccode, lecname){
    $(top.opener.document).find("#${params.SRCHCODE}").val(leccode);
    $(top.opener.document).find("#${params.SRCHTXT}").val(lecname);

    self.close();
}
//search_subject
function fn_select_subject() {
    $.ajax({
        type: "GET",
        url : '<c:url value="/subject/find_category_subject.json"/>?SEARCHCATEGORY='+encodeURIComponent($("#SEARCHKIND").val()),
        //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        //contentType: 'application/json',
        mimeType: 'application/json',
        async : false,
        success: function(RES) {
            if(RES.length  > 0){
                $('#SEARCHSBJTCD').find('option').remove();
                $.each(RES,function(idx, data){
                    var list = "";
                    list +="<option value='" + data.SUBJECT_CD +"'>" + data.SUBJECT_NM + "</option>";
                    $('#SEARCHSBJTCD')
                        .find('option')
                        .end()
                        .prepend(list)
                        .val(data.SUBJECT_CD);
                });
                $('#SEARCHSBJTCD').find('option').end().prepend("<option value=''>--전체검색--</option>").val("");
            }else {
                alert("검색결과가 없습니다.")
                return;
            }
        },error: function(){
            alert("검색 실패");
            return;
        }
    });
}
//search_teacher
function fn_select_teacher() {
    $.ajax({
        type: "GET",
        url : '<c:url value="/teacher/find_teacher_list.json"/>?SEARCHCATEGORY='+encodeURIComponent($("#SEARCHKIND").val())
              +'&SEARCHSBJTCD='+encodeURIComponent($("#SEARCHSBJTCD").val()),
        //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        //contentType: 'application/json',
        mimeType: 'application/json',
        async : false,
        success: function(RES) {
            if(RES.length  > 0){
                $('#SEARCHPRFID').find('option').remove();
                $.each(RES,function(idx, data){
                    var list = "";
                    list +="<option value='" + data.USER_ID +"'>" + data.USER_NM + "</option>";
                    $('#SEARCHPRFID')
                        .find('option')
                        .end()
                        .prepend(list)
                        .val(data.USER_ID);
                });
                $('#SEARCHPRFID').find('option').end().prepend("<option value=''>--전체검색--</option>").val("");
            }else {
                alert("검색결과가 없습니다.")
                return;
            }
        },error: function(){
            alert("검색 실패");
            return;
        }
    });
}
</script>