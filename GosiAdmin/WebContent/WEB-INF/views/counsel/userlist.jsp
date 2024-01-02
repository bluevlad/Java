<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
<script type="text/javascript">
    //상담일정 조회
    function listCounsel() {
        $('#frm').attr('action', '<c:url value="/counsel/list.do"/>').submit();
    }
    //회원 상세
    function usrView(userid, sch_day, ts_idx, cat_cd) {
        var url = '<c:url value="/counsel/MemberView.pop"/>'
                + '?USER_ID=' + userid + '&SCH_DAY=' + sch_day + '&TS_IDX=' + ts_idx + '&CAT_CD=' + cat_cd
        window.open(url, '_blank', 'location=no,resizable=no,width=820,height=600,top=50,left=50,scrollbars=yes,location=no,scrollbars=yes');
    }
    //엔터키 검색
    function fn_checkEnter(){
        $('#SEARCHKEYWORD').keyup(function(e)  {
            if(e.keyCode == 13) {
                goList(1);
            }
        });
        
        $('#subPageRow').keyup(function(e)  {
            if(e.keyCode == 13) {
                goList(1);
            }
        });
    }
    //검색
    function goList(page) {
        var codeStr = "";
        $("#codeList input[type=checkbox]:checked").each(function() {
            checkboxValue = $(this).val();
            codeStr+= checkboxValue+",";
        });
        var searchCategory = codeStr;

        $("#SEARCHCATEGORY_S").val(searchCategory);

        if(typeof(page) == "undefined") $("#subCurPage").val(1);
        else $("#subCurPage").val(page);
        
        $('#frm').attr('action','<c:url value="/counsel/userlist.do"/>').submit();
    }
</script>
</head>

<!--content -->
<div id="content">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${params.TOP_MENU_ID}" /> 
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${params.MENUTYPE}" /> 
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${params.MENU_NM}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${params.L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">
<input type="hidden" id="SEARCHCATEGORY" name="SEARCHCATEGORY" value="${params.SEARCHCATEGORY}">
<input type="hidden" id="subCurPage" name="subCurPage" value="${params.subCurPage}">
<input type="hidden" id="SCH_DAY" name="SCH_DAY" value="${params.SCH_DAY}" />
<input type="hidden" id="CAT_CD" name="CAT_CD" value="${params.CAT_CD}" />
    <h2>● ${params.L_MENU_NM} > <strong>${params.MENU_NM}</strong></h2>
    <table class="table01">
        <tr>
            <th>상담직종</th>
            <td colspan="3">
            <div class="item" id="codeList">
                <c:forEach items="${rankList}"  var="data" varStatus="status" >
                    <c:set var="vChecked">
                       <c:choose>
                        <c:when test="${fn:contains(params.SEARCHCATEGORY_S, data.CODE)}">checked="checked"</c:when>
                         <c:otherwise></c:otherwise>
                       </c:choose>
                     </c:set>
                    <input type="checkbox" id="SEARCHCATEGORY_S" name="SEARCHCATEGORY_S" class="i_check" value="${data.CODE}" <c:out value='${vChecked}'/>><label for="a1">${data.NAME}</label>
                </c:forEach>
            </div>
            </td>
        </tr>
        <tr>
            <th width="15%">검색</th>
            <td>            
                <label for="SEARCHTYPE"></label>
                <select style="width:100px;"   id="SEARCHTYPE" name="SEARCHTYPE">
                    <option value=""  <c:if test="${params.SEARCHTYPE == ''}">selected</c:if>>-전체-</option>
                    <option value="USER_ID"  <c:if test="${params.SEARCHTYPE == 'USER_ID'}">selected</c:if>>회원ID</option>
                    <option value="USER_NM"  <c:if test="${params.SEARCHTYPE == 'USER_NM'}">selected</c:if>>이름</option>
                </select>
                <label for="SEARCHKEYWORD"></label>
                <input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="40"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
            <th width="15%">화면출력건수</th>
            <td width="30%">               
                <input   size="5" title="검색" type="text" id="subPageRow" name="subPageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.subPageRow}" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
        </tr>
    </table>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:listCounsel();">상담시간표 조회</a></li>
    </ul>

    <!-- 테이블 -->
    <table class="table02">
        <tr>
            <th>No</th>
            <th>상담일</th>
            <th>회차</th>
            <th>이름(ID)</th>
            <th>구분</th>
            <th>신청일시</th>
        </tr>
        <tbody>
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="data" varStatus="status">
            <tr>
                <td>${(status.index+1)}</td>
                <td>${data.SCH_DAY}</td>
                <td>${data.TIME_SET}</td>
                <td><a href="javascript:usrView('${data.USER_ID}', '${data.SCH_DAY}', '${data.TS_IDX}', '${data.USER_CATEGORY}')">${data.USER_NM}(${data.USER_ID})</a></td>
                <td>${data.USER_CATEGORY_NM}/${data.USER_CODE1_NM}<c:if test="${data.USER_CODE2_NM ne null and data.USER_CODE2_NM ne ''}">,${data.USER_CODE2_NM}</c:if></td>
                <td>
                    ${data.REG_DT}
                    <c:if test="${data.RESERVE == 'N'}">
                    <br><font color="red">(${data.CANCEL_DATE} 취소)</font>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
                <td colspan="7">상담을 신청한 인원이 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <!--//테이블--> 
    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${subCurPage}"/>/<c:out value="${totalPage}"/>)</span></p>
   
    <!-- paginate-->
    <c:if test="${not empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->

</form>
</div>
<!--//content -->
