var gDELIM = "`";
var gIsEvent = false 
var gDevelopmentMode = true;


function dmbAlert(aMsg) {
    if (gDevelopmentMode) {
       
        alert(aMsg);
    }
}


//================= << Utility Data Set, Get Handlers >>========================
//필드에 값을 세팅한다.
function setValue(fld, val) {
    switch (fld.type) {
        case "text" :
            fld.value = val;
        case "password" :
        case "textarea" :
        case "file" :
        case "hidden" :
            fld.value = val;
            break;
        case "select-one" :
            for (var idx=0; idx < fld.options.length; idx++) {
                if (fld.options[idx].value == val) {
                    fld.selectedIndex = idx;
                    break;
                }
            }
            break;
        case "select-multiple" :
            for (var idx = 0; idx < fld.options.length; idx++) {
                var isExist = true;
                if (isExist) {//update: setValue.hasValue(fld.options[idx].value)
                    fld.options[idx].selected = true;
                }
            }
            break;
        case "radio" :
        case "checkbox" :
            if (fld.value == val) {
                fld.checked = true;
            }
            break;
    }
}

//필드의 값을 얻어낸다.
function getValue(obj) {
    
    var fld = getField(obj);
    
    var myValue = null;
    switch (fld.type) {
        case "text" :
        case "password" :
        case "textarea" :
        case "file" :
        case "hidden" :
            myValue = fld.value;
            break;
        case "select-one" :
        	if (typeof(fld.options) == "undefined" || fld.selectedIndex == -1) {
        		return '';
        	}
            myValue = fld.options[fld.selectedIndex].value;
            break;
        case "select-multiple" :
        	if (typeof(fld.options) == "undefined") {
        		return '';
        	}
            myValue = new Array();
            for (var idx=0; idx<fld.options.length; idx++) {
                if (fld.options[idx].selected) {
                    myValue += gDELIM + fld.options[idx].value;
                }
            }
            break;
        case "radio" :  
			myValue = fld.value;
			break;
		case "checkbox" :
            if (fld.checked) {
                myValue = fld.value;
            }
            break;
    }

    return (myValue == null ? '' : myValue);
}

/**
 * 필드를 입력받아 포맷을 제거한 값을 돌려준다.
 * unformat() 함수를 그대로 이용하기 위해 원래 field의 값을 다른 변수에 복사하고,
 * 이 변수를 unformat() 함수에 넘긴다.
 * 
 * 
 */
function getValueOnly(obj) {
	if (getValue(getField(obj)) == null) return '';

    if (typeof(obj.mytype) != "undefined") {
        var fieldClone = new createFieldClone(obj);
        unformat(fieldClone);
        return fieldClone.value;
    } else {
        return obj.value;
    }
}

/**
 * getValueOnly() 함수에서 이용하는 함수.
 * 인수로 주어진 field의 속성을 갖는 다른 변수를 생성한다.
 *
 *
 */
function createFieldClone(obj) {
    this.type = obj.type;
    this.value = getValue(obj);
    this.mytype = obj.mytype;
	this.options = obj.options;
	this.selectedIndex = obj.selectedIndex;
	this.checked = obj.checked;
    return this;
}

//컴포넌트의 값을 리턴한다.
//컴포넌트의 타입이 select-multiple일 경우에는 값배열을 리턴한다.
//obj: event 객체이거나 폼 구성요소
function getField(obj) {
   
    if (typeof(obj.cancelBubble) != "undefined") {
        gIsEvent = true
        return obj.srcElement;
    }
    else {
        gIsEvent = false
        return obj
    }
}


//Checking for Leading Minus Sign
// general purpose function to see if a suspected numeric input
// is a positive or negative integer
function isInteger(inputVal) {
    inputStr = inputVal.toString()
    for (var i = 0; i < inputStr.length; i++) {
        var oneChar = inputStr.charAt(i)
        if (i == 0 && oneChar == "-") {
            continue
        }

        if (oneChar < "0" || oneChar > "9") {
            return false
        }
    }
    return true
}

/**
* 이메일 유효성체크
* 
*/
function isEmail(obj) {
   
    var fld = getField(obj);
    var re = /(\S+)@(\S+)\.(\S+)/;
    var inputVal = getValue(fld);

    if (inputVal == '') {
    return;
    }
    if (re.test(inputVal)) {
        return true;
    }
    doSelection(fld);
    dmbAlert("유효한 이메일 주소를 입력하십시오.\n입력예(kira@kira.or.kr)");
    return false;
}

/**
* 전화번호 유효성 체크
*
*
*/
function isTel(obj) {
    
    var fld = getField(obj);
    var input = getValue(fld);
    if (input == '') {
    return;
    }
    var re = /\b(\d{2,4})[\-](\d{3,4})[\-](\d{3,4})/;
    if (re.test(input)) {
        return true;
    }
    doSelection(fld);
    dmbAlert("전화번호 입력 오류입니다.\n입력예: 02-1111-1111");
    return false;
}

/**
* 
* 필수 입력항목 체
*
*/
function isEssential(obj) {
    
    var fld = getField(obj)
    var input = getValue(fld)
    input = trim(input)
    if (input != "") {
        return true
    }
    doSelection(fld)
    
    if (fld.desc != null && fld.desc != '') {
        dmbAlert(fld.desc + "는(은) 필수입력항목입니다.")
    } else {
        dmbAlert(fld.name + "는(은) 필수입력항목입니다.")
    }
    return false
}


//---------------------------------------------------------------------
//알파벳체크
//---------------------------------------------------------------------
function alphanum(name) {

  var ch = "\0";
  var flag = true;

  for (var i = 0, ch = name.charAt(i);
      (i <name.length) && (flag); ch = name.charAt(++i)) {
      if ((ch >= '0') && (ch <= '9')) 
           ;
      else if ((ch >= 'a') && (ch <= 'z')) 
           ;
      else if ((ch >= 'A') && (ch <= 'Z')) 
           ;
      else  
           flag = false;
  }
  return (flag);
}



function isDateFormat(obj) {
   
    var fld = getField(obj)
    var input = getValue(fld)

    if (input == '') {
        return;
    }

    //update: 13월도 입력이 통과됨
    var re = /^((19|20|99)\d{2})(1[0-2]|0[1-9])(0[1-9]|[12][0-9]|3[01])$/



    if (!re.test(input)) {
        doSelection(fld)
        dmbAlert("날짜 입력이 잘못되었습니다.\n입력예: 20031225");
        return false;
    }

    var year    = input.substring(0,4);
    var month    = input.substring(4,6);
    var day        = input.substring(6,8);

    var inMonths = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

    var isDay ;
    isDay     = (month !=2 ) ? inMonths[month-1] : ((year%4 == 0 && year%100 !=0 || year%400 ==0) ? 29:28);

    if(month > 12 || month < 1 || day < 1 || day > isDay)
    {
        doSelection(fld)
        dmbAlert("날짜 입력이 잘못되었습니다.\n입력예: 20031125");
        return false;
    }

    return true;
}


function isDateTimeFormat(obj) {
   
    var fld = getField(obj)
    var input = getValue(fld)    
    
    if( input =='' ) return true;    // 김경수.
    
    var re = /\b((19|20)\d{2})(1[0-2]|0[1-9])(0[1-9]|1[0-9]|2[0-9]|3[0-1])(0[0-9]|1[0-9]|2[0-3])(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])\b/

    if (!re.test(input)) {
        doSelection(fld)
        dmbAlert("날짜 입력이 잘못되었습니다.\n입력예: 20040101000000");
        return false;
    }

    var year    = input.substring(0,4);
    var month    = input.substring(4,6);
    var day        = input.substring(6,8);

    var inMonths = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

    var isDay ;
    isDay     = (month !=2 ) ? inMonths[month-1] : ((year%4 == 0 && year%100 !=0 || year%400 ==0) ? 29:28);

    if(month > 12 || month < 1 || day < 1 || day > isDay)
    {
        doSelection(fld)        
        dmbAlert("날짜 입력이 잘못되었습니다.\n입력예: 20040101000001");
        return false;
    }
    
    return true;
}


//========================<< 오직 숫자만 입력가능 >>================================
function isOnlyNumber(obj) {
    fld = getField(obj);
    var inputVal = getValue(fld);
    var inputStr = inputVal.toString();

    for (var i = 0; i < inputStr.length; i++) {
        var oneChar = inputStr.charAt(i);

        if (oneChar < "0" || oneChar > "9") {
            doSelection(fld);
            dmbAlert("숫자를 입력하십시오.");
            return false;
        }
    }
    return true;
}




// 영문과 숫자의 조합인지 확인 
function isNumberAndEnglish(obj) {
   
    var fld = getField(obj);
    var inputVal = getValue(fld);
    
    // 영문과 숫자만 존재하는지 확인하기 위한 정규표현식
    var re = /^[0-9A-Za-z]*$/;
    if (re.test(inputVal)) {
        return true;
    } else {
        doSelection(fld);
        dmbAlert("영문과 숫자만 입력가능합니다");
        return false;
    }
}

// 영문과 숫자의 조합인지 확인 
function isEssNumberAndEnglish(obj) {
    if (isEssential(obj)) {
        if (isNumberAndEnglish(obj)) {
            return true
        }
    }
    return false
}



// 영문과 숫자의 조합인지 확인 
function isEssSerialCode(obj) {
    if (isEssential(obj)) {
        if (isSerialCode(obj)) {
            return true
        }
    }
    return false
}



function commafy(fld) {
    var inputVal = getValue(fld);
    if (inputVal == null) {
    	return
    }
    
    var frontVal = getFront(inputVal, ".");
    var point = "."
    if (frontVal == null) {
        frontVal = inputVal
        point = ""
    }

    //(2004-04-10)
    for(var i=0; i<frontVal.length; i++){
        if(frontVal.length>1 && frontVal.substring(0,1)=="0"){
            frontVal = frontVal.substring(1,frontVal.length);
            i--;
        }
    }

    var endVal = getEnd(inputVal, ".");
    if (endVal == null) {
        endVal = ""
    }

    var re = /(-?\d+)(\d{3})/
    while (re.test(frontVal)) {
        frontVal = frontVal.replace(re, "$1,$2");
    }

    setValue(fld, frontVal + point + endVal);
}


function toUpper(fld) {
    var input = getValue(fld);
    if (input != null && input != '') {
	    setValue(fld, input.toUpperCase());
	}
}



// separate function to accommodate IE timing problem
function doSelection(fld) {
    try {
        fld.focus()
    }
    catch (ex1) {
    }
    finally {
        try {
            fld.select()
        }
        catch (ex2) {
        }
    }
}


//===================<< Utility String Handlers >>=============================>
function ltrim(inputStr) {
    var re = /(\s*)/
    var matchArray = re.exec(inputStr);
    return RegExp.rightContext
}

function rtrim(inputStr) {
    var re = /(\s*)$/
    var matchArray = re.exec(inputStr);
    return RegExp.leftContext
}

function trim(inputStr) {
    var newStr = ltrim(inputStr);
    newStr = rtrim(newStr);
    return newStr
}

// extract front part of string prior to searchString
function getFront(mainStr,searchStr){
	if (mainStr == null) {
		return null
	}
    foundOffset = mainStr.indexOf(searchStr)
    if (foundOffset == -1) {
        return null
    }
    return mainStr.substring(0,foundOffset)
}
// extract back end of string after searchString
function getEnd(mainStr,searchStr) {
	if (mainStr == null) {
		return null
	}
    foundOffset = mainStr.indexOf(searchStr)
    if (foundOffset == -1) {
        return null
    }
    return mainStr.substring(foundOffset+searchStr.length,mainStr.length)
}
// insert insertString immediately before searchString
function insertString(mainStr,searchStr,insertStr) {
    var front = getFront(mainStr,searchStr)
    var end = getEnd(mainStr,searchStr)
    if (front != null && end != null) {
        return front + insertStr + searchStr + end
    }
    return null
}
// remove deleteString
function deleteString(mainStr,deleteStr) {
    return replaceString(mainStr,deleteStr,"")
}
// replace searchString with replaceString
function replaceString(mainStr,searchStr,replaceStr) {
    var front = getFront(mainStr,searchStr)
    var end = getEnd(mainStr,searchStr)
    if (front != null && end != null) {
        return front + replaceStr + end
    }
    return null
}

//---------------------------------------------------------------------
//주민번호체크
//---------------------------------------------------------------------


function jumin_num_check(name1, name2) {

  var weight = 0;
  var check;

  weight += (name1.charAt(0) - '0') * 2;         
  weight += (name1.charAt(1) - '0') * 3;         
  weight += (name1.charAt(2) - '0') * 4;       
  weight += (name1.charAt(3) - '0') * 5;      
  weight += (name1.charAt(4) - '0') * 6;     
  weight += (name1.charAt(5) - '0') * 7;    
  weight += (name2.charAt(0) - '0') * 8;   
  weight += (name2.charAt(1) - '0') * 9;  
  weight += (name2.charAt(2) - '0') * 2; 
  weight += (name2.charAt(3) - '0') * 3;
  weight += (name2.charAt(4) - '0') * 4; 
  weight += (name2.charAt(5) - '0') * 5;
  
  check = (11 - weight % 11) % 10;

  return ((name2.charAt(6) - '0') == check ? 1 : 0);
}

//---------------------------------------------------------------------
// 외국인등록번호 유효성 체크
//---------------------------------------------------------------------
function checkFrnNum1(obj)
{
    
    var fld     = getField(obj)
    var input = getValue(fld)
    var re = /\b(\d{2})(1[0-2]|0?[1-9])(0?[1-9]|[12][0-9]|3[01])/

    if(re.test(input)) {
        comNext(fld).focus();
        return true;
    }
    else {
        doSelection(fld);
        dmbAlert("외국인등록번호를 확인해 주십시요.");
        return false;
    }

}

function checkFrnNum2(obj) {
   
    var para = getValue(comPrev(getField(obj))) + getValue(getField(obj));

    if(para.length != 13) {
        doSelection(getField(obj));
        dmbAlert("외국인등록번호를 확인해 주십시요.");
        return (false);
    }

    if((para.charAt(6) == "5") || (para.charAt(6) == "6")) {
        birthYear = "19";
    }
    else if((para.charAt(6) == "7") || (para.charAt(6) == "8")) {
        birthYear = "20";
    }
    else if((para.charAt(6) == "9") || (para.charAt(6) == "0")) {
        birthYear = "18";
    }
    else {
        doSelection(getField(obj));
        dmbAlert("외국인등록번호의 생년월일을 확인해 주십시요.");
        return (false);
    }

    birthYear += para.substr(0, 2);
    birthMonth = para.substr(2, 2) - 1;
    birthDate = para.substr(4, 2);
    birth = new Date(birthYear, birthMonth, birthDate);

    if(birth.getYear() % 100 != para.substr(0, 2)
        || birth.getMonth() != birthMonth
        || birth.getDate() != birthDate) {
        doSelection(getField(obj));
        dmbAlert("외국인등록번호의 생년월일을 확인해 주십시요.");
        return (false);
    }

    if(!checkFrnCheckSum(para)) {
        doSelection(getField(obj));
        dmbAlert("외국인등록번호를 확인해 주십시요.");
        return (false);
    }
}

function checkFrnCheckSum(para) {
    var sum = 0;
    var odd = 0;
    
    buf = new Array(13);
    for(i = 0; i < 13; i++) {
        buf[i] = parseInt(para.charAt(i));
    }

    odd = buf[7]*10 + buf[8];
    
    if(odd%2 != 0) {
      return (false);
    }

    if((buf[11] != 6)&&(buf[11] != 7)&&(buf[11] != 8)&&(buf[11] != 9)) {
      return (false);
    }
        
    multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
    for(i = 0, sum = 0; i < 12; i++) {
        sum += (buf[i] *= multipliers[i]);
    }

    sum=11-(sum%11);
    
    if(sum>=10) {
        sum-=10;
    }

    sum += 2;

    if(sum>=10) {
        sum-=10;
    }

    if( sum != buf[12]) {
        return (false);
    }
    else {
        return (true);
    }
}

//===============<< 폼 제어 >>========================
//특정 document에 있는 모든 폼에 대해서 reset을 수행한다.
function reset(aDocument) {
    var size = aDocument.forms.length;

    for (var i = 0; i < size; i++) {
        var eachForm = document.forms[i]
        eachForm.reset();
    }
}



//------------------------------------------------------------------------------
// Only Number Key Press 
//
// Using    : <input type="text" name="fldname" onkeypress="onlyNumKeyPress();">
//------------------------------------------------------------------------------
function onlyNumKeyPress() {
    if((event.keyCode < 48) || (event.keyCode > 57)) {
        event.returnValue = false;
    }
    return true;
}

//키보드의 숫자키가 입력되었는지를 판단한다.
//사용법: onKeyPress 이벤트에 걸린 자바스크립트에서 직접 혹은 간접적으로 사용할 것.
function isNumKeyPress() {
    return (event != null && (event.keyCode >= 48 && event.keyCode <= 57));
}

//키보드의 소문자가 눌렸는지를 판단한다.
//사용법: onKeyPress 이벤트에 걸린 자바스크립트에서 직접 혹은 간접적으로 사용할 것.
function isLowerAlphaKeyPress() {
    return (event != null && (event.keyCode >= 97 && event.keyCode <= 122));
}

//키보드의 대문자가 눌렸는지를 판단한다.
//사용법: onKeyPress 이벤트에 걸린 자바스크립트에서 직접 혹은 간접적으로 사용할 것.
function isUpperAlphaKeyPress() {
    return (event != null && (event.keyCode >= 65 && event.keyCode <= 90));
}

//키보드의 A~Z, a~z 중 하나가 눌렀는지를 판단한다.
//사용법: onKeyPress 이벤트에 걸린 자바스크립트에서 직접 혹은 간접적으로 사용할 것.
function isAlphaKeyPress() {
    if (isUpperAlphaKeyPress() ||  isUpperAlphaKeyPress()) {
        return true;
    }

    return false;
}

//키보드의 a~z, A~Z, 0~9 중 하나를 눌렀는지를 판단한다.
//사용법: onKeyPress 이벤트에 걸린 자바스크립트에서 직접 혹은 간접적으로 사용할 것.
function isAlphaNumKeyPress() {
    if (isAlphaKeyPress() || isNumKeyPress()) {
        return true;
    }

    return false;
}

//-----------------------------------------------------------------------------------

//***************************************************************
// 한글만 입력 받는다
// Using    : <input type="text" name="name" onkeypress="onlyWordPress();">

	function onlyWordPress() {
		if( (event.keyCode >= 48) && (event.keyCode <=57) || (event.keyCode >=65) && (event.keyCode <= 90) || (event.keyCode >= 97) && (event.keyCode <= 122) ){
			event.returnValue = false;
		}
		return true;
	}
