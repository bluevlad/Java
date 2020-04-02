<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet" type="text/css"  />
<link href="<c:url value="/resources/css/base.css" />" rel="stylesheet" type="text/css"  />
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" type="text/css"  />
<link href="<c:url value="/resources/libs/jquery-ui/css/redmond/jquery-ui-1.8.18.custom.css" />" rel="stylesheet" type="text/css"  />
<link href="<c:url value="/resources/libs/jquery-timepicker/jquery.ui.timepicker.css" />" rel="stylesheet" type="text/css"  />
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery-ui/jquery-ui-1.8.18.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.tmpl.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.form.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.cookie.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.hoverIntent.minified.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.dcverticalmegamenu.1.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.dcjqaccordion.2.7.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery/jquery.validator.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/jquery.ui.timepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.position.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.widget.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/libs/jquery-timepicker/include/jquery.ui.tabs.min.js" />"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
var ORDERNO = "${searchMap.ORDERNO}";
var USER_ID = "${searchMap.USER_ID}";
var PRINT_STS = "${searchMap.PRINT_STS}";
var CNT = "${searchMap.CNT}";

var orderstatuscode = "${searchMap.orderstatuscode}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

var now = new Date();
var year= now.getFullYear();
var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
var date = year+"-"+mon+"-"+day;

	function fn_barProc() 
	{
		/********************             삽입 될 데이터 목록          *******************/
		/*	   이 부분은 자바스크립트 함수를 호출 할때 parameter로 받아서 처리 하셔도 됩니다     */
		/*	 예 함수 ctkprint_barProc(value1, value2) , 내부변수1 = value1 내부변수2 = value2 */
		/***************************************************************/		
		//티켓의 왼쪽 부분 입력 시작
		/*
			ctkprint_bar.prt_text_L숫자 이 객체에 L숫자 부분에서 숫자는 라인번호임 (1~25), L은 Left부분 의미, R은 Right
			입력되는 데이터 구성 = "내용;글씨체;글씨크기;글씨굵기(true/false);정렬(left/right)";
		*/
		 /* ctkprint_bar.prt_text_L3 = "${view[0].USRNM}(${view[0].OFFERERID})" + ";굴림;10;true;left";
		ctkprint_bar.prt_text_R8 = "${view[0].MOCKNAME}" + " (${view[0].CLASSSERIESCODENM})" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R12 = "전국모의고사(학원시행)" + ";굴림;10;false;left";
		ctkprint_bar.prt_text_L25 = "${fn:substring(view[0].PRINTTIME, 0, 10)}" + ";굴림;10;false;left"; 
		
		
		ctkprint_bar.prt_text_L4 = "${searchMap.USER_NM}(${searchMap.ORDERNO})" + ";굴림;12;true;left";
		
		
		ctkprint_bar.prt_text_R8 = "${list[0].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R11 = "${list[1].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R14 = "${list[2].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R17 = "${list[3].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R20 = "${list[4].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R23 = "${list[5].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R26 = "${list[6].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R29 = "${list[7].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		ctkprint_bar.prt_text_R32 = "${list[8].SUBJECT_TITLE2}" + ";굴림;12;true;left";
		
		ctkprint_bar.prt_text_L25 = date + ";굴림;10;false;left";
		*/

		ctkprint_bar.prt_text_L4 = "${searchMap.USER_NM}(${searchMap.ORDERNO})" + ";굴림;10;true;left";
		
		
		ctkprint_bar.prt_text_L8 = "               ${list[0].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		if("${list[1].PTYPE}" == "D") {
		ctkprint_bar.prt_text_L11 = "               ${list[1].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		}
		if("${list[2].PTYPE}" == "D") {
		ctkprint_bar.prt_text_L14 = "               ${list[2].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		}
		if("${list[3].PTYPE}" == "D") {
		ctkprint_bar.prt_text_L17 = "               ${list[3].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		}
		if("${list[4].PTYPE}" == "D") {
		ctkprint_bar.prt_text_L20 = "               ${list[4].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		}
		if("${list[5].PTYPE}" == "D") {
		ctkprint_bar.prt_text_L23 = "               ${list[5].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		}
		if("${list[6].PTYPE}" == "D") {
		ctkprint_bar.prt_text_L26 = "               ${list[6].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		}
		if("${list[7].PTYPE}" == "D") {
		ctkprint_bar.prt_text_L29 = "               ${list[7].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		}
		if("${list[8].PTYPE}" == "D") {
		ctkprint_bar.prt_text_L32 = "               ${list[8].SUBJECT_TITLE2}" + ";굴림;8;false;left";
		}
		
		ctkprint_bar.prt_text_L28 = date + ";굴림;8;false;left";
		
		//인쇄 관련 부분 시작
		ctkprint_bar.CT_DOC_Prt = 1;	//인쇄 요청 코드
		ctkprint_bar.CT_DOC_Prt2 = 1; 	//미리보기 코드(일반 프린터 인쇄)
		
		var _url = '<c:url value="/productOrder/updatePrint.do"/>?ORDERNO=' + ORDERNO;
		
		$.ajax({
			type : "GET",
			url : _url,
			dataType : "json",
			async : false,
			success : function(json){
				//alert("duplication.length:"+json.duplication.length);
				if(json && json.length > 0) {
					$(json).each(function(index, mouigosa){
						
					});	
				}
				
				alert("수강증 출력이 완료되었습니다.");
				
				if(PRINT_STS == "1") {
					window.opener.searchFrm.orderstatuscode.value=orderstatuscode;
					 window.opener.searchFrm.searchkey.value=searchkey;
					 window.opener.searchFrm.searchtype.value=searchtype;
					 window.opener.searchFrm.paycode.value=paycode;
					 window.opener.searchFrm.sdate.value=sdate;
					 window.opener.searchFrm.edate.value=edate;
					 window.opener.searchFrm.currentPage.value=currentPage;
					 window.opener.searchFrm.pageRow.value=pageRow;
					 window.opener.searchFrm.TOP_MENU_ID.value=TOP_MENU_ID;
					 window.opener.searchFrm.MENUTYPE.value=MENUTYPE;
					 window.opener.searchFrm.L_MENU_NM.value=L_MENU_NM;
					 
					 window.opener.goList(currentPage);
				}else{
					
					window.opener.searchFrm.orderstatuscode.value=orderstatuscode;
					 window.opener.searchFrm.searchkey.value=searchkey;
					 window.opener.searchFrm.searchtype.value=searchtype;
					 window.opener.searchFrm.paycode.value=paycode;
					 window.opener.searchFrm.sdate.value=sdate;
					 window.opener.searchFrm.edate.value=edate;
					 window.opener.searchFrm.currentPage.value=currentPage;
					 window.opener.searchFrm.pageRow.value=pageRow;
					 window.opener.searchFrm.TOP_MENU_ID.value=TOP_MENU_ID;
					 window.opener.searchFrm.MENUTYPE.value=MENUTYPE;
					 window.opener.searchFrm.L_MENU_NM.value=L_MENU_NM;
					 
					 window.opener.view(ORDERNO,USER_ID);
				}
				
				window.self.close();
			},
			error: function(d, json){
				alert("error: "+d.status);
			}
		});
	}
-->
</SCRIPT>


</head>
<body>
	
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
</body>
</html>