<!--
/************************
*
* formname의 checkname을 모두 toggle, allcheckname = akkcheck
* 사용법 1:
* allChekboxToggle(this, 'formName','usn_check','allCheckbox');
* 사용법 2: allcheck 발생원이 check박스의 경우
* allChekboxToggle(this, 'formName','usn_check');
*/
var CPATH = "/smis";
//var LectureWindow=null;

function windows_focus(oWin) {
  if (oWin == null) {
		alert("팝업차단 기능으로 인해 팝업 페이지가 차단되었습니다.\n차단된 팝업창을 허용해 주십시오.");
  }
  if (oWin.opener == null) {
  	oWin.opener = window;
  }
//  oWin.opener.name = "opener";
  oWin.focus();
}


function allChekboxToggle(srcObject,  formName, chekName, allCheckName) {
	var frm = getObject(formName);
	var obj = null;
	if(allCheckName != null ) {
		var allcheck = getObject(allCheckName);

		if (allcheck.checked == false) {
			allcheck.checked = true;
		} else {
			allcheck.checked = false;
		}
		obj = allcheck;
		//alert("a "+ obj.name);
	} else {
		obj = srcObject;
		//alert("b " + obj.name);
	}

	if(frm ) {
		for (var i=0; i<frm.elements.length;i++) {
			//alert(frm.elements[i].type + "," + frm.elements[i].name);
			if (frm.elements[i].type == "checkbox" && frm.elements[i].name == chekName) {
				frm.elements[i].checked = obj.checked;
			}
		}
	} else {
		alert("FormName(" + formName+") is invalid!!!");
	}
}

// 이미지의 원래크기 보여주기
function ShowBigImageOne(url) {
		var urlname = CPATH + "/etc/showimageone.jsp?url="+escape(url);
		browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=no,resizable=yes,menubar=no,scrollbars=yes,width=10,height=10");
		windows_focus(browsing_window);
}

/* element 의 Top 과 Left 를 가져온가 */
function element_top(el)
{
	var et = 0
	while (el)
	{
		et += el.offsetTop
		el = el.offsetParent
	}
	return et
}

function element_left(el)
{
	var et = 0
	while (el)
	{
		et += el.offsetLeft
		el = el.offsetParent
	}
	return et
}
function popupWindow(theURL)  {
	popupWindow(theUrl,null);
}
function popupWindow(theURL, winName) {
  var oWin = window.open(theURL,winName,'width=10, height=10, scrollbars=auto, menubar=no,status=yes, titlebar=yes, toolbar=no, location=no, top=0,left=0, resizable=yes');
	windows_focus(oWin);
}
/* 회원에 대한 개별정보를 보여준다.*/
function viewUser(home_url, usn){
    var oWin = window.open(home_url+"/xadmin/user/view_user.do?cmd=view&usn="+usn, "user", "width=10, height=10, scrollbars=yes, resizable=yes");
		windows_focus(oWin);
}
/**
* document d의 id나 name으로 오브젝트를 찾아 돌려줌
* document.all이나 document.objname의 비표준 script를 사용하지 맙시다.<b>
* object에는 항상 id와 name을 동시에 쓰는 습관을
*/
function getObject(n, d) {
  var p,i,x;  if(!d) d=document;
  if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document;
	n=n.substring(0,p);
  }
  if(!(x=d[n])&&d.all) x=d.all[n];
  for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=KMM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

// CheckBoX나 Radio에서 checked된 값을 가져온다. checkbox의 경우 첫번째 값
function getFormCheckedValue(frm, name) {
	for(var n=0; n < frm.elements.length; n ++ ) {
		if(frm.elements[n].name == name) {
			if(frm.elements[n].checked){
				return frm.elements[n].value;
			};
		}
	}
}



function KMM_swapImgRestore() { //v3.0
  var i,x,a=document.KMM_sr;
  for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function KMM_preloadImages() { //v3.0
  var d=document;
  if(d.images){ if(!d.KMM_p) d.KMM_p=new Array();
  var i,j=d.KMM_p.length,a=KMM_preloadImages.arguments;
  for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.KMM_p[j]=new Image; d.KMM_p[j++].src=a[i];}
  }
}

function KMM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document;
  if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document;
	n=n.substring(0,p);
  }
  if(!(x=d[n])&&d.all) x=d.all[n];
  for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=KMM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function KMM_swapImage() { //v3.0
  var i,j=0,x,a=KMM_swapImage.arguments;
  document.KMM_sr=new Array;
  for(i=0;i<(a.length-2);i+=3) {
    if ((x=KMM_findObj(a[i]))!=null){document.KMM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
  }
}

function KMM_showHideLayers() { //v6.0
  var i,p,v,obj,args=KMM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) {
  	if ((obj=KMM_findObj(args[i]))!=null) {
		v=args[i+2];
    	if (obj.style) {
			obj=obj.style;
			if(v=='show') {
				obj.visibility='visible';
				obj.display='';
			} else {
				//obj.visibility='hide';
				obj.display='none';
			}
		}
	}
  }

}

// 하반 바로가기 Link
function open_quickLink1()  {
	var img = getObject("imgRelated");
	var qck1 = getObject("qck1");
	if(qck1) {
		if(qck1.style) {
		  	qck1.style.visibility=(qck1.style.visibility == "visible")?"hidden":"visible";
		  	qck1.style.pixelTop = element_top(img) - qck1.style.pixelHeight;
		  	qck1.style.pixelLeft = element_left(img) + 17;
		}
	}
}


function inputcheck(obj, str) {
	if(obj) {
		if(obj.isMultiLine == false) {
			if(obj.checked && obj.value == str)	return true;
		} else {
		    for (j=0;j<obj.length;j++) {
		        if (obj[j].checked && obj[j].value == str) return true;
		    }
		}
	}
    return false;
}

/*
* str1에서 cnt 만큰 잘라서 가져온다.
*/
function getStr(str1, cnt) {
	var msglen = 0;
	var msgstr = "";
	for(k=0;k<str1.length && msglen < parseInt(cnt);k++){
		t = str1.charAt(k);
		if (escape(t).length > 4) {
			if((msglen+2) <= parseInt(cnt)) msgstr += t;
			msglen += 2;
		} else {
			if((msglen+1) <= parseInt(cnt)) msgstr += t;
			msglen++;
		}
	}
	return msgstr;
}

/*
* str1읠 byte를 세온다.
*/
function strLength(var1) {
	return var1.bytes();
}


// [Cookie] Sets value in a cookie
function setCookie(cookieName, cookieValue, expires, path, domain, secure) {
	document.cookie =
		escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
};

// [Cookie] Gets a value from a cookie
function getCookie(cookieName) {
	var cookieValue = '';
	var posName = document.cookie.indexOf(escape(cookieName) + '=');
	if (posName != -1) {
		var posValue = posName + (escape(cookieName) + '=').length;
		var endPos = document.cookie.indexOf(';', posValue);
		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));
		else cookieValue = unescape(document.cookie.substring(posValue));
	}
	return (cookieValue);
};


function tr_over(obj){
	if (obj.tagName == "TR") {

		for(i=0;i< obj.cells.length; i++) {
			var oTd = obj.cells.item(i);
			oTd.setAttribute("oldBackground", oTd.style.backgroundColor);
			oTd.style.backgroundColor = "#cdcdcd";
		}
	};


}

function tr_out(obj) {
	if (obj.tagName == "TR") {
		for(i=0;i< obj.cells.length; i++) {
			var oTd = obj.cells.item(i);
			oTd.style.backgroundColor = oTd.getAttribute("oldBackground");
		}
	};
}

/**
* select 위에 div 올릴때. select 잠시 안보이게 하기
* tagname = "SELECT"
*/
function hideControl (tagName, popupObj)
{
    if (document.all) {

        var x = element_left (popupObj);
        var y = element_top (popupObj);
        var w = popupObj.offsetWidth;
        var h = popupObj.offsetHeight;

        var i;
        for (i = 0; i < document.all.tags(tagName).length; ++i) {
            var obj = document.all.tags(tagName)[i];
            if (!obj || !obj.offsetParent) continue;

            var ox = element_left (obj);
            var oy = element_top (obj);
            var ow = obj.offsetWidth;
            var oh = obj.offsetHeight;

            if (ox > (x + w) || (ox + ow) < x) continue;
            if (oy > (y + h) || (oy + oh) < y) continue;

            if(obj.style.visibility == "hidden") continue;

            if(!popupObj.overFlag)
                popupObj.overFlag = new Array ();

            popupObj.overFlag[popupObj.overFlag.length] = obj;
            obj.style.visibility = "hidden";
        }
    }

}

function showControl(popupObj)
{
    if (popupObj.overFlag) {
        var i;
        for (i = 0; i < popupObj.overFlag.length; ++i)
            popupObj.overFlag[i].style.visibility = "";
    }
    popupObj.overFlag = null;
}



function printPost(){
	var oWin = open(CPATH + "/etc/print.html", "printWin", "width=750, height=600, resizable=yes, scrollbars=yes");
	windows_focus(oWin);
}




function viewPrintPost(printWin){
	var printObj = document.all["contentArea" ];
	var printHTML = "";
	//alert("base is " + document.location.pathname);
	if(typeof(printObj) == "object" && typeof(printWin.document.all.printArea) == "object"){
		oColl = printObj.all;
		for(x=0;x<oColl.length;x++){
			if(oColl[x].tagName == "IMG") {
				oColl[x].src = oColl[x].src;
			}
		}

		printHTML = "<TABLE cellSpacing=0 cellPadding=0 width=650 border=0>"
		for(i = 0; i < printObj.rows.length; i++) {
			printHTML += printObj.rows[i].outerHTML;
		}
		printHTML +="</TABLE>"

		printWin.document.all.printArea.innerHTML = printHTML;
	}

}

function leftmenu_over(o) {
	o.oldColor = o.style.backgroundColor
	o.style.backgroundColor= '#4BB0DC';
}

function leftmenu_out(o) {
	o.style.backgroundColor=o.oldColor; // '#DEEAEF';
}

function resizeBodyTo(w, h)
{
    window.resizeTo(w, h);
/*
    with(document.body)
    {
        window.resizeTo(w * 2 - clientWidth, h * 2 - clientHeight);
    }
*/
}
//클럽 새창 띄우기
function open_club(cid) {
	var oWin = window.open(CPATH+'/club/default.do?club_id='+ cid,'','width=900, height=600, status=0, menubars=no, scrollbars=no');
	windows_focus(oWin);
}

function open_msgbox() {
	 var oWin = window.open(CPATH+'/msgmst/rcvlist.do','msg_win','width=460,height=610,status=yes,menubars=no,scrollbars=no,top=0px,left=0px');
	windows_focus(oWin);
}


function logout() {
	if (confirm("접속을 종료 하시겟습니까?")) {
//		if(LectureWindow) {
//			LectureWindow.close();
//		}
		document.location = CPATH + "/logout.do";
	}
}


// 강의 보기
function openLec(leccode, chasi){
	var urlname = CPATH + "/course/studyroom.do?extleccode="+leccode+"&chpcode="+chasi;
	var oWin = window.open(urlname,
		"LectureWindow",
		"top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=no,width=820,height=694");
	
	windows_focus(oWin);
}
// 복습 강의 보기 
function openReviewLec(leccode, chasi){
	var urlname = CPATH + "/review/studyroom.do?extleccode="+leccode+"&chpcode="+chasi;
	var oWin = window.open(urlname,
		"LectureWindow",
		"top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=no,width=820,height=694");
	
	windows_focus(oWin);
}

// Sample 강의 보기
function openLecSample(filename){
	var urlname = CPATH + "/studyroom/StudyRoomSample.jsp?file="+filename;
	var oWin = window.open(urlname,
		"SampleLectureWindow",
		"top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=no,width=820,height=694");
	windows_focus(oWin);
}

function ZipCodeSeach(zipcode_name, address_name, form_name) {
	var urlname = CPATH + "/etc/Zipcode.do?post="+zipcode_name+"&addr="+address_name+"&form="+form_name;
	var oWin = window.open(urlname,
		"zipcodewin",
		"top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=no,width=100,height=100");
	windows_focus(oWin);
}

function loginRequest(){
	alert("로그인을 해주세요");
}

function showHelp(help_file) {
	var oWin = window.open(CPATH+'/help/'+help_file,
	'help',
	'width=460,height=610,status=yes,menubars=no,scrollbars=no,top=0px,left=0px');
	windows_focus(oWin);
}

function CloseSiteMap() {
	var oSiteMap = document.getElementById("divSiteMap");
	if(oSiteMap) {
		oSiteMap.style.display = "none";
	}
}

function OpenSiteMap(path) {
	var oSiteMap = document.getElementById("divSiteMap");
	var odivMenu = document.getElementById("divMenu");
	if(oSiteMap) {
		oSiteMap.innerHTML="<iframe src='"+path+"/etc/SiteMap.do' name='ifSiteMap' id='ifSiteMap' width='220' height='400' scrolling='no' frameborder='1'></iframe>";
		oSiteMap.style.display = "";
		oSiteMap.style.top = element_top(odivMenu);
		oSiteMap.style.left = element_left(odivMenu)-oSiteMap.offsetWidth;
//		oSiteMap.style.left = 922-220;
	}
}
function ClosePopupDiv() {
	var oSiteMap = document.getElementById("divPopup");
	if(oSiteMap) {
		oSiteMap.style.display = "none";
	}
}

// Select box 에서 선택시 해당 Url로 이동 
function KMM_jumpMenu(frmName, selObj,restore){ 
	var frm = getObject(frmName);
	if(frm) {
		var url = selObj.options[selObj.selectedIndex].value;
		if(url != "") {
			frm.action = url;
			frm.submit();
		}
		
	}
  if (restore) selObj.selectedIndex=0;
}

var firstListOpen = false;
function showMyLecture(tobj){
	var o = document.getElementById("divMyLecture");
	var obj = document.getElementById("divMyLecture");
	if(o) {
		var ch = o.style.visibility;
		if (ch=='hidden') {
			if(!firstListOpen){
				obj.innerHTML="<iframe src='"+CPATH+"/mypage/ShowMyLecture.do' name='ifMyLecture' id='ifMyLecture' width='150' height='200' frameborder='1' ></iframe>";
				firstListOpen = true;
				o.style.top = element_top(tobj)+tobj.offsetHeight;
				o.style.left = element_left(tobj);
			}
			o.style.visibility = "visible";
			document.onmousemove = closeMyLecture;
		} else {
			o.style.visibility = "hidden";
			document.onmousemove = null;
		}
	}
}

function closeMyLecture(){
	var SelectedObj = event.srcElement; 
	
	if (SelectedObj.id.indexOf("mylecture_select") == -1) {
		var oLecture = document.getElementById("divMyLecture");
		if(oLecture) {
				oLecture.style.visibility = "hidden";
				document.onmousemove = null;
		}
	}
}
//-->
