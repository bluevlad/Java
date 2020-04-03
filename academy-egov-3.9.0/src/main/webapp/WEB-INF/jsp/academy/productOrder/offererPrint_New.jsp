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

var L_TITLE = "${list[0].SUBJECT_TITLE}";
	L_TITLE = L_TITLE.substring(0,14);

var PRICE = "${list[0].PRICE}";
PRICE = SetComma(PRICE);
	
var now = new Date();
var year= now.getFullYear();
var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
var hour = now.getHours();
var min = now.getMinutes();
var date = year+"-"+mon+"-"+day+" "+hour+":"+min;

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
			ctkprint_bar.prt_text_L4 = "${searchMap.USER_NM}(${searchMap.ORDERNO})" + ";굴림;12;true;left";
			ctkprint_bar.prt_text_R8 = "${list[0].SUBJECT_TITLE2}" + ";굴림;12;true;left";
			ctkprint_bar.prt_text_L25 = date + ";굴림;10;false;left";
		*/

		ctkprint_bar.prt_text_L4 = "수강증" + ";궁서;18;true;left";
		ctkprint_bar.prt_text_L8 = L_TITLE + "${list[0].SUB_TITLE}" + ";굴림체;18;true;left";
		ctkprint_bar.prt_text_L12 = "${list[0].MIN_DT} ~ ${list[0].MAX_DT}" + ";굴림체;18;true;left";
		ctkprint_bar.prt_text_L16 = "${searchMap.USER_NM}" + ";굴림체;18;true;left";
		ctkprint_bar.prt_text_L19 = "(${searchMap.USER_ID})" + ";굴림;10;false;left";
		ctkprint_bar.prt_text_L22 = PRICE+"원 (${list[0].PAYNAME})" + ";굴림체;10;false;left";
		ctkprint_bar.prt_text_L24 = "(${searchMap.ORDERNO})" + ";굴림;10;false;left";
		
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
				window.self.close();
			},
			error: function(d, json){
				alert("error: "+d.status);
			}
		});
	}

	//숫자 3자리 단위 , 표시하기
    function SetComma(frm) {
        var rtn = "";
        var val = "";
        var j = 0;
        x = frm.length;

        for(i=x; i>0; i--) {
        if(frm.substring(i,i-1) != ",") {
        val = frm.substring(i,i-1)+val;
        }
        }
        x = val.length;
        for(i=x; i>0; i--) {
        if(j%3 == 0 && j!=0) {
        rtn = val.substring(i,i-1)+","+rtn;
        }else {
        rtn = val.substring(i,i-1)+rtn;
        }
        j++;
        }
        return rtn;
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