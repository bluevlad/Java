<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
</head>
<body>

<!--content -->
<div id="content">

<c:import url="/WEB-INF/views/survey/tab.jsp" />

    <p>&nbsp; </p>
	<form name="frm" id="frm" method="post" action="">
	<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
	<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
	<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
	<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />

	<input type="hidden" id="S_MENU" name="S_MENU" value="${params.S_MENU}" />

	<table class="table01">
		<col width="20%"/>
		<col width="80%"/>
	    <tr>
	        <th>제목</th>
	        <td>${view.SURVEYTITLE}</td>
	    </tr>
	    <tr>
	        <th>설문기간</th>
	        <td>${view.SURVEY_SDAT}&nbsp;~&nbsp;${view.SURVEY_EDAT}</td>
	    </tr>
	    <tr>
	        <th>설명</th>
	        <td>${view.SURVEYDESC}</td>
	    </tr>
    </table>
	<br>
		<c:forEach var="set" items="${rList}">
	<table class="table01">
    	<tr>
        	<th>${set.QUETITLE}</th>
    	</tr>
	    <tr>
	        <td>
				<table class="surveyTable01" style="width:100%">
					<col width="*" />
				    <col width="100" />
				    <col width="100" />
				    <col width="100" />
				    <c:if test="${set.QUETYPE == 'S' || set.QUETYPE == 'M'}">
				    <tr>
			        	<th>문항</th>
			        	<th>인원</th>
			        	<th>비율</th>
			        	<th>*</th>
			        </tr>
					<c:forEach var="i" begin="1" end="${set.QUECOUNT}">
				    <tr>
			        <c:choose>
			            <c:when test="${i == 1}">
			        	<td><c:out value="${set.QUEVIW1}"/></td>
			        	<td><a href="javascript:fn_user_list('${view.SURVEYID}','${set.QUEID}','${set.QSEQ}')"><c:out value="${set.A1}"/></a></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A1*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph01_m.jpg" width="${(set.A1*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
	 		            </c:when>
			            <c:when test="${i == 2}">
			        	<td><c:out value="${set.QUEVIW2}"/></td>
			        	<td><c:out value="${set.A2}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A2*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph02_m.jpg" width="${(set.A2*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			            <c:when test="${i == 3}">
			        	<td><c:out value="${set.QUEVIW3}"/></td>
			        	<td><c:out value="${set.A3}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A3*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph03_m.jpg" width="${(set.A3*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			            <c:when test="${i == 4}">
			        	<td><c:out value="${set.QUEVIW4}"/></td>
			        	<td><c:out value="${set.A4}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A4*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph04_m.jpg" width="${(set.A4*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			            <c:when test="${i == 5}">
			        	<td><c:out value="${set.QUEVIW5}"/></td>
			        	<td><c:out value="${set.A5}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A5*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph05_m.jpg" width="${(set.A5*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			            <c:when test="${i == 6}">
			        	<td><c:out value="${set.QUEVIW6}"/></td>
			        	<td><c:out value="${set.A6}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A6*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph01_m.jpg" width="${(set.A6*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			            <c:when test="${i == 7}">
			        	<td><c:out value="${set.QUEVIW7}"/></td>
			        	<td><c:out value="${set.A7}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A7*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph02_m.jpg" width="${(set.A7*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			            <c:when test="${i == 8}">
			        	<td><c:out value="${set.QUEVIW8}"/></td>
			        	<td><c:out value="${set.A8}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A8*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph03_m.jpg" width="${(set.A8*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			            <c:when test="${i == 9}">
			        	<td><c:out value="${set.QUEVIW9}"/></td>
			        	<td><c:out value="${set.A9}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A9*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph04_m.jpg" width="${(set.A9*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			            <c:when test="${i == 10}">
			        	<td><c:out value="${set.QUEVIW10}"/></td>
			        	<td><c:out value="${set.A10}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${(set.A10*1.001)/(set.ASUM*1.001)}"/></td>
			        	<td><img src="http://file.willbes.net/new_image/2016/06/graph05_m.jpg" width="${(set.A10*1.001)/(set.ASUM*1.001)*100}" border="0" height="15"/></td>
			            </c:when>
			        </c:choose>
				    </tr>
				    </c:forEach>
				    </c:if>
				    <c:if test="${set.QUETYPE == 'T'}">
				    <tr>
			        	<th colspan="2">답변</td>
			        </tr>
				    <tr>
			        	<td colspan="2">
						<c:forEach var="item" items="${aList}">
						    <c:if test="${set.QUEID == item.QUEID}">
							<c:if test="${item.QSEQ == 1}">	        		
			        		<b>[<c:out value="${item.USER_ID}"/>]</b>&nbsp;&nbsp;
							</c:if>
				        		<c:forEach var="i" begin="1" end="${set.QUECOUNT}">
								    <c:if test="${item.QSEQ == i}">
					        		<c:out value="${item.USER_ANSW}"/>&nbsp;/&nbsp;
					        		</c:if>
				        		</c:forEach>
						<c:if test="${item.QSEQ == set.QUECOUNT}">	        		
			        	</td>
					</tr>
					<tr>
						<td>
						</c:if>
						    </c:if>
						</c:forEach>
					</c:if>
				    <c:if test="${set.QUETYPE == 'D'}">
				    <tr>
			        	<th colspan="2">답변</td>
			        </tr>
						<c:forEach var="item" items="${aList}">
						    <c:if test="${set.QUEID == item.QUEID}">
				    <tr>
			        	<td colspan="2">[<c:out value="${item.USER_ID}"/>]&nbsp;&nbsp;
			        	<c:out value="${item.USER_ANSW}"/></td>
					</tr>
						    </c:if>
						</c:forEach>
					</c:if>
				</table>
	        </td>
	    </tr>
	</table>
	    </c:forEach>
    
    <!--버튼-->
	<ul class="boardBtns">
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
	</form>
</div>
<!--//content --> 
<script type="text/javascript">
// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/survey/survey/list.do' />");
	$("#frm").submit();
}

//아이템 항목 보기
function fn_user_list(surveyid, queid, qseq) {
	window.open('<c:url value="/survey/survey/userList.pop"/>?SURVEYID='+surveyid+'&QUEID='+queid+'&QSEQ='+qseq, '_survey', 'location=no,resizable=no,width=800,height=600,top=0,left=0,location=no,scrollbars=yes');
}
</script>
</body>
</html>