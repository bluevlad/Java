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
    <h2>● <strong>${params.SEARCHYEAR}년 제${params.SEARCHROUND}회 윌비스 전국모의고사</strong></h2>

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
                    <!-- ${fn:substring(list[0].EXAMENDTIME,4, 6)}월
                    ${fn:substring(list[0].EXAMENDTIME,6, 8)}일 -->
                    ${fn:substring(view[0].EXAMENDTIME,8, 10)}시
                <!--8월 25일 10 ~ 14시 온라인경우-->
                <!--8월 25일(일) 오전 10시 / 시험시간 : 100분  [오프라인인경우]-->
                </strong>
                </td>
            </tr>
        <c:set var="CLASSLOW" value="0" />
        <c:set var="CLASS9YN" value="N" />
        <c:set var="CLASS7YN" value="N" />
        <c:set var="COURTCLASSYN" value="N" />
        <c:set var="POLICECLASSYN" value="N" />
        <c:set var="FIRECLASSYN" value="N" />
        <c:set var="CLASS7PUBYN" value="N" />
        <c:forEach items="${classlist}" var="item" varStatus="index">
            <c:if test="${item.CLASSCODE eq '001'}" >
                <c:set var="CLASS9YN" value="Y" />
                <c:if test="${CLASSLOW < 1}" >
                    <c:set var="CLASSLOW" value="${CLASSLOW+1}" />
                </c:if>
            </c:if>
            <c:if test="${item.CLASSCODE eq '002'}" >
                <c:set var="CLASS7YN" value="Y" />
                <c:if test="${CLASSLOW < 2}" >
                    <c:set var="CLASSLOW" value="${CLASSLOW+1}" />
                </c:if>
            </c:if>
            <c:if test="${item.CLASSCODE eq '003'}" >
                <c:set var="COURTCLASSYN" value="Y" />
                <c:if test="${CLASSLOW < 3}" >
                    <c:set var="CLASSLOW" value="${CLASSLOW+1}" />
                </c:if>
            </c:if>
            <c:if test="${item.CLASSCODE eq '008'}" >
                <c:set var="POLICECLASSYN" value="Y" />
                <c:if test="${CLASSLOW < 4}" >
                    <c:set var="CLASSLOW" value="${CLASSLOW+1}" />
                </c:if>
            </c:if>
            <c:if test="${item.CLASSCODE eq '006'}" >
                <c:set var="FIRECLASSYN" value="Y" />
                <c:if test="${CLASSLOW < 5}" >
                    <c:set var="CLASSLOW" value="${CLASSLOW+1}" />
                </c:if>
            </c:if>
            <c:if test="${item.CLASSCODE eq '020'}" >
                <c:set var="CLASS7PUBYN" value="Y" />
                <c:if test="${CLASSLOW < 6}" >
                    <c:set var="CLASSLOW" value="${CLASSLOW+1}" />
                </c:if>
            </c:if>
        </c:forEach>
            <tr <c:if test="${CLASS9YN eq 'N'}">style="display:none;"</c:if>>
                <th width="13%" rowspan="${CLASSLOW}">응시직렬</th>
                <th width="13%">9급<br>시험시간 100분</th>
                <td>
                <c:set var="CHOICKVALUE" value="&nbsp;" />
                <c:forEach items="${classlist}" var="item" varStatus="index">
                    <c:if test="${item.CLASSCODE eq '001'}" >
                        <c:if test="${item.CLASSCODE eq view[0].CLASSCODE && item.CLASSSERIESCODE eq view[0].CLASSSERIESCODE}">
                            <c:set var="CHOICKVALUE" value="${item.CLASSSERIESCODENM}" />
                        </c:if>
                    </c:if>
                </c:forEach>${CHOICKVALUE}
                </td>
            </tr>
            <tr <c:if test="${CLASS7YN eq 'N'}">style="display:none;"</c:if>>
                <th width="15%">7급<br>시험시간 140분</th>
                <td>
                <c:set var="CHOICKVALUE" value="&nbsp;" />
                <c:forEach items="${classlist}" var="item" varStatus="index">
                    <c:if test="${item.CLASSCODE eq '002'}" >
                        <c:if test="${item.CLASSCODE eq view[0].CLASSCODE && item.CLASSSERIESCODE eq view[0].CLASSSERIESCODE}">
                            <c:set var="CHOICKVALUE" value="${item.CLASSSERIESCODENM}" />
                        </c:if>
                    </c:if>
                </c:forEach>${CHOICKVALUE}
                </td>
            </tr>
            <tr <c:if test="${COURTCLASSYN eq 'N'}">style="display:none;"</c:if>>
                <th width="15%">법원직</th>
                <td>
                <c:set var="CHOICKVALUE" value="&nbsp;" />
                <c:forEach items="${classlist}" var="item" varStatus="index">
                    <c:if test="${item.CLASSCODE eq '003'}" >
                        <c:if test="${item.CLASSCODE eq view[0].CLASSCODE && item.CLASSSERIESCODE eq view[0].CLASSSERIESCODE}">
                            <c:set var="CHOICKVALUE" value="${item.CLASSSERIESCODENM}" />
                        </c:if>
                    </c:if>
                </c:forEach>${CHOICKVALUE}
                </td>
            </tr>
            <tr <c:if test="${POLICECLASSYN eq 'N'}">style="display:none;"</c:if>>
                <th width="15%">경찰직</th>
                <td>
                <c:set var="CHOICKVALUE" value="&nbsp;" />
                <c:forEach items="${classlist}" var="item" varStatus="index">
                    <c:if test="${item.CLASSCODE eq '008'}" >
                        <c:if test="${item.CLASSCODE eq view[0].CLASSCODE && item.CLASSSERIESCODE eq view[0].CLASSSERIESCODE}">
                            <c:set var="CHOICKVALUE" value="${item.CLASSSERIESCODENM}" />
                        </c:if>
                    </c:if>
                </c:forEach>${CHOICKVALUE}
                </td>
            </tr>
            <tr <c:if test="${FIRECLASSYN eq 'N'}">style="display:none;"</c:if>>
                <th width="15%">소방직</th>
                <td>
                <c:set var="CHOICKVALUE" value="&nbsp;" />
                <c:forEach items="${classlist}" var="item" varStatus="index">
                    <c:if test="${item.CLASSCODE eq '006'}" >
                        <c:if test="${item.CLASSCODE eq view[0].CLASSCODE && item.CLASSSERIESCODE eq view[0].CLASSSERIESCODE}">
                            <c:set var="CHOICKVALUE" value="${item.CLASSSERIESCODENM}" />
                        </c:if>
                    </c:if>
                </c:forEach>${CHOICKVALUE}
                </td>
            </tr>
            <tr <c:if test="${CLASS7PUBYN eq 'N'}">style="display:none;"</c:if>>
                <th width="15%">7급외무행정</th>
                <td>
                <c:set var="CHOICKVALUE" value="&nbsp;" />
                <c:forEach items="${classlist}" var="item" varStatus="index">
                    <c:if test="${item.CLASSCODE eq '020'}" >
                        <c:if test="${item.CLASSCODE eq view[0].CLASSCODE && item.CLASSSERIESCODE eq view[0].CLASSSERIESCODE}">
                            <c:set var="CHOICKVALUE" value="${item.CLASSSERIESCODENM}" />
                        </c:if>
                    </c:if>
                </c:forEach>${CHOICKVALUE}
                </td>
            </tr>

            <!--가산점 : 온라인 신청 경우 나타남-->
            <tr class="ADDAREA">
                <th rowspan="3">가산점<br>
                  <!-- <a href="<c:url value="/offerer/pointDesc"/>" target="_blank" class="btn08">상세보기</a> -->
                </th>
                <th width="15%">취업보호대상자</th>
                <td>
                    <input type="radio" name="ADDPOINT1" value="0" <c:if test="${'0' eq view[0].ADDPOINT1}">checked=checked</c:if>> 해당없음
                    <input type="radio" name="ADDPOINT1" value="5" <c:if test="${'5' eq view[0].ADDPOINT1}">checked=checked</c:if>> 5%
                    <input type="radio" name="ADDPOINT1" value="10" <c:if test="${'10' eq view[0].ADDPOINT1}">checked=checked</c:if>> 10%
                </td>
            </tr>
            <tr class="ADDAREA">
                <th width="15%" rowspan="2">자격증 가산점</th>
                <td>
                  <span class="sort01">공통적용</span>
                    <input type="radio" name="ADDPOINT2" value="0" <c:if test="${'0' eq view[0].ADDPOINT2}">checked=checked</c:if>> 해당없음
                    <input type="radio" name="ADDPOINT2" value="0.5" <c:if test="${'0.5' eq view[0].ADDPOINT2}">checked=checked</c:if>> 0.5%
                    <input type="radio" name="ADDPOINT2" value="1" <c:if test="${'1' eq view[0].ADDPOINT2}">checked=checked</c:if>> 1%
                </td>
            </tr>
            <tr class="ADDAREA">
                <td>
                    <span class="sort01">직렬별적용</span>
                    <input type="radio" name="ADDPOINT3" value="0" <c:if test="${'0' eq view[0].ADDPOINT3}">checked=checked</c:if>> 해당없음
                    <input type="radio" name="ADDPOINT3" value="3" <c:if test="${'3' eq view[0].ADDPOINT3}">checked=checked</c:if>> 3%
                    <input type="radio" name="ADDPOINT3" value="5" <c:if test="${'5' eq view[0].ADDPOINT3}">checked=checked</c:if>> 5%
                </td>
            </tr>
            <!--가산점 : 오프라인 신청 경우 나타남 //-->
            <tr <c:if test="${('001' ne view[0].CLASSCODE) and ('008' ne view[0].CLASSCODE) and ('006' ne view[0].CLASSCODE) and ('020' ne view[0].CLASSCODE)}">style="display:none;"</c:if>>
                <th colspan="2">선택과목<span id="subjectlen"></span></th>
                <td id="CHOICEITEMAREA">
                <c:set var="EMPTYSPACE" value="&nbsp;" />
                <c:forEach items="${list}" var="item" varStatus="index">
                    <c:if test="${item.MOCKCODE eq view[0].EXAMCODE}" >
                        <c:set var="SET_CHECKED" value="" />
                        <c:set var="EMPTYSPACE" value="" />
                        <c:forEach items="${subjectview}" var="sitem" varStatus="sindex">
                            <c:if test="${item.ITEMID eq sitem.ITEMID}" >
                                <c:set var="SET_CHECKED" value="checked=checked" />
                            </c:if>
                        </c:forEach>
                        <c:if test="${!empty item.SUBJECT_NM}" >
                            <input name="CHOICEITEMARR" type="checkbox" value="${item.ITEMID},${item.SUBJECT_NM}" ${SET_CHECKED} /> ${item.SUBJECT_NM}
                        </c:if>
                    </c:if>
                </c:forEach>${EMPTYSPACE}<span></span>
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
    <c:if test="${'002' eq view[0].CLASSCODE}">
        $("#subjectlen").html("(2과목)");
    </c:if>
    <c:if test="${'008' eq view[0].CLASSCODE}">
        $("#subjectlen").html("(3과목)");
    </c:if>
    <c:if test="${'006' eq view[0].CLASSCODE}">
        $("#subjectlen").html("(2과목)");
    </c:if>
    <c:if test="${'020' eq view[0].CLASSCODE}">
    $("#subjectlen").html("(1과목)");
</c:if>

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
    if($("#CLASSCODE").val()=="001" || $("#CLASSCODE").val()=="008" || $("#CLASSCODE").val()=="006" || $("#CLASSCODE").val()=="020"){
        if($("#CLASSCODE").val()=="001"){
            if($("input[name='CHOICEITEMARR']:checked").size()!=2){
                alert("선택과목을 2과목 선택해주세요");
                return;
            }
        }
        if($("#CLASSCODE").val()=="008"){
            if($("input[name='CHOICEITEMARR']:checked").size()!=3){
                alert("선택과목을 3과목 선택해주세요");
                return;
            }
        }
        if($("#CLASSCODE").val()=="006"){
            if($("input[name='CHOICEITEMARR']:checked").size()!=2){
                alert("선택과목을 2과목 선택해주세요");
                return;
            }
        }
        if($("#CLASSCODE").val()=="020"){
            if($("input[name='CHOICEITEMARR']:checked").size()!=1){
                alert("선택과목을 1과목 선택해주세요");
                return;
            }
        }
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
    $("#ADDPOINT1", opener.document).val($("input[name='ADDPOINT1']:checked").val());
    $("#ADDPOINT2", opener.document).val($("input[name='ADDPOINT2']:checked").val());
    $("#ADDPOINT3", opener.document).val($("input[name='ADDPOINT3']:checked").val());
    self.close();
}
</script>