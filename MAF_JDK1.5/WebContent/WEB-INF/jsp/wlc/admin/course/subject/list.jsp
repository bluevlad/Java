<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doWrite(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "add";
		    frm.submit();
		}
	}
	function doDel(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "delete";
		    frm.submit();
		}
	}
	function doView(sjt_cd){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "edit";
			frm.sjt_cd.value = sjt_cd; 
			frm.submit();
		}
	}
    function doSearch() {
		var frm = getObject("myform");
		if(frm) {
   			frm.cmd.value = "list";
			frm.submit();
		}     
    }
	function doExcel(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "excel";
		    frm.submit();
		}
	}
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td><div class="searchContainer">
	        <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'list');return false;">
	        <input type="image" value="test" width="0" height="0" border="0" class="hidden"/>
			<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
			<mf:input type="hidden" name="miv_page" value="1"/>
			<mf:input type="hidden" name="cmd" value="list"/>
			<mf:input type="hidden" name="sjt_cd" value=""/> 
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
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
                                    $("org_code").value='<c:out value="${LISTOP.ht.org_code}"/>';
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
                                   
                                    if(option.value == '<c:out value="${LISTOP.ht.os_region}"/>') {
                                       option.selected = true;
                                       var obj = getObject("os_region");
                                       pageAJAXComm.getOrg(obj,"os_distribute");
                                    }
                    
                                    if(option.value == '<c:out value="${LISTOP.ht.os_distribute}"/>') {
                                       option.selected = true;
                                       var obj = getObject("os_distribute");
                                       pageAJAXComm.getOrg(obj,"os_dealer");
                                    }
                    
                                    if(option.value == '<c:out value="${LISTOP.ht.os_dealer}"/>') {
                                       option.selected = true;
                                       var obj = getObject("os_dealer");
                                        $("org_code").value='<c:out value="${LISTOP.ht.org_code}"/>';
                                        $("org_name").value='<c:out value="${LISTOP.ht.org_name}"/>';
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
                                    <label for="os_region"><mfmt:message bundle="title.common" key="org_region"/></label>
                                </th>
                                <td colspan="3">
                                    <select name="os_region" id="os_region" style="WIDTH: 90%;" class="os_selector" onChange="pageAJAXComm.getNew(this,'os_distribute')">
                                        <option value=''>-</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label for="os_region"><mfmt:message bundle="title.common" key="org_dist"/></label>
                                </th>
                                <td colspan="3">
                                    <select name="os_distribute" style="WIDTH: 90%;" class="os_selector" onChange="pageAJAXComm.getNew(this,'os_dealer')">
                                        <option value=''>-</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label for="os_region"><mfmt:message bundle="title.common" key="org_dealer"/></label>
                                </th>
                                <td colspan="3">
                                    <select name="os_dealer" style="WIDTH: 90%;" class="os_selector" onChange="DataShow.setData(this)">
                                        <option value=''>-</option>
                                    </select>
                                </td>
                            </tr>
                    <!-- -->
				<tr>
					<th><mf:label name="section"/></th>
					<td>
						<mf:select name="s_section" codeGroup="SECTION" curValue="${LISTOP.ht.s_section}" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
					<th><mf:label name="vc_type"/></th>
					<td>
						<mf:select name="s_vc_type" curValue="${LISTOP.ht.s_vc_type}" codeGroup="VC_TYPE" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
				</tr>
				<tr>
					<th><mf:label name="field"/></th>
					<td>
						<mf:select name="s_field" curValue="${LISTOP.ht.s_field}" codeGroup="FIELD" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
					<th><mf:label name="cert_lvl"/></th>
					<td>
						<mf:select name="s_cert_lvl" curValue="${LISTOP.ht.s_cert_lvl}" codeGroup="CERT_LVL" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
				</tr>
				<tr>
					<th><mf:label name="otype"/></th>
					<td>
						<mf:select name="s_otype" curValue="${LISTOP.ht.s_otype}" codeGroup="OTYPE" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
					<th><mf:label name="subject_nm"/></th>
					<td><mf:input name="s_subject_nm" value="${LISTOP.ht.s_subject_nm}" size="40" cssStyle="width:150px"/></td>					
				</tr>
	        </table>
	        <table border="0" cellspacing="0" cellpadding="2" class="searchBtn" width="100%">
	        	<tr>
	        		<td align="right">
		                <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
	                </td>
	        	</tr>
	        </table>
	        </div>    
        </td>
    </tr>
	<tr>
	    <td><div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			<col width="20"/>
			<col width="50"/>
            <col width="80"/>
			<col width="100"/>
			<col width="*"/>
			<col width="150"/>
			<col width="80"/>
			<thead>
			    <tr>
			    	<th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_sjt_cd')"/></th>
			    	<th>#</th>
		            <th><mf:header name="section" sort="true"/><br>
                        <mf:header name="cert_lvl" sort="true"/></th>
                    <th><mf:header name="crs_nm" sort="true"/><br>
                        <mf:header name="sjt_cd" sort="true"/></th>
		            <th><mf:header name="subject_nm" sort="true"/></th>
		            <th><mf:header name="orgn_cd" sort="true"/></th>
                    <th><mf:header name="isuse" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mf:input type="checkbox" name="v_sjt_cd" value="${item.sjt_cd}"/></td>
					<td class="center"><mh:out value="${status.count}"/></td>
					<td class="center"><mh:out value="${item.section}" codeGroup="SECTION" td="true"/><br>
                        <mh:out value="${item.cert_lvl}" codeGroup ="CERT_LVL" td="true"/></td>
                    <td class="center"><mh:out value="${item.crs_nm}" td="true"/><br>
                        <a href='javascript:doView("<c:out value="${item.sjt_cd}"/>");'><mh:out value="${item.sjt_cd}" td="true"/></a></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.sjt_cd}"/>");'><mh:out value="${item.subject_nm}" td="true"/></a></td>
					<td class="center"><mh:out value="${item.org_name_disp}"/></td>
                    <td class="center"><mh:out value="${item.isuse}" codeGroup="ISUSE"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
	        </mf:form> 
		    </div>
	    </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td align="right">
	    	<mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
	    	<mf:button bundle="button" key="delete" onclick="doDel()"/>
	    	<mf:button bundle="button" key="exceldown" onclick="doExcel()"/><br/>
	    	<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
	    </td>
    </tr>
</table>