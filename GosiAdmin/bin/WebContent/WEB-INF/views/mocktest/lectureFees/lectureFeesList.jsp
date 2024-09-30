<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
$(function() {
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDateFicker2("sDate", "eDate");
});

function fn_Search() {
    if($("#sDate").val()=='' || $("#eDate").val()==''){
        $("#SEARCHSDATE,#SEARCHEDATE").val("");
    }
    if($("#sDate").val()!='' || $("#eDate").val()!=''){
        if($("#sDate").val()!='' && $("#eDate").val()!=''){
            if(parseInt($("#eDate").val().replace(/-/g,'')) < parseInt($("#sDate").val().replace(/-/g,''))){
                alert('검색 종료일은 시작일보다 큰 날짜를 선택하세요.');
                return;
            }           
        }       
        $("#SEARCHSDATE").val($("#sDate").val().replace(/\-/ig, '/'));
        $("#SEARCHEDATE").val($("#eDate").val().replace(/\-/ig, '/'));      
    }
    $("#form").attr("action", "<c:url value='/lectureFees/lectureFeesList.do' />");
    $("#form").submit();
}

function fn_excelDownload(val  , name){
    if($("#sDate").val()=='' || $("#eDate").val()==''){
        $("#SEARCHSDATE,#SEARCHEDATE").val("");
    }
    if($("#sDate").val()!='' || $("#eDate").val()!=''){
        if($("#sDate").val()!='' && $("#eDate").val()!=''){
            if(parseInt($("#eDate").val().replace(/-/g,'')) < parseInt($("#sDate").val().replace(/-/g,''))){
                alert('종료일은 시작일보다 큰 날짜를 선택하세요.');
                return;
            }           
        }       
        $("#SEARCHSDATE").val($("#sDate").val().replace(/\-/ig, '/'));
        $("#SEARCHEDATE").val($("#eDate").val().replace(/\-/ig, '/'));      
    }
    $("#PROFCODE").val(val);
    $("#TMEMBERNAME").val(name);
//    $("#form").attr("action", "<c:url value='/lectureFees/lectureFeesExcelDownLoad.do' />");
    $("#form").attr("action", "<c:url value='/lectureFees/lectureFeesExcelDownLoad.do' />");
    $("#form").submit();    
}

function fn_detailView(val  , name){
    if($("#sDate").val()=='' || $("#eDate").val()==''){
        $("#SEARCHSDATE,#SEARCHEDATE").val("");
    }
    if($("#sDate").val()!='' || $("#eDate").val()!=''){
        if($("#sDate").val()!='' && $("#eDate").val()!=''){
            if(parseInt($("#eDate").val().replace(/-/g,'')) < parseInt($("#sDate").val().replace(/-/g,''))){
                alert('종료일은 시작일보다 큰 날짜를 선택하세요.');
                return;
            }           
        }       
        $("#SEARCHSDATE").val($("#sDate").val().replace(/\-/ig, '/'));
        $("#SEARCHEDATE").val($("#eDate").val().replace(/\-/ig, '/'));      
    }
    $("#PROFCODE").val(val);
    $("#TMEMBERNAME").val(name);
    $("#form").attr("action", "<c:url value='/lectureFees/lectureFeesDetailView.do' />");
    $("#form").submit();    
}

function onOnlyNumber(obj) {
    for (var i = 0; i < obj.value.length ; i++){
      chr = obj.value.substr(i,1);  
      chr = escape(chr);
      key_eg = chr.charAt(1);
      if (key_eg == "u"){
        key_num = chr.substr(i,(chr.length-1));   
        if((key_num < "AC00") || (key_num > "D7A3")) { 
          event.returnValue = false;
        }    
      }
    }
    if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
    } else {
      event.returnValue = false;
    }
} 

//엔터키 검색
function fn_checkEnter(){
    alert(1);
    $('#SEARCHTEXT').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}

function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#form").attr("action", "<c:url value='/lectureFees/lectureFeesList.do' />");
    $("#form").submit();
}
</script>
</head>

<div id="content">
    <h2>● 모의고사 > <strong>강사료정산</strong></h2>

<form id="form" name="form" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEARCHSDATE" name="SEARCHSDATE" value="${params.SEARCHSDATE}"/>
<input type="hidden" id="SEARCHEDATE" name="SEARCHEDATE" value="${params.SEARCHEDATE}"/>
<input type="hidden" id="PROFCODE" name="PROFCODE" value=""/>
<input type="hidden" id="TMEMBERNAME" name="TMEMBERNAME" value=""/>

    <!-- 검색-->
    <table class="table01">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="30%">
            <col width="15%">
            <col width="">
        </colgroup>
        <tr>
            <th>검색</th>
            <td>
                <input id="sDate" name="sDate" type="text" maxlength="10" class="i_text" value="${fn:replace(params.SEARCHSDATE,'/','-')}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
                ~ <input id="eDate" name="eDate" type="text" maxlength="10" class="i_text" value="${fn:replace(params.SEARCHEDATE,'/','-')}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>                    
                &nbsp;&nbsp;
            </td>
            <th>강사</th>
            <td>
                <input type="text" name="SEARCHTEXT" id="SEARCHTEXT" class="i_text" style="width:160px;" title="레이블 텍스트" value="${params.SEARCHTEXT}">
                <span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="javascript:fn_Search();" onkeypress="javascript:fn_checkEnter();">검색</button></span>
            </td>
        </tr>
    </table>
    <!-- 검색-->

    <!--버튼-->
     <!--//버튼-->  
    
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="6%">
            <col width="6%">
            <col width="30%">
            <col width="30%">
            <col width="8%">
            <col width="10%">
            <col width="3%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">순번</th>
            <th scope="col">강사명</th>
            <th scope="col">수강료 합계</th>
            <th scope="col">강사지급액</th>
            <th scope="col">수강인원</th>
            <th scope="col">Excel</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty list}">
        <input type="hidden" id="currentPage" name="currentPage">
        <c:forEach items="${list}" var="data" varStatus="status">
        <tr>
            <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
            <td>
                <a href='javascript:fn_detailView("${data.PROFCODE}","${data.TMEMBERNAME}");'>
                    <c:out value="${data.TMEMBERNAME}"/>
                </a>
            </td>
            <td><c:out value="${data.MONEY}"/>원</td>
            <td><c:out value="${data.ALLOWANCE}"/>원</td>
            <td><c:out value="${data.CNT}"/>명</td>
            <td>
                    <span class="btn_pack medium"><button type="button" onclick='javascript:fn_excelDownload("${data.PROFCODE}","${data.TMEMBERNAME}");'>Excel</button></span>
            </td>
        </tr>
        </c:forEach>
        </c:if>
        <c:if test="${empty list}">
        <tr bgColor=#ffffff align=center> 
            <td colspan="7">데이터가 존재하지 않습니다.</td>
        </tr>
        </c:if> 
        </tbody>
    </table>
   </form> 

    <!--paging  -->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!-- //paging  -->

</div>
<!--//content --> 
