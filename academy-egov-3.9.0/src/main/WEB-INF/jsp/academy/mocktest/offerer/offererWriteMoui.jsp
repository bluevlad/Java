<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
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
    <h2>● <strong>윌비스 경찰 전국모의고사</strong></h2>

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
        <!--가산점 : 오프라인 신청 경우 나타남 //-->
        <tr class="ADDCHOICE">
            <th colspan="2">선택과목</th>
            <td id="CHOICEITEMAREA">
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

$(document).ready(function(){
    if($("input[name='CLASSARR']:checked").size()>0){
        $("#subjectlen").html("");

		$("input[name='CLASSARR']").change(function() {
			var curMockCd = $(this).val().split(",")[0];
			$("#EXAMCODE").val(curMockCd);
			$("#CLASSCODE").val($(this).val().split(",")[1]);
			$("#CLASSSERIESCODE").val($(this).val().split(",")[2]);
			$(".ADDCHOICE").hide();
			
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

    $("#mouiArea1", opener.document).html($("#MOCKNAME").val() + "<br/>" + curText);
    $("#CLASSCODE", opener.document).val($("#CLASSCODE").val());
    $("#CLASSSERIESCODE", opener.document).val($("#CLASSSERIESCODE").val());
    $("#AREACODE", opener.document).val($("#AREACODE").val());
    $("#EXAMCODE", opener.document).val($("#EXAMCODE").val());
    $("#EXAMYEAR", opener.document).val($("#EXAMYEAR").val());
    $("#EXAMROUND", opener.document).val($("#EXAMROUND").val());
    //$("#RECEIPTTYPE", opener.document).val($("#RECEIPTTYPE").val());
    $("#PAYMENTSTATE", opener.document).val($("#PAYMENTSTATE").val());
    $("#PAYMENTTARGETAMOUNT", opener.document).val($("#PAYMENTTARGETAMOUNT").val());
    $("#PAYMENTAMOUNT", opener.document).val($("#PAYMENTTARGETAMOUNT").val());
    $("#OFFCLOSEPERSONNUMBER", opener.document).val($("#OFFCLOSEPERSONNUMBER").val());

    $("#PAYAREA", opener.document).html($("#PAYMENTTARGETAMOUNT").val()+"원");
    $("#mouiArea2", opener.document).html($("#PAYMENTTARGETAMOUNT").val()+"원");
    $("#mouiArea3", opener.document).html($("#DATEAREA > strong").html());

    self.close();
}

//지역선택
function isArea(val) {
  var curArea = "";

  if (val == 'O') {
      curArea = '<input type="radio" id="AREACODE" name="AREACODE" value="00">&nbsp;전국';
	}else{
	    <c:forEach items="${AreaList}" var="item" varStatus="status">
	    curArea += '<input type="radio" id="AREACODE" name="AREACODE" value="${item.CODEVALUE}">&nbsp;${item.CODENAME}&nbsp;&nbsp;';
		</c:forEach>
	}
	$("#vArea").html(curArea);

}

</script>
