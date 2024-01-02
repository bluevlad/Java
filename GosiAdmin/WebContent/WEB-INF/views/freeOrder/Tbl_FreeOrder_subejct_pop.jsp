<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
//var MOCKCODE = "${searchMap.MOCKCODE}";
//alert("MOCKCODE:"+MOCKCODE);
//var message = "${searchMap.message}";

var keyword = "${searchMap.keyword}";
var p_currentPage = "${searchMap.p_currentPage}";
var p_pageRow = "${searchMap.p_pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	 /* if(message != "") {
		alert(message);
	} */
	
	/* alert(	"keyword:"+keyword +"\n"+
			"p_currentPage:"+p_currentPage +"\n"+
			"p_pageRow:"+p_pageRow +"\n"+
			"TOP_MENU_ID:"+TOP_MENU_ID +"\n"+
			"MENUTYPE:"+MENUTYPE +"\n"+
			"L_MENU_NM:"+L_MENU_NM); */
	
	/* alert("S_EXAMYEAR:"+S_EXAMYEAR +"\n"+
	"S_EXAMROUND:"+S_EXAMROUND +"\n"+
	"S_searchFlag:"+S_searchFlag +"\n"+
	"S_searchKeyWord:"+S_searchKeyWord +"\n"+
	"S_currentPage:"+S_currentPage +"\n"+
	"S_pageRow:"+S_pageRow); */
}

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
	 $('#search_keyword').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//검색
function goList(page) {	
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	
	$('#searchFrm').attr('action','<c:url value="/freeOrder/pop_subject_add.pop"/>').submit();
}

//강의명 클릭
function put_leccode(leccode)
{
 window.opener.popFrm.leccode.value=leccode;
 window.opener.popFrm.keyword.value=keyword;
 window.opener.popFrm.currentPage.value=p_currentPage;
 window.opener.popFrm.pageRow.value=p_pageRow;
 window.opener.popFrm.TOP_MENU_ID.value=TOP_MENU_ID;
 window.opener.popFrm.MENUTYPE.value=MENUTYPE;
 window.opener.popFrm.L_MENU_NM.value=L_MENU_NM;
 window.opener.save();
 window.self.close();
}

</script>
</head>
  <!--content -->
  <div>
  
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">

	<input type="hidden" id="userid" name="userid" value="${searchMap.userid}">	
	
	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}">
	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}">
	
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
	
	<input type="hidden" id="keyword" name="keyword" value="${searchMap.keyword}" />
	<input type="hidden" id="p_currentPage" name="p_currentPage" value="${searchMap.p_currentPage}" />
	<input type="hidden" id="p_pageRow" name="p_pageRow" value="${searchMap.p_pageRow}" />
	
   <!--테이블--> 
      <table class="table01">
          <tr>
            <th width="15%">검색</th>
            <td>            
            	<select id="s_cat_cd" name="s_cat_cd">
					<option value="">--전체검색--</option>
				<c:forEach items="${category_list}"  var="category_list">
					<option value="${category_list.CODE }" <c:if test="${searchMap.s_cat_cd == category_list.CODE}">selected</c:if>>${category_list.NAME }</option>
				</c:forEach>
				</select>
					
				<select id="s_menu_id" name="s_menu_id">
					<option value="">--전체검색--</option>
				<c:forEach items="${lec_list}"  var="lec_list">
					<option value="${lec_list.CODE }" <c:if test="${searchMap.s_menu_id == lec_list.CODE}">selected</c:if>>${lec_list.NAME }</option>
				</c:forEach>
				</select>
				
				<select id="s_sjt_cd" name="s_sjt_cd">
					<option value="">--전체검색--</option>
				<c:forEach items="${subject_list}"  var="subject_list">
					<option value="${subject_list.SUBJECT_CD }" <c:if test="${searchMap.s_sjt_cd == subject_list.SUBJECT_CD}">selected</c:if>>${subject_list.SUBJECT_NM }</option>
				</c:forEach>
				</select>
				
				<select id="search_type" name="search_type">
					<option value="1" <c:if test="${searchMap.search_type == '1'}">selected</c:if>>강사명</option>
					<option value="2" <c:if test="${searchMap.search_type == '2'}">selected</c:if>>강의코드</option>
					<option value="3" <c:if test="${searchMap.search_type == '3'}">selected</c:if>>강의명</option>
			   	</select>
				
				<input  title="검색" size="15" type="text" id="search_keyword" name="search_keyword"  value="${searchMap.search_keyword}" style="ime-mode:active;" onkeypress="fn_checkEnter()">
				
				<input type="button"   onclick="goList(1)"  value="검색" />
		    </td>
          </tr>
      </table>
    
    <!--//테이블-->	
       
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    
    
    <!--테이블-->
     <table class="table02">
        <tr>
          	<th width="7%">No</th>
          	<th>활성/비활성</th>
			<th>강의코드</th>
			<th>직종</th>
			<th>과목</th>
			<th>강의명</th>
			<th>강사</th>
			<th>강의월</th>
        </tr>
         <c:if test="${not empty list}">
          <c:forEach items="${list}" var="list" varStatus="status">
             <tr>
				<td>${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
				
				<c:if test="${list.SUBJECT_ISUSE eq 'Y'}">
				<td>${(list.SUBJECT_ISUSE == 'Y') ? '활성' : '비활성' }</td>
				</c:if>
				
				<c:if test="${list.SUBJECT_ISUSE ne 'Y'}">
				<td bgcolor="#FFA7A7">${(list.SUBJECT_ISUSE == 'Y') ? '활성' : '비활성' }</td>
				</c:if>
				
				<td>${list.LECCODE}</td>
				<td>${list.CAT_NM}</td>
				<td>${list.SUBJECT_NM}</td>
				<td><a href="javascript:put_leccode('${list.LECCODE}')">${list.SUBJECT_TITLE}</a></td>
	            <td>${list.USER_NM}</td>
	            <td>${list.SUBJECT_OFF_OPEN_YEAR}년 ${list.SUBJECT_OFF_OPEN_MONTH}월</td>
	        </tr>
      </c:forEach>
		</c:if>
		<c:if test="${empty list}">
		         <tr bgColor=#ffffff align=center> 
			<td colspan="8">데이터가 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
     </table>
     <!--//테이블--> 
   
    <!-- paginate-->
	    <c:if test="${not empty list}">
			<c:out value="${pagingHtml}" escapeXml="false" />
		</c:if>
    <!--//paginate-->
    
</form>
</div>
<!--//content --> 

</html>