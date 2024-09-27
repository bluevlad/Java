<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
</head>
<body>
<!--content -->
<div id="content_pop" style="padding-left:10px;">

	<h2>● 강의제작 > <strong>자료</strong></h2>
	
	<c:import url="/lecture/lecview.html" >
	    <c:param name="LECCODE" value="${params.LECCODE}" />
	</c:import>

<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="ADDAREA" name="ADDAREA" value="${params.ADDAREA}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
 <input type="hidden" id="LECCODE" name="LECCODE" value="${params.LECCODE}" />
    <p class="pInto01">&nbsp;</p>
          
    <!-- 테이블-->
    <table class="table02">
    <c:set var="Type" value=""/>
    
    <c:forEach items="${wmv}" var="item" varStatus="loop">
    	<c:if test="${fn:indexOf(item.WMV_PMP, 'PMP') > -1 }">
    		<c:set var="Type" value="PMP"/>
    	</c:if>
    </c:forEach>
    
    <c:if test="${Type eq 'PMP' }">
    	 <b>PMP 다운로드 (총강의 / 다운로드강의수) : ${list.size()} / ${down_count.size()}</b><br/>
    </c:if>
   <div class="fR"><button type="button" class="btnChkDn" onclick="javascript:MSelCheck('frm');">선택다운로드</button></div>
		<tr>
	        <th width="85">회차</th>
	        <th>학습내용</th>
	        <th>강의자료</th>
	        <th>동영상보기</th>
	        <th>강의시간</th>
	        <th>학습시간</th>
	        <th>최초수강</th>
	        <th>최종수강</th>
	        <th><input id="allCheck"  value="" type="checkbox" onclick="pmpCheckAll(this.checked)" /> PMP</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item"  varStatus="loop">
				<tr>
			         <td>${item.MOVIE_ORDER1}회 ${item.MOVIE_ORDER2}강</td>
			         <td>${item.MOVIE_NAME}</td>
			         <td><a href="javascript:fn_FileDownload('${item.MOVIE_DATA_FILENAME}');">${item.MOVIE_DATA_FILE_YN}</a></td>
			         <td>
			         		<c:if test="${item.MOVIE_FILENAME1 ne '' }"><a href="javascript:fn_player_pop('2', '${params.LECCODE}', '${params.BRIDGE_LECCODE}', '${item.MOVIE_NO}', 'Wide')">와이드</a></c:if>
			         		/<a href="javascript:fn_player_pop('2', '${params.LECCODE}', '${params.BRIDGE_LECCODE}', '${item.MOVIE_NO}', 'low_Q')">저화질</a>
			         		/<a href="javascript:fn_player_pop('2', '${params.LECCODE}', '${params.BRIDGE_LECCODE}', '${item.MOVIE_NO}', 'high_Q')">고화질</a>
			         <td>
						<c:set var="LIST_MOVIE_TIME" value="${item.MOVIE_TIME}" />
                        <c:set var="LIST_MOVIE_TIME_S" value="${LIST_MOVIE_TIME % 60}" />
                        <c:set var="LIST_MOVIE_TIME" value="${(LIST_MOVIE_TIME - LIST_MOVIE_TIME_S) / 60}" />
						<c:set var="LIST_MOVIE_TIME_M" value="${LIST_MOVIE_TIME % 60}" />
						<c:set var="LIST_MOVIE_TIME" value="${(LIST_MOVIE_TIME - LIST_MOVIE_TIME_M) / 60}" />
						<c:set var="LIST_MOVIE_TIME_H" value="${LIST_MOVIE_TIME % 60}" />
						<fmt:formatNumber value="${LIST_MOVIE_TIME_H}" type="number"/>시간
						<fmt:formatNumber value="${LIST_MOVIE_TIME_M}" type="number"/>분
						<fmt:formatNumber value="${LIST_MOVIE_TIME_S}" type="number"/>초
			         </td>
			         <td><font color="red">${item.CURR_TIME}</font>/${item.STUDY_TIME}(초)</td>
			         <td>${item.STUDY_DT}</td>
			         <td>${item.UPD_DT}</td>
			         <td>
						<c:set var="pmpFile" value="${item.PMP_URL}/${item.PMP_FILENAME}"/>
						<c:if test="${ !empty item.PMP_FILENAME}"><!-- ${pmpFile} -->
							<a href="javascript:SelCheck('${ params.LECCODE }', ${ item.MOVIE_NO });"><img src="/resources/images/ico15.gif" alt="내려받기"></a><!-- 다운로드 아이콘 -->
							 <c:choose>
								 <c:when test='${fn:indexOf(item.PMP_URL, "//") > 0}'>
								 	<c:set var="pmpUrl" value='${fn:substring(item.PMP_URL, fn:indexOf(item.PMP_URL, "//") + 2, fn:length(item.PMP_URL))}'></c:set>
								 </c:when>
								 <c:otherwise>
								 	<c:set var="pmpUrl" value='${fn:substring(item.PMP_URL, fn:indexOf(item.PMP_URL, "/") + 1, fn:length(item.PMP_URL))}'></c:set>
								 </c:otherwise>
							 </c:choose>
							<c:set var="pmpUrlDirector" value='${fn:substring(pmpUrl, fn:indexOf(pmpUrl, "/") + 1, fn:length(pmpUrl))}'></c:set>
							<input type="checkbox" id="${ item.MOVIE_NO }" name="cfilename" value="${ item.MOVIE_ORDER1 }회${item.MOVIE_ORDER2}_강.avi?|movie|?${pmpUrlDirector}/${item.PMP_FILENAME}?${item.MOVIE_NO}?${item.SAMPLE_START_DT}T00:00:00U${item.SAMPLE_END_DT}T24:00:00??-1?disallow?allow?video?${item.MOVIE_FREE_FLAG}-${params.LECCODE}">
							<input type="hidden" id="MOVIE_NO" name="MOVIE_NO" value="${item.MOVIE_NO}" />
						</c:if>			         	
			         </td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="8">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	 
        </tbody>
	</table>      
    <!-- //테이블--> 
	
    <!--버튼-->
	<ul class="boardBtns">
	    <li><a href="javascript:self.close();">닫기</a></li>
	</ul>
    <!--//버튼--> 	
	
</form>	
</div>
<!--//content --> 

<script type="text/javascript">

function fn_Player(G){
	 var w = '1020';  //가로 
	 var h = '550'; //세로 
	 var scroll = 'no'; //옵션
	 var name = "sample_StarPlayer";
	 var LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
     var TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
     var settings = 'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable=no' 
	 //var mp4url = encodeURI('http://hd.willbes.gscdn.com/');
	window.open('<c:url value="/player/view.pop2"/>?GUBUN=' + G, name, settings);
}

//function fn_player_pop(G,L,BRL, LK){
function fn_player_pop(G, L, BRL ,MNO, Q){
	var w = '960';  //가로
	var h = '500'; //세로
	if(Q == 'Wide'){
		//alert(1);
		w = '1575';  //가로1178
		h = '678'; //세로
	} else if(Q == 'high_Q'){
		//alert(2);
		w = '1530';  //가로960 + 570
		h = '540'; //세로
	} else if(Q == 'low_Q'){
		//alert(3);
		w = '916';  //가로960
		h = '405'; //세로
	} else {
		//alert(4);
		w = '960';  //가로
		h = '500'; //세로
	}
	 var scroll = 'no'; //옵션
	 var name = "sample_StarPlayer";
	 var LeftPosition = (screen.width) ? (screen.width-w)/2 : 0;
    var TopPosition = (screen.height) ? (screen.height-h)/2 : 0;
    var settings = 'height='+h+',width='+w+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',resizable=yes' 
	 //var mp4url = encodeURI('http://hd.willbes.gscdn.com/');
    var url = "<c:url value='/player/view.pop2'/>?GUBUN=" + G +"&Quality=" + Q;
    url += "&LECCODE=" + L;
    url += "&BRIDGE_LECCODE=" + BRL;
    url += "&MOVIE_NO=" + MNO;
    //url += "&LINK=" + LK;
    url += "&URL=" + encodeURI('http://hd.willbes.gscdn.com/');
	window.open(url, name, settings);
}

//파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
}

//pmp플레이어 다운로드
var DownUIWnd
var FormName = "";
function SelCheck(leccode, movieNo) {
	//alert(leccode + " <:> " +  movieNo);
	/*
	FormName = fname ;
	eval('var theForm = document.'+FormName ) ;
    
	var ChkObj = typeof(theForm.cfilename);
 	*/
 	//alert($("input[id='" + movieNo + "']").val());
	if (movieNo == null || movieNo == "") {
		alert("컨텐츠가 없습니다.");
	} else {
		if( DownUIWnd==undefined || DownUIWnd.closed ) {
			OpenDownUI(leccode, movieNo);
			return;
		}
		
		var cfilenameValue = $("input[id='" + movieNo + "']").val();
		AddFile(cfilenameValue);
		/*
		var TCount = typeof(theForm.cfilename.length);
		//alert(TCount);
		var sel_check = false;	
		
		if(TCount == "undefined") {
			if(theForm.cfilename.checked) {
				if( DownUIWnd==undefined || DownUIWnd.closed ) {
					OpenDownUI();
					return;
				}
				
				AddFile(theForm.cfilename[i].value);
			}
		} else {
			var FData = "";
			var CheckSize = theForm.cfilename.length;
		
			if( DownUIWnd==undefined || DownUIWnd.closed ) {
				OpenDownUI(leccode);
				return;
			}
			for(var i = 0; i < CheckSize; i++) {
				if (theForm.cfilename[i].checked) {
					sel_check = true;
					AddFile(theForm.cfilename[i].value);
				}
			}
		} 
		*/
	}
}

//체크박스로 한꺼번에 pmp 다운로드
//PMP 강의 전체 선택 소스

var DownUIWnd
var FormName = "";

function MSelCheck(fname) {

	FormName = fname ;
	eval('var theForm = document.'+FormName ) ;

	var ChkObj = typeof(theForm.cfilename);

	if (ChkObj == "undefined") {
		alert("컨텐츠가 없습니다.");
	} else {
		var TCount = typeof(theForm.cfilename.length);

		var sel_check = false;
		
		if(TCount == "undefined") {
			if(theForm.cfilename.checked) {
				if( DownUIWnd==undefined || DownUIWnd.closed ) {
					OpenMDownUI();
					return;
				}

				AddMFile(theForm.cfilename[i].value);
			}
		} else {
			var FData = "";
			var CheckSize = theForm.cfilename.length;

			if( DownUIWnd==undefined || DownUIWnd.closed ) {
				OpenMDownUI();
				return;
			}

			for(var i = 0; i < CheckSize; i++) {
				if (theForm.cfilename[i].checked) {
					sel_check = true;

					AddMFile(theForm.cfilename[i].value);
					//========================================================
					// cfilename value sample
					// 구분자 : ?
					// 1. 기기에 저장할 파일명
					// 2. 기기에 저장할 루트폴더 (|movie|:영상, |music|:음원)
					// 3. 컨텐트 다운로드 Path (BaseURL 제외)
					// 4. ContentID
					// 5. 재생기간 (yyyy-mm-ddThh:mm:ssUyyyy-mm-ddThh:mm:ss)
					// 6. 워터마크
					// 7. 재생횟수 (-1:무제한)
					// 8. TV-Out 여부 (allow/disallow)
					// 9. ScreenShot 여부 (allow/disallow)
				 // 10. video audio 설정
				 // 11. 다운로드 로그 추가 필드
					// PMP_UI.html의 AddFile스크립트 안의 XML스키마 형식에 맞춰 고정값이면 PMP_UI에서 처리해도 됨.
					//========================================================
				}
			}
		}
	}
}
//PMP 다운로드 팝업창을 띄운다.
function OpenMDownUI()
{
	//alert('test : '+'<c:out value="${ fn:length(pmpdownflag) }"/>');
	var pmpdownflagCnt = '<c:out value="${ fn:length(pmpdownflag) }"/>';
	var url = "<c:url value='/lecture/pmpDownloadView.pop3'/>?LECCODE="+$("#LECCODE").val() +"&MOVIE_NO=" + $("#MOVIE_NO").val() + "&DownType=M";

	if (pmpdownflagCnt != null && pmpdownflagCnt > 0){
		if( DownUIWnd==undefined || DownUIWnd.closed ){
			if(confirm("PMP강의는 강의파일을 다운받았을 경우에는 환불이 불가합니다. 진행 하시겠습니까?") ) {
				DownUIWnd = window.open(url, 'PMPDown_UI', 'width=618,height=729,menubar=no,status=yes,resizable=no,scrollbars=no');
			}
		}
	} else {
		if( DownUIWnd==undefined || DownUIWnd.closed ){
			DownUIWnd = window.open(url, 'PMPDown_UI', 'width=618,height=729,menubar=no,status=yes,resizable=no,scrollbars=no');
		}
	}
}

// PMP 다운로드 팝업창에 선택한 컨텐트 정보를 전달한다.
function AddMFile(FData)
{
	//alert(FData);
	if( DownUIWnd==undefined || DownUIWnd.closed )
	{
		OpenMDownUI();
		return;
	}

	DownUIWnd.AddFile(FData);
}
//체크박스로 한꺼번에 pmp 다운로드 
function pmpCheckAll(pVal)
{
	var obj = document.frm.cfilename;
	if (pVal)
	{						
		for (i=0; i<obj.length ; i++)
		{
			obj[i].checked = true;
		}
	}
	else
	{
		for (i=0; i<obj.length ; i++)
		{
			obj[i].checked = false;
		}
	}
}
//PMP 다운로드 팝업창을 띄운다.
function OpenDownUI(leccode, movieNo)
{
	url = "<c:url value='/lecture/pmpDownloadView.pop3?LECCODE=" + leccode + "&MOVIE_NO=" + movieNo+ "'/>";
	if( DownUIWnd==undefined || DownUIWnd.closed )
		DownUIWnd = window.open(url, 'PMPDown_UI_ADMIN', 'width=618,height=729,menubar=no,status=yes,resizable=no,scrollbars=no');
}

// PMP 다운로드 팝업창에 선택한 컨텐트 정보를 전달한다.
function AddFile(FData)
{
	//alert(FData);
	if( DownUIWnd==undefined || DownUIWnd.closed )
	{
		OpenDownUI();
		return;
	}
	//alert(DownUIWnd);
	DownUIWnd.AddFile(FData);
}	
</script>
</body>
</html>