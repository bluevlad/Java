<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<TITLE>(주) UBQ 찾아오시는 길 - 네이버 지도 (이동/확대 기능 그대로 사용가능)</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<meta name="author" content="(주)UBQ">
<meta name="description" content="UBQ">
<meta name="keywords" content="UBQ">
<script type="text/javascript" src="http://map.naver.com/js/naverMap.naver?key=640bedefadd549d92977ca86e02bf421"></script>
<link rel="stylesheet" href="link.css">
</HEAD>
<BODY>
<!-- 네이버지도 시작 -->
ㅇ 주소 :  (우) 425-906&nbsp;&nbsp; 경기도 안산시 단원구 고잔동 708-2 삼영타운 401호&nbsp;<BR>
ㅇ 자가용 이용시 : 영동고속도로 안산IC 에서 안산시청으로 직진해서 안산법원 근처 홈플러스(구 홈에버) 옆 건물<BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;로데오 사거리 우측 첫건물  (1층 던킨도너츠, 2층 미스터피자 ) <BR>
ㅇ 지하철 이용시 : 4호선 고잔역에서 도보로 15분 소요 - 홈플러스(구 홈에버) 방향<BR><BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="(주)유비큐 위치표시 " onclick="showMark();">&nbsp;&nbsp;<input type="button" value="(주)유비큐 위치이동" onclick="moveLatLng();"><BR><BR>
<div id="map"></div>
<div id="display"></div>
<script type="text/javascript">
/*
 * 지도API 2.0은 기존의 카텍 좌표 외에도 위경도 좌표를 지원합니다.
 * 위경도 좌표를 사용하기 위해서는 기존의 NPoint 클래스 대신 NLatLng 클래스를 사용해야 합니다.
 *
 * http://maps.naver.com/api/geocode.php 에서 "경기도성남시정자1동25-1"을 검색한 결과인
 * x : 321033, y : 529749
 * 를 예로 들어 설명해 보겠습니다.
 *
 * 편의를 위해 전역변수로 mapObj, tm128, latlng를 선언해 두었습니다.
 */
var mapObj = new NMap(document.getElementById('map'),800,600);
var tm128 = new NPoint(296397,523772);
var latlng;
/*
 * 경기도성남시정자1동25-1의 위치로 이동합니다. 레벨은 1로 지정하였습니다.
 * 인덱스맵과 확대/축소 컨트롤러를 등록하고 마우스 줌인/아웃을 활성화 하였습니다.
 */
mapObj.setCenterAndZoom(tm128, 4);
mapObj.addControl(new NIndexMap());
mapObj.addControl(new NZoomControl());
mapObj.enableWheelZoom();

/*
 * 정자1동25-1의 tm128 좌표를 위경도로 변경하여 display div에 표시하도록 하였습니다.
 * 위경도를 tm128 좌표로 변경하려면 fromLatLngToTM128 메소드를 사용해야 합니다.
 */            
function transFromTM128ToLatLng()
{
    latlng = mapObj.fromTM128ToLatLng(tm128);
    //document.getElementById("display").innerHTML = latlng;
}
/*
 * NPoint가 사용되는 곳이면 NLatLng을 대신해서 사용할 수 있습니다.
 */            
function moveLatLng()
{
    mapObj.setCenter(latlng);
}
/*
 * NMark도 마찬가지로 tm128 대신 위경도를 사용하여 아이콘을 표시하였습니다.
 */            
function showMark()
{
    transFromTM128ToLatLng()
    var mark = new NMark(latlng, new NIcon('http://static.naver.com/maps/ic_spot.png',new NSize(52,41),new NSize(14,40)));
    mapObj.addOverlay(mark);
}

/*
 * NLatLng에 위경도가 아닌 tm128 좌표를 넣게되면 예상치 못한 결과가 나오게 됩니다.
 * 카텍 좌표는 NPoint, 위경도 좌표는 NLatLng를 사용해야 함을 잊지마세요.
 * 그리고 NLatLng는 (lat, lng)를 아규먼트로 받습니다.
 * 위도(y), 경도(x)의 순으로 아규먼트를 넣어야 합니다. 이점도 주의하세요.
 */            
function moveError()
{
    var errPoint = new NLatLng(tm128.y, tm128.x);
    mapObj.setCenter(errPoint)
}
</script>
<!-- 네이버지도 끝 -->
 </BODY>
</HTML>
