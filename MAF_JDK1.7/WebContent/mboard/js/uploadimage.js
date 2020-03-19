<!--
var attach_max_number = 10;
var fileObjName = "file0";

function createFileObj() {
	var num = String(parseInt(fileObjName.substring(4, fileObjName.length)) + 1);
	if(num <= attach_max_number ) {
		var str = "";
	    var fileName = "file" + num;
		var newDiv = document.createElement("DIV");
		newDiv.id = "filelist" + num;
		newDiv.style.paddingBottom = '4px';
		document.getElementById("filesDiv").appendChild(newDiv);
		str = "<input type='file' name='file"+num+"' id='file"+num+"' size=32 style='width:277px;' class='box'  onchange='setImg("+num+")'>";
		str += " <a href='javascript:removeFileObj("+num+")'>ªË¡¶</a>";
		document.getElementById("filelist"+num).innerHTML = str;
	
		picImg[parseInt(num)+picList.length] = new Image();
		picUpload[num] = "";
		picUploadObj[num] = "file"+num;
	
	    fileObjName = fileName;
	} else {
		alert("Max " + attach_max_number + " files avali");
	}
}
function removeFileObj(num) {
	document.getElementById("filelist"+num).innerHTML = "";
	document.getElementById("filelist"+num).style.display = 'none';
	picUpload[num] = "";
	picUploadObj[num] = "";
	if (!picImg[num+picList.length])
		picImg[num+picList.length] = new Image();
	picImg[num+picList.length].src = "";
	writeList();
}

function setImg(num) {
	var bCheck = false;
	var imgExtension = new Array("gif", "jpg", "png");
	var ft = document.getElementById("file"+num);
	if(ft) {
		f = ft.value;
		for (var i=0; i < imgExtension.length; i++) {
			if (f.substring(f.length - 3, f.length).toLowerCase() == imgExtension[i] ) {
				bCheck = true;
				break;
			}
		}
		if (bCheck) {
			picUpload[num] = document.getElementById("file"+num).value;
			picUploadObj[num] = "file"+num;
			picImg[num+picList.length].src = picUpload[num];
			writeList();
		}
	} else {
		alert("file"+num);
	}


}

function checkShowType(num) {
	if (num == 1)
		divDisplay("fList", "block");
	else
		divDisplay("fList", "none");		
}

function colorPick(color) {
 document.Edit_Form.title.style.color = color;
 document.Edit_Form.titlecolor.value = color;
 divDisplay('colorPicker', 'none');
}

function updateChar(length_limit)
{
	var comment='';
	comment = eval("document.Edit_Form.title");
	var form = document.Edit_Form;
	var length = calculate_msglen(comment.value);
	if (length > length_limit) {
		alert("Max " + length_limit + "byte vali");
		comment.value = comment.value.replace(/\r\n$/, "");
		comment.value = assert_msglen(comment.value, length_limit);
	}
}
function calculate_msglen(message)
{
	var nbytes = 0;

	for (i=0; i<message.length; i++) {
		var ch = message.charAt(i);
		if(escape(ch).length > 4) {
			nbytes += 2;
		} else if (ch == '\n') {
			if (message.charAt(i-1) != '\r') {
				nbytes += 1;
			}
		} else if (ch == '<' || ch == '>') {
			nbytes += 4;
		} else {
			nbytes += 1;
		}
	}

	return nbytes;
}
function assert_msglen(message, maximum)
{
	var inc = 0;
	var nbytes = 0;
	var msg = "";
	var msglen = message.length;

	for (i=0; i<msglen; i++) {
		var ch = message.charAt(i);
		if (escape(ch).length > 4) {
			inc = 2;
		} else if (ch == '\n') {
			if (message.charAt(i-1) != '\r') {
				inc = 1;
			}
		} else if (ch == '<' || ch == '>') {
			inc = 4;
		} else {
			inc = 1;
		}
		if ((nbytes + inc) > maximum) {
			break;
		}
		nbytes += inc;
		msg += ch;
	}
	return msg;
}

function createFileObjSmall() {
	var num = String(parseInt(fileObjName.substring(4, fileObjName.length)) + 1);
	if(num <= attach_max_number ) {
		var str = "";
	    var fileName = "file" + num;
		var newDiv = document.createElement("DIV");
		newDiv.id = "filelist" + num;
		newDiv.style.paddingBottom = '4px';
		document.getElementById("filesDiv").appendChild(newDiv);
		str = "<input type='file' name='file"+num+"' id='file"+num+"' style='width:150px;' class='box'  onchange='setImg("+num+")'>";
		str += " <a href='javascript:removeFileObj("+num+")'>delete</a>";
		document.getElementById("filelist"+num).innerHTML = str;
	
		picImg[parseInt(num)+picList.length] = new Image();
		picUpload[num] = "";
		picUploadObj[num] = "file"+num;
	
	    fileObjName = fileName;
	} else {
		alert("max  " + attach_max_number + " file atta.");
	}
}
//-->