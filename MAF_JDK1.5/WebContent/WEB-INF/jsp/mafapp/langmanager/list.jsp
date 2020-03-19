<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function frmSubmit() {
        var  frm = document.getElementById("frmEdit");
        if( frm) {
            
             frm.submit()

        } else {
            alert("invalid form id");
        }
  }

	function frmReset() {
        var  frm = document.getElementById("frmEdit");
        if( frm) {
            frm.reset();
        }
    }
    function SrchFrmReset() {
        var  frm = document.getElementById("myform");
        if( frm) {
            frm.reset();
        }
    }

    
    function doSearch(formName) {
        var frm = getObject(formName);
        if(frm) {
            frm.miv_page.value = 1;
            frm.submit();
        }     
    }
    
    // 수정된 column에 check box를 true로 바꿈 .
    function udateCheck( val){
        var frm = getObject("frmEdit");

        if(frm ) {
            for (var i=0; i<frm.elements.length;i++) {
                //alert(frm.elements[i].type + "," + frm.elements[i].name);
                if (frm.elements[i].type == "checkbox" && frm.elements[i].name == "codes" && frm.elements[i].value== val) {
                    frm.elements[i].checked = true;
                }
            }
        
        }
    }
//-->
</SCRIPT>


    
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td><div class="searchContainer">
            <h1><mfmt:message bundle="common" key="searchtitle"/></h1>
            <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch('myform');return false; ">
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <mf:input type="hidden" name="cmd" value="list"/>
            <input type="hidden" name="miv_page" value="1"/>
            
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <td><mf:label name="code" for="s_code"/></td>
                    <td><mf:input name="s_code" type="text" value="${LISTOP.ht.s_code}" /></td>     
                    <td><mf:label name="active_yn" for="s_active_yn"/></td>
                    <td><mf:select name="s_active_yn" codeGroup="ACTIVE_YN" curValue="${LISTOP.ht.s_active_yn}">
                        <option value="">ALL</option>
                    </mf:select>
                    </td>  
                    <td><mf:label name="allnames" for="s_allnames"/></td>
                    <td><mf:input name="s_allnames" type="text" value="${LISTOP.ht.s_allnames}" /></td>                      
                </tr>
            </table>
            
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td  >
                    <mf:button onclick="doSearch('myform')" bundle="button" key="search" />
                    <mf:button bundle="button" key="reset" onclick="SrchFrmReset()" />
                    </td>
                </tr>
            </table>
            </mf:form> 
            </div>    
        </td>
    </tr>

    <tr>
        <td><div class="listContainer">
        <mf:form action="${control_action}" method="post" name="frmEdit" id="frmEdit" onSubmit="return frmSubmit(this,'update');return false; ">
        <input type="hidden" name="cmd" value="update"/>
        <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list " 
            enableAlternateRows="true" rowAlternateClass="alternateRow">
        <thead>
            <tr>
                <th># </th>
                <th><input type="checkbox" name="checkboxAll" 
                            value="allcode"  class="checkbox" 
                            onclick="allChekboxToggle(this,'frmEdit','codes')"></th>   
                <th><mf:header name="code"/> </th> 
                <th><mf:header name="code2"/> </th>            
                <th><mf:header name="allnames"/> </th>          
                <th><mf:header name="active_yn"/> </th>         
                <th><mf:header name="local_name"/> </th>
                <th><mf:header name="reg_nm"/> </th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${navigator.list}" varStatus="status">
            <tr   >
                <td class='center' ><c:out value="${status.count}"/></td>
                <td class='center' ><mf:input type="checkbox" name="codes" value="${item.code}"/></td>
                <td class='center' ><mh:out value="${item.code}" td="true"/></td> 
                <td class='center' ><mf:input type="text" name="code2_${item.code}" value="${item.code2}" onchange="udateCheck('${item.code}')"/></td>
                <td ><mf:input type="text" name="allnames_${item.code}" value="${item.allnames}" onchange="udateCheck('${item.code}')"/></td>  
                <td class='center' ><mf:select name="active_yn_${item.code}" codeGroup="ACTIVE_YN" curValue="${item.active_yn}" onchange="udateCheck('${item.code}')"/></td>  
                <td ><mf:input type="text" name="local_name_${item.code}" value="${item.local_name}" onchange="udateCheck('${item.code}')"/></td>
                <td ><mh:out value="${item.reg_nm}" td="true"/></td>
            </tr>
        </c:forEach>
        </tbody>
        </table></mf:form></div>
        </td>
    </tr>
    <tr>

        <td colspan="2" align="center">
            <mf:button bundle="button" key="save" onclick="frmSubmit()"/>
            <mf:button bundle="button" key="reset" onclick="frmReset()" /></td>

    </tr>
</table>


        
        
        