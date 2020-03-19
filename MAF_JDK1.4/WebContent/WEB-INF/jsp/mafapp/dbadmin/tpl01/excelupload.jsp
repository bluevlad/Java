<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<?xml version="1.0" encoding="UTF-8"  ?>
<!DOCTYPE maf-command PUBLIC
         "-//Miranet//DTD maf-command config XML V1.0//EN"
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