var form;
var dept_cd_val;
var usn_val;


function forminit(select_form,dept_cd_param,usn_param)
{
	dept_cd_val = dept_cd_param;
	usn_val = usn_param;
	form = select_form;
	set_dept_cd();
	set_usn();
}

function change_dept_cd()
{
	set_usn();
}




function set_dept_cd()
{
	var i = 0;
	form.usn.length=0;
    
	for(j=0 ; j < COUNT ; j++){			
	    if(j==0) form.dept_cd.options[i++]=new Option("--부서선택--","");
		if( !chk_dept_cd(INFO_1[j]) ){
			//form.dept_cd.options[i++]=new Option("["+INFO_1[j]+"] "+INFO_2[j], INFO_1[j]);    // name, code
			form.dept_cd.options[i++]=new Option(INFO_2[j], INFO_1[j]);    // name, code
			if(INFO_1[j]==dept_cd_val) {
			    form.dept_cd.selectedIndex=(i-1);
		    }
		}
	}
	if(form.dept_cd.selectedIndex < 0)
	    form.dept_cd.selectedIndex=0;
}

function set_usn()
{
	var i = 0;
	var select_dept_cd = form.dept_cd[form.dept_cd.selectedIndex].value;
	form.usn.length=0;
	
    
	for(j=0 ; j < COUNT ; j++){
	    if(j==0)    form.usn.options[i++]=new Option("--부서원선택--","");
		if( (INFO_1[j] == select_dept_cd) && !chk_usn(INFO_3[j]) ){   
			//form.usn.options[i++]=new Option("["+INFO_3[j]+"] "+INFO_4[j], INFO_3[j]);  // name, code
			form.usn.options[i++]=new Option(INFO_4[j]+"["+INFO_5[j]+"]", INFO_3[j]);  // name, code
			if(INFO_3[j]==usn_val) {
			    form.usn.selectedIndex=(i-1);
		    }
		}
	}
	if(form.usn.selectedIndex < 0)
	    form.usn.selectedIndex=0;
}


function chk_dept_cd(arg)
{
	for(i = 0; i < form.dept_cd.length; i++)
	{
		if( form.dept_cd[i].value == arg )
			return true;
	}
	return false;
}

function chk_usn(arg)
{
	for(i = 0; i < form.usn.length; i++)
	{
		if( form.usn[i].value == arg )
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
