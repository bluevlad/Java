<%@ page contentType = "text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<br>
<c:set var="ActionClassName" value="${className}Bean" />
<textarea cols="90" rows="30" name="bean" id="frmBean">
public class <c:out value="${ActionClassName}"/> {

<c:forEach items="${columns}" var="item">
	<c:if test="${!empty(item.comments)}">/**
	* <c:out value="${item.comments}"/>
	*/</c:if>
    <c:set var="def" value="null"/>
    <c:if test="${'int' == item.javaType}"><c:set var="def" value="0"/></c:if>
    <c:if test="${!empty item.data_default}"><c:set var="def" value="\"${item.data_default}\""/></c:if>
	<c:out value="private ${item.javaType} "/><mh:out value="${item.name} =" case="lower"/><c:out value="${def}"/>;
</c:forEach>

	////////////////////////////////////////////////////////////////////////////////
	
<c:forEach items="${columns}" var="item">
	/**
	* Get <mh:out value="${item.name}" case="lower"/> : <c:out value="${item.comments}"/>
	* DB TYPE : <c:out value="${item.type}"/>
	*/
	public <c:out value="${item.javaType}"/> get<mh:out value="${item.name}" case="capital"/>(){
		return this.<mh:out value="${item.name}" case="lower"/>;
	}
	/**
	* Set <mh:out value="${item.name}" case="lower"/> : <c:out value="${item.comments}"/>
	* DB TYPE : <mh:out value="${item.type}" />
	*/
	public void set<mh:out value="${item.name}" case="capital"/>(<mh:out value="${item.javaType}" /> <mh:out value="${item.name}" case="lower"/>){
		this.<mh:out value="${item.name}" case="lower"/> = <mh:out value="${item.name}" case="lower"/>;
	}
</c:forEach>
}
</textarea>


