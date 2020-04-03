/**
 * 화면에 alert 형태로 뿌려줄 메시지를 저장하는 배열.
 * alert은 단방향의 공지형 메시지이므로 서술형으로 작성한다.
 *
 * (필요하신 분은 아래쪽에 새로운 메시지를 추가해주시기 바랍니다.
 * 
 */
var alertMsg = new Array();
alertMsg['com_no_result'] = '조회결과가 없습니다.';
alertMsg['com_success_delete'] = '선택하신 정보가 성공적으로 삭제되었습니다.';
alertMsg['com_success_save'] = '입력하신 정보가 성공적으로 저장되었습니다.';

/**
 * showAlert('com_no_result')와 같은 형태로 호출.
 */
function showAlert(msg_id) {
	if (alertMsg[msg_id]) {
		alert(alertMsg[msg_id]);
	}
}

/**
 * 화면에 confirm 형태로 뿌려줄 메시지를 저장하는 배열.
 * confirm은 yes/no를 묻는 형태이므로 질문형으로 작성한다.
 * 
 * (필요하신 분은 아래쪽에 새로운 메시지를 추가해주시기 바랍니다.
 * 
 */
var confirmMsg = new Array();
confirmMsg['com_confirm_new'] = '작업중인 정보는 모두 손실됩니다. 초기화하시겠습니까?';
confirmMsg['com_confirm_delete'] = '선택하신 정보가 삭제되어 복구할 수 없게됩니다. 삭제하시겠습니까?';
confirmMsg['com_confirm_save'] = '입력하신 정보가 저장됩니다. 저장하시겠습니까?';

/**
 * 화면에 confirm 형태로 메시지를 띄우고, '확인'을 눌렀을 경우
 * 지정된 javascript 함수를 실행시켜주는 함수.
 * 함수명이 없을 경우 confirm 결과를 단순히 return하고, 함수명이 있을 경우
 * confirm 결과에 따라 해당 함수를 호출해준다.
 * showAlert('com_confirm_new', 'alert', '안녕하십니까')와 같은 형태로 호출.
 *
 * @param msg_id 		confirMsg 배열의 index
 * @param functionName	'확인'을 눌렀을 경우 실행하고자 하는 javascript 함수의 이름
 * @param args			functionName 함수에게 전달될 인수
 */
function showConfirm(msg_id, functionStr, args) {
	if (confirmMsg[msg_id]) {
		var ok = confirm(confirmMsg[msg_id]);
		if (ok && functionStr) {
			eval(functionStr)(args);
		} else {
			return ok;
		}
	}
}