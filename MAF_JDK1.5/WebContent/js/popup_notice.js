<!--

var canAlert = true;

function noMore_getCookie(name)
{
    cookieName=name+"=";
    cookieAll=document.cookie;
    cookieAll=unescape(cookieAll);
    
    returnCookie=cookieAll.substring(cookieAll.indexOf(cookieName)+cookieName.length, cookieAll.length);
    
    return returnCookie;
}

function oneDay_getCookie(name)
{
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length )
	{
	    var y = (x+nameOfCookie.length);
	    if ( document.cookie.substring( x, y ) == nameOfCookie ) {
	        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
	            endOfCookie = document.cookie.length;
	        return unescape( document.cookie.substring( y, endOfCookie ) );
	    }
	    x = document.cookie.indexOf( " ", x ) + 1;
	    if ( x == 0 )
	    break;
	}
	return "";
}

function setCookie( name, value, expiredays ) 
{ 
	var todayDate = new Date(); 
	todayDate.setDate( todayDate.getDate() + expiredays ); 
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";" 
} 

function winFocus(oWin) {
		if (oWin == null) {
			if(canAlert) {
				canAlert = false;
				alert("팝업차단 기능으로 인해 팝업 페이지가 차단되었습니다.\n차단된 팝업창을 허용해 주십시오.");
			}
	  } else {	    
	    oWin.focus();
		}
}

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

function closeWin(cookieName) 
{ 
		var frm = getObject('frmChk');
		
		if (frm) {
	    if ( frm.nomore.checked ) {
	        setCookie(cookieName, "done" , 1); 
			}
		}
	  top.self.close(); 
} 

function closeDiv(cookieName) {
		var frm = getObject('frmChk');
		
		if (frm) {
	    if ( frm.nomore.checked ) {
	        setCookie(cookieName, "done" , 1); 
			}
		}
	  top.ClosePopupDiv(); 
}

-->