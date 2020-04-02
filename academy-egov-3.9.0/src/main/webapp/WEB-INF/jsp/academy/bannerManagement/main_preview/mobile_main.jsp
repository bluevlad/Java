<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>[윌비스경찰]신광은경찰팀</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="description" content="경찰 가장 빠른 합격전략 윌비스경찰, 압도적 경찰1타강사진 윌비스경찰학원, 경찰합격 독보적 윌비스 신광은경찰팀">
<meta name="keywords" content="경찰공무원, 경찰공무원인강, 경찰학원, 경찰시험, 노량진경찰학원, 윌비스경찰, 윌비스경찰학원, 신광은경찰팀, 경찰프리패스, 신광은프리패스, 신광은경찰프리패스">
<link rel="stylesheet" href="http://www.willbescop.net//mobile/css/swiper.min.css">
<link rel="stylesheet" href="http://www.willbescop.net//mobile/css/style.css">
<script src="http://www.willbescop.net//mobile/js/lib/jquery-1.11.2.min.js"></script>
<script src="http://www.willbescop.net//mobile/js/common.js"></script>
<script type="text/javascript" src="http://www.willbescop.net/assets/js/login.js"></script>
<script language="javascript">
 top.document.title = "[윌비스경찰]신광은경찰팀"; 
</script>
<link rel="stylesheet" type="text/css" href="http://www.willbescop.net/assets/libs/colorbox/colorbox.css" />
</head>
<body>
<p id="accessibility"><a href="#container">본문바로가기</a></p>   
<div id="wrap">
    <div id="header">
        <div class="head">
            

            <h1><a href="http://www.willbescop.net//mobile/index.html?Mobile_Flag=Y"><img src="http://www.willbescop.net//mobile/img/h1_logo.png" alt="윌비스"></a></h1>
            <button type="button" class="opener"><span class="hidden">확장영역 열기</span></button>
            <div id="aside">
                <h2 class="skip">확장형 메뉴</h2>
                <button type="button" class="close"><span class="hidden">확장형 메뉴 닫기</span></button>
                <div class="logAr">
                 
                           
                        <div class="logBf">
                            <span class="ment">로그인이 필요합니다.</span>
                            <span class="bts"><a href="/login/M_login.html" class="btn Gren">로그인</a></span>
                        </div>
                    
                    
                    
                </div>
                <ul class="menu">
                    <li class="has"><a href="#">내 강의실</a>
                        <ul>
                            <li><a href="javascript:fn_mylecture('');">수강중인 강의</a></li>
                            <li><a href="javascript:fn_mylecture_will('');">수강예정 강의</a></li>
                            <li><a href="javascript:fn_mylecture_end('')">수강종료 강의</a></li>
                            <li><a href="javascript:fn_go_sloc('yearPkg');">프리패스</a></li>
                            <li><a href="javascript:fn_mycart('');">장바구니</a></li>
                            <li><a href="javascript:fn_mypaylist('');">주문내역</a></li>
                        </ul>
                    </li>
                    <li><a href="javascript:fn_freelecture();">무료특강</a></li>
                    <li><a href="javascript:fn_lecture();">수강신청</a></li>
                    <li><a href="javascript:fn_teacher_intro();">교수소개</a></li>
                    <li><a href="javascript:fn_go_loc('eventList');">이벤트</a></li>
                    <li><a href="javascript:fn_go_loc('lecReply');">수강후기</a></li>
                    <li class="has"><a href="">수험가이드</a>
                        <ul>
                            <li><a href="javascript:fn_guide();">수험가이드</a></li>
                            <li><a href="javascript:fn_go_loc('examNotice');">시험공고</a></li>
                            <li><a href="javascript:fn_go_loc('esamNews');">수험뉴스</a></li>
                        </ul>
                    </li>
                    <li class="has"><a href="#">고객센터</a>
                        <ul>
                            <li><a href="javascript:fn_go_loc('notice');">공지사항</a></li>
                            <li><a href="javascript:fn_go_loc('faq');">FAQ</a></li>
                            <li><a href="javascript:fn_go_loc('qna');">동영상 상담실</a></li>
                        </ul>
                    </li>
                    <!-- <li><a href="#">이용안내</a></li> -->
                </ul>
            </div>
            <script type="text/javascript">
            function fn_mylecture(id){
                if(id != ""){
                    var url = '/movieLectureInfo/ingList.html?LEFT_MENU_CD=2_1&pageCmd=ing&topMenuType=O&topMenuGnb=OM_009&topMenu=MAIN&topMenuName=ÀÏ¹Ý°æÂû&menuID=OM_009_002_001&Mobile_Flag=Y'
                    location.href = url;
                }else{
                    alert("로그인후 이용 가능합니다.");
                    return;
                }
            }

            function fn_mylecture_will(id){
                if(id != ""){
                    var url = '/movieLectureInfoWill/willList.html?LEFT_MENU_CD=2_1&topMenuType=O&pageCmd=will&topMenuType=O&topMenuGnb=OM_009&topMenu=MAIN&topMenuName=ÀÏ¹Ý°æÂû&menuID=OM_009_002_002&Mobile_Flag=Y'
                    location.href = url;
                }else{
                    alert("로그인후 이용 가능합니다.");
                    return;
                }
            }

            function fn_mylecture_end(id){
                if(id != ""){
                    var url = '/movieLectureInfoEnd/endList.html?LEFT_MENU_CD=2_1&topMenuType=O&pageCmd=end&topMenuType=O&topMenuGnb=OM_009&topMenu=MAIN&topMenuName=ÀÏ¹Ý°æÂû&menuID=OM_009_002_003&Mobile_Flag=Y'
                    location.href = url;
                }else{
                    alert("로그인후 이용 가능합니다.");
                    return;
                }
            }

            function fn_mycart(id){
                if(id != ""){
                    var url = '/cart/movie.html?topMenuType=O&topMenuGnb=OM_009&topMenu=MAIN&topMenuName=ÀÏ¹Ý°æÂû&menuID=OM_009_005_002&Mobile_Flag=Y'
                    location.href = url;
                }else{
                    alert("로그인후 이용 가능합니다.");
                    return;
                }
            }

			function fn_mypage() {
			
				alert("로그인후 이용 가능합니다.");
				return;
			
			}

			function fn_mypaylist(id){
                if(id != ""){
                    var url = '/pay/list.html?LEFT_MENU_CD=3_1&topMenu=MAIN&topMenuName=ÀÏ¹Ý°æÂû&topMenuType=O&topMenuGnb=OM_009&menuID=OM_009_005_001&Mobile_Flag=Y'
                    location.href = url;
                }else{
                    alert("로그인후 이용 가능합니다.");
                    return;
                }
            }

			function fn_freelecture(){
					var url = '/lecture/movieLectureFreeList.html?topMenu=081&topMenuName=ÀÏ¹Ý°æÂû&topMenuType=O&leftMenuLType=M0000&lecKType=F&FREE_TAB=TAB_001&Mobile_Flag=Y'
                    location.href = url;
            }

			function fn_lecture(){
				var url = '/lecture/movieLectureList.html?topMenu=081&topMenuName=ÀÏ¹Ý°æÂû&topMenuType=O&leftMenuLType=M0001&lecKType=D&Mobile_Flag=Y'
				location.href = url;
        	}

			function fn_teacher_intro(){
				var url = '/teacher/movieTeacherList.html?topMenu=081&topMenuType=O&topMenuGnb=OM_002&Mobile_Flag=Y'
				location.href = url;
        	}

			//배너링크 이동
			function fn_bannerLink(seq, val, link, flag){
		        if(link == "" || link == "/&Mobile_Flag=Y") {
		            alert("이동할 링크 주소가 존재하지 않습니다.");
		            return;
		        }
				$.ajax({
					type: "GET",
					url: "/updateBannerViewCount.json",
					data: "SEQ=" + seq,
					cache: false,
					dataType: "json",
					error: function (request, status, error) {
						alert("오류가 발생하였습니다.");
					},
					success: function (response, status, request) {
						if(response.RES =="SUCCESS") {
							if(val == 1){
								if(flag == "2"){
									window.open(link)									
								}else{
									top.location.href = link;									
								}
							}
							if(val == 2){
								var openNewWindow = window.open("about:blank");
								openNewWindow.location.href = link;
							}
						}
					}
				});
			}
			//배너클릭
			function fn_bannerClick(seq){
				$.ajax({
					type: "GET",
					url: "/updateBannerViewCount.json",
					data: "SEQ=" + seq,
					cache: false,
					dataType: "json",
					error: function (request, status, error) {
						alert("오류가 발생하였습니다.");
					},
					success: function (response, status, request) {
					}
				});
			}
            function fn_go_sloc(loc){
            
                alert("로그인후 이용 가능합니다.");
                return;
            
            }

            function fn_go_loc(loc){
                if(loc == 'lecReply') {
                    var url = "/lectureReply/list.html?Mobile_Flag=Y"
                            + "&topMenuType=O&topMenuGnb=OM_009&topMenu=MAIN&topMenuName=ÀÏ¹Ý°æÂû"
                            + "&LEFT_MENU_CD=5&INCTYPE=list";
                    location.href = url;
                } else if(loc == 'eventList') {
                    var url = "http://www.willbescop.net//mobile/board/list.html?topMenuType=O&topMenuGnb=OM_008&topMenu=MAIN&menuID=OM_008_003"
                            + "&BOARDTYPE=BT401&INCTYPE=list&BOARD_MNG_SEQ=&currentPage=1"
                            + "&SEARCHKIND=&SEARCHTEXT=";
                    location.href = url;
                } else if(loc == 'notice') {
                    var url = 'http://www.willbescop.net//mobile/board/list.html'
                            + '?topMenuType=O&topMenuGnb=OM_008&topMenu=MAIN&menuID=OM_008_001&topMenuName=ÀÏ¹Ý°æÂû'
                            + '&BOARDTYPE=BT101&INCTYPE=list&BOARD_MNG_SEQ=NOTICE_013&DIVICE_TYPE=MO&currentPage=1'
                            + '&SEARCHKIND=&SEARCHTEXT=';
                    location.href = url;
                } else if(loc == 'faq') {
                    var url = 'http://www.willbescop.net//mobile/boardReply/faq.html'
                            + '?topMenuType=O&topMenuGnb=OM_008&topMenu=MAIN&menuID=OM_008_008&topMenuName=ÀÏ¹Ý°æÂû'
                            + '&BOARDTYPE=BT102&INCTYPE=c_faq&BOARD_MNG_SEQ=FAQ_005&DIVICE_TYPE=MO&currentPage=1'
                            + '&SEARCHKIND=&SEARCHTEXT=';
                    location.href = url;
                } else if(loc == 'qna') {
                    var url = 'http://www.willbescop.net//mobile/boardReply/list.html'
                            + '?topMenuType=O&topMenuGnb=OM_008&topMenu=MAIN&menuID=OM_008_004&topMenuName=ÀÏ¹Ý°æÂû'
                            + '&BOARDTYPE=BT103&INCTYPE=c_list&BOARD_MNG_SEQ=&DIVICE_TYPE=MO&currentPage=1'
                            + '&SEARCHKIND=&SEARCHTEXT=';
                    location.href = url;
                } else if(loc == 'guide') {
                    var url = 'http://www.willbescop.net//mobile/board/list.html'
                            + '?topMenuType=O&topMenuGnb=OM_005&topMenu=MAIN&menuID=OM_005_001&topMenuName=ÀÏ¹Ý°æÂû'
                            + '&BOARDTYPE=BT201&INCTYPE=list&BOARD_MNG_SEQ=&DIVICE_TYPE=MO&currentPage=1'
                            + '&SEARCHKIND=&SEARCHTEXT=';
                    location.href = url;
                } else if(loc == 'examNotice') {
                    var url = 'http://www.willbescop.net//mobile/board/list.html'
                            + '?topMenuType=O&topMenuGnb=OM_005&topMenu=MAIN&menuID=OM_005_001&topMenuName=ÀÏ¹Ý°æÂû'
                            + '&BOARDTYPE=BT202&INCTYPE=list&BOARD_MNG_SEQ=NOTICE_009&DIVICE_TYPE=MO&currentPage=1'
                            + '&SEARCHKIND=&SEARCHTEXT=';
                    location.href = url;
                } else if(loc == 'esamNews') {
                    var url = 'http://www.willbescop.net//mobile/board/list.html'
                            + '?topMenuType=O&topMenuGnb=OM_005&topMenu=MAIN&menuID=OM_005_002&topMenuName=ÀÏ¹Ý°æÂû'
                            + '&BOARDTYPE=BT203&INCTYPE=list&BOARD_MNG_SEQ=NOTICE_010&DIVICE_TYPE=MO&currentPage=1'
                            + '&SEARCHKIND=&SEARCHTEXT=';
                    location.href = url;
                }
            }
            
            function fn_guide(){
                    var url = '/boardExamInfoOn/board_list.html?topMenu=081&topMenuType=O&topMenuGnb=OM_005&menuID=OM_005_001&BOARDTYPE=1&INCTYPE=list&BOARD_MNG_SEQ=NOTICE_009';
                    location.href = url;
            }
            </script>

            <!-- //aside -->
            <button type="button" class="my" onclick="javascript:fn_mylecture('');"><span class="hidden">내강의실</span></button>
        </div>
        <div class="gnb">
            <ul>
                <li><a href="javascript:fn_freelecture();">무료특강</a></li>
                <li><a href="javascript:fn_lecture();">수강신청</a></li>
                <li><a href="javascript:fn_teacher_intro();">교수소개</a></li>
                <li><a href="javascript:fn_go_loc('eventList');">이벤트</a></li>
            </ul>
        </div>
    </div>
    <!-- //header -->

<script type="text/javascript" src="http://www.willbescop.net/assets/js/login.js"></script>

    <div id="container">
        <div class="mS1">
            
            <a href='javascript:fn_bannerLink("118","1","/event/movie/event.html?event_cd=On_160803_p&amp;EventReply=Y&amp;topMenuType=O&amp;searchEventNo=1", "1")'><img src="http://file3.willbes.net/banner/20160808105724858.JPG" alt="1 - 탑배너"></a>
            
        </div>
        <!-- // mS1 -->
        <div class="mS2">
            <h3 class="skip">메인 슬라이딩 배너</h3>
            <!-- Swiper -->
            <div class="swiper-container">
                <div class="swiper-wrapper">
                
                    <a href='javascript:fn_bannerLink("964","1","/event/movie/event.html?event_cd=On_161230_p#main", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161230141128531.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("859","1","/event/movie/event.html?event_cd=On_161124_p&amp;topMenuType=O#main", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161220101613816.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("606","1","/event/movie/event.html?event_cd=On_161026_p", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161101165048405.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("454","1","/lecture/movieLectureDetail.html?topMenu=081&amp;topMenuType=O&amp;searchSubjectCode=1004&amp;searchLeccode=D201600766&amp;leftMenuLType=M0001&amp;lecKType=D", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161017112420632.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("930","1","/event/movie/event.html?event_cd=On_161226_p&amp;EventReply=Y&amp;topMenuType=O&amp;searchEventNo=41#main", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161228145237934.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("757","1","/lecture/movieLectureDetail.html?topMenu=081&amp;topMenuType=O&amp;searchSubjectCode=1005&amp;searchLeccode=D201600816&amp;leftMenuLType=M0001&amp;lecKType=D", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161129162143162.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("756","1","/lecture/movieLectureDetail.html?topMenu=081&amp;topMenuType=O&amp;searchSubjectCode=1003&amp;searchLeccode=D201600807&amp;leftMenuLType=M0001&amp;lecKType=D", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161129162054880.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("755","1","/lecture/movieLectureDetail.html?topMenu=081&amp;topMenuType=O&amp;searchSubjectCode=1002&amp;searchLeccode=D201600765&amp;leftMenuLType=M0001&amp;lecKType=D", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161129161839409.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("754","1","/lecture/movieLectureDetail.html?topMenu=081&amp;topMenuType=O&amp;searchSubjectCode=1002&amp;searchLeccode=D201600764&amp;leftMenuLType=M0001&amp;lecKType=D", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20161129161803280.jpg" alt="2 - 메인 배너"></a>
                
                    <a href='javascript:fn_bannerLink("367","1","/event/movie/event.html?event_cd=On_160804_p#freepass", "2");' class="swiper-slide" ><img src="http://file3.willbes.net/banner/20160926183704323.jpg" alt="2 - 메인 배너"></a>
                
                </div>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div>
        <!-- // mS2 -->
		<!-- M_compus add 2016-11-10 -->
		<div class="campusGo">
        	· <strong>전국캠퍼스</strong> 바로가기
            <select name="#" id="#" class="selTy1" onchange="window.open(this.value);" title="캠퍼스선택">
				<option value=""> 선택</option>
				<option value="http://www.willbescop.net/pass/main.html">노량진</option>
				<option value="http://sillim.willbes.net/pass/main.html" >신림</option>
				<option value="http://daegu.willbes.net/pass/main.html" >대구</option>
				<option value="http://busan.willbes.net/pass/main.html" >부산</option>
				<option value="http://gwangju.willbes.net/pass/main.html" >광주</option>
				<option value="http://jeju.willbes.net/pass/main.html" >제주</option>
			</select>
        </div>
        <div class="mS3">
            <h3 class="hTy1">신규강좌</h3>
            <!-- tab -->
            <div class="swiper-container">
                <ul class="swiper-wrapper">
                
                    <li class="swiper-slide"><a href='#' id="1004">형사소송법</a></li>
                
                    <li class="swiper-slide"><a href='#' id="1005">경찰학개론</a></li>
                
                    <li class="swiper-slide"><a href='#' id="1003">형법</a></li>
                
                    <li class="swiper-slide"><a href='#' id="1002">한국사</a></li>
                
                    <li class="swiper-slide"><a href='#' id="1001">영어</a></li>
                
                </ul>
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
            <!-- tab -->

            <!-- profListWp -->
            <div id="profListWp" class="profListWp">
                <ul class="profList">
                
                    <li><a href='javascript:fn_lectureDetail("1001","D201600805");'>
                        <span class="photo">
                            <span class="photoCir">
	                            
	                            
	                            	<img src="http://file3.willbes.net/member_upload/20160721193550735.png" alt="하승민" >
	                                                           
                                <span class="frames"><img src="http://www.willbescop.net//mobile/img/pframe.png" alt=""></span>
                            </span>
                        </span>
                        <span class="cont">
                            <span class="name">하승민 교수님</span>
                            <span class="tit1">&nbsp;</span>
                            <span class="tit2">2017년 1차대비 하승민 영어 기본이론(11월)</span>
                        </span>
                    </a></li>
                    
                    <li><a href='javascript:fn_lectureDetail("1001","D201600786");'>
                        <span class="photo">
                            <span class="photoCir">
	                            
	                            
	                            	<img src="http://file3.willbes.net/member_upload/20160721193550735.png" alt="하승민" >
	                                                           
                                <span class="frames"><img src="http://www.willbescop.net//mobile/img/pframe.png" alt=""></span>
                            </span>
                        </span>
                        <span class="cont">
                            <span class="name">하승민 교수님</span>
                            <span class="tit1">&nbsp;</span>
                            <span class="tit2">2017대비 하승민 영어 심화영어</span>
                        </span>
                    </a></li>
                    
                </ul>
            </div>
            <!-- // profListWp -->
        </div>
        <!-- // mS3 -->
        <div class="mS4">
        


            <ul class="tabs tabs3">
                <li class="active"><a href="#tabConts1">공지사항</a></li>
                <li><a href="#tabConts2">시험공고</a></li>
                <li><a href="#tabConts3">수험뉴스</a></li>
            </ul>
            <div class="tabConts">
                <div id="tabConts1">
                    <ul class="ntList">
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("notice","21637", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          [공지] 이용약관 변경 안내 (시행일 20...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-28</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("notice","21387", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          신광은 경찰팀 1단계 문제풀이 진도별교재...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-27</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("notice","18183", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          12/4 전국 모의고사 온라인 접수하기 ...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-13</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("notice","18211", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          
                          2017년 경찰공무원 채용시험 계획 공고
                          </span>
                    
                  
                          <span class="date">2016-12-13</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("notice","16292", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          2017년 1차대비 3단계 문제풀이 오픈...
                          
                          </span>
                    
                  
                          <span class="date">2016-11-29</span>
                          </a>
                        </li>
                    
                    </ul>
                </div>
                <!-- //tabConts1 -->
                <div id="tabConts2">
                    <ul class="ntList">
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNotice","21899", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          [공지] 2017년 국민안전처 해양경비안전...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-29</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNotice","18218", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          [공지] 2017년 경찰공무원 채용시험 계...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-13</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNotice","17360", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          [공지] 2016년 제2차 순경공채 필기시...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-06</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNotice","3593", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          2016년 2차 경찰공무원(순경)채용 필...
                          
                          </span>
                    
                  
                          <span class="date">2016-09-09</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNotice","3607", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          2016년 2차 경찰공무원(순경)채용 필...
                          
                          </span>
                    
                  
                          <span class="date">2016-09-09</span>
                          </a>
                        </li>
                    
                    </ul>
                </div>
                <!-- //tabConts2 -->
                <div id="tabConts3">
                    <ul class="ntList">
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNews","18270", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          경찰 채용 ‘우리 지역 얼마나 치열했나’...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-13</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNews","18271", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          2017년 경찰공무원 채용 “올해와 비슷...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-13</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNews","17325", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          해경 간부후보 필기시험 ‘3월 11일 실...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-06</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNews","17326", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          순경 2차 ‘합격선 1차 시험보다 낮아져...
                          
                          </span>
                    
                  
                          <span class="date">2016-12-06</span>
                          </a>
                        </li>
                    
                        <li>
                  
                    
                          <a href='javascript:fn_board_view("examNews","16446", "0", "MAIN", "ÀÏ¹Ý°æÂû");'>
                          <span class="tit">
                          경찰 채용 ‘우리 지역 얼마나 치열했나’...
                          
                          </span>
                    
                  
                          <span class="date">2016-11-30</span>
                          </a>
                        </li>
                    
                    </ul>
                </div>
                <!-- //tabConts3 -->
            </div>
 
        </div>
        <!-- //mS4 -->
        <div class="mS5">
            <div class="hWp"><h3 class="hTy1">무료특강</h3><a href="javascript:fn_freelecture();" class="btn More">더보기</a></div>
            <ul class="freeList">
            
                <li><a href='javascript:fn_bannerLink("3955","1","/event/movie/event.html?event_cd=On_160920_p&Mobile_Flag=Y", "3")'><img src="http://file3.willbes.net/banner/20160922130059989.jpg" alt="입문특강"></a></li>
            
                <li><a href='javascript:fn_bannerLink("860","1","/lecture/movieLectureFreeList.html?topMenu=081&amp;topMenuName=일반경찰&amp;topMenuType=O&amp;leftMenuLType=M0000&amp;lecKType=F&amp;FREE_TAB=TAB_002&Mobile_Flag=Y", "3")'><img src="http://file3.willbes.net/banner/20161216174345048.jpg" alt="핵심포인트 0원특강 "></a></li>
            
                <li><a href='javascript:fn_bannerLink("345","1","/lecture/movieLectureFreeList.html?topMenu=081&amp;topMenuName=일반경찰&amp;topMenuType=O&amp;leftMenuLType=M0000&amp;lecKType=F&amp;FREE_TAB=TAB_005&Mobile_Flag=Y", "3")'><img src="http://file3.willbes.net/banner/20160922103211485.jpg" alt="기출문제 해설특강"></a></li>
            
            </ul>
        </div>
        
        <style type="text/css">
			.mS5	.freeList_compus				{overflow:hidden;}
			.mS5	.freeList_compus li			{float:left;  text-align:center; width:16.6%;}
			.mS5	.freeList_compus li a		{display:block; padding:0.1em}
			.mS5	.freeList_compus li a img	{width:100%}
		</style>

		<!-- M_compus add 2016-11-10 -->
        <div class="mS5">
            <div class="hWp">
			<h3 class="hTy1">전국캠퍼스</h3><!--a href="#" class="btn More">더보기</a--></div>
            <ul class="freeList_compus">
            	<li><a href="http://www.willbescop.net/pass/main.html" target="_blank">노량진</a></li>
             	<li><a href="http://sillim.willbes.net/pass/main.html" target="_blank">신림</a></li>
			 	<li><a href="http://daegu.willbes.net/pass/main.html" target="_blank">대구</a></li>
			 	<li><a href="http://busan.willbes.net/pass/main.html" target="_blank">부산</a></li>
			 	<li><a href="http://gwangju.willbes.net/pass/main.html" target="_blank">광주</a></li>
			 	<li><a href="http://jeju.willbes.net/pass/main.html" target="_blank">제주</a></li>
            </ul>
        </div>
        <!-- M_compus add 2016-11-10 -->
        <!-- //mS5 -->
    </div>
    <!--// container  -->
<!-- Swiper JS -->
<script src="http://www.willbescop.net//mobile/js/lib/swiper.min.js"></script>
<!-- Initialize Swiper -->
<script>
var swiper = new Swiper('.mS2 .swiper-container', {
    nextButton: $('.mS2 .swiper-container').find('.swiper-button-next'),
    prevButton: $('.mS2 .swiper-container').find('.swiper-button-prev'),
    spaceBetween: 0,
    loop: true,
    autoplay: 2500,
});

var swiper2 = new Swiper('.mS3 .swiper-container', {
    nextButton: $('.mS3 .swiper-container').find('.swiper-button-next'),
    prevButton: $('.mS3 .swiper-container').find('.swiper-button-prev'),
    slidesPerView: 4,
    spaceBetween: 0,
    loop: true
});

$(function(){
    $('.mS3 .swiper-slide a').click(function(){
        $('.mS3 .swiper-slide').removeClass('swiper-slide-active');
        $(this).parent().addClass('swiper-slide-active');

        set_subject($(this).attr('id'));

        return false;
    });
});

</script>



    <div id="footer">
        <div class="link">
        
            <a href="/login/M_login.html">로그인</a>
            <span class="bar">|</span>
            <a href="/user/memberEntryProvision.html?topMenuType=O">회원가입</a>
            <span class="bar">|</span>
                 
                 
            <a href="/main/index.html" target="_blank">PC버전 (동영상)</a>
            <span class="bar">|</span>
            <a href="/pass/main.html" target="_blank">PC버전 (학원실강)</a>
        </div>
        <p class="info">윌비스 사이버 아카데미 : 사업자등록번호 119-85-23089</p>
        <p class="copy">Copyright © 2015 (주)윌비스, All Rights Reserved.
    </div>
    <!--//footer  -->   
</div>
  <script type="text/javascript">
		$(document).ready(function(){
			$('a').css('cursor', 'default')
			$('a').unbind('click');
			$('a').click(function(){
			 return false;
			});
			$('button').css('cursor', 'default');
			$("button").attr("disabled",true);
			
			if('${params.IS_PREVIEW}'=='5'&&'${params.PREVIEW_BANNER_NO}'!=''){
				var banner = '${params.PREVIEW_BANNER_NO}';
				switch(banner) {
				    case '1':
				    	$(".mS1").css('border', '3px solid red'); 
				        break;
				    case '2':
				    	$(".mS2").css('border', '3px solid red'); 
				        break;
				    case '3':
				        break;
				    case '4':
				        break;
				    case '5':
				        break;
				    case '6':
				        break;
				    case '7':
				    	$(".mS5").css('border', '3px solid red'); 
				    	var scrollY = $(".mS5").offset().top;
				    	 $("html, body").animate({scrollTop:scrollY}, "slow");
				        break;
				}
			}
		});
    </script>
</body>
</html>

