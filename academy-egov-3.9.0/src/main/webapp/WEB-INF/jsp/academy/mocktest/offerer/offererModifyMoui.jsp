<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<head>
</head>

<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="IDENTYID" name="IDENTYID" value="${view[0].IDENTYID}"/>
<input type="hidden" id="MOCKNAME" name="MOCKNAME" value="${view[0].MOCKNAME}"/>
<input type="hidden" id="EXAMCODE" name="EXAMCODE" value="${view[0].MOCKCODE}"/>
<input type="hidden" id="CLASSCODE" name="CLASSCODE" value="${view[0].CLASSCODE}"/>
<c:set var="SET_CLASSSERIESCODE" value="" />
<c:forEach items="${classlist}" var="item" varStatus="index">
    <c:if test="${item.MOCKCODE eq view[0].EXAMCODE && item.CLASSCODE eq view[0].CLASSCODE}">
        <c:set var="SET_CLASSSERIESCODE" value="${item.CLASSSERIESCODE}" />
    </c:if>
</c:forEach>
<input type="hidden" id="CLASSSERIESCODE" name="CLASSSERIESCODE" value="${SET_CLASSSERIESCODE}"/>
<input type="hidden" id="EXAMYEAR" name="EXAMYEAR" value="${view[0].EXAMYEAR}"/>
<input type="hidden" id="EXAMROUND" name="EXAMROUND" value="${view[0].EXAMROUND}"/>
<input type="hidden" id="RECEIPTTYPE" name="RECEIPTTYPE" value="0"/>
<input type="hidden" id="PAYMENTSTATE" name="PAYMENTSTATE" value="0"/>
<input type="hidden" id="PAYMENTTARGETAMOUNT" name="PAYMENTTARGETAMOUNT" value="${view[0].SALEAMOUNTS}"/>
<input type="hidden" id="ADDDISCOUNTRATIO" name="ADDDISCOUNTRATIO" value="0"/>
<input type="hidden" id="ADDDISCOUNTAMOUNT" name="ADDDISCOUNTAMOUNT" value="0"/>

    <div id="content_pop">
    <h2>● <strong>${params.SEARCHYEAR}년 ${view[0].MOCKNAME}</strong></h2>

    <c:if test="${empty list}" >
        <table class="table01">
          <tr>
            <th align="center">지원 가능한 모의고사가 없습니다.</th>
          </tr>
        </table>

        <!--버튼-->
        <ul class="boardBtns">
            <li><a href="javascript:self.close();">닫기</a></li>
        </ul>
        <!--//버튼-->
    </c:if>
    <c:if test="${!empty list}" >
        <table class="table01">
            <tr>
                <th colspan="2">응시형태</th>
                <td>
                    <input type="radio" name="EXAMTYPE" value="0" <c:if test="${'0' eq view[0].EXAMTYPE}">checked="checked"</c:if>>온라인
                    <input type="radio" name="EXAMTYPE" value="1" <c:if test="${'1' eq view[0].EXAMTYPE}">checked="checked"</c:if>>오프라인(학원)
                 </td>
            </tr>
            <tr>
                <th colspan="2">시험일자 </th>
                <td id="DATEAREA">
                <strong>
                    ${fn:substring(view[0].EXAMSTARTTIME,4, 6)}월
                    ${fn:substring(view[0].EXAMSTARTTIME,6, 8)}일
                    ${fn:substring(view[0].EXAMSTARTTIME,8, 10)}시
                     ~
                    ${fn:substring(view[0].EXAMENDTIME,8, 10)}시
                </strong>
                </td>
            </tr>
        <tr>
            <th width="13%">응시직렬</th>
            <th width="13%">${list[0].CLASSSCODENM}</th>
            <td>
            <c:set var="EMPTYSPACE" value="&nbsp;" />
            <c:forEach items="${classlist}" var="item" varStatus="index">
                <c:if test="${item.CLASSCODE eq list[0].CLASSCODE}" >
                    <c:set var="EMPTYSPACE" value="" />
                    <input name="CLASSARR" type="radio" value="${item.MOCKCODE},${item.CLASSCODE},${item.CLASSSERIESCODE}" <c:if test="${item.MOCKCODE eq list[0].MOCKCODE}">checked=checked</c:if>> ${item.CLASSSERIESCODENM}
                </c:if>
            </c:forEach>${EMPTYSPACE}
            </td>
        </tr>
       <tr>
           <th colspan="2">응시지역<span id="subjectlen"></span></th>
           <td>
           	<div id="vArea">
			<c:forEach items="${AreaList}" var="item" varStatus="status">
           	<input type="radio" id="AREACODE" name="AREACODE" value="${item.CODEVALUE}">&nbsp;${item.CODENAME}&nbsp;&nbsp;
           	</c:forEach>
           	</div>
           </td>
       </tr>
            <tr>
                <th colspan="2">응시료</th>
                <td id="COSTAREA">
                <strong class="txt01">
                    <c:if test="${!empty view[0].SALEAMOUNTS}" >
                        ${view[0].SALEAMOUNTS}원
                    </c:if>
                    <c:if test="${empty view[0].SALEAMOUNTS}" >
                        ${view[0].PAYMENTTARGETAMOUNT}원
                    </c:if>
                </strong>
                </td>
            </tr>
        </table>

        <!--버튼-->
        <ul class="boardBtns">
            <li><a href="javascript:fn_modify();">수정</a></li>
            <li><a href="javascript:self.close();">닫기</a></li>
        </ul>
        <!--//버튼-->
    </c:if>
    </div><!--content//-->
</form>

<script type="text/javascript">
var curSubJect = "";
var curDate = "";
var curCost = "";
var curText = "";

$(document).ready(function(){

    $("input[name='CLASSARR']").change(function() {
        var curMockCd = $(this).val().split(",")[0];
        $("#EXAMCODE").val(curMockCd);
        $("#CLASSCODE").val($(this).val().split(",")[1]);
        $("#CLASSSERIESCODE").val($(this).val().split(",")[2]);
        $("#MOCKNAME").val($(this).val().split(",")[3]);
        curSubJect = "";
        <c:forEach items="${list}" var="item" varStatus="index">
            if(curMockCd == "${item.MOCKCODE}"){
                var useYn = "N";
                <c:forEach items="${clslist}" var="citem" varStatus="cindex">
                    if( $("#CLASSCODE").val() == "${citem.CLASSCODE}" && $("#CLASSSERIESCODE").val() == "${citem.CLASSSERIESCODE}" && "${item.SUBJECT_CD}" == "${citem.SUBJECT_CD}" ){
                        useYn = "Y";
                    }
                </c:forEach>
                if(useYn == "Y"){
                    <c:set var="SET_CHECKED" value="" />
                    <c:forEach items="${subjectview}" var="sitem" varStatus="sindex">
                        <c:if test="${item.ITEMID eq sitem.ITEMID}" >
                            <c:set var="SET_CHECKED" value="checked=checked" />
                        </c:if>
                    </c:forEach>
                    curSubJect += "<input name=\"CHOICEITEMARR\" type=\"checkbox\" value=\"${item.ITEMID},${item.SUBJECT_NM}\" ${SET_CHECKED}>" + "&nbsp;${item.SUBJECT_NM}&nbsp;";
                }

                curCost = "${item.SALEAMOUNTS}";

                curDate = "${fn:substring(item.EXAMSTARTTIME,4, 6)}월&nbsp;";
                curDate += "${fn:substring(item.EXAMSTARTTIME,6, 8)}일&nbsp;";
                curDate += "${fn:substring(item.EXAMSTARTTIME,8, 10)}시";
                curDate += " ~ ";
                //curDate += "${fn:substring(item.EXAMENDTIME,4, 6)}월&nbsp;";
                //curDate += "${fn:substring(item.EXAMENDTIME,6, 8)}일&nbsp;";
                curDate += "${fn:substring(item.EXAMENDTIME,8, 10)}시";

                $("#MOCKNAME").val("${item.MOCKNAME}");
            }
        </c:forEach>
        $("#PAYMENTTARGETAMOUNT").val(curCost);
        $("#CHOICEITEMAREA").html(curSubJect);
        $("#COSTAREA > strong").html(curCost+"원");
        $("#DATEAREA > strong").html(curDate);

    });

    var curMockCd = "${view[0].EXAMCODE}";
    $("#EXAMCODE").val(curMockCd);
    $("#CLASSCODE").val("${view[0].CLASSCODE}");
    $("#CLASSSERIESCODE").val("${view[0].CLASSSERIESCODE}");

    var curSubJect = "";
    var curDate = "";
    var curCost = "";
    var curOffInfo = "";
    <c:forEach items="${list}" var="item" varStatus="index">
        if(curMockCd == "${item.MOCKCODE}"){
            var useYn = "N";
            <c:forEach items="${clslist}" var="citem" varStatus="cindex">
                if( $("#CLASSCODE").val() == "${citem.CLASSCODE}" && $("#CLASSSERIESCODE").val() == "${citem.CLASSSERIESCODE}" && "${item.SUBJECT_CD}" == "${citem.SUBJECT_CD}" ){
                    useYn = "Y";
                }
            </c:forEach>
            if(useYn == "Y"){
                <c:set var="SET_CHECKED" value="" />
                <c:forEach items="${subjectview}" var="sitem" varStatus="sindex">
                    <c:if test="${item.ITEMID eq sitem.ITEMID}" >
                        <c:set var="SET_CHECKED" value="checked=checked" />
                    </c:if>
                </c:forEach>
                curSubJect += "<input name=\"CHOICEITEMARR\" type=\"checkbox\" value=\"${item.ITEMID},${item.SUBJECT_NM}\" ${SET_CHECKED}>" + "&nbsp;${item.SUBJECT_NM}&nbsp;";
            }

            curCost = "${item.SALEAMOUNTS}";

            curDate = "${fn:substring(item.EXAMSTARTTIME,4, 6)}월&nbsp;";
            curDate += "${fn:substring(item.EXAMSTARTTIME,6, 8)}일&nbsp;";
            curDate += "${fn:substring(item.EXAMSTARTTIME,8, 10)}시";
            curDate += " ~ ";
            curDate += "${fn:substring(item.EXAMENDTIME,4, 6)}월&nbsp;";
            curDate += "${fn:substring(item.EXAMENDTIME,6, 8)}일&nbsp;";
            curDate += "${fn:substring(item.EXAMENDTIME,8, 10)}시";

            $("#RECEIPTSTARTTIME").val("${item.RECEIPTSTARTTIME}");
            $("#RECEIPTENDTIME").val("${item.RECEIPTENDTIME}");
            $("#OFFCLOSEPERSONNUMBER").val("${item.OFFCLOSEPERSONNUMBER}");

            curOffInfo = "&nbsp;";
            curOffInfo += "[접수시간 : ";
            curOffInfo += "${fn:substring(item.RECEIPTSTARTTIME,4, 6)}월 ${fn:substring(item.RECEIPTSTARTTIME,6, 8)}일 ${fn:substring(item.RECEIPTSTARTTIME,8, 10)}시 ${fn:substring(item.RECEIPTSTARTTIME,10, 12)}분~${fn:substring(item.RECEIPTENDTIME,4, 6)}월 ${fn:substring(item.RECEIPTENDTIME,6, 8)}일 ${fn:substring(item.RECEIPTENDTIME,8, 10)}시 ${fn:substring(item.RECEIPTENDTIME,10, 12)}분";
            curOffInfo += " , 마감인원 : ${list[0].OFFCLOSEPERSONNUMBER}]";
        }
    </c:forEach>

    $("#PAYMENTTARGETAMOUNT").val(curCost);
    $("#CHOICEITEMAREA").html(curSubJect);
    $("#COSTAREA > strong").html(curCost+"원");
    $("#DATEAREA > strong").html(curDate);
    $("#offinfo").html(curOffInfo);
});

function fn_modify(){
    var curSubJect = "";
    var curText = "";
    if($("#CLASSCODE").val()=="001" || $("#CLASSCODE").val()=="003" || $("#CLASSCODE").val()=="006"){
        $("input[name='CHOICEITEMARR']:checked").each(function(){
            curSubJect += "<input name=\"CHOICEITEMARR\" type=\"hidden\" value=\"" + $(this).val().split(",")[0] +  "\">";
            if(curText!=""){
                curText += "&nbsp;,&nbsp;";
            }
            curText += $(this).val().split(",")[1];
        });
        if(curText != ""){
            $("#mouiArea1_1", opener.document).html("선택 : " + curText);
        }
        $("#CHOICEITEMAREA", opener.document).html(curSubJect);
    }
    $("#EXAMTYPE", opener.document).val($("input[name='EXAMTYPE']:checked").val());    
    self.close();
}
</script>