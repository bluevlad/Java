<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function changeLang() {   
        var frm = getObject('myForm');
        frm.cmd.value="view";
        
        frm.submit();
    }
    function goList() {   
        var frm = getObject('myForm');
        frm.cmd.value="list";
        
        frm.submit();
    }
    function getExcel() {   
        var frm = getObject('myForm');
        frm.cmd.value="downloadExcel";
        
        frm.submit();
    }
    function upExcel() {   
        var frm = getObject('myForm');
        frm.cmd.value="upExcel";
        
        frm.submit();
    }
    function updateOne(bundle,key, obj) {
        var frm = getObject('myForm');
        var lang = frm.lang.value;        
        var url = '<mh:ACTION />';
        new Ajax.Request(url, {
                method: 'get', 
                parameters: {'cmd':'updateOne','bundle':bundle,'key':key,'message':obj.value, 'locale':lang},                        
                onSuccess: function(transport) {
                    var json = transport.responseText.evalJSON();
                    var nv = 'nv_ans_'+json.qseq;
                }
              }
             )
    }
//-->
</SCRIPT>



<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td><div class="searchContainer">
        	<h1><mfmt:message bundle="common" key="searchtitle"/></h1>
                <mf:form action="${controlaction}" name="myForm" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="cmd" value="view"/>
                    <mf:input type="hidden" name="bundle" value="${bundle}"/>
                	<mf:select name="lang" items="${langList}" keyValue="code" keyText="allnames" curValue="${curLang}"/>
                    <mf:button onclick="changeLang()" bundle="button" key="search" />
                    <mf:button onclick="goList()" bundle="button" key="goList" />
                    <mf:button onclick="getExcel()" bundle="button" key="button.excel.down" /><br>
                    <mf:input type="file" value="" name="excelfile"></mf:input>
                    <mf:button onclick="upExcel()" bundle="button" key="button.excel.up" />
               </mf:form>
	        </div>    
        </td>
    </tr>

	<tr>
	    <td><div class="listContainer">
        Category : <c:out value="${bundle}"/><br>
		<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list " 
			enableAlternateRows="true" rowAlternateClass="alternateRow">
         <col width="100"/>
         <col width="*"/>
         <col width="*"/>
		<thead>
		    <tr>
		    	<th>key</th>
                <th>default(<c:out value="${defaultLocale}"/>)</th>
                <th>message</th>
		    </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="item" items="${resourceBundle.resource}" varStatus="status">
			<tr   >
				<td class='center' ><c:out value="${item.key}"/><br><c:out value="${item.value.desc}"/></td>
                <td class='center' ><mf:input name="defaultMessage" type="text" size="40" value="${item.value.localeValueMap[defaultLocale].value}"/></td>
                <td class='center' ><mf:input name="localeMessage" type="text" size="40" value="${item.value.localeValueMap[curLang].value}" 
                    onchange="updateOne('${bundle}','${item.key}',this)" /></td>
			</tr>
            
	    </c:forEach>
	    </tbody>
	    </table></div>

	    </td>
    </tr>

</table>
