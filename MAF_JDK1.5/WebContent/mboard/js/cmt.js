function chkLength(el){
    var maxbyte = el.getAttribute("MAXBYTE");
	var a = document.getElementById("strcnt");
	if (el.value != "strcnt") {
		if (el.value.bytes() > parseInt(maxbyte)) {
			a.innerHTML = el.value.bytes();
			return doError(el,"{name}의 길이가 초과되었습니다 (최대 "+maxbyte+"바이트)");
		}
		else{
			a.innerHTML = el.value.bytes();
		}
	}
	else{
		a.innerHTML = "0";
	}
}

function cmtWrite(){
	var vfrm = document.getElementById("frmCmt");
	if(validate(vfrm)){
		vfrm.cmd.value ="cmt_add";
		vfrm.action ="act.do";
		vfrm.submit();
	}
}
function cmtDelete(c_id) {
	var vfrm = document.getElementById("frmCmt");
	if(confirm('이 커멘트를 삭제 하시겟습니까?')){
		vfrm.cmd.value ="cmt_del";
		vfrm.c_id.value = c_id;
		vfrm.action ="act.do";
		vfrm.submit();
	}
}
