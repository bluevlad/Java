var fixedX = -1; // 레이어 X축 위치 (-1 : 버튼에 바로 아래에 표시)
var fixedY = -1; // 레이어 Y축 위치 (-1 : 버튼에 바로 아래에 표시)
var startAt = 0; // 일요일 표시 부분 / 0 : 일요일(일월화...) / 1 : 월요일(...금토일)
var showWeekNumber = 0; // 주(week)보임 유무 - 0 : 감춤 / 1 : 보임
var showToday = 1; // 오늘 날짜 표시 유무 - 0 : 감춤 / 1 : 보임
var imgDir = CPATH+'/js/calendar/'; // 이미지 디렉토리 - ./ : 현재 디렉토리

var gotoString = "오늘 날짜로 표시" // 오늘 날짜 링크에 마우스 올렸을시 상태바와 title메세지 / 원문 : Go To Current Month
var todayString = "오늘 날짜 : " // 오늘 날짜 메세지 / 원문 : Today is
var weekString = "Week" // 좌측 주(week)표시 / 원문 : Wk
var scrollLeftMessage = "이전 달로 이동" // 이전 달(month)로 이동하는 버튼에 마우스 올리면 상태바에 나타는 메세지
// 원문 : Click to scroll to previous month. Hold mouse button to scroll automatically.
var scrollRightMessage = "다음 달로 이동" // 다음 달(month)로 이동하는 버튼에 마우스 올리면 상태바에 나타는 메세지
// 원문 : Click to scroll to next month. Hold mouse button to scroll automatically.
var selectMonthMessage = "달을 선택합니다." // 달(month)을 선택하는 부분에 마우스 올리면 상태바에 나타나는 메세지
// 원문 : Click to select a month.
var selectYearMessage = "년을 선택합니다." // 년(year)을 선택하는 부분에 마우스 올리면 상태바에 나타나는 메세지
// 원문 : Click to select a year.
var selectDateMessage = "날짜를 선택합니다. : [date]" // 날짜에 마우스 올렸을시 상대바에 나타나는 메세지 / [data] : 날짜를 표시
// 원문 : Select [date] as date.

// 각 변수 선언
var crossobj, crossMonthObj, crossYearObj, monthSelected, yearSelected, dateSelected, omonthSelected, oyearSelected, odateSelected, monthConstructed, yearConstructed, intervalID1, intervalID2, timeoutID1, timeoutID2, ctlToPlaceValue, ctlNow, dateFormat, nStartingYear

var bPageLoaded = false;
var ie = document.all;
var dom = document.getElementById;
var bShow = false;
var ns4 = document.layers;

var today = new	Date(); // 날짜 변수 선언
var dateNow = today.getDate(); // 로컬 컴퓨터의 일(day)을 구함
var monthNow = today.getMonth(); // 로컬 컴퓨터의 월(month)을 구함
var yearNow = today.getYear(); // 로컬 컴퓨터의 년(year)을 구함
var cal_imgsrc = new Array("drop1.gif","drop2.gif","left1.gif","left2.gif","right1.gif","right2.gif"); // 이미지 배열
var cal_img	= new Array(); // 배열 선언

// 월(month)을 표시하는 m을 3개(mmm) 적었을시
//var monthName = new Array("January","February","March","April","May","June","July","August","September","October","November","December");
var monthName = new Array("1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월");
// 월(month)을 표시하는 m을 4개(mmmm) 적었을시
var monthName2 = new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");

// 달력 구성 - 위 "일요일 표시 부분"과 관련
if (startAt==0) {
	// 일 월 화 수 목 금 토
	//dayName = new Array("Sun","Mon","Tue","Wed","Thu","Fri","Sat");
	dayName = new Array("일","월","화","수","목","금","토");
} else {
	// 월 화 수 목 금 토 일
	//dayName = new Array("Mon","Tue","Wed","Thu","Fri","Sat","Sun");
	dayName = new Array("월","화","수","목","금","토", "일");
}

function hideElement(elmID, overDiv) {
	if(ie) {
		for(i = 0; i < document.all.tags(elmID).length; i++) {
			obj = document.all.tags(elmID)[i];

			if(!obj || !obj.offsetParent) {
				continue;
			}

			objLeft = obj.offsetLeft;
			objTop = obj.offsetTop;
			objParent = obj.offsetParent;

			while(objParent.tagName.toUpperCase() != "BODY") {
				objLeft += objParent.offsetLeft;
				objTop += objParent.offsetTop;
				objParent = objParent.offsetParent;
			}

			objHeight = obj.offsetHeight;
			objWidth = obj.offsetWidth;

			if((overDiv.offsetLeft + overDiv.offsetWidth) <= objLeft);
			else if((overDiv.offsetTop + overDiv.offsetHeight) <= objTop);
			else if(overDiv.offsetTop >= (objTop + objHeight));
			else if(overDiv.offsetLeft >= (objLeft + objWidth));
			else {
				obj.style.visibility = "hidden";
			}
		}
	}
}

function showElement(elmID) {
	if(ie) {
		for(i = 0; i < document.all.tags(elmID).length; i++) {
			obj = document.all.tags( elmID )[i];

			if(!obj || !obj.offsetParent) {
				continue;
			}

			obj.style.visibility = "";
		}
	}
}

function HolidayRec(d, m, y, desc) {
	this.d = d;
	this.m = m;
	this.y = y;
	this.desc = desc;
}

var HolidaysCounter = 0;
var Holidays = new Array();

function addHoliday(d, m, y, desc) {
	Holidays[HolidaysCounter++] = new HolidayRec ( d, m, y, desc );
}

if(dom) {
	for(i=0; i<cal_imgsrc.length; i++) {
		cal_img[i] = new Image;
		cal_img[i].src = imgDir + cal_imgsrc[i];
	}

	document.write ("<div onclick='bShow=true' id='calendar'	style='z-index:+999;position:absolute;visibility:hidden;'><table	width="+((showWeekNumber==1)?250:220)+" style='font-family:arial;font-size:11px;border-width:1;border-style:solid;border-color:#a0a0a0;font-family:arial; font-size:11px}' bgcolor='#ffffff'><tr bgcolor='#0000aa'><td><table width='"+((showWeekNumber==1)?248:218)+"'><tr><td style='padding:2px;font-family:arial; font-size:11px;'><font color='#ffffff'><B><span id='caption'></span></B></font></td><td align=right><a href='javascript:hideCalendar()'><IMG SRC='"+imgDir+"close.gif' WIDTH='15' HEIGHT='13' BORDER='0' ALT='Close the Calendar'></a></td></tr></table></td></tr><tr><td style='padding:5px' bgcolor=#ffffff><span id='content'></span></td></tr>");

	if(showToday==1) {
		document.write ("<tr bgcolor=#f0f0f0><td style='padding:5px' align=center><span id='lblToday'></span></td></tr>");
	}

	document.write ("</table></div><div id='selectMonth' style='z-index:+999;position:absolute;visibility:hidden;'></div><div id='selectYear' style='z-index:+999;position:absolute;visibility:hidden;'></div>");
}

var styleAnchor = "text-decoration:none;color:black;";
var styleLightBorder = "border-style:solid;border-width:1px;border-color:#a0a0a0;";

function swapImage(srcImg, destImg) {
	if(ie) {
		document.getElementById(srcImg).setAttribute("src",imgDir + destImg);
	}
}

function calendarinit() {
	if(!ns4) {
		if(!ie) {
			yearNow += 1900;
		}

		crossobj = (dom)?document.getElementById("calendar").style : ie? document.all.calendar : document.calendar;
		hideCalendar();
		crossMonthObj = (dom)?document.getElementById("selectMonth").style : ie? document.all.selectMonth : document.selectMonth;
		crossYearObj = (dom)?document.getElementById("selectYear").style : ie? document.all.selectYear : document.selectYear;
		monthConstructed = false;
		yearConstructed = false;

		if(showToday==1) {
			document.getElementById("lblToday").innerHTML =	todayString+" <a onmousemove='window.status=\""+gotoString+"\"' onmouseout='window.status=\"\"' title='"+gotoString+"' style='"+styleAnchor+"' href='javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();'>"+yearNow+"년 " +monthName[monthNow].substring(0,3)+" "+dateNow+"일 "+dayName[(today.getDay()-startAt==-1)?6:(today.getDay()-startAt)]+"요일</a>";
		}

		sHTML1 = "<span id='spanLeft' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer' onmouseover='swapImage(\"changeLeft\",\"left2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+scrollLeftMessage+"\"' onclick='javascript:decMonth()' onmouseout='clearInterval(intervalID1);swapImage(\"changeLeft\",\"left1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartDecMonth()\",500)'	onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'>&nbsp<IMG id='changeLeft' SRC='"+imgDir+"left1.gif' width=10 height=11 BORDER=0>&nbsp</span>&nbsp;";
		sHTML1 += "<span id='spanRight' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer'	onmouseover='swapImage(\"changeRight\",\"right2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+scrollRightMessage+"\"' onmouseout='clearInterval(intervalID1);swapImage(\"changeRight\",\"right1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onclick='incMonth()' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartIncMonth()\",500)'	onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'>&nbsp<IMG id='changeRight' SRC='"+imgDir+"right1.gif'	width=10 height=11 BORDER=0>&nbsp</span>&nbsp";
		sHTML1 += "<span id='spanMonth' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer'	onmouseover='swapImage(\"changeMonth\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+selectMonthMessage+"\"' onmouseout='swapImage(\"changeMonth\",\"drop1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onclick='popUpMonth()'></span>&nbsp;";
		sHTML1 += "<span id='spanYear' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer' onmouseover='swapImage(\"changeYear\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+selectYearMessage+"\"'	onmouseout='swapImage(\"changeYear\",\"drop1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"'	onclick='popUpYear()'></span>&nbsp;";

		document.getElementById("caption").innerHTML = sHTML1;
		bPageLoaded = true;
	}
}

function hideCalendar()	{
	crossobj.visibility = "hidden";
	if(crossMonthObj != null) {
		crossMonthObj.visibility="hidden";
	}

	if(crossYearObj != null) {
		crossYearObj.visibility="hidden";
	}

	showElement('SELECT');
	showElement('APPLET');
}

function padZero(num) {
	return (num < 10)? '0' + num : num;
}

function constructDate(d,m,y) {
	sTmp = dateFormat
	sTmp = sTmp.replace("dd","<e>");
	sTmp = sTmp.replace("d","<d>");
	sTmp = sTmp.replace("<e>",padZero(d));
	sTmp = sTmp.replace("<d>",d);
	sTmp = sTmp.replace("mmmm","<p>");
	sTmp = sTmp.replace("mmm","<o>");
	sTmp = sTmp.replace("mm","<n>");
	sTmp = sTmp.replace("m","<m>");
	sTmp = sTmp.replace("<m>",m+1);
	sTmp = sTmp.replace("<n>",padZero(m+1));
	sTmp = sTmp.replace("<o>",monthName[m]);
	sTmp = sTmp.replace("<p>",monthName2[m]);
	sTmp = sTmp.replace("yyyy",y);

	return sTmp.replace("yy",padZero(y%100));
}

function closeCalendar() {
	var sTmp;
	hideCalendar();
	ctlToPlaceValue.value =	constructDate(dateSelected,monthSelected,yearSelected);
}

function StartDecMonth() {
	intervalID1 = setInterval("decMonth()",80);
}

function StartIncMonth() {
	intervalID1 = setInterval("incMonth()",80);
}

function incMonth() {
	monthSelected++;

	if (monthSelected>11) {
		monthSelected=0;
		yearSelected++;
	}
	constructCalendar();
}

function decMonth() {
	monthSelected--;

	if (monthSelected<0) {
		monthSelected=11;
		yearSelected--;
	}
	constructCalendar();
}

function constructMonth() {
	popDownYear();

	if (!monthConstructed) {
		sHTML =	"";

		for(i=0; i<12; i++) {
			sName =	monthName[i];

			if (i==monthSelected){
				sName =	"<B>" +	sName +	"</B>";
			}
			sHTML += "<tr><td id='m" + i + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='monthConstructed=false;monthSelected=" + i + ";constructCalendar();popDownMonth();event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>";
		}
		document.getElementById("selectMonth").innerHTML = "<table width=40	style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;' bgcolor='#FFFFDD' cellspacing=0 onmouseover='clearTimeout(timeoutID1)'	onmouseout='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"popDownMonth()\",100);event.cancelBubble=true'>" +	sHTML +	"</table>";
		monthConstructed = true;
	}
}

function popUpMonth() {
	constructMonth();
	crossMonthObj.visibility = (dom||ie)? "visible"	: "show";
	crossMonthObj.left = parseInt(crossobj.left) + 50;
	crossMonthObj.top = parseInt(crossobj.top) + 26;
	hideElement('SELECT', document.getElementById("selectMonth"));
	hideElement('APPLET', document.getElementById("selectMonth"));
}

function popDownMonth()	{
	crossMonthObj.visibility = "hidden";
}

function incYear() {
	for(i=0; i<7; i++) {
		newYear	= (i+nStartingYear)+1;

		if (newYear==yearSelected) {
			txtYear = "&nbsp;<B>"+ newYear +"</B>&nbsp;";
		} else {
			txtYear = "&nbsp;" + newYear + "&nbsp;";
		}
		document.getElementById("y"+i).innerHTML = txtYear;
	}
	nStartingYear++;
	bShow = true;
}

function decYear() {
	for (i=0; i<7; i++) {
		newYear	= (i+nStartingYear)-1;

		if (newYear==yearSelected) {
			txtYear = "&nbsp;<B>"+ newYear +"</B>&nbsp;";
		} else {
			txtYear = "&nbsp;" + newYear + "&nbsp;";
		}
		document.getElementById("y"+i).innerHTML = txtYear;
	}
	nStartingYear--;
	bShow = true;
}

function selectYear(nYear) {
	yearSelected = parseInt(nYear+nStartingYear);
	yearConstructed = false;
	constructCalendar();
	popDownYear();
}

function constructYear() {
	popDownMonth();
	sHTML =	"";

	if(!yearConstructed) {
		sHTML =	"<tr><td align='center'	onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='clearInterval(intervalID1);this.style.backgroundColor=\"\"' style='cursor:pointer'	onmousedown='clearInterval(intervalID1);intervalID1=setInterval(\"decYear()\",30)' onmouseup='clearInterval(intervalID1)'>-</td></tr>";
		j = 0;
		nStartingYear =	yearSelected-3;

		for (i=(yearSelected-3); i<=(yearSelected+3); i++) {
			sName =	i;

			if (i==yearSelected) {
				sName =	"<B>" +	sName +	"</B>"
			}
			sHTML += "<tr><td id='y" + j + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='selectYear("+j+");event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>";
			j ++;
		}
		sHTML += "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='clearInterval(intervalID2);this.style.backgroundColor=\"\"' style='cursor:pointer' onmousedown='clearInterval(intervalID2);intervalID2=setInterval(\"incYear()\",30)'	onmouseup='clearInterval(intervalID2)'>+</td></tr>";
		document.getElementById("selectYear").innerHTML	= "<table width=44 style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;'	bgcolor='#FFFFDD' onmouseover='clearTimeout(timeoutID2)' onmouseout='clearTimeout(timeoutID2);timeoutID2=setTimeout(\"popDownYear()\",100)' cellspacing=0>"	+ sHTML	+ "</table>";
		yearConstructed	= true;
	}
}

function popDownYear() {
	clearInterval(intervalID1);
	clearTimeout(timeoutID1);
	clearInterval(intervalID2);
	clearTimeout(timeoutID2);
	crossYearObj.visibility= "hidden";
}

function popUpYear() {
	var leftOffset;
	constructYear();
	crossYearObj.visibility	= (dom||ie)? "visible" : "show";
	leftOffset = parseInt(crossobj.left) + document.getElementById("spanYear").offsetLeft;

	if(ie) {
		leftOffset += 6;
	}
	crossYearObj.left = leftOffset;
	crossYearObj.top = parseInt(crossobj.top) + 26;
}

function WeekNbr(n) {
	year = n.getFullYear();
	month = n.getMonth() + 1;

	if (startAt == 0) {
		day = n.getDate() + 1;
	} else {
		day = n.getDate();
	}

	a = Math.floor((14-month) / 12);
	y = year + 4800 - a;
	m = month + 12 * a - 3;
	b = Math.floor(y/4) - Math.floor(y/100) + Math.floor(y/400);
	J = day + Math.floor((153 * m + 2) / 5) + 365 * y + b - 32045;
	d4 = (((J + 31741 - (J % 7)) % 146097) % 36524) % 1461;
	L = Math.floor(d4 / 1460);
	d1 = ((d4 - L) % 365) + L;
	week = Math.floor(d1/7) + 1;

	return week;
}

function constructCalendar() {
	var aNumDays = Array (31,0,31,30,31,30,31,31,30,31,30,31);
	var dateMessage;
	var startDate =	new Date (yearSelected,monthSelected,1);
	var endDate;

	if(monthSelected==1) {
		endDate	= new Date (yearSelected,monthSelected+1,1);
		endDate	= new Date (endDate	- (24*60*60*1000));
		numDaysInMonth = endDate.getDate();
	} else {
		numDaysInMonth = aNumDays[monthSelected];
	}

	datePointer = 0;
	dayPointer = startDate.getDay() - startAt;

	if(dayPointer<0) {
		dayPointer = 6;
	}
	sHTML =	"<table	 border=0 style='font-family:verdana;font-size:10px;'><tr>";

	if(showWeekNumber==1) {
		sHTML += "<td width=27><b>" + weekString + "</b></td><td width=1 rowspan=7 bgcolor='#d0d0d0' style='padding:0px'><img src='"+imgDir+"divider.gif' width=1></td>";
	}

	for(i=0; i<7; i++) {
		sHTML += "<td width='27' align='right'><B>"+ dayName[i]+"</B></td>";
	}
	sHTML +="</tr><tr>";

	if(showWeekNumber==1) {
		sHTML += "<td align=right>" + WeekNbr(startDate) + "&nbsp;</td>";
	}

	for(var i=1; i<=dayPointer;i++)	{
		sHTML += "<td>&nbsp;</td>";
	}

	for(datePointer=1; datePointer<=numDaysInMonth; datePointer++) {
		dayPointer++;
		sHTML += "<td align=right>";
		sStyle = styleAnchor;

		if((datePointer==odateSelected) && (monthSelected==omonthSelected) && (yearSelected==oyearSelected)) {
			sStyle += styleLightBorder;
		}
		sHint = "";

		for(k=0;k<HolidaysCounter;k++) {
			if((parseInt(Holidays[k].d)==datePointer)&&(parseInt(Holidays[k].m)==(monthSelected+1))) {
				if((parseInt(Holidays[k].y)==0)||((parseInt(Holidays[k].y)==yearSelected)&&(parseInt(Holidays[k].y)!=0))) {
					sStyle+="background-color:#FFDDDD;";
					sHint+=sHint==""?Holidays[k].desc:"\n"+Holidays[k].desc;
				}
			}
		}
		var regexp= /\"/g;
		sHint=sHint.replace(regexp,"&quot;");
		dateMessage = "onmousemove='window.status=\""+selectDateMessage.replace("[date]",constructDate(datePointer,monthSelected,yearSelected))+"\"' onmouseout='window.status=\"\"' ";

		if((datePointer==dateNow)&&(monthSelected==monthNow)&&(yearSelected==yearNow)) {
			sHTML += "<b><a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer+";closeCalendar();'><font color=#ff0000>&nbsp;" + datePointer + "</font>&nbsp;</a></b>";
		} else
		if(dayPointer % 7 == (startAt * -1)+1) {
			sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer + ";closeCalendar();'>&nbsp;<font color=#909090>" + datePointer + "</font>&nbsp;</a>";
		} else {
			sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer + ";closeCalendar();'>&nbsp;" + datePointer + "&nbsp;</a>";
		}
		sHTML += "";

		if((dayPointer+startAt) % 7 == startAt) {
			sHTML += "</tr><tr>";

			if((showWeekNumber==1)&&(datePointer<numDaysInMonth)) {
				sHTML += "<td align=right>" + (WeekNbr(new Date(yearSelected,monthSelected,datePointer+1))) + "&nbsp;</td>";
			}
		}
	}
	document.getElementById("content").innerHTML = sHTML;
	document.getElementById("spanMonth").innerHTML = "&nbsp;" + monthName[monthSelected] + "&nbsp;<IMG id='changeMonth' SRC='"+imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0>";
	document.getElementById("spanYear").innerHTML =	"&nbsp;" + yearSelected	+ "&nbsp;<IMG id='changeYear' SRC='"+imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0>";
}

function popUpCalendar(ctl, ctl2, format) {
	var leftpos = 0;
	var toppos = 0;

	if(bPageLoaded) {
		if(crossobj.visibility == "hidden") {

			ctlToPlaceValue	= ctl2;
			dateFormat=format;
			formatChar = " ";
			aFormat	= dateFormat.split(formatChar);

			if(aFormat.length<3) {
				formatChar = "/";
				aFormat	= dateFormat.split(formatChar);

				if(aFormat.length<3) {
					formatChar = ".";
					aFormat	= dateFormat.split(formatChar);

					if(aFormat.length<3) {
						formatChar = "-";
						aFormat	= dateFormat.split(formatChar);

						if (aFormat.length<3) {
							formatChar="";
						}
					}
				}
			}
			tokensChanged =	'0';

			if(formatChar != "") {
				aData =	ctl2.value.split(formatChar);
				for(i=0;i<3;i++) {
					if ((aFormat[i]=="d") || (aFormat[i]=="dd")) {
						dateSelected = parseInt(aData[i], 10);
						tokensChanged++;
					} else
					if((aFormat[i]=="m") || (aFormat[i]=="mm")) {
						monthSelected =	parseInt(aData[i], 10) - 1;
						tokensChanged++;
					} else
					if(aFormat[i]=="yyyy") {
						yearSelected = parseInt(aData[i], 10);
						tokensChanged++;
					}else
					if(aFormat[i]=="mmm") {

						for(j=0; j<12;	j++) {
							if (aData[i]==monthName[j]) {
								monthSelected=j;
								tokensChanged++;
							}
						}
					} else
					if(aFormat[i]=="mmmm") {
						for(j=0; j<12;	j++) {
							if (aData[i]==monthName2[j]) {
								monthSelected=j;
								tokensChanged ++;
							}
						}
					}
				}
			}

			if((tokensChanged!=3)||isNaN(dateSelected)||isNaN(monthSelected)||isNaN(yearSelected)) {
				dateSelected = dateNow;
				monthSelected =	monthNow;
				yearSelected = yearNow;
			}
			odateSelected=dateSelected;
			omonthSelected=monthSelected;
			oyearSelected=yearSelected;

			aTag = ctl;
			do {
				aTag = aTag.offsetParent;
				leftpos	+= aTag.offsetLeft;
				toppos += aTag.offsetTop;
			} while(aTag.tagName!="BODY");

			crossobj.left =	fixedX==-1 ? ctl.offsetLeft	+ leftpos :	fixedX;
			crossobj.top = fixedY==-1 ?	ctl.offsetTop +	toppos + ctl.offsetHeight +	2 :	fixedY;
			constructCalendar (1, monthSelected, yearSelected);
			crossobj.visibility=(dom||ie||mozila)? "visible" : "show";
			hideElement('SELECT', document.getElementById("calendar"));
			hideElement('APPLET', document.getElementById("calendar"));

			bShow = true;
		} else {
			hideCalendar();

			if (ctlNow!=ctl) {
				popUpCalendar(ctl, ctl2, format);
			}
		}
			ctlNow = ctl;
	}
}

document.onkeypress = function hidecal1() {
	if(event.keyCode==27) {
		hideCalendar();
	}
}

document.onclick = function hidecal2() {
	if(!bShow) {
		hideCalendar();
	}
	bShow = false;
}

if(ie) {
	calendarinit();
} else {
	window.onload = calendarinit;
}
var layerQueue = new Array();
var layerIndex = -1;


function hideElement(elmID, overDiv) {
	if(ie) {
		for(i = 0; i < document.getElementsByTagName(elmID).length; i++) {
			obj = document.getElementsByTagName( elmID )[i];

			if(!obj || !obj.offsetParent) {
				continue;
	  		}
  		  	objLeft   = obj.offsetLeft;
	 		objTop    = obj.offsetTop;
	  		objParent = obj.offsetParent;

	  		while(objParent.tagName.toUpperCase() != "BODY") {
				objLeft  += objParent.offsetLeft;
				objTop   += objParent.offsetTop;
				objParent = objParent.offsetParent;
	  		}

	 		objHeight = obj.offsetHeight;
	  		objWidth = obj.offsetWidth;

	  		if(( overDiv.offsetLeft + overDiv.offsetWidth ) <= objLeft );
	  		else if(( overDiv.offsetTop + overDiv.offsetHeight ) <= objTop );
	  		else if( overDiv.offsetTop >= ( objTop + objHeight ));
	  		else if( overDiv.offsetLeft >= ( objLeft + objWidth ));
	  		else {
				obj.style.visibility = "hidden";
	  		}
		}
  	}
}


function showElement(elmID) {
	if(ie) {
		for(i = 0; i < document.getElementsByTagName( elmID ).length; i++) {
			obj = document.getElementsByTagName( elmID )[i];

			if(!obj || !obj.offsetParent) {
				continue;
			}

			obj.style.visibility = "";
		}
	}
}

function lw_createLayer(layerName, top_pos, left_pos, width, height, bgcolor, bordercolor, z_index) {
	document.write("<div ONCLICK='event.cancelBubble=true' id='"+layerName+"' style='z-index:" + z_index + ";position:absolute;top:"+top_pos+";left:"+left_pos+";visibility:hidden;'><table bgcolor='"+bgcolor+"' style='border-width:1px;border-style:solid;border-color:" + bordercolor + "' cellpadding=2 cellspacing=0 width=0><tr><td valign=top width='"+width+"' height='"+height+"'><span id='"+layerName+"_content'></span></td></tr></table></div>");
}

function lw_getObj(objName) {
	return (dom)?document.getElementById(objName).style:ie?eval("document.all."+objName) :eval("document."+objName);
}

function lw_showLayer(layerName) {
	found=false;

	for(i=0;i<=layerIndex;i++) {
		if (layerQueue[i]==layerName) {
			found=true;
		}
	}

	if ((lw_getObj(layerName).visibility!="visible")&&(lw_getObj(layerName).visibility!="show")) {
		lw_getObj(layerName).visibility = (dom||ie)?"visible":"show";
		layerQueue[++layerIndex] = layerName;

		hideElement('SELECT', document.getElementById(layerName));
		hideElement('APPLET', document.getElementById(layerName));
	}
}

function lw_hideLayer() {
	showElement('SELECT', document.getElementById(layerQueue[layerIndex]));
	showElement('APPLET', document.getElementById(layerQueue[layerIndex]));

	lw_getObj(layerQueue[layerIndex--]).visibility = "hidden";
}

function lw_hideLayerName(layerName) {
	var i;
	var tmpQueue=new Array();
	var newIndex=-1;

	showElement('SELECT', document.getElementById(layerName));
	showElement('APPLET', document.getElementById(layerName));

	lw_getObj(layerName).visibility = "hidden";

	for(i=0;i<=layerIndex;i++) {
		if((layerQueue[i]!="")&&(layerQueue[i]!=layerName)) {
			tmpQueue [++newIndex] = layerQueue[i];
			hideElement('SELECT', document.getElementById(layerQueue[i]));
			hideElement('APPLET', document.getElementById(layerQueue[i]));
		}

	}
	layerQueue = tmpQueue;
	layerIndex = newIndex;
}

function lw_closeAllLayers() {
	while(layerIndex >= 0) {
		lw_hideLayer();
	}
}

function lw_closeLastLayer() {
	if(layerIndex >= 0) {
		while ((lw_getObj(layerQueue[layerIndex]).visibility!="visible") && (layerIndex>0)) {
			layerIndex--;
		}
		lw_hideLayer();
	}
}

function lw_escLayer(e) {
	if(navigator.appName=="Netscape") {
		var keyCode = e.keyCode?e.keyCode:e.which?e.which:e.charCode;
		if((keyCode==27)||(keyCode==1)) {
			lw_closeLastLayer();
		}
	} else
	if((event.keyCode==0)||(event.keyCode==27)) {
		lw_closeLastLayer();
	}
}


var lw_leftpos = 0;
var lw_toppos = 0;
var lw_width = 0;
var lw_height = 0;

function lw_calcpos(obj) {
	lw_leftpos=0;
	lw_toppos=0;
	lw_width = obj.offsetWidth;
	lw_height = obj.offsetHeight;
	var aTag = obj;

	do {
		lw_leftpos += aTag.offsetLeft;
		lw_toppos += aTag.offsetTop;
		aTag = aTag.offsetParent;
	} while(aTag.tagName!="BODY");
}
document.onkeypress = lw_escLayer;
document.onclick = lw_closeAllLayers;
