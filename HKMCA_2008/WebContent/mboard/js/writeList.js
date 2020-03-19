var beforePic = 0;
var varPre = "addPic";
var timer_imgCalc;
var picList = new Array();
var picListDel = new Array();
var picImg = new Array();

for (var i=0; i < picList.length; i++) {
	picImg[i] = new Image();
	picImg[i].src = picList[i];
}

var picUpload = new Array();
var picUploadObj = new Array();

picImg[picList.length] = new Image();
picImg[picList.length].src = "";
picUpload[0] = "";
picUploadObj[0] = "file0";


function calcFileSize() {
	var fileSize = 0;
	for (var i=0; i < picImg.length; i++) {
		if ( i < picList.length) {
			if (!picListDel[i])
				fileSize += parseInt(picImg[i].fileSize);
		} else {
			if (picImg[i].src != "")
				fileSize += parseInt(picImg[i].fileSize);
		}
	}
	fileSize = Math.ceil(fileSize / 1024);
	document.getElementById("sizeCheck").innerHTML = fileSize;
}

function imgDel(num, obj) {
	picListDel[num] = obj.checked;
//	writeList();
}

function writeFileSize() {
	var fileSize = 0;
	document.getElementById("sizeCheck").innerHTML = fileSize;

}

function writeList() {
	var str = "<table border='0' cellpadding='0' cellspacing='0' style='padding:5px; padding-right:0px;' bgcolor='#FFFFFF'><tr valign='top'>";
	for (var i=0; i < picList.length; i++) {
		if (!picListDel[i])
			str += "<td><img src='"+picList[i]+"' width=50 height=38 border=0 hspace=1></td>";
	}
	for (var i=0; i < picUpload.length; i++) {
		if (picUpload[i] != "")
			str += "<td><img src='"+picUpload[i]+"' width=50 height=38 border=0 hspace=1></td>";		
	}
	str += "</tr></table>";
	alert(str);
	document.getElementById("pictureArea").innerHTML = str;
	timer_imgCalc = setTimeout("calcFileSize()", 1000);
}
//writeList();  //getElementById("pictureArea") error...
