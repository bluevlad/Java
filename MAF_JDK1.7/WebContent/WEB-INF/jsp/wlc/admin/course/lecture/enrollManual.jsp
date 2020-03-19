<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.calendar.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
    function doWrite(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "enrollManualOk";
            frm.submit();
        }
    }
        
    function doSearch(frm) {
        if(frm) {
            frm.cmd.value = "enrollManual";
            frm.miv_page.value = 1;
            return true;
        }     
    }
//-->
</SCRIPT>

<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
        <th><mf:label name="leccode"/></th> 
        <td><mh:out value="${item.leccode}" td="true"/></td>
        <th><mf:label name="alias_cd"/></th> 
        <td><mh:out value="${item.alias_cd}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_year"/></th> 
        <td><mh:out value="${item.lec_year}" td="true"/></td>
        <th><mf:label name="lecnumb"/></th> 
        <td><mh:out value="${item.lecnumb}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lecname"/></th> 
        <td colspan="3"><mh:out value="${item.lecname}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lecstat"/></th> 
        <td><mh:out td="true"  codeGroup="LECSTAT" value="${item.lecstat}"/> <a href='<c:url value="/wlc.admin/lecreq.do">
            <c:param name="leccode" value="${item.leccode}"/>
            <c:param name="cmd" value="view"/>
            </c:url>'>(<mh:out value="${item.cnt_lr}"/> / <mh:out value="${item.cnt_lp}"/> / <mh:out value="${item.cnt_le}"/> )</a> </td>

        <th><mf:label name="ltype"/></th> 
        <td><mh:out td="true"  codeGroup="LTYPE" value="${item.ltype}"/></td>
    </tr>
    <tr>
        <th><mf:label name="otype"/></th> 
        <td><mh:out td="true"  codeGroup="OTYPE" value="${item.otype}"/></td>
        <th><mf:label name="lec_type"/></th> 
        <td><mh:out td="true"  codeGroup="LEC_TYPE" value="${item.lec_type}" /></td>
    </tr>
    <tr>
        <th><mf:label name="lec_capacity"/></th> 
        <td><mh:out td="true" value="${item.lec_capacity}" /></td>
        <th><mf:label name="lec_min_capacity"/></th> 
        <td><mh:out td="true" value="${item.lec_min_capacity}"/></td>
     </tr>

</table>
<mf:form action="${control_action}" method="post" name="myform" id="myform"
            onSubmit="return frmSubmit(this,'enrollManual');return false; ">
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <mf:input type="hidden" name="LISTOP2" value="${LISTOP2.serializeUrl}" />
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden" />
            <input type="hidden" name="miv_page" value="1" />
            <input type="hidden" name="cmd" value="enrollManualOk" />
            <mf:input type='hidden' name='leccode' value='${leccode}'/>        
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
        <h1><mfmt:message bundle="common" key="searchtitle" /></h1>
        
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
            <!-- -->
                    <script>
                        function os_init() {
                            pageAJAXComm.getOrg('','<c:out value="${type}"/>');
                        }
                        var pageAJAXComm = {
                            getOrg: function(obj, destination) {
                                var URL = "<%=request.getContextPath()%>/common.do?cmd=getOrg&destination="+destination;
                                if(obj) {
                                    URL += "&p_org=" + obj.value;
                                    $("org_code").value=obj.value;
                                    $("org_name").value=obj.options[obj.selectedIndex].text;
                                }else {
                                    URL += '&p_org=<c:out value="${org_cd}"/>';
                                    $("org_code").value='<c:out value="${LISTOP2.ht.org_code}"/>';
                                }
                                var rqst = new Ajax.Request(URL, {
                                    onSuccess: function (xmlHttp) {
                                        DataShow.jsonData(xmlHttp);
                                    },
                                    onFailure: function (xmlHttp) {
                                        alert('fail!!!');
                                    }
                                });
                            },
                            getNew: function(obj, destination) {
                                var URL = "<%=request.getContextPath()%>/common.do?cmd=getOrg&destination="+destination;
                                if(obj) {
                                    URL += "&p_org=" + obj.value;
                                    $("org_code").value=obj.value;
                                    $("org_name").value=obj.options[obj.selectedIndex].text;
                                }
                                var rqst = new Ajax.Request(URL, {
                                    onSuccess: function (xmlHttp) {
                                        ReDataShow.jsonData(xmlHttp);
                                    },
                                    onFailure: function (xmlHttp) {
                                        alert('fail!!!');
                                    }
                                });
                            }
                            
                        }
                        
                        var DataShow = {
                            jsonData: function(xmlHttp) {
                                var serverData = xmlHttp.responseText;
                                var evalData = serverData.evalJSON();
                                var k = getObject(evalData.destination);
                                k.options.length=1 // 초기화             
                                for( var i=0; i < evalData.list.length; i++ ) {
                                   var option = new Option();
                                   option.value = evalData.list[i].code;
                                   option.text = evalData.list[i].name;
                                   option.text = " [" + evalData.list[i].code + "] " + evalData.list[i].name;
                    
                                   k.options.add(option);
                                   
                                    if(option.value == '<c:out value="${LISTOP2.ht.os_region}"/>') {
                                       option.selected = true;
                                       var obj = getObject("os_region");
                                       pageAJAXComm.getOrg(obj,"os_distribute");
                                    }
                    
                                    if(option.value == '<c:out value="${LISTOP2.ht.os_distribute}"/>') {
                                       option.selected = true;
                                       var obj = getObject("os_distribute");
                                       pageAJAXComm.getOrg(obj,"os_dealer");
                                    }
                    
                                    if(option.value == '<c:out value="${LISTOP.ht.os_dealer}"/>') {
                                       option.selected = true;
                                       var obj = getObject("os_dealer");
                                        $("org_code").value='<c:out value="${LISTOP2.ht.org_code}"/>';
                                        $("org_name").value='<c:out value="${LISTOP2.ht.org_name}"/>';
                                    }
                                }
                            },
                            setData: function(option) {
                                $("org_code").value=option.value;
                                $("org_name").value=option.options[option.selectedIndex].text;
                                
                            }
                        }
                    
                        var ReDataShow = { //이미 설정되어 있는 경우 
                            jsonData: function(xmlHttp) {
                                var serverData = xmlHttp.responseText;
                                var evalData = serverData.evalJSON();
                                var k = getObject(evalData.destination);
                                k.options.length=1 // 초기화             
                                for( var i=0; i < evalData.list.length; i++ ) {
                                   var option = new Option();
                                   option.value = evalData.list[i].code;
                                   option.text = " [" + evalData.list[i].code + "] " + evalData.list[i].name;
                    
                                   k.options.add(option);
                                }
                            }
                        }
                    
                        Event.observe(window, 'load', os_init);
                    </script>
                            <mf:input type="hidden" name="org_code" value="${LISTOP.ht.org_code}"/>
                            <mf:input type="hidden" name="org_name" value="${LISTOP.ht.org_name}"/>
                            <tr>
                                <th>
                                    <label for="os_region">Region. </label>
                                </th>
                                <td colspan="3">
                                    <select name="os_region" id="os_region" style="WIDTH: 90%;" class="os_selector" onChange="pageAJAXComm.getNew(this,'os_distribute')">
                                        <option value=''>-</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label for="os_region">Distribute. </label>
                                </th>
                                <td colspan="3">
                                    <select name="os_distribute" style="WIDTH: 90%;" class="os_selector" onChange="pageAJAXComm.getNew(this,'os_dealer')">
                                        <option value=''>-</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label for="os_region">Dealer. </label>
                                </th>
                                <td colspan="3">
                                    <select name="os_dealer" style="WIDTH: 90%;" class="os_selector" onChange="DataShow.setData(this)">
                                        <option value=''>-</option>
                                    </select>
                                </td>
                            </tr>
                    <!-- -->
                <tr>
                    <th><mfmt:message bundle="table.maf_user" key="sub_section"/></th>
                    <td><mf:select name="s_sub_section" codeGroup="SUB_SECTION" curValue="${LISTOP.ht.s_sub_section}">
                        <option value="">--</option>
                    </mf:select></td>

                    <th><mfmt:message bundle="table.maf_user" key="userid"/></th>
                    <td><mf:input type="text" name="s_userid" value="${LISTOP.ht.s_userid}" /></td>
                </tr>

            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td>
                        <mf:button onclick="frmSubmit('myform','view');" bundle="button" key="return" />
                        <mf:button onclick="frmSubmit('myform','enrollManual')" bundle="button" key="search" /> 
                        <mf:button onclick="frmReset('myform');" bundle="button" key="reset" /></td>
                </tr>
            </table>
        </div>
        </td>
    </tr>
    <tr>
        <td>
        <div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="15"/>
                <col width="15"/>
                <col width="60"/>
                <col width="80"/>
                <col width="60"/>
                <col width="*"/>
                <col width="80"/>
                <col width="80"/>
                <col width="30"/>
				<thead>
			    <tr>
                    <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_usn')"/></th>
			    	<th># </th>
                    <th><mf:header name="org_cd" sort="true"/></th>
                    <th><mf:header name="pos_nm" sort="true"/></th>
		            <th><mf:header name="userid" sort="true"/></th>
		            <th><mf:header name="nm" sort="true"/></th>
		            <th><mf:header name="mst_section" sort="true"/></th>
                    <th><mf:header name="sub_section" sort="true"/></th>
                    <th><mf:header name="reqcnt" /></th>
			    </tr>
			    </thead>
			    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
                    <td class="center"><mf:input type="checkbox" name="v_usn" value="${item.usn}"/></td>
					<td class="center" ><mh:out value="${((LISTOP.ht.miv_page-1)*LISTOP.ht.miv_pagesize)+status.count}"/></td>
					<td class="center"><mh:out value="${item.org_cd}" td="true"/></td>
                    <td class="center"><mh:out value="${item.pos_nm}" td="true"/></td>
                    <td class="center"><mh:out value="${item.userid}" td="true"/></td>
					<td class="center"><mh:out value="${item.nm}" td="true"/></td>
					<td class="center">&nbsp;<mh:out value="${item.mst_section}" codeGroup="SECTION"/></td>
					<td class="center">&nbsp;<mh:out value="${item.sub_section}" codeGroup="SUB_SECTION"/></td>
					<td class="center"><mh:out value="${item.lecnumb}" td="true"/></td>
				</tr>
			    </c:forEach>
			    </tbody>
		    </table>
	        
		    </div>
        </td>
    </tr>
</table>
</mf:form>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
    </tr>
	<tr>
    	<td align="center"><mf:form action="${control_action}" method='post' name='frmNavi' id='frmNavi'>
            <mf:input type='hidden' name='leccode' value='${leccode}'/>   
            <mf:input type='hidden' name='cmd' value='enrollManual'/>       
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <mf:input type="hidden" name="LISTOP2" value="${LISTOP2.serializeUrl}"/>   
            <jsp:include page="/WEB-INF/layout/lib/navigator_noform.jsp" flush="true"/></mf:form></td>
    </tr>
</table>
