<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/mt/css/reset.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/mt/css/base.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/mt/css/main.css"/>" />
<script type="text/javascript">
window.onload = function () {
}

var initBody;
function beforePrint(){
    initBody = document.body.innerHTML;
    document.body.innerHTML = idPrint.innerHTML;
}

function afterPrint(){
    document.body.innerHTML = initBody;
}

//프린트하기
function print_info() {
    window.print();
}
window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;
</script>
</head>

<div class="wrap">
    <ul class="btns">
    <c:choose>
        <c:when test="${searchMap.sts == '1'}"><li><a href="#" onclick="print_info()" class="btn02 grd_01">문제 인쇄하기</a></li></c:when>
        <c:when test="${searchMap.sts == '2'}"><li><a href="#" onclick="print_info()" class="btn02 grd_01">해설 인쇄하기</a></li></c:when>
    </c:choose>
    </ul>

    <div id="content">
    <form id="popFrm" name="popFrm" method="post">
    <input type="hidden" id="ITEMID" name="ITEMID" value="${searchMap.ITEMID}" />
         <div id="idPrint" class="Qimg" style="overflow-y:scroll;width:500px;height:100px;">
         <c:if test="${searchMap.sts == '1'}">  <!-- 문제 표시 -->
             <c:forEach items="${question_list}" var="question_list" varStatus="status">
                <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '1' || searchMap.QUESTIONREGISTRATIONOPTION == '2' }">    <!-- 이미지로 등록 -->
                    <c:if test="${question_list.QUESTIONFILEID ne null}">
                        <ol>
                            <li>
                                <span style="displa:block; margin-top: 10px; float:left; font-size:12pt;  font-weight:bold; width:24px;">${question_list.NEW_QUESTIONNUMBER}.</span>
                                <span style="margin-left:5px;"><img style="width:450px;" src="<c:url value="/imgFileView.do?path=${question_list.FILEPATH }" />"></span>
                            </li>
                        </ol>
                    </c:if><br><br>
                </c:if>

                <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '3' || searchMap.QUESTIONREGISTRATIONOPTION == '4' }">  <!-- 텍스트 , 엑셀 텍스트 -->
                    <c:if test="${question_list.QUESTION ne null}">
                        <ol>
                            <li>
                                <span style="float:left; font-size:12pt;  font-weight:bold; width:24px;">${question_list.NEW_QUESTIONNUMBER}.</span>
                                <span style="margin-left:5px;">${question_list.QUESTION}</span>
                            </li>
                        </ol>
                    </c:if><br><br>
                </c:if>
             </c:forEach>
         </c:if>

         <c:if test="${searchMap.sts == '2'}">  <!-- 정답해설 표시 -->
             <c:forEach items="${question_list}" var="question_list" varStatus="status">
                <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '1' || searchMap.QUESTIONREGISTRATIONOPTION == '2' }">    <!-- 이미지로 등록 -->
                    <c:if test="${question_list.ANSWEREXPLAINFILEID ne null}">
                        <ol>
                            <li>
                                <span style="displa:block; margin-top: 10px; float:left; font-size:12pt;  font-weight:bold; width:24px;">${question_list.NEW_QUESTIONNUMBER}.</span>
                                <span style="margin-left:5px;"><img style="width:450px;" src="<c:url value="/imgFileView.do?path=${question_list.FILEPATH2 }" />"></span>
                            </li>
                        </ol>
                    </c:if><br><br>
                </c:if>

                <c:if test="${searchMap.QUESTIONREGISTRATIONOPTION == '3' || searchMap.QUESTIONREGISTRATIONOPTION == '4' }">  <!-- 텍스트 , 엑셀 텍스트 -->
                    <c:if test="${question_list.ANSWEREXPLAIN ne null}">
                        <ol>
                            <li>
                                <span style="float:left; font-size:12pt;  font-weight:bold; width:24px;">${question_list.NEW_QUESTIONNUMBER}.</span>
                                <span style="margin-left:5px;">${question_list.ANSWEREXPLAIN}</span>
                            </li>
                        </ol>
                    </c:if><br><br>
                </c:if>
             </c:forEach>
         </c:if>
         </div>
    </form>
    </div>
</div>