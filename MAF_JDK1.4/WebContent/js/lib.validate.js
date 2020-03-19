

var FldDefaultColor;
var FildBackColor;

var NO_BLANK = "{name} is required!";
var NOT_VALID = "{name} is invalid format";
var VALIDATELOADED = true;


String.prototype.trim = function(str) {
    str = this != window ? this : str;
    return str.replace(/^\s+/g,'').replace(/\s+$/g,'');
}

String.prototype.hasFinalConsonant = function(str) {
    str = this != window ? this : str;
    var strTemp = str.substr(str.length-1);
    return ((strTemp.charCodeAt(0)-16)%28!=0);
}

String.prototype.bytes = function(str) {
    str = this != window ? this : str;
	var len = 0; //bug. 
    for(j=0; j<str.length; j++) {
        var chr = str.charAt(j);
        len += (chr.charCodeAt() > 128) ? 2 : 1
    }
    return len;
}



function validate(mfrm) {
	var len =0;
	len = mfrm.elements.length;

	for (i = 0; i < len ; i++ ) {
        var el = mfrm.elements[i];
        if (el.tagName == "FIELDSET") continue;

      
        try{
        	el.value = el.value.trim();
		} catch(NS_ERROR_DOM_SECURITY_ERR) {}

        var minbyte = el.getAttribute("MINBYTE");
        var maxbyte = el.getAttribute("MAXBYTE");
        var option = el.getAttribute("OPTION");
        var match = el.getAttribute("MATCH");
        var glue = el.getAttribute('GLUE');
        var PATTERN = el.getAttribute('PATTERN');

        if (el.getAttribute("REQUIRED") != null) {
            if (el.type.toLowerCase() == "radio" || el.type.toLowerCase() == "checkbox")
            {

                if(!chkRadio(mfrm,el)) return doError(el,NO_BLANK);
            }

			if (el.name != "keyword" || mfrm.elements["sel_type"].value != "0"){	
			
				if (el.value == null || el.value == "") {
					return doError(el,NO_BLANK);
				}
			}
			else
				mfrm.elements["keyword"].value = "";
        }

        if (PATTERN != null && el.value != "") {
            if (!PATTERN(el,pattern)) return false;
        }

        if (minbyte != null) {
            if (el.value.bytes() < parseInt(minbyte)) {
                return doError(el,"{name} is mininum "+minbyte+" bytes.");
            }
        }

        if (maxbyte != null && el.value != "") {
            if (el.value.bytes() > parseInt(maxbyte)) {
                return doError(el,"{name} over maximum( "+maxbyte+"),"+el.value.bytes());
            }
        }

//        if (match && (el.value != mfrm.elements[match].value)) return doError(el,"{name} is not same.");

        if (funcs[option] && option != null && el.value != "") {
            if (el.getAttribute('SPAN') != null) {
                var _value = new Array();
                for (span=0; span<el.getAttribute('SPAN');span++ ) {
                    _value[span] = mfrm.elements[i+span].value;
                }
                var value = _value.join(glue == null ? '' : glue);
                if (!funcs[option](el,value)) return false;
            } else {
                if (!funcs[option](el)) return false;
            }
        }


    }
    
    return true;
}

function josa(str,tail) {
    return (str.hasFinalConsonant()) ? tail.substring(0,1) : tail.substring(1,2);
}

function doError(el,type,action) {
    var pattern = /{([a-zA-Z0-9_]+)\+?}/;
    var name = (hname = el.getAttribute("HNAME")) ? hname : el.getAttribute("NAME");
    pattern.exec(type);
    var tail = (RegExp.$2) ? josa(eval(RegExp.$1),RegExp.$2) : "";

    var error = (herror = el.getAttribute("HERROR")) ? herror : type.replace(pattern,eval(RegExp.$1) + tail);

    alert(error);

    if(el.getAttribute("SELECT") != null) el.select();
    if(el.getAttribute("DELETE") != null) el.value = "";
    if(el.getAttribute("NOFOCUS") == null) el.focus();
    return false;
}
function chkRadio(mfrm,field){

	fieldname=mfrm[field.name];
	//fieldname = eval(mfrm.name+'.'+field.name);


	if(fieldname.isMultiLine == false) {
		if(fieldname.checked)	return true;
	} else {
	    for (j=0;j<fieldname.length;j++) {
	        if (fieldname[j].checked) return true
	    }
	}
    return false;
}
/// pattern mapping///
var funcs = new Array();
funcs['http'] = isValidHttp;
funcs['email'] = isValidEmail;
funcs['phone'] = isValidPhone;
funcs['userid'] = isValidUserid;
funcs['metaid'] = isValidMetaid;
funcs['number'] = isNumeric;
funcs['engonly'] = alphaOnly;
funcs['alphanum'] = alphaNum;
funcs['jumin'] = isValidJumin;
funcs['bizno'] = isValidBizNo;

/// pattern ///
function PATTERN(el,pattern) {
    pattern = eval("/"+pattern+"$/")
    return (pattern.test(el.value)) ? true : doError(el,"{name} is invalid format.");
}
function isValidHttp(el,value) {
    var value = value ? value : el.value;
    var pattern = /^http:\/\/[\.a-zA-Z0-9-]+\.[\.a-zA-Z0-9-]+\.[a-zA-Z]+[\.\/a-zA-Z0-9-]+$/;
    return (pattern.test(value)) ? true : doError(el,NOT_VALID);
}
function isValidEmail(el,value) {
    var value = value ? value : el.value;
    var pattern = /^[_a-zA-Z0-9-\.]+@[\.a-zA-Z0-9-]+\.[a-zA-Z]+$/;
    return (pattern.test(value)) ? true : doError(el,NOT_VALID);
}

function isValidUserid(el) {
    var pattern = /^[a-zA-Z]{1}[a-zA-Z0-9_]{4,19}$/;
    return (pattern.test(el.value)) ? true : doError(el,"{name} is 5~ 20 bytes,\n alpahbet, number, _ character only");
}

function isValidMetaid(el) {
    var pattern = /[a-zA-Z0-9_]{5,20}$/;
    return (pattern.test(el.value)) ? true : doError(el,"{name} is 5~ 20 bytes,\n alpahbet, number, _ character only");
}



function alphaOnly(el) {
    var pattern = /^[a-zA-Z]+$/;
    return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}

function isNumeric(el) {
    var pattern = /^[\.0-9]+$/;
    return (pattern.test(el.value)) ? true : doError(el,"{name} only has number");
}

function alphaNum(el) {
    var pattern = /^[a-zA-Z0-9]+$/;
    return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}

function isValidJumin(el,value) {
    var pattern = /^([0-9]{6})-?([0-9]{7})$/;
    var num = value ? value : el.value;
    if (!pattern.test(num)) return doError(el,NOT_VALID);
    num = RegExp.$1 + RegExp.$2;

    var sum = 0;
    var last = num.charCodeAt(12) - 0x30;
    var bases = "234567892345";
    for (var i=0; i<12; i++) {
        if (isNaN(num.substring(i,i+1))) return doError(el,NOT_VALID);
        sum += (num.charCodeAt(i) - 0x30) * (bases.charCodeAt(i) - 0x30);
    }
    var mod = sum % 11;
    return ((11 - mod) % 10 == last) ? true : doError(el,NOT_VALID);
}

function isValidBizNo(el, value) {
    var pattern = /([0-9]{3})-?([0-9]{2})-?([0-9]{5})/;
    var num = value ? value : el.value;
    if (!pattern.test(num)) return doError(el,NOT_VALID);
    num = RegExp.$1 + RegExp.$2 + RegExp.$3;
    var cVal = 0;
    for (var i=0; i<8; i++) {
        var cKeyNum = parseInt(((_tmp = i % 3) == 0) ? 1 : ( _tmp  == 1 ) ? 3 : 7);
        cVal += (parseFloat(num.substring(i,i+1)) * cKeyNum) % 10;
    }
    var li_temp = parseFloat(num.substring(i,i+1)) * 5 + '0';
    cVal += parseFloat(li_temp.substring(0,1)) + parseFloat(li_temp.substring(1,2));
    return (parseInt(num.substring(9,10)) == 10-(cVal % 10)%10) ? true : doError(el,NOT_VALID);
}

function isValidPhone(el,value) {
    var pattern = /^([0]{1}[0-9]{1,2})-?([1-9]{1}[0-9]{2,3})-?([0-9]{4})$/;
    var num = value ? value : el.value;
    if (pattern.exec(num)) {
        if(RegExp.$1 == "010" || RegExp.$1 == "011" || RegExp.$1 == "016" || RegExp.$1 == "017" || RegExp.$1 == "018" || RegExp.$1 == "019") {
            if (!el.getAttribute('SPAN')) el.value = RegExp.$1 + "-" + RegExp.$2 + "-" + RegExp.$3;
        }
        return true;
    } else {
        return doError(el,NOT_VALID);
    }
}


function chkLength(el, a){
    var maxbyte = el.getAttribute("MAXBYTE");
    id_value = eval(a);


	if (el.value != "") {
		if (el.value.bytes() > parseInt(maxbyte)) {
			id_value.innerHTML = el.value.bytes();
			return doError(el,"{name} over maximum . (MAX. "+maxbyte+" Bytes)");
		}
		else{
			id_value.innerHTML = el.value.bytes();
		}
	}
	else{
		id_value.innerHTML = "0";
	}
}

var init_true;

function ValidInitialized()
{
    init_true = true;

    for (var i = 0; i < document.forms.length; i++) {
        
        for (var j = 0; j < document.forms[i].elements.length; j++) {
           
            if (document.forms[i].elements[j].getAttribute("REQUIRED") != null) {
                if (document.forms[i].elements[j].getAttribute("NOCOLOR") == null) {
                    document.forms[i].elements[j].style.backgroundColor = FildBackColor ? FildBackColor : '#f4ecc6';
                    //document.forms[i].elements[j].className = "required";
                    //document.forms[i].elements[j].style.backgroundPosition = "top right";
                    //document.forms[i].elements[j].style.backgroundRepeat = "no-repeat";
                }
            }
			if (document.forms[i].elements[j].getAttribute("readonly") ) {
                if (document.forms[i].elements[j].getAttribute("NOCOLOR") == null) {
										document.forms[i].elements[j].style.color = '#909090';
										document.forms[i].elements[j].style.backgroundColor =  '#f0f0f0';
                    //document.forms[i].elements[j].className = "required";
                    //document.forms[i].elements[j].style.backgroundPosition = "top right";
                    //document.forms[i].elements[j].style.backgroundRepeat = "no-repeat";
                }
            }						
        }
    }
}
//function window::onload(){
//	Initialized();
//}
addEvent(window, 'load', ValidInitialized);
