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
var LECCODE = "${searchMap.LECCODE}";

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
	
	/* alert("LECCODE:"+LECCODE +"\n"+
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
}

//숫자 입력 폼
function chk(obj, target){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {
			if(target == "PRICE") {
				$('#PRICE').val("");      
				$('#PRICE').focus();         
				return;
			}
		}       
		else {          
			if (val < 1) {             
				/* if(target == "OFFCLOSEPERSONNUMBER") {
					$('#OFFCLOSEPERSONNUMBER').val("");      
					$('#OFFCLOSEPERSONNUMBER').focus();         
					return;
				}  */         
			}       
		}    
	}
}

//적용
function add()
{	
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
 window.opener.getSubPrice(LECCODE,$('#PRICE').val());
 window.self.close();
}
</script>
</head>
  <!--content -->
  <div>
  
    <form id="searchFrm" name="searchFrm" method="post">

	<input type="hidden" id="LECCODE" name="LECCODE" value="${searchMap.LECCODE}">	
	
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
          <th>원수강료</th>
          <td><fmt:formatNumber value="${searchMap.SUBJECT_REAL_PRICE}" type="currency" /></td>
        </tr>
        <tr> 
    	  <th>전체회차</th>
          <td>${searchMap.LEC_SCHEDULE}</td>
        </tr>
        <tr>  
          <th>회당수강료</th>
          <td><fmt:formatNumber value="${searchMap.DAY_PRICE}" type="currency" /></td>
        </tr>
        <tr>  
          <th>진행회차</th>
          <td>${searchMap.USE_SCHEDULE}</td>
        </tr>
        <tr>  
          <th>결제대상금액</th>
          <td><input  title="검색" size="15" type="text" id="PRICE" name="PRICE"  value="${searchMap.PRICE}" onkeyup="chk(this,'PRICE');" onblur="chk(this,'PRICE');"></td>
        </tr>
    </table>
    
    <!--버튼-->    
    <ul class="boardBtns">
        <li><a href="javascript:add();">적용</a></li>
        <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼-->
    
</form>
</div>
<!--//content --> 

</html>