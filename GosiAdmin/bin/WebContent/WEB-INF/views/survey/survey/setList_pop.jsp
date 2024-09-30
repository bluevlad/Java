<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
</head>

  <!--content -->
	<h2>● 온라인설문 > <strong>세트정보</strong></h2>

    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

	<input type="hidden" id="S_MENU" name="S_MENU" value="${params.S_MENU}" />
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
	<input type="hidden" id="SETID" name="SETID" value="" />

    <table class="table04">
    	<tr>
        	<th width="10%">검색</th>
            <td width="50%">            
				<label for="SEARCHKIND"></label>
				설문세트제목
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_checkEnter()">
		    <th width="15%">화면출력건수</th>
		    <td width="30%">               
               	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
          </tr>
	</table>
    
    <!--//테이블-->
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    <!--테이블-->
          
	<table class="table02">
                <tr>
                  <th width="10%">NO</th>
                  <th>제목</th>
                  <th width="15%">문항수</th>
                  <th width="15%">사용여부</th>
                </tr>
              <tbody>
              <c:if test="${not empty list}">
	              <c:forEach items="${list}" var="list" varStatus="status">
		                <tr>
		                  <td>${totalCount-((params.currentPage-1)*params.pageRow)-status.index}</td>
		                  <td><a href="javascript:fn_sel('${list.SETID}','${list.SETTITLE}');">${list.SETTITLE}</a></td>
		                  <td>${list.CNT}</td>
		                  <td>${list.ISUSE}</td>
		                </tr>
			        </c:forEach>
				</c:if>
				<c:if test="${empty list}">
		            <tr bgColor=#ffffff align=center> 
						<td colspan="4">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>	 
                </tbody>
            </table>
          
          <!--//테이블--> 
	    <!-- paginate-->
		    <c:if test="${not empty list}">
				<c:out value="${pagingHtml}" escapeXml="false" />
			</c:if>
	   <!--//paginate-->
		</form> 
	</div>
	</table>
  <!--//content -->
<script type="text/javascript">

//엔터키 검색
function fn_checkEnter(){
	$('#SEARCHTEXT').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
	
	$('#pageRow').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/survey/survey/setList.pop"/>').submit();
}

//상세
function fn_sel(setid, setnm) {
	$(opener.document).find("#SETID").val(setid);
	$(opener.document).find("#SETTITLE").val(setnm);
}
</script>
  </html>