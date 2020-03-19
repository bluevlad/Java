<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="JavaScript">
<!--
function na_restore_img_src(name, nsdoc)
{
  var img = eval((navigator.appName.indexOf('Netscape', 0) != -1) ? nsdoc+'.'+name : 'document.all.'+name);
  if (name == '')
    return;
  if (img && img.altsrc) {
    img.src    = img.altsrc;
    img.altsrc = null;
  } 
}

function na_preload_img()
{ 
  var img_list = na_preload_img.arguments;
  if (document.preloadlist == null) 
    document.preloadlist = new Array();
  var top = document.preloadlist.length;
  for (var i=0; i < img_list.length-1; i++) {
    document.preloadlist[top+i] = new Image;
    document.preloadlist[top+i].src = img_list[i+1];
  } 
}

function na_change_img_src(name, nsdoc, rpath, preload)
{ 
  var img = eval((navigator.appName.indexOf('Netscape', 0) != -1) ? nsdoc+'.'+name : 'document.all.'+name);
  if (name == '')
    return;
  if (img) {
    img.altsrc = img.src;
    img.src    = rpath;
  } 
}

// -->
</script>

    <![if !IE]>
    <style type="text/css">
    /* main top menu hack */
        .default_skin .top_ci
        {
            padding-top: 0px;
        }

        .default_skin .navi
        {
            padding-top: 0px;
        }
    </style>
    <![endif]>
    <script src="http://www.at4u.or.kr/LIB/JS/common.js" language="javascript" type="text/javascript"><!-- at4u JavaScript 공통 함수 //--></script>
    <script language="javascript" type="text/javascript">
    </script>

<!--본문 내용-->
<table border="0" cellpadding="0" cellspacing="0">
    <tr>    
        <td colspan="2" align="center" style="padding-top: 31px "><img src="<c:url value="/images/ubq/title_txt02.gif"/>" border="0"></td>
    </tr>
    <tr>    
        <td style="padding-top: 37px " width="55%">
	        <!-- http://www.at4u.or.kr/F03000000000/ kado start -->                             
	        <!--contents start-->
	        <!--main_contents start-->      
	        <div id="main_contents" class="main_contents">
            <div id="body" class="sub_contents">
            <div class="s_txt" align="center">
            <!-- 본문 상단 영역 Start -->
            <div class="all_img padt_15 mart_5">
            <!-- 큰 이미지 영역 Start -->
            <div class="img_big padt_15">
            <img id="L_img" src="<c:url value="/images/ubq/KidsVoice2-c.gif"/>" alt="키즈보이스2 사진 - 대화하기"  width="350"/>
            </div>
            <!-- 큰 이미지 영역 End -->
            <!-- 작은 이미지 리스트 영역 Start -->
            <a href="javascript: changeImg('S_img_1');" tabindex="2030">
            <img id="S_img_1" src="<c:url value="/images/ubq/KidsVoice2-a.gif"/>" width="70" height="54" alt="키즈보이스 2 사진 - 초기화면"/>
            </a>&nbsp;&nbsp;&nbsp;
            <a href="javascript: changeImg('S_img_2');" tabindex="2040">
            <img id="S_img_2" src="<c:url value="/images/ubq/KidsVoice2-b.gif"/>" width="70" height="54" alt="키즈보이스 2 사진 - 그림상징" />
            </a>&nbsp;&nbsp;&nbsp;
            <a href="javascript: changeImg('S_img_3');" tabindex="2050">
            <img id="S_img_3" src="<c:url value="/images/ubq/KidsVoice2-c.gif"/>" width="70" height="54" alt="키즈보이스 2 사진 - 대화하기" />
            </a><BR><작은 사진을 클릭하면 크게 보실 수 있습니다.>
            <!-- 작은 이미지 리스트 영역 End -->
            </div>
        </td>
        <td style="padding-top: 37px ">
            <!-- 본문 상단 영역 End -->
            <div class="explan padl_15 mart_20">
            <dl class="inline_list">
            <dt class="bolder"><li>제품명</li></dt>
            <dd class="padb_10">키즈보이스 II  ( KidsVoice 2 )</dd>
            </dl>
            <dl>
            <dt class="bolder"><li>제조,공급원</li> </dt>
            <dd class="padb_10">(주)유비큐 / Made in Korea</dd>
            </dl>
            <dl class="inline_list">
            <dt class="bolder"><li>제품가격 : 3,300,000원</li></dt>
            <dd class="padb_5">※ 구입전에 고객만족센터 견적요청 필수</dd>
            </dl>
            <dl class="inline_list">
            <dt class="bolder"><li>계좌번호 : </li></dt>
            <dd>우리은행 / 1005-401-261775  </dd>
            </dl>
            <!-- 첨부 파일 영역 -->
            <dl class="explan">
            <dt class="bolder"><li>설치&middot;사용방법 동영상 (다운로드)</li></dt>
            <dd class="padl_10 padb_5"><a href="<c:url value="/images/ubq/kids_01.wmv"/>" tabindex="2070">키즈보이스_01.wmv<img src="http://www.at4u.or.kr/IMG/TWB/ICON/etc.gif" border="0" alt=" file download"></a>(파일크기 : 5 M Byte)</dd>
            </dl>
            <dl class="explan">
            <dt class="bolder"><li>활용사례 동영상 (다운로드)</li></dt>
            <dd class="padl_10 padb_5"><a href="<c:url value="/images/ubq/kids_02.wmv"/>" tabindex="2080">키즈보이스_02.wmv<img src="http://www.at4u.or.kr/IMG/TWB/ICON/etc.gif" border="0" alt=" file download"></a>(파일크기 : 5 M Byte)</dd>
            </dl>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="padding-left: 37px "><BR><BR>
            <!-- 본문 하단 영역 Start -->
            <div class="explan1">
            <dl class="explan_widt">
            <dt class="bolder padb_5"><li>제품개요</li></dt>
            <dd class="padb_10">장애인의 원활한 의사소통을 돕고 언어발달과 언어습득 훈련, 발성발어 촉진, 발음훈련을 위한 보조공학기기</dd>
            </dl>
            <dl class="explan_widt">
            <dt class="bolder padb_5"><li>제품사양</li></dt>
            <dd class="padb_10"> - 크기 : 27.65(가로)×19.7(세로)×1.95(높이)㎝ <br/>
                                 - 무게 : 1kg<br/>
                                 - OS : Windows Embedded CE 6.0<br>
                                 - CPU : VIA C7-M 500Mhz<br/>
                                 - Main Memory : SDRAM 256M<br/>
                                 - Storage : 2G(DOC), SD 1 Slot<br/>
                                 - Display : 10.4인치 터치모니터<br/>
                                 - 사운드 : 스테레오, 내장 마이크 지원<br/>
                                 - Battery: Li-ION 2400 mAh<br/>
                                 - 사용시간 : 보통 3~3.5시간<br/>
                                 - Input : 100-240V, 50-60Hz, 1.7A<br>
                                 - Ouput : 19V 3.42A</dd>
            </dl>
            <dl class="explan_widt">
            <dt class="bolder padb_5"><li>주요기능</li></dt>
            <dd class="padb_10">- 원하는 문장을 입력해서 음성으로 재생하는 문장 의사소통기능 (대화하기)<br />- 그림상징 및 어휘를 탑재하여 화면을 터치하는 사용<br />- 문자를 음성으로 읽어주는 음성합성엔진(TTS) 탑재하여 음성출력<br />- 어휘/그림상징 편집(추가/삭제/수정기능, 음성 녹음 및 재생 기능, <br />- 면 불할 기능, 외부소리 저장기능 등 다양한 기능 제공</dd>
            </dl>
            <dl class="explan_widt">
            <dt class="bolder padb_5"><li>제품특징</li></dt>
            <dd class="padb_10">- 현진 특수교사 및 국내 특수교육 연구진이 개발한 한국형 그림상징(K-PCS) 탑재<br />- 멀티페이지 카테고리 만들기, Search 기능, 터치스크린 인터페이스제공으로 사용이 편리<br />- 그림 어휘 추가 기능, CF카드 방식의 데이타 저장매체로 사용 및 관리가 용이하며 외부 충격에 강하도록 제품 설계<br />- 간편한 휴대성과 이동성, 견고성, 미려한 디자인 등을 고려하여 다양하고편리한 기능제공</dd>
            </dl>
            <dl class="explan_widt">
            <dt class="bolder padb_5"><li>구성품목</li></dt>
            <dd class="padb_10">- 본체 1개 / 충전용 아답터(220V) 1개 / 배터리 1개 / 휴대용 가방 1개/ 휴대용 파우치 1개 / 사용설명서 1부</dd>
            </dl>
            <dl class="explan_widt">
            <dt class="bolder padb_5"><li>사용가능 대상</li></dt>
            <dd class="padb_10">의사소통에 어려움을 겪는 지체,뇌병변,청각장애인</dd>
            </dl>
            <dl class="explan_widt">
            <dt class="bolder padb_5"><li>기대효과</li></dt>
            <dd class="padb_10">- 언어치료 및 재활 치료 등의 도구로 활용될 수 있어 구어의 발달을 촉진하여 대화술의 발전 가능<br />- 문장이해력 향상으로 인터넷 등을 활용할 수 있는 능력향상<br />- 의사소통에 어려움을 겪고 있는 아동 또는 성인의 의사소통을 좀 더 쉽게 해주어 삶의 질 향상<br />- 의사소통 실패로 인한 좌절을 줄이고, 문제행동 감소에 도움<br />- 다양한 상호작용 안에서 효과적으로 참여하여 사회성 향상 및 더 많은 일반 활동 참여 가능</dd>
            </dl>
            <dl class="explan_widt">
            <dt class="bolder padb_5"><li>사용방법</li></dt>
            <dd class="padb_10">① 파워를 누른 후 그림상징 의사소통을 사용하려면 [시작]을, 문장으로 의사소통을 하려면 [대화하기]를 선택<br />② 의사소통이 필요한 상황에 해당하는 그림을 선택(화면터치)<br />③ 어휘를 누른 후 하단의 관련 동사를 눌러 의사소통(음성으로 터치와 동시에 출력됨)<br /> ④ [대화하기]를 눌려 문장을 입력하여 [소리듣기] 로 음성을 출력</dd>
            </dl>
            </div>
            <!-- 본문 하단 영역 End -->
            </div>
            </div>
            </div>
            </div>
            <!--main_contents end-->
            <!--contents end-->
        </td>                               
    </tr>
</table>
<!--/본문 내용-->
