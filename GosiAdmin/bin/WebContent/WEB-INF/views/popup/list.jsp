<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
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
//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		goList(1);
		return;
	}
	if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#pageRow").val("${params.pageRow}");
    }
}

function fn_IsNumber(input) {
    var chars = "0123456789";
    for (var inx = 0; inx < input.value.length; inx++) {
        if (chars.indexOf(input.value.charAt(inx)) == -1)
            return false;
     }
     return true;
}
//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/popup/list.do"/>').submit();
}

//등록
function fn_add() {
	$('#searchFrm').attr('action','<c:url value="/popup/write.do"/>').submit();
}

//삭제
function checkDelete() {
	var tmp =0; 
	$("input[name=DEL_ARR]:checked").each(function (index){
		tmp++;
	});
	if(tmp == 0){
		alert("대상이 선택되지 않았습니다.");
		return;
	}
	if(confirm("삭제하시겠습니까?")) {		
		$('#searchFrm').attr('action','<c:url value="/popup/allDelete.do"/>').submit();
	}
}
//전체적용
function checkOPENY() {
	var tmp =""; 
	$("input[name=DEL_ARR]:checked").each(function (index){
		var val = $(this).val();
		var split = val.split(",");
		tmp += "'"+split[0]+"'" + ",";
	});
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("대상이 선택되지 않았습니다.");
		return;
	}
	if(confirm("적용하시겠습니까?")) {		
		var last = tmp.lastIndexOf(',');
		tmp = tmp.substr(0,last);
		$("#OPEN_YN_ARR").val(tmp);
		$('#searchFrm').attr('action','<c:url value="/popup/openY.do"/>').submit();
	}
}
//전체미적용
function checkOPENN() {
	var tmp =""; 
	$("input[name=DEL_ARR]:checked").each(function (index){
		var val = $(this).val();
		var split = val.split(",");
		tmp += "'"+split[0]+"'" + ",";
	});
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("대상이 선택되지 않았습니다.");
		return;
	}
	if(confirm("미적용하시겠습니까?")) {		
		var last = tmp.lastIndexOf(',');
		tmp = tmp.substr(0,last);
		$("#OPEN_YN_ARR").val(tmp);
		$('#searchFrm').attr('action','<c:url value="/popup/openN.do"/>').submit();
	}
}

// 코드 상세
function view(no) {
	$("#NO").val(no);
	$('#searchFrm').attr('action','<c:url value="/popup/modify.do"/>').submit();
}
</script>
</head>

  <!--content -->
  <div id="content">
	<h2>● 사이트관리 > <strong>팝업 관리</strong></h2>
    
    <!--테이블-->    
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
    <input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
	<input type="hidden" id="deleteIds" name="deleteIds">
	<input type="hidden" id="USERID" name="USERID" value="${AdmUserInfo.USERID}" />
	<input type="hidden" id="NO" name="NO" value="" />
	<input type="hidden" id="OPEN_YN_ARR" name="OPEN_YN_ARR" value="" />
	
	<input type="hidden" id="ONOFF_DIV" name="ONOFF_DIV" value="${params.ONOFF_DIV}" />
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
	
    
      <table class="table01">
          <tr>
            <th width="10%">검색</th>
            <td width="50%">            

						<label for="SEARCHKIND"></label>
						<select name="SEARCHKIND" id="SEARCHKIND">
							<option value="">--전체검색--</option>
							<c:forEach items="${categoryList}" var="item" varStatus="loop">
								<option value="${item.CODE}" <c:if test="${params.SEARCHKIND== item.CODE }">selected="selected"</c:if>>${item.NAME}</option>					
							</c:forEach>
						</select>
						<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_checkEnter()">
				<input type="button"   onclick="goList(1)"  value="검색" />	
		    <th width="15%">화면출력건수</th>
		    <td width="30%">               
	                	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
		                <input type="button"   onclick="goList(1)"  value="검색" />
            </td>
          </tr>
      </table>
    
    <!--//테이블-->
    			<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${params.currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
                <%-- <div style="float:left; width:100%; text-align:right; margin-top:30px;">
                	<span ><strong>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</strong></span>&nbsp;
                </div> --%>
                <!--//버튼--> 
    <!--테이블-->
          
            <table class="table02">
                <tr>
                  <th width="85">
                      <input id="allCheck"  value="" type="checkbox" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />No
                  </th>
                  <th width="10%">등록일</th>
                  <th>직렬</th>
                  <th>썸네일</th>
                  <th width="25%">제목</th>
                  <th>시작일</th>
                  <th>종료일</th>
                  <th>적용여부</th>
                  <th>뷰카운트</th>
                </tr>
              <tbody>
              <c:if test="${not empty list}">
	              <c:forEach items="${list}" var="list" varStatus="status">
		                <tr>
		                  <td>
			                    <input  name="DEL_ARR"  value="${list.NO},${list.POPUP_IMG},${list.THUMBNAIL}" type="checkbox" />
			              		${totalCount-((params.currentPage-1)*params.pageRow)-status.index}
			              </td>
		                  <td>${list.REG_DT}</td>
		                  <td>
		                  		<c:choose>
							    	<c:when test="${list.ISALL == 'Y'}">전체</c:when>
							    	<c:otherwise>
							    		<c:forEach items="${list.CODENAME}"  var="codeData" varStatus="status" >
								    		<c:out value="${codeData}"/><br>
							    		</c:forEach>
							    	</c:otherwise>
							   </c:choose>
		                  </td>
		                  <td><a href="javascript:view('${list.NO}')"><img src="<c:url value="/imgFileView.do?path=${list.THUMBNAIL}"/>" width="150px" height="100px"></a></td>
		                  <td><a href="javascript:view('${list.NO}')">${list.TITLE}</a></td>
		                  <td>${list.START_DATE}</td>
		                  <td>${list.END_DATE}</td>
		                  <td>${list.OPEN_YN_NM}</td>
		                  <td><fmt:formatNumber value="${list.HIT}" type="number"/></td>
		                </tr>
			        </c:forEach>
				</c:if>
				<c:if test="${empty list}">
		            <tr bgColor=#ffffff align=center> 
						<td colspan="9">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>	 
                </tbody>
            </table>
         </form> 
          
          <!--//테이블--> 
   		<!--버튼-->
    
	    <ul class="boardBtns">
	   	  <li><a href="javascript:fn_add();">등록</a></li>
	   	  <li><a href="javascript:checkOPENY();">적용</a></li>
	   	  <li><a href="javascript:checkOPENN();">미적용</a></li>
	        <li><a href="javascript:checkDelete();">삭제</a></li>
	    </ul>
    <!--버튼-->
	    <!-- paginate-->
		    <c:if test="${not empty list}">
				<c:out value="${pagingHtml}" escapeXml="false" />
			</c:if>
	   <!--//paginate-->
	</div>
  <!--//content --> 