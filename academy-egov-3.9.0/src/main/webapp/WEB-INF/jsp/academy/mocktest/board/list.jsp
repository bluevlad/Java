<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">

//숫자 입력 폼
function chk(obj){
	var val = obj.value;
	if (val) {
		if (val.match(/^\d+$/gi) == null) {
			$('#pageRow').val("");
			$('#pageRow').focus();
			return;
			}
		else {
			if (val < 1) {
				$('#pageRow').val("");
				$('#pageRow').focus();
				return;
				}
			}
		}
}

//엔터키 검색
function fn_checkEnter(){
	/* $('#searchKeyWord').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});

	$('#pageRow').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	}); */
}

function goList(page) {
	alert("준비중 입니다.");

//	if(typeof(page) == "undefined") $("#currentPage").val(1);
//	else $("#currentPage").val(page);
//	$("#form").attr("action", "<c:url value='/offExamReg/offExamList.do' />");
//	$("#form").submit();
}
function goBoardView(boardId){
	alert("준비중 입니다.");
//	var searchText = $("#searchText").val();
//	var searchType = $("#searchType  option:selected").val();
//	var pageRow = $("#pageRow").val();

//	if(typeof(searchText) == "undefined") $("#searchText").val("");

//	location.href='<c:url value="/offExamReg/offExamView.do"/>' + '?mockCode='+mockcode+'&currentPage='+${currentPage}+'&pageRow='+pageRow+'&searchText='+searchText+'&searchType='+searchType;
}
</script>
</head>

<div id="content">
	<div id="stitle">
		<ul id="stitle_l"><img src="<c:url value="/resources/img/common/icon_stitle01_02.png"/>" align="absmiddle" />모의고사 공고</ul>
		<ul id="stitle_r">* Home > 게시판관리 > 모의고사 공고</ul>
	</div>
	<!--테이블-->
	<div class="form_table_s" style="margin-top:30px; float:left; width:100%;">
		<form name="searchFrm" id="form" action="<c:url value="/offExamReg/offExamList.do"/>" method="post">
		<table class="tbl_type" border="1" cellspacing="0" summary="모의고사 리스트">
		<caption></caption>
		<colgroup>
			<col width="15%">
			<col width="85%">
		</colgroup>
		<thead>
		<tr>
            <th height="30" scope="col">검색</th>
			<td scope="col" style="text-align:left;">
				<div class="item" style="margin-left:0px;">

					<input type="hidden" name="_method" value="get">
					<select style="width:100px;" name="searchCategory" id="searchCategory">
						<option value="1" >--대분류--</option>
						<option value="2" >9급공무원</option>
						<option value="3" >9급공무원</option>
						<option value="4" >7급공무원</option>
						<option value="5" >세무관세직</option>
						<option value="6" >법원직</option>
					</select>
					<select style="width:100px;" name="searchKind" id="searchKind">
						<option value="1" >--검색--</option>
						<option value="1" >제목</option>
						<option value="1" >이름</option>
						<option value="2" >내용</option>
					</select>
	                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<input class="i_text" title="레이블 텍스트" type="text" name="searchText" id="searchText" style="width:160px;" value="${searchText }">
	            	<span class="btn_pack medium" style="vertical-align:middle;"><button type="button" onclick="goList(1)">검색</button></span>

            	</div>

            </td>
		</tr>
     	</thead>
     	<thead>
<%--           <tr>
            <th height="30" scope="col">화면출력건수</th>
            <td scope="col" style="text-align:left;">
	            <div class="item" style="margin-left:0px;">
	                <input class="i_text"  title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2" style="width:20px;ime-mode:disabled;" onkeyup="chk(this)" onblur="chk(this)" value="${pageRow}"  onkeypress="fn_checkEnter()">
	                &nbsp;&nbsp;
	                <span class="btn_pack medium" style="vertical-align:middle;"><button type="button"   onclick="goList(1)">검색</button></span>
				</div>
			</td>
          </tr> --%>
        </thead>
     	<tbody>
     	</tbody>
     	</table>
     	</form>
     </div>
     <!--//테이블-->


	<div class="form_table" style="margin-top:10px; float:left; width:100%; margin-left:0px;">
		<table class="tbl_type" border="1" cellspacing="0" summary="모의고사 리스트" style="min-width:800px;">
		<caption></caption>
		<colgroup>
			<col width="7%">
			<col width="8%">
			<col width="50%">
			<col width="">
			<col width="">
			<col width="">
			<col width="">
		</colgroup>
		<thead>
		<tr>
			<th scope="col">No</th>
			<th scope="col">구분</th>
			<th scope="col">제목</th>
			<th scope="col">첨부</th>
			<th scope="col">작성자</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${not empty list}">
		<form id="form" name="form" method="post">
			<input type="hidden" id="currentPage" name="currentPage">
			<c:forEach items="${list}" var="data" varStatus="status">
	        <tr>
				<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
	            <td>9급</td>
				<td style="text-align:left;">
					<a href='javascript:goBoardView("${data.BCONTENTID}");'>
	            		<c:out value="${data.TITLE}"/>
	            	</a>
	            </td>
					<td bgcolor="#feffec"><c:if test="${data.ATTACHFILEID eq null}"><img src="<c:url value="/resources/img/common/icon_disk01.png"/>" width="16" height="16" /></c:if></td>
				<td>
						<c:out value="${data.REG_ID}"/>
				</td>
				<td>
						<fmt:formatDate value="${data.REG_DT}" pattern="yyyy-MM-dd" />
				</td>
				<td><c:out value="${data.HITS}"/></td>
	        </tr>
			</c:forEach>
		</form>
		</c:if>
		<c:if test="${empty list}">
            <tr bgColor=#ffffff align=center>
				<td colspan="7">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
              </tbody>
            </table>
    </div>
	<!--paging  -->
	<c:if test="${not empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
    <!-- //paging  -->
    <!--빈공간-->
    <div style="float:left; width:100%; height:30px;"></div>
    <!--//빈공간-->
</div>
<!--//content -->

</html>