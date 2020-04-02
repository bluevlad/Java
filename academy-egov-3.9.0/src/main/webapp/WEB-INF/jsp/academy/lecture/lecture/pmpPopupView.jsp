<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="web.cmm.service.MirProperties" %>
<html class="">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9">
<title>윌비스고시학원 - 관리자</title>
<link href="/resources/pmp/images/nb3forpmp.css" rel="stylesheet" type="text/css"><style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style1 {
	color: #FF6699;
	font-weight: bold;
}
.style2 {font-family: Geneva, Arial, Helvetica, sans-serif}
-->
</style>
<!-- 
<style type="text/css">
<!--
html,body,div,span,object,iframe,h1,h2,h3,h4,h5,h6,p,
blockquote,pre,abbr,address,cite,code,del,dfn,em,img,
ins,kbd,q,samp,small,strong,sub,sup,var,b,i,dl,dt,dd,ol,ul,li,
fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,
article,aside,canvas,details,figcaption,figure,footer,header,hgroup,
menu,nav,section,summary,time,mark,audio,video{margin:0; padding:0; }

article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section {display:block;}

li { list-style-type:none; }
img,fieldset,iframe{border:0;}
img,fieldset { border:none; }
img {max-width:100%; }/*ipad사이즈에서 userlogin 의 title등 %로 된  것을 위해*/
li img, dd img { vertical-align:top; }
input { vertical-align:middle; }
hr{display:none;}

a { text-decoration:none; color:#666; }
a:hover { color:#333; }

h1,h2,h3,h4,h5,h6 { font-size:100%; }
address {font-style:normal; font-weight:normal;}   
  
body,select {font:12px/1.5 'Nanum Gothic', Dotum, Gulim; color:#666; }

.pmp {}
.pmp_list { width:618px;}
.pmp_list > li {padding:0 21px;}
.pmp_list > li.pmp_t {position:relative; height:60px; background:url('/resources/images/pmp/pmp_download_t.png') no-repeat;}
.pmp_list > li.pmp_t a {position:absolute; top:20px; right:20px; display:block; width:24px; padding:5px 0; text-align:center;}

.pmp_list > li.pmp_m {background:url(/resources/images/pmp/pmp_download_m.png) repeat-y;}/*백그라운드*/
	.pmp_m_List > li {/*border-bottom:#8ca1c0 1px solid; border-top:#b1c4d7 1px solid;*/ padding:10px 0;}
	.pmp_m_List > li div {background-color:#548cc7; border:#1c4f84 1px solid; padding:10px;}
	.pmp_m_List > li.frist {border-top:none; }
	.pmp_m_List > li.frist p {padding:10px 0 0 150px; background:url(/resources/images/pmp/pmp_download_save.png) no-repeat; height:28px;}
	.pmp_m_List > li.second ul {background-color:#548cc7; border:#1c4f84 1px solid; padding:10px;}
	.pmp_m_List > li.second ul li.download { background:url(/resources/images/pmp/pmp_download_load_bar.png) no-repeat; margin-top:10px;}
	.pmp_m_List > li.second ul li.download p {background:url(/resources/images/pmp/pmp_download_load.png) repeat-x; height:10px;}	
	
	.pmp_m_List > li.fourth {position:relative;}
	.pmp_m_List > li.fourth p.close { position:absolute; top:18px; left:100px; color:#000;}	
	.pmp_m_List > li.fourth ul {padding:10px 0;}
	.pmp_m_List > li.fourth ul li {display:inline; float:left; margin-right:10px;}
	.pmp_m_List > li.fourth ul li a {display:block; width:120px;}
	.pmp_m_List > li.fourth ul:after {content:""; display:block; clear:both;}	


.table01 {border-spacing:0; border-collapse:collapse; border:#fff 1px solid; width:100%; font-size:11px;}
.table01 th {background-color:#f1f1f1; text-align:center; padding:5px 3px; font-weight:bold;  color:#fff;}	
.table01 td {color:#fff;}
.table01 th {vertical-align:middle;}
.table01 td {text-align:left; padding:5px 3px;} 

-- >
</style>
 -->
 

<script type="text/javascript" src="/resources/pmp/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/resources/pmp/js/jquery-ui-1.7.2.custom.min.js"></script>
<c:set var="userId" value="${ params.REG_ID }"></c:set>
<c:set var="leccode" value="${ params.LECCODE }"></c:set>
<c:set var="movieNo" value="${ params.MOVIE_NO }"></c:set>
<c:set var="pmp_default_ip" value="${ detail.SUBJECT_PMP_DEFAULT_PATH }"></c:set>
<c:set var="subjectTitle" value="${ detail.SUBJECT_TITLE }"></c:set>
<!-- ${ userId } : ${ movieNo } : ${ pmp_default_ip } : ${subjectTitle } : ${leccode } -->
<c:if test="${ detail.SUBJECT_PMP_DEFAULT_PATH eq null || detail.SUBJECT_PMP_DEFAULT_PATH eq ''}">
	<script type="text/javascript">
		alert("pmp 다운 경로가 없습니다.");//로그인후 가능합니다.
		self.close();
	</script>	
</c:if>
<%

	//실 서비스시에는 로그인한 사용자의 세션등으로 부터 아이디 정보를 받아온다.
    String control_servlet = (String)request.getAttribute("servlet_name");

 	String UserID = (String)pageContext.getAttribute("userId");
	String pmp_default_ip = (String)pageContext.getAttribute("pmp_default_ip");
	String pmpname = (String)pageContext.getAttribute("subjectTitle");
	
	String SaveFolder = "";
	String pmp_ip = "";
	String WebBaseURL = "";
	String ManagerURL = "";
	
	//  키관리 사이트에서 생성한 사이트 아이디
	String SiteID= "0072";
	if(pmp_default_ip != null){
		String[] arr_pmp_default_ip = pmp_default_ip.split("/");
		//System.out.println(arr_pmp_default_ip+"=arr_pmp_default_ip");
		for( int i = 0; i < arr_pmp_default_ip.length; i++ ){
			if(i == 2){
				pmp_ip = arr_pmp_default_ip[i];
			}
		}
	}

if(UserID == null || "".equals(UserID)){
%>
<script type="text/javascript">
	alert("로그인후 가능합니다.");//로그인후 가능합니다.
	self.close();
</script>		 
<%
	}

if("".equals(pmpname)){
	SaveFolder = "hanlim";
} else {
	SaveFolder = pmpname.replace("/", "-");
}

//  컨텐츠가 있는 디렉토리의 웹 URL 경로
WebBaseURL = "http://"+pmp_ip+"/";

//  NCG_MANAGER.asp 파일의 주소
ManagerURL = "http://"+pmp_ip+"/ncg_manager.asp";

%>
<!-- <br/> webBaseurl : < %= WebBaseURL %> : < %= ManagerURL %> -->  
<script type="text/javascript">

$(document).ready(function() {
	Init();
});

var bInit = false;
var nListUpperIdx = -1;
var UserID = "${ params.USER_ID }";		// 사용자 아이디
var DownType = "${ params.DownType }";
 
//다운로드 초기화
function Init()
{	
	var SiteID = "<%=SiteID%>";		 

	// 기기관련 라이브 업데이트 URL 
	var UpdateSvr = "http://support.netsync.co.kr/@LiveUpdate/NB3ForPMP/Dev/"; 
	
	// LMS 지원여부 (0:지원안함, 1:지원)
	var LMSDiv = 0;
	
	// LMS 기능 적용시 필요.
	var RawLogURL = "";
	// LMS 기능 적용시 필요.
	var strReserved = "";
	// 다운로드 로그 URL
	var DownLogURL = "<%=MirProperties.getProperty("Globals.DomainURL")%>/lecture/pmpDownloadLog.pop3?";
	var nRet = PMPDown.Init(SiteID, UpdateSvr, UserID, LMSDiv, RawLogURL, strReserved, DownLogURL);	
	//alert("nRet : "+nRet);
	if( nRet != 0 )
	{
		if( nRet == 1000 )	{
			alert('기기가 연결되어있지 않습니다. 기기를 PC에 연결해 주십시오.');
			self.close();
			return;
		}else if( nRet == 1200 )	{
			alert('넷싱크 시스템 파일이 생성되었습니다. USB 케이블을 분리한 후 다시 연결하여 주십시오.');
			self.close();
			return;
		}else if ( nRet == 1201 )	{
			alert('넷싱크 시스템 파일이 발급상태입니다. USB 케이블을 분리한 후 다시 연결하여 주십시오.');
			self.close();
			return;
		}else if ( nRet == 1202 )	{
			alert('넷싱크 시스템 파일이 손상되었습니다. USB 케이블을 분리한 후 다시 연결하여 주십시오. 또는 넷싱크 지원 펌웨어인지를 확인해주세요.');
			self.close();
			return;
		}else if( nRet == 1203 )	{
			alert('지정된 경로에 넷싱크 ID 파일이 없거나 손상되었습니다. \n플레이어의 USB 케이블을 분리하신 후 다시 연결하여 주십시오. 프로그램도 종료하신 후에 다시 실행시켜 주십시오.');
			self.close();
			return;
		}else	{
			alert('초기화에 실패했습니다. ErrCode='+nRet);
			self.close();
			return;
		}
	}

	chkCloseWhenDone.checked = false;
	SetCloseWhenDone();
   
	// 윈도우 초기화 후에 다운로드 목록을 추가 
	if(DownType != 'M'){
		window.opener.SelCheck('${leccode}', '${movieNo}');
	}else{
		window.opener.MSelCheck(window.opener.FormName);
	}

	bInit = true;
}

// 다운로드 파일 추가 - 환경설정의 수정이슈가 있음.
function AddFile( FData )
{

//〓〓〓〓〓 사이트 환경에 맞게 적절한 데이터로 수정.  〓〓〓〓〓〓
	var folder = "\\<%=SaveFolder%>\\"; // 기기에 저장할 폴더 설정, 루트폴더(movie/music) 밑에 생성
	var baseurl = "<%=WebBaseURL%>";
	var drmurl  = "<%=ManagerURL%>";
//〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓
	var LMSDiv = 0; 

	var arrCom = FData.split("?");
	
	if( arrCom.length != 11)
		return;

	var strXML = "";
	strXML += "<fileitem>\r\n\t<file>";					strXML += arrCom[0]; 	
	strXML += "</file>\r\n\t<baseurl>";					strXML += baseurl; 		
	strXML += "</baseurl>\r\n\t<folder>";				strXML += arrCom[1] + folder ;  		
	strXML += "</folder>\r\n\t<url>";					strXML += arrCom[2]; 	
	strXML += "</url>\r\n\t<cid>";						strXML += arrCom[3]; 	
	strXML += "</cid>\r\n\t<period>";					strXML += arrCom[4]; 	
	strXML += "</period>\r\n\t<watermark>";				strXML += arrCom[5]; 	
	strXML += "</watermark>\r\n\t<playcount>";			strXML += arrCom[6]; 
	strXML += "</playcount>\r\n\t<avout>";				strXML += arrCom[7]; 
	strXML += "</avout>\r\n\t<scrshot>";				strXML += arrCom[8]; 
	strXML += "</scrshot>\r\n\t<drmurl>";				strXML += drmurl;	
	strXML += "</drmurl>\r\n\t<lms>";					strXML += LMSDiv;		
	strXML += "</lms>\r\n\t<mediatype>";		        strXML += arrCom[9];		
	strXML += "</mediatype>\r\n\t<downloginfo>";		 strXML += arrCom[10];	
	strXML += "</downloginfo>\r\n</fileitem>";
    
	var nResult = PMPDown.AddFile(strXML);
	//alert(" strXML : "+strXML);
	//alert("PMPDown :> " + $("#PMPDown").val()  + " : " + PMPDown.value);
	if( nResult != 0 ){
		alert( PMPDown.GetErrorMsg() );
	}
	//alert(" strXML : "+strXML);
	self.focus();	
}

//다운로드 파일 삭제
function RemoveFile( idx )
{
	PMPDown.RemoveFile(idx);
}

// 다운로드 파일 순서 변경
function ExchangeFiles( idx1, idx2 )
{
	PMPDown.ExchangeFiles( idx1, idx2 );
}

// 다운로드 시작
function BeginDownload()
{
	PMPDown.BeginDownload();
	self.focus();
}

// 다운로드 취소
function CancelDownload()
{
	PMPDown.CancelDownload();
}

// 다운로드 후 팝업창 닫기
function SetCloseWhenDone()
{
	PMPDown.SetCloseWhenDone(chkCloseWhenDone.checked);
}

// 기기폴더 위치변경
function changeDevFolder( foldername )
{
	PMPDown.ChangeDevFolder( foldername );
}

// 기기폴더 생성
function createDevFolder()
{
	PMPDown.CreateDevFolder("");
}

// 기기파일 삭제
function removeDevFile( filename )
{
	PMPDown.RemoveDevFile( filename );
}
// 치기화 버턴
function  setPMPTime()
{
	if( confirm('PMP 시간 초기화 후에는 USB를 다시 연결해주셔야 합니다. 진행하시겠습니까?') ){
		PMPDown.SetDeviceTime();
	}
}

function test(){
	alert("PMPDown : " + PMPDown.DeviceInfo(strState, strDevModel, strFirmVer, strCurDir, nDiskSizeKB, nDiskSpaceKB, strFolderList, strFileList, strRule));
}
</script>

<!-- Javascript Events -->
<script type="text/javascript"  for="PMPDown" event="DownloadPolicy(szDownFolder, bCloseWhenDone, bShutDownWhenDone)">
	// 다운로드 정책 설정
	// szDownFolder ; 파일을 다운로드 할 폴더, 현재 PMP 폴더. 다운로드 폴더가 정해진 파일은 이 폴더로 다운로드 하지 않음.
	// bCloseWhenDone ; 다운로드 완료시 브라우저를 닫을지 여부.
	// bShutDownWhenDone ; 다운로드 완료시 시스템을 종료할지 여부.

	downloadFolder.innerHTML = szDownFolder;
	chkCloseWhenDone.checked = bCloseWhenDone;
</script>

<script type="text/javascript" for="PMPDown" event="DownloadInfo(nState, strFileName, lTotalFileSizeKB, lTotalAmountKB, lTotalRemainedKB, nIdxOfFile, nNumOfFiles, lFileSizeKB, lFileAmountKB, nKBPS)">
	// 다운로드 정보
	// nState ; 현재 상태, 1-대기, 2-다운로드중, 3-에러, 4-완료.
	// strFileName ; 현재 다운로드 받고있는 파일 이름
	// lTotalFileSizeKB ; 다운로드 할 모든 파일의 크기.
	// lTotalAmountKB ; 지금껏 다운로드 한 양.
	// nIdxOfFile ; 현재 다운로드 하고 있는 파일의 0-based 인덱스.
	// nNumOfFiles ; 다운로드 목록에 있는 모든 파일의 개수.
	// lFileSizeKB ; 현재 다운로드 하고 있는 파일의 크기.
	// lFileAmountKB ; 현재 파일의 다운로드 한 양.
	// nKBPS ; 전송 속도. KBytes/sec
	
	fileNumber.innerHTML = (nIdxOfFile+1) + " / " + nNumOfFiles;

	fileName.title = strFileName;
	fileName.style.cursor = "default";

	if (strFileName.length >= 19) strFileName = strFileName.substring(0, 19) + '..';
	fileName.innerHTML = strFileName;
	
	totalRemainedAmount.innerHTML = lTotalRemainedKB + " KB";//(lTotalFileSizeKB - lTotalAmountKB) + " KB";

	fileSizee.innerHTML = lFileSizeKB + " KB";
	fileAmount.innerHTML = lFileAmountKB + " KB";
	transferRate.innerHTML = nKBPS + " KB/s";

	var ProgbarWidth = 551;
	var downrate = Number((lFileAmountKB/lFileSizeKB) * 100);
	document.getElementById("down_prograsbar").width = ProgbarWidth * (downrate/100);
	//down_prograsbar.innerHTML = "<div align='center'>"+ downrate + " %</div>";


</script>




<script type="text/javascript" for="PMPDown" event="DeviceInfo(strState, strDevModel, strFirmVer, strCurDir, nDiskSizeKB, nDiskSpaceKB, strFolderList, strFileList, strRule)">
	// strState ; 기기상태. connected, disconnected
	// strDevModel ; 기기 모델명
	// strCurDir ; 드라이브명을 포함한 현재 경로
	// nDiskSizeKB ; 기기의 총 용량
	// nDiskSpaceKB ; 기기의 남은 용량
	// strFolderList ; 현재 경로 안의 모든 폴더 리스트, 구분자 - '|'
	// strFileList ; 현재 경로 안의 모든 파일 리스트, 구분자 - '|'
	// strRule : 기기에 저장되어 있는 파일의 재생룰 정보, 구분자 - '^'
	
	// alert('strFirmVer :'+strFirmVer);

	devDiskSize.innerHTML = "( "+ nDiskSpaceKB + " KB / " + nDiskSizeKB + " KB )";
	//downloadFolder.innerHTML = strCurDir;
	devModel.innerHTML = "<div align='center'><b>"+ strDevModel +"</b></div>";
	
	var arrDevFolders = strFolderList.split("|");
	var arrDevFiles = strFileList.split("|");
	var DFolders_HTML;
	var DFiles_HTML;
	DFolders_HTML = "<table width=120 border=0 cellspacing=0 cellpadding=0>";
	DFiles_HTML = "<table width=277 border=0 cellpadding=0 cellspacing=0 bgcolor='#FFFFFF'>";

	for( var i=0 ; i<arrDevFolders.length ; i++ )
	{
		if( arrDevFolders[i].length==0 )
			continue;
		DFolders_HTML += "<tr><td height=20>&nbsp;<a href=\"javascript:top.changeDevFolder('" + arrDevFolders[i] + "');\"><img src='/resources/pmp/images/55_folder.jpg' width=12 height=10 border=0>&nbsp;" + arrDevFolders[i] + "</a><br/></td>";
		DFolders_HTML += "</tr>";
	}
	DFolders_HTML += "</table>";
	devFolders.DeviceFolders.innerHTML = DFolders_HTML;
	
	for( i=0 ; i<arrDevFiles.length ; i++ )
	{
		var arrDevFileRule = strRule.split("^");

		if( arrDevFileRule[i].length == 0 ) {
			continue;
		} else {
			var arrRulePeriod = arrDevFileRule[i].split("|")[0];
			var arrRulePlayCount = arrDevFileRule[i].split("|")[1];
		}
		
		var FileRuleInfo = '';
		var PlayStartDate, PlayEndDate, PlayPeriod, PlayCount;
		
		if(arrRulePeriod == '' && arrRulePlayCount == '') {
			FileRuleInfo = '';
		} else {
			PlayStartYear = arrRulePeriod.substring(0,4);
			PlayStartMonth = arrRulePeriod.substring(4,6);
			PlayStartDay = arrRulePeriod.substring(6,8);
			PlayStartHour = arrRulePeriod.substring(8,10);
			PlayStartMin = arrRulePeriod.substring(10,12);
			PlayStartSec = arrRulePeriod.substring(12,14);
			PlayStartDate = PlayStartYear + "-" + PlayStartMonth + "-" + PlayStartDay + "(" + PlayStartHour + ":" + PlayStartMin + ":" + PlayStartSec +")";
			
			PlayEndYear = arrRulePeriod.substring(14,18);
			PlayEndMonth = arrRulePeriod.substring(18,20);
			PlayEndDay = arrRulePeriod.substring(20,22);
			PlayEndHour = arrRulePeriod.substring(22,24);
			PlayEndMin = arrRulePeriod.substring(24,26);
			PlayEndSec = arrRulePeriod.substring(26,28);
			PlayEndDate = PlayEndYear + "-" + PlayEndMonth + "-" + PlayEndDay + "(" + PlayEndHour + ":" + PlayEndMin + ":" + PlayEndSec +")";
			
			PlayPeriod = PlayStartDate + " ~ " + PlayEndDate;
			
			if( arrRulePlayCount == -1 ) {
				PlayCount = '무제한';
			} else {
				PlayCount = arrRulePlayCount;
			}
			
			FileRuleInfo = "title='- 재생기간 : \n&nbsp;&nbsp;"+PlayPeriod+" \n- 재생횟수 : "+PlayCount+"'";
		}
		
		if( arrDevFiles[i].length==0 )
			continue;
		
		if (i%2 == 0) {
			td_bgcolor = "f1f1f1";
		} else {
			td_bgcolor = "ffffff";
		}

		DFiles_HTML += "<tr "+FileRuleInfo+"><td width=2 bgcolor='"+ td_bgcolor +"'></td>"
		DFiles_HTML += "<td width=255 height=22 bgcolor='"+ td_bgcolor +"'>"+ arrDevFiles[i] + "</td>"
		DFiles_HTML += "<td width=20  bgcolor='"+ td_bgcolor +"'><a href=\"javascript:top.removeDevFile('" + arrDevFiles[i] + "');\"><img src='/resources/pmp/images/del_1.gif' width=11 height=12 border=0></a></td>";
		DFiles_HTML += "</tr>";
	}
	DFiles_HTML += "</table>";
	devFiles.DeviceFiles.innerHTML = DFiles_HTML;
</script>

<script type="text/javascript" for="PMPDown" event="ResetFileList(strFileInfo)">
	// 다운로드 리스트 리셋

	//alert('strFileInfo : '+strFileInfo);
	var xmlDoc = new ActiveXObject("Msxml2.DOMDocument.3.0");
	xmlDoc.async = false;
	xmlDoc.loadXML(strFileInfo);
	
	if (xmlDoc.parseError.errorCode != 0) {
	   var myErr = xmlDoc.parseError;
	   alert("XML ParseError : " + myErr.reason);
	} else {
		FileInfo = xmlDoc.documentElement.selectNodes("file");	   

		html = "<table width=421 border=0 cellspacing=0 cellpadding=0>";	   
	   
	    var Flen =  FileInfo.length ; 
		for( var i=0;  i<Flen ; i++)
		{
			var FName = FileInfo[i].getAttribute("name");
			var FSize = FileInfo[i].getAttribute("size");
			var FState = FileInfo[i].getAttribute("state");
			
			if (i%2 == 0) {
				td_bgcolor = "f1f1f1";
			} else {
				td_bgcolor = "ffffff";
			}
			html += "<tr><td width=3 bgcolor='"+ td_bgcolor +"'></td>";
			html += "<td align=left width=190 height=22 style='word-break:break-all;' bgcolor='"+ td_bgcolor +"'>" + FName + "</td>";
			html += "<td align=center width=97 bgcolor='"+ td_bgcolor +"'>" + FSize + " KB</td>";
			html += "<td align=center width=77 bgcolor='"+ td_bgcolor +"' class='sstt'>" + FState + "</td>";
			html += "<td align=center width=54 bgcolor='"+ td_bgcolor +"'><a href='javascript:top.ExchangeFiles(" + (i-1) + "," + i + ");'><img src='/resources/pmp/images/up.gif' width=11 height=12 border=0></a>&nbsp<a href='javascript:top.ExchangeFiles(" + i + "," + (i+1) + ");'><img src='/resources/pmp/images/down.gif' width=11 height=12 border=0></a>&nbsp<a href='javascript:top.RemoveFile(" + i + ");'><img src='/resources/pmp/images/del.gif' width=11 height=12 border=0></a></td>";
			html += "</tr>";		

 
		}
		html += "</table>";
		fileList.downlist.innerHTML = html;	
	}
</script>

<script type="text/javascript" for="PMPDown" event="RemainTime(strTotalElapseTime,strTotalRemainedTime,strFileElapseTime,strFileRemainedTime)">
	// 다운로드 시간 정보
	// strTotalRemainedTime ; 다운로드 할 전체 파일의 다운로드 남은시간 
	// strFileElapseTime ; 다운로드 파일의 다운로드 경과시간
	// strFileRemainedTime ; 다운로드 파일의 남은시간
	
	totalRemainedTime.innerHTML = strTotalRemainedTime;
	fileElapse.innerHTML = strFileElapseTime;
	fileRemained.innerHTML = strFileRemainedTime;
</script>
<!-- Javascript Events -->

</head>
<body>
<table width="618" height="729" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="618" height="729" align="center" valign="top" background="/resources/pmp/images/bg.gif"><div  style="padding-left:563; padding-top:10">
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td> <div align="right"><a href="javascript:window.close();" onFocus="this.blur();"><img src="/resources/pmp/images/t_3_n.jpg"  border="0" onmouseover="javascript:this.src='/resources/pmp/images/t_3_o.jpg'" onMouseOut="javascript:this.src='/resources/pmp/images/t_3_n.jpg'"></a></div></td>
        </tr>
      </table>
    </div>
        <!--다운로드저장위치-->
        <div style="padding-top:21">
          <table width="576" height="38" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td align="right" background="/resources/pmp/images/11_bg.gif" style="padding-right:10"><table width="410" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="342" style="padding-top:4" id="downloadFolder"></td>
                  </tr>
              </table></td>
            </tr>
          </table>
        </div>
      <!--/다운로드저장위치-->
	  <!--현재파일-->
        <div style="padding-top:10">
          <table width="576" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="/resources/pmp/images/22_j.gif" width="576" height="29"></td>
            </tr>
            <tr>
              <td height="77" valign="top" background="/resources/pmp/images/22_bg.gif" ><div style="padding-left:76; padding-top:15">
                  
                <table border="0" cellspacing="0" cellpadding="0">
                  <tr> 
                    <td colspan="3" width="249" class="style1" id="fileName" style="word-break:break-all;">-</td>
                    <td class="style1"></td>
                    <td colspan="3" id="fileSizee"> KB</td>
                  </tr>
                  <tr> 
                    <td height="5" colspan="7"></td>
                  </tr>
                  <tr> 
                    <td width="86" id="fileElapse">-</td>
                    <td width="58">&nbsp;</td>
                    <td width="95" id="fileRemained">-</td>
                    <td width="61">&nbsp;</td>
                    <td width="76" id="fileAmount" style="word-break:break-all;"> 
                      KB</td>
                    <td width="58">&nbsp; 
                    </td>
                    <td width="66" id="transferRate" style="word-break:break-all;">KB/s</td>
                  </tr>
                </table>
              </div>
                  <div style="padding-top:10 ; padding-left:14">
                    <table width="551" height="13" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td background="/resources/pmp/images/22_barbg.gif" style="padding:1"><img id="down_prograsbar" src="/resources/pmp/images/22_bar.jpg" width="0" height="11"></td>
                      </tr>
                    </table>
                  </div></td>
            </tr>
          </table>
        </div>
      <!--/현재파일-->
	  <!--다운로드리스트-->
        <div style="padding-top:13">
          <table width="576" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="/resources/pmp/images/33_j.gif" width="576" height="27"></td>
            </tr>
            <tr>
              <td height="158" valign="top" background="/resources/pmp/images/33_bg.gif" ><table width="576" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="456"><div style="padding-left:8; padding-top:35">
                        <table width="443" height="115" border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <td valign="top"><iframe id="fileList" src="/resources/pmp/Downlist.html" width="443" height="115"  scrolling="yes" frameborder="0"></iframe></td>
                          </tr>
                        </table>
                    </div></td>
                    <td valign="bottom"><table width="110" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="20" align="right" class="ff" id="fileNumber">-</td>
                        </tr>
                        <tr>
                          <td height="27">&nbsp;</td>
                        </tr>
                        <tr>
                          <td height="20" align="right" class="ff" id="totalRemainedAmount">-</td>
                        </tr>
                        <tr>
                          <td height="27">&nbsp;</td>
                        </tr>
                        <tr>
                          <td height="20" align="right" class="ff" id="totalRemainedTime">-</td>
                        </tr>
                    </table></td>
                  </tr>
              </table></td>
            </tr>
          </table>
        </div>
      <!--/다운로드리스트-->
	  <!--전송완료후 및 다운로드버튼들-->
        <div style="padding-top:13">
          <table width="576" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="/resources/pmp/images/44_j.gif" width="576" height="27"></td>
            </tr>
            <tr>
              <td height="52" valign="top" background="/resources/pmp/images/44_bg.jpg">

			    <table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="60" align="right" valign="top" style="padding-top:2">
					    <table width="60" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="60"><input type="checkbox" id="chkCloseWhenDone" name="checkbox" value="checkbox" onClick="SetCloseWhenDone();"></td>
                        </tr>
                        </table>
					</td>
                    <td width="513" align="right" style="padding-top:10">
					    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td><a onFocus="this.blur();"><img src='/resources/pmp/images/44_bu_download_n.jpg' width="138" height='42' border='0' onMouseOver="javascript:this.src='/resources/pmp/images/44_bu_download_o.jpg'" onMousedown="javascript:this.src='/resources/pmp/images/44_bu_download_d.jpg'" onMouseup="javascript:this.src='/resources/pmp/images/44_bu_download_o.jpg'" onMouseout="javascript:this.src='/resources/pmp/images/44_bu_download_n.jpg'" onClick="BeginDownload();" style="cursor:hand;"></a></td>
                          <td><a onFocus="this.blur();"><img src='/resources/pmp/images/44_bu_cancel_n.jpg' width="119" height='42' border='0' onmouseover="javascript:this.src='/resources/pmp/images/44_bu_cancel_o.jpg'" onmousedown="javascript:this.src='/resources/pmp/images/44_bu_cancel_d.jpg'" onmouseup="javascript:this.src='/resources/pmp/images/44_bu_cancel_o.jpg'" onmouseout="javascript:this.src='/resources/pmp/images/44_bu_cancel_n.jpg'" onClick="CancelDownload();" style="cursor:hand;"></a></td>
                          <td><a onFocus="this.blur();"><img src='/resources/pmp/images/55_bu_time_n.jpg' width="142" height='42' border='0' onmouseover="javascript:this.src='/resources/pmp/images/55_bu_time_o.jpg'" onmousedown="javascript:this.src='/resources/pmp/images/55_bu_time_x.jpg'" onmouseup="javascript:this.src='/resources/pmp/images/55_bu_time_o.jpg'" onmouseout="javascript:this.src='/resources/pmp/images/55_bu_time_n.jpg'" onClick="setPMPTime();" style="cursor:hand;"></a></td>
                          <td><a onFocus="this.blur();"><img src='/resources/pmp/images/44_bu_close_n.jpg' width="114" height='42' border='0' onmouseover="javascript:this.src='/resources/pmp/images/44_bu_close_o.jpg'" onmousedown="javascript:this.src='/resources/pmp/images/44_bu_close_d.jpg'" onmouseup="javascript:this.src='/resources/pmp/images/44_bu_close_o.jpg'" onmouseout="javascript:this.src='/resources/pmp/images/44_bu_close_n.jpg'" onClick="window.close();" style="cursor:hand;"></a></td>
                        </tr>
                        </table>
					</td>
                  </tr>
              </table></td>
            </tr>
          </table>
        </div>
      <!--/전송완료후 및 다운로드버튼들-->
	  <!--기기상태-->
        <div style="padding-top:24">
          <table width="578" height="137" border="0" cellpadding="0" cellspacing="0"  background="/resources/pmp/images/55_bg.jpg">
            <tr>
              <td width="133" valign="top"><div style="padding-left:9; padding-top:7">
                <table width="118" height="121" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td height="50" align="center" class="ssg" id="devModel">-</td>
                  </tr>
                  <tr>
                    <td height="71" align="center" class="ssg" id="devDiskSize">-</td>
                  </tr>
                </table>
              </div></td>
              <td width="143" align="left" valign="top" style="padding-top:7"><table width="137" height="121" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="24" align="right"><table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          
                        <td>&nbsp;</td>
                          
                        <td width="24"></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td valign="top"><iframe id="devFolders" src="/resources/pmp/DeviceFolder.html" width="137" height="97"  scrolling="yes" frameborder="0"></iframe></td>
                  </tr>
              </table></td>
              <td width="302" valign="top" style="padding-top:31"><table width="294" height="97" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><iframe id="devFiles" src="/resources/pmp/DeviceFileList.html" width="294" height="97"  scrolling="yes" frameborder="0"></iframe></td>
                  </tr>
              </table></td>
            </tr>
          </table>
        </div>
      <!--/기기상태-->
	</td>
  </tr>
</table>

<!--  
    <div class="pmp">
        <ul class="pmp_list">
        
            <li class="pmp_t">
            <a href="javascript:window.close();"><img src="/resources/images/pmp/pmp_btn_t_close.png" width="15" height="15" border="0" alt="창 닫기"></a>
            </li>
            
            <li class="pmp_m">
                <ul class="pmp_m_List">
                    <li class="frist"><p><span id="downloadFolder"></span></p></li>
                    
                    <li class="second"> 
                        <p><img src="/resources/images/pmp/pmp_download_file_title.png" width="576" border="0" alt="현재파일"></p>
                        <div><iframe id="fileList" src="/resources/pmp/Downlist.html" width="443" height="115"  scrolling="yes" frameborder="0"></iframe></div>
                    </li>
                    <li class="third">
                      <p><img src="/resources/images/pmp/pmp_download_list_title.png" width="576" border="0" alt="다운로드 리스트"></p>
                      <div><iframe id="devFolders" src="/resources/pmp/DeviceFolder.html" width="137" height="97"  scrolling="yes" frameborder="0"></iframe></div>
                    </li>
                    
                    <li class="fourth">
                      <p><img src="/resources/images/pmp/pmp_download_finish_title.png" width="576" border="0" alt="전송 후 닫기"></p>
                      <ul>
                        <lI><a href="#" onClick="BeginDownload();"><img src="/resources/images/pmp/btn_pmp_download.png" width="120" height="29" border="0" alt="다운로드 취소 PMP초기화 닫기버튼"></a></lI>
                        <lI><a href="#" onClick="CancelDownload();"><img src="/resources/images/pmp/btn_pmp_cancel.png" width="120" height="29" border="0" alt="취소"></a></lI>
                        <lI><a href="#" onclick="setPMPTime();"><img src="/resources/images/pmp/btn_pmp_firsttime.png" width="120" height="29" border="0" alt="PMP초기화" /></a></lI>
                        <lI><a href="javascript:window.close();"><img src="/resources/images/pmp/btn_pmp_close.png" width="120" height="29" border="0" alt="닫기"></a></lI>
                      </ul>
                      <div><iframe id="devFiles" src="/resources/pmp/DeviceFileList.html" width="294" height="97"  scrolling="yes" frameborder="0"></iframe></div>
                      <p class="close"><input type="checkbox" id="chkCloseWhenDone" name="checkbox" value="checkbox" onClick="SetCloseWhenDone();"> 창닫기</p>
                    </li>               
                </ul>
            </li>
        </ul>
        <p><img src="/resources/images/pmp/pmp_download_b.png" width="616" height="59"></p>
   </div>
   
   -->
   
<!-- ActiveX 변경  -->
<!-- <object id='PMPDown' classid='CLSID:C1582C6A-606D-442C-B1AB-EBA96BAAEC01' codebase='/resources/pmp/PMPDown.UnityNB3forPMP.cab#version=1,0,1305,2417' width='0' height='0'></object> -->
<object id='PMPDown' classid='CLSID:C1582C6A-606D-442C-B1AB-EBA96BAAEC01' codebase='/resources/pmp/PMPDown.UnityNB3forPMP_20130610.cab#version=1,0,1305,2417' width='0' height='0'></object>
S
</body>
</html>