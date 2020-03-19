<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<?xml version="1.0" encoding="UTF-8"  ?>
<!DOCTYPE maf-command PUBLIC
         "-//Rainend//DTD maf-command config XML V1.0//EN"
         "excel-upload.dtd">

<upload desc="sample.xls" startrow="1">
	<sqls>
		<sql seq="0">
		INSERT INTO <c:out value="${tabsname}"/>
		    (<c:forEach var="item" items="${columns}" varStatus="status"><mh:out value="${item.name}" case="lower"/>, </c:forEach>
		    reg_dt,update_dt, reg_usn, update_usn)
		  VALUES
		    (<c:forEach var="item" items="${columns}" varStatus="status">:<mh:out value="${item.name}" case="lower"/>, </c:forEach>
		    sysdate, sysdate, :reg_usn, :reg_usn)
		</sql>
	</sqls>
	<fields>
		<c:forEach var="item" items="${columns}" varStatus="status">
		<field col="<c:out value="${status.count}"/>" fieldname="<mh:out value="${item.name}" case="lower"/>" type="string"  title="<mh:out value="${item.name}" case="lower"/>" check="true" sample="" desc="<mh:out value="${item.comments}"/>" />
		</c:forEach>
	</fields>
</upload>		