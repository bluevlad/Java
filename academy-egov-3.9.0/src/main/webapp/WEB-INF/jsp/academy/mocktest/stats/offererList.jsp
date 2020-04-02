<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head></head>

<div id="content">
  <h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
<form id="form" name="form" method="post" action="<c:url value='/stats/statsPersonList.do' />">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>

    <!--테이블-->
    <table class="table01">
        <caption></caption>
        <colgroup>
        <col width="15%">
        <col width="*">
        <col width="15%">
        <col width="15%">
        </colgroup>
        <tr>
            <th>검색</th>
            <td>
                <select style="width:85px;" id="SEARCHYEAR" name="SEARCHYEAR">
                    <option value="">년도</option>
	                <c:forEach var="i" begin="2016" end="2020" step="1">
	                <option value="${i}" <c:if test="${params.SEARCHYEAR == i}">selected</c:if>><c:out value="${i}"/></option>
					</c:forEach>
                </select>&nbsp;
                <select style="width:85px;" id="SEARCHROUND" name="SEARCHROUND">
                    <option value="">회차</option>
	                <c:forEach var="i" begin="1" end="30" step="1">
	                <option value="${i}" <c:if test="${params.SEARCHROUND == i}">selected</c:if>><c:out value="${i}"/></option>
					</c:forEach>
                </select>&nbsp;
                <select style="width:85px;" id="SEARCHEXAMTYPE" name="SEARCHEXAMTYPE">
                    <option value="">전체검색</option>
                    <option value="0" <c:if test="${params.SEARCHEXAMTYPE == '0' }">selected="selected"</c:if>>온라인</option>
                    <option value="1" <c:if test="${params.SEARCHEXAMTYPE == '1' }">selected="selected"</c:if>>오프라인</option>
                </select>&nbsp;
                <select style="width:85px;" id="SEARCHCLASSCODE" name="SEARCHCLASSCODE" onchange="getSubCODE('')">
                    <option value="">전체검색</option>
                    <c:forEach items="${registration_list}"  var="registration_list">
                        <option value="${registration_list.CODE }" <c:if test="${params.SEARCHCLASSCODE == registration_list.CODE}">selected</c:if>>${registration_list.NAME }</option>
                    </c:forEach>
                </select>&nbsp;
                <select style="width:85px;" id="SEARCHCLASSSERIESCODE" name="SEARCHCLASSSERIESCODE" onchange="getSubCODE2('')">
                    <option value="">전체검색</option>
                </select>&nbsp;
                <select style="width:85px;" id="SEARCHSUBJECT" name="SEARCHSUBJECT">
                    <option value="">전체검색</option>
                </select>&nbsp;&nbsp;
            </td>
            <th>화면출력건수</th>
            <td>
                <input title="검색" type="text" id="pageRow" name="pageRow" size="5" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow}" onkeypress="fn_RowNumCheck()">
                <span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="javascript:fn_Search();">검색</button></span>
            </td>
        </tr>
    </table>
    <!--//테이블-->

     <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Excel();">엑셀다운로드</a></li>
    </ul>
    <!--//버튼-->
    Off모의고사접수인원:${offCntY}명, 취소:${offCntN}명 &nbsp;&nbsp;&nbsp;&nbsp; On모의고사접수인원:${onCntY}명, 취소:${onCntN}명
    <!--테이블-->
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="">
            <col width="5%">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
            <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col"><div class="item">NO</div></th>
            <th scope="col">주문번호</th>
            <th scope="col">년</th>
            <th scope="col">회차</th>
            <th scope="col">모의고사명</th>
            <th scope="col">과목</th>
            <th scope="col">ON/OFF</th>
            <th scope="col">수험번호</th>
            <th scope="col">성명</th>
            <th scope="col">전화번호</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="item" varStatus="index">
        <tr>
            <td>${totalCount - (( params.currentPage - 1) * params.pageRow) - index.index}</td>
            <td>${item.ORDERNO}</td>
            <td>${item.EXAMYEAR}</td>
            <td>${item.EXAMROUND}</td>
            <td>${item.MOCKNAME}</td>
            <td>${item.SUBJECT_NM}</td>
            <td>
                <c:if test="${item.EXAMTYPE eq 0 }">온라인</c:if>
                <c:if test="${item.EXAMTYPE eq 1 }">오프라인</c:if>
            </td>
            <td>${fn:substring(item.IDENTYID, 4, 13)}</td>
            <td>${item.USER_NM}</td>
            <td>${item.PHONE_NO}</td>
        </tr>
        </c:forEach>
        <c:if test="${empty list}">
        <tr bgColor=#ffffff align=center>
            <td colspan="10">데이터가 존재하지 않습니다.</td>
        </tr>
        </c:if>
        </tbody>
    </table>
    <!--//테이블-->

    <!-- paginate-->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->
</form>
</div>
<!--//content -->

<script type="text/javascript">
function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#form").attr("action", "<c:url value='/stats/statsOffererList.do' />");
    $("#form").submit();
}

function fn_Search() {
    $("#currentPage").val(1);
    $("#form").attr("action", "<c:url value='/stats/statsOffererList.do' />");
    $("#form").submit();
}

function fn_Excel() {
    $("#currentPage").val(1);
    $("#form").attr("action", "<c:url value='/stats/statsOffererListExcel.do' />");
    $("#form").submit();
}

//비동기 직렬 가져오기
function getSubCODE(val) {
    var CLASSCODE = "";
    if(val == ""){
        CLASSCODE = $("#SEARCHCLASSCODE option:selected").val();
    }else{
        CLASSCODE = "${params.SEARCHCLASSCODE}";
    }
    if(CLASSCODE == "" || CLASSCODE == undefined) {
        return false;
    }

    var appendStr = "";
    $.ajax({
        type : "GET",
        url : "<c:url value='/mouigosa/reg/subCode2.do'/>" + '?CLASSCODE=' + CLASSCODE,
        dataType : "json",
        async : false,
        success : function(json){
            $("#SEARCHCLASSSERIESCODE").empty().data("options");
            $("#SEARCHCLASSSERIESCODE").append("<option value=''>전체검색</option>");
            $("#SEARCHSUBJECT").empty().data("options");
            $("#SEARCHSUBJECT").append("<option value=''>전체검색</option>");
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa){
                    appendStr += "<option value=\"" + mouigosa.CODE + "\">" + mouigosa.NAME + "</option>";
                });
                $("#SEARCHCLASSSERIESCODE").append(appendStr);
                if(val != ""){
                    <c:if test="${!empty params.SEARCHCLASSSERIESCODE}">
                        $("#SEARCHCLASSSERIESCODE").val("${params.SEARCHCLASSSERIESCODE}");
                        getSubCODE2("m");
                    </c:if>
                }
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });
}

//과목 가져오기
function getSubCODE2(val) {
    var CLASSCODE = "";
    var CLASSSERIES = "";
    if(val == ""){
        CLASSCODE = $("#SEARCHCLASSCODE option:selected").val();
        CLASSSERIES = $("#SEARCHCLASSSERIESCODE option:selected").val();
    }else{
        CLASSCODE = "${params.SEARCHCLASSCODE}";
        CLASSSERIES = "${params.SEARCHCLASSSERIESCODE}";
    }

    if(CLASSCODE == "" || CLASSCODE == undefined) {
        return false;
    }

    var appendStr = "";
    $.ajax({
        type : "GET",
        url : "<c:url value='/stats/getSubjectList.do'/>" + '?SEARCHCLASSCODE=' + CLASSCODE + '&SEARCHCLASSSERIESCODE=' + CLASSSERIES,
        dataType : "json",
        async : false,
        success : function(json){
            $("#SEARCHSUBJECT").empty().data("options");
            $("#SEARCHSUBJECT").append("<option value=''>전체검색</option>");
            if(json && json.length > 0) {
                $(json).each(function(index, mouigosa){
                    appendStr += "<option value=\"" + mouigosa.ITEMID + "\">" + mouigosa.SUBJECT_NM + "</option>";
                });
                $("#SEARCHSUBJECT").append(appendStr);
            }
            if(val != ""){
                <c:if test="${!empty params.SEARCHSUBJECT}">
                    $("#SEARCHSUBJECT").val("${params.SEARCHSUBJECT}");
                </c:if>
            }
        },
        error: function(d, json){
            alert("error: "+d.status);
        }
    });
}

$(document).ready(function(){
    <c:if test="${!empty params.SEARCHCLASSSERIESCODE}">
        getSubCODE();
    </c:if>
});

</script>