var form;
var crs_cd_val;
var lec_cd_val;

function forminit(select_form, crs_cd_param, lec_cd_param)
{
	crs_cd_val = crs_cd_param;
	lec_cd_val = lec_cd_param;
	form = select_form;
	set_crs_cd();
	set_sjt_cd();
	set_lec_cd();
}

function change_crs_cd()
{
	set_sjt_cd();
}

function change_sjt_cd()
{
	set_lec_cd();
}

function set_crs_cd()
{
	var i = 0;
	form.crs_cd.length=0;
    
	for(j=0 ; j<COUNT ; j++){
	    if(j==0) form.crs_cd.options[i++]=new Option("--과정선택--","");
		if(!chk_crs_cd(INFO_1[j]) ){   
			form.crs_cd.options[i++]=new Option("["+INFO_1[j]+"] "+INFO_2[j], INFO_1[j]);  // name, code
			if(INFO_1[j]==crs_cd_val) {
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
	var select_crs_cd = form.crs_cd[form.crs_cd.selectedIndex].value; 
	
	for(j=0 ; j < COUNT ; j++) {	    
	    if(j==0) form.sjt_cd.options[i++]=new Option("--과목선택--","");
		if( (INFO_1[j] == select_crs_cd) && !chk_sjt_cd(INFO_3[j]) ) {
			form.sjt_cd.options[i++]=new Option("["+INFO_3[j]+"] "+INFO_4[j], INFO_3[j]);  // name, code
			if(INFO_3[j]==sjt_cd_val) {
			    form.sjt_cd.selectedIndex=(i-1);
		    }
		}
	}
	if(form.sjt_cd.selectedIndex < 0)
	    form.sjt_cd.selectedIndex=0;
}

function set_lec_cd()
{
	var i = 0;
	var select_crs_cd = form.crs_cd[form.crs_cd.selectedIndex].value;
	var select_sjt_cd = form.sjt_cd[form.sjt_cd.selectedIndex].value;
    form.lec_cd.length=0;
	
	for(j=0 ; j < COUNT ; j++){
	    if(j==0) form.lec_cd.options[i++]=new Option("--강의선택--","");
		if( (INFO_1[j] == select_crs_cd) && (INFO_3[j] == select_sjt_cd) && !chk_lec_cd(INFO_5[j]) ) {   // name, code
			form.lec_cd.options[i++]=new Option("["+INFO_5[j]+"] "+INFO_6[j], INFO_5[j]);  // name, code
			if(INFO_5[j]==lec_cd_val) {
			    form.lec_cd.selectedIndex=(i-1);
		    }
		}
	}
	if(form.lec_cd.selectedIndex < 1)
	   form.lec_cd.selectedIndex=0;
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
	for(i = 0; i < form.sjt_cd.length; i++)
	{
		if( form.sjt_cd[i].value == arg )
			return true;
	}
	return false;
}

function chk_lec_cd(arg)
{
	for(i = 0; i < form.lec_cd.length; i++)
	{
		if( form.lec_cd[i].value == arg )
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