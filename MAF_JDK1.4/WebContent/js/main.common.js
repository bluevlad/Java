<!--
var CPATH = "/smis";
var current_notice='1';
function goNotice () {
    var bid = '';
    if (current_notice == 1) bid = 'hotnews';
    else bid = 'JOBNEWS';

    document.location.href = CPATH+'/board/list.do?bid=' + bid;
}
function Notice_Click(n) {
	current_notice = n;
	img_1 = getObject('News_Image1');
	img_2 = getObject('News_Image2');
	
	if (n==1) {
		img_1.cSrc = img_1.overSrc;
		img_2.cSrc = img_2.oriSrc;
		KMM_showHideLayers('news_01','','show', 'news_02','','hide');
	} else {
		img_2.cSrc = img_2.overSrc;
		img_1.cSrc = img_1.oriSrc;
		KMM_showHideLayers('news_01','','hide', 'news_02','','show');
	}
}
function Notice_Out(n){
	KMM_swapImgRestore();
	img_1 = getObject('News_Image1');
	img_2 = getObject('News_Image2');
	img_1.src = img_1.cSrc;
	img_2.src = img_2.cSrc;
	
}
function Notice_Over(n){
	img_1 = getObject('News_Image1');
	img_2 = getObject('News_Image2');
	Notice_Click(n);
	if(n == 1) {
		img_1.src = img_1.overSrc;
		img_2.src = img_2.oriSrc;
	} else {
		img_2.src = img_2.overSrc;
		img_1.src = img_1.oriSrc;
	}
}
function Notice_Init() {
	img_1 = getObject('News_Image1');
	img_2 = getObject('News_Image2');
	img_1.overSrc = CPATH + '/images/st02/main/col3_title_notice_1.gif';
	img_2.overSrc = CPATH + '/images/st02/main/col3_title_notice_2.gif';
	img_1.oriSrc = CPATH + '/images/st02/main/col3_title_notice_1_1.gif';
	img_2.oriSrc = CPATH + '/images/st02/main/col3_title_notice_2_1.gif';
	Notice_Over(1);
	Notice_Click(1);
}


//-->
