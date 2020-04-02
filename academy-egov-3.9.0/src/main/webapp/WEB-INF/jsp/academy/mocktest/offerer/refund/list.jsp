<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head></head>

<div id="content">
    <h2>● 신청자관리 > <strong>환불관리</strong></h2>
    <form id="form" name="form" method="get" action="">
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
    <input type="hidden" id="pageRow" name="pageRow" value="${pageRow}">
    <input type="hidden" id="SEARCHSDATE" name="SEARCHSDATE" value="${params.SEARCHSDATE}"/>
    <input type="hidden" id="SEARCHEDATE" name="SEARCHEDATE" value="${params.SEARCHEDATE}"/>
    <input type="hidden" id="IDENTYID" name="IDENTYID" value=""/>

    <!-- 검색-->
    <table class="table01" summary="검색">
        <caption></caption>
        <colgroup>
            <col width="15%">
            <col width="85%">
        </colgroup>
        <tr>
            <th>검색</th>
            <td>
                <input id="sDate" name="sDate" type="text" maxlength="10" class="i_text" value="${fn:replace(params.SEARCHSDATE,'/','-')}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
                ~ <input id="eDate" name="eDate" type="text" maxlength="10" class="i_text" value="${fn:replace(params.SEARCHEDATE,'/','-')}" readonly="readonly" style="width:90px;IME-MODE:disabled;" onKeyDown="onOnlyNumber(this);"/>
                &nbsp;&nbsp;
                <select name="SEARCHEXAMTYPE" id="SEARCHEXAMTYPE">
                    <option value="">시험구분</option>
                    <option value="0" <c:if test="${params.SEARCHEXAMTYPE == '0' }">selected="selected"</c:if>>온라인</option>
                    <option value="1" <c:if test="${param.SEARCHEXAMTYPE == '1' }">selected="selected"</c:if>>오프라인</option>
                </select>
                <select name="SEARCHPAYMENT" id="SEARCHPAYMENT">
                    <option value="">결제상태</option>
                    <!-- <option value="0" <c:if test="${params.SEARCHPAYMENT == '0' }">selected="selected"</c:if>>대기중</option> -->
                    <option value="1" <c:if test="${params.SEARCHPAYMENT == '1' }">selected="selected"</c:if>>결제완료</option>
                    <option value="2" <c:if test="${params.SEARCHPAYMENT == '2' }">selected="selected"</c:if>>환불완료</option>
                    <option value="3" <c:if test="${params.SEARCHPAYMENT == '3' }">selected="selected"</c:if>>취소완료</option>
                </select>
                <input type="text" name="SEARCHTEXT" id="SEARCHTEXT" class="i_text" style="width:160px;" title="레이블 텍스트" value="${params.SEARCHTEXT}">
                <span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="javascript:fn_Search();">검색</button></span>
            </td>
        </tr>
    </table>
     <!-- 검색-->

     <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Excel();">엑셀다운로드</a></li>
    </ul>
    <!--//버튼-->

    <p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <table class="table02">
        <caption></caption>
        <colgroup>
            <col width="30px">
            <col width="60px">
            <col width="75px">
            <col width="60px">
            <col width="70px">
            <col width="60px">
            <col width="80px">
            <col width="70px">
            <col width="*">
            <col width="80px">
            <col width="70px">
            <col width="50px">
            <col width="70px">
            <col width="60px">
        </colgroup>
        <thead>
            <tr>
                <th scope="col"><div class="item">NO</div></th>
                <th scope="col">주문번호</th>
                <th scope="col">시험구분</th>
                <th scope="col">이름<br/>ID</th>
                <th scope="col">응시번호</th>
                <th scope="col">연락처</th>
                <th scope="col">신청구분</th>
                <th scope="col">등록일</th>
                <th scope="col">상품명</th>
                <th scope="col">결제금액</th>
                <th scope="col">결제상태</th>
                <th scope="col">결제<br/>구분</th>
                <th scope="col">결제일</th>
                <th scope="col">응시표<br/>출력</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="item" varStatus="index">
            <tr>
                <td>
                    ${totalCount - (( params.currentPage - 1) * params.pageRow) - index.index}
                </td>
                <td><a href="javascript:fn_View('${item.IDENTYID}');" style="color:blue;">${item.ORDERNO}</a></td>
                <td>
                    <c:if test="${item.EXAMTYPE eq 0 }">온라인</c:if>
                    <c:if test="${item.EXAMTYPE eq 1 }">오프라인</c:if>
                </td>
                <td>${item.USER_NM}<br/>${item.USER_ID}</td>
                <td>${item.IDENTYID}</td>
                <td>${item.PHONE_NO}</td>
                <td>
                    <c:if test="${item.RECEIPTTYPE eq 0 }">온라인접수</c:if>
                    <c:if test="${item.RECEIPTTYPE eq 1 }">방문접수</c:if>
                    <c:if test="${item.RECEIPTTYPE eq 2 }">데스크접수</c:if>
                </td>
                <td>${fn:substring(item.REG_DT,2,10)}</td>
                <td>
                    <c:if test="${empty item.EXAMSTATUS || item.EXAMSTATUS eq 0 }">
                        <a href="javascript:fn_Modify('${item.IDENTYID}');" style="color:blue;">${item.MOCKNAME}</a>
                    </c:if>
                    <c:if test="${!empty item.EXAMSTATUS && item.EXAMSTATUS ne 0 }">
                        ${item.MOCKNAME}
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.PAYMENTSTATE ne 0}">
                        <%-- <a href="javascript:fn_Refund('${item.IDENTYID}');" style="color:blue;"><fmt:formatNumber value="${item.PAYMENTAMOUNT}" pattern="##,###.##" /></a> --%>
                        <c:if test="${empty item.PAYMENTTARGETAMOUNT}"> 
                        	<a href="javascript:fn_Refund('${item.IDENTYID}');" style="color:blue;"><fmt:formatNumber value="${item.PAYMENTAMOUNT}" pattern="##,###.##" /></a>
                        </c:if>
                        <c:if test="${!empty item.PAYMENTTARGETAMOUNT ne ''}"> 
                        	<a href="javascript:fn_Refund('${item.IDENTYID}');" style="color:blue;"><fmt:formatNumber value="${item.PAYMENTTARGETAMOUNT}" pattern="##,###.##" /></a>
                        </c:if>
                        
                    </c:if>
                    <%-- <c:if test="${item.PAYMENTSTATE eq 2 }">
                        <br/><a href="javascript:fn_Refund('${item.IDENTYID}');" style="color:red;">-<fmt:formatNumber value="${item.PAYMENTAMOUNT}" pattern="##,###.##" /></a>
                    </c:if> --%>
                </td>
                <td>
                    <c:if test="${item.PAYMENTSTATE eq 0 }">대기중</c:if>
                    <c:if test="${item.PAYMENTSTATE eq 1 }">결제완료</c:if>
                    <c:if test="${item.PAYMENTSTATE eq 2 }">환불완료</c:if>
                    <c:if test="${item.PAYMENTSTATE eq 3 }">취소완료</c:if>
                </td>
                <td>
                    <c:if test="${item.PAYMENTTYPE eq 0 }">카드</c:if>
                    <c:if test="${item.PAYMENTTYPE eq 1 }">현금</c:if>
                    <c:if test="${item.PAYMENTTYPE eq 2 }">예금</c:if>
                </td>
                <td>${fn:substring(item.PCREATETIME,2,10)}</td>
                <c:if test="${item.PRINTFLAG eq 0 }">
                    <td><a href="javascript:fn_Print('${item.IDENTYID}','${item.EXAMCODE}');" style="color:blue;">미출력</a></td>
                </c:if>
                <c:if test="${item.PRINTFLAG == 1 }">
                    <td title="출력아이디 : ${item.PRINTUSER}&#13;출력시간 : <fmt:formatDate value="${item.PRINTTIME}" pattern="yyyy-MM-dd HH:mm"/>">출력</td>
                </c:if>
            </tr>
        </c:forEach>
        <c:if test="${empty list}">
            <tr>
                <td colspan="14">데이터가 존재하지 않습니다</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <!-- paginate-->
    <c:if test="${!empty list}">
        <c:out value="${pagingHtml}" escapeXml="false" />
    </c:if>
    <!--//paginate-->

</form>
</div>

<script type="text/javascript">
$(function() {
    setDateFickerImageUrl('<c:url value='/resources/img/common/icon_calendar01.png'/>');
    initDateFicker2("sDate", "eDate");
});

function goList(page) {
    if(typeof(page) == "undefined") $("#currentPage").val(1);
    else $("#currentPage").val(page);
    $("#form").attr("action", "<c:url value='/offerer/refund/list.do' />");
    $("#form").submit();
}

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
    $("#form").attr("action", "<c:url value='/offerer/refund/list.do' />");
    $("#form").submit();
}

function fn_View(val){
    $("#IDENTYID").val(val);
    $("#form").attr("action", "<c:url value='/offerer/offererView.do' />");
    $("#form").submit();
}

function fn_Modify(val){
    $("#IDENTYID").val(val);
    $("#form").attr("action", "<c:url value='/offerer/offererModify.do'/>");
    $("#form").submit();
}

function fn_Refund(val){
    $("#IDENTYID").val(val);
    $("#form").attr("action", "<c:url value='/offerer/refund/offererRefund.do'/>");
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

//응시표복원
function fn_Print(identyid, examcode){
    window.open('<c:url value="/offerer/offererPrint.pop"/>?IDENTYID=' + identyid + '&MOCKCODE=' + examcode, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=620,height=330');
    $("#form").attr("action", "<c:url value='/offerer/refund/list.do' />");
    $("#form").submit();
}

function fn_Excel() {
    $("#currentPage").val(1);
    $("#form").attr("action", "<c:url value='/offerer/refund/excel.do' />");
    $("#form").submit();
}
</script>
