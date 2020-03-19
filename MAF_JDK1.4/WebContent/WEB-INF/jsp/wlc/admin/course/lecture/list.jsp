<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.calendar.js"></script>
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
	function doView(leccode){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "view";
			frm.leccode.value = leccode; 
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
	function doCopy(){
		var frm = getObject("myform");
			frm.cmd.value = "copy";
			frm.submit();
	}
	function doUpload(){
		var frm = getObject("myform");
			frm.cmd.value = "upload";
			frm.submit();
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
			<mf:input type="hidden" name="leccode" value=""/> 
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
						<mf:select name="s_section" curValue="${LISTOP.ht.s_section}" codeGroup="SECTION" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
					<th><mf:label name="field"/></th>
					<td><mf:select name="s_field" codeGroup="FIELD" curValue="${LISTOP.ht.s_field}">
                        <option value="">-</option>
                        </mf:select></td>					
				</tr>
				<tr>
					<th><mf:label name="cert_lvl"/></th>
					<td>
						<mf:select name="s_cert_lvl" curValue="${LISTOP.ht.s_cert_lvl}" codeGroup="CERT_LVL" >
						<option value="">-</option>
						</mf:select>
					</td>
					<th><mf:label name="lecstat"/></th>
					<td><mf:select name="s_lecstat" codeGroup="LECSTAT" curValue="${LISTOP.ht.s_lecstat}">
                        <option value="">-</option></mf:select></td>					
				</tr>
				<tr>
					<th><mf:label name="lec_date"/></th>
					<td>
						<mf:input type="text" name="s_lec_sdate" size="10" value="${LISTOP.ht.s_lec_sdate}" onclick="popUpCalendar(this, this, 'yyyymmdd');" /> ~
						<mf:input type="text" name="s_lec_edate" size="10" value="${LISTOP.ht.s_lec_edate}" onclick="popUpCalendar(this, this, 'yyyymmdd');" />
					</td>
					<th><mf:label name="vc_type"/></th>
					<td>
						<mf:select name="s_vc_type" curValue="${LISTOP.ht.s_vc_type}" codeGroup="VC_TYPE" cssStyle="width:150px">
						<option value="">-</option>
						</mf:select>
					</td>					
				</tr>
				<tr>
					<th><mf:label name="lecname"/></th>
					<td colspan="3"><mf:input type="text" name="s_lecname" value="${LISTOP.ht.s_lecname}" cssStyle="width:300px"/></td>					
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
			<thead>
			    <tr>
			    	<th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_leccode')"/></th>

		            <th><mf:header name="section" sort="true"/><br>
                        <mf:header name="grade" sort="true"/></th>
		            <th><mf:header name="field" sort="true"/><br>
                        <mf:header name="orgn_cd" sort="true"/></th>
		            <th><mf:header name="leccode" sort="true"/><br>
		            <mf:header name="lecname" sort="true"/></th>
		            <th><mf:header name="alias_cd" sort="true"/></th>
		            <th><mf:header name="lecstat" sort="true"/></th>
                    <th><mf:header name="otype" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mf:input type="checkbox" name="v_leccode" value="${item.leccode}"/></td>

					<td class="center"><mh:out value="${item.section}" td="true" codeGroup="SECTION"/><br>
                        <mh:out value="${item.cert_lvl}" codeGroup="CERT_LVL" td="true"/></td>
					<td class="center"><mh:out value="${item.field}" codeGroup="FIELD"/><br>
                        <mh:out value="${item.orgn_cd}"/></td>
					<td class="left"><a href='javascript:doView("<c:out value="${item.leccode}"/>");'><mh:out value="${item.leccode}" td="true"/><br>
					   <strong><mh:out value="${item.lecname}" td="true"/></strong></a></td>
					<td class="center"><mh:out value="${item.alias_cd}" td="true"/></td>
					<td class="center"><mh:out value="${item.lecstat}" codeGroup="LECSTAT"/><br>
                            <mh:out value="${item.cnt_lr}"/> / <mh:out value="${item.cnt_lp}"/> / <mh:out value="${item.cnt_le}"/></td>
					<td class="center"><mh:out value="${item.otype}" codeGroup="OTYPE" td="true"/></td>

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
	    	<mf:button bundle="button" key="add" onclick="doWrite()"/>
	    	<mf:button bundle="button" key="delete" onclick="doDel()"/>
	    	<mf:button bundle="button" key="exceldown" onclick="doExcel()"/>
	    </td>
    </tr>
	<tr>
	    <td align="right">
	    	<mf:button bundle="button" key="button.copysubject" onclick="doCopy()" />
	    	<mf:button bundle="button" key="button.lecadd" onclick="doUpload()"/>
	    	<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
	    </td>
    </tr>
</table>
<Strong> #<mf:header name="lecstat"/></strong> = <mh:out value="LR" codeGroup="REQ_STAT"/> / <mh:out value="LP" codeGroup="REQ_STAT"/> / <mh:out value="LE" codeGroup="REQ_STAT"/>
