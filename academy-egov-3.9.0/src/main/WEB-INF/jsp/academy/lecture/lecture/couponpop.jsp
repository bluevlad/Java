<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<table class="table02">
	<tr>
        <th width="110"><!-- <input name="input" type="checkbox" value=""> -->No </th>
        <th width="80">쿠폰</th>
        <th width="80">쿠폰이름</th>
        <th width="75">할인액</th>
        <th width="55">쿠폰유효기간</th>
        <th width="80">적용기간</th>
       <!--  <th width="75">대상</th> -->
     </tr>
     <c:if test="${not empty list}">
	 <c:forEach items="${list}" var="list" varStatus="status">
	
		<tr >
		        <td class="tdCenter"><input name="COUPON_INFO_ARR" type="radio" value="${list.CCODE}#${list.CNAME}"/></td>
		        <td>${list.CCODE}</td>
		        <td>${list.CNAME}</td>
		        <td>
			        ${list.REGPRICE}
		            
		            <c:choose>
						<c:when test="${list.REGTYPE eq 'C' }">
							(%)
						</c:when>
						
						<c:when test="${list.REGTYPE eq 'P' }">
							(point)
						</c:when>
						
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
		        </td>
		        <td>${fn:substring(list.EXPDATES, 0, 10)} ~ ${fn:substring(list.EXPDATEE, 0, 10)}</td>
		        <!-- <td class="tdLeft"><a href="#">${item.SUBJECT_TITLE}</a></td> -->
		        <td>${list.EXPDAY}일
					<c:if test="${(list.EXPDATEE > list.THISTIME) }"> <input type="hidden" name="eday" id="eday" value="${list.EXPDAY}"> </c:if>
				</td>
		        <%-- <td>${list.TCLASS}</td> --%>
			</tr>
	
	  </c:forEach>
	  </c:if>
	
		<c:if test="${empty list}">
		         <tr bgColor=#ffffff align=center> 
			<td colspan="7">표시할 쿠폰이 존재하지 않습니다.</td>
		</tr>
		</c:if>	 
	
	</table>




