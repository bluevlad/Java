function go_reply() {
	var vfrm = document.getElementById("frmBBS");
	vfrm.cmd.value ="addreply";
	vfrm.action = "write.do";
	vfrm.submit();
}

function go_delete() {
	if(confirm("Delete?") ) {
		var vfrm = document.getElementById("frmBBS");
		vfrm.cmd.value ="delete";
		vfrm.action="act.do";
		vfrm.submit();
	}
}

function go_list() {
	var vfrm = document.getElementById("frmBBS");
	vfrm.action="list.do";
	vfrm.submit();
}

function go_edit() {
	var vfrm = document.getElementById("frmBBS");
	vfrm.action="write.do";
	vfrm.submit();
}

function showfrmPasswd(item) {
//	var top = element_top(item)+item.height;
//	var left = element_left(item) ;
	var mDiv = document.getElementById("divDelete");
	if(mDiv) {
//		mDiv.style.top = top;
		mDiv.style.left = 650+"px";
		mDiv.style.display = "";
	}
}

function goChkDel() {
	var vfrm =  document.getElementById("frmPasswd");
	var frmBbs = document.getElementById("frmBBS");
	if(vfrm) {
		if(validate(vfrm)){
			frmBbs.passwd.value = vfrm.passwd.value;
			frmBbs.cmd.value ="delete";
			frmBbs.action ="act.do";
			frmBbs.submit();
		} 
	}
}

function hidefrmPasswd() {
	var mDiv =  document.getElementById("divDelete");
	if(mDiv) {
		mDiv.style.display='none';
	}
}
