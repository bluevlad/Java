/* ============================================================ */
/* 
 * ajax 공통 함수 모음
 * ajax call의 방식에 따른 함수 구분
 * 전송방식(type) : post, get
 * 데이터방식(dataType) : json, html  
 * =============================================================
 * 순번			수정일자		수정자		내용
 * =============================================================
 * 1			20120720		이병환		최초작성
 * =============================================================	
 */
 /* ============================================================ */
(function($) {
	
	//defualt param (isNoFrame=true는 top와 left, footer 레이아웃을 사용하지 않기위함 )
	var layoutParam = 'ajaxFrame=true&';
	var defaultTargetAreaID = "changeDiv";
	var defaultAjaxErrorMessage = "일시적인 시스템장애로 요청하신 페이지에 접근하실 수 없습니다.<br/>관련 문의사항은 담당자에게 문의해 주시기 바랍니다.";
	var defaultContentType = "application/x-www-form-urlencoded;charset=UTF-8";
	var defaultAjaxErrorTitle = "에러";
	
	//----------------------------------------------------------------------------------
	// ajaxCall Object 생성
	//----------------------------------------------------------------------------------
	$.ajaxCall = {

		//----------------------------------------------------------------------------------
		// post + html 방식
		//----------------------------------------------------------------------------------	
		post: function(url, formName, targetAreaID, successHandler, errorHandler, completeHandler) {
			jShowProgress();
			if (!(targetAreaID == undefined || targetAreaID == null || targetAreaID == "")) {
				defaultTargetAreaID = targetAreaID;
			}
			
			var ajaxParam = layoutParam + $("#" + formName).serialize();
			
			$.ajaxCall._ajaxHtml(url, ajaxParam, defaultTargetAreaID, successHandler, errorHandler, completeHandler);
		},
				
		//----------------------------------------------------------------------------------
		// get + html 방식
		//----------------------------------------------------------------------------------	
		get : function(urlData, targetAreaID,successHandler) {
			jShowProgress();
			if (!(targetAreaID == undefined || targetAreaID == null || targetAreaID == "")) {
				defaultTargetAreaID = targetAreaID;
			}
			
			var idx = urlData.indexOf('?');
			var url = '';
			if(idx > 0){
				var ajaxParam = layoutParam +  urlData.substring(idx+1, urlData.length);		
				url = urlData.substring(0, idx);		
			}
			//else{
			//	jHideProgress();
			//	jAlert('파라미터가 없습니다.!!', defaultAjaxErrorTitle);
			//	return;
			//}
			
			//ajaxParam 인코딩 할것
			//ajaxParam = escape(encodeURIComponent(ajaxParam),false);
			//ajaxParam = encodeURIComponent(ajaxParam);
			
			$.ajaxCall._ajaxHtml(url, ajaxParam, defaultTargetAreaID,successHandler);
		},

		//----------------------------------------------------------------------------------
		// post + json 방식
		//----------------------------------------------------------------------------------	
		jsonPost: function(url, formName, callback, exceptionCallback,isNoProgressBar) {
			
			if(isNoProgressBar !=true){
				jShowProgress();
			}
									
			var ajaxParam = layoutParam + $("#" + formName).serialize();
			
			$.ajaxCall._ajaxJson(url, ajaxParam, callback, exceptionCallback,isNoProgressBar);
		},

		//----------------------------------------------------------------------------------
		// html 데이터방식의 실질 함수
		//----------------------------------------------------------------------------------	
		_ajaxHtml : function( url , data, targetAreaID, successHandler, errorHandler, completeHandler){
			$.ajax({
				type				:	"POST",
				url				:	url ,
				dataType		:	"html",
				contentType 	: 	defaultContentType,
				//data				:	data.replace(/%/g,'%25'),
				data				:	data,	
				success			:	successHandler || function(htmlString){
											jHideProgress();
											//----------------------------------------------------------------------------------
											// ajax html 방식은 에러시 에러페이지가 그대로 로딩됨
											//----------------------------------------------------------------------------------												
											$("#" + targetAreaID).empty().append(htmlString);
										},
				error				:	errorHandler || function(xhr, ajaxOptions, thrownError){
											jHideProgress();
											//jAlert(defaultAjaxErrorMessage + '\nxhr:'+xhr.responseTest + '\najaxOptions:'+ajaxOptions +'\nthrownError:'+thrownError, "에러" );
											jAlert(makeAjaxErrorMessage(defaultAjaxErrorMessage, xhr, ajaxOptions, thrownError), defaultAjaxErrorTitle);
										},
				complete		: completeHandler || function(){}
			});
		},
				
		//----------------------------------------------------------------------------------
		// json 데이터방식의 실질 함수
		//----------------------------------------------------------------------------------	
		_ajaxJson : function( url, data, callback, exceptionCallback ,isNoProgressBar ){
			
			$.ajax({
				type				:	"POST",
				url				:	url ,
				dataType		:	"json",
				contentType 	: 	defaultContentType,
				//data				:	data.replace(/%/g,'%25'),
				data				:	data,	
				success			:	function(result){
					//var parseData = eval('(' + result + ')');
					if(isNoProgressBar !=true){	
						jHideProgress();
					}
					
					if (!(callback == undefined || callback == "" || callback == null)) {
						//callback(result);
						window[callback](result);
					}else{
						//alert(result.page_Num);
					}
					
				},
				error				:	function(xhr, ajaxOptions, thrownError){
					if(isNoProgressBar !=true){	
						jHideProgress();
					}
					if (!(exceptionCallback == undefined || exceptionCallback == "" || exceptionCallback == null)) {
						window[exceptionCallback](result);
						//exceptionCallback(thrownError);
					}else{
						jAlert(makeAjaxErrorMessage(defaultAjaxErrorMessage, xhr, ajaxOptions, thrownError), defaultAjaxErrorTitle );
					}
				},
			});
		},
		
	},
	
	//----------------------------------------------------------------------------------
	// wrapper 함수들
	//----------------------------------------------------------------------------------	
	ajaxCallPost = function(url, formName, targetAreaID, successHandler, errorHandler, completeHandler) {
//		$.ajaxCall.jsonPost('/adminPoc/common/controller/session_ajax', formName, 'ajaxCallJsonPost_callback','' ,true);
		$.ajaxCall.post(url, formName, targetAreaID, successHandler, errorHandler, completeHandler);
	};
	
	ajaxCallJsonPost_callback = function(result) {
		if(!result) {
			location.href="/adminPoc/main/login/loginForm";
		}
	};
	
	ajaxCallGet = function(urlData, targetAreaID,successHandler) {
//		$.ajaxCall.jsonPost('/adminPoc/common/controller/session_ajax', formName, 'ajaxCallJsonPost_callback','' ,true);
		$.ajaxCall.get(urlData, targetAreaID,successHandler);
	};
	
	ajaxCallJsonPost = function(url, formName, callback, exceptionCallback) {
//		$.ajaxCall.jsonPost('/adminPoc/common/controller/session_ajax', formName, 'ajaxCallJsonPost_callback', exceptionCallback,'' ,true);
		$.ajaxCall.jsonPost(url, formName, callback, exceptionCallback);
	};
	
	//----------------------------------------------------------------------------------
	// private 함수들
	//----------------------------------------------------------------------------------
	function makeAjaxErrorMessage (defaultAjaxErrorMessage, xhr, ajaxOptions, thrownError){
		return defaultAjaxErrorMessage;
	}
	
	
})(jQuery);