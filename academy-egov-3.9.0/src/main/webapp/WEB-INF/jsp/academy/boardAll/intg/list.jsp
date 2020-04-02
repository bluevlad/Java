<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
$(document).ready(function(){
    setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
    initDatePicker1("searchStartDate"); 
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
    initDatePicker1("searchEndDate");
    $('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

//숫자 입력 폼
function chk(obj){
    var val = obj.value;
    if (val) {       
        if (val.match(/^\d+$/gi) == null) {          
            $('#pageRow').val("");      
            $('#pageRow').focus();         
            return;       
        } else {          
            if (val < 1) {             
                $('#pageRow').val("");          
                $('#pageRow').focus();            
                return;          
            }       
        }    
    }
}

//엔터키 검색
function fn_checkEnter(){
    $('#SEARCHKEYWORD').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
    $('#searchText').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });    
    $('#pageRow').keyup(function(e)  {
        if(e.keyCode == 13) {
            goList(1);
        }
    });
}
//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
    if(event.keyCode == 13){
        goList(1);
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

function goList(page) {

    var codeStr = "";
    $("#codeList input[type=checkbox]:checked").each(function() {
        checkboxValue = $(this).val();
        codeStr+= checkboxValue+",";
    });
    //var searchCategory = $("#searchCategory").val();
    var searchCategory = codeStr;

    var searchKind = $("#searchKind").val();
    var searchText = $("#searchText").val();
    var sDate = $("#searchStartDate").val();
    var eDate = $("#searchEndDate").val();
     $("#SEARCHCATEGORY").val(searchCategory);
    $("#SEARCHKIND").val(searchKind);
    $("#SEARCHTEXT").val(searchText);
    $("#SDATE").val(sDate);
    $("#EDATE").val(eDate);
    
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);

    $('#form').attr('target' ,'_self');
    $("#form").attr("action", "<c:url value='/intgboard/list.do' />");
    $("#form").submit();
}

function view(BOARD_MNG_SEQ, BOARDVIEW_SEQ, BOARDVIEWPARENT_SEQ, BOARDVIEWCODENAME) {
    $("#BOARD_MNG_SEQ").val(BOARD_MNG_SEQ);
    $("#BOARDVIEW_SEQ").val(BOARDVIEW_SEQ);
    $("#BOARDVIEWPARENT_SEQ").val(BOARDVIEWPARENT_SEQ);
    $("#BOARDVIEWCODENAME").val(BOARDVIEWCODENAME);


    $("#currentPage").val('1');
    $("#SEARCHCATEGORY").val('');
    $("#SEARCHTYPE").val('');
    $("#SEARCHKEYWORD").val('');
    $("#SEARCHKIND").val('');
    $("#SEARCHTEXT").val('');

    if(BOARD_MNG_SEQ.indexOf("FAQ") != -1){
        window.open('', 'viewBoard', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=800 ');
        $('#form').attr('target' ,'viewBoard');
        $('#form').attr('action','<c:url value="/board/boardFAQView.pop"/>');
        $("#form").submit();
    }
    else if(BOARD_MNG_SEQ.indexOf("NOTICE") != -1 || BOARD_MNG_SEQ.indexOf("BOARD") != -1){
        window.open('', 'viewBoard', 'scrollbars=yes,toolbar=no,resizable=yes,width=1200,height=800 ');
        $('#form').attr('target' ,'viewBoard');
        $('#form').attr('action','<c:url value="/board/boardView.pop"/>');
        $("#form").submit();
    }
}
</script>
</head>

  <!--content -->
  <div id="content">
    <h2>● 게시판관리 > <strong>통합게시판</strong></h2>

<form id="form" name="form" method="post">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">

    <input type="hidden" id="SEARCHONOFFDIV" name="SEARCHONOFFDIV" value="${params.SEARCHONOFFDIV}"/>
    <input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value=""/>
    <input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value=""/>
    <input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value=""/>
    <input type="hidden" id="SDATE" name="SDATE" value=""/>
    <input type="hidden" id="EDATE" name="EDATE" value=""/>
    <input type="hidden" id="BOARD_MNG_SEQ" name="BOARD_MNG_SEQ" value="">
    <input type="hidden" id="BOARDVIEW_SEQ" name="BOARDVIEW_SEQ" value="">
    <input type="hidden" id="BOARDVIEWPARENT_SEQ" name="BOARDVIEWPARENT_SEQ" value="">
    <input type="hidden" id="BOARDVIEWCODENAME" name="BOARDVIEWCODENAME" value="">
    
    <!--테이블-->
        <table class="table01">
        <tr>
            <th width="10%">게시판명/ID</th>
            <td>
                <label for="SEARCHTYPE"></label>
                <select style="width:100px;" id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value="">전체</option>
                    <option value="BRDID"  <c:if test="${params.SEARCHTYPE == 'BRDID'}">selected</c:if>>게시판ID</option>
                    <option value="BRDNM"  <c:if test="${params.SEARCHTYPE == 'BRDNM'}">selected</c:if>>게시판명</option>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input type="text" class="i_text"  title="게시판명/ID" id="SEARCHKEYWORD" name=SEARCHKEYWORD  type="text" size="35"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            </td>
            <th width="10%">게시판유형</th>
            <td colspan="3">
                <select style="width:100px;" name="SEARCHBRDTYPE" id="SEARCHBRDTYPE">
                    <option value="" <c:if test="${params.SEARCHBRDTYPE == '' }">selected="selected"</c:if>>전체</option>
                    <option value="NOTICE" <c:if test="${params.SEARCHBRDTYPE == 'NOTICE' }">selected="selected"</c:if>>공지사항</option>
                    <option value="FAQ" <c:if test="${params.SEARCHBRDTYPE == 'FAQ' }">selected="selected"</c:if>>FAQ</option>
                    <option value="BOARD" <c:if test="${params.SEARCHBRDTYPE == 'BOARD' }">selected="selected"</c:if>>일반게시판</option>
                </select>
            </td>
        </tr>
        <tr>
            <th width="10%">직종구분</th>
            <td colspan="5">
                <div class="item" id="codeList">
                <c:forEach items="${rankList}"  var="data" varStatus="status" >
                    <c:set var="vChecked">
                       <c:choose>
                        <c:when test="${fn:contains(params.SEARCHCATEGORY, data.CODE)}">checked="checked"</c:when>
                         <c:otherwise></c:otherwise>
                       </c:choose>
                     </c:set>
                    <input type="checkbox" name="searchCategory" class="i_check" value="${data.CODE}" <c:out value='${vChecked}'/> ><label for="a1">${data.NAME }</label>
                </c:forEach>
                </div>
            </td>
        </tr>
        <tr>
            <th width="10%">검색</th>
            <td>
                <select style="width:100px;" name="searchKind" id="searchKind">
                    <option value="SEARCHSUBJECT" <c:if test="${params.SEARCHKIND == 'SEARCHSUBJECT' }">selected="selected"</c:if>>제목</option>
                    <option value="SEARCHNAME" <c:if test="${params.SEARCHKIND == 'SEARCHNAME' }">selected="selected"</c:if>>작성자</option>
                </select>
                <input type="text" class="i_text" title="검색" name="searchText" id="searchText" size="35" value="${params.SEARCHTEXT}" onKeyPress="fn_checkEnter()" />
            </td>
            <th width="10%">작성일</th>
            <td>
                <input type="text" id="searchStartDate" name="searchStartDate" value="${params.SDATE}" style="width:20;" maxlength="8" readonly="readonly"/> 
                ~
                <input type="text" id="searchEndDate" name="searchEndDate" value="${params.EDATE}" style="width:20;" maxlength="8" readonly="readonly"/>
            </td>
            <th width="10%">화면출력건수</th>
            <td width="10%">               
                <input type="text"   size="5" title="검색" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${pageRow}" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
        </tr>
        </table>
        
     <!--//테이블-->
        <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
        <table class="table02">
            <tr>
                <th scope="col" width="5%">No</th>
                <th scope="col" width="10%">게시판ID</th>
                <th scope="col" width="10%">게시판명</th>
                <th scope="col" width="10%">게시판유형</th>
                <th scope="col">직종구분</th>
                <th scope="col">제목</th>
                <th scope="col" width="7%">첨부</th>
                <th scope="col" width="7%">작성자</th>
                <th scope="col" width="10%">작성일</th>
                <th scope="col" width="5%">조회수</th>
            </tr>
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="data" varStatus="status">
            <tr>
                <td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
                <td>${data.BOARD_MNG_SEQ}</td>
                <td>${data.BOARD_MNG_NAME}</td>
                <td>${data.BOARD_MNG_TYPE_NM}</td>
                <td>${data.CODENAME}</td>
                <td style="text-align:left; padding-left:${data.LISTLEVEL*10}px;" >
                    <a href='javascript:view("${data.BOARD_MNG_SEQ}","${data.BOARD_SEQ}","${data.PARENT_BOARD_SEQ}", "${data.CODENAME}");'>
                        <c:if test="${data.PARENT_BOARD_SEQ ne '0'}"><img src="<c:url value="/resources/images//re.gif"/>" align="absmiddle" /><c:out value="${data.SUBJECT}"/></c:if>
                        <c:if test="${data.PARENT_BOARD_SEQ eq '0'}">
                            <c:if test="${data.NOTICE_TOP_YN == 'Y'}">
                                <strong style="color:black;"><c:out value="${data.SUBJECT}"/></strong>
                            </c:if>
                            <c:if test="${data.NOTICE_TOP_YN == 'N'}">
                               <c:out value="${data.SUBJECT}"/>
                            </c:if>
                        </c:if>
                    </a>
                </td>
                <td><c:if test="${data.FILE_NAME ne null}"><img src="<c:url value="/resources/img/common/icon_disk01.png"/>" width="16" height="16" /></c:if></td>
                <td><c:out value="${data.USER_NM}"/></td>
                <td>${fn:substring(data.REG_DT, 0, 10)}</td>
                <td><fmt:formatNumber value="${data.HITS}" type="number"/></td>
            </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center> 
                <td colspan="10">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if> 
       </table>
</form>             

    <!--paging  -->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!-- //paging  -->

</div>
<!--//content --> 
