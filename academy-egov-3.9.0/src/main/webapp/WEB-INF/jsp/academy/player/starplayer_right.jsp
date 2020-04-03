<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="utf-8" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.8.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui/jquery-ui-1.9.1.custom.js'/>"></script>
<link href="<c:url value="/resources/css/player/player.css" />" rel="stylesheet" type="text/css"  />
<style>
.paginate_complex{padding:20px 0;line-height:normal;text-align:center; float:left; width:100%;}
.paginate_complex a,
.paginate_complex strong{display:inline-block;position:relative;z-index:2;margin:0 -3px;padding:1px 8px;border-left:1px solid #d6d6d6;border-right:1px solid #d6d6d6;background-color:#fff;font:bold 12px/16px Gulim, Tahoma, Sans-serif;color:#323232;text-decoration:none;vertical-align:top}
.paginate_complex a:hover,
.paginate_complex a:active,

.paginate_complex strong{color:#ff8600}
.paginate_complex .direction{border:0;font-weight:normal;color:#767676;text-decoration:none !important;z-index:1}
.paginate_complex .direction:hover,
.paginate_complex .direction:active,
.paginate_complex .direction:focus{color:#323232;background-color:#fff}
.paginate_complex .prev{border-left:0}
.paginate_complex .next{border-right:0}
.paginate_complex .direction span{display:inline-block;position:relative;top:4px;width:0;height:0;font-size:0;line-height:0;vertical-align:top}
.paginate_complex .prev span{*left:-4px;margin-right:1px;border:3px solid;border-top:solid #fff;border-bottom:solid #fff;border-left:0}
.paginate_complex .next span{margin-left:1px;border:3px solid;border-top:solid #fff;border-bottom:solid #fff;border-right:0}

.paginate_complex a {background:#000000; color:#FFFFFF;}
</style>
</head>
<body>
<form name=myform method="post" action="" target="fmLeft" style="margin:0" >
<input type="hidden" name="orderno" value="">
<input type="hidden" name="leccode" value="${params.LECCODE}">
<input type="hidden" name="samplecount" value="">
<input type="hidden" name="movie_no" >
<input type="hidden" name="bgcolor" id="bgcolor">	
	<c:if test="${params.Quality == 'Wide'}">
		<div class="contWp widePlayer2Cont">
	</c:if>
	<c:if test="${params.Quality == 'high_Q'}">
		<div class="contWp highPlayerCont">
	</c:if>
	<c:if test="${params.Quality == 'low_Q'}">
		<div class="contWp lowPlayerCont">
	</c:if>

<div class="listHead">
	<table>		
			<tbody>
				<tr>
					<td class="th1"><span>회차</span></td>
					<td class="th2"><span>강의명</span></td>
					<td class="th3"><span>자료</span></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<c:set var="MOVIE_URL" value=""/>
	<div class="listBody">
		<table>
			<caption>강의목록 리스트</caption>
			<tbody>
			<c:forEach items="${list}" var="item" varStatus="loop">
				<c:if test="${item.MOVIE_NO eq params.MOVIE_NO}">
					<tr>
						<td class="td1">${(totalCount +1)-(totalCount - (( params.currentPage - 1) * params.pageRow) - loop.index)}강 <%-- ${item.MOVIE_ORDER2}강 --%></td>
						
						
						
					<c:if test="${params.FLAG eq 'high_Q' }">
					    <c:choose>
					    <c:when test="${item.WIDE_URL ne ''}"><c:set var="MOVIE_URL" value="${item.WIDE_URL}"/></c:when>
					    <c:otherwise><c:set var="MOVIE_URL" value="${item.MOVIE_URL}"/></c:otherwise>
					    </c:choose>
					</c:if>
					<c:if test="${params.FLAG eq 'low_Q' }">
					   <c:set var="MOVIE_URL" value="${playerinfo.MP4_URL}"/>
					</c:if>

						<td class="td2">
							<input type="hidden" name="MOVIE_NAME_${item.MOVIE_NO}" id="MOVIE_NAME_${item.MOVIE_NO}" value="${item.MOVIE_NAME}" >
							<c:if test="${params.MOVIE_NO eq item.MOVIE_NO}"><b>${item.MOVIE_NAME}</b></c:if><c:if test="${params.MOVIE_NO ne item.MOVIE_NO}">${item.MOVIE_NAME}</c:if>
						</td>
						<td class="td3">
						<c:if test="${!empty item.MOVIE_DATA_FILENAME and item.MOVIE_DATA_FILE_YN eq 'Y'}">
							<c:if test="${params.LEARNING_CD eq 'M0000' || params.LEARNING_CD eq 'M0005'}">
								<a href="javascript:fn_FileDownload('${item.MOVIE_DATA_FILENAME}','${item.MOVIE_NO}');"><span class="file">자료</span></a>
							</c:if>
							<c:if test="${params.LEARNING_CD ne 'M0000' && params.LEARNING_CD ne'M0005'}">
								<span class="file">자료</span>
							</c:if>
						</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			</tbody>
		</table>
	</div>

</form>
<form name=myform method="post" action="" target="fmLeft" style="margin:0" >
	<input type="hidden" name="orderno" value="">
	<input type="hidden" name="leccode" value="">
	<input type="hidden" name="samplecount" value="">
	<input type="hidden" name="movie_no" >
	<input type="hidden" name="bgcolor" id="bgcolor">	
</form>
<form id="pageform" name="pageform"  method="post" action="" target="fmRight" style="margin:0">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.currentPage}">
<input type="hidden" name="LECCODE" value="${params.LECCODE}">
<input type="hidden" name="BRIDGE_LECCODE" value="${params.BRIDGE_LECCODE}">
<input type="hidden" name="MOVIE_NO" value="${params.MOVIE_NO}">
</form>

<script language="JavaScript">
//페이징
function goList(page) {
	url = "/player/view_right.pop2?LECCODE=${params.LECCODE}&BRIDGE_LECCODE=${params.BRIDGE_LECCODE}&MOVIE_NO=${params.MOVIE_NO}&GUBUN=${params.GUBUN}&PAGE=1&position=1&samplecount=1&currentPage="+page;
	//alert("goList : "+url);
	$(parent.document).find("#fmRight").attr("src", url);	
	
}

function fn_DoMovie(mno, i){
	url = "/player/view_left.pop2?LECCODE=${params.LECCODE}&BRIDGE_LECCODE=${params.BRIDGE_LECCODE}&GUBUN=${params.GUBUN}&&BROWSER=${params.BROWSER}&Quality=${params.Quality}&MOVIE_NO="+mno;
	//alert("fn_DoMovie : "+url);
	$(parent.document).find("#fmLeft").attr("src", url); 
}

//파일 다운로드
function fn_FileDownload(path){
	location.href = "<c:url value='/download.do' />?path=" + path;
}
</script>
</body>
