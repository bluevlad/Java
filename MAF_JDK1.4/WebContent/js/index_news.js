<!--
var CPATH = "/smis";
var current_notice='1';

function goNotice () {
    var bid = '';
    if (current_notice == 1) {
			bid = 'KAIS_EDU ';
		} else if (current_notice == 2) {
			bid = 'EXAM_NOTICE';
    } else if (current_notice == 3) {
			bid = 'KAIS_EDU ';
		} else {
			bid = 'EXAM_NOTICE';
		}

    document.location.href = CPATH+'/board/list.do?bid=' + bid;
}
function Notice_Click(n) {
	current_notice = n;
	img_1 = getObject('News_Image1');
	img_2 = getObject('News_Image2');
	img_3 = getObject('News_Image3');
	img_4 = getObject('News_Image4');
	
	if (n==1) {
		img_1.cSrc = img_1.overSrc;
		img_2.cSrc = img_2.oriSrc;
		KMM_showHideLayers('news_01','','show', 'news_02','','hide');
	} else if(n==2) {
		img_2.cSrc = img_2.overSrc;
		img_1.cSrc = img_1.oriSrc;
		KMM_showHideLayers('news_01','','hide', 'news_02','','show');
	} else if(n==3) {
		img_3.cSrc = img_3.overSrc;
		img_4.cSrc = img_4.oriSrc;
		KMM_showHideLayers('news_03','','show', 'news_04','','hide');
	} else if(n==4) {
		img_4.cSrc = img_4.overSrc;
		img_3.cSrc = img_3.oriSrc;
		KMM_showHideLayers('news_03','','hide', 'news_04','','show');
	}
}
function Notice_Out(n) {
	KMM_swapImgRestore();
	img_1 = getObject('News_Image1');
	img_2 = getObject('News_Image2');
	img_3 = getObject('News_Image3');
	img_4 = getObject('News_Image4');
	img_1.src = img_1.cSrc;
	img_2.src = img_2.cSrc;
	img_3.src = img_3.cSrc;
	img_4.src = img_4.cSrc;
	
}
function Notice_Over(n){
	img_1 = getObject('News_Image1');
	img_2 = getObject('News_Image2');
	img_3 = getObject('News_Image3');
	img_4 = getObject('News_Image4');
	
	Notice_Click(n);
	if(n == 1) {
		img_1.src = img_1.overSrc;
		img_2.src = img_2.oriSrc;
	} else if(n == 2) {
		img_2.src = img_2.overSrc;
		img_1.src = img_1.oriSrc;
	} else if(n == 3) {
		img_3.src = img_3.overSrc;
		img_4.src = img_4.oriSrc;
	} else if(n == 4) {
		img_4.src = img_4.overSrc;
		img_3.src = img_3.oriSrc;
	}
}

function Notice_Init() {
	img_1 = getObject('News_Image1');
	img_2 = getObject('News_Image2');
	img_3 = getObject('News_Image3');
	img_4 = getObject('News_Image4');
	img_1.overSrc = CPATH + '/images/st02/main/col3_title_notice_1.gif';
	img_2.overSrc = CPATH + '/images/st02/main/col3_title_notice_2.gif';
	img_3.overSrc = CPATH + '/images/st02/main/col3_title_notice_3.gif';
	img_4.overSrc = CPATH + '/images/st02/main/col3_title_notice_4.gif';
	img_1.oriSrc = CPATH + '/images/st02/main/col3_title_notice_1_1.gif';
	img_2.oriSrc = CPATH + '/images/st02/main/col3_title_notice_2_1.gif';
	img_3.oriSrc = CPATH + '/images/st02/main/col3_title_notice_3_1.gif';
	img_4.oriSrc = CPATH + '/images/st02/main/col3_title_notice_4_1.gif';
	Notice_Over(1);
	Notice_Over(3);

}

Notice_Init(1);
//-->