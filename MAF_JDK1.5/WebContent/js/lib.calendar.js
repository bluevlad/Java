/********************************************
*
* 어디서 가져 왔는지 출처는 잊었느나..
* 필요해 의해 Javascript 의 프로토 타입을 이용 
* 변수를 내부에 고정 시켰다..
* 
*  김상준 (2006.04.17)
*  
* 변경내용 : null 값 설정 기능 추가 (2006.04.17)
* 설정 변경시 이파일을 수정 하지 마시고.

*/
PopUpCalendarClass = function() {
    this.startAt = 0; // 일요일 표시 부분 / 0 : 일요일(일월화...) / 1 : 월요일(...금토일)
    this.showWeekNumber = 0; // 주(week)보임 유무 - 0 : 감춤 / 1 : 보임
    this.showToday = 1; // 오늘 날짜 표시 유무 - 0 : 감춤 / 1 : 보임
    this.imgDir = '/hkmca/js/lib.calendar/'; // 이미지 디렉토리 - ./ : 현재 디렉토리
    this.allowNull = 1;  // null 문자 입력 기능 활성화  1: 보임 // 나머지 안보임 
    
    this.gotoString = "오늘 날짜로 표시" // 오늘 날짜 링크에 마우스 올렸을시 상태바와 title메세지 / 원문 : Go To Current Month
    this.todayString = "오늘 : " // 오늘 날짜 메세지 / 원문 : Today is
    this.weekString = "Week" // 좌측 주(week)표시 / 원문 : Wk
    this.scrollLeftMessage = "이전 달로 이동" // 이전 달(month)로 이동하는 버튼에 마우스 올리면 상태바에 나타는 메세지
    // 원문 : Click to scroll to previous month. Hold mouse button to scroll automatically.
    this.scrollRightMessage = "다음 달로 이동" // 다음 달(month)로 이동하는 버튼에 마우스 올리면 상태바에 나타는 메세지
    // 원문 : Click to scroll to next month. Hold mouse button to scroll automatically.
    this.selectMonthMessage = "달을 선택합니다." // 달(month)을 선택하는 부분에 마우스 올리면 상태바에 나타나는 메세지
    // 원문 : Click to select a month.
    this.selectYearMessage = "년을 선택합니다." // 년(year)을 선택하는 부분에 마우스 올리면 상태바에 나타나는 메세지
    // 원문 : Click to select a year.
    this.selectDateMessage = "날짜를 선택합니다. : [date]" // 날짜에 마우스 올렸을시 상대바에 나타나는 메세지 / [data] : 날짜를 표시
    // 원문 : Select [date] as date.
    
    // 각 변수 선언
    this.crossobj;
    this.crossMonthObj;
    this.crossYearObj;
    this.monthSelected;
    this.yearSelected;
    this.dateSelected;
    this.omonthSelected;
    this.oyearSelected;
    this.odateSelected;
    this.monthConstructed;
    this.yearConstructed;
    this.intervalID1;
    this.intervalID2;
    this.timeoutID1;
    this.timeoutID2;
    this.ctlToPlaceValue;
    this.ctlNow;
    this.dateFormat;
    this.nStartingYear;
    
    this.bPageLoaded = false;
    this.ie = document.all;
    this.dom = document.getElementById;
    this.bShow = false;
    this.ns4 = document.layers;
    
    this.formatChar = " ";
    this.today = new	Date(); // 날짜 변수 선언
    this.dateNow = this.today.getDate(); // 로컬 컴퓨터의 일(day)을 구함
    this.monthNow = this.today.getMonth(); // 로컬 컴퓨터의 월(month)을 구함
    this.yearNow = this.today.getYear(); // 로컬 컴퓨터의 년(year)을 구함
    this.cal_imgsrc = new Array("drop1.gif","drop2.gif","left1.gif","left2.gif","right1.gif","right2.gif"); // 이미지 배열
    this.cal_img	= new Array(); // 배열 선언
    
    // 월(month)을 표시하는 m을 3개(mmm) 적었을시
    //var monthName = new Array("January","February","March","April","May","June","July","August","September","October","November","December");
    this.monthName = new Array("1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월");
    // 월(month)을 표시하는 m을 4개(mmmm) 적었을시
    this.monthName2 = new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");
    
    // 달력 구성 - 위 "일요일 표시 부분"과 관련
    if (this.startAt==0) {
    	// 일 월 화 수 목 금 토
    	//this.dayName = new Array("Sun","Mon","Tue","Wed","Thu","Fri","Sat");
    	this.dayName = new Array("일","월","화","수","목","금","토");
    } else {
    	// 월 화 수 목 금 토 일
    	//this.dayName = new Array("Mon","Tue","Wed","Thu","Fri","Sat","Sun");
    	this.dayName = new Array("월","화","수","목","금","토", "일");
    }
    this.HolidaysCounter = 0;
    this.Holidays = new Array();
    this.styleAnchor = "text-decoration:none;color:black;";
    this.styleAnchorTxt = "text-decoration:underline;color:#000000;";
    this.styleLightBorder = "border-style:solid;border-width:1px;border-color:#a0a0a0;";
    
    this.layerQueue = new Array();
    this.layerIndex = -1;
}

PopUpCalendarClass.prototype.init=function () {
	if(!this.ns4) {
		if(!this.ie) {
			this.yearNow += 1900;
		}

		this.crossobj = (this.dom)?document.getElementById("calCalendar").style : this.ie? document.all.calCalendar : document.calCalendar;
		this.hideCalendar();
		this.crossMonthObj = (this.dom)?document.getElementById("calSelectMonth").style : this.ie? document.all.calSelectMonth : document.calSelectMonth;
		this.crossYearObj = (this.dom)?document.getElementById("calSelectYear").style : this.ie? document.all.calSelectYear : document.calSelectYear;
		this.monthConstructed = false;
		this.yearConstructed = false;

		if(this.showToday==1) {
            sHtml = this.todayString + 
                " <a onmousemove='window.status=\""+
                this.gotoString+"\"' onmouseout='window.status=\"\"' title='"+
                this.gotoString+"' style='"+
                this.styleAnchorTxt+"' href='javascript:popCalObj.monthSelected=popCalObj.monthNow;popCalObj.yearSelected=popCalObj.yearNow;popCalObj.constructCalendar();'>"+
                this.yearNow+"년 " +
                this.monthName[this.monthNow].substring(0,3)+" "+
                this.dateNow+"일("+
                this.dayName[(this.today.getDay()-this.startAt==-1)?6:(this.today.getDay()-this.startAt)]+")</a>";
            if( this.allowNull==1) {
                sHtml += "&nbsp;<a onmousemove='window.status=\"빈날자입력\"' style='"+
                    this.styleAnchorTxt+"' href='javascript:popCalObj.setNull();'>[빈날자]</a>" ;
            }          
            document.getElementById("lblToday").innerHTML = sHtml;      
		}


		sHTML1 = "<span id='spanLeft' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer' "
                + " onmouseover='popCalObj.swapImage(\"changeLeft\",\"left2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""
                + this.scrollLeftMessage+"\"' onclick='javascript:popCalObj.decMonth()' "
                + " onmouseout='clearInterval(popCalObj.intervalID1);popCalObj.swapImage(\"changeLeft\",\"left1.gif\");"
                + "this.style.borderColor=\"#3366FF\";window.status=\"\"' "
                + " onmousedown='clearTimeout(popCalObj.timeoutID1);popCalObj.timeoutID1=setTimeout(\"popCalObj.StartDecMonth()\",500)'	"
                + " onmouseup='clearTimeout(popCalObj.timeoutID1);clearInterval(popCalObj.intervalID1)'>&nbsp<IMG id='changeLeft' SRC='"
                + this.imgDir+"left1.gif' width=10 height=11 BORDER=0>&nbsp</span>&nbsp;";
		sHTML1 += "<span id='spanRight' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer'"
                + " onmouseover='popCalObj.swapImage(\"changeRight\",\"right2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""
                + this.scrollRightMessage+"\"' onclick='popCalObj.incMonth()' "
                + " onmouseout='clearInterval(popCalObj.intervalID1);popCalObj.swapImage(\"changeRight\",\"right1.gif\");" 
                +" this.style.borderColor=\"#3366FF\";window.status=\"\"' "
                + " onmousedown='clearTimeout(popCalObj.timeoutID1);popCalObj.timeoutID1=setTimeout(\"popCalObj.StartIncMonth()\",500)'	"
                + " onmouseup='clearTimeout(popCalObj.timeoutID1);clearInterval(popCalObj.intervalID1)'>&nbsp<IMG id='changeRight' SRC='"
                + this.imgDir+"right1.gif'	width=10 height=11 BORDER=0>&nbsp</span>&nbsp";
		sHTML1 += "<span id='calSpanMonth' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer'	"
                + " onmouseover='popCalObj.swapImage(\"calChangeMonth\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""
                + this.selectMonthMessage+"\"' onclick='popCalObj.popUpMonth()' "
                + " onmouseout='popCalObj.swapImage(\"calChangeMonth\",\"drop1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' ></span>&nbsp;";
		sHTML1 += "<span id='calSpanYear' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer' "
                + " onmouseover='popCalObj.swapImage(\"calChangeYear\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""
                + this.selectYearMessage+"\"'	onclick='popCalObj.popUpYear()' "
                + " onmouseout='popCalObj.swapImage(\"calChangeYear\",\"drop1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"'	></span>&nbsp;";

		document.getElementById("calCaption").innerHTML = sHTML1;
		this.bPageLoaded = true;
	}
}


PopUpCalendarClass.prototype.hideElement=function (overDiv) {
	hideControl('SELECT',overDiv);
}


PopUpCalendarClass.prototype.showElement=function () {
	showControl(document.getElementById("calCalendar"));
}

HolidayRec = function(d, m, y, desc) {
	this.d = d;
	this.m = m;
	this.y = y;
	this.desc = desc;
}



PopUpCalendarClass.prototype.addHoliday=function (d, m, y, desc) {
	this.Holidays[this.HolidaysCounter++] = new HolidayRec ( d, m, y, desc );
}



PopUpCalendarClass.prototype.swapImage=function(srcImg, destImg) {
	if(this.ie) {
		document.getElementById(srcImg).setAttribute("src",this.imgDir + destImg);
	}
}



PopUpCalendarClass.prototype.hideCalendar=function ()	{
	this.crossobj.visibility = "hidden";
	if(this.crossMonthObj != null) {
		this.crossMonthObj.visibility="hidden";
	}

	if(this.crossYearObj != null) {
		this.crossYearObj.visibility="hidden";
	}

	this.showElement();

}

PopUpCalendarClass.prototype.padZero=function (num) {
	return (num < 10)? '0' + num : num;
}

PopUpCalendarClass.prototype.constructDate=function(d,m,y) {
	sTmp = this.dateFormat;
	sTmp = sTmp.replace("dd","<e>");
	sTmp = sTmp.replace("d","<d>");
	sTmp = sTmp.replace("<e>",this.padZero(d));
	sTmp = sTmp.replace("<d>",d);
	sTmp = sTmp.replace("mmmm","<p>");
	sTmp = sTmp.replace("mmm","<o>");
	sTmp = sTmp.replace("mm","<n>");
	sTmp = sTmp.replace("m","<m>");
	sTmp = sTmp.replace("<m>",m+1);
	sTmp = sTmp.replace("<n>",this.padZero(m+1));
	sTmp = sTmp.replace("<o>",this.monthName[m]);
	sTmp = sTmp.replace("<p>",this.monthName2[m]);
	sTmp = sTmp.replace("yyyy",y);

	return sTmp.replace("yy",this.padZero(y%100));
}

PopUpCalendarClass.prototype.closeCalendar=function () {
	var sTmp;
	this.hideCalendar();
	this.ctlToPlaceValue.value =	this.constructDate(this.dateSelected,this.monthSelected, this.yearSelected);
}

PopUpCalendarClass.prototype.setNull=function () {
	var sTmp;
	this.hideCalendar();
	this.ctlToPlaceValue.value =	"";
}

PopUpCalendarClass.prototype.StartDecMonth=function () {
	this.intervalID1 = setInterval("popCalObj.decMonth()",80);
}

PopUpCalendarClass.prototype.StartIncMonth=function () {
	this.intervalID1 = setInterval("popCalObj.incMonth()",80);
}

PopUpCalendarClass.prototype.incMonth=function () {
	this.monthSelected++;

	if (this.monthSelected>11) {
		this.monthSelected=0;
		this.yearSelected++;
	}
	this.constructCalendar();
}

PopUpCalendarClass.prototype.decMonth=function () {
	this.monthSelected--;

	if (this.monthSelected<0) {
		this.monthSelected=11;
		this.yearSelected--;
	}
	this.constructCalendar();
}

PopUpCalendarClass.prototype.constructMonth=function () {
	this.popDownYear();
	var sHTML =	"";

	if (!this.monthConstructed) {

		for(i=0; i<12; i++) {
			sName =	this.monthName[i];
			if (i==this.monthSelected){
				sName =	"<B>" +	sName +	"</B>";
			}
			sHTML += "<tr><td id='calM" + i + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' ";
            sHTML += " onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer'  ";
            sHTML += " onclick='popCalObj.monthConstructed=false;popCalObj.monthSelected=" + i + ";popCalObj.constructCalendar();popCalObj.popDownMonth();event.cancelBubble=true'>&nbsp;";
            sHTML += sName + "&nbsp;</td></tr>";
		}
        sTable = "<table width=40	style='font-family:arial; font-size:11px; border-width:1;border-style:solid; border-color:#a0a0a0;' ";
        sTable += " bgcolor='#FFFFDD' cellspacing=0 ";
        sTable += " onmouseover='clearTimeout(popCalObj.timeoutID1)'	";
        sTable += " onmouseout='clearTimeout(popCalObj.timeoutID1);popCalObj.timeoutID1=setTimeout(\"popCalObj.popDownMonth()\",100);event.cancelBubble=true'>"
		document.getElementById("calSelectMonth").innerHTML = sTable +	sHTML +	"</table>";
		this.monthConstructed = true;
	}
}

PopUpCalendarClass.prototype.popUpMonth=function () {
	this.constructMonth();
	this.crossMonthObj.visibility = (this.dom||this.ie)? "visible"	: "show";
	this.crossMonthObj.left = parseInt(this.crossobj.left) + 50;
	this.crossMonthObj.top = parseInt(this.crossobj.top) + 26;
	this.hideElement('SELECT', document.getElementById("calSelectMonth"));
	
}

PopUpCalendarClass.prototype.popDownMonth=function ()	{
	this.crossMonthObj.visibility = "hidden";
}

PopUpCalendarClass.prototype.incYear=function () {
    var txtYear = "";
	for(i=0; i<7; i++) {
		this.newYear	= (i+this.nStartingYear)+1;

		if (this.newYear==this.yearSelected) {
			txtYear = "&nbsp;<B>"+ this.newYear +"</B>&nbsp;";
		} else {
			txtYear = "&nbsp;" + this.newYear + "&nbsp;";
		}
		document.getElementById("calY"+i).innerHTML = txtYear;
	}
	this.nStartingYear++;
	this.bShow = true;
}

PopUpCalendarClass.prototype.decYear=function () {
    var txtYear = "";
	for (i=0; i<7; i++) {
		this.newYear	= (i+this.nStartingYear)-1;

		if (this.newYear==this.yearSelected) {
			txtYear = "&nbsp;<B>"+ this.newYear +"</B>&nbsp;";
		} else {
			txtYear = "&nbsp;" + this.newYear + "&nbsp;";
		}
		document.getElementById("calY"+i).innerHTML = txtYear;
	}
	this.nStartingYear--;
	this.bShow = true;
}

PopUpCalendarClass.prototype.selectYear=function (nYear) {
	this.yearSelected = parseInt(nYear + this.nStartingYear);
	this.yearConstructed = false;
	this.constructCalendar();
	this.popDownYear();
}

PopUpCalendarClass.prototype.constructYear=function () {
	this.popDownMonth();
	var sHTML =	"";

	if(!this.yearConstructed) {
		sHTML =	"<tr><td align='center'	onmouseover='this.style.backgroundColor=\"#FFCC99\"' ";
        sHTML += " onmouseout='clearInterval(popCalObj.intervalID1);this.style.backgroundColor=\"\"' style='cursor:pointer'	";
        sHTML += " onmousedown='clearInterval(popCalObj.intervalID1);popCalObj.intervalID1=setInterval(\"popCalObj.decYear()\",30)' ";
        sHTML += " onmouseup='clearInterval(popCalObj.intervalID1)'>-</td></tr>";
		j = 0;
		this.nStartingYear =	this.yearSelected-3;
		for (i=(this.yearSelected-3); i<=(this.yearSelected+3); i++) {
			sName =	i;

			if (i==this.yearSelected) {
				sName =	"<B>" +	sName +	"</B>"
			}
			sHTML += "<tr><td id='calY" + j + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' ";
            sHTML += " onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' ";
            sHTML += " onclick='popCalObj.selectYear("+j+");event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>";
			j ++;
		}
		sHTML += "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' ";
        sHTML += " onmouseout='clearInterval(popCalObj.intervalID2);this.style.backgroundColor=\"\"' style='cursor:pointer' ";
        sHTML += " onmousedown='clearInterval(popCalObj.intervalID2);popCalObj.intervalID2=setInterval(\"popCalObj.incYear()\",30)'	";
        sHTML += " onmouseup='clearInterval(popCalObj.intervalID2)'>+</td></tr>";
        
        sTable = "<table width=44 style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;'	";
        sTable += " bgcolor='#FFFFDD' onmouseover='clearTimeout(popCalObj.timeoutID2)' ";
        sTable += " onmouseout='clearTimeout(popCalObj.timeoutID2);popCalObj.timeoutID2=setTimeout(\"popCalObj.popDownYear()\",100)' cellspacing=0>";
		document.getElementById("calSelectYear").innerHTML	= sTable	+ sHTML	+ "</table>";
		this.yearConstructed	= true;
	}
}

PopUpCalendarClass.prototype.popDownYear=function () {
	clearInterval(this.intervalID1);
	clearTimeout(this.timeoutID1);
	clearInterval(this.intervalID2);
	clearTimeout(this.timeoutID2);
	this.crossYearObj.visibility= "hidden";
}

PopUpCalendarClass.prototype.popUpYear=function () {
	var leftOffset;
	this.constructYear();
	this.crossYearObj.visibility	= (this.dom||this.ie)? "visible" : "show";
	leftOffset = parseInt(this.crossobj.left) + document.getElementById("calSpanYear").offsetLeft;

	if(this.ie) {
		leftOffset += 6;
	}
	this.crossYearObj.left = leftOffset;
	this.crossYearObj.top = parseInt(this.crossobj.top) + 26;
}

PopUpCalendarClass.prototype.WeekNbr=function (n) {
	year = n.getFullYear();
	month = n.getMonth() + 1;

	if (this.startAt == 0) {
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

PopUpCalendarClass.prototype.constructCalendar=function () {
	var aNumDays = Array (31,0,31,30,31,30,31,31,30,31,30,31);
	var dateMessage;
	var startDate =	new Date (this.yearSelected, this.monthSelected,1);
	var endDate;

	if(this.monthSelected==1) {
		endDate	= new Date (this.yearSelected, this.monthSelected+1, 1);
		endDate	= new Date (endDate	- (24*60*60*1000));
		numDaysInMonth = endDate.getDate();
	} else {
		numDaysInMonth = aNumDays[this.monthSelected];
	}

	datePointer = 0;
	dayPointer = startDate.getDay() - this.startAt;

	if(dayPointer<0) {
		dayPointer = 6;
	}
	sHTML =	"<table	 border=0 style='font-family:verdana;font-size:11px;'><tr>";

	if(this.showWeekNumber==1) {
		sHTML += "<td width=27><b>" + this.weekString + "</b></td><td width=1 rowspan=7 bgcolor='#d0d0d0' style='padding:0px'>"
        sHTML += "<img src='"+this.imgDir+"divider.gif' width=1></td>";
	}

	for(i=0; i<7; i++) {
		sHTML += "<td width='27' align='right'><B>"+ this.dayName[i]+"</B></td>";
	}
	sHTML +="</tr><tr>";

	if(this.showWeekNumber==1) {
		sHTML += "<td align=right>" + this.WeekNbr(startDate) + "&nbsp;</td>";
	}

	for(var i=1; i<=dayPointer;i++)	{
		sHTML += "<td>&nbsp;</td>";
	}

	for(datePointer=1; datePointer<=numDaysInMonth; datePointer++) {
		dayPointer++;
		sHTML += "<td align=right>";
		sStyle = this.styleAnchor;

		if((datePointer==this.odateSelected) && (this.monthSelected==this.omonthSelected) && (this.yearSelected==this.oyearSelected)) {
			sStyle += this.styleLightBorder;
		}
		sHint = "";

		for(k=0;k < this.HolidaysCounter;k++) {
			if((parseInt(this.Holidays[k].d)==datePointer)&&(parseInt(this.Holidays[k].m)==(this.monthSelected+1))) {
				if((parseInt(this.Holidays[k].y)==0)||((parseInt(this.Holidays[k].y)==this.yearSelected)&&(parseInt(this.Holidays[k].y)!=0))) {
					sStyle+="background-color:#FFDDDD;";
					sHint+=sHint==""?this.Holidays[k].desc:"\n"+this.Holidays[k].desc;
				}
			}
		}
		var regexp= /\"/g;
		sHint=sHint.replace(regexp,"&quot;");
        
		dateMessage = "onmousemove='window.status=\""
                        +this.selectDateMessage.replace("[date]", this.constructDate(this.datePointer,this.monthSelected,this.yearSelected))
                        +"\"' onmouseout='window.status=\"\"' ";

		if((datePointer==this.dateNow)&&(this.monthSelected==this.monthNow)&&(this.yearSelected==this.yearNow)) {
			sHTML += "<b><a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' "
                    + " href='javascript:popCalObj.dateSelected="+datePointer+";popCalObj.closeCalendar();'><font color=#ff0000>&nbsp;" + datePointer + "</font>&nbsp;</a></b>";
		} else {
    		if(dayPointer % 7 == (this.startAt * -1)+1) {
    			sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' "
                        + " href='javascript:popCalObj.dateSelected="+datePointer + ";popCalObj.closeCalendar();'>&nbsp;<font color=#909090>" + datePointer + "</font>&nbsp;</a>";
    		} else {
    			sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' "
                        + " href='javascript:popCalObj.dateSelected="+datePointer + ";popCalObj.closeCalendar();'>&nbsp;" + datePointer + "&nbsp;</a>";
    		}
        }
		sHTML += "";

		if((dayPointer+this.startAt) % 7 == this.startAt) {
			sHTML += "</tr><tr>";

			if((this.showWeekNumber==1)&&(datePointer<numDaysInMonth)) {
				sHTML += "<td align=right>" + (this.WeekNbr(new Date(this.yearSelected, this.monthSelected, this.datePointer+1))) + "&nbsp;</td>";
			}
		}
	}
	document.getElementById("calContent").innerHTML = sHTML;
	document.getElementById("calSpanMonth").innerHTML = "&nbsp;" + this.monthName[this.monthSelected] + "&nbsp;"
                                        + "<IMG id='calChangeMonth' SRC='"+this.imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0>";
	document.getElementById("calSpanYear").innerHTML =	"&nbsp;" + this.yearSelected	+ "&nbsp;"
                                        + "<IMG id='calChangeYear' SRC='"+this.imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0>";
}

var popCalObj = new PopUpCalendarClass();


if(popCalObj.dom) {
	for(i=0; i<popCalObj.cal_imgsrc.length; i++) {
		popCalObj.cal_img[i] = new Image;
		popCalObj.cal_img[i].src = popCalObj.imgDir + popCalObj.cal_imgsrc[i];
	}

	document.write ("<div onclick='popCalObj.bShow=true' id='calCalendar'	style='z-index:+999;position:absolute;visibility:hidden;left:0;top:0;'>");
    document.write ("<table	width="+((popCalObj.showWeekNumber==1)?250:220));
    document.write (" style='font-family:arial;font-size:11px;border-width:1;border-style:solid;border-color:#a0a0a0;font-family:arial; font-size:11px;' bgcolor='#ffffff'><tr bgcolor='#0000aa'><td>");
    document.write ("<table width='"+((popCalObj.showWeekNumber==1)?248:218)+"'><tr>");
    document.write ("<td style='padding:2px;font-family:arial; font-size:11px;'><font color='#ffffff'><B><span id='calCaption'></span></B></font></td>");
    document.write ("<td align=right><a href='javascript:popCalObj.hideCalendar()'><IMG SRC='"+popCalObj.imgDir+"close.gif' WIDTH='15' HEIGHT='13' BORDER='0' ALT='Close the Calendar'></a></td></tr></table>");
    document.write ("</td></tr><tr><td style='padding:5px' bgcolor=#ffffff><span id='calContent'></span></td></tr>");

	if(popCalObj.showToday==1) {
		document.write ("<tr bgcolor=#f0f0f0><td style='padding:5px' align=center><span id='lblToday'></span></td></tr>");
	}

	document.write ("</table></div><div id='calSelectMonth' style='z-index:+999;position:absolute;visibility:hidden;'>");
    document.write ("</div><div id='calSelectYear' style='z-index:+999;position:absolute;visibility:hidden;'></div>");
}

popCalObj.init();

/**
* ctl : calendar가 나탈날 위치의 object
* target : input element 값을 읽고 저장할 곳 
*/
function popUpCalendar(ctl, target, format) {
	var leftpos = 0;
	var toppos = 0;

	if(popCalObj.bPageLoaded) {
		if(popCalObj.crossobj.visibility == "hidden") {
			
			popCalObj.ctlToPlaceValue	= target;
			popCalObj.dateFormat=format;
			popCalObj.formatChar = " ";
			var aFormat	= popCalObj.dateFormat.split(popCalObj.formatChar);

			if(aFormat.length<3) {
				popCalObj.formatChar = "/";
				aFormat	= popCalObj.dateFormat.split(popCalObj.formatChar);

				if(aFormat.length<3) {
					popCalObj.formatChar = ".";
					aFormat	= popCalObj.dateFormat.split(popCalObj.formatChar);

					if(aFormat.length<3) {
						popCalObj.formatChar = "-";
						aFormat	= popCalObj.dateFormat.split(popCalObj.formatChar);

						if (aFormat.length<3) {
							popCalObj.formatChar="";
						}
					}
				}
			}
			var tokensChanged =	'0';

			if(popCalObj.formatChar != "") {
				aData =	target.value.split(popCalObj.formatChar);

				for(i=0;i<3;i++) {
					if ((aFormat[i]=="d") || (aFormat[i]=="dd")) {
						popCalObj.dateSelected = parseInt(aData[i], 10);
						tokensChanged++;
					} else
					if((aFormat[i]=="m") || (aFormat[i]=="mm")) {
						popCalObj.monthSelected =	parseInt(aData[i], 10) - 1;
						tokensChanged++;
					} else
					if(aFormat[i]=="yyyy") {
						popCalObj.yearSelected = parseInt(aData[i], 10);
						tokensChanged++;
					}else
					if(aFormat[i]=="mmm") {

						for(j=0; j<12;	j++) {
							if (aData[i]==popCalObj.monthName[j]) {
								popCalObj.monthSelected=j;
								tokensChanged++;
							}
						}
					} else
					if(aFormat[i]=="mmmm") {
						for(j=0; j<12;	j++) {
							if (aData[i]==popCalObj.monthName2[j]) {
								popCalObj.monthSelected=j;
								tokensChanged ++;
							}
						}
					}
				}
			}

			if((tokensChanged!=3)||isNaN(popCalObj.dateSelected)||isNaN(popCalObj.monthSelected)||isNaN(popCalObj.yearSelected)) {
				popCalObj.dateSelected = popCalObj.dateNow;
				popCalObj.monthSelected =	popCalObj.monthNow;
				popCalObj.yearSelected = popCalObj.yearNow;
			}
			popCalObj.odateSelected     = popCalObj.dateSelected;
			popCalObj.omonthSelected    = popCalObj.monthSelected;
			popCalObj.oyearSelected     = popCalObj.yearSelected;

			var aTag = ctl;
			do {
				aTag = aTag.offsetParent;
				leftpos	+= aTag.offsetLeft +0;
				toppos += aTag.offsetTop +0 ;
			} while(aTag.tagName!="BODY");
            fixedX = -1; // 레이어 X축 위치 (-1 : 버튼에 바로 아래에 표시)
            fixedY = -1; // 레이어 Y축 위치 (-1 : 버튼에 바로 아래에 표시)

			var tx = fixedX==-1 ? ctl.offsetLeft + leftpos : fixedX;
			var ty = fixedY==-1 ? ctl.offsetTop + toppos + ctl.offsetHeight + 2 : fixedY;
			popCalObj.crossobj.left = tx +'px';
			popCalObj.crossobj.top =  ty +'px';
			popCalObj.constructCalendar (1, popCalObj.monthSelected, popCalObj.yearSelected);
			popCalObj.crossobj.visibility=(popCalObj.dom||popCalObj.ie)? "visible" : "show";
			popCalObj.hideElement( document.getElementById("calCalendar"));
			

			popCalObj.bShow = true;
			
		} else {
			popCalObj.hideCalendar();

			if (ctlNow!=ctl) {
				popUpCalendar(ctl, target, format);
			}
		}
			ctlNow = ctl;
	}
}

document.onkeypress = function hidecal1() {
	if(event.keyCode==27) {
		popCalObj.hideCalendar();
	}
}

document.onclick = function hidecal2() {
	if(!popCalObj.bShow) {
		popCalObj.hideCalendar();
	}
	popCalObj.bShow = false;
}

var layerQueue = new Array();
var layerIndex = -1;



