function chkLength(el){
    var maxbyte = el.getAttribute("MAXBYTE");
	var a = document.getElementById("strcnt");
	var temp=0;
	var tcount=0;
	if (el.value != "strcnt") {
		var str = new String(el.value);
		temp = str.length;
		for (var k=0;k<temp;k++)
		{
			onechar = str.charAt(k);
			if (escape(onechar) =='%0D') { } else if (escape(onechar).length > 4) { tcount += 2; } else { tcount++; }
		}
		
		if (tcount > parseInt(maxbyte)) {
			a.innerHTML = tcount;
			return doError(el,"{name}의 길이가 초과되었습니다 (최대 "+maxbyte+"바이트)");
		}
		else{
			a.innerHTML = tcount;
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
	if(confirm('이 커멘트를 삭제 하시겠습니까?')){
		vfrm.cmd.value ="cmt_del";
		vfrm.c_id.value = c_id;
		vfrm.action ="act.do";
		vfrm.submit();
	}
}