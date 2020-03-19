<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(org_cd){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "view";
			frm.org_cd.value=org_cd;
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
//-->
</SCRIPT>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td><div class="searchContainer">
        	<h1><mfmt:message bundle="common" key="searchtitle"/></h1>
		   	<mf:form action="${control_action}" method="post" name="myform" id="myform">
			<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
			<mf:input type="hidden" name="miv_page" value="1"/>
			<mf:input type="hidden" name="cmd" value="list"/>
			<mf:input type='hidden' name="org_cd" value=""/> 
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
					<th><mf:label name="org_cd"/></th>
					<td><mf:input type="text" name="s_org_code" value="${LISTOP.ht.s_org_code}"/></td>					
					<th><mf:label name="org_name"/></th>
					<td><mf:input type="text" name="s_org_name" value="${LISTOP.ht.s_org_name}"/></td>
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
			    	<th># </th>
			      	<th><mf:header name="region" sort="true"/> </th>			
		            <th><mf:header name="org_cd" sort="true"/></th>
		            <th><mf:header name="org_name" sort="true"/></th>
		            <th><mf:header name="org_type" sort="true"/></th>
		            <th><mf:header name="nation" sort="true"/></th>
			    </tr>
			    </thead>
			    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr >
					<td class="center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
					<td class="center"><mh:out value="${item.region_name}" td="true"/></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.org_cd}"/>");'><mh:out value="${item.org_cd}" td="true"/></a></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.org_cd}"/>");'><mh:out value="${item.org_name}" td="true"/></a></td>
					<td class="center"><mh:out value="${item.org_type}" codeGroup="ORG_TYPE"/></td>
					<td class="center"><mh:out value="${item.nation}" td="true"/></td>
				</tr>
			    </c:forEach>
			    </tbody>
		    </table>
	        </mf:form>
		    </div>
	    </td>
    </tr>
	<tr>
    	<td align="center">
    		<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
    	</td>
    </tr>
</table>