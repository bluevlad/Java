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
var USER_ID = "${searchMap.USER_ID}";

var orderstatuscode = "${searchMap.orderstatuscode}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	 /* if(message != "") {
		alert(message);
	} */
	
	/* alert("USER_ID:"+USER_ID +"\n"+
			"orderstatuscode:"+orderstatuscode +"\n"+
			"searchkey:"+searchkey +"\n"+
			"searchtype:"+searchtype +"\n"+
			"paycode:"+paycode +"\n"+		
			"sdate:"+sdate +"\n"+
			"edate:"+edate +"\n"+
			"currentPage:"+currentPage +"\n"+
			"pageRow:"+pageRow +"\n"+
			"TOP_MENU_ID:"+TOP_MENU_ID +"\n"+
			"MENUTYPE:"+MENUTYPE +"\n"+
			"L_MENU_NM:"+L_MENU_NM); */
	
	if(USER_ID != ""){
		getSubCODE('CLASSSERIESCODE', '<c:url value="/productOrder/subCode.do"/>', USER_ID);
	}
}

//비동기 선택 
function getSubCODE(target, url, USER_ID) {
	
	$("#CLASSSERIESCODE").html('');
	
	var _url = url + '?USER_ID=' + USER_ID;	
	var leccodes = "";
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("subCodes.length:"+json.subCodes.length);
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){			
					leccodes = "'"+mouigosa.LECCODE+"'";
					$("#"+target).append('<tr><td style=text-align:left;>' + mouigosa.SUBJECT_TITLE + '</td><td>' + mouigosa.SUBJECT_REAL_PRICE + '</td><td><input type="button" onclick="godel('+ leccodes +')" value="삭제" /></td><input type="hidden" name="SUBJECT_REAL_PRICE" id="SUBJECT_REAL_PRICE" value="' + mouigosa.SUBJECT_REAL_PRICE + '"><input type="hidden" name="USER_ID2" id="USER_ID2" value="' + mouigosa.USER_ID + '"></tr>');					
				});	
			}
			
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
	
	var tmp =""; 
	$("input[name=SUBJECT_REAL_PRICE]").each(function (index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
		}else{
			tmp = parseInt(tmp) + parseInt($(this).val());
		}
	});
	
	if(tmp == ""){
		document.getElementById("TOTAL_PRICE").innerHTML = "0";
	}else{
		document.getElementById("TOTAL_PRICE").innerHTML = tmp;
	}
	
	var tmp2 ="";	
	$("input[name=USER_ID2]").each(function (index){
		if(tmp2 == null || tmp2 == ""){
			tmp2 = $(this).val();
		}
	});
	
	$("#USER_ID").val(tmp2);
	
	/* alert("TOTAL_PRICE:"+tmp +"\n"+
			 "USER_ID:"+tmp2); */
	
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
	
	$('#searchFrm').attr('action','<c:url value="/productOrder/pop_subject_add.pop"/>').submit();
}

//수강신청하기 클릭
function put_leccode()
{	
 window.opener.searchFrm.USER_ID.value=$('#USER_ID').val();
 window.opener.searchFrm.orderstatuscode.value=orderstatuscode;
 window.opener.searchFrm.searchkey.value=searchkey;
 window.opener.searchFrm.searchtype.value=searchtype;
 window.opener.searchFrm.paycode.value=paycode;
 window.opener.searchFrm.sdate.value=sdate;
 window.opener.searchFrm.edate.value=edate;
 window.opener.searchFrm.currentPage.value=currentPage;
 window.opener.searchFrm.pageRow.value=pageRow;
 window.opener.searchFrm.TOP_MENU_ID.value=TOP_MENU_ID;
 window.opener.searchFrm.MENUTYPE.value=MENUTYPE;
 window.opener.searchFrm.L_MENU_NM.value=L_MENU_NM;
 window.opener.getSubCODE('CLASSSERIESCODE', '<c:url value="/productOrder/subCode.do"/>', $('#USER_ID').val());
 window.self.close();
}

//비동기 선택 
function getSubCODE2(target, url, LECCODE, KIND_TYPE) {
			
	/* alert("USER_ID.val:"+$('#USER_ID').val() +"\n"+
			"USER_ID:"+USER_ID); */
	
	if(USER_ID == "" && $('#USER_ID').val() == "") {
		USER_ID = "U";
	}else if($('#USER_ID').val() != "") {
		USER_ID = $('#USER_ID').val();
	}
	
	/* alert("USER_ID:"+USER_ID +"\n"+
			"LECCODE:"+LECCODE +"\n"+
			"KIND_TYPE:"+KIND_TYPE); */
	
	$("#CLASSSERIESCODE").html('');
	
	var _url = url + '?USER_ID=' + USER_ID + '&LECCODE=' + LECCODE + '&KIND_TYPE=' + KIND_TYPE;	
	var leccodes = "";
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("subCodes.length:"+json.subCodes.length);
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){			
					leccodes = "'"+mouigosa.LECCODE+"'";
					$("#"+target).append('<tr><td style=text-align:left;>' + mouigosa.SUBJECT_TITLE + '</td><td>' + mouigosa.SUBJECT_REAL_PRICE + '</td><td><input type="button" onclick="godel('+ leccodes +')" value="삭제" /></td><input type="hidden" name="SUBJECT_REAL_PRICE" id="SUBJECT_REAL_PRICE" value="' + mouigosa.SUBJECT_REAL_PRICE + '"><input type="hidden" name="USER_ID2" id="USER_ID2" value="' + mouigosa.USER_ID + '"></tr>');					
				});	
			}
			
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
	
	var tmp =""; 
	$("input[name=SUBJECT_REAL_PRICE]").each(function (index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
		}else{
			tmp = parseInt(tmp) + parseInt($(this).val());
		}
	});
	
	if(tmp == ""){
		document.getElementById("TOTAL_PRICE").innerHTML = "0";
	}else{
		document.getElementById("TOTAL_PRICE").innerHTML = tmp;
	}
	
	var tmp2 ="";	
	$("input[name=USER_ID2]").each(function (index){
		if(tmp2 == null || tmp2 == ""){
			tmp2 = $(this).val();
		}
	});
	
	$("#USER_ID").val(tmp2);
	
	/* alert("TOTAL_PRICE:"+tmp +"\n"+
			 "USER_ID:"+tmp2); */
	
}

//삭제
function godel(LECCODE) {
	/* alert("USER_ID:"+$('#USER_ID').val() +"\n"+
	 "LECCODE:"+LECCODE); */
	
	USER_ID = $('#USER_ID').val();
	
	$("#CLASSSERIESCODE").html('');
	
	var _url = '<c:url value="/productOrder/deleteCart.do"/>?USER_ID=' + USER_ID + '&LECCODE=' + LECCODE;
	var leccodes = "";
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("duplication.length:"+json.duplication.length);
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){
					leccodes = "'"+mouigosa.LECCODE+"'";
					$("#CLASSSERIESCODE").append('<tr><td style=text-align:left;>' + mouigosa.SUBJECT_TITLE + '</td><td>' + mouigosa.SUBJECT_REAL_PRICE + '</td><td><input type="button" onclick="godel('+ leccodes +')" value="삭제" /></td><input type="hidden" name="SUBJECT_REAL_PRICE" id="SUBJECT_REAL_PRICE" value="' + mouigosa.SUBJECT_REAL_PRICE + '"><input type="hidden" name="USER_ID2" id="USER_ID2" value="' + mouigosa.USER_ID + '"></tr>');
				});	
			}
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
	
	var tmp =""; 
	$("input[name=SUBJECT_REAL_PRICE]").each(function (index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
		}else{
			tmp = parseInt(tmp) + parseInt($(this).val());
		}
	});
	
	if(tmp == ""){
		document.getElementById("TOTAL_PRICE").innerHTML = "0";
	}else{
		document.getElementById("TOTAL_PRICE").innerHTML = tmp;
	}
}

//장바구니 비우기
function del_all() {
	
	//alert("USER_ID:"+$("#USER_ID").val());
	
	$("#CLASSSERIESCODE").html('<tr bgColor=#ffffff align=center><td colspan="3">장바구니가비었습니다.</td></tr>');
	
	var _url = '<c:url value="/productOrder/deleteAllCart.do"/>?USER_ID=' + $("#USER_ID").val();
	var leccodes = "";
	$.ajax({
		type : "GET",
		url : _url,
		dataType : "json",
		async : false,
		success : function(json){
			//alert("duplication.length:"+json.duplication.length);
			if(json && json.length > 0) {
				$(json).each(function(index, mouigosa){
					leccodes = "'"+mouigosa.LECCODE+"'";
					$("#CLASSSERIESCODE").append('<tr><td style=text-align:left;>' + mouigosa.SUBJECT_TITLE + '</td><td>' + mouigosa.SUBJECT_REAL_PRICE + '</td><td><input type="button" onclick="godel('+ leccodes +')" value="삭제" /></td><input type="hidden" name="SUBJECT_REAL_PRICE" id="SUBJECT_REAL_PRICE" value="' + mouigosa.SUBJECT_REAL_PRICE + '"><input type="hidden" name="USER_ID2" id="USER_ID2" value="' + mouigosa.USER_ID + '"></tr>');
				});	
			}
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
	
	var tmp =""; 
	$("input[name=SUBJECT_REAL_PRICE]").each(function (index){
		if(tmp == null || tmp == ""){
			tmp = $(this).val();
		}else{
			tmp = parseInt(tmp) + parseInt($(this).val());
		}
	});
	
	if(tmp == ""){
		document.getElementById("TOTAL_PRICE").innerHTML = "0";
	}else{
		document.getElementById("TOTAL_PRICE").innerHTML = tmp;
	}
	
}
</script>
</head>
  <!--content -->
  <div>
  
    <form id="searchFrm" name="searchFrm" method="post">

	<input type="hidden" id="USER_ID" name="USER_ID" value="${searchMap.USER_ID}">	
	
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
	
	<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
	<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
	<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
	<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
	<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
	<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />
	
   <!--테이블--> 
      <table class="table01">
          <tr>
            <th width="15%">검색</th>
            <td>            
            	<select id="s_cat_cd" name="s_cat_cd">
					<option value="">--직종 검색--</option>
				<c:forEach items="${category_list}"  var="category_list">
					<option value="${category_list.CODE }" <c:if test="${searchMap.s_cat_cd == category_list.CODE}">selected</c:if>>${category_list.NAME }</option>
				</c:forEach>
				</select>
					
				<select id="s_menu_id" name="s_menu_id">
					<option value="">--학습형태 검색--</option>
					<option value="M0101" <c:if test="${searchMap.s_menu_id == 'M0101'}">selected</c:if>>이론강좌</option>
					<option value="M0102" <c:if test="${searchMap.s_menu_id == 'M0102'}">selected</c:if>>문제풀이</option>
					<option value="M0103" <c:if test="${searchMap.s_menu_id == 'M0103'}">selected</c:if>>특강</option>
					<option value="M0104" <c:if test="${searchMap.s_menu_id == 'M0104'}">selected</c:if>>모의고사</option>
				</select>
				
				<select id="s_sjt_cd" name="s_sjt_cd">
					<option value="">--과목 검색--</option>
				<c:forEach items="${subject_list}"  var="subject_list">
					<option value="${subject_list.SUBJECT_CD }" <c:if test="${searchMap.s_sjt_cd == subject_list.SUBJECT_CD}">selected</c:if>>${subject_list.SUBJECT_NM }</option>
				</c:forEach>
				</select>
				
				<select id="search_type" name="search_type">
					<option value="">--검색 조건 선택--</option>
					<option value="1" <c:if test="${searchMap.search_type == '1'}">selected</c:if>>강사명</option>
					<option value="2" <c:if test="${searchMap.search_type == '2'}">selected</c:if>>강의코드</option>
					<option value="3" <c:if test="${searchMap.search_type == '3'}">selected</c:if>>강의명</option>
			   	</select>
				
				<input  title="검색" size="15" type="text" id="search_keyword" name="search_keyword"  value="${searchMap.search_keyword}" onkeypress="fn_checkEnter()">
				
				<input type="button"   onclick="goList(1)"  value="검색" />
		    </td>
          </tr>
      </table>
    
    <!--//테이블-->	
       
	<p style="text-align:left;">&nbsp;<span>총${totalCount}건</span></p>
    
    <table  style="width:100%;">
	    <tr>
			<td>
			    <!--테이블-->
			     <table class="table02" style="width:100%;">
			        <tr>
						<th>직종</th>
						<th>학습형태</th>
						<th>과목</th>
						<th>강의코드</th>
						<th>강의명</th>
						<th>강의상태</th>
						<th>구분</th>
						<th>회차</th>
						<th>수강료</th>
						<th>선택</th>
			        </tr>
			         <c:if test="${not empty list}">
			          <c:forEach items="${list}" var="list" varStatus="status">
			             <tr>
							<td>${list.CATEGORY_NM}</td>
							<td>${list.LEARNING_NM}</td>
							<td>${list.LECCODE}</td>
							<td>${list.SUBJECT_NM}</td>
							<td style="text-align:left;">${list.SUBJECT_TITLE}</td>
				            <td>${list.LEC_STAT}</td>
				            <td>${list.SUBJECT_TYPE_NM}</td>
				            <td>${list.LEC_SCHEDULE}</td>
				            <td><fmt:formatNumber value="${list.SUBJECT_REAL_PRICE}" type="currency" /></td>
				            
				            <td><input type="button" onclick="javascript:getSubCODE2('CLASSSERIESCODE', '<c:url value="/productOrder/subCode2.do"/>', '${list.LECCODE}', '${list.LEC_TYPE_CHOICE}')" value="선택" /></td>
				        </tr>
			      </c:forEach>
					</c:if>
					<c:if test="${empty list}">
					         <tr bgColor=#ffffff align=center> 
						<td colspan="9">데이터가 존재하지 않습니다.</td>
					</tr>
					</c:if>	 
			     </table>
			     <!--//테이블--> 
	     
		     </td>
		     
		     <td style="margin-top:10px; float:left; width:100%;">
		     	<!--테이블-->
			         
			           <div class="form_table" style="margin-top:10px; float:left; width:100%;">
			           <table class="tbl_type" style="margin-top:10px; float:left; width:100%;" border="1" cellspacing="0" summary="받은쪽지">
			             <caption>
			             </caption>
			             <colgroup>
			             <col width="15%">
			             <col width="5%">
			             <col width="5%">
			             </colgroup>
			             <thead>
			               <tr>
			               	 <th scope="col">품목</th>
			                 <th scope="col">가격</th>
			                 <th scope="col">삭제</th>
			               </tr>
			             </thead>
			             
			             <tbody id="CLASSSERIESCODE" style="background-color:#efefef">
			             <tr bgColor=#ffffff align=center> 
							<td colspan="3">장바구니가비었습니다.</td>
						 </tr>			             
						 </tbody>
			              
			           </table>
			           <br><br><br><br>
			           	실 결제금액:<span id="TOTAL_PRICE">0</span>원
			           <br><br>
			          <input type="button" onclick='javascript:del_all();' value="장바구니비우기" />
			          <br>
			          <input type="button" onclick='javascript:put_leccode();' value="수강신청하기" />
		         </div>
		         <!--//테이블-->
		     </td>
	     </tr>
     </table>
    <!-- paginate-->
	    <c:if test="${not empty list}">
			<c:out value="${pagingHtml}" escapeXml="false" />
		</c:if>
    <!--//paginate-->
    
</form>
</div>
<!--//content --> 

</html>