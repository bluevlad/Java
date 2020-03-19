<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@ page import="modules.etest.support.Etest" %>
<jsp:include page="_category.jsp" flush="true"/>
<table border="0"   cellpadding="2" cellspacing="1" class="view" width="100%"> 
	<col width="20%"/>
	<col width="30%"/>
	<col width="20%"/>
	<col width="30%"/>
<c:choose>
    <c:when test="${item.quetype == 's' || item.quetype == 'm'}">
        <mf:input type="hidden" name="tmp" value="${item.quecount}"/>
    </c:when>
    <c:otherwise>
        <input type="hidden" name="tmp" value="4">
    </c:otherwise>
</c:choose>
	<mf:input type="hidden" name="sjt_cd" value="${item.sjt_cd}"/>
	<mf:input type="hidden" name="queid" value="${item.queid}"/>
    <mf:input type="hidden" name="cat_id" value="${item.cat_id}" />
    <mf:input type="hidden" name="from" value="${from}"/>
    <mf:input type="hidden" name="setid" value="${setid}"/>
    
	<input type="hidden" name="queviw1" value="">
	<input type="hidden" name="queviw2" value="">
	<input type="hidden" name="queviw3" value="">
	<input type="hidden" name="queviw4" value="">
	<input type="hidden" name="queviw5" value="">
	<input type="hidden" name="queviw6" value="">
	<input type="hidden" name="queviw7" value="">
	<input type="hidden" name="queviw8" value="">
	<input type="hidden" name="queviw9" value="">
	<input type="hidden" name="queviw10" value="">
	<input type="hidden" name="queviw11" value="">
	<input type="hidden" name="queviw12" value="">
	<input type="hidden" name="queviw13" value="">
	<input type="hidden" name="queviw14" value="">
	<input type="hidden" name="queviw15" value="">
	<input type="hidden" name="queviw16" value="">
	<input type="hidden" name="queviw17" value="">
	<input type="hidden" name="queviw18" value="">
	<input type="hidden" name="queviw19" value="">
	<input type="hidden" name="queviw20" value="">
    <tr>
        <th height="28"><mf:label name="quetype"/></th>
        <td >
            <mf:select name="quetype" codeGroup="ETEST.QTYPE" curValue="${item.quetype}" onchange="type_reload('myform')"/>
        </td>
        <th height="28" ><mf:label name="quecount"/></th>
        <td >
            <select name="quecount" onchange="changeQueuCount();">
	        <c:forEach var="i" begin="0" end="20">
	        	<mf:option value="${i}" curValue="${item.quecount}"><mfmt:message bundle="table.exm_bank" key="label.quecount" > <mfmt:param value="${i}"/></mfmt:message></mf:option>
	        </c:forEach>
            </select> 
        </td>
    </tr>
    <tr>
        <th height="28"><mf:label name="quelevel"/></th>
        <td >
            <mf:select name="quelevel" codeGroup="ETEST.QLEVEL" curValue="${item.quelevel}" />
        </td>
        <th height="28"><mf:label name="active_yn"/></th>
        <td >

    		<mf:input type="radio" value="Y" name="active_yn" curValue="${item.active_yn}" /><mfmt:message bundle="common" key="select.active"/>
    		<mf:input type="radio" value="N" name="active_yn" curValue="${item.active_yn}" /><mfmt:message bundle="common" key="select.inactive"/>

        </td>
    </tr>
    <tr>
        <th height="28" ><mf:label name="quescore"/></th>
        <td >
	        <mf:input type="text" name="quescore" size="5" value="${item.quescore}"  onkeyup="checkNumber(this);" 
	        	onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;"/>
	        <mfmt:message bundle="table.exm_bank" key="label.score"/>
        </td>
        <th height="28"><mf:label name="cat_name"/></th>
        <td >
            <mf:input type="text" name="cat_name"  value="${item.cat_name}" onclick="CategorySelector.showList('cat_name')" cssClass="dropdown" required="true"/><div id="catTreeDiv" ></div>
        </td>
        
    </tr>
</table>

<br><br>
<table border="0"   cellpadding="2" cellspacing="1" class="view" width="100%"> 
	<col width="15%"/>
	<col width="15%"/>
	<col width="70%"/>

    <tr>
        <th rowspan=3 ><mfmt:message bundle="table.exm_bank" key="title"/></th>
        <th ><mf:label name="quetitle"/></th>
        <td >
        <textarea name="quetitle" style="width:100%" rows='3' required><c:out value="${item.quetitle}"/></textarea>
        </td>
    </tr>
    <tr>
        <th><mf:label name="quetext"/></th>
        <td>
        <textarea name="quetext" style="width:100%" rows='3' hname="<mf:header name="quetext"/>"><c:out value="${item.quetext}"/></textarea>
        

        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.exm_bank" key="queimag"/></th>
        <td>
        <input type="file" name="queimag" style="width:100%" value='' >
            <c:if test="${!empty item.queimag}">
            	<c:url var="iurl" value="/wdownload">
            		<c:param name="type" value="exam_file"/>
            		<c:param name="ext" value="${item.sjt_cd}"/>
            		<c:param name="filename" value="${item.queimag}"/>
            	</c:url>
                <br><a href='<c:out value="${iurl}"/>' target='_blank'><mh:out value="${item.queimag}"/></a>&nbsp;&nbsp;<input type="checkbox" name="queimag_flag" style="border:none" value="Y" checked >:<mfmt:message bundle="common.message" key="message.use_file"/><br>
            </c:if>
        </td>
    </tr>
    
    <c:forEach var="v" begin="1" end="20" varStatus="status">
        <c:set var="view" value= "none"/>
        <c:set var="Sview" value= ""/>
        <c:set var="Mview" value= ""/>
        <c:choose>
            <c:when test="${item.quetype == 's'}">
                <c:set var="Mview" value= "none"/>    
            </c:when>
            <c:when test="${item.quetype == 'm'}">
                <c:set var="Sview" value= "none"/>    
            </c:when>
            <c:otherwise>
                <c:set var="Sview" value= ""/>
                <c:set var="Mview" value= ""/>
            </c:otherwise>
        </c:choose>
        <c:if test="${empty Sview || empty Mview}">
            <c:set var="view" value= ""/>
        </c:if>
        <c:choose>
            <c:when test="${status.count == 1}">
                <c:set var="viw" value= "${item.queviw1}"/>
            </c:when>
            <c:when test="${status.count == 2}">
                <c:set var="viw" value= "${item.queviw2}"/>
            </c:when>
            <c:when test="${status.count == 3}">
                <c:set var="viw" value= "${item.queviw3}"/>
            </c:when>
            <c:when test="${status.count == 4}">
                <c:set var="viw" value= "${item.queviw4}"/>
            </c:when>
            <c:when test="${status.count == 5}">
                <c:set var="viw" value= "${item.queviw5}"/>
            </c:when>
            <c:when test="${status.count == 6}">
                <c:set var="viw" value= "${item.queviw6}"/>
            </c:when>
            <c:when test="${status.count == 7}">
                <c:set var="viw" value= "${item.queviw7}"/>
            </c:when>
            <c:when test="${status.count == 8}">
                <c:set var="viw" value= "${item.queviw8}"/>
            </c:when>
            <c:when test="${status.count == 9}">
                <c:set var="viw" value= "${item.queviw9}"/>
            </c:when>
            <c:when test="${status.count == 10}">
                <c:set var="viw" value= "${item.queviw10}"/>
            </c:when>
            <c:when test="${status.count == 11}">
                <c:set var="viw" value= "${item.queviw11}"/>
            </c:when>
            <c:when test="${status.count == 12}">
                <c:set var="viw" value= "${item.queviw12}"/>
            </c:when>
            <c:when test="${status.count == 13}">
                <c:set var="viw" value= "${item.queviw13}"/>
            </c:when>
            <c:when test="${status.count == 14}">
                <c:set var="viw" value= "${item.queviw14}"/>
            </c:when>
            <c:when test="${status.count == 15}">
                <c:set var="viw" value= "${item.queviw15}"/>
            </c:when>
            <c:when test="${status.count == 16}">
                <c:set var="viw" value= "${item.queviw16}"/>
            </c:when>
            <c:when test="${status.count == 17}">
                <c:set var="viw" value= "${item.queviw17}"/>
            </c:when>
            <c:when test="${status.count == 18}">
                <c:set var="viw" value= "${item.queviw18}"/>
            </c:when>
            <c:when test="${status.count == 19}">
                <c:set var="viw" value= "${item.queviw19}"/>
            </c:when>
            <c:when test="${status.count == 20}">
                <c:set var="viw" value= "${item.queviw20}"/>
            </c:when>
            <c:otherwise>
                <c:set var="viw" value= ""/>
            </c:otherwise>
        </c:choose>
    <!-- single select -->
    <tr style='<c:out value="display:${Sview}"/>' id='<c:out value="SviewTEXT${status.count}"/>'>
    	<mh:indexOf var="t" value="${item.queansw}" needle="${status.count}"/>
        <th><input type='radio'  name='queansw_Scheck' value='<c:out value="${status.count}"/>' <c:if test="${t >= 0}">checked</c:if> />
        		<mfmt:message bundle="table.exm_bank" key="queviw"/><c:out value="${status.count}"/></th>
        <td colspan="2" ><mf:textarea cssStyle="width:100%" rows="3" name="Squeviw${status.count}" value="${viw}"/></td>
    </tr>
    <!-- multi select -->
    <tr style='<c:out value="display:${Mview}"/>' id='<c:out value="MviewTEXT${status.count}"/>' >
    	<mh:indexOf var="t" value="${item.queansw}" needle="${status.count}"/>
        <th ><input type="checkbox" name="queansw_Mcheck" value='<c:out value="${status.count}"/>' <c:if test="${t >= 0}">checked</c:if> />
        	<mfmt:message bundle="table.exm_bank" key="queviw"/><c:out value="${status.count}"/></th>
        <td colspan="2"><textarea style="width:100%" rows=3 name='<c:out value="Mqueviw${status.count}"/>' ><c:out value="${viw}"/></textarea></td>
    </tr>
    </c:forEach>    
    
    <tr style="display:'none'" id="answer">
        <th ><mf:label  name="queansw"/></th>
        <td colspan="2" >
        <mf:textarea cssStyle="width:100%" rows="3" name="queansw_word" value="${item.queansw}"/><br>
            <mfmt:message bundle="table.exm_bank" key="wlc_exm_bnk.queansw.seusul.tip"/>
        </td>
    </tr>
    <tr>
        <th ><mf:label  name="quedesc"/></th>
        <td colspan="2">
        <mf:textarea cssStyle="width:100%" rows="3" name="quedesc"  value="${item.quedesc}"/>
        </td>
    </tr>    
    <tr>
        <th colspan="3" >&nbsp;</th>
    </tr>    
</table>