/**
 *
 *	FileName	: checkbox_func.js
 * Comment	: 체크 박스와 관련된 함수 정의
 * History		: 2004/06/03,	 진영석.	v1.0
 *
 * @version	1.0
 */


 /*
  * Function Name	: noUse
  * Comment			: 체크여부에 따라 object 를 disable 시킨다.
  *	 Param obj	 		: 체크여부에 따라 disable 될 object 
  * History				: 2004/06/03, 진영석. v1.0
  *
  * @version	1.0
  */
function noUse(obj) 
{
	var checkobj = event.srcElement;
	var value = checkobj.checked;

    if(value) obj.chk="";
	else obj.chk="req";

	obj.disabled = value;
}

/*
 * Function Name	: commAllCheck
 * Comment			: 체크박스 전체를 선택하거나, 선택 취소한다.
 *	 Param thisForm	: 적용할 form Object
 *	 Param checkName	 : 전체 선택, 선택 취소 할 체크박스 이름
 *	 Param checkObj	: 체크여부를 확인할 체크박스 Object
 *	 Param value		: 체크여부
 * History				: 2004/06/03, 진영석. v1.0
 *
 * @version	1.0
 */
function commAllCheck(obj, checkObj, value) {
	if (value == null) value = obj.checked;
	
	if (checkObj != null)	{
		if (checkObj.length == null){	// 1개만 발견됨
			checkObj.checked = value;
		}else{	// 여러개 발견됨
			for (i = 0; i < checkObj.length; i++){
				checkObj[i].checked = value;
			}
		}
	}
}

/*
 * Function Name	: commCountCheck
 * Comment			: checkName 으로 체크박스를 검색하고, 검색된 체크박스 중 선택된 수만 반환
 *	 Param thisForm	: 적용할 form Object
 *	 Param checkName	 : 전체 선택, 선택 취소 할 체크박스 이름
 *	 Param bcheck		: 체크여부
 * History				: 2004/06/03, 진영석. v1.0
 *
 * @version	1.0
 */
function commCountCheck(thisForm, checkName, bcheck) {
    var check_value = (bcheck == null) ? true: bcheck;
    var count = 0;
	var col = thisForm.all.item (checkName);
	if (col == null) return -1;
	if (col.length == null) { // 1개만 발견됨
		count = (col.checked == check_value) ? 1 : 0;
	}else{  // 여러개 발견됨
		for (i = 0; i < col.length; i++) count = (col[i].checked == check_value) ? count + 1 : count;
	}
	return count;
}
