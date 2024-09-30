<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<html>
<head>
<meta name="decorator" content="index">

<script type="text/javascript">

//var sts = "${sts}";
//alert("sts:"+sts);

/* var REG_DT = "${order_list.REG_DT}";
var USER_NM = "${order_list.USER_NM}";
var BIRTH_DT = "${order_list.BIRTH_DT}";
var TEL_NO = "${order_list.TEL_NO}";
var PHONE_NO = "${order_list.PHONE_NO}"; */

//alert("MOCKCODE:"+MOCKCODE);

var keyword = "${searchMap.keyword}";
var currentPage = "${searchMap.currentPage}";
var pageRow = "${searchMap.pageRow}";

var TOP_MENU_ID = "${menuParams.TOP_MENU_ID}";
var MENUTYPE = "${menuParams.MENUTYPE}";
var L_MENU_NM = "${menuParams.L_MENU_NM}";

window.onload = function () {
	/* alert("REG_DT:"+REG_DT +"\n"+
	"USER_NM:"+USER_NM +"\n"+
	"BIRTH_DT:"+BIRTH_DT +"\n"+
	"TEL_NO:"+TEL_NO +"\n"+
	"PHONE_NO:"+PHONE_NO ); */
	
	/* alert(	"keyword:"+keyword +"\n"+
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
			$('#realprice'+target).val("");      
			$('#realprice'+target).focus();         
			return;
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

//수강변경
function pop_change(userid, orderno, package_no) {
	
	window.open('<c:url value="/freeOrder/pop_change.pop"/>?cmdcnt=one&userid=' + userid + '&orderno=' + orderno + '&package_no=' + package_no + '&keyword=' + escape(encodeURIComponent($("#keyword").val()))
			 + '&currentPage=' + $("#currentPage").val() + '&pageRow=' + $("#pageRow").val()
			 + '&TOP_MENU_ID=' + $("#TOP_MENU_ID").val() + '&MENUTYPE=' + $("#MENUTYPE").val() + '&L_MENU_NM=' + $("#L_MENU_NM").val(), '_blank', 'location=no,resizable=no,width=820,height=700,top=0,left=0,scrollbars=no,location=no,scrollbars=yes');
}

//상품명 팝업
function view(leccode){
//	alert("강사 정보 팝업 추후개발");
	return;

}

//목록
function goList() {	
	$("#cmd").val("Y");
	$('#myform').attr('action','<c:url value="/freeOrder/changelist.do"/>').submit();
}
</script>

</head>

<!--content -->
  <div id="content">
	<h2>● 상품주문관리 > <strong>수강변경관리</strong></h2>
    
    <!--테이블-->    
    <form id="myform" name="myform" method="post">
    
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${menuParams.TOP_MENU_ID}" />
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${menuParams.MENUTYPE}" />
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${menuParams.L_MENU_NM}" />
	
	<input type="hidden" id="cmd" name="cmd" value="${searchMap.cmd}" />
	<input type="hidden" id="keyword" name="keyword" value="${searchMap.keyword}" />
	<input type="hidden" id="currentPage" name="currentPage" value="${searchMap.currentPage}" />
	<input type="hidden" id="pageRow" name="pageRow" value="${searchMap.pageRow}" />
	
	<table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 주문내역</p></td>
      </tr>
    </table>
    
	<!--테이블-->
          
    <table class="table02">
        <tr>          
          <th width="13%">상품코드</th>
          <th width="10%">상품구분</th>
          <th width="52%">상품명</th>
          <th width="10%">원가</th>
          <th width="15%">상태코드</th>
        </tr>
        <tbody>
        
        <c:set var="tot_sum" value="0"/>
        
        <c:if test="${not empty lec_list}">
         <c:forEach items="${lec_list}" var="lec_list" varStatus="status">
              
            <tr>
              <td>
              		<c:choose>
						<c:when test="${lec_list.PACKAGE_NO eq lec_list.NEW_PACKAGE_NO1}">
							<font color="red">${lec_list.PACKAGE_NO}</font>
						</c:when>
						
						<c:when test="${lec_list.PACKAGE_NO eq lec_list.NEW_PACKAGE_NO2}">
							<font color="blue">${lec_list.PACKAGE_NO}</font>
						</c:when>
						
						<c:otherwise>
							${lec_list.PACKAGE_NO}
						</c:otherwise>
					</c:choose>
              </td>
              <td>
              		${lec_list.GBNNAME}
			  </td>
              
              <td>
              	<a href="#">${lec_list.PACKAGE_NAME}</a> <%-- ${lec_list.PACKAGE_NO} --%>
              	
              	<c:if test="${lec_list.PACKAGE_NO eq lec_list.OLD_PACKAGE_NO2}">
              		<font color="red">=&gt;&nbsp;${lec_list.NEW_PACKAGE_NO2}</font>
              	</c:if>
              </td>
              
              <td>
              		<font color="tomato"><fmt:formatNumber value="${lec_list.PRICE}" groupingUsed="true" /> 원</font>
			  </td>
			  <td>  
			  	${lec_list.STATUSCODENAME}  
				<c:if test="${lec_list.PACKAGE_NO eq searchMap.package_no}">
					<c:if test="${lec_list.PACKAGE_NO ne lec_list.OLD_PACKAGE_NO2}">
						<c:if test="${(fn:substring(lec_list.PACKAGE_NO, 0, 1) eq  'D') && lec_list.ISCANCEL eq '0' }"> 
							<input type="button" onclick="javascript:pop_change('${lec_list.USERID}','${lec_list.ORDERNO}','${lec_list.PACKAGE_NO}');" value="수강변경" />
						</c:if>
					</c:if>
				</c:if>
			  </td>				              
            </tr>
            
            <c:set var="tot_sum" value="${tot_sum + lec_list.PRICE}"/>
              
     	</c:forEach>
		</c:if>
		
		<c:if test="${empty lec_list}">
		    <tr bgColor=#ffffff align=center> 
				<td colspan="5">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<c:set var="dlvPrice" value="0"></c:set>
		<c:if test="${!empty approval_list } " >
			<c:set var="dlvPrice" value="${approval_list.ADDPRICE }"></c:set>
		</c:if>
		<tr>
          <td>&nbsp;</td>
          <td colspan="4" align="right"><fmt:formatNumber value="${tot_sum}" groupingUsed="true" />원 + 택배비(<fmt:formatNumber value="${dlvPrice}" groupingUsed="true" />원) = <fmt:formatNumber value="${tot_sum + dlvPrice}" groupingUsed="true" />원</td>
        </tr>
            	 
        </tbody>
    </table>     
          
    <!--//테이블-->
	
    <c:choose>
		<c:when test="${deliverscount > 0 }">
			<input type="hidden" id="zipcd" name="zipcd" value="${del_list.ZIPCD}" />
		</c:when>
		
		<c:otherwise>
			<input type="hidden" id="zipcd" name="zipcd" value="" />
		</c:otherwise>
	</c:choose>
	
	<br><br>
    <table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 배송지정보&#13;</p></td>
      </tr>
    </table>
    
    <!--//배송지정보 테이블--> 
   	<table class="table01">
   	<tr>
   		<th width="13%">주문번호</th>
		<td width="20%">
			${order_list.ORDERNO}
		</td>
		
		<th width="14%">주문날짜</th>
		<td width="20%">
			${order_list.REG_DT}
		</td>
		
		<th width="13%">주문자명</th>
		<td width="20%">
			${order_list.USER_NM}
		</td>
	</tr>
	<tr>
   		<th >생년월일</th>
		<td >
			${order_list.BIRTH_DT}
		</td>
		
		<th >전화</th>
		<td >
			${order_list.TEL_NO}
		</td>
		
		<th >휴대폰</th>
		<td >
			${order_list.PHONE_NO}
		</td>
	</tr>
	<tr>
   		<th >주소</th>
		<td colspan="3">
			[${order_list.ZIP_CODE}] ${order_list.ADDRESS1}${order_list.ADDRESS1}
		</td>
		
		<th >이메일</th>
		<td >
			${order_list.EMAIL}
		</td>
	</tr>
	</table>
	<!--//배송지정보 테이블-->
    
       
    
    <br><br>
    <table width="100%" border="0" cellspacing="0" cellpadding="12">
      <tr>
        <td align="left" bgcolor="#FFFFFF"><p>▣ 결제 정보</p></td>
      </tr>
    </table>
    
    <!--//결제 정보 테이블--> 
   	<table class="table01">
   	<tr>
   		<th width="13%">총구매금액</th>
		<td width="20%">
			<fmt:formatNumber value="${list.PRICE}" groupingUsed="true" /> 원
		</td>
		
		<th width="14%">추가금액(택배)</th>
		<td width="20%">
			<fmt:formatNumber value="${list.ADDPRICE}" groupingUsed="true" /> 원
		</td>
		
		<th width="13%">사용한 포인트</th>
		<td width="20%">
			${list.POINT}
		</td>
	</tr>
	<tr>
   		<th >총지불금액</th>
		<td >		
			<c:set var="tot_price" value="${list.PRICE + list.ADDPRICE + list.POINT}"/>
			<fmt:formatNumber value="${tot_price}" groupingUsed="true" /> 원
		</td>
		
		<th >지불방법</th>
		<td >
			<c:choose>
				<c:when test="${list.PAYCODENAME eq 'PAY100'}">
					<c:set var="paycodename" value="무통장입금"/>
				</c:when>
				
				<c:when test="${list.PAYCODENAME eq 'PAY110'}">
					<c:set var="paycodename" value="카드결제"/>
				</c:when>
				
				<c:when test="${list.PAYCODENAME eq 'PAY120'}">
					<c:set var="paycodename" value="가상계좌"/>
				</c:when>
				
				<c:when test="${list.PAYCODENAME eq 'PAY130'}">
					<c:set var="paycodename" value="계좌이체"/>
				</c:when>
			</c:choose>
			
			${paycodename}
		</td>
		
		<th >입금계좌</th>
		<td >
			<c:choose>
				<c:when test="${list.VCDBANK eq '06'}">
					<c:set var="vcdbanknm" value="국민은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '20'}">
					<c:set var="vcdbanknm" value="우리은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '39'}">
					<c:set var="vcdbanknm" value="경남은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '04'}">
					<c:set var="vcdbanknm" value="국민은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '03'}">
					<c:set var="vcdbanknm" value="기업은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '11'}">
					<c:set var="vcdbanknm" value="농협중앙회"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '32'}">
					<c:set var="vcdbanknm" value="부산은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '07'}">
					<c:set var="vcdbanknm" value="수협중앙회"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '26'}">
					<c:set var="vcdbanknm" value="신한은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '88'}">
					<c:set var="vcdbanknm" value="신한은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '05'}">
					<c:set var="vcdbanknm" value="외환은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '71'}">
					<c:set var="vcdbanknm" value="우체국"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '23'}">
					<c:set var="vcdbanknm" value="SC제일은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '31'}">
					<c:set var="vcdbanknm" value="대구은행"/>
				</c:when>
				
				<c:when test="${list.VCDBANK eq '81'}">
					<c:set var="vcdbanknm" value="하나은행"/>
				</c:when>
			</c:choose>
			
			<c:if test="${list.PAYCODENAME eq 'PAY120'}">
				${vcdbanknm}
			</c:if>
			${list.ACCTNONAME}
		</td>
	</tr>
	<tr>
   		<th >입금자명</th>
		<td colspan="5">
			${list.PAYNAME}
		</td>
	</tr>
	<tr>	
		<th >TID</th>
		<td colspan="5">
			${list.TID}
		</td>
	</tr>
	<tr>	
		<th >결제리턴값</th>
		<td colspan="5">
			${list.RETURNVALUE}
		</td>
	</tr>
	</table>
	<!--//결제 정보 테이블-->
	</form>

<!--버튼-->
    <ul class="boardBtns">
   	  <li><a href="javascript:goList();">목록</a></li>
    </ul>
    <!--//버튼-->
    
</div>