<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
//엔터키 검색
function fn_Enter(){
	$("#SEARCHTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			fn_Search();
	});
}
//주문자 클릭시
function view(userid){
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
    	window.open('<c:url value="/productOrder/MemberView1.pop"/>?userid=' + userid, '_blank', 'location=no,resizable=no,width=800,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
    }
}

function Detailview(userid, orderno){
    if(userid=="" || userid ==null){
        alert("비회원입니다.");
        return;
    }else{
    	window.open('<c:url value="/lecture/FreePassDetailList.pop"/>?USER_ID=' + userid+ '&ORDERNO=' + orderno, '_blank', 'location=no,resizable=no,width=1050,height=630,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
    }
}

//SMS보내기 
function smssend_pop() {
	
	var tmp = "";
	var userphone = "";
	$("input[name=smsname]").each(function(index){
		if ($(this).is(':checked')) {
			if(tmp == null || tmp == ""){
				tmp = $(this).val();
				userphone = $("#userphone"+index).val();
			}else{
				tmp = tmp + "/" + $(this).val();
				userphone = userphone + "/" + $("#userphone"+index).val();
			}
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("받을사람을 선택해 주세요.");
		return;
	}
	/* alert("tmp:"+tmp +"\n"+
			"userphone:"+userphone); */
	window.open('<c:url value="/productOrder/add.pop"/>?userphone=' + userphone + '&smsname=' + escape(encodeURIComponent(tmp))
			 , '_blank', 'location=no,resizable=no,width=1005,height=400,top=0,left=0,scrollbars=no,location=no,scrollbars=no');
}

//쪽지
function fn_message() {
	var tmp ="";
	var tmp_nm="";
	
	$("input[name=smsname]:checked").each(function (index){
		tmp += $(this).attr('id') + ",";
		tmp_nm += $(this).val() + ",";
		//alert(tmp + " : " + tmp_nm);
	});
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("대상이 선택되지 않았습니다.");
		return;
	}
	if(confirm("쪽지를 보내시겠습니까?")) {		
		var last = tmp.lastIndexOf(',');
		tmp = tmp.substr(0,last);
		var last_nm = tmp_nm.lastIndexOf(',');
		tmp_nm = tmp_nm.substr(0,last_nm);
		
		$("#MESSAGEID").val(tmp);
		$("#MESSAGENM").val(tmp_nm);
		window.open('', 'message', 'scrollbars=no,toolbar=no,resizable=yes,width=1027,height=300 ');
		$('#frm').attr('target' ,'message');
		$('#frm').attr('action','<c:url value="/memberManagement/memberCheckMessage.pop"/>').submit();
	}
}

function fn_excel() { //ck 추가 

	$('#frm').attr('action','<c:url value="/lectureYear/payList_excel.do"/>').submit();
}
//페이징
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);
	$("#frm").attr("action", "<c:url value='/lectureYear/payUserList.pop' />");
	$("#frm").submit();
}

// 검색
function fn_Search() {
	$("#currentPage").val(1);
	$("#frm").attr("action", "<c:url value='/lectureYear/payUserList.pop' />");
	$("#frm").submit();
}
</script>
</head>
<body>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="searchStartDate" name="searchStartDate" value="${params.searchStartDate}"/>
<input type="hidden" id="searchEndDate" name="searchEndDate" value="${params.searchEndDate}"/>
<input type="hidden" id="LECCODE" name="LECCODE" value="${params.LECCODE}"/>
<input type="hidden" id="STATUSCODE" name="STATUSCODE" value="${params.STATUSCODE}"/>
<input type="hidden" id="MESSAGEID" name="MESSAGEID" value="">
<input type="hidden" id="MESSAGENM" name="MESSAGENM" value="">


	<h2>● 경영관리> <strong>프리패스 매출관리</strong></h2>
    <table class="table01">
        <tr>
			<th width="15%">검색</th>
			<td>
				<select name="SEARCHTYPE" id="SEARCHTYPE">
					<option value="">--전체검색--</option>
					<option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>회원명</option>
					<option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>회원ID</option>
				</select> 
				<label for="SEARCHTEXT"></label>
				<input type="text" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
          </td>
          <th width="15%">화면출력건수</th>
          <td width="30%"><input type="text" id="pageRow" name="pageRow" value="${params.pageRow}" title="화면출력건수" size="5" maxlength="2" style="ime-mode:disabled;" onKeyUp="fn_RowNumCheck(this);"/> <input type="button" onclick="fn_Search()" value="검색" /></td>
        </tr>
    </table>      
    <!--버튼-->    
    <ul class="boardBtns">
    	<li><a href="javascript:fn_excel();">Excel(전체)</a></li>
   	    <li><a href="javascript:smssend_pop();">SMS보내기</a></li>
   	    <li><a href="javascript:fn_message();">쪽지발송</a></li>
    </ul>
    <!--//버튼-->
    
    <!-- 총건수 -->
    	<p class="pInto01">&nbsp;<span>총${fn:length(list)}건</span></p>         
    <!-- //총건수 -->        
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
			<th><input id="allCheck"  value="" type="checkbox" onclick="checkAll('allCheck', 'smsname')" /></th>
	        <th width="85">수강자</th>
	        <th>아이디</th>
	        <th>수강시작일</th>
	        <th>수강종료일</th>
	        <th>수강상태</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
					<td>
						<input type="checkbox" name="smsname" id="${item.USER_ID}" value="${item.USER_NM}" />
					</td>
			    	<td><a href="javascript:void(0)" onclick="javascript:view('${item.USER_ID}')">
				           ${item.USER_NM}
				    	</a>
				    <input type="hidden" name="userphone${loop.index}" id="userphone${loop.index}" value="${fn:replace(item.PHONE_NO,'-', '')}">
				    </td>
					<td><a href="#" onclick="javascript:Detailview('${item.USER_ID}','${item.ORDERNO }')">
							${item.USER_ID}
					</a></td>
					<td><fmt:formatDate value="${item.START_DATE}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${item.END_DATE}" pattern="yyyy-MM-dd"/></td>
					<td>${ item.STATUSCODE_NM }</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="8">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	 
        </tbody>
	</table>      
    <!-- //테이블-->
    <!-- paginate-->
	<c:if test="${!empty list}">
		<c:out value="${pagingHtml}" escapeXml="false" />
	</c:if>
	<!--//paginate-->
    <!--버튼-->
	<ul class="boardBtns">
	    <li><a href="javascript:self.close();">닫기</a></li>
	</ul>
    <!--//버튼--> 	
	
</form>	
</div>
<!--//content --> 

<script type="text/javascript">

</script>
</body>
</html>