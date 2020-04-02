<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
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
	<c:if test="${INS eq 'Y'}">
		window.opener.location.reload();
		window.close();
	</c:if>
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
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/TmCouponTeacherList.pop"/>').submit();
}



//추가
function fn_Add(){
	if($("input[name='DEL_ARR']:checked").length < 1){
		alert("선택한 항목이 없습니다");
		return;
	}else{
		
		var appendStr = "";
		var curValStr = "";
		
		$("input[name='DEL_ARR']:checked").each(function(i,el){
			
			appendStr = $(this).parent().next().next().text();
			curValStr = $(this).val();
			
			
		});
		
		$("#TEACHER_NM", opener.document).val(appendStr);
		$("#TEACHER", opener.document).val(curValStr);
		
		self.close();
	}
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
	
	<input type="hidden" id="EVENT_ID" name="EVENT_ID" value="${EVENT_ID}">	
   <!--테이블--> 
      <table class="table05">
          <tr>
            <th width="15%">검색</th>
            <td class="tdLeft">            
            	<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="">--전체검색--</option>
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>코드</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>과목명</option>
				</select>
				
				<input  title="검색" size="15" type="text" id="SEARCHTEXT" name="SEARCHTEXT"  value="${searchMap.search_keyword}" style="ime-mode:active;" onkeypress="fn_checkEnter()">
				
				<input type="button"   onclick="goList(1)"  value="검색" />
		    </td>
          </tr>
      </table>
	</form>

    <p>&nbsp; </p>
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Add();">교수추가</a></li>
    </ul>
    <!--//버튼-->
    
    <!--//테이블-->	
    <form id="regFrm" name="regFrm" method="post">
	<input type="hidden" id="cmd" name="cmd" value="pop_add">	
	<input type="hidden" id="EVENT_ID" name="EVENT_ID" value="${EVENT_ID}">	
	
	<p class="pInto01">&nbsp;<span>총${totalCount}건 (<c:out value="${currentPage}"/>/<c:out value="${totalPage}"/>)</span></p>
    
    
      <!-- 테이블-->
    <table class="table05">
		<tr>
	        <th width="85"><!-- <input type="checkbox" name="allCheck" id="allCheck" class="i_check" value="" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" /> -->No</th>
	        <th>아이디</th>
	        <th>이름</th>
	        <th>상태(ON)</th>
	        <th>상태(OFF)</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
			    	<td>
			    		<input type="radio" name="DEL_ARR" id="DEL_ARR" class="i_check" value="${item.SUBJECT_CD}" /> ${totalCount-((params.currentPage-1)*params.pageRow)-loop.index} 
			        </td>
	                <td>${item.USER_ID}</td>
	                <td><a href="javascript:fn_Modify('${item.SUBJECT_CD}');">${item.USER_NM}</a></td>
	                <td>${item.ON_OPENYNNM}</td>
					<td>${item.OFF_OPENYNNM}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="5">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	 
        </tbody>
	</table>      
    <!-- //테이블--> 
   
    <!-- paginate-->
	    <c:if test="${not empty list}">
			<c:out value="${pagingHtml}" escapeXml="false" />
		</c:if>
    <!--//paginate-->
    
</form>
</div>
<!--//content --> 

</html>