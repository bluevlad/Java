<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function doWrite(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "addRst";
		    frm.submit();
		}
	}
	
	function doView(exmid){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "view";
			frm.exmid.value=exmid;
			frm.submit();
		}
	}
    
    function doSearch(frm) {
		if(frm) {
   			frm.cmd.value = "userSel";
			frm.miv_page.value = 1;
			return true;
		}     
    }

//-->
</SCRIPT>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />
        <tr>
            <th nowrap><mf:header name="exmid" /></th>
            <td><mh:out value="${item.exmid}" /></td>
            <th nowrap><mf:header name="exmowner" /></th>
            <td><mh:out value="${item.exmowner}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="exmtitle" /></th>
            <td><mh:out value="${item.exmtitle}" /></td>
            <th nowrap><mf:header name="exm_sdat_t1" /></th>
            <td><mh:out value="${item.exm_sdat_t1}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="exm_edat_t1" /></th>
            <td><mh:out value="${item.exm_edat_t1}" /></td>
            <th nowrap><mf:header name="update_dt" /></th>
            <td><mh:out value="${item.update_dt}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="setid" /></th>
            <td><mh:out value="${item.setid}" /></td>
            <th nowrap><mf:header name="active_yn" /></th>
            <td><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" /></td>
        </tr>
</table>
<mf:form action="${control_action}" method="post" name="myform" id="myform"
            onSubmit="return frmSubmit(this,'userSel');return false; ">
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden" />
            <input type="hidden" name="miv_page" value="1" />
            <input type="hidden" name="cmd" value="userSel" />
            <mf:input type='hidden' name='exmid' value='${exmid}'/>        
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
                        <mf:button onclick="frmSubmit('myform','userSel')" bundle="button" key="search" /> 
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
				<thead>
			    <tr>
                    <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_usn')"/></th>
			    	<th># </th>
                    <th><mf:header name="org_name" sort="true"/></th>
                    <th><mf:header name="pos_nm" sort="true"/></th>
		            <th><mf:header name="userid" sort="true"/></th>
		            <th><mf:header name="nm" sort="true"/></th>
		            <th><mf:header name="mst_section" sort="true"/></th>
                    <th><mf:header name="sub_section" sort="true"/></th>
                    <th><mf:header name="reqcnt"/></th>
			    </tr>
			    </thead>
			    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
                    <td class="center"><mf:input type="checkbox" name="v_usn" value="${item.usn}"/></td>
					<td class="center" ><mh:out value="${((LISTOP.ht.miv_page-1)*LISTOP.ht.miv_pagesize)+status.count}"/></td>
					<td class="center"><mh:out value="${item.org_name}" td="true"/></td>
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
            <mf:input type='hidden' name='exmid' value='${exmid}'/>    
             <mf:input type='hidden' name='cmd' value='userSel'/>      
            <jsp:include page="/WEB-INF/layout/lib/navigator_noform.jsp" flush="true"/></mf:form></td>
    </tr>
</table>