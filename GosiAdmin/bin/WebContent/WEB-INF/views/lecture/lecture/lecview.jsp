<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <!-- 테이블-->
    <table class="table02">
        <tr>
            <th width="80">직종</th>
            <th width="80">과목</th>
            <th width="75">강의코드</th>
            <th width="55">강사명</th>
            <th>강의명</th>
            <th width="75">학습형태</th>
	        <th width="80">배수</th>
        </tr>
        <c:forEach items="${lecview}" var="item" varStatus="loop">
            <tr>
                <td>${item.CATEGORY_NM}</td>
                <td>${item.SUBJECT_NM}</td>
                <td>${item.LECCODE}<br><span name="qq" id="qq">${item.BRIDGE_LECCODE}</span></td>
                <td>${item.SUBJECT_TEACHER_NM}</td>
                <td style="text-align:left;">${item.SUBJECT_TITLE}</td>
                <td>${item.LEARNING_NM}</td>
		        <td class="txt03"><c:if test="${item.LEC_BAESU eq '00' or item.LEC_BAESU eq '' or empty item.LEC_BAESU}">배수제한없음</c:if><c:if test="${item.LEC_BAESU ne '00' and item.LEC_BAESU ne '' and not empty item.LEC_BAESU}">${item.LEC_BAESU/10}배수</c:if></td>
            </tr>
        </c:forEach>            
    </table>      
    <!-- //테이블-->
