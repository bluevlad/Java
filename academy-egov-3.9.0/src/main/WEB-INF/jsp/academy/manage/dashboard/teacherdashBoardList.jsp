<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>


<%-- <link rel="stylesheet" href="<c:url value='/resources/layout.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/common.css'/>">

<link href="<c:url value="/resources/libs/jquery-ui/css/redmond/jquery-ui-1.8.18.custom.css" />" rel="stylesheet" type="text/css"  />
<link href="<c:url value="/resources/libs/jquery-timepicker/jquery.ui.timepicker.css" />" rel="stylesheet" type="text/css"  />

<script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/jquery.ui.timepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.position.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.widget.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.tabs.min.js" />"></script> 
  <script src="https://www.google.com/uds/?file=visualization&amp;v=1&amp;packages=corechart" type="text/javascript"></script>
--%>



<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>매출통계</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<style>
.ui-datepicker select.ui-datepicker-month{ width:30%; font-size: 12px; }
.ui-datepicker select.ui-datepicker-year{ width:40%; font-size: 12px; }
</style>
<link href="<c:url value='/resources/css/skin/blue.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/libs/jquery-ui/css/redmond/jquery-ui-1.8.18.custom.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/libs/jquery-timepicker/jquery.ui.timepicker.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/paginate.css" type="text/css" rel="stylesheet'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/reset.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/base.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/dashstyle.css'/>">
<script src="<c:url value='/resources/libs/jquery/jquery-1.11.2.min.js'/>"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
$(function(){
     $.datepicker.regional['ko'] = {
          closeText: '닫기',
          prevText: '이전',
          nextText: '다음',
          currentText: '오늘',
          monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
          dayNames: ['일','월','화','수','목','금','토'],
          dayNamesShort: ['일','월','화','수','목','금','토'],
          dayNamesMin: ['일','월','화','수','목','금','토'],
          dateFormat: 'yymmdd',
          firstDay: 0,
          showMonthAfterYear: true,
          changeYear: true,
          yearSuffix: '년 ',
          autoSize: false};
     $.datepicker.setDefaults($.datepicker.regional['ko']);
});
</script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		google.load('visualization', '1', {packages: ['corechart']});
	</script>

	<script>
	function fn_teacher_list() {
		$("#listForm").attr("action", "<c:url value='/teacherMypage/dashBoardList.html' />");
		$("#listForm").submit();
	}

	function drawVisualization() {
		// Some raw data (not necessarily accurate)
		var data = google.visualization.arrayToDataTable([
			['Month', '전년도월', '현재년도월'],
			<c:forEach items="${dashBoard3}" var="data" varStatus="status">
			['01',  <c:out value="${data.P_01}" />, <c:out value="${data.C_01}" />],
			['02',  <c:out value="${data.P_02}" />, <c:out value="${data.C_02}" />],
			['03',  <c:out value="${data.P_03}" />, <c:out value="${data.C_03}" />],
			['04',  <c:out value="${data.P_04}" />, <c:out value="${data.C_04}" />],
			['05',  <c:out value="${data.P_05}" />, <c:out value="${data.C_05}" />],
			['06',  <c:out value="${data.P_06}" />, <c:out value="${data.C_06}" />],
			['07',  <c:out value="${data.P_07}" />, <c:out value="${data.C_07}" />],
			['08',  <c:out value="${data.P_08}" />, <c:out value="${data.C_08}" />],
			['09',  <c:out value="${data.P_09}" />, <c:out value="${data.C_09}" />],
			['10',  <c:out value="${data.P_10}" />, <c:out value="${data.C_10}" />],
			['11',  <c:out value="${data.P_11}" />, <c:out value="${data.C_11}" />],
			['12',  <c:out value="${data.P_12}" />, <c:out value="${data.C_12}" />]
			</c:forEach>
		]);
		var options = {
			seriesType: "bars"
		};
		var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
		chart.draw(data, options);
	}
	google.setOnLoadCallback(drawVisualization);

	</script>
<script type="text/javascript">
  google.load("visualization", "1", {packages:["corechart"]});
  google.setOnLoadCallback(drawChart);
  function drawChart() {

	  
	  <c:set var="area01" value="0" />
       <c:set var="area02" value="0" />
       <c:set var="area03" value="0" />
       <c:set var="area04" value="0" />
       <c:set var="area05" value="0" />
       <c:set var="area06" value="0" />
       <c:set var="area07" value="0" />
       <c:set var="area08" value="0" />
       <c:set var="area09" value="0" />
       <c:set var="area10" value="0" />
       <c:set var="area11" value="0" />
       <c:set var="area12" value="0" />
       <c:set var="area13" value="0" />
       <c:set var="area14" value="0" />
       <c:set var="area15" value="0" />
       <c:set var="area16" value="0" />
       <c:forEach items="${dashBoard4}" var="data" varStatus="status">
         <c:if test="${data.NAME ne '총 수강인원' }">
       	<c:if test="${data.CODE_VAL eq 'SE' }"><!-- 서울 -->
       		 <c:set var="area01" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'CB' }"><!-- 충북 -->
       		 <c:set var="area02" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'KY' }"><!-- 경기 -->
       		 <c:set var="area03" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'CN' }"><!-- 충남 -->
       		 <c:set var="area04" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'IC' }"><!-- 인천-->
       		 <c:set var="area05" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'DJ' }"><!-- 대전-->
       		 <c:set var="area06" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'KW' }"><!-- 강원-->
       		 <c:set var="area07" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'JB' }"><!-- 전북-->
       		 <c:set var="area08" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'JN' }"><!-- 전남-->
       		 <c:set var="area09" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'KN' }"><!-- 경남-->
       		 <c:set var="area10" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'KJ' }"><!-- 광주 -->
       		 <c:set var="area11" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'US' }"><!-- 울산 -->
       		 <c:set var="area12" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'KB' }"><!--경북 -->
       		 <c:set var="area13" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'BU' }"><!-- 부산 -->
       		 <c:set var="area14" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'TK' }"><!-- 대구 -->
       		 <c:set var="area15" value="${data.USER_COUNT}" />
        	</c:if>
             <c:if test="${data.CODE_VAL eq 'JJ' }"><!-- 제주 -->
       		 <c:set var="area16" value="${data.USER_COUNT}" />
        	</c:if>
         </c:if>
</c:forEach>
	  
    var data = google.visualization.arrayToDataTable([
      ['Local', 'Percentage'],
      ['서울', ${area01 }], ['경기', ${area03 }],
      ['인천', ${area05 }], ['강원', ${area07 }],
      ['전남', ${area09 }], ['광주', ${area11 }],
      ['경북', ${area13 }],['대구', ${area15 }],
      ['충북', ${area02 }], ['충남', ${area04 }],
      ['대전', ${area06 }], ['전북', ${area08 }],
      ['경남', ${area10 }], ['울산', ${area12 }],
      ['부산', ${area14 }], ['제주', ${area16 }]
    ]);

    var options = {
      width:350,
      height:350,
      pieHole: 0.4,
      legend: 'none',
      title: '지역별 수강인원'
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
  }
</script>
</head>
<body>
<form name="listForm" id="listForm" method="post" action="">
<div class="statWrap">
  <table class="basicTb">
    <colgroup>
      <col width="20%">
      <col width="30%">
      <col width="20%">
      <col width="30%">
    </colgroup>
    <tbody>
      <tr>
        <th>교수명</th>
        <td>${Teachernm}</td>
        <th>조회기간</th>
        <td>${startdate } ~ ${ enddate}</td>
      </tr>      
    </tbody>
  </table>
  
  
  
  <c:set var="totalPrice" value="0" />
  <c:forEach items="${dashBoard1}" var="data" varStatus="status">
	<c:choose>
	<c:when test="${data.LEC_TYPE_CHOICE eq 'A'}">
		<c:set var="totalPrice" value="${data.TOTAL_PRICE}" />
	</c:when>
	<c:otherwise>
	</c:otherwise>
	</c:choose>
  </c:forEach>
	
  <div class="dashBdWp">
    <div class="dashBd fL">
      <h3>● 월별 매출 관리</h3>
      <div class="bx">
        <table>
          <caption>월별 매출 관리</caption>
          <tfoot>
            <tr>
              <th>실매출</th>
              <td><fmt:formatNumber value="${totalPrice }" groupingUsed="true"/></td>
            </tr>
          </tfoot>
          <tbody>
          <c:forEach items="${dashBoard1}" var="data" varStatus="status">
                  <tr>
			<c:choose>
			<c:when test="${data.LEC_TYPE_CHOICE eq 'A'}">
				<th>총매출</th>
				<td><fmt:formatNumber value="${data.SUM_PRICE }" groupingUsed="true"/></td>
			</c:when>
			<c:otherwise>
				<th>${data.LEC_TYPE_CHOICE_DESC }</th>
				<td><fmt:formatNumber value="${data.TOTAL_PRICE }" groupingUsed="true"/></td>
			</c:otherwise>
			</c:choose>
			</tr>
           </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <div class="dashBd fR">
      <h3>● 강의별 수강인원</h3>
      <div class="bx">
        <table>
          <caption>강의별 수강인원</caption>
          <tfoot>
            <c:forEach items="${dashBoard2}" var="data" varStatus="status">
                 <c:if test="${data.NAME == '총 수강인원' }">
			<tr>
				<th>${data.NAME }</th>
				<td><fmt:formatNumber value="${data.USER_COUNT }" groupingUsed="true"/></td>
			</tr>
			</c:if>
			</c:forEach>
          </tfoot>
          <tbody>
            <c:forEach items="${dashBoard2}" var="data" varStatus="status">
                  <c:if test="${data.NAME ne '총 수강인원' }">
			<tr>
				<th>${data.NAME }</th>
				<td><fmt:formatNumber value="${data.USER_COUNT }" groupingUsed="true"/></td>
			</tr>
			</c:if>
			</c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <h3>● 지역별 수강인원</h3>
  <div class="dashBdWp">
    <div class="tb">             
      <table class="localDataTb">
        <caption>지역별 수강인원</caption>
        <thead>
          <tr>
            <th>지역</th>
            <td>인원</td>
            <th>지역</th>
            <td>인원</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th>서울</th>
            <td>${area01 }명</td>
            <th>충북</th>
            <td>${area02 }명</td>
          </tr>
          <tr>
            <th>경기</th>
            <td>${area03 }명</td>
            <th>충남</th>
            <td>${area04 }명</td>
          </tr>
          <tr>
            <th>인천</th>
            <td>${area05 }명</td>
            <th>대전</th>
            <td>${area06 }명</td>
          </tr>
          <tr>
            <th>강원</th>
            <td>${area07 }명</td>
            <th>전북</th>
            <td>${area08 }명</td>
          </tr>
          <tr>
            <th>전남</th>
            <td>${area09 }명</td>
            <th>경남</th>
            <td>${area10 }명</td>
          </tr>
          <tr>
            <th>광주</th>
            <td>${area11 }명</td>
            <th>울산</th>
            <td>${area12 }명</td>
          </tr>
          <tr>
            <th>경북</th>
            <td>${area13 }명</td>
            <th>부산</th>
            <td>${area14 }명</td>
          </tr>
          <tr>
            <th>대구</th>
            <td>${area15 }명</td>
            <th>제주</th>
            <td>${area16 }명</td>
          </tr>                 
        </tbody>
      </table>
    </div> 
    <div class="chart">
      <div id="piechart"></div>      
      <ul class="pieChartLegend">
        <li><span class="c1"></span>서울</li>
        <li><span class="c2"></span>경기</li>
        <li><span class="c3"></span>인천</li>
        <li><span class="c4"></span>강원</li>
        <li><span class="c5"></span>전남</li>
        <li><span class="c6"></span>광주</li>
        <li><span class="c7"></span>경북</li>
        <li><span class="c8"></span>대구</li>
        <li><span class="c9"></span>충북</li>
        <li><span class="c10"></span>충남</li>
        <li><span class="c11"></span>대전</li>
        <li><span class="c12"></span>전북</li>
        <li><span class="c13"></span>경남</li>
        <li><span class="c14"></span>울산</li>
        <li><span class="c15"></span>부산</li>
        <li><span class="c16"></span>제주</li>
      </ul>     
    </div>           
  </div>

  <h3>● 연간정산추이</h3>
  <div class="graphArea mgB3">
    <div id="chart_div" style="width:750px; height: 300px;"></div>
  </div>

 
</div>
<!-- //statWrap -->
			
</body>
</html>