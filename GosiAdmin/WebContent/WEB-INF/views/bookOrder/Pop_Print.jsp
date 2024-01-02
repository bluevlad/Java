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

window.onload = function () {
	/* alert("REG_DT:"+REG_DT +"\n"+
	"USER_NM:"+USER_NM +"\n"+
	"BIRTH_DT:"+BIRTH_DT +"\n"+
	"TEL_NO:"+TEL_NO +"\n"+
	"PHONE_NO:"+PHONE_NO ); */
	
	window.print();
} 
</script>

</head>

<!--content -->
  <div id="">
    <!--테이블-->    
    <table style="width:100%;">
    <tr>
	    <td width="3%">
	    </td>
    	<td width="94%">
			<h2 align=center><strong>상 품 주 문 정 보</strong></h2>
		    <div align=right><font size=2>${orderinf_list.to_date}</font></div>

			<table style="width:100%;">
		      <tr>
		        <td align="left" bgcolor="#FFFFFF"><p>▣ 주문자정보</p></td>
		      </tr>
		    </table>
		    
		    <!--//주문자정보 테이블--> 
		   	<table class="table05">
		   	<tr>
		   		<th width="13%">주문번호</th>
				<td width="20%">
					${searchMap.orderno}
				</td>
				
				<th width="14%">주문날짜</th>
				<td width="20%">
					${orderinf_list.regdate}
				</td>
				
				<th width="13%">주문자명</th>
				<td width="20%">
					${orderinf_list.username}
				</td>
			</tr>
			<tr>
		   		<th >생년월일</th>
				<td width="20%">
					${orderinf_list.BIRTH_DAY}
				</td>
				
				<th >전화</th>
				<td width="20%">
					${orderinf_list.telno}
				</td>
				
				<th >휴대폰</th>
				<td width="20%">
					${orderinf_list.cellno}
				</td>
			</tr>
			<tr>
		   		<th >주소</th>
				<td colspan="3">
					[${orderinf_list.zipcd}] ${orderinf_list.addr}
				</td>
				
				<th >이메일</th>
				<td >
					${orderinf_list.email}
				</td>
			</tr>
			</table>
			<!--//주문자정보 테이블-->
			
			<br />
		    
		    <table width="100%" border="0" cellspacing="0" cellpadding="12">
		      <tr>
		        <td align="left" bgcolor="#FFFFFF"><p>▣ 배송지 정보</p></td>
		      </tr>
		    </table>
		    
		    <!--//배송지 테이블--> 
		   	<table class="table05">
		   	<tr>
		   		<th width="13%">송장번호</th>
				<td width="20%">
					${inf_list.sendNo}
				</td>
				
				<th width="14%">수령인</th>
				<td width="20%">
					${inf_list.userName}
				</td>

		   		<th width="13%">배송방법</th>
				<td width="20%">
					${inf_list.DLEORDER_NM}
				</td>
			</tr>
			<tr>
				<th width="13%">생년월일</th>
				<td width="20%">
					${inf_list.BIRTH_DAY}
				</td>
				
		   		<th width="14%">전화</th>
				<td width="20%">
					${inf_list.telno}
				</td>
				
				<th width="13%">휴대폰</th>
				<td width="20%">
					${inf_list.cellno}
				</td>
			</tr>
			
			<tr>
		   		<th >주소</th>
				<td colspan="3">
					 [${inf_list.zipcd}] ${inf_list.addr}
				</td>
				
				<th >배송일</th>
				<td >
					${inf_list.sendDate}
				</td>
			</tr>
			
			<tr>
		   		<th >메모</th>
				<td colspan="5">
					${inf_list.memo}
				</td>
			</tr>
			</table>
			<!--//배송지 테이블-->
			
			<br>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="12">
		      <tr>
		        <td align="left" bgcolor="#FFFFFF"><p>▣ 주문내역</p></td>
		      </tr>
		    </table>
		    
			<!--테이블-->
		          
		    <table class="table05">
		        <tr>          
		          <th>상품코드</th>
		          <th>상품명</th>
		          <th>저자</th>
		          <th>상품구분</th>
		          <th>정가</th>
		          <th>기본할인율</th>        
		          <th>판매가</th>
		          <th>수량</th>
		          <th>합계금액</th>
		          <th>상태코드</th>
		        </tr>
		        <tbody>
		        
		        <c:set var="tot_sum" value="0"/>
		        
		        <c:if test="${not empty list}">
		         <c:forEach items="${list}" var="list" varStatus="status">
		              
		            <tr>
		              <td>${list.MGNTNO}</td>
		              <td>${list.NAME}</td>
		              <td>${list.BOOK_AUTHOR}</td>
		              <td>${list.GBNNAME}</td>
		              <td><fmt:formatNumber value="${list.PRICE}" groupingUsed="true" /></td>              
		              <td>${list.RATE}</td>
		              <td><fmt:formatNumber value="${list.REALPRICE}" groupingUsed="true" /></td>
		              <td>${list.CNT}개</td>
		              <td><font color=tomato><fmt:formatNumber value="${list.TOT_PRICE}" groupingUsed="true" /></font></td>
		              <td>  
		              	<c:choose>
							<c:when test="${list.STATUSCODENAME eq 'DLV100'}">
								<c:set var="statuscodename" value="무통장입금"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV105'}">
								<c:set var="statuscodename" value="입금완료"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV110'}">
								<c:set var="statuscodename" value="배송준비중"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV120'}">
								<c:set var="statuscodename" value="배송중"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV130'}">
								<c:set var="statuscodename" value="배송완료"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV140'}">
								<c:set var="statuscodename" value="취소요청"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV150'}">
								<c:set var="statuscodename" value="취소완료"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV160'}">
								<c:set var="statuscodename" value="교환요청"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV170'}">
								<c:set var="statuscodename" value="교환배송중"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV180'}">
								<c:set var="statuscodename" value="교환완료"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV220'}">
								<c:set var="statuscodename" value="환불요청"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV230'}">
								<c:set var="statuscodename" value="환불완료"/>
							</c:when>
							
							<c:when test="${list.STATUSCODENAME eq 'DLV240'}">
								<c:set var="statuscodename" value="단과수강취소"/>
							</c:when>
						</c:choose>
						
						${statuscodename}
					  </td>			              
		            </tr>
		            
		            <c:choose>
						<c:when test="${list.STATUSCODENAME ne 'DLV150' and list.STATUSCODENAME ne 'DLV180' and list.STATUSCODENAME ne 'DLV230' and list.STATUSCODENAME ne 'DLV240'}">
							<c:set var="tot_sum" value="${tot_sum + list.TOT_PRICE}"/>
						</c:when>
						
						<c:otherwise>
							<c:set var="tot_sum" value="0"/>
						</c:otherwise>
					</c:choose>
		              
		     	</c:forEach>
				</c:if>
				
				<c:if test="${empty list}">
				          <tr bgColor=#ffffff align=center> 
						<td colspan="10">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				
				<c:choose>
					<c:when test="${tot_sum < 40000}">
						<c:set var="addprice" value="2500"/>
					</c:when>
					
					<c:otherwise>
						<c:set var="addprice" value="0"/>
					</c:otherwise>
				</c:choose>
					
				<tr>
		          <td><b>구매상품 합계</b></td>
		          <td colspan="9" align="right">
		          		<b><fmt:formatNumber value="${tot_sum}" groupingUsed="true" />원 + (택배비:<fmt:formatNumber value="${addprice}" groupingUsed="true" />) = <font color=tomato><fmt:formatNumber value="${tot_sum + addprice}" groupingUsed="true" />원</font></b>
		          </td>
		        </tr>
		            	 
		        </tbody>
		    </table>
	    </td>
	    <td width="3%">
	    </td>
    </tr>
    </table>


</div>