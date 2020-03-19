
var ViewErrorMsg = true;

// deplecated : use  Prototype 
//function $(s){
//    var elements = new Array();
//    for (var i = 0; i < arguments.length; i++) {
//        var element = arguments[i];
//        if (typeof element == 'string')
//            element = document.getElementById(element);
//        if (arguments.length == 1)
//            return element;
//        elements.push(element);
//    }
//    return elements;
//}

//function $(n,d) {
//	return getObject(n,d);
//}


function windows_focus(oWin) {
  if (oWin == null) {
		maf.alert("system.message","popup.fail");
  }
  if (oWin.opener == null) {
  	oWin.opener = window;
  }

  oWin.focus();
}

/************************
*
* formname의 checkname을 모두 toggle, allcheckname = akkcheck
* 사용법 1:
* allChekboxToggle(this, 'formName','usn_check','allCheckbox');
* 사용법 2: allcheck 발생원이 check박스의 경우
* allChekboxToggle(this, 'formName','usn_check');
*/
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

/**
	Checkbox나 select에서 선택 또는 check된 개수를 구한다.
*/
function getSelectedCount(formName, attId) {
    var frm = getObject(formName);
	var cnt=0;

	if(frm ) {
		for (var i=0; i<frm.elements.length;i++) {
			//alert(frm.elements[i].type + "," + frm.elements[i].name);
			if (frm.elements[i].name == attId && frm.elements[i].type == "checkbox" ) {
				if(frm.elements[i].checked == true) {
					cnt ++;
				}
			}
			if (frm.elements[i].name == attId && frm.elements[i].type == "selectbox" ) {
				if(frm.elements[i].selected == true) {
					cnt ++;
				}
			}
		}
	}
	return cnt;
}

/**
*
* Form의 select 의 모든 Options을 select 삭태로 바꿈 // multiselect 일경우 
*
*/
function selectOptionAll(formName, attId) {
	var frm = getObject(formName);
	var cnt=0;

	if(frm ) {
		var tSel = frm.elements[attId];
		if(tSel) {
			if (tSel.type == "select-multiple" ) {
				for(j=0;j < tSel.options.length  ; j++){
					tSel.options[j].selected = true;
				}
			}
		}
	}
}

/**
* selectbox 간의 option 이동 multiselect(select 에 multiple attribute 설정시) 도 지원 
* param :
*   frmName : 작업 대상 form Name
*   srcObjectName : 원본 Selectbox Name
*   tgtObjectName : 대상 Selectbox Name
* 	delSrc : 원본에서 가져온놈의 삭제 여부 (기본 원본값 지움 )
* exam : selectMove('testForm','src','tgt')
*		selectMove('testForm','src','tgt',false)
*/
function selectMove(frmName, srcObjectName, tgtObjectName, delSrc) {
	var frm = getObject(frmName);
	if(!frm) {
		if(ViewErrorMsg) alert( "form["  + frmName + "] not found in document");
		return false;
	}
	var srcOption = frm.elements[srcObjectName];
	if(!frm) {
		if(ViewErrorMsg) alert( "select ["  + frmName + "] not found in document");
		return false;
	}
	var tgtOption = frm.elements[tgtObjectName];
	if(!frm) {
		if(ViewErrorMsg) alert( "select ["  + frmName + "] not found in document");
		return false;
	}
	var chk=false;
	if( delSrc == null) {
		delSrc = true;
	}
	var hasCode = false;
	for(i=0;i < srcOption.options.length  ; i++){
        if(srcOption.options[i].selected == true){
        	for(j=0; j < tgtOption.options.length; j++ ) {
	        	if(srcOption.options[i].value == tgtOption.options[j].value) {
	        		hasCode = true;
	        		break;
	        	}
	        }
        	if(!hasCode) {
	            var text = srcOption.options[i].text;
	            var value = srcOption.options[i].value;
	            var option2 = new Option(text, value);
	            var sl = tgtOption.options.length;
	            tgtOption.options[sl] = option2;
				tgtOption.options[sl].selected = true;
				if(delSrc) {
		            srcOption.options[i] = null;
					i--
				}
				chk=true;
			}
        }
    }
}

/**
* selectbox 간의 option 이동 multiselect모두(select 에 multiple attribute 설정시) 도 지원 
* param :
*   frmName : 작업 대상 form Name
*   srcObjectName : 원본 Selectbox Name
*   tgtObjectName : 대상 Selectbox Name
* 	delSrc : 원본에서 가져온놈의 삭제 여부 (기본 원본값 지움 )
* exam : selectMove('testForm','src','tgt')
*		selectMove('testForm','src','tgt',false)
*/
function selectMoveAll(frmName, srcObjectName, tgtObjectName, delSrc) {
	var frm = getObject(frmName);
	if(!frm) {
		if(ViewErrorMsg) alert( "form["  + frmName + "] not found in document");
		return false;
	}
	var srcOption = frm.elements[srcObjectName];
	if(!frm) {
		if(ViewErrorMsg) alert( "select ["  + frmName + "] not found in document");
		return false;
	}
	var tgtOption = frm.elements[tgtObjectName];
	if(!frm) {
		if(ViewErrorMsg) alert( "select ["  + frmName + "] not found in document");
		return false;
	}
	var chk=false;
	if( delSrc == null) {
		delSrc = true;
	}
	var hasCode = false;
	for(i=0;i < srcOption.options.length  ; i++){
		hasCode = false;
        for(j=0; j < tgtOption.options.length; j++ ) {
        	if(srcOption.options[i].value == tgtOption.options[j].value) {
        		hasCode = true;
        		break;
        	}
        }
        if(!hasCode) {
            var text = srcOption.options[i].text;
            var value = srcOption.options[i].value;
            var option2 = new Option(text, value);
            var sl = tgtOption.options.length;
            tgtOption.options[sl] = option2;
			tgtOption.options[sl].selected = true;
			if(delSrc) {
	            srcOption.options[i] = null;
				i--
			}
			chk=true;
		}
    }
}	

function selectDel(frmName, tgtObjectName) {
	var frm = getObject(frmName);
	var srcOption ;
	if(frm) {
		srcOption = frm.elements[tgtObjectName];
	}
	if(srcOption) {
		for(i=0;i < srcOption.options.length  ; i++){
	        if(srcOption.options[i].selected == true){
	            srcOption.options[i] = null;
				i--
	        }
	    }
	}
}

function selectDelAll(frmName, tgtObjectName) {
	var frm = getObject(frmName);
	var srcOption ;
	if(frm) {
		srcOption = frm.elements[tgtObjectName];
	}
	if(srcOption) {
		srcOption.options.length=0;
	}
}

// 이미지의 원래크기 보여주기
function ShowBigImageOne(url) {
		var urlname = CPATH + "/etc/showimageone.jsp?url="+escape(url);
		browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=no,resizable=yes,menubar=no,scrollbars=yes,width=10,height=10");
		windows_focus(browsing_window);
}

/* 
* element 의 Top 과 Left 를 가져온가 
*/
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


function popupWindow(theURL, winName, scroll_yn) {
	if (!scroll_yn) {
		scroll_yn = "yes"
	}
  var oWin = window.open(theURL,winName,'width=10,height=10,scrollbars='+scroll_yn+',menubar=no,status=yes,titlebar=yes,toolbar=no,location=no,top=0,left=0,resizable=yes');
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

/**
*
* http://www.dustindiaz.com/getelementsbyclass/
* 
*/
function getElementsByClass(searchClass,node,tag) {
	var classElements = new Array();
	if ( node == null )
		node = document;
	if ( tag == null )
		tag = '*';
	var els = node.getElementsByTagName(tag);
	var elsLen = els.length;
	var pattern = new RegExp("(^|\\s)"+searchClass+"(\\s|$)");
	for (i = 0, j = 0; i < elsLen; i++) {
		if ( pattern.test(els[i].className) ) {
			classElements[j] = els[i];
			j++;
		}
	}
	return classElements;
}

// CheckBoX나 Radio에서 checked된 값을 가져온다. checkbox의 경우 첫번째 값
function getFormCheckedValue(frm, name) {
	for(var n=0; n < frm.elements.length; n ++ ) {
		if(frm.elements[n].name == name) {
			if(frm.elements[n].checked){
				return frm.elements[n].value;
			}
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
* 
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
* str1에서 cnt 만큰 잘라서 가져온다.
*/
function strLength(var1) {
	return var1.bytes();
}

function getCookie( name ) {
    var start = document.cookie.indexOf( name + "=" );
    var len = start + name.length + 1;
    if ( ( !start ) && ( name != document.cookie.substring( 0, name.length ) ) ) {
        return null;
    }
    if ( start == -1 ) return null;
    var end = document.cookie.indexOf( ';', len );
    if ( end == -1 ) end = document.cookie.length;
    return unescape( document.cookie.substring( len, end ) );
}

function setCookie( name, value, expires, path, domain, secure ) {
    var today = new Date();
    today.setTime( today.getTime() );
    if ( expires ) {
        expires = expires * 1000 * 60 * 60 * 24;
    }
    var expires_date = new Date( today.getTime() + (expires) );
    document.cookie = name+'='+escape( value ) +
        ( ( expires ) ? ';expires='+expires_date.toGMTString() : '' ) + //expires.toGMTString()
        ( ( path ) ? ';path=' + path : '' ) +
        ( ( domain ) ? ';domain=' + domain : '' ) +
        ( ( secure ) ? ';secure' : '' );
}

function deleteCookie( name, path, domain ) {
    if ( getCookie( name ) ) document.cookie = name + '=' +
            ( ( path ) ? ';path=' + path : '') +
            ( ( domain ) ? ';domain=' + domain : '' ) +
            ';expires=Thu, 01-Jan-1970 00:00:01 GMT';
}

function tr_over(obj){
	if (obj.tagName == "TR") {
		for(var i=0;i< obj.cells.length; i++) {
			var oTd = obj.cells.item(i);
			oTd.setAttribute("oldBackground", oTd.style.backgroundColor);
			oTd.style.backgroundColor = "#ebebeb";
		}
	}
}

function tr_out(obj) {
	if (obj.tagName == "TR") {
		for(var i=0;i< obj.cells.length; i++) {
			var oTd = obj.cells.item(i);
			oTd.style.backgroundColor = oTd.getAttribute("oldBackground");
		}
	}
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
	var printObj = document.all["contentArea"];
	var printHTML = "";
	//alert("base is " + document.location.pathname);
	if(typeof(printObj) != "undefined" && typeof(printWin.document.getElementById("printArea")) != "undefined")
	{
		printHTML = "<TABLE cellSpacing=0 cellPadding=0 width=572 border=0>";
		for(var i=0; i < printObj.rows.length; i++) // -2를 하는것은 마지막의 두줄은 프린트하지 않기 위함.
		{
			printHTML += "<tr>"+printObj.rows.item(i).innerHTML+"</tr>";
		}
		printHTML += "</TABLE>";
		printWin.document.getElementById("printArea").innerHTML = printHTML;
	} else {
		alert("type:" +typeof( printObj));
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

//쪽지창 띄우기
function open_msgbox() {
	 var oWin = window.open(CPATH+'/pager/default.do','msg_win','width=10,height=10,status=yes,menubars=no,scrollbars=yes,top=0px,left=0px');
	windows_focus(oWin);
}

function logout() {
	if (confirm("logout ?")) {

		document.location = CPATH + "/logout.do";
	}
}

// 강의 보기 
function openLec(leccode, chasi){
	var urlname = CPATH + "/course/studyroom.do?extleccode="+leccode+"&chpcode="+chasi;
	var oWin = window.open(urlname,
		"LectureWindow",
		"top=100px,left=100px,status=yes,resizable=yes,menubar=no,scrollbars=no,width=820,height=694");
	
	windows_focus(oWin);
}

/**
* 우편번호 찾기 
* Parameter
* formName : 받을 폼의 이름
* param : post=postName&post1=post1Name&post2=post2Name&addrDisp=addrDispName&addr1=addr1Name&addr2=addr2Name
*    - post = 우편번호(123-123)으로 받을 오브젝트이름
*    - post1Name = 우편번호앞자리 받을 오브젝트이름
*    - post2Name = 우편번호뒷자리 받을 오브젝트이름
*    - addrDispName = 주소 전체 받을 오브젝트이름
*    - addr1Name = 주소 앞부분 받을 오브젝트이름
*    - addr2Name = 주소 뒷부분 받을 오브젝트이름
* formName을 제외한 param은 특정한 것이 빠져도 오류는 없음
*/
function ZipCodeSeach(zipcode_name, address_name, form_name) {
	var urlname = CPATH + "/Zipcode.do?post="+zipcode_name+"&addr="+address_name+"&form="+form_name;
	var oWin = window.open(urlname,
		"zipcodewin",
		"top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=no,width=100,height=100");
	windows_focus(oWin);
}

function ZipCodeSeach(formName, param) {
	var urlname = CPATH + "/Zipcode.do?form="+formName+"&"+param;
	var oWin = window.open(urlname,
		"zipcodewin",
		"top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=no,width=100,height=100");
	windows_focus(oWin);
}

function UserInfo(cmd, formName) {
	var urlname = CPATH + "/Userid.do?cmd="+cmd+"&form="+formName;
	var oWin = window.open(urlname,
		"userwin",
		"top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=no,width=100,height=100");
	windows_focus(oWin);
}

function loginRequest(){
	alert("login required!!!");
}

function showHelp(help_file) {
	var oWin = window.open(CPATH+'/help/'+help_file,
	'help',
	'width=460,height=610,status=yes,menubars=no,scrollbars=no,top=0px,left=0px');
	windows_focus(oWin);
}

var oSiteMapShowInit = false;
function CloseSiteMap() {
	var oSiteMap = document.getElementById("divSiteMap");
	if(oSiteMap) {
		oSiteMap.style.visibility = "hidden";
	}
}

function OpenSiteMap(path) {
	var oSiteMap = document.getElementById("divSiteMap");
	var odivMenu = document.getElementById("divQuickLink");
	if(oSiteMap) {
	
		var ch = oSiteMap.style.visibility;
		
		if (ch!='visible') {
			if(! oSiteMapShowInit){
				oSiteMap.innerHTML="<iframe src='"+path+"/etc/sitemap/sitemap.jsp' name='ifSiteMap' id='ifSiteMap' width='220' height='400' scrolling='no' marginwidth='0' marginheight='0' style='border:1 solid #cccccc'></iframe>";
				oSiteMap.style.top = element_top(odivMenu);
				oSiteMap.style.left = element_left(odivMenu)-oSiteMap.offsetWidth;
				oSiteMapShowInit = true;
			}
			oSiteMap.style.visibility = "visible";
		} else {
			oSiteMap.style.visibility = "hidden";
		}
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

function escapeXml(str)
{
    return str.replace(/&nbsp;/g, " ").replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&amp;/g, "&");
}

function write_flash( ) {

	//filename, vWidth, vHeight, id
	a=write_flash.arguments
	filename = a[0];
	vWidth = a[1];
	vHeight = a[2];
	id = a[3];

	document.write("<OBJECT classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' ");
	document.write(" codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0' ");
	document.write(" WIDTH='" + vWidth + "' HEIGHT='" + vHeight +"' id='" + id + "' ALIGN=''>");
	document.write("<PARAM NAME=movie VALUE='" + filename + "'>");
	document.write("<PARAM NAME=quality VALUE=best><PARAM NAME=bgcolor VALUE=#FFFFFF>");
	for(i=4;i<(a.length-1);i+=2) {
		if(a[i] != null) {
			document.write("<PARAM NAME='" + a[i] + "' VALUE='" + a[i+1] + "'>" );
		}
	}	
	document.write("<EMBED src='" + filename + "' quality=best bgcolor='#FFFFFF' ");
	document.write(" WIDTH='" + vWidth + "' HEIGHT='" + vHeight + "' NAME='" + id + "' ALIGN='' ");
	for(i=4;i<(a.length-1);i+=2) {
		if(a[i] != null) {
			document.write(" " + a[i] +"='"+ a[i+1] + "' " );
		}
	}	
	document.write(" TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'></EMBED>");
	document.write("</OBJECT>");
}	

function write_player( ) {

	//filename, vWidth, vHeight, id
	a=write_player.arguments
	url = a[0];
	vWidth = a[1];
	vHeight = a[2];

	document.write("<object classid='clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95'");
	document.write(" codebase='http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701'");
	document.write(" id='MMPlayer' standby='Loading Microsoft Windows Media Player components...' type='application/x-oleobject'");
	document.write(" width='"+ vWidth +"' height='"+ vHeight +"'");
	document.write(" vspace='5' hspace='5' align='middle'>");
	document.write(" <param name='Autostart' value='True'>");
	document.write(" <param name='DefaultFrame' value='Slide'>");
	document.write(" <param name='FileName' value='" + url + "'>");
	document.write(" <param name='ShowControls' value='True'>");
	document.write(" <param name='ShowDisplay' value='False'>");
	document.write(" <param name='ShowStatusBar' value='False'>");
	document.write(" <param name='showPosition' value='False'>");
	document.write(" <param name='loop' value='1'>");
	document.write("</object>");
}	


function showSyllabus(leccode){
     var oWin = window.open(CPATH + "/etc/syllabus.do?leccode="+leccode, "syllabus", "width=450, height=500, toolbar=no, scrollbars=yes");
		windows_focus(oWin);
}

function frmSubmit(frmName) {
    var frm = getObject(frmName);
    if(frm) {
        if(VALIDATELOADED) {
            if( validate(frm) ) {
                frm.submit();
            }
        } else {
            frm.submit();
        }
    } else {
        alert ("form[" + frmName + "] is invalid");
    }
}

function frmSubmit(frm, cmd) 
{
	if (typeof frm == "string") {
		frm = getObject(frm);
	}
	if(cmd) {
		frm.cmd.value=cmd;
	}
    if(frm) {
    	if(typeof validate != "undefined") {
            if (validate(frm)) {
             	frm.submit();
          	}
         }  else {
         	frm.submit();
         }
    }
}

function frmReset(frmName) {
    var frm = getObject(frmName);
    if(frm) {
         frm.reset();
    }
}

/* 회원에 대한 개별정보를 보여준다.*/
function viewUser(home_url, usn){
    var oWin = window.open(CPATH+"/user/view_user.do?cmd=view&usn="+usn, "user", "width=10, height=10, scrollbars=yes, resizable=yes");
		windows_focus(oWin);
}

/* **********************************************
*  브라우저별 이벤트 처리
*  ex) 
*    addEvent(window, 'load', 'et_init'); // 이벤트 감시
*    addEvent(option, "dblclick", function(){removeSkill(this)});
*/
function addEvent(obj, evType, fn){
	if (obj.addEventListener) {
		obj.addEventListener(evType, fn, true);
		return true;
	} else if (obj.attachEvent) {
		var r = obj.attachEvent("on"+evType, fn);
		return r;
	} else {
		obj['on' + evType] = fn;
	}
}

function addLoadEvent(func) {
	addEvent(window, 'load', func);
}
	
	/**
	* 시험 시작 
	*/
	function eWlcTestGo(exmid, lec_num) {
	var urlname = CPATH + "/wlc.learner/doTest.do?exmid="+exmid+"&lec_num="+lec_num;
		browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=no,resizable=yes,menubar=no,scrollbars=no,width=10,height=10");
		windows_focus(browsing_window);
    }
    
	/**
	* 시험 시작 
	*/
	function eTestGo(exmid) {
	var urlname = CPATH + "/etest/doTest.do?exmid="+exmid;
		browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=no,resizable=yes,menubar=no,scrollbars=no,width=10,height=10");
		windows_focus(browsing_window);
    }
    
    function showSubjectInfo(sjt_code) {
    	var urlname = CPATH + "/common.do?cmd=getSubjectInfo&sjt_cd=" + sjt_code;
		browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=no,resizable=yes,menubar=no,scrollbars=yes,width=600,height=400");
		windows_focus(browsing_window);
    }
    /**
    * Home으로 이동 
    */
    function goHome() {
    	document.location=CPATH+"/";
    }
    
    /**
    * checkbox를 radio처럼 사용 
    * 
    */
     function checkboxToggle(obj) {
        var pv = obj.checked;

        if(obj.form ) {
            for (var i=0; i<obj.form.elements.length;i++) {
                if (obj.form.elements[i].type == "checkbox" && obj.form.elements[i].name == obj.name) {
                    obj.form.elements[i].checked = false;
                }
            }
         }
         obj.checked = pv;

    }

     /**
     * return Object length
     * 
     */
     function getLength(obj){
         if(obj ==  "undefined" || obj != "[object]"){
             return 0;
         }else if(toString(obj.length) == "[object]" && obj.length > 1){
             return obj.length;
         }else{
             return 1;
         }
     }

     function doPrdSearch(frm_id) {
         var urlname = CPATH + "/product/Prd.do?cmd=search&frm_id="+frm_id;
         var oWin = window.open(urlname,
             "setPrd",
             "top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=yes,width=100,height=100");
         windows_focus(oWin);
     }

     function doPrdSearch(frm_id, elm_id) {
         var urlname = CPATH + "/product/Prd.do?cmd=search&frm_id="+frm_id+"&elm_id="+elm_id;
         var oWin = window.open(urlname,
             "setPrd",
             "top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=yes,width=100,height=100");
         windows_focus(oWin);
     }     