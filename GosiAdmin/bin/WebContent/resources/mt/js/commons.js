/**
 * 대분류/소분류/중분류/과목 선택 함수 createGroup.jsp 파일 참조
 * @param selectId
 * @param depth
 * @param target
 * @returns {Boolean}
 */

var dateFickerImageUrl = '';

function setDateFickerImageUrl(url) {
	dateFickerImageUrl = url;
	
	
}

function getSubCodes(selectId, depth, target, url, kemsCode) {
	var parentCodeId = $("#"+selectId+" option:selected").val();
	
	if(parentCodeId == "" || parentCodeId == undefined) {
		return false;
	}
	
	switch(depth) {
		case 2 : 
			$("#courseClassCode").html('').append("<option value=\"\">중분류선택</option>");
			$("#lectureClassCode").html('').append("<option value=\"\">소분류선택</option>");
			$("#subjectCodeClass").html('').append("<option value=\"\">과목분류선택</option>");
			break;
		case 3 : 
			$("#lectureClassCode").html('').append("<option value=\"\">소분류선택</option>");
			$("#subjectCodeClass").html('').append("<option value=\"\">과목분류선택</option>");
			break;
		case 4 : 
			$("#subjectCodeClass").html('').append("<option value=\"\">과목분류선택</option>");
			break;
	}
	
	var _url = url + '?parentCodeId=' + parentCodeId + '&depth=' + (depth+1);
	if(depth == '4') _url = _url + "&kemsCode=" + kemsCode;
	
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			if(json.subCodes && json.subCodes.length > 0) {
				$(json.subCodes).each(function(index, eduCode){
					$("#"+target).append("<option value=\""+eduCode.eduCodeId+"\"> "+eduCode.eduCodeName +" </option>");
				});	
			}
		}
	});
}

function getLectureInfo(eduNo, target, url) {
	if(eduNo == "") {
		return false;
	}	
	var _url = url + '?eduNo=' + eduNo;
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			if(json.lectureInfoList && json.lectureInfoList.length > 0) {
				$(json.lectureInfoList).each(function(index, eduLectureInfo){
					$("#"+target).append("<option value=\""+eduLectureInfo.eduLectureInfoNo+"\"> "+eduLectureInfo.lectureName +" </option>");
				});	
			}
		}
	});	
}
function getSubjectInfo(eduNo, eduLectureInfoNo, target, url) {
	$("#"+target+ " option").empty();
	$("#"+target).html('').append("<option value=\"\">과목분류선택</option>");
	if(eduNo == ""||eduLectureInfoNo=="") {
		return false;
	}		
	var _url = url + '?eduNo=' + eduNo +'&eduLectureInfoNo='+eduLectureInfoNo;
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			if(json.subjectInfoList && json.subjectInfoList.length > 0) {
				$(json.subjectInfoList).each(function(index, eduSubjectInfo){
					$("#"+target).append("<option value=\""+eduSubjectInfo.eduSubjectInfoNo+"\"> "+eduSubjectInfo.subjectName +" </option>");
				});	
			}
		}
	});	
}
/**
 * 기간 설정 dateFicker start ~ end
 * @param startId
 * @param endId
 */
function initDateFicker2(start, end) {
	var receiptDates = $("#"+start+", #"+end).datepicker({
		changeMonth: true,
		numberOfMonths: 2,
		showOn: "button",
		buttonImageOnly: true,
		buttonImage: dateFickerImageUrl,
		onSelect: function( selectedDate ) {
			var option = this.id == start ? "minDate" : "maxDate";
			var instance = $(this).data("datepicker");
			var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			receiptDates.not(this).datepicker("option", option, date);
		}
	});
}

/**
 * 기산설정 dateFicker one
 * @param id
 */
function initDateFicker1(id) {
	var receiptDates = $("#"+id).datepicker({
		changeMonth: true,
		numberOfMonths: 2,
		showOn: "button",
		buttonImageOnly: true,
		buttonImage: dateFickerImageUrl
	});
}

/**
 * dateFicker one
 * @param id
 */
function initDatePicker1(id) {
	var receiptDates = $("#"+id).datepicker({
		changeMonth: true,
		numberOfMonths: 1,
		showOn: "button",
		buttonImageOnly: true,
		buttonImage: dateFickerImageUrl
	});
}

/**
 * 시간설정 timeFicker
 * @param start
 * @param end
 */
function initTimeFicker2(start, end) {
	var eduTimes = $("#"+start+", #"+end).timepicker({
		hours: {
	        starts: 0,
	        ends: 23
	    },
	    minutes: {
	        starts: 0,
	        ends: 55,
	        interval: 5
	    },
	    rows: 4,
	    showHours: true,
	    showMinutes: true
	});
}


/**
 * 기산설정 timeFicker on
 * @param id
 */
function initTimeFicker1(id) {
	var eduTimes = $("#"+id).timepicker({
		hours: {
	        starts: 0,
	        ends: 23
	    },
	    minutes: {
	        starts: 0,
	        ends: 55,
	        interval: 5
	    },
	    rows: 4,
	    showHours: true,
	    showMinutes: true
	});
}

/**
 * datickper yyyy-mm-dd 를 datetime 으로 변환하도록
 * @param str
 * @returns {Boolean}
 */
function getDateString(date, time) {
	if(time == null || time == "" || time == undefined) time = " 00:00:00";
	else time = " " + time+":00";
	return (date+time).replace(/\-/ig, '/').split('.')[0];
}


/**
 * 특수문자 확인
 * @param str
 * @returns {Boolean}
 */
function isValidStr(str) {
	var re = /[~!@\#$%<>^&*\()\-=+_\']/gi;
	
	if(re.test(str)) {
		return false;
	} else {
		return true;
	}
}

/**
 * form validator
 * @param id
 */
function formValidate(id) {
	var result = true;
	$("#"+id+" .must").each(function(){
		if($(this).attr("type") == "radio") {
			var value = $("[name='"+$(this).attr("name")+"']:checked").val();
			if(value == "" || value == undefined) {
				alert($(this).attr("title"));
				$(this).focus();
				result = false;
				return false;
			}
		} else {
			if(($(this).val() == "" || $(this).val() == undefined) && $(this).attr("disabled") == undefined) {
				alert($(this).attr("title"));
				$(this).focus();
				result = false;
				return false;
			}
		}
		
		
		if($(this).hasClass("number")) {
			if(!$.isNumeric($(this).val())) {
				alert($(this).attr("alt") + "항목은 숫자만 등록이 가능합니다.");
				$(this).focus();
				result = false;
				return false;
			}
		}
		
// 		if(!isValidStr($(this).val())) {
// 			alert($(this).attr("alt") + "에 특수문자는 입력이 불가능합니다.");
// 			$(this).focus();
// 			confirm = true;
// 			return false;
// 		}
	});
	
	return result;
}


function checkAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

//SMS 문자길이 체크
function chkStrLen(tg,maxLen){ 
	var curText; 
	var strLen; 
	var byteIs; 
	var lastByte; 
	var thisChar; 
	var escChar; 
	var curTotalMsg; 
	var okMsg; 

	curText = new String(tg.value); 
	strLen = curText.length; 
	byteIs = 0; 

	for(i=0; i<strLen; i++) { 
	thisChar = curText.charAt(i); 
	escChar = escape(thisChar); 

	                // ´,¨, ¸ : 2byte 임에도 브라우져에서 1byte로 계산 
	                if (thisChar == "´" || thisChar == "¨" || thisChar == "¸" || thisChar == "§" ){
	                         byteIs++; 
	                } 

	if (escChar.length > 4) { 
	byteIs += 2;  //특수문자 한글인 경우. 
	}else if(thisChar != '\r') {  //개행을 제외한 이외의 경우 
	byteIs += 1; 
	} 

	                if(byteIs > parseInt(maxLen)){ // 3페이지까지 
	                      alert('('+maxLen+'자)를 초과하실 수 없습니다.'); 
	                      thisText = curText.substring(0, i); 
	                      tg.value = thisText; 
	                      byteIs = lastByte; 
	                      break; 
	                } 

	                lastByte = byteIs; 
	} 

	curTotalMsg = Math.ceil(byteIs / maxLen); 
	curEndByte = curTotalMsg * maxLen; 

	        //parent.document.all.maxbyte.value = curEndByte; 
	        parent.document.all.byte.value = byteIs; 
	} 

/**
 * for form validation
 */
jQuery.extend(jQuery.validator.messages, {
	   required: "반드시 입력해야 합니다.",
	   remote: "수정 바랍니다.",
	   email: "이메일 주소를 올바로 입력하세요.",
	   url: "URL을 올바로 입력하세요.",
	   date: "날짜가 잘못 입력됐습니다.",
	   dateISO: "ISO 형식에 맞는 날짜로 입력하세요.",
	   number: "숫자만 입력하세요.",
	   digits: "숫자(digits)만 입력하세요.",
	   creditcard: "올바른 신용카드 번호를 입력하세요.",
	   equalTo: "값이 서로 다릅니다.",
	   accept: "승낙해 주세요.",
	   maxlength: jQuery.validator.format("{0}글자 이상은 입력할 수 없습니다."),
	   minlength: jQuery.validator.format("적어도 {0}글자는 입력해야 합니다."),
	   rangelength: jQuery.validator.format("{0}글자 이상 {1}글자 이하로 입력해 주세요."),
	   range: jQuery.validator.format("{0}에서 {1} 사이의 값을 입력하세요."),
	   max: jQuery.validator.format("{0} 이하로 입력해 주세요."),
	   min: jQuery.validator.format("{0} 이상으로 입력해 주세요.")
	});

