/* ============================================================ */
/* 
 * CodeList를 가기고 오는 함수들의 모음
 * 업무에 dependency 하기 때문에 Srping의 cmp admin 업무에만 종속된다.
 * =============================================================
 * 순번			수정일자		수정자		내용
 * =============================================================
 * 1			20120813		이균			코드는 2depth로 설정이 되므로 level 주석처리
 * 2			20120813		이균			코드조회 uri를 CommonVars.jsp 에 지정하여 사용
 * 3			20120817		이균			selected, checked 적용 및 개발자선택 디폴터옵션 적용
 * =============================================================	
 */
 /* ============================================================ */

/**
 * <pre>
 *  json post 방식을 통한 ajax call 
 * </pre> 
 */
(function($) {

	$.ajaxCallCode = {
		_getCodeList : function(elementGubun, url, jsonObject, callback, exceptionCallback, completeCallback){
								var targetUrl = url || codeSelectUri;
							    $.ajax({
										url : targetUrl
									,	type : "post"
									,	dataType : "json"
									,	contentType : "application/json;charset=UTF-8"
									,	data : jsonObject
									, 	success : callback || ((elementGubun === 'selectbox') ? fnDefaultSuccessJsonHtmlHandler : ((elementGubun === 'radio') ? fnRadioSuccessJsonHtmlHandler : fnDefaultHandler))
									,	error : exceptionCallback || fnDefaultErrorJsonHtmlHandler
									, 	complete : completeCallback || function(){}
								});
							}
	};
	
	fnAjaxCodeListJsonPost = function(elementGubun, url, jsonObject, callback, exceptionCallback, completeCallback) {
		$.ajaxCallCode._getCodeList(elementGubun, url, jsonObject, callback, exceptionCallback, completeCallback);
	};
	
	
})(jQuery);

/**
 * <pre>
 *  성공핸들러 : json ajax를 통해 코드리스트를 획득하여 target selectbox의 options를 생성
 * </pre> 
 * @param {Object} data
 */
function fnDefaultSuccessJsonHtmlHandler (data) {
	var elementName = data.targetObjectName || "codeList"; 
	if (data.comMap.errorCode || data.comMap.errorMessage) {
		jAlert("에러코드=[" + data.comMap.errorCode + "]" + "<br/>" + "에러메시지=[" + data.comMap.errorMessage + "]", "에러");	
	}
	else {
		$('#' + elementName).html('');
		if (data.isDefaultOption === true) $('#' + elementName).append($('<option></option>').val('').html(data.totalOption || '-- 선택하세요 --'));
		$.each(data.codeInfoList, function(){
			if (data.selectedValue == this.codeId) {
				$('#' + elementName).append($('<option selected></option>').val(this.codeId).html(this.codeName));
			}
			else {
				$('#' + elementName).append($('<option></option>').val(this.codeId).html(this.codeName));
			}
		});
	}
}

/**
 * <pre>
 *  성공핸들러 : json ajax를 통해 코드리스트를 획득하여 target에 radio button 생성
 * </pre> 
 * @param {Object} data
 */
function fnRadioSuccessJsonHtmlHandler (data) {
	var elementName = data.targetObjectName || "radioList"; 
	if (data.comMap.errorCode || data.comMap.errorMessage) {
		jAlert("에러코드=[" + data.comMap.errorCode + "]\n에러메시지=[" + data.comMap.errorMessage + "]", "에러");
	}
	else {
		var radioName = elementName + "_radio";
		
		$('#' + elementName).html('');
		if (data.isDefaultOption === true) $('#' + elementName).append($("<input type='radio' name='" + radioName + "' id='" + radioName + "' value='' onChange=radioChange(this)>" + (data.totalOption || "전체") + "</input>"));
		$.each(data.codeInfoList, function(i){
			if (data.selectedValue == this.codeId) {
				$('#' + elementName).append($("<input type='radio' class='radio' checked name='" + radioName + "' id='" + radioName + "' onChange=radioChange(this) >" + this.codeName + "&nbsp;&nbsp;" + "</input>" ).val(this.codeId));
			}
			else {
				$('#' + elementName).append($("<input type='radio' class='radio' name='" + radioName + "' id='" + radioName + "' onChange=radioChange(this) >" + this.codeName  + "&nbsp;&nbsp;" + "</input>").val(this.codeId));
			}
		
		});
		
	}
}

/**
 * <pre>
 * selectbox의 onChange 이벤드시 적용되는 함수
 * 하위 레벨의 코드리스트를 가져온다.
 * </pre> 
 * @param {Object} element
 * @param {Object} targetObjectName
 * @param {Object} isDefaultOption
 * @param {Object} url
 * @param {Object} userFunc
 * @param {Object} isContinue
 */
function fnChangeSelect(element, targetObjectName,  isDefaultOption, url, userFunc, isContinue, totalOption, selectedValue){
	//------------------------------------------------------------------------------
	// codeList 획득 및 options 생성 ajax json post call
	//------------------------------------------------------------------------------
	if (userFunc) {
		$.one(userFunc);
	}
	if (isContinue || true) {
		if (targetObjectName && $("#" + element.id).val().toString()) {
			fnAjaxCodeListJsonPost(    'selectbox'
													, url || codeSelectUri
													, JSON.stringify({  "codeId" 					: $("#" + element.id).val().toString()
																			,	"isDefaultOption"		: (isDefaultOption === false) ? false : true
																			,	"totalOption"			: totalOption
																			,	"selectedValue"			: selectedValue
																			,	"targetObjectName" : targetObjectName}));
		}
	}	
}

/**
 * <pre>
 * 개발자가 override해야할 함수
 * 디폴트는 빈처리
 * </pre> 
 */
function radioChange(element){}

/**
 * <pre>
 * 디폴트 핸들러
 * </pre> 
 */
function fnDefaultHandler (data) {}


/**
 * <pre>
 * 에러핸들러 : json post call 시 에러처리
 * </pre> 
 * @param {Object} data
 */
function fnDefaultErrorJsonHtmlHandler (xhr, ajaxOptions, thrownError) {
	var defaultAjaxErrorMessage = "일시적인 시스템장애로 요청하신 페이지에 접근하실 수 없습니다.<br/>관련 문의사항은 담당자에게 문의해 주시기 바랍니다.";
	jAlert(defaultAjaxErrorMessage);
}

