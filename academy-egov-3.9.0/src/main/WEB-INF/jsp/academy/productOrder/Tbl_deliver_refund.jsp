<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
var addprice = "${searchMap.addprice}";
var message = "${message}";

var orderstatuscode = "${searchMap.orderstatuscode}";
var search_date_type = "${searchMap.search_date_type}";
var searchkey = "${searchMap.searchkey}";
var searchtype = "${searchMap.searchtype}";
var paycode = "${searchMap.paycode}";
var payall = "${searchMap.payall}";
var sdate = "${searchMap.sdate}";
var edate = "${searchMap.edate}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	
	/* alert("addprice:"+addprice +"\n"+
			"message:"+message); */
	
	/* alert("orderstatuscode:"+orderstatuscode +"\n"+
			"search_date_type:"+search_date_type +"\n"+
			"searchkey:"+searchkey +"\n"+
			"searchtype:"+searchtype +"\n"+
			"paycode:"+paycode +"\n"+			
			"payall:"+payall +"\n"+
			"sdate:"+sdate +"\n"+
			"edate:"+edate +"\n"+
			"currentPage:"+currentPage +"\n"+
			"pageRow:"+pageRow +"\n"+
			"TOP_MENU_ID:"+TOP_MENU_ID +"\n"+
			"MENUTYPE:"+MENUTYPE +"\n"+
			"L_MENU_NM:"+L_MENU_NM); */
			
	if(message == "등록완료") {
		//opener.document.location.href = opener.document.URL;
		
		window.opener.searchFrm.orderstatuscode.value=orderstatuscode;
		 window.opener.searchFrm.search_date_type.value=search_date_type;
		 window.opener.searchFrm.searchkey.value=searchkey;
		 window.opener.searchFrm.searchtype.value=searchtype;
		 window.opener.searchFrm.paycode.value=paycode;
		 window.opener.searchFrm.payall.value=payall;
		 window.opener.searchFrm.sdate.value=sdate;
		 window.opener.searchFrm.edate.value=edate;		 
		 window.opener.searchFrm.currentPage.value=currentPage;
		 window.opener.searchFrm.pageRow.value=pageRow;
		 
		 window.opener.searchFrm.TOP_MENU_ID.value=TOP_MENU_ID;
		 window.opener.searchFrm.MENUTYPE.value=MENUTYPE;
		 window.opener.searchFrm.L_MENU_NM.value=L_MENU_NM;
		 
		 window.opener.goList(currentPage);
		 
		self.close();
	}
			
	if(addprice == "0") {
		alert("책정된 택배비가 없습니다.");
		self.close();
	}
}

//숫자 입력 폼
function chk(obj, target){
	var val = obj.value;
	if (val) {       
		if (val.match(/^\d+$/gi) == null) {
			if(target == "price") {
				$('#price').val("");      
				$('#price').focus();         
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

//환불
function frmsubmit1(){
	if ($('#price').val() == "") {
		alert("적절한 값을 넣어주세요");
		$('#price').focus();
		return;
	}
	
	//완전환불이 가능하게 변경하므로써 미만에서 이하로 바꿈
	if(parseInt($('#price').val()) > parseInt(addprice)){
		alert("부분환불 가능금액은 "+addprice+"원 이하여야 합니다.\n적절한 금액을 재입력해주세요");
		$('#price').focus();
		return;
	}

	$('#popFrm').attr('action','<c:url value="/productOrder/deliver_refund_insert.do"/>').submit();
}
</script>
</head>

<!--팝업-->
<form id="popFrm" name="popFrm" method="post">
							  
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
								  
<input type="hidden" id="orderstatuscode" name="orderstatuscode" value="${searchMap.orderstatuscode}" />
<input type="hidden" id="search_date_type" name="search_date_type" value="${searchMap.search_date_type}" />
<input type="hidden" id="searchkey" name="searchkey" value="${searchMap.searchkey}" />
<input type="hidden" id="searchtype" name="searchtype" value="${searchMap.searchtype}" />
<input type="hidden" id="paycode" name="paycode" value="${searchMap.paycode}" />
<input type="hidden" id="payall" name="payall" value="${searchMap.payall}" />
<input type="hidden" id="sdate" name="sdate" value="${searchMap.sdate}" />
<input type="hidden" id="edate" name="edate" value="${searchMap.edate}" />
<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />

<input type="hidden" id="orderno" name="orderno" value="${searchMap.orderno}" />
<input type="hidden" id="cmd" name="cmd"  value="deliver_refund_insert" />

<table width="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="40" align="center" bgcolor="#FFFFFF"> <div align="left">
       <div align="left"><font style="color:RED;font-weight:bold">합계금액이 
         0원이 아닙니다.</font><BR>
         도서 및 테입이 모두 환불처리 되었는지 다시 한번 확인해 주시기 바랍니다. <BR>
       </div>
       <table border="0" cellpadding="0" cellspacing="0" style="text-align:left; width:100%;">
          <tr>
			<td height="18px" width="100%">
			<font style="color:RED;font-weight:bold">환불할 금액</font>을 입력하여 주세요<BR>
			환불가능한 금액은 <font style="color:RED;font-weight:bold">${searchMap.addprice}원</font>미만으로 설정하셔야 합니다.<BR>
			만약 <font style="color:RED;font-weight:bold">전액환불인 경우 주문관리의 상태코드변경을 이용</font>해주세요.<BR>	<BR>		
			</td>
		</tr>
	   </table>
  	</td>
   </tr>
</table>
            
<!--테이블-->
          
    <table class="table02">
        <tr>          
          <th>환불할 금액</th>
          <th>환불일자(YYYYMMDD)</th>
        </tr>
        <tbody>
              
            <tr>
              <td><input type="text" name="price" id="price" value="${searchMap.addprice}" onkeyup="chk(this)" onblur="chk(this)" style="width:70;"></td>
              
              <td><input type="text" name="confirmdate" id="confirmdate" style="width:70;"></td>         
            </tr>
        </tbody>
    </table>    
          
    <!--//테이블-->
    
    <!--버튼-->
    <ul class="boardBtns">
   	  <li>
   	  	<a href="javascript:frmsubmit1();">환불</a>			
   	  </li>
    </ul>
    <!--//버튼-->

</form>
</html>