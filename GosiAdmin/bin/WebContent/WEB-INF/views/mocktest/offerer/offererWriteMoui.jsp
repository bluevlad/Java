<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<head>
</head>

<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="MOCKNAME" name="MOCKNAME" value="${list[0].MOCKNAME}"/>
<input type="hidden" id="EXAMCODE" name="EXAMCODE" value="${list[0].MOCKCODE}"/>
<input type="hidden" id="CLASSCODE" name="CLASSCODE" value="${list[0].CLASSCODE}"/>
<c:set var="SET_CLASSSERIESCODE" value="" />
<c:forEach items="${classlist}" var="item" varStatus="index">
    <c:if test="${item.MOCKCODE eq list[0].MOCKCODE && item.CLASSCODE eq list[0].CLASSCODE}">
        <c:set var="SET_CLASSSERIESCODE" value="${item.CLASSSERIESCODE}" />
    </c:if>
</c:forEach>
<input type="hidden" id="CLASSSERIESCODE" name="CLASSSERIESCODE" value="${SET_CLASSSERIESCODE}"/>
<input type="hidden" id="USER_ID" name="USER_ID" value="testId"/>
<input type="hidden" id="USER_NM" name="USER_NM" value="testName"/>
<input type="hidden" id="PHONE_NO" name="PHONE_NO" value="0100009999"/>
<input type="hidden" id="EXAMYEAR" name="EXAMYEAR" value="${list[0].EXAMYEAR}"/>
<input type="hidden" id="EXAMROUND" name="EXAMROUND" value="${list[0].EXAMROUND}"/>
<input type="hidden" id="RECEIPTTYPE" name="RECEIPTTYPE" value="0"/>
<input type="hidden" id="PAYMENTSTATE" name="PAYMENTSTATE" value="0"/>
<input type="hidden" id="PAYMENTTARGETAMOUNT" name="PAYMENTTARGETAMOUNT" value="${list[0].SALEAMOUNTS}"/>
<input type="hidden" id="ADDDISCOUNTRATIO" name="ADDDISCOUNTRATIO" value="0"/>
<input type="hidden" id="ADDDISCOUNTAMOUNT" name="ADDDISCOUNTAMOUNT" value="0"/>
<input type="hidden" id="OFFCLOSEPERSONNUMBER" name="OFFCLOSEPERSONNUMBER" value="${list[0].OFFCLOSEPERSONNUMBER}"/>

    <div id="content_pop">
    <h2>● <strong>윌비스 전국모의고사</strong></h2>

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
                <input type="radio" name="EXAMTYPE" value="1" checked>오프라인(학원)
             </td>
        </tr>
        <tr>
        <th colspan="2">시험일자 </th>
            <td id="DATEAREA">
            <strong>
                ${fn:substring(list[0].EXAMSTARTTIME,4, 6)}월
                ${fn:substring(list[0].EXAMSTARTTIME,6, 8)}일
                ${fn:substring(list[0].EXAMSTARTTIME,8, 10)}시
                 ~
                <!-- ${fn:substring(list[0].EXAMENDTIME,4, 6)}월
                ${fn:substring(list[0].EXAMENDTIME,6, 8)}일 -->
                ${fn:substring(list[0].EXAMENDTIME,8, 10)}시
            <!--8월 25일 10 ~ 14시 온라인경우-->
            <!--8월 25일(일) 오전 10시 / 시험시간 : 100분  [오프라인인경우]-->
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
        <!--가산점 : 온라인 신청 경우 나타남-->
        <tr class="ADDAREA">
            <th rowspan="3">가산점<br>
              <!-- <a href="<c:url value="/offerer/pointDesc"/>" target="_blank" class="btn08">상세보기</a> -->
            </th>
            <th width="15%">취업보호대상자</th>
            <td>
                <input type="radio" name="ADDPOINT1" value="0" checked> 해당없음
                <input type="radio" name="ADDPOINT1" value="5"> 5%
                <input type="radio" name="ADDPOINT1" value="10"> 10%
            </td>
        </tr>
        <tr class="ADDAREA">
            <th width="15%" rowspan="2">자격증 가산점</th>
            <td>
              <span class="sort01">공통적용</span>
                <input type="radio" name="ADDPOINT2" value="0" checked> 해당없음
                <input type="radio" name="ADDPOINT2" value="0.5"> 0.5%
                <input type="radio" name="ADDPOINT2" value="1"> 1%
            </td>
        </tr>
        <tr class="ADDAREA">
            <td>
                <span class="sort01">직렬별적용</span>
                <input type="radio" name="ADDPOINT3" value="0" checked> 해당없음
                <input type="radio" name="ADDPOINT3" value="3"> 3%
                <input type="radio" name="ADDPOINT3" value="5"> 5%
            </td>
        </tr>
        <!--가산점 : 오프라인 신청 경우 나타남 //-->
        <tr class="ADDCHOICE">
            <th colspan="2">선택과목</th>
            <td id="CHOICEITEMAREA">
            <!--
                <c:set var="EMPTYSPACE" value="&nbsp;" />
                <c:forEach items="${list}" var="item" varStatus="index">
                    <c:if test="${item.MOCKCODE eq list[0].MOCKCODE}" >
                        <c:if test="${!empty item.SUBJECT_NM}" >
                            <c:set var="EMPTYSPACE" value="" />
                            <input name="CHOICEITEMARR" type="checkbox" value="${item.ITEMID},${item.SUBJECT_NM}"/> ${item.SUBJECT_NM}
                        </c:if>
                    </c:if>
                </c:forEach>${EMPTYSPACE}<span></span>
            -->
            </td>
        </tr>
        <tr>
            <th colspan="2">응시료</th>
            <td id="COSTAREA"><strong class="txt01">${list[0].SALEAMOUNTS}원</strong></td>
        </tr>
    </table>

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:fn_Reg();">등록</a></li>
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

window.onload = function(){
};

$(document).ready(function(){
    if($("input[name='CLASSARR']:checked").size()>0){
        if($("input[name='CLASSARR']:checked").val().split(",")[1] == "001"){
            $("#subjectlen").html("(2과목)");
        }else if($("input[name='CLASSARR']:checked").val().split(",")[1] == "008"){
            $("#subjectlen").html("(3과목)");
        }else if($("input[name='CLASSARR']:checked").val().split(",")[1] == "006"){
            $("#subjectlen").html("(2과목)");
        }else if($("input[name='CLASSARR']:checked").val().split(",")[1] == "011"){
            $("#subjectlen").html("(2과목)");
        }else if($("input[name='CLASSARR']:checked").val().split(",")[1] == "015"){
            $("#subjectlen").html("(2과목)");
        }else if($("input[name='CLASSARR']:checked").val().split(",")[1] == "016"){
            $("#subjectlen").html("(2과목)");
        }else if($("input[name='CLASSARR']:checked").val().split(",")[1] == "020"){
            $("#subjectlen").html("(1과목)");
        }else{
            $("#subjectlen").html("");
        }

		$("input[name='CLASSARR']").change(function() {
			var curMockCd = $(this).val().split(",")[0];
			$("#EXAMCODE").val(curMockCd);
			$("#CLASSCODE").val($(this).val().split(",")[1]);
			$("#CLASSSERIESCODE").val($(this).val().split(",")[2]);

			if($("#CLASSCODE").val()=="001" || $("#CLASSCODE").val()=="008" || $("#CLASSCODE").val()=="006" || $("#CLASSCODE").val()=="011" || $("#CLASSCODE").val()=="015" || $("#CLASSCODE").val()=="016" || $("#CLASSCODE").val()=="020"){
				$(".ADDCHOICE").show();
			}else{
				$(".ADDCHOICE").hide();
			}

			curSubJect = "";
			<c:forEach items="${list}" var="item" varStatus="index">
				if(curMockCd == "${item.MOCKCODE}"){
					<c:if test="${!empty item.SUBJECT_NM}" >

						var useYn = "N";
						<c:forEach items="${clslist}" var="citem" varStatus="cindex">
							if( $("#CLASSCODE").val() == "${citem.CLASSCODE}" && $("#CLASSSERIESCODE").val() == "${citem.CLASSSERIESCODE}" && "${item.SUBJECT_CD}" == "${citem.SUBJECT_CD}" ){
								useYn = "Y";
							}
						</c:forEach>
						if(useYn == "Y"){
							curSubJect += "<input name=\"CHOICEITEMARR\" type=\"checkbox\" value=\"${item.ITEMID},${item.SUBJECT_NM}\">" + "&nbsp;${item.SUBJECT_NM}&nbsp;";
						}
						//curSubJect += "<input name=\"CHOICEITEMARR\" type=\"checkbox\" value=\"${item.ITEMID},${item.SUBJECT_NM}\">" + "&nbsp;${item.SUBJECT_NM}&nbsp;";
					</c:if>
					curCost = "${item.SALEAMOUNTS}";

					curDate = "${fn:substring(item.EXAMSTARTTIME,4, 6)}월&nbsp;";
					curDate += "${fn:substring(item.EXAMSTARTTIME,6, 8)}일&nbsp;";
					curDate += "${fn:substring(item.EXAMSTARTTIME,8, 10)}시";
					curDate += " ~ ";
					//curDate += "${fn:substring(item.EXAMENDTIME,4, 6)}월&nbsp;";
					//curDate += "${fn:substring(item.EXAMENDTIME,6, 8)}일&nbsp;";
					curDate += "${fn:substring(item.EXAMENDTIME,8, 10)}시";

					$("#MOCKNAME").val("${item.MOCKNAME}");

					$("#OFFCLOSEPERSONNUMBER").val("${item.OFFCLOSEPERSONNUMBER}");

				}
			</c:forEach>

			$("#PAYMENTTARGETAMOUNT").val(curCost);
			$("#CHOICEITEMAREA").html(curSubJect);
			$("#COSTAREA > strong").html(curCost+"원");
			$("#DATEAREA > strong").html(curDate);

		});

        var curMockCd = $("input[name='CLASSARR']:checked").val().split(",")[0];
        $("#EXAMCODE").val(curMockCd);
        $("#CLASSCODE").val($("input[name='CLASSARR']:checked").val().split(",")[1]);
        $("#CLASSSERIESCODE").val($("input[name='CLASSARR']:checked").val().split(",")[2]);

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
                    curSubJect += "<input name=\"CHOICEITEMARR\" type=\"checkbox\" value=\"${item.ITEMID},${item.SUBJECT_NM}\">" + "&nbsp;${item.SUBJECT_NM}&nbsp;";
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
    }
});

function fn_Reg(){
    if($("#CLASSCODE").val()=="001" || $("#CLASSCODE").val()=="008" || $("#CLASSCODE").val()=="006" || $("#CLASSCODE").val()=="011" || $("#CLASSCODE").val()=="015" || $("#CLASSCODE").val()=="020"){
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
        if($("#CLASSCODE").val()=="011"){
            if($("input[name='CHOICEITEMARR']:checked").size()!=2){
                alert("선택과목을 2과목 선택해주세요");
                return;
            }
        }
        if($("#CLASSCODE").val()=="015"){
            if($("input[name='CHOICEITEMARR']:checked").size()!=2){
                alert("선택과목을 2과목 선택해주세요");
                return;
            }
        }
        if($("#CLASSCODE").val()=="016"){
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

        curSubJect = "";
        curText = "";
        $("input[name='CHOICEITEMARR']:checked").each(function(){
            curSubJect += "<input name=\"CHOICEITEMARR\" type=\"hidden\" value=\"" + $(this).val().split(",")[0] +  "\">";
            if(curText!=""){
                curText += "&nbsp;,&nbsp;";
            }
            curText += $(this).val().split(",")[1];
        });
        $("#CHOICEITEMAREA", opener.document).html(curSubJect);
    }
    $("#mouiArea1", opener.document).html($("#MOCKNAME").val() + "<br/>" + curText);
    $("#CLASSCODE", opener.document).val($("#CLASSCODE").val());
    $("#CLASSSERIESCODE", opener.document).val($("#CLASSSERIESCODE").val());
    $("#EXAMCODE", opener.document).val($("#EXAMCODE").val());
    $("#EXAMYEAR", opener.document).val($("#EXAMYEAR").val());
    $("#EXAMROUND", opener.document).val($("#EXAMROUND").val());
    //$("#RECEIPTTYPE", opener.document).val($("#RECEIPTTYPE").val());
    $("#PAYMENTSTATE", opener.document).val($("#PAYMENTSTATE").val());
    $("#PAYMENTTARGETAMOUNT", opener.document).val($("#PAYMENTTARGETAMOUNT").val());
    $("#ADDPOINT1", opener.document).val($("input[name='ADDPOINT1']:checked").val());
    $("#ADDPOINT2", opener.document).val($("input[name='ADDPOINT2']:checked").val());
    $("#ADDPOINT3", opener.document).val($("input[name='ADDPOINT3']:checked").val());
    $("#PAYMENTAMOUNT", opener.document).val($("#PAYMENTTARGETAMOUNT").val());
    $("#OFFCLOSEPERSONNUMBER", opener.document).val($("#OFFCLOSEPERSONNUMBER").val());

    $("#PAYAREA", opener.document).html($("#PAYMENTTARGETAMOUNT").val()+"원");
    $("#mouiArea2", opener.document).html($("#PAYMENTTARGETAMOUNT").val()+"원");
    $("#mouiArea3", opener.document).html($("#DATEAREA > strong").html());

    self.close();
}

</script>
