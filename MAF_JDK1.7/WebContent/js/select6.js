var form;

var cat_cd_val;
var crs_cd_val;
var prtcode_val;
var leccode_val;
var lec_year_val;
var lecnumb_val;

// 폼초기화
function forminit(select_form, cat_cd_param, crs_cd_param, prtcode_param, leccode_param, year_param, lecnumb_param)
{
    cat_cd_val = cat_cd_param;
    crs_cd_val = crs_cd_param;
    prtcode_val = prtcode_param;
    leccode_val = leccode_param;
    lec_year_val = year_param;
    lecnumb_val = lecnumb_param;

    form = select_form;
    set_cat_cd();
    set_crs_cd();
    set_sjt_cd();
    set_leccode();
    set_lec_year();
    set_lecnumb();
}

// 분야선택시 과정코드 선택박스 초기화
function change_cat_cd()
{
    set_crs_cd();
}

// 과정선택시 과목코드 선택박스 초기화
function change_crs_cd()
{
    set_sjt_cd();
}

// 과목선택시 강의코드 선택박스 초기화
function change_sjt_cd()
{
    set_leccode();
}

// 강의코드선택시 년도 선택박스 초기화
function change_leccode()
{
    set_lec_year();
}

// 년도선택시 차수 선택박스 초기화
function change_lec_year()
{
    set_lecnumb();
}

// 분야선택 초기화
function set_cat_cd()
{
    var i = 0;
    form.crs_cd.length=0;
    for(j=0 ; j < COUNT ; j++){
            
        if(j==0) form.cat_cd.options[i++]=new Option("--분야선택--","");
        if( !chk_object(INFO_1[j], form.cat_cd) ){
            form.cat_cd.options[i++]=new Option("["+INFO_1[j]+"] "+INFO_2[j], INFO_1[j]);    // name, code
            if(INFO_1[j]==cat_cd_val) {
                form.cat_cd.selectedIndex=(i-1);
            }
        }
    }
    if(form.cat_cd.selectedIndex < 0)
        form.cat_cd.selectedIndex=0;
    
    set_crs_cd();
    set_sjt_cd();
    set_leccode();
    set_lec_year();
    set_lecnumb();
}

// 과정선택 초기화
function set_crs_cd()
{
    var i = 0;
    var select_cat_cd = form.cat_cd[form.cat_cd.selectedIndex].value;
    form.crs_cd.length=0;
    
    for(j=0 ; j < COUNT ; j++){
        if(j==0) form.crs_cd.options[i++]=new Option("--과정선택--","");
        if( (INFO_1[j] == select_cat_cd) && !chk_object(INFO_3[j], form.crs_cd) ){   
            form.crs_cd.options[i++]=new Option("["+INFO_3[j]+"] "+INFO_4[j], INFO_3[j]);  // name, code
            if(INFO_3[j]==crs_cd_val) {
                form.crs_cd.selectedIndex=(i-1);
            }
        }
    }
    if(form.crs_cd.selectedIndex < 0)
        form.crs_cd.selectedIndex=0;
    set_sjt_cd();
    set_leccode();
    set_lec_year();
    set_lecnumb();
}

// 과목선택 초기화
function set_sjt_cd()
{
    var i = 0;
    var select_cat_cd = form.cat_cd[form.cat_cd.selectedIndex].value; 
    var select_crs_cd = form.crs_cd[form.crs_cd.selectedIndex].value; 
    form.prtcode.length=0;
    
    for(j=0 ; j < COUNT ; j++){
        if(j==0) form.prtcode.options[i++]=new Option("--Product선택--","");
        if( (INFO_1[j] == select_cat_cd) && (INFO_3[j] == select_crs_cd) && !chk_object(INFO_5[j], form.prtcode) ) {
            form.prtcode.options[i++]=new Option("["+INFO_5[j]+"] "+INFO_6[j], INFO_5[j]);  // name, code
            if(INFO_5[j]==prtcode_val) {
                form.prtcode.selectedIndex=(i-1);
            }
        }
    }
    if(form.prtcode.selectedIndex < 0)
        form.prtcode.selectedIndex=0;
    set_leccode();
    set_lec_year();
    set_lecnumb();
}

// 강의선택 초기화
function set_leccode()
{
    var i = 0;
    var select_prtcode = form.prtcode[form.prtcode.selectedIndex].value;
    form.leccode.length=0;
    
    for(j=0 ; j < COUNT ; j++){
        if(j==0) form.leccode.options[i++]=new Option("--Class선택--","");
        if( (INFO_5[j] == select_prtcode) && !chk_object(INFO_7[j], form.leccode) ) {   // name, code
            form.leccode.options[i++]=new Option("["+INFO_7[j]+"] "+INFO_8[j], INFO_7[j]);  // name, code
            if(INFO_7[j]==leccode_val) {
                form.leccode.selectedIndex=(i-1);
            }
        }
    }
    if(form.leccode.selectedIndex < 0)
        form.leccode.selectedIndex=0;
    set_lec_year();
    set_lecnumb();
}

// 년도선택 초기화
function set_lec_year()
{
    var i = 0;
    var select_leccode = form.leccode[form.leccode.selectedIndex].value;
    form.lec_year.length=0;
    
    for(j=0 ; j < COUNT ; j++){
        if(j==0) form.lec_year.options[i++]=new Option("--년도선택--","");
        if( (INFO_7[j] == select_leccode) && !chk_object(INFO_9[j], form.lec_year) ) {   // name, code
            form.lec_year.options[i++]=new Option(INFO_9[j]+"년도", INFO_9[j]);  // name, code
            if(INFO_9[j]==lec_year_val) {
                form.lec_year.selectedIndex=(i-1);
            }
        }
    }
    if(form.lec_year.selectedIndex < 0)
        form.lec_year.selectedIndex=0;
    set_lecnumb();
}

// 차수선택 초기화
function set_lecnumb()
{
    var i = 0;
    var select_leccode = form.leccode[form.leccode.selectedIndex].value;
    var select_lec_year = form.lec_year[form.lec_year.selectedIndex].value;
    form.lecnumb.length=0;
    
    for(j=0 ; j < COUNT ; j++){
        if(j==0) form.lecnumb.options[i++]=new Option("--차수--","");
        if( (INFO_7[j] == select_leccode) && (INFO_9[j] == select_lec_year) && !chk_object(INFO_10[j], form.lecnumb) ) {   // name, code
            form.lecnumb.options[i++]=new Option(INFO_10[j]+"차수", INFO_10[j]);  // name, code
            if(INFO_10[j]==lecnumb_val) {
                form.lecnumb.selectedIndex=(i-1);
            }
        }
    }
    if(form.lecnumb.selectedIndex < 0)
        form.lecnumb.selectedIndex=0;
}

//중복체크
function chk_object(args, obj)
{
    for(i = 0; i < obj.length; i++)
    {
        if( obj[i].value == args )
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