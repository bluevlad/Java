<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page language="java"%>
<script src="${CPATH}/js/lib.paging.js"></script>
<script>
	// 페이징 라이브러리 사용하기
	PG = new Paging(300);	// 총 300개글

	//설정변경
	//PG.config = {
	//	thisPageStyle: 'font-weight: bold; color:#33B7FB',
	//	//otherPageStyle: 'color: #000000',
	//	itemPerPage: 15,	// 리스트 목록수
	//	pagePerView: 15		// 페이지당 네비게이션 항목수
	//};

	// 한두개 설정만 바꿀때는 이렇게도..
//	PG.config.prevIcon = '/image/icon/prev.gif';
//	PG.config.nextIcon = '/image/icon/next.gif';

	document.write(PG);
</script>
