/**
 * 자바스크립트 공통함수
 *
 * 주의: 아래의 모든 메소드는 입력폼의 필드이름(myform.myfield)을
 *       파라미터로 받는다. 필드의 값(myform.myfield.value)이 아님을
 *       유념할 것.
 */


/**
 * 입력값이 NULL인지 체크
 */
function isNull(input) {
    if (input.value == null || input.value == "") {
        return true;
    }
    return false;
}

/**
 * 입력값에 스페이스 이외의 의미있는 값이 있는지 체크
 */
function isEmpty(input) {
    if (input.value == null || input.value.replace(/ /gi,"") == "") {
        return true;
    }
    return false;
}

/**
 * 입력값을 모두 지운다
 */
function setEmpty() {
    var objEv = event.srcElement;
    objEv.value="";
}

/**
 * 입력값을 모두 소문자로 치환한다
 */
function setLowerCase() {
    var objEv = event.srcElement;
    objEv.value=objEv.value.toLowerCase();
}

/**
 * 입력값을 모두 대문자로 치환한다
 */
function setUpperCase() {
    var objEv = event.srcElement;
    objEv.value=objEv.value.toUpperCase();
}


/**
 * 입력값에 특정 문자(chars)가 있는지 체크
 * 특정 문자를 허용하지 않으려 할 때 사용
 * ex) if (containsChars(form.name,"!,*&^%$#@~;")) {
 *         alert("이름 필드에는 특수 문자를 사용할 수 없습니다.");
 *     }
 */
function containsChars(input,chars) {
    for (var inx = 0; inx < input.value.length; inx++) {
       if (chars.indexOf(input.value.charAt(inx)) != -1)
           return true;
    }
    return false;
}

/**
 * 입력값이 특정 문자(chars)만으로 되어있는지 체크
 * 특정 문자만 허용하려 할 때 사용
 * ex) if (!containsCharsOnly(form.blood,"ABO")) {
 *         alert("혈액형 필드에는 A,B,O 문자만 사용할 수 있습니다.");
 *     }
 */
function containsCharsOnly(input,chars) {
    for (var inx = 0; inx < input.value.length; inx++) {
       if (chars.indexOf(input.value.charAt(inx)) == -1)
           return false;
    }
    return true;
}

/**
 * 입력값이 알파벳인지 체크
 * 아래 isAlphabet() 부터 isNumComma()까지의 메소드가
 * 자주 쓰이는 경우에는 var chars 변수를 
 * global 변수로 선언하고 사용하도록 한다.
 * ex) var uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 *     var lowercase = "abcdefghijklmnopqrstuvwxyz"; 
 *     var number    = "0123456789";
 *     function isAlphaNum(input) {
 *         var chars = uppercase + lowercase + number;
 *         return containsCharsOnly(input,chars);
 *     }
 */
function isAlphabet(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값이 알파벳 대문자인지 체크
 */
function isUpperCase(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값이 알파벳 소문자인지 체크
 */
function isLowerCase(input) {
    var chars = "abcdefghijklmnopqrstuvwxyz";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값에 숫자만 있는지 체크
 */
function isNumber(input) {
    var chars = "0123456789";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값이 알파벳,숫자로 되어있는지 체크
 */
function isAlphaNum(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값이 알파벳,숫자, _  - 로 되어있는지 체크
 */
function isAlphaNumDash(input) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
    return containsCharsOnly(input,chars);
}


/**
 * 입력값이 숫자,대시(-)로 되어있는지 체크
 */
function isNumDash(input) {
    var chars = "-0123456789";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값이 숫자,콤마(,)로 되어있는지 체크
 */
function isNumComma(input) {
    var chars = ",0123456789";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값이 숫자,dot(.)로 되어있는지 체크
 */
function isNumDot(input) {
    var chars = ".0123456789";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값에서 콤마를 없앤다.
 */
function removeComma(input) {
    return input.value.replace(/,/gi,"");
}

/**
 * 입력값이 사용자가 정의한 포맷 형식인지 체크
 * 자세한 format 형식은 자바스크립트의 'regular expression'을 참조
 */
function isValidFormat(input,format) {
    if (input.value.search(format) != -1) {
        return true;
    }
    return false;
}

/**
 * 입력값이 이메일 형식인지 체크
 */
function isValidEmail(input) {
//    var format = /^(\S+)@(\S+)\.([A-Za-z]+)$/;
    var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
    return isValidFormat(input,format);
}

/**
 * 입력값이 전화번호 형식(숫자-숫자-숫자)인지 체크
 */
function isValidPhone(input) {
    var format = /^(\d+)-(\d+)-(\d+)$/;
    return isValidFormat(input,format);
}

/**
 * 선택된 라디오버튼이 있는지 체크
 */
function hasCheckedRadio(input) {
    if (input.length > 1) {
        for (var inx = 0; inx < input.length; inx++) {
            if (input[inx].checked) return true;
        }
    } else {
        if (input.checked) return true;
    }
    return false;
}

/**
 * 선택된 체크박스가 있는지 체크
 */
function hasCheckedBox(input) {
    return hasCheckedRadio(input);
}

/**
*
*/
var folderID = "";
var ie = document.layers?1:0;

function allSelect(frm)
{
	var trk=0;
	for (var i=0;i<frm.elements.length;i++)
	{
		var e = frm.elements[i];
		if(((e.name != 'all') && (e.name != 'allbox') && (e.type=='checkbox')))
		{
			trk++;
			e.checked = frm.allbox.checked;
		}
	}
}

/**
* Checkbox  all Selection, non-all Selection
* frm : form name,  obj : object name, allobj : all selection checkbox name
* <input type=checkbox name="allPagerBox" onClick="allSelect(this.form, 'pager_send_id', allPagerBox)">
* <input type=checkbox name="pager_send_id" value="user01"> 
*
*/
function allSelect(frm, obj, allobj)
{
	var trk=0;
	for (var i=0;i<frm.elements.length;i++)
	{
		var e = frm.elements[i];
		if(e.name != 'checkbox' && e.name == obj && e.disabled == false) {
		    e.checked = allobj.checked;
		}
	}
}

/**
 * 입력값의 바이트 길이를 리턴
 */
function getByteLength(input) {
    var byteLength = 0;
    for (var inx = 0; inx < input.value.length; inx++) {
        var oneChar = escape(input.value.charAt(inx));
        if ( oneChar.length == 1 ) {
            byteLength ++;
        } else if (oneChar.indexOf("%u") != -1) {
            byteLength += 2;
        } else if (oneChar.indexOf("%") != -1) {
            byteLength += oneChar.length/3;
        }
    }
    return byteLength;
}

/**
 * 입력값의 바이트 길이가 주어진 길이보다 크면 false를 리턴
 */
function checkLenth(input, len) {
    if(getByteLength(input) > len) {
        return false;
    } else true;
    
}

/**
 * 입력문자열(str1)에서  주어진 길이(str_len) 만큼만 남기고 나머지 문자는 삭제하여 리턴
 */
function getStrCut(str1, str_len) {
	var msglen = 0;
	var msgstr = "";
	for(k=0; k < str1.length && msglen < parseInt(str_len); k++) {
		t = str1.charAt(k);
		if (escape(t).length > 4) {
			if((msglen+2) <= parseInt(str_len)) msgstr += t;
			msglen += 2;
		} else {
			if((msglen+1) <= parseInt(str_len)) msgstr += t;
			msglen++;
		}
	}
	return msgstr;
}

/**
 * validation check Jumin-no
 */
function isValidJumin(input)
{
    if( !isNumber(input) )
        return false;

    var jumin_num = input.value;        
  	var tmp = 0
  	var yy  = jumin_num.substring(0,2)
  	var mm  = jumin_num.substring(2,4)
  	var dd  = jumin_num.substring(4,6)
  	var sex = jumin_num.substring(6,7)

  	if ((jumin_num.length != 13 ) || ( mm < 1 || mm > 12 || dd < 1) || (sex != 1 && sex !=2 && sex !=3 && sex !=4))
		return false;
  	
  	var idnum1, idnum2;
  	idnum1 = jumin_num.substring(0,6);
  	idnum2 = jumin_num.substring(6,13);

  	for (var i = 0; i <=5 ; i++)
		tmp = tmp + ((i%8+2) * parseInt(idnum1.substring(i,i+1)))
 
	for (var i = 6; i <=11 ; i++)
        tmp = tmp + ((i%8+2) * parseInt(idnum2.substring(i-6,i-5)))

	tmp = 11 - (tmp %11)
  	tmp = tmp % 10

  	if (tmp != idnum2.substring(6,7))
   		return false;
  	
  	return true;
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

/**
* return Object value
* 
*/
/*function getValue(object){
    if(object.length > 1)
        return object.options[object.selectedIndex].value;
    else
        return object.options.value;
}*/


 
/**
* 파일 확장자 체크 fileCheck(form.excel_file.value, '.xls#.cvs')
* file_type ".xls#.txt"
*/

function FileNameCheck(filename,fileext)
{
	words = fileext.split(",");
	allowSubmit = false;
    if (!filename) return;
    ext = filename.slice(filename.indexOf(".")).toLowerCase();
    for (var i = 0; i < words.length; i++) {
        if (words[i] == ext) {
            allowSubmit = true; 
            break; 
        }
    }
    if(allowSubmit) {
        return true;
    } else {
        return false;
    }    
    return false;
}


/**
* com.speedlearn_kp.HtmlUtil.java Class
*    public synchronized static String doPageShortCutForm(int page, int all_page, int position, String width, String form_name)
*<table border=0 cellpadding=0 cellspacing=0 width=100%>
*   <tr>
*       <td align=center height=35 width=100%> 
*           <font color=red><b>1</b></font>  
*           <a class="page" href="javascript:pageSubmit(pageform,2,2);">2</a>
*           <a class="page" href="javascript:pageSubmit(pageform,3,3);">3</a> 
*       </td>
*   </tr>
*</table>
*
* page navigation function
* 
*/
function pageSubmit(form, gopage, position) 
{
    form.page.value=gopage;
    form.position.value=position;
    form.submit()
}
//pageSubmit_one

function pageSubmit_one(form, gopage, position) 
{
    form.page.value=gopage;
    form.position.value=position;
    form.submit()
}

function download(goUrl){
    var dnwin;
    if(dnwin == null){
        dnwin = window.open(goUrl, "filedown", "resizable=no,width=1,height=1,top=-1,left=-1,location=no,scrollbars=no,menubar=no");
        dnwin.resizeTo(0,0);
    }
    //dnwin.close();
}

/**
 * 날짜 유효성 검사
 * 해당 년월일이 존재하면 true, 아니면 false리턴
 */
function chkDate(y, m, d) {
	var er = 0; // 에러 변수
	var daa = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

	if (y%1000 != 0 && y%4 == 0) daa[1] = 29; // 윤년
	if (d > daa[m-1] || d < 1) er = 1; // 날짜 체크
	if (m < 1 || m > 12) er = 1; // 월 체크
	if (m%1 != 0 || y%1 != 0 || d%1 != 0) er = 1; // 정수 체크
	if (er == 1) return false;
	else return true;
}

/**
 * 날짜 유효성 검사2
 * 해당 년월일이 존재하면 true, 아니면 false리턴
 */
function chkDateYYYYMMDD(yyyymmdd) {
	var y = yyyymmdd.substring(0, 4);
	var m = yyyymmdd.substring(4, 6);
	var d = yyyymmdd.substring(6, 8);

	var er = 0; // 에러 변수
	var daa = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

	if (y%1000 != 0 && y%4 == 0) daa[1] = 29; // 윤년
	if (d > daa[m-1] || d < 1) er = 1; // 날짜 체크
	if (m < 1 || m > 12) er = 1; // 월 체크
	if (m%1 != 0 || y%1 != 0 || d%1 != 0) er = 1; // 정수 체크
	if (er == 1) return false;
	else return true;
}
/**
 *  fckeditor 글자수 없는 경우 체크
 */
function fckeditorValidCheck(name, msg) {

var oEditor = FCKeditorAPI.GetInstance(name) ; // contents 는 fckeditor 생성시 id 
	if( oEditor ) 	{ 
		if( oEditor.GetXHTML() == "" ){
			alert(msg);
			oEditor.Focus();
			return false;
		}
	}
	return true;
}

/**
 * fckeditor 글자 수 제한
 */
 
function fckeditorLengthCheck(name, maxsize, msg) {

	var oEditor = FCKeditorAPI.GetInstance(name) ; // contents 는 fckeditor 생성시 id 
	if( oEditor ) 	{ 
		var div = document.createElement("DIV"); 		
		div.innerHTML = oEditor.GetXHTML(); 		
		var  contents = div.innerHTML; 	
		var charlength = 0;
		;
		for (var i = 0; i < contents.length; i++) {
				
			ch = escape(contents.charAt(i));
	
			if ( ch.length == 1 ) {
				charlength++;
			}else if (ch.indexOf("%u") != -1) {
				charlength += 2;
			}else if (ch.indexOf("%") != -1) {
				charlength += ch.length/3;
			}					
			
		}
		if(charlength > maxsize) {
			 alert(msg);
			 return false; 
		}
	
	} 			
	return true;
}


//콤마제거 주의점 반환되는 타입이 문자열이다.
function unformatNumber(s)
{
	var reg = new RegExp(",", "g"); 
	var i = 0;
	var len = 0;

	s =  s.replace(reg, "")
	len = s.length;
	while (s.charAt(i) == '0') i++;

	s =  s.substring(i,len);
	return (s == "") ? "0" : s;
}

//시간 
function displayTime(obj_hh, obj_min,  def_hh, def_min, classtype){
    document.writeln("<select name=\""+obj_hh+"\" class=\""+classtype+"\">");
        var timeformat = "";

        for(i=0; i<24; i++){
            if(i<10) timeformat = "0"+i;
            else timeformat = i;
            document.writeln("<option value=\""+timeformat+"\" "+ (timeformat == def_hh ? "selected":"") +">"+timeformat+"</option>");  
        }
        document.writeln("</select>:");
     document.writeln("<select name=\""+obj_min+"\" class=\""+classtype+"\">");
        for(i=0; i<60; i++){
            if(i<10) timeformat = "0"+i;
            else timeformat = i;
            document.writeln("<option value=\""+timeformat+"\" "+ (timeformat == def_min ? "selected":"") +">"+timeformat+"</option>");  
        }
        document.writeln("</select>");					         
    }	