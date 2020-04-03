/*
 *  jAlert(msg, title, function) 
 *  	기본 메세지
 *  	- jAlert('삭제하시겠습니까?');
 *  	callback 처리
 *  	-jAlert('삭제하시겠습니까?', '' , function(){ alert('callback 처리') } );
 *  
 *  jConfirm(msg , title , function);
 *		jConfirm("삭제하시겠습니까?" , '' , function(result){
 *			if(result == true){
 *				alert("확인 버튼 이벤트");
 *			}else{
 *				return;		//취소 버튼 이벤트
 *			}
 *		}
 *	 );	
  */
(function($) {
	var $alert_dialog = $('<div id="alert_layer"></div>');
	$.alerts = {
		
		alert: function(message, title, callback) {
			
			if( title == null || title == '' ) title = '확 인';
			$.alerts._show(title, message, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, callback) {
			
			if( title == null || title == '' ) title = '확 인';
			$.alerts._show(title, message, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
				
		// Private methods
		_show: function(title, msg, type, callback) {
			//$alert_dialog.html('<div class="jAlertImg"><p style="margin:0 0 0 40px;">'+msg+'</p></div>');
			$alert_dialog.html('<table><tr><td width="40px"><div class="jAlertImg"/></td><td>'+msg+'</td></tr></table>');
			switch( type ) {
				case 'alert':
					$alert_dialog.dialog({
						height: 200,    // 창의 높이 설정. 기본 auto.
					    width: 400,    // 창의 넓이 설정. 기본 auto.
						autoOpen: false,
						buttons:     {         
							"확 인": function() { 
																$(this).dialog("close"); 
																callback(true);
															}         
						} ,
						modal: true,
						resizable: false,
						dialogClass: 'dialog-style',
						title : title
					});
					
					break;
				case 'confirm':
					
					$alert_dialog.dialog({     
						height: 200,    // 창의 높이 설정. 기본 auto.
					    width: 400,    // 창의 넓이 설정. 기본 auto.
						autoOpen: false,
						buttons:     {         
							"확 인": function() { 
																$(this).dialog("close");
																if( callback ) callback(true);
															},         
							"취 소": function() { 
																$(this).dialog("close");
																if( callback ) callback(false);
															}
						} ,
						modal: true,
						resizable: false,
						dialogClass: 'dialog-style',
						title : title 
					});
					
					break;
			}
//			$alert_dialog.dialog("option", "width", "auto");
//			$alert_dialog.dialog("option", "height", "auto"); 
			$alert_dialog.dialog("open");
		}
		
	},
	
	jAlert = function(message, title, callback) {
		$.alerts.alert(message, title, callback);
	};
	
	jConfirm = function(message, title, callback) {
		$.alerts.confirm(message, title, callback);
	};
	
	jShowProgress = function(){
		$("BODY").append('<div id="progressbar"><div class="jProgressbar"></div></div>');
		$('#progressbar').dialog({     
			modal: true,
			height:150,
			width:300,
			resizable: false,
			open:function(){
				jQuery(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").remove();		//타이틀바 없애기
			//	jQuery(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar-close").remove(); //닫기 버튼 없애기
			}
		});	
	};
	
	jHideProgress = function(){
		$('#progressbar').dialog("close");
		$("#progressbar").remove();
	};
	
})(jQuery);