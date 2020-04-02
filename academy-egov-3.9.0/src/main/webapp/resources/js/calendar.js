
/********************************************************************************************************
달력 Div 시작
********************************************************************************************************/
document.write("<div id=\"mypopup\" style=\"position: absolute; visibility: hidden; z-index: 1000; background-color: #99BDDF; layer-background-color: #99BDDF;\">	");
document.write("<iframe id=\"ifmCal\" frameBorder=\"0\" SCROLLING=\"no\" style=\"margin:0 0 0 0; width:170; height:100\"></iframe>									");
document.write("</div>																																				");
/********************************************************************************************************
달력 Div 종료
********************************************************************************************************/

var nn4 = (document.layers) ? true : false;
var ie  = (document.all) ? true : false;
var dom = (document.getElementById && !document.all) ? true : false;
var popups = new Array(); // keeps track of popup windows we create
var calHtml = '';

var ifmHeight = 0;
var ifmWidth = 0;

var setGB = "";

var functionExecFlag;

// language and preferences
wDay = new Array('일요일','월요일','화요일','수요일','목요일','금요일','토요일');
wDayAbbrev = new Array('<font color=#CC0000>Su</font>','Mo','Tu','We','Th','Fr','<font color=#0000CC>Sa</font>');
wMonth = new Array('01','02','03','04','05','06','07','08','09','10','11','12');
//wMonth = new Array('1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월');
wOrder = new Array('첫번째','두번째','세번째','네번째','마지막');
wStart = 0;

// 년월일 세팅
function calendar_search(obj, inputName, functionName) {
	functionExecFlag = false;
	this.setGB = "YMD";
	calPopup(obj, inputName, functionName);
}

//년월일 세팅
function calendar_search2(obj, inputName, functionName) {
	functionExecFlag = false;
	this.setGB = "YMD";
	calPopup2(obj, inputName, functionName);
}


// 년월 세팅
function calendar_search_month(obj, inputName, functionName) {
	functionExecFlag = false;
	this.setGB = "YM";
	calPopup(obj, inputName, functionName);
}

// 년 세팅
function calendar_search_year(obj, inputName, functionName) {
	functionExecFlag = false;
	this.setGB = "Y";
	calPopup(obj, inputName, functionName);
}

function calPopup(obj, inputName, functionName) {
	id = "mypopup";
	xOffset = 10;
	yOffset = 10;
	validationscript = "";

	attachListener(id);
	registerPopup(id);
	calHtml = makeCalHtml(id,null,null,null,inputName, functionName,validationscript);
	writeLayer(id, calHtml);
	setLayerPos(obj,id,xOffset,yOffset);
	showLayer(id);
	// 사이즈가 자동으로 조절되지 않아 어쩔수 없이 추가.
	setTimeout("reSize()",10);
	return true;
}

function calPopup2(obj, inputName, functionName) {
	id = "mypopup";
	xOffset = 10;
	yOffset = 10;
	validationscript = "";

	attachListener(id);
	registerPopup(id);
	calHtml = makeCalHtml(id,null,null,null,inputName, functionName,validationscript);
	writeLayer(id, calHtml);
	setLayerPos2(obj,id,xOffset,yOffset);
	showLayer(id);
	// 사이즈가 자동으로 조절되지 않아 어쩔수 없이 추가.
	setTimeout("reSize()",10);
	return true;
}

function attachListener(id) {

	var layer = new pathToLayer(id)
	if (layer.obj.listening == null) {
		document.oldMouseupEvent = document.onmouseup;
		if (document.oldMouseupEvent != null) {
			document.onmouseup = new Function("document.oldMouseupEvent(); hideLayersNotClicked();");
		} else {
			document.onmouseup = hideLayersNotClicked;
		}
		layer.obj.listening = true;
	}
}

function registerPopup(id) {
	// register this popup window with the popups array
	var layer = new pathToLayer(id);
	if (layer.obj.registered == null) {
		var index = popups.length ? popups.length : 0;
		popups[index] = layer;
		layer.obj.registered = 1;
	}
}

function makeCalHtml(id, calYear, calMonth, calDay, inputName, functionName, validationscript) {
	var arrDate;
	var calYear;
	var calMonth;
	var calDay;

	// 처음 팝업을 띄울 경우만 해당
	if(calYear == null || calYear == "") {
		if(eval(inputName).value == "") {
			var now = new Date();
			calYear = now.getYear() ;
			calMonth = now.getMonth()+1;
			calDay = now.getDate();
		} else {
			arrDate = removeChar(eval(inputName).value);
			calYear = arrDate.substring(0,4);
			calMonth = arrDate.substring(4,6) == "" ? "01" : arrDate.substring(4,6);
			calDay = arrDate.substring(6,8) == "" ? "01" : arrDate.substring(6,8);
		}
   }

	var daysInMonth = new Array(0,31,28,31,30,31,30,31,31,30,31,30,31);
	if ((calYear % 4 == 0 && calYear % 100 != 0) || calYear % 400 == 0) {
		daysInMonth[2] = 29;
	}

	var calDate = new Date(calYear,calMonth-1,calDay);

	//-----------------------------------------
	// check if the currently selected day is
	// more than what our target month has
	//-----------------------------------------
	if (calMonth < calDate.getMonth()+1) {
		calDay = calDay-calDate.getDate();
		calDate = new Date(calYear,calMonth-1,calDay);
	}

	var calNextYear  = calDate.getMonth() == 11 ? calDate.getFullYear()+1 : calDate.getFullYear();
	var calNextMonth = calDate.getMonth() == 11 ? 1 : calDate.getMonth()+2;
	var calLastYear  = calDate.getMonth() == 0 ? calDate.getFullYear()-1 : calDate.getFullYear();
	var calLastMonth = calDate.getMonth() == 0 ? 12 : calDate.getMonth();

	var todayDate = new Date();

	//---------------------------------------------------------
	// this relies on the javascript bug-feature of offsetting
	// values over 31 days properly. Negative day offsets do NOT
	// work with Netscape 4.x, and negative months do not work
	// in Safari. This works everywhere.
	//---------------------------------------------------------
	var calStartOfThisMonthDate = new Date(calYear,calMonth-1,1);
	var calOffsetToFirstDayOfLastMonth = calStartOfThisMonthDate.getDay() >= wStart ? calStartOfThisMonthDate.getDay()-wStart : 7-wStart-calStartOfThisMonthDate.getDay()
	if (calOffsetToFirstDayOfLastMonth > 0) {
		var calStartDate = new Date(calLastYear,calLastMonth-1,1); // we start in last month
	} else {
		var calStartDate = new Date(calYear,calMonth-1,1); // we start in this month
	}
	var calStartYear = calStartDate.getFullYear();
	var calStartMonth = calStartDate.getMonth();
	var calCurrentDay = calOffsetToFirstDayOfLastMonth ? daysInMonth[calStartMonth+1]-(calOffsetToFirstDayOfLastMonth-1) : 1;
	var isSeleted = '';
	var startYear = 2009;
	var lastYear = todayDate.getFullYear();
	
	var html = '';

	if (todayDate.getFullYear() < calDate.getFullYear()) {
		lastYear = calDate.getFullYear();
	} else if ( 2009 > calDate.getFullYear() ) {
		startYear = calDate.getFullYear();
	} 

	html += '<br />\n';
	html += '<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">\n';
	html += '  <tr>\n';
	html += '    <td width="10"><img src="/resource/admin/images/calender/cal_1.gif"></td>\n';
	html += '    <td background="/resource/admin/images/calender/cal_bgt.gif"></td>\n';
	html += '    <td width="16"><img src="/resource/admin/images/calender/cal_2.gif"></td>\n';
	html += '  </tr>\n';
	html += '  <tr>\n';
	html += '    <td background="/resource/admin/images/calender/cal_bgl.gif"></td>\n';
	html += '    <td class="cal_align">\n';
	html += '		<table border="0" cellspacing="0" cellpadding="0">\n';
	html += '		  <tr>\n';
	html += '			<td><img src="/resource/admin/images/calender/cal_day_l2.gif" width="12" height="10" onClick="javascript:parent.updateCal(\''+id+'\','+(eval(calYear)-1)+','+calMonth+','+calDay+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); return false;" style="cursor:hand;" /></td>\n';
	html += '			<td><img src="/resource/admin/images/calender/cal_day_l1.gif" width="8" height="10" onClick="javascript:parent.updateCal(\''+id+'\','+calLastYear+','+calLastMonth+','+calDay+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); return false;" style="cursor:hand;" /></td>\n';

	html += '			<td class="cal_txt9B">';
	html += '<select name="y" class="select1" onchange="javascript:parent.updateCal(\''+id+'\',y.options[y.selectedIndex].value,m.options[m.selectedIndex].value,'+calDay+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); return false;">';
	        for (var y=lastYear+90; y >= startYear-4; y--) {
	        	if(y == calDate.getFullYear()) { isSeleted = 'selected'; } else {isSeleted = '';} 
	        	html +=  '<option value=\''+ y + '\'' + isSeleted + '>' + y + '</option>';
	        }
	html += '</select>'
	html += '<select name="m" class="select1" onchange="javascript:parent.updateCal(\''+id+'\',y.options[y.selectedIndex].value,m.options[m.selectedIndex].value,'+calDay+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); return false;">';
	        for (var m=1; m <= 12; m++) {
	        	if(m == wMonth[calDate.getMonth()]) { isSeleted = 'selected'; } else {isSeleted = '';} 
	        	html +=  '<option value=\''+ m + '\'' + isSeleted + '>' + m + '</option>';
	        }
	html += '</select>'
	
	                  
	                                         
	                                         +'</td>\n';
	
	//	html += '			<td class="cal_txt9B">'+calDate.getFullYear()+''+wMonth[calDate.getMonth()]+'</td>\n';
	html += '			<td><img src="/resource/admin/images/calender/cal_day_r1.gif" width="8" height="10" onClick="javascript:parent.updateCal(\''+id+'\','+calNextYear+','+calNextMonth+','+calDay+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); return false;" style="cursor:hand;" /></td>\n';
	html += '			<td><img src="/resource/admin/images/calender/cal_day_r2.gif" width="12" height="10"  onClick="javascript:parent.updateCal(\''+id+'\','+(eval(calYear)+1)+','+calMonth+','+calDay+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); return false;" style="cursor:hand;" /></td>\n';
	html += '		  </tr>\n';
	html += '		</table>\n';


	html += '      <table width="100%" border="0" cellspacing="0" cellpadding="0">\n';
	html += '        <tr>\n';
	html += '          <td width="13"></td>\n';
	html += '          <td class="cal_bg"></td>\n';
	html += '          <td width="13"></td>\n';
	html += '        </tr>\n';
	html += '        <tr>\n';
	html += '          <td class="cal_bg"></td>\n';
	html += '          <td class="cal_bg">\n';
	
	html += '          	<table width="100%" border="0" cellspacing="0" cellpadding="0">\n';
	html += '            <tr>\n';
	html += '              <td class="cal_day"><img src="/resource/admin/images/calender/cal_day1.gif" width="12" height="5" /></td>\n';
	html += '              <td class="cal_day"><img src="/resource/admin/images/calender/cal_day2.gif" width="13" height="5" /></td>\n';
	html += '              <td class="cal_day"><img src="/resource/admin/images/calender/cal_day3.gif" width="12" height="5" /></td>\n';
	html += '              <td class="cal_day"><img src="/resource/admin/images/calender/cal_day4.gif" width="16" height="5" /></td>\n';
	html += '              <td class="cal_day"><img src="/resource/admin/images/calender/cal_day5.gif" width="12" height="5" /></td>\n';
	html += '              <td class="cal_day"><img src="/resource/admin/images/calender/cal_day6.gif" width="11" height="5" /></td>\n';
	html += '              <td class="cal_day"><img src="/resource/admin/images/calender/cal_day7.gif" width="12" height="5" /></td>\n';
	html += '            </tr>\n';

	for (var row=1; row <= 7; row++) {
		// check if we started a new month at the beginning of this row
		upcomingDate = new Date(calStartYear,calStartMonth,calCurrentDay);

		if (upcomingDate.getDate() <= 8 && row > 5) {
			continue; // skip this row
		}

		html += '<tr>\n';
		for (var col=0; col < 7; col++) {
			var tdColor = '"#FFFFFF"';
			if (row == 1) {
				//tdColor = '"#EEEEFF"';
				//html += '<td bgcolor='+tdColor+' align="center" class="cal_txt8">'+wDayAbbrev[(wStart+col)%7]+'</td>\n';
			} else {
				var hereDate = new Date(calStartYear,calStartMonth,calCurrentDay);
				var hereDay = hereDate.getDate();

				//현재달이 아닌경우
				if (hereDate.getMonth() != calDate.getMonth()) {
					html += '<td class="cal_txt8">&nbsp;</td>\n';	//<td class="cal_txt8">&nbsp;</td>
				} else {
					//오늘
					if (hereDate.getYear() == todayDate.getYear() && hereDate.getMonth() == todayDate.getMonth() && hereDate.getDate() == todayDate.getDate()) {
						if(col==0) {
							html += '<td class="cal_txt8" bgcolor="#FFDBDE" style="cursor:hand;" onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">'+hereDay+'</font></td>\n';
						} else if (col == 6) {
							html += '<td class="cal_txt8" bgcolor="#FFDBDE" style="cursor:hand;" onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">'+hereDay+'</font></td>\n';
						} else {
							html += '<td class="cal_txt8" bgcolor="#FFDBDE" style="cursor:hand;" onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">'+hereDay+'</font></td>\n'; // 오늘
						}
					} else {
						if(col==0) {
							html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"  ><span onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font color="red">'+hereDay+'</font></span></td>\n';
						} else if (col == 6) {
							html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;" ><span onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font color="blue">'+hereDay+'</font></span></td>\n';
						} else {
							html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;" ><span onClick="javascript:parent.changeFormDate('+hereDate.getFullYear()+','+(hereDate.getMonth()+1)+','+hereDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">'+hereDay+'</span></td>\n';
						}
					}
				}
				calCurrentDay++;
			}
		}
		html += '</tr>\n';
	}



	html += '			<tr>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '            </tr>\n';
	html += '          </table>\n';
	
	html += '			<table width="100%" border="0" cellspacing="0" cellpadding="0">\n';
	html += '              <tr>\n';
	html += '                <td width="30"><img src="/resource/admin/images/calender/btn_cal_today.gif" width="30" height="12" onClick="javascript:parent.updateCal(\''+id+'\','+todayDate.getFullYear()+','+(todayDate.getMonth()+1)+','+todayDate.getDate()+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); return false;" style="cursor:hand" /></td>\n';
	html += '                <td>&nbsp;</td>\n';
	html += '                <td width="30"><img src="/resource/admin/images/calender/btn_cal_close.gif" width="30" height="12" onClick="javascript:parent.hideLayer(\''+id+'\');" style="cursor:hand" /></td>\n';
	html += '              </tr>\n';
	html += '            </table>\n';
   
   
 	html += '  		  </td>\n';
	html += '          <td class="cal_bg"></td>\n';
	html += '        </tr>\n';
	html += '        <tr>\n';
	html += '          <td></td>\n';
	html += '          <td class="cal_bg"></td>\n';
	html += '          <td></td>\n';
	html += '        </tr>\n';
	html += '      </table>\n';
	html += '    </td>\n';
	html += '    <td background="/resource/admin/images/calender/cal_bgr.gif"></td>\n';
	html += '  </tr>\n';
	html += '  <tr>\n';
	html += '    <td><img src="/resource/admin/images/calender/cal_3.gif"></td>\n';
	html += '    <td background="/resource/admin/images/calender/cal_bgb.gif"></td>\n';
	html += '    <td><img src="/resource/admin/images/calender/cal_4.gif"></td>\n';
	html += '  </tr>\n';
	html += '</table> \n';
 
	return html;
}


function makeCalHtml2(id, calYear, calMonth, calDay, inputName, functionName, validationscript) {
	var arrDate;
	var calYear;
	var calMonth;
	var calDay;

	// 처음 팝업을 띄울 경우만 해당
	if(calYear == null || calYear == "") {
		if(eval(inputName).value == "") {
			var now = new Date();
			calYear = now.getYear() ;
			calMonth = now.getMonth()+1;
			calDay = now.getDate();
		} else {
			arrDate = removeChar(eval(inputName).value);
			calYear = arrDate.substring(0,4);
			calMonth = arrDate.substring(4,6) == "" ? "01" : arrDate.substring(4,6);
			calDay = arrDate.substring(6,8) == "" ? "01" : arrDate.substring(6,8);
		}
   }

	var daysInMonth = new Array(0,31,28,31,30,31,30,31,31,30,31,30,31);
	if ((calYear % 4 == 0 && calYear % 100 != 0) || calYear % 400 == 0) {
		daysInMonth[2] = 29;
	}

	var calDate = new Date(calYear,calMonth-1,calDay);

	//-----------------------------------------
	// check if the currently selected day is
	// more than what our target month has
	//-----------------------------------------
	if (calMonth < calDate.getMonth()+1) {
		calDay = calDay-calDate.getDate();
		calDate = new Date(calYear,calMonth-1,calDay);
	}

	var calNextYear  = calDate.getMonth() == 11 ? calDate.getFullYear()+1 : calDate.getFullYear();
	var calNextMonth = calDate.getMonth() == 11 ? 1 : calDate.getMonth()+2;
	var calLastYear  = calDate.getMonth() == 0 ? calDate.getFullYear()-1 : calDate.getFullYear();
	var calLastMonth = calDate.getMonth() == 0 ? 12 : calDate.getMonth();

	var todayDate = new Date();

	//---------------------------------------------------------
	// this relies on the javascript bug-feature of offsetting
	// values over 31 days properly. Negative day offsets do NOT
	// work with Netscape 4.x, and negative months do not work
	// in Safari. This works everywhere.
	//---------------------------------------------------------
	var calStartOfThisMonthDate = new Date(calYear,calMonth-1,1);
	var calOffsetToFirstDayOfLastMonth = calStartOfThisMonthDate.getDay() >= wStart ? calStartOfThisMonthDate.getDay()-wStart : 7-wStart-calStartOfThisMonthDate.getDay()
	if (calOffsetToFirstDayOfLastMonth > 0) {
		var calStartDate = new Date(calLastYear,calLastMonth-1,1); // we start in last month
	} else {
		var calStartDate = new Date(calYear,calMonth-1,1); // we start in this month
	}
	var calStartYear = calStartDate.getFullYear();
	var calStartMonth = calStartDate.getMonth();
	var calCurrentDay = calOffsetToFirstDayOfLastMonth ? daysInMonth[calStartMonth+1]-(calOffsetToFirstDayOfLastMonth-1) : 1;

	var isSeleted = '';
	var startYear = 2000;
	var lastYear = todayDate.getFullYear();
	
	var html = '';

	if (todayDate.getFullYear() < calDate.getFullYear()) {
		lastYear = calDate.getFullYear();
	} else if ( 2009 > calDate.getFullYear() ) {
		startYear = calDate.getFullYear();
	} 
	

	html += '<br />\n';
	html += '<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">\n';
	html += '  <tr>\n';
	html += '    <td width="10"><img src="/resource/admin/images/calender/cal_1.gif"></td>\n';
	html += '    <td background="/resource/admin/images/calender/cal_bgt.gif"></td>\n';
	html += '    <td width="16"><img src="/resource/admin/images/calender/cal_2.gif"></td>\n';
	html += '  </tr>\n';
	html += '  <tr>\n';
	html += '    <td background="/resource/admin/images/calender/cal_bgl.gif"></td>\n';
	html += '    <td class="cal_align">\n';
	html += '      <table width="100%" border="0" cellspacing="0" cellpadding="0">\n';
	html += '        <tr>\n';
	html += '          <td width="13"></td>\n';
	html += '          <td class="cal_bg"></td>\n';
	html += '          <td width="13"></td>\n';
	html += '        </tr>\n';
	html += '        <tr>\n';
	html += '          <td class="cal_bg"></td>\n';
	html += '          <td class="cal_bg">\n';
	
	html += '          	<table width="100%" border="0" cellspacing="0" cellpadding="0">\n';
	html += '            <tr>\n';
	html += '            </tr>\n';

	html += '<tr>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+1+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+1+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">1</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+2+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+2+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">2</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+3+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+3+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">3</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+4+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+4+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">4</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+5+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+5+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">5</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+6+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+6+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">6</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+7+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+7+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">7</font></td>\n';
	html += '</tr>\n';
	html += '<tr>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+8+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+8+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">8</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+9+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+9+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">9</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+10+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+10+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">10</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+11+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+11+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">11</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+12+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+12+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">12</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+13+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+13+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">13</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+14+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+14+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">14</font></td>\n';
	html += '</tr>\n';
	html += '<tr>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+15+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+15+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">15</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+16+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+16+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">16</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+17+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+17+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">17</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+18+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+18+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">18</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+19+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+19+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">19</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+20+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+20+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">20</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+21+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+21+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">21</font></td>\n';
	html += '</tr>\n';
	html += '<tr>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+22+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+22+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">22</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+23+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+23+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">23</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+24+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+24+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">24</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+25+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+25+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">25</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+26+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+26+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">26</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+27+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+27+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">27</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+28+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+28+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">28</font></td>\n';
	html += '</tr>\n';
	html += '<tr>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+29+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+29+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">29</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+30+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+30+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">30</font></td>\n';
	html += '<td class="cal_txt8" style="cursor:hand;" onClick="javascript:parent.changeFormDate2('+2010+','+8+','+31+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;"><font onClick="javascript:parent.changeFormDate2('+2010+','+8+','+31+',\''+inputName+'\',\''+functionName+'\',\''+validationscript+'\'); parent.hideLayer(\''+id+'\'); return false;">31</font></td>\n';
	html += '</tr>\n';

	html += '			<tr>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '              <td>&nbsp;</td>\n';
	html += '            </tr>\n';
	html += '          </table>\n';
	
	html += '			<table width="100%" border="0" cellspacing="0" cellpadding="0">\n';
	html += '              <tr>\n';
	html += '                <td>&nbsp;</td>\n';
	html += '                <td width="30"><img src="/resource/admin/images/calender/btn_cal_close.gif" width="30" height="12" onClick="javascript:parent.hideLayer(\''+id+'\');" style="cursor:hand" /></td>\n';
	html += '              </tr>\n';
	html += '            </table>\n';
   
   
 	html += '  		  </td>\n';
	html += '          <td class="cal_bg"></td>\n';
	html += '        </tr>\n';
	html += '        <tr>\n';
	html += '          <td></td>\n';
	html += '          <td class="cal_bg"></td>\n';
	html += '          <td></td>\n';
	html += '        </tr>\n';
	html += '      </table>\n';
	html += '    </td>\n';
	html += '    <td background="/resource/admin/images/calender/cal_bgr.gif"></td>\n';
	html += '  </tr>\n';
	html += '  <tr>\n';
	html += '    <td><img src="/resource/admin/images/calender/cal_3.gif"></td>\n';
	html += '    <td background="/resource/admin/images/calender/cal_bgb.gif"></td>\n';
	html += '    <td><img src="/resource/admin/images/calender/cal_4.gif"></td>\n';
	html += '  </tr>\n';
	html += '</table> \n';
 
	return html;
}

function updateCal(id, calYear, calMonth, calDay, inputName, functionName, validationscript) {
	calHtml = makeCalHtml(id,calYear,calMonth,calDay,inputName, functionName,validationscript);
	writeLayer(id,calHtml);
}
function updateSelect(id, calYear, calMonth, calDay, inputName, functionName, validationscript) {
	var calYear = "";
	var calMonth = "";
	var calDay = "";
		
	calHtml = makeCalHtml(id,calYear,calMonth,calDay,inputName, functionName,validationscript);
	writeLayer(id,calHtml);
}

function reSize() {
	var ifm = document.getElementById("ifmCal");
	ifmHeight = ifmCal.document.body.scrollHeight + (ifmCal.document.body.offsetHeight - ifmCal.document.body.clientHeight);
	ifm.style.height = ifmHeight;
	ifm.style.width = 200;
}

function writeLayer(id, html) {
	var layer = new pathToLayer(id);
	if (nn4) {
		layer.obj.document.open();
		layer.obj.document.write(html);
		layer.obj.document.close();
	} else {
		if(typeof(ifmCal.spCal)!="object") {
			ifmCal.document.write('<html><head><link rel="stylesheet" type="text/css" href="/css/style.css"></head><body valign="top" topmargin="0"><span id="spCal" valign="top"></span></body></html>\n');
		}
		ifmCal.spCal.innerHTML = '';
		ifmCal.spCal.innerHTML = html;

		reSize();
	}
}

function setLayerPos(obj, id, xOffset, yOffset) {
	var newX = 0;
	var newY = 0;
	var maxX = document.body.clientWidth - 200;
	//var maxY = document.body.clientHeight - 200;
	var maxY = 890;

	if (obj.offsetParent) {
		// if called from href="setLayerPos(this,'example')" then obj will
		// have no offsetParent properties. Use onClick= instead.
		while (obj.offsetParent) {
			newX += obj.offsetLeft;
			newY += obj.offsetTop;
			obj = obj.offsetParent;
		}
	} else if (obj.x) {
		// nn4 - only works with "a" tags
		newX += obj.x;
		newY += obj.y;
	}
	
	// apply the offsets
	newX += xOffset;
	//newY += yOffset;

	// max를 넘어가면
	if(newX > maxX) newX = maxX - 20;
	if(newY > maxY) newY = maxY - 20;

	// apply the new positions to our layer
	//var layer = new pathToLayer(id);
	var layer = document.getElementById( id );
	//newY=0;
	newY = newY -30;
	if (nn4) {
		layer.style.left = newX;
		layer.style.top  = newY;
	} else {
		// the px avoids errors with doctype strict modes
		layer.style.left = newX + 'px';
		layer.style.top  = newY + 'px';
	}
}


function setLayerPos2(obj, id, xOffset, yOffset) {
	var newX = 0;
	var newY = 0;
	var maxX = document.body.clientWidth - 200;
	//var maxY = document.body.clientHeight - 200;
	var maxY = 890;

	alert(document.body.clientHeight);
	if (obj.offsetParent) {
		// if called from href="setLayerPos(this,'example')" then obj will
		// have no offsetParent properties. Use onClick= instead.
		while (obj.offsetParent) {
			newX += obj.offsetLeft;
			newY += obj.offsetTop;
			obj = obj.offsetParent;
		}
	} else if (obj.x) {
		// nn4 - only works with "a" tags
		newX += obj.x;
		newY += obj.y;
	}
	
	// apply the offsets
	newX += xOffset;
	//newY += yOffset;

	// max를 넘어가면
	if(newX > maxX) newX = maxX - 20;
	if(newY > maxY) newY = maxY - 20;

	// apply the new positions to our layer
	//var layer = new pathToLayer(id);
	var layer = document.getElementById( id );
	//newY=0;
	newY = newY -30;
	if (nn4) {
		layer.style.left = newX;
		layer.style.top  = newY;
	} else {
		// the px avoids errors with doctype strict modes
		layer.style.left = newX + 'px';
		layer.style.top  = newY + 'px';
	}
}

function hideLayersNotClicked(e) {
	if (!e) var e = window.event;
	e.cancelBubble = true;
	if (e.stopPropagation) e.stopPropagation();
	if (e.target) {
		var clicked = e.target;
	} else if (e.srcElement) {
		var clicked = e.srcElement;
	}

	// go through each popup window,
	// checking if it has been clicked
	for (var i=0; i < popups.length; i++) {
		if (nn4) {
			if ((popups[i].style.left < e.pageX) &&
				(popups[i].style.left+popups[i].style.clip.width > e.pageX) &&
				(popups[i].style.top < e.pageY) &&
				(popups[i].style.top+popups[i].style.clip.height > e.pageY)) {
				return true;
			} else {
				hideLayer(popups[i].obj.id);
				return true;
			}
		} else if (ie) {
			while (clicked.parentElement != null) {
				if (popups[i].obj.id == clicked.id) {
					return true;
				}
				clicked = clicked.parentElement;
			}
			hideLayer(popups[i].obj.id);
			return true;
		} else if (dom) {
			while (clicked.parentNode != null) {
				if (popups[i].obj.id == clicked.id) {
					return true;
				}
				clicked = clicked.parentNode;
			}
			hideLayer(popups[i].obj.id);
			return true;
		}
		return true;
	}
	return true;
}

function pathToLayer(id) {
	if (nn4) {
		this.obj = document.layers[id];
		this.style = document.layers[id];
	} else if (ie) {
		this.obj = document.all[id];
		this.style = document.all[id].style;
	} else {
		this.obj = document.getElementById(id);
		this.style = document.getElementById(id).style;
	}
}

function showLayer(id) {
	var layer = new pathToLayer(id)
	layer.style.visibility = "visible";
}
 
function hideLayer(id) {
	var layer = new pathToLayer(id);
	layer.style.visibility = "hidden";
}

function changeFormDate(changeYear,changeMonth,changeDay,inputName,functionName,validationscript) {

	if(changeMonth < 10) {
		changeMonth = '0' + changeMonth;
	}
	if(changeDay < 10) {
		changeDay = '0' + changeDay;
	}

	if(this.setGB == "YMD") {
		eval(inputName).value = changeYear + "-" + changeMonth + "-" + changeDay;
	} else if(this.setGB == "YM") {
		eval(inputName).value = changeYear + "-" + changeMonth;
	} else if(this.setGB == "Y") {
		eval(inputName).value = changeYear;
	}

	if (validationscript) {
		eval(validationscript+"('"+inputName+"')"); // to update the other selection boxes in the form
	}
	if(!functionExecFlag) {
		try {
			eval(functionName);
			functionExecFlag = true;
		} catch(e) {}
	}	
}

function changeFormDate2(changeYear,changeMonth,changeDay,inputName,functionName,validationscript) {

	if(changeMonth < 10) {
		changeMonth = '0' + changeMonth;
	}
	if(changeDay < 10) {
		changeDay = '0' + changeDay;
	}

	if(this.setGB == "YMD") {
		eval(inputName).value = changeDay;
	} else if(this.setGB == "YM") {
		eval(inputName).value = changeYear + "-" + changeMonth;
	} else if(this.setGB == "Y") {
		eval(inputName).value = changeYear;
	}

	if (validationscript) {
		eval(validationscript+"('"+inputName+"')"); // to update the other selection boxes in the form
	}
	if(!functionExecFlag) {
		try {
			eval(functionName);
			functionExecFlag = true;
		} catch(e) {}
	}	
}

//숫자 이외의 값을 제거한다.
function removeChar(strValue){
	var strReturnValue = "";

	for (i=0; i<strValue.length; i++) {
		if ((strValue.charAt(i) >= '0') && (strValue.charAt(i) <= '9'))
			strReturnValue += strValue.charAt(i);
	}

	return strReturnValue;
}
