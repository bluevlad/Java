/**
 *
 *	FileName	: checkbox_func.js
 * Comment	: üũ �ڽ��� ���õ� �Լ� ����
 * History		: 2004/06/03,	 ������.	v1.0
 *
 * @version	1.0
 */


 /*
  * Function Name	: noUse
  * Comment			: üũ���ο� ���� object �� disable ��Ų��.
  *	 Param obj	 		: üũ���ο� ���� disable �� object 
  * History				: 2004/06/03, ������. v1.0
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
 * Comment			: üũ�ڽ� ��ü�� �����ϰų�, ���� ����Ѵ�.
 *	 Param thisForm	: ������ form Object
 *	 Param checkName	 : ��ü ����, ���� ��� �� üũ�ڽ� �̸�
 *	 Param checkObj	: üũ���θ� Ȯ���� üũ�ڽ� Object
 *	 Param value		: üũ����
 * History				: 2004/06/03, ������. v1.0
 *
 * @version	1.0
 */
function commAllCheck(obj, checkObj, value) {
	if (value == null) value = obj.checked;
	
	if (checkObj != null)	{
		if (checkObj.length == null){	// 1���� �߰ߵ�
			checkObj.checked = value;
		}else{	// ������ �߰ߵ�
			for (i = 0; i < checkObj.length; i++){
				checkObj[i].checked = value;
			}
		}
	}
}

/*
 * Function Name	: commCountCheck
 * Comment			: checkName ���� üũ�ڽ��� �˻��ϰ�, �˻��� üũ�ڽ� �� ���õ� ���� ��ȯ
 *	 Param thisForm	: ������ form Object
 *	 Param checkName	 : ��ü ����, ���� ��� �� üũ�ڽ� �̸�
 *	 Param bcheck		: üũ����
 * History				: 2004/06/03, ������. v1.0
 *
 * @version	1.0
 */
function commCountCheck(thisForm, checkName, bcheck) {
    var check_value = (bcheck == null) ? true: bcheck;
    var count = 0;
	var col = thisForm.all.item (checkName);
	if (col == null) return -1;
	if (col.length == null) { // 1���� �߰ߵ�
		count = (col.checked == check_value) ? 1 : 0;
	}else{  // ������ �߰ߵ�
		for (i = 0; i < col.length; i++) count = (col[i].checked == check_value) ? count + 1 : count;
	}
	return count;
}
