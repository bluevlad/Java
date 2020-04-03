<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><head>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mt_upg.css' />" >

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    if("back"=="${returnData}"){
        alert("성적데이타가 등록되지 않아 확인이 불가능합니다");
        history.back();
    }
</script>
</head>


<div id="content">
    <h2>● 경영관리 > <strong>개인별 모의고사통계</strong></h2>
<form id="form" name="form" method="post" action="<c:url value='/stats/statsPersonList.do' />">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}" />
<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}" />
<input type="hidden" id="SEARCHROUND" name="SEARCHROUND" value="${params.SEARCHROUND}" />
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}" />
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" />
<input type="hidden" id="IDENTYID" name="IDENTYID" value="${params.IDENTYID}" />
<input type="hidden" id="MOCKCODE" name="MOCKCODE" value="${params.MOCKCODE}" />
<input type="hidden" id="USER_ID" name="USER_ID" value="${params.USER_ID}" />

    <ul class="tabA">
        <li><a href="<c:url value="/stats/statsPersonTotalList.do"/>?MENU_ID=${MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&IDENTYID=${params.IDENTYID}&MOCKCODE=${params.MOCKCODE}&USER_ID=${params.USER_ID}" class="active">전체 성적 분석</a></li>
        <li><a href="<c:url value="/stats/statsPersonSubjectList.do"/>?MENU_ID=${MENU_ID}&TOP_MENU_ID=${TOP_MENU_ID}&MENUTYPE=${MENUTYPE}&IDENTYID=${params.IDENTYID}&MOCKCODE=${params.MOCKCODE}&USER_ID=${params.USER_ID}">과목별 문항 분석</a></li>
    </ul>

    <table border="0" cellspacing="0" cellpadding="0" class="table01">
      <tr>
        <th width="20%">시행일</th>
        <th width="20%">응시직급</th>
        <th width="20%">응시직렬</th>
        <th width="20%">응시번호</th>
        <th>성명</th>
      </tr>
      <tr>
        <td class="tdCenter">
            ${fn:substring(totalinfolist[0].EXAMSTARTTIME,4, 6)}월
            ${fn:substring(totalinfolist[0].EXAMSTARTTIME,6, 8)}일
            ${fn:substring(totalinfolist[0].EXAMSTARTTIME,8, 10)}시
            ${fn:substring(totalinfolist[0].EXAMSTARTTIME,10, 12)}분
        </td>
        <td class="tdCenter">${totalinfolist[0].CLASSCODENM}</td>
        <td class="tdCenter">${totalinfolist[0].CLASSSERIESCODENM}</td>
        <td class="tdCenter">${totalinfolist[0].IDENTYID}</td>
        <td class="tdCenter">${totalinfolist[0].USER_NM}</td>
      </tr>
    </table>
    <ul class="allAssay">
        <li class="leftTable">
          <table border="0" cellspacing="0" cellpadding="0" class="tableA">
          <caption class="smallSub">종합분석</caption>
          <tr>
            <th width="20%">구분</th>
            <th width="20%">원점수</th>
            <th width="20%">조정점수</th>
            </tr>
          <tr>
            <td class="tdCenter">총점</td>
            <td class="tdCenter">${totalinfo1_map.T_O}</td>
            <td class="tdCenter">${totalinfo1_map.T_A}</td>
            </tr>
          <tr>
            <td class="tdCenter">평균</td>
            <td class="tdCenter">${totalinfo1_map.PA_O}</td>
            <td class="tdCenter">${totalinfo1_map.PA_A}</td>
            </tr>
          <tr>
            <td class="tdCenter">전체평균</td>
            <td class="tdCenter">${totalinfo1_map.TA_O}</td>
            <td class="tdCenter">${totalinfo1_map.TA_A}</td>
          </tr>
          <tr>
            <td class="tdCenter">석차</td>
            <td class="tdCenter">${totalinfo1_map.R_O} / ${totalinfo1_map.TO_O}</td>
            <td class="tdCenter">${totalinfo1_map.R_A} / ${totalinfo1_map.TO_A}</td>
          </tr>
          <tr>
            <td class="tdCenter">상위수준</td>
            <td class="tdCenter">${totalinfo1_map.TOPPER_O}%</td>
            <td class="tdCenter">${totalinfo1_map.TOPPER_A}%</td>
          </tr>
        </table>
        </li>

        <!-- 그래프영역1 -->
        <li class="rightGraph" style="padding-top:0px;">
            <div id="chart_div1" style="width: 450px; height: 265px;"></div>
        </li>
        <script type="text/javascript">
            var tot_avg1 = parseInt("${totalinfo1_map.TA_A}");
            var top_avg1 = parseInt("${totalinfo1_map.TO_SA}");
            var my_avg1 = parseInt("${totalinfo1_map.PA_A}");
            google.load('visualization', '1', {packages: ['corechart']});
            function drawVisualization() {
                // Some raw data (not necessarily accurate)
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Topping');
                data.addColumn('number', '점수');
                data.addRows([
                    ['전체', tot_avg1],
                    ['최고', top_avg1],
                    ['본인', my_avg1]
                ]);
                var options = {
                    title : '조정점수 기준'
                    ,seriesType: "bars"
                    ,vAxis: {minValue:0}
                };
                var chart = new google.visualization.ComboChart(document.getElementById('chart_div1'));
                chart.draw(data, options);
            }
            google.setOnLoadCallback(drawVisualization);
        </script>
        <!-- //그래프영역1 -->
    </ul>
    <p class="textInfo_03"><span>(${totalinfolist[0].USER_NM})</span>님의 점수는
      <br>
평균 <span>(${totalinfo1_map.PA_A}점)</span>으로, 전체 <span>(${totalinfo1_map.TO_A}명)</span>에서 <span>(${totalinfo1_map.R_A}위)</span>이며  상위 수준 <span>(${totalinfo1_map.TOPPER_A}%)</span>입니다.</p>
    <ul class="allAssay01">
        <li>
            <p class="smallSub">전체 응시자 평균점수 분포표</p>
            <!-- 그래프영역2 -->
            <div>
                <div id="chart_div2" style="width: 900px; height: 365px;"></div>
            </div>
            <script type="text/javascript">
                var g_my_size = 0;
                function drawVisualization2() {
                    var val2_5 = parseInt("${totalinfo2_map.val_5}");
                    var val2_10 = parseInt("${totalinfo2_map.val_10}");
                    var val2_15 = parseInt("${totalinfo2_map.val_15}");
                    var val2_20 = parseInt("${totalinfo2_map.val_20}");
                    var val2_25 = parseInt("${totalinfo2_map.val_25}");
                    var val2_30 = parseInt("${totalinfo2_map.val_30}");
                    var val2_35 = parseInt("${totalinfo2_map.val_35}");
                    var val2_40 = parseInt("${totalinfo2_map.val_40}");
                    var val2_45 = parseInt("${totalinfo2_map.val_45}");
                    var val2_50 = parseInt("${totalinfo2_map.val_50}");
                    var val2_55 = parseInt("${totalinfo2_map.val_55}");
                    var val2_60 = parseInt("${totalinfo2_map.val_60}");
                    var val2_65 = parseInt("${totalinfo2_map.val_65}");
                    var val2_70 = parseInt("${totalinfo2_map.val_70}");
                    var val2_75 = parseInt("${totalinfo2_map.val_75}");
                    var val2_80 = parseInt("${totalinfo2_map.val_80}");
                    var val2_85 = parseInt("${totalinfo2_map.val_85}");
                    var val2_90 = parseInt("${totalinfo2_map.val_90}");
                    var val2_95 = parseInt("${totalinfo2_map.val_95}");
                    var val2_100 = parseInt("${totalinfo2_map.val_100}");

                    if(g_my_size < val2_5) g_my_size = val2_5;
                    if(g_my_size < val2_10) g_my_size = val2_10;
                    if(g_my_size < val2_15) g_my_size = val2_15;
                    if(g_my_size < val2_20) g_my_size = val2_20;
                    if(g_my_size < val2_25) g_my_size = val2_25;
                    if(g_my_size < val2_30) g_my_size = val2_30;
                    if(g_my_size < val2_35) g_my_size = val2_35;
                    if(g_my_size < val2_40) g_my_size = val2_40;
                    if(g_my_size < val2_45) g_my_size = val2_45;
                    if(g_my_size < val2_50) g_my_size = val2_50;
                    if(g_my_size < val2_55) g_my_size = val2_55;
                    if(g_my_size < val2_60) g_my_size = val2_60;
                    if(g_my_size < val2_65) g_my_size = val2_65;
                    if(g_my_size < val2_70) g_my_size = val2_70;
                    if(g_my_size < val2_75) g_my_size = val2_75;
                    if(g_my_size < val2_80) g_my_size = val2_80;
                    if(g_my_size < val2_85) g_my_size = val2_85;
                    if(g_my_size < val2_90) g_my_size = val2_90;
                    if(g_my_size < val2_95) g_my_size = val2_95;
                    if(g_my_size < val2_100) g_my_size = val2_100;

                    if(0 <= my_avg1 && my_avg1 <= 5) var g_my_val5 = g_my_size;
                    if(5 < my_avg1 && my_avg1 <= 10) var g_my_val10 = g_my_size;
                    if(10 < my_avg1 && my_avg1 <= 15) var g_my_val15 = g_my_size;
                    if(15 < my_avg1 && my_avg1 <= 20) var g_my_val20 = g_my_size;
                    if(20 < my_avg1 && my_avg1 <= 25) var g_my_val25 = g_my_size;
                    if(25 < my_avg1 && my_avg1 <= 30) var g_my_val30 = g_my_size;
                    if(30 < my_avg1 && my_avg1 <= 35) var g_my_val35 = g_my_size;
                    if(35 < my_avg1 && my_avg1 <= 40) var g_my_val40 = g_my_size;
                    if(40 < my_avg1 && my_avg1 <= 45) var g_my_val45 = g_my_size;
                    if(45 < my_avg1 && my_avg1 <= 50) var g_my_val50 = g_my_size;
                    if(50 < my_avg1 && my_avg1 <= 55) var g_my_val55 = g_my_size;
                    if(55 < my_avg1 && my_avg1 <= 60) var g_my_val60 = g_my_size;
                    if(60 < my_avg1 && my_avg1 <= 65) var g_my_val65 = g_my_size;
                    if(65 < my_avg1 && my_avg1 <= 70) var g_my_val70 = g_my_size;
                    if(70 < my_avg1 && my_avg1 <= 75) var g_my_val75 = g_my_size;
                    if(75 < my_avg1 && my_avg1 <= 80) var g_my_val80 = g_my_size;
                    if(80 < my_avg1 && my_avg1 <= 85) var g_my_val85 = g_my_size;
                    if(85 < my_avg1 && my_avg1 <= 90) var g_my_val90 = g_my_size;
                    if(90 < my_avg1 && my_avg1 <= 95) var g_my_val95 = g_my_size;
                    if(95 < my_avg1 && my_avg1 <= 100) var g_my_val100 = g_my_size;


                    // Some raw data (not necessarily accurate)
                    var data = google.visualization.arrayToDataTable([
                        ["Score",  "분포도",  "It' you"],
                        ['5',  val2_5,  g_my_val5],
                        ['10',  val2_10,  g_my_val10],
                        ['15',  val2_15,  g_my_val15],
                        ['20',  val2_20,  g_my_val20],
                        ['25',  val2_25,  g_my_val25],
                        ['30',  val2_30,  g_my_val30],
                        ['35',  val2_35,  g_my_val35],
                        ['40',  val2_40,  g_my_val40],
                        ['45',  val2_45,  g_my_val45],
                        ['50',  val2_50,  g_my_val50],
                        ['55',  val2_55,  g_my_val55],
                        ['60',  val2_60,  g_my_val60],
                        ['65',  val2_65,  g_my_val65],
                        ['70',  val2_70,  g_my_val70],
                        ['75',  val2_75,  g_my_val75],
                        ['80',  val2_80,  g_my_val80],
                        ['85',  val2_85,  g_my_val85],
                        ['90',  val2_90,  g_my_val90],
                        ['95',  val2_95,  g_my_val95],
                        ['100',  val2_100,  g_my_val100]
                    ]);
                    var options = {
                        title : '조정점수 기준',
                        vAxis: {minValue:0 },
                        hAxis: {minValue:0, },
                        seriesType: "line"
                        ,series: {1: {type: "bars"}}
                    };
                    var chart = new google.visualization.ComboChart(document.getElementById('chart_div2'));
                    chart.draw(data, options);
                }
                google.setOnLoadCallback(drawVisualization2);
            </script>
            <!-- //그래프영역2 -->
        </li>
        <li>
            <table border="0" cellspacing="0" cellpadding="0" class="table01 mgT20">
              <caption class="smallSub">과목별 득점분석</caption>
              <tr>
                <th rowspan="2">구분</th>
                <c:forEach items="${totalinfo2_TblH}" var="item" varStatus="index">
                    <c:if test="${item.SUBJECTTYPEDIVISION eq '1' }"><th rowspan="2">${item.SUBJECT_NM}</th></c:if>
                    <c:if test="${item.SUBJECTTYPEDIVISION ne '1' }"><th colspan="2">${item.SUBJECT_NM}</th></c:if>
                </c:forEach>
              </tr>
              <tr>
                <c:forEach items="${totalinfo2_TblH}" var="item" varStatus="index">
                    <c:if test="${item.SUBJECTTYPEDIVISION ne '1' }">
                        <th width="12%">원점수</th>
                        <th width="12%">조정점수</th>
                    </c:if>
                </c:forEach>
              </tr>

            <c:forEach items="${TblArr}" var="steps" varStatus="loop">
                <tr>
                    <td class="tdCenter">
                        <c:if test="${loop.index eq '0' }">본인</c:if>
                        <c:if test="${loop.index eq '1' }">전체평균</c:if>
                        <c:if test="${loop.index eq '2' }">최고</c:if>
                        <c:if test="${loop.index eq '3' }">과목석차</c:if>
                    </td>
                        <c:forEach items="${steps}" var="item" varStatus="index">
                            <td class="tdCenter">${item}</td>
                        </c:forEach>
                </tr>
            </c:forEach>
            </table>
        </li>
        <li>
            <table border="0" cellspacing="0" cellpadding="0" class="table01 mgT20">
              <caption class="smallSub">과목별 점수 위치</caption>

                <c:forEach items="${Tbl3Arr}" var="steps" varStatus="loop">
                    <tr>
                        <c:forEach items="${steps}" var="item" varStatus="index">
                            <c:if test="${index.index eq '0' }">
                                <th width="10%">${item}</th>
                                <td><div id="chart3_div${loop.index}" style="width: 700px; height: 165px;"></div></td>
                            </c:if>
                            <script type="text/javascript">
                                <c:if test="${index.index eq '1' }">my_avg = parseInt("${item}");</c:if>
                                <c:if test="${index.index eq '2' }">tot_avg = parseInt("${item}");</c:if>
                                <c:if test="${index.index eq '3' }">top_avg = parseInt("${item}");</c:if>
                            </script>
                        </c:forEach>
                    </tr>

                    <script type="text/javascript">
                        // Some raw data (not necessarily accurate)
                        var data = new google.visualization.DataTable();
                        data.addColumn('string', 'Topping');
                        data.addColumn('number', '점수');
                        data.addRows([
                            ['전체', tot_avg],
                            ['최고', top_avg],
                            ['본인', my_avg]
                        ]);
                        var options = {
                            vAxis: {minValue:0}
                        };
                        var chart = new google.visualization.BarChart(document.getElementById("chart3_div${loop.index}"));
                        chart.draw(data, options);
                    </script>
                </c:forEach>
            </table>
        </li>
    </ul>
    <!-- 
    <ul class="allAssay">
    <li class="leftTable">
          <table border="0" cellspacing="0" cellpadding="0" class="table01">
          <caption class="smallSub">응시회차 별 성적비교 (최근 5회)</caption>
            <c:forEach items="${Tbl4Arr}" var="steps" varStatus="loop">
                <tr>
                    <c:forEach items="${steps}" var="item" varStatus="index">
                        <c:if test="${loop.index eq '0' }">
                            <th>${item}</th>
                        </c:if>
                        <c:if test="${loop.index ne '0' }">
                            <td class="tdCenter">${item}</td>
                        </c:if>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        </li>
        <li class="rightGraph" style="padding-left:0px;padding-top:0px;">
            <div>
                <div id="chart_div4" style="width: 450px; height: 265px;"></div>
            </div>
            <script type="text/javascript">
                function drawVisualization4() {
                    // Some raw data (not necessarily accurate)
                    var data = new google.visualization.DataTable();
                    data.addColumn('string', 'Topping');
                    data.addColumn('number', '점수');
                    data.addRows([
                    <c:forEach items="${chartAvg}" var="item" varStatus="loop">
                        <c:if test="${loop.index eq '0' }">
                            ["${item.TIT}", parseInt("${item.VAL}")]
                        </c:if>
                        <c:if test="${loop.index ne '0' }">
                            ,["${item.TIT}", parseInt("${item.VAL}")]
                        </c:if>
                    </c:forEach>
                    ]);
                    var options = {
                        title : '평균점수 그래프'
                        ,seriesType: "bars"
                        ,vAxis: {minValue:0}
                    };
                    var chart = new google.visualization.ComboChart(document.getElementById('chart_div4'));
                    chart.draw(data, options);
                }
                google.setOnLoadCallback(drawVisualization4);
            </script>
        </li>
    </ul>
 -->
    <!--버튼-->
    <ul class="boardBtns">
        <li><a href='javascript:fn_List();'>목록</a></li>
    </ul>
    <!--//버튼-->

</form>
</div>
<!--//content -->

<script type="text/javascript">
// 목록으로
function fn_List(){
    $("#form").attr("action","<c:url value='/stats/statsPersonList.do' />");
    $("#form").submit();
}
// 인쇄
function fn_Print(){
    print();
}
</script>
