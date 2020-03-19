var form;
var cat_cd_val;
var crs_cd_val;


function forminit(select_form,cat_cd_param,crs_cd_param)
{
	cat_cd_val = cat_cd_param;
	crs_cd_val = crs_cd_param;
	form = select_form;
	set_cat_cd();
	set_crs_cd();
}

function change_cat_cd()
{
	set_crs_cd();
}




function set_cat_cd()
{
	var i = 0;
	form.crs_cd.length=0;
    
	for(j=0 ; j < COUNT ; j++){			
	    if(j==0) form.cat_cd.options[i++]=new Option("--분야선택--","");
		if( !chk_cat_cd(INFO_1[j]) ){
			form.cat_cd.options[i++]=new Option("["+INFO_1[j]+"] "+INFO_2[j], INFO_1[j]);    // name, code
			if(INFO_1[j]==cat_cd_val) {
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
			if(INFO_3[j]==crs_cd_val) {
			    form.crs_cd.selectedIndex=(i-1);
		    }
		}
	}
	if(form.crs_cd.selectedIndex < 0)
	    form.crs_cd.selectedIndex=0;
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




function getLength(obj){
    if(obj ==  "undefined" || obj != "[object]"){
        return 0;
    }else if(toString(obj.length) == "[object]" && obj.length > 1){
        return obj.length;
    }else{
        return 1;
    }
}
