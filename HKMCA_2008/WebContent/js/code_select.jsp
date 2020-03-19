<%@ page contentType="application/x-javascript; charset=euc-kr" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

var COUNT = ${fn:length(navigator.list)}; 
INFO_1 = new Array(COUNT);
INFO_2 = new Array(COUNT);
INFO_3 = new Array(COUNT);
INFO_4 = new Array(COUNT);
INFO_5 = new Array(COUNT);
INFO_6 = new Array(COUNT);


<c:forEach var="item" items="${navigator.list}" varStatus="status">

	INFO_1[${status.count-1}] = "${item.cat_cd}"; // 
	INFO_2[${status.count-1}] = "${item.cat_nm}"; // 
	INFO_3[${status.count-1}] = "${item.crs_cd}"; // 
	INFO_4[${status.count-1}] = "${item.crs_nm}"; // 
	INFO_5[${status.count-1}] = "${item.prtcode}"; // 
	INFO_6[${status.count-1}] = "${item.name}"; // 


</c:forEach>


var form;
function forminit(select_form)
{
alert('td');
	form = getObject(select_form);
	set_cat_cd();
	set_crs_cd();
	set_sjt_cd();
}

function change_cat_cd()
{
	set_crs_cd();
}

function change_crs_cd()
{
	set_sjt_cd();
}


function set_cat_cd()
{
	var i = 0;
	form.crs_cd.length=0;
    
	for(j=0 ; j < COUNT ; j++){			
	    if(j==0) form.cat_cd.options[i++]=new Option("--분야선택--","");
		if( !chk_cat_cd(INFO_1[j]) ){
			form.cat_cd.options[i++]=new Option("["+INFO_1[j]+"] "+INFO_2[j], INFO_1[j]);    // name, code
			if(INFO_1[j]=="${LISTOP.ht.cat_cd}") {
			    form.cat_cd.selectedIndex=(i-1);
		    }
		}
	}
	if(form.cat_cd.selectedIndex < 0)
	    form.cat_cd.selectedIndex=0;
}

function set_crs_cd()
{
	var i = 0;
	var select_cat_cd = form.cat_cd[form.cat_cd.selectedIndex].value;
	form.crs_cd.length=0;
	
    
	for(j=0 ; j < COUNT ; j++){
	    if(j==0)    form.crs_cd.options[i++]=new Option("--과정선택--","");
		if( (INFO_1[j] == select_cat_cd) && !chk_crs_cd(INFO_3[j]) ){   
			form.crs_cd.options[i++]=new Option("["+INFO_3[j]+"] "+INFO_4[j], INFO_3[j]);  // name, code
			if(INFO_3[j]=="${LISTOP.ht.crs_cd}") {
			    form.crs_cd.selectedIndex=(i-1);
		    }
		}
	}
	if(form.crs_cd.selectedIndex < 0)
	    form.crs_cd.selectedIndex=0;
	set_sjt_cd();
}

function set_sjt_cd()
{
	var i = 0;
	var select_cat_cd = form.cat_cd[form.cat_cd.selectedIndex].value; 
	var select_crs_cd = form.crs_cd[form.crs_cd.selectedIndex].value; 
	form.prtcode.length=0;
	
	
	for(j=0 ; j < COUNT ; j++) {	    
	    if(j==0) form.prtcode.options[i++]=new Option("--Product선택--","");
		if( (INFO_1[j] == select_cat_cd) && (INFO_3[j] == select_crs_cd) && !chk_sjt_cd(INFO_5[j]) ) {
			form.prtcode.options[i++]=new Option("["+INFO_5[j]+"] "+INFO_6[j], INFO_5[j]);  // name, code
			if(INFO_5[j]=="${LISTOP.ht.prtcode}") {
			    form.prtcode.selectedIndex=(i-1);
		    }
		}
	}
	if(form.prtcode.selectedIndex < 0)
	    form.prtcode.selectedIndex=0;
}



function chk_cat_cd(arg)
{
	for(i = 0; i < form.cat_cd.length; i++)
	{
		if( form.cat_cd[i].value == arg )
			return true;
	}
	return false;
}

function chk_crs_cd(arg)
{
	for(i = 0; i < form.crs_cd.length; i++)
	{
		if( form.crs_cd[i].value == arg )
			return true;
	}
	return false;
}

function chk_sjt_cd(arg)
{
	for(i = 0; i < form.prtcode.length; i++)
	{
		if( form.prtcode[i].value == arg )
			return true;
	}
	return false;
}

function chk_leccode(arg)
{
	for(i = 0; i < form.leccode.length; i++)
	{
		if( form.leccode[i].value == arg )
			return true;
	}
	return false;
}


function getLength(obj){
    if(obj ==  "undefined" || obj != "[object]"){
        return 0;
    }else if(toString(obj.length) == "[object]" && obj.length > 1){
        return obj.length;
    }else{
        return 1;
    }
}
