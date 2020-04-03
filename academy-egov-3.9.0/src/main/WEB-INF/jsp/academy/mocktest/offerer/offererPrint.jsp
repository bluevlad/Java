<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<script>
<!--
    function fn_barProc() 
    {
        /********************             삽입 될 데이터 목록          *******************/
        /*     이 부분은 자바스크립트 함수를 호출 할때 parameter로 받아서 처리 하셔도 됩니다     */
        /*   예 함수 ctkprint_barProc(value1, value2) , 내부변수1 = value1 내부변수2 = value2 */
        /***************************************************************/       
        //티켓의 왼쪽 부분 입력 시작
        /*
            ctkprint_bar.prt_text_L숫자 이 객체에 L숫자 부분에서 숫자는 라인번호임 (1~25), L은 Left부분 의미, R은 Right
            입력되는 데이터 구성 = "내용;글씨체;글씨크기;글씨굵기(true/false);정렬(left/right)";
        */
        
        ctkprint_bar.prt_text_L4 = "수강증;궁서;18;true;left";
        ctkprint_bar.prt_text_L8 = "시험일 : ${view[0].EXAMSTARTDATE2}시" + ";굴림;10;true;left";
        ctkprint_bar.prt_text_L10 = "${view[0].MOCKNAME}" + ";굴림;16;true;left";
        ctkprint_bar.prt_text_L14 = "${view[0].CLASSCODENM} / ${view[0].CLASSSERIESCODENM} / ${view[0].AREANM}" + ";굴림;14;true;left";
        ctkprint_bar.prt_text_L18 = "${view[0].USRNM}" + ";굴림;16;true;left";
        ctkprint_bar.prt_text_L22 = "응시번호 : ${fn:substring(view[0].IDENTYID, 8, 13)}" + ";굴림;12;true;left";
        ctkprint_bar.prt_text_L24 = "(${view[0].USER_ID}) (${view[0].PAYMENTNM})" + ";굴림;12;true;left";
        ctkprint_bar.prt_text_L28 = "${fn:substring(view[0].PRINTTIME, 0, 10)}" + ";굴림;10;false;left";

        //인쇄 관련 부분 시작
        ctkprint_bar.CT_DOC_Prt = 1;    //인쇄 요청 코드
        ctkprint_bar.CT_DOC_Prt2 = 1; //미리보기 코드
        alert("출력되었습니다");
    }
-->
</script>
</head>

<c:set var="subTitles" value=""/>
<c:forEach items="${viewslist}" var="item">
    <c:if test="${empty subTitles}"> <c:set var="subTitles" value="${item}"/> </c:if>
    <c:if test="${!empty subTitles}"> <c:set var="subTitles" value="${subTitles},${item}"/> </c:if>
</c:forEach>
<input type="hidden" id="stitles" name="stitles" value="${subTitles}" />

<button type="button" id="btn" onclick="javascript:fn_barProc();" style="margin-left:300px;margin-top:20px;">출력</button>

<script language="javascript">
    var cabPath = "<c:url value='/resources/libs/ctk_barcode.CAB' />";
    jwill = '<OBJECT';
    jwill += ' id="ctkprint_bar"';
    jwill += ' CLASSID="CLSID:B28045C9-D1F9-43BF-9198-3777E49BFF57"';
    jwill += ' CODEBASE="' + cabPath + '#version=1,0,0,1">';      
    jwill += '</OBJECT>';
    document.write(jwill);
</script>
