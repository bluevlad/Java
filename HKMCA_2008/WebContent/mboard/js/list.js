function MBoard_Write() {
	var vfrm = document.getElementById("frmBBS");
	vfrm.action = "/mboard/write.do";
	vfrm.submit();
}
function MBoard_Search() {
	var vfrm = document.getElementById("frmBBS");
	var vfrmSrch = document.getElementById("frmSrch"); 
	vfrm.v_key.value = vfrmSrch.v_key.value;
	vfrm.v_page.value = 1;
	if (vfrmSrch.v_key.value == "X") {
		vfrm.v_srch.value = "";
	} else {
		vfrm.v_srch.value = vfrmSrch.v_srch.value;
	}
	vfrm.submit();
}
function doSelcatKey(obj) {
	var txt = document.getElementById("v_srch"); 
	if(txt && obj) {
		if(obj.value == 'X' ) {
			txt.value = "";
			Search();
		}
	}
}
function doSelcat (obj) {
    var txt = document.getElementById("v_srch");
    var selKey = document.getElementById("v_key");
	if(obj.value != '') {
	    if(txt && obj) {
	    	txt.value = obj.value;
			selKey.value="CATEGORY";
		}
	}
}
function switchTo(st) {
	var vfrm = document.getElementById("frmBBS");
	if(st=="W") {
		vfrm.v_status.value="W";
	} else {
		vfrm.v_status.value="T";
	}
	vfrm.submit();
}
//function mboard_popup(bid, c_index, seq) {
//	var urlname = CPATH+"/mboard/popup/slide_popup.jsp?bid=" + bid + "&c_index=" + c_index+"&seq="+seq;
//	browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=yes,resizable=no,location=false, menubar=no,status=no,scrollbars=no,width=10,height=10");
//	browsing_window.focus();
//}

function ShowOnly(skey, srch) {

	var vfrm = document.getElementById("frmSrch");

	if (skey == 'category' ) {
		vfrm.v_key.value='CATEGORY';
		vfrm.v_srch.value=srch;
	}
	MBoard_Search();

}
